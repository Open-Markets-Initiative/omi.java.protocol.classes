package com.omi.parser.util;

import com.omi.protocol.Container;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class LittleEndianUtils extends ParserUtils {

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

    public static int toInteger(Container container, int size) {
        switch (size) {
            case 4:
                return from4Bytes(container);
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

    private static long from6Bytes(Container container) {
        return (((container.getByte() & 0xFFL) << 0) |
                ((container.getByte() & 0xFFL) << 8) |
                ((container.getByte() & 0xFFL) << 16) |
                ((container.getByte() & 0xFFL) << 24) |
                ((container.getByte() & 0xFFL) << 32) |
                ((container.getByte() & 0xFFL) << 40));
    }

    public static char toCharacter(Container container, int length) {
        return BufferUtil.ParseByte(container.getByte());
    }

    public static String toString(Container container, int length) {
        return new String(Arrays.copyOfRange(container.getBytes(), container.getOffset(), container.incrementOffset(length)), StandardCharsets.UTF_8);
    }

    public static short from2Bytes(Container container) {
        return (short) (((container.getByte() & 0xFF) << 0) |
                ((container.getByte() & 0xFF) << 8));
    }

    private static long from8Bytes(Container container) {
        return (((container.getByte() & 0xFFL) << 0) |
                ((container.getByte() & 0xFFL) << 8) |
                ((container.getByte() & 0xFFL) << 16) |
                ((container.getByte() & 0xFFL) << 24) |
                ((container.getByte() & 0xFFL) << 32) |
                ((container.getByte() & 0xFFL) << 40) |
                ((container.getByte() & 0xFFL) << 48) |
                ((container.getByte() & 0xFFL) << 56));
    }

    public static int from4Bytes(Container container) {
        return ( ((container.getByte() & 0xFF) << 0) |
                ((container.getByte() & 0xFF) << 8) |
                ((container.getByte() & 0xFF) << 16) |
                ((container.getByte() & 0xFF) << 24) );
    }

    public static byte ignore(Container container, int size) {
        container.incrementOffset(size);
        return -1;
    }
}
