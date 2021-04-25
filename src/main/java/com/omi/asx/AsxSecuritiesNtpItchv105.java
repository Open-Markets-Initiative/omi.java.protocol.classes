package com.omi.asx;

import com.omi.parser.util.BigEndianUtils;
import com.omi.parser.util.LittleEndianUtils;
import com.omi.protocol.Container;
import com.omi.protocol.IBinaryProtocolElement;
import com.omi.protocol.Payload;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.ArrayList;

/**
* Java parser for Asx Securities Itch Ntp 1.05 protocol
* 
* @version 1.0
* @since 04/25/2021 16:10:25
*/

public class AsxSecuritiesNtpItchv105 {

    ///////////////////////////////////////////////////////////////////////
    // Enum Values
    ///////////////////////////////////////////////////////////////////////

    /**
    * Buyer Side Values
    */
    public enum ENUM_BUYER_SIDE {
        E_S('S', "Sell"),
        E_B('B', "Buy");

        public final Character enumType;
        public final String enumValue;

        ENUM_BUYER_SIDE(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_BUYER_SIDE> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_BUYER_SIDE s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_BUYER_SIDE valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Event Code Values
    */
    public enum ENUM_EVENT_CODE {
        E_C('C', "Business Trade Date Has Ended");

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
    * Expiry Month Values
    */
    public enum ENUM_EXPIRY_MONTH {
        E_1(1, "Jan"),
        E_2(2, "Feb"),
        E_3(3, "Mar"),
        E_4(4, "Apr"),
        E_5(5, "May"),
        E_6(6, "Jun"),
        E_7(7, "Jul"),
        E_8(8, "Aug"),
        E_9(9, "Sep"),
        E_10(10, "Oct"),
        E_11(11, "Nov"),
        E_12(12, "Dec");

        public final Integer enumType;
        public final String enumValue;

        ENUM_EXPIRY_MONTH(Integer enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Integer, ENUM_EXPIRY_MONTH> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_EXPIRY_MONTH s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_EXPIRY_MONTH valueOf(Integer s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Message Type Values
    */
    public enum ENUM_MESSAGE_TYPE {
        E_T('T', "Seconds Message"),
        E_S('S', "End Of Business Trade Date Message"),
        E_f('f', "Future Symbol Directory Message"),
        E_h('h', "Options Symbol Directory Message"),
        E_M('M', "Combination Symbol Directory Message"),
        E_m('m', "Bundles Symbol Directory"),
        E_O('O', "Order Book State Message"),
        E_A('A', "Add Order Message"),
        E_X('X', "Order Volume Cancelled Message"),
        E_D('D', "Order Deleted Message"),
        E_E('E', "Order Executed Message"),
        E_C('C', "Auction Order Executed Message"),
        E_e('e', "Combination Order Executed Message"),
        E_j('j', "Implied Order Added Message"),
        E_l('l', "Implied Order Replaced Message"),
        E_k('k', "Implied Order Deleted Message"),
        E_P('P', "Trade Executed Message"),
        E_p('p', "Combination Trade Executed Message"),
        E_B('B', "Trade Cancellation Message"),
        E_Z('Z', "Equilibrium Price Message"),
        E_t('t', "Open High Low Last Trade Adjustment Message"),
        E_Y('Y', "Market Settlement Message"),
        E_x('x', "Text Message"),
        E_q('q', "Request For Quote Message"),
        E_W('W', "Anomalous Order Threshold Publish Message"),
        E_V('V', "Volume And Open Interest Message");

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
    * Opposite Side Values
    */
    public enum ENUM_OPPOSITE_SIDE {
        E_S('S', "Sell"),
        E_B('B', "Buy");

        public final Character enumType;
        public final String enumValue;

        ENUM_OPPOSITE_SIDE(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_OPPOSITE_SIDE> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_OPPOSITE_SIDE s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_OPPOSITE_SIDE valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Option Type Values
    */
    public enum ENUM_OPTION_TYPE {
        E_P('P', "Put"),
        E_C('C', "Call");

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
    * Price Method Values
    */
    public enum ENUM_PRICE_METHOD {
        E_0(0, "Net Price"),
        E_2(2, "Yield Difference"),
        E_3(3, "Individual"),
        E_4(4, "Average Price");

        public final Integer enumType;
        public final String enumValue;

        ENUM_PRICE_METHOD(Integer enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Integer, ENUM_PRICE_METHOD> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_PRICE_METHOD s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_PRICE_METHOD valueOf(Integer s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Rfq Side Values
    */
    public enum ENUM_RFQ_SIDE {
        E_T('T', "Two Sided Quote"),
        E_B('B', "Bid Quote"),
        E_S('S', "Ask Quote"),
        E_X('X', "Crossing");

        public final Character enumType;
        public final String enumValue;

        ENUM_RFQ_SIDE(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_RFQ_SIDE> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_RFQ_SIDE s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_RFQ_SIDE valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Seller Side Values
    */
    public enum ENUM_SELLER_SIDE {
        E_S('S', "Sell"),
        E_B('B', "Buy");

        public final Character enumType;
        public final String enumValue;

        ENUM_SELLER_SIDE(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_SELLER_SIDE> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_SELLER_SIDE s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_SELLER_SIDE valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Session State Values
    */
    public enum ENUM_SESSION_STATE {
        E_P('P', "Pre Open"),
        E_O('O', "Opened"),
        E_R('R', "Regulatory Halt"),
        E_H('H', "Halted"),
        E_C('C', "Closed"),
        E_M('M', "Maintenance");

        public final Character enumType;
        public final String enumValue;

        ENUM_SESSION_STATE(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_SESSION_STATE> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_SESSION_STATE s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_SESSION_STATE valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Side Values
    */
    public enum ENUM_SIDE {
        E_S('S', "Sell"),
        E_B('B', "Buy");

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
    * Side Leg Values
    */
    public enum ENUM_SIDE_LEG {
        E_SPACE(' ', "Not Defined"),
        E_B('B', "Buy"),
        E_S('S', "Sell");

        public final Character enumType;
        public final String enumValue;

        ENUM_SIDE_LEG(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_SIDE_LEG> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_SIDE_LEG s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_SIDE_LEG valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Trade Type Values
    */
    public enum ENUM_TRADE_TYPE {
        E_T('T', "Normal Trade"),
        E_t('t', "Normal Cross"),
        E_L('L', "Auction Trade"),
        E_l('l', "Auction Cross"),
        E_S('S', "Combination To Underlying Trade"),
        E_s('s', "Combination To Underlying Cross"),
        E_R('R', "Combination To Combination Trade"),
        E_r('r', "Combination To Combination Cross"),
        E_A('A', "Strip To Strip Trade"),
        E_a('a', "Strip To Strip Cross"),
        E_B('B', "Strip To Outright Trade"),
        E_b('b', "Strip To Outright Cross");

        public final Character enumType;
        public final String enumValue;

        ENUM_TRADE_TYPE(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_TRADE_TYPE> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_TRADE_TYPE s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_TRADE_TYPE valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }


    // Voi Trade Date: 2 Byte Unsigned Fixed Width Integer
    public static class VoiTradeDate implements IBinaryProtocolElement {
        public static final int LENGTH = 2;
        public final IBinaryProtocolElement parent;
        public final Short value;

        public VoiTradeDate(Short value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static VoiTradeDate parse(Container container, IBinaryProtocolElement element) {
            return new VoiTradeDate(BigEndianUtils.toShort(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "voiTradeDate = "+ this.value;
        }
    }

    // Open Interest: 8 Byte Unsigned Fixed Width Integer
    public static class OpenInterest implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public OpenInterest(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static OpenInterest parse(Container container, IBinaryProtocolElement element) {
            return new OpenInterest(BigEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "openInterest = "+ this.value;
        }
    }

    // Cumulative Volume: 8 Byte Unsigned Fixed Width Integer
    public static class CumulativeVolume implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public CumulativeVolume(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static CumulativeVolume parse(Container container, IBinaryProtocolElement element) {
            return new CumulativeVolume(BigEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "cumulativeVolume = "+ this.value;
        }
    }

    // Tradeable Instrument Id: 4 Byte Unsigned Fixed Width Integer
    public static class TradeableInstrumentId implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public TradeableInstrumentId(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static TradeableInstrumentId parse(Container container, IBinaryProtocolElement element) {
            return new TradeableInstrumentId(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "tradeableInstrumentId = "+ this.value;
        }
    }

    // Trade Date: 2 Byte Unsigned Fixed Width Integer
    public static class TradeDate implements IBinaryProtocolElement {
        public static final int LENGTH = 2;
        public final IBinaryProtocolElement parent;
        public final Short value;

        public TradeDate(Short value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static TradeDate parse(Container container, IBinaryProtocolElement element) {
            return new TradeDate(BigEndianUtils.toShort(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "tradeDate = "+ this.value;
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
    * Volume And Open Interest Message
    */
    public static class VolumeAndOpenInterestMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public TradeDate tradeDate;
        public TradeableInstrumentId tradeableInstrumentId;
        public CumulativeVolume cumulativeVolume;
        public OpenInterest openInterest;
        public VoiTradeDate voiTradeDate;

        // constructor for Volume And Open Interest Message
        private VolumeAndOpenInterestMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Volume And Open Interest Message
        public static VolumeAndOpenInterestMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new VolumeAndOpenInterestMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.tradeDate = TradeDate.parse(container, element);
            element.tradeableInstrumentId = TradeableInstrumentId.parse(container, element);
            element.cumulativeVolume = CumulativeVolume.parse(container, element);
            element.openInterest = OpenInterest.parse(container, element);
            element.voiTradeDate = VoiTradeDate.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.tradeDate).append("\n");
            sb.append(this.tradeableInstrumentId).append("\n");
            sb.append(this.cumulativeVolume).append("\n");
            sb.append(this.openInterest).append("\n");
            sb.append(this.voiTradeDate).append("\n");
            return sb.toString();
        }
    }

    // Etr Lower Price: 8 Byte Signed Fixed Width Integer
    public static class EtrLowerPrice implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public EtrLowerPrice(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static EtrLowerPrice parse(Container container, IBinaryProtocolElement element) {
            return new EtrLowerPrice(BigEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "etrLowerPrice = "+ this.value;
        }
    }

    // Etr Upper Price: 8 Byte Signed Fixed Width Integer
    public static class EtrUpperPrice implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public EtrUpperPrice(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static EtrUpperPrice parse(Container container, IBinaryProtocolElement element) {
            return new EtrUpperPrice(BigEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "etrUpperPrice = "+ this.value;
        }
    }

    // Etr Price: 8 Byte Signed Fixed Width Integer
    public static class EtrPrice implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public EtrPrice(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static EtrPrice parse(Container container, IBinaryProtocolElement element) {
            return new EtrPrice(BigEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "etrPrice = "+ this.value;
        }
    }

    // Aot Lower Price: 8 Byte Signed Fixed Width Integer
    public static class AotLowerPrice implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public AotLowerPrice(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static AotLowerPrice parse(Container container, IBinaryProtocolElement element) {
            return new AotLowerPrice(BigEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "aotLowerPrice = "+ this.value;
        }
    }

    // Aot Upper Price: 8 Byte Signed Fixed Width Integer
    public static class AotUpperPrice implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public AotUpperPrice(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static AotUpperPrice parse(Container container, IBinaryProtocolElement element) {
            return new AotUpperPrice(BigEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "aotUpperPrice = "+ this.value;
        }
    }

    // Aot Price: 8 Byte Signed Fixed Width Integer
    public static class AotPrice implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public AotPrice(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static AotPrice parse(Container container, IBinaryProtocolElement element) {
            return new AotPrice(BigEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "aotPrice = "+ this.value;
        }
    }

    /**
    * Anomalous Order Threshold Publish Message
    */
    public static class AnomalousOrderThresholdPublishMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public TradeDate tradeDate;
        public TradeableInstrumentId tradeableInstrumentId;
        public AotPrice aotPrice;
        public AotUpperPrice aotUpperPrice;
        public AotLowerPrice aotLowerPrice;
        public EtrPrice etrPrice;
        public EtrUpperPrice etrUpperPrice;
        public EtrLowerPrice etrLowerPrice;

        // constructor for Anomalous Order Threshold Publish Message
        private AnomalousOrderThresholdPublishMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Anomalous Order Threshold Publish Message
        public static AnomalousOrderThresholdPublishMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new AnomalousOrderThresholdPublishMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.tradeDate = TradeDate.parse(container, element);
            element.tradeableInstrumentId = TradeableInstrumentId.parse(container, element);
            element.aotPrice = AotPrice.parse(container, element);
            element.aotUpperPrice = AotUpperPrice.parse(container, element);
            element.aotLowerPrice = AotLowerPrice.parse(container, element);
            element.etrPrice = EtrPrice.parse(container, element);
            element.etrUpperPrice = EtrUpperPrice.parse(container, element);
            element.etrLowerPrice = EtrLowerPrice.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.tradeDate).append("\n");
            sb.append(this.tradeableInstrumentId).append("\n");
            sb.append(this.aotPrice).append("\n");
            sb.append(this.aotUpperPrice).append("\n");
            sb.append(this.aotLowerPrice).append("\n");
            sb.append(this.etrPrice).append("\n");
            sb.append(this.etrUpperPrice).append("\n");
            sb.append(this.etrLowerPrice).append("\n");
            return sb.toString();
        }
    }

    // Quantity: 4 Byte Unsigned Fixed Width Integer
    public static class Quantity implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public Quantity(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Quantity parse(Container container, IBinaryProtocolElement element) {
            return new Quantity(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "quantity = "+ this.value;
        }
    }

    // Rfq Side: 1 Byte Ascii String Enum with 4 values
    public static class RfqSide implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_RFQ_SIDE value;

        public RfqSide(ENUM_RFQ_SIDE value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static RfqSide parse(Container container, IBinaryProtocolElement element) {
            return new RfqSide(ENUM_RFQ_SIDE.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "rfqSide = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    /**
    * Request For Quote Message
    */
    public static class RequestForQuoteMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public TradeDate tradeDate;
        public TradeableInstrumentId tradeableInstrumentId;
        public RfqSide rfqSide;
        public Quantity quantity;

        // constructor for Request For Quote Message
        private RequestForQuoteMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Request For Quote Message
        public static RequestForQuoteMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new RequestForQuoteMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.tradeDate = TradeDate.parse(container, element);
            element.tradeableInstrumentId = TradeableInstrumentId.parse(container, element);
            element.rfqSide = RfqSide.parse(container, element);
            element.quantity = Quantity.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.tradeDate).append("\n");
            sb.append(this.tradeableInstrumentId).append("\n");
            sb.append(this.rfqSide).append("\n");
            sb.append(this.quantity).append("\n");
            return sb.toString();
        }
    }

    // Text: 100 Byte Ascii String
    public static class Text implements IBinaryProtocolElement {
        public static final int LENGTH = 100;
        public final IBinaryProtocolElement parent;
        public final String value;

        public Text(String value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Text parse(Container container, IBinaryProtocolElement element) {
            return new Text(BigEndianUtils.toString(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "text = "+ this.value;
        }
    }

    // Source Id: 6 Byte Ascii String
    public static class SourceId implements IBinaryProtocolElement {
        public static final int LENGTH = 6;
        public final IBinaryProtocolElement parent;
        public final String value;

        public SourceId(String value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static SourceId parse(Container container, IBinaryProtocolElement element) {
            return new SourceId(BigEndianUtils.toString(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "sourceId = "+ this.value;
        }
    }

    /**
    * Text Message
    */
    public static class TextMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public TradeDate tradeDate;
        public SourceId sourceId;
        public Text text;

        // constructor for Text Message
        private TextMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Text Message
        public static TextMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new TextMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.tradeDate = TradeDate.parse(container, element);
            element.sourceId = SourceId.parse(container, element);
            element.text = Text.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.tradeDate).append("\n");
            sb.append(this.sourceId).append("\n");
            sb.append(this.text).append("\n");
            return sb.toString();
        }
    }

    // Total Traded Volume: 8 Byte Unsigned Fixed Width Integer
    public static class TotalTradedVolume implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public TotalTradedVolume(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static TotalTradedVolume parse(Container container, IBinaryProtocolElement element) {
            return new TotalTradedVolume(BigEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "totalTradedVolume = "+ this.value;
        }
    }

    // Last Volume: 4 Byte Unsigned Fixed Width Integer
    public static class LastVolume implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public LastVolume(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static LastVolume parse(Container container, IBinaryProtocolElement element) {
            return new LastVolume(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "lastVolume = "+ this.value;
        }
    }

    // Last Trade: 8 Byte Signed Fixed Width Integer
    public static class LastTrade implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public LastTrade(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static LastTrade parse(Container container, IBinaryProtocolElement element) {
            return new LastTrade(BigEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "lastTrade = "+ this.value;
        }
    }

    // Lowest Trade: 8 Byte Signed Fixed Width Integer
    public static class LowestTrade implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public LowestTrade(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static LowestTrade parse(Container container, IBinaryProtocolElement element) {
            return new LowestTrade(BigEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "lowestTrade = "+ this.value;
        }
    }

    // Highest Trade: 8 Byte Signed Fixed Width Integer
    public static class HighestTrade implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public HighestTrade(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static HighestTrade parse(Container container, IBinaryProtocolElement element) {
            return new HighestTrade(BigEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "highestTrade = "+ this.value;
        }
    }

    // Opening Trade: 8 Byte Signed Fixed Width Integer
    public static class OpeningTrade implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public OpeningTrade(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static OpeningTrade parse(Container container, IBinaryProtocolElement element) {
            return new OpeningTrade(BigEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "openingTrade = "+ this.value;
        }
    }

    /**
    * Market Settlement Message
    */
    public static class MarketSettlementMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public TradeDate tradeDate;
        public TradeableInstrumentId tradeableInstrumentId;
        public OpeningTrade openingTrade;
        public HighestTrade highestTrade;
        public LowestTrade lowestTrade;
        public LastTrade lastTrade;
        public LastVolume lastVolume;
        public TotalTradedVolume totalTradedVolume;

        // constructor for Market Settlement Message
        private MarketSettlementMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Market Settlement Message
        public static MarketSettlementMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new MarketSettlementMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.tradeDate = TradeDate.parse(container, element);
            element.tradeableInstrumentId = TradeableInstrumentId.parse(container, element);
            element.openingTrade = OpeningTrade.parse(container, element);
            element.highestTrade = HighestTrade.parse(container, element);
            element.lowestTrade = LowestTrade.parse(container, element);
            element.lastTrade = LastTrade.parse(container, element);
            element.lastVolume = LastVolume.parse(container, element);
            element.totalTradedVolume = TotalTradedVolume.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.tradeDate).append("\n");
            sb.append(this.tradeableInstrumentId).append("\n");
            sb.append(this.openingTrade).append("\n");
            sb.append(this.highestTrade).append("\n");
            sb.append(this.lowestTrade).append("\n");
            sb.append(this.lastTrade).append("\n");
            sb.append(this.lastVolume).append("\n");
            sb.append(this.totalTradedVolume).append("\n");
            return sb.toString();
        }
    }

    /**
    * Open High Low Last Trade Adjustment Message
    */
    public static class OpenHighLowLastTradeAdjustmentMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public TradeDate tradeDate;
        public TradeableInstrumentId tradeableInstrumentId;
        public OpeningTrade openingTrade;
        public HighestTrade highestTrade;
        public LowestTrade lowestTrade;
        public LastTrade lastTrade;
        public LastVolume lastVolume;
        public TotalTradedVolume totalTradedVolume;

        // constructor for Open High Low Last Trade Adjustment Message
        private OpenHighLowLastTradeAdjustmentMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Open High Low Last Trade Adjustment Message
        public static OpenHighLowLastTradeAdjustmentMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new OpenHighLowLastTradeAdjustmentMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.tradeDate = TradeDate.parse(container, element);
            element.tradeableInstrumentId = TradeableInstrumentId.parse(container, element);
            element.openingTrade = OpeningTrade.parse(container, element);
            element.highestTrade = HighestTrade.parse(container, element);
            element.lowestTrade = LowestTrade.parse(container, element);
            element.lastTrade = LastTrade.parse(container, element);
            element.lastVolume = LastVolume.parse(container, element);
            element.totalTradedVolume = TotalTradedVolume.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.tradeDate).append("\n");
            sb.append(this.tradeableInstrumentId).append("\n");
            sb.append(this.openingTrade).append("\n");
            sb.append(this.highestTrade).append("\n");
            sb.append(this.lowestTrade).append("\n");
            sb.append(this.lastTrade).append("\n");
            sb.append(this.lastVolume).append("\n");
            sb.append(this.totalTradedVolume).append("\n");
            return sb.toString();
        }
    }

    // Ask Quantity: 8 Byte Unsigned Fixed Width Integer
    public static class AskQuantity implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public AskQuantity(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static AskQuantity parse(Container container, IBinaryProtocolElement element) {
            return new AskQuantity(BigEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "askQuantity = "+ this.value;
        }
    }

    // Bid Quantity: 8 Byte Unsigned Fixed Width Integer
    public static class BidQuantity implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public BidQuantity(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static BidQuantity parse(Container container, IBinaryProtocolElement element) {
            return new BidQuantity(BigEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "bidQuantity = "+ this.value;
        }
    }

    // Matched Quantity: 8 Byte Unsigned Fixed Width Integer
    public static class MatchedQuantity implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public MatchedQuantity(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static MatchedQuantity parse(Container container, IBinaryProtocolElement element) {
            return new MatchedQuantity(BigEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "matchedQuantity = "+ this.value;
        }
    }

    // Equilibrium Price: 8 Byte Signed Fixed Width Integer
    public static class EquilibriumPrice implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public EquilibriumPrice(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static EquilibriumPrice parse(Container container, IBinaryProtocolElement element) {
            return new EquilibriumPrice(BigEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "equilibriumPrice = "+ this.value;
        }
    }

    /**
    * Equilibrium Price Message
    */
    public static class EquilibriumPriceMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public TradeDate tradeDate;
        public TradeableInstrumentId tradeableInstrumentId;
        public EquilibriumPrice equilibriumPrice;
        public MatchedQuantity matchedQuantity;
        public BidQuantity bidQuantity;
        public AskQuantity askQuantity;

        // constructor for Equilibrium Price Message
        private EquilibriumPriceMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Equilibrium Price Message
        public static EquilibriumPriceMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new EquilibriumPriceMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.tradeDate = TradeDate.parse(container, element);
            element.tradeableInstrumentId = TradeableInstrumentId.parse(container, element);
            element.equilibriumPrice = EquilibriumPrice.parse(container, element);
            element.matchedQuantity = MatchedQuantity.parse(container, element);
            element.bidQuantity = BidQuantity.parse(container, element);
            element.askQuantity = AskQuantity.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.tradeDate).append("\n");
            sb.append(this.tradeableInstrumentId).append("\n");
            sb.append(this.equilibriumPrice).append("\n");
            sb.append(this.matchedQuantity).append("\n");
            sb.append(this.bidQuantity).append("\n");
            sb.append(this.askQuantity).append("\n");
            return sb.toString();
        }
    }

    // Trade Id: 8 Byte Unsigned Fixed Width Integer
    public static class TradeId implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public TradeId(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static TradeId parse(Container container, IBinaryProtocolElement element) {
            return new TradeId(BigEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "tradeId = "+ this.value;
        }
    }

    /**
    * Trade Cancellation Message
    */
    public static class TradeCancellationMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public TradeDate tradeDate;
        public TradeableInstrumentId tradeableInstrumentId;
        public TradeId tradeId;

        // constructor for Trade Cancellation Message
        private TradeCancellationMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Trade Cancellation Message
        public static TradeCancellationMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new TradeCancellationMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.tradeDate = TradeDate.parse(container, element);
            element.tradeableInstrumentId = TradeableInstrumentId.parse(container, element);
            element.tradeId = TradeId.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.tradeDate).append("\n");
            sb.append(this.tradeableInstrumentId).append("\n");
            sb.append(this.tradeId).append("\n");
            return sb.toString();
        }
    }

    // Seller Participant Id: 3 Byte Ascii String
    public static class SellerParticipantId implements IBinaryProtocolElement {
        public static final int LENGTH = 3;
        public final IBinaryProtocolElement parent;
        public final String value;

        public SellerParticipantId(String value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static SellerParticipantId parse(Container container, IBinaryProtocolElement element) {
            return new SellerParticipantId(BigEndianUtils.toString(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "sellerParticipantId = "+ this.value;
        }
    }

    // Seller Combination Trade Id: 8 Byte Unsigned Fixed Width Integer
    public static class SellerCombinationTradeId implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public SellerCombinationTradeId(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static SellerCombinationTradeId parse(Container container, IBinaryProtocolElement element) {
            return new SellerCombinationTradeId(BigEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "sellerCombinationTradeId = "+ this.value;
        }
    }

    // Seller Order Id: 8 Byte Unsigned Fixed Width Integer
    public static class SellerOrderId implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public SellerOrderId(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static SellerOrderId parse(Container container, IBinaryProtocolElement element) {
            return new SellerOrderId(BigEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "sellerOrderId = "+ this.value;
        }
    }

    // Seller Side: 1 Byte Ascii String Enum with 2 values
    public static class SellerSide implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_SELLER_SIDE value;

        public SellerSide(ENUM_SELLER_SIDE value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static SellerSide parse(Container container, IBinaryProtocolElement element) {
            return new SellerSide(ENUM_SELLER_SIDE.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "sellerSide = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Seller Tradeable Instrument Id: 4 Byte Unsigned Fixed Width Integer
    public static class SellerTradeableInstrumentId implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public SellerTradeableInstrumentId(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static SellerTradeableInstrumentId parse(Container container, IBinaryProtocolElement element) {
            return new SellerTradeableInstrumentId(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "sellerTradeableInstrumentId = "+ this.value;
        }
    }

    // Buyer Participant Id: 3 Byte Ascii String
    public static class BuyerParticipantId implements IBinaryProtocolElement {
        public static final int LENGTH = 3;
        public final IBinaryProtocolElement parent;
        public final String value;

        public BuyerParticipantId(String value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static BuyerParticipantId parse(Container container, IBinaryProtocolElement element) {
            return new BuyerParticipantId(BigEndianUtils.toString(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "buyerParticipantId = "+ this.value;
        }
    }

    // Buyer Combination Trade Id: 8 Byte Unsigned Fixed Width Integer
    public static class BuyerCombinationTradeId implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public BuyerCombinationTradeId(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static BuyerCombinationTradeId parse(Container container, IBinaryProtocolElement element) {
            return new BuyerCombinationTradeId(BigEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "buyerCombinationTradeId = "+ this.value;
        }
    }

    // Buyer Order Id: 8 Byte Unsigned Fixed Width Integer
    public static class BuyerOrderId implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public BuyerOrderId(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static BuyerOrderId parse(Container container, IBinaryProtocolElement element) {
            return new BuyerOrderId(BigEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "buyerOrderId = "+ this.value;
        }
    }

    // Buyer Side: 1 Byte Ascii String Enum with 2 values
    public static class BuyerSide implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_BUYER_SIDE value;

        public BuyerSide(ENUM_BUYER_SIDE value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static BuyerSide parse(Container container, IBinaryProtocolElement element) {
            return new BuyerSide(ENUM_BUYER_SIDE.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "buyerSide = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Buyer Tradeable Instrument Id: 4 Byte Unsigned Fixed Width Integer
    public static class BuyerTradeableInstrumentId implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public BuyerTradeableInstrumentId(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static BuyerTradeableInstrumentId parse(Container container, IBinaryProtocolElement element) {
            return new BuyerTradeableInstrumentId(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "buyerTradeableInstrumentId = "+ this.value;
        }
    }

    // Trade Price: 8 Byte Signed Fixed Width Integer
    public static class TradePrice implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public TradePrice(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static TradePrice parse(Container container, IBinaryProtocolElement element) {
            return new TradePrice(BigEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "tradePrice = "+ this.value;
        }
    }

    // Executed Quantity: 4 Byte Unsigned Fixed Width Integer
    public static class ExecutedQuantity implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public ExecutedQuantity(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static ExecutedQuantity parse(Container container, IBinaryProtocolElement element) {
            return new ExecutedQuantity(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "executedQuantity = "+ this.value;
        }
    }

    // Trade Type: 1 Byte Ascii String Enum with 12 values
    public static class TradeType implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_TRADE_TYPE value;

        public TradeType(ENUM_TRADE_TYPE value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static TradeType parse(Container container, IBinaryProtocolElement element) {
            return new TradeType(ENUM_TRADE_TYPE.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "tradeType = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    /**
    * Combination Trade Executed Message
    */
    public static class CombinationTradeExecutedMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public TradeDate tradeDate;
        public TradeableInstrumentId tradeableInstrumentId;
        public TradeType tradeType;
        public TradeId tradeId;
        public ExecutedQuantity executedQuantity;
        public TradePrice tradePrice;
        public BuyerTradeableInstrumentId buyerTradeableInstrumentId;
        public BuyerSide buyerSide;
        public BuyerOrderId buyerOrderId;
        public BuyerCombinationTradeId buyerCombinationTradeId;
        public BuyerParticipantId buyerParticipantId;
        public SellerTradeableInstrumentId sellerTradeableInstrumentId;
        public SellerSide sellerSide;
        public SellerOrderId sellerOrderId;
        public SellerCombinationTradeId sellerCombinationTradeId;
        public SellerParticipantId sellerParticipantId;

        // constructor for Combination Trade Executed Message
        private CombinationTradeExecutedMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Combination Trade Executed Message
        public static CombinationTradeExecutedMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new CombinationTradeExecutedMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.tradeDate = TradeDate.parse(container, element);
            element.tradeableInstrumentId = TradeableInstrumentId.parse(container, element);
            element.tradeType = TradeType.parse(container, element);
            element.tradeId = TradeId.parse(container, element);
            element.executedQuantity = ExecutedQuantity.parse(container, element);
            element.tradePrice = TradePrice.parse(container, element);
            element.buyerTradeableInstrumentId = BuyerTradeableInstrumentId.parse(container, element);
            element.buyerSide = BuyerSide.parse(container, element);
            element.buyerOrderId = BuyerOrderId.parse(container, element);
            element.buyerCombinationTradeId = BuyerCombinationTradeId.parse(container, element);
            element.buyerParticipantId = BuyerParticipantId.parse(container, element);
            element.sellerTradeableInstrumentId = SellerTradeableInstrumentId.parse(container, element);
            element.sellerSide = SellerSide.parse(container, element);
            element.sellerOrderId = SellerOrderId.parse(container, element);
            element.sellerCombinationTradeId = SellerCombinationTradeId.parse(container, element);
            element.sellerParticipantId = SellerParticipantId.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.tradeDate).append("\n");
            sb.append(this.tradeableInstrumentId).append("\n");
            sb.append(this.tradeType).append("\n");
            sb.append(this.tradeId).append("\n");
            sb.append(this.executedQuantity).append("\n");
            sb.append(this.tradePrice).append("\n");
            sb.append(this.buyerTradeableInstrumentId).append("\n");
            sb.append(this.buyerSide).append("\n");
            sb.append(this.buyerOrderId).append("\n");
            sb.append(this.buyerCombinationTradeId).append("\n");
            sb.append(this.buyerParticipantId).append("\n");
            sb.append(this.sellerTradeableInstrumentId).append("\n");
            sb.append(this.sellerSide).append("\n");
            sb.append(this.sellerOrderId).append("\n");
            sb.append(this.sellerCombinationTradeId).append("\n");
            sb.append(this.sellerParticipantId).append("\n");
            return sb.toString();
        }
    }

    // Participant Id Seller: 3 Byte Ascii String
    public static class ParticipantIdSeller implements IBinaryProtocolElement {
        public static final int LENGTH = 3;
        public final IBinaryProtocolElement parent;
        public final String value;

        public ParticipantIdSeller(String value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static ParticipantIdSeller parse(Container container, IBinaryProtocolElement element) {
            return new ParticipantIdSeller(BigEndianUtils.toString(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "participantIdSeller = "+ this.value;
        }
    }

    // Participant Id Buyer: 3 Byte Ascii String
    public static class ParticipantIdBuyer implements IBinaryProtocolElement {
        public static final int LENGTH = 3;
        public final IBinaryProtocolElement parent;
        public final String value;

        public ParticipantIdBuyer(String value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static ParticipantIdBuyer parse(Container container, IBinaryProtocolElement element) {
            return new ParticipantIdBuyer(BigEndianUtils.toString(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "participantIdBuyer = "+ this.value;
        }
    }

    // Combination Trade Id: 8 Byte Unsigned Fixed Width Integer
    public static class CombinationTradeId implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public CombinationTradeId(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static CombinationTradeId parse(Container container, IBinaryProtocolElement element) {
            return new CombinationTradeId(BigEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "combinationTradeId = "+ this.value;
        }
    }

    /**
    * Trade Executed Message
    */
    public static class TradeExecutedMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public TradeDate tradeDate;
        public TradeableInstrumentId tradeableInstrumentId;
        public TradeType tradeType;
        public TradeId tradeId;
        public ExecutedQuantity executedQuantity;
        public TradePrice tradePrice;
        public CombinationTradeId combinationTradeId;
        public ParticipantIdBuyer participantIdBuyer;
        public ParticipantIdSeller participantIdSeller;

        // constructor for Trade Executed Message
        private TradeExecutedMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Trade Executed Message
        public static TradeExecutedMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new TradeExecutedMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.tradeDate = TradeDate.parse(container, element);
            element.tradeableInstrumentId = TradeableInstrumentId.parse(container, element);
            element.tradeType = TradeType.parse(container, element);
            element.tradeId = TradeId.parse(container, element);
            element.executedQuantity = ExecutedQuantity.parse(container, element);
            element.tradePrice = TradePrice.parse(container, element);
            element.combinationTradeId = CombinationTradeId.parse(container, element);
            element.participantIdBuyer = ParticipantIdBuyer.parse(container, element);
            element.participantIdSeller = ParticipantIdSeller.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.tradeDate).append("\n");
            sb.append(this.tradeableInstrumentId).append("\n");
            sb.append(this.tradeType).append("\n");
            sb.append(this.tradeId).append("\n");
            sb.append(this.executedQuantity).append("\n");
            sb.append(this.tradePrice).append("\n");
            sb.append(this.combinationTradeId).append("\n");
            sb.append(this.participantIdBuyer).append("\n");
            sb.append(this.participantIdSeller).append("\n");
            return sb.toString();
        }
    }

    // Order Id: 8 Byte Unsigned Fixed Width Integer
    public static class OrderId implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public OrderId(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static OrderId parse(Container container, IBinaryProtocolElement element) {
            return new OrderId(BigEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "orderId = "+ this.value;
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

    /**
    * Implied Order Deleted Message
    */
    public static class ImpliedOrderDeletedMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public TradeDate tradeDate;
        public TradeableInstrumentId tradeableInstrumentId;
        public Side side;
        public OrderId orderId;

        // constructor for Implied Order Deleted Message
        private ImpliedOrderDeletedMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Implied Order Deleted Message
        public static ImpliedOrderDeletedMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new ImpliedOrderDeletedMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.tradeDate = TradeDate.parse(container, element);
            element.tradeableInstrumentId = TradeableInstrumentId.parse(container, element);
            element.side = Side.parse(container, element);
            element.orderId = OrderId.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.tradeDate).append("\n");
            sb.append(this.tradeableInstrumentId).append("\n");
            sb.append(this.side).append("\n");
            sb.append(this.orderId).append("\n");
            return sb.toString();
        }
    }

    // Price: 8 Byte Signed Fixed Width Integer
    public static class Price implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public Price(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Price parse(Container container, IBinaryProtocolElement element) {
            return new Price(BigEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "price = "+ this.value;
        }
    }

    // Order Book Priority: 8 Byte Unsigned Fixed Width Integer
    public static class OrderBookPriority implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public OrderBookPriority(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static OrderBookPriority parse(Container container, IBinaryProtocolElement element) {
            return new OrderBookPriority(BigEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "orderBookPriority = "+ this.value;
        }
    }

    /**
    * Implied Order Replaced Message
    */
    public static class ImpliedOrderReplacedMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public TradeDate tradeDate;
        public TradeableInstrumentId tradeableInstrumentId;
        public Side side;
        public OrderId orderId;
        public OrderBookPriority orderBookPriority;
        public Quantity quantity;
        public Price price;

        // constructor for Implied Order Replaced Message
        private ImpliedOrderReplacedMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Implied Order Replaced Message
        public static ImpliedOrderReplacedMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new ImpliedOrderReplacedMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.tradeDate = TradeDate.parse(container, element);
            element.tradeableInstrumentId = TradeableInstrumentId.parse(container, element);
            element.side = Side.parse(container, element);
            element.orderId = OrderId.parse(container, element);
            element.orderBookPriority = OrderBookPriority.parse(container, element);
            element.quantity = Quantity.parse(container, element);
            element.price = Price.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.tradeDate).append("\n");
            sb.append(this.tradeableInstrumentId).append("\n");
            sb.append(this.side).append("\n");
            sb.append(this.orderId).append("\n");
            sb.append(this.orderBookPriority).append("\n");
            sb.append(this.quantity).append("\n");
            sb.append(this.price).append("\n");
            return sb.toString();
        }
    }

    /**
    * Implied Order Added Message
    */
    public static class ImpliedOrderAddedMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public TradeDate tradeDate;
        public TradeableInstrumentId tradeableInstrumentId;
        public Side side;
        public OrderId orderId;
        public OrderBookPriority orderBookPriority;
        public Quantity quantity;
        public Price price;

        // constructor for Implied Order Added Message
        private ImpliedOrderAddedMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Implied Order Added Message
        public static ImpliedOrderAddedMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new ImpliedOrderAddedMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.tradeDate = TradeDate.parse(container, element);
            element.tradeableInstrumentId = TradeableInstrumentId.parse(container, element);
            element.side = Side.parse(container, element);
            element.orderId = OrderId.parse(container, element);
            element.orderBookPriority = OrderBookPriority.parse(container, element);
            element.quantity = Quantity.parse(container, element);
            element.price = Price.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.tradeDate).append("\n");
            sb.append(this.tradeableInstrumentId).append("\n");
            sb.append(this.side).append("\n");
            sb.append(this.orderId).append("\n");
            sb.append(this.orderBookPriority).append("\n");
            sb.append(this.quantity).append("\n");
            sb.append(this.price).append("\n");
            return sb.toString();
        }
    }

    // Opposite Order Id: 8 Byte Unsigned Fixed Width Integer
    public static class OppositeOrderId implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public OppositeOrderId(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static OppositeOrderId parse(Container container, IBinaryProtocolElement element) {
            return new OppositeOrderId(BigEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "oppositeOrderId = "+ this.value;
        }
    }

    // Opposite Side: 1 Byte Ascii String Enum with 2 values
    public static class OppositeSide implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_OPPOSITE_SIDE value;

        public OppositeSide(ENUM_OPPOSITE_SIDE value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static OppositeSide parse(Container container, IBinaryProtocolElement element) {
            return new OppositeSide(ENUM_OPPOSITE_SIDE.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "oppositeSide = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Opposite Tradeable Instrument Id: 4 Byte Unsigned Fixed Width Integer
    public static class OppositeTradeableInstrumentId implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public OppositeTradeableInstrumentId(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static OppositeTradeableInstrumentId parse(Container container, IBinaryProtocolElement element) {
            return new OppositeTradeableInstrumentId(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "oppositeTradeableInstrumentId = "+ this.value;
        }
    }

    // Quantity Remaining: 4 Byte Unsigned Fixed Width Integer
    public static class QuantityRemaining implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public QuantityRemaining(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static QuantityRemaining parse(Container container, IBinaryProtocolElement element) {
            return new QuantityRemaining(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "quantityRemaining = "+ this.value;
        }
    }

    /**
    * Combination Order Executed Message
    */
    public static class CombinationOrderExecutedMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public TradeDate tradeDate;
        public TradeableInstrumentId tradeableInstrumentId;
        public Side side;
        public OrderId orderId;
        public QuantityRemaining quantityRemaining;
        public TradeType tradeType;
        public TradeId tradeId;
        public ExecutedQuantity executedQuantity;
        public TradePrice tradePrice;
        public OppositeTradeableInstrumentId oppositeTradeableInstrumentId;
        public OppositeSide oppositeSide;
        public OppositeOrderId oppositeOrderId;
        public CombinationTradeId combinationTradeId;

        // constructor for Combination Order Executed Message
        private CombinationOrderExecutedMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Combination Order Executed Message
        public static CombinationOrderExecutedMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new CombinationOrderExecutedMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.tradeDate = TradeDate.parse(container, element);
            element.tradeableInstrumentId = TradeableInstrumentId.parse(container, element);
            element.side = Side.parse(container, element);
            element.orderId = OrderId.parse(container, element);
            element.quantityRemaining = QuantityRemaining.parse(container, element);
            element.tradeType = TradeType.parse(container, element);
            element.tradeId = TradeId.parse(container, element);
            element.executedQuantity = ExecutedQuantity.parse(container, element);
            element.tradePrice = TradePrice.parse(container, element);
            element.oppositeTradeableInstrumentId = OppositeTradeableInstrumentId.parse(container, element);
            element.oppositeSide = OppositeSide.parse(container, element);
            element.oppositeOrderId = OppositeOrderId.parse(container, element);
            element.combinationTradeId = CombinationTradeId.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.tradeDate).append("\n");
            sb.append(this.tradeableInstrumentId).append("\n");
            sb.append(this.side).append("\n");
            sb.append(this.orderId).append("\n");
            sb.append(this.quantityRemaining).append("\n");
            sb.append(this.tradeType).append("\n");
            sb.append(this.tradeId).append("\n");
            sb.append(this.executedQuantity).append("\n");
            sb.append(this.tradePrice).append("\n");
            sb.append(this.oppositeTradeableInstrumentId).append("\n");
            sb.append(this.oppositeSide).append("\n");
            sb.append(this.oppositeOrderId).append("\n");
            sb.append(this.combinationTradeId).append("\n");
            return sb.toString();
        }
    }

    /**
    * Auction Order Executed Message
    */
    public static class AuctionOrderExecutedMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public TradeDate tradeDate;
        public TradeableInstrumentId tradeableInstrumentId;
        public Side side;
        public OrderId orderId;
        public QuantityRemaining quantityRemaining;
        public TradeType tradeType;
        public TradeId tradeId;
        public ExecutedQuantity executedQuantity;
        public TradePrice tradePrice;
        public OppositeOrderId oppositeOrderId;

        // constructor for Auction Order Executed Message
        private AuctionOrderExecutedMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Auction Order Executed Message
        public static AuctionOrderExecutedMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new AuctionOrderExecutedMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.tradeDate = TradeDate.parse(container, element);
            element.tradeableInstrumentId = TradeableInstrumentId.parse(container, element);
            element.side = Side.parse(container, element);
            element.orderId = OrderId.parse(container, element);
            element.quantityRemaining = QuantityRemaining.parse(container, element);
            element.tradeType = TradeType.parse(container, element);
            element.tradeId = TradeId.parse(container, element);
            element.executedQuantity = ExecutedQuantity.parse(container, element);
            element.tradePrice = TradePrice.parse(container, element);
            element.oppositeOrderId = OppositeOrderId.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.tradeDate).append("\n");
            sb.append(this.tradeableInstrumentId).append("\n");
            sb.append(this.side).append("\n");
            sb.append(this.orderId).append("\n");
            sb.append(this.quantityRemaining).append("\n");
            sb.append(this.tradeType).append("\n");
            sb.append(this.tradeId).append("\n");
            sb.append(this.executedQuantity).append("\n");
            sb.append(this.tradePrice).append("\n");
            sb.append(this.oppositeOrderId).append("\n");
            return sb.toString();
        }
    }

    // Counter Party Id: 3 Byte Ascii String
    public static class CounterPartyId implements IBinaryProtocolElement {
        public static final int LENGTH = 3;
        public final IBinaryProtocolElement parent;
        public final String value;

        public CounterPartyId(String value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static CounterPartyId parse(Container container, IBinaryProtocolElement element) {
            return new CounterPartyId(BigEndianUtils.toString(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "counterPartyId = "+ this.value;
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
        public TradeDate tradeDate;
        public TradeableInstrumentId tradeableInstrumentId;
        public Side side;
        public OrderId orderId;
        public QuantityRemaining quantityRemaining;
        public TradeType tradeType;
        public TradeId tradeId;
        public ExecutedQuantity executedQuantity;
        public TradePrice tradePrice;
        public CombinationTradeId combinationTradeId;
        public CounterPartyId counterPartyId;

        // constructor for Order Executed Message
        private OrderExecutedMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Order Executed Message
        public static OrderExecutedMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new OrderExecutedMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.tradeDate = TradeDate.parse(container, element);
            element.tradeableInstrumentId = TradeableInstrumentId.parse(container, element);
            element.side = Side.parse(container, element);
            element.orderId = OrderId.parse(container, element);
            element.quantityRemaining = QuantityRemaining.parse(container, element);
            element.tradeType = TradeType.parse(container, element);
            element.tradeId = TradeId.parse(container, element);
            element.executedQuantity = ExecutedQuantity.parse(container, element);
            element.tradePrice = TradePrice.parse(container, element);
            element.combinationTradeId = CombinationTradeId.parse(container, element);
            element.counterPartyId = CounterPartyId.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.tradeDate).append("\n");
            sb.append(this.tradeableInstrumentId).append("\n");
            sb.append(this.side).append("\n");
            sb.append(this.orderId).append("\n");
            sb.append(this.quantityRemaining).append("\n");
            sb.append(this.tradeType).append("\n");
            sb.append(this.tradeId).append("\n");
            sb.append(this.executedQuantity).append("\n");
            sb.append(this.tradePrice).append("\n");
            sb.append(this.combinationTradeId).append("\n");
            sb.append(this.counterPartyId).append("\n");
            return sb.toString();
        }
    }

    /**
    * Order Deleted Message
    */
    public static class OrderDeletedMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public TradeDate tradeDate;
        public TradeableInstrumentId tradeableInstrumentId;
        public Side side;
        public OrderId orderId;

        // constructor for Order Deleted Message
        private OrderDeletedMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Order Deleted Message
        public static OrderDeletedMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new OrderDeletedMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.tradeDate = TradeDate.parse(container, element);
            element.tradeableInstrumentId = TradeableInstrumentId.parse(container, element);
            element.side = Side.parse(container, element);
            element.orderId = OrderId.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.tradeDate).append("\n");
            sb.append(this.tradeableInstrumentId).append("\n");
            sb.append(this.side).append("\n");
            sb.append(this.orderId).append("\n");
            return sb.toString();
        }
    }

    /**
    * Order Volume Cancelled Message
    */
    public static class OrderVolumeCancelledMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public TradeDate tradeDate;
        public TradeableInstrumentId tradeableInstrumentId;
        public Side side;
        public OrderId orderId;
        public Quantity quantity;

        // constructor for Order Volume Cancelled Message
        private OrderVolumeCancelledMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Order Volume Cancelled Message
        public static OrderVolumeCancelledMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new OrderVolumeCancelledMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.tradeDate = TradeDate.parse(container, element);
            element.tradeableInstrumentId = TradeableInstrumentId.parse(container, element);
            element.side = Side.parse(container, element);
            element.orderId = OrderId.parse(container, element);
            element.quantity = Quantity.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.tradeDate).append("\n");
            sb.append(this.tradeableInstrumentId).append("\n");
            sb.append(this.side).append("\n");
            sb.append(this.orderId).append("\n");
            sb.append(this.quantity).append("\n");
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
        public TradeDate tradeDate;
        public TradeableInstrumentId tradeableInstrumentId;
        public Side side;
        public OrderId orderId;
        public OrderBookPriority orderBookPriority;
        public Quantity quantity;
        public Price price;

        // constructor for Add Order Message
        private AddOrderMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Add Order Message
        public static AddOrderMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new AddOrderMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.tradeDate = TradeDate.parse(container, element);
            element.tradeableInstrumentId = TradeableInstrumentId.parse(container, element);
            element.side = Side.parse(container, element);
            element.orderId = OrderId.parse(container, element);
            element.orderBookPriority = OrderBookPriority.parse(container, element);
            element.quantity = Quantity.parse(container, element);
            element.price = Price.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.tradeDate).append("\n");
            sb.append(this.tradeableInstrumentId).append("\n");
            sb.append(this.side).append("\n");
            sb.append(this.orderId).append("\n");
            sb.append(this.orderBookPriority).append("\n");
            sb.append(this.quantity).append("\n");
            sb.append(this.price).append("\n");
            return sb.toString();
        }
    }

    // Session State: 1 Byte Ascii String Enum with 6 values
    public static class SessionState implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_SESSION_STATE value;

        public SessionState(ENUM_SESSION_STATE value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static SessionState parse(Container container, IBinaryProtocolElement element) {
            return new SessionState(ENUM_SESSION_STATE.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "sessionState = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    /**
    * Order Book State Message
    */
    public static class OrderBookStateMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public TradeDate tradeDate;
        public TradeableInstrumentId tradeableInstrumentId;
        public SessionState sessionState;

        // constructor for Order Book State Message
        private OrderBookStateMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Order Book State Message
        public static OrderBookStateMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new OrderBookStateMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.tradeDate = TradeDate.parse(container, element);
            element.tradeableInstrumentId = TradeableInstrumentId.parse(container, element);
            element.sessionState = SessionState.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.tradeDate).append("\n");
            sb.append(this.tradeableInstrumentId).append("\n");
            sb.append(this.sessionState).append("\n");
            return sb.toString();
        }
    }

    // Price Leg: 8 Byte Signed Fixed Width Integer
    public static class PriceLeg implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public PriceLeg(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static PriceLeg parse(Container container, IBinaryProtocolElement element) {
            return new PriceLeg(BigEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "priceLeg = "+ this.value;
        }
    }

    // Ratio Leg: 4 Byte Unsigned Fixed Width Integer
    public static class RatioLeg implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public RatioLeg(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static RatioLeg parse(Container container, IBinaryProtocolElement element) {
            return new RatioLeg(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "ratioLeg = "+ this.value;
        }
    }

    // Side Leg: 1 Byte Ascii String Enum with 3 values
    public static class SideLeg implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_SIDE_LEG value;

        public SideLeg(ENUM_SIDE_LEG value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static SideLeg parse(Container container, IBinaryProtocolElement element) {
            return new SideLeg(ENUM_SIDE_LEG.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "sideLeg = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Tradeable Instrument Id Leg: 4 Byte Unsigned Fixed Width Integer
    public static class TradeableInstrumentIdLeg implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public TradeableInstrumentIdLeg(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static TradeableInstrumentIdLeg parse(Container container, IBinaryProtocolElement element) {
            return new TradeableInstrumentIdLeg(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "tradeableInstrumentIdLeg = "+ this.value;
        }
    }

    /**
    * Bundle Leg
    */
    public static class BundleLeg implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public TradeableInstrumentIdLeg tradeableInstrumentIdLeg;
        public SideLeg sideLeg;
        public RatioLeg ratioLeg;
        public PriceLeg priceLeg;

        // constructor for Bundle Leg
        private BundleLeg(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Bundle Leg
        public static BundleLeg parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new BundleLeg(parentElement);

            element.tradeableInstrumentIdLeg = TradeableInstrumentIdLeg.parse(container, element);
            element.sideLeg = SideLeg.parse(container, element);
            element.ratioLeg = RatioLeg.parse(container, element);
            element.priceLeg = PriceLeg.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.tradeableInstrumentIdLeg).append("\n");
            sb.append(this.sideLeg).append("\n");
            sb.append(this.ratioLeg).append("\n");
            sb.append(this.priceLeg).append("\n");
            return sb.toString();
        }
    }

    // Legs: 1 Byte Unsigned Fixed Width Integer
    public static class Legs implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public Legs(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Legs parse(Container container, IBinaryProtocolElement element) {
            return new Legs(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "legs = "+ this.value;
        }
    }

    // Price Minimum Tick: 4 Byte Unsigned Fixed Width Integer
    public static class PriceMinimumTick implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public PriceMinimumTick(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static PriceMinimumTick parse(Container container, IBinaryProtocolElement element) {
            return new PriceMinimumTick(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "priceMinimumTick = "+ this.value;
        }
    }

    // Price Fractional Denominator: 4 Byte Unsigned Fixed Width Integer
    public static class PriceFractionalDenominator implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public PriceFractionalDenominator(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static PriceFractionalDenominator parse(Container container, IBinaryProtocolElement element) {
            return new PriceFractionalDenominator(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "priceFractionalDenominator = "+ this.value;
        }
    }

    // Price Display Decimals: 1 Byte Unsigned Fixed Width Integer
    public static class PriceDisplayDecimals implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public PriceDisplayDecimals(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static PriceDisplayDecimals parse(Container container, IBinaryProtocolElement element) {
            return new PriceDisplayDecimals(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "priceDisplayDecimals = "+ this.value;
        }
    }

    // Price Method: 1 Byte Unsigned Fixed Width Integer Enum with 4 values
    public static class PriceMethod implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_PRICE_METHOD value;

        public PriceMethod(ENUM_PRICE_METHOD value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static PriceMethod parse(Container container, IBinaryProtocolElement element) {
            return new PriceMethod(ENUM_PRICE_METHOD.valueOf((int)BigEndianUtils.toByte(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "priceMethod = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Cfi Code: 6 Byte Ascii String
    public static class CfiCode implements IBinaryProtocolElement {
        public static final int LENGTH = 6;
        public final IBinaryProtocolElement parent;
        public final String value;

        public CfiCode(String value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static CfiCode parse(Container container, IBinaryProtocolElement element) {
            return new CfiCode(BigEndianUtils.toString(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "cfiCode = "+ this.value;
        }
    }

    // Long Name: 60 Byte Ascii String
    public static class LongName implements IBinaryProtocolElement {
        public static final int LENGTH = 60;
        public final IBinaryProtocolElement parent;
        public final String value;

        public LongName(String value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static LongName parse(Container container, IBinaryProtocolElement element) {
            return new LongName(BigEndianUtils.toString(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "longName = "+ this.value;
        }
    }

    // Symbol Name: 32 Byte Ascii String
    public static class SymbolName implements IBinaryProtocolElement {
        public static final int LENGTH = 32;
        public final IBinaryProtocolElement parent;
        public final String value;

        public SymbolName(String value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static SymbolName parse(Container container, IBinaryProtocolElement element) {
            return new SymbolName(BigEndianUtils.toString(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "symbolName = "+ this.value;
        }
    }

    /**
    * Bundles Symbol Directory
    */
    public static class BundlesSymbolDirectory implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public TradeDate tradeDate;
        public TradeableInstrumentId tradeableInstrumentId;
        public SymbolName symbolName;
        public LongName longName;
        public CfiCode cfiCode;
        public PriceMethod priceMethod;
        public PriceDisplayDecimals priceDisplayDecimals;
        public PriceFractionalDenominator priceFractionalDenominator;
        public PriceMinimumTick priceMinimumTick;
        public Legs legs;
        public BundleLeg bundleLeg;

        // constructor for Bundles Symbol Directory
        private BundlesSymbolDirectory(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Bundles Symbol Directory
        public static BundlesSymbolDirectory parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new BundlesSymbolDirectory(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.tradeDate = TradeDate.parse(container, element);
            element.tradeableInstrumentId = TradeableInstrumentId.parse(container, element);
            element.symbolName = SymbolName.parse(container, element);
            element.longName = LongName.parse(container, element);
            element.cfiCode = CfiCode.parse(container, element);
            element.priceMethod = PriceMethod.parse(container, element);
            element.priceDisplayDecimals = PriceDisplayDecimals.parse(container, element);
            element.priceFractionalDenominator = PriceFractionalDenominator.parse(container, element);
            element.priceMinimumTick = PriceMinimumTick.parse(container, element);
            element.legs = Legs.parse(container, element);
            element.bundleLeg = BundleLeg.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.tradeDate).append("\n");
            sb.append(this.tradeableInstrumentId).append("\n");
            sb.append(this.symbolName).append("\n");
            sb.append(this.longName).append("\n");
            sb.append(this.cfiCode).append("\n");
            sb.append(this.priceMethod).append("\n");
            sb.append(this.priceDisplayDecimals).append("\n");
            sb.append(this.priceFractionalDenominator).append("\n");
            sb.append(this.priceMinimumTick).append("\n");
            sb.append(this.legs).append("\n");
            sb.append(this.bundleLeg).append("\n");
            return sb.toString();
        }
    }

    /**
    * Combination Leg
    */
    public static class CombinationLeg implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public TradeableInstrumentIdLeg tradeableInstrumentIdLeg;
        public SideLeg sideLeg;
        public RatioLeg ratioLeg;
        public PriceLeg priceLeg;

        // constructor for Combination Leg
        private CombinationLeg(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Combination Leg
        public static CombinationLeg parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new CombinationLeg(parentElement);

            element.tradeableInstrumentIdLeg = TradeableInstrumentIdLeg.parse(container, element);
            element.sideLeg = SideLeg.parse(container, element);
            element.ratioLeg = RatioLeg.parse(container, element);
            element.priceLeg = PriceLeg.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.tradeableInstrumentIdLeg).append("\n");
            sb.append(this.sideLeg).append("\n");
            sb.append(this.ratioLeg).append("\n");
            sb.append(this.priceLeg).append("\n");
            return sb.toString();
        }
    }

    /**
    * Combination Symbol Directory Message
    */
    public static class CombinationSymbolDirectoryMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public TradeDate tradeDate;
        public TradeableInstrumentId tradeableInstrumentId;
        public SymbolName symbolName;
        public LongName longName;
        public CfiCode cfiCode;
        public PriceMethod priceMethod;
        public PriceDisplayDecimals priceDisplayDecimals;
        public PriceFractionalDenominator priceFractionalDenominator;
        public PriceMinimumTick priceMinimumTick;
        public Legs legs;
        public CombinationLeg combinationLeg;

        // constructor for Combination Symbol Directory Message
        private CombinationSymbolDirectoryMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Combination Symbol Directory Message
        public static CombinationSymbolDirectoryMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new CombinationSymbolDirectoryMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.tradeDate = TradeDate.parse(container, element);
            element.tradeableInstrumentId = TradeableInstrumentId.parse(container, element);
            element.symbolName = SymbolName.parse(container, element);
            element.longName = LongName.parse(container, element);
            element.cfiCode = CfiCode.parse(container, element);
            element.priceMethod = PriceMethod.parse(container, element);
            element.priceDisplayDecimals = PriceDisplayDecimals.parse(container, element);
            element.priceFractionalDenominator = PriceFractionalDenominator.parse(container, element);
            element.priceMinimumTick = PriceMinimumTick.parse(container, element);
            element.legs = Legs.parse(container, element);
            element.combinationLeg = CombinationLeg.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.tradeDate).append("\n");
            sb.append(this.tradeableInstrumentId).append("\n");
            sb.append(this.symbolName).append("\n");
            sb.append(this.longName).append("\n");
            sb.append(this.cfiCode).append("\n");
            sb.append(this.priceMethod).append("\n");
            sb.append(this.priceDisplayDecimals).append("\n");
            sb.append(this.priceFractionalDenominator).append("\n");
            sb.append(this.priceMinimumTick).append("\n");
            sb.append(this.legs).append("\n");
            sb.append(this.combinationLeg).append("\n");
            return sb.toString();
        }
    }

    // Basis Of Quotation: 10 Byte Ascii String
    public static class BasisOfQuotation implements IBinaryProtocolElement {
        public static final int LENGTH = 10;
        public final IBinaryProtocolElement parent;
        public final String value;

        public BasisOfQuotation(String value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static BasisOfQuotation parse(Container container, IBinaryProtocolElement element) {
            return new BasisOfQuotation(BigEndianUtils.toString(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "basisOfQuotation = "+ this.value;
        }
    }

    // Expiry Date: 4 Byte Unsigned Fixed Width Integer
    public static class ExpiryDate implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public ExpiryDate(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static ExpiryDate parse(Container container, IBinaryProtocolElement element) {
            return new ExpiryDate(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "expiryDate = "+ this.value;
        }
    }

    // Block Lot Size: 4 Byte Unsigned Fixed Width Integer
    public static class BlockLotSize implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public BlockLotSize(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static BlockLotSize parse(Container container, IBinaryProtocolElement element) {
            return new BlockLotSize(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "blockLotSize = "+ this.value;
        }
    }

    // Payments Per Year: 1 Byte Unsigned Fixed Width Integer
    public static class PaymentsPerYear implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public PaymentsPerYear(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static PaymentsPerYear parse(Container container, IBinaryProtocolElement element) {
            return new PaymentsPerYear(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "paymentsPerYear = "+ this.value;
        }
    }

    // Coupon Rate: 2 Byte Unsigned Fixed Width Integer
    public static class CouponRate implements IBinaryProtocolElement {
        public static final int LENGTH = 2;
        public final IBinaryProtocolElement parent;
        public final Short value;

        public CouponRate(Short value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static CouponRate parse(Container container, IBinaryProtocolElement element) {
            return new CouponRate(BigEndianUtils.toShort(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "couponRate = "+ this.value;
        }
    }

    // Maturity Value: 1 Byte Unsigned Fixed Width Integer
    public static class MaturityValue implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public MaturityValue(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static MaturityValue parse(Container container, IBinaryProtocolElement element) {
            return new MaturityValue(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "maturityValue = "+ this.value;
        }
    }

    // Lot Size Or Face Value: 8 Byte Unsigned Fixed Width Integer
    public static class LotSizeOrFaceValue implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public LotSizeOrFaceValue(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static LotSizeOrFaceValue parse(Container container, IBinaryProtocolElement element) {
            return new LotSizeOrFaceValue(BigEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "lotSizeOrFaceValue = "+ this.value;
        }
    }

    // Currency: 3 Byte Ascii String
    public static class Currency implements IBinaryProtocolElement {
        public static final int LENGTH = 3;
        public final IBinaryProtocolElement parent;
        public final String value;

        public Currency(String value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Currency parse(Container container, IBinaryProtocolElement element) {
            return new Currency(BigEndianUtils.toString(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "currency = "+ this.value;
        }
    }

    // Volatility: 8 Byte Unsigned Fixed Width Integer
    public static class Volatility implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public Volatility(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Volatility parse(Container container, IBinaryProtocolElement element) {
            return new Volatility(BigEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "volatility = "+ this.value;
        }
    }

    // Prior Day Settlement: 8 Byte Signed Fixed Width Integer
    public static class PriorDaySettlement implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public PriorDaySettlement(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static PriorDaySettlement parse(Container container, IBinaryProtocolElement element) {
            return new PriorDaySettlement(BigEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "priorDaySettlement = "+ this.value;
        }
    }

    // Last Trading Date: 4 Byte Unsigned Fixed Width Integer
    public static class LastTradingDate implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public LastTradingDate(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static LastTradingDate parse(Container container, IBinaryProtocolElement element) {
            return new LastTradingDate(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "lastTradingDate = "+ this.value;
        }
    }

    // Strike Price Minimum Tick: 4 Byte Unsigned Fixed Width Integer
    public static class StrikePriceMinimumTick implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public StrikePriceMinimumTick(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static StrikePriceMinimumTick parse(Container container, IBinaryProtocolElement element) {
            return new StrikePriceMinimumTick(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "strikePriceMinimumTick = "+ this.value;
        }
    }

    // Strike Price Fractional Denominator: 4 Byte Unsigned Fixed Width Integer
    public static class StrikePriceFractionalDenominator implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public StrikePriceFractionalDenominator(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static StrikePriceFractionalDenominator parse(Container container, IBinaryProtocolElement element) {
            return new StrikePriceFractionalDenominator(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "strikePriceFractionalDenominator = "+ this.value;
        }
    }

    // Strike Price Decimal Position: 1 Byte Unsigned Fixed Width Integer
    public static class StrikePriceDecimalPosition implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public StrikePriceDecimalPosition(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static StrikePriceDecimalPosition parse(Container container, IBinaryProtocolElement element) {
            return new StrikePriceDecimalPosition(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "strikePriceDecimalPosition = "+ this.value;
        }
    }

    // Underlying Tradeable Instrument Id: 4 Byte Unsigned Fixed Width Integer
    public static class UnderlyingTradeableInstrumentId implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public UnderlyingTradeableInstrumentId(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static UnderlyingTradeableInstrumentId parse(Container container, IBinaryProtocolElement element) {
            return new UnderlyingTradeableInstrumentId(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "underlyingTradeableInstrumentId = "+ this.value;
        }
    }

    // Strike: 8 Byte Signed Fixed Width Integer
    public static class Strike implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public Strike(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Strike parse(Container container, IBinaryProtocolElement element) {
            return new Strike(BigEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "strike = "+ this.value;
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

    // Expiry Month: 1 Byte Unsigned Fixed Width Integer Enum with 12 values
    public static class ExpiryMonth implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_EXPIRY_MONTH value;

        public ExpiryMonth(ENUM_EXPIRY_MONTH value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static ExpiryMonth parse(Container container, IBinaryProtocolElement element) {
            return new ExpiryMonth(ENUM_EXPIRY_MONTH.valueOf((int)BigEndianUtils.toByte(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "expiryMonth = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Expiry Year: 2 Byte Unsigned Fixed Width Integer
    public static class ExpiryYear implements IBinaryProtocolElement {
        public static final int LENGTH = 2;
        public final IBinaryProtocolElement parent;
        public final Short value;

        public ExpiryYear(Short value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static ExpiryYear parse(Container container, IBinaryProtocolElement element) {
            return new ExpiryYear(BigEndianUtils.toShort(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "expiryYear = "+ this.value;
        }
    }

    // Instrument: 6 Byte Ascii String
    public static class Instrument implements IBinaryProtocolElement {
        public static final int LENGTH = 6;
        public final IBinaryProtocolElement parent;
        public final String value;

        public Instrument(String value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Instrument parse(Container container, IBinaryProtocolElement element) {
            return new Instrument(BigEndianUtils.toString(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "instrument = "+ this.value;
        }
    }

    // Exchange: 6 Byte Ascii String
    public static class Exchange implements IBinaryProtocolElement {
        public static final int LENGTH = 6;
        public final IBinaryProtocolElement parent;
        public final String value;

        public Exchange(String value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Exchange parse(Container container, IBinaryProtocolElement element) {
            return new Exchange(BigEndianUtils.toString(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "exchange = "+ this.value;
        }
    }

    // Isin: 12 Byte Ascii String
    public static class Isin implements IBinaryProtocolElement {
        public static final int LENGTH = 12;
        public final IBinaryProtocolElement parent;
        public final String value;

        public Isin(String value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Isin parse(Container container, IBinaryProtocolElement element) {
            return new Isin(BigEndianUtils.toString(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "isin = "+ this.value;
        }
    }

    /**
    * Options Symbol Directory Message
    */
    public static class OptionsSymbolDirectoryMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public TradeDate tradeDate;
        public TradeableInstrumentId tradeableInstrumentId;
        public SymbolName symbolName;
        public LongName longName;
        public Isin isin;
        public Exchange exchange;
        public Instrument instrument;
        public CfiCode cfiCode;
        public ExpiryYear expiryYear;
        public ExpiryMonth expiryMonth;
        public OptionType optionType;
        public Strike strike;
        public UnderlyingTradeableInstrumentId underlyingTradeableInstrumentId;
        public PriceDisplayDecimals priceDisplayDecimals;
        public PriceFractionalDenominator priceFractionalDenominator;
        public PriceMinimumTick priceMinimumTick;
        public StrikePriceDecimalPosition strikePriceDecimalPosition;
        public StrikePriceFractionalDenominator strikePriceFractionalDenominator;
        public StrikePriceMinimumTick strikePriceMinimumTick;
        public LastTradingDate lastTradingDate;
        public PriorDaySettlement priorDaySettlement;
        public Volatility volatility;
        public Currency currency;
        public LotSizeOrFaceValue lotSizeOrFaceValue;
        public MaturityValue maturityValue;
        public CouponRate couponRate;
        public PaymentsPerYear paymentsPerYear;
        public BlockLotSize blockLotSize;
        public ExpiryDate expiryDate;
        public BasisOfQuotation basisOfQuotation;

        // constructor for Options Symbol Directory Message
        private OptionsSymbolDirectoryMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Options Symbol Directory Message
        public static OptionsSymbolDirectoryMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new OptionsSymbolDirectoryMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.tradeDate = TradeDate.parse(container, element);
            element.tradeableInstrumentId = TradeableInstrumentId.parse(container, element);
            element.symbolName = SymbolName.parse(container, element);
            element.longName = LongName.parse(container, element);
            element.isin = Isin.parse(container, element);
            element.exchange = Exchange.parse(container, element);
            element.instrument = Instrument.parse(container, element);
            element.cfiCode = CfiCode.parse(container, element);
            element.expiryYear = ExpiryYear.parse(container, element);
            element.expiryMonth = ExpiryMonth.parse(container, element);
            element.optionType = OptionType.parse(container, element);
            element.strike = Strike.parse(container, element);
            element.underlyingTradeableInstrumentId = UnderlyingTradeableInstrumentId.parse(container, element);
            element.priceDisplayDecimals = PriceDisplayDecimals.parse(container, element);
            element.priceFractionalDenominator = PriceFractionalDenominator.parse(container, element);
            element.priceMinimumTick = PriceMinimumTick.parse(container, element);
            element.strikePriceDecimalPosition = StrikePriceDecimalPosition.parse(container, element);
            element.strikePriceFractionalDenominator = StrikePriceFractionalDenominator.parse(container, element);
            element.strikePriceMinimumTick = StrikePriceMinimumTick.parse(container, element);
            element.lastTradingDate = LastTradingDate.parse(container, element);
            element.priorDaySettlement = PriorDaySettlement.parse(container, element);
            element.volatility = Volatility.parse(container, element);
            element.currency = Currency.parse(container, element);
            element.lotSizeOrFaceValue = LotSizeOrFaceValue.parse(container, element);
            element.maturityValue = MaturityValue.parse(container, element);
            element.couponRate = CouponRate.parse(container, element);
            element.paymentsPerYear = PaymentsPerYear.parse(container, element);
            element.blockLotSize = BlockLotSize.parse(container, element);
            element.expiryDate = ExpiryDate.parse(container, element);
            element.basisOfQuotation = BasisOfQuotation.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.tradeDate).append("\n");
            sb.append(this.tradeableInstrumentId).append("\n");
            sb.append(this.symbolName).append("\n");
            sb.append(this.longName).append("\n");
            sb.append(this.isin).append("\n");
            sb.append(this.exchange).append("\n");
            sb.append(this.instrument).append("\n");
            sb.append(this.cfiCode).append("\n");
            sb.append(this.expiryYear).append("\n");
            sb.append(this.expiryMonth).append("\n");
            sb.append(this.optionType).append("\n");
            sb.append(this.strike).append("\n");
            sb.append(this.underlyingTradeableInstrumentId).append("\n");
            sb.append(this.priceDisplayDecimals).append("\n");
            sb.append(this.priceFractionalDenominator).append("\n");
            sb.append(this.priceMinimumTick).append("\n");
            sb.append(this.strikePriceDecimalPosition).append("\n");
            sb.append(this.strikePriceFractionalDenominator).append("\n");
            sb.append(this.strikePriceMinimumTick).append("\n");
            sb.append(this.lastTradingDate).append("\n");
            sb.append(this.priorDaySettlement).append("\n");
            sb.append(this.volatility).append("\n");
            sb.append(this.currency).append("\n");
            sb.append(this.lotSizeOrFaceValue).append("\n");
            sb.append(this.maturityValue).append("\n");
            sb.append(this.couponRate).append("\n");
            sb.append(this.paymentsPerYear).append("\n");
            sb.append(this.blockLotSize).append("\n");
            sb.append(this.expiryDate).append("\n");
            sb.append(this.basisOfQuotation).append("\n");
            return sb.toString();
        }
    }

    /**
    * Future Symbol Directory Message
    */
    public static class FutureSymbolDirectoryMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public TradeDate tradeDate;
        public TradeableInstrumentId tradeableInstrumentId;
        public SymbolName symbolName;
        public LongName longName;
        public Isin isin;
        public Exchange exchange;
        public Instrument instrument;
        public CfiCode cfiCode;
        public ExpiryYear expiryYear;
        public ExpiryMonth expiryMonth;
        public PriceDisplayDecimals priceDisplayDecimals;
        public PriceFractionalDenominator priceFractionalDenominator;
        public PriceMinimumTick priceMinimumTick;
        public LastTradingDate lastTradingDate;
        public PriorDaySettlement priorDaySettlement;
        public Currency currency;
        public LotSizeOrFaceValue lotSizeOrFaceValue;
        public MaturityValue maturityValue;
        public CouponRate couponRate;
        public PaymentsPerYear paymentsPerYear;
        public BlockLotSize blockLotSize;
        public ExpiryDate expiryDate;

        // constructor for Future Symbol Directory Message
        private FutureSymbolDirectoryMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Future Symbol Directory Message
        public static FutureSymbolDirectoryMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new FutureSymbolDirectoryMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.tradeDate = TradeDate.parse(container, element);
            element.tradeableInstrumentId = TradeableInstrumentId.parse(container, element);
            element.symbolName = SymbolName.parse(container, element);
            element.longName = LongName.parse(container, element);
            element.isin = Isin.parse(container, element);
            element.exchange = Exchange.parse(container, element);
            element.instrument = Instrument.parse(container, element);
            element.cfiCode = CfiCode.parse(container, element);
            element.expiryYear = ExpiryYear.parse(container, element);
            element.expiryMonth = ExpiryMonth.parse(container, element);
            element.priceDisplayDecimals = PriceDisplayDecimals.parse(container, element);
            element.priceFractionalDenominator = PriceFractionalDenominator.parse(container, element);
            element.priceMinimumTick = PriceMinimumTick.parse(container, element);
            element.lastTradingDate = LastTradingDate.parse(container, element);
            element.priorDaySettlement = PriorDaySettlement.parse(container, element);
            element.currency = Currency.parse(container, element);
            element.lotSizeOrFaceValue = LotSizeOrFaceValue.parse(container, element);
            element.maturityValue = MaturityValue.parse(container, element);
            element.couponRate = CouponRate.parse(container, element);
            element.paymentsPerYear = PaymentsPerYear.parse(container, element);
            element.blockLotSize = BlockLotSize.parse(container, element);
            element.expiryDate = ExpiryDate.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.tradeDate).append("\n");
            sb.append(this.tradeableInstrumentId).append("\n");
            sb.append(this.symbolName).append("\n");
            sb.append(this.longName).append("\n");
            sb.append(this.isin).append("\n");
            sb.append(this.exchange).append("\n");
            sb.append(this.instrument).append("\n");
            sb.append(this.cfiCode).append("\n");
            sb.append(this.expiryYear).append("\n");
            sb.append(this.expiryMonth).append("\n");
            sb.append(this.priceDisplayDecimals).append("\n");
            sb.append(this.priceFractionalDenominator).append("\n");
            sb.append(this.priceMinimumTick).append("\n");
            sb.append(this.lastTradingDate).append("\n");
            sb.append(this.priorDaySettlement).append("\n");
            sb.append(this.currency).append("\n");
            sb.append(this.lotSizeOrFaceValue).append("\n");
            sb.append(this.maturityValue).append("\n");
            sb.append(this.couponRate).append("\n");
            sb.append(this.paymentsPerYear).append("\n");
            sb.append(this.blockLotSize).append("\n");
            sb.append(this.expiryDate).append("\n");
            return sb.toString();
        }
    }

    // Event Code: 1 Byte Ascii String Enum with 1 values
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
    * End Of Business Trade Date Message
    */
    public static class EndOfBusinessTradeDateMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public TradeDate tradeDate;
        public EventCode eventCode;

        // constructor for End Of Business Trade Date Message
        private EndOfBusinessTradeDateMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for End Of Business Trade Date Message
        public static EndOfBusinessTradeDateMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new EndOfBusinessTradeDateMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.tradeDate = TradeDate.parse(container, element);
            element.eventCode = EventCode.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.tradeDate).append("\n");
            sb.append(this.eventCode).append("\n");
            return sb.toString();
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
    * Seconds Message
    */
    public static class SecondsMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Seconds seconds;

        // constructor for Seconds Message
        private SecondsMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Seconds Message
        public static SecondsMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new SecondsMessage(parentElement);

            element.seconds = Seconds.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.seconds).append("\n");
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
        // -- parsing End Of Business Trade Date Message
        if (messageType.enumType == 'S') {
            return EndOfBusinessTradeDateMessage.parse(container, null);
        }
        // -- parsing Future Symbol Directory Message
        if (messageType.enumType == 'f') {
            return FutureSymbolDirectoryMessage.parse(container, null);
        }
        // -- parsing Options Symbol Directory Message
        if (messageType.enumType == 'h') {
            return OptionsSymbolDirectoryMessage.parse(container, null);
        }
        // -- parsing Combination Symbol Directory Message
        if (messageType.enumType == 'M') {
            return CombinationSymbolDirectoryMessage.parse(container, null);
        }
        // -- parsing Bundles Symbol Directory
        if (messageType.enumType == 'm') {
            return BundlesSymbolDirectory.parse(container, null);
        }
        // -- parsing Order Book State Message
        if (messageType.enumType == 'O') {
            return OrderBookStateMessage.parse(container, null);
        }
        // -- parsing Add Order Message
        if (messageType.enumType == 'A') {
            return AddOrderMessage.parse(container, null);
        }
        // -- parsing Order Volume Cancelled Message
        if (messageType.enumType == 'X') {
            return OrderVolumeCancelledMessage.parse(container, null);
        }
        // -- parsing Order Deleted Message
        if (messageType.enumType == 'D') {
            return OrderDeletedMessage.parse(container, null);
        }
        // -- parsing Order Executed Message
        if (messageType.enumType == 'E') {
            return OrderExecutedMessage.parse(container, null);
        }
        // -- parsing Auction Order Executed Message
        if (messageType.enumType == 'C') {
            return AuctionOrderExecutedMessage.parse(container, null);
        }
        // -- parsing Combination Order Executed Message
        if (messageType.enumType == 'e') {
            return CombinationOrderExecutedMessage.parse(container, null);
        }
        // -- parsing Implied Order Added Message
        if (messageType.enumType == 'j') {
            return ImpliedOrderAddedMessage.parse(container, null);
        }
        // -- parsing Implied Order Replaced Message
        if (messageType.enumType == 'l') {
            return ImpliedOrderReplacedMessage.parse(container, null);
        }
        // -- parsing Implied Order Deleted Message
        if (messageType.enumType == 'k') {
            return ImpliedOrderDeletedMessage.parse(container, null);
        }
        // -- parsing Trade Executed Message
        if (messageType.enumType == 'P') {
            return TradeExecutedMessage.parse(container, null);
        }
        // -- parsing Combination Trade Executed Message
        if (messageType.enumType == 'p') {
            return CombinationTradeExecutedMessage.parse(container, null);
        }
        // -- parsing Trade Cancellation Message
        if (messageType.enumType == 'B') {
            return TradeCancellationMessage.parse(container, null);
        }
        // -- parsing Equilibrium Price Message
        if (messageType.enumType == 'Z') {
            return EquilibriumPriceMessage.parse(container, null);
        }
        // -- parsing Open High Low Last Trade Adjustment Message
        if (messageType.enumType == 't') {
            return OpenHighLowLastTradeAdjustmentMessage.parse(container, null);
        }
        // -- parsing Market Settlement Message
        if (messageType.enumType == 'Y') {
            return MarketSettlementMessage.parse(container, null);
        }
        // -- parsing Text Message
        if (messageType.enumType == 'x') {
            return TextMessage.parse(container, null);
        }
        // -- parsing Request For Quote Message
        if (messageType.enumType == 'q') {
            return RequestForQuoteMessage.parse(container, null);
        }
        // -- parsing Anomalous Order Threshold Publish Message
        if (messageType.enumType == 'W') {
            return AnomalousOrderThresholdPublishMessage.parse(container, null);
        }
        // -- parsing Volume And Open Interest Message
        if (messageType.enumType == 'V') {
            return VolumeAndOpenInterestMessage.parse(container, null);
        }

        return null;
    }

    // Message Type: 1 Byte Ascii String Enum with 26 values
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
