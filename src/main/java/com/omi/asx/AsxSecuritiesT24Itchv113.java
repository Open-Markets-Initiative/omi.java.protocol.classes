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
* Java parser for Asx Securities Itch T24 1.13 protocol
* 
* @version 1.0
* @since 04/25/2021 15:26:15
*/

public class AsxSecuritiesT24Itchv113 {

    ///////////////////////////////////////////////////////////////////////
    // Enum Values
    ///////////////////////////////////////////////////////////////////////

    /**
    * Activated Values
    */
    public enum ENUM_ACTIVATED {
        E_Y('Y', "Yes"),
        E_N('N', "No");

        public final Character enumType;
        public final String enumValue;

        ENUM_ACTIVATED(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_ACTIVATED> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_ACTIVATED s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_ACTIVATED valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Contract Type Values
    */
    public enum ENUM_CONTRACT_TYPE {
        E_F('F', "Cfut"),
        E_O('O', "Copta"),
        E_E('E', "Eopta"),
        E_N('N', "Oopt"),
        E_S('S', "Csprd"),
        E_A('A', "Sprd"),
        E_D('D', "Sfut");

        public final Character enumType;
        public final String enumValue;

        ENUM_CONTRACT_TYPE(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_CONTRACT_TYPE> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_CONTRACT_TYPE s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_CONTRACT_TYPE valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Event Code Values
    */
    public enum ENUM_EVENT_CODE {
        E_O('O', "Open"),
        E_S('S', "Start"),
        E_C('C', "End"),
        E_P('P', "Paused"),
        E_R('R', "Resumed");

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
    * Financial Type Values
    */
    public enum ENUM_FINANCIAL_TYPE {
        E_C('C', "Commodity"),
        E_D('D', "Cfd"),
        E_E('E', "Equity"),
        E_X('X', "Government Bond"),
        E_B('B', "Bank Bill");

        public final Character enumType;
        public final String enumValue;

        ENUM_FINANCIAL_TYPE(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_FINANCIAL_TYPE> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_FINANCIAL_TYPE s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_FINANCIAL_TYPE valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Message Type Values
    */
    public enum ENUM_MESSAGE_TYPE {
        E_T('T', "Time Message"),
        E_S('S', "System Event"),
        E_f('f', "Future Symbol Directory"),
        E_g('g', "Spread Symbol Directory"),
        E_h('h', "Option Symbol Directory"),
        E_O('O', "Order Book State"),
        E_A('A', "Order Added"),
        E_U('U', "Order Replaced"),
        E_X('X', "Order Volume Cancelled"),
        E_D('D', "Order Deleted"),
        E_j('j', "Implied Order Added"),
        E_l('l', "Implied Order Replaced"),
        E_k('k', "Implied Order Deleted"),
        E_m('m', "Custom Market Order Added"),
        E_n('n', "Custom Market Order Replaced"),
        E_r('r', "Custom Market Order Deleted"),
        E_E('E', "Order Executed"),
        E_C('C', "Order Executed With Price"),
        E_e('e', "Spread Executed"),
        E_P('P', "Trade Spread Execution Chain"),
        E_u('u', "Custom Market Executed"),
        E_p('p', "Custom Market Trade"),
        E_B('B', "Trade Cancellation"),
        E_Z('Z', "Equilibrium Price Auction Info"),
        E_t('t', "Open High Low Last Trade Adjustment"),
        E_Y('Y', "Market Settlement"),
        E_x('x', "Ad Hoc Text"),
        E_q('q', "Request For Quote"),
        E_W('W', "Anomalous Order Threshold Publish"),
        E_V('V', "Volume And Open Interest");

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
    * Printable Values
    */
    public enum ENUM_PRINTABLE {
        E_Y('Y', "Yes"),
        E_N('N', "No");

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
    * Trade Type Values
    */
    public enum ENUM_TRADE_TYPE {
        E_T('T', "Normal"),
        E_t('t', "Crossing Normal"),
        E_W('W', "Sweeping"),
        E_w('w', "Crossing Sweeping"),
        E_L('L', "Levelling"),
        E_l('l', "Crossing Levelling"),
        E_S('S', "Spread To Underlying"),
        E_s('s', "Crossing Spread To Underlying"),
        E_R('R', "Intra Spread"),
        E_r('r', "Crossing Intra Spread"),
        E_Q('Q', "Inter Spread"),
        E_q('q', "Crossing Inter Spread"),
        E_U('U', "Custom"),
        E_u('u', "Crossing Custom");

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

    /**
    * Trading Status Values
    */
    public enum ENUM_TRADING_STATUS {
        E_p('p', "Pending"),
        E_H('H', "Halted"),
        E_P('P', "Pre Open"),
        E_C('C', "Closed"),
        E_l('l', "Levelling"),
        E_L('L', "Locked"),
        E_O('O', "Opened"),
        E_U('U', "Unavailable"),
        E_d('d', "Pre Price Discovery"),
        E_I('I', "Inactive"),
        E_D('D', "Price Discovery"),
        E_A('A', "Activated"),
        E_R('R', "Regulatory Halt");

        public final Character enumType;
        public final String enumValue;

        ENUM_TRADING_STATUS(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_TRADING_STATUS> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_TRADING_STATUS s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_TRADING_STATUS valueOf(Character s)  {
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

    // Open Interest: 4 Byte Unsigned Fixed Width Integer
    public static class OpenInterest implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public OpenInterest(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static OpenInterest parse(Container container, IBinaryProtocolElement element) {
            return new OpenInterest(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "openInterest = "+ this.value;
        }
    }

    // Cumulative Volume: 4 Byte Unsigned Fixed Width Integer
    public static class CumulativeVolume implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public CumulativeVolume(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static CumulativeVolume parse(Container container, IBinaryProtocolElement element) {
            return new CumulativeVolume(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "cumulativeVolume = "+ this.value;
        }
    }

    // Contract Number: 4 Byte Unsigned Fixed Width Integer
    public static class ContractNumber implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public ContractNumber(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static ContractNumber parse(Container container, IBinaryProtocolElement element) {
            return new ContractNumber(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "contractNumber = "+ this.value;
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
    * Volume And Open Interest
    */
    public static class VolumeAndOpenInterest implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public TradeDate tradeDate;
        public ContractNumber contractNumber;
        public CumulativeVolume cumulativeVolume;
        public OpenInterest openInterest;
        public VoiTradeDate voiTradeDate;

        // constructor for Volume And Open Interest
        private VolumeAndOpenInterest(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Volume And Open Interest
        public static VolumeAndOpenInterest parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new VolumeAndOpenInterest(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.tradeDate = TradeDate.parse(container, element);
            element.contractNumber = ContractNumber.parse(container, element);
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
            sb.append(this.contractNumber).append("\n");
            sb.append(this.cumulativeVolume).append("\n");
            sb.append(this.openInterest).append("\n");
            sb.append(this.voiTradeDate).append("\n");
            return sb.toString();
        }
    }

    // Etr Lower Price: 4 Byte Signed Fixed Width Integer
    public static class EtrLowerPrice implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public EtrLowerPrice(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static EtrLowerPrice parse(Container container, IBinaryProtocolElement element) {
            return new EtrLowerPrice(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "etrLowerPrice = "+ this.value;
        }
    }

    // Etr Upper Price: 4 Byte Signed Fixed Width Integer
    public static class EtrUpperPrice implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public EtrUpperPrice(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static EtrUpperPrice parse(Container container, IBinaryProtocolElement element) {
            return new EtrUpperPrice(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "etrUpperPrice = "+ this.value;
        }
    }

    // Etr Price: 4 Byte Signed Fixed Width Integer
    public static class EtrPrice implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public EtrPrice(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static EtrPrice parse(Container container, IBinaryProtocolElement element) {
            return new EtrPrice(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "etrPrice = "+ this.value;
        }
    }

    // Aot Lower Price: 4 Byte Signed Fixed Width Integer
    public static class AotLowerPrice implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public AotLowerPrice(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static AotLowerPrice parse(Container container, IBinaryProtocolElement element) {
            return new AotLowerPrice(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "aotLowerPrice = "+ this.value;
        }
    }

    // Aot Upper Price: 4 Byte Signed Fixed Width Integer
    public static class AotUpperPrice implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public AotUpperPrice(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static AotUpperPrice parse(Container container, IBinaryProtocolElement element) {
            return new AotUpperPrice(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "aotUpperPrice = "+ this.value;
        }
    }

    // Aot Price: 4 Byte Signed Fixed Width Integer
    public static class AotPrice implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public AotPrice(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static AotPrice parse(Container container, IBinaryProtocolElement element) {
            return new AotPrice(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "aotPrice = "+ this.value;
        }
    }

    /**
    * Anomalous Order Threshold Publish
    */
    public static class AnomalousOrderThresholdPublish implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public TradeDate tradeDate;
        public ContractNumber contractNumber;
        public AotPrice aotPrice;
        public AotUpperPrice aotUpperPrice;
        public AotLowerPrice aotLowerPrice;
        public EtrPrice etrPrice;
        public EtrUpperPrice etrUpperPrice;
        public EtrLowerPrice etrLowerPrice;

        // constructor for Anomalous Order Threshold Publish
        private AnomalousOrderThresholdPublish(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Anomalous Order Threshold Publish
        public static AnomalousOrderThresholdPublish parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new AnomalousOrderThresholdPublish(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.tradeDate = TradeDate.parse(container, element);
            element.contractNumber = ContractNumber.parse(container, element);
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
            sb.append(this.contractNumber).append("\n");
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

    /**
    * Request For Quote
    */
    public static class RequestForQuote implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public TradeDate tradeDate;
        public ContractNumber contractNumber;
        public Price price;
        public Quantity quantity;

        // constructor for Request For Quote
        private RequestForQuote(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Request For Quote
        public static RequestForQuote parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new RequestForQuote(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.tradeDate = TradeDate.parse(container, element);
            element.contractNumber = ContractNumber.parse(container, element);
            element.price = Price.parse(container, element);
            element.quantity = Quantity.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.tradeDate).append("\n");
            sb.append(this.contractNumber).append("\n");
            sb.append(this.price).append("\n");
            sb.append(this.quantity).append("\n");
            return sb.toString();
        }
    }

    // Text Message: 100 Byte Ascii String
    public static class TextMessage implements IBinaryProtocolElement {
        public static final int LENGTH = 100;
        public final IBinaryProtocolElement parent;
        public final String value;

        public TextMessage(String value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static TextMessage parse(Container container, IBinaryProtocolElement element) {
            return new TextMessage(BigEndianUtils.toString(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "textMessage = "+ this.value;
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
    * Ad Hoc Text
    */
    public static class AdHocText implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public TradeDate tradeDate;
        public SourceId sourceId;
        public TextMessage textMessage;

        // constructor for Ad Hoc Text
        private AdHocText(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Ad Hoc Text
        public static AdHocText parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new AdHocText(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.tradeDate = TradeDate.parse(container, element);
            element.sourceId = SourceId.parse(container, element);
            element.textMessage = TextMessage.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.tradeDate).append("\n");
            sb.append(this.sourceId).append("\n");
            sb.append(this.textMessage).append("\n");
            return sb.toString();
        }
    }

    // Settlement Type: 1 Byte Ascii String
    public static class SettlementType implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Character value;

        public SettlementType(Character value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static SettlementType parse(Container container, IBinaryProtocolElement element) {
            return new SettlementType(BigEndianUtils.toCharacter(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "settlementType = "+ this.value;
        }
    }

    // Volatility: 4 Byte Unsigned Fixed Width Integer
    public static class Volatility implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public Volatility(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Volatility parse(Container container, IBinaryProtocolElement element) {
            return new Volatility(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "volatility = "+ this.value;
        }
    }

    // Settlement Price: 4 Byte Signed Fixed Width Integer
    public static class SettlementPrice implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public SettlementPrice(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static SettlementPrice parse(Container container, IBinaryProtocolElement element) {
            return new SettlementPrice(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "settlementPrice = "+ this.value;
        }
    }

    /**
    * Market Settlement
    */
    public static class MarketSettlement implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public TradeDate tradeDate;
        public ContractNumber contractNumber;
        public SettlementPrice settlementPrice;
        public Volatility volatility;
        public SettlementType settlementType;

        // constructor for Market Settlement
        private MarketSettlement(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Market Settlement
        public static MarketSettlement parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new MarketSettlement(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.tradeDate = TradeDate.parse(container, element);
            element.contractNumber = ContractNumber.parse(container, element);
            element.settlementPrice = SettlementPrice.parse(container, element);
            element.volatility = Volatility.parse(container, element);
            element.settlementType = SettlementType.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.tradeDate).append("\n");
            sb.append(this.contractNumber).append("\n");
            sb.append(this.settlementPrice).append("\n");
            sb.append(this.volatility).append("\n");
            sb.append(this.settlementType).append("\n");
            return sb.toString();
        }
    }

    // Opening Trade Price: 1 Bit
    public static class OpeningTradePrice implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public OpeningTradePrice(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static OpeningTradePrice parse(Container container, IBinaryProtocolElement element) {
            return new OpeningTradePrice(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "openingTradePrice = "+ this.value;
        }
    }

    // Highest Traded Price: 1 Bit
    public static class HighestTradedPrice implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public HighestTradedPrice(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static HighestTradedPrice parse(Container container, IBinaryProtocolElement element) {
            return new HighestTradedPrice(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "highestTradedPrice = "+ this.value;
        }
    }

    // Lowest Traded Price: 1 Bit
    public static class LowestTradedPrice implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public LowestTradedPrice(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static LowestTradedPrice parse(Container container, IBinaryProtocolElement element) {
            return new LowestTradedPrice(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "lowestTradedPrice = "+ this.value;
        }
    }

    // Total Traded Volume and Total Trades: 1 Bit
    public static class TotalTradedVolumeAndTotalTrades implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public TotalTradedVolumeAndTotalTrades(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static TotalTradedVolumeAndTotalTrades parse(Container container, IBinaryProtocolElement element) {
            return new TotalTradedVolumeAndTotalTrades(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "totalTradedVolumeAndTotalTrades = "+ this.value;
        }
    }

    // Last Traded Price: 1 Bit
    public static class LastTradedPrice implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public LastTradedPrice(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static LastTradedPrice parse(Container container, IBinaryProtocolElement element) {
            return new LastTradedPrice(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "lastTradedPrice = "+ this.value;
        }
    }

    // Last Traded Volume: 1 Bit
    public static class LastTradedVolume implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public LastTradedVolume(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static LastTradedVolume parse(Container container, IBinaryProtocolElement element) {
            return new LastTradedVolume(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "lastTradedVolume = "+ this.value;
        }
    }

    // Reserved: 2 Bit
    public static class Reserved implements IBinaryProtocolElement {
        public static final int LENGTH = 2;
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

    // bit values for MarketUpdates
    public static class MarketUpdatesBits {
        public static final byte RESERVED                              = (byte)(1 << 6);   
        public static final byte LAST_TRADED_VOLUME                    = (byte)(1 << 5);   
        public static final byte LAST_TRADED_PRICE                     = (byte)(1 << 4);   
        public static final byte TOTAL_TRADED_VOLUME_AND_TOTAL_TRADES  = (byte)(1 << 3);   
        public static final byte LOWEST_TRADED_PRICE                   = (byte)(1 << 2);   
        public static final byte HIGHEST_TRADED_PRICE                  = (byte)(1 << 1);   
        public static final byte OPENING_TRADE_PRICE                   = (byte)(1 << 0);   
    }

    // Market Updates: 1 Byte Unsigned Fixed Width Integer: Struct of 7 fields
    public static class MarketUpdates implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement _parent;

        // bits
        public final boolean  _reserved;                            //  0 
        public final boolean  _lastTradedVolume;                    //  1 
        public final boolean  _lastTradedPrice;                     //  2 
        public final boolean  _totalTradedVolumeAndTotalTrades;     //  3 
        public final boolean  _lowestTradedPrice;                   //  4 
        public final boolean  _highestTradedPrice;                  //  5 
        public final boolean  _openingTradePrice;                   //  6 

        public MarketUpdates (Byte value, IBinaryProtocolElement element) {
            this._parent = element;

            this._reserved = (value & MarketUpdatesBits.RESERVED) != 0;
            this._lastTradedVolume = (value & MarketUpdatesBits.LAST_TRADED_VOLUME) != 0;
            this._lastTradedPrice = (value & MarketUpdatesBits.LAST_TRADED_PRICE) != 0;
            this._totalTradedVolumeAndTotalTrades = (value & MarketUpdatesBits.TOTAL_TRADED_VOLUME_AND_TOTAL_TRADES) != 0;
            this._lowestTradedPrice = (value & MarketUpdatesBits.LOWEST_TRADED_PRICE) != 0;
            this._highestTradedPrice = (value & MarketUpdatesBits.HIGHEST_TRADED_PRICE) != 0;
            this._openingTradePrice = (value & MarketUpdatesBits.OPENING_TRADE_PRICE) != 0;
        }

        public static MarketUpdates parse(Container container, IBinaryProtocolElement element) {
            return new MarketUpdates(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("MarketUpdates:\n");
            sb.append(StringUtils.repeat('.', 0) + (this._reserved?"1":"0") + StringUtils.repeat('.', 6) +" // Reserved\n");
            sb.append(StringUtils.repeat('.', 1) + (this._lastTradedVolume?"1":"0") + StringUtils.repeat('.', 5) +" // Last Traded Volume\n");
            sb.append(StringUtils.repeat('.', 2) + (this._lastTradedPrice?"1":"0") + StringUtils.repeat('.', 4) +" // Last Traded Price\n");
            sb.append(StringUtils.repeat('.', 3) + (this._totalTradedVolumeAndTotalTrades?"1":"0") + StringUtils.repeat('.', 3) +" // Total Traded Volume and Total Trades\n");
            sb.append(StringUtils.repeat('.', 4) + (this._lowestTradedPrice?"1":"0") + StringUtils.repeat('.', 2) +" // Lowest Traded Price\n");
            sb.append(StringUtils.repeat('.', 5) + (this._highestTradedPrice?"1":"0") + StringUtils.repeat('.', 1) +" // Highest Traded Price\n");
            sb.append(StringUtils.repeat('.', 6) + (this._openingTradePrice?"1":"0") + StringUtils.repeat('.', 0) +" // Opening Trade Price\n");
            return sb.toString();
        }
    }

    // Total Trades: 4 Byte Unsigned Fixed Width Integer
    public static class TotalTrades implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public TotalTrades(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static TotalTrades parse(Container container, IBinaryProtocolElement element) {
            return new TotalTrades(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "totalTrades = "+ this.value;
        }
    }

    // Total Traded Volume: 4 Byte Unsigned Fixed Width Integer
    public static class TotalTradedVolume implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public TotalTradedVolume(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static TotalTradedVolume parse(Container container, IBinaryProtocolElement element) {
            return new TotalTradedVolume(BigEndianUtils.toInteger(container, LENGTH), element);
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

    // Last Trade: 4 Byte Signed Fixed Width Integer
    public static class LastTrade implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public LastTrade(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static LastTrade parse(Container container, IBinaryProtocolElement element) {
            return new LastTrade(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "lastTrade = "+ this.value;
        }
    }

    // Lowest Trade: 4 Byte Signed Fixed Width Integer
    public static class LowestTrade implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public LowestTrade(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static LowestTrade parse(Container container, IBinaryProtocolElement element) {
            return new LowestTrade(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "lowestTrade = "+ this.value;
        }
    }

    // Highest Trade: 4 Byte Signed Fixed Width Integer
    public static class HighestTrade implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public HighestTrade(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static HighestTrade parse(Container container, IBinaryProtocolElement element) {
            return new HighestTrade(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "highestTrade = "+ this.value;
        }
    }

    // Opening Trade: 4 Byte Signed Fixed Width Integer
    public static class OpeningTrade implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public OpeningTrade(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static OpeningTrade parse(Container container, IBinaryProtocolElement element) {
            return new OpeningTrade(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "openingTrade = "+ this.value;
        }
    }

    /**
    * Open High Low Last Trade Adjustment
    */
    public static class OpenHighLowLastTradeAdjustment implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public TradeDate tradeDate;
        public ContractNumber contractNumber;
        public OpeningTrade openingTrade;
        public HighestTrade highestTrade;
        public LowestTrade lowestTrade;
        public LastTrade lastTrade;
        public LastVolume lastVolume;
        public TotalTradedVolume totalTradedVolume;
        public TotalTrades totalTrades;
        public MarketUpdates marketUpdates;

        // constructor for Open High Low Last Trade Adjustment
        private OpenHighLowLastTradeAdjustment(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Open High Low Last Trade Adjustment
        public static OpenHighLowLastTradeAdjustment parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new OpenHighLowLastTradeAdjustment(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.tradeDate = TradeDate.parse(container, element);
            element.contractNumber = ContractNumber.parse(container, element);
            element.openingTrade = OpeningTrade.parse(container, element);
            element.highestTrade = HighestTrade.parse(container, element);
            element.lowestTrade = LowestTrade.parse(container, element);
            element.lastTrade = LastTrade.parse(container, element);
            element.lastVolume = LastVolume.parse(container, element);
            element.totalTradedVolume = TotalTradedVolume.parse(container, element);
            element.totalTrades = TotalTrades.parse(container, element);
            element.marketUpdates = MarketUpdates.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.tradeDate).append("\n");
            sb.append(this.contractNumber).append("\n");
            sb.append(this.openingTrade).append("\n");
            sb.append(this.highestTrade).append("\n");
            sb.append(this.lowestTrade).append("\n");
            sb.append(this.lastTrade).append("\n");
            sb.append(this.lastVolume).append("\n");
            sb.append(this.totalTradedVolume).append("\n");
            sb.append(this.totalTrades).append("\n");
            sb.append(this.marketUpdates).append("\n");
            return sb.toString();
        }
    }

    // Best Ask Quantity: 4 Byte Unsigned Fixed Width Integer
    public static class BestAskQuantity implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public BestAskQuantity(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static BestAskQuantity parse(Container container, IBinaryProtocolElement element) {
            return new BestAskQuantity(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "bestAskQuantity = "+ this.value;
        }
    }

    // Best Bid Quantity: 4 Byte Unsigned Fixed Width Integer
    public static class BestBidQuantity implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public BestBidQuantity(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static BestBidQuantity parse(Container container, IBinaryProtocolElement element) {
            return new BestBidQuantity(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "bestBidQuantity = "+ this.value;
        }
    }

    // Best Ask Price: 4 Byte Signed Fixed Width Integer
    public static class BestAskPrice implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public BestAskPrice(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static BestAskPrice parse(Container container, IBinaryProtocolElement element) {
            return new BestAskPrice(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "bestAskPrice = "+ this.value;
        }
    }

    // Best Bid Price: 4 Byte Signed Fixed Width Integer
    public static class BestBidPrice implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public BestBidPrice(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static BestBidPrice parse(Container container, IBinaryProtocolElement element) {
            return new BestBidPrice(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "bestBidPrice = "+ this.value;
        }
    }

    // Equilibrium Price: 4 Byte Signed Fixed Width Integer
    public static class EquilibriumPrice implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public EquilibriumPrice(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static EquilibriumPrice parse(Container container, IBinaryProtocolElement element) {
            return new EquilibriumPrice(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "equilibriumPrice = "+ this.value;
        }
    }

    /**
    * Equilibrium Price Auction Info
    */
    public static class EquilibriumPriceAuctionInfo implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public TradeDate tradeDate;
        public ContractNumber contractNumber;
        public EquilibriumPrice equilibriumPrice;
        public BestBidPrice bestBidPrice;
        public BestAskPrice bestAskPrice;
        public BestBidQuantity bestBidQuantity;
        public BestAskQuantity bestAskQuantity;

        // constructor for Equilibrium Price Auction Info
        private EquilibriumPriceAuctionInfo(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Equilibrium Price Auction Info
        public static EquilibriumPriceAuctionInfo parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new EquilibriumPriceAuctionInfo(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.tradeDate = TradeDate.parse(container, element);
            element.contractNumber = ContractNumber.parse(container, element);
            element.equilibriumPrice = EquilibriumPrice.parse(container, element);
            element.bestBidPrice = BestBidPrice.parse(container, element);
            element.bestAskPrice = BestAskPrice.parse(container, element);
            element.bestBidQuantity = BestBidQuantity.parse(container, element);
            element.bestAskQuantity = BestAskQuantity.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.tradeDate).append("\n");
            sb.append(this.contractNumber).append("\n");
            sb.append(this.equilibriumPrice).append("\n");
            sb.append(this.bestBidPrice).append("\n");
            sb.append(this.bestAskPrice).append("\n");
            sb.append(this.bestBidQuantity).append("\n");
            sb.append(this.bestAskQuantity).append("\n");
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

    /**
    * Trade Cancellation
    */
    public static class TradeCancellation implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public TradeDate tradeDate;
        public MatchNumber matchNumber;

        // constructor for Trade Cancellation
        private TradeCancellation(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Trade Cancellation
        public static TradeCancellation parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new TradeCancellation(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.tradeDate = TradeDate.parse(container, element);
            element.matchNumber = MatchNumber.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.tradeDate).append("\n");
            sb.append(this.matchNumber).append("\n");
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

    // Trade Side Of Non Custom Order: 1 Byte Ascii String
    public static class TradeSideOfNonCustomOrder implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Character value;

        public TradeSideOfNonCustomOrder(Character value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static TradeSideOfNonCustomOrder parse(Container container, IBinaryProtocolElement element) {
            return new TradeSideOfNonCustomOrder(BigEndianUtils.toCharacter(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "tradeSideOfNonCustomOrder = "+ this.value;
        }
    }

    // Traded Contract Number: 4 Byte Unsigned Fixed Width Integer
    public static class TradedContractNumber implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public TradedContractNumber(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static TradedContractNumber parse(Container container, IBinaryProtocolElement element) {
            return new TradedContractNumber(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "tradedContractNumber = "+ this.value;
        }
    }

    // Trade Price: 4 Byte Signed Fixed Width Integer
    public static class TradePrice implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public TradePrice(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static TradePrice parse(Container container, IBinaryProtocolElement element) {
            return new TradePrice(BigEndianUtils.toInteger(container, LENGTH), element);
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

    // Trade Type: 1 Byte Ascii String Enum with 14 values
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

    // Custom Market Quantity Remaining: 4 Byte Unsigned Fixed Width Integer
    public static class CustomMarketQuantityRemaining implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public CustomMarketQuantityRemaining(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static CustomMarketQuantityRemaining parse(Container container, IBinaryProtocolElement element) {
            return new CustomMarketQuantityRemaining(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "customMarketQuantityRemaining = "+ this.value;
        }
    }

    // Custom Market Order Number: 8 Byte Unsigned Fixed Width Integer
    public static class CustomMarketOrderNumber implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public CustomMarketOrderNumber(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static CustomMarketOrderNumber parse(Container container, IBinaryProtocolElement element) {
            return new CustomMarketOrderNumber(BigEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "customMarketOrderNumber = "+ this.value;
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

    // Order Number: 8 Byte Unsigned Fixed Width Integer
    public static class OrderNumber implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public OrderNumber(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static OrderNumber parse(Container container, IBinaryProtocolElement element) {
            return new OrderNumber(BigEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "orderNumber = "+ this.value;
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
    * Custom Market Trade
    */
    public static class CustomMarketTrade implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public TradeDate tradeDate;
        public ContractNumber contractNumber;
        public Side side;
        public OrderNumber orderNumber;
        public QuantityRemaining quantityRemaining;
        public CustomMarketOrderNumber customMarketOrderNumber;
        public CustomMarketQuantityRemaining customMarketQuantityRemaining;
        public TradeType tradeType;
        public MatchNumber matchNumber;
        public ExecutedQuantity executedQuantity;
        public TradePrice tradePrice;
        public TradedContractNumber tradedContractNumber;
        public TradeSideOfNonCustomOrder tradeSideOfNonCustomOrder;
        public Printable printable;

        // constructor for Custom Market Trade
        private CustomMarketTrade(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Custom Market Trade
        public static CustomMarketTrade parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new CustomMarketTrade(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.tradeDate = TradeDate.parse(container, element);
            element.contractNumber = ContractNumber.parse(container, element);
            element.side = Side.parse(container, element);
            element.orderNumber = OrderNumber.parse(container, element);
            element.quantityRemaining = QuantityRemaining.parse(container, element);
            element.customMarketOrderNumber = CustomMarketOrderNumber.parse(container, element);
            element.customMarketQuantityRemaining = CustomMarketQuantityRemaining.parse(container, element);
            element.tradeType = TradeType.parse(container, element);
            element.matchNumber = MatchNumber.parse(container, element);
            element.executedQuantity = ExecutedQuantity.parse(container, element);
            element.tradePrice = TradePrice.parse(container, element);
            element.tradedContractNumber = TradedContractNumber.parse(container, element);
            element.tradeSideOfNonCustomOrder = TradeSideOfNonCustomOrder.parse(container, element);
            element.printable = Printable.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.tradeDate).append("\n");
            sb.append(this.contractNumber).append("\n");
            sb.append(this.side).append("\n");
            sb.append(this.orderNumber).append("\n");
            sb.append(this.quantityRemaining).append("\n");
            sb.append(this.customMarketOrderNumber).append("\n");
            sb.append(this.customMarketQuantityRemaining).append("\n");
            sb.append(this.tradeType).append("\n");
            sb.append(this.matchNumber).append("\n");
            sb.append(this.executedQuantity).append("\n");
            sb.append(this.tradePrice).append("\n");
            sb.append(this.tradedContractNumber).append("\n");
            sb.append(this.tradeSideOfNonCustomOrder).append("\n");
            sb.append(this.printable).append("\n");
            return sb.toString();
        }
    }

    // Trade Side Of Leg: 1 Byte Ascii String
    public static class TradeSideOfLeg implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Character value;

        public TradeSideOfLeg(Character value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static TradeSideOfLeg parse(Container container, IBinaryProtocolElement element) {
            return new TradeSideOfLeg(BigEndianUtils.toCharacter(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "tradeSideOfLeg = "+ this.value;
        }
    }

    /**
    * Custom Market Executed
    */
    public static class CustomMarketExecuted implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public TradeDate tradeDate;
        public OrderNumber orderNumber;
        public QuantityRemaining quantityRemaining;
        public TradeType tradeType;
        public MatchNumber matchNumber;
        public ExecutedQuantity executedQuantity;
        public TradePrice tradePrice;
        public TradedContractNumber tradedContractNumber;
        public TradeSideOfLeg tradeSideOfLeg;
        public Printable printable;

        // constructor for Custom Market Executed
        private CustomMarketExecuted(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Custom Market Executed
        public static CustomMarketExecuted parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new CustomMarketExecuted(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.tradeDate = TradeDate.parse(container, element);
            element.orderNumber = OrderNumber.parse(container, element);
            element.quantityRemaining = QuantityRemaining.parse(container, element);
            element.tradeType = TradeType.parse(container, element);
            element.matchNumber = MatchNumber.parse(container, element);
            element.executedQuantity = ExecutedQuantity.parse(container, element);
            element.tradePrice = TradePrice.parse(container, element);
            element.tradedContractNumber = TradedContractNumber.parse(container, element);
            element.tradeSideOfLeg = TradeSideOfLeg.parse(container, element);
            element.printable = Printable.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.tradeDate).append("\n");
            sb.append(this.orderNumber).append("\n");
            sb.append(this.quantityRemaining).append("\n");
            sb.append(this.tradeType).append("\n");
            sb.append(this.matchNumber).append("\n");
            sb.append(this.executedQuantity).append("\n");
            sb.append(this.tradePrice).append("\n");
            sb.append(this.tradedContractNumber).append("\n");
            sb.append(this.tradeSideOfLeg).append("\n");
            sb.append(this.printable).append("\n");
            return sb.toString();
        }
    }

    // Spread Trade Price: 4 Byte Signed Fixed Width Integer
    public static class SpreadTradePrice implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public SpreadTradePrice(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static SpreadTradePrice parse(Container container, IBinaryProtocolElement element) {
            return new SpreadTradePrice(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "spreadTradePrice = "+ this.value;
        }
    }

    // Seller Quantity Remaining: 4 Byte Unsigned Fixed Width Integer
    public static class SellerQuantityRemaining implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public SellerQuantityRemaining(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static SellerQuantityRemaining parse(Container container, IBinaryProtocolElement element) {
            return new SellerQuantityRemaining(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "sellerQuantityRemaining = "+ this.value;
        }
    }

    // Selling Order Number: 8 Byte Unsigned Fixed Width Integer
    public static class SellingOrderNumber implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public SellingOrderNumber(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static SellingOrderNumber parse(Container container, IBinaryProtocolElement element) {
            return new SellingOrderNumber(BigEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "sellingOrderNumber = "+ this.value;
        }
    }

    // Side Of Seller: 1 Byte Ascii String
    public static class SideOfSeller implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Character value;

        public SideOfSeller(Character value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static SideOfSeller parse(Container container, IBinaryProtocolElement element) {
            return new SideOfSeller(BigEndianUtils.toCharacter(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "sideOfSeller = "+ this.value;
        }
    }

    // Seller Contract Number: 4 Byte Unsigned Fixed Width Integer
    public static class SellerContractNumber implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public SellerContractNumber(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static SellerContractNumber parse(Container container, IBinaryProtocolElement element) {
            return new SellerContractNumber(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "sellerContractNumber = "+ this.value;
        }
    }

    // Buyer Quantity Remaining: 4 Byte Unsigned Fixed Width Integer
    public static class BuyerQuantityRemaining implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public BuyerQuantityRemaining(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static BuyerQuantityRemaining parse(Container container, IBinaryProtocolElement element) {
            return new BuyerQuantityRemaining(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "buyerQuantityRemaining = "+ this.value;
        }
    }

    // Buyer Order Number: 8 Byte Unsigned Fixed Width Integer
    public static class BuyerOrderNumber implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public BuyerOrderNumber(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static BuyerOrderNumber parse(Container container, IBinaryProtocolElement element) {
            return new BuyerOrderNumber(BigEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "buyerOrderNumber = "+ this.value;
        }
    }

    // Side Of Buyer: 1 Byte Ascii String
    public static class SideOfBuyer implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Character value;

        public SideOfBuyer(Character value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static SideOfBuyer parse(Container container, IBinaryProtocolElement element) {
            return new SideOfBuyer(BigEndianUtils.toCharacter(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "sideOfBuyer = "+ this.value;
        }
    }

    // Buyer: 4 Byte Unsigned Fixed Width Integer
    public static class Buyer implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public Buyer(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Buyer parse(Container container, IBinaryProtocolElement element) {
            return new Buyer(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "buyer = "+ this.value;
        }
    }

    /**
    * Trade Spread Execution Chain
    */
    public static class TradeSpreadExecutionChain implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public TradeDate tradeDate;
        public Buyer buyer;
        public SideOfBuyer sideOfBuyer;
        public BuyerOrderNumber buyerOrderNumber;
        public BuyerQuantityRemaining buyerQuantityRemaining;
        public SellerContractNumber sellerContractNumber;
        public SideOfSeller sideOfSeller;
        public SellingOrderNumber sellingOrderNumber;
        public SellerQuantityRemaining sellerQuantityRemaining;
        public TradeType tradeType;
        public MatchNumber matchNumber;
        public ExecutedQuantity executedQuantity;
        public TradePrice tradePrice;
        public TradedContractNumber tradedContractNumber;
        public SpreadTradePrice spreadTradePrice;
        public Printable printable;

        // constructor for Trade Spread Execution Chain
        private TradeSpreadExecutionChain(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Trade Spread Execution Chain
        public static TradeSpreadExecutionChain parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new TradeSpreadExecutionChain(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.tradeDate = TradeDate.parse(container, element);
            element.buyer = Buyer.parse(container, element);
            element.sideOfBuyer = SideOfBuyer.parse(container, element);
            element.buyerOrderNumber = BuyerOrderNumber.parse(container, element);
            element.buyerQuantityRemaining = BuyerQuantityRemaining.parse(container, element);
            element.sellerContractNumber = SellerContractNumber.parse(container, element);
            element.sideOfSeller = SideOfSeller.parse(container, element);
            element.sellingOrderNumber = SellingOrderNumber.parse(container, element);
            element.sellerQuantityRemaining = SellerQuantityRemaining.parse(container, element);
            element.tradeType = TradeType.parse(container, element);
            element.matchNumber = MatchNumber.parse(container, element);
            element.executedQuantity = ExecutedQuantity.parse(container, element);
            element.tradePrice = TradePrice.parse(container, element);
            element.tradedContractNumber = TradedContractNumber.parse(container, element);
            element.spreadTradePrice = SpreadTradePrice.parse(container, element);
            element.printable = Printable.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.tradeDate).append("\n");
            sb.append(this.buyer).append("\n");
            sb.append(this.sideOfBuyer).append("\n");
            sb.append(this.buyerOrderNumber).append("\n");
            sb.append(this.buyerQuantityRemaining).append("\n");
            sb.append(this.sellerContractNumber).append("\n");
            sb.append(this.sideOfSeller).append("\n");
            sb.append(this.sellingOrderNumber).append("\n");
            sb.append(this.sellerQuantityRemaining).append("\n");
            sb.append(this.tradeType).append("\n");
            sb.append(this.matchNumber).append("\n");
            sb.append(this.executedQuantity).append("\n");
            sb.append(this.tradePrice).append("\n");
            sb.append(this.tradedContractNumber).append("\n");
            sb.append(this.spreadTradePrice).append("\n");
            sb.append(this.printable).append("\n");
            return sb.toString();
        }
    }

    /**
    * Spread Executed
    */
    public static class SpreadExecuted implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public TradeDate tradeDate;
        public ContractNumber contractNumber;
        public Side side;
        public OrderNumber orderNumber;
        public QuantityRemaining quantityRemaining;
        public TradeType tradeType;
        public MatchNumber matchNumber;
        public ExecutedQuantity executedQuantity;
        public TradePrice tradePrice;
        public TradedContractNumber tradedContractNumber;
        public SpreadTradePrice spreadTradePrice;
        public TradeSideOfLeg tradeSideOfLeg;
        public Printable printable;

        // constructor for Spread Executed
        private SpreadExecuted(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Spread Executed
        public static SpreadExecuted parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new SpreadExecuted(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.tradeDate = TradeDate.parse(container, element);
            element.contractNumber = ContractNumber.parse(container, element);
            element.side = Side.parse(container, element);
            element.orderNumber = OrderNumber.parse(container, element);
            element.quantityRemaining = QuantityRemaining.parse(container, element);
            element.tradeType = TradeType.parse(container, element);
            element.matchNumber = MatchNumber.parse(container, element);
            element.executedQuantity = ExecutedQuantity.parse(container, element);
            element.tradePrice = TradePrice.parse(container, element);
            element.tradedContractNumber = TradedContractNumber.parse(container, element);
            element.spreadTradePrice = SpreadTradePrice.parse(container, element);
            element.tradeSideOfLeg = TradeSideOfLeg.parse(container, element);
            element.printable = Printable.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.tradeDate).append("\n");
            sb.append(this.contractNumber).append("\n");
            sb.append(this.side).append("\n");
            sb.append(this.orderNumber).append("\n");
            sb.append(this.quantityRemaining).append("\n");
            sb.append(this.tradeType).append("\n");
            sb.append(this.matchNumber).append("\n");
            sb.append(this.executedQuantity).append("\n");
            sb.append(this.tradePrice).append("\n");
            sb.append(this.tradedContractNumber).append("\n");
            sb.append(this.spreadTradePrice).append("\n");
            sb.append(this.tradeSideOfLeg).append("\n");
            sb.append(this.printable).append("\n");
            return sb.toString();
        }
    }

    // Buying Order Number: 8 Byte Unsigned Fixed Width Integer
    public static class BuyingOrderNumber implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public BuyingOrderNumber(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static BuyingOrderNumber parse(Container container, IBinaryProtocolElement element) {
            return new BuyingOrderNumber(BigEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "buyingOrderNumber = "+ this.value;
        }
    }

    /**
    * Order Executed With Price
    */
    public static class OrderExecutedWithPrice implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public TradeDate tradeDate;
        public ContractNumber contractNumber;
        public BuyingOrderNumber buyingOrderNumber;
        public BuyerQuantityRemaining buyerQuantityRemaining;
        public SellingOrderNumber sellingOrderNumber;
        public SellerQuantityRemaining sellerQuantityRemaining;
        public TradeType tradeType;
        public MatchNumber matchNumber;
        public ExecutedQuantity executedQuantity;
        public TradePrice tradePrice;

        // constructor for Order Executed With Price
        private OrderExecutedWithPrice(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Order Executed With Price
        public static OrderExecutedWithPrice parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new OrderExecutedWithPrice(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.tradeDate = TradeDate.parse(container, element);
            element.contractNumber = ContractNumber.parse(container, element);
            element.buyingOrderNumber = BuyingOrderNumber.parse(container, element);
            element.buyerQuantityRemaining = BuyerQuantityRemaining.parse(container, element);
            element.sellingOrderNumber = SellingOrderNumber.parse(container, element);
            element.sellerQuantityRemaining = SellerQuantityRemaining.parse(container, element);
            element.tradeType = TradeType.parse(container, element);
            element.matchNumber = MatchNumber.parse(container, element);
            element.executedQuantity = ExecutedQuantity.parse(container, element);
            element.tradePrice = TradePrice.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.tradeDate).append("\n");
            sb.append(this.contractNumber).append("\n");
            sb.append(this.buyingOrderNumber).append("\n");
            sb.append(this.buyerQuantityRemaining).append("\n");
            sb.append(this.sellingOrderNumber).append("\n");
            sb.append(this.sellerQuantityRemaining).append("\n");
            sb.append(this.tradeType).append("\n");
            sb.append(this.matchNumber).append("\n");
            sb.append(this.executedQuantity).append("\n");
            sb.append(this.tradePrice).append("\n");
            return sb.toString();
        }
    }

    /**
    * Order Executed
    */
    public static class OrderExecuted implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public TradeDate tradeDate;
        public ContractNumber contractNumber;
        public Side side;
        public OrderNumber orderNumber;
        public QuantityRemaining quantityRemaining;
        public TradeType tradeType;
        public MatchNumber matchNumber;
        public ExecutedQuantity executedQuantity;
        public TradePrice tradePrice;

        // constructor for Order Executed
        private OrderExecuted(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Order Executed
        public static OrderExecuted parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new OrderExecuted(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.tradeDate = TradeDate.parse(container, element);
            element.contractNumber = ContractNumber.parse(container, element);
            element.side = Side.parse(container, element);
            element.orderNumber = OrderNumber.parse(container, element);
            element.quantityRemaining = QuantityRemaining.parse(container, element);
            element.tradeType = TradeType.parse(container, element);
            element.matchNumber = MatchNumber.parse(container, element);
            element.executedQuantity = ExecutedQuantity.parse(container, element);
            element.tradePrice = TradePrice.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.tradeDate).append("\n");
            sb.append(this.contractNumber).append("\n");
            sb.append(this.side).append("\n");
            sb.append(this.orderNumber).append("\n");
            sb.append(this.quantityRemaining).append("\n");
            sb.append(this.tradeType).append("\n");
            sb.append(this.matchNumber).append("\n");
            sb.append(this.executedQuantity).append("\n");
            sb.append(this.tradePrice).append("\n");
            return sb.toString();
        }
    }

    /**
    * Custom Market Order Deleted
    */
    public static class CustomMarketOrderDeleted implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public TradeDate tradeDate;
        public OrderNumber orderNumber;

        // constructor for Custom Market Order Deleted
        private CustomMarketOrderDeleted(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Custom Market Order Deleted
        public static CustomMarketOrderDeleted parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new CustomMarketOrderDeleted(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.tradeDate = TradeDate.parse(container, element);
            element.orderNumber = OrderNumber.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.tradeDate).append("\n");
            sb.append(this.orderNumber).append("\n");
            return sb.toString();
        }
    }

    // Order Book Priority: 4 Byte Unsigned Fixed Width Integer
    public static class OrderBookPriority implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public OrderBookPriority(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static OrderBookPriority parse(Container container, IBinaryProtocolElement element) {
            return new OrderBookPriority(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "orderBookPriority = "+ this.value;
        }
    }

    /**
    * Custom Market Order Replaced
    */
    public static class CustomMarketOrderReplaced implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public TradeDate tradeDate;
        public OrderNumber orderNumber;
        public OrderBookPriority orderBookPriority;
        public Quantity quantity;

        // constructor for Custom Market Order Replaced
        private CustomMarketOrderReplaced(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Custom Market Order Replaced
        public static CustomMarketOrderReplaced parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new CustomMarketOrderReplaced(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.tradeDate = TradeDate.parse(container, element);
            element.orderNumber = OrderNumber.parse(container, element);
            element.orderBookPriority = OrderBookPriority.parse(container, element);
            element.quantity = Quantity.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.tradeDate).append("\n");
            sb.append(this.orderNumber).append("\n");
            sb.append(this.orderBookPriority).append("\n");
            sb.append(this.quantity).append("\n");
            return sb.toString();
        }
    }

    // Price Leg 6: 4 Byte Signed Fixed Width Integer
    public static class PriceLeg6 implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public PriceLeg6(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static PriceLeg6 parse(Container container, IBinaryProtocolElement element) {
            return new PriceLeg6(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "priceLeg6 = "+ this.value;
        }
    }

    // Ratio Leg 6: 2 Byte Unsigned Fixed Width Integer
    public static class RatioLeg6 implements IBinaryProtocolElement {
        public static final int LENGTH = 2;
        public final IBinaryProtocolElement parent;
        public final Short value;

        public RatioLeg6(Short value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static RatioLeg6 parse(Container container, IBinaryProtocolElement element) {
            return new RatioLeg6(BigEndianUtils.toShort(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "ratioLeg6 = "+ this.value;
        }
    }

    // Side Leg 6: 1 Byte Ascii String
    public static class SideLeg6 implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Character value;

        public SideLeg6(Character value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static SideLeg6 parse(Container container, IBinaryProtocolElement element) {
            return new SideLeg6(BigEndianUtils.toCharacter(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "sideLeg6 = "+ this.value;
        }
    }

    // Contract Number Leg 6: 4 Byte Unsigned Fixed Width Integer
    public static class ContractNumberLeg6 implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public ContractNumberLeg6(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static ContractNumberLeg6 parse(Container container, IBinaryProtocolElement element) {
            return new ContractNumberLeg6(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "contractNumberLeg6 = "+ this.value;
        }
    }

    // Price Leg 5: 4 Byte Signed Fixed Width Integer
    public static class PriceLeg5 implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public PriceLeg5(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static PriceLeg5 parse(Container container, IBinaryProtocolElement element) {
            return new PriceLeg5(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "priceLeg5 = "+ this.value;
        }
    }

    // Ratio Leg 5: 2 Byte Unsigned Fixed Width Integer
    public static class RatioLeg5 implements IBinaryProtocolElement {
        public static final int LENGTH = 2;
        public final IBinaryProtocolElement parent;
        public final Short value;

        public RatioLeg5(Short value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static RatioLeg5 parse(Container container, IBinaryProtocolElement element) {
            return new RatioLeg5(BigEndianUtils.toShort(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "ratioLeg5 = "+ this.value;
        }
    }

    // Side Leg 5: 1 Byte Ascii String
    public static class SideLeg5 implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Character value;

        public SideLeg5(Character value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static SideLeg5 parse(Container container, IBinaryProtocolElement element) {
            return new SideLeg5(BigEndianUtils.toCharacter(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "sideLeg5 = "+ this.value;
        }
    }

    // Contract Number Leg 5: 4 Byte Unsigned Fixed Width Integer
    public static class ContractNumberLeg5 implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public ContractNumberLeg5(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static ContractNumberLeg5 parse(Container container, IBinaryProtocolElement element) {
            return new ContractNumberLeg5(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "contractNumberLeg5 = "+ this.value;
        }
    }

    // Price Leg 4: 4 Byte Signed Fixed Width Integer
    public static class PriceLeg4 implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public PriceLeg4(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static PriceLeg4 parse(Container container, IBinaryProtocolElement element) {
            return new PriceLeg4(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "priceLeg4 = "+ this.value;
        }
    }

    // Ratio Leg 4: 2 Byte Unsigned Fixed Width Integer
    public static class RatioLeg4 implements IBinaryProtocolElement {
        public static final int LENGTH = 2;
        public final IBinaryProtocolElement parent;
        public final Short value;

        public RatioLeg4(Short value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static RatioLeg4 parse(Container container, IBinaryProtocolElement element) {
            return new RatioLeg4(BigEndianUtils.toShort(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "ratioLeg4 = "+ this.value;
        }
    }

    // Side Leg 4: 1 Byte Ascii String
    public static class SideLeg4 implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Character value;

        public SideLeg4(Character value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static SideLeg4 parse(Container container, IBinaryProtocolElement element) {
            return new SideLeg4(BigEndianUtils.toCharacter(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "sideLeg4 = "+ this.value;
        }
    }

    // Contract Number Leg 4: 4 Byte Unsigned Fixed Width Integer
    public static class ContractNumberLeg4 implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public ContractNumberLeg4(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static ContractNumberLeg4 parse(Container container, IBinaryProtocolElement element) {
            return new ContractNumberLeg4(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "contractNumberLeg4 = "+ this.value;
        }
    }

    // Price Leg 3: 4 Byte Signed Fixed Width Integer
    public static class PriceLeg3 implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public PriceLeg3(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static PriceLeg3 parse(Container container, IBinaryProtocolElement element) {
            return new PriceLeg3(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "priceLeg3 = "+ this.value;
        }
    }

    // Ratio Leg 3: 2 Byte Unsigned Fixed Width Integer
    public static class RatioLeg3 implements IBinaryProtocolElement {
        public static final int LENGTH = 2;
        public final IBinaryProtocolElement parent;
        public final Short value;

        public RatioLeg3(Short value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static RatioLeg3 parse(Container container, IBinaryProtocolElement element) {
            return new RatioLeg3(BigEndianUtils.toShort(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "ratioLeg3 = "+ this.value;
        }
    }

    // Side Leg 3: 1 Byte Ascii String
    public static class SideLeg3 implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Character value;

        public SideLeg3(Character value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static SideLeg3 parse(Container container, IBinaryProtocolElement element) {
            return new SideLeg3(BigEndianUtils.toCharacter(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "sideLeg3 = "+ this.value;
        }
    }

    // Contract Number Leg 3: 4 Byte Unsigned Fixed Width Integer
    public static class ContractNumberLeg3 implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public ContractNumberLeg3(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static ContractNumberLeg3 parse(Container container, IBinaryProtocolElement element) {
            return new ContractNumberLeg3(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "contractNumberLeg3 = "+ this.value;
        }
    }

    // Price Leg 2: 4 Byte Signed Fixed Width Integer
    public static class PriceLeg2 implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public PriceLeg2(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static PriceLeg2 parse(Container container, IBinaryProtocolElement element) {
            return new PriceLeg2(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "priceLeg2 = "+ this.value;
        }
    }

    // Ratio Leg 2: 2 Byte Unsigned Fixed Width Integer
    public static class RatioLeg2 implements IBinaryProtocolElement {
        public static final int LENGTH = 2;
        public final IBinaryProtocolElement parent;
        public final Short value;

        public RatioLeg2(Short value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static RatioLeg2 parse(Container container, IBinaryProtocolElement element) {
            return new RatioLeg2(BigEndianUtils.toShort(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "ratioLeg2 = "+ this.value;
        }
    }

    // Side Leg 2: 1 Byte Ascii String
    public static class SideLeg2 implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Character value;

        public SideLeg2(Character value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static SideLeg2 parse(Container container, IBinaryProtocolElement element) {
            return new SideLeg2(BigEndianUtils.toCharacter(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "sideLeg2 = "+ this.value;
        }
    }

    // Contract Number Leg 2: 4 Byte Unsigned Fixed Width Integer
    public static class ContractNumberLeg2 implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public ContractNumberLeg2(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static ContractNumberLeg2 parse(Container container, IBinaryProtocolElement element) {
            return new ContractNumberLeg2(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "contractNumberLeg2 = "+ this.value;
        }
    }

    // Price Leg 1: 4 Byte Signed Fixed Width Integer
    public static class PriceLeg1 implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public PriceLeg1(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static PriceLeg1 parse(Container container, IBinaryProtocolElement element) {
            return new PriceLeg1(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "priceLeg1 = "+ this.value;
        }
    }

    // Ratio Leg 1: 2 Byte Unsigned Fixed Width Integer
    public static class RatioLeg1 implements IBinaryProtocolElement {
        public static final int LENGTH = 2;
        public final IBinaryProtocolElement parent;
        public final Short value;

        public RatioLeg1(Short value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static RatioLeg1 parse(Container container, IBinaryProtocolElement element) {
            return new RatioLeg1(BigEndianUtils.toShort(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "ratioLeg1 = "+ this.value;
        }
    }

    // Side Leg 1: 1 Byte Ascii String
    public static class SideLeg1 implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Character value;

        public SideLeg1(Character value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static SideLeg1 parse(Container container, IBinaryProtocolElement element) {
            return new SideLeg1(BigEndianUtils.toCharacter(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "sideLeg1 = "+ this.value;
        }
    }

    // Contract Number Leg 1: 4 Byte Unsigned Fixed Width Integer
    public static class ContractNumberLeg1 implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public ContractNumberLeg1(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static ContractNumberLeg1 parse(Container container, IBinaryProtocolElement element) {
            return new ContractNumberLeg1(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "contractNumberLeg1 = "+ this.value;
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

    /**
    * Custom Market Order Added
    */
    public static class CustomMarketOrderAdded implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public TradeDate tradeDate;
        public OrderNumber orderNumber;
        public OrderBookPriority orderBookPriority;
        public Quantity quantity;
        public Legs legs;
        public ContractNumberLeg1 contractNumberLeg1;
        public SideLeg1 sideLeg1;
        public RatioLeg1 ratioLeg1;
        public PriceLeg1 priceLeg1;
        public ContractNumberLeg2 contractNumberLeg2;
        public SideLeg2 sideLeg2;
        public RatioLeg2 ratioLeg2;
        public PriceLeg2 priceLeg2;
        public ContractNumberLeg3 contractNumberLeg3;
        public SideLeg3 sideLeg3;
        public RatioLeg3 ratioLeg3;
        public PriceLeg3 priceLeg3;
        public ContractNumberLeg4 contractNumberLeg4;
        public SideLeg4 sideLeg4;
        public RatioLeg4 ratioLeg4;
        public PriceLeg4 priceLeg4;
        public ContractNumberLeg5 contractNumberLeg5;
        public SideLeg5 sideLeg5;
        public RatioLeg5 ratioLeg5;
        public PriceLeg5 priceLeg5;
        public ContractNumberLeg6 contractNumberLeg6;
        public SideLeg6 sideLeg6;
        public RatioLeg6 ratioLeg6;
        public PriceLeg6 priceLeg6;

        // constructor for Custom Market Order Added
        private CustomMarketOrderAdded(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Custom Market Order Added
        public static CustomMarketOrderAdded parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new CustomMarketOrderAdded(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.tradeDate = TradeDate.parse(container, element);
            element.orderNumber = OrderNumber.parse(container, element);
            element.orderBookPriority = OrderBookPriority.parse(container, element);
            element.quantity = Quantity.parse(container, element);
            element.legs = Legs.parse(container, element);
            element.contractNumberLeg1 = ContractNumberLeg1.parse(container, element);
            element.sideLeg1 = SideLeg1.parse(container, element);
            element.ratioLeg1 = RatioLeg1.parse(container, element);
            element.priceLeg1 = PriceLeg1.parse(container, element);
            element.contractNumberLeg2 = ContractNumberLeg2.parse(container, element);
            element.sideLeg2 = SideLeg2.parse(container, element);
            element.ratioLeg2 = RatioLeg2.parse(container, element);
            element.priceLeg2 = PriceLeg2.parse(container, element);
            element.contractNumberLeg3 = ContractNumberLeg3.parse(container, element);
            element.sideLeg3 = SideLeg3.parse(container, element);
            element.ratioLeg3 = RatioLeg3.parse(container, element);
            element.priceLeg3 = PriceLeg3.parse(container, element);
            element.contractNumberLeg4 = ContractNumberLeg4.parse(container, element);
            element.sideLeg4 = SideLeg4.parse(container, element);
            element.ratioLeg4 = RatioLeg4.parse(container, element);
            element.priceLeg4 = PriceLeg4.parse(container, element);
            element.contractNumberLeg5 = ContractNumberLeg5.parse(container, element);
            element.sideLeg5 = SideLeg5.parse(container, element);
            element.ratioLeg5 = RatioLeg5.parse(container, element);
            element.priceLeg5 = PriceLeg5.parse(container, element);
            element.contractNumberLeg6 = ContractNumberLeg6.parse(container, element);
            element.sideLeg6 = SideLeg6.parse(container, element);
            element.ratioLeg6 = RatioLeg6.parse(container, element);
            element.priceLeg6 = PriceLeg6.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.tradeDate).append("\n");
            sb.append(this.orderNumber).append("\n");
            sb.append(this.orderBookPriority).append("\n");
            sb.append(this.quantity).append("\n");
            sb.append(this.legs).append("\n");
            sb.append(this.contractNumberLeg1).append("\n");
            sb.append(this.sideLeg1).append("\n");
            sb.append(this.ratioLeg1).append("\n");
            sb.append(this.priceLeg1).append("\n");
            sb.append(this.contractNumberLeg2).append("\n");
            sb.append(this.sideLeg2).append("\n");
            sb.append(this.ratioLeg2).append("\n");
            sb.append(this.priceLeg2).append("\n");
            sb.append(this.contractNumberLeg3).append("\n");
            sb.append(this.sideLeg3).append("\n");
            sb.append(this.ratioLeg3).append("\n");
            sb.append(this.priceLeg3).append("\n");
            sb.append(this.contractNumberLeg4).append("\n");
            sb.append(this.sideLeg4).append("\n");
            sb.append(this.ratioLeg4).append("\n");
            sb.append(this.priceLeg4).append("\n");
            sb.append(this.contractNumberLeg5).append("\n");
            sb.append(this.sideLeg5).append("\n");
            sb.append(this.ratioLeg5).append("\n");
            sb.append(this.priceLeg5).append("\n");
            sb.append(this.contractNumberLeg6).append("\n");
            sb.append(this.sideLeg6).append("\n");
            sb.append(this.ratioLeg6).append("\n");
            sb.append(this.priceLeg6).append("\n");
            return sb.toString();
        }
    }

    /**
    * Implied Order Deleted
    */
    public static class ImpliedOrderDeleted implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public TradeDate tradeDate;
        public ContractNumber contractNumber;
        public Side side;
        public OrderNumber orderNumber;

        // constructor for Implied Order Deleted
        private ImpliedOrderDeleted(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Implied Order Deleted
        public static ImpliedOrderDeleted parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new ImpliedOrderDeleted(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.tradeDate = TradeDate.parse(container, element);
            element.contractNumber = ContractNumber.parse(container, element);
            element.side = Side.parse(container, element);
            element.orderNumber = OrderNumber.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.tradeDate).append("\n");
            sb.append(this.contractNumber).append("\n");
            sb.append(this.side).append("\n");
            sb.append(this.orderNumber).append("\n");
            return sb.toString();
        }
    }

    /**
    * Implied Order Replaced
    */
    public static class ImpliedOrderReplaced implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public TradeDate tradeDate;
        public ContractNumber contractNumber;
        public Side side;
        public OrderNumber orderNumber;
        public OrderBookPriority orderBookPriority;
        public Quantity quantity;
        public Price price;

        // constructor for Implied Order Replaced
        private ImpliedOrderReplaced(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Implied Order Replaced
        public static ImpliedOrderReplaced parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new ImpliedOrderReplaced(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.tradeDate = TradeDate.parse(container, element);
            element.contractNumber = ContractNumber.parse(container, element);
            element.side = Side.parse(container, element);
            element.orderNumber = OrderNumber.parse(container, element);
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
            sb.append(this.contractNumber).append("\n");
            sb.append(this.side).append("\n");
            sb.append(this.orderNumber).append("\n");
            sb.append(this.orderBookPriority).append("\n");
            sb.append(this.quantity).append("\n");
            sb.append(this.price).append("\n");
            return sb.toString();
        }
    }

    /**
    * Implied Order Added
    */
    public static class ImpliedOrderAdded implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public TradeDate tradeDate;
        public ContractNumber contractNumber;
        public Side side;
        public OrderNumber orderNumber;
        public OrderBookPriority orderBookPriority;
        public Quantity quantity;
        public Price price;

        // constructor for Implied Order Added
        private ImpliedOrderAdded(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Implied Order Added
        public static ImpliedOrderAdded parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new ImpliedOrderAdded(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.tradeDate = TradeDate.parse(container, element);
            element.contractNumber = ContractNumber.parse(container, element);
            element.side = Side.parse(container, element);
            element.orderNumber = OrderNumber.parse(container, element);
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
            sb.append(this.contractNumber).append("\n");
            sb.append(this.side).append("\n");
            sb.append(this.orderNumber).append("\n");
            sb.append(this.orderBookPriority).append("\n");
            sb.append(this.quantity).append("\n");
            sb.append(this.price).append("\n");
            return sb.toString();
        }
    }

    /**
    * Order Deleted
    */
    public static class OrderDeleted implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public TradeDate tradeDate;
        public ContractNumber contractNumber;
        public Side side;
        public OrderNumber orderNumber;

        // constructor for Order Deleted
        private OrderDeleted(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Order Deleted
        public static OrderDeleted parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new OrderDeleted(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.tradeDate = TradeDate.parse(container, element);
            element.contractNumber = ContractNumber.parse(container, element);
            element.side = Side.parse(container, element);
            element.orderNumber = OrderNumber.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.tradeDate).append("\n");
            sb.append(this.contractNumber).append("\n");
            sb.append(this.side).append("\n");
            sb.append(this.orderNumber).append("\n");
            return sb.toString();
        }
    }

    /**
    * Order Volume Cancelled
    */
    public static class OrderVolumeCancelled implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public TradeDate tradeDate;
        public ContractNumber contractNumber;
        public Side side;
        public OrderNumber orderNumber;
        public Quantity quantity;

        // constructor for Order Volume Cancelled
        private OrderVolumeCancelled(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Order Volume Cancelled
        public static OrderVolumeCancelled parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new OrderVolumeCancelled(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.tradeDate = TradeDate.parse(container, element);
            element.contractNumber = ContractNumber.parse(container, element);
            element.side = Side.parse(container, element);
            element.orderNumber = OrderNumber.parse(container, element);
            element.quantity = Quantity.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.tradeDate).append("\n");
            sb.append(this.contractNumber).append("\n");
            sb.append(this.side).append("\n");
            sb.append(this.orderNumber).append("\n");
            sb.append(this.quantity).append("\n");
            return sb.toString();
        }
    }

    /**
    * Order Replaced
    */
    public static class OrderReplaced implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public TradeDate tradeDate;
        public ContractNumber contractNumber;
        public Side side;
        public OrderNumber orderNumber;
        public OrderBookPriority orderBookPriority;
        public Quantity quantity;
        public Price price;

        // constructor for Order Replaced
        private OrderReplaced(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Order Replaced
        public static OrderReplaced parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new OrderReplaced(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.tradeDate = TradeDate.parse(container, element);
            element.contractNumber = ContractNumber.parse(container, element);
            element.side = Side.parse(container, element);
            element.orderNumber = OrderNumber.parse(container, element);
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
            sb.append(this.contractNumber).append("\n");
            sb.append(this.side).append("\n");
            sb.append(this.orderNumber).append("\n");
            sb.append(this.orderBookPriority).append("\n");
            sb.append(this.quantity).append("\n");
            sb.append(this.price).append("\n");
            return sb.toString();
        }
    }

    /**
    * Order Added
    */
    public static class OrderAdded implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public TradeDate tradeDate;
        public ContractNumber contractNumber;
        public Side side;
        public OrderNumber orderNumber;
        public OrderBookPriority orderBookPriority;
        public Quantity quantity;
        public Price price;

        // constructor for Order Added
        private OrderAdded(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Order Added
        public static OrderAdded parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new OrderAdded(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.tradeDate = TradeDate.parse(container, element);
            element.contractNumber = ContractNumber.parse(container, element);
            element.side = Side.parse(container, element);
            element.orderNumber = OrderNumber.parse(container, element);
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
            sb.append(this.contractNumber).append("\n");
            sb.append(this.side).append("\n");
            sb.append(this.orderNumber).append("\n");
            sb.append(this.orderBookPriority).append("\n");
            sb.append(this.quantity).append("\n");
            sb.append(this.price).append("\n");
            return sb.toString();
        }
    }

    // Trading Status: 1 Byte Ascii String Enum with 13 values
    public static class TradingStatus implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_TRADING_STATUS value;

        public TradingStatus(ENUM_TRADING_STATUS value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static TradingStatus parse(Container container, IBinaryProtocolElement element) {
            return new TradingStatus(ENUM_TRADING_STATUS.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "tradingStatus = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    /**
    * Order Book State
    */
    public static class OrderBookState implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public TradeDate tradeDate;
        public ContractNumber contractNumber;
        public TradingStatus tradingStatus;

        // constructor for Order Book State
        private OrderBookState(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Order Book State
        public static OrderBookState parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new OrderBookState(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.tradeDate = TradeDate.parse(container, element);
            element.contractNumber = ContractNumber.parse(container, element);
            element.tradingStatus = TradingStatus.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.tradeDate).append("\n");
            sb.append(this.contractNumber).append("\n");
            sb.append(this.tradingStatus).append("\n");
            return sb.toString();
        }
    }

    // Activated: 1 Byte Ascii String Enum with 2 values
    public static class Activated implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_ACTIVATED value;

        public Activated(ENUM_ACTIVATED value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Activated parse(Container container, IBinaryProtocolElement element) {
            return new Activated(ENUM_ACTIVATED.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "activated = "+ this.value.enumType +" ("+ this.value.enumValue +")";
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

    // Lot Size Or Face Value: 4 Byte Unsigned Fixed Width Integer
    public static class LotSizeOrFaceValue implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public LotSizeOrFaceValue(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static LotSizeOrFaceValue parse(Container container, IBinaryProtocolElement element) {
            return new LotSizeOrFaceValue(BigEndianUtils.toInteger(container, LENGTH), element);
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

    // Financial Type: 1 Byte Ascii String Enum with 5 values
    public static class FinancialType implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_FINANCIAL_TYPE value;

        public FinancialType(ENUM_FINANCIAL_TYPE value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static FinancialType parse(Container container, IBinaryProtocolElement element) {
            return new FinancialType(ENUM_FINANCIAL_TYPE.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "financialType = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Prior Day Settlement: 4 Byte Signed Fixed Width Integer
    public static class PriorDaySettlement implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public PriorDaySettlement(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static PriorDaySettlement parse(Container container, IBinaryProtocolElement element) {
            return new PriorDaySettlement(BigEndianUtils.toInteger(container, LENGTH), element);
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

    // Strike Price Minimum Tick: 2 Byte Unsigned Fixed Width Integer
    public static class StrikePriceMinimumTick implements IBinaryProtocolElement {
        public static final int LENGTH = 2;
        public final IBinaryProtocolElement parent;
        public final Short value;

        public StrikePriceMinimumTick(Short value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static StrikePriceMinimumTick parse(Container container, IBinaryProtocolElement element) {
            return new StrikePriceMinimumTick(BigEndianUtils.toShort(container, LENGTH), element);
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

    // Price Minimum Tick: 2 Byte Unsigned Fixed Width Integer
    public static class PriceMinimumTick implements IBinaryProtocolElement {
        public static final int LENGTH = 2;
        public final IBinaryProtocolElement parent;
        public final Short value;

        public PriceMinimumTick(Short value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static PriceMinimumTick parse(Container container, IBinaryProtocolElement element) {
            return new PriceMinimumTick(BigEndianUtils.toShort(container, LENGTH), element);
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

    // Price Decimal Position: 1 Byte Unsigned Fixed Width Integer
    public static class PriceDecimalPosition implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public PriceDecimalPosition(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static PriceDecimalPosition parse(Container container, IBinaryProtocolElement element) {
            return new PriceDecimalPosition(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "priceDecimalPosition = "+ this.value;
        }
    }

    // Underlying Contract Number: 4 Byte Unsigned Fixed Width Integer
    public static class UnderlyingContractNumber implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public UnderlyingContractNumber(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static UnderlyingContractNumber parse(Container container, IBinaryProtocolElement element) {
            return new UnderlyingContractNumber(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "underlyingContractNumber = "+ this.value;
        }
    }

    // Strike: 4 Byte Unsigned Fixed Width Integer
    public static class Strike implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public Strike(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Strike parse(Container container, IBinaryProtocolElement element) {
            return new Strike(BigEndianUtils.toInteger(container, LENGTH), element);
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

    // Expiry Month: 1 Byte Unsigned Fixed Width Integer
    public static class ExpiryMonth implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public ExpiryMonth(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static ExpiryMonth parse(Container container, IBinaryProtocolElement element) {
            return new ExpiryMonth(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "expiryMonth = "+ this.value;
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

    // Contract Type: 1 Byte Ascii String Enum with 7 values
    public static class ContractType implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_CONTRACT_TYPE value;

        public ContractType(ENUM_CONTRACT_TYPE value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static ContractType parse(Container container, IBinaryProtocolElement element) {
            return new ContractType(ENUM_CONTRACT_TYPE.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "contractType = "+ this.value.enumType +" ("+ this.value.enumValue +")";
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

    /**
    * Option Symbol Directory
    */
    public static class OptionSymbolDirectory implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public TradeDate tradeDate;
        public ContractNumber contractNumber;
        public Exchange exchange;
        public Instrument instrument;
        public ContractType contractType;
        public ExpiryYear expiryYear;
        public ExpiryMonth expiryMonth;
        public OptionType optionType;
        public Strike strike;
        public UnderlyingContractNumber underlyingContractNumber;
        public PriceDecimalPosition priceDecimalPosition;
        public PriceFractionalDenominator priceFractionalDenominator;
        public PriceMinimumTick priceMinimumTick;
        public StrikePriceDecimalPosition strikePriceDecimalPosition;
        public StrikePriceFractionalDenominator strikePriceFractionalDenominator;
        public StrikePriceMinimumTick strikePriceMinimumTick;
        public LastTradingDate lastTradingDate;
        public PriorDaySettlement priorDaySettlement;
        public Volatility volatility;
        public FinancialType financialType;
        public Currency currency;
        public LotSizeOrFaceValue lotSizeOrFaceValue;
        public MaturityValue maturityValue;
        public CouponRate couponRate;
        public PaymentsPerYear paymentsPerYear;
        public Activated activated;

        // constructor for Option Symbol Directory
        private OptionSymbolDirectory(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Option Symbol Directory
        public static OptionSymbolDirectory parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new OptionSymbolDirectory(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.tradeDate = TradeDate.parse(container, element);
            element.contractNumber = ContractNumber.parse(container, element);
            element.exchange = Exchange.parse(container, element);
            element.instrument = Instrument.parse(container, element);
            element.contractType = ContractType.parse(container, element);
            element.expiryYear = ExpiryYear.parse(container, element);
            element.expiryMonth = ExpiryMonth.parse(container, element);
            element.optionType = OptionType.parse(container, element);
            element.strike = Strike.parse(container, element);
            element.underlyingContractNumber = UnderlyingContractNumber.parse(container, element);
            element.priceDecimalPosition = PriceDecimalPosition.parse(container, element);
            element.priceFractionalDenominator = PriceFractionalDenominator.parse(container, element);
            element.priceMinimumTick = PriceMinimumTick.parse(container, element);
            element.strikePriceDecimalPosition = StrikePriceDecimalPosition.parse(container, element);
            element.strikePriceFractionalDenominator = StrikePriceFractionalDenominator.parse(container, element);
            element.strikePriceMinimumTick = StrikePriceMinimumTick.parse(container, element);
            element.lastTradingDate = LastTradingDate.parse(container, element);
            element.priorDaySettlement = PriorDaySettlement.parse(container, element);
            element.volatility = Volatility.parse(container, element);
            element.financialType = FinancialType.parse(container, element);
            element.currency = Currency.parse(container, element);
            element.lotSizeOrFaceValue = LotSizeOrFaceValue.parse(container, element);
            element.maturityValue = MaturityValue.parse(container, element);
            element.couponRate = CouponRate.parse(container, element);
            element.paymentsPerYear = PaymentsPerYear.parse(container, element);
            element.activated = Activated.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.tradeDate).append("\n");
            sb.append(this.contractNumber).append("\n");
            sb.append(this.exchange).append("\n");
            sb.append(this.instrument).append("\n");
            sb.append(this.contractType).append("\n");
            sb.append(this.expiryYear).append("\n");
            sb.append(this.expiryMonth).append("\n");
            sb.append(this.optionType).append("\n");
            sb.append(this.strike).append("\n");
            sb.append(this.underlyingContractNumber).append("\n");
            sb.append(this.priceDecimalPosition).append("\n");
            sb.append(this.priceFractionalDenominator).append("\n");
            sb.append(this.priceMinimumTick).append("\n");
            sb.append(this.strikePriceDecimalPosition).append("\n");
            sb.append(this.strikePriceFractionalDenominator).append("\n");
            sb.append(this.strikePriceMinimumTick).append("\n");
            sb.append(this.lastTradingDate).append("\n");
            sb.append(this.priorDaySettlement).append("\n");
            sb.append(this.volatility).append("\n");
            sb.append(this.financialType).append("\n");
            sb.append(this.currency).append("\n");
            sb.append(this.lotSizeOrFaceValue).append("\n");
            sb.append(this.maturityValue).append("\n");
            sb.append(this.couponRate).append("\n");
            sb.append(this.paymentsPerYear).append("\n");
            sb.append(this.activated).append("\n");
            return sb.toString();
        }
    }

    // Secondary Ratio: 1 Byte Unsigned Fixed Width Integer
    public static class SecondaryRatio implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public SecondaryRatio(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static SecondaryRatio parse(Container container, IBinaryProtocolElement element) {
            return new SecondaryRatio(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "secondaryRatio = "+ this.value;
        }
    }

    // Primary Ratio: 1 Byte Unsigned Fixed Width Integer
    public static class PrimaryRatio implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public PrimaryRatio(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static PrimaryRatio parse(Container container, IBinaryProtocolElement element) {
            return new PrimaryRatio(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "primaryRatio = "+ this.value;
        }
    }

    // Second Leg Contract Number: 4 Byte Unsigned Fixed Width Integer
    public static class SecondLegContractNumber implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public SecondLegContractNumber(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static SecondLegContractNumber parse(Container container, IBinaryProtocolElement element) {
            return new SecondLegContractNumber(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "secondLegContractNumber = "+ this.value;
        }
    }

    // First Leg Contract Number: 4 Byte Unsigned Fixed Width Integer
    public static class FirstLegContractNumber implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public FirstLegContractNumber(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static FirstLegContractNumber parse(Container container, IBinaryProtocolElement element) {
            return new FirstLegContractNumber(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "firstLegContractNumber = "+ this.value;
        }
    }

    /**
    * Spread Symbol Directory
    */
    public static class SpreadSymbolDirectory implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public TradeDate tradeDate;
        public ContractNumber contractNumber;
        public Exchange exchange;
        public ContractType contractType;
        public FirstLegContractNumber firstLegContractNumber;
        public SecondLegContractNumber secondLegContractNumber;
        public PrimaryRatio primaryRatio;
        public SecondaryRatio secondaryRatio;
        public PriceDecimalPosition priceDecimalPosition;
        public PriceFractionalDenominator priceFractionalDenominator;
        public PriceMinimumTick priceMinimumTick;

        // constructor for Spread Symbol Directory
        private SpreadSymbolDirectory(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Spread Symbol Directory
        public static SpreadSymbolDirectory parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new SpreadSymbolDirectory(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.tradeDate = TradeDate.parse(container, element);
            element.contractNumber = ContractNumber.parse(container, element);
            element.exchange = Exchange.parse(container, element);
            element.contractType = ContractType.parse(container, element);
            element.firstLegContractNumber = FirstLegContractNumber.parse(container, element);
            element.secondLegContractNumber = SecondLegContractNumber.parse(container, element);
            element.primaryRatio = PrimaryRatio.parse(container, element);
            element.secondaryRatio = SecondaryRatio.parse(container, element);
            element.priceDecimalPosition = PriceDecimalPosition.parse(container, element);
            element.priceFractionalDenominator = PriceFractionalDenominator.parse(container, element);
            element.priceMinimumTick = PriceMinimumTick.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.tradeDate).append("\n");
            sb.append(this.contractNumber).append("\n");
            sb.append(this.exchange).append("\n");
            sb.append(this.contractType).append("\n");
            sb.append(this.firstLegContractNumber).append("\n");
            sb.append(this.secondLegContractNumber).append("\n");
            sb.append(this.primaryRatio).append("\n");
            sb.append(this.secondaryRatio).append("\n");
            sb.append(this.priceDecimalPosition).append("\n");
            sb.append(this.priceFractionalDenominator).append("\n");
            sb.append(this.priceMinimumTick).append("\n");
            return sb.toString();
        }
    }

    /**
    * Future Symbol Directory
    */
    public static class FutureSymbolDirectory implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public TradeDate tradeDate;
        public ContractNumber contractNumber;
        public Exchange exchange;
        public Instrument instrument;
        public ContractType contractType;
        public ExpiryYear expiryYear;
        public ExpiryMonth expiryMonth;
        public PriceDecimalPosition priceDecimalPosition;
        public PriceFractionalDenominator priceFractionalDenominator;
        public PriceMinimumTick priceMinimumTick;
        public LastTradingDate lastTradingDate;
        public PriorDaySettlement priorDaySettlement;
        public FinancialType financialType;
        public Currency currency;
        public LotSizeOrFaceValue lotSizeOrFaceValue;
        public MaturityValue maturityValue;
        public CouponRate couponRate;
        public PaymentsPerYear paymentsPerYear;

        // constructor for Future Symbol Directory
        private FutureSymbolDirectory(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Future Symbol Directory
        public static FutureSymbolDirectory parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new FutureSymbolDirectory(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.tradeDate = TradeDate.parse(container, element);
            element.contractNumber = ContractNumber.parse(container, element);
            element.exchange = Exchange.parse(container, element);
            element.instrument = Instrument.parse(container, element);
            element.contractType = ContractType.parse(container, element);
            element.expiryYear = ExpiryYear.parse(container, element);
            element.expiryMonth = ExpiryMonth.parse(container, element);
            element.priceDecimalPosition = PriceDecimalPosition.parse(container, element);
            element.priceFractionalDenominator = PriceFractionalDenominator.parse(container, element);
            element.priceMinimumTick = PriceMinimumTick.parse(container, element);
            element.lastTradingDate = LastTradingDate.parse(container, element);
            element.priorDaySettlement = PriorDaySettlement.parse(container, element);
            element.financialType = FinancialType.parse(container, element);
            element.currency = Currency.parse(container, element);
            element.lotSizeOrFaceValue = LotSizeOrFaceValue.parse(container, element);
            element.maturityValue = MaturityValue.parse(container, element);
            element.couponRate = CouponRate.parse(container, element);
            element.paymentsPerYear = PaymentsPerYear.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.tradeDate).append("\n");
            sb.append(this.contractNumber).append("\n");
            sb.append(this.exchange).append("\n");
            sb.append(this.instrument).append("\n");
            sb.append(this.contractType).append("\n");
            sb.append(this.expiryYear).append("\n");
            sb.append(this.expiryMonth).append("\n");
            sb.append(this.priceDecimalPosition).append("\n");
            sb.append(this.priceFractionalDenominator).append("\n");
            sb.append(this.priceMinimumTick).append("\n");
            sb.append(this.lastTradingDate).append("\n");
            sb.append(this.priorDaySettlement).append("\n");
            sb.append(this.financialType).append("\n");
            sb.append(this.currency).append("\n");
            sb.append(this.lotSizeOrFaceValue).append("\n");
            sb.append(this.maturityValue).append("\n");
            sb.append(this.couponRate).append("\n");
            sb.append(this.paymentsPerYear).append("\n");
            return sb.toString();
        }
    }

    // Event Code: 1 Byte Ascii String Enum with 5 values
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
    * System Event
    */
    public static class SystemEvent implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public TradeDate tradeDate;
        public EventCode eventCode;

        // constructor for System Event
        private SystemEvent(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for System Event
        public static SystemEvent parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new SystemEvent(parentElement);

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
    * Time Message
    */
    public static class TimeMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Second second;

        // constructor for Time Message
        private TimeMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Time Message
        public static TimeMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new TimeMessage(parentElement);

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
        
        // -- parsing Time Message
        if (messageType.enumType == 'T') {
            return TimeMessage.parse(container, null);
        }
        // -- parsing System Event
        if (messageType.enumType == 'S') {
            return SystemEvent.parse(container, null);
        }
        // -- parsing Future Symbol Directory
        if (messageType.enumType == 'f') {
            return FutureSymbolDirectory.parse(container, null);
        }
        // -- parsing Spread Symbol Directory
        if (messageType.enumType == 'g') {
            return SpreadSymbolDirectory.parse(container, null);
        }
        // -- parsing Option Symbol Directory
        if (messageType.enumType == 'h') {
            return OptionSymbolDirectory.parse(container, null);
        }
        // -- parsing Order Book State
        if (messageType.enumType == 'O') {
            return OrderBookState.parse(container, null);
        }
        // -- parsing Order Added
        if (messageType.enumType == 'A') {
            return OrderAdded.parse(container, null);
        }
        // -- parsing Order Replaced
        if (messageType.enumType == 'U') {
            return OrderReplaced.parse(container, null);
        }
        // -- parsing Order Volume Cancelled
        if (messageType.enumType == 'X') {
            return OrderVolumeCancelled.parse(container, null);
        }
        // -- parsing Order Deleted
        if (messageType.enumType == 'D') {
            return OrderDeleted.parse(container, null);
        }
        // -- parsing Implied Order Added
        if (messageType.enumType == 'j') {
            return ImpliedOrderAdded.parse(container, null);
        }
        // -- parsing Implied Order Replaced
        if (messageType.enumType == 'l') {
            return ImpliedOrderReplaced.parse(container, null);
        }
        // -- parsing Implied Order Deleted
        if (messageType.enumType == 'k') {
            return ImpliedOrderDeleted.parse(container, null);
        }
        // -- parsing Custom Market Order Added
        if (messageType.enumType == 'm') {
            return CustomMarketOrderAdded.parse(container, null);
        }
        // -- parsing Custom Market Order Replaced
        if (messageType.enumType == 'n') {
            return CustomMarketOrderReplaced.parse(container, null);
        }
        // -- parsing Custom Market Order Deleted
        if (messageType.enumType == 'r') {
            return CustomMarketOrderDeleted.parse(container, null);
        }
        // -- parsing Order Executed
        if (messageType.enumType == 'E') {
            return OrderExecuted.parse(container, null);
        }
        // -- parsing Order Executed With Price
        if (messageType.enumType == 'C') {
            return OrderExecutedWithPrice.parse(container, null);
        }
        // -- parsing Spread Executed
        if (messageType.enumType == 'e') {
            return SpreadExecuted.parse(container, null);
        }
        // -- parsing Trade Spread Execution Chain
        if (messageType.enumType == 'P') {
            return TradeSpreadExecutionChain.parse(container, null);
        }
        // -- parsing Custom Market Executed
        if (messageType.enumType == 'u') {
            return CustomMarketExecuted.parse(container, null);
        }
        // -- parsing Custom Market Trade
        if (messageType.enumType == 'p') {
            return CustomMarketTrade.parse(container, null);
        }
        // -- parsing Trade Cancellation
        if (messageType.enumType == 'B') {
            return TradeCancellation.parse(container, null);
        }
        // -- parsing Equilibrium Price Auction Info
        if (messageType.enumType == 'Z') {
            return EquilibriumPriceAuctionInfo.parse(container, null);
        }
        // -- parsing Open High Low Last Trade Adjustment
        if (messageType.enumType == 't') {
            return OpenHighLowLastTradeAdjustment.parse(container, null);
        }
        // -- parsing Market Settlement
        if (messageType.enumType == 'Y') {
            return MarketSettlement.parse(container, null);
        }
        // -- parsing Ad Hoc Text
        if (messageType.enumType == 'x') {
            return AdHocText.parse(container, null);
        }
        // -- parsing Request For Quote
        if (messageType.enumType == 'q') {
            return RequestForQuote.parse(container, null);
        }
        // -- parsing Anomalous Order Threshold Publish
        if (messageType.enumType == 'W') {
            return AnomalousOrderThresholdPublish.parse(container, null);
        }
        // -- parsing Volume And Open Interest
        if (messageType.enumType == 'V') {
            return VolumeAndOpenInterest.parse(container, null);
        }

        return null;
    }

    // Message Type: 1 Byte Ascii String Enum with 30 values
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

    // Trading Service: 3 Byte Ascii String
    public static class TradingService implements IBinaryProtocolElement {
        public static final int LENGTH = 3;
        public final IBinaryProtocolElement parent;
        public final String value;

        public TradingService(String value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static TradingService parse(Container container, IBinaryProtocolElement element) {
            return new TradingService(BigEndianUtils.toString(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "tradingService = "+ this.value;
        }
    }

    // Session Week: 2 Byte Ascii String
    public static class SessionWeek implements IBinaryProtocolElement {
        public static final int LENGTH = 2;
        public final IBinaryProtocolElement parent;
        public final String value;

        public SessionWeek(String value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static SessionWeek parse(Container container, IBinaryProtocolElement element) {
            return new SessionWeek(BigEndianUtils.toString(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "sessionWeek = "+ this.value;
        }
    }

    // Session Year: 2 Byte Ascii String
    public static class SessionYear implements IBinaryProtocolElement {
        public static final int LENGTH = 2;
        public final IBinaryProtocolElement parent;
        public final String value;

        public SessionYear(String value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static SessionYear parse(Container container, IBinaryProtocolElement element) {
            return new SessionYear(BigEndianUtils.toString(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "sessionYear = "+ this.value;
        }
    }

    // Protocol Version: 3 Byte Ascii String
    public static class ProtocolVersion implements IBinaryProtocolElement {
        public static final int LENGTH = 3;
        public final IBinaryProtocolElement parent;
        public final String value;

        public ProtocolVersion(String value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static ProtocolVersion parse(Container container, IBinaryProtocolElement element) {
            return new ProtocolVersion(BigEndianUtils.toString(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "protocolVersion = "+ this.value;
        }
    }

    /**
    * Session
    */
    public static class Session implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public ProtocolVersion protocolVersion;
        public SessionYear sessionYear;
        public SessionWeek sessionWeek;
        public TradingService tradingService;

        // constructor for Session
        private Session(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Session
        public static Session parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new Session(parentElement);

            element.protocolVersion = ProtocolVersion.parse(container, element);
            element.sessionYear = SessionYear.parse(container, element);
            element.sessionWeek = SessionWeek.parse(container, element);
            element.tradingService = TradingService.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.protocolVersion).append("\n");
            sb.append(this.sessionYear).append("\n");
            sb.append(this.sessionWeek).append("\n");
            sb.append(this.tradingService).append("\n");
            return sb.toString();
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
