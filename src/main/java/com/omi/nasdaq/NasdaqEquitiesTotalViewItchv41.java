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
* Java parser for Nasdaq Equities Itch TotalView 4.1 protocol
* 
* @version 1.0
* @since 04/25/2021 15:26:15
*/

public class NasdaqEquitiesTotalViewItchv41 {

    ///////////////////////////////////////////////////////////////////////
    // Enum Values
    ///////////////////////////////////////////////////////////////////////

    /**
    * Cross Type Values
    */
    public enum ENUM_CROSS_TYPE {
        E_O('O', "Opening"),
        E_C('C', "Closing"),
        E_H('H', "Cross For Ipo And Halted"),
        E_I('I', "Nasdaq Cross Network");

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
    * Event Code Values
    */
    public enum ENUM_EVENT_CODE {
        E_O('O', "Start Of Messages"),
        E_S('S', "Start Of System Hours"),
        E_Q('Q', "Start Of Market Hours"),
        E_M('M', "End Of Market Hours"),
        E_E('E', "End Of System Hours"),
        E_C('C', "End Of Message"),
        E_A('A', "Halt"),
        E_R('R', "Quote Only Period"),
        E_B('B', "Resumption");

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
    * Financial Status Indicator Values
    */
    public enum ENUM_FINANCIAL_STATUS_INDICATOR {
        E_D('D', "Delinquent"),
        E_E('E', "Deficient"),
        E_Q('Q', "Bankrupt"),
        E_S('S', "Suspended"),
        E_G('G', "Deficient And Bankrupt"),
        E_H('H', "Deficient And Delinquent"),
        E_J('J', "Delinquent And Bankrupt"),
        E_K('K', "Deficient Delinquent And Bankrupt");

        public final Character enumType;
        public final String enumValue;

