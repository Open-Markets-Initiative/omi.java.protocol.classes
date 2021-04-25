package com.omi.parser.util;

import com.omi.protocol.Container;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class BigEndianUtils extends ParserUtils {

    public static byte toByte(Container container, int length) {
        if (length == 1) {
            return container.getByte();
        } else {
            // TODO fix
            for (int i = 0; i < length; i++) {
                container.getByte();
            }
            return -1;
        }
    }

    public static short toShort(Container container, int size) {
        switch (size) {
            case 2:
                return from2Bytes(container);
            default:
                throw new IllegalArgumentException();
        }
    }

    public static long toLong(Container container, int size) {
        switch (size) {
            case 6:
                return from6Bytes(container);
            case 8:
                return from8Bytes(container);
            default:
                throw new IllegalArgumentException();
        }
    }

    public static int toInteger(Container container, int size) {
        switch (size) {
            case 4:
                return from4Bytes(container);
            default:
                throw new IllegalArgumentException();
        }
    }

    private static long from8Bytes(Container container) {
        return ((container.getByte() & 0xFFL) << 56) |
                ((container.getByte() & 0xFFL) << 48) |
                ((container.getByte() & 0xFFL) << 40) |
                ((container.getByte() & 0xFFL) << 32) |
                ((container.getByte() & 0xFFL) << 24) |
                ((container.getByte() & 0xFFL) << 16) |
                ((container.getByte() & 0xFFL) << 8) |
                ((container.getByte() & 0xFFL));
    }

    public static long from6Bytes(Container container) {
        return ((container.getByte() & 0xFFL) << 40) |
                ((container.getByte() & 0xFFL) << 32) |
                ((container.getByte() & 0xFFL) << 24) |
                ((container.getByte() & 0xFFL) << 16) |
                ((container.getByte() & 0xFFL) << 8) |
                ((container.getByte() & 0xFFL));
    }

    public static int from4Bytes(Container container) {
        return (((container.getByte() & 0xFF) << 24) |
                ((container.getByte() & 0xFF) << 16) |
                ((container.getByte() & 0xFF) << 8) |
                ((container.getByte() & 0xFF)));
    }

    public static short from2Bytes(Container container) {
        return (short) (((container.getByte() & 0xFF) << 8) |
                        ((container.getByte() & 0xFF)));
    }

    /*
    public static short from2Bytes(byte[] bytes, int i) {
        return (short) (((bytes[i] & 0xFF) << 8) |
                        ((bytes[i+1] & 0xFF)));
    }

    public static int from4Bytes(byte[] bytes, int offset) {
        return ((bytes[offset] & 0xFF) << 24) |
                ((bytes[offset+1] & 0xFF) << 16) |
                ((bytes[offset+2] & 0xFF) << 8) |
                ((bytes[offset+3] & 0xFF));
    }

    public static long from5Bytes(byte[] bytes, int offset) {
        return ((bytes[offset] & 0xFFL) << 32) |
                ((bytes[offset+1] & 0xFFL) << 24) |
                ((bytes[offset+2] & 0xFFL) << 16) |
                ((bytes[offset+3] & 0xFFL) << 8) |
                ((bytes[offset+4] & 0xFFL));
    }

    private static long from8Bytes(byte[] bytes, int offset) {
        return ((bytes[offset] & 0xFFL) << 56) |
                ((bytes[offset+1] & 0xFFL) << 48) |
                ((bytes[offset+2] & 0xFFL) << 40) |
                ((bytes[offset+3] & 0xFFL) << 32) |
                ((bytes[offset+4] & 0xFFL) << 24) |
                ((bytes[offset+5] & 0xFFL) << 16) |
                ((bytes[offset+6] & 0xFFL) << 8) |
                ((bytes[offset+7] & 0xFFL));
    }
     */

    public static char toCharacter(Container container, int length) {
        return BufferUtil.ParseByte(container.getByte());
    }

    public static String toString(byte[] bytes, int i, int length) {
        return new String(Arrays.copyOfRange(bytes, i, length+i), StandardCharsets.UTF_8);
    }

    public static String toString(Container container, int length) {
        return new String(Arrays.copyOfRange(container.getBytes(), container.getOffset(), container.incrementOffset(length)), StandardCharsets.UTF_8);
    }

    public static byte ignore(Container container, int size) {
        container.incrementOffset(size);
        return -1;
    }

/*
    public static Integer toByte(Container container, int messageTypeLength) {
        return Integer.valueOf(container.getByte());
    }
 */
}
