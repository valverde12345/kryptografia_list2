import java.util.ArrayList;

/**
 * Created by Adam on 2015-10-19.
 */

public class StreamCipherDecryptor {
    private static final String cryptogramsFilePath = "datagrams.txt";
    private static ArrayList<Integer>[] cryptogramsArray;
    private static ArrayList<Integer> codeToDecrypt;
    private static ArrayList<SingleChar> possibleChars = new ArrayList<>();
    private static ArrayList<ProbablyKey> probablyKeys;
    private static int max, key[];

    public StreamCipherDecryptor() {
        cryptogramsArray = Operations.getCryptogramsArray(cryptogramsFilePath);
        codeToDecrypt = cryptogramsArray[cryptogramsArray.length - 1];
        max = codeToDecrypt.size();
        key = new int[codeToDecrypt.size()];

        /****************** Initialize every key value on -1 **********************/
        for (int i = 0; i < key.length; i++) key[i] = -1;

        /************* Getting every chars that can be used in text ***************/
        possibleChars = Operations.getPossibleChars();

        /************* In loop program looking for most probably keys *************/
        for (int i = 0; i < max; i++) {
            probablyKeys = new ArrayList<>();
            for (ArrayList<Integer> cryptogram : cryptogramsArray) {
                int value = cryptogram.get(i);
                for (SingleChar singleChar : possibleChars) {
                    int key = xor(value, singleChar.getCharacter());
                    ProbablyKey p = getProbablyKey(key);
                    if (p == null) {
                        p = new ProbablyKey(key);
                        p.addWeight(singleChar.getWeight());
                        probablyKeys.add(p);
                    }
                    else  p.addWeight(singleChar.getWeight());
                }
            }
            probablyKeys = Sort.sort(probablyKeys);
            key[i] = chooseBestKey(i);
        }
        printDecryted();
    }

    private ProbablyKey getProbablyKey(int key) {
        for (ProbablyKey probablyKey : probablyKeys) if (probablyKey.getKey() == key) return probablyKey;
        return null;
    }

    private int chooseBestKey(int i) {
        for (ProbablyKey p : probablyKeys) {
            int counter = 0;
            for (ArrayList<Integer> cryptogram : cryptogramsArray) {
                int encrypted = xor(cryptogram.get(i), p.getKey());
                if (!isCharacter(encrypted)) break;
                counter++;
            }
            if (counter > cryptogramsArray.length-1) return p.getKey();
        }
        return -1;
    }

    /******************************** Simple operations ***********************************/
    private int xor(int a, int b) {
        return a ^  b;
    }

    private Boolean isCharacter(int k) {
        for (SingleChar singleChar : possibleChars) if (singleChar.getCharacter() == k) return true;
        return false;
    }
    /****************************** End simple operations **********************************/


    /******************************** Printing functions ***********************************/
    private void printDecryted() {
        System.out.print("Decrypted message: ");
        for (int i = 0; i < max; i++) System.out.print((char)(codeToDecrypt.get(i)^key[i]));
        System.out.println();
    }
    /******************************** End printing functions ***********************************/

    public static void main(String[] args) {
        new StreamCipherDecryptor();
    }
}
