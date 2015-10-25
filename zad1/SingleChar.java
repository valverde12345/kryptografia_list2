/**
 * Created by Adam on 2015-10-21.
 */
public class SingleChar {
    private int character;
    private double weight;

    public SingleChar(int character, double weight) {
        this.character = character;
        this.weight = weight;
    }

    public SingleChar(int character) {
        this.character = character;
        weight = 1.0;
    }

    public int getCharacter() {
        return character;
    }

    public double getWeight() {
        return weight;
    }
}