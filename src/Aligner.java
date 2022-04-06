
import java.util.function.BiFunction;

public class Aligner {

    float delta;
    BiFunction<Character, Character, Float> alpha;

    public Aligner(BiFunction<Character, Character, Float> comparator, float delta) {
        alpha = comparator.apply(c1, c2);
        delta = this.delta;
    };

}
