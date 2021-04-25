package com.omi.cme;

import com.omi.parser.util.BigEndianUtils;
import com.omi.parser.util.LittleEndianUtils;
import com.omi.protocol.Container;
import com.omi.protocol.IBinaryProtocolElement;
import com.omi.protocol.Payload;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.ArrayList;

/**
* Java parser for Cme Futures Sbe Mdp3 1.9 protocol
* 
* @version 1.0
* @since 04/25/2021 15:26:15
*/

public class CmeFuturesMdp3Sbev19 {

    ///////////////////////////////////////////////////////////////////////
    // Enum Values
    ///////////////////////////////////////////////////////////////////////

    /**
    * Aggressor Side Values
    */
    public enum ENUM_AGGRESSOR_SIDE {
        E_255(255, "No Value"),
        E_0(0, "No Aggressor"),
        E_1(1, "Buy"),
        E_2(2, "Sell");

        public final Integer enumType;
        public final String enumValue;

        ENUM_AGGRESSOR_SIDE(Integer enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Integer, ENUM_AGGRESSOR_SIDE> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_AGGRESSOR_SIDE s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_AGGRESSOR_SIDE valueOf(Integer s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Event Type Values
    */
    public enum ENUM_EVENT_TYPE {
        E_5(5, "Activation"),
        E_7(7, "Last Eligible Trade Date");

        public final Integer enumType;
        public final String enumValue;

        ENUM_EVENT_TYPE(Integer enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Integer, ENUM_EVENT_TYPE> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_EVENT_TYPE s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_EVENT_TYPE valueOf(Integer s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Halt Reason Values
    */
    public enum ENUM_HALT_REASON {
        E_0(0, "Group Schedule"),
        E_1(1, "Surveillance Intervention"),
        E_2(2, "Market Event"),
        E_3(3, "Instrument Activation"),
        E_4(4, "Instrument Expiration"),
        E_5(5, "Unknown"),
        E_6(6, "Recovery In Process");

        public final Integer enumType;
        public final String enumValue;

        ENUM_HALT_REASON(Integer enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Integer, ENUM_HALT_REASON> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_HALT_REASON s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_HALT_REASON valueOf(Integer s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Leg Side Values
    */
    public enum ENUM_LEG_SIDE {
        E_1(1, "Buy Side"),
        E_2(2, "Sell Side");

        public final Integer enumType;
        public final String enumValue;

        ENUM_LEG_SIDE(Integer enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Integer, ENUM_LEG_SIDE> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_LEG_SIDE s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_LEG_SIDE valueOf(Integer s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Md Entry Type  Values
    */
    public enum ENUM_MD_ENTRY_TYPE {
        E_0('0', "Bid"),
        E_1('1', "Offer"),
        E_2('2', "Trade"),
        E_4('4', "Open Price"),
        E_6('6', "Settlement Price"),
        E_7('7', "Trading Session High Price"),
        E_8('8', "Trading Session Low Price"),
        E_B('B', "Cleared Volume"),
        E_C('C', "Open Interest"),
        E_E('E', "Implied Bid"),
        E_F('F', "Implied Offer"),
        E_J('J', "Book Reset"),
        E_N('N', "Session High Bid"),
        E_O('O', "Session Low Offer"),
        E_W('W', "Fixing Price"),
        E_e('e', "Electronic Volume"),
        E_g('g', "Threshold Limitsand Price Band Variation");

        public final Character enumType;
        public final String enumValue;

        ENUM_MD_ENTRY_TYPE(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_MD_ENTRY_TYPE> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_MD_ENTRY_TYPE s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_MD_ENTRY_TYPE valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Md Entry Type Book Values
    */
    public enum ENUM_MD_ENTRY_TYPE_BOOK {
        E_0('0', "Bid"),
        E_1('1', "Offer"),
        E_E('E', "Implied Bid"),
        E_F('F', "Implied Offer"),
        E_J('J', "Book Reset");

        public final Character enumType;
        public final String enumValue;

        ENUM_MD_ENTRY_TYPE_BOOK(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_MD_ENTRY_TYPE_BOOK> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_MD_ENTRY_TYPE_BOOK s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_MD_ENTRY_TYPE_BOOK valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Md Entry Type Daily Statistics Values
    */
    public enum ENUM_MD_ENTRY_TYPE_DAILY_STATISTICS {
        E_6('6', "Settlement Price"),
        E_B('B', "Cleared Volume"),
        E_C('C', "Open Interest"),
        E_W('W', "Fixing Price");

        public final Character enumType;
        public final String enumValue;

        ENUM_MD_ENTRY_TYPE_DAILY_STATISTICS(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_MD_ENTRY_TYPE_DAILY_STATISTICS> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_MD_ENTRY_TYPE_DAILY_STATISTICS s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_MD_ENTRY_TYPE_DAILY_STATISTICS valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Md Entry Type Statistics Values
    */
    public enum ENUM_MD_ENTRY_TYPE_STATISTICS {
        E_4('4', "Open Price"),
        E_7('7', "High Trade"),
        E_8('8', "Low Trade"),
        E_N('N', "Highest Bid"),
        E_O('O', "Lowest Offer");

        public final Character enumType;
        public final String enumValue;

        ENUM_MD_ENTRY_TYPE_STATISTICS(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_MD_ENTRY_TYPE_STATISTICS> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_MD_ENTRY_TYPE_STATISTICS s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_MD_ENTRY_TYPE_STATISTICS valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Md Security Trading Status Values
    */
    public enum ENUM_MD_SECURITY_TRADING_STATUS {
        E_255(255, "No Value"),
        E_2(2, "Trading Halt"),
        E_4(4, "Close"),
        E_15(15, "New Price Indication"),
        E_17(17, "Ready To Trade"),
        E_18(18, "Not Available For Trading"),
        E_20(20, "Unknownor Invalid"),
        E_21(21, "Pre Open"),
        E_24(24, "Pre Cross"),
        E_25(25, "Cross"),
        E_26(26, "Post Close"),
        E_103(103, "No Change");

        public final Integer enumType;
        public final String enumValue;

        ENUM_MD_SECURITY_TRADING_STATUS(Integer enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Integer, ENUM_MD_SECURITY_TRADING_STATUS> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_MD_SECURITY_TRADING_STATUS s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_MD_SECURITY_TRADING_STATUS valueOf(Integer s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Md Update Action Values
    */
    public enum ENUM_MD_UPDATE_ACTION {
        E_0(0, "New"),
        E_1(1, "Change"),
        E_2(2, "Delete"),
        E_3(3, "Delete Thru"),
        E_4(4, "Delete From"),
        E_5(5, "Overlay");

        public final Integer enumType;
        public final String enumValue;

        ENUM_MD_UPDATE_ACTION(Integer enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Integer, ENUM_MD_UPDATE_ACTION> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_MD_UPDATE_ACTION s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_MD_UPDATE_ACTION valueOf(Integer s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Open Close Settl Flag Values
    */
    public enum ENUM_OPEN_CLOSE_SETTL_FLAG {
        E_255(255, "No Value"),
        E_0(0, "Daily Open Price"),
        E_5(5, "Indicative Opening Price");

        public final Integer enumType;
        public final String enumValue;

        ENUM_OPEN_CLOSE_SETTL_FLAG(Integer enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Integer, ENUM_OPEN_CLOSE_SETTL_FLAG> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_OPEN_CLOSE_SETTL_FLAG s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_OPEN_CLOSE_SETTL_FLAG valueOf(Integer s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Order Update Action Values
    */
    public enum ENUM_ORDER_UPDATE_ACTION {
        E_0(0, "New"),
        E_1(1, "Update"),
        E_2(2, "Delete");

        public final Integer enumType;
        public final String enumValue;

        ENUM_ORDER_UPDATE_ACTION(Integer enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Integer, ENUM_ORDER_UPDATE_ACTION> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_ORDER_UPDATE_ACTION s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_ORDER_UPDATE_ACTION valueOf(Integer s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Put Or Call Values
    */
    public enum ENUM_PUT_OR_CALL {
        E_0(0, "Put"),
        E_1(1, "Call");

        public final Integer enumType;
        public final String enumValue;

        ENUM_PUT_OR_CALL(Integer enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Integer, ENUM_PUT_OR_CALL> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_PUT_OR_CALL s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_PUT_OR_CALL valueOf(Integer s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Security Trading Event Values
    */
    public enum ENUM_SECURITY_TRADING_EVENT {
        E_0(0, "No Event"),
        E_1(1, "No Cancel"),
        E_4(4, "Reset Statistics"),
        E_5(5, "Implied Matching On"),
        E_6(6, "Implied Matching Off");

        public final Integer enumType;
        public final String enumValue;

        ENUM_SECURITY_TRADING_EVENT(Integer enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Integer, ENUM_SECURITY_TRADING_EVENT> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_SECURITY_TRADING_EVENT s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_SECURITY_TRADING_EVENT valueOf(Integer s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Security Trading Status Values
    */
    public enum ENUM_SECURITY_TRADING_STATUS {
        E_255(255, "No Value"),
        E_2(2, "Trading Halt"),
        E_4(4, "Close"),
        E_15(15, "New Price Indication"),
        E_17(17, "Ready To Trade"),
        E_18(18, "Not Available For Trading"),
        E_20(20, "Unknownor Invalid"),
        E_21(21, "Pre Open"),
        E_24(24, "Pre Cross"),
        E_25(25, "Cross"),
        E_26(26, "Post Close"),
        E_103(103, "No Change");

        public final Integer enumType;
        public final String enumValue;

        ENUM_SECURITY_TRADING_STATUS(Integer enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Integer, ENUM_SECURITY_TRADING_STATUS> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_SECURITY_TRADING_STATUS s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_SECURITY_TRADING_STATUS valueOf(Integer s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Security Update Action Values
    */
    public enum ENUM_SECURITY_UPDATE_ACTION {
        E_A('A', "Add"),
        E_D('D', "Delete"),
        E_M('M', "Modify");

        public final Character enumType;
        public final String enumValue;

        ENUM_SECURITY_UPDATE_ACTION(Character enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Character, ENUM_SECURITY_UPDATE_ACTION> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_SECURITY_UPDATE_ACTION s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_SECURITY_UPDATE_ACTION valueOf(Character s)  {
            return BY_VALUE.get(s);
        }
    }

    /**
    * Template Id Values
    */
    public enum ENUM_TEMPLATE_ID {
        E_4(4, "Channel Reset"),
        E_12(12, "Admin Heartbeat"),
        E_15(15, "Admin Login"),
        E_16(16, "Admin Logout"),
        E_27(27, "Md Instrument Definition Future"),
        E_29(29, "Md Instrument Definition Spread"),
        E_30(30, "Security Status"),
        E_32(32, "Md Incremental Refresh Book"),
        E_33(33, "Md Incremental Refresh Daily Statistics"),
        E_34(34, "Md Incremental Refresh Limits Banding"),
        E_35(35, "Md Incremental Refresh Session Statistics"),
        E_37(37, "Md Incremental Refresh Volume"),
        E_38(38, "Snapshot Full Refresh"),
        E_39(39, "Quote Request"),
        E_41(41, "Md Instrument Definition Option"),
        E_42(42, "Md Incremental Refresh Trade Summary"),
        E_43(43, "Md Incremental Refresh Order Book"),
        E_44(44, "Snapshot Full Refresh Order Book"),
        E_46(46, "Md Incremental Refresh Book"),
        E_47(47, "Md Incremental Refresh Order Book"),
        E_48(48, "Md Incremental Refresh Trade Summary"),
        E_49(49, "Md Incremental Refresh Daily Statistics"),
        E_50(50, "Md Incremental Refresh Limits Banding"),
        E_51(51, "Md Incremental Refresh Session Statistics"),
        E_52(52, "Snapshot Full Refresh"),
        E_53(53, "Snapshot Full Refresh Order Book"),
        E_54(54, "Md Instrument Definition Future"),
        E_55(55, "Md Instrument Definition Option"),
        E_56(56, "Md Instrument Definition Spread");

        public final Integer enumType;
        public final String enumValue;

        ENUM_TEMPLATE_ID(Integer enumType, String enumValue) {
            this.enumType = enumType;
            this.enumValue = enumValue;
        }

        private static final Map<Integer, ENUM_TEMPLATE_ID> BY_VALUE = new HashMap<>();

        static {
            for (ENUM_TEMPLATE_ID s: values()) {
                BY_VALUE.put(s.enumType, s);
            }
        }

        public static ENUM_TEMPLATE_ID valueOf(Integer s)  {
            return BY_VALUE.get(s);
        }
    }


    // Leg Option Delta: 4 Byte Signed Fixed Width Integer Nullable
    public static class LegOptionDelta implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public LegOptionDelta(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value/10000;
        }

        public static LegOptionDelta parse(Container container, IBinaryProtocolElement element) {
            return new LegOptionDelta(LittleEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "legOptionDelta = "+ this.value;
        }
    }

    // Leg Price Pricenull: 8 Byte Signed Fixed Width Integer Nullable
    public static class LegPricePricenull implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public LegPricePricenull(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value/10000000;
        }

        public static LegPricePricenull parse(Container container, IBinaryProtocolElement element) {
            return new LegPricePricenull(LittleEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "legPricePricenull = "+ this.value;
        }
    }

    // Leg Ratio Qty: 1 Byte Signed Fixed Width Integer
    public static class LegRatioQty implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public LegRatioQty(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static LegRatioQty parse(Container container, IBinaryProtocolElement element) {
            return new LegRatioQty(LittleEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "legRatioQty = "+ this.value;
        }
    }

    // Leg Side: 1 Byte Unsigned Fixed Width Integer Enum with 2 values
    public static class LegSide implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_LEG_SIDE value;

        public LegSide(ENUM_LEG_SIDE value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static LegSide parse(Container container, IBinaryProtocolElement element) {
            return new LegSide(ENUM_LEG_SIDE.valueOf((int)LittleEndianUtils.toByte(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "legSide = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Leg Security Id: 4 Byte Signed Fixed Width Integer
    public static class LegSecurityId implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public LegSecurityId(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static LegSecurityId parse(Container container, IBinaryProtocolElement element) {
            return new LegSecurityId(LittleEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "legSecurityId = "+ this.value;
        }
    }

    /**
    * M D Instrument Definition Spread Leg Group
    */
    public static class MDInstrumentDefinitionSpreadLegGroup implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public LegSecurityId legSecurityId;
        public LegSide legSide;
        public LegRatioQty legRatioQty;
        public LegPricePricenull legPricePricenull;
        public LegOptionDelta legOptionDelta;

        // constructor for M D Instrument Definition Spread Leg Group
        private MDInstrumentDefinitionSpreadLegGroup(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for M D Instrument Definition Spread Leg Group
        public static MDInstrumentDefinitionSpreadLegGroup parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new MDInstrumentDefinitionSpreadLegGroup(parentElement);

            element.legSecurityId = LegSecurityId.parse(container, element);
            element.legSide = LegSide.parse(container, element);
            element.legRatioQty = LegRatioQty.parse(container, element);
            element.legPricePricenull = LegPricePricenull.parse(container, element);
            element.legOptionDelta = LegOptionDelta.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.legSecurityId).append("\n");
            sb.append(this.legSide).append("\n");
            sb.append(this.legRatioQty).append("\n");
            sb.append(this.legPricePricenull).append("\n");
            sb.append(this.legOptionDelta).append("\n");
            return sb.toString();
        }
    }

    // Num In Group: 1 Byte Unsigned Fixed Width Integer
    public static class NumInGroup implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public NumInGroup(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static NumInGroup parse(Container container, IBinaryProtocolElement element) {
            return new NumInGroup(LittleEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "numInGroup = "+ this.value;
        }
    }

    // Block Length: 2 Byte Unsigned Fixed Width Integer
    public static class BlockLength implements IBinaryProtocolElement {
        public static final int LENGTH = 2;
        public final IBinaryProtocolElement parent;
        public final Short value;

        public BlockLength(Short value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static BlockLength parse(Container container, IBinaryProtocolElement element) {
            return new BlockLength(LittleEndianUtils.toShort(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "blockLength = "+ this.value;
        }
    }

    /**
    * Group Size
    */
    public static class GroupSize implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public BlockLength blockLength;
        public NumInGroup numInGroup;

        // constructor for Group Size
        private GroupSize(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Group Size
        public static GroupSize parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new GroupSize(parentElement);

            element.blockLength = BlockLength.parse(container, element);
            element.numInGroup = NumInGroup.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.blockLength).append("\n");
            sb.append(this.numInGroup).append("\n");
            return sb.toString();
        }
    }

    /**
    * M D Instrument Definition Spread Leg Groups
    */
    public static class MDInstrumentDefinitionSpreadLegGroups implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public GroupSize groupSize;
        public List<MDInstrumentDefinitionSpreadLegGroup> mDInstrumentDefinitionSpreadLegGroupList = new ArrayList<>();

        // constructor for M D Instrument Definition Spread Leg Groups
        private MDInstrumentDefinitionSpreadLegGroups(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for M D Instrument Definition Spread Leg Groups
        public static MDInstrumentDefinitionSpreadLegGroups parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new MDInstrumentDefinitionSpreadLegGroups(parentElement);

            element.groupSize = GroupSize.parse(container, element);

            // M D Instrument Definition Spread Leg Group: Struct of 5 fields
            for (int i = 0; i < element.groupSize.numInGroup.value; i++) {
                element.mDInstrumentDefinitionSpreadLegGroupList.add(MDInstrumentDefinitionSpreadLegGroup.parse(container, element));
            }

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.groupSize).append("\n");
            for (var mDInstrumentDefinitionSpreadLegGroup: mDInstrumentDefinitionSpreadLegGroupList) {
                sb.append(mDInstrumentDefinitionSpreadLegGroup).append("\n");
            }
            return sb.toString();
        }
    }

    // Min Lot Size: 4 Byte Signed Fixed Width Integer Nullable
    public static class MinLotSize implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public MinLotSize(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value/10000;
        }

        public static MinLotSize parse(Container container, IBinaryProtocolElement element) {
            return new MinLotSize(LittleEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "minLotSize = "+ this.value;
        }
    }

    // Lot Type: 1 Byte Signed Fixed Width Integer
    public static class LotType implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public LotType(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static LotType parse(Container container, IBinaryProtocolElement element) {
            return new LotType(LittleEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "lotType = "+ this.value;
        }
    }

    /**
    * Lot Type Rules Group
    */
    public static class LotTypeRulesGroup implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public LotType lotType;
        public MinLotSize minLotSize;

        // constructor for Lot Type Rules Group
        private LotTypeRulesGroup(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Lot Type Rules Group
        public static LotTypeRulesGroup parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new LotTypeRulesGroup(parentElement);

            element.lotType = LotType.parse(container, element);
            element.minLotSize = MinLotSize.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.lotType).append("\n");
            sb.append(this.minLotSize).append("\n");
            return sb.toString();
        }
    }

    /**
    * Lot Type Rules Groups
    */
    public static class LotTypeRulesGroups implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public GroupSize groupSize;
        public List<LotTypeRulesGroup> lotTypeRulesGroupList = new ArrayList<>();

        // constructor for Lot Type Rules Groups
        private LotTypeRulesGroups(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Lot Type Rules Groups
        public static LotTypeRulesGroups parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new LotTypeRulesGroups(parentElement);

            element.groupSize = GroupSize.parse(container, element);

            // Lot Type Rules Group: Struct of 2 fields
            for (int i = 0; i < element.groupSize.numInGroup.value; i++) {
                element.lotTypeRulesGroupList.add(LotTypeRulesGroup.parse(container, element));
            }

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.groupSize).append("\n");
            for (var lotTypeRulesGroup: lotTypeRulesGroupList) {
                sb.append(lotTypeRulesGroup).append("\n");
            }
            return sb.toString();
        }
    }

    // Electronic Match Eligible: 1 Bit
    public static class ElectronicMatchEligible implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public ElectronicMatchEligible(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static ElectronicMatchEligible parse(Container container, IBinaryProtocolElement element) {
            return new ElectronicMatchEligible(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "electronicMatchEligible = "+ this.value;
        }
    }

    // Order Cross Eligible: 1 Bit
    public static class OrderCrossEligible implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public OrderCrossEligible(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static OrderCrossEligible parse(Container container, IBinaryProtocolElement element) {
            return new OrderCrossEligible(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "orderCrossEligible = "+ this.value;
        }
    }

    // Block Trade Eligible: 1 Bit
    public static class BlockTradeEligible implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public BlockTradeEligible(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static BlockTradeEligible parse(Container container, IBinaryProtocolElement element) {
            return new BlockTradeEligible(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "blockTradeEligible = "+ this.value;
        }
    }

    // Efp Eligible: 1 Bit
    public static class EfpEligible implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public EfpEligible(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static EfpEligible parse(Container container, IBinaryProtocolElement element) {
            return new EfpEligible(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "efpEligible = "+ this.value;
        }
    }

    // Ebf Eligible: 1 Bit
    public static class EbfEligible implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public EbfEligible(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static EbfEligible parse(Container container, IBinaryProtocolElement element) {
            return new EbfEligible(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "ebfEligible = "+ this.value;
        }
    }

    // Efs Eligible: 1 Bit
    public static class EfsEligible implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public EfsEligible(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static EfsEligible parse(Container container, IBinaryProtocolElement element) {
            return new EfsEligible(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "efsEligible = "+ this.value;
        }
    }

    // Efr Eligible: 1 Bit
    public static class EfrEligible implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public EfrEligible(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static EfrEligible parse(Container container, IBinaryProtocolElement element) {
            return new EfrEligible(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "efrEligible = "+ this.value;
        }
    }

    // Otc Eligible: 1 Bit
    public static class OtcEligible implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public OtcEligible(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static OtcEligible parse(Container container, IBinaryProtocolElement element) {
            return new OtcEligible(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "otcEligible = "+ this.value;
        }
    }

    // I Link Indicative Mass Quoting Eligible: 1 Bit
    public static class ILinkIndicativeMassQuotingEligible implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public ILinkIndicativeMassQuotingEligible(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static ILinkIndicativeMassQuotingEligible parse(Container container, IBinaryProtocolElement element) {
            return new ILinkIndicativeMassQuotingEligible(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "iLinkIndicativeMassQuotingEligible = "+ this.value;
        }
    }

    // Negative Strike Eligible: 1 Bit
    public static class NegativeStrikeEligible implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public NegativeStrikeEligible(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static NegativeStrikeEligible parse(Container container, IBinaryProtocolElement element) {
            return new NegativeStrikeEligible(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "negativeStrikeEligible = "+ this.value;
        }
    }

    // Negative Price Outright Eligible: 1 Bit
    public static class NegativePriceOutrightEligible implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public NegativePriceOutrightEligible(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static NegativePriceOutrightEligible parse(Container container, IBinaryProtocolElement element) {
            return new NegativePriceOutrightEligible(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "negativePriceOutrightEligible = "+ this.value;
        }
    }

    // Is Fractional: 1 Bit
    public static class IsFractional implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public IsFractional(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static IsFractional parse(Container container, IBinaryProtocolElement element) {
            return new IsFractional(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "isFractional = "+ this.value;
        }
    }

    // Volatility Quoted Option: 1 Bit
    public static class VolatilityQuotedOption implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public VolatilityQuotedOption(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static VolatilityQuotedOption parse(Container container, IBinaryProtocolElement element) {
            return new VolatilityQuotedOption(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "volatilityQuotedOption = "+ this.value;
        }
    }

    // Rfq Cross Eligible: 1 Bit
    public static class RfqCrossEligible implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public RfqCrossEligible(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static RfqCrossEligible parse(Container container, IBinaryProtocolElement element) {
            return new RfqCrossEligible(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "rfqCrossEligible = "+ this.value;
        }
    }

    // Zero Price Outright Eligible: 1 Bit
    public static class ZeroPriceOutrightEligible implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public ZeroPriceOutrightEligible(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static ZeroPriceOutrightEligible parse(Container container, IBinaryProtocolElement element) {
            return new ZeroPriceOutrightEligible(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "zeroPriceOutrightEligible = "+ this.value;
        }
    }

    // Decaying Product Eligibility: 1 Bit
    public static class DecayingProductEligibility implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public DecayingProductEligibility(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static DecayingProductEligibility parse(Container container, IBinaryProtocolElement element) {
            return new DecayingProductEligibility(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "decayingProductEligibility = "+ this.value;
        }
    }

    // Variable Product Eligibility: 1 Bit
    public static class VariableProductEligibility implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public VariableProductEligibility(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static VariableProductEligibility parse(Container container, IBinaryProtocolElement element) {
            return new VariableProductEligibility(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "variableProductEligibility = "+ this.value;
        }
    }

    // Daily Product Eligibility: 1 Bit
    public static class DailyProductEligibility implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public DailyProductEligibility(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static DailyProductEligibility parse(Container container, IBinaryProtocolElement element) {
            return new DailyProductEligibility(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "dailyProductEligibility = "+ this.value;
        }
    }

    // Gt Orders Eligibility: 1 Bit
    public static class GtOrdersEligibility implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public GtOrdersEligibility(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static GtOrdersEligibility parse(Container container, IBinaryProtocolElement element) {
            return new GtOrdersEligibility(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "gtOrdersEligibility = "+ this.value;
        }
    }

    // Implied Matching Eligibility: 1 Bit
    public static class ImpliedMatchingEligibility implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public ImpliedMatchingEligibility(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static ImpliedMatchingEligibility parse(Container container, IBinaryProtocolElement element) {
            return new ImpliedMatchingEligibility(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "impliedMatchingEligibility = "+ this.value;
        }
    }

    // Triangulation Eligible: 1 Bit
    public static class TriangulationEligible implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public TriangulationEligible(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static TriangulationEligible parse(Container container, IBinaryProtocolElement element) {
            return new TriangulationEligible(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "triangulationEligible = "+ this.value;
        }
    }

    // Variable Cab Eligible: 1 Bit
    public static class VariableCabEligible implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public VariableCabEligible(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static VariableCabEligible parse(Container container, IBinaryProtocolElement element) {
            return new VariableCabEligible(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "variableCabEligible = "+ this.value;
        }
    }

    // Reserved 10: 10 Bit
    public static class Reserved10 implements IBinaryProtocolElement {
        public static final int LENGTH = 10;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public Reserved10(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Reserved10 parse(Container container, IBinaryProtocolElement element) {
            return new Reserved10(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "reserved10 = "+ this.value;
        }
    }

    // bit values for InstAttribValue
    public static class InstAttribValueBits {
        public static final byte RESERVED_10                              = (byte)(1 << 22);  
        public static final byte VARIABLE_CAB_ELIGIBLE                    = (byte)(1 << 21);  
        public static final byte TRIANGULATION_ELIGIBLE                   = (byte)(1 << 20);  
        public static final byte IMPLIED_MATCHING_ELIGIBILITY             = (byte)(1 << 19);  
        public static final byte GT_ORDERS_ELIGIBILITY                    = (byte)(1 << 18);  
        public static final byte DAILY_PRODUCT_ELIGIBILITY                = (byte)(1 << 17);  
        public static final byte VARIABLE_PRODUCT_ELIGIBILITY             = (byte)(1 << 16);  
        public static final byte DECAYING_PRODUCT_ELIGIBILITY             = (byte)(1 << 15);  
        public static final byte ZERO_PRICE_OUTRIGHT_ELIGIBLE             = (byte)(1 << 14);  
        public static final byte RFQ_CROSS_ELIGIBLE                       = (byte)(1 << 13);  
        public static final byte VOLATILITY_QUOTED_OPTION                 = (byte)(1 << 12);  
        public static final byte IS_FRACTIONAL                            = (byte)(1 << 11);  
        public static final byte NEGATIVE_PRICE_OUTRIGHT_ELIGIBLE         = (byte)(1 << 10);  
        public static final byte NEGATIVE_STRIKE_ELIGIBLE                 = (byte)(1 << 9);   
        public static final byte I_LINK_INDICATIVE_MASS_QUOTING_ELIGIBLE  = (byte)(1 << 8);   
        public static final byte OTC_ELIGIBLE                             = (byte)(1 << 7);   
        public static final byte EFR_ELIGIBLE                             = (byte)(1 << 6);   
        public static final byte EFS_ELIGIBLE                             = (byte)(1 << 5);   
        public static final byte EBF_ELIGIBLE                             = (byte)(1 << 4);   
        public static final byte EFP_ELIGIBLE                             = (byte)(1 << 3);   
        public static final byte BLOCK_TRADE_ELIGIBLE                     = (byte)(1 << 2);   
        public static final byte ORDER_CROSS_ELIGIBLE                     = (byte)(1 << 1);   
        public static final byte ELECTRONIC_MATCH_ELIGIBLE                = (byte)(1 << 0);   
    }

    // Inst Attrib Value: Struct of 23 fields
    public static class InstAttribValue implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement _parent;

        // bits
        public final boolean  _reserved10;                             //  0 
        public final boolean  _variableCabEligible;                    //  1 
        public final boolean  _triangulationEligible;                  //  2 
        public final boolean  _impliedMatchingEligibility;             //  3 
        public final boolean  _gtOrdersEligibility;                    //  4 
        public final boolean  _dailyProductEligibility;                //  5 
        public final boolean  _variableProductEligibility;             //  6 
        public final boolean  _decayingProductEligibility;             //  7 
        public final boolean  _zeroPriceOutrightEligible;              //  8 
        public final boolean  _rfqCrossEligible;                       //  9 
        public final boolean  _volatilityQuotedOption;                 //  10
        public final boolean  _isFractional;                           //  11
        public final boolean  _negativePriceOutrightEligible;          //  12
        public final boolean  _negativeStrikeEligible;                 //  13
        public final boolean  _iLinkIndicativeMassQuotingEligible;     //  14
        public final boolean  _otcEligible;                            //  15
        public final boolean  _efrEligible;                            //  16
        public final boolean  _efsEligible;                            //  17
        public final boolean  _ebfEligible;                            //  18
        public final boolean  _efpEligible;                            //  19
        public final boolean  _blockTradeEligible;                     //  20
        public final boolean  _orderCrossEligible;                     //  21
        public final boolean  _electronicMatchEligible;                //  22

        public InstAttribValue (Integer value, IBinaryProtocolElement element) {
            this._parent = element;

            this._reserved10 = (value & InstAttribValueBits.RESERVED_10) != 0;
            this._variableCabEligible = (value & InstAttribValueBits.VARIABLE_CAB_ELIGIBLE) != 0;
            this._triangulationEligible = (value & InstAttribValueBits.TRIANGULATION_ELIGIBLE) != 0;
            this._impliedMatchingEligibility = (value & InstAttribValueBits.IMPLIED_MATCHING_ELIGIBILITY) != 0;
            this._gtOrdersEligibility = (value & InstAttribValueBits.GT_ORDERS_ELIGIBILITY) != 0;
            this._dailyProductEligibility = (value & InstAttribValueBits.DAILY_PRODUCT_ELIGIBILITY) != 0;
            this._variableProductEligibility = (value & InstAttribValueBits.VARIABLE_PRODUCT_ELIGIBILITY) != 0;
            this._decayingProductEligibility = (value & InstAttribValueBits.DECAYING_PRODUCT_ELIGIBILITY) != 0;
            this._zeroPriceOutrightEligible = (value & InstAttribValueBits.ZERO_PRICE_OUTRIGHT_ELIGIBLE) != 0;
            this._rfqCrossEligible = (value & InstAttribValueBits.RFQ_CROSS_ELIGIBLE) != 0;
            this._volatilityQuotedOption = (value & InstAttribValueBits.VOLATILITY_QUOTED_OPTION) != 0;
            this._isFractional = (value & InstAttribValueBits.IS_FRACTIONAL) != 0;
            this._negativePriceOutrightEligible = (value & InstAttribValueBits.NEGATIVE_PRICE_OUTRIGHT_ELIGIBLE) != 0;
            this._negativeStrikeEligible = (value & InstAttribValueBits.NEGATIVE_STRIKE_ELIGIBLE) != 0;
            this._iLinkIndicativeMassQuotingEligible = (value & InstAttribValueBits.I_LINK_INDICATIVE_MASS_QUOTING_ELIGIBLE) != 0;
            this._otcEligible = (value & InstAttribValueBits.OTC_ELIGIBLE) != 0;
            this._efrEligible = (value & InstAttribValueBits.EFR_ELIGIBLE) != 0;
            this._efsEligible = (value & InstAttribValueBits.EFS_ELIGIBLE) != 0;
            this._ebfEligible = (value & InstAttribValueBits.EBF_ELIGIBLE) != 0;
            this._efpEligible = (value & InstAttribValueBits.EFP_ELIGIBLE) != 0;
            this._blockTradeEligible = (value & InstAttribValueBits.BLOCK_TRADE_ELIGIBLE) != 0;
            this._orderCrossEligible = (value & InstAttribValueBits.ORDER_CROSS_ELIGIBLE) != 0;
            this._electronicMatchEligible = (value & InstAttribValueBits.ELECTRONIC_MATCH_ELIGIBLE) != 0;
        }

        public static InstAttribValue parse(Container container, IBinaryProtocolElement element) {
            return new InstAttribValue(BigEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("InstAttribValue:\n");
            sb.append(StringUtils.repeat('.', 0) + (this._reserved10?"1":"0") + StringUtils.repeat('.', 22) +" // Reserved 10\n");
            sb.append(StringUtils.repeat('.', 1) + (this._variableCabEligible?"1":"0") + StringUtils.repeat('.', 21) +" // Variable Cab Eligible\n");
            sb.append(StringUtils.repeat('.', 2) + (this._triangulationEligible?"1":"0") + StringUtils.repeat('.', 20) +" // Triangulation Eligible\n");
            sb.append(StringUtils.repeat('.', 3) + (this._impliedMatchingEligibility?"1":"0") + StringUtils.repeat('.', 19) +" // Implied Matching Eligibility\n");
            sb.append(StringUtils.repeat('.', 4) + (this._gtOrdersEligibility?"1":"0") + StringUtils.repeat('.', 18) +" // Gt Orders Eligibility\n");
            sb.append(StringUtils.repeat('.', 5) + (this._dailyProductEligibility?"1":"0") + StringUtils.repeat('.', 17) +" // Daily Product Eligibility\n");
            sb.append(StringUtils.repeat('.', 6) + (this._variableProductEligibility?"1":"0") + StringUtils.repeat('.', 16) +" // Variable Product Eligibility\n");
            sb.append(StringUtils.repeat('.', 7) + (this._decayingProductEligibility?"1":"0") + StringUtils.repeat('.', 15) +" // Decaying Product Eligibility\n");
            sb.append(StringUtils.repeat('.', 8) + (this._zeroPriceOutrightEligible?"1":"0") + StringUtils.repeat('.', 14) +" // Zero Price Outright Eligible\n");
            sb.append(StringUtils.repeat('.', 9) + (this._rfqCrossEligible?"1":"0") + StringUtils.repeat('.', 13) +" // Rfq Cross Eligible\n");
            sb.append(StringUtils.repeat('.', 10) + (this._volatilityQuotedOption?"1":"0") + StringUtils.repeat('.', 12) +" // Volatility Quoted Option\n");
            sb.append(StringUtils.repeat('.', 11) + (this._isFractional?"1":"0") + StringUtils.repeat('.', 11) +" // Is Fractional\n");
            sb.append(StringUtils.repeat('.', 12) + (this._negativePriceOutrightEligible?"1":"0") + StringUtils.repeat('.', 10) +" // Negative Price Outright Eligible\n");
            sb.append(StringUtils.repeat('.', 13) + (this._negativeStrikeEligible?"1":"0") + StringUtils.repeat('.', 9) +" // Negative Strike Eligible\n");
            sb.append(StringUtils.repeat('.', 14) + (this._iLinkIndicativeMassQuotingEligible?"1":"0") + StringUtils.repeat('.', 8) +" // I Link Indicative Mass Quoting Eligible\n");
            sb.append(StringUtils.repeat('.', 15) + (this._otcEligible?"1":"0") + StringUtils.repeat('.', 7) +" // Otc Eligible\n");
            sb.append(StringUtils.repeat('.', 16) + (this._efrEligible?"1":"0") + StringUtils.repeat('.', 6) +" // Efr Eligible\n");
            sb.append(StringUtils.repeat('.', 17) + (this._efsEligible?"1":"0") + StringUtils.repeat('.', 5) +" // Efs Eligible\n");
            sb.append(StringUtils.repeat('.', 18) + (this._ebfEligible?"1":"0") + StringUtils.repeat('.', 4) +" // Ebf Eligible\n");
            sb.append(StringUtils.repeat('.', 19) + (this._efpEligible?"1":"0") + StringUtils.repeat('.', 3) +" // Efp Eligible\n");
            sb.append(StringUtils.repeat('.', 20) + (this._blockTradeEligible?"1":"0") + StringUtils.repeat('.', 2) +" // Block Trade Eligible\n");
            sb.append(StringUtils.repeat('.', 21) + (this._orderCrossEligible?"1":"0") + StringUtils.repeat('.', 1) +" // Order Cross Eligible\n");
            sb.append(StringUtils.repeat('.', 22) + (this._electronicMatchEligible?"1":"0") + StringUtils.repeat('.', 0) +" // Electronic Match Eligible\n");
            return sb.toString();
        }
    }

    /**
    * Inst Attrib Group
    */
    public static class InstAttribGroup implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public InstAttribValue instAttribValue;

        // constructor for Inst Attrib Group
        private InstAttribGroup(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Inst Attrib Group
        public static InstAttribGroup parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new InstAttribGroup(parentElement);

            element.instAttribValue = InstAttribValue.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.instAttribValue).append("\n");
            return sb.toString();
        }
    }

    /**
    * Inst Attrib Groups
    */
    public static class InstAttribGroups implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public GroupSize groupSize;
        public List<InstAttribGroup> instAttribGroupList = new ArrayList<>();

        // constructor for Inst Attrib Groups
        private InstAttribGroups(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Inst Attrib Groups
        public static InstAttribGroups parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new InstAttribGroups(parentElement);

            element.groupSize = GroupSize.parse(container, element);

            // Inst Attrib Group: Struct of 1 fields
            for (int i = 0; i < element.groupSize.numInGroup.value; i++) {
                element.instAttribGroupList.add(InstAttribGroup.parse(container, element));
            }

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.groupSize).append("\n");
            for (var instAttribGroup: instAttribGroupList) {
                sb.append(instAttribGroup).append("\n");
            }
            return sb.toString();
        }
    }

    // Market Depth: 1 Byte Signed Fixed Width Integer
    public static class MarketDepth implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public MarketDepth(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static MarketDepth parse(Container container, IBinaryProtocolElement element) {
            return new MarketDepth(LittleEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "marketDepth = "+ this.value;
        }
    }

    // Md Feed Type: 3 Byte Ascii String
    public static class MdFeedType implements IBinaryProtocolElement {
        public static final int LENGTH = 3;
        public final IBinaryProtocolElement parent;
        public final String value;

        public MdFeedType(String value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static MdFeedType parse(Container container, IBinaryProtocolElement element) {
            return new MdFeedType(BigEndianUtils.toString(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "mdFeedType = "+ this.value;
        }
    }

    /**
    * M D Feed Types Group
    */
    public static class MDFeedTypesGroup implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public MdFeedType mdFeedType;
        public MarketDepth marketDepth;

        // constructor for M D Feed Types Group
        private MDFeedTypesGroup(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for M D Feed Types Group
        public static MDFeedTypesGroup parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new MDFeedTypesGroup(parentElement);

            element.mdFeedType = MdFeedType.parse(container, element);
            element.marketDepth = MarketDepth.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.mdFeedType).append("\n");
            sb.append(this.marketDepth).append("\n");
            return sb.toString();
        }
    }

    /**
    * M D Feed Types Groups
    */
    public static class MDFeedTypesGroups implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public GroupSize groupSize;
        public List<MDFeedTypesGroup> mDFeedTypesGroupList = new ArrayList<>();

        // constructor for M D Feed Types Groups
        private MDFeedTypesGroups(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for M D Feed Types Groups
        public static MDFeedTypesGroups parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new MDFeedTypesGroups(parentElement);

            element.groupSize = GroupSize.parse(container, element);

            // M D Feed Types Group: Struct of 2 fields
            for (int i = 0; i < element.groupSize.numInGroup.value; i++) {
                element.mDFeedTypesGroupList.add(MDFeedTypesGroup.parse(container, element));
            }

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.groupSize).append("\n");
            for (var mDFeedTypesGroup: mDFeedTypesGroupList) {
                sb.append(mDFeedTypesGroup).append("\n");
            }
            return sb.toString();
        }
    }

    // Event Time: 8 Byte Unsigned Fixed Width Integer
    public static class EventTime implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public EventTime(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static EventTime parse(Container container, IBinaryProtocolElement element) {
            return new EventTime(LittleEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "eventTime = "+ this.value;
        }
    }

    // Event Type: 1 Byte Unsigned Fixed Width Integer Enum with 2 values
    public static class EventType implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_EVENT_TYPE value;

        public EventType(ENUM_EVENT_TYPE value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static EventType parse(Container container, IBinaryProtocolElement element) {
            return new EventType(ENUM_EVENT_TYPE.valueOf((int)LittleEndianUtils.toByte(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "eventType = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    /**
    * Events Group
    */
    public static class EventsGroup implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public EventType eventType;
        public EventTime eventTime;

        // constructor for Events Group
        private EventsGroup(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Events Group
        public static EventsGroup parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new EventsGroup(parentElement);

            element.eventType = EventType.parse(container, element);
            element.eventTime = EventTime.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.eventType).append("\n");
            sb.append(this.eventTime).append("\n");
            return sb.toString();
        }
    }

    /**
    * Events Groups
    */
    public static class EventsGroups implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public GroupSize groupSize;
        public List<EventsGroup> eventsGroupList = new ArrayList<>();

        // constructor for Events Groups
        private EventsGroups(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Events Groups
        public static EventsGroups parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new EventsGroups(parentElement);

            element.groupSize = GroupSize.parse(container, element);

            // Events Group: Struct of 2 fields
            for (int i = 0; i < element.groupSize.numInGroup.value; i++) {
                element.eventsGroupList.add(EventsGroup.parse(container, element));
            }

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.groupSize).append("\n");
            for (var eventsGroup: eventsGroupList) {
                sb.append(eventsGroup).append("\n");
            }
            return sb.toString();
        }
    }

    // Trading Reference Date: 2 Byte Unsigned Fixed Width Integer Nullable
    public static class TradingReferenceDate implements IBinaryProtocolElement {
        public static final int LENGTH = 2;
        public final IBinaryProtocolElement parent;
        public final Short value;

        public TradingReferenceDate(Short value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static TradingReferenceDate parse(Container container, IBinaryProtocolElement element) {
            return new TradingReferenceDate(LittleEndianUtils.toShort(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "tradingReferenceDate = "+ this.value;
        }
    }

    // Sub Fraction: 1 Byte Unsigned Fixed Width Integer Nullable
    public static class SubFraction implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public SubFraction(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static SubFraction parse(Container container, IBinaryProtocolElement element) {
            return new SubFraction(LittleEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "subFraction = "+ this.value;
        }
    }

    // Main Fraction: 1 Byte Unsigned Fixed Width Integer Nullable
    public static class MainFraction implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public MainFraction(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static MainFraction parse(Container container, IBinaryProtocolElement element) {
            return new MainFraction(LittleEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "mainFraction = "+ this.value;
        }
    }

    // Max Price Variation Pricenul L 9: 8 Byte Signed Fixed Width Integer Nullable
    public static class MaxPriceVariationPricenulL9 implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public MaxPriceVariationPricenulL9(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value/1000000000;
        }

        public static MaxPriceVariationPricenulL9 parse(Container container, IBinaryProtocolElement element) {
            return new MaxPriceVariationPricenulL9(LittleEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "maxPriceVariationPricenulL9 = "+ this.value;
        }
    }

    // Low Limit Price Pricenul L 9: 8 Byte Signed Fixed Width Integer Nullable
    public static class LowLimitPricePricenulL9 implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public LowLimitPricePricenulL9(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value/1000000000;
        }

        public static LowLimitPricePricenulL9 parse(Container container, IBinaryProtocolElement element) {
            return new LowLimitPricePricenulL9(LittleEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "lowLimitPricePricenulL9 = "+ this.value;
        }
    }

    // High Limit Price Pricenul L 9: 8 Byte Signed Fixed Width Integer Nullable
    public static class HighLimitPricePricenulL9 implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public HighLimitPricePricenulL9(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value/1000000000;
        }

        public static HighLimitPricePricenulL9 parse(Container container, IBinaryProtocolElement element) {
            return new HighLimitPricePricenulL9(LittleEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "highLimitPricePricenulL9 = "+ this.value;
        }
    }

    // Cleared Volume: 4 Byte Signed Fixed Width Integer Nullable
    public static class ClearedVolume implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public ClearedVolume(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static ClearedVolume parse(Container container, IBinaryProtocolElement element) {
            return new ClearedVolume(LittleEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "clearedVolume = "+ this.value;
        }
    }

    // Open Interest Qty: 4 Byte Signed Fixed Width Integer Nullable
    public static class OpenInterestQty implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public OpenInterestQty(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static OpenInterestQty parse(Container container, IBinaryProtocolElement element) {
            return new OpenInterestQty(LittleEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "openInterestQty = "+ this.value;
        }
    }

    // Final Daily: 1 Bit
    public static class FinalDaily implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public FinalDaily(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static FinalDaily parse(Container container, IBinaryProtocolElement element) {
            return new FinalDaily(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "finalDaily = "+ this.value;
        }
    }

    // Actual: 1 Bit
    public static class Actual implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public Actual(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Actual parse(Container container, IBinaryProtocolElement element) {
            return new Actual(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "actual = "+ this.value;
        }
    }

    // Rounded: 1 Bit
    public static class Rounded implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public Rounded(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Rounded parse(Container container, IBinaryProtocolElement element) {
            return new Rounded(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "rounded = "+ this.value;
        }
    }

    // Intraday: 1 Bit
    public static class Intraday implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public Intraday(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Intraday parse(Container container, IBinaryProtocolElement element) {
            return new Intraday(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "intraday = "+ this.value;
        }
    }

    // Reserved Bits: 3 Bit
    public static class ReservedBits implements IBinaryProtocolElement {
        public static final int LENGTH = 3;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public ReservedBits(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static ReservedBits parse(Container container, IBinaryProtocolElement element) {
            return new ReservedBits(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "reservedBits = "+ this.value;
        }
    }

    // Null Value: 1 Bit
    public static class NullValue implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public NullValue(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static NullValue parse(Container container, IBinaryProtocolElement element) {
            return new NullValue(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "nullValue = "+ this.value;
        }
    }

    // bit values for SettlPriceType
    public static class SettlPriceTypeBits {
        public static final byte NULL_VALUE     = (byte)(1 << 5);   
        public static final byte RESERVED_BITS  = (byte)(1 << 4);   
        public static final byte INTRADAY       = (byte)(1 << 3);   
        public static final byte ROUNDED        = (byte)(1 << 2);   
        public static final byte ACTUAL         = (byte)(1 << 1);   
        public static final byte FINAL_DAILY    = (byte)(1 << 0);   
    }

    // Settl Price Type: Struct of 6 fields
    public static class SettlPriceType implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement _parent;

        // bits
        public final boolean  _nullValue;    //  0 
        public final boolean  _reservedBits; //  1 
        public final boolean  _intraday;     //  2 
        public final boolean  _rounded;      //  3 
        public final boolean  _actual;       //  4 
        public final boolean  _finalDaily;   //  5 

        public SettlPriceType (Byte value, IBinaryProtocolElement element) {
            this._parent = element;

            this._nullValue = (value & SettlPriceTypeBits.NULL_VALUE) != 0;
            this._reservedBits = (value & SettlPriceTypeBits.RESERVED_BITS) != 0;
            this._intraday = (value & SettlPriceTypeBits.INTRADAY) != 0;
            this._rounded = (value & SettlPriceTypeBits.ROUNDED) != 0;
            this._actual = (value & SettlPriceTypeBits.ACTUAL) != 0;
            this._finalDaily = (value & SettlPriceTypeBits.FINAL_DAILY) != 0;
        }

        public static SettlPriceType parse(Container container, IBinaryProtocolElement element) {
            return new SettlPriceType(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("SettlPriceType:\n");
            sb.append(StringUtils.repeat('.', 0) + (this._nullValue?"1":"0") + StringUtils.repeat('.', 5) +" // Null Value\n");
            sb.append(StringUtils.repeat('.', 1) + (this._reservedBits?"1":"0") + StringUtils.repeat('.', 4) +" // Reserved Bits\n");
            sb.append(StringUtils.repeat('.', 2) + (this._intraday?"1":"0") + StringUtils.repeat('.', 3) +" // Intraday\n");
            sb.append(StringUtils.repeat('.', 3) + (this._rounded?"1":"0") + StringUtils.repeat('.', 2) +" // Rounded\n");
            sb.append(StringUtils.repeat('.', 4) + (this._actual?"1":"0") + StringUtils.repeat('.', 1) +" // Actual\n");
            sb.append(StringUtils.repeat('.', 5) + (this._finalDaily?"1":"0") + StringUtils.repeat('.', 0) +" // Final Daily\n");
            return sb.toString();
        }
    }

    // Trading Reference Price Pricenul L 9: 8 Byte Signed Fixed Width Integer Nullable
    public static class TradingReferencePricePricenulL9 implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public TradingReferencePricePricenulL9(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value/1000000000;
        }

        public static TradingReferencePricePricenulL9 parse(Container container, IBinaryProtocolElement element) {
            return new TradingReferencePricePricenulL9(LittleEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "tradingReferencePricePricenulL9 = "+ this.value;
        }
    }

    // Unit Of Measure: 30 Byte Ascii String
    public static class UnitOfMeasure implements IBinaryProtocolElement {
        public static final int LENGTH = 30;
        public final IBinaryProtocolElement parent;
        public final String value;

        public UnitOfMeasure(String value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static UnitOfMeasure parse(Container container, IBinaryProtocolElement element) {
            return new UnitOfMeasure(BigEndianUtils.toString(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "unitOfMeasure = "+ this.value;
        }
    }

    // Tick Rule: 1 Byte Signed Fixed Width Integer Nullable
    public static class TickRule implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public TickRule(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static TickRule parse(Container container, IBinaryProtocolElement element) {
            return new TickRule(LittleEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "tickRule = "+ this.value;
        }
    }

    // Price Ratio Pricenul L 9: 8 Byte Signed Fixed Width Integer Nullable
    public static class PriceRatioPricenulL9 implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public PriceRatioPricenulL9(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value/1000000000;
        }

        public static PriceRatioPricenulL9 parse(Container container, IBinaryProtocolElement element) {
            return new PriceRatioPricenulL9(LittleEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "priceRatioPricenulL9 = "+ this.value;
        }
    }

    // Price Display Format: 1 Byte Unsigned Fixed Width Integer Nullable
    public static class PriceDisplayFormat implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public PriceDisplayFormat(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static PriceDisplayFormat parse(Container container, IBinaryProtocolElement element) {
            return new PriceDisplayFormat(LittleEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "priceDisplayFormat = "+ this.value;
        }
    }

    // Display Factor Decimal 9: 8 Byte Signed Fixed Width Integer
    public static class DisplayFactorDecimal9 implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public DisplayFactorDecimal9(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value/1000000000;
        }

        public static DisplayFactorDecimal9 parse(Container container, IBinaryProtocolElement element) {
            return new DisplayFactorDecimal9(LittleEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "displayFactorDecimal9 = "+ this.value;
        }
    }

    // Min Price Increment Optional: 8 Byte Signed Fixed Width Integer Nullable
    public static class MinPriceIncrementOptional implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public MinPriceIncrementOptional(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value/1000000000;
        }

        public static MinPriceIncrementOptional parse(Container container, IBinaryProtocolElement element) {
            return new MinPriceIncrementOptional(LittleEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "minPriceIncrementOptional = "+ this.value;
        }
    }

    // Max Trade Vol: 4 Byte Unsigned Fixed Width Integer
    public static class MaxTradeVol implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public MaxTradeVol(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static MaxTradeVol parse(Container container, IBinaryProtocolElement element) {
            return new MaxTradeVol(LittleEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "maxTradeVol = "+ this.value;
        }
    }

    // Min Trade Vol: 4 Byte Unsigned Fixed Width Integer
    public static class MinTradeVol implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public MinTradeVol(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static MinTradeVol parse(Container container, IBinaryProtocolElement element) {
            return new MinTradeVol(LittleEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "minTradeVol = "+ this.value;
        }
    }

    // Match Algorithm: 1 Byte Ascii String
    public static class MatchAlgorithm implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Character value;

        public MatchAlgorithm(Character value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static MatchAlgorithm parse(Container container, IBinaryProtocolElement element) {
            return new MatchAlgorithm(BigEndianUtils.toCharacter(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "matchAlgorithm = "+ this.value;
        }
    }

    // User Defined Instrument: 1 Byte Ascii String
    public static class UserDefinedInstrument implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Character value;

        public UserDefinedInstrument(Character value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static UserDefinedInstrument parse(Container container, IBinaryProtocolElement element) {
            return new UserDefinedInstrument(BigEndianUtils.toCharacter(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "userDefinedInstrument = "+ this.value;
        }
    }

    // Security Sub Type: 5 Byte Ascii String
    public static class SecuritySubType implements IBinaryProtocolElement {
        public static final int LENGTH = 5;
        public final IBinaryProtocolElement parent;
        public final String value;

        public SecuritySubType(String value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static SecuritySubType parse(Container container, IBinaryProtocolElement element) {
            return new SecuritySubType(BigEndianUtils.toString(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "securitySubType = "+ this.value;
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

    // Week: 1 Byte Unsigned Fixed Width Integer Nullable
    public static class Week implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public Week(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Week parse(Container container, IBinaryProtocolElement element) {
            return new Week(LittleEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "week = "+ this.value;
        }
    }

    // Day: 1 Byte Unsigned Fixed Width Integer Nullable
    public static class Day implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public Day(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Day parse(Container container, IBinaryProtocolElement element) {
            return new Day(LittleEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "day = "+ this.value;
        }
    }

    // Month: 1 Byte Unsigned Fixed Width Integer Nullable
    public static class Month implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public Month(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Month parse(Container container, IBinaryProtocolElement element) {
            return new Month(LittleEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "month = "+ this.value;
        }
    }

    // Year: 2 Byte Unsigned Fixed Width Integer Nullable
    public static class Year implements IBinaryProtocolElement {
        public static final int LENGTH = 2;
        public final IBinaryProtocolElement parent;
        public final Short value;

        public Year(Short value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Year parse(Container container, IBinaryProtocolElement element) {
            return new Year(LittleEndianUtils.toShort(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "year = "+ this.value;
        }
    }

    /**
    * Maturity Month Year
    */
    public static class MaturityMonthYear implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Year year;
        public Month month;
        public Day day;
        public Week week;

        // constructor for Maturity Month Year
        private MaturityMonthYear(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Maturity Month Year
        public static MaturityMonthYear parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new MaturityMonthYear(parentElement);

            element.year = Year.parse(container, element);
            element.month = Month.parse(container, element);
            element.day = Day.parse(container, element);
            element.week = Week.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.year).append("\n");
            sb.append(this.month).append("\n");
            sb.append(this.day).append("\n");
            sb.append(this.week).append("\n");
            return sb.toString();
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

    // Security Type: 6 Byte Ascii String
    public static class SecurityType implements IBinaryProtocolElement {
        public static final int LENGTH = 6;
        public final IBinaryProtocolElement parent;
        public final String value;

        public SecurityType(String value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static SecurityType parse(Container container, IBinaryProtocolElement element) {
            return new SecurityType(BigEndianUtils.toString(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "securityType = "+ this.value;
        }
    }

    // Security Id: 4 Byte Signed Fixed Width Integer
    public static class SecurityId implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public SecurityId(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static SecurityId parse(Container container, IBinaryProtocolElement element) {
            return new SecurityId(LittleEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "securityId = "+ this.value;
        }
    }

    // Symbol: 20 Byte Ascii String
    public static class Symbol implements IBinaryProtocolElement {
        public static final int LENGTH = 20;
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

    // Asset: 6 Byte Ascii String
    public static class Asset implements IBinaryProtocolElement {
        public static final int LENGTH = 6;
        public final IBinaryProtocolElement parent;
        public final String value;

        public Asset(String value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Asset parse(Container container, IBinaryProtocolElement element) {
            return new Asset(BigEndianUtils.toString(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "asset = "+ this.value;
        }
    }

    // Security Group: 6 Byte Ascii String
    public static class SecurityGroup implements IBinaryProtocolElement {
        public static final int LENGTH = 6;
        public final IBinaryProtocolElement parent;
        public final String value;

        public SecurityGroup(String value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static SecurityGroup parse(Container container, IBinaryProtocolElement element) {
            return new SecurityGroup(BigEndianUtils.toString(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "securityGroup = "+ this.value;
        }
    }

    // Security Exchange: 4 Byte Ascii String
    public static class SecurityExchange implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final String value;

        public SecurityExchange(String value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static SecurityExchange parse(Container container, IBinaryProtocolElement element) {
            return new SecurityExchange(BigEndianUtils.toString(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "securityExchange = "+ this.value;
        }
    }

    // Underlying Product Optional: 1 Byte Unsigned Fixed Width Integer Nullable
    public static class UnderlyingProductOptional implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public UnderlyingProductOptional(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static UnderlyingProductOptional parse(Container container, IBinaryProtocolElement element) {
            return new UnderlyingProductOptional(LittleEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "underlyingProductOptional = "+ this.value;
        }
    }

    // Market Segment Id: 1 Byte Unsigned Fixed Width Integer
    public static class MarketSegmentId implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public MarketSegmentId(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static MarketSegmentId parse(Container container, IBinaryProtocolElement element) {
            return new MarketSegmentId(LittleEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "marketSegmentId = "+ this.value;
        }
    }

    // Appl Id: 2 Byte Signed Fixed Width Integer
    public static class ApplId implements IBinaryProtocolElement {
        public static final int LENGTH = 2;
        public final IBinaryProtocolElement parent;
        public final Short value;

        public ApplId(Short value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static ApplId parse(Container container, IBinaryProtocolElement element) {
            return new ApplId(LittleEndianUtils.toShort(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "applId = "+ this.value;
        }
    }

    // Md Security Trading Status: 1 Byte Unsigned Fixed Width Integer Enum with 12 values
    public static class MdSecurityTradingStatus implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_MD_SECURITY_TRADING_STATUS value;

        public MdSecurityTradingStatus(ENUM_MD_SECURITY_TRADING_STATUS value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static MdSecurityTradingStatus parse(Container container, IBinaryProtocolElement element) {
            return new MdSecurityTradingStatus(ENUM_MD_SECURITY_TRADING_STATUS.valueOf((int)LittleEndianUtils.toByte(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "mdSecurityTradingStatus = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Last Update Time: 8 Byte Unsigned Fixed Width Integer
    public static class LastUpdateTime implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public LastUpdateTime(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static LastUpdateTime parse(Container container, IBinaryProtocolElement element) {
            return new LastUpdateTime(LittleEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "lastUpdateTime = "+ this.value;
        }
    }

    // Security Update Action: 1 Byte Ascii String Enum with 3 values
    public static class SecurityUpdateAction implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_SECURITY_UPDATE_ACTION value;

        public SecurityUpdateAction(ENUM_SECURITY_UPDATE_ACTION value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static SecurityUpdateAction parse(Container container, IBinaryProtocolElement element) {
            return new SecurityUpdateAction(ENUM_SECURITY_UPDATE_ACTION.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "securityUpdateAction = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Tot Num Reports Optional: 4 Byte Unsigned Fixed Width Integer Nullable
    public static class TotNumReportsOptional implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public TotNumReportsOptional(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static TotNumReportsOptional parse(Container container, IBinaryProtocolElement element) {
            return new TotNumReportsOptional(LittleEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "totNumReportsOptional = "+ this.value;
        }
    }

    // Last Trade Msg: 1 Bit
    public static class LastTradeMsg implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public LastTradeMsg(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static LastTradeMsg parse(Container container, IBinaryProtocolElement element) {
            return new LastTradeMsg(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "lastTradeMsg = "+ this.value;
        }
    }

    // Last Volume Msg: 1 Bit
    public static class LastVolumeMsg implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public LastVolumeMsg(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static LastVolumeMsg parse(Container container, IBinaryProtocolElement element) {
            return new LastVolumeMsg(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "lastVolumeMsg = "+ this.value;
        }
    }

    // Last Quote Msg: 1 Bit
    public static class LastQuoteMsg implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public LastQuoteMsg(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static LastQuoteMsg parse(Container container, IBinaryProtocolElement element) {
            return new LastQuoteMsg(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "lastQuoteMsg = "+ this.value;
        }
    }

    // Last Stats Msg: 1 Bit
    public static class LastStatsMsg implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public LastStatsMsg(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static LastStatsMsg parse(Container container, IBinaryProtocolElement element) {
            return new LastStatsMsg(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "lastStatsMsg = "+ this.value;
        }
    }

    // Last Implied Msg: 1 Bit
    public static class LastImpliedMsg implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public LastImpliedMsg(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static LastImpliedMsg parse(Container container, IBinaryProtocolElement element) {
            return new LastImpliedMsg(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "lastImpliedMsg = "+ this.value;
        }
    }

    // Recovery Msg: 1 Bit
    public static class RecoveryMsg implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public RecoveryMsg(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static RecoveryMsg parse(Container container, IBinaryProtocolElement element) {
            return new RecoveryMsg(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "recoveryMsg = "+ this.value;
        }
    }

    // Reserved: 1 Bit
    public static class Reserved implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
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

    // End Of Event: 1 Bit
    public static class EndOfEvent implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public EndOfEvent(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static EndOfEvent parse(Container container, IBinaryProtocolElement element) {
            return new EndOfEvent(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "endOfEvent = "+ this.value;
        }
    }

    // bit values for MatchEventIndicator
    public static class MatchEventIndicatorBits {
        public static final byte END_OF_EVENT      = (byte)(1 << 7);   
        public static final byte RESERVED          = (byte)(1 << 6);   
        public static final byte RECOVERY_MSG      = (byte)(1 << 5);   
        public static final byte LAST_IMPLIED_MSG  = (byte)(1 << 4);   
        public static final byte LAST_STATS_MSG    = (byte)(1 << 3);   
        public static final byte LAST_QUOTE_MSG    = (byte)(1 << 2);   
        public static final byte LAST_VOLUME_MSG   = (byte)(1 << 1);   
        public static final byte LAST_TRADE_MSG    = (byte)(1 << 0);   
    }

    // Match Event Indicator: Struct of 8 fields
    public static class MatchEventIndicator implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement _parent;

        // bits
        public final boolean  _endOfEvent;      //  0 
        public final boolean  _reserved;        //  1 
        public final boolean  _recoveryMsg;     //  2 
        public final boolean  _lastImpliedMsg;  //  3 
        public final boolean  _lastStatsMsg;    //  4 
        public final boolean  _lastQuoteMsg;    //  5 
        public final boolean  _lastVolumeMsg;   //  6 
        public final boolean  _lastTradeMsg;    //  7 

        public MatchEventIndicator (Byte value, IBinaryProtocolElement element) {
            this._parent = element;

            this._endOfEvent = (value & MatchEventIndicatorBits.END_OF_EVENT) != 0;
            this._reserved = (value & MatchEventIndicatorBits.RESERVED) != 0;
            this._recoveryMsg = (value & MatchEventIndicatorBits.RECOVERY_MSG) != 0;
            this._lastImpliedMsg = (value & MatchEventIndicatorBits.LAST_IMPLIED_MSG) != 0;
            this._lastStatsMsg = (value & MatchEventIndicatorBits.LAST_STATS_MSG) != 0;
            this._lastQuoteMsg = (value & MatchEventIndicatorBits.LAST_QUOTE_MSG) != 0;
            this._lastVolumeMsg = (value & MatchEventIndicatorBits.LAST_VOLUME_MSG) != 0;
            this._lastTradeMsg = (value & MatchEventIndicatorBits.LAST_TRADE_MSG) != 0;
        }

        public static MatchEventIndicator parse(Container container, IBinaryProtocolElement element) {
            return new MatchEventIndicator(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("MatchEventIndicator:\n");
            sb.append(StringUtils.repeat('.', 0) + (this._endOfEvent?"1":"0") + StringUtils.repeat('.', 7) +" // End Of Event\n");
            sb.append(StringUtils.repeat('.', 1) + (this._reserved?"1":"0") + StringUtils.repeat('.', 6) +" // Reserved\n");
            sb.append(StringUtils.repeat('.', 2) + (this._recoveryMsg?"1":"0") + StringUtils.repeat('.', 5) +" // Recovery Msg\n");
            sb.append(StringUtils.repeat('.', 3) + (this._lastImpliedMsg?"1":"0") + StringUtils.repeat('.', 4) +" // Last Implied Msg\n");
            sb.append(StringUtils.repeat('.', 4) + (this._lastStatsMsg?"1":"0") + StringUtils.repeat('.', 3) +" // Last Stats Msg\n");
            sb.append(StringUtils.repeat('.', 5) + (this._lastQuoteMsg?"1":"0") + StringUtils.repeat('.', 2) +" // Last Quote Msg\n");
            sb.append(StringUtils.repeat('.', 6) + (this._lastVolumeMsg?"1":"0") + StringUtils.repeat('.', 1) +" // Last Volume Msg\n");
            sb.append(StringUtils.repeat('.', 7) + (this._lastTradeMsg?"1":"0") + StringUtils.repeat('.', 0) +" // Last Trade Msg\n");
            return sb.toString();
        }
    }

    /**
    * Md Instrument Definition Spread 56
    */
    public static class MdInstrumentDefinitionSpread56 implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public MatchEventIndicator matchEventIndicator;
        public TotNumReportsOptional totNumReportsOptional;
        public SecurityUpdateAction securityUpdateAction;
        public LastUpdateTime lastUpdateTime;
        public MdSecurityTradingStatus mdSecurityTradingStatus;
        public ApplId applId;
        public MarketSegmentId marketSegmentId;
        public UnderlyingProductOptional underlyingProductOptional;
        public SecurityExchange securityExchange;
        public SecurityGroup securityGroup;
        public Asset asset;
        public Symbol symbol;
        public SecurityId securityId;
        public SecurityType securityType;
        public CfiCode cfiCode;
        public MaturityMonthYear maturityMonthYear;
        public Currency currency;
        public SecuritySubType securitySubType;
        public UserDefinedInstrument userDefinedInstrument;
        public MatchAlgorithm matchAlgorithm;
        public MinTradeVol minTradeVol;
        public MaxTradeVol maxTradeVol;
        public MinPriceIncrementOptional minPriceIncrementOptional;
        public DisplayFactorDecimal9 displayFactorDecimal9;
        public PriceDisplayFormat priceDisplayFormat;
        public PriceRatioPricenulL9 priceRatioPricenulL9;
        public TickRule tickRule;
        public UnitOfMeasure unitOfMeasure;
        public TradingReferencePricePricenulL9 tradingReferencePricePricenulL9;
        public SettlPriceType settlPriceType;
        public OpenInterestQty openInterestQty;
        public ClearedVolume clearedVolume;
        public HighLimitPricePricenulL9 highLimitPricePricenulL9;
        public LowLimitPricePricenulL9 lowLimitPricePricenulL9;
        public MaxPriceVariationPricenulL9 maxPriceVariationPricenulL9;
        public MainFraction mainFraction;
        public SubFraction subFraction;
        public TradingReferenceDate tradingReferenceDate;
        public EventsGroups eventsGroups;
        public MDFeedTypesGroups mDFeedTypesGroups;
        public InstAttribGroups instAttribGroups;
        public LotTypeRulesGroups lotTypeRulesGroups;
        public MDInstrumentDefinitionSpreadLegGroups mDInstrumentDefinitionSpreadLegGroups;

        // constructor for Md Instrument Definition Spread 56
        private MdInstrumentDefinitionSpread56(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Md Instrument Definition Spread 56
        public static MdInstrumentDefinitionSpread56 parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new MdInstrumentDefinitionSpread56(parentElement);

            element.matchEventIndicator = MatchEventIndicator.parse(container, element);
            element.totNumReportsOptional = TotNumReportsOptional.parse(container, element);
            element.securityUpdateAction = SecurityUpdateAction.parse(container, element);
            element.lastUpdateTime = LastUpdateTime.parse(container, element);
            element.mdSecurityTradingStatus = MdSecurityTradingStatus.parse(container, element);
            element.applId = ApplId.parse(container, element);
            element.marketSegmentId = MarketSegmentId.parse(container, element);
            element.underlyingProductOptional = UnderlyingProductOptional.parse(container, element);
            element.securityExchange = SecurityExchange.parse(container, element);
            element.securityGroup = SecurityGroup.parse(container, element);
            element.asset = Asset.parse(container, element);
            element.symbol = Symbol.parse(container, element);
            element.securityId = SecurityId.parse(container, element);
            element.securityType = SecurityType.parse(container, element);
            element.cfiCode = CfiCode.parse(container, element);
            element.maturityMonthYear = MaturityMonthYear.parse(container, element);
            element.currency = Currency.parse(container, element);
            element.securitySubType = SecuritySubType.parse(container, element);
            element.userDefinedInstrument = UserDefinedInstrument.parse(container, element);
            element.matchAlgorithm = MatchAlgorithm.parse(container, element);
            element.minTradeVol = MinTradeVol.parse(container, element);
            element.maxTradeVol = MaxTradeVol.parse(container, element);
            element.minPriceIncrementOptional = MinPriceIncrementOptional.parse(container, element);
            element.displayFactorDecimal9 = DisplayFactorDecimal9.parse(container, element);
            element.priceDisplayFormat = PriceDisplayFormat.parse(container, element);
            element.priceRatioPricenulL9 = PriceRatioPricenulL9.parse(container, element);
            element.tickRule = TickRule.parse(container, element);
            element.unitOfMeasure = UnitOfMeasure.parse(container, element);
            element.tradingReferencePricePricenulL9 = TradingReferencePricePricenulL9.parse(container, element);
            element.settlPriceType = SettlPriceType.parse(container, element);
            element.openInterestQty = OpenInterestQty.parse(container, element);
            element.clearedVolume = ClearedVolume.parse(container, element);
            element.highLimitPricePricenulL9 = HighLimitPricePricenulL9.parse(container, element);
            element.lowLimitPricePricenulL9 = LowLimitPricePricenulL9.parse(container, element);
            element.maxPriceVariationPricenulL9 = MaxPriceVariationPricenulL9.parse(container, element);
            element.mainFraction = MainFraction.parse(container, element);
            element.subFraction = SubFraction.parse(container, element);
            element.tradingReferenceDate = TradingReferenceDate.parse(container, element);
            element.eventsGroups = EventsGroups.parse(container, element);
            element.mDFeedTypesGroups = MDFeedTypesGroups.parse(container, element);
            element.instAttribGroups = InstAttribGroups.parse(container, element);
            element.lotTypeRulesGroups = LotTypeRulesGroups.parse(container, element);
            element.mDInstrumentDefinitionSpreadLegGroups = MDInstrumentDefinitionSpreadLegGroups.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.matchEventIndicator).append("\n");
            sb.append(this.totNumReportsOptional).append("\n");
            sb.append(this.securityUpdateAction).append("\n");
            sb.append(this.lastUpdateTime).append("\n");
            sb.append(this.mdSecurityTradingStatus).append("\n");
            sb.append(this.applId).append("\n");
            sb.append(this.marketSegmentId).append("\n");
            sb.append(this.underlyingProductOptional).append("\n");
            sb.append(this.securityExchange).append("\n");
            sb.append(this.securityGroup).append("\n");
            sb.append(this.asset).append("\n");
            sb.append(this.symbol).append("\n");
            sb.append(this.securityId).append("\n");
            sb.append(this.securityType).append("\n");
            sb.append(this.cfiCode).append("\n");
            sb.append(this.maturityMonthYear).append("\n");
            sb.append(this.currency).append("\n");
            sb.append(this.securitySubType).append("\n");
            sb.append(this.userDefinedInstrument).append("\n");
            sb.append(this.matchAlgorithm).append("\n");
            sb.append(this.minTradeVol).append("\n");
            sb.append(this.maxTradeVol).append("\n");
            sb.append(this.minPriceIncrementOptional).append("\n");
            sb.append(this.displayFactorDecimal9).append("\n");
            sb.append(this.priceDisplayFormat).append("\n");
            sb.append(this.priceRatioPricenulL9).append("\n");
            sb.append(this.tickRule).append("\n");
            sb.append(this.unitOfMeasure).append("\n");
            sb.append(this.tradingReferencePricePricenulL9).append("\n");
            sb.append(this.settlPriceType).append("\n");
            sb.append(this.openInterestQty).append("\n");
            sb.append(this.clearedVolume).append("\n");
            sb.append(this.highLimitPricePricenulL9).append("\n");
            sb.append(this.lowLimitPricePricenulL9).append("\n");
            sb.append(this.maxPriceVariationPricenulL9).append("\n");
            sb.append(this.mainFraction).append("\n");
            sb.append(this.subFraction).append("\n");
            sb.append(this.tradingReferenceDate).append("\n");
            sb.append(this.eventsGroups).append("\n");
            sb.append(this.mDFeedTypesGroups).append("\n");
            sb.append(this.instAttribGroups).append("\n");
            sb.append(this.lotTypeRulesGroups).append("\n");
            sb.append(this.mDInstrumentDefinitionSpreadLegGroups).append("\n");
            return sb.toString();
        }
    }

    // Related Symbol: 20 Byte Ascii String
    public static class RelatedSymbol implements IBinaryProtocolElement {
        public static final int LENGTH = 20;
        public final IBinaryProtocolElement parent;
        public final String value;

        public RelatedSymbol(String value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static RelatedSymbol parse(Container container, IBinaryProtocolElement element) {
            return new RelatedSymbol(BigEndianUtils.toString(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "relatedSymbol = "+ this.value;
        }
    }

    // Related Security Id: 4 Byte Signed Fixed Width Integer
    public static class RelatedSecurityId implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public RelatedSecurityId(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static RelatedSecurityId parse(Container container, IBinaryProtocolElement element) {
            return new RelatedSecurityId(LittleEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "relatedSecurityId = "+ this.value;
        }
    }

    /**
    * Related Instruments Group
    */
    public static class RelatedInstrumentsGroup implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public RelatedSecurityId relatedSecurityId;
        public RelatedSymbol relatedSymbol;

        // constructor for Related Instruments Group
        private RelatedInstrumentsGroup(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Related Instruments Group
        public static RelatedInstrumentsGroup parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new RelatedInstrumentsGroup(parentElement);

            element.relatedSecurityId = RelatedSecurityId.parse(container, element);
            element.relatedSymbol = RelatedSymbol.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.relatedSecurityId).append("\n");
            sb.append(this.relatedSymbol).append("\n");
            return sb.toString();
        }
    }

    /**
    * Related Instruments Groups
    */
    public static class RelatedInstrumentsGroups implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public GroupSize groupSize;
        public List<RelatedInstrumentsGroup> relatedInstrumentsGroupList = new ArrayList<>();

        // constructor for Related Instruments Groups
        private RelatedInstrumentsGroups(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Related Instruments Groups
        public static RelatedInstrumentsGroups parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new RelatedInstrumentsGroups(parentElement);

            element.groupSize = GroupSize.parse(container, element);

            // Related Instruments Group: Struct of 2 fields
            for (int i = 0; i < element.groupSize.numInGroup.value; i++) {
                element.relatedInstrumentsGroupList.add(RelatedInstrumentsGroup.parse(container, element));
            }

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.groupSize).append("\n");
            for (var relatedInstrumentsGroup: relatedInstrumentsGroupList) {
                sb.append(relatedInstrumentsGroup).append("\n");
            }
            return sb.toString();
        }
    }

    // Underlying Symbol: 20 Byte Ascii String
    public static class UnderlyingSymbol implements IBinaryProtocolElement {
        public static final int LENGTH = 20;
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

    // Underlying Security Id: 4 Byte Signed Fixed Width Integer
    public static class UnderlyingSecurityId implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public UnderlyingSecurityId(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static UnderlyingSecurityId parse(Container container, IBinaryProtocolElement element) {
            return new UnderlyingSecurityId(LittleEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "underlyingSecurityId = "+ this.value;
        }
    }

    /**
    * Underlyings Group
    */
    public static class UnderlyingsGroup implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public UnderlyingSecurityId underlyingSecurityId;
        public UnderlyingSymbol underlyingSymbol;

        // constructor for Underlyings Group
        private UnderlyingsGroup(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Underlyings Group
        public static UnderlyingsGroup parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new UnderlyingsGroup(parentElement);

            element.underlyingSecurityId = UnderlyingSecurityId.parse(container, element);
            element.underlyingSymbol = UnderlyingSymbol.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.underlyingSecurityId).append("\n");
            sb.append(this.underlyingSymbol).append("\n");
            return sb.toString();
        }
    }

    /**
    * Underlyings Groups
    */
    public static class UnderlyingsGroups implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public GroupSize groupSize;
        public List<UnderlyingsGroup> underlyingsGroupList = new ArrayList<>();

        // constructor for Underlyings Groups
        private UnderlyingsGroups(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Underlyings Groups
        public static UnderlyingsGroups parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new UnderlyingsGroups(parentElement);

            element.groupSize = GroupSize.parse(container, element);

            // Underlyings Group: Struct of 2 fields
            for (int i = 0; i < element.groupSize.numInGroup.value; i++) {
                element.underlyingsGroupList.add(UnderlyingsGroup.parse(container, element));
            }

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.groupSize).append("\n");
            for (var underlyingsGroup: underlyingsGroupList) {
                sb.append(underlyingsGroup).append("\n");
            }
            return sb.toString();
        }
    }

    // Unit Of Measure Qty Decimal 9 Null: 8 Byte Signed Fixed Width Integer Nullable
    public static class UnitOfMeasureQtyDecimal9Null implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public UnitOfMeasureQtyDecimal9Null(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value/1000000000;
        }

        public static UnitOfMeasureQtyDecimal9Null parse(Container container, IBinaryProtocolElement element) {
            return new UnitOfMeasureQtyDecimal9Null(LittleEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "unitOfMeasureQtyDecimal9Null = "+ this.value;
        }
    }

    // Min Price Increment Amount Pricenul L 9: 8 Byte Signed Fixed Width Integer Nullable
    public static class MinPriceIncrementAmountPricenulL9 implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public MinPriceIncrementAmountPricenulL9(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value/1000000000;
        }

        public static MinPriceIncrementAmountPricenulL9 parse(Container container, IBinaryProtocolElement element) {
            return new MinPriceIncrementAmountPricenulL9(LittleEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "minPriceIncrementAmountPricenulL9 = "+ this.value;
        }
    }

    // Min Cab Price Pricenul L 9: 8 Byte Signed Fixed Width Integer Nullable
    public static class MinCabPricePricenulL9 implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public MinCabPricePricenulL9(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value/1000000000;
        }

        public static MinCabPricePricenulL9 parse(Container container, IBinaryProtocolElement element) {
            return new MinCabPricePricenulL9(LittleEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "minCabPricePricenulL9 = "+ this.value;
        }
    }

    // Settl Currency: 3 Byte Ascii String
    public static class SettlCurrency implements IBinaryProtocolElement {
        public static final int LENGTH = 3;
        public final IBinaryProtocolElement parent;
        public final String value;

        public SettlCurrency(String value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static SettlCurrency parse(Container container, IBinaryProtocolElement element) {
            return new SettlCurrency(BigEndianUtils.toString(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "settlCurrency = "+ this.value;
        }
    }

    // Strike Currency: 3 Byte Ascii String
    public static class StrikeCurrency implements IBinaryProtocolElement {
        public static final int LENGTH = 3;
        public final IBinaryProtocolElement parent;
        public final String value;

        public StrikeCurrency(String value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static StrikeCurrency parse(Container container, IBinaryProtocolElement element) {
            return new StrikeCurrency(BigEndianUtils.toString(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "strikeCurrency = "+ this.value;
        }
    }

    // Strike Price Pricenul L 9: 8 Byte Signed Fixed Width Integer Nullable
    public static class StrikePricePricenulL9 implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public StrikePricePricenulL9(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value/1000000000;
        }

        public static StrikePricePricenulL9 parse(Container container, IBinaryProtocolElement element) {
            return new StrikePricePricenulL9(LittleEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "strikePricePricenulL9 = "+ this.value;
        }
    }

    // Put Or Call: 1 Byte Unsigned Fixed Width Integer Enum with 2 values
    public static class PutOrCall implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_PUT_OR_CALL value;

        public PutOrCall(ENUM_PUT_OR_CALL value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static PutOrCall parse(Container container, IBinaryProtocolElement element) {
            return new PutOrCall(ENUM_PUT_OR_CALL.valueOf((int)LittleEndianUtils.toByte(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "putOrCall = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Underlying Product: 1 Byte Unsigned Fixed Width Integer
    public static class UnderlyingProduct implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public UnderlyingProduct(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static UnderlyingProduct parse(Container container, IBinaryProtocolElement element) {
            return new UnderlyingProduct(LittleEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "underlyingProduct = "+ this.value;
        }
    }

    /**
    * Md Instrument Definition Option 55
    */
    public static class MdInstrumentDefinitionOption55 implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public MatchEventIndicator matchEventIndicator;
        public TotNumReportsOptional totNumReportsOptional;
        public SecurityUpdateAction securityUpdateAction;
        public LastUpdateTime lastUpdateTime;
        public MdSecurityTradingStatus mdSecurityTradingStatus;
        public ApplId applId;
        public MarketSegmentId marketSegmentId;
        public UnderlyingProduct underlyingProduct;
        public SecurityExchange securityExchange;
        public SecurityGroup securityGroup;
        public Asset asset;
        public Symbol symbol;
        public SecurityId securityId;
        public SecurityType securityType;
        public CfiCode cfiCode;
        public PutOrCall putOrCall;
        public MaturityMonthYear maturityMonthYear;
        public Currency currency;
        public StrikePricePricenulL9 strikePricePricenulL9;
        public StrikeCurrency strikeCurrency;
        public SettlCurrency settlCurrency;
        public MinCabPricePricenulL9 minCabPricePricenulL9;
        public MatchAlgorithm matchAlgorithm;
        public MinTradeVol minTradeVol;
        public MaxTradeVol maxTradeVol;
        public MinPriceIncrementOptional minPriceIncrementOptional;
        public MinPriceIncrementAmountPricenulL9 minPriceIncrementAmountPricenulL9;
        public DisplayFactorDecimal9 displayFactorDecimal9;
        public TickRule tickRule;
        public MainFraction mainFraction;
        public SubFraction subFraction;
        public PriceDisplayFormat priceDisplayFormat;
        public UnitOfMeasure unitOfMeasure;
        public UnitOfMeasureQtyDecimal9Null unitOfMeasureQtyDecimal9Null;
        public TradingReferencePricePricenulL9 tradingReferencePricePricenulL9;
        public SettlPriceType settlPriceType;
        public ClearedVolume clearedVolume;
        public OpenInterestQty openInterestQty;
        public LowLimitPricePricenulL9 lowLimitPricePricenulL9;
        public HighLimitPricePricenulL9 highLimitPricePricenulL9;
        public UserDefinedInstrument userDefinedInstrument;
        public TradingReferenceDate tradingReferenceDate;
        public EventsGroups eventsGroups;
        public MDFeedTypesGroups mDFeedTypesGroups;
        public InstAttribGroups instAttribGroups;
        public LotTypeRulesGroups lotTypeRulesGroups;
        public UnderlyingsGroups underlyingsGroups;
        public RelatedInstrumentsGroups relatedInstrumentsGroups;

        // constructor for Md Instrument Definition Option 55
        private MdInstrumentDefinitionOption55(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Md Instrument Definition Option 55
        public static MdInstrumentDefinitionOption55 parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new MdInstrumentDefinitionOption55(parentElement);

            element.matchEventIndicator = MatchEventIndicator.parse(container, element);
            element.totNumReportsOptional = TotNumReportsOptional.parse(container, element);
            element.securityUpdateAction = SecurityUpdateAction.parse(container, element);
            element.lastUpdateTime = LastUpdateTime.parse(container, element);
            element.mdSecurityTradingStatus = MdSecurityTradingStatus.parse(container, element);
            element.applId = ApplId.parse(container, element);
            element.marketSegmentId = MarketSegmentId.parse(container, element);
            element.underlyingProduct = UnderlyingProduct.parse(container, element);
            element.securityExchange = SecurityExchange.parse(container, element);
            element.securityGroup = SecurityGroup.parse(container, element);
            element.asset = Asset.parse(container, element);
            element.symbol = Symbol.parse(container, element);
            element.securityId = SecurityId.parse(container, element);
            element.securityType = SecurityType.parse(container, element);
            element.cfiCode = CfiCode.parse(container, element);
            element.putOrCall = PutOrCall.parse(container, element);
            element.maturityMonthYear = MaturityMonthYear.parse(container, element);
            element.currency = Currency.parse(container, element);
            element.strikePricePricenulL9 = StrikePricePricenulL9.parse(container, element);
            element.strikeCurrency = StrikeCurrency.parse(container, element);
            element.settlCurrency = SettlCurrency.parse(container, element);
            element.minCabPricePricenulL9 = MinCabPricePricenulL9.parse(container, element);
            element.matchAlgorithm = MatchAlgorithm.parse(container, element);
            element.minTradeVol = MinTradeVol.parse(container, element);
            element.maxTradeVol = MaxTradeVol.parse(container, element);
            element.minPriceIncrementOptional = MinPriceIncrementOptional.parse(container, element);
            element.minPriceIncrementAmountPricenulL9 = MinPriceIncrementAmountPricenulL9.parse(container, element);
            element.displayFactorDecimal9 = DisplayFactorDecimal9.parse(container, element);
            element.tickRule = TickRule.parse(container, element);
            element.mainFraction = MainFraction.parse(container, element);
            element.subFraction = SubFraction.parse(container, element);
            element.priceDisplayFormat = PriceDisplayFormat.parse(container, element);
            element.unitOfMeasure = UnitOfMeasure.parse(container, element);
            element.unitOfMeasureQtyDecimal9Null = UnitOfMeasureQtyDecimal9Null.parse(container, element);
            element.tradingReferencePricePricenulL9 = TradingReferencePricePricenulL9.parse(container, element);
            element.settlPriceType = SettlPriceType.parse(container, element);
            element.clearedVolume = ClearedVolume.parse(container, element);
            element.openInterestQty = OpenInterestQty.parse(container, element);
            element.lowLimitPricePricenulL9 = LowLimitPricePricenulL9.parse(container, element);
            element.highLimitPricePricenulL9 = HighLimitPricePricenulL9.parse(container, element);
            element.userDefinedInstrument = UserDefinedInstrument.parse(container, element);
            element.tradingReferenceDate = TradingReferenceDate.parse(container, element);
            element.eventsGroups = EventsGroups.parse(container, element);
            element.mDFeedTypesGroups = MDFeedTypesGroups.parse(container, element);
            element.instAttribGroups = InstAttribGroups.parse(container, element);
            element.lotTypeRulesGroups = LotTypeRulesGroups.parse(container, element);
            element.underlyingsGroups = UnderlyingsGroups.parse(container, element);
            element.relatedInstrumentsGroups = RelatedInstrumentsGroups.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.matchEventIndicator).append("\n");
            sb.append(this.totNumReportsOptional).append("\n");
            sb.append(this.securityUpdateAction).append("\n");
            sb.append(this.lastUpdateTime).append("\n");
            sb.append(this.mdSecurityTradingStatus).append("\n");
            sb.append(this.applId).append("\n");
            sb.append(this.marketSegmentId).append("\n");
            sb.append(this.underlyingProduct).append("\n");
            sb.append(this.securityExchange).append("\n");
            sb.append(this.securityGroup).append("\n");
            sb.append(this.asset).append("\n");
            sb.append(this.symbol).append("\n");
            sb.append(this.securityId).append("\n");
            sb.append(this.securityType).append("\n");
            sb.append(this.cfiCode).append("\n");
            sb.append(this.putOrCall).append("\n");
            sb.append(this.maturityMonthYear).append("\n");
            sb.append(this.currency).append("\n");
            sb.append(this.strikePricePricenulL9).append("\n");
            sb.append(this.strikeCurrency).append("\n");
            sb.append(this.settlCurrency).append("\n");
            sb.append(this.minCabPricePricenulL9).append("\n");
            sb.append(this.matchAlgorithm).append("\n");
            sb.append(this.minTradeVol).append("\n");
            sb.append(this.maxTradeVol).append("\n");
            sb.append(this.minPriceIncrementOptional).append("\n");
            sb.append(this.minPriceIncrementAmountPricenulL9).append("\n");
            sb.append(this.displayFactorDecimal9).append("\n");
            sb.append(this.tickRule).append("\n");
            sb.append(this.mainFraction).append("\n");
            sb.append(this.subFraction).append("\n");
            sb.append(this.priceDisplayFormat).append("\n");
            sb.append(this.unitOfMeasure).append("\n");
            sb.append(this.unitOfMeasureQtyDecimal9Null).append("\n");
            sb.append(this.tradingReferencePricePricenulL9).append("\n");
            sb.append(this.settlPriceType).append("\n");
            sb.append(this.clearedVolume).append("\n");
            sb.append(this.openInterestQty).append("\n");
            sb.append(this.lowLimitPricePricenulL9).append("\n");
            sb.append(this.highLimitPricePricenulL9).append("\n");
            sb.append(this.userDefinedInstrument).append("\n");
            sb.append(this.tradingReferenceDate).append("\n");
            sb.append(this.eventsGroups).append("\n");
            sb.append(this.mDFeedTypesGroups).append("\n");
            sb.append(this.instAttribGroups).append("\n");
            sb.append(this.lotTypeRulesGroups).append("\n");
            sb.append(this.underlyingsGroups).append("\n");
            sb.append(this.relatedInstrumentsGroups).append("\n");
            return sb.toString();
        }
    }

    // Flow Schedule Type: 1 Byte Signed Fixed Width Integer Nullable
    public static class FlowScheduleType implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public FlowScheduleType(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static FlowScheduleType parse(Container container, IBinaryProtocolElement element) {
            return new FlowScheduleType(LittleEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "flowScheduleType = "+ this.value;
        }
    }

    // Contract Multiplier Unit: 1 Byte Signed Fixed Width Integer Nullable
    public static class ContractMultiplierUnit implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public ContractMultiplierUnit(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static ContractMultiplierUnit parse(Container container, IBinaryProtocolElement element) {
            return new ContractMultiplierUnit(LittleEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "contractMultiplierUnit = "+ this.value;
        }
    }

    // Contract Multiplier: 4 Byte Signed Fixed Width Integer Nullable
    public static class ContractMultiplier implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public ContractMultiplier(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static ContractMultiplier parse(Container container, IBinaryProtocolElement element) {
            return new ContractMultiplier(LittleEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "contractMultiplier = "+ this.value;
        }
    }

    // Original Contract Size: 4 Byte Signed Fixed Width Integer Nullable
    public static class OriginalContractSize implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public OriginalContractSize(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static OriginalContractSize parse(Container container, IBinaryProtocolElement element) {
            return new OriginalContractSize(LittleEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "originalContractSize = "+ this.value;
        }
    }

    // Decay Start Date: 2 Byte Unsigned Fixed Width Integer Nullable
    public static class DecayStartDate implements IBinaryProtocolElement {
        public static final int LENGTH = 2;
        public final IBinaryProtocolElement parent;
        public final Short value;

        public DecayStartDate(Short value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static DecayStartDate parse(Container container, IBinaryProtocolElement element) {
            return new DecayStartDate(LittleEndianUtils.toShort(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "decayStartDate = "+ this.value;
        }
    }

    // Decay Quantity: 4 Byte Signed Fixed Width Integer Nullable
    public static class DecayQuantity implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public DecayQuantity(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static DecayQuantity parse(Container container, IBinaryProtocolElement element) {
            return new DecayQuantity(LittleEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "decayQuantity = "+ this.value;
        }
    }

    // Min Price Increment: 8 Byte Signed Fixed Width Integer
    public static class MinPriceIncrement implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public MinPriceIncrement(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value/1000000000;
        }

        public static MinPriceIncrement parse(Container container, IBinaryProtocolElement element) {
            return new MinPriceIncrement(LittleEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "minPriceIncrement = "+ this.value;
        }
    }

    /**
    * Md Instrument Definition Future 54
    */
    public static class MdInstrumentDefinitionFuture54 implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public MatchEventIndicator matchEventIndicator;
        public TotNumReportsOptional totNumReportsOptional;
        public SecurityUpdateAction securityUpdateAction;
        public LastUpdateTime lastUpdateTime;
        public MdSecurityTradingStatus mdSecurityTradingStatus;
        public ApplId applId;
        public MarketSegmentId marketSegmentId;
        public UnderlyingProduct underlyingProduct;
        public SecurityExchange securityExchange;
        public SecurityGroup securityGroup;
        public Asset asset;
        public Symbol symbol;
        public SecurityId securityId;
        public SecurityType securityType;
        public CfiCode cfiCode;
        public MaturityMonthYear maturityMonthYear;
        public Currency currency;
        public SettlCurrency settlCurrency;
        public MatchAlgorithm matchAlgorithm;
        public MinTradeVol minTradeVol;
        public MaxTradeVol maxTradeVol;
        public MinPriceIncrement minPriceIncrement;
        public DisplayFactorDecimal9 displayFactorDecimal9;
        public MainFraction mainFraction;
        public SubFraction subFraction;
        public PriceDisplayFormat priceDisplayFormat;
        public UnitOfMeasure unitOfMeasure;
        public UnitOfMeasureQtyDecimal9Null unitOfMeasureQtyDecimal9Null;
        public TradingReferencePricePricenulL9 tradingReferencePricePricenulL9;
        public SettlPriceType settlPriceType;
        public OpenInterestQty openInterestQty;
        public ClearedVolume clearedVolume;
        public HighLimitPricePricenulL9 highLimitPricePricenulL9;
        public LowLimitPricePricenulL9 lowLimitPricePricenulL9;
        public MaxPriceVariationPricenulL9 maxPriceVariationPricenulL9;
        public DecayQuantity decayQuantity;
        public DecayStartDate decayStartDate;
        public OriginalContractSize originalContractSize;
        public ContractMultiplier contractMultiplier;
        public ContractMultiplierUnit contractMultiplierUnit;
        public FlowScheduleType flowScheduleType;
        public MinPriceIncrementAmountPricenulL9 minPriceIncrementAmountPricenulL9;
        public UserDefinedInstrument userDefinedInstrument;
        public TradingReferenceDate tradingReferenceDate;
        public EventsGroups eventsGroups;
        public MDFeedTypesGroups mDFeedTypesGroups;
        public InstAttribGroups instAttribGroups;
        public LotTypeRulesGroups lotTypeRulesGroups;

        // constructor for Md Instrument Definition Future 54
        private MdInstrumentDefinitionFuture54(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Md Instrument Definition Future 54
        public static MdInstrumentDefinitionFuture54 parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new MdInstrumentDefinitionFuture54(parentElement);

            element.matchEventIndicator = MatchEventIndicator.parse(container, element);
            element.totNumReportsOptional = TotNumReportsOptional.parse(container, element);
            element.securityUpdateAction = SecurityUpdateAction.parse(container, element);
            element.lastUpdateTime = LastUpdateTime.parse(container, element);
            element.mdSecurityTradingStatus = MdSecurityTradingStatus.parse(container, element);
            element.applId = ApplId.parse(container, element);
            element.marketSegmentId = MarketSegmentId.parse(container, element);
            element.underlyingProduct = UnderlyingProduct.parse(container, element);
            element.securityExchange = SecurityExchange.parse(container, element);
            element.securityGroup = SecurityGroup.parse(container, element);
            element.asset = Asset.parse(container, element);
            element.symbol = Symbol.parse(container, element);
            element.securityId = SecurityId.parse(container, element);
            element.securityType = SecurityType.parse(container, element);
            element.cfiCode = CfiCode.parse(container, element);
            element.maturityMonthYear = MaturityMonthYear.parse(container, element);
            element.currency = Currency.parse(container, element);
            element.settlCurrency = SettlCurrency.parse(container, element);
            element.matchAlgorithm = MatchAlgorithm.parse(container, element);
            element.minTradeVol = MinTradeVol.parse(container, element);
            element.maxTradeVol = MaxTradeVol.parse(container, element);
            element.minPriceIncrement = MinPriceIncrement.parse(container, element);
            element.displayFactorDecimal9 = DisplayFactorDecimal9.parse(container, element);
            element.mainFraction = MainFraction.parse(container, element);
            element.subFraction = SubFraction.parse(container, element);
            element.priceDisplayFormat = PriceDisplayFormat.parse(container, element);
            element.unitOfMeasure = UnitOfMeasure.parse(container, element);
            element.unitOfMeasureQtyDecimal9Null = UnitOfMeasureQtyDecimal9Null.parse(container, element);
            element.tradingReferencePricePricenulL9 = TradingReferencePricePricenulL9.parse(container, element);
            element.settlPriceType = SettlPriceType.parse(container, element);
            element.openInterestQty = OpenInterestQty.parse(container, element);
            element.clearedVolume = ClearedVolume.parse(container, element);
            element.highLimitPricePricenulL9 = HighLimitPricePricenulL9.parse(container, element);
            element.lowLimitPricePricenulL9 = LowLimitPricePricenulL9.parse(container, element);
            element.maxPriceVariationPricenulL9 = MaxPriceVariationPricenulL9.parse(container, element);
            element.decayQuantity = DecayQuantity.parse(container, element);
            element.decayStartDate = DecayStartDate.parse(container, element);
            element.originalContractSize = OriginalContractSize.parse(container, element);
            element.contractMultiplier = ContractMultiplier.parse(container, element);
            element.contractMultiplierUnit = ContractMultiplierUnit.parse(container, element);
            element.flowScheduleType = FlowScheduleType.parse(container, element);
            element.minPriceIncrementAmountPricenulL9 = MinPriceIncrementAmountPricenulL9.parse(container, element);
            element.userDefinedInstrument = UserDefinedInstrument.parse(container, element);
            element.tradingReferenceDate = TradingReferenceDate.parse(container, element);
            element.eventsGroups = EventsGroups.parse(container, element);
            element.mDFeedTypesGroups = MDFeedTypesGroups.parse(container, element);
            element.instAttribGroups = InstAttribGroups.parse(container, element);
            element.lotTypeRulesGroups = LotTypeRulesGroups.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.matchEventIndicator).append("\n");
            sb.append(this.totNumReportsOptional).append("\n");
            sb.append(this.securityUpdateAction).append("\n");
            sb.append(this.lastUpdateTime).append("\n");
            sb.append(this.mdSecurityTradingStatus).append("\n");
            sb.append(this.applId).append("\n");
            sb.append(this.marketSegmentId).append("\n");
            sb.append(this.underlyingProduct).append("\n");
            sb.append(this.securityExchange).append("\n");
            sb.append(this.securityGroup).append("\n");
            sb.append(this.asset).append("\n");
            sb.append(this.symbol).append("\n");
            sb.append(this.securityId).append("\n");
            sb.append(this.securityType).append("\n");
            sb.append(this.cfiCode).append("\n");
            sb.append(this.maturityMonthYear).append("\n");
            sb.append(this.currency).append("\n");
            sb.append(this.settlCurrency).append("\n");
            sb.append(this.matchAlgorithm).append("\n");
            sb.append(this.minTradeVol).append("\n");
            sb.append(this.maxTradeVol).append("\n");
            sb.append(this.minPriceIncrement).append("\n");
            sb.append(this.displayFactorDecimal9).append("\n");
            sb.append(this.mainFraction).append("\n");
            sb.append(this.subFraction).append("\n");
            sb.append(this.priceDisplayFormat).append("\n");
            sb.append(this.unitOfMeasure).append("\n");
            sb.append(this.unitOfMeasureQtyDecimal9Null).append("\n");
            sb.append(this.tradingReferencePricePricenulL9).append("\n");
            sb.append(this.settlPriceType).append("\n");
            sb.append(this.openInterestQty).append("\n");
            sb.append(this.clearedVolume).append("\n");
            sb.append(this.highLimitPricePricenulL9).append("\n");
            sb.append(this.lowLimitPricePricenulL9).append("\n");
            sb.append(this.maxPriceVariationPricenulL9).append("\n");
            sb.append(this.decayQuantity).append("\n");
            sb.append(this.decayStartDate).append("\n");
            sb.append(this.originalContractSize).append("\n");
            sb.append(this.contractMultiplier).append("\n");
            sb.append(this.contractMultiplierUnit).append("\n");
            sb.append(this.flowScheduleType).append("\n");
            sb.append(this.minPriceIncrementAmountPricenulL9).append("\n");
            sb.append(this.userDefinedInstrument).append("\n");
            sb.append(this.tradingReferenceDate).append("\n");
            sb.append(this.eventsGroups).append("\n");
            sb.append(this.mDFeedTypesGroups).append("\n");
            sb.append(this.instAttribGroups).append("\n");
            sb.append(this.lotTypeRulesGroups).append("\n");
            return sb.toString();
        }
    }

    // Md Entry Type Book: 1 Byte Ascii String Enum with 5 values
    public static class MdEntryTypeBook implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_MD_ENTRY_TYPE_BOOK value;

        public MdEntryTypeBook(ENUM_MD_ENTRY_TYPE_BOOK value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static MdEntryTypeBook parse(Container container, IBinaryProtocolElement element) {
            return new MdEntryTypeBook(ENUM_MD_ENTRY_TYPE_BOOK.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "mdEntryTypeBook = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Md Display Qty: 4 Byte Signed Fixed Width Integer
    public static class MdDisplayQty implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public MdDisplayQty(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static MdDisplayQty parse(Container container, IBinaryProtocolElement element) {
            return new MdDisplayQty(LittleEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "mdDisplayQty = "+ this.value;
        }
    }

    // Md Entry Px: 8 Byte Signed Fixed Width Integer
    public static class MdEntryPx implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public MdEntryPx(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value/10000000;
        }

        public static MdEntryPx parse(Container container, IBinaryProtocolElement element) {
            return new MdEntryPx(LittleEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "mdEntryPx = "+ this.value;
        }
    }

    // Md Order Priority: 8 Byte Unsigned Fixed Width Integer Nullable
    public static class MdOrderPriority implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public MdOrderPriority(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static MdOrderPriority parse(Container container, IBinaryProtocolElement element) {
            return new MdOrderPriority(LittleEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "mdOrderPriority = "+ this.value;
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
            return new OrderId(LittleEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "orderId = "+ this.value;
        }
    }

    /**
    * Snapshot Full Refresh Order Book Group
    */
    public static class SnapshotFullRefreshOrderBookGroup implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public OrderId orderId;
        public MdOrderPriority mdOrderPriority;
        public MdEntryPx mdEntryPx;
        public MdDisplayQty mdDisplayQty;
        public MdEntryTypeBook mdEntryTypeBook;

        // constructor for Snapshot Full Refresh Order Book Group
        private SnapshotFullRefreshOrderBookGroup(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Snapshot Full Refresh Order Book Group
        public static SnapshotFullRefreshOrderBookGroup parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new SnapshotFullRefreshOrderBookGroup(parentElement);

            element.orderId = OrderId.parse(container, element);
            element.mdOrderPriority = MdOrderPriority.parse(container, element);
            element.mdEntryPx = MdEntryPx.parse(container, element);
            element.mdDisplayQty = MdDisplayQty.parse(container, element);
            element.mdEntryTypeBook = MdEntryTypeBook.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.orderId).append("\n");
            sb.append(this.mdOrderPriority).append("\n");
            sb.append(this.mdEntryPx).append("\n");
            sb.append(this.mdDisplayQty).append("\n");
            sb.append(this.mdEntryTypeBook).append("\n");
            return sb.toString();
        }
    }

    /**
    * Snapshot Full Refresh Order Book Groups
    */
    public static class SnapshotFullRefreshOrderBookGroups implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public GroupSize groupSize;
        public List<SnapshotFullRefreshOrderBookGroup> snapshotFullRefreshOrderBookGroupList = new ArrayList<>();

        // constructor for Snapshot Full Refresh Order Book Groups
        private SnapshotFullRefreshOrderBookGroups(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Snapshot Full Refresh Order Book Groups
        public static SnapshotFullRefreshOrderBookGroups parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new SnapshotFullRefreshOrderBookGroups(parentElement);

            element.groupSize = GroupSize.parse(container, element);

            // Snapshot Full Refresh Order Book Group: Struct of 5 fields
            for (int i = 0; i < element.groupSize.numInGroup.value; i++) {
                element.snapshotFullRefreshOrderBookGroupList.add(SnapshotFullRefreshOrderBookGroup.parse(container, element));
            }

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.groupSize).append("\n");
            for (var snapshotFullRefreshOrderBookGroup: snapshotFullRefreshOrderBookGroupList) {
                sb.append(snapshotFullRefreshOrderBookGroup).append("\n");
            }
            return sb.toString();
        }
    }

    // Transact Time: 8 Byte Unsigned Fixed Width Integer
    public static class TransactTime implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public TransactTime(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static TransactTime parse(Container container, IBinaryProtocolElement element) {
            return new TransactTime(LittleEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "transactTime = "+ this.value;
        }
    }

    // Current Chunk: 4 Byte Unsigned Fixed Width Integer
    public static class CurrentChunk implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public CurrentChunk(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static CurrentChunk parse(Container container, IBinaryProtocolElement element) {
            return new CurrentChunk(LittleEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "currentChunk = "+ this.value;
        }
    }

    // No Chunks: 4 Byte Unsigned Fixed Width Integer
    public static class NoChunks implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public NoChunks(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static NoChunks parse(Container container, IBinaryProtocolElement element) {
            return new NoChunks(LittleEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "noChunks = "+ this.value;
        }
    }

    // Tot Num Reports: 4 Byte Unsigned Fixed Width Integer
    public static class TotNumReports implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public TotNumReports(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static TotNumReports parse(Container container, IBinaryProtocolElement element) {
            return new TotNumReports(LittleEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "totNumReports = "+ this.value;
        }
    }

    // Last Msg Seq Num Processed: 4 Byte Unsigned Fixed Width Integer
    public static class LastMsgSeqNumProcessed implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public LastMsgSeqNumProcessed(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static LastMsgSeqNumProcessed parse(Container container, IBinaryProtocolElement element) {
            return new LastMsgSeqNumProcessed(LittleEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "lastMsgSeqNumProcessed = "+ this.value;
        }
    }

    /**
    * Snapshot Full Refresh Order Book 53
    */
    public static class SnapshotFullRefreshOrderBook53 implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public LastMsgSeqNumProcessed lastMsgSeqNumProcessed;
        public TotNumReports totNumReports;
        public SecurityId securityId;
        public NoChunks noChunks;
        public CurrentChunk currentChunk;
        public TransactTime transactTime;
        public SnapshotFullRefreshOrderBookGroups snapshotFullRefreshOrderBookGroups;

        // constructor for Snapshot Full Refresh Order Book 53
        private SnapshotFullRefreshOrderBook53(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Snapshot Full Refresh Order Book 53
        public static SnapshotFullRefreshOrderBook53 parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new SnapshotFullRefreshOrderBook53(parentElement);

            element.lastMsgSeqNumProcessed = LastMsgSeqNumProcessed.parse(container, element);
            element.totNumReports = TotNumReports.parse(container, element);
            element.securityId = SecurityId.parse(container, element);
            element.noChunks = NoChunks.parse(container, element);
            element.currentChunk = CurrentChunk.parse(container, element);
            element.transactTime = TransactTime.parse(container, element);
            element.snapshotFullRefreshOrderBookGroups = SnapshotFullRefreshOrderBookGroups.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.lastMsgSeqNumProcessed).append("\n");
            sb.append(this.totNumReports).append("\n");
            sb.append(this.securityId).append("\n");
            sb.append(this.noChunks).append("\n");
            sb.append(this.currentChunk).append("\n");
            sb.append(this.transactTime).append("\n");
            sb.append(this.snapshotFullRefreshOrderBookGroups).append("\n");
            return sb.toString();
        }
    }

    // Md Entry Type : 1 Byte Ascii String Enum with 17 values
    public static class MdEntryType implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_MD_ENTRY_TYPE value;

        public MdEntryType(ENUM_MD_ENTRY_TYPE value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static MdEntryType parse(Container container, IBinaryProtocolElement element) {
            return new MdEntryType(ENUM_MD_ENTRY_TYPE.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "mdEntryType = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Open Close Settl Flag: 1 Byte Unsigned Fixed Width Integer Enum with 3 values
    public static class OpenCloseSettlFlag implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_OPEN_CLOSE_SETTL_FLAG value;

        public OpenCloseSettlFlag(ENUM_OPEN_CLOSE_SETTL_FLAG value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static OpenCloseSettlFlag parse(Container container, IBinaryProtocolElement element) {
            return new OpenCloseSettlFlag(ENUM_OPEN_CLOSE_SETTL_FLAG.valueOf((int)LittleEndianUtils.toByte(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "openCloseSettlFlag = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Md Price Level Optional: 1 Byte Signed Fixed Width Integer Nullable
    public static class MdPriceLevelOptional implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public MdPriceLevelOptional(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static MdPriceLevelOptional parse(Container container, IBinaryProtocolElement element) {
            return new MdPriceLevelOptional(LittleEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "mdPriceLevelOptional = "+ this.value;
        }
    }

    // Number Of Orders Optional: 4 Byte Signed Fixed Width Integer Nullable
    public static class NumberOfOrdersOptional implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public NumberOfOrdersOptional(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static NumberOfOrdersOptional parse(Container container, IBinaryProtocolElement element) {
            return new NumberOfOrdersOptional(LittleEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "numberOfOrdersOptional = "+ this.value;
        }
    }

    // Md Entry Size Optional: 4 Byte Signed Fixed Width Integer Nullable
    public static class MdEntrySizeOptional implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public MdEntrySizeOptional(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static MdEntrySizeOptional parse(Container container, IBinaryProtocolElement element) {
            return new MdEntrySizeOptional(LittleEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "mdEntrySizeOptional = "+ this.value;
        }
    }

    // Md Entry Px Optional: 8 Byte Signed Fixed Width Integer Nullable
    public static class MdEntryPxOptional implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public MdEntryPxOptional(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value/10000000;
        }

        public static MdEntryPxOptional parse(Container container, IBinaryProtocolElement element) {
            return new MdEntryPxOptional(LittleEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "mdEntryPxOptional = "+ this.value;
        }
    }

    /**
    * Snapshot Full Refresh Group
    */
    public static class SnapshotFullRefreshGroup implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public MdEntryPxOptional mdEntryPxOptional;
        public MdEntrySizeOptional mdEntrySizeOptional;
        public NumberOfOrdersOptional numberOfOrdersOptional;
        public MdPriceLevelOptional mdPriceLevelOptional;
        public TradingReferenceDate tradingReferenceDate;
        public OpenCloseSettlFlag openCloseSettlFlag;
        public SettlPriceType settlPriceType;
        public MdEntryType mdEntryType;

        // constructor for Snapshot Full Refresh Group
        private SnapshotFullRefreshGroup(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Snapshot Full Refresh Group
        public static SnapshotFullRefreshGroup parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new SnapshotFullRefreshGroup(parentElement);

            element.mdEntryPxOptional = MdEntryPxOptional.parse(container, element);
            element.mdEntrySizeOptional = MdEntrySizeOptional.parse(container, element);
            element.numberOfOrdersOptional = NumberOfOrdersOptional.parse(container, element);
            element.mdPriceLevelOptional = MdPriceLevelOptional.parse(container, element);
            element.tradingReferenceDate = TradingReferenceDate.parse(container, element);
            element.openCloseSettlFlag = OpenCloseSettlFlag.parse(container, element);
            element.settlPriceType = SettlPriceType.parse(container, element);
            element.mdEntryType = MdEntryType.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.mdEntryPxOptional).append("\n");
            sb.append(this.mdEntrySizeOptional).append("\n");
            sb.append(this.numberOfOrdersOptional).append("\n");
            sb.append(this.mdPriceLevelOptional).append("\n");
            sb.append(this.tradingReferenceDate).append("\n");
            sb.append(this.openCloseSettlFlag).append("\n");
            sb.append(this.settlPriceType).append("\n");
            sb.append(this.mdEntryType).append("\n");
            return sb.toString();
        }
    }

    /**
    * Snapshot Full Refresh Groups
    */
    public static class SnapshotFullRefreshGroups implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public GroupSize groupSize;
        public List<SnapshotFullRefreshGroup> snapshotFullRefreshGroupList = new ArrayList<>();

        // constructor for Snapshot Full Refresh Groups
        private SnapshotFullRefreshGroups(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Snapshot Full Refresh Groups
        public static SnapshotFullRefreshGroups parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new SnapshotFullRefreshGroups(parentElement);

            element.groupSize = GroupSize.parse(container, element);

            // Snapshot Full Refresh Group: Struct of 8 fields
            for (int i = 0; i < element.groupSize.numInGroup.value; i++) {
                element.snapshotFullRefreshGroupList.add(SnapshotFullRefreshGroup.parse(container, element));
            }

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.groupSize).append("\n");
            for (var snapshotFullRefreshGroup: snapshotFullRefreshGroupList) {
                sb.append(snapshotFullRefreshGroup).append("\n");
            }
            return sb.toString();
        }
    }

    // Trade Date: 2 Byte Unsigned Fixed Width Integer Nullable
    public static class TradeDate implements IBinaryProtocolElement {
        public static final int LENGTH = 2;
        public final IBinaryProtocolElement parent;
        public final Short value;

        public TradeDate(Short value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static TradeDate parse(Container container, IBinaryProtocolElement element) {
            return new TradeDate(LittleEndianUtils.toShort(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "tradeDate = "+ this.value;
        }
    }

    // Rpt Seq: 4 Byte Unsigned Fixed Width Integer
    public static class RptSeq implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public RptSeq(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static RptSeq parse(Container container, IBinaryProtocolElement element) {
            return new RptSeq(LittleEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "rptSeq = "+ this.value;
        }
    }

    /**
    * Snapshot Full Refresh 52
    */
    public static class SnapshotFullRefresh52 implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public LastMsgSeqNumProcessed lastMsgSeqNumProcessed;
        public TotNumReports totNumReports;
        public SecurityId securityId;
        public RptSeq rptSeq;
        public TransactTime transactTime;
        public LastUpdateTime lastUpdateTime;
        public TradeDate tradeDate;
        public MdSecurityTradingStatus mdSecurityTradingStatus;
        public HighLimitPricePricenulL9 highLimitPricePricenulL9;
        public LowLimitPricePricenulL9 lowLimitPricePricenulL9;
        public MaxPriceVariationPricenulL9 maxPriceVariationPricenulL9;
        public SnapshotFullRefreshGroups snapshotFullRefreshGroups;

        // constructor for Snapshot Full Refresh 52
        private SnapshotFullRefresh52(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Snapshot Full Refresh 52
        public static SnapshotFullRefresh52 parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new SnapshotFullRefresh52(parentElement);

            element.lastMsgSeqNumProcessed = LastMsgSeqNumProcessed.parse(container, element);
            element.totNumReports = TotNumReports.parse(container, element);
            element.securityId = SecurityId.parse(container, element);
            element.rptSeq = RptSeq.parse(container, element);
            element.transactTime = TransactTime.parse(container, element);
            element.lastUpdateTime = LastUpdateTime.parse(container, element);
            element.tradeDate = TradeDate.parse(container, element);
            element.mdSecurityTradingStatus = MdSecurityTradingStatus.parse(container, element);
            element.highLimitPricePricenulL9 = HighLimitPricePricenulL9.parse(container, element);
            element.lowLimitPricePricenulL9 = LowLimitPricePricenulL9.parse(container, element);
            element.maxPriceVariationPricenulL9 = MaxPriceVariationPricenulL9.parse(container, element);
            element.snapshotFullRefreshGroups = SnapshotFullRefreshGroups.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.lastMsgSeqNumProcessed).append("\n");
            sb.append(this.totNumReports).append("\n");
            sb.append(this.securityId).append("\n");
            sb.append(this.rptSeq).append("\n");
            sb.append(this.transactTime).append("\n");
            sb.append(this.lastUpdateTime).append("\n");
            sb.append(this.tradeDate).append("\n");
            sb.append(this.mdSecurityTradingStatus).append("\n");
            sb.append(this.highLimitPricePricenulL9).append("\n");
            sb.append(this.lowLimitPricePricenulL9).append("\n");
            sb.append(this.maxPriceVariationPricenulL9).append("\n");
            sb.append(this.snapshotFullRefreshGroups).append("\n");
            return sb.toString();
        }
    }

    // Padding 1: 1 Byte
    public static class Padding1 implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public Padding1(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Padding1 parse(Container container, IBinaryProtocolElement element) {
            return new Padding1(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "padding1 = "+ this.value;
        }
    }

    // Md Entry Type Statistics: 1 Byte Ascii String Enum with 5 values
    public static class MdEntryTypeStatistics implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_MD_ENTRY_TYPE_STATISTICS value;

        public MdEntryTypeStatistics(ENUM_MD_ENTRY_TYPE_STATISTICS value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static MdEntryTypeStatistics parse(Container container, IBinaryProtocolElement element) {
            return new MdEntryTypeStatistics(ENUM_MD_ENTRY_TYPE_STATISTICS.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "mdEntryTypeStatistics = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Md Update Action: 1 Byte Unsigned Fixed Width Integer Enum with 6 values
    public static class MdUpdateAction implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_MD_UPDATE_ACTION value;

        public MdUpdateAction(ENUM_MD_UPDATE_ACTION value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static MdUpdateAction parse(Container container, IBinaryProtocolElement element) {
            return new MdUpdateAction(ENUM_MD_UPDATE_ACTION.valueOf((int)LittleEndianUtils.toByte(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "mdUpdateAction = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    /**
    * M D Incremental Refresh Session Statistics Group
    */
    public static class MDIncrementalRefreshSessionStatisticsGroup implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public MdEntryPx mdEntryPx;
        public SecurityId securityId;
        public RptSeq rptSeq;
        public OpenCloseSettlFlag openCloseSettlFlag;
        public MdUpdateAction mdUpdateAction;
        public MdEntryTypeStatistics mdEntryTypeStatistics;
        public MdEntrySizeOptional mdEntrySizeOptional;
        public Padding1 padding1;

        // constructor for M D Incremental Refresh Session Statistics Group
        private MDIncrementalRefreshSessionStatisticsGroup(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for M D Incremental Refresh Session Statistics Group
        public static MDIncrementalRefreshSessionStatisticsGroup parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new MDIncrementalRefreshSessionStatisticsGroup(parentElement);

            element.mdEntryPx = MdEntryPx.parse(container, element);
            element.securityId = SecurityId.parse(container, element);
            element.rptSeq = RptSeq.parse(container, element);
            element.openCloseSettlFlag = OpenCloseSettlFlag.parse(container, element);
            element.mdUpdateAction = MdUpdateAction.parse(container, element);
            element.mdEntryTypeStatistics = MdEntryTypeStatistics.parse(container, element);
            element.mdEntrySizeOptional = MdEntrySizeOptional.parse(container, element);
            element.padding1 = Padding1.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.mdEntryPx).append("\n");
            sb.append(this.securityId).append("\n");
            sb.append(this.rptSeq).append("\n");
            sb.append(this.openCloseSettlFlag).append("\n");
            sb.append(this.mdUpdateAction).append("\n");
            sb.append(this.mdEntryTypeStatistics).append("\n");
            sb.append(this.mdEntrySizeOptional).append("\n");
            sb.append(this.padding1).append("\n");
            return sb.toString();
        }
    }

    /**
    * M D Incremental Refresh Session Statistics Groups
    */
    public static class MDIncrementalRefreshSessionStatisticsGroups implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public GroupSize groupSize;
        public List<MDIncrementalRefreshSessionStatisticsGroup> mDIncrementalRefreshSessionStatisticsGroupList = new ArrayList<>();

        // constructor for M D Incremental Refresh Session Statistics Groups
        private MDIncrementalRefreshSessionStatisticsGroups(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for M D Incremental Refresh Session Statistics Groups
        public static MDIncrementalRefreshSessionStatisticsGroups parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new MDIncrementalRefreshSessionStatisticsGroups(parentElement);

            element.groupSize = GroupSize.parse(container, element);

            // M D Incremental Refresh Session Statistics Group: Struct of 8 fields
            for (int i = 0; i < element.groupSize.numInGroup.value; i++) {
                element.mDIncrementalRefreshSessionStatisticsGroupList.add(MDIncrementalRefreshSessionStatisticsGroup.parse(container, element));
            }

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.groupSize).append("\n");
            for (var mDIncrementalRefreshSessionStatisticsGroup: mDIncrementalRefreshSessionStatisticsGroupList) {
                sb.append(mDIncrementalRefreshSessionStatisticsGroup).append("\n");
            }
            return sb.toString();
        }
    }

    // Padding 2: 2 Byte
    public static class Padding2 implements IBinaryProtocolElement {
        public static final int LENGTH = 2;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public Padding2(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Padding2 parse(Container container, IBinaryProtocolElement element) {
            return new Padding2(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "padding2 = "+ this.value;
        }
    }

    /**
    * Md Incremental Refresh Session Statistics 51
    */
    public static class MdIncrementalRefreshSessionStatistics51 implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public TransactTime transactTime;
        public MatchEventIndicator matchEventIndicator;
        public Padding2 padding2;
        public MDIncrementalRefreshSessionStatisticsGroups mDIncrementalRefreshSessionStatisticsGroups;

        // constructor for Md Incremental Refresh Session Statistics 51
        private MdIncrementalRefreshSessionStatistics51(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Md Incremental Refresh Session Statistics 51
        public static MdIncrementalRefreshSessionStatistics51 parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new MdIncrementalRefreshSessionStatistics51(parentElement);

            element.transactTime = TransactTime.parse(container, element);
            element.matchEventIndicator = MatchEventIndicator.parse(container, element);
            element.padding2 = Padding2.parse(container, element);
            element.mDIncrementalRefreshSessionStatisticsGroups = MDIncrementalRefreshSessionStatisticsGroups.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.transactTime).append("\n");
            sb.append(this.matchEventIndicator).append("\n");
            sb.append(this.padding2).append("\n");
            sb.append(this.mDIncrementalRefreshSessionStatisticsGroups).append("\n");
            return sb.toString();
        }
    }

    // Max Price Variation Pricenull: 8 Byte Signed Fixed Width Integer Nullable
    public static class MaxPriceVariationPricenull implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public MaxPriceVariationPricenull(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value/10000000;
        }

        public static MaxPriceVariationPricenull parse(Container container, IBinaryProtocolElement element) {
            return new MaxPriceVariationPricenull(LittleEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "maxPriceVariationPricenull = "+ this.value;
        }
    }

    // Low Limit Price Pricenull: 8 Byte Signed Fixed Width Integer Nullable
    public static class LowLimitPricePricenull implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public LowLimitPricePricenull(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value/10000000;
        }

        public static LowLimitPricePricenull parse(Container container, IBinaryProtocolElement element) {
            return new LowLimitPricePricenull(LittleEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "lowLimitPricePricenull = "+ this.value;
        }
    }

    // High Limit Price Pricenull: 8 Byte Signed Fixed Width Integer Nullable
    public static class HighLimitPricePricenull implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public HighLimitPricePricenull(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value/10000000;
        }

        public static HighLimitPricePricenull parse(Container container, IBinaryProtocolElement element) {
            return new HighLimitPricePricenull(LittleEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "highLimitPricePricenull = "+ this.value;
        }
    }

    /**
    * M D Incremental Refresh Limits Banding Group
    */
    public static class MDIncrementalRefreshLimitsBandingGroup implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public HighLimitPricePricenull highLimitPricePricenull;
        public LowLimitPricePricenull lowLimitPricePricenull;
        public MaxPriceVariationPricenull maxPriceVariationPricenull;
        public SecurityId securityId;
        public RptSeq rptSeq;

        // constructor for M D Incremental Refresh Limits Banding Group
        private MDIncrementalRefreshLimitsBandingGroup(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for M D Incremental Refresh Limits Banding Group
        public static MDIncrementalRefreshLimitsBandingGroup parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new MDIncrementalRefreshLimitsBandingGroup(parentElement);

            element.highLimitPricePricenull = HighLimitPricePricenull.parse(container, element);
            element.lowLimitPricePricenull = LowLimitPricePricenull.parse(container, element);
            element.maxPriceVariationPricenull = MaxPriceVariationPricenull.parse(container, element);
            element.securityId = SecurityId.parse(container, element);
            element.rptSeq = RptSeq.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.highLimitPricePricenull).append("\n");
            sb.append(this.lowLimitPricePricenull).append("\n");
            sb.append(this.maxPriceVariationPricenull).append("\n");
            sb.append(this.securityId).append("\n");
            sb.append(this.rptSeq).append("\n");
            return sb.toString();
        }
    }

    /**
    * M D Incremental Refresh Limits Banding Groups
    */
    public static class MDIncrementalRefreshLimitsBandingGroups implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public GroupSize groupSize;
        public List<MDIncrementalRefreshLimitsBandingGroup> mDIncrementalRefreshLimitsBandingGroupList = new ArrayList<>();

        // constructor for M D Incremental Refresh Limits Banding Groups
        private MDIncrementalRefreshLimitsBandingGroups(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for M D Incremental Refresh Limits Banding Groups
        public static MDIncrementalRefreshLimitsBandingGroups parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new MDIncrementalRefreshLimitsBandingGroups(parentElement);

            element.groupSize = GroupSize.parse(container, element);

            // M D Incremental Refresh Limits Banding Group: Struct of 5 fields
            for (int i = 0; i < element.groupSize.numInGroup.value; i++) {
                element.mDIncrementalRefreshLimitsBandingGroupList.add(MDIncrementalRefreshLimitsBandingGroup.parse(container, element));
            }

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.groupSize).append("\n");
            for (var mDIncrementalRefreshLimitsBandingGroup: mDIncrementalRefreshLimitsBandingGroupList) {
                sb.append(mDIncrementalRefreshLimitsBandingGroup).append("\n");
            }
            return sb.toString();
        }
    }

    /**
    * Md Incremental Refresh Limits Banding 50
    */
    public static class MdIncrementalRefreshLimitsBanding50 implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public TransactTime transactTime;
        public MatchEventIndicator matchEventIndicator;
        public Padding2 padding2;
        public MDIncrementalRefreshLimitsBandingGroups mDIncrementalRefreshLimitsBandingGroups;

        // constructor for Md Incremental Refresh Limits Banding 50
        private MdIncrementalRefreshLimitsBanding50(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Md Incremental Refresh Limits Banding 50
        public static MdIncrementalRefreshLimitsBanding50 parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new MdIncrementalRefreshLimitsBanding50(parentElement);

            element.transactTime = TransactTime.parse(container, element);
            element.matchEventIndicator = MatchEventIndicator.parse(container, element);
            element.padding2 = Padding2.parse(container, element);
            element.mDIncrementalRefreshLimitsBandingGroups = MDIncrementalRefreshLimitsBandingGroups.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.transactTime).append("\n");
            sb.append(this.matchEventIndicator).append("\n");
            sb.append(this.padding2).append("\n");
            sb.append(this.mDIncrementalRefreshLimitsBandingGroups).append("\n");
            return sb.toString();
        }
    }

    // Padding 7: 7 Byte
    public static class Padding7 implements IBinaryProtocolElement {
        public static final int LENGTH = 7;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public Padding7(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Padding7 parse(Container container, IBinaryProtocolElement element) {
            return new Padding7(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "padding7 = "+ this.value;
        }
    }

    // Md Entry Type Daily Statistics: 1 Byte Ascii String Enum with 4 values
    public static class MdEntryTypeDailyStatistics implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_MD_ENTRY_TYPE_DAILY_STATISTICS value;

        public MdEntryTypeDailyStatistics(ENUM_MD_ENTRY_TYPE_DAILY_STATISTICS value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static MdEntryTypeDailyStatistics parse(Container container, IBinaryProtocolElement element) {
            return new MdEntryTypeDailyStatistics(ENUM_MD_ENTRY_TYPE_DAILY_STATISTICS.valueOf(BigEndianUtils.toCharacter(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "mdEntryTypeDailyStatistics = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    /**
    * M D Incremental Refresh Daily Statistics Group
    */
    public static class MDIncrementalRefreshDailyStatisticsGroup implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public MdEntryPxOptional mdEntryPxOptional;
        public MdEntrySizeOptional mdEntrySizeOptional;
        public SecurityId securityId;
        public RptSeq rptSeq;
        public TradingReferenceDate tradingReferenceDate;
        public SettlPriceType settlPriceType;
        public MdUpdateAction mdUpdateAction;
        public MdEntryTypeDailyStatistics mdEntryTypeDailyStatistics;
        public Padding7 padding7;

        // constructor for M D Incremental Refresh Daily Statistics Group
        private MDIncrementalRefreshDailyStatisticsGroup(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for M D Incremental Refresh Daily Statistics Group
        public static MDIncrementalRefreshDailyStatisticsGroup parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new MDIncrementalRefreshDailyStatisticsGroup(parentElement);

            element.mdEntryPxOptional = MdEntryPxOptional.parse(container, element);
            element.mdEntrySizeOptional = MdEntrySizeOptional.parse(container, element);
            element.securityId = SecurityId.parse(container, element);
            element.rptSeq = RptSeq.parse(container, element);
            element.tradingReferenceDate = TradingReferenceDate.parse(container, element);
            element.settlPriceType = SettlPriceType.parse(container, element);
            element.mdUpdateAction = MdUpdateAction.parse(container, element);
            element.mdEntryTypeDailyStatistics = MdEntryTypeDailyStatistics.parse(container, element);
            element.padding7 = Padding7.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.mdEntryPxOptional).append("\n");
            sb.append(this.mdEntrySizeOptional).append("\n");
            sb.append(this.securityId).append("\n");
            sb.append(this.rptSeq).append("\n");
            sb.append(this.tradingReferenceDate).append("\n");
            sb.append(this.settlPriceType).append("\n");
            sb.append(this.mdUpdateAction).append("\n");
            sb.append(this.mdEntryTypeDailyStatistics).append("\n");
            sb.append(this.padding7).append("\n");
            return sb.toString();
        }
    }

    /**
    * M D Incremental Refresh Daily Statistics Groups
    */
    public static class MDIncrementalRefreshDailyStatisticsGroups implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public GroupSize groupSize;
        public List<MDIncrementalRefreshDailyStatisticsGroup> mDIncrementalRefreshDailyStatisticsGroupList = new ArrayList<>();

        // constructor for M D Incremental Refresh Daily Statistics Groups
        private MDIncrementalRefreshDailyStatisticsGroups(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for M D Incremental Refresh Daily Statistics Groups
        public static MDIncrementalRefreshDailyStatisticsGroups parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new MDIncrementalRefreshDailyStatisticsGroups(parentElement);

            element.groupSize = GroupSize.parse(container, element);

            // M D Incremental Refresh Daily Statistics Group: Struct of 9 fields
            for (int i = 0; i < element.groupSize.numInGroup.value; i++) {
                element.mDIncrementalRefreshDailyStatisticsGroupList.add(MDIncrementalRefreshDailyStatisticsGroup.parse(container, element));
            }

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.groupSize).append("\n");
            for (var mDIncrementalRefreshDailyStatisticsGroup: mDIncrementalRefreshDailyStatisticsGroupList) {
                sb.append(mDIncrementalRefreshDailyStatisticsGroup).append("\n");
            }
            return sb.toString();
        }
    }

    /**
    * Md Incremental Refresh Daily Statistics 49
    */
    public static class MdIncrementalRefreshDailyStatistics49 implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public TransactTime transactTime;
        public MatchEventIndicator matchEventIndicator;
        public Padding2 padding2;
        public MDIncrementalRefreshDailyStatisticsGroups mDIncrementalRefreshDailyStatisticsGroups;

        // constructor for Md Incremental Refresh Daily Statistics 49
        private MdIncrementalRefreshDailyStatistics49(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Md Incremental Refresh Daily Statistics 49
        public static MdIncrementalRefreshDailyStatistics49 parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new MdIncrementalRefreshDailyStatistics49(parentElement);

            element.transactTime = TransactTime.parse(container, element);
            element.matchEventIndicator = MatchEventIndicator.parse(container, element);
            element.padding2 = Padding2.parse(container, element);
            element.mDIncrementalRefreshDailyStatisticsGroups = MDIncrementalRefreshDailyStatisticsGroups.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.transactTime).append("\n");
            sb.append(this.matchEventIndicator).append("\n");
            sb.append(this.padding2).append("\n");
            sb.append(this.mDIncrementalRefreshDailyStatisticsGroups).append("\n");
            return sb.toString();
        }
    }

    // Padding 4: 4 Byte
    public static class Padding4 implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public Padding4(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Padding4 parse(Container container, IBinaryProtocolElement element) {
            return new Padding4(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "padding4 = "+ this.value;
        }
    }

    // Last Qty: 4 Byte Signed Fixed Width Integer
    public static class LastQty implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public LastQty(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static LastQty parse(Container container, IBinaryProtocolElement element) {
            return new LastQty(LittleEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "lastQty = "+ this.value;
        }
    }

    /**
    * M D Incremental Refresh Trade Summary Order Group
    */
    public static class MDIncrementalRefreshTradeSummaryOrderGroup implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public OrderId orderId;
        public LastQty lastQty;
        public Padding4 padding4;

        // constructor for M D Incremental Refresh Trade Summary Order Group
        private MDIncrementalRefreshTradeSummaryOrderGroup(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for M D Incremental Refresh Trade Summary Order Group
        public static MDIncrementalRefreshTradeSummaryOrderGroup parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new MDIncrementalRefreshTradeSummaryOrderGroup(parentElement);

            element.orderId = OrderId.parse(container, element);
            element.lastQty = LastQty.parse(container, element);
            element.padding4 = Padding4.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.orderId).append("\n");
            sb.append(this.lastQty).append("\n");
            sb.append(this.padding4).append("\n");
            return sb.toString();
        }
    }

    // Padding 5: 5 Byte
    public static class Padding5 implements IBinaryProtocolElement {
        public static final int LENGTH = 5;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public Padding5(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Padding5 parse(Container container, IBinaryProtocolElement element) {
            return new Padding5(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "padding5 = "+ this.value;
        }
    }

    /**
    * Group Size 8 Byte
    */
    public static class GroupSize8Byte implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public BlockLength blockLength;
        public Padding5 padding5;
        public NumInGroup numInGroup;

        // constructor for Group Size 8 Byte
        private GroupSize8Byte(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Group Size 8 Byte
        public static GroupSize8Byte parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new GroupSize8Byte(parentElement);

            element.blockLength = BlockLength.parse(container, element);
            element.padding5 = Padding5.parse(container, element);
            element.numInGroup = NumInGroup.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.blockLength).append("\n");
            sb.append(this.padding5).append("\n");
            sb.append(this.numInGroup).append("\n");
            return sb.toString();
        }
    }

    /**
    * M D Incremental Refresh Trade Summary Order Groups
    */
    public static class MDIncrementalRefreshTradeSummaryOrderGroups implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public GroupSize8Byte groupSize8Byte;
        public List<MDIncrementalRefreshTradeSummaryOrderGroup> mDIncrementalRefreshTradeSummaryOrderGroupList = new ArrayList<>();

        // constructor for M D Incremental Refresh Trade Summary Order Groups
        private MDIncrementalRefreshTradeSummaryOrderGroups(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for M D Incremental Refresh Trade Summary Order Groups
        public static MDIncrementalRefreshTradeSummaryOrderGroups parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new MDIncrementalRefreshTradeSummaryOrderGroups(parentElement);

            element.groupSize8Byte = GroupSize8Byte.parse(container, element);

            // M D Incremental Refresh Trade Summary Order Group: Struct of 3 fields
            for (int i = 0; i < element.groupSize8Byte.numInGroup.value; i++) {
                element.mDIncrementalRefreshTradeSummaryOrderGroupList.add(MDIncrementalRefreshTradeSummaryOrderGroup.parse(container, element));
            }

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.groupSize8Byte).append("\n");
            for (var mDIncrementalRefreshTradeSummaryOrderGroup: mDIncrementalRefreshTradeSummaryOrderGroupList) {
                sb.append(mDIncrementalRefreshTradeSummaryOrderGroup).append("\n");
            }
            return sb.toString();
        }
    }

    // Md Trade Entry Id: 4 Byte Unsigned Fixed Width Integer Nullable
    public static class MdTradeEntryId implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public MdTradeEntryId(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static MdTradeEntryId parse(Container container, IBinaryProtocolElement element) {
            return new MdTradeEntryId(LittleEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "mdTradeEntryId = "+ this.value;
        }
    }

    // Aggressor Side: 1 Byte Unsigned Fixed Width Integer Enum with 4 values
    public static class AggressorSide implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_AGGRESSOR_SIDE value;

        public AggressorSide(ENUM_AGGRESSOR_SIDE value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static AggressorSide parse(Container container, IBinaryProtocolElement element) {
            return new AggressorSide(ENUM_AGGRESSOR_SIDE.valueOf((int)LittleEndianUtils.toByte(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "aggressorSide = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Md Entry Size: 4 Byte Signed Fixed Width Integer
    public static class MdEntrySize implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public MdEntrySize(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static MdEntrySize parse(Container container, IBinaryProtocolElement element) {
            return new MdEntrySize(LittleEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "mdEntrySize = "+ this.value;
        }
    }

    /**
    * M D Incremental Refresh Trade Summary Group
    */
    public static class MDIncrementalRefreshTradeSummaryGroup implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public MdEntryPx mdEntryPx;
        public MdEntrySize mdEntrySize;
        public SecurityId securityId;
        public RptSeq rptSeq;
        public NumberOfOrdersOptional numberOfOrdersOptional;
        public AggressorSide aggressorSide;
        public MdUpdateAction mdUpdateAction;
        public MdTradeEntryId mdTradeEntryId;
        public Padding2 padding2;

        // constructor for M D Incremental Refresh Trade Summary Group
        private MDIncrementalRefreshTradeSummaryGroup(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for M D Incremental Refresh Trade Summary Group
        public static MDIncrementalRefreshTradeSummaryGroup parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new MDIncrementalRefreshTradeSummaryGroup(parentElement);

            element.mdEntryPx = MdEntryPx.parse(container, element);
            element.mdEntrySize = MdEntrySize.parse(container, element);
            element.securityId = SecurityId.parse(container, element);
            element.rptSeq = RptSeq.parse(container, element);
            element.numberOfOrdersOptional = NumberOfOrdersOptional.parse(container, element);
            element.aggressorSide = AggressorSide.parse(container, element);
            element.mdUpdateAction = MdUpdateAction.parse(container, element);
            element.mdTradeEntryId = MdTradeEntryId.parse(container, element);
            element.padding2 = Padding2.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.mdEntryPx).append("\n");
            sb.append(this.mdEntrySize).append("\n");
            sb.append(this.securityId).append("\n");
            sb.append(this.rptSeq).append("\n");
            sb.append(this.numberOfOrdersOptional).append("\n");
            sb.append(this.aggressorSide).append("\n");
            sb.append(this.mdUpdateAction).append("\n");
            sb.append(this.mdTradeEntryId).append("\n");
            sb.append(this.padding2).append("\n");
            return sb.toString();
        }
    }

    /**
    * M D Incremental Refresh Trade Summary Groups
    */
    public static class MDIncrementalRefreshTradeSummaryGroups implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public GroupSize groupSize;
        public List<MDIncrementalRefreshTradeSummaryGroup> mDIncrementalRefreshTradeSummaryGroupList = new ArrayList<>();

        // constructor for M D Incremental Refresh Trade Summary Groups
        private MDIncrementalRefreshTradeSummaryGroups(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for M D Incremental Refresh Trade Summary Groups
        public static MDIncrementalRefreshTradeSummaryGroups parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new MDIncrementalRefreshTradeSummaryGroups(parentElement);

            element.groupSize = GroupSize.parse(container, element);

            // M D Incremental Refresh Trade Summary Group: Struct of 9 fields
            for (int i = 0; i < element.groupSize.numInGroup.value; i++) {
                element.mDIncrementalRefreshTradeSummaryGroupList.add(MDIncrementalRefreshTradeSummaryGroup.parse(container, element));
            }

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.groupSize).append("\n");
            for (var mDIncrementalRefreshTradeSummaryGroup: mDIncrementalRefreshTradeSummaryGroupList) {
                sb.append(mDIncrementalRefreshTradeSummaryGroup).append("\n");
            }
            return sb.toString();
        }
    }

    /**
    * Md Incremental Refresh Trade Summary 48
    */
    public static class MdIncrementalRefreshTradeSummary48 implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public TransactTime transactTime;
        public MatchEventIndicator matchEventIndicator;
        public Padding2 padding2;
        public MDIncrementalRefreshTradeSummaryGroups mDIncrementalRefreshTradeSummaryGroups;
        public MDIncrementalRefreshTradeSummaryOrderGroups mDIncrementalRefreshTradeSummaryOrderGroups;

        // constructor for Md Incremental Refresh Trade Summary 48
        private MdIncrementalRefreshTradeSummary48(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Md Incremental Refresh Trade Summary 48
        public static MdIncrementalRefreshTradeSummary48 parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new MdIncrementalRefreshTradeSummary48(parentElement);

            element.transactTime = TransactTime.parse(container, element);
            element.matchEventIndicator = MatchEventIndicator.parse(container, element);
            element.padding2 = Padding2.parse(container, element);
            element.mDIncrementalRefreshTradeSummaryGroups = MDIncrementalRefreshTradeSummaryGroups.parse(container, element);
            element.mDIncrementalRefreshTradeSummaryOrderGroups = MDIncrementalRefreshTradeSummaryOrderGroups.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.transactTime).append("\n");
            sb.append(this.matchEventIndicator).append("\n");
            sb.append(this.padding2).append("\n");
            sb.append(this.mDIncrementalRefreshTradeSummaryGroups).append("\n");
            sb.append(this.mDIncrementalRefreshTradeSummaryOrderGroups).append("\n");
            return sb.toString();
        }
    }

    // Padding 6: 6 Byte
    public static class Padding6 implements IBinaryProtocolElement {
        public static final int LENGTH = 6;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public Padding6(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Padding6 parse(Container container, IBinaryProtocolElement element) {
            return new Padding6(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "padding6 = "+ this.value;
        }
    }

    // Md Display Qty Optional: 4 Byte Signed Fixed Width Integer Nullable
    public static class MdDisplayQtyOptional implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public MdDisplayQtyOptional(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static MdDisplayQtyOptional parse(Container container, IBinaryProtocolElement element) {
            return new MdDisplayQtyOptional(LittleEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "mdDisplayQtyOptional = "+ this.value;
        }
    }

    // Order Id Optional: 8 Byte Unsigned Fixed Width Integer Nullable
    public static class OrderIdOptional implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public OrderIdOptional(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static OrderIdOptional parse(Container container, IBinaryProtocolElement element) {
            return new OrderIdOptional(LittleEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "orderIdOptional = "+ this.value;
        }
    }

    /**
    * M D Incremental Refresh Order Book Group
    */
    public static class MDIncrementalRefreshOrderBookGroup implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public OrderIdOptional orderIdOptional;
        public MdOrderPriority mdOrderPriority;
        public MdEntryPxOptional mdEntryPxOptional;
        public MdDisplayQtyOptional mdDisplayQtyOptional;
        public SecurityId securityId;
        public MdUpdateAction mdUpdateAction;
        public MdEntryTypeBook mdEntryTypeBook;
        public Padding6 padding6;

        // constructor for M D Incremental Refresh Order Book Group
        private MDIncrementalRefreshOrderBookGroup(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for M D Incremental Refresh Order Book Group
        public static MDIncrementalRefreshOrderBookGroup parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new MDIncrementalRefreshOrderBookGroup(parentElement);

            element.orderIdOptional = OrderIdOptional.parse(container, element);
            element.mdOrderPriority = MdOrderPriority.parse(container, element);
            element.mdEntryPxOptional = MdEntryPxOptional.parse(container, element);
            element.mdDisplayQtyOptional = MdDisplayQtyOptional.parse(container, element);
            element.securityId = SecurityId.parse(container, element);
            element.mdUpdateAction = MdUpdateAction.parse(container, element);
            element.mdEntryTypeBook = MdEntryTypeBook.parse(container, element);
            element.padding6 = Padding6.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.orderIdOptional).append("\n");
            sb.append(this.mdOrderPriority).append("\n");
            sb.append(this.mdEntryPxOptional).append("\n");
            sb.append(this.mdDisplayQtyOptional).append("\n");
            sb.append(this.securityId).append("\n");
            sb.append(this.mdUpdateAction).append("\n");
            sb.append(this.mdEntryTypeBook).append("\n");
            sb.append(this.padding6).append("\n");
            return sb.toString();
        }
    }

    /**
    * M D Incremental Refresh Order Book Groups
    */
    public static class MDIncrementalRefreshOrderBookGroups implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public GroupSize groupSize;
        public List<MDIncrementalRefreshOrderBookGroup> mDIncrementalRefreshOrderBookGroupList = new ArrayList<>();

        // constructor for M D Incremental Refresh Order Book Groups
        private MDIncrementalRefreshOrderBookGroups(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for M D Incremental Refresh Order Book Groups
        public static MDIncrementalRefreshOrderBookGroups parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new MDIncrementalRefreshOrderBookGroups(parentElement);

            element.groupSize = GroupSize.parse(container, element);

            // M D Incremental Refresh Order Book Group: Struct of 8 fields
            for (int i = 0; i < element.groupSize.numInGroup.value; i++) {
                element.mDIncrementalRefreshOrderBookGroupList.add(MDIncrementalRefreshOrderBookGroup.parse(container, element));
            }

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.groupSize).append("\n");
            for (var mDIncrementalRefreshOrderBookGroup: mDIncrementalRefreshOrderBookGroupList) {
                sb.append(mDIncrementalRefreshOrderBookGroup).append("\n");
            }
            return sb.toString();
        }
    }

    /**
    * Md Incremental Refresh Order Book 47
    */
    public static class MdIncrementalRefreshOrderBook47 implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public TransactTime transactTime;
        public MatchEventIndicator matchEventIndicator;
        public Padding2 padding2;
        public MDIncrementalRefreshOrderBookGroups mDIncrementalRefreshOrderBookGroups;

        // constructor for Md Incremental Refresh Order Book 47
        private MdIncrementalRefreshOrderBook47(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Md Incremental Refresh Order Book 47
        public static MdIncrementalRefreshOrderBook47 parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new MdIncrementalRefreshOrderBook47(parentElement);

            element.transactTime = TransactTime.parse(container, element);
            element.matchEventIndicator = MatchEventIndicator.parse(container, element);
            element.padding2 = Padding2.parse(container, element);
            element.mDIncrementalRefreshOrderBookGroups = MDIncrementalRefreshOrderBookGroups.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.transactTime).append("\n");
            sb.append(this.matchEventIndicator).append("\n");
            sb.append(this.padding2).append("\n");
            sb.append(this.mDIncrementalRefreshOrderBookGroups).append("\n");
            return sb.toString();
        }
    }

    // Order Update Action: 1 Byte Unsigned Fixed Width Integer Enum with 3 values
    public static class OrderUpdateAction implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_ORDER_UPDATE_ACTION value;

        public OrderUpdateAction(ENUM_ORDER_UPDATE_ACTION value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static OrderUpdateAction parse(Container container, IBinaryProtocolElement element) {
            return new OrderUpdateAction(ENUM_ORDER_UPDATE_ACTION.valueOf((int)LittleEndianUtils.toByte(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "orderUpdateAction = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Reference Id: 1 Byte Unsigned Fixed Width Integer Nullable
    public static class ReferenceId implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public ReferenceId(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static ReferenceId parse(Container container, IBinaryProtocolElement element) {
            return new ReferenceId(LittleEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "referenceId = "+ this.value;
        }
    }

    /**
    * M D Incremental Refresh Book Order Group
    */
    public static class MDIncrementalRefreshBookOrderGroup implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public OrderId orderId;
        public MdOrderPriority mdOrderPriority;
        public MdDisplayQtyOptional mdDisplayQtyOptional;
        public ReferenceId referenceId;
        public OrderUpdateAction orderUpdateAction;
        public Padding2 padding2;

        // constructor for M D Incremental Refresh Book Order Group
        private MDIncrementalRefreshBookOrderGroup(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for M D Incremental Refresh Book Order Group
        public static MDIncrementalRefreshBookOrderGroup parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new MDIncrementalRefreshBookOrderGroup(parentElement);

            element.orderId = OrderId.parse(container, element);
            element.mdOrderPriority = MdOrderPriority.parse(container, element);
            element.mdDisplayQtyOptional = MdDisplayQtyOptional.parse(container, element);
            element.referenceId = ReferenceId.parse(container, element);
            element.orderUpdateAction = OrderUpdateAction.parse(container, element);
            element.padding2 = Padding2.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.orderId).append("\n");
            sb.append(this.mdOrderPriority).append("\n");
            sb.append(this.mdDisplayQtyOptional).append("\n");
            sb.append(this.referenceId).append("\n");
            sb.append(this.orderUpdateAction).append("\n");
            sb.append(this.padding2).append("\n");
            return sb.toString();
        }
    }

    /**
    * M D Incremental Refresh Book Order Groups
    */
    public static class MDIncrementalRefreshBookOrderGroups implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public GroupSize8Byte groupSize8Byte;
        public List<MDIncrementalRefreshBookOrderGroup> mDIncrementalRefreshBookOrderGroupList = new ArrayList<>();

        // constructor for M D Incremental Refresh Book Order Groups
        private MDIncrementalRefreshBookOrderGroups(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for M D Incremental Refresh Book Order Groups
        public static MDIncrementalRefreshBookOrderGroups parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new MDIncrementalRefreshBookOrderGroups(parentElement);

            element.groupSize8Byte = GroupSize8Byte.parse(container, element);

            // M D Incremental Refresh Book Order Group: Struct of 6 fields
            for (int i = 0; i < element.groupSize8Byte.numInGroup.value; i++) {
                element.mDIncrementalRefreshBookOrderGroupList.add(MDIncrementalRefreshBookOrderGroup.parse(container, element));
            }

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.groupSize8Byte).append("\n");
            for (var mDIncrementalRefreshBookOrderGroup: mDIncrementalRefreshBookOrderGroupList) {
                sb.append(mDIncrementalRefreshBookOrderGroup).append("\n");
            }
            return sb.toString();
        }
    }

    // Md Price Level: 1 Byte Unsigned Fixed Width Integer
    public static class MdPriceLevel implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public MdPriceLevel(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static MdPriceLevel parse(Container container, IBinaryProtocolElement element) {
            return new MdPriceLevel(LittleEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "mdPriceLevel = "+ this.value;
        }
    }

    /**
    * M D Incremental Refresh Book Group
    */
    public static class MDIncrementalRefreshBookGroup implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public MdEntryPxOptional mdEntryPxOptional;
        public MdEntrySizeOptional mdEntrySizeOptional;
        public SecurityId securityId;
        public RptSeq rptSeq;
        public NumberOfOrdersOptional numberOfOrdersOptional;
        public MdPriceLevel mdPriceLevel;
        public MdUpdateAction mdUpdateAction;
        public MdEntryTypeBook mdEntryTypeBook;
        public Padding5 padding5;

        // constructor for M D Incremental Refresh Book Group
        private MDIncrementalRefreshBookGroup(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for M D Incremental Refresh Book Group
        public static MDIncrementalRefreshBookGroup parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new MDIncrementalRefreshBookGroup(parentElement);

            element.mdEntryPxOptional = MdEntryPxOptional.parse(container, element);
            element.mdEntrySizeOptional = MdEntrySizeOptional.parse(container, element);
            element.securityId = SecurityId.parse(container, element);
            element.rptSeq = RptSeq.parse(container, element);
            element.numberOfOrdersOptional = NumberOfOrdersOptional.parse(container, element);
            element.mdPriceLevel = MdPriceLevel.parse(container, element);
            element.mdUpdateAction = MdUpdateAction.parse(container, element);
            element.mdEntryTypeBook = MdEntryTypeBook.parse(container, element);
            element.padding5 = Padding5.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.mdEntryPxOptional).append("\n");
            sb.append(this.mdEntrySizeOptional).append("\n");
            sb.append(this.securityId).append("\n");
            sb.append(this.rptSeq).append("\n");
            sb.append(this.numberOfOrdersOptional).append("\n");
            sb.append(this.mdPriceLevel).append("\n");
            sb.append(this.mdUpdateAction).append("\n");
            sb.append(this.mdEntryTypeBook).append("\n");
            sb.append(this.padding5).append("\n");
            return sb.toString();
        }
    }

    /**
    * M D Incremental Refresh Book Groups
    */
    public static class MDIncrementalRefreshBookGroups implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public GroupSize groupSize;
        public List<MDIncrementalRefreshBookGroup> mDIncrementalRefreshBookGroupList = new ArrayList<>();

        // constructor for M D Incremental Refresh Book Groups
        private MDIncrementalRefreshBookGroups(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for M D Incremental Refresh Book Groups
        public static MDIncrementalRefreshBookGroups parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new MDIncrementalRefreshBookGroups(parentElement);

            element.groupSize = GroupSize.parse(container, element);

            // M D Incremental Refresh Book Group: Struct of 9 fields
            for (int i = 0; i < element.groupSize.numInGroup.value; i++) {
                element.mDIncrementalRefreshBookGroupList.add(MDIncrementalRefreshBookGroup.parse(container, element));
            }

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.groupSize).append("\n");
            for (var mDIncrementalRefreshBookGroup: mDIncrementalRefreshBookGroupList) {
                sb.append(mDIncrementalRefreshBookGroup).append("\n");
            }
            return sb.toString();
        }
    }

    /**
    * Md Incremental Refresh Book 46
    */
    public static class MdIncrementalRefreshBook46 implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public TransactTime transactTime;
        public MatchEventIndicator matchEventIndicator;
        public Padding2 padding2;
        public MDIncrementalRefreshBookGroups mDIncrementalRefreshBookGroups;
        public MDIncrementalRefreshBookOrderGroups mDIncrementalRefreshBookOrderGroups;

        // constructor for Md Incremental Refresh Book 46
        private MdIncrementalRefreshBook46(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Md Incremental Refresh Book 46
        public static MdIncrementalRefreshBook46 parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new MdIncrementalRefreshBook46(parentElement);

            element.transactTime = TransactTime.parse(container, element);
            element.matchEventIndicator = MatchEventIndicator.parse(container, element);
            element.padding2 = Padding2.parse(container, element);
            element.mDIncrementalRefreshBookGroups = MDIncrementalRefreshBookGroups.parse(container, element);
            element.mDIncrementalRefreshBookOrderGroups = MDIncrementalRefreshBookOrderGroups.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.transactTime).append("\n");
            sb.append(this.matchEventIndicator).append("\n");
            sb.append(this.padding2).append("\n");
            sb.append(this.mDIncrementalRefreshBookGroups).append("\n");
            sb.append(this.mDIncrementalRefreshBookOrderGroups).append("\n");
            return sb.toString();
        }
    }

    /**
    * Snapshot Full Refresh Order Book 44
    */
    public static class SnapshotFullRefreshOrderBook44 implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public LastMsgSeqNumProcessed lastMsgSeqNumProcessed;
        public TotNumReports totNumReports;
        public SecurityId securityId;
        public NoChunks noChunks;
        public CurrentChunk currentChunk;
        public TransactTime transactTime;
        public SnapshotFullRefreshOrderBookGroups snapshotFullRefreshOrderBookGroups;

        // constructor for Snapshot Full Refresh Order Book 44
        private SnapshotFullRefreshOrderBook44(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Snapshot Full Refresh Order Book 44
        public static SnapshotFullRefreshOrderBook44 parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new SnapshotFullRefreshOrderBook44(parentElement);

            element.lastMsgSeqNumProcessed = LastMsgSeqNumProcessed.parse(container, element);
            element.totNumReports = TotNumReports.parse(container, element);
            element.securityId = SecurityId.parse(container, element);
            element.noChunks = NoChunks.parse(container, element);
            element.currentChunk = CurrentChunk.parse(container, element);
            element.transactTime = TransactTime.parse(container, element);
            element.snapshotFullRefreshOrderBookGroups = SnapshotFullRefreshOrderBookGroups.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.lastMsgSeqNumProcessed).append("\n");
            sb.append(this.totNumReports).append("\n");
            sb.append(this.securityId).append("\n");
            sb.append(this.noChunks).append("\n");
            sb.append(this.currentChunk).append("\n");
            sb.append(this.transactTime).append("\n");
            sb.append(this.snapshotFullRefreshOrderBookGroups).append("\n");
            return sb.toString();
        }
    }

    /**
    * Md Incremental Refresh Order Book 43
    */
    public static class MdIncrementalRefreshOrderBook43 implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public TransactTime transactTime;
        public MatchEventIndicator matchEventIndicator;
        public Padding2 padding2;
        public MDIncrementalRefreshOrderBookGroups mDIncrementalRefreshOrderBookGroups;

        // constructor for Md Incremental Refresh Order Book 43
        private MdIncrementalRefreshOrderBook43(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Md Incremental Refresh Order Book 43
        public static MdIncrementalRefreshOrderBook43 parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new MdIncrementalRefreshOrderBook43(parentElement);

            element.transactTime = TransactTime.parse(container, element);
            element.matchEventIndicator = MatchEventIndicator.parse(container, element);
            element.padding2 = Padding2.parse(container, element);
            element.mDIncrementalRefreshOrderBookGroups = MDIncrementalRefreshOrderBookGroups.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.transactTime).append("\n");
            sb.append(this.matchEventIndicator).append("\n");
            sb.append(this.padding2).append("\n");
            sb.append(this.mDIncrementalRefreshOrderBookGroups).append("\n");
            return sb.toString();
        }
    }

    /**
    * Md Incremental Refresh Trade Summary 42
    */
    public static class MdIncrementalRefreshTradeSummary42 implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public TransactTime transactTime;
        public MatchEventIndicator matchEventIndicator;
        public Padding2 padding2;
        public MDIncrementalRefreshTradeSummaryGroups mDIncrementalRefreshTradeSummaryGroups;
        public MDIncrementalRefreshTradeSummaryOrderGroups mDIncrementalRefreshTradeSummaryOrderGroups;

        // constructor for Md Incremental Refresh Trade Summary 42
        private MdIncrementalRefreshTradeSummary42(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Md Incremental Refresh Trade Summary 42
        public static MdIncrementalRefreshTradeSummary42 parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new MdIncrementalRefreshTradeSummary42(parentElement);

            element.transactTime = TransactTime.parse(container, element);
            element.matchEventIndicator = MatchEventIndicator.parse(container, element);
            element.padding2 = Padding2.parse(container, element);
            element.mDIncrementalRefreshTradeSummaryGroups = MDIncrementalRefreshTradeSummaryGroups.parse(container, element);
            element.mDIncrementalRefreshTradeSummaryOrderGroups = MDIncrementalRefreshTradeSummaryOrderGroups.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.transactTime).append("\n");
            sb.append(this.matchEventIndicator).append("\n");
            sb.append(this.padding2).append("\n");
            sb.append(this.mDIncrementalRefreshTradeSummaryGroups).append("\n");
            sb.append(this.mDIncrementalRefreshTradeSummaryOrderGroups).append("\n");
            return sb.toString();
        }
    }

    // Trading Reference Price Pricenull: 8 Byte Signed Fixed Width Integer Nullable
    public static class TradingReferencePricePricenull implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public TradingReferencePricePricenull(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value/10000000;
        }

        public static TradingReferencePricePricenull parse(Container container, IBinaryProtocolElement element) {
            return new TradingReferencePricePricenull(LittleEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "tradingReferencePricePricenull = "+ this.value;
        }
    }

    // Unit Of Measure Qty Pricenull: 8 Byte Signed Fixed Width Integer Nullable
    public static class UnitOfMeasureQtyPricenull implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public UnitOfMeasureQtyPricenull(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value/10000000;
        }

        public static UnitOfMeasureQtyPricenull parse(Container container, IBinaryProtocolElement element) {
            return new UnitOfMeasureQtyPricenull(LittleEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "unitOfMeasureQtyPricenull = "+ this.value;
        }
    }

    // Display Factor Float: 8 Byte Signed Fixed Width Integer
    public static class DisplayFactorFloat implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public DisplayFactorFloat(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value/10000000;
        }

        public static DisplayFactorFloat parse(Container container, IBinaryProtocolElement element) {
            return new DisplayFactorFloat(LittleEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "displayFactorFloat = "+ this.value;
        }
    }

    // Min Price Increment Amount Pricenull: 8 Byte Signed Fixed Width Integer Nullable
    public static class MinPriceIncrementAmountPricenull implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public MinPriceIncrementAmountPricenull(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value/10000000;
        }

        public static MinPriceIncrementAmountPricenull parse(Container container, IBinaryProtocolElement element) {
            return new MinPriceIncrementAmountPricenull(LittleEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "minPriceIncrementAmountPricenull = "+ this.value;
        }
    }

    // Min Price Increment Pricenull: 8 Byte Signed Fixed Width Integer Nullable
    public static class MinPriceIncrementPricenull implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public MinPriceIncrementPricenull(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value/10000000;
        }

        public static MinPriceIncrementPricenull parse(Container container, IBinaryProtocolElement element) {
            return new MinPriceIncrementPricenull(LittleEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "minPriceIncrementPricenull = "+ this.value;
        }
    }

    // Min Cab Price Pricenull: 8 Byte Signed Fixed Width Integer Nullable
    public static class MinCabPricePricenull implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public MinCabPricePricenull(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value/10000000;
        }

        public static MinCabPricePricenull parse(Container container, IBinaryProtocolElement element) {
            return new MinCabPricePricenull(LittleEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "minCabPricePricenull = "+ this.value;
        }
    }

    // Strike Price Pricenull: 8 Byte Signed Fixed Width Integer Nullable
    public static class StrikePricePricenull implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public StrikePricePricenull(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value/10000000;
        }

        public static StrikePricePricenull parse(Container container, IBinaryProtocolElement element) {
            return new StrikePricePricenull(LittleEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "strikePricePricenull = "+ this.value;
        }
    }

    /**
    * Md Instrument Definition Option 41
    */
    public static class MdInstrumentDefinitionOption41 implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public MatchEventIndicator matchEventIndicator;
        public TotNumReportsOptional totNumReportsOptional;
        public SecurityUpdateAction securityUpdateAction;
        public LastUpdateTime lastUpdateTime;
        public MdSecurityTradingStatus mdSecurityTradingStatus;
        public ApplId applId;
        public MarketSegmentId marketSegmentId;
        public UnderlyingProduct underlyingProduct;
        public SecurityExchange securityExchange;
        public SecurityGroup securityGroup;
        public Asset asset;
        public Symbol symbol;
        public SecurityId securityId;
        public SecurityType securityType;
        public CfiCode cfiCode;
        public PutOrCall putOrCall;
        public MaturityMonthYear maturityMonthYear;
        public Currency currency;
        public StrikePricePricenull strikePricePricenull;
        public StrikeCurrency strikeCurrency;
        public SettlCurrency settlCurrency;
        public MinCabPricePricenull minCabPricePricenull;
        public MatchAlgorithm matchAlgorithm;
        public MinTradeVol minTradeVol;
        public MaxTradeVol maxTradeVol;
        public MinPriceIncrementPricenull minPriceIncrementPricenull;
        public MinPriceIncrementAmountPricenull minPriceIncrementAmountPricenull;
        public DisplayFactorFloat displayFactorFloat;
        public TickRule tickRule;
        public MainFraction mainFraction;
        public SubFraction subFraction;
        public PriceDisplayFormat priceDisplayFormat;
        public UnitOfMeasure unitOfMeasure;
        public UnitOfMeasureQtyPricenull unitOfMeasureQtyPricenull;
        public TradingReferencePricePricenull tradingReferencePricePricenull;
        public SettlPriceType settlPriceType;
        public ClearedVolume clearedVolume;
        public OpenInterestQty openInterestQty;
        public LowLimitPricePricenull lowLimitPricePricenull;
        public HighLimitPricePricenull highLimitPricePricenull;
        public UserDefinedInstrument userDefinedInstrument;
        public TradingReferenceDate tradingReferenceDate;
        public EventsGroups eventsGroups;
        public MDFeedTypesGroups mDFeedTypesGroups;
        public InstAttribGroups instAttribGroups;
        public LotTypeRulesGroups lotTypeRulesGroups;
        public UnderlyingsGroups underlyingsGroups;
        public RelatedInstrumentsGroups relatedInstrumentsGroups;

        // constructor for Md Instrument Definition Option 41
        private MdInstrumentDefinitionOption41(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Md Instrument Definition Option 41
        public static MdInstrumentDefinitionOption41 parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new MdInstrumentDefinitionOption41(parentElement);

            element.matchEventIndicator = MatchEventIndicator.parse(container, element);
            element.totNumReportsOptional = TotNumReportsOptional.parse(container, element);
            element.securityUpdateAction = SecurityUpdateAction.parse(container, element);
            element.lastUpdateTime = LastUpdateTime.parse(container, element);
            element.mdSecurityTradingStatus = MdSecurityTradingStatus.parse(container, element);
            element.applId = ApplId.parse(container, element);
            element.marketSegmentId = MarketSegmentId.parse(container, element);
            element.underlyingProduct = UnderlyingProduct.parse(container, element);
            element.securityExchange = SecurityExchange.parse(container, element);
            element.securityGroup = SecurityGroup.parse(container, element);
            element.asset = Asset.parse(container, element);
            element.symbol = Symbol.parse(container, element);
            element.securityId = SecurityId.parse(container, element);
            element.securityType = SecurityType.parse(container, element);
            element.cfiCode = CfiCode.parse(container, element);
            element.putOrCall = PutOrCall.parse(container, element);
            element.maturityMonthYear = MaturityMonthYear.parse(container, element);
            element.currency = Currency.parse(container, element);
            element.strikePricePricenull = StrikePricePricenull.parse(container, element);
            element.strikeCurrency = StrikeCurrency.parse(container, element);
            element.settlCurrency = SettlCurrency.parse(container, element);
            element.minCabPricePricenull = MinCabPricePricenull.parse(container, element);
            element.matchAlgorithm = MatchAlgorithm.parse(container, element);
            element.minTradeVol = MinTradeVol.parse(container, element);
            element.maxTradeVol = MaxTradeVol.parse(container, element);
            element.minPriceIncrementPricenull = MinPriceIncrementPricenull.parse(container, element);
            element.minPriceIncrementAmountPricenull = MinPriceIncrementAmountPricenull.parse(container, element);
            element.displayFactorFloat = DisplayFactorFloat.parse(container, element);
            element.tickRule = TickRule.parse(container, element);
            element.mainFraction = MainFraction.parse(container, element);
            element.subFraction = SubFraction.parse(container, element);
            element.priceDisplayFormat = PriceDisplayFormat.parse(container, element);
            element.unitOfMeasure = UnitOfMeasure.parse(container, element);
            element.unitOfMeasureQtyPricenull = UnitOfMeasureQtyPricenull.parse(container, element);
            element.tradingReferencePricePricenull = TradingReferencePricePricenull.parse(container, element);
            element.settlPriceType = SettlPriceType.parse(container, element);
            element.clearedVolume = ClearedVolume.parse(container, element);
            element.openInterestQty = OpenInterestQty.parse(container, element);
            element.lowLimitPricePricenull = LowLimitPricePricenull.parse(container, element);
            element.highLimitPricePricenull = HighLimitPricePricenull.parse(container, element);
            element.userDefinedInstrument = UserDefinedInstrument.parse(container, element);
            element.tradingReferenceDate = TradingReferenceDate.parse(container, element);
            element.eventsGroups = EventsGroups.parse(container, element);
            element.mDFeedTypesGroups = MDFeedTypesGroups.parse(container, element);
            element.instAttribGroups = InstAttribGroups.parse(container, element);
            element.lotTypeRulesGroups = LotTypeRulesGroups.parse(container, element);
            element.underlyingsGroups = UnderlyingsGroups.parse(container, element);
            element.relatedInstrumentsGroups = RelatedInstrumentsGroups.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.matchEventIndicator).append("\n");
            sb.append(this.totNumReportsOptional).append("\n");
            sb.append(this.securityUpdateAction).append("\n");
            sb.append(this.lastUpdateTime).append("\n");
            sb.append(this.mdSecurityTradingStatus).append("\n");
            sb.append(this.applId).append("\n");
            sb.append(this.marketSegmentId).append("\n");
            sb.append(this.underlyingProduct).append("\n");
            sb.append(this.securityExchange).append("\n");
            sb.append(this.securityGroup).append("\n");
            sb.append(this.asset).append("\n");
            sb.append(this.symbol).append("\n");
            sb.append(this.securityId).append("\n");
            sb.append(this.securityType).append("\n");
            sb.append(this.cfiCode).append("\n");
            sb.append(this.putOrCall).append("\n");
            sb.append(this.maturityMonthYear).append("\n");
            sb.append(this.currency).append("\n");
            sb.append(this.strikePricePricenull).append("\n");
            sb.append(this.strikeCurrency).append("\n");
            sb.append(this.settlCurrency).append("\n");
            sb.append(this.minCabPricePricenull).append("\n");
            sb.append(this.matchAlgorithm).append("\n");
            sb.append(this.minTradeVol).append("\n");
            sb.append(this.maxTradeVol).append("\n");
            sb.append(this.minPriceIncrementPricenull).append("\n");
            sb.append(this.minPriceIncrementAmountPricenull).append("\n");
            sb.append(this.displayFactorFloat).append("\n");
            sb.append(this.tickRule).append("\n");
            sb.append(this.mainFraction).append("\n");
            sb.append(this.subFraction).append("\n");
            sb.append(this.priceDisplayFormat).append("\n");
            sb.append(this.unitOfMeasure).append("\n");
            sb.append(this.unitOfMeasureQtyPricenull).append("\n");
            sb.append(this.tradingReferencePricePricenull).append("\n");
            sb.append(this.settlPriceType).append("\n");
            sb.append(this.clearedVolume).append("\n");
            sb.append(this.openInterestQty).append("\n");
            sb.append(this.lowLimitPricePricenull).append("\n");
            sb.append(this.highLimitPricePricenull).append("\n");
            sb.append(this.userDefinedInstrument).append("\n");
            sb.append(this.tradingReferenceDate).append("\n");
            sb.append(this.eventsGroups).append("\n");
            sb.append(this.mDFeedTypesGroups).append("\n");
            sb.append(this.instAttribGroups).append("\n");
            sb.append(this.lotTypeRulesGroups).append("\n");
            sb.append(this.underlyingsGroups).append("\n");
            sb.append(this.relatedInstrumentsGroups).append("\n");
            return sb.toString();
        }
    }

    // Side: 1 Byte Signed Fixed Width Integer Nullable
    public static class Side implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public Side(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Side parse(Container container, IBinaryProtocolElement element) {
            return new Side(LittleEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "side = "+ this.value;
        }
    }

    // Quote Type: 1 Byte Signed Fixed Width Integer
    public static class QuoteType implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public QuoteType(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static QuoteType parse(Container container, IBinaryProtocolElement element) {
            return new QuoteType(LittleEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "quoteType = "+ this.value;
        }
    }

    // Order Qty: 4 Byte Signed Fixed Width Integer Nullable
    public static class OrderQty implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public OrderQty(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static OrderQty parse(Container container, IBinaryProtocolElement element) {
            return new OrderQty(LittleEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "orderQty = "+ this.value;
        }
    }

    /**
    * Related Sym Group
    */
    public static class RelatedSymGroup implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Symbol symbol;
        public SecurityId securityId;
        public OrderQty orderQty;
        public QuoteType quoteType;
        public Side side;
        public Padding2 padding2;

        // constructor for Related Sym Group
        private RelatedSymGroup(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Related Sym Group
        public static RelatedSymGroup parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new RelatedSymGroup(parentElement);

            element.symbol = Symbol.parse(container, element);
            element.securityId = SecurityId.parse(container, element);
            element.orderQty = OrderQty.parse(container, element);
            element.quoteType = QuoteType.parse(container, element);
            element.side = Side.parse(container, element);
            element.padding2 = Padding2.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.symbol).append("\n");
            sb.append(this.securityId).append("\n");
            sb.append(this.orderQty).append("\n");
            sb.append(this.quoteType).append("\n");
            sb.append(this.side).append("\n");
            sb.append(this.padding2).append("\n");
            return sb.toString();
        }
    }

    /**
    * Related Sym Groups
    */
    public static class RelatedSymGroups implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public GroupSize groupSize;
        public List<RelatedSymGroup> relatedSymGroupList = new ArrayList<>();

        // constructor for Related Sym Groups
        private RelatedSymGroups(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Related Sym Groups
        public static RelatedSymGroups parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new RelatedSymGroups(parentElement);

            element.groupSize = GroupSize.parse(container, element);

            // Related Sym Group: Struct of 6 fields
            for (int i = 0; i < element.groupSize.numInGroup.value; i++) {
                element.relatedSymGroupList.add(RelatedSymGroup.parse(container, element));
            }

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.groupSize).append("\n");
            for (var relatedSymGroup: relatedSymGroupList) {
                sb.append(relatedSymGroup).append("\n");
            }
            return sb.toString();
        }
    }

    // Padding 3: 3 Byte
    public static class Padding3 implements IBinaryProtocolElement {
        public static final int LENGTH = 3;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public Padding3(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Padding3 parse(Container container, IBinaryProtocolElement element) {
            return new Padding3(BigEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "padding3 = "+ this.value;
        }
    }

    // Quote Req Id: 23 Byte Ascii String
    public static class QuoteReqId implements IBinaryProtocolElement {
        public static final int LENGTH = 23;
        public final IBinaryProtocolElement parent;
        public final String value;

        public QuoteReqId(String value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static QuoteReqId parse(Container container, IBinaryProtocolElement element) {
            return new QuoteReqId(BigEndianUtils.toString(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "quoteReqId = "+ this.value;
        }
    }

    /**
    * Quote Request 39
    */
    public static class QuoteRequest39 implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public TransactTime transactTime;
        public QuoteReqId quoteReqId;
        public MatchEventIndicator matchEventIndicator;
        public Padding3 padding3;
        public RelatedSymGroups relatedSymGroups;

        // constructor for Quote Request 39
        private QuoteRequest39(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Quote Request 39
        public static QuoteRequest39 parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new QuoteRequest39(parentElement);

            element.transactTime = TransactTime.parse(container, element);
            element.quoteReqId = QuoteReqId.parse(container, element);
            element.matchEventIndicator = MatchEventIndicator.parse(container, element);
            element.padding3 = Padding3.parse(container, element);
            element.relatedSymGroups = RelatedSymGroups.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.transactTime).append("\n");
            sb.append(this.quoteReqId).append("\n");
            sb.append(this.matchEventIndicator).append("\n");
            sb.append(this.padding3).append("\n");
            sb.append(this.relatedSymGroups).append("\n");
            return sb.toString();
        }
    }

    /**
    * Snapshot Full Refresh 38
    */
    public static class SnapshotFullRefresh38 implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public LastMsgSeqNumProcessed lastMsgSeqNumProcessed;
        public TotNumReports totNumReports;
        public SecurityId securityId;
        public RptSeq rptSeq;
        public TransactTime transactTime;
        public LastUpdateTime lastUpdateTime;
        public TradeDate tradeDate;
        public MdSecurityTradingStatus mdSecurityTradingStatus;
        public HighLimitPricePricenull highLimitPricePricenull;
        public LowLimitPricePricenull lowLimitPricePricenull;
        public MaxPriceVariationPricenull maxPriceVariationPricenull;
        public SnapshotFullRefreshGroups snapshotFullRefreshGroups;

        // constructor for Snapshot Full Refresh 38
        private SnapshotFullRefresh38(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Snapshot Full Refresh 38
        public static SnapshotFullRefresh38 parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new SnapshotFullRefresh38(parentElement);

            element.lastMsgSeqNumProcessed = LastMsgSeqNumProcessed.parse(container, element);
            element.totNumReports = TotNumReports.parse(container, element);
            element.securityId = SecurityId.parse(container, element);
            element.rptSeq = RptSeq.parse(container, element);
            element.transactTime = TransactTime.parse(container, element);
            element.lastUpdateTime = LastUpdateTime.parse(container, element);
            element.tradeDate = TradeDate.parse(container, element);
            element.mdSecurityTradingStatus = MdSecurityTradingStatus.parse(container, element);
            element.highLimitPricePricenull = HighLimitPricePricenull.parse(container, element);
            element.lowLimitPricePricenull = LowLimitPricePricenull.parse(container, element);
            element.maxPriceVariationPricenull = MaxPriceVariationPricenull.parse(container, element);
            element.snapshotFullRefreshGroups = SnapshotFullRefreshGroups.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.lastMsgSeqNumProcessed).append("\n");
            sb.append(this.totNumReports).append("\n");
            sb.append(this.securityId).append("\n");
            sb.append(this.rptSeq).append("\n");
            sb.append(this.transactTime).append("\n");
            sb.append(this.lastUpdateTime).append("\n");
            sb.append(this.tradeDate).append("\n");
            sb.append(this.mdSecurityTradingStatus).append("\n");
            sb.append(this.highLimitPricePricenull).append("\n");
            sb.append(this.lowLimitPricePricenull).append("\n");
            sb.append(this.maxPriceVariationPricenull).append("\n");
            sb.append(this.snapshotFullRefreshGroups).append("\n");
            return sb.toString();
        }
    }

    /**
    * M D Incremental Refresh Volume Group
    */
    public static class MDIncrementalRefreshVolumeGroup implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public MdEntrySize mdEntrySize;
        public SecurityId securityId;
        public RptSeq rptSeq;
        public MdUpdateAction mdUpdateAction;
        public Padding3 padding3;

        // constructor for M D Incremental Refresh Volume Group
        private MDIncrementalRefreshVolumeGroup(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for M D Incremental Refresh Volume Group
        public static MDIncrementalRefreshVolumeGroup parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new MDIncrementalRefreshVolumeGroup(parentElement);

            element.mdEntrySize = MdEntrySize.parse(container, element);
            element.securityId = SecurityId.parse(container, element);
            element.rptSeq = RptSeq.parse(container, element);
            element.mdUpdateAction = MdUpdateAction.parse(container, element);
            element.padding3 = Padding3.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.mdEntrySize).append("\n");
            sb.append(this.securityId).append("\n");
            sb.append(this.rptSeq).append("\n");
            sb.append(this.mdUpdateAction).append("\n");
            sb.append(this.padding3).append("\n");
            return sb.toString();
        }
    }

    /**
    * M D Incremental Refresh Volume Groups
    */
    public static class MDIncrementalRefreshVolumeGroups implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public GroupSize groupSize;
        public List<MDIncrementalRefreshVolumeGroup> mDIncrementalRefreshVolumeGroupList = new ArrayList<>();

        // constructor for M D Incremental Refresh Volume Groups
        private MDIncrementalRefreshVolumeGroups(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for M D Incremental Refresh Volume Groups
        public static MDIncrementalRefreshVolumeGroups parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new MDIncrementalRefreshVolumeGroups(parentElement);

            element.groupSize = GroupSize.parse(container, element);

            // M D Incremental Refresh Volume Group: Struct of 5 fields
            for (int i = 0; i < element.groupSize.numInGroup.value; i++) {
                element.mDIncrementalRefreshVolumeGroupList.add(MDIncrementalRefreshVolumeGroup.parse(container, element));
            }

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.groupSize).append("\n");
            for (var mDIncrementalRefreshVolumeGroup: mDIncrementalRefreshVolumeGroupList) {
                sb.append(mDIncrementalRefreshVolumeGroup).append("\n");
            }
            return sb.toString();
        }
    }

    /**
    * Md Incremental Refresh Volume 37
    */
    public static class MdIncrementalRefreshVolume37 implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public TransactTime transactTime;
        public MatchEventIndicator matchEventIndicator;
        public Padding2 padding2;
        public MDIncrementalRefreshVolumeGroups mDIncrementalRefreshVolumeGroups;

        // constructor for Md Incremental Refresh Volume 37
        private MdIncrementalRefreshVolume37(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Md Incremental Refresh Volume 37
        public static MdIncrementalRefreshVolume37 parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new MdIncrementalRefreshVolume37(parentElement);

            element.transactTime = TransactTime.parse(container, element);
            element.matchEventIndicator = MatchEventIndicator.parse(container, element);
            element.padding2 = Padding2.parse(container, element);
            element.mDIncrementalRefreshVolumeGroups = MDIncrementalRefreshVolumeGroups.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.transactTime).append("\n");
            sb.append(this.matchEventIndicator).append("\n");
            sb.append(this.padding2).append("\n");
            sb.append(this.mDIncrementalRefreshVolumeGroups).append("\n");
            return sb.toString();
        }
    }

    /**
    * Md Incremental Refresh Session Statistics 35
    */
    public static class MdIncrementalRefreshSessionStatistics35 implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public TransactTime transactTime;
        public MatchEventIndicator matchEventIndicator;
        public Padding2 padding2;
        public MDIncrementalRefreshSessionStatisticsGroups mDIncrementalRefreshSessionStatisticsGroups;

        // constructor for Md Incremental Refresh Session Statistics 35
        private MdIncrementalRefreshSessionStatistics35(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Md Incremental Refresh Session Statistics 35
        public static MdIncrementalRefreshSessionStatistics35 parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new MdIncrementalRefreshSessionStatistics35(parentElement);

            element.transactTime = TransactTime.parse(container, element);
            element.matchEventIndicator = MatchEventIndicator.parse(container, element);
            element.padding2 = Padding2.parse(container, element);
            element.mDIncrementalRefreshSessionStatisticsGroups = MDIncrementalRefreshSessionStatisticsGroups.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.transactTime).append("\n");
            sb.append(this.matchEventIndicator).append("\n");
            sb.append(this.padding2).append("\n");
            sb.append(this.mDIncrementalRefreshSessionStatisticsGroups).append("\n");
            return sb.toString();
        }
    }

    /**
    * Md Incremental Refresh Limits Banding 34
    */
    public static class MdIncrementalRefreshLimitsBanding34 implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public TransactTime transactTime;
        public MatchEventIndicator matchEventIndicator;
        public Padding2 padding2;
        public MDIncrementalRefreshLimitsBandingGroups mDIncrementalRefreshLimitsBandingGroups;

        // constructor for Md Incremental Refresh Limits Banding 34
        private MdIncrementalRefreshLimitsBanding34(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Md Incremental Refresh Limits Banding 34
        public static MdIncrementalRefreshLimitsBanding34 parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new MdIncrementalRefreshLimitsBanding34(parentElement);

            element.transactTime = TransactTime.parse(container, element);
            element.matchEventIndicator = MatchEventIndicator.parse(container, element);
            element.padding2 = Padding2.parse(container, element);
            element.mDIncrementalRefreshLimitsBandingGroups = MDIncrementalRefreshLimitsBandingGroups.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.transactTime).append("\n");
            sb.append(this.matchEventIndicator).append("\n");
            sb.append(this.padding2).append("\n");
            sb.append(this.mDIncrementalRefreshLimitsBandingGroups).append("\n");
            return sb.toString();
        }
    }

    /**
    * Md Incremental Refresh Daily Statistics 33
    */
    public static class MdIncrementalRefreshDailyStatistics33 implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public TransactTime transactTime;
        public MatchEventIndicator matchEventIndicator;
        public Padding2 padding2;
        public MDIncrementalRefreshDailyStatisticsGroups mDIncrementalRefreshDailyStatisticsGroups;

        // constructor for Md Incremental Refresh Daily Statistics 33
        private MdIncrementalRefreshDailyStatistics33(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Md Incremental Refresh Daily Statistics 33
        public static MdIncrementalRefreshDailyStatistics33 parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new MdIncrementalRefreshDailyStatistics33(parentElement);

            element.transactTime = TransactTime.parse(container, element);
            element.matchEventIndicator = MatchEventIndicator.parse(container, element);
            element.padding2 = Padding2.parse(container, element);
            element.mDIncrementalRefreshDailyStatisticsGroups = MDIncrementalRefreshDailyStatisticsGroups.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.transactTime).append("\n");
            sb.append(this.matchEventIndicator).append("\n");
            sb.append(this.padding2).append("\n");
            sb.append(this.mDIncrementalRefreshDailyStatisticsGroups).append("\n");
            return sb.toString();
        }
    }

    /**
    * Md Incremental Refresh Book 32
    */
    public static class MdIncrementalRefreshBook32 implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public TransactTime transactTime;
        public MatchEventIndicator matchEventIndicator;
        public Padding2 padding2;
        public MDIncrementalRefreshBookGroups mDIncrementalRefreshBookGroups;
        public MDIncrementalRefreshBookOrderGroups mDIncrementalRefreshBookOrderGroups;

        // constructor for Md Incremental Refresh Book 32
        private MdIncrementalRefreshBook32(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Md Incremental Refresh Book 32
        public static MdIncrementalRefreshBook32 parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new MdIncrementalRefreshBook32(parentElement);

            element.transactTime = TransactTime.parse(container, element);
            element.matchEventIndicator = MatchEventIndicator.parse(container, element);
            element.padding2 = Padding2.parse(container, element);
            element.mDIncrementalRefreshBookGroups = MDIncrementalRefreshBookGroups.parse(container, element);
            element.mDIncrementalRefreshBookOrderGroups = MDIncrementalRefreshBookOrderGroups.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.transactTime).append("\n");
            sb.append(this.matchEventIndicator).append("\n");
            sb.append(this.padding2).append("\n");
            sb.append(this.mDIncrementalRefreshBookGroups).append("\n");
            sb.append(this.mDIncrementalRefreshBookOrderGroups).append("\n");
            return sb.toString();
        }
    }

    // Security Trading Event: 1 Byte Unsigned Fixed Width Integer Enum with 5 values
    public static class SecurityTradingEvent implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_SECURITY_TRADING_EVENT value;

        public SecurityTradingEvent(ENUM_SECURITY_TRADING_EVENT value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static SecurityTradingEvent parse(Container container, IBinaryProtocolElement element) {
            return new SecurityTradingEvent(ENUM_SECURITY_TRADING_EVENT.valueOf((int)LittleEndianUtils.toByte(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "securityTradingEvent = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Halt Reason: 1 Byte Unsigned Fixed Width Integer Enum with 7 values
    public static class HaltReason implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_HALT_REASON value;

        public HaltReason(ENUM_HALT_REASON value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static HaltReason parse(Container container, IBinaryProtocolElement element) {
            return new HaltReason(ENUM_HALT_REASON.valueOf((int)LittleEndianUtils.toByte(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "haltReason = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Security Trading Status: 1 Byte Unsigned Fixed Width Integer Enum with 12 values
    public static class SecurityTradingStatus implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final ENUM_SECURITY_TRADING_STATUS value;

        public SecurityTradingStatus(ENUM_SECURITY_TRADING_STATUS value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static SecurityTradingStatus parse(Container container, IBinaryProtocolElement element) {
            return new SecurityTradingStatus(ENUM_SECURITY_TRADING_STATUS.valueOf((int)LittleEndianUtils.toByte(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "securityTradingStatus = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    // Security Id Optional: 4 Byte Signed Fixed Width Integer Nullable
    public static class SecurityIdOptional implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public SecurityIdOptional(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static SecurityIdOptional parse(Container container, IBinaryProtocolElement element) {
            return new SecurityIdOptional(LittleEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "securityIdOptional = "+ this.value;
        }
    }

    /**
    * Security Status 30
    */
    public static class SecurityStatus30 implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public TransactTime transactTime;
        public SecurityGroup securityGroup;
        public Asset asset;
        public SecurityIdOptional securityIdOptional;
        public TradeDate tradeDate;
        public MatchEventIndicator matchEventIndicator;
        public SecurityTradingStatus securityTradingStatus;
        public HaltReason haltReason;
        public SecurityTradingEvent securityTradingEvent;

        // constructor for Security Status 30
        private SecurityStatus30(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Security Status 30
        public static SecurityStatus30 parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new SecurityStatus30(parentElement);

            element.transactTime = TransactTime.parse(container, element);
            element.securityGroup = SecurityGroup.parse(container, element);
            element.asset = Asset.parse(container, element);
            element.securityIdOptional = SecurityIdOptional.parse(container, element);
            element.tradeDate = TradeDate.parse(container, element);
            element.matchEventIndicator = MatchEventIndicator.parse(container, element);
            element.securityTradingStatus = SecurityTradingStatus.parse(container, element);
            element.haltReason = HaltReason.parse(container, element);
            element.securityTradingEvent = SecurityTradingEvent.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.transactTime).append("\n");
            sb.append(this.securityGroup).append("\n");
            sb.append(this.asset).append("\n");
            sb.append(this.securityIdOptional).append("\n");
            sb.append(this.tradeDate).append("\n");
            sb.append(this.matchEventIndicator).append("\n");
            sb.append(this.securityTradingStatus).append("\n");
            sb.append(this.haltReason).append("\n");
            sb.append(this.securityTradingEvent).append("\n");
            return sb.toString();
        }
    }

    // Price Ratio Pricenull: 8 Byte Signed Fixed Width Integer Nullable
    public static class PriceRatioPricenull implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public PriceRatioPricenull(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value/10000000;
        }

        public static PriceRatioPricenull parse(Container container, IBinaryProtocolElement element) {
            return new PriceRatioPricenull(LittleEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "priceRatioPricenull = "+ this.value;
        }
    }

    // Min Price Increment Price: 8 Byte Signed Fixed Width Integer
    public static class MinPriceIncrementPrice implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public MinPriceIncrementPrice(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value/10000000;
        }

        public static MinPriceIncrementPrice parse(Container container, IBinaryProtocolElement element) {
            return new MinPriceIncrementPrice(LittleEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "minPriceIncrementPrice = "+ this.value;
        }
    }

    /**
    * Md Instrument Definition Spread 29
    */
    public static class MdInstrumentDefinitionSpread29 implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public MatchEventIndicator matchEventIndicator;
        public TotNumReportsOptional totNumReportsOptional;
        public SecurityUpdateAction securityUpdateAction;
        public LastUpdateTime lastUpdateTime;
        public MdSecurityTradingStatus mdSecurityTradingStatus;
        public ApplId applId;
        public MarketSegmentId marketSegmentId;
        public UnderlyingProductOptional underlyingProductOptional;
        public SecurityExchange securityExchange;
        public SecurityGroup securityGroup;
        public Asset asset;
        public Symbol symbol;
        public SecurityId securityId;
        public SecurityType securityType;
        public CfiCode cfiCode;
        public MaturityMonthYear maturityMonthYear;
        public Currency currency;
        public SecuritySubType securitySubType;
        public UserDefinedInstrument userDefinedInstrument;
        public MatchAlgorithm matchAlgorithm;
        public MinTradeVol minTradeVol;
        public MaxTradeVol maxTradeVol;
        public MinPriceIncrementPrice minPriceIncrementPrice;
        public DisplayFactorFloat displayFactorFloat;
        public PriceDisplayFormat priceDisplayFormat;
        public PriceRatioPricenull priceRatioPricenull;
        public TickRule tickRule;
        public UnitOfMeasure unitOfMeasure;
        public TradingReferencePricePricenull tradingReferencePricePricenull;
        public SettlPriceType settlPriceType;
        public OpenInterestQty openInterestQty;
        public ClearedVolume clearedVolume;
        public HighLimitPricePricenull highLimitPricePricenull;
        public LowLimitPricePricenull lowLimitPricePricenull;
        public MaxPriceVariationPricenull maxPriceVariationPricenull;
        public MainFraction mainFraction;
        public SubFraction subFraction;
        public TradingReferenceDate tradingReferenceDate;
        public EventsGroups eventsGroups;
        public MDFeedTypesGroups mDFeedTypesGroups;
        public InstAttribGroups instAttribGroups;
        public LotTypeRulesGroups lotTypeRulesGroups;
        public MDInstrumentDefinitionSpreadLegGroups mDInstrumentDefinitionSpreadLegGroups;

        // constructor for Md Instrument Definition Spread 29
        private MdInstrumentDefinitionSpread29(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Md Instrument Definition Spread 29
        public static MdInstrumentDefinitionSpread29 parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new MdInstrumentDefinitionSpread29(parentElement);

            element.matchEventIndicator = MatchEventIndicator.parse(container, element);
            element.totNumReportsOptional = TotNumReportsOptional.parse(container, element);
            element.securityUpdateAction = SecurityUpdateAction.parse(container, element);
            element.lastUpdateTime = LastUpdateTime.parse(container, element);
            element.mdSecurityTradingStatus = MdSecurityTradingStatus.parse(container, element);
            element.applId = ApplId.parse(container, element);
            element.marketSegmentId = MarketSegmentId.parse(container, element);
            element.underlyingProductOptional = UnderlyingProductOptional.parse(container, element);
            element.securityExchange = SecurityExchange.parse(container, element);
            element.securityGroup = SecurityGroup.parse(container, element);
            element.asset = Asset.parse(container, element);
            element.symbol = Symbol.parse(container, element);
            element.securityId = SecurityId.parse(container, element);
            element.securityType = SecurityType.parse(container, element);
            element.cfiCode = CfiCode.parse(container, element);
            element.maturityMonthYear = MaturityMonthYear.parse(container, element);
            element.currency = Currency.parse(container, element);
            element.securitySubType = SecuritySubType.parse(container, element);
            element.userDefinedInstrument = UserDefinedInstrument.parse(container, element);
            element.matchAlgorithm = MatchAlgorithm.parse(container, element);
            element.minTradeVol = MinTradeVol.parse(container, element);
            element.maxTradeVol = MaxTradeVol.parse(container, element);
            element.minPriceIncrementPrice = MinPriceIncrementPrice.parse(container, element);
            element.displayFactorFloat = DisplayFactorFloat.parse(container, element);
            element.priceDisplayFormat = PriceDisplayFormat.parse(container, element);
            element.priceRatioPricenull = PriceRatioPricenull.parse(container, element);
            element.tickRule = TickRule.parse(container, element);
            element.unitOfMeasure = UnitOfMeasure.parse(container, element);
            element.tradingReferencePricePricenull = TradingReferencePricePricenull.parse(container, element);
            element.settlPriceType = SettlPriceType.parse(container, element);
            element.openInterestQty = OpenInterestQty.parse(container, element);
            element.clearedVolume = ClearedVolume.parse(container, element);
            element.highLimitPricePricenull = HighLimitPricePricenull.parse(container, element);
            element.lowLimitPricePricenull = LowLimitPricePricenull.parse(container, element);
            element.maxPriceVariationPricenull = MaxPriceVariationPricenull.parse(container, element);
            element.mainFraction = MainFraction.parse(container, element);
            element.subFraction = SubFraction.parse(container, element);
            element.tradingReferenceDate = TradingReferenceDate.parse(container, element);
            element.eventsGroups = EventsGroups.parse(container, element);
            element.mDFeedTypesGroups = MDFeedTypesGroups.parse(container, element);
            element.instAttribGroups = InstAttribGroups.parse(container, element);
            element.lotTypeRulesGroups = LotTypeRulesGroups.parse(container, element);
            element.mDInstrumentDefinitionSpreadLegGroups = MDInstrumentDefinitionSpreadLegGroups.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.matchEventIndicator).append("\n");
            sb.append(this.totNumReportsOptional).append("\n");
            sb.append(this.securityUpdateAction).append("\n");
            sb.append(this.lastUpdateTime).append("\n");
            sb.append(this.mdSecurityTradingStatus).append("\n");
            sb.append(this.applId).append("\n");
            sb.append(this.marketSegmentId).append("\n");
            sb.append(this.underlyingProductOptional).append("\n");
            sb.append(this.securityExchange).append("\n");
            sb.append(this.securityGroup).append("\n");
            sb.append(this.asset).append("\n");
            sb.append(this.symbol).append("\n");
            sb.append(this.securityId).append("\n");
            sb.append(this.securityType).append("\n");
            sb.append(this.cfiCode).append("\n");
            sb.append(this.maturityMonthYear).append("\n");
            sb.append(this.currency).append("\n");
            sb.append(this.securitySubType).append("\n");
            sb.append(this.userDefinedInstrument).append("\n");
            sb.append(this.matchAlgorithm).append("\n");
            sb.append(this.minTradeVol).append("\n");
            sb.append(this.maxTradeVol).append("\n");
            sb.append(this.minPriceIncrementPrice).append("\n");
            sb.append(this.displayFactorFloat).append("\n");
            sb.append(this.priceDisplayFormat).append("\n");
            sb.append(this.priceRatioPricenull).append("\n");
            sb.append(this.tickRule).append("\n");
            sb.append(this.unitOfMeasure).append("\n");
            sb.append(this.tradingReferencePricePricenull).append("\n");
            sb.append(this.settlPriceType).append("\n");
            sb.append(this.openInterestQty).append("\n");
            sb.append(this.clearedVolume).append("\n");
            sb.append(this.highLimitPricePricenull).append("\n");
            sb.append(this.lowLimitPricePricenull).append("\n");
            sb.append(this.maxPriceVariationPricenull).append("\n");
            sb.append(this.mainFraction).append("\n");
            sb.append(this.subFraction).append("\n");
            sb.append(this.tradingReferenceDate).append("\n");
            sb.append(this.eventsGroups).append("\n");
            sb.append(this.mDFeedTypesGroups).append("\n");
            sb.append(this.instAttribGroups).append("\n");
            sb.append(this.lotTypeRulesGroups).append("\n");
            sb.append(this.mDInstrumentDefinitionSpreadLegGroups).append("\n");
            return sb.toString();
        }
    }

    /**
    * Md Instrument Definition Future 27
    */
    public static class MdInstrumentDefinitionFuture27 implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public MatchEventIndicator matchEventIndicator;
        public TotNumReportsOptional totNumReportsOptional;
        public SecurityUpdateAction securityUpdateAction;
        public LastUpdateTime lastUpdateTime;
        public MdSecurityTradingStatus mdSecurityTradingStatus;
        public ApplId applId;
        public MarketSegmentId marketSegmentId;
        public UnderlyingProduct underlyingProduct;
        public SecurityExchange securityExchange;
        public SecurityGroup securityGroup;
        public Asset asset;
        public Symbol symbol;
        public SecurityId securityId;
        public SecurityType securityType;
        public CfiCode cfiCode;
        public MaturityMonthYear maturityMonthYear;
        public Currency currency;
        public SettlCurrency settlCurrency;
        public MatchAlgorithm matchAlgorithm;
        public MinTradeVol minTradeVol;
        public MaxTradeVol maxTradeVol;
        public MinPriceIncrementPrice minPriceIncrementPrice;
        public DisplayFactorFloat displayFactorFloat;
        public MainFraction mainFraction;
        public SubFraction subFraction;
        public PriceDisplayFormat priceDisplayFormat;
        public UnitOfMeasure unitOfMeasure;
        public UnitOfMeasureQtyPricenull unitOfMeasureQtyPricenull;
        public TradingReferencePricePricenull tradingReferencePricePricenull;
        public SettlPriceType settlPriceType;
        public OpenInterestQty openInterestQty;
        public ClearedVolume clearedVolume;
        public HighLimitPricePricenull highLimitPricePricenull;
        public LowLimitPricePricenull lowLimitPricePricenull;
        public MaxPriceVariationPricenull maxPriceVariationPricenull;
        public DecayQuantity decayQuantity;
        public DecayStartDate decayStartDate;
        public OriginalContractSize originalContractSize;
        public ContractMultiplier contractMultiplier;
        public ContractMultiplierUnit contractMultiplierUnit;
        public FlowScheduleType flowScheduleType;
        public MinPriceIncrementAmountPricenull minPriceIncrementAmountPricenull;
        public UserDefinedInstrument userDefinedInstrument;
        public TradingReferenceDate tradingReferenceDate;
        public EventsGroups eventsGroups;
        public MDFeedTypesGroups mDFeedTypesGroups;
        public InstAttribGroups instAttribGroups;
        public LotTypeRulesGroups lotTypeRulesGroups;

        // constructor for Md Instrument Definition Future 27
        private MdInstrumentDefinitionFuture27(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Md Instrument Definition Future 27
        public static MdInstrumentDefinitionFuture27 parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new MdInstrumentDefinitionFuture27(parentElement);

            element.matchEventIndicator = MatchEventIndicator.parse(container, element);
            element.totNumReportsOptional = TotNumReportsOptional.parse(container, element);
            element.securityUpdateAction = SecurityUpdateAction.parse(container, element);
            element.lastUpdateTime = LastUpdateTime.parse(container, element);
            element.mdSecurityTradingStatus = MdSecurityTradingStatus.parse(container, element);
            element.applId = ApplId.parse(container, element);
            element.marketSegmentId = MarketSegmentId.parse(container, element);
            element.underlyingProduct = UnderlyingProduct.parse(container, element);
            element.securityExchange = SecurityExchange.parse(container, element);
            element.securityGroup = SecurityGroup.parse(container, element);
            element.asset = Asset.parse(container, element);
            element.symbol = Symbol.parse(container, element);
            element.securityId = SecurityId.parse(container, element);
            element.securityType = SecurityType.parse(container, element);
            element.cfiCode = CfiCode.parse(container, element);
            element.maturityMonthYear = MaturityMonthYear.parse(container, element);
            element.currency = Currency.parse(container, element);
            element.settlCurrency = SettlCurrency.parse(container, element);
            element.matchAlgorithm = MatchAlgorithm.parse(container, element);
            element.minTradeVol = MinTradeVol.parse(container, element);
            element.maxTradeVol = MaxTradeVol.parse(container, element);
            element.minPriceIncrementPrice = MinPriceIncrementPrice.parse(container, element);
            element.displayFactorFloat = DisplayFactorFloat.parse(container, element);
            element.mainFraction = MainFraction.parse(container, element);
            element.subFraction = SubFraction.parse(container, element);
            element.priceDisplayFormat = PriceDisplayFormat.parse(container, element);
            element.unitOfMeasure = UnitOfMeasure.parse(container, element);
            element.unitOfMeasureQtyPricenull = UnitOfMeasureQtyPricenull.parse(container, element);
            element.tradingReferencePricePricenull = TradingReferencePricePricenull.parse(container, element);
            element.settlPriceType = SettlPriceType.parse(container, element);
            element.openInterestQty = OpenInterestQty.parse(container, element);
            element.clearedVolume = ClearedVolume.parse(container, element);
            element.highLimitPricePricenull = HighLimitPricePricenull.parse(container, element);
            element.lowLimitPricePricenull = LowLimitPricePricenull.parse(container, element);
            element.maxPriceVariationPricenull = MaxPriceVariationPricenull.parse(container, element);
            element.decayQuantity = DecayQuantity.parse(container, element);
            element.decayStartDate = DecayStartDate.parse(container, element);
            element.originalContractSize = OriginalContractSize.parse(container, element);
            element.contractMultiplier = ContractMultiplier.parse(container, element);
            element.contractMultiplierUnit = ContractMultiplierUnit.parse(container, element);
            element.flowScheduleType = FlowScheduleType.parse(container, element);
            element.minPriceIncrementAmountPricenull = MinPriceIncrementAmountPricenull.parse(container, element);
            element.userDefinedInstrument = UserDefinedInstrument.parse(container, element);
            element.tradingReferenceDate = TradingReferenceDate.parse(container, element);
            element.eventsGroups = EventsGroups.parse(container, element);
            element.mDFeedTypesGroups = MDFeedTypesGroups.parse(container, element);
            element.instAttribGroups = InstAttribGroups.parse(container, element);
            element.lotTypeRulesGroups = LotTypeRulesGroups.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.matchEventIndicator).append("\n");
            sb.append(this.totNumReportsOptional).append("\n");
            sb.append(this.securityUpdateAction).append("\n");
            sb.append(this.lastUpdateTime).append("\n");
            sb.append(this.mdSecurityTradingStatus).append("\n");
            sb.append(this.applId).append("\n");
            sb.append(this.marketSegmentId).append("\n");
            sb.append(this.underlyingProduct).append("\n");
            sb.append(this.securityExchange).append("\n");
            sb.append(this.securityGroup).append("\n");
            sb.append(this.asset).append("\n");
            sb.append(this.symbol).append("\n");
            sb.append(this.securityId).append("\n");
            sb.append(this.securityType).append("\n");
            sb.append(this.cfiCode).append("\n");
            sb.append(this.maturityMonthYear).append("\n");
            sb.append(this.currency).append("\n");
            sb.append(this.settlCurrency).append("\n");
            sb.append(this.matchAlgorithm).append("\n");
            sb.append(this.minTradeVol).append("\n");
            sb.append(this.maxTradeVol).append("\n");
            sb.append(this.minPriceIncrementPrice).append("\n");
            sb.append(this.displayFactorFloat).append("\n");
            sb.append(this.mainFraction).append("\n");
            sb.append(this.subFraction).append("\n");
            sb.append(this.priceDisplayFormat).append("\n");
            sb.append(this.unitOfMeasure).append("\n");
            sb.append(this.unitOfMeasureQtyPricenull).append("\n");
            sb.append(this.tradingReferencePricePricenull).append("\n");
            sb.append(this.settlPriceType).append("\n");
            sb.append(this.openInterestQty).append("\n");
            sb.append(this.clearedVolume).append("\n");
            sb.append(this.highLimitPricePricenull).append("\n");
            sb.append(this.lowLimitPricePricenull).append("\n");
            sb.append(this.maxPriceVariationPricenull).append("\n");
            sb.append(this.decayQuantity).append("\n");
            sb.append(this.decayStartDate).append("\n");
            sb.append(this.originalContractSize).append("\n");
            sb.append(this.contractMultiplier).append("\n");
            sb.append(this.contractMultiplierUnit).append("\n");
            sb.append(this.flowScheduleType).append("\n");
            sb.append(this.minPriceIncrementAmountPricenull).append("\n");
            sb.append(this.userDefinedInstrument).append("\n");
            sb.append(this.tradingReferenceDate).append("\n");
            sb.append(this.eventsGroups).append("\n");
            sb.append(this.mDFeedTypesGroups).append("\n");
            sb.append(this.instAttribGroups).append("\n");
            sb.append(this.lotTypeRulesGroups).append("\n");
            return sb.toString();
        }
    }

    // Text: 180 Byte Ascii String
    public static class Text implements IBinaryProtocolElement {
        public static final int LENGTH = 180;
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

    /**
    * Admin Logout 16
    */
    public static class AdminLogout16 implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public Text text;

        // constructor for Admin Logout 16
        private AdminLogout16(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Admin Logout 16
        public static AdminLogout16 parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new AdminLogout16(parentElement);

            element.text = Text.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.text).append("\n");
            return sb.toString();
        }
    }

    // Heart Bt Int: 1 Byte Signed Fixed Width Integer
    public static class HeartBtInt implements IBinaryProtocolElement {
        public static final int LENGTH = 1;
        public final IBinaryProtocolElement parent;
        public final Byte value;

        public HeartBtInt(Byte value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static HeartBtInt parse(Container container, IBinaryProtocolElement element) {
            return new HeartBtInt(LittleEndianUtils.toByte(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "heartBtInt = "+ this.value;
        }
    }

    /**
    * Admin Login 15
    */
    public static class AdminLogin15 implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public HeartBtInt heartBtInt;

        // constructor for Admin Login 15
        private AdminLogin15(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Admin Login 15
        public static AdminLogin15 parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new AdminLogin15(parentElement);

            element.heartBtInt = HeartBtInt.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.heartBtInt).append("\n");
            return sb.toString();
        }
    }

    /**
    * Channel Reset Group
    */
    public static class ChannelResetGroup implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public ApplId applId;

        // constructor for Channel Reset Group
        private ChannelResetGroup(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Channel Reset Group
        public static ChannelResetGroup parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new ChannelResetGroup(parentElement);

            element.applId = ApplId.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.applId).append("\n");
            return sb.toString();
        }
    }

    /**
    * Channel Reset Groups
    */
    public static class ChannelResetGroups implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public GroupSize groupSize;
        public List<ChannelResetGroup> channelResetGroupList = new ArrayList<>();

        // constructor for Channel Reset Groups
        private ChannelResetGroups(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Channel Reset Groups
        public static ChannelResetGroups parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new ChannelResetGroups(parentElement);

            element.groupSize = GroupSize.parse(container, element);

            // Channel Reset Group: Struct of 1 fields
            for (int i = 0; i < element.groupSize.numInGroup.value; i++) {
                element.channelResetGroupList.add(ChannelResetGroup.parse(container, element));
            }

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.groupSize).append("\n");
            for (var channelResetGroup: channelResetGroupList) {
                sb.append(channelResetGroup).append("\n");
            }
            return sb.toString();
        }
    }

    /**
    * Channel Reset 4
    */
    public static class ChannelReset4 implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public TransactTime transactTime;
        public MatchEventIndicator matchEventIndicator;
        public ChannelResetGroups channelResetGroups;

        // constructor for Channel Reset 4
        private ChannelReset4(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Channel Reset 4
        public static ChannelReset4 parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new ChannelReset4(parentElement);

            element.transactTime = TransactTime.parse(container, element);
            element.matchEventIndicator = MatchEventIndicator.parse(container, element);
            element.channelResetGroups = ChannelResetGroups.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.transactTime).append("\n");
            sb.append(this.matchEventIndicator).append("\n");
            sb.append(this.channelResetGroups).append("\n");
            return sb.toString();
        }
    }

    /**
    * Parse Payload
    */
    public static Payload parsePayload(Container container, ENUM_TEMPLATE_ID templateId) {
        
        // -- parsing Channel Reset 4
        if (templateId.enumType == 4) {
            return ChannelReset4.parse(container, null);
        }
        // -- parsing Admin Heartbeat 12
        if (templateId.enumType == 12) {
            /**
            * 0
            */
        }
        // -- parsing Admin Login 15
        if (templateId.enumType == 15) {
            return AdminLogin15.parse(container, null);
        }
        // -- parsing Admin Logout 16
        if (templateId.enumType == 16) {
            return AdminLogout16.parse(container, null);
        }
        // -- parsing Md Instrument Definition Future 27
        if (templateId.enumType == 27) {
            return MdInstrumentDefinitionFuture27.parse(container, null);
        }
        // -- parsing Md Instrument Definition Spread 29
        if (templateId.enumType == 29) {
            return MdInstrumentDefinitionSpread29.parse(container, null);
        }
        // -- parsing Security Status 30
        if (templateId.enumType == 30) {
            return SecurityStatus30.parse(container, null);
        }
        // -- parsing Md Incremental Refresh Book 32
        if (templateId.enumType == 32) {
            return MdIncrementalRefreshBook32.parse(container, null);
        }
        // -- parsing Md Incremental Refresh Daily Statistics 33
        if (templateId.enumType == 33) {
            return MdIncrementalRefreshDailyStatistics33.parse(container, null);
        }
        // -- parsing Md Incremental Refresh Limits Banding 34
        if (templateId.enumType == 34) {
            return MdIncrementalRefreshLimitsBanding34.parse(container, null);
        }
        // -- parsing Md Incremental Refresh Session Statistics 35
        if (templateId.enumType == 35) {
            return MdIncrementalRefreshSessionStatistics35.parse(container, null);
        }
        // -- parsing Md Incremental Refresh Volume 37
        if (templateId.enumType == 37) {
            return MdIncrementalRefreshVolume37.parse(container, null);
        }
        // -- parsing Snapshot Full Refresh 38
        if (templateId.enumType == 38) {
            return SnapshotFullRefresh38.parse(container, null);
        }
        // -- parsing Quote Request 39
        if (templateId.enumType == 39) {
            return QuoteRequest39.parse(container, null);
        }
        // -- parsing Md Instrument Definition Option 41
        if (templateId.enumType == 41) {
            return MdInstrumentDefinitionOption41.parse(container, null);
        }
        // -- parsing Md Incremental Refresh Trade Summary 42
        if (templateId.enumType == 42) {
            return MdIncrementalRefreshTradeSummary42.parse(container, null);
        }
        // -- parsing Md Incremental Refresh Order Book 43
        if (templateId.enumType == 43) {
            return MdIncrementalRefreshOrderBook43.parse(container, null);
        }
        // -- parsing Snapshot Full Refresh Order Book 44
        if (templateId.enumType == 44) {
            return SnapshotFullRefreshOrderBook44.parse(container, null);
        }
        // -- parsing Md Incremental Refresh Book 46
        if (templateId.enumType == 46) {
            return MdIncrementalRefreshBook46.parse(container, null);
        }
        // -- parsing Md Incremental Refresh Order Book 47
        if (templateId.enumType == 47) {
            return MdIncrementalRefreshOrderBook47.parse(container, null);
        }
        // -- parsing Md Incremental Refresh Trade Summary 48
        if (templateId.enumType == 48) {
            return MdIncrementalRefreshTradeSummary48.parse(container, null);
        }
        // -- parsing Md Incremental Refresh Daily Statistics 49
        if (templateId.enumType == 49) {
            return MdIncrementalRefreshDailyStatistics49.parse(container, null);
        }
        // -- parsing Md Incremental Refresh Limits Banding 50
        if (templateId.enumType == 50) {
            return MdIncrementalRefreshLimitsBanding50.parse(container, null);
        }
        // -- parsing Md Incremental Refresh Session Statistics 51
        if (templateId.enumType == 51) {
            return MdIncrementalRefreshSessionStatistics51.parse(container, null);
        }
        // -- parsing Snapshot Full Refresh 52
        if (templateId.enumType == 52) {
            return SnapshotFullRefresh52.parse(container, null);
        }
        // -- parsing Snapshot Full Refresh Order Book 53
        if (templateId.enumType == 53) {
            return SnapshotFullRefreshOrderBook53.parse(container, null);
        }
        // -- parsing Md Instrument Definition Future 54
        if (templateId.enumType == 54) {
            return MdInstrumentDefinitionFuture54.parse(container, null);
        }
        // -- parsing Md Instrument Definition Option 55
        if (templateId.enumType == 55) {
            return MdInstrumentDefinitionOption55.parse(container, null);
        }
        // -- parsing Md Instrument Definition Spread 56
        if (templateId.enumType == 56) {
            return MdInstrumentDefinitionSpread56.parse(container, null);
        }

        return null;
    }

    // Version: 2 Byte Unsigned Fixed Width Integer Static
    public static class Version implements IBinaryProtocolElement {
        public static final int LENGTH = 2;
        public final IBinaryProtocolElement parent;
        public final Short value;

        public Version(Short value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static Version parse(Container container, IBinaryProtocolElement element) {
            return new Version(LittleEndianUtils.toShort(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "version = "+ this.value;
        }
    }

    // Schema Id: 2 Byte Unsigned Fixed Width Integer Static
    public static class SchemaId implements IBinaryProtocolElement {
        public static final int LENGTH = 2;
        public final IBinaryProtocolElement parent;
        public final Short value;

        public SchemaId(Short value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static SchemaId parse(Container container, IBinaryProtocolElement element) {
            return new SchemaId(LittleEndianUtils.toShort(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "schemaId = "+ this.value;
        }
    }

    // Template Id: 2 Byte Unsigned Fixed Width Integer Enum with 29 values
    public static class TemplateId implements IBinaryProtocolElement {
        public static final int LENGTH = 2;
        public final IBinaryProtocolElement parent;
        public final ENUM_TEMPLATE_ID value;

        public TemplateId(ENUM_TEMPLATE_ID value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static TemplateId parse(Container container, IBinaryProtocolElement element) {
            return new TemplateId(ENUM_TEMPLATE_ID.valueOf((int)LittleEndianUtils.toShort(container, LENGTH)), element);
        }

        @Override
        public String toString() {
            return "templateId = "+ this.value.enumType +" ("+ this.value.enumValue +")";
        }
    }

    /**
    * Message Header
    */
    public static class MessageHeader implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public BlockLength blockLength;
        public TemplateId templateId;
        public SchemaId schemaId;
        public Version version;

        // constructor for Message Header
        private MessageHeader(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Message Header
        public static MessageHeader parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new MessageHeader(parentElement);

            element.blockLength = BlockLength.parse(container, element);
            element.templateId = TemplateId.parse(container, element);
            element.schemaId = SchemaId.parse(container, element);
            element.version = Version.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.blockLength).append("\n");
            sb.append(this.templateId).append("\n");
            sb.append(this.schemaId).append("\n");
            sb.append(this.version).append("\n");
            return sb.toString();
        }
    }

    // Message Size: 2 Byte Unsigned Fixed Width Integer
    public static class MessageSize implements IBinaryProtocolElement {
        public static final int LENGTH = 2;
        public final IBinaryProtocolElement parent;
        public final Short value;

        public MessageSize(Short value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static MessageSize parse(Container container, IBinaryProtocolElement element) {
            return new MessageSize(LittleEndianUtils.toShort(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "messageSize = "+ this.value;
        }
    }

    /**
    * Message
    */
    public static class Message implements IBinaryProtocolElement {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public MessageSize messageSize;
        public MessageHeader messageHeader;
        public Payload payload;

        // constructor for Message
        private Message(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Message
        public static Message parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new Message(parentElement);

            element.messageSize = MessageSize.parse(container, element);
            element.messageHeader = MessageHeader.parse(container, element);
            element.payload = parsePayload(container, element.messageHeader.templateId.value);

            return element;
        }

        public String toString() {
            return "messageSize=" + this.messageSize.toString() +"\n"
				+ "messageHeader=" + this.messageHeader.toString() +"\n"
				+ "payload = "+ this.payload.toString();
        }
    }

    // Sending Time: 8 Byte Unsigned Fixed Width Integer
    public static class SendingTime implements IBinaryProtocolElement {
        public static final int LENGTH = 8;
        public final IBinaryProtocolElement parent;
        public final Long value;

        public SendingTime(Long value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static SendingTime parse(Container container, IBinaryProtocolElement element) {
            return new SendingTime(LittleEndianUtils.toLong(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "sendingTime = "+ this.value;
        }
    }

    // Message Sequence Number: 4 Byte Unsigned Fixed Width Integer
    public static class MessageSequenceNumber implements IBinaryProtocolElement {
        public static final int LENGTH = 4;
        public final IBinaryProtocolElement parent;
        public final Integer value;

        public MessageSequenceNumber(Integer value, IBinaryProtocolElement element) {
            this.parent = element;
            this.value = value;
        }

        public static MessageSequenceNumber parse(Container container, IBinaryProtocolElement element) {
            return new MessageSequenceNumber(LittleEndianUtils.toInteger(container, LENGTH), element);
        }

        @Override
        public String toString() {
            return "messageSequenceNumber = "+ this.value;
        }
    }

    /**
    * Binary Packet Header
    */
    public static class BinaryPacketHeader implements Payload {

        // parent element
        public final IBinaryProtocolElement parent;

        // fields
        public MessageSequenceNumber messageSequenceNumber;
        public SendingTime sendingTime;

        // constructor for Binary Packet Header
        private BinaryPacketHeader(IBinaryProtocolElement element) {
            this.parent = element;
        }

        // parser for Binary Packet Header
        public static BinaryPacketHeader parse(Container container, IBinaryProtocolElement parentElement) {
            var element = new BinaryPacketHeader(parentElement);

            element.messageSequenceNumber = MessageSequenceNumber.parse(container, element);
            element.sendingTime = SendingTime.parse(container, element);

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.messageSequenceNumber).append("\n");
            sb.append(this.sendingTime).append("\n");
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
        public BinaryPacketHeader binaryPacketHeader;
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

            element.binaryPacketHeader = BinaryPacketHeader.parse(container, element);

            // Message: Struct of 3 fields
            while (!container.parsed()) {
                element.messageList.add(Message.parse(container, element));
            }

            return element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.binaryPacketHeader).append("\n");
            for (var message: messageList) {
                sb.append(message).append("\n");
            }
            return sb.toString();
        }
    }

}
