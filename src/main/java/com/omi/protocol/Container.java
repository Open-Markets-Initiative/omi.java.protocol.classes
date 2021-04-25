package com.omi.protocol;

import com.omi.parser.util.BufferUtil;

public class Container {

    private byte[] bytes;
    private int offset;

    public Container(byte[] bytes) {
        this.bytes = bytes;
        this.offset = 0;
    }

    public byte[] getBytes() {
        return this.bytes;
    }

    public byte getCurrentByte() {
        return this.bytes[this.offset];
    }

    public int getOffset() {
        return this.offset;
    }

    public static Container initialize(byte[] bytes) {
        return new Container(bytes);
    }

    public byte getByte() {
        return this.bytes[this.offset++];
    }

    public int incrementOffset(int i) {
        this.offset += i;
        return this.offset;
    }

    public boolean parsed() {
        return this.offset >= this.bytes.length;
    }

    public void print(String comment) {
        System.out.println(comment +" => current offset="+ this.offset);
        //BufferUtil.printInHex(this.bytes);
    }
}
