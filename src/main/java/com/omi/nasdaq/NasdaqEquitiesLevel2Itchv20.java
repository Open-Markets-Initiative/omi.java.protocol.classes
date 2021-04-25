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
* Java parser for Nasdaq Equities Itch Level2 2.0 protocol
* 
* @version 1.0
* @since 04/25/2021 15:26:15
*/

public class NasdaqEquitiesLevel2Itchv20 {

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
    * Current Trading State Values
    */
    public enum ENUM_CURRENT_TRADING_STATE {
        E_H('H', "Halted"),
        E_P('P', "Paused"),
        E_Q('Q', "Quotation Only Period"),
        E_T('T', "Trading");

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
        E_SPACE(' ', "In Compliance");

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
        E_Y('Y', "Set Up For Ipo Release"),
        E_N('N', "Not Set Up For Ipo Release"),
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
    * Market Code Values
    */
    public enum ENUM_MARKET_CODE {
        E_Q('Q', "Nasdaq"),
        E_B('B', "Bx"),
        E_X('X', "Psx");

        public final Character enumType;
        public final String enumValue;

        ENUM_MARKET_CODE(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_MARKET_CODE> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_MARKET_CODE s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_MARKET_CODE valueOf(Character s)  {
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
    * Market Side Values
    */
    public enum ENUM_MARKET_SIDE {
        E_B('B', "Bid Update"),
        E_S('S', "Offer Ask Update");

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
        E_S('S', "System Event Message"),
        E_R('R', "Stock Directory Message"),
        E_H('H', "Stock Trading Action Message"),
        E_Y('Y', "Reg Sho Restriction Message"),
        E_P('P', "Participant Position Summary Message"),
        E_h('h', "Operational Halt Message"),
        E_U('U', "Market Participant Bidask Update Message"),
        E_N('N', "Retail Price Interest Indicator Message"),
        E_V('V', "Mwcb Decline Level Message"),
        E_W('W', "Mwcb Status Message"),
        E_K('K', "Ipo Quoting Period Update Message");

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
    * Operational Halt Action Values
    */
    public enum ENUM_OPERATIONAL_HALT_ACTION {
        E_H('H', "Trading Halted"),
        E_T('T', "Trading Resumed");

        public final Character enumType;
        public final String enumValue;

        ENUM_OPERATIONAL_HALT_ACTION(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_OPERATIONAL_HALT_ACTION> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_OPERATIONAL_HALT_ACTION s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_OPERATIONAL_HALT_ACTION valueOf(Character s)  {
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


    // Ipo Price: 10 Byte Signed Fixed Width Integer
    public static class IpoPrice implements IBinaryProtocolElement {
        public static final int LENGTH = 10;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public IpoPrice(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static IpoPrice parse(Container container, IBinaryProtocolElement element) {
            return new IpoPrice(BigEndianUtils.toByte(container, LENGTH), element);
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

    /**
    * Ipo Quoting Period Update Message
    */
    public static class IpoQuotingPeriodUpdateMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public TrackingNumber trackingNumber;
        public Timestamp timestamp;
        public Stock stock;
        public IpoQuotationReleaseTime ipoQuotationReleaseTime;
        public IpoQuotationReleaseQualifier ipoQuotationReleaseQualifier;
        public IpoPrice ipoPrice;

        // constructor for Ipo Quoting Period Update Message
        private IpoQuotingPeriodUpdateMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Ipo Quoting Period Update Message
        public static IpoQuotingPeriodUpdateMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new IpoQuotingPeriodUpdateMessage(parentElement);

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
    * Mwcb Status Message
    */
    public static class MwcbStatusMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public TrackingNumber trackingNumber;
        public Timestamp timestamp;
        public BreachedLevel breachedLevel;

        // constructor for Mwcb Status Message
        private MwcbStatusMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Mwcb Status Message
        public static MwcbStatusMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new MwcbStatusMessage(parentElement);

            element.trackingNumber = TrackingNumber.parse(container, element);
            element.timestamp = Timestamp.parse(container, element);
            element.breachedLevel = BreachedLevel.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
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
            sb.append(this.trackingNumber).append("\n");
            sb.append(this.timestamp).append("\n");
            sb.append(this.level1).append("\n");
            sb.append(this.level2).append("\n");
            sb.append(this.level3).append("\n");
            return sb.toString();
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

    /**
    * Retail Price Interest Indicator Message
    */
    public static class RetailPriceInterestIndicatorMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public TrackingNumber trackingNumber;
        public Timestamp timestamp;
        public Stock stock;
        public InterestFlag interestFlag;

        // constructor for Retail Price Interest Indicator Message
        private RetailPriceInterestIndicatorMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Retail Price Interest Indicator Message
        public static RetailPriceInterestIndicatorMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new RetailPriceInterestIndicatorMessage(parentElement);

            element.trackingNumber = TrackingNumber.parse(container, element);
            element.timestamp = Timestamp.parse(container, element);
            element.stock = Stock.parse(container, element);
            element.interestFlag = InterestFlag.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.trackingNumber).append("\n");
            sb.append(this.timestamp).append("\n");
            sb.append(this.stock).append("\n");
            sb.append(this.interestFlag).append("\n");
            return sb.toString();
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

    // Participant Shares: 4 Byte Unsigned Fixed Width Integer
    public static class ParticipantShares implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public ParticipantShares(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static ParticipantShares parse(Container container, IBinaryProtocolElement element) {
            return new ParticipantShares(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "participantShares = "+ this.value;
        }
    }

    // Market Side: 1 Byte Ascii String Enum with 2 values
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

    /**
    * Market Participant Bidask Update Message
    */
    public static class MarketParticipantBidaskUpdateMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public TrackingNumber trackingNumber;
        public Timestamp timestamp;
        public MarketSide marketSide;
        public ParticipantShares participantShares;
        public Stock stock;
        public Price price;
        public Mpid mpid;

        // constructor for Market Participant Bidask Update Message
        private MarketParticipantBidaskUpdateMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Market Participant Bidask Update Message
        public static MarketParticipantBidaskUpdateMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new MarketParticipantBidaskUpdateMessage(parentElement);

            element.trackingNumber = TrackingNumber.parse(container, element);
            element.timestamp = Timestamp.parse(container, element);
            element.marketSide = MarketSide.parse(container, element);
            element.participantShares = ParticipantShares.parse(container, element);
            element.stock = Stock.parse(container, element);
            element.price = Price.parse(container, element);
            element.mpid = Mpid.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.trackingNumber).append("\n");
            sb.append(this.timestamp).append("\n");
            sb.append(this.marketSide).append("\n");
            sb.append(this.participantShares).append("\n");
            sb.append(this.stock).append("\n");
            sb.append(this.price).append("\n");
            sb.append(this.mpid).append("\n");
            return sb.toString();
        }
    }

    // Operational Halt Action: 1 Byte Ascii String Enum with 2 values
    public static class OperationalHaltAction implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_OPERATIONAL_HALT_ACTION value;

        public OperationalHaltAction(ENUM_OPERATIONAL_HALT_ACTION value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static OperationalHaltAction parse(Container container, IBinaryProtocolElement element) {
            return new OperationalHaltAction(ENUM_OPERATIONAL_HALT_ACTION.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "operationalHaltAction = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Market Code: 1 Byte Ascii String Enum with 3 values
    public static class MarketCode implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_MARKET_CODE value;

        public MarketCode(ENUM_MARKET_CODE value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static MarketCode parse(Container container, IBinaryProtocolElement element) {
            return new MarketCode(ENUM_MARKET_CODE.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "marketCode = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    /**
    * Operational Halt Message
    */
    public static class OperationalHaltMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public TrackingNumber trackingNumber;
        public Timestamp timestamp;
        public Stock stock;
        public MarketCode marketCode;
        public OperationalHaltAction operationalHaltAction;

        // constructor for Operational Halt Message
        private OperationalHaltMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Operational Halt Message
        public static OperationalHaltMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new OperationalHaltMessage(parentElement);

            element.trackingNumber = TrackingNumber.parse(container, element);
            element.timestamp = Timestamp.parse(container, element);
            element.stock = Stock.parse(container, element);
            element.marketCode = MarketCode.parse(container, element);
            element.operationalHaltAction = OperationalHaltAction.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.trackingNumber).append("\n");
            sb.append(this.timestamp).append("\n");
            sb.append(this.stock).append("\n");
            sb.append(this.marketCode).append("\n");
            sb.append(this.operationalHaltAction).append("\n");
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

    /**
    * Participant Position Summary Message
    */
    public static class ParticipantPositionSummaryMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public TrackingNumber trackingNumber;
        public Timestamp timestamp;
        public Mpid mpid;
        public Stock stock;
        public PrimaryMarketMaker primaryMarketMaker;
        public MarketMakerMode marketMakerMode;
        public MarketParticipantState marketParticipantState;

        // constructor for Participant Position Summary Message
        private ParticipantPositionSummaryMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Participant Position Summary Message
        public static ParticipantPositionSummaryMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new ParticipantPositionSummaryMessage(parentElement);

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

    /**
    * Reg Sho Restriction Message
    */
    public static class RegShoRestrictionMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public TrackingNumber trackingNumber;
        public Timestamp timestamp;
        public Stock stock;
        public RegShoAction regShoAction;

        // constructor for Reg Sho Restriction Message
        private RegShoRestrictionMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Reg Sho Restriction Message
        public static RegShoRestrictionMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new RegShoRestrictionMessage(parentElement);

            element.trackingNumber = TrackingNumber.parse(container, element);
            element.timestamp = Timestamp.parse(container, element);
            element.stock = Stock.parse(container, element);
            element.regShoAction = RegShoAction.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.trackingNumber).append("\n");
            sb.append(this.timestamp).append("\n");
            sb.append(this.stock).append("\n");
            sb.append(this.regShoAction).append("\n");
            return sb.toString();
        }
    }

    // Trading Action Reason: 4 Byte Ascii String Enum with 34 values
    public static class TradingActionReason implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final String value;

        public TradingActionReason(String value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static TradingActionReason parse(Container container, IBinaryProtocolElement element) {
            return new TradingActionReason(BigEndianUtils.toString(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "tradingActionReason = "+ this.value;
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
    * Stock Trading Action Message
    */
    public static class StockTradingActionMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public TrackingNumber trackingNumber;
        public Timestamp timestamp;
        public Stock stock;
        public CurrentTradingState currentTradingState;
        public TradingActionReason tradingActionReason;

        // constructor for Stock Trading Action Message
        private StockTradingActionMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Stock Trading Action Message
        public static StockTradingActionMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new StockTradingActionMessage(parentElement);

            element.trackingNumber = TrackingNumber.parse(container, element);
            element.timestamp = Timestamp.parse(container, element);
            element.stock = Stock.parse(container, element);
            element.currentTradingState = CurrentTradingState.parse(container, element);
            element.tradingActionReason = TradingActionReason.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.trackingNumber).append("\n");
            sb.append(this.timestamp).append("\n");
            sb.append(this.stock).append("\n");
            sb.append(this.currentTradingState).append("\n");
            sb.append(this.tradingActionReason).append("\n");
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

    // Issue Subtype: 2 Byte Ascii String Enum with 58 values
    public static class IssueSubtype implements IBinaryProtocolElement {
        public static final int LENGTH = 2;
        public final IBinaryProtocolElement parent;
        public final String value;

        public IssueSubtype(String value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static IssueSubtype parse(Container container, IBinaryProtocolElement element) {
            return new IssueSubtype(BigEndianUtils.toString(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "issueSubtype = "+ this.value;
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
        public TrackingNumber trackingNumber;
        public Timestamp timestamp;
        public Stock stock;
        public MarketCategory marketCategory;
        public FinancialStatusIndicator financialStatusIndicator;
        public RoundLotSize roundLotSize;
        public RoundLotsOnly roundLotsOnly;
        public IssueClassification issueClassification;
        public IssueSubtype issueSubtype;
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

            element.trackingNumber = TrackingNumber.parse(container, element);
            element.timestamp = Timestamp.parse(container, element);
            element.stock = Stock.parse(container, element);
            element.marketCategory = MarketCategory.parse(container, element);
            element.financialStatusIndicator = FinancialStatusIndicator.parse(container, element);
            element.roundLotSize = RoundLotSize.parse(container, element);
            element.roundLotsOnly = RoundLotsOnly.parse(container, element);
            element.issueClassification = IssueClassification.parse(container, element);
            element.issueSubtype = IssueSubtype.parse(container, element);
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
            sb.append(this.trackingNumber).append("\n");
            sb.append(this.timestamp).append("\n");
            sb.append(this.stock).append("\n");
            sb.append(this.marketCategory).append("\n");
            sb.append(this.financialStatusIndicator).append("\n");
            sb.append(this.roundLotSize).append("\n");
            sb.append(this.roundLotsOnly).append("\n");
            sb.append(this.issueClassification).append("\n");
            sb.append(this.issueSubtype).append("\n");
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

            element.trackingNumber = TrackingNumber.parse(container, element);
            element.timestamp = Timestamp.parse(container, element);
            element.eventCode = EventCode.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
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
        // -- parsing Reg Sho Restriction Message
        if (messageType.enumType == 'Y') {
            return RegShoRestrictionMessage.parse(container, null);
        }
        // -- parsing Participant Position Summary Message
        if (messageType.enumType == 'P') {
            return ParticipantPositionSummaryMessage.parse(container, null);
        }
        // -- parsing Operational Halt Message
        if (messageType.enumType == 'h') {
            return OperationalHaltMessage.parse(container, null);
        }
        // -- parsing Market Participant Bidask Update Message
        if (messageType.enumType == 'U') {
            return MarketParticipantBidaskUpdateMessage.parse(container, null);
        }
        // -- parsing Retail Price Interest Indicator Message
        if (messageType.enumType == 'N') {
            return RetailPriceInterestIndicatorMessage.parse(container, null);
        }
        // -- parsing Mwcb Decline Level Message
        if (messageType.enumType == 'V') {
            return MwcbDeclineLevelMessage.parse(container, null);
        }
        // -- parsing Mwcb Status Message
        if (messageType.enumType == 'W') {
            return MwcbStatusMessage.parse(container, null);
        }
        // -- parsing Ipo Quoting Period Update Message
        if (messageType.enumType == 'K') {
            return IpoQuotingPeriodUpdateMessage.parse(container, null);
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
