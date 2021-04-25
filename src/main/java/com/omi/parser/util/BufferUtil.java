package com.omi.parser.util;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.function.Function;

public class BufferUtil {

    public static void printInHex(byte[] bytes) {
        int i = 0;
        for (Byte b : bytes) {
            //if (i%16 == 0) { System.out.println(); }
            if (i%8 == 0) { System.out.println(); };

            System.out.printf("0x%02X ", b);
            i++;
        }
        System.out.println();
    }

    public static void bufferInfo(ByteBuffer buffer) {
        //printInHex(buffer.array());
        System.out.println("buffer position="+ buffer.position());
        System.out.println("buffer limit="+ buffer.limit());
    }

    Function<ByteBuffer, String> bufferToString = buffer -> {
        Charset utf8 = StandardCharsets.UTF_8;
        return String.valueOf(utf8.decode(buffer));
    };

    public static char ParseByte(byte b) {
        return (char) (b & 0xFF);
    }

    public static Function<ByteBuffer, Byte> bufferToByte = buffer -> buffer.get();
    public static Function<ByteBuffer, Integer> bufferToInteger = buffer -> buffer.getInt();
    public static Function<ByteBuffer, Short> bufferToShort = buffer -> buffer.getShort();
    public static Function<ByteBuffer, Long> getLong = buffer -> buffer.getLong();

    // this needs to be modified to specify length
    public static Function<ByteBuffer, String> getSessionToString = (ByteBuffer buffer) -> {
        Charset utf8 = StandardCharsets.UTF_8;
        return String.valueOf(utf8.decode(buffer));
    };

}
