package com.dudenko.encryption.tea.base.dataconverters;

public class CharToIntConverter {
    public void convert(byte[] data, int[] result, int bufferOffset) {
        assert bufferOffset + (data.length / 4) <= result.length;

        int shift = 24;
        int j = bufferOffset;
        result[j] = 0;
        for (byte byteOfData : data) {
            result[j] |= ((byteOfData & 0xff) << shift);
            if (shift == 0) {
                shift = 24;
                j++;
                if (j < result.length) {
                    result[j] = 0;
                }
            } else {
                shift -= 8;
            }
        }
    }
}
