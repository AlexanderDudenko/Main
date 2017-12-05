package com.dudenko.encryption.tea.base;

import com.dudenko.encryption.tea.base.deserializers.IntToCharSerializer;
import com.dudenko.encryption.tea.base.serializers.CharToIntSerializer;

public class TEADecryptor {
    /* a key schedule constants */
    private final int DELTA = 0x9E3779B9;
    private final int DELTA2 = 0xC6EF3720;

    private int[] encryptionKeyParts;

    public TEADecryptor(int[] encryptionKeyParts) {
        this.encryptionKeyParts = encryptionKeyParts;
    }

    public byte[] decrypt(byte[] data) {
        assert data.length % 4 == 0;
        assert (data.length / 4) % 2 == 1;
        int[] buffer = new int[data.length / 4];

        new CharToIntSerializer().serialize(data, buffer, 0);
        decrypt(buffer);
        return new IntToCharSerializer().deserialize(buffer, 1, buffer[0]);
    }


    private void decrypt(int[] data) {
        assert data.length % 2 == 1;
        int v0, v1, sum;
        for (int i = 1; i < data.length; i += 2) {
            v0 = data[i];
            v1 = data[i + 1];
            sum = DELTA2;
            for (int j = 0; j < 32; ++j) {
                v1 -= ((v0 << 4) + encryptionKeyParts[2] ^ v0) + (sum ^ (v0 >>> 5)) + encryptionKeyParts[3];
                v0 -= ((v1 << 4) + encryptionKeyParts[0] ^ v1) + (sum ^ (v1 >>> 5)) + encryptionKeyParts[1];
                sum -= DELTA;
            }
            data[i] = v0;
            data[i + 1] = v1;
        }
    }
}
