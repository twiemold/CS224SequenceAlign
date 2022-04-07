
import java.util.function.BiFunction;

public class Aligner {

    float delta;
    BiFunction <Character, Character, Float> alpha;
    int[][] alignmentArray;

    public Aligner(BiFunction<Character, Character, Float> comparator, float delta) {
        alpha = comparator;
        this.delta = delta;
    };

    public float align(String s1, String s2) {
        return 0;

    };
    private float opt(int i, int j) {
        return 0;

    };
    private String traceback(int i, int j){
        return "";
    };

}
