import java.util.ArrayList;

/**
 * Created by Adam on 2015-10-21.
 */
public class Sort {

    public static ArrayList<ProbablyKey> sort(ArrayList<ProbablyKey> f) {
        ProbablyKey key;
        int j;

        for (int i = 1; i < f.size(); i++){
            j = i;
            key = f.get(i);
            while (j > 0 && f.get(j-1).getWeight() < key.getWeight()){
                f.set(j, f.get(j-1));
                j--;
            }
            f.set(j,key);
        }
        return f;
    }
}
