import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Adam on 2015-10-19.
 */
public class Operations {

    public static ArrayList<Integer>[] getCryptogramsArray(String filePath) {
        String textLine;
        ArrayList<String> cryptograms = new ArrayList<>();
        ArrayList<Integer>[] cryptogramsArray = null;
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            textLine = bufferedReader.readLine();
            while (textLine != null) {
                cryptograms.add(textLine);
                textLine = bufferedReader.readLine();
            }
            bufferedReader.close();

            /** Creating an array of arrayLists to keeping int values of every character in every datagrams **/
            cryptogramsArray = (ArrayList<Integer>[])new ArrayList[cryptograms.size()];

            /*************** Adding in loop to table converted values from binary to integer *****************/
            int index = 0;
            for (String s : cryptograms) {
                cryptogramsArray[index] = new ArrayList<>();
                String[] charactersArray = s.split(" ");
                for (String character : charactersArray) cryptogramsArray[index].add(binToInt(character));
                index++;
            }
        }
        catch (IOException e) {
            print("Error while reading from file " + filePath);
        }
        catch (Exception e) {
            print("Error while creating datagrams Array "+filePath+" "+e.toString());
        }
        return cryptogramsArray;
    }

    /******************   Method converts string of binaries to int value *******************/
    private static int binToInt(String s) {
        int index = 0, value = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '1') value += (int)Math.pow(2.0, (double)index);
            index++;
        }
        return value;
    }

    /********** Function returning array with every char that can be used in text **********/
    public static ArrayList<SingleChar> getPossibleChars() {
        ArrayList<SingleChar> possibleChars = new ArrayList<>();

        possibleChars.add(new SingleChar((int) 'a', 8.91));
        possibleChars.add(new SingleChar((int)'i', 8.21));
        possibleChars.add(new SingleChar((int)'o', 7.75));
        possibleChars.add(new SingleChar((int)'e', 7.66));
        possibleChars.add(new SingleChar((int)'z', 5.64));
        possibleChars.add(new SingleChar((int)'n', 5.52));
        possibleChars.add(new SingleChar((int)'r', 4.69));
        possibleChars.add(new SingleChar((int)'w', 4.65));
        possibleChars.add(new SingleChar((int)'s', 4.32));
        possibleChars.add(new SingleChar((int)'t', 3.98));
        possibleChars.add(new SingleChar((int)'c', 3.96));
        possibleChars.add(new SingleChar((int)'y', 3.76));
        possibleChars.add(new SingleChar((int)'k', 3.51));
        possibleChars.add(new SingleChar((int)'d', 3.25));
        possibleChars.add(new SingleChar((int)'p', 3.13));
        possibleChars.add(new SingleChar((int)'m', 2.8));
        possibleChars.add(new SingleChar((int)'u', 2.5));
        possibleChars.add(new SingleChar((int)'j', 2.28));
        possibleChars.add(new SingleChar((int)'l', 2.1));
        possibleChars.add(new SingleChar((int)'b', 1.47));
        possibleChars.add(new SingleChar((int)'g', 1.42));
        possibleChars.add(new SingleChar((int)'h', 1.08));
        possibleChars.add(new SingleChar((int)'f', 0.3));
        possibleChars.add(new SingleChar((int)'q', 0.14));
        possibleChars.add(new SingleChar((int)'v', 0.04));
        possibleChars.add(new SingleChar((int)'x', 0.02));
        possibleChars.add(new SingleChar((int) ' ', 10.0));
        possibleChars.add(new SingleChar((int) '!'));
        possibleChars.add(new SingleChar((int) '"'));
        possibleChars.add(new SingleChar((int) '.'));
        possibleChars.add(new SingleChar((int) ','));
        possibleChars.add(new SingleChar((int) ':'));
        possibleChars.add(new SingleChar((int) '-'));
        possibleChars.add(new SingleChar((int) ';'));
        possibleChars.add(new SingleChar((int) ')'));
        possibleChars.add(new SingleChar((int) '('));
        possibleChars.add(new SingleChar((int) '?'));
        possibleChars.add(new SingleChar((int) ';'));
        for (int i = 65; i <= 90; i++) possibleChars.add(new SingleChar(i)); //Duze litery
        for (int i = 48; i <= 57; i++) possibleChars.add(new SingleChar(i)); //liczby

        return possibleChars;
    }

    public static void print(String s) {
        System.out.println("Operations - "+s);
    }
}
