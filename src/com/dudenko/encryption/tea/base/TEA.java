package com.dudenko.encryption.tea.base;

public class TEA {
    public static final int KEY_LENGTH = 16;

    private int encryptionKeyParts[] = new int[4];


    public TEA(byte[] key) {
        if (key == null) {
            throw new RuntimeException("Invalid key: Key is null");
        }
        if (key.length < 16) {
            throw new RuntimeException("Invalid key: Key length must be less than 16 bytes");
        }

        // char to int conversion
        for (int off = 0, i = 0; i < 4; i++) {
            encryptionKeyParts[i] = ((key[off++] & 0xff)) |
                    ((key[off++] & 0xff) << 8) |
                    ((key[off++] & 0xff) << 16) |
                    ((key[off++] & 0xff) << 24);
        }
    }


    public byte[] decrypt(byte[] dataForDecrypt) {
        return new TEADecryptor(encryptionKeyParts).decrypt(dataForDecrypt);
    }


    public byte[] encrypt(byte[] dataForEncrypt) {
        return new TEAEncryptor(encryptionKeyParts).encrypt(dataForEncrypt);
    }
}
