
import java.util.function.BiFunction;

public class Aligner {

    final int arraySize = 50;

    float delta;
    BiFunction <Character, Character, Float> comparator;
    float[][] alignmentArray = new float[arraySize][arraySize];
    String s1;
    String s2;

    public Aligner(BiFunction<Character, Character, Float> comparator, float delta) {
        this.comparator = comparator;
        this.delta = delta;
    }

    public float align(String s1, String s2) {
        this.s1 = s1;
        this.s2 = s2;
        // m is length of s1
        // n is length of s2
        int i, j;
        // Initialize A[i][0] = i*delta for each i
        for (i = 0; i < s1.length()+1; ++i) {
            alignmentArray[i][0] = (i * delta);
        }
        // Initialize A[0][j] = j*delta for each j
        for (j = 0; j < s2.length()+1; ++j) {
            alignmentArray[0][j] = (j * delta);
        }
        // for each j = 1 to n
        for (j = 1; j < s2.length()+1; ++j) {
            // for each i = 1 to m
            for (i = 1; i < s1.length()+1; ++i) {
                // opt(i, j);
                opt(i, j);
            }
        }
        System.out.println(alignmentArray[s1.length()][s2.length()]);
        System.out.println(traceback(s1.length(), s2.length()));
        return alignmentArray[s1.length()][s1.length()];
    }
    private float opt(int i, int j) {
        if (alignmentArray[i][j] == 0 && i != 0 && j != 0) {
            // apply lambda function
            float alpha = comparator.apply(s1.charAt(i-1), s2.charAt(j-1));
            alignmentArray[i][j] = Math.min(Math.min(alpha + opt(i - 1, j - 1), delta + opt(i - 1,j)), delta + opt(i, j - 1));
        }
        return alignmentArray[i][j];
    }

    private String traceback(int i, int j) {
        String alignmentString = "";
        while (i > 0 && j > 0) {
            String lineString = "";
            float current = alignmentArray[i][j];
            float diagonal = alignmentArray[i-1][j-1];
            float horizontal = alignmentArray[i-1][j];
            float vertical = alignmentArray[i][j-1];
            float smallestVal = Math.min(diagonal,
                    Math.min(horizontal, vertical));
            if (current == diagonal) {
                i--;
                j--;
                lineString = String.format("%s aligns with %s\n", s1.charAt(i), s2.charAt(j));
            } else if (current == horizontal) {
                i--;
                lineString = String.format("%s aligns with -\n", s1.charAt(i));
            } else if (current == vertical) {
                j--;
                lineString = String.format("- aligns with %s\n", s2.charAt(j));
            } else {
                if (smallestVal == diagonal) {
                    i--;
                    j--;
                    lineString = String.format("%s aligns with %s\n", s1.charAt(i), s2.charAt(j));
                } else if (smallestVal == horizontal) {
                    i--;
                    lineString = String.format("%s aligns with -\n", s1.charAt(i));
                } else {
                    j--;
                    lineString = String.format("- aligns with %s\n", s2.charAt(j));
                }
            }
            alignmentString += lineString;
        }
        return alignmentString;
    }

}
