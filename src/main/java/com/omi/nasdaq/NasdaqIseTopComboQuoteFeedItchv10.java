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
* Java parser for Nasdaq Ise Itch TopComboQuoteFeed 1.0 protocol
* 
* @version 1.0
* @since 04/25/2021 16:10:25
*/

public class NasdaqIseTopComboQuoteFeedItchv10 {

    ///////////////////////////////////////////////////////////////////////
    // Enum Values
    ///////////////////////////////////////////////////////////////////////

    /**
    * Current Trading State Values
    */
    public enum ENUM_CURRENT_TRADING_STATE {
        E_H('H', "Halt In Effect"),
        E_T('T', "Trading Resumed");

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
    * Message Type Values
    */
    public enum ENUM_MESSAGE_TYPE {
        E_S('S', "System Event Message"),
        E_R('R', "Complex Strategy Directory Message"),
        E_O('O', "Strategy Open Closed Message"),
        E_H('H', "Strategy Trading Action Message"),
        E_C('C', "Strategy Best Bid And Ask Update"),
        E_D('D', "Strategy Best Bid Update"),
        E_E('E', "Strategy Best Ask Update"),
        E_t('t', "Complex Strategy Ticker Message");

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
        E_P('P', "Put"),
        E_SPACE(' ', "Na");

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
    * Quote Condition Values
    */
    public enum ENUM_QUOTE_CONDITION {
        E_SPACE(' ', "Regular Quote"),
        E_X('X', "Halted");

        public final Character enumType;
        public final String enumValue;

