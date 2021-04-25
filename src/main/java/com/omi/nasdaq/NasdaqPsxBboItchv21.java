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
* Java parser for Nasdaq Psx Itch Bbo 2.1 protocol
* 
* @version 1.0
* @since 04/25/2021 15:26:15
*/

public class NasdaqPsxBboItchv21 {

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
        E_SPACE(' ', "Na");

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
        E_O('O', "Start Of Transmissions"),
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
        E_Y('Y', "Nasdaq Listed Instrument"),
        E_N('N', "Nasdaq Listed Instrument"),
        E_SPACE(' ', "Na");

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
    * Luld Reference Price Tier Values
    */
    public enum ENUM_LULD_REFERENCE_PRICE_TIER {
        E_1('1', "Tier 1"),
        E_2('2', "Tier 2"),
        E_SPACE(' ', "Na");

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
        E_A('A', "Nyse Amex"),
        E_P('P', "Nyse Arca"),
        E_Z('Z', "Bats Bzx"),
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
    * Message Type Values
    */
    public enum ENUM_MESSAGE_TYPE {
        E_S('S', "System Event Message"),
        E_R('R', "Stock Directory Message"),
        E_H('H', "Stock Trading Action Message"),
        E_Y('Y', "Reg Sho Short Sale Price Test Restricted Indicator Message"),
        E_V('V', "Mwcb Decline Level Message"),
        E_W('W', "Mwcb Status Message"),
        E_h('h', "Operational Halt Message"),
        E_Q('Q', "Quotation Message"),
        E_A('A', "Next Shares Quotation Message");

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
        E_H('H', "Halted"),
        E_T('T', "Resumed");

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
        E_N('N', "Odd Mixed Allowed");

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
    * Security Class Values
    */
    public enum ENUM_SECURITY_CLASS {
        E_Q('Q', "Nasdaq Listed Issue"),
        E_N('N', "Nyse"),
        E_A('A', "Nyse Mkt"),
        E_P('P', "Nyse Arca"),
        E_Z('Z', "Bats"),
        E_V('V', "Iexg"),
        E_SPACE(' ', "Na");

        public final Character enumType;
        public final String enumValue;

