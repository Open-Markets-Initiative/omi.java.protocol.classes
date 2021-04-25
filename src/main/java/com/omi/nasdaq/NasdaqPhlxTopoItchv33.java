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
* Java parser for Nasdaq Phlx Itch Topo 3.3 protocol
* 
* @version 1.0
* @since 04/25/2021 16:10:25
*/

public class NasdaqPhlxTopoItchv33 {

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
        E_T('T', "Timestamp Message"),
        E_S('S', "System Event Message"),
        E_D('D', "Options Directory Message"),
        E_H('H', "Trading Action Message"),
        E_O('O', "Security Open Closed Message"),
        E_q('q', "Short Best Bid And Ask Update Message"),
        E_Q('Q', "Long Best Bid And Ask Update Message"),
        E_a('a', "Short Best Ask Update Message"),
        E_b('b', "Short Best Bid Update Message"),
        E_A('A', "Long Best Ask Update Message"),
        E_B('B', "Long Best Bid Update Message"),
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
    * Mpv Values
    */
    public enum ENUM_MPV {
        E_E('E', "Penny Everywhere"),
        E_S('S', "Scaled"),
        E_P('P', "Penny Pilot");

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
        E_SPACE(' ', "Regular Quoteautox Eligible"),
        E_F('F', "Non Firm Quote"),
        E_R('R', "Rotational Quote"),
        E_X('X', "Bid Side Firm"),
        E_Y('Y', "Ask Side Firm");

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

