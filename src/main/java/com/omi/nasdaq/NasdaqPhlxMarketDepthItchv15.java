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
* Java parser for Nasdaq Phlx Itch MarketDepth 1.5 protocol
* 
* @version 1.0
* @since 04/25/2021 16:10:25
*/

public class NasdaqPhlxMarketDepthItchv15 {

    ///////////////////////////////////////////////////////////////////////
    // Enum Values
    ///////////////////////////////////////////////////////////////////////

    /**
    * Auction Type Values
    */
    public enum ENUM_AUCTION_TYPE {
        E_O('O', "Opening"),
        E_R('R', "Reopening"),
        E_I('I', "Exposure");

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
    * Change Reason Values
    */
    public enum ENUM_CHANGE_REASON {
        E_U('U', "User"),
        E_R('R', "Reprice"),
        E_S('S', "Suspend"),
        E_E('E', "Exhausted");

        public final Character enumType;
        public final String enumValue;

        ENUM_CHANGE_REASON(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_CHANGE_REASON> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_CHANGE_REASON s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_CHANGE_REASON valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Cross Type Values
    */
    public enum ENUM_CROSS_TYPE {
        E_O('O', "Opening Reopening");

        public final Character enumType;
        public final String enumValue;

        ENUM_CROSS_TYPE(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_CROSS_TYPE> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_CROSS_TYPE s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_CROSS_TYPE valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Current Trading State Values
    */
    public enum ENUM_CURRENT_TRADING_STATE {
        E_H('H', "Halt"),
        E_T('T', "Trading"),
        E_B('B', "Buy Side Trading Suspended Exhausted"),
        E_S('S', "Sell Side Trading Suspended Exhausted");

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
    * Customer Indicator Values
    */
    public enum ENUM_CUSTOMER_INDICATOR {
        E_C('C', "Customer"),
        E_F('F', "Firm"),
        E_M('M', "Onfloor"),
        E_P('P', "Professional"),
        E_B('B', "Non Phlx");

        public final Character enumType;
        public final String enumValue;

        ENUM_CUSTOMER_INDICATOR(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_CUSTOMER_INDICATOR> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_CUSTOMER_INDICATOR s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_CUSTOMER_INDICATOR valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Imbalance Direction Values
    */
    public enum ENUM_IMBALANCE_DIRECTION {
        E_B('B', "Buy"),
        E_S('S', "Sell");

        public final Character enumType;
        public final String enumValue;

        ENUM_IMBALANCE_DIRECTION(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_IMBALANCE_DIRECTION> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_IMBALANCE_DIRECTION s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_IMBALANCE_DIRECTION valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Market Side Values
    */
    public enum ENUM_MARKET_SIDE {
        E_B('B', "Buy"),
        E_S('S', "Sell"),
        E_A('A', "Buy"),
        //E_B('B', "Sell"), // Typo?
        E_M('M', "Buy"),
        E_N('N', "Sell");

        public final Character enumType;
        public final String enumValue;

        ENUM_MARKET_SIDE(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_MARKET_SIDE> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_MARKET_SIDE s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_MARKET_SIDE valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Message Type Values
    */
    public enum ENUM_MESSAGE_TYPE {
        E_T('T', "Seconds Message"),
        E_S('S', "System Event Message"),
        E_L('L', "Base Reference Message"),
        E_R('R', "Option Directory Message"),
        E_H('H', "Trading Action Message"),
        E_O('O', "Security Open Message"),
        E_a('a', "Add Order Message Short Form"),
        E_A('A', "Add Order Message Long Form"),
        E_j('j', "Add Quote Message Short Form"),
        E_J('J', "Add Quote Message Long Form"),
        E_E('E', "Single Side Executed Message"),
        E_C('C', "Single Side Executed With Price Message"),
        E_X('X', "Single Side Cancel Message"),
        E_u('u', "Single Side Replace Message Short Form"),
        E_U('U', "Single Side Replace Message Long Form"),
        E_v('v', "Order Replace Message Short Form"),
        E_V('V', "Single Side Replace Long Form Message"),
        E_D('D', "Single Side Delete Message"),
        E_G('G', "Single Side Update Message"),
        E_k('k', "Quote Replace Short Form Message"),
        E_K('K', "Quote Replace Long Form Message"),
        E_Y('Y', "Quote Delete Message"),
        E_Z('Z', "Block Delete Message"),
        E_P('P', "Non Auction Options Trade Message"),
        E_Q('Q', "Options Cross Trade Message"),
        E_B('B', "Broken Trade Order Execution Message"),
        E_I('I', "Auction Notification Message");

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
    * Mpv Values
    */
    public enum ENUM_MPV {
        E_E('E', "Everywhere"),
        E_S('S', "Scaled"),
        E_P('P', "Pilot");

        public final Character enumType;
        public final String enumValue;

        ENUM_MPV(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_MPV> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_MPV s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_MPV valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Open State Values
    */
    public enum ENUM_OPEN_STATE {
        E_Y('Y', "Open"),
        E_N('N', "Closed");

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
    * Option Type Values
    */
    public enum ENUM_OPTION_TYPE {
        E_C('C', "Call"),
        E_P('P', "Put");

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
    * Options Closing Type Values
    */
    public enum ENUM_OPTIONS_CLOSING_TYPE {
        E_N('N', "Normal"),
        E_L('L', "Late"),
        E_W('W', "Wco Early Closing");

        public final Character enumType;
        public final String enumValue;

        ENUM_OPTIONS_CLOSING_TYPE(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_OPTIONS_CLOSING_TYPE> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_OPTIONS_CLOSING_TYPE s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_OPTIONS_CLOSING_TYPE valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Printable Values
    */
    public enum ENUM_PRINTABLE {
        E_N('N', "Nonprintable"),
        E_Y('Y', "Printable");

        public final Character enumType;
        public final String enumValue;

        ENUM_PRINTABLE(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_PRINTABLE> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_PRINTABLE s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_PRINTABLE valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Tradable Values
    */
    public enum ENUM_TRADABLE {
        E_Y('Y', "Tradable"),
        E_N('N', "Not Tradable");

        public final Character enumType;
        public final String enumValue;

        ENUM_TRADABLE(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_TRADABLE> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_TRADABLE s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_TRADABLE valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Trade Indicator Values
    */
    public enum ENUM_TRADE_INDICATOR {
        E_O('O', "Non Displayable"),
        E_C('C', "Complex"),
        E_P('P', "Pixl");

        public final Character enumType;
        public final String enumValue;

        ENUM_TRADE_INDICATOR(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_TRADE_INDICATOR> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_TRADE_INDICATOR s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_TRADE_INDICATOR valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }


    // Reserved: 3 Byte Unsigned Fixed Width Integer
    public static class Reserved implements IBinaryProtocolElement {
        public static final int LENGTH = 3;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public Reserved(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Reserved parse(Container container, IBinaryProtocolElement element) {
            return new Reserved(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "reserved = "+ this.value;
        }
    }

    // Customer Indicator: 1 Byte Ascii String Enum with 5 values
    public static class CustomerIndicator implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_CUSTOMER_INDICATOR value;

        public CustomerIndicator(ENUM_CUSTOMER_INDICATOR value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static CustomerIndicator parse(Container container, IBinaryProtocolElement element) {
            return new CustomerIndicator(ENUM_CUSTOMER_INDICATOR.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "customerIndicator = "+ this.value.enumType +" ("+ this.value.enumValue +")";
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

    // Imbalance Price: 4 Byte Unsigned Fixed Width Integer
    public static class ImbalancePrice implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public ImbalancePrice(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static ImbalancePrice parse(Container container, IBinaryProtocolElement element) {
            return new ImbalancePrice(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "imbalancePrice = "+ this.value;
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

    // Imbalance Direction: 1 Byte Ascii String Enum with 2 values
    public static class ImbalanceDirection implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_IMBALANCE_DIRECTION value;

        public ImbalanceDirection(ENUM_IMBALANCE_DIRECTION value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static ImbalanceDirection parse(Container container, IBinaryProtocolElement element) {
            return new ImbalanceDirection(ENUM_IMBALANCE_DIRECTION.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "imbalanceDirection = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Paired Contracts: 4 Byte Unsigned Fixed Width Integer
    public static class PairedContracts implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public PairedContracts(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static PairedContracts parse(Container container, IBinaryProtocolElement element) {
            return new PairedContracts(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "pairedContracts = "+ this.value;
        }
    }

    // Auction Type: 1 Byte Ascii String Enum with 3 values
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

    // Timestamp: 4 Byte Unsigned Fixed Width Integer
    public static class Timestamp implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public Timestamp(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Timestamp parse(Container container, IBinaryProtocolElement element) {
            return new Timestamp(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "timestamp = "+ this.value;
        }
    }

    /**
    * Auction Notification Message
    */
    public static class AuctionNotificationMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public AuctionId auctionId;
        public AuctionType auctionType;
        public PairedContracts pairedContracts;
        public ImbalanceDirection imbalanceDirection;
        public OptionId optionId;
        public ImbalancePrice imbalancePrice;
        public ImbalanceVolume imbalanceVolume;
        public CustomerIndicator customerIndicator;
        public Reserved reserved;

        // constructor for Auction Notification Message
        private AuctionNotificationMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Auction Notification Message
        public static AuctionNotificationMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new AuctionNotificationMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.auctionId = AuctionId.parse(container, element);
            element.auctionType = AuctionType.parse(container, element);
            element.pairedContracts = PairedContracts.parse(container, element);
            element.imbalanceDirection = ImbalanceDirection.parse(container, element);
            element.optionId = OptionId.parse(container, element);
            element.imbalancePrice = ImbalancePrice.parse(container, element);
            element.imbalanceVolume = ImbalanceVolume.parse(container, element);
            element.customerIndicator = CustomerIndicator.parse(container, element);
            element.reserved = Reserved.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.auctionId).append("\n");
            sb.append(this.auctionType).append("\n");
            sb.append(this.pairedContracts).append("\n");
            sb.append(this.imbalanceDirection).append("\n");
            sb.append(this.optionId).append("\n");
            sb.append(this.imbalancePrice).append("\n");
            sb.append(this.imbalanceVolume).append("\n");
            sb.append(this.customerIndicator).append("\n");
            sb.append(this.reserved).append("\n");
            return sb.toString();
        }
    }

    // Match Number: 4 Byte Unsigned Fixed Width Integer
    public static class MatchNumber implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public MatchNumber(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static MatchNumber parse(Container container, IBinaryProtocolElement element) {
            return new MatchNumber(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "matchNumber = "+ this.value;
        }
    }

    // Cross Number: 4 Byte Unsigned Fixed Width Integer
    public static class CrossNumber implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public CrossNumber(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static CrossNumber parse(Container container, IBinaryProtocolElement element) {
            return new CrossNumber(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "crossNumber = "+ this.value;
        }
    }

    /**
    * Broken Trade Order Execution Message
    */
    public static class BrokenTradeOrderExecutionMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public CrossNumber crossNumber;
        public MatchNumber matchNumber;

        // constructor for Broken Trade Order Execution Message
        private BrokenTradeOrderExecutionMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Broken Trade Order Execution Message
        public static BrokenTradeOrderExecutionMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new BrokenTradeOrderExecutionMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.crossNumber = CrossNumber.parse(container, element);
            element.matchNumber = MatchNumber.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.crossNumber).append("\n");
            sb.append(this.matchNumber).append("\n");
            return sb.toString();
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

    // Cross Type: 1 Byte Ascii String Enum with 1 values
    public static class CrossType implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_CROSS_TYPE value;

        public CrossType(ENUM_CROSS_TYPE value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static CrossType parse(Container container, IBinaryProtocolElement element) {
            return new CrossType(ENUM_CROSS_TYPE.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "crossType = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    /**
    * Options Cross Trade Message
    */
    public static class OptionsCrossTradeMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public OptionId optionId;
        public CrossNumber crossNumber;
        public MatchNumber matchNumber;
        public CrossType crossType;
        public Price price;
        public Volume volume;

        // constructor for Options Cross Trade Message
        private OptionsCrossTradeMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Options Cross Trade Message
        public static OptionsCrossTradeMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new OptionsCrossTradeMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.optionId = OptionId.parse(container, element);
            element.crossNumber = CrossNumber.parse(container, element);
            element.matchNumber = MatchNumber.parse(container, element);
            element.crossType = CrossType.parse(container, element);
            element.price = Price.parse(container, element);
            element.volume = Volume.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.optionId).append("\n");
            sb.append(this.crossNumber).append("\n");
            sb.append(this.matchNumber).append("\n");
            sb.append(this.crossType).append("\n");
            sb.append(this.price).append("\n");
            sb.append(this.volume).append("\n");
            return sb.toString();
        }
    }

    // Trade Indicator: 1 Byte Ascii String Enum with 3 values
    public static class TradeIndicator implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_TRADE_INDICATOR value;

        public TradeIndicator(ENUM_TRADE_INDICATOR value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static TradeIndicator parse(Container container, IBinaryProtocolElement element) {
            return new TradeIndicator(ENUM_TRADE_INDICATOR.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "tradeIndicator = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    /**
    * Non Auction Options Trade Message
    */
    public static class NonAuctionOptionsTradeMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public TradeIndicator tradeIndicator;
        public OptionId optionId;
        public CrossNumber crossNumber;
        public MatchNumber matchNumber;
        public Price price;
        public Volume volume;

        // constructor for Non Auction Options Trade Message
        private NonAuctionOptionsTradeMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Non Auction Options Trade Message
        public static NonAuctionOptionsTradeMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new NonAuctionOptionsTradeMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.tradeIndicator = TradeIndicator.parse(container, element);
            element.optionId = OptionId.parse(container, element);
            element.crossNumber = CrossNumber.parse(container, element);
            element.matchNumber = MatchNumber.parse(container, element);
            element.price = Price.parse(container, element);
            element.volume = Volume.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.tradeIndicator).append("\n");
            sb.append(this.optionId).append("\n");
            sb.append(this.crossNumber).append("\n");
            sb.append(this.matchNumber).append("\n");
            sb.append(this.price).append("\n");
            sb.append(this.volume).append("\n");
            return sb.toString();
        }
    }

    // Cancelled Reference Number Delta: 4 Byte Unsigned Fixed Width Integer
    public static class CancelledReferenceNumberDelta implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public CancelledReferenceNumberDelta(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static CancelledReferenceNumberDelta parse(Container container, IBinaryProtocolElement element) {
            return new CancelledReferenceNumberDelta(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "cancelledReferenceNumberDelta = "+ this.value;
        }
    }

    // Number Of Reference Number Deltas: 2 Byte Unsigned Fixed Width Integer
    public static class NumberOfReferenceNumberDeltas implements IBinaryProtocolElement {
        public static final int LENGTH = 2;
        public final IBinaryProtocolElement parent;
        public final Short value;

        public NumberOfReferenceNumberDeltas(Short value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static NumberOfReferenceNumberDeltas parse(Container container, IBinaryProtocolElement element) {
            return new NumberOfReferenceNumberDeltas(BigEndianUtils.toShort(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "numberOfReferenceNumberDeltas = "+ this.value;
        }
    }

    /**
    * Block Delete Message
    */
    public static class BlockDeleteMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public NumberOfReferenceNumberDeltas numberOfReferenceNumberDeltas;
        public List<CancelledReferenceNumberDelta> cancelledReferenceNumberDeltaList = new ArrayList<>();

        // constructor for Block Delete Message
        private BlockDeleteMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Block Delete Message
        public static BlockDeleteMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new BlockDeleteMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.numberOfReferenceNumberDeltas = NumberOfReferenceNumberDeltas.parse(container, element);

            // Cancelled Reference Number Delta: 4 Byte Unsigned Fixed Width Integer
            for (int i = 0; i < element.numberOfReferenceNumberDeltas.value; i++) {
                element.cancelledReferenceNumberDeltaList.add(CancelledReferenceNumberDelta.parse(container, element));
            }

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.numberOfReferenceNumberDeltas).append("\n");
            for (var cancelledReferenceNumberDelta: cancelledReferenceNumberDeltaList) {
                sb.append(cancelledReferenceNumberDelta).append("\n");
            }
            return sb.toString();
        }
    }

    // Ask Reference Number Delta: 4 Byte Unsigned Fixed Width Integer
    public static class AskReferenceNumberDelta implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public AskReferenceNumberDelta(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static AskReferenceNumberDelta parse(Container container, IBinaryProtocolElement element) {
            return new AskReferenceNumberDelta(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "askReferenceNumberDelta = "+ this.value;
        }
    }

    // Bid Reference Number Delta: 4 Byte Unsigned Fixed Width Integer
    public static class BidReferenceNumberDelta implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public BidReferenceNumberDelta(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static BidReferenceNumberDelta parse(Container container, IBinaryProtocolElement element) {
            return new BidReferenceNumberDelta(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "bidReferenceNumberDelta = "+ this.value;
        }
    }

    /**
    * Quote Delete Message
    */
    public static class QuoteDeleteMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public BidReferenceNumberDelta bidReferenceNumberDelta;
        public AskReferenceNumberDelta askReferenceNumberDelta;

        // constructor for Quote Delete Message
        private QuoteDeleteMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Quote Delete Message
        public static QuoteDeleteMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new QuoteDeleteMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.bidReferenceNumberDelta = BidReferenceNumberDelta.parse(container, element);
            element.askReferenceNumberDelta = AskReferenceNumberDelta.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.bidReferenceNumberDelta).append("\n");
            sb.append(this.askReferenceNumberDelta).append("\n");
            return sb.toString();
        }
    }

    // Ask Size: 4 Byte Unsigned Fixed Width Integer
    public static class AskSize implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public AskSize(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static AskSize parse(Container container, IBinaryProtocolElement element) {
            return new AskSize(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "askSize = "+ this.value;
        }
    }

    // Ask: 4 Byte Unsigned Fixed Width Integer
    public static class Ask implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public Ask(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Ask parse(Container container, IBinaryProtocolElement element) {
            return new Ask(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "ask = "+ this.value;
        }
    }

    // Bid Size: 4 Byte Unsigned Fixed Width Integer
    public static class BidSize implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public BidSize(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static BidSize parse(Container container, IBinaryProtocolElement element) {
            return new BidSize(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "bidSize = "+ this.value;
        }
    }

    // Bid: 4 Byte Unsigned Fixed Width Integer
    public static class Bid implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public Bid(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Bid parse(Container container, IBinaryProtocolElement element) {
            return new Bid(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "bid = "+ this.value;
        }
    }

    // Original Ask Reference Number Delta: 4 Byte Unsigned Fixed Width Integer
    public static class OriginalAskReferenceNumberDelta implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public OriginalAskReferenceNumberDelta(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static OriginalAskReferenceNumberDelta parse(Container container, IBinaryProtocolElement element) {
            return new OriginalAskReferenceNumberDelta(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "originalAskReferenceNumberDelta = "+ this.value;
        }
    }

    // Original Bid Reference Number Delta: 4 Byte Unsigned Fixed Width Integer
    public static class OriginalBidReferenceNumberDelta implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public OriginalBidReferenceNumberDelta(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static OriginalBidReferenceNumberDelta parse(Container container, IBinaryProtocolElement element) {
            return new OriginalBidReferenceNumberDelta(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "originalBidReferenceNumberDelta = "+ this.value;
        }
    }

    /**
    * Quote Replace Long Form Message
    */
    public static class QuoteReplaceLongFormMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public OriginalBidReferenceNumberDelta originalBidReferenceNumberDelta;
        public BidReferenceNumberDelta bidReferenceNumberDelta;
        public OriginalAskReferenceNumberDelta originalAskReferenceNumberDelta;
        public AskReferenceNumberDelta askReferenceNumberDelta;
        public Bid bid;
        public BidSize bidSize;
        public Ask ask;
        public AskSize askSize;

        // constructor for Quote Replace Long Form Message
        private QuoteReplaceLongFormMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Quote Replace Long Form Message
        public static QuoteReplaceLongFormMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new QuoteReplaceLongFormMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.originalBidReferenceNumberDelta = OriginalBidReferenceNumberDelta.parse(container, element);
            element.bidReferenceNumberDelta = BidReferenceNumberDelta.parse(container, element);
            element.originalAskReferenceNumberDelta = OriginalAskReferenceNumberDelta.parse(container, element);
            element.askReferenceNumberDelta = AskReferenceNumberDelta.parse(container, element);
            element.bid = Bid.parse(container, element);
            element.bidSize = BidSize.parse(container, element);
            element.ask = Ask.parse(container, element);
            element.askSize = AskSize.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.originalBidReferenceNumberDelta).append("\n");
            sb.append(this.bidReferenceNumberDelta).append("\n");
            sb.append(this.originalAskReferenceNumberDelta).append("\n");
            sb.append(this.askReferenceNumberDelta).append("\n");
            sb.append(this.bid).append("\n");
            sb.append(this.bidSize).append("\n");
            sb.append(this.ask).append("\n");
            sb.append(this.askSize).append("\n");
            return sb.toString();
        }
    }

    // Short Ask Size: 2 Byte Unsigned Fixed Width Integer
    public static class ShortAskSize implements IBinaryProtocolElement {
        public static final int LENGTH = 2;
        public final IBinaryProtocolElement parent;
        public final Short value;

        public ShortAskSize(Short value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static ShortAskSize parse(Container container, IBinaryProtocolElement element) {
            return new ShortAskSize(BigEndianUtils.toShort(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "shortAskSize = "+ this.value;
        }
    }

    // Ask Price: 2 Byte Unsigned Fixed Width Integer
    public static class AskPrice implements IBinaryProtocolElement {
        public static final int LENGTH = 2;
        public final IBinaryProtocolElement parent;
        public final Short value;

        public AskPrice(Short value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static AskPrice parse(Container container, IBinaryProtocolElement element) {
            return new AskPrice(BigEndianUtils.toShort(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "askPrice = "+ this.value;
        }
    }

    // Short Bid Size: 2 Byte Unsigned Fixed Width Integer
    public static class ShortBidSize implements IBinaryProtocolElement {
        public static final int LENGTH = 2;
        public final IBinaryProtocolElement parent;
        public final Short value;

        public ShortBidSize(Short value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static ShortBidSize parse(Container container, IBinaryProtocolElement element) {
            return new ShortBidSize(BigEndianUtils.toShort(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "shortBidSize = "+ this.value;
        }
    }

    // Bid Price: 2 Byte Unsigned Fixed Width Integer
    public static class BidPrice implements IBinaryProtocolElement {
        public static final int LENGTH = 2;
        public final IBinaryProtocolElement parent;
        public final Short value;

        public BidPrice(Short value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static BidPrice parse(Container container, IBinaryProtocolElement element) {
            return new BidPrice(BigEndianUtils.toShort(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "bidPrice = "+ this.value;
        }
    }

    /**
    * Quote Replace Short Form Message
    */
    public static class QuoteReplaceShortFormMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public OriginalBidReferenceNumberDelta originalBidReferenceNumberDelta;
        public BidReferenceNumberDelta bidReferenceNumberDelta;
        public OriginalAskReferenceNumberDelta originalAskReferenceNumberDelta;
        public AskReferenceNumberDelta askReferenceNumberDelta;
        public BidPrice bidPrice;
        public ShortBidSize shortBidSize;
        public AskPrice askPrice;
        public ShortAskSize shortAskSize;

        // constructor for Quote Replace Short Form Message
        private QuoteReplaceShortFormMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Quote Replace Short Form Message
        public static QuoteReplaceShortFormMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new QuoteReplaceShortFormMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.originalBidReferenceNumberDelta = OriginalBidReferenceNumberDelta.parse(container, element);
            element.bidReferenceNumberDelta = BidReferenceNumberDelta.parse(container, element);
            element.originalAskReferenceNumberDelta = OriginalAskReferenceNumberDelta.parse(container, element);
            element.askReferenceNumberDelta = AskReferenceNumberDelta.parse(container, element);
            element.bidPrice = BidPrice.parse(container, element);
            element.shortBidSize = ShortBidSize.parse(container, element);
            element.askPrice = AskPrice.parse(container, element);
            element.shortAskSize = ShortAskSize.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.originalBidReferenceNumberDelta).append("\n");
            sb.append(this.bidReferenceNumberDelta).append("\n");
            sb.append(this.originalAskReferenceNumberDelta).append("\n");
            sb.append(this.askReferenceNumberDelta).append("\n");
            sb.append(this.bidPrice).append("\n");
            sb.append(this.shortBidSize).append("\n");
            sb.append(this.askPrice).append("\n");
            sb.append(this.shortAskSize).append("\n");
            return sb.toString();
        }
    }

    // Change Reason: 1 Byte Ascii String Enum with 4 values
    public static class ChangeReason implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_CHANGE_REASON value;

        public ChangeReason(ENUM_CHANGE_REASON value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static ChangeReason parse(Container container, IBinaryProtocolElement element) {
            return new ChangeReason(ENUM_CHANGE_REASON.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "changeReason = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Reference Number Delta: 4 Byte Unsigned Fixed Width Integer
    public static class ReferenceNumberDelta implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public ReferenceNumberDelta(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static ReferenceNumberDelta parse(Container container, IBinaryProtocolElement element) {
            return new ReferenceNumberDelta(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "referenceNumberDelta = "+ this.value;
        }
    }

    /**
    * Single Side Update Message
    */
    public static class SingleSideUpdateMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public ReferenceNumberDelta referenceNumberDelta;
        public ChangeReason changeReason;
        public Price price;
        public Volume volume;

        // constructor for Single Side Update Message
        private SingleSideUpdateMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Single Side Update Message
        public static SingleSideUpdateMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new SingleSideUpdateMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.referenceNumberDelta = ReferenceNumberDelta.parse(container, element);
            element.changeReason = ChangeReason.parse(container, element);
            element.price = Price.parse(container, element);
            element.volume = Volume.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.referenceNumberDelta).append("\n");
            sb.append(this.changeReason).append("\n");
            sb.append(this.price).append("\n");
            sb.append(this.volume).append("\n");
            return sb.toString();
        }
    }

    /**
    * Single Side Delete Message
    */
    public static class SingleSideDeleteMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public ReferenceNumberDelta referenceNumberDelta;

        // constructor for Single Side Delete Message
        private SingleSideDeleteMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Single Side Delete Message
        public static SingleSideDeleteMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new SingleSideDeleteMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.referenceNumberDelta = ReferenceNumberDelta.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.referenceNumberDelta).append("\n");
            return sb.toString();
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

    // New Reference Number Delta: 4 Byte Unsigned Fixed Width Integer
    public static class NewReferenceNumberDelta implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public NewReferenceNumberDelta(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static NewReferenceNumberDelta parse(Container container, IBinaryProtocolElement element) {
            return new NewReferenceNumberDelta(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "newReferenceNumberDelta = "+ this.value;
        }
    }

    // Original Reference Number Delta: 4 Byte Unsigned Fixed Width Integer
    public static class OriginalReferenceNumberDelta implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public OriginalReferenceNumberDelta(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static OriginalReferenceNumberDelta parse(Container container, IBinaryProtocolElement element) {
            return new OriginalReferenceNumberDelta(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "originalReferenceNumberDelta = "+ this.value;
        }
    }

    /**
    * Single Side Replace Long Form Message
    */
    public static class SingleSideReplaceLongFormMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public OriginalReferenceNumberDelta originalReferenceNumberDelta;
        public NewReferenceNumberDelta newReferenceNumberDelta;
        public Price price;
        public Volume volume;
        public OrderId orderId;

        // constructor for Single Side Replace Long Form Message
        private SingleSideReplaceLongFormMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Single Side Replace Long Form Message
        public static SingleSideReplaceLongFormMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new SingleSideReplaceLongFormMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.originalReferenceNumberDelta = OriginalReferenceNumberDelta.parse(container, element);
            element.newReferenceNumberDelta = NewReferenceNumberDelta.parse(container, element);
            element.price = Price.parse(container, element);
            element.volume = Volume.parse(container, element);
            element.orderId = OrderId.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.originalReferenceNumberDelta).append("\n");
            sb.append(this.newReferenceNumberDelta).append("\n");
            sb.append(this.price).append("\n");
            sb.append(this.volume).append("\n");
            sb.append(this.orderId).append("\n");
            return sb.toString();
        }
    }

    // Short Volume: 2 Byte Unsigned Fixed Width Integer
    public static class ShortVolume implements IBinaryProtocolElement {
        public static final int LENGTH = 2;
        public final IBinaryProtocolElement parent;
        public final Short value;

        public ShortVolume(Short value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static ShortVolume parse(Container container, IBinaryProtocolElement element) {
            return new ShortVolume(BigEndianUtils.toShort(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "shortVolume = "+ this.value;
        }
    }

    // Short Price: 2 Byte Unsigned Fixed Width Integer
    public static class ShortPrice implements IBinaryProtocolElement {
        public static final int LENGTH = 2;
        public final IBinaryProtocolElement parent;
        public final Short value;

        public ShortPrice(Short value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static ShortPrice parse(Container container, IBinaryProtocolElement element) {
            return new ShortPrice(BigEndianUtils.toShort(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "shortPrice = "+ this.value;
        }
    }

    /**
    * Order Replace Message Short Form
    */
    public static class OrderReplaceMessageShortForm implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public OriginalReferenceNumberDelta originalReferenceNumberDelta;
        public NewReferenceNumberDelta newReferenceNumberDelta;
        public ShortPrice shortPrice;
        public ShortVolume shortVolume;
        public OrderId orderId;

        // constructor for Order Replace Message Short Form
        private OrderReplaceMessageShortForm(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Order Replace Message Short Form
        public static OrderReplaceMessageShortForm parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new OrderReplaceMessageShortForm(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.originalReferenceNumberDelta = OriginalReferenceNumberDelta.parse(container, element);
            element.newReferenceNumberDelta = NewReferenceNumberDelta.parse(container, element);
            element.shortPrice = ShortPrice.parse(container, element);
            element.shortVolume = ShortVolume.parse(container, element);
            element.orderId = OrderId.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.originalReferenceNumberDelta).append("\n");
            sb.append(this.newReferenceNumberDelta).append("\n");
            sb.append(this.shortPrice).append("\n");
            sb.append(this.shortVolume).append("\n");
            sb.append(this.orderId).append("\n");
            return sb.toString();
        }
    }

    /**
    * Single Side Replace Message Long Form
    */
    public static class SingleSideReplaceMessageLongForm implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public OriginalReferenceNumberDelta originalReferenceNumberDelta;
        public NewReferenceNumberDelta newReferenceNumberDelta;
        public Price price;
        public Volume volume;

        // constructor for Single Side Replace Message Long Form
        private SingleSideReplaceMessageLongForm(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Single Side Replace Message Long Form
        public static SingleSideReplaceMessageLongForm parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new SingleSideReplaceMessageLongForm(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.originalReferenceNumberDelta = OriginalReferenceNumberDelta.parse(container, element);
            element.newReferenceNumberDelta = NewReferenceNumberDelta.parse(container, element);
            element.price = Price.parse(container, element);
            element.volume = Volume.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.originalReferenceNumberDelta).append("\n");
            sb.append(this.newReferenceNumberDelta).append("\n");
            sb.append(this.price).append("\n");
            sb.append(this.volume).append("\n");
            return sb.toString();
        }
    }

    /**
    * Single Side Replace Message Short Form
    */
    public static class SingleSideReplaceMessageShortForm implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public OriginalReferenceNumberDelta originalReferenceNumberDelta;
        public NewReferenceNumberDelta newReferenceNumberDelta;
        public ShortPrice shortPrice;
        public ShortVolume shortVolume;

        // constructor for Single Side Replace Message Short Form
        private SingleSideReplaceMessageShortForm(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Single Side Replace Message Short Form
        public static SingleSideReplaceMessageShortForm parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new SingleSideReplaceMessageShortForm(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.originalReferenceNumberDelta = OriginalReferenceNumberDelta.parse(container, element);
            element.newReferenceNumberDelta = NewReferenceNumberDelta.parse(container, element);
            element.shortPrice = ShortPrice.parse(container, element);
            element.shortVolume = ShortVolume.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.originalReferenceNumberDelta).append("\n");
            sb.append(this.newReferenceNumberDelta).append("\n");
            sb.append(this.shortPrice).append("\n");
            sb.append(this.shortVolume).append("\n");
            return sb.toString();
        }
    }

    // Cancelled Contracts: 4 Byte Unsigned Fixed Width Integer
    public static class CancelledContracts implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public CancelledContracts(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static CancelledContracts parse(Container container, IBinaryProtocolElement element) {
            return new CancelledContracts(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "cancelledContracts = "+ this.value;
        }
    }

    /**
    * Single Side Cancel Message
    */
    public static class SingleSideCancelMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public ReferenceNumberDelta referenceNumberDelta;
        public CancelledContracts cancelledContracts;

        // constructor for Single Side Cancel Message
        private SingleSideCancelMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Single Side Cancel Message
        public static SingleSideCancelMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new SingleSideCancelMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.referenceNumberDelta = ReferenceNumberDelta.parse(container, element);
            element.cancelledContracts = CancelledContracts.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.referenceNumberDelta).append("\n");
            sb.append(this.cancelledContracts).append("\n");
            return sb.toString();
        }
    }

    // Printable: 1 Byte Ascii String Enum with 2 values
    public static class Printable implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_PRINTABLE value;

        public Printable(ENUM_PRINTABLE value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Printable parse(Container container, IBinaryProtocolElement element) {
            return new Printable(ENUM_PRINTABLE.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "printable = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    /**
    * Single Side Executed With Price Message
    */
    public static class SingleSideExecutedWithPriceMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public ReferenceNumberDelta referenceNumberDelta;
        public CrossNumber crossNumber;
        public MatchNumber matchNumber;
        public Printable printable;
        public Price price;
        public Volume volume;

        // constructor for Single Side Executed With Price Message
        private SingleSideExecutedWithPriceMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Single Side Executed With Price Message
        public static SingleSideExecutedWithPriceMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new SingleSideExecutedWithPriceMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.referenceNumberDelta = ReferenceNumberDelta.parse(container, element);
            element.crossNumber = CrossNumber.parse(container, element);
            element.matchNumber = MatchNumber.parse(container, element);
            element.printable = Printable.parse(container, element);
            element.price = Price.parse(container, element);
            element.volume = Volume.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.referenceNumberDelta).append("\n");
            sb.append(this.crossNumber).append("\n");
            sb.append(this.matchNumber).append("\n");
            sb.append(this.printable).append("\n");
            sb.append(this.price).append("\n");
            sb.append(this.volume).append("\n");
            return sb.toString();
        }
    }

    // Executed Contracts: 4 Byte Unsigned Fixed Width Integer
    public static class ExecutedContracts implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public ExecutedContracts(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static ExecutedContracts parse(Container container, IBinaryProtocolElement element) {
            return new ExecutedContracts(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "executedContracts = "+ this.value;
        }
    }

    /**
    * Single Side Executed Message
    */
    public static class SingleSideExecutedMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public ReferenceNumberDelta referenceNumberDelta;
        public ExecutedContracts executedContracts;
        public CrossNumber crossNumber;
        public MatchNumber matchNumber;

        // constructor for Single Side Executed Message
        private SingleSideExecutedMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Single Side Executed Message
        public static SingleSideExecutedMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new SingleSideExecutedMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.referenceNumberDelta = ReferenceNumberDelta.parse(container, element);
            element.executedContracts = ExecutedContracts.parse(container, element);
            element.crossNumber = CrossNumber.parse(container, element);
            element.matchNumber = MatchNumber.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.referenceNumberDelta).append("\n");
            sb.append(this.executedContracts).append("\n");
            sb.append(this.crossNumber).append("\n");
            sb.append(this.matchNumber).append("\n");
            return sb.toString();
        }
    }

    /**
    * Add Quote Message Long Form
    */
    public static class AddQuoteMessageLongForm implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public BidReferenceNumberDelta bidReferenceNumberDelta;
        public AskReferenceNumberDelta askReferenceNumberDelta;
        public OptionId optionId;
        public Bid bid;
        public BidSize bidSize;
        public Ask ask;
        public AskSize askSize;

        // constructor for Add Quote Message Long Form
        private AddQuoteMessageLongForm(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Add Quote Message Long Form
        public static AddQuoteMessageLongForm parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new AddQuoteMessageLongForm(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.bidReferenceNumberDelta = BidReferenceNumberDelta.parse(container, element);
            element.askReferenceNumberDelta = AskReferenceNumberDelta.parse(container, element);
            element.optionId = OptionId.parse(container, element);
            element.bid = Bid.parse(container, element);
            element.bidSize = BidSize.parse(container, element);
            element.ask = Ask.parse(container, element);
            element.askSize = AskSize.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.bidReferenceNumberDelta).append("\n");
            sb.append(this.askReferenceNumberDelta).append("\n");
            sb.append(this.optionId).append("\n");
            sb.append(this.bid).append("\n");
            sb.append(this.bidSize).append("\n");
            sb.append(this.ask).append("\n");
            sb.append(this.askSize).append("\n");
            return sb.toString();
        }
    }

    /**
    * Add Quote Message Short Form
    */
    public static class AddQuoteMessageShortForm implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public BidReferenceNumberDelta bidReferenceNumberDelta;
        public AskReferenceNumberDelta askReferenceNumberDelta;
        public OptionId optionId;
        public BidPrice bidPrice;
        public ShortBidSize shortBidSize;
        public AskPrice askPrice;
        public ShortAskSize shortAskSize;

        // constructor for Add Quote Message Short Form
        private AddQuoteMessageShortForm(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Add Quote Message Short Form
        public static AddQuoteMessageShortForm parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new AddQuoteMessageShortForm(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.bidReferenceNumberDelta = BidReferenceNumberDelta.parse(container, element);
            element.askReferenceNumberDelta = AskReferenceNumberDelta.parse(container, element);
            element.optionId = OptionId.parse(container, element);
            element.bidPrice = BidPrice.parse(container, element);
            element.shortBidSize = ShortBidSize.parse(container, element);
            element.askPrice = AskPrice.parse(container, element);
            element.shortAskSize = ShortAskSize.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.bidReferenceNumberDelta).append("\n");
            sb.append(this.askReferenceNumberDelta).append("\n");
            sb.append(this.optionId).append("\n");
            sb.append(this.bidPrice).append("\n");
            sb.append(this.shortBidSize).append("\n");
            sb.append(this.askPrice).append("\n");
            sb.append(this.shortAskSize).append("\n");
            return sb.toString();
        }
    }

    // Market Side: 1 Byte Ascii String Enum with 6 values
    public static class MarketSide implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_MARKET_SIDE value;

        public MarketSide(ENUM_MARKET_SIDE value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static MarketSide parse(Container container, IBinaryProtocolElement element) {
            return new MarketSide(ENUM_MARKET_SIDE.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "marketSide = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Order Reference Number Delta: 4 Byte Unsigned Fixed Width Integer
    public static class OrderReferenceNumberDelta implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public OrderReferenceNumberDelta(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static OrderReferenceNumberDelta parse(Container container, IBinaryProtocolElement element) {
            return new OrderReferenceNumberDelta(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "orderReferenceNumberDelta = "+ this.value;
        }
    }

    /**
    * Add Order Message Long Form
    */
    public static class AddOrderMessageLongForm implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public OrderReferenceNumberDelta orderReferenceNumberDelta;
        public MarketSide marketSide;
        public OptionId optionId;
        public Price price;
        public Volume volume;
        public OrderId orderId;

        // constructor for Add Order Message Long Form
        private AddOrderMessageLongForm(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Add Order Message Long Form
        public static AddOrderMessageLongForm parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new AddOrderMessageLongForm(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.orderReferenceNumberDelta = OrderReferenceNumberDelta.parse(container, element);
            element.marketSide = MarketSide.parse(container, element);
            element.optionId = OptionId.parse(container, element);
            element.price = Price.parse(container, element);
            element.volume = Volume.parse(container, element);
            element.orderId = OrderId.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.orderReferenceNumberDelta).append("\n");
            sb.append(this.marketSide).append("\n");
            sb.append(this.optionId).append("\n");
            sb.append(this.price).append("\n");
            sb.append(this.volume).append("\n");
            sb.append(this.orderId).append("\n");
            return sb.toString();
        }
    }

    /**
    * Add Order Message Short Form
    */
    public static class AddOrderMessageShortForm implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public OrderReferenceNumberDelta orderReferenceNumberDelta;
        public MarketSide marketSide;
        public OptionId optionId;
        public ShortPrice shortPrice;
        public ShortVolume shortVolume;
        public OrderId orderId;

        // constructor for Add Order Message Short Form
        private AddOrderMessageShortForm(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Add Order Message Short Form
        public static AddOrderMessageShortForm parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new AddOrderMessageShortForm(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.orderReferenceNumberDelta = OrderReferenceNumberDelta.parse(container, element);
            element.marketSide = MarketSide.parse(container, element);
            element.optionId = OptionId.parse(container, element);
            element.shortPrice = ShortPrice.parse(container, element);
            element.shortVolume = ShortVolume.parse(container, element);
            element.orderId = OrderId.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.orderReferenceNumberDelta).append("\n");
            sb.append(this.marketSide).append("\n");
            sb.append(this.optionId).append("\n");
            sb.append(this.shortPrice).append("\n");
            sb.append(this.shortVolume).append("\n");
            sb.append(this.orderId).append("\n");
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
    * Security Open Message
    */
    public static class SecurityOpenMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public OptionId optionId;
        public OpenState openState;

        // constructor for Security Open Message
        private SecurityOpenMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Security Open Message
        public static SecurityOpenMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new SecurityOpenMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.optionId = OptionId.parse(container, element);
            element.openState = OpenState.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.optionId).append("\n");
            sb.append(this.openState).append("\n");
            return sb.toString();
        }
    }

    // Current Trading State: 1 Byte Ascii String Enum with 4 values
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
    * Trading Action Message
    */
    public static class TradingActionMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public OptionId optionId;
        public CurrentTradingState currentTradingState;

        // constructor for Trading Action Message
        private TradingActionMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Trading Action Message
        public static TradingActionMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new TradingActionMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.optionId = OptionId.parse(container, element);
            element.currentTradingState = CurrentTradingState.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.optionId).append("\n");
            sb.append(this.currentTradingState).append("\n");
            return sb.toString();
        }
    }

    // Mpv: 1 Byte Ascii String Enum with 3 values
    public static class Mpv implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_MPV value;

        public Mpv(ENUM_MPV value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Mpv parse(Container container, IBinaryProtocolElement element) {
            return new Mpv(ENUM_MPV.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "mpv = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Tradable: 1 Byte Ascii String Enum with 2 values
    public static class Tradable implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_TRADABLE value;

        public Tradable(ENUM_TRADABLE value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Tradable parse(Container container, IBinaryProtocolElement element) {
            return new Tradable(ENUM_TRADABLE.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "tradable = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Options Closing Type: 1 Byte Ascii String Enum with 3 values
    public static class OptionsClosingType implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_OPTIONS_CLOSING_TYPE value;

        public OptionsClosingType(ENUM_OPTIONS_CLOSING_TYPE value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static OptionsClosingType parse(Container container, IBinaryProtocolElement element) {
            return new OptionsClosingType(ENUM_OPTIONS_CLOSING_TYPE.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "optionsClosingType = "+ this.value.enumType +" ("+ this.value.enumValue +")";
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

    // Option Type: 1 Byte Ascii String Enum with 2 values
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

    // Expiration Date: 1 Byte Unsigned Fixed Width Integer
    public static class ExpirationDate implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public ExpirationDate(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static ExpirationDate parse(Container container, IBinaryProtocolElement element) {
            return new ExpirationDate(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "expirationDate = "+ this.value;
        }
    }

    // Expiration Month: 1 Byte Unsigned Fixed Width Integer
    public static class ExpirationMonth implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public ExpirationMonth(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static ExpirationMonth parse(Container container, IBinaryProtocolElement element) {
            return new ExpirationMonth(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "expirationMonth = "+ this.value;
        }
    }

    // Expiration Year: 1 Byte Unsigned Fixed Width Integer
    public static class ExpirationYear implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public ExpirationYear(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static ExpirationYear parse(Container container, IBinaryProtocolElement element) {
            return new ExpirationYear(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "expirationYear = "+ this.value;
        }
    }

    // Security Symbol: 6 Byte Ascii String
    public static class SecuritySymbol implements IBinaryProtocolElement {
        public static final int LENGTH = 6;
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

    /**
    * Option Directory Message
    */
    public static class OptionDirectoryMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public OptionId optionId;
        public SecuritySymbol securitySymbol;
        public ExpirationYear expirationYear;
        public ExpirationMonth expirationMonth;
        public ExpirationDate expirationDate;
        public ExplicitStrikePrice explicitStrikePrice;
        public OptionType optionType;
        public Source source;
        public UnderlyingSymbol underlyingSymbol;
        public OptionsClosingType optionsClosingType;
        public Tradable tradable;
        public Mpv mpv;

        // constructor for Option Directory Message
        private OptionDirectoryMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Option Directory Message
        public static OptionDirectoryMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new OptionDirectoryMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.optionId = OptionId.parse(container, element);
            element.securitySymbol = SecuritySymbol.parse(container, element);
            element.expirationYear = ExpirationYear.parse(container, element);
            element.expirationMonth = ExpirationMonth.parse(container, element);
            element.expirationDate = ExpirationDate.parse(container, element);
            element.explicitStrikePrice = ExplicitStrikePrice.parse(container, element);
            element.optionType = OptionType.parse(container, element);
            element.source = Source.parse(container, element);
            element.underlyingSymbol = UnderlyingSymbol.parse(container, element);
            element.optionsClosingType = OptionsClosingType.parse(container, element);
            element.tradable = Tradable.parse(container, element);
            element.mpv = Mpv.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.optionId).append("\n");
            sb.append(this.securitySymbol).append("\n");
            sb.append(this.expirationYear).append("\n");
            sb.append(this.expirationMonth).append("\n");
            sb.append(this.expirationDate).append("\n");
            sb.append(this.explicitStrikePrice).append("\n");
            sb.append(this.optionType).append("\n");
            sb.append(this.source).append("\n");
            sb.append(this.underlyingSymbol).append("\n");
            sb.append(this.optionsClosingType).append("\n");
            sb.append(this.tradable).append("\n");
            sb.append(this.mpv).append("\n");
            return sb.toString();
        }
    }

    // Base Reference Number: 8 Byte Unsigned Fixed Width Integer
    public static class BaseReferenceNumber implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public BaseReferenceNumber(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static BaseReferenceNumber parse(Container container, IBinaryProtocolElement element) {
            return new BaseReferenceNumber(BigEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "baseReferenceNumber = "+ this.value;
        }
    }

    /**
    * Base Reference Message
    */
    public static class BaseReferenceMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public BaseReferenceNumber baseReferenceNumber;

        // constructor for Base Reference Message
        private BaseReferenceMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Base Reference Message
        public static BaseReferenceMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new BaseReferenceMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.baseReferenceNumber = BaseReferenceNumber.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.baseReferenceNumber).append("\n");
            return sb.toString();
        }
    }

    // Event Code: 1 Byte Ascii String
    public static class EventCode implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Character value;

        public EventCode(Character value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static EventCode parse(Container container, IBinaryProtocolElement element) {
            return new EventCode(BigEndianUtils.toCharacter(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "eventCode = "+ this.value;
        }
    }

    /**
    * System Event Message
    */
    public static class SystemEventMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public EventCode eventCode;

        // constructor for System Event Message
        private SystemEventMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for System Event Message
        public static SystemEventMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new SystemEventMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.eventCode = EventCode.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.eventCode).append("\n");
            return sb.toString();
        }
    }

    // Second: 4 Byte Unsigned Fixed Width Integer
    public static class Second implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public Second(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Second parse(Container container, IBinaryProtocolElement element) {
            return new Second(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "second = "+ this.value;
        }
    }

    /**
    * Seconds Message
    */
    public static class SecondsMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Second second;

        // constructor for Seconds Message
        private SecondsMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Seconds Message
        public static SecondsMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new SecondsMessage(parentElement);

            element.second = Second.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.second).append("\n");
            return sb.toString();
        }
    }

    /**
    * Parse Payload
    */
    public static Payload parsePayload(Container container, ENUM_MESSAGE_TYPE messageType) {
        
        // -- parsing Seconds Message
        if (messageType.enumType == 'T') {
            return SecondsMessage.parse(container, null);
        }
        // -- parsing System Event Message
        if (messageType.enumType == 'S') {
            return SystemEventMessage.parse(container, null);
        }
        // -- parsing Base Reference Message
        if (messageType.enumType == 'L') {
            return BaseReferenceMessage.parse(container, null);
        }
        // -- parsing Option Directory Message
        if (messageType.enumType == 'R') {
            return OptionDirectoryMessage.parse(container, null);
        }
        // -- parsing Trading Action Message
        if (messageType.enumType == 'H') {
            return TradingActionMessage.parse(container, null);
        }
        // -- parsing Security Open Message
        if (messageType.enumType == 'O') {
            return SecurityOpenMessage.parse(container, null);
        }
        // -- parsing Add Order Message Short Form
        if (messageType.enumType == 'a') {
            return AddOrderMessageShortForm.parse(container, null);
        }
        // -- parsing Add Order Message Long Form
        if (messageType.enumType == 'A') {
            return AddOrderMessageLongForm.parse(container, null);
        }
        // -- parsing Add Quote Message Short Form
        if (messageType.enumType == 'j') {
            return AddQuoteMessageShortForm.parse(container, null);
        }
        // -- parsing Add Quote Message Long Form
        if (messageType.enumType == 'J') {
            return AddQuoteMessageLongForm.parse(container, null);
        }
        // -- parsing Single Side Executed Message
        if (messageType.enumType == 'E') {
            return SingleSideExecutedMessage.parse(container, null);
        }
        // -- parsing Single Side Executed With Price Message
        if (messageType.enumType == 'C') {
            return SingleSideExecutedWithPriceMessage.parse(container, null);
        }
        // -- parsing Single Side Cancel Message
        if (messageType.enumType == 'X') {
            return SingleSideCancelMessage.parse(container, null);
        }
        // -- parsing Single Side Replace Message Short Form
        if (messageType.enumType == 'u') {
            return SingleSideReplaceMessageShortForm.parse(container, null);
        }
        // -- parsing Single Side Replace Message Long Form
        if (messageType.enumType == 'U') {
            return SingleSideReplaceMessageLongForm.parse(container, null);
        }
        // -- parsing Order Replace Message Short Form
        if (messageType.enumType == 'v') {
            return OrderReplaceMessageShortForm.parse(container, null);
        }
        // -- parsing Single Side Replace Long Form Message
        if (messageType.enumType == 'V') {
            return SingleSideReplaceLongFormMessage.parse(container, null);
        }
        // -- parsing Single Side Delete Message
        if (messageType.enumType == 'D') {
            return SingleSideDeleteMessage.parse(container, null);
        }
        // -- parsing Single Side Update Message
        if (messageType.enumType == 'G') {
            return SingleSideUpdateMessage.parse(container, null);
        }
        // -- parsing Quote Replace Short Form Message
        if (messageType.enumType == 'k') {
            return QuoteReplaceShortFormMessage.parse(container, null);
        }
        // -- parsing Quote Replace Long Form Message
        if (messageType.enumType == 'K') {
            return QuoteReplaceLongFormMessage.parse(container, null);
        }
        // -- parsing Quote Delete Message
        if (messageType.enumType == 'Y') {
            return QuoteDeleteMessage.parse(container, null);
        }
        // -- parsing Block Delete Message
        if (messageType.enumType == 'Z') {
            return BlockDeleteMessage.parse(container, null);
        }
        // -- parsing Non Auction Options Trade Message
        if (messageType.enumType == 'P') {
            return NonAuctionOptionsTradeMessage.parse(container, null);
        }
        // -- parsing Options Cross Trade Message
        if (messageType.enumType == 'Q') {
            return OptionsCrossTradeMessage.parse(container, null);
        }
        // -- parsing Broken Trade Order Execution Message
        if (messageType.enumType == 'B') {
            return BrokenTradeOrderExecutionMessage.parse(container, null);
        }
        // -- parsing Auction Notification Message
        if (messageType.enumType == 'I') {
            return AuctionNotificationMessage.parse(container, null);
        }

        return null;
    }

    // Message Type: 1 Byte Ascii String Enum with 27 values
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
            return new Count(BigEndianUtils.toShort(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "count = "+ this.value;
        }
    }

    // Sequence: 8 Byte Unsigned Fixed Width Integer
    public static class Sequence implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public Sequence(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Sequence parse(Container container, IBinaryProtocolElement element) {
            return new Sequence(BigEndianUtils.toLong(container, LENGTH), element);
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
