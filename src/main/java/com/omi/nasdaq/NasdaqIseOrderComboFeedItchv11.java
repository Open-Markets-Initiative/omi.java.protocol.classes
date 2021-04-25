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
* Java parser for Nasdaq Ise Itch OrderComboFeed 1.1 protocol
* 
* @version 1.0
* @since 04/25/2021 15:26:15
*/

public class NasdaqIseOrderComboFeedItchv11 {

    ///////////////////////////////////////////////////////////////////////
    // Enum Values
    ///////////////////////////////////////////////////////////////////////

    /**
    * Auction Event Values
    */
    public enum ENUM_AUCTION_EVENT {
        E_S('S', "Start"),
        E_U('U', "Auction Update"),
        E_E('E', "End Of Auction");

        public final Character enumType;
        public final String enumValue;

        ENUM_AUCTION_EVENT(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_AUCTION_EVENT> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_AUCTION_EVENT s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_AUCTION_EVENT valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Auction Type Values
    */
    public enum ENUM_AUCTION_TYPE {
        E_E('E', "Exposure"),
        E_C('C', "Facilitation"),
        E_S('S', "Solicitation"),
        E_P('P', "Pim");

        public final Character enumType;
        public final String enumValue;

        ENUM_AUCTION_TYPE(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_AUCTION_TYPE> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_AUCTION_TYPE s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_AUCTION_TYPE valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

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
    * Exec Flag Values
    */
    public enum ENUM_EXEC_FLAG {
        E_N('N', "None"),
        E_A('A', "Aon");

        public final Character enumType;
        public final String enumValue;

        ENUM_EXEC_FLAG(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_EXEC_FLAG> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_EXEC_FLAG s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_EXEC_FLAG valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Leg Side Values
    */
    public enum ENUM_LEG_SIDE {
        E_B('B', "Buy"),
        E_S('S', "Sell");

        public final Character enumType;
        public final String enumValue;

        ENUM_LEG_SIDE(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_LEG_SIDE> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_LEG_SIDE s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_LEG_SIDE valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Message Type Values
    */
    public enum ENUM_MESSAGE_TYPE {
        E_S('S', "System Event Message"),
        E_R('R', "Complex Strategy Directory Message"),
        E_H('H', "Strategy Trading Action Message"),
        E_O('O', "Strategy Open Closed Message"),
        E_L('L', "Complex Strategy Order On Book Message"),
        E_J('J', "Complex Strategy Auction Message");

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
    * Option Type Values
    */
    public enum ENUM_OPTION_TYPE {
        E_C('C', "Call"),
        E_P('P', "Put"),
        E_SPACE(' ', "Stock");

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
    * Order Capacity Values
    */
    public enum ENUM_ORDER_CAPACITY {
        E_C('C', "Customer"),
        E_D('D', "Customer Professional"),
        E_F('F', "Firm"),
        E_B('B', "Broker Dealer Customer"),
        E_K('K', "Broker Dealer Firm"),
        E_E('E', "Proprietary"),
        E_N('N', "Away Market Maker"),
        E_M('M', "Market Maker");

        public final Character enumType;
        public final String enumValue;

        ENUM_ORDER_CAPACITY(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_ORDER_CAPACITY> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_ORDER_CAPACITY s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_ORDER_CAPACITY valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Order Type Values
    */
    public enum ENUM_ORDER_TYPE {
        E_M('M', "Market"),
        E_L('L', "Limit");

        public final Character enumType;
        public final String enumValue;

        ENUM_ORDER_TYPE(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_ORDER_TYPE> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_ORDER_TYPE s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_ORDER_TYPE valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Scope Values
    */
    public enum ENUM_SCOPE {
        E_L('L', "Local"),
        E_N('N', "National");

        public final Character enumType;
        public final String enumValue;

        ENUM_SCOPE(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_SCOPE> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_SCOPE s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_SCOPE valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Side Values
    */
    public enum ENUM_SIDE {
        E_B('B', "Bid"),
        E_A('A', "Offer Ask"),
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


    // Response Size: 4 Byte Unsigned Fixed Width Integer
    public static class ResponseSize implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public ResponseSize(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static ResponseSize parse(Container container, IBinaryProtocolElement element) {
            return new ResponseSize(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "responseSize = "+ this.value;
        }
    }

    // Response Price: 4 Byte Unsigned Fixed Width Integer
    public static class ResponsePrice implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public ResponsePrice(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static ResponsePrice parse(Container container, IBinaryProtocolElement element) {
            return new ResponsePrice(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "responsePrice = "+ this.value;
        }
    }

    /**
    * Auction Response
    */
    public static class AuctionResponse implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public ResponsePrice responsePrice;
        public ResponseSize responseSize;

        // constructor for Auction Response
        private AuctionResponse(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Auction Response
        public static AuctionResponse parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new AuctionResponse(parentElement);

            element.responsePrice = ResponsePrice.parse(container, element);
            element.responseSize = ResponseSize.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.responsePrice).append("\n");
            sb.append(this.responseSize).append("\n");
            return sb.toString();
        }
    }

    // Number Of Responses: 1 Byte Unsigned Fixed Width Integer
    public static class NumberOfResponses implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public NumberOfResponses(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static NumberOfResponses parse(Container container, IBinaryProtocolElement element) {
            return new NumberOfResponses(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "numberOfResponses = "+ this.value;
        }
    }

    // Auction Type: 1 Byte Ascii String Enum with 4 values
    public static class AuctionType implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_AUCTION_TYPE value;

        public AuctionType(ENUM_AUCTION_TYPE value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static AuctionType parse(Container container, IBinaryProtocolElement element) {
            return new AuctionType(ENUM_AUCTION_TYPE.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "auctionType = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Auction Event: 1 Byte Ascii String Enum with 3 values
    public static class AuctionEvent implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_AUCTION_EVENT value;

        public AuctionEvent(ENUM_AUCTION_EVENT value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static AuctionEvent parse(Container container, IBinaryProtocolElement element) {
            return new AuctionEvent(ENUM_AUCTION_EVENT.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "auctionEvent = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Cmta: 6 Byte Ascii String
    public static class Cmta implements IBinaryProtocolElement {
        public static final int LENGTH = 6;
        public final IBinaryProtocolElement parent;
        public final String value;

        public Cmta(String value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Cmta parse(Container container, IBinaryProtocolElement element) {
            return new Cmta(BigEndianUtils.toString(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "cmta = "+ this.value;
        }
    }

    // Giveup: 6 Byte Ascii String
    public static class Giveup implements IBinaryProtocolElement {
        public static final int LENGTH = 6;
        public final IBinaryProtocolElement parent;
        public final String value;

        public Giveup(String value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Giveup parse(Container container, IBinaryProtocolElement element) {
            return new Giveup(BigEndianUtils.toString(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "giveup = "+ this.value;
        }
    }

    // Owner Id: 6 Byte Ascii String
    public static class OwnerId implements IBinaryProtocolElement {
        public static final int LENGTH = 6;
        public final IBinaryProtocolElement parent;
        public final String value;

        public OwnerId(String value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static OwnerId parse(Container container, IBinaryProtocolElement element) {
            return new OwnerId(BigEndianUtils.toString(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "ownerId = "+ this.value;
        }
    }

    // Scope: 1 Byte Ascii String Enum with 2 values
    public static class Scope implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_SCOPE value;

        public Scope(ENUM_SCOPE value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Scope parse(Container container, IBinaryProtocolElement element) {
            return new Scope(ENUM_SCOPE.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "scope = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Order Capacity: 1 Byte Ascii String Enum with 8 values
    public static class OrderCapacity implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_ORDER_CAPACITY value;

        public OrderCapacity(ENUM_ORDER_CAPACITY value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static OrderCapacity parse(Container container, IBinaryProtocolElement element) {
            return new OrderCapacity(ENUM_ORDER_CAPACITY.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "orderCapacity = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Exec Flag: 1 Byte Ascii String Enum with 2 values
    public static class ExecFlag implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_EXEC_FLAG value;

        public ExecFlag(ENUM_EXEC_FLAG value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static ExecFlag parse(Container container, IBinaryProtocolElement element) {
            return new ExecFlag(ENUM_EXEC_FLAG.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "execFlag = "+ this.value.enumType +" ("+ this.value.enumValue +")";
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

    // Order Type: 1 Byte Ascii String Enum with 2 values
    public static class OrderType implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_ORDER_TYPE value;

        public OrderType(ENUM_ORDER_TYPE value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static OrderType parse(Container container, IBinaryProtocolElement element) {
            return new OrderType(ENUM_ORDER_TYPE.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "orderType = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Auction Id: 4 Byte Unsigned Fixed Width Integer
    public static class AuctionId implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public AuctionId(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static AuctionId parse(Container container, IBinaryProtocolElement element) {
            return new AuctionId(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "auctionId = "+ this.value;
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
    * Complex Strategy Auction Message
    */
    public static class ComplexStrategyAuctionMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public StrategyId strategyId;
        public AuctionId auctionId;
        public OrderType orderType;
        public Side side;
        public Price price;
        public Size size;
        public ExecFlag execFlag;
        public OrderCapacity orderCapacity;
        public Scope scope;
        public OwnerId ownerId;
        public Giveup giveup;
        public Cmta cmta;
        public AuctionEvent auctionEvent;
        public AuctionType auctionType;
        public NumberOfResponses numberOfResponses;
        public List<AuctionResponse> auctionResponseList = new ArrayList<>();

        // constructor for Complex Strategy Auction Message
        private ComplexStrategyAuctionMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Complex Strategy Auction Message
        public static ComplexStrategyAuctionMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new ComplexStrategyAuctionMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.strategyId = StrategyId.parse(container, element);
            element.auctionId = AuctionId.parse(container, element);
            element.orderType = OrderType.parse(container, element);
            element.side = Side.parse(container, element);
            element.price = Price.parse(container, element);
            element.size = Size.parse(container, element);
            element.execFlag = ExecFlag.parse(container, element);
            element.orderCapacity = OrderCapacity.parse(container, element);
            element.scope = Scope.parse(container, element);
            element.ownerId = OwnerId.parse(container, element);
            element.giveup = Giveup.parse(container, element);
            element.cmta = Cmta.parse(container, element);
            element.auctionEvent = AuctionEvent.parse(container, element);
            element.auctionType = AuctionType.parse(container, element);
            element.numberOfResponses = NumberOfResponses.parse(container, element);

            // Auction Response: Struct of 2 fields
            for (int i = 0; i < element.numberOfResponses.value; i++) {
                element.auctionResponseList.add(AuctionResponse.parse(container, element));
            }

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.strategyId).append("\n");
            sb.append(this.auctionId).append("\n");
            sb.append(this.orderType).append("\n");
            sb.append(this.side).append("\n");
            sb.append(this.price).append("\n");
            sb.append(this.size).append("\n");
            sb.append(this.execFlag).append("\n");
            sb.append(this.orderCapacity).append("\n");
            sb.append(this.scope).append("\n");
            sb.append(this.ownerId).append("\n");
            sb.append(this.giveup).append("\n");
            sb.append(this.cmta).append("\n");
            sb.append(this.auctionEvent).append("\n");
            sb.append(this.auctionType).append("\n");
            sb.append(this.numberOfResponses).append("\n");
            for (var auctionResponse: auctionResponseList) {
                sb.append(auctionResponse).append("\n");
            }
            return sb.toString();
        }
    }

    /**
    * Complex Strategy Order On Book Message
    */
    public static class ComplexStrategyOrderOnBookMessage implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Timestamp timestamp;
        public StrategyId strategyId;
        public OrderType orderType;
        public Side side;
        public Price price;
        public Size size;
        public ExecFlag execFlag;
        public OrderCapacity orderCapacity;
        public Scope scope;
        public OwnerId ownerId;
        public Giveup giveup;
        public Cmta cmta;

        // constructor for Complex Strategy Order On Book Message
        private ComplexStrategyOrderOnBookMessage(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Complex Strategy Order On Book Message
        public static ComplexStrategyOrderOnBookMessage parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new ComplexStrategyOrderOnBookMessage(parentElement);

            element.timestamp = Timestamp.parse(container, element);
            element.strategyId = StrategyId.parse(container, element);
            element.orderType = OrderType.parse(container, element);
            element.side = Side.parse(container, element);
            element.price = Price.parse(container, element);
            element.size = Size.parse(container, element);
            element.execFlag = ExecFlag.parse(container, element);
            element.orderCapacity = OrderCapacity.parse(container, element);
            element.scope = Scope.parse(container, element);
            element.ownerId = OwnerId.parse(container, element);
            element.giveup = Giveup.parse(container, element);
            element.cmta = Cmta.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.timestamp).append("\n");
            sb.append(this.strategyId).append("\n");
            sb.append(this.orderType).append("\n");
            sb.append(this.side).append("\n");
            sb.append(this.price).append("\n");
            sb.append(this.size).append("\n");
            sb.append(this.execFlag).append("\n");
            sb.append(this.orderCapacity).append("\n");
            sb.append(this.scope).append("\n");
            sb.append(this.ownerId).append("\n");
            sb.append(this.giveup).append("\n");
            sb.append(this.cmta).append("\n");
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

    // Leg Side: 1 Byte Ascii String Enum with 2 values
    public static class LegSide implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_LEG_SIDE value;

        public LegSide(ENUM_LEG_SIDE value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static LegSide parse(Container container, IBinaryProtocolElement element) {
            return new LegSide(ENUM_LEG_SIDE.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "legSide = "+ this.value.enumType +" ("+ this.value.enumValue +")";
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
        public LegSide legSide;
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
            element.legSide = LegSide.parse(container, element);
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
            sb.append(this.legSide).append("\n");
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
        // -- parsing Strategy Trading Action Message
        if (messageType.enumType == 'H') {
            return StrategyTradingActionMessage.parse(container, null);
        }
        // -- parsing Strategy Open Closed Message
        if (messageType.enumType == 'O') {
            return StrategyOpenClosedMessage.parse(container, null);
        }
        // -- parsing Complex Strategy Order On Book Message
        if (messageType.enumType == 'L') {
            return ComplexStrategyOrderOnBookMessage.parse(container, null);
        }
        // -- parsing Complex Strategy Auction Message
        if (messageType.enumType == 'J') {
            return ComplexStrategyAuctionMessage.parse(container, null);
        }

        return null;
    }

    // Message Type: 1 Byte Ascii String Enum with 6 values
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
