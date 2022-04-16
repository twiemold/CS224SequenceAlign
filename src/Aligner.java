/**
 * Thomas Wiemold
 * 4/12/2022
 * CS224
 * Programming Assignment #5
 * Sequence Alignment using Dynamic Programming
 */

import java.util.function.BiFunction;

public class Aligner {
    String s1, s2;
    float delta;
    BiFunction <Character, Character, Float> comparator;
    float[][] alignmentArray;
    byte[][] choices;

    public Aligner(BiFunction<Character, Character, Float> comparator, float delta) {
        this.comparator = comparator;
        this.delta = delta;
    }

    public float align(String s1, String s2) {
        this.s1 = s1;
        this.s2 = s2;
        alignmentArray = new float[s1.length()+1][s2.length()+1];
        choices = new byte[s1.length()+1][s2.length()+1];
        int i, j;
        for (i = 0; i < s1.length()+1; ++i) {
            alignmentArray[i][0] = (i * delta);
        }
        for (j = 0; j < s2.length()+1; ++j) {
            alignmentArray[0][j] = (j * delta);
        }
        for (j = 1; j < s2.length()+1; ++j) {
            for (i = 1; i < s1.length()+1; ++i) {
                opt(i, j);
            }
        }
        System.out.println(traceback(s1.length(), s2.length()));
        return alignmentArray[s1.length()][s1.length()];
    }

    private float opt(int i, int j) {
        if (alignmentArray[i][j] == 0 && i != 0 && j != 0) {
            float alpha = comparator.apply(s1.charAt(i-1), s2.charAt(j-1));
            float first = delta + opt(i - 1,j);
            float second = alpha + opt(i - 1, j - 1);
            float third = delta + opt(i, j - 1);
            float minVal = Math.min(Math.min(second, first), third);
            if (minVal == third) {
                choices[i][j] = 3;
            } else if (minVal == second) {
                choices[i][j] = 2;
            } else {
                choices[i][j] = 1;
            }
            alignmentArray[i][j] = minVal;
        }
        return alignmentArray[i][j];
    }

    private String traceback(int i, int j) {
        StringBuilder alignmentString = new StringBuilder();
        StringBuilder iString = new StringBuilder();
        StringBuilder jString = new StringBuilder();
        while (i > 0 && j > 0) {
            byte choice = choices[i][j];
            if (choice == 3) {
                j--;
                iString.append("-");
                jString.append(s2.charAt(j));
            } else if (choice == 2) {
                i--;
                j--;
                iString.append(s1.charAt(i));
                jString.append(s2.charAt(j));
            }  else {
                i--;
                iString.append(s1.charAt(i));
                jString.append("-");
            }
        }
        iString.reverse();
        jString.reverse();
        alignmentString.append(jString);
        alignmentString.append("\n");
        alignmentString.append(iString);
        return alignmentString.toString();
    }
}
