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
* Java parser for Nasdaq Bx Options Itch TopOfMarket 1.2 protocol
* 
* @version 1.0
* @since 04/25/2021 16:10:25
*/

public class NasdaqBxOptionsTopOfMarketItchv12 {

    ///////////////////////////////////////////////////////////////////////
    // Enum Values
    ///////////////////////////////////////////////////////////////////////

    /**
    * Current Trading State Values
    */
    public enum ENUM_CURRENT_TRADING_STATE {
        E_H('H', "Halt"),
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
    * Message Type Values
    */
    public enum ENUM_MESSAGE_TYPE {
        E_T('T', "Timestamp Message"),
        E_S('S', "System Event Message"),
        E_D('D', "Options Directory Message"),
        E_H('H', "Trading Action Message"),
        E_O('O', "Security Open Message"),
        E_q('q', "Best Bid And Ask Update Short Form Message"),
        E_Q('Q', "Best Bid And Ask Update Long Form Message"),
        E_b('b', "Best Bid Update Short Form Message"),
        E_a('a', "Best Ask Update Short Form Message"),
        E_B('B', "Best Bid Update Long Form Message"),
        E_A('A', "Best Ask Update Long Form Message"),
        E_R('R', "Trade Report Message"),
        E_X('X', "Broken Trade Report Message");

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
    * Minimum Price Variation Values
    */
    public enum ENUM_MINIMUM_PRICE_VARIATION {
        E_E('E', "Penny Everywhere"),
        E_S('S', "Scaled"),
        E_P('P', "Penny Pilot");

        public final Character enumType;
        public final String enumValue;

