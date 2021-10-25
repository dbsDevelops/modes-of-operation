package es.usj.crypto.cipher;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Arrays;

/**
 * Sample code to demonstrate how Cipher Algorithm modes of operation work.
 *
 * Input BMP Image is encrypted with DES and AES using different modes of operation.
 */
public class ModesOfOperationApp {

    // Folder to write output BMP Images
    public static final String OUTPUT_FOLDER = "/Users/aborroy/Downloads/usj/tmp/";

    // BMP Format length of header expressed in bytes
    public static final Integer BMP_BYTE_HEADER_LENGTH = 54;

    // Input BMP file, loaded from "resources" folder
    static String fileNameInput = "logo-usj.bmp";
    static String fileNameInputNoExt = fileNameInput.substring(0, fileNameInput.indexOf("."));

    /**
     * Stores encrypted images for "fileNameInput" with different ciphers and different modes of operation
     */
    public static void main(String... args) throws Exception {

        // Any other transformation for encryption operation may be used from:
        // https://docs.oracle.com/en/java/javase/11/docs/specs/security/standard-names.html

        // DES cipher using ECB and CBC
        encrypt("DES/ECB/PKCS5Padding");
        encrypt("DES/CBC/PKCS5Padding");

        // AES cipher using ECB and CBC
        encrypt("AES/ECB/PKCS5Padding");
        encrypt("AES/CBC/PKCS5Padding");

    }

    /**
     * Creates a file applying the encryption operation specified
     * @param transformation Any valid String for Ciphers in https://docs.oracle.com/en/java/javase/11/docs/specs/security/standard-names.html
     */
    private static void encrypt(String transformation) throws Exception {

        // Create an instance for the Cipher
        Cipher cipher = Cipher.getInstance(transformation);
        SecretKey secret_key = KeyGenerator.getInstance(transformation.substring(0, 3)).generateKey();
        cipher.init(Cipher.ENCRYPT_MODE, secret_key);

        // Calculate fileName for the encrypted output file
        String fileNameOutput = getFileName(transformation);

        // Load input file, create output file and create output stream for Cipher
        try (InputStream fileIn = ModesOfOperationApp.class.getClassLoader().getResourceAsStream(fileNameInput);
             FileOutputStream fileOut = new FileOutputStream(OUTPUT_FOLDER + fileNameOutput);
             CipherOutputStream cipherOut = new CipherOutputStream(fileOut, cipher)) {

            // Skip BMP header, so it can be opened after encryption operation
            byte[] inputBmp = fileIn.readAllBytes();
            byte[] header = Arrays.copyOfRange(inputBmp, 0, BMP_BYTE_HEADER_LENGTH + 1);
            byte[] imgData = Arrays.copyOfRange(inputBmp, BMP_BYTE_HEADER_LENGTH, inputBmp.length);
            fileOut.write(header);
            fileOut.flush();

            // Cipher data bits
            cipherOut.write(imgData);

        }

    }

    /**
     * Include cipher and mode of operation in file name from "tranformation" string
     */
    private static String getFileName(String transformation) {
        return fileNameInputNoExt + "-" +
            transformation.substring(0, transformation.lastIndexOf("/")).replaceAll("/", "-") +
            ".bmp";
    }

}
