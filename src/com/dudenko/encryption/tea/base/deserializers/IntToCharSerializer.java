package com.dudenko.encryption.tea.base.deserializers;

public class IntToCharSerializer {
    public byte[] deserialize(int[] data, int dataOffset, int dataLength) {
        assert dataLength <= (data.length - dataOffset) * 4;
        byte[] result = new byte[dataLength];
        int i = dataOffset;
        int count = 0;
        for (int j = 0; j < dataLength; j++) {
            result[j] = (byte) ((data[i] >> (24 - (8 * count))) & 0xff);
            count++;
            if (count == 4) {
                count = 0;
                i++;
            }
        }
        return result;
    }
}
