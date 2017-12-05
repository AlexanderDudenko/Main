package com.dudenko.encryption.tea;

import com.dudenko.encryption.tea.base.TEA;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class App {
    private static final String ENCRYPTION_KEY_FILE = "encryptionKey.txt";
    private static final String INPUT_DATA_FILE = "inputData.txt";
    private static final String ENCRYPTED_DATA_FILE = "encrypted.dat";
    private static final String DECRYPTED_DATA_FILE = "decrypted.txt";


    public static void main(String[] args) {
        try {
            test1();
            test2();
        } catch (IOException e) {
            System.err.println("Error: " + e);
        }
    }


    private static void test1() throws IOException {
        byte[] encryptedKey = readKeyFromFile();
        TEA tea = new TEA(encryptedKey);

        byte[] dataForEncrypt = readDataBlockFromFile(INPUT_DATA_FILE, 16);
        byte[] encryptedData = tea.encrypt(dataForEncrypt);

        writeDataToFile(encryptedData, ENCRYPTED_DATA_FILE);
    }


    private static void test2() throws IOException {
        byte[] encryptedKey = readKeyFromFile();
        TEA tea = new TEA(encryptedKey);

        byte[] dataForDecrypt = readDataBlockFromFile(ENCRYPTED_DATA_FILE);
        byte[] decryptedData = tea.decrypt(dataForDecrypt);

        writeDataToFile(decryptedData, DECRYPTED_DATA_FILE);
    }


    private static byte[] readKeyFromFile() {
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(ENCRYPTION_KEY_FILE);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Unable to open file " + ENCRYPTION_KEY_FILE, e);
        }

        byte[] encryptedKey = new byte[TEA.KEY_LENGTH];
        try {
            int read = fileInputStream.read(encryptedKey, 0, TEA.KEY_LENGTH);
            if (read == 0) {
                throw new RuntimeException("Encryption key data can't be null");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error during read file " + ENCRYPTION_KEY_FILE, e);
        }

        return encryptedKey;
    }


    private static byte[] readDataBlockFromFile(String fileName, int blockSize) throws IOException {
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Unable to open file " + fileName, e);
        }

        return readDataBlockFromFile(blockSize, fileInputStream);
    }


    private static byte[] readDataBlockFromFile(String fileName) throws IOException {
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Unable to open file " + fileName, e);
        }

        return readDataBlockFromFile(fileInputStream.available(), fileInputStream);
    }


    private static byte[] readDataBlockFromFile(int blockSize, FileInputStream fileInputStream) throws IOException {
        byte[] data = new byte[blockSize];
        try {
            int read = fileInputStream.read(data, 0, blockSize);

            if (read < blockSize) {
                throw new RuntimeException("Unable to read " + blockSize + " bytes of data. Specify file with correct data.");
            }
        } finally {
            fileInputStream.close();
        }
        return data;
    }


    private static void writeDataToFile(byte[] data, String fileName) {
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(fileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Unable to open file " + fileName, e);
        }

        try {
            fileOutputStream.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Unable to write data to file " + fileName, e);
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                System.err.println("Unable to close file output stream :" + e);
            }
        }
    }
}
