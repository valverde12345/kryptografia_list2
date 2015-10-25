import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.SecureRandom;

/**
 * Created by Adam on 2015-10-23.
 */
public class RC4Decryptor {
    private static final String cryptogramPath = "cryptogram.txt";
    private static final String keyPath = "key.txt";
    private static String key;
    private static byte[] messageAsBytes;
    private static char[] possibleCharacters;

    public RC4Decryptor() {
        key = Operations.getKey(keyPath);
        possibleCharacters = Operations.getPossibleChars();
        messageAsBytes = Operations.getMessage(cryptogramPath).getBytes();

        int possibilities = 16;
        int[] counters = new int[8];
        for (counters[0] = 0; counters[0] < possibilities; counters[0]++) {
            for (counters[1] = 0; counters[1] < possibilities; counters[1]++) {
                for (counters[2] = 0; counters[2] < possibilities; counters[2]++) {
                    for (counters[3] = 0; counters[3] < possibilities; counters[3]++) {
                        for (counters[4] = 0; counters[4] < possibilities; counters[4]++) {
                            for (counters[5] = 0; counters[5] < possibilities; counters[5]++) {
                                for (counters[6] = 0; counters[6] < possibilities; counters[6]++) {
                                    for (counters[7] = 0; counters[7] < possibilities; counters[7]++) {
                                        try {
                                            String generatedKey = getGeneratedKey(counters)+key;
                                            if (checkIfDecryptedMessageIsCorrect(decrypt(generatedKey.getBytes()))) {
                                                print("Decrypted message: "+decrypt(generatedKey.getBytes()));
                                                return;
                                            }
                                        }
                                        catch (Exception e) {}
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /*************** Function generates key depending on values on counters ******************/
    private String getGeneratedKey(int[] counters) {
        String generatedKey = "";
        for (int value : counters) generatedKey += possibleCharacters[value];
        return generatedKey;
    }

    /****************** Function decrypts message and returning it as String *****************/
    private String decrypt(byte[] keyInByte) throws Exception {
        SecureRandom secureRandom = new SecureRandom(keyInByte);
        KeyGenerator keyGenerator = KeyGenerator.getInstance("RC4");
        keyGenerator.init(secureRandom);
        SecretKey secretKey = keyGenerator.generateKey();

        Cipher cipher = Cipher.getInstance("RC4");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decrypted = cipher.doFinal(messageAsBytes);
        return decrypted.toString();
    }

    /********** Function checks if every character of message can be used in text ************/
    private Boolean checkIfDecryptedMessageIsCorrect(String descryptedMessage) {
        for (int i = 0; i < descryptedMessage.length(); i++) {
            if (!isCorrectChar(descryptedMessage.charAt(i))) return false;
        }
        return true;
    }

    /******************  Function checks if character can be used in text  *******************/
    private Boolean isCorrectChar(char c) {
        for (int i = 0; i < possibleCharacters.length; i++) if (possibleCharacters[i] == c) return true;
        return false;
    }

    private static void print(String s) {
        System.out.println(s);
    }

    public static void main(String args[]) {
        new RC4Decryptor();
    }
}