        ENUM_SECURITY_CLASS(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_SECURITY_CLASS> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_SECURITY_CLASS s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_SECURITY_CLASS valueOf(Character s)  {
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


    // Nasdaq Best Bid: 4 Byte Signed Fixed Width Integer
    public static class NasdaqBestBid implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public NasdaqBestBid(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static NasdaqBestBid parse(Container container, IBinaryProtocolElement element) {
            return new NasdaqBestBid(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "nasdaqBestBid = "+ this.value;
        }
    }

    // Security Class: 1 Byte Ascii String Enum with 7 values
    public static class SecurityClass implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_SECURITY_CLASS value;

        public SecurityClass(ENUM_SECURITY_CLASS value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static SecurityClass parse(Container container, IBinaryProtocolElement element) {
            return new SecurityClass(ENUM_SECURITY_CLASS.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "securityClass = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Next Shares Symbol: 8 Byte Ascii String
    public static class NextSharesSymbol implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final String value;

        public NextSharesSymbol(String value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static NextSharesSymbol parse(Container container, IBinaryProtocolElement element) {
            return new NextSharesSymbol(BigEndianUtils.toString(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "nextSharesSymbol = "+ this.value;
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
    * Next Shares Quotation Message
    */
    public static class NextSharesQuotationMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public TrackingNumber trackingNumber;
        public Timestamp timestamp;
        public NextSharesSymbol nextSharesSymbol;
        public SecurityClass securityClass;
        public NasdaqBestBid nasdaqBestBid;

        // constructor for Next Shares Quotation Message
        private NextSharesQuotationMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Next Shares Quotation Message
        public static NextSharesQuotationMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new NextSharesQuotationMessage(parentElement);

            element.trackingNumber = TrackingNumber.parse(container, element);
            element.timestamp = Timestamp.parse(container, element);
            element.nextSharesSymbol = NextSharesSymbol.parse(container, element);
            element.securityClass = SecurityClass.parse(container, element);
            element.nasdaqBestBid = NasdaqBestBid.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.trackingNumber).append("\n");
            sb.append(this.timestamp).append("\n");
            sb.append(this.nextSharesSymbol).append("\n");
            sb.append(this.securityClass).append("\n");
            sb.append(this.nasdaqBestBid).append("\n");
            return sb.toString();
        }
    }

    // Psx Best Offer Size: 4 Byte Unsigned Fixed Width Integer
    public static class PsxBestOfferSize implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public PsxBestOfferSize(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static PsxBestOfferSize parse(Container container, IBinaryProtocolElement element) {
            return new PsxBestOfferSize(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "psxBestOfferSize = "+ this.value;
        }
    }

    // Psx Best Offer Price: 4 Byte Signed Fixed Width Integer
    public static class PsxBestOfferPrice implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public PsxBestOfferPrice(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static PsxBestOfferPrice parse(Container container, IBinaryProtocolElement element) {
            return new PsxBestOfferPrice(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "psxBestOfferPrice = "+ this.value;
        }
    }

    // Psx Best Bid Size: 4 Byte Unsigned Fixed Width Integer
    public static class PsxBestBidSize implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public PsxBestBidSize(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static PsxBestBidSize parse(Container container, IBinaryProtocolElement element) {
            return new PsxBestBidSize(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "psxBestBidSize = "+ this.value;
        }
    }

    // Psx Best Bid Price: 4 Byte Signed Fixed Width Integer
    public static class PsxBestBidPrice implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public PsxBestBidPrice(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static PsxBestBidPrice parse(Container container, IBinaryProtocolElement element) {
            return new PsxBestBidPrice(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "psxBestBidPrice = "+ this.value;
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

    /**
    * Quotation Message
    */
    public static class QuotationMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public TrackingNumber trackingNumber;
        public Timestamp timestamp;
        public Stock stock;
        public SecurityClass securityClass;
        public PsxBestBidPrice psxBestBidPrice;
        public PsxBestBidSize psxBestBidSize;
        public PsxBestOfferPrice psxBestOfferPrice;
        public PsxBestOfferSize psxBestOfferSize;

        // constructor for Quotation Message
        private QuotationMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Quotation Message
        public static QuotationMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new QuotationMessage(parentElement);

            element.trackingNumber = TrackingNumber.parse(container, element);
            element.timestamp = Timestamp.parse(container, element);
            element.stock = Stock.parse(container, element);
            element.securityClass = SecurityClass.parse(container, element);
            element.psxBestBidPrice = PsxBestBidPrice.parse(container, element);
            element.psxBestBidSize = PsxBestBidSize.parse(container, element);
            element.psxBestOfferPrice = PsxBestOfferPrice.parse(container, element);
            element.psxBestOfferSize = PsxBestOfferSize.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.trackingNumber).append("\n");
            sb.append(this.timestamp).append("\n");
            sb.append(this.stock).append("\n");
            sb.append(this.securityClass).append("\n");
            sb.append(this.psxBestBidPrice).append("\n");
            sb.append(this.psxBestBidSize).append("\n");
            sb.append(this.psxBestOfferPrice).append("\n");
            sb.append(this.psxBestOfferSize).append("\n");
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
        public SecurityClass securityClass;
        public CurrentTradingState currentTradingState;
        public Reason reason;

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
            element.securityClass = SecurityClass.parse(container, element);
            element.currentTradingState = CurrentTradingState.parse(container, element);
            element.reason = Reason.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.trackingNumber).append("\n");
            sb.append(this.timestamp).append("\n");
            sb.append(this.stock).append("\n");
            sb.append(this.securityClass).append("\n");
            sb.append(this.currentTradingState).append("\n");
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

    // Issue Sub Type: 2 Byte Ascii String
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

    // Issue Classification: 1 Byte Ascii String
    public static class IssueClassification implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Character value;

        public IssueClassification(Character value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static IssueClassification parse(Container container, IBinaryProtocolElement element) {
            return new IssueClassification(BigEndianUtils.toCharacter(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "issueClassification = "+ this.value;
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
        // -- parsing Reg Sho Short Sale Price Test Restricted Indicator Message
        if (messageType.enumType == 'Y') {
            return RegShoShortSalePriceTestRestrictedIndicatorMessage.parse(container, null);
        }
        // -- parsing Mwcb Decline Level Message
        if (messageType.enumType == 'V') {
            return MwcbDeclineLevelMessage.parse(container, null);
        }
        // -- parsing Mwcb Status Message
        if (messageType.enumType == 'W') {
            return MwcbStatusMessage.parse(container, null);
        }
        // -- parsing Operational Halt Message
        if (messageType.enumType == 'h') {
            return OperationalHaltMessage.parse(container, null);
        }
        // -- parsing Quotation Message
        if (messageType.enumType == 'Q') {
            return QuotationMessage.parse(container, null);
        }
        // -- parsing Next Shares Quotation Message
        if (messageType.enumType == 'A') {
            return NextSharesQuotationMessage.parse(container, null);
        }

        return null;
    }

    // Message Type: 1 Byte Ascii String Enum with 9 values
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
