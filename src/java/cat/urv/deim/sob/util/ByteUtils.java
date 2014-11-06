/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.util;

import java.nio.ByteBuffer;

/**
 *
 * @author javigd
 */
public class ByteUtils {

    private static ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES * Config.TRIM_BYTEARRAY_SIZE);

    public static byte[] longToBytes(long x) {
        buffer.putLong(0, x);
        return buffer.array();
    }

    public static long bytesToLong(byte[] bytes) {
        buffer.put(bytes, 0, bytes.length);
        buffer.flip();//need flip 
        Long res = buffer.getLong();
        buffer.clear();
        return res;
    }

    public static byte[] trimByteArray(byte[] bytes, int maxSize) {
        byte[] trimmed = new byte[maxSize];
        int last = bytes.length - 1;
        System.arraycopy(bytes, last - maxSize, trimmed, 0, maxSize);
        return trimmed;
    }

    public static byte[] combine(byte[] firstSet, byte[] secondSet) {
        byte[] combined = new byte[firstSet.length + secondSet.length];

        System.arraycopy(firstSet, 0, combined, 0, firstSet.length);
        System.arraycopy(secondSet, 0, combined, firstSet.length, secondSet.length);

        return combined;
    }
}