        ENUM_QUOTE_CONDITION(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_QUOTE_CONDITION> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_QUOTE_CONDITION s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_QUOTE_CONDITION valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Side Values
    */
    public enum ENUM_SIDE {
        E_B('B', "Buy"),
        E_S('S', "Sell"),
        E_SPACE(' ', "Hidden");

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
    * Strategy Type Values
    */
    public enum ENUM_STRATEGY_TYPE {
        E_V('V', "Vertical Spread"),
        E_T('T', "Time Spread"),
        E_D('D', "Diagonal Spread"),
        E_S('S', "Straddle"),
        E_G('G', "Strangle"),
        E_C('C', "Combo"),
        E_R('R', "Risk Reversal"),
        E_A('A', "Ratio Spread"),
        E_U('U', "Custom");

        public final Character enumType;
        public final String enumValue;

        ENUM_STRATEGY_TYPE(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_STRATEGY_TYPE> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_STRATEGY_TYPE s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_STRATEGY_TYPE valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }


    // Trade Condition: 1 Byte Ascii String
    public static class TradeCondition implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Character value;

        public TradeCondition(Character value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static TradeCondition parse(Container container, IBinaryProtocolElement element) {
            return new TradeCondition(BigEndianUtils.toCharacter(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "tradeCondition = "+ this.value;
        }
    }

    // First: 8 Byte Unsigned Fixed Width Integer
    public static class First implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public First(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static First parse(Container container, IBinaryProtocolElement element) {
            return new First(BigEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "first = "+ this.value;
        }
    }

    // Low: 8 Byte Unsigned Fixed Width Integer
    public static class Low implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public Low(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Low parse(Container container, IBinaryProtocolElement element) {
            return new Low(BigEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "low = "+ this.value;
        }
    }

    // High: 8 Byte Unsigned Fixed Width Integer
    public static class High implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public High(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static High parse(Container container, IBinaryProtocolElement element) {
            return new High(BigEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "high = "+ this.value;
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

    // Size: 4 Byte Unsigned Fixed Width Integer
    public static class Size implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public Size(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Size parse(Container container, IBinaryProtocolElement element) {
            return new Size(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "size = "+ this.value;
        }
    }

    // Last Price: 8 Byte Unsigned Fixed Width Integer
    public static class LastPrice implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public LastPrice(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static LastPrice parse(Container container, IBinaryProtocolElement element) {
            return new LastPrice(BigEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "lastPrice = "+ this.value;
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

    /**
    * Complex Strategy Ticker Message
    */
    public static class ComplexStrategyTickerMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public StrategyId strategyId;
        public LastPrice lastPrice;
        public Size size;
        public Volume volume;
        public High high;
        public Low low;
        public First first;
        public TradeCondition tradeCondition;

        // constructor for Complex Strategy Ticker Message
        private ComplexStrategyTickerMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Complex Strategy Ticker Message
        public static ComplexStrategyTickerMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new ComplexStrategyTickerMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.strategyId = StrategyId.parse(container, element);
            element.lastPrice = LastPrice.parse(container, element);
            element.size = Size.parse(container, element);
            element.volume = Volume.parse(container, element);
            element.high = High.parse(container, element);
            element.low = Low.parse(container, element);
            element.first = First.parse(container, element);
            element.tradeCondition = TradeCondition.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.strategyId).append("\n");
            sb.append(this.lastPrice).append("\n");
            sb.append(this.size).append("\n");
            sb.append(this.volume).append("\n");
            sb.append(this.high).append("\n");
            sb.append(this.low).append("\n");
            sb.append(this.first).append("\n");
            sb.append(this.tradeCondition).append("\n");
            return sb.toString();
        }
    }

    // Ntt Market Size: 4 Byte Unsigned Fixed Width Integer
    public static class NttMarketSize implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public NttMarketSize(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static NttMarketSize parse(Container container, IBinaryProtocolElement element) {
            return new NttMarketSize(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "nttMarketSize = "+ this.value;
        }
    }

    // Market Size: 4 Byte Unsigned Fixed Width Integer
    public static class MarketSize implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public MarketSize(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static MarketSize parse(Container container, IBinaryProtocolElement element) {
            return new MarketSize(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "marketSize = "+ this.value;
        }
    }

    // Ntt Size: 4 Byte Unsigned Fixed Width Integer
    public static class NttSize implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public NttSize(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static NttSize parse(Container container, IBinaryProtocolElement element) {
            return new NttSize(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "nttSize = "+ this.value;
        }
    }

    // Pro Cust Size: 4 Byte Unsigned Fixed Width Integer
    public static class ProCustSize implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public ProCustSize(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static ProCustSize parse(Container container, IBinaryProtocolElement element) {
            return new ProCustSize(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "proCustSize = "+ this.value;
        }
    }

    // Cust Size: 4 Byte Unsigned Fixed Width Integer
    public static class CustSize implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public CustSize(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static CustSize parse(Container container, IBinaryProtocolElement element) {
            return new CustSize(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "custSize = "+ this.value;
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

    // Quote Condition: 1 Byte Ascii String Enum with 2 values
    public static class QuoteCondition implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_QUOTE_CONDITION value;

        public QuoteCondition(ENUM_QUOTE_CONDITION value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static QuoteCondition parse(Container container, IBinaryProtocolElement element) {
            return new QuoteCondition(ENUM_QUOTE_CONDITION.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "quoteCondition = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    /**
    * Strategy Best Ask Update
    */
    public static class StrategyBestAskUpdate implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public StrategyId strategyId;
        public QuoteCondition quoteCondition;
        public Price price;
        public Size size;
        public CustSize custSize;
        public ProCustSize proCustSize;
        public NttSize nttSize;
        public MarketSize marketSize;
        public NttMarketSize nttMarketSize;

        // constructor for Strategy Best Ask Update
        private StrategyBestAskUpdate(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Strategy Best Ask Update
        public static StrategyBestAskUpdate parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new StrategyBestAskUpdate(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.strategyId = StrategyId.parse(container, element);
            element.quoteCondition = QuoteCondition.parse(container, element);
            element.price = Price.parse(container, element);
            element.size = Size.parse(container, element);
            element.custSize = CustSize.parse(container, element);
            element.proCustSize = ProCustSize.parse(container, element);
            element.nttSize = NttSize.parse(container, element);
            element.marketSize = MarketSize.parse(container, element);
            element.nttMarketSize = NttMarketSize.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.strategyId).append("\n");
            sb.append(this.quoteCondition).append("\n");
            sb.append(this.price).append("\n");
            sb.append(this.size).append("\n");
            sb.append(this.custSize).append("\n");
            sb.append(this.proCustSize).append("\n");
            sb.append(this.nttSize).append("\n");
            sb.append(this.marketSize).append("\n");
            sb.append(this.nttMarketSize).append("\n");
            return sb.toString();
        }
    }

    /**
    * Strategy Best Bid Update
    */
    public static class StrategyBestBidUpdate implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public StrategyId strategyId;
        public QuoteCondition quoteCondition;
        public Price price;
        public Size size;
        public CustSize custSize;
        public ProCustSize proCustSize;
        public NttSize nttSize;
        public MarketSize marketSize;
        public NttMarketSize nttMarketSize;

        // constructor for Strategy Best Bid Update
        private StrategyBestBidUpdate(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Strategy Best Bid Update
        public static StrategyBestBidUpdate parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new StrategyBestBidUpdate(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.strategyId = StrategyId.parse(container, element);
            element.quoteCondition = QuoteCondition.parse(container, element);
            element.price = Price.parse(container, element);
            element.size = Size.parse(container, element);
            element.custSize = CustSize.parse(container, element);
            element.proCustSize = ProCustSize.parse(container, element);
            element.nttSize = NttSize.parse(container, element);
            element.marketSize = MarketSize.parse(container, element);
            element.nttMarketSize = NttMarketSize.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.strategyId).append("\n");
            sb.append(this.quoteCondition).append("\n");
            sb.append(this.price).append("\n");
            sb.append(this.size).append("\n");
            sb.append(this.custSize).append("\n");
            sb.append(this.proCustSize).append("\n");
            sb.append(this.nttSize).append("\n");
            sb.append(this.marketSize).append("\n");
            sb.append(this.nttMarketSize).append("\n");
            return sb.toString();
        }
    }

    // Ask Ntt Market Size: 4 Byte Unsigned Fixed Width Integer
    public static class AskNttMarketSize implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public AskNttMarketSize(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static AskNttMarketSize parse(Container container, IBinaryProtocolElement element) {
            return new AskNttMarketSize(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "askNttMarketSize = "+ this.value;
        }
    }

    // Ask Market Size: 4 Byte Unsigned Fixed Width Integer
    public static class AskMarketSize implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public AskMarketSize(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static AskMarketSize parse(Container container, IBinaryProtocolElement element) {
            return new AskMarketSize(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "askMarketSize = "+ this.value;
        }
    }

    // Ask Ntt Size: 4 Byte Unsigned Fixed Width Integer
    public static class AskNttSize implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public AskNttSize(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static AskNttSize parse(Container container, IBinaryProtocolElement element) {
            return new AskNttSize(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "askNttSize = "+ this.value;
        }
    }

    // Ask Pro Cust Size: 4 Byte Unsigned Fixed Width Integer
    public static class AskProCustSize implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public AskProCustSize(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static AskProCustSize parse(Container container, IBinaryProtocolElement element) {
            return new AskProCustSize(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "askProCustSize = "+ this.value;
        }
    }

    // Ask Cust Size: 4 Byte Unsigned Fixed Width Integer
    public static class AskCustSize implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public AskCustSize(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static AskCustSize parse(Container container, IBinaryProtocolElement element) {
            return new AskCustSize(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "askCustSize = "+ this.value;
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

    // Ask Price: 4 Byte Unsigned Fixed Width Integer
    public static class AskPrice implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public AskPrice(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static AskPrice parse(Container container, IBinaryProtocolElement element) {
            return new AskPrice(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "askPrice = "+ this.value;
        }
    }

    // Bid Ntt Market Size: 4 Byte Unsigned Fixed Width Integer
    public static class BidNttMarketSize implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public BidNttMarketSize(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static BidNttMarketSize parse(Container container, IBinaryProtocolElement element) {
            return new BidNttMarketSize(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "bidNttMarketSize = "+ this.value;
        }
    }

    // Bid Market Size: 4 Byte Unsigned Fixed Width Integer
    public static class BidMarketSize implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public BidMarketSize(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static BidMarketSize parse(Container container, IBinaryProtocolElement element) {
            return new BidMarketSize(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "bidMarketSize = "+ this.value;
        }
    }

    // Bid Ntt Size: 4 Byte Unsigned Fixed Width Integer
    public static class BidNttSize implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public BidNttSize(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static BidNttSize parse(Container container, IBinaryProtocolElement element) {
            return new BidNttSize(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "bidNttSize = "+ this.value;
        }
    }

    // Bid Pro Cust Size: 4 Byte Unsigned Fixed Width Integer
    public static class BidProCustSize implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public BidProCustSize(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static BidProCustSize parse(Container container, IBinaryProtocolElement element) {
            return new BidProCustSize(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "bidProCustSize = "+ this.value;
        }
    }

    // Bid Cust Size: 4 Byte Unsigned Fixed Width Integer
    public static class BidCustSize implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public BidCustSize(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static BidCustSize parse(Container container, IBinaryProtocolElement element) {
            return new BidCustSize(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "bidCustSize = "+ this.value;
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

    // Bid Price: 4 Byte Unsigned Fixed Width Integer
    public static class BidPrice implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public BidPrice(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static BidPrice parse(Container container, IBinaryProtocolElement element) {
            return new BidPrice(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "bidPrice = "+ this.value;
        }
    }

    /**
    * Strategy Best Bid And Ask Update
    */
    public static class StrategyBestBidAndAskUpdate implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public StrategyId strategyId;
        public QuoteCondition quoteCondition;
        public BidPrice bidPrice;
        public BidSize bidSize;
        public BidCustSize bidCustSize;
        public BidProCustSize bidProCustSize;
        public BidNttSize bidNttSize;
        public BidMarketSize bidMarketSize;
        public BidNttMarketSize bidNttMarketSize;
        public AskPrice askPrice;
        public AskSize askSize;
        public AskCustSize askCustSize;
        public AskProCustSize askProCustSize;
        public AskNttSize askNttSize;
        public AskMarketSize askMarketSize;
        public AskNttMarketSize askNttMarketSize;

        // constructor for Strategy Best Bid And Ask Update
        private StrategyBestBidAndAskUpdate(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Strategy Best Bid And Ask Update
        public static StrategyBestBidAndAskUpdate parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new StrategyBestBidAndAskUpdate(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.strategyId = StrategyId.parse(container, element);
            element.quoteCondition = QuoteCondition.parse(container, element);
            element.bidPrice = BidPrice.parse(container, element);
            element.bidSize = BidSize.parse(container, element);
            element.bidCustSize = BidCustSize.parse(container, element);
            element.bidProCustSize = BidProCustSize.parse(container, element);
            element.bidNttSize = BidNttSize.parse(container, element);
            element.bidMarketSize = BidMarketSize.parse(container, element);
            element.bidNttMarketSize = BidNttMarketSize.parse(container, element);
            element.askPrice = AskPrice.parse(container, element);
            element.askSize = AskSize.parse(container, element);
            element.askCustSize = AskCustSize.parse(container, element);
            element.askProCustSize = AskProCustSize.parse(container, element);
            element.askNttSize = AskNttSize.parse(container, element);
            element.askMarketSize = AskMarketSize.parse(container, element);
            element.askNttMarketSize = AskNttMarketSize.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.strategyId).append("\n");
            sb.append(this.quoteCondition).append("\n");
            sb.append(this.bidPrice).append("\n");
            sb.append(this.bidSize).append("\n");
            sb.append(this.bidCustSize).append("\n");
            sb.append(this.bidProCustSize).append("\n");
            sb.append(this.bidNttSize).append("\n");
            sb.append(this.bidMarketSize).append("\n");
            sb.append(this.bidNttMarketSize).append("\n");
            sb.append(this.askPrice).append("\n");
            sb.append(this.askSize).append("\n");
            sb.append(this.askCustSize).append("\n");
            sb.append(this.askProCustSize).append("\n");
            sb.append(this.askNttSize).append("\n");
            sb.append(this.askMarketSize).append("\n");
            sb.append(this.askNttMarketSize).append("\n");
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
    * Strategy Trading Action Message
    */
    public static class StrategyTradingActionMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public StrategyId strategyId;
        public CurrentTradingState currentTradingState;

        // constructor for Strategy Trading Action Message
        private StrategyTradingActionMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Strategy Trading Action Message
        public static StrategyTradingActionMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new StrategyTradingActionMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.strategyId = StrategyId.parse(container, element);
            element.currentTradingState = CurrentTradingState.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.strategyId).append("\n");
            sb.append(this.currentTradingState).append("\n");
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
        public Timestamp timestamp;
        public StrategyId strategyId;
        public OpenState openState;

        // constructor for Strategy Open Closed Message
        private StrategyOpenClosedMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Strategy Open Closed Message
        public static StrategyOpenClosedMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new StrategyOpenClosedMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.strategyId = StrategyId.parse(container, element);
            element.openState = OpenState.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.strategyId).append("\n");
            sb.append(this.openState).append("\n");
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

    // Explicit Strike Price: 8 Byte Unsigned Fixed Width Integer
    public static class ExplicitStrikePrice implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public ExplicitStrikePrice(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static ExplicitStrikePrice parse(Container container, IBinaryProtocolElement element) {
            return new ExplicitStrikePrice(BigEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "explicitStrikePrice = "+ this.value;
        }
    }

    // Expiration Day: 1 Byte Unsigned Fixed Width Integer
    public static class ExpirationDay implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public ExpirationDay(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static ExpirationDay parse(Container container, IBinaryProtocolElement element) {
            return new ExpirationDay(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "expirationDay = "+ this.value;
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

    // Leg Id: 1 Byte Unsigned Fixed Width Integer
    public static class LegId implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public LegId(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static LegId parse(Container container, IBinaryProtocolElement element) {
            return new LegId(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "legId = "+ this.value;
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
    * Leg Information
    */
    public static class LegInformation implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public OptionId optionId;
        public SecuritySymbol securitySymbol;
        public LegId legId;
        public ExpirationYear expirationYear;
        public ExpirationMonth expirationMonth;
        public ExpirationDay expirationDay;
        public ExplicitStrikePrice explicitStrikePrice;
        public OptionType optionType;
        public Side side;
        public LegRatio legRatio;

        // constructor for Leg Information
        private LegInformation(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Leg Information
        public static LegInformation parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new LegInformation(parentElement);

            element.optionId = OptionId.parse(container, element);
            element.securitySymbol = SecuritySymbol.parse(container, element);
            element.legId = LegId.parse(container, element);
            element.expirationYear = ExpirationYear.parse(container, element);
            element.expirationMonth = ExpirationMonth.parse(container, element);
            element.expirationDay = ExpirationDay.parse(container, element);
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
            sb.append(this.legId).append("\n");
            sb.append(this.expirationYear).append("\n");
            sb.append(this.expirationMonth).append("\n");
            sb.append(this.expirationDay).append("\n");
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

    // Strategy Type: 1 Byte Ascii String Enum with 9 values
    public static class StrategyType implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_STRATEGY_TYPE value;

        public StrategyType(ENUM_STRATEGY_TYPE value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static StrategyType parse(Container container, IBinaryProtocolElement element) {
            return new StrategyType(ENUM_STRATEGY_TYPE.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "strategyType = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    /**
    * Complex Strategy Directory Message
    */
    public static class ComplexStrategyDirectoryMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public StrategyId strategyId;
        public StrategyType strategyType;
        public Source source;
        public UnderlyingSymbol underlyingSymbol;
        public NumberOfLegs numberOfLegs;
        public List<LegInformation> legInformationList = new ArrayList<>();

        // constructor for Complex Strategy Directory Message
        private ComplexStrategyDirectoryMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Complex Strategy Directory Message
        public static ComplexStrategyDirectoryMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new ComplexStrategyDirectoryMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.strategyId = StrategyId.parse(container, element);
            element.strategyType = StrategyType.parse(container, element);
            element.source = Source.parse(container, element);
            element.underlyingSymbol = UnderlyingSymbol.parse(container, element);
            element.numberOfLegs = NumberOfLegs.parse(container, element);

            // Leg Information: Struct of 10 fields
            for (int i = 0; i < element.numberOfLegs.value; i++) {
                element.legInformationList.add(LegInformation.parse(container, element));
            }

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.strategyId).append("\n");
            sb.append(this.strategyType).append("\n");
            sb.append(this.source).append("\n");
            sb.append(this.underlyingSymbol).append("\n");
            sb.append(this.numberOfLegs).append("\n");
            for (var legInformation: legInformationList) {
                sb.append(legInformation).append("\n");
            }
            return sb.toString();
        }
    }

    // Subversion: 1 Byte Unsigned Fixed Width Integer
    public static class Subversion implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public Subversion(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Subversion parse(Container container, IBinaryProtocolElement element) {
            return new Subversion(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "subversion = "+ this.value;
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

    // Current Day: 1 Byte Unsigned Fixed Width Integer
    public static class CurrentDay implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public CurrentDay(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static CurrentDay parse(Container container, IBinaryProtocolElement element) {
            return new CurrentDay(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "currentDay = "+ this.value;
        }
    }

    // Current Month: 1 Byte Unsigned Fixed Width Integer
    public static class CurrentMonth implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public CurrentMonth(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static CurrentMonth parse(Container container, IBinaryProtocolElement element) {
            return new CurrentMonth(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "currentMonth = "+ this.value;
        }
    }

    // Current Year: 2 Byte Unsigned Fixed Width Integer
    public static class CurrentYear implements IBinaryProtocolElement {
        public static final int LENGTH = 2;
        public final IBinaryProtocolElement parent;
        public final Short value;

        public CurrentYear(Short value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static CurrentYear parse(Container container, IBinaryProtocolElement element) {
            return new CurrentYear(BigEndianUtils.toShort(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "currentYear = "+ this.value;
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
        public Timestamp timestamp;
        public EventCode eventCode;
        public CurrentYear currentYear;
        public CurrentMonth currentMonth;
        public CurrentDay currentDay;
        public Version version;
        public Subversion subversion;

        // constructor for System Event Message
        private SystemEventMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for System Event Message
        public static SystemEventMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new SystemEventMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.eventCode = EventCode.parse(container, element);
            element.currentYear = CurrentYear.parse(container, element);
            element.currentMonth = CurrentMonth.parse(container, element);
            element.currentDay = CurrentDay.parse(container, element);
            element.version = Version.parse(container, element);
            element.subversion = Subversion.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.eventCode).append("\n");
            sb.append(this.currentYear).append("\n");
            sb.append(this.currentMonth).append("\n");
            sb.append(this.currentDay).append("\n");
            sb.append(this.version).append("\n");
            sb.append(this.subversion).append("\n");
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
        // -- parsing Complex Strategy Directory Message
        if (messageType.enumType == 'R') {
            return ComplexStrategyDirectoryMessage.parse(container, null);
        }
        // -- parsing Strategy Open Closed Message
        if (messageType.enumType == 'O') {
            return StrategyOpenClosedMessage.parse(container, null);
        }
        // -- parsing Strategy Trading Action Message
        if (messageType.enumType == 'H') {
            return StrategyTradingActionMessage.parse(container, null);
        }
        // -- parsing Strategy Best Bid And Ask Update
        if (messageType.enumType == 'C') {
            return StrategyBestBidAndAskUpdate.parse(container, null);
        }
        // -- parsing Strategy Best Bid Update
        if (messageType.enumType == 'D') {
            return StrategyBestBidUpdate.parse(container, null);
        }
        // -- parsing Strategy Best Ask Update
        if (messageType.enumType == 'E') {
            return StrategyBestAskUpdate.parse(container, null);
        }
        // -- parsing Complex Strategy Ticker Message
        if (messageType.enumType == 't') {
            return ComplexStrategyTickerMessage.parse(container, null);
        }

        return null;
    }

    // Message Type: 1 Byte Ascii String Enum with 8 values
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