    // Price 4: 4 Byte Unsigned Fixed Width Integer
    public static class Price4 implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public Price4(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Price4 parse(Container container, IBinaryProtocolElement element) {
            return new Price4(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "price4 = "+ this.value;
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
        public Price4 price4;
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
            element.price4 = Price4.parse(container, element);
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
            sb.append(this.price4).append("\n");
            sb.append(this.volume).append("\n");
            return sb.toString();
        }
    }

    // Size 4: 4 Byte Unsigned Fixed Width Integer
    public static class Size4 implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public Size4(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Size4 parse(Container container, IBinaryProtocolElement element) {
            return new Size4(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "size4 = "+ this.value;
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
    * Long Best Bid Update Message
    */
    public static class LongBestBidUpdateMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Nanoseconds nanoseconds;
        public OptionId optionId;
        public QuoteCondition quoteCondition;
        public Price4 price4;
        public Size4 size4;

        // constructor for Long Best Bid Update Message
        private LongBestBidUpdateMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Long Best Bid Update Message
        public static LongBestBidUpdateMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new LongBestBidUpdateMessage(parentElement);

            element.nanoseconds = Nanoseconds.parse(container, element);
            element.optionId = OptionId.parse(container, element);
            element.quoteCondition = QuoteCondition.parse(container, element);
            element.price4 = Price4.parse(container, element);
            element.size4 = Size4.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.nanoseconds).append("\n");
            sb.append(this.optionId).append("\n");
            sb.append(this.quoteCondition).append("\n");
            sb.append(this.price4).append("\n");
            sb.append(this.size4).append("\n");
            return sb.toString();
        }
    }

    /**
    * Long Best Ask Update Message
    */
    public static class LongBestAskUpdateMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Nanoseconds nanoseconds;
        public OptionId optionId;
        public QuoteCondition quoteCondition;
        public Price4 price4;
        public Size4 size4;

        // constructor for Long Best Ask Update Message
        private LongBestAskUpdateMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Long Best Ask Update Message
        public static LongBestAskUpdateMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new LongBestAskUpdateMessage(parentElement);

            element.nanoseconds = Nanoseconds.parse(container, element);
            element.optionId = OptionId.parse(container, element);
            element.quoteCondition = QuoteCondition.parse(container, element);
            element.price4 = Price4.parse(container, element);
            element.size4 = Size4.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.nanoseconds).append("\n");
            sb.append(this.optionId).append("\n");
            sb.append(this.quoteCondition).append("\n");
            sb.append(this.price4).append("\n");
            sb.append(this.size4).append("\n");
            return sb.toString();
        }
    }

    // Size 2: 2 Byte Unsigned Fixed Width Integer
    public static class Size2 implements IBinaryProtocolElement {
        public static final int LENGTH = 2;
        public final IBinaryProtocolElement parent;
        public final Short value;

        public Size2(Short value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Size2 parse(Container container, IBinaryProtocolElement element) {
            return new Size2(BigEndianUtils.toShort(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "size2 = "+ this.value;
        }
    }

    // Price 2: 2 Byte Unsigned Fixed Width Integer
    public static class Price2 implements IBinaryProtocolElement {
        public static final int LENGTH = 2;
        public final IBinaryProtocolElement parent;
        public final Short value;

        public Price2(Short value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Price2 parse(Container container, IBinaryProtocolElement element) {
            return new Price2(BigEndianUtils.toShort(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "price2 = "+ this.value;
        }
    }

    /**
    * Short Best Bid Update Message
    */
    public static class ShortBestBidUpdateMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Nanoseconds nanoseconds;
        public OptionId optionId;
        public QuoteCondition quoteCondition;
        public Price2 price2;
        public Size2 size2;

        // constructor for Short Best Bid Update Message
        private ShortBestBidUpdateMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Short Best Bid Update Message
        public static ShortBestBidUpdateMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new ShortBestBidUpdateMessage(parentElement);

            element.nanoseconds = Nanoseconds.parse(container, element);
            element.optionId = OptionId.parse(container, element);
            element.quoteCondition = QuoteCondition.parse(container, element);
            element.price2 = Price2.parse(container, element);
            element.size2 = Size2.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.nanoseconds).append("\n");
            sb.append(this.optionId).append("\n");
            sb.append(this.quoteCondition).append("\n");
            sb.append(this.price2).append("\n");
            sb.append(this.size2).append("\n");
            return sb.toString();
        }
    }

    /**
    * Short Best Ask Update Message
    */
    public static class ShortBestAskUpdateMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Nanoseconds nanoseconds;
        public OptionId optionId;
        public QuoteCondition quoteCondition;
        public Price2 price2;
        public Size2 size2;

        // constructor for Short Best Ask Update Message
        private ShortBestAskUpdateMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Short Best Ask Update Message
        public static ShortBestAskUpdateMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new ShortBestAskUpdateMessage(parentElement);

            element.nanoseconds = Nanoseconds.parse(container, element);
            element.optionId = OptionId.parse(container, element);
            element.quoteCondition = QuoteCondition.parse(container, element);
            element.price2 = Price2.parse(container, element);
            element.size2 = Size2.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.nanoseconds).append("\n");
            sb.append(this.optionId).append("\n");
            sb.append(this.quoteCondition).append("\n");
            sb.append(this.price2).append("\n");
            sb.append(this.size2).append("\n");
            return sb.toString();
        }
    }

    // Ask Size 4: 4 Byte Unsigned Fixed Width Integer
    public static class AskSize4 implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public AskSize4(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static AskSize4 parse(Container container, IBinaryProtocolElement element) {
            return new AskSize4(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "askSize4 = "+ this.value;
        }
    }

    // Ask Price 4: 4 Byte Unsigned Fixed Width Integer
    public static class AskPrice4 implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public AskPrice4(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static AskPrice4 parse(Container container, IBinaryProtocolElement element) {
            return new AskPrice4(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "askPrice4 = "+ this.value;
        }
    }

    // Bid Size 4: 4 Byte Unsigned Fixed Width Integer
    public static class BidSize4 implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public BidSize4(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static BidSize4 parse(Container container, IBinaryProtocolElement element) {
            return new BidSize4(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "bidSize4 = "+ this.value;
        }
    }

    // Bid Price 4: 4 Byte Unsigned Fixed Width Integer
    public static class BidPrice4 implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public BidPrice4(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static BidPrice4 parse(Container container, IBinaryProtocolElement element) {
            return new BidPrice4(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "bidPrice4 = "+ this.value;
        }
    }

    /**
    * Long Best Bid And Ask Update Message
    */
    public static class LongBestBidAndAskUpdateMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Nanoseconds nanoseconds;
        public OptionId optionId;
        public QuoteCondition quoteCondition;
        public BidPrice4 bidPrice4;
        public BidSize4 bidSize4;
        public AskPrice4 askPrice4;
        public AskSize4 askSize4;

        // constructor for Long Best Bid And Ask Update Message
        private LongBestBidAndAskUpdateMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Long Best Bid And Ask Update Message
        public static LongBestBidAndAskUpdateMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new LongBestBidAndAskUpdateMessage(parentElement);

            element.nanoseconds = Nanoseconds.parse(container, element);
            element.optionId = OptionId.parse(container, element);
            element.quoteCondition = QuoteCondition.parse(container, element);
            element.bidPrice4 = BidPrice4.parse(container, element);
            element.bidSize4 = BidSize4.parse(container, element);
            element.askPrice4 = AskPrice4.parse(container, element);
            element.askSize4 = AskSize4.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.nanoseconds).append("\n");
            sb.append(this.optionId).append("\n");
            sb.append(this.quoteCondition).append("\n");
            sb.append(this.bidPrice4).append("\n");
            sb.append(this.bidSize4).append("\n");
            sb.append(this.askPrice4).append("\n");
            sb.append(this.askSize4).append("\n");
            return sb.toString();
        }
    }

    // Ask Size 2: 2 Byte Unsigned Fixed Width Integer
    public static class AskSize2 implements IBinaryProtocolElement {
        public static final int LENGTH = 2;
        public final IBinaryProtocolElement parent;
        public final Short value;

        public AskSize2(Short value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static AskSize2 parse(Container container, IBinaryProtocolElement element) {
            return new AskSize2(BigEndianUtils.toShort(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "askSize2 = "+ this.value;
        }
    }

    // Ask Price 2: 2 Byte Unsigned Fixed Width Integer
    public static class AskPrice2 implements IBinaryProtocolElement {
        public static final int LENGTH = 2;
        public final IBinaryProtocolElement parent;
        public final Short value;

        public AskPrice2(Short value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static AskPrice2 parse(Container container, IBinaryProtocolElement element) {
            return new AskPrice2(BigEndianUtils.toShort(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "askPrice2 = "+ this.value;
        }
    }

    // Bid Size 2: 2 Byte Unsigned Fixed Width Integer
    public static class BidSize2 implements IBinaryProtocolElement {
        public static final int LENGTH = 2;
        public final IBinaryProtocolElement parent;
        public final Short value;

        public BidSize2(Short value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static BidSize2 parse(Container container, IBinaryProtocolElement element) {
            return new BidSize2(BigEndianUtils.toShort(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "bidSize2 = "+ this.value;
        }
    }

    // Bid Price 2: 2 Byte Unsigned Fixed Width Integer
    public static class BidPrice2 implements IBinaryProtocolElement {
        public static final int LENGTH = 2;
        public final IBinaryProtocolElement parent;
        public final Short value;

        public BidPrice2(Short value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static BidPrice2 parse(Container container, IBinaryProtocolElement element) {
            return new BidPrice2(BigEndianUtils.toShort(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "bidPrice2 = "+ this.value;
        }
    }

    /**
    * Short Best Bid And Ask Update Message
    */
    public static class ShortBestBidAndAskUpdateMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Nanoseconds nanoseconds;
        public OptionId optionId;
        public QuoteCondition quoteCondition;
        public BidPrice2 bidPrice2;
        public BidSize2 bidSize2;
        public AskPrice2 askPrice2;
        public AskSize2 askSize2;

        // constructor for Short Best Bid And Ask Update Message
        private ShortBestBidAndAskUpdateMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Short Best Bid And Ask Update Message
        public static ShortBestBidAndAskUpdateMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new ShortBestBidAndAskUpdateMessage(parentElement);

            element.nanoseconds = Nanoseconds.parse(container, element);
            element.optionId = OptionId.parse(container, element);
            element.quoteCondition = QuoteCondition.parse(container, element);
            element.bidPrice2 = BidPrice2.parse(container, element);
            element.bidSize2 = BidSize2.parse(container, element);
            element.askPrice2 = AskPrice2.parse(container, element);
            element.askSize2 = AskSize2.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.nanoseconds).append("\n");
            sb.append(this.optionId).append("\n");
            sb.append(this.quoteCondition).append("\n");
            sb.append(this.bidPrice2).append("\n");
            sb.append(this.bidSize2).append("\n");
            sb.append(this.askPrice2).append("\n");
            sb.append(this.askSize2).append("\n");
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
    * Security Open Closed Message
    */
    public static class SecurityOpenClosedMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Nanoseconds nanoseconds;
        public OptionId optionId;
        public OpenState openState;

        // constructor for Security Open Closed Message
        private SecurityOpenClosedMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Security Open Closed Message
        public static SecurityOpenClosedMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new SecurityOpenClosedMessage(parentElement);

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
        public Mpv mpv;

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
            element.mpv = Mpv.parse(container, element);

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
            sb.append(this.mpv).append("\n");
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
    * Timestamp Message
    */
    public static class TimestampMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Second second;

        // constructor for Timestamp Message
        private TimestampMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Timestamp Message
        public static TimestampMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new TimestampMessage(parentElement);

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
        // -- parsing Security Open Closed Message
        if (messageType.enumType == 'O') {
            return SecurityOpenClosedMessage.parse(container, null);
        }
        // -- parsing Short Best Bid And Ask Update Message
        if (messageType.enumType == 'q') {
            return ShortBestBidAndAskUpdateMessage.parse(container, null);
        }
        // -- parsing Long Best Bid And Ask Update Message
        if (messageType.enumType == 'Q') {
            return LongBestBidAndAskUpdateMessage.parse(container, null);
        }
        // -- parsing Short Best Ask Update Message
        if (messageType.enumType == 'a') {
            return ShortBestAskUpdateMessage.parse(container, null);
        }
        // -- parsing Short Best Bid Update Message
        if (messageType.enumType == 'b') {
            return ShortBestBidUpdateMessage.parse(container, null);
        }
        // -- parsing Long Best Ask Update Message
        if (messageType.enumType == 'A') {
            return LongBestAskUpdateMessage.parse(container, null);
        }
        // -- parsing Long Best Bid Update Message
        if (messageType.enumType == 'B') {
            return LongBestBidUpdateMessage.parse(container, null);
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
