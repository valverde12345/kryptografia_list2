import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Adam on 2015-10-23.
 */
public class Operations {
    private static String messageAsString = "";

    public static String getMessage(String filePath) {
        String textLine;
        ArrayList<Integer> cryptogram = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            textLine = bufferedReader.readLine();
            bufferedReader.close();

            String[] charactersArray = textLine.split(" ");
            for (String character : charactersArray) cryptogram.add(binToInt(character));
            messageAsString = textLine;
        }
        catch (IOException e) {
            print("Error while reading from file " + filePath);
        }
        catch (Exception e) {
            print("Error while creating datagrams Array "+filePath+" "+e.toString());
        }
        return messageAsString;
    }

    public static String getKey(String filePath) {
        String textLine = "";
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            textLine = bufferedReader.readLine();
            bufferedReader.close();
        }
        catch (IOException e) {
            print("Error while reading from file " + filePath);
        }
        catch (Exception e) {
            print("Error while creating key Array "+filePath+" "+e.toString());
        }
        return textLine;
    }

    /********************* Function converts String of binaries to int value *********************/
    private static int binToInt(String s) {
        int index = 0, value = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '1') value += (int)Math.pow(2.0, (double)index);
            index++;
        }
        return value;
    }

    /************* Function returning array with every char that can be used in text *************/
    public static char[] getPossibleChars() {
        char[] possibleChars = new char[77];
        for (int i = 0; i <= 9; i++) possibleChars[i] = (char)(i + 48);  //Cyfry
        for (int i = 10; i <= 35; i++) possibleChars[i] = (char)(i + 87);  //Male litery
        for (int i = 36; i <= 61; i++) possibleChars[i] = (char)(i + 29); //Duze litery
        possibleChars[62] = ' ';
        possibleChars[63] = '.';
        possibleChars[64] = ',';
        possibleChars[65] = ':';
        possibleChars[66] = ';';
        possibleChars[67] = '?';
        possibleChars[68] = ')';
        possibleChars[69] = '(';
        possibleChars[70] = '!';
        possibleChars[71] = '@';
        possibleChars[72] = '"';
        possibleChars[73] = '\'';
        possibleChars[74] = '=';
        possibleChars[75] = '+';
        possibleChars[76] = '-';
        return possibleChars;
    }

    public static void print(String s) {
        System.out.println("Operations - "+s);
    }
}
