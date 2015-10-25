/**
 * Created by Adam on 2015-10-22.
 */
public class ProbablyKey {
    private int key;
    private int amount = 0;
    private double weight = 0.0;

    public ProbablyKey(int key) {
        this.key = key;
        addAmount();
    }

    public int getKey() {
        return key;
    }

    public int getAmount() {
        return amount;
    }

    public void addAmount() {
        amount++;
    }

    public void addWeight(double weight) {
        this.weight += weight;
    }

    public double getWeight() {
        return weight;
    }
}
