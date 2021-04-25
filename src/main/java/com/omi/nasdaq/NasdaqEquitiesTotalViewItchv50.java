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
* Java parser for Nasdaq Equities Itch TotalView 5.0 protocol
* 
* @version 1.0
* @since 04/25/2021 15:26:15
*/

public class NasdaqEquitiesTotalViewItchv50 {

    ///////////////////////////////////////////////////////////////////////
    // Enum Values
    ///////////////////////////////////////////////////////////////////////

    /**
    * Authenticity Values
    */
    public enum ENUM_AUTHENTICITY {
        E_P('P', "Live Production"),
        E_T('T', "Test");

        public final Character enumType;
        public final String enumValue;

        ENUM_AUTHENTICITY(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_AUTHENTICITY> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_AUTHENTICITY s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_AUTHENTICITY valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Breached Level Values
    */
    public enum ENUM_BREACHED_LEVEL {
        E_1('1', "Level 1"),
        E_2('2', "Level 2"),
        E_3('3', "Level 3");

        public final Character enumType;
        public final String enumValue;

        ENUM_BREACHED_LEVEL(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_BREACHED_LEVEL> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_BREACHED_LEVEL s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_BREACHED_LEVEL valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Buy Sell Indicator Values
    */
    public enum ENUM_BUY_SELL_INDICATOR {
        E_B('B', "Buy"),
        E_S('S', "Sell");

        public final Character enumType;
        public final String enumValue;

        ENUM_BUY_SELL_INDICATOR(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_BUY_SELL_INDICATOR> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_BUY_SELL_INDICATOR s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_BUY_SELL_INDICATOR valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Cross Type Values
    */
    public enum ENUM_CROSS_TYPE {
        E_O('O', "Opening Cross"),
        E_C('C', "Closing Cross"),
        E_H('H', "Cross Halted Or Paused"),
        E_I('I', "Intraday Cross And Post Close Cross");

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
    * Etp Flag Values
    */
    public enum ENUM_ETP_FLAG {
        E_Y('Y', "Etp"),
        E_N('N', "Not Etp"),
        E_SPACE(' ', "Not Available");

        public final Character enumType;
        public final String enumValue;

        ENUM_ETP_FLAG(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_ETP_FLAG> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_ETP_FLAG s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_ETP_FLAG valueOf(Character s)  {
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
        E_C('C', "End Of Messages");

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
        E_D('D', "Deficient"),
        E_E('E', "Delinquent"),
        E_Q('Q', "Bankrupt"),
        E_S('S', "Suspended"),
        E_G('G', "Deficient And Bankrupt"),
        E_H('H', "Deficient And Delinquent"),
        E_J('J', "Delinquent And Bankrupt"),
        E_K('K', "Deficient Delinquent And Bankrupt"),
        E_C('C', "Creations And Redemptions Suspended"),
        E_N('N', "Normal"),
        E_SPACE(' ', "Na");

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
        E_N('N', "No Imbalance"),
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
        E_B('B', "Buy Side Rpi Orders Available"),
        E_S('S', "Sell Side Rpi Orders Available"),
        E_A('A', "Both Sides Rpi Orders Available"),
        E_N('N', "No Rpi Orders Available");

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
    * Inverse Indicator Values
    */
    public enum ENUM_INVERSE_INDICATOR {
        E_Y('Y', "Inverse Etp"),
        E_N('N', "Not Inverse Etp");

        public final Character enumType;
        public final String enumValue;

        ENUM_INVERSE_INDICATOR(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_INVERSE_INDICATOR> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_INVERSE_INDICATOR s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_INVERSE_INDICATOR valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Ipo Flag Values
    */
    public enum ENUM_IPO_FLAG {
        E_Y('Y', "Set Up For Ipo Realease"),
        E_N('N', "Not Set Up For Ipo Realease"),
        E_SPACE(' ', "Not Available");

        public final Character enumType;
        public final String enumValue;

        ENUM_IPO_FLAG(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_IPO_FLAG> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_IPO_FLAG s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_IPO_FLAG valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Ipo Quotation Release Qualifier Values
    */
    public enum ENUM_IPO_QUOTATION_RELEASE_QUALIFIER {
        E_A('A', "Anticipated Quotation Release Time"),
        E_C('C', "Ipo Release Canceled Or Postponed");

        public final Character enumType;
        public final String enumValue;

        ENUM_IPO_QUOTATION_RELEASE_QUALIFIER(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_IPO_QUOTATION_RELEASE_QUALIFIER> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_IPO_QUOTATION_RELEASE_QUALIFIER s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_IPO_QUOTATION_RELEASE_QUALIFIER valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Issue Classification Values
    */
    public enum ENUM_ISSUE_CLASSIFICATION {
        E_A('A', "American Depositary Share"),
        E_B('B', "Bond"),
        E_C('C', "Common"),
        E_F('F', "Depository"),
        E_I('I', "144 A"),
        E_L('L', "Limited"),
        E_N('N', "Notes"),
        E_O('O', "Ordinary Share"),
        E_P('P', "Preferred"),
        E_Q('Q', "Other"),
        E_R('R', "Right"),
        E_S('S', "Shares"),
        E_T('T', "Convertible"),
        E_U('U', "Unit"),
        E_V('V', "Units Benif Int"),
        E_W('W', "Warrant");

        public final Character enumType;
        public final String enumValue;

        ENUM_ISSUE_CLASSIFICATION(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_ISSUE_CLASSIFICATION> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_ISSUE_CLASSIFICATION s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_ISSUE_CLASSIFICATION valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Luld Reference Price Tier Values
    */
    public enum ENUM_LULD_REFERENCE_PRICE_TIER {
        E_1('1', "Tier 1"),
        E_2('2', "Tier 2"),
        E_SPACE(' ', "Not Available");

        public final Character enumType;
        public final String enumValue;

        ENUM_LULD_REFERENCE_PRICE_TIER(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_LULD_REFERENCE_PRICE_TIER> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_LULD_REFERENCE_PRICE_TIER s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_LULD_REFERENCE_PRICE_TIER valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Market Category Values
    */
    public enum ENUM_MARKET_CATEGORY {
        E_Q('Q', "Nasdaq Global Select Market"),
        E_G('G', "Nasdaq Global Market"),
        E_S('S', "Nasdaq Capital Market"),
        E_N('N', "Nyse"),
        E_A('A', "Nyse Mkt"),
        E_P('P', "Nyse Arca"),
        E_Z('Z', "Bats Z"),
        E_V('V', "Investors Exchange"),
        E_SPACE(' ', "Na");

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
        E_R('R', "Pre Syndicate"),
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
        E_E('E', "Excused"),
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
        E_S('S', "System Event Message"),
        E_R('R', "Stock Directory Message"),
        E_H('H', "Stock Trading Action Message"),
        E_Y('Y', "Reg Sho Short Sale Price Test Restricted Indicator Message"),
        E_L('L', "Market Participant Position Message"),
        E_V('V', "Mwcb Decline Level Message"),
        E_W('W', "Mwcb Status Level Message"),
        E_K('K', "Ipo Quoting Period Update"),
        E_A('A', "Add Order No Mpid Attribution Message"),
        E_J('J', "Luld Auction Collar Message"),
        E_F('F', "Add Order With Mpid Attribution Message"),
        E_E('E', "Order Executed Message"),
        E_C('C', "Order Executed With Price Message"),
        E_X('X', "Order Cancel Message"),
        E_D('D', "Order Delete Message"),
        E_U('U', "Order Replace Message"),
        E_P('P', "Non Cross Trade Message"),
        E_Q('Q', "Cross Trade Message"),
        E_B('B', "Broken Trade Message"),
        E_I('I', "Net Order Imbalance Indicator Message"),
        E_N('N', "Retail Interest Message");

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
        E_N('N', "Non Primary");

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
        E_N('N', "Non Printable"),
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
        E_0('0', "No Price Test"),
        E_1('1', "Reg Sho Short Sale Price Test Restriction"),
        E_2('2', "Test Restriction Remains");

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
    * Round Lots Only Values
    */
    public enum ENUM_ROUND_LOTS_ONLY {
        E_Y('Y', "Round Lots Only"),
        E_N('N', "No Restrictions");

        public final Character enumType;
        public final String enumValue;

        ENUM_ROUND_LOTS_ONLY(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_ROUND_LOTS_ONLY> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_ROUND_LOTS_ONLY s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_ROUND_LOTS_ONLY valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Short Sale Threshold Indicator Values
    */
    public enum ENUM_SHORT_SALE_THRESHOLD_INDICATOR {
        E_Y('Y', "Restricted"),
        E_N('N', "Not Restricted"),
        E_SPACE(' ', "Na");

        public final Character enumType;
        public final String enumValue;

        ENUM_SHORT_SALE_THRESHOLD_INDICATOR(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_SHORT_SALE_THRESHOLD_INDICATOR> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_SHORT_SALE_THRESHOLD_INDICATOR s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_SHORT_SALE_THRESHOLD_INDICATOR valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Trading State Values
    */
    public enum ENUM_TRADING_STATE {
        E_H('H', "Halted"),
        E_P('P', "Paused"),
        E_Q('Q', "Quotation Only Period"),
        E_T('T', "Trading");

        public final Character enumType;
        public final String enumValue;

        ENUM_TRADING_STATE(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_TRADING_STATE> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_TRADING_STATE s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_TRADING_STATE valueOf(Character s)  {
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

    // Timestamp: 6 Byte Unsigned Fixed Width Integer
    public static class Timestamp implements IBinaryProtocolElement {
        public static final int LENGTH = 6;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public Timestamp(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Timestamp parse(Container container, IBinaryProtocolElement element) {
            return new Timestamp(BigEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "timestamp = "+ this.value;
        }
    }

    // Tracking Number: 2 Byte Unsigned Fixed Width Integer
    public static class TrackingNumber implements IBinaryProtocolElement {
        public static final int LENGTH = 2;
        public final IBinaryProtocolElement parent;
        public final Short value;

        public TrackingNumber(Short value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static TrackingNumber parse(Container container, IBinaryProtocolElement element) {
            return new TrackingNumber(BigEndianUtils.toShort(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "trackingNumber = "+ this.value;
        }
    }

    // Stock Locate: 2 Byte Unsigned Fixed Width Integer
    public static class StockLocate implements IBinaryProtocolElement {
        public static final int LENGTH = 2;
        public final IBinaryProtocolElement parent;
        public final Short value;

        public StockLocate(Short value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static StockLocate parse(Container container, IBinaryProtocolElement element) {
            return new StockLocate(BigEndianUtils.toShort(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "stockLocate = "+ this.value;
        }
    }

    /**
    * Retail Interest Message
    */
    public static class RetailInterestMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public StockLocate stockLocate;
        public TrackingNumber trackingNumber;
        public Timestamp timestamp;
        public Stock stock;
        public InterestFlag interestFlag;

        // constructor for Retail Interest Message
        private RetailInterestMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Retail Interest Message
        public static RetailInterestMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new RetailInterestMessage(parentElement);

            element.stockLocate = StockLocate.parse(container, element);
            element.trackingNumber = TrackingNumber.parse(container, element);
            element.timestamp = Timestamp.parse(container, element);
            element.stock = Stock.parse(container, element);
            element.interestFlag = InterestFlag.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.stockLocate).append("\n");
            sb.append(this.trackingNumber).append("\n");
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

    // Current Reference Price: 4 Byte Signed Fixed Width Integer
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

    // Near Price: 4 Byte Signed Fixed Width Integer
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

    // Far Price: 4 Byte Signed Fixed Width Integer
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
        public StockLocate stockLocate;
        public TrackingNumber trackingNumber;
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

            element.stockLocate = StockLocate.parse(container, element);
            element.trackingNumber = TrackingNumber.parse(container, element);
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
            sb.append(this.stockLocate).append("\n");
            sb.append(this.trackingNumber).append("\n");
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
        public StockLocate stockLocate;
        public TrackingNumber trackingNumber;
        public Timestamp timestamp;
        public MatchNumber matchNumber;

        // constructor for Broken Trade Message
        private BrokenTradeMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Broken Trade Message
        public static BrokenTradeMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new BrokenTradeMessage(parentElement);

            element.stockLocate = StockLocate.parse(container, element);
            element.trackingNumber = TrackingNumber.parse(container, element);
            element.timestamp = Timestamp.parse(container, element);
            element.matchNumber = MatchNumber.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.stockLocate).append("\n");
            sb.append(this.trackingNumber).append("\n");
            sb.append(this.timestamp).append("\n");
            sb.append(this.matchNumber).append("\n");
            return sb.toString();
        }
    }

    // Cross Price: 4 Byte Signed Fixed Width Integer
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
        public StockLocate stockLocate;
        public TrackingNumber trackingNumber;
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

            element.stockLocate = StockLocate.parse(container, element);
            element.trackingNumber = TrackingNumber.parse(container, element);
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
            sb.append(this.stockLocate).append("\n");
            sb.append(this.trackingNumber).append("\n");
            sb.append(this.timestamp).append("\n");
            sb.append(this.crossShares).append("\n");
            sb.append(this.stock).append("\n");
            sb.append(this.crossPrice).append("\n");
            sb.append(this.matchNumber).append("\n");
            sb.append(this.crossType).append("\n");
            return sb.toString();
        }
    }

    // Price: 4 Byte Signed Fixed Width Integer
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

    // Buy Sell Indicator: 1 Byte Ascii String Enum with 2 values
    public static class BuySellIndicator implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_BUY_SELL_INDICATOR value;

        public BuySellIndicator(ENUM_BUY_SELL_INDICATOR value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static BuySellIndicator parse(Container container, IBinaryProtocolElement element) {
            return new BuySellIndicator(ENUM_BUY_SELL_INDICATOR.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "buySellIndicator = "+ this.value.enumType +" ("+ this.value.enumValue +")";
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
    * Non Cross Trade Message
    */
    public static class NonCrossTradeMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public StockLocate stockLocate;
        public TrackingNumber trackingNumber;
        public Timestamp timestamp;
        public OrderReferenceNumber orderReferenceNumber;
        public BuySellIndicator buySellIndicator;
        public Shares shares;
        public Stock stock;
        public Price price;
        public MatchNumber matchNumber;

        // constructor for Non Cross Trade Message
        private NonCrossTradeMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Non Cross Trade Message
        public static NonCrossTradeMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new NonCrossTradeMessage(parentElement);

            element.stockLocate = StockLocate.parse(container, element);
            element.trackingNumber = TrackingNumber.parse(container, element);
            element.timestamp = Timestamp.parse(container, element);
            element.orderReferenceNumber = OrderReferenceNumber.parse(container, element);
            element.buySellIndicator = BuySellIndicator.parse(container, element);
            element.shares = Shares.parse(container, element);
            element.stock = Stock.parse(container, element);
            element.price = Price.parse(container, element);
            element.matchNumber = MatchNumber.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.stockLocate).append("\n");
            sb.append(this.trackingNumber).append("\n");
            sb.append(this.timestamp).append("\n");
            sb.append(this.orderReferenceNumber).append("\n");
            sb.append(this.buySellIndicator).append("\n");
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

    /**
    * Order Replace Message
    */
    public static class OrderReplaceMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public StockLocate stockLocate;
        public TrackingNumber trackingNumber;
        public Timestamp timestamp;
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

            element.stockLocate = StockLocate.parse(container, element);
            element.trackingNumber = TrackingNumber.parse(container, element);
            element.timestamp = Timestamp.parse(container, element);
            element.originalOrderReferenceNumber = OriginalOrderReferenceNumber.parse(container, element);
            element.newOrderReferenceNumber = NewOrderReferenceNumber.parse(container, element);
            element.shares = Shares.parse(container, element);
            element.price = Price.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.stockLocate).append("\n");
            sb.append(this.trackingNumber).append("\n");
            sb.append(this.timestamp).append("\n");
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
        public StockLocate stockLocate;
        public TrackingNumber trackingNumber;
        public Timestamp timestamp;
        public OrderReferenceNumber orderReferenceNumber;

        // constructor for Order Delete Message
        private OrderDeleteMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Order Delete Message
        public static OrderDeleteMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new OrderDeleteMessage(parentElement);

            element.stockLocate = StockLocate.parse(container, element);
            element.trackingNumber = TrackingNumber.parse(container, element);
            element.timestamp = Timestamp.parse(container, element);
            element.orderReferenceNumber = OrderReferenceNumber.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.stockLocate).append("\n");
            sb.append(this.trackingNumber).append("\n");
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
        public StockLocate stockLocate;
        public TrackingNumber trackingNumber;
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

            element.stockLocate = StockLocate.parse(container, element);
            element.trackingNumber = TrackingNumber.parse(container, element);
            element.timestamp = Timestamp.parse(container, element);
            element.orderReferenceNumber = OrderReferenceNumber.parse(container, element);
            element.canceledShares = CanceledShares.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.stockLocate).append("\n");
            sb.append(this.trackingNumber).append("\n");
            sb.append(this.timestamp).append("\n");
            sb.append(this.orderReferenceNumber).append("\n");
            sb.append(this.canceledShares).append("\n");
            return sb.toString();
        }
    }

    // Execution Price: 4 Byte Signed Fixed Width Integer
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
        public StockLocate stockLocate;
        public TrackingNumber trackingNumber;
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

            element.stockLocate = StockLocate.parse(container, element);
            element.trackingNumber = TrackingNumber.parse(container, element);
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
            sb.append(this.stockLocate).append("\n");
            sb.append(this.trackingNumber).append("\n");
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
        public StockLocate stockLocate;
        public TrackingNumber trackingNumber;
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

            element.stockLocate = StockLocate.parse(container, element);
            element.trackingNumber = TrackingNumber.parse(container, element);
            element.timestamp = Timestamp.parse(container, element);
            element.orderReferenceNumber = OrderReferenceNumber.parse(container, element);
            element.executedShares = ExecutedShares.parse(container, element);
            element.matchNumber = MatchNumber.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.stockLocate).append("\n");
            sb.append(this.trackingNumber).append("\n");
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
    * Add Order With Mpid Attribution Message
    */
    public static class AddOrderWithMpidAttributionMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public StockLocate stockLocate;
        public TrackingNumber trackingNumber;
        public Timestamp timestamp;
        public OrderReferenceNumber orderReferenceNumber;
        public BuySellIndicator buySellIndicator;
        public Shares shares;
        public Stock stock;
        public Price price;
        public Attribution attribution;

        // constructor for Add Order With Mpid Attribution Message
        private AddOrderWithMpidAttributionMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Add Order With Mpid Attribution Message
        public static AddOrderWithMpidAttributionMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new AddOrderWithMpidAttributionMessage(parentElement);

            element.stockLocate = StockLocate.parse(container, element);
            element.trackingNumber = TrackingNumber.parse(container, element);
            element.timestamp = Timestamp.parse(container, element);
            element.orderReferenceNumber = OrderReferenceNumber.parse(container, element);
            element.buySellIndicator = BuySellIndicator.parse(container, element);
            element.shares = Shares.parse(container, element);
            element.stock = Stock.parse(container, element);
            element.price = Price.parse(container, element);
            element.attribution = Attribution.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.stockLocate).append("\n");
            sb.append(this.trackingNumber).append("\n");
            sb.append(this.timestamp).append("\n");
            sb.append(this.orderReferenceNumber).append("\n");
            sb.append(this.buySellIndicator).append("\n");
            sb.append(this.shares).append("\n");
            sb.append(this.stock).append("\n");
            sb.append(this.price).append("\n");
            sb.append(this.attribution).append("\n");
            return sb.toString();
        }
    }

    // Auction Collar Extension: 4 Byte Unsigned Fixed Width Integer
    public static class AuctionCollarExtension implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public AuctionCollarExtension(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static AuctionCollarExtension parse(Container container, IBinaryProtocolElement element) {
            return new AuctionCollarExtension(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "auctionCollarExtension = "+ this.value;
        }
    }

    // Lower Auction Collar Price: 4 Byte Signed Fixed Width Integer
    public static class LowerAuctionCollarPrice implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public LowerAuctionCollarPrice(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static LowerAuctionCollarPrice parse(Container container, IBinaryProtocolElement element) {
            return new LowerAuctionCollarPrice(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "lowerAuctionCollarPrice = "+ this.value;
        }
    }

    // Upper Auction Collar Price: 4 Byte Signed Fixed Width Integer
    public static class UpperAuctionCollarPrice implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public UpperAuctionCollarPrice(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static UpperAuctionCollarPrice parse(Container container, IBinaryProtocolElement element) {
            return new UpperAuctionCollarPrice(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "upperAuctionCollarPrice = "+ this.value;
        }
    }

    // Auction Collar Reference Price: 4 Byte Signed Fixed Width Integer
    public static class AuctionCollarReferencePrice implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public AuctionCollarReferencePrice(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static AuctionCollarReferencePrice parse(Container container, IBinaryProtocolElement element) {
            return new AuctionCollarReferencePrice(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "auctionCollarReferencePrice = "+ this.value;
        }
    }

    /**
    * Luld Auction Collar Message
    */
    public static class LuldAuctionCollarMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public StockLocate stockLocate;
        public TrackingNumber trackingNumber;
        public Timestamp timestamp;
        public Stock stock;
        public AuctionCollarReferencePrice auctionCollarReferencePrice;
        public UpperAuctionCollarPrice upperAuctionCollarPrice;
        public LowerAuctionCollarPrice lowerAuctionCollarPrice;
        public AuctionCollarExtension auctionCollarExtension;

        // constructor for Luld Auction Collar Message
        private LuldAuctionCollarMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Luld Auction Collar Message
        public static LuldAuctionCollarMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new LuldAuctionCollarMessage(parentElement);

            element.stockLocate = StockLocate.parse(container, element);
            element.trackingNumber = TrackingNumber.parse(container, element);
            element.timestamp = Timestamp.parse(container, element);
            element.stock = Stock.parse(container, element);
            element.auctionCollarReferencePrice = AuctionCollarReferencePrice.parse(container, element);
            element.upperAuctionCollarPrice = UpperAuctionCollarPrice.parse(container, element);
            element.lowerAuctionCollarPrice = LowerAuctionCollarPrice.parse(container, element);
            element.auctionCollarExtension = AuctionCollarExtension.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.stockLocate).append("\n");
            sb.append(this.trackingNumber).append("\n");
            sb.append(this.timestamp).append("\n");
            sb.append(this.stock).append("\n");
            sb.append(this.auctionCollarReferencePrice).append("\n");
            sb.append(this.upperAuctionCollarPrice).append("\n");
            sb.append(this.lowerAuctionCollarPrice).append("\n");
            sb.append(this.auctionCollarExtension).append("\n");
            return sb.toString();
        }
    }

    /**
    * Add Order No Mpid Attribution Message
    */
    public static class AddOrderNoMpidAttributionMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public StockLocate stockLocate;
        public TrackingNumber trackingNumber;
        public Timestamp timestamp;
        public OrderReferenceNumber orderReferenceNumber;
        public BuySellIndicator buySellIndicator;
        public Shares shares;
        public Stock stock;
        public Price price;

        // constructor for Add Order No Mpid Attribution Message
        private AddOrderNoMpidAttributionMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Add Order No Mpid Attribution Message
        public static AddOrderNoMpidAttributionMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new AddOrderNoMpidAttributionMessage(parentElement);

            element.stockLocate = StockLocate.parse(container, element);
            element.trackingNumber = TrackingNumber.parse(container, element);
            element.timestamp = Timestamp.parse(container, element);
            element.orderReferenceNumber = OrderReferenceNumber.parse(container, element);
            element.buySellIndicator = BuySellIndicator.parse(container, element);
            element.shares = Shares.parse(container, element);
            element.stock = Stock.parse(container, element);
            element.price = Price.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.stockLocate).append("\n");
            sb.append(this.trackingNumber).append("\n");
            sb.append(this.timestamp).append("\n");
            sb.append(this.orderReferenceNumber).append("\n");
            sb.append(this.buySellIndicator).append("\n");
            sb.append(this.shares).append("\n");
            sb.append(this.stock).append("\n");
            sb.append(this.price).append("\n");
            return sb.toString();
        }
    }

    // Ipo Price: 4 Byte Signed Fixed Width Integer
    public static class IpoPrice implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public IpoPrice(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static IpoPrice parse(Container container, IBinaryProtocolElement element) {
            return new IpoPrice(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "ipoPrice = "+ this.value;
        }
    }

    // Ipo Quotation Release Qualifier: 1 Byte Ascii String Enum with 2 values
    public static class IpoQuotationReleaseQualifier implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_IPO_QUOTATION_RELEASE_QUALIFIER value;

        public IpoQuotationReleaseQualifier(ENUM_IPO_QUOTATION_RELEASE_QUALIFIER value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static IpoQuotationReleaseQualifier parse(Container container, IBinaryProtocolElement element) {
            return new IpoQuotationReleaseQualifier(ENUM_IPO_QUOTATION_RELEASE_QUALIFIER.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "ipoQuotationReleaseQualifier = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Ipo Quotation Release Time: 4 Byte Unsigned Fixed Width Integer
    public static class IpoQuotationReleaseTime implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public IpoQuotationReleaseTime(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static IpoQuotationReleaseTime parse(Container container, IBinaryProtocolElement element) {
            return new IpoQuotationReleaseTime(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "ipoQuotationReleaseTime = "+ this.value;
        }
    }

    /**
    * Ipo Quoting Period Update
    */
    public static class IpoQuotingPeriodUpdate implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public StockLocate stockLocate;
        public TrackingNumber trackingNumber;
        public Timestamp timestamp;
        public Stock stock;
        public IpoQuotationReleaseTime ipoQuotationReleaseTime;
        public IpoQuotationReleaseQualifier ipoQuotationReleaseQualifier;
        public IpoPrice ipoPrice;

        // constructor for Ipo Quoting Period Update
        private IpoQuotingPeriodUpdate(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Ipo Quoting Period Update
        public static IpoQuotingPeriodUpdate parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new IpoQuotingPeriodUpdate(parentElement);

            element.stockLocate = StockLocate.parse(container, element);
            element.trackingNumber = TrackingNumber.parse(container, element);
            element.timestamp = Timestamp.parse(container, element);
            element.stock = Stock.parse(container, element);
            element.ipoQuotationReleaseTime = IpoQuotationReleaseTime.parse(container, element);
            element.ipoQuotationReleaseQualifier = IpoQuotationReleaseQualifier.parse(container, element);
            element.ipoPrice = IpoPrice.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.stockLocate).append("\n");
            sb.append(this.trackingNumber).append("\n");
            sb.append(this.timestamp).append("\n");
            sb.append(this.stock).append("\n");
            sb.append(this.ipoQuotationReleaseTime).append("\n");
            sb.append(this.ipoQuotationReleaseQualifier).append("\n");
            sb.append(this.ipoPrice).append("\n");
            return sb.toString();
        }
    }

    // Breached Level: 1 Byte Ascii String Enum with 3 values
    public static class BreachedLevel implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_BREACHED_LEVEL value;

        public BreachedLevel(ENUM_BREACHED_LEVEL value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static BreachedLevel parse(Container container, IBinaryProtocolElement element) {
            return new BreachedLevel(ENUM_BREACHED_LEVEL.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "breachedLevel = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    /**
    * Mwcb Status Level Message
    */
    public static class MwcbStatusLevelMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public StockLocate stockLocate;
        public TrackingNumber trackingNumber;
        public Timestamp timestamp;
        public BreachedLevel breachedLevel;

        // constructor for Mwcb Status Level Message
        private MwcbStatusLevelMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Mwcb Status Level Message
        public static MwcbStatusLevelMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new MwcbStatusLevelMessage(parentElement);

            element.stockLocate = StockLocate.parse(container, element);
            element.trackingNumber = TrackingNumber.parse(container, element);
            element.timestamp = Timestamp.parse(container, element);
            element.breachedLevel = BreachedLevel.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.stockLocate).append("\n");
            sb.append(this.trackingNumber).append("\n");
            sb.append(this.timestamp).append("\n");
            sb.append(this.breachedLevel).append("\n");
            return sb.toString();
        }
    }

    // Level 3: 8 Byte Signed Fixed Width Integer
    public static class Level3 implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public Level3(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Level3 parse(Container container, IBinaryProtocolElement element) {
            return new Level3(BigEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "level3 = "+ this.value;
        }
    }

    // Level 2: 8 Byte Signed Fixed Width Integer
    public static class Level2 implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public Level2(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Level2 parse(Container container, IBinaryProtocolElement element) {
            return new Level2(BigEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "level2 = "+ this.value;
        }
    }

    // Level 1: 8 Byte Signed Fixed Width Integer
    public static class Level1 implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public Level1(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Level1 parse(Container container, IBinaryProtocolElement element) {
            return new Level1(BigEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "level1 = "+ this.value;
        }
    }

    /**
    * Mwcb Decline Level Message
    */
    public static class MwcbDeclineLevelMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public StockLocate stockLocate;
        public TrackingNumber trackingNumber;
        public Timestamp timestamp;
        public Level1 level1;
        public Level2 level2;
        public Level3 level3;

        // constructor for Mwcb Decline Level Message
        private MwcbDeclineLevelMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Mwcb Decline Level Message
        public static MwcbDeclineLevelMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new MwcbDeclineLevelMessage(parentElement);

            element.stockLocate = StockLocate.parse(container, element);
            element.trackingNumber = TrackingNumber.parse(container, element);
            element.timestamp = Timestamp.parse(container, element);
            element.level1 = Level1.parse(container, element);
            element.level2 = Level2.parse(container, element);
            element.level3 = Level3.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.stockLocate).append("\n");
            sb.append(this.trackingNumber).append("\n");
            sb.append(this.timestamp).append("\n");
            sb.append(this.level1).append("\n");
            sb.append(this.level2).append("\n");
            sb.append(this.level3).append("\n");
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

    // Market Maker Mode: 1 Byte Ascii String Enum with 5 values
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
        public StockLocate stockLocate;
        public TrackingNumber trackingNumber;
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

            element.stockLocate = StockLocate.parse(container, element);
            element.trackingNumber = TrackingNumber.parse(container, element);
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
            sb.append(this.stockLocate).append("\n");
            sb.append(this.trackingNumber).append("\n");
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

    // Locate Code: 2 Byte Unsigned Fixed Width Integer
    public static class LocateCode implements IBinaryProtocolElement {
        public static final int LENGTH = 2;
        public final IBinaryProtocolElement parent;
        public final Short value;

        public LocateCode(Short value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static LocateCode parse(Container container, IBinaryProtocolElement element) {
            return new LocateCode(BigEndianUtils.toShort(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "locateCode = "+ this.value;
        }
    }

    /**
    * Reg Sho Short Sale Price Test Restricted Indicator Message
    */
    public static class RegShoShortSalePriceTestRestrictedIndicatorMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public LocateCode locateCode;
        public TrackingNumber trackingNumber;
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

            element.locateCode = LocateCode.parse(container, element);
            element.trackingNumber = TrackingNumber.parse(container, element);
            element.timestamp = Timestamp.parse(container, element);
            element.stock = Stock.parse(container, element);
            element.regShoAction = RegShoAction.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.locateCode).append("\n");
            sb.append(this.trackingNumber).append("\n");
            sb.append(this.timestamp).append("\n");
            sb.append(this.stock).append("\n");
            sb.append(this.regShoAction).append("\n");
            return sb.toString();
        }
    }

    // Reason: 4 Byte Ascii String
    public static class Reason implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final String value;

        public Reason(String value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Reason parse(Container container, IBinaryProtocolElement element) {
            return new Reason(BigEndianUtils.toString(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "reason = "+ this.value;
        }
    }

    // Reserved: 1 Byte Ascii String
    public static class Reserved implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Character value;

        public Reserved(Character value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Reserved parse(Container container, IBinaryProtocolElement element) {
            return new Reserved(BigEndianUtils.toCharacter(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "reserved = "+ this.value;
        }
    }

    // Trading State: 1 Byte Ascii String Enum with 4 values
    public static class TradingState implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_TRADING_STATE value;

        public TradingState(ENUM_TRADING_STATE value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static TradingState parse(Container container, IBinaryProtocolElement element) {
            return new TradingState(ENUM_TRADING_STATE.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "tradingState = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    /**
    * Stock Trading Action Message
    */
    public static class StockTradingActionMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public StockLocate stockLocate;
        public TrackingNumber trackingNumber;
        public Timestamp timestamp;
        public Stock stock;
        public TradingState tradingState;
        public Reserved reserved;
        public Reason reason;

        // constructor for Stock Trading Action Message
        private StockTradingActionMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Stock Trading Action Message
        public static StockTradingActionMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new StockTradingActionMessage(parentElement);

            element.stockLocate = StockLocate.parse(container, element);
            element.trackingNumber = TrackingNumber.parse(container, element);
            element.timestamp = Timestamp.parse(container, element);
            element.stock = Stock.parse(container, element);
            element.tradingState = TradingState.parse(container, element);
            element.reserved = Reserved.parse(container, element);
            element.reason = Reason.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.stockLocate).append("\n");
            sb.append(this.trackingNumber).append("\n");
            sb.append(this.timestamp).append("\n");
            sb.append(this.stock).append("\n");
            sb.append(this.tradingState).append("\n");
            sb.append(this.reserved).append("\n");
            sb.append(this.reason).append("\n");
            return sb.toString();
        }
    }

    // Inverse Indicator: 1 Byte Ascii String Enum with 2 values
    public static class InverseIndicator implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_INVERSE_INDICATOR value;

        public InverseIndicator(ENUM_INVERSE_INDICATOR value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static InverseIndicator parse(Container container, IBinaryProtocolElement element) {
            return new InverseIndicator(ENUM_INVERSE_INDICATOR.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "inverseIndicator = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Etp Leverage Factor: 4 Byte Unsigned Fixed Width Integer
    public static class EtpLeverageFactor implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public EtpLeverageFactor(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static EtpLeverageFactor parse(Container container, IBinaryProtocolElement element) {
            return new EtpLeverageFactor(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "etpLeverageFactor = "+ this.value;
        }
    }

    // Etp Flag: 1 Byte Ascii String Enum with 3 values
    public static class EtpFlag implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_ETP_FLAG value;

        public EtpFlag(ENUM_ETP_FLAG value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static EtpFlag parse(Container container, IBinaryProtocolElement element) {
            return new EtpFlag(ENUM_ETP_FLAG.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "etpFlag = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Luld Reference Price Tier: 1 Byte Ascii String Enum with 3 values
    public static class LuldReferencePriceTier implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_LULD_REFERENCE_PRICE_TIER value;

        public LuldReferencePriceTier(ENUM_LULD_REFERENCE_PRICE_TIER value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static LuldReferencePriceTier parse(Container container, IBinaryProtocolElement element) {
            return new LuldReferencePriceTier(ENUM_LULD_REFERENCE_PRICE_TIER.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "luldReferencePriceTier = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Ipo Flag: 1 Byte Ascii String Enum with 3 values
    public static class IpoFlag implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_IPO_FLAG value;

        public IpoFlag(ENUM_IPO_FLAG value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static IpoFlag parse(Container container, IBinaryProtocolElement element) {
            return new IpoFlag(ENUM_IPO_FLAG.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "ipoFlag = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Short Sale Threshold Indicator: 1 Byte Ascii String Enum with 3 values
    public static class ShortSaleThresholdIndicator implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_SHORT_SALE_THRESHOLD_INDICATOR value;

        public ShortSaleThresholdIndicator(ENUM_SHORT_SALE_THRESHOLD_INDICATOR value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static ShortSaleThresholdIndicator parse(Container container, IBinaryProtocolElement element) {
            return new ShortSaleThresholdIndicator(ENUM_SHORT_SALE_THRESHOLD_INDICATOR.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "shortSaleThresholdIndicator = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Authenticity: 1 Byte Ascii String Enum with 2 values
    public static class Authenticity implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_AUTHENTICITY value;

        public Authenticity(ENUM_AUTHENTICITY value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Authenticity parse(Container container, IBinaryProtocolElement element) {
            return new Authenticity(ENUM_AUTHENTICITY.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "authenticity = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Issue Sub Type: 2 Byte Ascii String Enum with 59 values
    public static class IssueSubType implements IBinaryProtocolElement {
        public static final int LENGTH = 2;
        public final IBinaryProtocolElement parent;
        public final String value;

        public IssueSubType(String value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static IssueSubType parse(Container container, IBinaryProtocolElement element) {
            return new IssueSubType(BigEndianUtils.toString(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "issueSubType = "+ this.value;
        }
    }

    // Issue Classification: 1 Byte Ascii String Enum with 16 values
    public static class IssueClassification implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_ISSUE_CLASSIFICATION value;

        public IssueClassification(ENUM_ISSUE_CLASSIFICATION value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static IssueClassification parse(Container container, IBinaryProtocolElement element) {
            return new IssueClassification(ENUM_ISSUE_CLASSIFICATION.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "issueClassification = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Round Lots Only: 1 Byte Ascii String Enum with 2 values
    public static class RoundLotsOnly implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_ROUND_LOTS_ONLY value;

        public RoundLotsOnly(ENUM_ROUND_LOTS_ONLY value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static RoundLotsOnly parse(Container container, IBinaryProtocolElement element) {
            return new RoundLotsOnly(ENUM_ROUND_LOTS_ONLY.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "roundLotsOnly = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Round Lot Size: 4 Byte Unsigned Fixed Width Integer
    public static class RoundLotSize implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public RoundLotSize(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static RoundLotSize parse(Container container, IBinaryProtocolElement element) {
            return new RoundLotSize(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "roundLotSize = "+ this.value;
        }
    }

    // Financial Status Indicator: 1 Byte Ascii String Enum with 11 values
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

    // Market Category: 1 Byte Ascii String Enum with 9 values
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
        public StockLocate stockLocate;
        public TrackingNumber trackingNumber;
        public Timestamp timestamp;
        public Stock stock;
        public MarketCategory marketCategory;
        public FinancialStatusIndicator financialStatusIndicator;
        public RoundLotSize roundLotSize;
        public RoundLotsOnly roundLotsOnly;
        public IssueClassification issueClassification;
        public IssueSubType issueSubType;
        public Authenticity authenticity;
        public ShortSaleThresholdIndicator shortSaleThresholdIndicator;
        public IpoFlag ipoFlag;
        public LuldReferencePriceTier luldReferencePriceTier;
        public EtpFlag etpFlag;
        public EtpLeverageFactor etpLeverageFactor;
        public InverseIndicator inverseIndicator;

        // constructor for Stock Directory Message
        private StockDirectoryMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Stock Directory Message
        public static StockDirectoryMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new StockDirectoryMessage(parentElement);

            element.stockLocate = StockLocate.parse(container, element);
            element.trackingNumber = TrackingNumber.parse(container, element);
            element.timestamp = Timestamp.parse(container, element);
            element.stock = Stock.parse(container, element);
            element.marketCategory = MarketCategory.parse(container, element);
            element.financialStatusIndicator = FinancialStatusIndicator.parse(container, element);
            element.roundLotSize = RoundLotSize.parse(container, element);
            element.roundLotsOnly = RoundLotsOnly.parse(container, element);
            element.issueClassification = IssueClassification.parse(container, element);
            element.issueSubType = IssueSubType.parse(container, element);
            element.authenticity = Authenticity.parse(container, element);
            element.shortSaleThresholdIndicator = ShortSaleThresholdIndicator.parse(container, element);
            element.ipoFlag = IpoFlag.parse(container, element);
            element.luldReferencePriceTier = LuldReferencePriceTier.parse(container, element);
            element.etpFlag = EtpFlag.parse(container, element);
            element.etpLeverageFactor = EtpLeverageFactor.parse(container, element);
            element.inverseIndicator = InverseIndicator.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.stockLocate).append("\n");
            sb.append(this.trackingNumber).append("\n");
            sb.append(this.timestamp).append("\n");
            sb.append(this.stock).append("\n");
            sb.append(this.marketCategory).append("\n");
            sb.append(this.financialStatusIndicator).append("\n");
            sb.append(this.roundLotSize).append("\n");
            sb.append(this.roundLotsOnly).append("\n");
            sb.append(this.issueClassification).append("\n");
            sb.append(this.issueSubType).append("\n");
            sb.append(this.authenticity).append("\n");
            sb.append(this.shortSaleThresholdIndicator).append("\n");
            sb.append(this.ipoFlag).append("\n");
            sb.append(this.luldReferencePriceTier).append("\n");
            sb.append(this.etpFlag).append("\n");
            sb.append(this.etpLeverageFactor).append("\n");
            sb.append(this.inverseIndicator).append("\n");
            return sb.toString();
        }
    }

    // Event Code: 1 Byte Ascii String Enum with 6 values
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
        public StockLocate stockLocate;
        public TrackingNumber trackingNumber;
        public Timestamp timestamp;
        public EventCode eventCode;

        // constructor for System Event Message
        private SystemEventMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for System Event Message
        public static SystemEventMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new SystemEventMessage(parentElement);

            element.stockLocate = StockLocate.parse(container, element);
            element.trackingNumber = TrackingNumber.parse(container, element);
            element.timestamp = Timestamp.parse(container, element);
            element.eventCode = EventCode.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.stockLocate).append("\n");
            sb.append(this.trackingNumber).append("\n");
            sb.append(this.timestamp).append("\n");
            sb.append(this.eventCode).append("\n");
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
        // -- parsing Stock Directory Message
        if (messageType.enumType == 'R') {
            return StockDirectoryMessage.parse(container, null);
        }
        // -- parsing Stock Trading Action Message
        if (messageType.enumType == 'H') {
            return StockTradingActionMessage.parse(container, null);
        }
        // -- parsing Reg Sho Short Sale Price Test Restricted Indicator Message
        if (messageType.enumType == 'Y') {
            return RegShoShortSalePriceTestRestrictedIndicatorMessage.parse(container, null);
        }
        // -- parsing Market Participant Position Message
        if (messageType.enumType == 'L') {
            return MarketParticipantPositionMessage.parse(container, null);
        }
        // -- parsing Mwcb Decline Level Message
        if (messageType.enumType == 'V') {
            return MwcbDeclineLevelMessage.parse(container, null);
        }
        // -- parsing Mwcb Status Level Message
        if (messageType.enumType == 'W') {
            return MwcbStatusLevelMessage.parse(container, null);
        }
        // -- parsing Ipo Quoting Period Update
        if (messageType.enumType == 'K') {
            return IpoQuotingPeriodUpdate.parse(container, null);
        }
        // -- parsing Add Order No Mpid Attribution Message
        if (messageType.enumType == 'A') {
            return AddOrderNoMpidAttributionMessage.parse(container, null);
        }
        // -- parsing Luld Auction Collar Message
        if (messageType.enumType == 'J') {
            return LuldAuctionCollarMessage.parse(container, null);
        }
        // -- parsing Add Order With Mpid Attribution Message
        if (messageType.enumType == 'F') {
            return AddOrderWithMpidAttributionMessage.parse(container, null);
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
        // -- parsing Non Cross Trade Message
        if (messageType.enumType == 'P') {
            return NonCrossTradeMessage.parse(container, null);
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
        // -- parsing Retail Interest Message
        if (messageType.enumType == 'N') {
            return RetailInterestMessage.parse(container, null);
        }

        return null;
    }

    // Message Type: 1 Byte Ascii String Enum with 21 values
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
