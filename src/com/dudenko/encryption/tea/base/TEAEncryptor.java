package com.dudenko.encryption.tea.base;

import com.dudenko.encryption.tea.base.deserializers.IntToCharSerializer;
import com.dudenko.encryption.tea.base.serializers.CharToIntSerializer;

public class TEAEncryptor {
    /* a key schedule constant */
    private final int DELTA = 0x9E3779B9;

    private int encryptionKeyParts[];


    public TEAEncryptor(int[] encryptionKeyParts) {
        this.encryptionKeyParts = encryptionKeyParts;
    }


    public byte[] encrypt(byte[] data) {
        int paddedSize = ((data.length / 8) + (((data.length % 8) == 0) ? 0 : 1)) * 2;
        int[] buffer = new int[paddedSize + 1];
        buffer[0] = data.length;

        new CharToIntSerializer().serialize(data, buffer, 1);
        encrypt(buffer);
        return new IntToCharSerializer().deserialize(buffer, 0, buffer.length * 4);
    }


    private void encrypt(int[] buf) {
        assert buf.length % 2 == 1;
        int v0, v1, sum;

        for (int i = 1; i < buf.length; i += 2) {
            v0 = buf[i];
            v1 = buf[i + 1];
            sum = 0;
            for (int j = 0; j < 32; ++j) {
                sum += DELTA;
                v0 += ((v1 << 4) + encryptionKeyParts[0] ^ v1) + (sum ^ (v1 >>> 5)) + encryptionKeyParts[1];
                v1 += ((v0 << 4) + encryptionKeyParts[2] ^ v0) + (sum ^ (v0 >>> 5)) + encryptionKeyParts[3];
            }
            buf[i] = v0;
            buf[i + 1] = v1;
        }
    }
}