        ENUM_MINIMUM_PRICE_VARIATION(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_MINIMUM_PRICE_VARIATION> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_MINIMUM_PRICE_VARIATION s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_MINIMUM_PRICE_VARIATION valueOf(Character s)  {
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
    * Quote Condition Values
    */
    public enum ENUM_QUOTE_CONDITION {
        E_F('F', "Nonfirm Quote"),
        E_R('R', "Rotational Quote"),
        E_X('X', "Bid Side Firm"),
        E_Y('Y', "Ask Side Firm"),
        E_SPACE(' ', "Regular Quote");

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
    * Tradable Values
    */
    public enum ENUM_TRADABLE {
        E_Y('Y', "Yes"),
        E_N('N', "No");

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


    // Original Volume: 4 Byte Unsigned Fixed Width Integer
    public static class OriginalVolume implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public OriginalVolume(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static OriginalVolume parse(Container container, IBinaryProtocolElement element) {
            return new OriginalVolume(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "originalVolume = "+ this.value;
        }
    }

    // Original Price: 4 Byte Unsigned Fixed Width Integer
    public static class OriginalPrice implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public OriginalPrice(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static OriginalPrice parse(Container container, IBinaryProtocolElement element) {
            return new OriginalPrice(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "originalPrice = "+ this.value;
        }
    }

    // Original Cross Id: 4 Byte Unsigned Fixed Width Integer
    public static class OriginalCrossId implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public OriginalCrossId(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static OriginalCrossId parse(Container container, IBinaryProtocolElement element) {
            return new OriginalCrossId(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "originalCrossId = "+ this.value;
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

    /**
    * Broken Trade Report Message
    */
    public static class BrokenTradeReportMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Nanoseconds nanoseconds;
        public OptionId optionId;
        public OriginalCrossId originalCrossId;
        public OriginalPrice originalPrice;
        public OriginalVolume originalVolume;

        // constructor for Broken Trade Report Message
        private BrokenTradeReportMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Broken Trade Report Message
        public static BrokenTradeReportMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new BrokenTradeReportMessage(parentElement);

            element.nanoseconds = Nanoseconds.parse(container, element);
            element.optionId = OptionId.parse(container, element);
            element.originalCrossId = OriginalCrossId.parse(container, element);
            element.originalPrice = OriginalPrice.parse(container, element);
            element.originalVolume = OriginalVolume.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.nanoseconds).append("\n");
            sb.append(this.optionId).append("\n");
            sb.append(this.originalCrossId).append("\n");
            sb.append(this.originalPrice).append("\n");
            sb.append(this.originalVolume).append("\n");
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

    // Price Long: 4 Byte Unsigned Fixed Width Integer
    public static class PriceLong implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public PriceLong(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static PriceLong parse(Container container, IBinaryProtocolElement element) {
            return new PriceLong(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "priceLong = "+ this.value;
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

    // Cross Id: 4 Byte Unsigned Fixed Width Integer
    public static class CrossId implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public CrossId(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static CrossId parse(Container container, IBinaryProtocolElement element) {
            return new CrossId(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "crossId = "+ this.value;
        }
    }

    /**
    * Trade Report Message
    */
    public static class TradeReportMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Nanoseconds nanoseconds;
        public OptionId optionId;
        public CrossId crossId;
        public TradeCondition tradeCondition;
        public PriceLong priceLong;
        public Volume volume;

        // constructor for Trade Report Message
        private TradeReportMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Trade Report Message
        public static TradeReportMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new TradeReportMessage(parentElement);

            element.nanoseconds = Nanoseconds.parse(container, element);
            element.optionId = OptionId.parse(container, element);
            element.crossId = CrossId.parse(container, element);
            element.tradeCondition = TradeCondition.parse(container, element);
            element.priceLong = PriceLong.parse(container, element);
            element.volume = Volume.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.nanoseconds).append("\n");
            sb.append(this.optionId).append("\n");
            sb.append(this.crossId).append("\n");
            sb.append(this.tradeCondition).append("\n");
            sb.append(this.priceLong).append("\n");
            sb.append(this.volume).append("\n");
            return sb.toString();
        }
    }

    // Size Long: 4 Byte Unsigned Fixed Width Integer
    public static class SizeLong implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public SizeLong(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static SizeLong parse(Container container, IBinaryProtocolElement element) {
            return new SizeLong(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "sizeLong = "+ this.value;
        }
    }

    // Quote Condition: 1 Byte Ascii String Enum with 5 values
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
    * Best Ask Update Long Form Message
    */
    public static class BestAskUpdateLongFormMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Nanoseconds nanoseconds;
        public OptionId optionId;
        public QuoteCondition quoteCondition;
        public PriceLong priceLong;
        public SizeLong sizeLong;

        // constructor for Best Ask Update Long Form Message
        private BestAskUpdateLongFormMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Best Ask Update Long Form Message
        public static BestAskUpdateLongFormMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new BestAskUpdateLongFormMessage(parentElement);

            element.nanoseconds = Nanoseconds.parse(container, element);
            element.optionId = OptionId.parse(container, element);
            element.quoteCondition = QuoteCondition.parse(container, element);
            element.priceLong = PriceLong.parse(container, element);
            element.sizeLong = SizeLong.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.nanoseconds).append("\n");
            sb.append(this.optionId).append("\n");
            sb.append(this.quoteCondition).append("\n");
            sb.append(this.priceLong).append("\n");
            sb.append(this.sizeLong).append("\n");
            return sb.toString();
        }
    }

    /**
    * Best Bid Update Long Form Message
    */
    public static class BestBidUpdateLongFormMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Nanoseconds nanoseconds;
        public OptionId optionId;
        public QuoteCondition quoteCondition;
        public PriceLong priceLong;
        public SizeLong sizeLong;

        // constructor for Best Bid Update Long Form Message
        private BestBidUpdateLongFormMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Best Bid Update Long Form Message
        public static BestBidUpdateLongFormMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new BestBidUpdateLongFormMessage(parentElement);

            element.nanoseconds = Nanoseconds.parse(container, element);
            element.optionId = OptionId.parse(container, element);
            element.quoteCondition = QuoteCondition.parse(container, element);
            element.priceLong = PriceLong.parse(container, element);
            element.sizeLong = SizeLong.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.nanoseconds).append("\n");
            sb.append(this.optionId).append("\n");
            sb.append(this.quoteCondition).append("\n");
            sb.append(this.priceLong).append("\n");
            sb.append(this.sizeLong).append("\n");
            return sb.toString();
        }
    }

    // Size: 2 Byte Unsigned Fixed Width Integer
    public static class Size implements IBinaryProtocolElement {
        public static final int LENGTH = 2;
        public final IBinaryProtocolElement parent;
        public final Short value;

        public Size(Short value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Size parse(Container container, IBinaryProtocolElement element) {
            return new Size(BigEndianUtils.toShort(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "size = "+ this.value;
        }
    }

    // Price: 2 Byte Unsigned Fixed Width Integer
    public static class Price implements IBinaryProtocolElement {
        public static final int LENGTH = 2;
        public final IBinaryProtocolElement parent;
        public final Short value;

        public Price(Short value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Price parse(Container container, IBinaryProtocolElement element) {
            return new Price(BigEndianUtils.toShort(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "price = "+ this.value;
        }
    }

    /**
    * Best Ask Update Short Form Message
    */
    public static class BestAskUpdateShortFormMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Nanoseconds nanoseconds;
        public OptionId optionId;
        public QuoteCondition quoteCondition;
        public Price price;
        public Size size;

        // constructor for Best Ask Update Short Form Message
        private BestAskUpdateShortFormMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Best Ask Update Short Form Message
        public static BestAskUpdateShortFormMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new BestAskUpdateShortFormMessage(parentElement);

            element.nanoseconds = Nanoseconds.parse(container, element);
            element.optionId = OptionId.parse(container, element);
            element.quoteCondition = QuoteCondition.parse(container, element);
            element.price = Price.parse(container, element);
            element.size = Size.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.nanoseconds).append("\n");
            sb.append(this.optionId).append("\n");
            sb.append(this.quoteCondition).append("\n");
            sb.append(this.price).append("\n");
            sb.append(this.size).append("\n");
            return sb.toString();
        }
    }

    /**
    * Best Bid Update Short Form Message
    */
    public static class BestBidUpdateShortFormMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Nanoseconds nanoseconds;
        public OptionId optionId;
        public QuoteCondition quoteCondition;
        public Price price;
        public Size size;

        // constructor for Best Bid Update Short Form Message
        private BestBidUpdateShortFormMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Best Bid Update Short Form Message
        public static BestBidUpdateShortFormMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new BestBidUpdateShortFormMessage(parentElement);

            element.nanoseconds = Nanoseconds.parse(container, element);
            element.optionId = OptionId.parse(container, element);
            element.quoteCondition = QuoteCondition.parse(container, element);
            element.price = Price.parse(container, element);
            element.size = Size.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.nanoseconds).append("\n");
            sb.append(this.optionId).append("\n");
            sb.append(this.quoteCondition).append("\n");
            sb.append(this.price).append("\n");
            sb.append(this.size).append("\n");
            return sb.toString();
        }
    }

    // Ask Size Long: 4 Byte Unsigned Fixed Width Integer
    public static class AskSizeLong implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public AskSizeLong(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static AskSizeLong parse(Container container, IBinaryProtocolElement element) {
            return new AskSizeLong(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "askSizeLong = "+ this.value;
        }
    }

    // Ask Price Long: 4 Byte Unsigned Fixed Width Integer
    public static class AskPriceLong implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public AskPriceLong(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static AskPriceLong parse(Container container, IBinaryProtocolElement element) {
            return new AskPriceLong(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "askPriceLong = "+ this.value;
        }
    }

    // Bid Size Long: 4 Byte Unsigned Fixed Width Integer
    public static class BidSizeLong implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public BidSizeLong(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static BidSizeLong parse(Container container, IBinaryProtocolElement element) {
            return new BidSizeLong(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "bidSizeLong = "+ this.value;
        }
    }

    // Bid Price Long: 4 Byte Unsigned Fixed Width Integer
    public static class BidPriceLong implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public BidPriceLong(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static BidPriceLong parse(Container container, IBinaryProtocolElement element) {
            return new BidPriceLong(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "bidPriceLong = "+ this.value;
        }
    }

    /**
    * Best Bid And Ask Update Long Form Message
    */
    public static class BestBidAndAskUpdateLongFormMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Nanoseconds nanoseconds;
        public OptionId optionId;
        public QuoteCondition quoteCondition;
        public BidPriceLong bidPriceLong;
        public BidSizeLong bidSizeLong;
        public AskPriceLong askPriceLong;
        public AskSizeLong askSizeLong;

        // constructor for Best Bid And Ask Update Long Form Message
        private BestBidAndAskUpdateLongFormMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Best Bid And Ask Update Long Form Message
        public static BestBidAndAskUpdateLongFormMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new BestBidAndAskUpdateLongFormMessage(parentElement);

            element.nanoseconds = Nanoseconds.parse(container, element);
            element.optionId = OptionId.parse(container, element);
            element.quoteCondition = QuoteCondition.parse(container, element);
            element.bidPriceLong = BidPriceLong.parse(container, element);
            element.bidSizeLong = BidSizeLong.parse(container, element);
            element.askPriceLong = AskPriceLong.parse(container, element);
            element.askSizeLong = AskSizeLong.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.nanoseconds).append("\n");
            sb.append(this.optionId).append("\n");
            sb.append(this.quoteCondition).append("\n");
            sb.append(this.bidPriceLong).append("\n");
            sb.append(this.bidSizeLong).append("\n");
            sb.append(this.askPriceLong).append("\n");
            sb.append(this.askSizeLong).append("\n");
            return sb.toString();
        }
    }

    // Ask Size: 2 Byte Unsigned Fixed Width Integer
    public static class AskSize implements IBinaryProtocolElement {
        public static final int LENGTH = 2;
        public final IBinaryProtocolElement parent;
        public final Short value;

        public AskSize(Short value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static AskSize parse(Container container, IBinaryProtocolElement element) {
            return new AskSize(BigEndianUtils.toShort(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "askSize = "+ this.value;
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

    // Bid Size: 2 Byte Unsigned Fixed Width Integer
    public static class BidSize implements IBinaryProtocolElement {
        public static final int LENGTH = 2;
        public final IBinaryProtocolElement parent;
        public final Short value;

        public BidSize(Short value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static BidSize parse(Container container, IBinaryProtocolElement element) {
            return new BidSize(BigEndianUtils.toShort(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "bidSize = "+ this.value;
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
    * Best Bid And Ask Update Short Form Message
    */
    public static class BestBidAndAskUpdateShortFormMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Nanoseconds nanoseconds;
        public OptionId optionId;
        public QuoteCondition quoteCondition;
        public BidPrice bidPrice;
        public BidSize bidSize;
        public AskPrice askPrice;
        public AskSize askSize;

        // constructor for Best Bid And Ask Update Short Form Message
        private BestBidAndAskUpdateShortFormMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Best Bid And Ask Update Short Form Message
        public static BestBidAndAskUpdateShortFormMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new BestBidAndAskUpdateShortFormMessage(parentElement);

            element.nanoseconds = Nanoseconds.parse(container, element);
            element.optionId = OptionId.parse(container, element);
            element.quoteCondition = QuoteCondition.parse(container, element);
            element.bidPrice = BidPrice.parse(container, element);
            element.bidSize = BidSize.parse(container, element);
            element.askPrice = AskPrice.parse(container, element);
            element.askSize = AskSize.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.nanoseconds).append("\n");
            sb.append(this.optionId).append("\n");
            sb.append(this.quoteCondition).append("\n");
            sb.append(this.bidPrice).append("\n");
            sb.append(this.bidSize).append("\n");
            sb.append(this.askPrice).append("\n");
            sb.append(this.askSize).append("\n");
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
        public Nanoseconds nanoseconds;
        public OptionId optionId;
        public OpenState openState;

        // constructor for Security Open Message
        private SecurityOpenMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Security Open Message
        public static SecurityOpenMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new SecurityOpenMessage(parentElement);

            element.nanoseconds = Nanoseconds.parse(container, element);
            element.optionId = OptionId.parse(container, element);
            element.openState = OpenState.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.nanoseconds).append("\n");
            sb.append(this.optionId).append("\n");
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
    * Trading Action Message
    */
    public static class TradingActionMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Nanoseconds nanoseconds;
        public OptionId optionId;
        public CurrentTradingState currentTradingState;

        // constructor for Trading Action Message
        private TradingActionMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Trading Action Message
        public static TradingActionMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new TradingActionMessage(parentElement);

            element.nanoseconds = Nanoseconds.parse(container, element);
            element.optionId = OptionId.parse(container, element);
            element.currentTradingState = CurrentTradingState.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.nanoseconds).append("\n");
            sb.append(this.optionId).append("\n");
            sb.append(this.currentTradingState).append("\n");
            return sb.toString();
        }
    }

    // Minimum Price Variation: 1 Byte Ascii String Enum with 3 values
    public static class MinimumPriceVariation implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_MINIMUM_PRICE_VARIATION value;

        public MinimumPriceVariation(ENUM_MINIMUM_PRICE_VARIATION value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static MinimumPriceVariation parse(Container container, IBinaryProtocolElement element) {
            return new MinimumPriceVariation(ENUM_MINIMUM_PRICE_VARIATION.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "minimumPriceVariation = "+ this.value.enumType +" ("+ this.value.enumValue +")";
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

    // Option Closing Type: 1 Byte Ascii String
    public static class OptionClosingType implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Character value;

        public OptionClosingType(Character value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static OptionClosingType parse(Container container, IBinaryProtocolElement element) {
            return new OptionClosingType(BigEndianUtils.toCharacter(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "optionClosingType = "+ this.value;
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

    // Strike Price: 4 Byte Unsigned Fixed Width Integer
    public static class StrikePrice implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public StrikePrice(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static StrikePrice parse(Container container, IBinaryProtocolElement element) {
            return new StrikePrice(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "strikePrice = "+ this.value;
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
    * Options Directory Message
    */
    public static class OptionsDirectoryMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Nanoseconds nanoseconds;
        public OptionId optionId;
        public SecuritySymbol securitySymbol;
        public ExpirationYear expirationYear;
        public ExpirationMonth expirationMonth;
        public ExpirationDay expirationDay;
        public StrikePrice strikePrice;
        public OptionType optionType;
        public Source source;
        public UnderlyingSymbol underlyingSymbol;
        public OptionClosingType optionClosingType;
        public Tradable tradable;
        public MinimumPriceVariation minimumPriceVariation;

        // constructor for Options Directory Message
        private OptionsDirectoryMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Options Directory Message
        public static OptionsDirectoryMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new OptionsDirectoryMessage(parentElement);

            element.nanoseconds = Nanoseconds.parse(container, element);
            element.optionId = OptionId.parse(container, element);
            element.securitySymbol = SecuritySymbol.parse(container, element);
            element.expirationYear = ExpirationYear.parse(container, element);
            element.expirationMonth = ExpirationMonth.parse(container, element);
            element.expirationDay = ExpirationDay.parse(container, element);
            element.strikePrice = StrikePrice.parse(container, element);
            element.optionType = OptionType.parse(container, element);
            element.source = Source.parse(container, element);
            element.underlyingSymbol = UnderlyingSymbol.parse(container, element);
            element.optionClosingType = OptionClosingType.parse(container, element);
            element.tradable = Tradable.parse(container, element);
            element.minimumPriceVariation = MinimumPriceVariation.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.nanoseconds).append("\n");
            sb.append(this.optionId).append("\n");
            sb.append(this.securitySymbol).append("\n");
            sb.append(this.expirationYear).append("\n");
            sb.append(this.expirationMonth).append("\n");
            sb.append(this.expirationDay).append("\n");
            sb.append(this.strikePrice).append("\n");
            sb.append(this.optionType).append("\n");
            sb.append(this.source).append("\n");
            sb.append(this.underlyingSymbol).append("\n");
            sb.append(this.optionClosingType).append("\n");
            sb.append(this.tradable).append("\n");
            sb.append(this.minimumPriceVariation).append("\n");
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
        public Nanoseconds nanoseconds;
        public EventCode eventCode;
        public Version version;
        public Subversion subversion;

        // constructor for System Event Message
        private SystemEventMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for System Event Message
        public static SystemEventMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new SystemEventMessage(parentElement);

            element.nanoseconds = Nanoseconds.parse(container, element);
            element.eventCode = EventCode.parse(container, element);
            element.version = Version.parse(container, element);
            element.subversion = Subversion.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.nanoseconds).append("\n");
            sb.append(this.eventCode).append("\n");
            sb.append(this.version).append("\n");
            sb.append(this.subversion).append("\n");
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
    * Timestamp Message
    */
    public static class TimestampMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Seconds seconds;

        // constructor for Timestamp Message
        private TimestampMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Timestamp Message
        public static TimestampMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new TimestampMessage(parentElement);

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
        
        // -- parsing Timestamp Message
        if (messageType.enumType == 'T') {
            return TimestampMessage.parse(container, null);
        }
        // -- parsing System Event Message
        if (messageType.enumType == 'S') {
            return SystemEventMessage.parse(container, null);
        }
        // -- parsing Options Directory Message
        if (messageType.enumType == 'D') {
            return OptionsDirectoryMessage.parse(container, null);
        }
        // -- parsing Trading Action Message
        if (messageType.enumType == 'H') {
            return TradingActionMessage.parse(container, null);
        }
        // -- parsing Security Open Message
        if (messageType.enumType == 'O') {
            return SecurityOpenMessage.parse(container, null);
        }
        // -- parsing Best Bid And Ask Update Short Form Message
        if (messageType.enumType == 'q') {
            return BestBidAndAskUpdateShortFormMessage.parse(container, null);
        }
        // -- parsing Best Bid And Ask Update Long Form Message
        if (messageType.enumType == 'Q') {
            return BestBidAndAskUpdateLongFormMessage.parse(container, null);
        }
        // -- parsing Best Bid Update Short Form Message
        if (messageType.enumType == 'b') {
            return BestBidUpdateShortFormMessage.parse(container, null);
        }
        // -- parsing Best Ask Update Short Form Message
        if (messageType.enumType == 'a') {
            return BestAskUpdateShortFormMessage.parse(container, null);
        }
        // -- parsing Best Bid Update Long Form Message
        if (messageType.enumType == 'B') {
            return BestBidUpdateLongFormMessage.parse(container, null);
        }
        // -- parsing Best Ask Update Long Form Message
        if (messageType.enumType == 'A') {
            return BestAskUpdateLongFormMessage.parse(container, null);
        }
        // -- parsing Trade Report Message
        if (messageType.enumType == 'R') {
            return TradeReportMessage.parse(container, null);
        }
        // -- parsing Broken Trade Report Message
        if (messageType.enumType == 'X') {
            return BrokenTradeReportMessage.parse(container, null);
        }

        return null;
    }

    // Message Type: 1 Byte Ascii String Enum with 13 values
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
