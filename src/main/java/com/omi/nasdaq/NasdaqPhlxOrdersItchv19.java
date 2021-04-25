package com.omi.nasdaq;

import com.omi.parser.util.BigEndianUtils;
import com.omi.parser.util.LittleEndianUtils;
import com.omi.protocol.Container;
import com.omi.protocol.IBinaryProtocolElement;
import com.omi.protocol.Payload;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.ArrayList;

/**
* Java parser for Nasdaq Phlx Itch Orders 1.9 protocol
* 
* @version 1.0
* @since 04/25/2021 16:10:25
*/

public class NasdaqPhlxOrdersItchv19 {

    ///////////////////////////////////////////////////////////////////////
    // Enum Values
    ///////////////////////////////////////////////////////////////////////

    /**
    * Action Values
    */
    public enum ENUM_ACTION {
        E_A('A', "Add"),
        E_D('D', "Delete");

        public final Character enumType;
        public final String enumValue;

        ENUM_ACTION(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_ACTION> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_ACTION s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_ACTION valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * All Or None Values
    */
    public enum ENUM_ALL_OR_NONE {
        E_Y('Y', "All Or None Order"),
        E_N('N', "Not All Or None Order");

        public final Character enumType;
        public final String enumValue;

        ENUM_ALL_OR_NONE(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_ALL_OR_NONE> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_ALL_OR_NONE s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_ALL_OR_NONE valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Auction Side Values
    */
    public enum ENUM_AUCTION_SIDE {
        E_B('B', "Buy"),
        E_S('S', "Sell"),
        E_ASTERISK('*', "Solicitation Auction");

        public final Character enumType;
        public final String enumValue;

        ENUM_AUCTION_SIDE(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_AUCTION_SIDE> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_AUCTION_SIDE s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_AUCTION_SIDE valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Auction Type Values
    */
    public enum ENUM_AUCTION_TYPE {
        E_C('C', "Cola"),
        E_O('O', "Opening"),
        E_R('R', "Reopening"),
        E_P('P', "Pixl"),
        E_S('S', "Solicitation"),
        E_I('I', "Order Exposure");

        public final Character enumType;
        public final String enumValue;

        ENUM_AUCTION_TYPE(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_AUCTION_TYPE> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_AUCTION_TYPE s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_AUCTION_TYPE valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Current Trading State Values
    */
    public enum ENUM_CURRENT_TRADING_STATE {
        E_H('H', "Halt In Effect"),
        E_T('T', "Phlx Trading Resumed");

        public final Character enumType;
        public final String enumValue;

        ENUM_CURRENT_TRADING_STATE(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_CURRENT_TRADING_STATE> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_CURRENT_TRADING_STATE s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_CURRENT_TRADING_STATE valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Customer Firm Indicator Values
    */
    public enum ENUM_CUSTOMER_FIRM_INDICATOR {
        E_C('C', "Customer Order"),
        E_F('F', "Firm Order"),
        E_M('M', "Onfloor Market Maker"),
        E_B('B', "Broker Dealer Order"),
        E_P('P', "Professional Order"),
        E_SPACE(' ', "Na For Implied Order");

        public final Character enumType;
        public final String enumValue;

        ENUM_CUSTOMER_FIRM_INDICATOR(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_CUSTOMER_FIRM_INDICATOR> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_CUSTOMER_FIRM_INDICATOR s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_CUSTOMER_FIRM_INDICATOR valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Debit Or Credit Values
    */
    public enum ENUM_DEBIT_OR_CREDIT {
        E_D('D', "Net Debit"),
        E_C('C', "Net Credit"),
        E_SPACE(' ', "Even Or Market Order"),
        E_ASTERISK('*', "Anonymous");

        public final Character enumType;
        public final String enumValue;

        ENUM_DEBIT_OR_CREDIT(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_DEBIT_OR_CREDIT> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_DEBIT_OR_CREDIT s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_DEBIT_OR_CREDIT valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Event Code Values
    */
    public enum ENUM_EVENT_CODE {
        E_O('O', "Start Of Messages"),
        E_S('S', "Start Of System Hours"),
        E_Q('Q', "Start Of Opening Process"),
        E_N('N', "Start Of Normal Hours Closing Process"),
        E_L('L', "Start Of Late Hours Closing Process"),
        E_E('E', "End Of System Hours"),
        E_C('C', "End Of Messages"),
        E_W('W', "End Of Wco Early Closing");

        public final Character enumType;
        public final String enumValue;

        ENUM_EVENT_CODE(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_EVENT_CODE> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_EVENT_CODE s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_EVENT_CODE valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Leg Open Close Indicator Values
    */
    public enum ENUM_LEG_OPEN_CLOSE_INDICATOR {
        E_O('O', "Opens Position"),
        E_C('C', "Closes Position"),
        E_SPACE(' ', "Stock Leg");

        public final Character enumType;
        public final String enumValue;

        ENUM_LEG_OPEN_CLOSE_INDICATOR(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_LEG_OPEN_CLOSE_INDICATOR> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_LEG_OPEN_CLOSE_INDICATOR s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_LEG_OPEN_CLOSE_INDICATOR valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Market Qualifier Values
    */
    public enum ENUM_MARKET_QUALIFIER {
        E_O('O', "Opening Order"),
        E_I('I', "Implied Order"),
        E_SPACE(' ', "Na");

        public final Character enumType;
        public final String enumValue;

        ENUM_MARKET_QUALIFIER(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_MARKET_QUALIFIER> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_MARKET_QUALIFIER s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_MARKET_QUALIFIER valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Message Type Values
    */
    public enum ENUM_MESSAGE_TYPE {
        E_S('S', "System Event Message"),
        E_D('D', "Options Directory Message"),
        E_R('R', "Complex Order Strategy Message"),
        E_H('H', "Security Trading Action Message"),
        E_I('I', "Complex Trading Action Message"),
        E_P('P', "Security Open Closed Message"),
        E_Q('Q', "Strategy Open Closed Message"),
        E_O('O', "Simple Order Message"),
        E_X('X', "Complex Order Message"),
        E_A('A', "Auction Notification Message"),
        E_C('C', "Complex Auction Notification Message");

        public final Character enumType;
        public final String enumValue;

        ENUM_MESSAGE_TYPE(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_MESSAGE_TYPE> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_MESSAGE_TYPE s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_MESSAGE_TYPE valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Open Close Indicator Values
    */
    public enum ENUM_OPEN_CLOSE_INDICATOR {
        E_O('O', "Opens Position"),
        E_C('C', "Closes Position"),
        E_SPACE(' ', "Na");

        public final Character enumType;
        public final String enumValue;

        ENUM_OPEN_CLOSE_INDICATOR(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_OPEN_CLOSE_INDICATOR> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_OPEN_CLOSE_INDICATOR s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_OPEN_CLOSE_INDICATOR valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Open State Values
    */
    public enum ENUM_OPEN_STATE {
        E_Y('Y', "Open For Auto Execution"),
        E_N('N', "Closed For Auto Execution");

        public final Character enumType;
        public final String enumValue;

        ENUM_OPEN_STATE(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_OPEN_STATE> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_OPEN_STATE s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_OPEN_STATE valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Option Closing Type Values
    */
    public enum ENUM_OPTION_CLOSING_TYPE {
        E_N('N', "Normal"),
        E_L('L', "Late"),
        E_W('W', "Wco Early Closing");

        public final Character enumType;
        public final String enumValue;

        ENUM_OPTION_CLOSING_TYPE(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_OPTION_CLOSING_TYPE> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_OPTION_CLOSING_TYPE s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_OPTION_CLOSING_TYPE valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Option Type Values
    */
    public enum ENUM_OPTION_TYPE {
        E_C('C', "Call"),
        E_P('P', "Put"),
        E_SPACE(' ', "Stock");

        public final Character enumType;
        public final String enumValue;

        ENUM_OPTION_TYPE(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_OPTION_TYPE> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_OPTION_TYPE s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_OPTION_TYPE valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Order Status Values
    */
    public enum ENUM_ORDER_STATUS {
        E_O('O', "Open"),
        E_F('F', "Filled"),
        E_C('C', "Cancelled"),
        E_R('R', "Renotification");

        public final Character enumType;
        public final String enumValue;

        ENUM_ORDER_STATUS(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_ORDER_STATUS> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_ORDER_STATUS s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_ORDER_STATUS valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Order Type Values
    */
    public enum ENUM_ORDER_TYPE {
        E_M('M', "Market"),
        E_L('L', "Limit"),
        E_ASTERISK('*', "Anonymous");

        public final Character enumType;
        public final String enumValue;

        ENUM_ORDER_TYPE(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_ORDER_TYPE> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_ORDER_TYPE s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_ORDER_TYPE valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Phlx Tradable Values
    */
    public enum ENUM_PHLX_TRADABLE {
        E_Y('Y', "Tradable"),
        E_N('N', "Not Tradable");

        public final Character enumType;
        public final String enumValue;

        ENUM_PHLX_TRADABLE(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_PHLX_TRADABLE> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_PHLX_TRADABLE s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_PHLX_TRADABLE valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Side Values
    */
    public enum ENUM_SIDE {
        E_B('B', "Buy"),
        E_S('S', "Sell"),
        E_ASTERISK('*', "Hidden");

        public final Character enumType;
        public final String enumValue;

        ENUM_SIDE(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_SIDE> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_SIDE s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_SIDE valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Time In Force Values
    */
    public enum ENUM_TIME_IN_FORCE {
        E_D('D', "Day Order"),
        E_G('G', "Gtc"),
        E_I('I', "Ioc");

        public final Character enumType;
        public final String enumValue;

        ENUM_TIME_IN_FORCE(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_TIME_IN_FORCE> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_TIME_IN_FORCE s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_TIME_IN_FORCE valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }


    // Volume: 4 Byte Unsigned Fixed Width Integer
    public static class Volume implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public Volume(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Volume parse(Container container, IBinaryProtocolElement element) {
            return new Volume(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "volume = "+ this.value;
        }
    }

    // Debit Or Credit: 1 Byte Ascii String Enum with 4 values
    public static class DebitOrCredit implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_DEBIT_OR_CREDIT value;

        public DebitOrCredit(ENUM_DEBIT_OR_CREDIT value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static DebitOrCredit parse(Container container, IBinaryProtocolElement element) {
            return new DebitOrCredit(ENUM_DEBIT_OR_CREDIT.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "debitOrCredit = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Auction Side: 1 Byte Ascii String Enum with 3 values
    public static class AuctionSide implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_AUCTION_SIDE value;

        public AuctionSide(ENUM_AUCTION_SIDE value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static AuctionSide parse(Container container, IBinaryProtocolElement element) {
            return new AuctionSide(ENUM_AUCTION_SIDE.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "auctionSide = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Price: 4 Byte Unsigned Fixed Width Integer
    public static class Price implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public Price(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Price parse(Container container, IBinaryProtocolElement element) {
            return new Price(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "price = "+ this.value;
        }
    }

    // Auction Type: 1 Byte Ascii String Enum with 6 values
    public static class AuctionType implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_AUCTION_TYPE value;

        public AuctionType(ENUM_AUCTION_TYPE value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static AuctionType parse(Container container, IBinaryProtocolElement element) {
            return new AuctionType(ENUM_AUCTION_TYPE.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "auctionType = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Auction Id: 4 Byte Unsigned Fixed Width Integer
    public static class AuctionId implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public AuctionId(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static AuctionId parse(Container container, IBinaryProtocolElement element) {
            return new AuctionId(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "auctionId = "+ this.value;
        }
    }

    // Strategy Id: 4 Byte Unsigned Fixed Width Integer
    public static class StrategyId implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public StrategyId(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static StrategyId parse(Container container, IBinaryProtocolElement element) {
            return new StrategyId(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "strategyId = "+ this.value;
        }
    }

    // Nanoseconds: 4 Byte Unsigned Fixed Width Integer
    public static class Nanoseconds implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public Nanoseconds(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Nanoseconds parse(Container container, IBinaryProtocolElement element) {
            return new Nanoseconds(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "nanoseconds = "+ this.value;
        }
    }

    // Seconds: 4 Byte Unsigned Fixed Width Integer
    public static class Seconds implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public Seconds(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Seconds parse(Container container, IBinaryProtocolElement element) {
            return new Seconds(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "seconds = "+ this.value;
        }
    }

    /**
    * Complex Auction Notification Message
    */
    public static class ComplexAuctionNotificationMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Seconds seconds;
        public Nanoseconds nanoseconds;
        public StrategyId strategyId;
        public AuctionId auctionId;
        public AuctionType auctionType;
        public Price price;
        public AuctionSide auctionSide;
        public DebitOrCredit debitOrCredit;
        public Volume volume;

        // constructor for Complex Auction Notification Message
        private ComplexAuctionNotificationMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Complex Auction Notification Message
        public static ComplexAuctionNotificationMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new ComplexAuctionNotificationMessage(parentElement);

            element.seconds = Seconds.parse(container, element);
            element.nanoseconds = Nanoseconds.parse(container, element);
            element.strategyId = StrategyId.parse(container, element);
            element.auctionId = AuctionId.parse(container, element);
            element.auctionType = AuctionType.parse(container, element);
            element.price = Price.parse(container, element);
            element.auctionSide = AuctionSide.parse(container, element);
            element.debitOrCredit = DebitOrCredit.parse(container, element);
            element.volume = Volume.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.seconds).append("\n");
            sb.append(this.nanoseconds).append("\n");
            sb.append(this.strategyId).append("\n");
            sb.append(this.auctionId).append("\n");
            sb.append(this.auctionType).append("\n");
            sb.append(this.price).append("\n");
            sb.append(this.auctionSide).append("\n");
            sb.append(this.debitOrCredit).append("\n");
            sb.append(this.volume).append("\n");
            return sb.toString();
        }
    }

    // Reserved: 4 Byte Unsigned Fixed Width Integer
    public static class Reserved implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public Reserved(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Reserved parse(Container container, IBinaryProtocolElement element) {
            return new Reserved(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "reserved = "+ this.value;
        }
    }

    // Imbalance Volume: 4 Byte Unsigned Fixed Width Integer
    public static class ImbalanceVolume implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public ImbalanceVolume(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static ImbalanceVolume parse(Container container, IBinaryProtocolElement element) {
            return new ImbalanceVolume(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "imbalanceVolume = "+ this.value;
        }
    }

    // Matched Volume: 4 Byte Unsigned Fixed Width Integer
    public static class MatchedVolume implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public MatchedVolume(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static MatchedVolume parse(Container container, IBinaryProtocolElement element) {
            return new MatchedVolume(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "matchedVolume = "+ this.value;
        }
    }

    // Option Type: 1 Byte Ascii String Enum with 3 values
    public static class OptionType implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_OPTION_TYPE value;

        public OptionType(ENUM_OPTION_TYPE value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static OptionType parse(Container container, IBinaryProtocolElement element) {
            return new OptionType(ENUM_OPTION_TYPE.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "optionType = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Explicit Strike Price: 4 Byte Unsigned Fixed Width Integer
    public static class ExplicitStrikePrice implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public ExplicitStrikePrice(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static ExplicitStrikePrice parse(Container container, IBinaryProtocolElement element) {
            return new ExplicitStrikePrice(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "explicitStrikePrice = "+ this.value;
        }
    }

    // Day: 5 Bit
    public static class Day implements IBinaryProtocolElement {
        public static final int LENGTH = 5;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public Day(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Day parse(Container container, IBinaryProtocolElement element) {
            return new Day(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "day = "+ this.value;
        }
    }

    // Month: 4 Bit
    public static class Month implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public Month(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Month parse(Container container, IBinaryProtocolElement element) {
            return new Month(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "month = "+ this.value;
        }
    }

    // Year: 7 Bit
    public static class Year implements IBinaryProtocolElement {
        public static final int LENGTH = 7;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public Year(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Year parse(Container container, IBinaryProtocolElement element) {
            return new Year(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "year = "+ this.value;
        }
    }

    // bit values for Expiration
    public static class ExpirationBits {
        public static final byte YEAR   = (byte)(1 << 2);   
        public static final byte MONTH  = (byte)(1 << 1);   
        public static final byte DAY    = (byte)(1 << 0);   
    }

    // Expiration: Struct of 3 fields
    public static class Expiration implements IBinaryProtocolElement {
        public static final int LENGTH = 2;
        public final IBinaryProtocolElement _parent;

        // bits
        public final boolean  _year; //  0 
        public final boolean  _month; //  1 
        public final boolean  _day;  //  2 

        public Expiration (Short value, IBinaryProtocolElement element) {
            this._parent = element;

            this._year = (value & ExpirationBits.YEAR) != 0;
            this._month = (value & ExpirationBits.MONTH) != 0;
            this._day = (value & ExpirationBits.DAY) != 0;
        }

        public static Expiration parse(Container container, IBinaryProtocolElement element) {
            return new Expiration(BigEndianUtils.toShort(container, LENGTH), element);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Expiration:\n");
            sb.append(StringUtils.repeat('.', 0) + (this._year?"1":"0") + StringUtils.repeat('.', 2) +" // Year\n");
            sb.append(StringUtils.repeat('.', 1) + (this._month?"1":"0") + StringUtils.repeat('.', 1) +" // Month\n");
            sb.append(StringUtils.repeat('.', 2) + (this._day?"1":"0") + StringUtils.repeat('.', 0) +" // Day\n");
            return sb.toString();
        }
    }

    // Security Symbol: 5 Byte Ascii String
    public static class SecuritySymbol implements IBinaryProtocolElement {
        public static final int LENGTH = 5;
        public final IBinaryProtocolElement parent;
        public final String value;

        public SecuritySymbol(String value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static SecuritySymbol parse(Container container, IBinaryProtocolElement element) {
            return new SecuritySymbol(BigEndianUtils.toString(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "securitySymbol = "+ this.value;
        }
    }

    // Option Id: 4 Byte Unsigned Fixed Width Integer
    public static class OptionId implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public OptionId(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static OptionId parse(Container container, IBinaryProtocolElement element) {
            return new OptionId(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "optionId = "+ this.value;
        }
    }

    /**
    * Auction Notification Message
    */
    public static class AuctionNotificationMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Seconds seconds;
        public Nanoseconds nanoseconds;
        public OptionId optionId;
        public SecuritySymbol securitySymbol;
        public Expiration expiration;
        public ExplicitStrikePrice explicitStrikePrice;
        public OptionType optionType;
        public AuctionId auctionId;
        public AuctionType auctionType;
        public Price price;
        public AuctionSide auctionSide;
        public MatchedVolume matchedVolume;
        public ImbalanceVolume imbalanceVolume;
        public Reserved reserved;

        // constructor for Auction Notification Message
        private AuctionNotificationMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Auction Notification Message
        public static AuctionNotificationMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new AuctionNotificationMessage(parentElement);

            element.seconds = Seconds.parse(container, element);
            element.nanoseconds = Nanoseconds.parse(container, element);
            element.optionId = OptionId.parse(container, element);
            element.securitySymbol = SecuritySymbol.parse(container, element);
            element.expiration = Expiration.parse(container, element);
            element.explicitStrikePrice = ExplicitStrikePrice.parse(container, element);
            element.optionType = OptionType.parse(container, element);
            element.auctionId = AuctionId.parse(container, element);
            element.auctionType = AuctionType.parse(container, element);
            element.price = Price.parse(container, element);
            element.auctionSide = AuctionSide.parse(container, element);
            element.matchedVolume = MatchedVolume.parse(container, element);
            element.imbalanceVolume = ImbalanceVolume.parse(container, element);
            element.reserved = Reserved.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.seconds).append("\n");
            sb.append(this.nanoseconds).append("\n");
            sb.append(this.optionId).append("\n");
            sb.append(this.securitySymbol).append("\n");
            sb.append(this.expiration).append("\n");
            sb.append(this.explicitStrikePrice).append("\n");
            sb.append(this.optionType).append("\n");
            sb.append(this.auctionId).append("\n");
            sb.append(this.auctionType).append("\n");
            sb.append(this.price).append("\n");
            sb.append(this.auctionSide).append("\n");
            sb.append(this.matchedVolume).append("\n");
            sb.append(this.imbalanceVolume).append("\n");
            sb.append(this.reserved).append("\n");
            return sb.toString();
        }
    }

    // Leg Ratio: 4 Byte Unsigned Fixed Width Integer
    public static class LegRatio implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public LegRatio(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static LegRatio parse(Container container, IBinaryProtocolElement element) {
            return new LegRatio(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "legRatio = "+ this.value;
        }
    }

    // Side: 1 Byte Ascii String Enum with 3 values
    public static class Side implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_SIDE value;

        public Side(ENUM_SIDE value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Side parse(Container container, IBinaryProtocolElement element) {
            return new Side(ENUM_SIDE.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "side = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Leg Open Close Indicator: 1 Byte Ascii String Enum with 3 values
    public static class LegOpenCloseIndicator implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_LEG_OPEN_CLOSE_INDICATOR value;

        public LegOpenCloseIndicator(ENUM_LEG_OPEN_CLOSE_INDICATOR value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static LegOpenCloseIndicator parse(Container container, IBinaryProtocolElement element) {
            return new LegOpenCloseIndicator(ENUM_LEG_OPEN_CLOSE_INDICATOR.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "legOpenCloseIndicator = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    /**
    * Complex Order Leg
    */
    public static class ComplexOrderLeg implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public LegOpenCloseIndicator legOpenCloseIndicator;
        public OptionId optionId;
        public SecuritySymbol securitySymbol;
        public Expiration expiration;
        public ExplicitStrikePrice explicitStrikePrice;
        public OptionType optionType;
        public Side side;
        public LegRatio legRatio;

        // constructor for Complex Order Leg
        private ComplexOrderLeg(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Complex Order Leg
        public static ComplexOrderLeg parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new ComplexOrderLeg(parentElement);

            element.legOpenCloseIndicator = LegOpenCloseIndicator.parse(container, element);
            element.optionId = OptionId.parse(container, element);
            element.securitySymbol = SecuritySymbol.parse(container, element);
            element.expiration = Expiration.parse(container, element);
            element.explicitStrikePrice = ExplicitStrikePrice.parse(container, element);
            element.optionType = OptionType.parse(container, element);
            element.side = Side.parse(container, element);
            element.legRatio = LegRatio.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.legOpenCloseIndicator).append("\n");
            sb.append(this.optionId).append("\n");
            sb.append(this.securitySymbol).append("\n");
            sb.append(this.expiration).append("\n");
            sb.append(this.explicitStrikePrice).append("\n");
            sb.append(this.optionType).append("\n");
            sb.append(this.side).append("\n");
            sb.append(this.legRatio).append("\n");
            return sb.toString();
        }
    }

    // Number Of Legs: 1 Byte Unsigned Fixed Width Integer
    public static class NumberOfLegs implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public NumberOfLegs(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static NumberOfLegs parse(Container container, IBinaryProtocolElement element) {
            return new NumberOfLegs(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "numberOfLegs = "+ this.value;
        }
    }

    // Underlying Symbol: 13 Byte Ascii String
    public static class UnderlyingSymbol implements IBinaryProtocolElement {
        public static final int LENGTH = 13;
        public final IBinaryProtocolElement parent;
        public final String value;

        public UnderlyingSymbol(String value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static UnderlyingSymbol parse(Container container, IBinaryProtocolElement element) {
            return new UnderlyingSymbol(BigEndianUtils.toString(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "underlyingSymbol = "+ this.value;
        }
    }

    // Customer Firm Indicator: 1 Byte Ascii String Enum with 6 values
    public static class CustomerFirmIndicator implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_CUSTOMER_FIRM_INDICATOR value;

        public CustomerFirmIndicator(ENUM_CUSTOMER_FIRM_INDICATOR value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static CustomerFirmIndicator parse(Container container, IBinaryProtocolElement element) {
            return new CustomerFirmIndicator(ENUM_CUSTOMER_FIRM_INDICATOR.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "customerFirmIndicator = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Time In Force: 1 Byte Ascii String Enum with 3 values
    public static class TimeInForce implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_TIME_IN_FORCE value;

        public TimeInForce(ENUM_TIME_IN_FORCE value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static TimeInForce parse(Container container, IBinaryProtocolElement element) {
            return new TimeInForce(ENUM_TIME_IN_FORCE.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "timeInForce = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // All Or None: 1 Byte Ascii String Enum with 2 values
    public static class AllOrNone implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_ALL_OR_NONE value;

        public AllOrNone(ENUM_ALL_OR_NONE value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static AllOrNone parse(Container container, IBinaryProtocolElement element) {
            return new AllOrNone(ENUM_ALL_OR_NONE.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "allOrNone = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Limit Price: 4 Byte Unsigned Fixed Width Integer
    public static class LimitPrice implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public LimitPrice(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static LimitPrice parse(Container container, IBinaryProtocolElement element) {
            return new LimitPrice(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "limitPrice = "+ this.value;
        }
    }

    // Order Type: 1 Byte Ascii String Enum with 3 values
    public static class OrderType implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_ORDER_TYPE value;

        public OrderType(ENUM_ORDER_TYPE value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static OrderType parse(Container container, IBinaryProtocolElement element) {
            return new OrderType(ENUM_ORDER_TYPE.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "orderType = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Order Status: 1 Byte Ascii String Enum with 4 values
    public static class OrderStatus implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_ORDER_STATUS value;

        public OrderStatus(ENUM_ORDER_STATUS value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static OrderStatus parse(Container container, IBinaryProtocolElement element) {
            return new OrderStatus(ENUM_ORDER_STATUS.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "orderStatus = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Executable Order Volume: 4 Byte Unsigned Fixed Width Integer
    public static class ExecutableOrderVolume implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public ExecutableOrderVolume(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static ExecutableOrderVolume parse(Container container, IBinaryProtocolElement element) {
            return new ExecutableOrderVolume(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "executableOrderVolume = "+ this.value;
        }
    }

    // Original Order Volume: 4 Byte Unsigned Fixed Width Integer
    public static class OriginalOrderVolume implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public OriginalOrderVolume(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static OriginalOrderVolume parse(Container container, IBinaryProtocolElement element) {
            return new OriginalOrderVolume(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "originalOrderVolume = "+ this.value;
        }
    }

    // Order Id: 4 Byte Unsigned Fixed Width Integer
    public static class OrderId implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public OrderId(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static OrderId parse(Container container, IBinaryProtocolElement element) {
            return new OrderId(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "orderId = "+ this.value;
        }
    }

    /**
    * Complex Order Message
    */
    public static class ComplexOrderMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Seconds seconds;
        public Nanoseconds nanoseconds;
        public StrategyId strategyId;
        public OrderId orderId;
        public Side side;
        public OriginalOrderVolume originalOrderVolume;
        public ExecutableOrderVolume executableOrderVolume;
        public OrderStatus orderStatus;
        public OrderType orderType;
        public LimitPrice limitPrice;
        public DebitOrCredit debitOrCredit;
        public AllOrNone allOrNone;
        public TimeInForce timeInForce;
        public CustomerFirmIndicator customerFirmIndicator;
        public UnderlyingSymbol underlyingSymbol;
        public NumberOfLegs numberOfLegs;
        public List<ComplexOrderLeg> complexOrderLegList = new ArrayList<>();

        // constructor for Complex Order Message
        private ComplexOrderMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Complex Order Message
        public static ComplexOrderMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new ComplexOrderMessage(parentElement);

            element.seconds = Seconds.parse(container, element);
            element.nanoseconds = Nanoseconds.parse(container, element);
            element.strategyId = StrategyId.parse(container, element);
            element.orderId = OrderId.parse(container, element);
            element.side = Side.parse(container, element);
            element.originalOrderVolume = OriginalOrderVolume.parse(container, element);
            element.executableOrderVolume = ExecutableOrderVolume.parse(container, element);
            element.orderStatus = OrderStatus.parse(container, element);
            element.orderType = OrderType.parse(container, element);
            element.limitPrice = LimitPrice.parse(container, element);
            element.debitOrCredit = DebitOrCredit.parse(container, element);
            element.allOrNone = AllOrNone.parse(container, element);
            element.timeInForce = TimeInForce.parse(container, element);
            element.customerFirmIndicator = CustomerFirmIndicator.parse(container, element);
            element.underlyingSymbol = UnderlyingSymbol.parse(container, element);
            element.numberOfLegs = NumberOfLegs.parse(container, element);

            // Complex Order Leg: Struct of 8 fields
            for (int i = 0; i < element.numberOfLegs.value; i++) {
                element.complexOrderLegList.add(ComplexOrderLeg.parse(container, element));
            }

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.seconds).append("\n");
            sb.append(this.nanoseconds).append("\n");
            sb.append(this.strategyId).append("\n");
            sb.append(this.orderId).append("\n");
            sb.append(this.side).append("\n");
            sb.append(this.originalOrderVolume).append("\n");
            sb.append(this.executableOrderVolume).append("\n");
            sb.append(this.orderStatus).append("\n");
            sb.append(this.orderType).append("\n");
            sb.append(this.limitPrice).append("\n");
            sb.append(this.debitOrCredit).append("\n");
            sb.append(this.allOrNone).append("\n");
            sb.append(this.timeInForce).append("\n");
            sb.append(this.customerFirmIndicator).append("\n");
            sb.append(this.underlyingSymbol).append("\n");
            sb.append(this.numberOfLegs).append("\n");
            for (var complexOrderLeg: complexOrderLegList) {
                sb.append(complexOrderLeg).append("\n");
            }
            return sb.toString();
        }
    }

    // Open Close Indicator: 1 Byte Ascii String Enum with 3 values
    public static class OpenCloseIndicator implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_OPEN_CLOSE_INDICATOR value;

        public OpenCloseIndicator(ENUM_OPEN_CLOSE_INDICATOR value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static OpenCloseIndicator parse(Container container, IBinaryProtocolElement element) {
            return new OpenCloseIndicator(ENUM_OPEN_CLOSE_INDICATOR.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "openCloseIndicator = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Market Qualifier: 1 Byte Ascii String Enum with 3 values
    public static class MarketQualifier implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_MARKET_QUALIFIER value;

        public MarketQualifier(ENUM_MARKET_QUALIFIER value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static MarketQualifier parse(Container container, IBinaryProtocolElement element) {
            return new MarketQualifier(ENUM_MARKET_QUALIFIER.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "marketQualifier = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    /**
    * Simple Order Message
    */
    public static class SimpleOrderMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Seconds seconds;
        public Nanoseconds nanoseconds;
        public OptionId optionId;
        public SecuritySymbol securitySymbol;
        public Expiration expiration;
        public ExplicitStrikePrice explicitStrikePrice;
        public OptionType optionType;
        public OrderId orderId;
        public Side side;
        public OriginalOrderVolume originalOrderVolume;
        public ExecutableOrderVolume executableOrderVolume;
        public OrderStatus orderStatus;
        public OrderType orderType;
        public MarketQualifier marketQualifier;
        public LimitPrice limitPrice;
        public AllOrNone allOrNone;
        public TimeInForce timeInForce;
        public CustomerFirmIndicator customerFirmIndicator;
        public OpenCloseIndicator openCloseIndicator;

        // constructor for Simple Order Message
        private SimpleOrderMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Simple Order Message
        public static SimpleOrderMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new SimpleOrderMessage(parentElement);

            element.seconds = Seconds.parse(container, element);
            element.nanoseconds = Nanoseconds.parse(container, element);
            element.optionId = OptionId.parse(container, element);
            element.securitySymbol = SecuritySymbol.parse(container, element);
            element.expiration = Expiration.parse(container, element);
            element.explicitStrikePrice = ExplicitStrikePrice.parse(container, element);
            element.optionType = OptionType.parse(container, element);
            element.orderId = OrderId.parse(container, element);
            element.side = Side.parse(container, element);
            element.originalOrderVolume = OriginalOrderVolume.parse(container, element);
            element.executableOrderVolume = ExecutableOrderVolume.parse(container, element);
            element.orderStatus = OrderStatus.parse(container, element);
            element.orderType = OrderType.parse(container, element);
            element.marketQualifier = MarketQualifier.parse(container, element);
            element.limitPrice = LimitPrice.parse(container, element);
            element.allOrNone = AllOrNone.parse(container, element);
            element.timeInForce = TimeInForce.parse(container, element);
            element.customerFirmIndicator = CustomerFirmIndicator.parse(container, element);
            element.openCloseIndicator = OpenCloseIndicator.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.seconds).append("\n");
            sb.append(this.nanoseconds).append("\n");
            sb.append(this.optionId).append("\n");
            sb.append(this.securitySymbol).append("\n");
            sb.append(this.expiration).append("\n");
            sb.append(this.explicitStrikePrice).append("\n");
            sb.append(this.optionType).append("\n");
            sb.append(this.orderId).append("\n");
            sb.append(this.side).append("\n");
            sb.append(this.originalOrderVolume).append("\n");
            sb.append(this.executableOrderVolume).append("\n");
            sb.append(this.orderStatus).append("\n");
            sb.append(this.orderType).append("\n");
            sb.append(this.marketQualifier).append("\n");
            sb.append(this.limitPrice).append("\n");
            sb.append(this.allOrNone).append("\n");
            sb.append(this.timeInForce).append("\n");
            sb.append(this.customerFirmIndicator).append("\n");
            sb.append(this.openCloseIndicator).append("\n");
            return sb.toString();
        }
    }

    // Open State: 1 Byte Ascii String Enum with 2 values
    public static class OpenState implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_OPEN_STATE value;

        public OpenState(ENUM_OPEN_STATE value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static OpenState parse(Container container, IBinaryProtocolElement element) {
            return new OpenState(ENUM_OPEN_STATE.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "openState = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    /**
    * Strategy Open Closed Message
    */
    public static class StrategyOpenClosedMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Seconds seconds;
        public Nanoseconds nanoseconds;
        public StrategyId strategyId;
        public OpenState openState;

        // constructor for Strategy Open Closed Message
        private StrategyOpenClosedMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Strategy Open Closed Message
        public static StrategyOpenClosedMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new StrategyOpenClosedMessage(parentElement);

            element.seconds = Seconds.parse(container, element);
            element.nanoseconds = Nanoseconds.parse(container, element);
            element.strategyId = StrategyId.parse(container, element);
            element.openState = OpenState.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.seconds).append("\n");
            sb.append(this.nanoseconds).append("\n");
            sb.append(this.strategyId).append("\n");
            sb.append(this.openState).append("\n");
            return sb.toString();
        }
    }

    /**
    * Security Open Closed Message
    */
    public static class SecurityOpenClosedMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Seconds seconds;
        public Nanoseconds nanoseconds;
        public OptionId optionId;
        public SecuritySymbol securitySymbol;
        public Expiration expiration;
        public ExplicitStrikePrice explicitStrikePrice;
        public OptionType optionType;
        public OpenState openState;

        // constructor for Security Open Closed Message
        private SecurityOpenClosedMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Security Open Closed Message
        public static SecurityOpenClosedMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new SecurityOpenClosedMessage(parentElement);

            element.seconds = Seconds.parse(container, element);
            element.nanoseconds = Nanoseconds.parse(container, element);
            element.optionId = OptionId.parse(container, element);
            element.securitySymbol = SecuritySymbol.parse(container, element);
            element.expiration = Expiration.parse(container, element);
            element.explicitStrikePrice = ExplicitStrikePrice.parse(container, element);
            element.optionType = OptionType.parse(container, element);
            element.openState = OpenState.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.seconds).append("\n");
            sb.append(this.nanoseconds).append("\n");
            sb.append(this.optionId).append("\n");
            sb.append(this.securitySymbol).append("\n");
            sb.append(this.expiration).append("\n");
            sb.append(this.explicitStrikePrice).append("\n");
            sb.append(this.optionType).append("\n");
            sb.append(this.openState).append("\n");
            return sb.toString();
        }
    }

    // Current Trading State: 1 Byte Ascii String Enum with 2 values
    public static class CurrentTradingState implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_CURRENT_TRADING_STATE value;

        public CurrentTradingState(ENUM_CURRENT_TRADING_STATE value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static CurrentTradingState parse(Container container, IBinaryProtocolElement element) {
            return new CurrentTradingState(ENUM_CURRENT_TRADING_STATE.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "currentTradingState = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    /**
    * Complex Trading Action Message
    */
    public static class ComplexTradingActionMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Seconds seconds;
        public Nanoseconds nanoseconds;
        public StrategyId strategyId;
        public CurrentTradingState currentTradingState;

        // constructor for Complex Trading Action Message
        private ComplexTradingActionMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Complex Trading Action Message
        public static ComplexTradingActionMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new ComplexTradingActionMessage(parentElement);

            element.seconds = Seconds.parse(container, element);
            element.nanoseconds = Nanoseconds.parse(container, element);
            element.strategyId = StrategyId.parse(container, element);
            element.currentTradingState = CurrentTradingState.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.seconds).append("\n");
            sb.append(this.nanoseconds).append("\n");
            sb.append(this.strategyId).append("\n");
            sb.append(this.currentTradingState).append("\n");
            return sb.toString();
        }
    }

    /**
    * Security Trading Action Message
    */
    public static class SecurityTradingActionMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Seconds seconds;
        public Nanoseconds nanoseconds;
        public OptionId optionId;
        public SecuritySymbol securitySymbol;
        public Expiration expiration;
        public ExplicitStrikePrice explicitStrikePrice;
        public OptionType optionType;
        public CurrentTradingState currentTradingState;

        // constructor for Security Trading Action Message
        private SecurityTradingActionMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Security Trading Action Message
        public static SecurityTradingActionMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new SecurityTradingActionMessage(parentElement);

            element.seconds = Seconds.parse(container, element);
            element.nanoseconds = Nanoseconds.parse(container, element);
            element.optionId = OptionId.parse(container, element);
            element.securitySymbol = SecuritySymbol.parse(container, element);
            element.expiration = Expiration.parse(container, element);
            element.explicitStrikePrice = ExplicitStrikePrice.parse(container, element);
            element.optionType = OptionType.parse(container, element);
            element.currentTradingState = CurrentTradingState.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.seconds).append("\n");
            sb.append(this.nanoseconds).append("\n");
            sb.append(this.optionId).append("\n");
            sb.append(this.securitySymbol).append("\n");
            sb.append(this.expiration).append("\n");
            sb.append(this.explicitStrikePrice).append("\n");
            sb.append(this.optionType).append("\n");
            sb.append(this.currentTradingState).append("\n");
            return sb.toString();
        }
    }

    /**
    * Complex Order Strategy Leg
    */
    public static class ComplexOrderStrategyLeg implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public OptionId optionId;
        public SecuritySymbol securitySymbol;
        public Expiration expiration;
        public ExplicitStrikePrice explicitStrikePrice;
        public OptionType optionType;
        public Side side;
        public LegRatio legRatio;

        // constructor for Complex Order Strategy Leg
        private ComplexOrderStrategyLeg(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Complex Order Strategy Leg
        public static ComplexOrderStrategyLeg parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new ComplexOrderStrategyLeg(parentElement);

            element.optionId = OptionId.parse(container, element);
            element.securitySymbol = SecuritySymbol.parse(container, element);
            element.expiration = Expiration.parse(container, element);
            element.explicitStrikePrice = ExplicitStrikePrice.parse(container, element);
            element.optionType = OptionType.parse(container, element);
            element.side = Side.parse(container, element);
            element.legRatio = LegRatio.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.optionId).append("\n");
            sb.append(this.securitySymbol).append("\n");
            sb.append(this.expiration).append("\n");
            sb.append(this.explicitStrikePrice).append("\n");
            sb.append(this.optionType).append("\n");
            sb.append(this.side).append("\n");
            sb.append(this.legRatio).append("\n");
            return sb.toString();
        }
    }

    // Action: 1 Byte Ascii String Enum with 2 values
    public static class Action implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_ACTION value;

        public Action(ENUM_ACTION value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Action parse(Container container, IBinaryProtocolElement element) {
            return new Action(ENUM_ACTION.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "action = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Source: 1 Byte Unsigned Fixed Width Integer
    public static class Source implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public Source(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Source parse(Container container, IBinaryProtocolElement element) {
            return new Source(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "source = "+ this.value;
        }
    }

    /**
    * Complex Order Strategy Message
    */
    public static class ComplexOrderStrategyMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Seconds seconds;
        public Nanoseconds nanoseconds;
        public StrategyId strategyId;
        public Source source;
        public UnderlyingSymbol underlyingSymbol;
        public Action action;
        public NumberOfLegs numberOfLegs;
        public List<ComplexOrderStrategyLeg> complexOrderStrategyLegList = new ArrayList<>();

        // constructor for Complex Order Strategy Message
        private ComplexOrderStrategyMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Complex Order Strategy Message
        public static ComplexOrderStrategyMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new ComplexOrderStrategyMessage(parentElement);

            element.seconds = Seconds.parse(container, element);
            element.nanoseconds = Nanoseconds.parse(container, element);
            element.strategyId = StrategyId.parse(container, element);
            element.source = Source.parse(container, element);
            element.underlyingSymbol = UnderlyingSymbol.parse(container, element);
            element.action = Action.parse(container, element);
            element.numberOfLegs = NumberOfLegs.parse(container, element);

            // Complex Order Strategy Leg: Struct of 7 fields
            for (int i = 0; i < element.numberOfLegs.value; i++) {
                element.complexOrderStrategyLegList.add(ComplexOrderStrategyLeg.parse(container, element));
            }

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.seconds).append("\n");
            sb.append(this.nanoseconds).append("\n");
            sb.append(this.strategyId).append("\n");
            sb.append(this.source).append("\n");
            sb.append(this.underlyingSymbol).append("\n");
            sb.append(this.action).append("\n");
            sb.append(this.numberOfLegs).append("\n");
            for (var complexOrderStrategyLeg: complexOrderStrategyLegList) {
                sb.append(complexOrderStrategyLeg).append("\n");
            }
            return sb.toString();
        }
    }

    // Phlx Tradable: 1 Byte Ascii String Enum with 2 values
    public static class PhlxTradable implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_PHLX_TRADABLE value;

        public PhlxTradable(ENUM_PHLX_TRADABLE value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static PhlxTradable parse(Container container, IBinaryProtocolElement element) {
            return new PhlxTradable(ENUM_PHLX_TRADABLE.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "phlxTradable = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Option Closing Type: 1 Byte Ascii String Enum with 3 values
    public static class OptionClosingType implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_OPTION_CLOSING_TYPE value;

        public OptionClosingType(ENUM_OPTION_CLOSING_TYPE value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static OptionClosingType parse(Container container, IBinaryProtocolElement element) {
            return new OptionClosingType(ENUM_OPTION_CLOSING_TYPE.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "optionClosingType = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    /**
    * Options Directory Message
    */
    public static class OptionsDirectoryMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Seconds seconds;
        public Nanoseconds nanoseconds;
        public OptionId optionId;
        public SecuritySymbol securitySymbol;
        public Expiration expiration;
        public ExplicitStrikePrice explicitStrikePrice;
        public OptionType optionType;
        public Source source;
        public UnderlyingSymbol underlyingSymbol;
        public OptionClosingType optionClosingType;
        public PhlxTradable phlxTradable;

        // constructor for Options Directory Message
        private OptionsDirectoryMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Options Directory Message
        public static OptionsDirectoryMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new OptionsDirectoryMessage(parentElement);

            element.seconds = Seconds.parse(container, element);
            element.nanoseconds = Nanoseconds.parse(container, element);
            element.optionId = OptionId.parse(container, element);
            element.securitySymbol = SecuritySymbol.parse(container, element);
            element.expiration = Expiration.parse(container, element);
            element.explicitStrikePrice = ExplicitStrikePrice.parse(container, element);
            element.optionType = OptionType.parse(container, element);
            element.source = Source.parse(container, element);
            element.underlyingSymbol = UnderlyingSymbol.parse(container, element);
            element.optionClosingType = OptionClosingType.parse(container, element);
            element.phlxTradable = PhlxTradable.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.seconds).append("\n");
            sb.append(this.nanoseconds).append("\n");
            sb.append(this.optionId).append("\n");
            sb.append(this.securitySymbol).append("\n");
            sb.append(this.expiration).append("\n");
            sb.append(this.explicitStrikePrice).append("\n");
            sb.append(this.optionType).append("\n");
            sb.append(this.source).append("\n");
            sb.append(this.underlyingSymbol).append("\n");
            sb.append(this.optionClosingType).append("\n");
            sb.append(this.phlxTradable).append("\n");
            return sb.toString();
        }
    }

    // Version: 1 Byte Unsigned Fixed Width Integer
    public static class Version implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public Version(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Version parse(Container container, IBinaryProtocolElement element) {
            return new Version(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "version = "+ this.value;
        }
    }

    // Event Code: 1 Byte Ascii String Enum with 8 values
    public static class EventCode implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_EVENT_CODE value;

        public EventCode(ENUM_EVENT_CODE value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static EventCode parse(Container container, IBinaryProtocolElement element) {
            return new EventCode(ENUM_EVENT_CODE.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "eventCode = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    /**
    * System Event Message
    */
    public static class SystemEventMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Seconds seconds;
        public Nanoseconds nanoseconds;
        public EventCode eventCode;
        public Version version;

        // constructor for System Event Message
        private SystemEventMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for System Event Message
        public static SystemEventMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new SystemEventMessage(parentElement);

            element.seconds = Seconds.parse(container, element);
            element.nanoseconds = Nanoseconds.parse(container, element);
            element.eventCode = EventCode.parse(container, element);
            element.version = Version.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.seconds).append("\n");
            sb.append(this.nanoseconds).append("\n");
            sb.append(this.eventCode).append("\n");
            sb.append(this.version).append("\n");
            return sb.toString();
        }
    }

    /**
    * Parse Payload
    */
    public static Payload parsePayload(Container container, ENUM_MESSAGE_TYPE messageType) {
        
        // -- parsing System Event Message
        if (messageType.enumType == 'S') {
            return SystemEventMessage.parse(container, null);
        }
        // -- parsing Options Directory Message
        if (messageType.enumType == 'D') {
            return OptionsDirectoryMessage.parse(container, null);
        }
        // -- parsing Complex Order Strategy Message
        if (messageType.enumType == 'R') {
            return ComplexOrderStrategyMessage.parse(container, null);
        }
        // -- parsing Security Trading Action Message
        if (messageType.enumType == 'H') {
            return SecurityTradingActionMessage.parse(container, null);
        }
        // -- parsing Complex Trading Action Message
        if (messageType.enumType == 'I') {
            return ComplexTradingActionMessage.parse(container, null);
        }
        // -- parsing Security Open Closed Message
        if (messageType.enumType == 'P') {
            return SecurityOpenClosedMessage.parse(container, null);
        }
        // -- parsing Strategy Open Closed Message
        if (messageType.enumType == 'Q') {
            return StrategyOpenClosedMessage.parse(container, null);
        }
        // -- parsing Simple Order Message
        if (messageType.enumType == 'O') {
            return SimpleOrderMessage.parse(container, null);
        }
        // -- parsing Complex Order Message
        if (messageType.enumType == 'X') {
            return ComplexOrderMessage.parse(container, null);
        }
        // -- parsing Auction Notification Message
        if (messageType.enumType == 'A') {
            return AuctionNotificationMessage.parse(container, null);
        }
        // -- parsing Complex Auction Notification Message
        if (messageType.enumType == 'C') {
            return ComplexAuctionNotificationMessage.parse(container, null);
        }

        return null;
    }

    // Message Type: 1 Byte Ascii String Enum with 11 values
    public static class MessageType implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_MESSAGE_TYPE value;

        public MessageType(ENUM_MESSAGE_TYPE value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static MessageType parse(Container container, IBinaryProtocolElement element) {
            return new MessageType(ENUM_MESSAGE_TYPE.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "messageType = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Length: 2 Byte Unsigned Fixed Width Integer
    public static class Length implements IBinaryProtocolElement {
        public static final int LENGTH = 2;
        public final IBinaryProtocolElement parent;
        public final Short value;

        public Length(Short value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Length parse(Container container, IBinaryProtocolElement element) {
            return new Length(BigEndianUtils.toShort(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "length = "+ this.value;
        }
    }

    /**
    * Message Header
    */
    public static class MessageHeader implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Length length;
        public MessageType messageType;

        // constructor for Message Header
        private MessageHeader(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Message Header
        public static MessageHeader parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new MessageHeader(parentElement);

            element.length = Length.parse(container, element);
            element.messageType = MessageType.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.length).append("\n");
            sb.append(this.messageType).append("\n");
            return sb.toString();
        }
    }

    /**
    * Message
    */
    public static class Message implements IBinaryProtocolElement {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public MessageHeader messageHeader;
        public Payload payload;

        // constructor for Message
        private Message(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Message
        public static Message parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new Message(parentElement);

            element.messageHeader = MessageHeader.parse(container, element);
            element.payload = parsePayload(container, element.messageHeader.messageType.value);

            return element;
        }

        public String toString() {
            return "messageHeader=" + this.messageHeader.toString() +"\n"
				+ "payload = "+ this.payload.toString();
        }
    }

    // Count: 2 Byte Unsigned Fixed Width Integer
    public static class Count implements IBinaryProtocolElement {
        public static final int LENGTH = 2;
        public final IBinaryProtocolElement parent;
        public final Short value;

        public Count(Short value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Count parse(Container container, IBinaryProtocolElement element) {
            return new Count(LittleEndianUtils.toShort(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "count = "+ this.value;
        }
    }

    // Sequence: 4 Byte Unsigned Fixed Width Integer
    public static class Sequence implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public Sequence(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Sequence parse(Container container, IBinaryProtocolElement element) {
            return new Sequence(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "sequence = "+ this.value;
        }
    }

    // Session: 10 Byte Ascii String
    public static class Session implements IBinaryProtocolElement {
        public static final int LENGTH = 10;
        public final IBinaryProtocolElement parent;
        public final String value;

        public Session(String value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Session parse(Container container, IBinaryProtocolElement element) {
            return new Session(BigEndianUtils.toString(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "session = "+ this.value;
        }
    }

    /**
    * Packet Header
    */
    public static class PacketHeader implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Session session;
        public Sequence sequence;
        public Count count;

        // constructor for Packet Header
        private PacketHeader(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Packet Header
        public static PacketHeader parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new PacketHeader(parentElement);

            element.session = Session.parse(container, element);
            element.sequence = Sequence.parse(container, element);
            element.count = Count.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.session).append("\n");
            sb.append(this.sequence).append("\n");
            sb.append(this.count).append("\n");
            return sb.toString();
        }
    }

    /**
    * parse root Packet
    */
    public static class Packet implements Payload {

        // parent element
        private final IBinaryProtocolElement parent;

        // fields
        public PacketHeader packetHeader;
        public List<Message> messageList = new ArrayList<>();

        // constructor for Packet
        private Packet(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Packet
        public static Packet parse(byte[] bytes) {

            // byte container
            final Container container = new Container(bytes);

            // parent is null
            var element = new Packet(null);

            element.packetHeader = PacketHeader.parse(container, element);

            // Message: Struct of 2 fields
            while (!container.parsed()) {
                element.messageList.add(Message.parse(container, element));
            }

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.packetHeader).append("\n");
            for (var message: messageList) {
                sb.append(message).append("\n");
            }
            return sb.toString();
        }
    }

}