        ENUM_FINANCIAL_STATUS_INDICATOR(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_FINANCIAL_STATUS_INDICATOR> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_FINANCIAL_STATUS_INDICATOR s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_FINANCIAL_STATUS_INDICATOR valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Imbalance Direction Values
    */
    public enum ENUM_IMBALANCE_DIRECTION {
        E_B('B', "Buy"),
        E_S('S', "Sell"),
        E_N('N', "No"),
        E_O('O', "Insufficient Orders");

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
    * Interest Flag Values
    */
    public enum ENUM_INTEREST_FLAG {
        E_B('B', "Rpi Buy"),
        E_S('S', "Rpi Sell"),
        E_A('A', "Rpi Both"),
        E_N('N', "No Rpi");

        public final Character enumType;
        public final String enumValue;

        ENUM_INTEREST_FLAG(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_INTEREST_FLAG> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_INTEREST_FLAG s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_INTEREST_FLAG valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Market Category Values
    */
    public enum ENUM_MARKET_CATEGORY {
        E_N('N', "Nyse"),
        E_A('A', "Amex"),
        E_P('P', "Arca"),
        E_Q('Q', "Nasdaq Gsm"),
        E_G('G', "Nasdaq Gm"),
        E_S('S', "Nasdaq Cm"),
        E_Z('Z', "Bats");

        public final Character enumType;
        public final String enumValue;

        ENUM_MARKET_CATEGORY(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_MARKET_CATEGORY> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_MARKET_CATEGORY s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_MARKET_CATEGORY valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Market Maker Mode Values
    */
    public enum ENUM_MARKET_MAKER_MODE {
        E_N('N', "Normal"),
        E_P('P', "Passive"),
        E_S('S', "Syndicate"),
        E_L('L', "Penalty");

        public final Character enumType;
        public final String enumValue;

        ENUM_MARKET_MAKER_MODE(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_MARKET_MAKER_MODE> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_MARKET_MAKER_MODE s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_MARKET_MAKER_MODE valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Market Participant State Values
    */
    public enum ENUM_MARKET_PARTICIPANT_STATE {
        E_A('A', "Active"),
        E_E('E', "Excused Withdrawn"),
        E_W('W', "Withdrawn"),
        E_S('S', "Suspended"),
        E_D('D', "Deleted");

        public final Character enumType;
        public final String enumValue;

        ENUM_MARKET_PARTICIPANT_STATE(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_MARKET_PARTICIPANT_STATE> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_MARKET_PARTICIPANT_STATE s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_MARKET_PARTICIPANT_STATE valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Message Type Values
    */
    public enum ENUM_MESSAGE_TYPE {
        E_T('T', "Time Stamp Message"),
        E_S('S', "System Event Message"),
        E_R('R', "Stock Directory Message"),
        E_Y('Y', "Reg Sho Short Sale Price Test Restricted Indicator Message"),
        E_L('L', "Market Participant Position Message"),
        E_A('A', "Add Order Message"),
        E_F('F', "Add Order With Mpid Message"),
        E_E('E', "Order Executed Message"),
        E_C('C', "Order Executed With Price Message"),
        E_X('X', "Order Cancel Message"),
        E_D('D', "Order Delete Message"),
        E_U('U', "Order Replace Message"),
        E_P('P', "Trade Message"),
        E_Q('Q', "Cross Trade Message"),
        E_B('B', "Broken Trade Message"),
        E_I('I', "Net Order Imbalance Indicator Message"),
        E_N('N', "Retail Price Improvement Indicator Message");

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
    * Price Variation Indicator Values
    */
    public enum ENUM_PRICE_VARIATION_INDICATOR {
        E_L('L', "Less"),
        E_1('1', "1 To 199"),
        E_2('2', "2 To 299"),
        E_3('3', "3 To 399"),
        E_4('4', "4 To 499"),
        E_5('5', "5 To 599"),
        E_6('6', "6 To 699"),
        E_7('7', "7 To 799"),
        E_8('8', "8 To 899"),
        E_9('9', "9 To 999"),
        E_A('A', "10 To 1999"),
        E_B('B', "20 To 2999"),
        E_C('C', "30 Or Greater"),
        E_SPACE(' ', "No Calculation");

        public final Character enumType;
        public final String enumValue;

        ENUM_PRICE_VARIATION_INDICATOR(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_PRICE_VARIATION_INDICATOR> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_PRICE_VARIATION_INDICATOR s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_PRICE_VARIATION_INDICATOR valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Primary Market Maker Values
    */
    public enum ENUM_PRIMARY_MARKET_MAKER {
        E_Y('Y', "Primary"),
        E_N('N', "Nonprimary");

        public final Character enumType;
        public final String enumValue;

        ENUM_PRIMARY_MARKET_MAKER(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_PRIMARY_MARKET_MAKER> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_PRIMARY_MARKET_MAKER s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_PRIMARY_MARKET_MAKER valueOf(Character s)  {
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
    * Reg Sho Action Values
    */
    public enum ENUM_REG_SHO_ACTION {
        E_0('0', "No Price"),
        E_1('1', "Reg Sho Short Sale Price Test Restriction"),
        E_2('2', "Reg Sho Short Sale Price Test");

        public final Character enumType;
        public final String enumValue;

        ENUM_REG_SHO_ACTION(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_REG_SHO_ACTION> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_REG_SHO_ACTION s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_REG_SHO_ACTION valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Side Values
    */
    public enum ENUM_SIDE {
        E_B('B', "Buy"),
        E_S('S', "Sell");

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


    // Interest Flag: 1 Byte Ascii String Enum with 4 values
    public static class InterestFlag implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_INTEREST_FLAG value;

        public InterestFlag(ENUM_INTEREST_FLAG value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static InterestFlag parse(Container container, IBinaryProtocolElement element) {
            return new InterestFlag(ENUM_INTEREST_FLAG.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "interestFlag = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Stock: 8 Byte Ascii String
    public static class Stock implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final String value;

        public Stock(String value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Stock parse(Container container, IBinaryProtocolElement element) {
            return new Stock(BigEndianUtils.toString(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "stock = "+ this.value;
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
    * Retail Price Improvement Indicator Message
    */
    public static class RetailPriceImprovementIndicatorMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public Stock stock;
        public InterestFlag interestFlag;

        // constructor for Retail Price Improvement Indicator Message
        private RetailPriceImprovementIndicatorMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Retail Price Improvement Indicator Message
        public static RetailPriceImprovementIndicatorMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new RetailPriceImprovementIndicatorMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.stock = Stock.parse(container, element);
            element.interestFlag = InterestFlag.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.stock).append("\n");
            sb.append(this.interestFlag).append("\n");
            return sb.toString();
        }
    }

    // Price Variation Indicator: 1 Byte Ascii String Enum with 14 values
    public static class PriceVariationIndicator implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_PRICE_VARIATION_INDICATOR value;

        public PriceVariationIndicator(ENUM_PRICE_VARIATION_INDICATOR value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static PriceVariationIndicator parse(Container container, IBinaryProtocolElement element) {
            return new PriceVariationIndicator(ENUM_PRICE_VARIATION_INDICATOR.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "priceVariationIndicator = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Cross Type: 1 Byte Ascii String Enum with 4 values
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

    // Current Reference Price: 4 Byte Unsigned Fixed Width Integer
    public static class CurrentReferencePrice implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public CurrentReferencePrice(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static CurrentReferencePrice parse(Container container, IBinaryProtocolElement element) {
            return new CurrentReferencePrice(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "currentReferencePrice = "+ this.value;
        }
    }

    // Near Price: 4 Byte Unsigned Fixed Width Integer
    public static class NearPrice implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public NearPrice(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static NearPrice parse(Container container, IBinaryProtocolElement element) {
            return new NearPrice(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "nearPrice = "+ this.value;
        }
    }

    // Far Price: 4 Byte Unsigned Fixed Width Integer
    public static class FarPrice implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public FarPrice(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static FarPrice parse(Container container, IBinaryProtocolElement element) {
            return new FarPrice(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "farPrice = "+ this.value;
        }
    }

    // Imbalance Direction: 1 Byte Ascii String Enum with 4 values
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

    // Imbalance Shares: 8 Byte Unsigned Fixed Width Integer
    public static class ImbalanceShares implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public ImbalanceShares(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static ImbalanceShares parse(Container container, IBinaryProtocolElement element) {
            return new ImbalanceShares(BigEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "imbalanceShares = "+ this.value;
        }
    }

    // Paired Shares: 8 Byte Unsigned Fixed Width Integer
    public static class PairedShares implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public PairedShares(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static PairedShares parse(Container container, IBinaryProtocolElement element) {
            return new PairedShares(BigEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "pairedShares = "+ this.value;
        }
    }

    /**
    * Net Order Imbalance Indicator Message
    */
    public static class NetOrderImbalanceIndicatorMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public PairedShares pairedShares;
        public ImbalanceShares imbalanceShares;
        public ImbalanceDirection imbalanceDirection;
        public Stock stock;
        public FarPrice farPrice;
        public NearPrice nearPrice;
        public CurrentReferencePrice currentReferencePrice;
        public CrossType crossType;
        public PriceVariationIndicator priceVariationIndicator;

        // constructor for Net Order Imbalance Indicator Message
        private NetOrderImbalanceIndicatorMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Net Order Imbalance Indicator Message
        public static NetOrderImbalanceIndicatorMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new NetOrderImbalanceIndicatorMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.pairedShares = PairedShares.parse(container, element);
            element.imbalanceShares = ImbalanceShares.parse(container, element);
            element.imbalanceDirection = ImbalanceDirection.parse(container, element);
            element.stock = Stock.parse(container, element);
            element.farPrice = FarPrice.parse(container, element);
            element.nearPrice = NearPrice.parse(container, element);
            element.currentReferencePrice = CurrentReferencePrice.parse(container, element);
            element.crossType = CrossType.parse(container, element);
            element.priceVariationIndicator = PriceVariationIndicator.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.pairedShares).append("\n");
            sb.append(this.imbalanceShares).append("\n");
            sb.append(this.imbalanceDirection).append("\n");
            sb.append(this.stock).append("\n");
            sb.append(this.farPrice).append("\n");
            sb.append(this.nearPrice).append("\n");
            sb.append(this.currentReferencePrice).append("\n");
            sb.append(this.crossType).append("\n");
            sb.append(this.priceVariationIndicator).append("\n");
            return sb.toString();
        }
    }

    // Match Number: 8 Byte Unsigned Fixed Width Integer
    public static class MatchNumber implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public MatchNumber(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static MatchNumber parse(Container container, IBinaryProtocolElement element) {
            return new MatchNumber(BigEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "matchNumber = "+ this.value;
        }
    }

    /**
    * Broken Trade Message
    */
    public static class BrokenTradeMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public MatchNumber matchNumber;

        // constructor for Broken Trade Message
        private BrokenTradeMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Broken Trade Message
        public static BrokenTradeMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new BrokenTradeMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.matchNumber = MatchNumber.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.matchNumber).append("\n");
            return sb.toString();
        }
    }

    // Cross Price: 4 Byte Unsigned Fixed Width Integer
    public static class CrossPrice implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public CrossPrice(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static CrossPrice parse(Container container, IBinaryProtocolElement element) {
            return new CrossPrice(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "crossPrice = "+ this.value;
        }
    }

    // Cross Shares: 8 Byte Unsigned Fixed Width Integer
    public static class CrossShares implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public CrossShares(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static CrossShares parse(Container container, IBinaryProtocolElement element) {
            return new CrossShares(BigEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "crossShares = "+ this.value;
        }
    }

    /**
    * Cross Trade Message
    */
    public static class CrossTradeMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public CrossShares crossShares;
        public Stock stock;
        public CrossPrice crossPrice;
        public MatchNumber matchNumber;
        public CrossType crossType;

        // constructor for Cross Trade Message
        private CrossTradeMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Cross Trade Message
        public static CrossTradeMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new CrossTradeMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.crossShares = CrossShares.parse(container, element);
            element.stock = Stock.parse(container, element);
            element.crossPrice = CrossPrice.parse(container, element);
            element.matchNumber = MatchNumber.parse(container, element);
            element.crossType = CrossType.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.crossShares).append("\n");
            sb.append(this.stock).append("\n");
            sb.append(this.crossPrice).append("\n");
            sb.append(this.matchNumber).append("\n");
            sb.append(this.crossType).append("\n");
            return sb.toString();
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

    // Shares: 4 Byte Unsigned Fixed Width Integer
    public static class Shares implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public Shares(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Shares parse(Container container, IBinaryProtocolElement element) {
            return new Shares(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "shares = "+ this.value;
        }
    }

    // Side: 1 Byte Ascii String Enum with 2 values
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

    // Order Reference Number: 8 Byte Unsigned Fixed Width Integer
    public static class OrderReferenceNumber implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public OrderReferenceNumber(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static OrderReferenceNumber parse(Container container, IBinaryProtocolElement element) {
            return new OrderReferenceNumber(BigEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "orderReferenceNumber = "+ this.value;
        }
    }

    /**
    * Trade Message
    */
    public static class TradeMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public OrderReferenceNumber orderReferenceNumber;
        public Side side;
        public Shares shares;
        public Stock stock;
        public Price price;
        public MatchNumber matchNumber;

        // constructor for Trade Message
        private TradeMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Trade Message
        public static TradeMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new TradeMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.orderReferenceNumber = OrderReferenceNumber.parse(container, element);
            element.side = Side.parse(container, element);
            element.shares = Shares.parse(container, element);
            element.stock = Stock.parse(container, element);
            element.price = Price.parse(container, element);
            element.matchNumber = MatchNumber.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.orderReferenceNumber).append("\n");
            sb.append(this.side).append("\n");
            sb.append(this.shares).append("\n");
            sb.append(this.stock).append("\n");
            sb.append(this.price).append("\n");
            sb.append(this.matchNumber).append("\n");
            return sb.toString();
        }
    }

    // New Order Reference Number: 8 Byte Unsigned Fixed Width Integer
    public static class NewOrderReferenceNumber implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public NewOrderReferenceNumber(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static NewOrderReferenceNumber parse(Container container, IBinaryProtocolElement element) {
            return new NewOrderReferenceNumber(BigEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "newOrderReferenceNumber = "+ this.value;
        }
    }

    // Original Order Reference Number: 8 Byte Unsigned Fixed Width Integer
    public static class OriginalOrderReferenceNumber implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public OriginalOrderReferenceNumber(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static OriginalOrderReferenceNumber parse(Container container, IBinaryProtocolElement element) {
            return new OriginalOrderReferenceNumber(BigEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "originalOrderReferenceNumber = "+ this.value;
        }
    }

    // Timestamp Nanoseconds: 4 Byte Unsigned Fixed Width Integer
    public static class TimestampNanoseconds implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public TimestampNanoseconds(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static TimestampNanoseconds parse(Container container, IBinaryProtocolElement element) {
            return new TimestampNanoseconds(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "timestampNanoseconds = "+ this.value;
        }
    }

    /**
    * Order Replace Message
    */
    public static class OrderReplaceMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public TimestampNanoseconds timestampNanoseconds;
        public OriginalOrderReferenceNumber originalOrderReferenceNumber;
        public NewOrderReferenceNumber newOrderReferenceNumber;
        public Shares shares;
        public Price price;

        // constructor for Order Replace Message
        private OrderReplaceMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Order Replace Message
        public static OrderReplaceMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new OrderReplaceMessage(parentElement);

            element.timestampNanoseconds = TimestampNanoseconds.parse(container, element);
            element.originalOrderReferenceNumber = OriginalOrderReferenceNumber.parse(container, element);
            element.newOrderReferenceNumber = NewOrderReferenceNumber.parse(container, element);
            element.shares = Shares.parse(container, element);
            element.price = Price.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestampNanoseconds).append("\n");
            sb.append(this.originalOrderReferenceNumber).append("\n");
            sb.append(this.newOrderReferenceNumber).append("\n");
            sb.append(this.shares).append("\n");
            sb.append(this.price).append("\n");
            return sb.toString();
        }
    }

    /**
    * Order Delete Message
    */
    public static class OrderDeleteMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public OrderReferenceNumber orderReferenceNumber;

        // constructor for Order Delete Message
        private OrderDeleteMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Order Delete Message
        public static OrderDeleteMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new OrderDeleteMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.orderReferenceNumber = OrderReferenceNumber.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.orderReferenceNumber).append("\n");
            return sb.toString();
        }
    }

    // Canceled Shares: 4 Byte Unsigned Fixed Width Integer
    public static class CanceledShares implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public CanceledShares(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static CanceledShares parse(Container container, IBinaryProtocolElement element) {
            return new CanceledShares(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "canceledShares = "+ this.value;
        }
    }

    /**
    * Order Cancel Message
    */
    public static class OrderCancelMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public OrderReferenceNumber orderReferenceNumber;
        public CanceledShares canceledShares;

        // constructor for Order Cancel Message
        private OrderCancelMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Order Cancel Message
        public static OrderCancelMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new OrderCancelMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.orderReferenceNumber = OrderReferenceNumber.parse(container, element);
            element.canceledShares = CanceledShares.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.orderReferenceNumber).append("\n");
            sb.append(this.canceledShares).append("\n");
            return sb.toString();
        }
    }

    // Execution Price: 4 Byte Unsigned Fixed Width Integer
    public static class ExecutionPrice implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public ExecutionPrice(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static ExecutionPrice parse(Container container, IBinaryProtocolElement element) {
            return new ExecutionPrice(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "executionPrice = "+ this.value;
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

    // Executed Shares: 4 Byte Unsigned Fixed Width Integer
    public static class ExecutedShares implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public ExecutedShares(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static ExecutedShares parse(Container container, IBinaryProtocolElement element) {
            return new ExecutedShares(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "executedShares = "+ this.value;
        }
    }

    /**
    * Order Executed With Price Message
    */
    public static class OrderExecutedWithPriceMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public OrderReferenceNumber orderReferenceNumber;
        public ExecutedShares executedShares;
        public MatchNumber matchNumber;
        public Printable printable;
        public ExecutionPrice executionPrice;

        // constructor for Order Executed With Price Message
        private OrderExecutedWithPriceMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Order Executed With Price Message
        public static OrderExecutedWithPriceMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new OrderExecutedWithPriceMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.orderReferenceNumber = OrderReferenceNumber.parse(container, element);
            element.executedShares = ExecutedShares.parse(container, element);
            element.matchNumber = MatchNumber.parse(container, element);
            element.printable = Printable.parse(container, element);
            element.executionPrice = ExecutionPrice.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.orderReferenceNumber).append("\n");
            sb.append(this.executedShares).append("\n");
            sb.append(this.matchNumber).append("\n");
            sb.append(this.printable).append("\n");
            sb.append(this.executionPrice).append("\n");
            return sb.toString();
        }
    }

    /**
    * Order Executed Message
    */
    public static class OrderExecutedMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public OrderReferenceNumber orderReferenceNumber;
        public ExecutedShares executedShares;
        public MatchNumber matchNumber;

        // constructor for Order Executed Message
        private OrderExecutedMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Order Executed Message
        public static OrderExecutedMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new OrderExecutedMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.orderReferenceNumber = OrderReferenceNumber.parse(container, element);
            element.executedShares = ExecutedShares.parse(container, element);
            element.matchNumber = MatchNumber.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.orderReferenceNumber).append("\n");
            sb.append(this.executedShares).append("\n");
            sb.append(this.matchNumber).append("\n");
            return sb.toString();
        }
    }

    // Attribution: 4 Byte Ascii String
    public static class Attribution implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final String value;

        public Attribution(String value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Attribution parse(Container container, IBinaryProtocolElement element) {
            return new Attribution(BigEndianUtils.toString(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "attribution = "+ this.value;
        }
    }

    /**
    * Add Order With Mpid Message
    */
    public static class AddOrderWithMpidMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public OrderReferenceNumber orderReferenceNumber;
        public Side side;
        public Shares shares;
        public Stock stock;
        public Price price;
        public Attribution attribution;

        // constructor for Add Order With Mpid Message
        private AddOrderWithMpidMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Add Order With Mpid Message
        public static AddOrderWithMpidMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new AddOrderWithMpidMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.orderReferenceNumber = OrderReferenceNumber.parse(container, element);
            element.side = Side.parse(container, element);
            element.shares = Shares.parse(container, element);
            element.stock = Stock.parse(container, element);
            element.price = Price.parse(container, element);
            element.attribution = Attribution.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.orderReferenceNumber).append("\n");
            sb.append(this.side).append("\n");
            sb.append(this.shares).append("\n");
            sb.append(this.stock).append("\n");
            sb.append(this.price).append("\n");
            sb.append(this.attribution).append("\n");
            return sb.toString();
        }
    }

    /**
    * Add Order Message
    */
    public static class AddOrderMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public OrderReferenceNumber orderReferenceNumber;
        public Side side;
        public Shares shares;
        public Stock stock;
        public Price price;

        // constructor for Add Order Message
        private AddOrderMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Add Order Message
        public static AddOrderMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new AddOrderMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.orderReferenceNumber = OrderReferenceNumber.parse(container, element);
            element.side = Side.parse(container, element);
            element.shares = Shares.parse(container, element);
            element.stock = Stock.parse(container, element);
            element.price = Price.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.orderReferenceNumber).append("\n");
            sb.append(this.side).append("\n");
            sb.append(this.shares).append("\n");
            sb.append(this.stock).append("\n");
            sb.append(this.price).append("\n");
            return sb.toString();
        }
    }

    // Market Participant State: 1 Byte Ascii String Enum with 5 values
    public static class MarketParticipantState implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_MARKET_PARTICIPANT_STATE value;

        public MarketParticipantState(ENUM_MARKET_PARTICIPANT_STATE value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static MarketParticipantState parse(Container container, IBinaryProtocolElement element) {
            return new MarketParticipantState(ENUM_MARKET_PARTICIPANT_STATE.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "marketParticipantState = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Market Maker Mode: 1 Byte Ascii String Enum with 4 values
    public static class MarketMakerMode implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_MARKET_MAKER_MODE value;

        public MarketMakerMode(ENUM_MARKET_MAKER_MODE value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static MarketMakerMode parse(Container container, IBinaryProtocolElement element) {
            return new MarketMakerMode(ENUM_MARKET_MAKER_MODE.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "marketMakerMode = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Primary Market Maker: 1 Byte Ascii String Enum with 2 values
    public static class PrimaryMarketMaker implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_PRIMARY_MARKET_MAKER value;

        public PrimaryMarketMaker(ENUM_PRIMARY_MARKET_MAKER value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static PrimaryMarketMaker parse(Container container, IBinaryProtocolElement element) {
            return new PrimaryMarketMaker(ENUM_PRIMARY_MARKET_MAKER.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "primaryMarketMaker = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Mpid: 4 Byte Ascii String
    public static class Mpid implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final String value;

        public Mpid(String value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Mpid parse(Container container, IBinaryProtocolElement element) {
            return new Mpid(BigEndianUtils.toString(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "mpid = "+ this.value;
        }
    }

    /**
    * Market Participant Position Message
    */
    public static class MarketParticipantPositionMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public Mpid mpid;
        public Stock stock;
        public PrimaryMarketMaker primaryMarketMaker;
        public MarketMakerMode marketMakerMode;
        public MarketParticipantState marketParticipantState;

        // constructor for Market Participant Position Message
        private MarketParticipantPositionMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Market Participant Position Message
        public static MarketParticipantPositionMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new MarketParticipantPositionMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.mpid = Mpid.parse(container, element);
            element.stock = Stock.parse(container, element);
            element.primaryMarketMaker = PrimaryMarketMaker.parse(container, element);
            element.marketMakerMode = MarketMakerMode.parse(container, element);
            element.marketParticipantState = MarketParticipantState.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.mpid).append("\n");
            sb.append(this.stock).append("\n");
            sb.append(this.primaryMarketMaker).append("\n");
            sb.append(this.marketMakerMode).append("\n");
            sb.append(this.marketParticipantState).append("\n");
            return sb.toString();
        }
    }

    // Reg Sho Action: 1 Byte Ascii String Enum with 3 values
    public static class RegShoAction implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_REG_SHO_ACTION value;

        public RegShoAction(ENUM_REG_SHO_ACTION value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static RegShoAction parse(Container container, IBinaryProtocolElement element) {
            return new RegShoAction(ENUM_REG_SHO_ACTION.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "regShoAction = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    /**
    * Reg Sho Short Sale Price Test Restricted Indicator Message
    */
    public static class RegShoShortSalePriceTestRestrictedIndicatorMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public Stock stock;
        public RegShoAction regShoAction;

        // constructor for Reg Sho Short Sale Price Test Restricted Indicator Message
        private RegShoShortSalePriceTestRestrictedIndicatorMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Reg Sho Short Sale Price Test Restricted Indicator Message
        public static RegShoShortSalePriceTestRestrictedIndicatorMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new RegShoShortSalePriceTestRestrictedIndicatorMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.stock = Stock.parse(container, element);
            element.regShoAction = RegShoAction.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.stock).append("\n");
            sb.append(this.regShoAction).append("\n");
            return sb.toString();
        }
    }

    // Financial Status Indicator: 1 Byte Ascii String Enum with 8 values
    public static class FinancialStatusIndicator implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_FINANCIAL_STATUS_INDICATOR value;

        public FinancialStatusIndicator(ENUM_FINANCIAL_STATUS_INDICATOR value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static FinancialStatusIndicator parse(Container container, IBinaryProtocolElement element) {
            return new FinancialStatusIndicator(ENUM_FINANCIAL_STATUS_INDICATOR.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "financialStatusIndicator = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Market Category: 1 Byte Ascii String Enum with 7 values
    public static class MarketCategory implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_MARKET_CATEGORY value;

        public MarketCategory(ENUM_MARKET_CATEGORY value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static MarketCategory parse(Container container, IBinaryProtocolElement element) {
            return new MarketCategory(ENUM_MARKET_CATEGORY.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "marketCategory = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    /**
    * Stock Directory Message
    */
    public static class StockDirectoryMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public Stock stock;
        public MarketCategory marketCategory;
        public FinancialStatusIndicator financialStatusIndicator;

        // constructor for Stock Directory Message
        private StockDirectoryMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Stock Directory Message
        public static StockDirectoryMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new StockDirectoryMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.stock = Stock.parse(container, element);
            element.marketCategory = MarketCategory.parse(container, element);
            element.financialStatusIndicator = FinancialStatusIndicator.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.stock).append("\n");
            sb.append(this.marketCategory).append("\n");
            sb.append(this.financialStatusIndicator).append("\n");
            return sb.toString();
        }
    }

    // Event Code: 1 Byte Ascii String Enum with 9 values
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
    * Time Stamp Message
    */
    public static class TimeStampMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Second second;

        // constructor for Time Stamp Message
        private TimeStampMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Time Stamp Message
        public static TimeStampMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new TimeStampMessage(parentElement);

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
        
        // -- parsing Time Stamp Message
        if (messageType.enumType == 'T') {
            return TimeStampMessage.parse(container, null);
        }
        // -- parsing System Event Message
        if (messageType.enumType == 'S') {
            return SystemEventMessage.parse(container, null);
        }
        // -- parsing Stock Directory Message
        if (messageType.enumType == 'R') {
            return StockDirectoryMessage.parse(container, null);
        }
        // -- parsing Reg Sho Short Sale Price Test Restricted Indicator Message
        if (messageType.enumType == 'Y') {
            return RegShoShortSalePriceTestRestrictedIndicatorMessage.parse(container, null);
        }
        // -- parsing Market Participant Position Message
        if (messageType.enumType == 'L') {
            return MarketParticipantPositionMessage.parse(container, null);
        }
        // -- parsing Add Order Message
        if (messageType.enumType == 'A') {
            return AddOrderMessage.parse(container, null);
        }
        // -- parsing Add Order With Mpid Message
        if (messageType.enumType == 'F') {
            return AddOrderWithMpidMessage.parse(container, null);
        }
        // -- parsing Order Executed Message
        if (messageType.enumType == 'E') {
            return OrderExecutedMessage.parse(container, null);
        }
        // -- parsing Order Executed With Price Message
        if (messageType.enumType == 'C') {
            return OrderExecutedWithPriceMessage.parse(container, null);
        }
        // -- parsing Order Cancel Message
        if (messageType.enumType == 'X') {
            return OrderCancelMessage.parse(container, null);
        }
        // -- parsing Order Delete Message
        if (messageType.enumType == 'D') {
            return OrderDeleteMessage.parse(container, null);
        }
        // -- parsing Order Replace Message
        if (messageType.enumType == 'U') {
            return OrderReplaceMessage.parse(container, null);
        }
        // -- parsing Trade Message
        if (messageType.enumType == 'P') {
            return TradeMessage.parse(container, null);
        }
        // -- parsing Cross Trade Message
        if (messageType.enumType == 'Q') {
            return CrossTradeMessage.parse(container, null);
        }
        // -- parsing Broken Trade Message
        if (messageType.enumType == 'B') {
            return BrokenTradeMessage.parse(container, null);
        }
        // -- parsing Net Order Imbalance Indicator Message
        if (messageType.enumType == 'I') {
            return NetOrderImbalanceIndicatorMessage.parse(container, null);
        }
        // -- parsing Retail Price Improvement Indicator Message
        if (messageType.enumType == 'N') {
            return RetailPriceImprovementIndicatorMessage.parse(container, null);
        }

        return null;
    }

    // Message Type: 1 Byte Ascii String Enum with 17 values
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
