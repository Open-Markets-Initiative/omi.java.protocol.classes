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
* Java parser for Asx Securities Itch SR8 2.0 protocol
* 
* @version 1.0
* @since 04/25/2021 15:26:15
*/

public class AsxSecuritiesSR8Itchv20 {

    ///////////////////////////////////////////////////////////////////////
    // Enum Values
    ///////////////////////////////////////////////////////////////////////

    /**
    * Event Code Values
    */
    public enum ENUM_EVENT_CODE {
        E_O('O', "Start Of Messages"),
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
    * Exchange Order Type Values
    */
    public enum ENUM_EXCHANGE_ORDER_TYPE {
        E_4(4, "Market Bid"),
        E_8(8, "Price Stabilisation"),
        E_32(32, "Undisclosed");

        public final Integer enumType;
        public final String enumValue;

        ENUM_EXCHANGE_ORDER_TYPE(Integer enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Integer, ENUM_EXCHANGE_ORDER_TYPE> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_EXCHANGE_ORDER_TYPE s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_EXCHANGE_ORDER_TYPE valueOf(Integer s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Financial Product Values
    */
    public enum ENUM_FINANCIAL_PRODUCT {
        E_1(1, "Option"),
        E_3(3, "Future"),
        E_5(5, "Cash"),
        E_11(11, "Standard Combination");

        public final Integer enumType;
        public final String enumValue;

        ENUM_FINANCIAL_PRODUCT(Integer enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Integer, ENUM_FINANCIAL_PRODUCT> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_FINANCIAL_PRODUCT s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_FINANCIAL_PRODUCT valueOf(Integer s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Leg 1 Side Values
    */
    public enum ENUM_LEG_1_SIDE {
        E_B('B', "Buy Leg"),
        E_C('C', "Sell Leg");

        public final Character enumType;
        public final String enumValue;

        ENUM_LEG_1_SIDE(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_LEG_1_SIDE> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_LEG_1_SIDE s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_LEG_1_SIDE valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Leg 2 Side Values
    */
    public enum ENUM_LEG_2_SIDE {
        E_B('B', "Buy Leg"),
        E_C('C', "Sell Leg");

        public final Character enumType;
        public final String enumValue;

        ENUM_LEG_2_SIDE(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_LEG_2_SIDE> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_LEG_2_SIDE s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_LEG_2_SIDE valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Leg 3 Side Values
    */
    public enum ENUM_LEG_3_SIDE {
        E_B('B', "Buy Leg"),
        E_C('C', "Sell Leg"),
        E_QUESTION_MARK('?', "Not Defined");

        public final Character enumType;
        public final String enumValue;

        ENUM_LEG_3_SIDE(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_LEG_3_SIDE> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_LEG_3_SIDE s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_LEG_3_SIDE valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Leg 4 Side Values
    */
    public enum ENUM_LEG_4_SIDE {
        E_B('B', "Buy Leg"),
        E_C('C', "Sell Leg"),
        E_QUESTION_MARK('?', "Not Defined");

        public final Character enumType;
        public final String enumValue;

        ENUM_LEG_4_SIDE(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_LEG_4_SIDE> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_LEG_4_SIDE s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_LEG_4_SIDE valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Lot Type Values
    */
    public enum ENUM_LOT_TYPE {
        E_0(0, "Undefined"),
        E_1(1, "Odd Lot"),
        E_2(2, "Round Lot"),
        E_3(3, "Block Lot"),
        E_4(4, "All Or None Lot");

        public final Integer enumType;
        public final String enumValue;

        ENUM_LOT_TYPE(Integer enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Integer, ENUM_LOT_TYPE> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_LOT_TYPE s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_LOT_TYPE valueOf(Integer s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Message Type Values
    */
    public enum ENUM_MESSAGE_TYPE {
        E_T('T', "Seconds Message"),
        E_R('R', "Order Book Directory Message"),
        E_M('M', "Combination Order Book Directory Message"),
        E_L('L', "Tick Size Message"),
        E_S('S', "System Event Message"),
        E_O('O', "Order Book State Message"),
        E_A('A', "Add Order No Participant Id Message"),
        E_F('F', "Add Order Participant Id Message"),
        E_E('E', "Order Executed Message"),
        E_C('C', "Order Executed With Price Message"),
        E_U('U', "Order Replace Message"),
        E_D('D', "Order Delete Message"),
        E_P('P', "Trade Message"),
        E_Z('Z', "Equilibrium Price Update Message");

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
    * Occurred At Cross Values
    */
    public enum ENUM_OCCURRED_AT_CROSS {
        E_Y('Y', "Yes"),
        E_N('N', "No");

        public final Character enumType;
        public final String enumValue;

        ENUM_OCCURRED_AT_CROSS(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_OCCURRED_AT_CROSS> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_OCCURRED_AT_CROSS s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_OCCURRED_AT_CROSS valueOf(Character s)  {
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


    // Best Ask Quantity: 8 Byte Unsigned Fixed Width Integer
    public static class BestAskQuantity implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public BestAskQuantity(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static BestAskQuantity parse(Container container, IBinaryProtocolElement element) {
            return new BestAskQuantity(BigEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "bestAskQuantity = "+ this.value;
        }
    }

    // Best Bid Quantity: 8 Byte Unsigned Fixed Width Integer
    public static class BestBidQuantity implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public BestBidQuantity(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static BestBidQuantity parse(Container container, IBinaryProtocolElement element) {
            return new BestBidQuantity(BigEndianUtils.toLong(container, LENGTH), element);
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

    // Order Book Id: 4 Byte Unsigned Fixed Width Integer
    public static class OrderBookId implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public OrderBookId(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static OrderBookId parse(Container container, IBinaryProtocolElement element) {
            return new OrderBookId(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "orderBookId = "+ this.value;
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
    * Equilibrium Price Update Message
    */
    public static class EquilibriumPriceUpdateMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public TimestampNanoseconds timestampNanoseconds;
        public OrderBookId orderBookId;
        public BidQuantity bidQuantity;
        public AskQuantity askQuantity;
        public EquilibriumPrice equilibriumPrice;
        public BestBidPrice bestBidPrice;
        public BestAskPrice bestAskPrice;
        public BestBidQuantity bestBidQuantity;
        public BestAskQuantity bestAskQuantity;

        // constructor for Equilibrium Price Update Message
        private EquilibriumPriceUpdateMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Equilibrium Price Update Message
        public static EquilibriumPriceUpdateMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new EquilibriumPriceUpdateMessage(parentElement);

            element.timestampNanoseconds = TimestampNanoseconds.parse(container, element);
            element.orderBookId = OrderBookId.parse(container, element);
            element.bidQuantity = BidQuantity.parse(container, element);
            element.askQuantity = AskQuantity.parse(container, element);
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
            sb.append(this.timestampNanoseconds).append("\n");
            sb.append(this.orderBookId).append("\n");
            sb.append(this.bidQuantity).append("\n");
            sb.append(this.askQuantity).append("\n");
            sb.append(this.equilibriumPrice).append("\n");
            sb.append(this.bestBidPrice).append("\n");
            sb.append(this.bestAskPrice).append("\n");
            sb.append(this.bestBidQuantity).append("\n");
            sb.append(this.bestAskQuantity).append("\n");
            return sb.toString();
        }
    }

    // Occurred At Cross: 1 Byte Ascii String Enum with 2 values
    public static class OccurredAtCross implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_OCCURRED_AT_CROSS value;

        public OccurredAtCross(ENUM_OCCURRED_AT_CROSS value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static OccurredAtCross parse(Container container, IBinaryProtocolElement element) {
            return new OccurredAtCross(ENUM_OCCURRED_AT_CROSS.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "occurredAtCross = "+ this.value.enumType +" ("+ this.value.enumValue +")";
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

    // Participant Id Counterparty: 7 Byte Ascii String
    public static class ParticipantIdCounterparty implements IBinaryProtocolElement {
        public static final int LENGTH = 7;
        public final IBinaryProtocolElement parent;
        public final String value;

        public ParticipantIdCounterparty(String value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static ParticipantIdCounterparty parse(Container container, IBinaryProtocolElement element) {
            return new ParticipantIdCounterparty(BigEndianUtils.toString(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "participantIdCounterparty = "+ this.value;
        }
    }

    // Participant Id Owner: 7 Byte Ascii String
    public static class ParticipantIdOwner implements IBinaryProtocolElement {
        public static final int LENGTH = 7;
        public final IBinaryProtocolElement parent;
        public final String value;

        public ParticipantIdOwner(String value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static ParticipantIdOwner parse(Container container, IBinaryProtocolElement element) {
            return new ParticipantIdOwner(BigEndianUtils.toString(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "participantIdOwner = "+ this.value;
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

    // Quantity: 8 Byte Unsigned Fixed Width Integer
    public static class Quantity implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public Quantity(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Quantity parse(Container container, IBinaryProtocolElement element) {
            return new Quantity(BigEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "quantity = "+ this.value;
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

    // Match Id: 12 Byte Unsigned Fixed Width Integer
    public static class MatchId implements IBinaryProtocolElement {
        public static final int LENGTH = 12;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public MatchId(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static MatchId parse(Container container, IBinaryProtocolElement element) {
            return new MatchId(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "matchId = "+ this.value;
        }
    }

    /**
    * Trade Message
    */
    public static class TradeMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public TimestampNanoseconds timestampNanoseconds;
        public MatchId matchId;
        public Side side;
        public Quantity quantity;
        public OrderBookId orderBookId;
        public TradePrice tradePrice;
        public ParticipantIdOwner participantIdOwner;
        public ParticipantIdCounterparty participantIdCounterparty;
        public Printable printable;
        public OccurredAtCross occurredAtCross;

        // constructor for Trade Message
        private TradeMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Trade Message
        public static TradeMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new TradeMessage(parentElement);

            element.timestampNanoseconds = TimestampNanoseconds.parse(container, element);
            element.matchId = MatchId.parse(container, element);
            element.side = Side.parse(container, element);
            element.quantity = Quantity.parse(container, element);
            element.orderBookId = OrderBookId.parse(container, element);
            element.tradePrice = TradePrice.parse(container, element);
            element.participantIdOwner = ParticipantIdOwner.parse(container, element);
            element.participantIdCounterparty = ParticipantIdCounterparty.parse(container, element);
            element.printable = Printable.parse(container, element);
            element.occurredAtCross = OccurredAtCross.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestampNanoseconds).append("\n");
            sb.append(this.matchId).append("\n");
            sb.append(this.side).append("\n");
            sb.append(this.quantity).append("\n");
            sb.append(this.orderBookId).append("\n");
            sb.append(this.tradePrice).append("\n");
            sb.append(this.participantIdOwner).append("\n");
            sb.append(this.participantIdCounterparty).append("\n");
            sb.append(this.printable).append("\n");
            sb.append(this.occurredAtCross).append("\n");
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

    /**
    * Order Delete Message
    */
    public static class OrderDeleteMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public TimestampNanoseconds timestampNanoseconds;
        public OrderId orderId;
        public OrderBookId orderBookId;
        public Side side;

        // constructor for Order Delete Message
        private OrderDeleteMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Order Delete Message
        public static OrderDeleteMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new OrderDeleteMessage(parentElement);

            element.timestampNanoseconds = TimestampNanoseconds.parse(container, element);
            element.orderId = OrderId.parse(container, element);
            element.orderBookId = OrderBookId.parse(container, element);
            element.side = Side.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestampNanoseconds).append("\n");
            sb.append(this.orderId).append("\n");
            sb.append(this.orderBookId).append("\n");
            sb.append(this.side).append("\n");
            return sb.toString();
        }
    }

    // Exchange Order Type: 2 Byte Unsigned Fixed Width Integer Enum with 3 values
    public static class ExchangeOrderType implements IBinaryProtocolElement {
        public static final int LENGTH = 2;
        public final IBinaryProtocolElement parent;
        public final ENUM_EXCHANGE_ORDER_TYPE value;

        public ExchangeOrderType(ENUM_EXCHANGE_ORDER_TYPE value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static ExchangeOrderType parse(Container container, IBinaryProtocolElement element) {
            return new ExchangeOrderType(ENUM_EXCHANGE_ORDER_TYPE.valueOf((int)BigEndianUtils.toShort(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "exchangeOrderType = "+ this.value.enumType +" ("+ this.value.enumValue +")";
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

    // New Order Book Position: 4 Byte Unsigned Fixed Width Integer
    public static class NewOrderBookPosition implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public NewOrderBookPosition(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static NewOrderBookPosition parse(Container container, IBinaryProtocolElement element) {
            return new NewOrderBookPosition(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "newOrderBookPosition = "+ this.value;
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
        public OrderId orderId;
        public OrderBookId orderBookId;
        public Side side;
        public NewOrderBookPosition newOrderBookPosition;
        public Quantity quantity;
        public Price price;
        public ExchangeOrderType exchangeOrderType;

        // constructor for Order Replace Message
        private OrderReplaceMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Order Replace Message
        public static OrderReplaceMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new OrderReplaceMessage(parentElement);

            element.timestampNanoseconds = TimestampNanoseconds.parse(container, element);
            element.orderId = OrderId.parse(container, element);
            element.orderBookId = OrderBookId.parse(container, element);
            element.side = Side.parse(container, element);
            element.newOrderBookPosition = NewOrderBookPosition.parse(container, element);
            element.quantity = Quantity.parse(container, element);
            element.price = Price.parse(container, element);
            element.exchangeOrderType = ExchangeOrderType.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestampNanoseconds).append("\n");
            sb.append(this.orderId).append("\n");
            sb.append(this.orderBookId).append("\n");
            sb.append(this.side).append("\n");
            sb.append(this.newOrderBookPosition).append("\n");
            sb.append(this.quantity).append("\n");
            sb.append(this.price).append("\n");
            sb.append(this.exchangeOrderType).append("\n");
            return sb.toString();
        }
    }

    // Executed Quantity: 8 Byte Unsigned Fixed Width Integer
    public static class ExecutedQuantity implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public ExecutedQuantity(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static ExecutedQuantity parse(Container container, IBinaryProtocolElement element) {
            return new ExecutedQuantity(BigEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "executedQuantity = "+ this.value;
        }
    }

    /**
    * Order Executed With Price Message
    */
    public static class OrderExecutedWithPriceMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public TimestampNanoseconds timestampNanoseconds;
        public OrderId orderId;
        public OrderBookId orderBookId;
        public Side side;
        public ExecutedQuantity executedQuantity;
        public MatchId matchId;
        public ParticipantIdOwner participantIdOwner;
        public ParticipantIdCounterparty participantIdCounterparty;
        public TradePrice tradePrice;
        public OccurredAtCross occurredAtCross;
        public Printable printable;

        // constructor for Order Executed With Price Message
        private OrderExecutedWithPriceMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Order Executed With Price Message
        public static OrderExecutedWithPriceMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new OrderExecutedWithPriceMessage(parentElement);

            element.timestampNanoseconds = TimestampNanoseconds.parse(container, element);
            element.orderId = OrderId.parse(container, element);
            element.orderBookId = OrderBookId.parse(container, element);
            element.side = Side.parse(container, element);
            element.executedQuantity = ExecutedQuantity.parse(container, element);
            element.matchId = MatchId.parse(container, element);
            element.participantIdOwner = ParticipantIdOwner.parse(container, element);
            element.participantIdCounterparty = ParticipantIdCounterparty.parse(container, element);
            element.tradePrice = TradePrice.parse(container, element);
            element.occurredAtCross = OccurredAtCross.parse(container, element);
            element.printable = Printable.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestampNanoseconds).append("\n");
            sb.append(this.orderId).append("\n");
            sb.append(this.orderBookId).append("\n");
            sb.append(this.side).append("\n");
            sb.append(this.executedQuantity).append("\n");
            sb.append(this.matchId).append("\n");
            sb.append(this.participantIdOwner).append("\n");
            sb.append(this.participantIdCounterparty).append("\n");
            sb.append(this.tradePrice).append("\n");
            sb.append(this.occurredAtCross).append("\n");
            sb.append(this.printable).append("\n");
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
        public TimestampNanoseconds timestampNanoseconds;
        public OrderId orderId;
        public OrderBookId orderBookId;
        public Side side;
        public ExecutedQuantity executedQuantity;
        public MatchId matchId;
        public ParticipantIdOwner participantIdOwner;
        public ParticipantIdCounterparty participantIdCounterparty;

        // constructor for Order Executed Message
        private OrderExecutedMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Order Executed Message
        public static OrderExecutedMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new OrderExecutedMessage(parentElement);

            element.timestampNanoseconds = TimestampNanoseconds.parse(container, element);
            element.orderId = OrderId.parse(container, element);
            element.orderBookId = OrderBookId.parse(container, element);
            element.side = Side.parse(container, element);
            element.executedQuantity = ExecutedQuantity.parse(container, element);
            element.matchId = MatchId.parse(container, element);
            element.participantIdOwner = ParticipantIdOwner.parse(container, element);
            element.participantIdCounterparty = ParticipantIdCounterparty.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestampNanoseconds).append("\n");
            sb.append(this.orderId).append("\n");
            sb.append(this.orderBookId).append("\n");
            sb.append(this.side).append("\n");
            sb.append(this.executedQuantity).append("\n");
            sb.append(this.matchId).append("\n");
            sb.append(this.participantIdOwner).append("\n");
            sb.append(this.participantIdCounterparty).append("\n");
            return sb.toString();
        }
    }

    // Participant Id: 7 Byte Ascii String
    public static class ParticipantId implements IBinaryProtocolElement {
        public static final int LENGTH = 7;
        public final IBinaryProtocolElement parent;
        public final String value;

        public ParticipantId(String value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static ParticipantId parse(Container container, IBinaryProtocolElement element) {
            return new ParticipantId(BigEndianUtils.toString(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "participantId = "+ this.value;
        }
    }

    // Lot Type: 1 Byte Unsigned Fixed Width Integer Enum with 5 values
    public static class LotType implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_LOT_TYPE value;

        public LotType(ENUM_LOT_TYPE value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static LotType parse(Container container, IBinaryProtocolElement element) {
            return new LotType(ENUM_LOT_TYPE.valueOf((int)BigEndianUtils.toByte(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "lotType = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Order Book Position: 4 Byte Unsigned Fixed Width Integer
    public static class OrderBookPosition implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public OrderBookPosition(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static OrderBookPosition parse(Container container, IBinaryProtocolElement element) {
            return new OrderBookPosition(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "orderBookPosition = "+ this.value;
        }
    }

    /**
    * Add Order Participant Id Message
    */
    public static class AddOrderParticipantIdMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public TimestampNanoseconds timestampNanoseconds;
        public OrderId orderId;
        public OrderBookId orderBookId;
        public Side side;
        public OrderBookPosition orderBookPosition;
        public Quantity quantity;
        public Price price;
        public ExchangeOrderType exchangeOrderType;
        public LotType lotType;
        public ParticipantId participantId;

        // constructor for Add Order Participant Id Message
        private AddOrderParticipantIdMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Add Order Participant Id Message
        public static AddOrderParticipantIdMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new AddOrderParticipantIdMessage(parentElement);

            element.timestampNanoseconds = TimestampNanoseconds.parse(container, element);
            element.orderId = OrderId.parse(container, element);
            element.orderBookId = OrderBookId.parse(container, element);
            element.side = Side.parse(container, element);
            element.orderBookPosition = OrderBookPosition.parse(container, element);
            element.quantity = Quantity.parse(container, element);
            element.price = Price.parse(container, element);
            element.exchangeOrderType = ExchangeOrderType.parse(container, element);
            element.lotType = LotType.parse(container, element);
            element.participantId = ParticipantId.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestampNanoseconds).append("\n");
            sb.append(this.orderId).append("\n");
            sb.append(this.orderBookId).append("\n");
            sb.append(this.side).append("\n");
            sb.append(this.orderBookPosition).append("\n");
            sb.append(this.quantity).append("\n");
            sb.append(this.price).append("\n");
            sb.append(this.exchangeOrderType).append("\n");
            sb.append(this.lotType).append("\n");
            sb.append(this.participantId).append("\n");
            return sb.toString();
        }
    }

    /**
    * Add Order No Participant Id Message
    */
    public static class AddOrderNoParticipantIdMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public TimestampNanoseconds timestampNanoseconds;
        public OrderId orderId;
        public OrderBookId orderBookId;
        public Side side;
        public OrderBookPosition orderBookPosition;
        public Quantity quantity;
        public Price price;
        public ExchangeOrderType exchangeOrderType;
        public LotType lotType;

        // constructor for Add Order No Participant Id Message
        private AddOrderNoParticipantIdMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Add Order No Participant Id Message
        public static AddOrderNoParticipantIdMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new AddOrderNoParticipantIdMessage(parentElement);

            element.timestampNanoseconds = TimestampNanoseconds.parse(container, element);
            element.orderId = OrderId.parse(container, element);
            element.orderBookId = OrderBookId.parse(container, element);
            element.side = Side.parse(container, element);
            element.orderBookPosition = OrderBookPosition.parse(container, element);
            element.quantity = Quantity.parse(container, element);
            element.price = Price.parse(container, element);
            element.exchangeOrderType = ExchangeOrderType.parse(container, element);
            element.lotType = LotType.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestampNanoseconds).append("\n");
            sb.append(this.orderId).append("\n");
            sb.append(this.orderBookId).append("\n");
            sb.append(this.side).append("\n");
            sb.append(this.orderBookPosition).append("\n");
            sb.append(this.quantity).append("\n");
            sb.append(this.price).append("\n");
            sb.append(this.exchangeOrderType).append("\n");
            sb.append(this.lotType).append("\n");
            return sb.toString();
        }
    }

    // State Name: 20 Byte Ascii String
    public static class StateName implements IBinaryProtocolElement {
        public static final int LENGTH = 20;
        public final IBinaryProtocolElement parent;
        public final String value;

        public StateName(String value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static StateName parse(Container container, IBinaryProtocolElement element) {
            return new StateName(BigEndianUtils.toString(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "stateName = "+ this.value;
        }
    }

    /**
    * Order Book State Message
    */
    public static class OrderBookStateMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public TimestampNanoseconds timestampNanoseconds;
        public OrderBookId orderBookId;
        public StateName stateName;

        // constructor for Order Book State Message
        private OrderBookStateMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Order Book State Message
        public static OrderBookStateMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new OrderBookStateMessage(parentElement);

            element.timestampNanoseconds = TimestampNanoseconds.parse(container, element);
            element.orderBookId = OrderBookId.parse(container, element);
            element.stateName = StateName.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestampNanoseconds).append("\n");
            sb.append(this.orderBookId).append("\n");
            sb.append(this.stateName).append("\n");
            return sb.toString();
        }
    }

    // Event Code: 1 Byte Ascii String Enum with 2 values
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
        public TimestampNanoseconds timestampNanoseconds;
        public EventCode eventCode;

        // constructor for System Event Message
        private SystemEventMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for System Event Message
        public static SystemEventMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new SystemEventMessage(parentElement);

            element.timestampNanoseconds = TimestampNanoseconds.parse(container, element);
            element.eventCode = EventCode.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestampNanoseconds).append("\n");
            sb.append(this.eventCode).append("\n");
            return sb.toString();
        }
    }

    // Price To: 4 Byte Signed Fixed Width Integer
    public static class PriceTo implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public PriceTo(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static PriceTo parse(Container container, IBinaryProtocolElement element) {
            return new PriceTo(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "priceTo = "+ this.value;
        }
    }

    // Price From: 4 Byte Signed Fixed Width Integer
    public static class PriceFrom implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public PriceFrom(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static PriceFrom parse(Container container, IBinaryProtocolElement element) {
            return new PriceFrom(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "priceFrom = "+ this.value;
        }
    }

    // Tick Size: 8 Byte Unsigned Fixed Width Integer
    public static class TickSize implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public TickSize(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static TickSize parse(Container container, IBinaryProtocolElement element) {
            return new TickSize(BigEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "tickSize = "+ this.value;
        }
    }

    /**
    * Tick Size Message
    */
    public static class TickSizeMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public TimestampNanoseconds timestampNanoseconds;
        public OrderBookId orderBookId;
        public TickSize tickSize;
        public PriceFrom priceFrom;
        public PriceTo priceTo;

        // constructor for Tick Size Message
        private TickSizeMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Tick Size Message
        public static TickSizeMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new TickSizeMessage(parentElement);

            element.timestampNanoseconds = TimestampNanoseconds.parse(container, element);
            element.orderBookId = OrderBookId.parse(container, element);
            element.tickSize = TickSize.parse(container, element);
            element.priceFrom = PriceFrom.parse(container, element);
            element.priceTo = PriceTo.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestampNanoseconds).append("\n");
            sb.append(this.orderBookId).append("\n");
            sb.append(this.tickSize).append("\n");
            sb.append(this.priceFrom).append("\n");
            sb.append(this.priceTo).append("\n");
            return sb.toString();
        }
    }

    // Leg 4 Ratio: 4 Byte Unsigned Fixed Width Integer
    public static class Leg4Ratio implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public Leg4Ratio(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Leg4Ratio parse(Container container, IBinaryProtocolElement element) {
            return new Leg4Ratio(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "leg4Ratio = "+ this.value;
        }
    }

    // Leg 4 Side: 1 Byte Ascii String Enum with 3 values
    public static class Leg4Side implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_LEG_4_SIDE value;

        public Leg4Side(ENUM_LEG_4_SIDE value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Leg4Side parse(Container container, IBinaryProtocolElement element) {
            return new Leg4Side(ENUM_LEG_4_SIDE.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "leg4Side = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Leg 4 Symbol: 32 Byte Ascii String
    public static class Leg4Symbol implements IBinaryProtocolElement {
        public static final int LENGTH = 32;
        public final IBinaryProtocolElement parent;
        public final String value;

        public Leg4Symbol(String value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Leg4Symbol parse(Container container, IBinaryProtocolElement element) {
            return new Leg4Symbol(BigEndianUtils.toString(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "leg4Symbol = "+ this.value;
        }
    }

    // Leg 3 Ratio: 4 Byte Unsigned Fixed Width Integer
    public static class Leg3Ratio implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public Leg3Ratio(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Leg3Ratio parse(Container container, IBinaryProtocolElement element) {
            return new Leg3Ratio(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "leg3Ratio = "+ this.value;
        }
    }

    // Leg 3 Side: 1 Byte Ascii String Enum with 3 values
    public static class Leg3Side implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_LEG_3_SIDE value;

        public Leg3Side(ENUM_LEG_3_SIDE value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Leg3Side parse(Container container, IBinaryProtocolElement element) {
            return new Leg3Side(ENUM_LEG_3_SIDE.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "leg3Side = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Leg 3 Symbol: 32 Byte Ascii String
    public static class Leg3Symbol implements IBinaryProtocolElement {
        public static final int LENGTH = 32;
        public final IBinaryProtocolElement parent;
        public final String value;

        public Leg3Symbol(String value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Leg3Symbol parse(Container container, IBinaryProtocolElement element) {
            return new Leg3Symbol(BigEndianUtils.toString(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "leg3Symbol = "+ this.value;
        }
    }

    // Leg 2 Ratio: 4 Byte Unsigned Fixed Width Integer
    public static class Leg2Ratio implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public Leg2Ratio(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Leg2Ratio parse(Container container, IBinaryProtocolElement element) {
            return new Leg2Ratio(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "leg2Ratio = "+ this.value;
        }
    }

    // Leg 2 Side: 1 Byte Ascii String Enum with 2 values
    public static class Leg2Side implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_LEG_2_SIDE value;

        public Leg2Side(ENUM_LEG_2_SIDE value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Leg2Side parse(Container container, IBinaryProtocolElement element) {
            return new Leg2Side(ENUM_LEG_2_SIDE.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "leg2Side = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Leg 2 Symbol: 32 Byte Ascii String
    public static class Leg2Symbol implements IBinaryProtocolElement {
        public static final int LENGTH = 32;
        public final IBinaryProtocolElement parent;
        public final String value;

        public Leg2Symbol(String value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Leg2Symbol parse(Container container, IBinaryProtocolElement element) {
            return new Leg2Symbol(BigEndianUtils.toString(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "leg2Symbol = "+ this.value;
        }
    }

    // Leg 1 Ratio: 4 Byte Unsigned Fixed Width Integer
    public static class Leg1Ratio implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public Leg1Ratio(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Leg1Ratio parse(Container container, IBinaryProtocolElement element) {
            return new Leg1Ratio(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "leg1Ratio = "+ this.value;
        }
    }

    // Leg 1 Side: 1 Byte Ascii String Enum with 2 values
    public static class Leg1Side implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_LEG_1_SIDE value;

        public Leg1Side(ENUM_LEG_1_SIDE value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Leg1Side parse(Container container, IBinaryProtocolElement element) {
            return new Leg1Side(ENUM_LEG_1_SIDE.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "leg1Side = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Leg 1 Symbol: 32 Byte Ascii String
    public static class Leg1Symbol implements IBinaryProtocolElement {
        public static final int LENGTH = 32;
        public final IBinaryProtocolElement parent;
        public final String value;

        public Leg1Symbol(String value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Leg1Symbol parse(Container container, IBinaryProtocolElement element) {
            return new Leg1Symbol(BigEndianUtils.toString(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "leg1Symbol = "+ this.value;
        }
    }

    // Nominal Value: 8 Byte Unsigned Fixed Width Integer
    public static class NominalValue implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public NominalValue(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static NominalValue parse(Container container, IBinaryProtocolElement element) {
            return new NominalValue(BigEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "nominalValue = "+ this.value;
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

    // Odd Lot Size: 4 Byte Unsigned Fixed Width Integer
    public static class OddLotSize implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public OddLotSize(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static OddLotSize parse(Container container, IBinaryProtocolElement element) {
            return new OddLotSize(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "oddLotSize = "+ this.value;
        }
    }

    // Number Of Decimals In Nominal Value: 2 Byte Unsigned Fixed Width Integer
    public static class NumberOfDecimalsInNominalValue implements IBinaryProtocolElement {
        public static final int LENGTH = 2;
        public final IBinaryProtocolElement parent;
        public final Short value;

        public NumberOfDecimalsInNominalValue(Short value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static NumberOfDecimalsInNominalValue parse(Container container, IBinaryProtocolElement element) {
            return new NumberOfDecimalsInNominalValue(BigEndianUtils.toShort(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "numberOfDecimalsInNominalValue = "+ this.value;
        }
    }

    // Number Of Decimals In Price: 2 Byte Unsigned Fixed Width Integer
    public static class NumberOfDecimalsInPrice implements IBinaryProtocolElement {
        public static final int LENGTH = 2;
        public final IBinaryProtocolElement parent;
        public final Short value;

        public NumberOfDecimalsInPrice(Short value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static NumberOfDecimalsInPrice parse(Container container, IBinaryProtocolElement element) {
            return new NumberOfDecimalsInPrice(BigEndianUtils.toShort(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "numberOfDecimalsInPrice = "+ this.value;
        }
    }

    // Trading Currency: 3 Byte Ascii String
    public static class TradingCurrency implements IBinaryProtocolElement {
        public static final int LENGTH = 3;
        public final IBinaryProtocolElement parent;
        public final String value;

        public TradingCurrency(String value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static TradingCurrency parse(Container container, IBinaryProtocolElement element) {
            return new TradingCurrency(BigEndianUtils.toString(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "tradingCurrency = "+ this.value;
        }
    }

    // Financial Product: 1 Byte Unsigned Fixed Width Integer Enum with 4 values
    public static class FinancialProduct implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_FINANCIAL_PRODUCT value;

        public FinancialProduct(ENUM_FINANCIAL_PRODUCT value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static FinancialProduct parse(Container container, IBinaryProtocolElement element) {
            return new FinancialProduct(ENUM_FINANCIAL_PRODUCT.valueOf((int)BigEndianUtils.toByte(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "financialProduct = "+ this.value.enumType +" ("+ this.value.enumValue +")";
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

    // Long Name: 32 Byte Ascii String
    public static class LongName implements IBinaryProtocolElement {
        public static final int LENGTH = 32;
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

    // Symbol: 32 Byte Ascii String
    public static class Symbol implements IBinaryProtocolElement {
        public static final int LENGTH = 32;
        public final IBinaryProtocolElement parent;
        public final String value;

        public Symbol(String value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Symbol parse(Container container, IBinaryProtocolElement element) {
            return new Symbol(BigEndianUtils.toString(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "symbol = "+ this.value;
        }
    }

    /**
    * Combination Order Book Directory Message
    */
    public static class CombinationOrderBookDirectoryMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public TimestampNanoseconds timestampNanoseconds;
        public OrderBookId orderBookId;
        public Symbol symbol;
        public LongName longName;
        public Isin isin;
        public FinancialProduct financialProduct;
        public TradingCurrency tradingCurrency;
        public NumberOfDecimalsInPrice numberOfDecimalsInPrice;
        public NumberOfDecimalsInNominalValue numberOfDecimalsInNominalValue;
        public OddLotSize oddLotSize;
        public RoundLotSize roundLotSize;
        public BlockLotSize blockLotSize;
        public NominalValue nominalValue;
        public Leg1Symbol leg1Symbol;
        public Leg1Side leg1Side;
        public Leg1Ratio leg1Ratio;
        public Leg2Symbol leg2Symbol;
        public Leg2Side leg2Side;
        public Leg2Ratio leg2Ratio;
        public Leg3Symbol leg3Symbol;
        public Leg3Side leg3Side;
        public Leg3Ratio leg3Ratio;
        public Leg4Symbol leg4Symbol;
        public Leg4Side leg4Side;
        public Leg4Ratio leg4Ratio;

        // constructor for Combination Order Book Directory Message
        private CombinationOrderBookDirectoryMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Combination Order Book Directory Message
        public static CombinationOrderBookDirectoryMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new CombinationOrderBookDirectoryMessage(parentElement);

            element.timestampNanoseconds = TimestampNanoseconds.parse(container, element);
            element.orderBookId = OrderBookId.parse(container, element);
            element.symbol = Symbol.parse(container, element);
            element.longName = LongName.parse(container, element);
            element.isin = Isin.parse(container, element);
            element.financialProduct = FinancialProduct.parse(container, element);
            element.tradingCurrency = TradingCurrency.parse(container, element);
            element.numberOfDecimalsInPrice = NumberOfDecimalsInPrice.parse(container, element);
            element.numberOfDecimalsInNominalValue = NumberOfDecimalsInNominalValue.parse(container, element);
            element.oddLotSize = OddLotSize.parse(container, element);
            element.roundLotSize = RoundLotSize.parse(container, element);
            element.blockLotSize = BlockLotSize.parse(container, element);
            element.nominalValue = NominalValue.parse(container, element);
            element.leg1Symbol = Leg1Symbol.parse(container, element);
            element.leg1Side = Leg1Side.parse(container, element);
            element.leg1Ratio = Leg1Ratio.parse(container, element);
            element.leg2Symbol = Leg2Symbol.parse(container, element);
            element.leg2Side = Leg2Side.parse(container, element);
            element.leg2Ratio = Leg2Ratio.parse(container, element);
            element.leg3Symbol = Leg3Symbol.parse(container, element);
            element.leg3Side = Leg3Side.parse(container, element);
            element.leg3Ratio = Leg3Ratio.parse(container, element);
            element.leg4Symbol = Leg4Symbol.parse(container, element);
            element.leg4Side = Leg4Side.parse(container, element);
            element.leg4Ratio = Leg4Ratio.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestampNanoseconds).append("\n");
            sb.append(this.orderBookId).append("\n");
            sb.append(this.symbol).append("\n");
            sb.append(this.longName).append("\n");
            sb.append(this.isin).append("\n");
            sb.append(this.financialProduct).append("\n");
            sb.append(this.tradingCurrency).append("\n");
            sb.append(this.numberOfDecimalsInPrice).append("\n");
            sb.append(this.numberOfDecimalsInNominalValue).append("\n");
            sb.append(this.oddLotSize).append("\n");
            sb.append(this.roundLotSize).append("\n");
            sb.append(this.blockLotSize).append("\n");
            sb.append(this.nominalValue).append("\n");
            sb.append(this.leg1Symbol).append("\n");
            sb.append(this.leg1Side).append("\n");
            sb.append(this.leg1Ratio).append("\n");
            sb.append(this.leg2Symbol).append("\n");
            sb.append(this.leg2Side).append("\n");
            sb.append(this.leg2Ratio).append("\n");
            sb.append(this.leg3Symbol).append("\n");
            sb.append(this.leg3Side).append("\n");
            sb.append(this.leg3Ratio).append("\n");
            sb.append(this.leg4Symbol).append("\n");
            sb.append(this.leg4Side).append("\n");
            sb.append(this.leg4Ratio).append("\n");
            return sb.toString();
        }
    }

    /**
    * Order Book Directory Message
    */
    public static class OrderBookDirectoryMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public TimestampNanoseconds timestampNanoseconds;
        public OrderBookId orderBookId;
        public Symbol symbol;
        public LongName longName;
        public Isin isin;
        public FinancialProduct financialProduct;
        public TradingCurrency tradingCurrency;
        public NumberOfDecimalsInPrice numberOfDecimalsInPrice;
        public NumberOfDecimalsInNominalValue numberOfDecimalsInNominalValue;
        public OddLotSize oddLotSize;
        public RoundLotSize roundLotSize;
        public BlockLotSize blockLotSize;
        public NominalValue nominalValue;

        // constructor for Order Book Directory Message
        private OrderBookDirectoryMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Order Book Directory Message
        public static OrderBookDirectoryMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new OrderBookDirectoryMessage(parentElement);

            element.timestampNanoseconds = TimestampNanoseconds.parse(container, element);
            element.orderBookId = OrderBookId.parse(container, element);
            element.symbol = Symbol.parse(container, element);
            element.longName = LongName.parse(container, element);
            element.isin = Isin.parse(container, element);
            element.financialProduct = FinancialProduct.parse(container, element);
            element.tradingCurrency = TradingCurrency.parse(container, element);
            element.numberOfDecimalsInPrice = NumberOfDecimalsInPrice.parse(container, element);
            element.numberOfDecimalsInNominalValue = NumberOfDecimalsInNominalValue.parse(container, element);
            element.oddLotSize = OddLotSize.parse(container, element);
            element.roundLotSize = RoundLotSize.parse(container, element);
            element.blockLotSize = BlockLotSize.parse(container, element);
            element.nominalValue = NominalValue.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestampNanoseconds).append("\n");
            sb.append(this.orderBookId).append("\n");
            sb.append(this.symbol).append("\n");
            sb.append(this.longName).append("\n");
            sb.append(this.isin).append("\n");
            sb.append(this.financialProduct).append("\n");
            sb.append(this.tradingCurrency).append("\n");
            sb.append(this.numberOfDecimalsInPrice).append("\n");
            sb.append(this.numberOfDecimalsInNominalValue).append("\n");
            sb.append(this.oddLotSize).append("\n");
            sb.append(this.roundLotSize).append("\n");
            sb.append(this.blockLotSize).append("\n");
            sb.append(this.nominalValue).append("\n");
            return sb.toString();
        }
    }

    // Timestamp Seconds: 4 Byte Unsigned Fixed Width Integer
    public static class TimestampSeconds implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public TimestampSeconds(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static TimestampSeconds parse(Container container, IBinaryProtocolElement element) {
            return new TimestampSeconds(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "timestampSeconds = "+ this.value;
        }
    }

    /**
    * Seconds Message
    */
    public static class SecondsMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public TimestampSeconds timestampSeconds;

        // constructor for Seconds Message
        private SecondsMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Seconds Message
        public static SecondsMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new SecondsMessage(parentElement);

            element.timestampSeconds = TimestampSeconds.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestampSeconds).append("\n");
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
        // -- parsing Order Book Directory Message
        if (messageType.enumType == 'R') {
            return OrderBookDirectoryMessage.parse(container, null);
        }
        // -- parsing Combination Order Book Directory Message
        if (messageType.enumType == 'M') {
            return CombinationOrderBookDirectoryMessage.parse(container, null);
        }
        // -- parsing Tick Size Message
        if (messageType.enumType == 'L') {
            return TickSizeMessage.parse(container, null);
        }
        // -- parsing System Event Message
        if (messageType.enumType == 'S') {
            return SystemEventMessage.parse(container, null);
        }
        // -- parsing Order Book State Message
        if (messageType.enumType == 'O') {
            return OrderBookStateMessage.parse(container, null);
        }
        // -- parsing Add Order No Participant Id Message
        if (messageType.enumType == 'A') {
            return AddOrderNoParticipantIdMessage.parse(container, null);
        }
        // -- parsing Add Order Participant Id Message
        if (messageType.enumType == 'F') {
            return AddOrderParticipantIdMessage.parse(container, null);
        }
        // -- parsing Order Executed Message
        if (messageType.enumType == 'E') {
            return OrderExecutedMessage.parse(container, null);
        }
        // -- parsing Order Executed With Price Message
        if (messageType.enumType == 'C') {
            return OrderExecutedWithPriceMessage.parse(container, null);
        }
        // -- parsing Order Replace Message
        if (messageType.enumType == 'U') {
            return OrderReplaceMessage.parse(container, null);
        }
        // -- parsing Order Delete Message
        if (messageType.enumType == 'D') {
            return OrderDeleteMessage.parse(container, null);
        }
        // -- parsing Trade Message
        if (messageType.enumType == 'P') {
            return TradeMessage.parse(container, null);
        }
        // -- parsing Equilibrium Price Update Message
        if (messageType.enumType == 'Z') {
            return EquilibriumPriceUpdateMessage.parse(container, null);
        }

        return null;
    }

    // Message Type: 1 Byte Ascii String Enum with 14 values
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
