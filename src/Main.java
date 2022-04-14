/**
 * Thomas Wiemold
 * 4/12/2022
 * CS224
 * Programming Assignment #5
 * Sequence Alignment using Dynamic Programming
 * Main.java from Jason Hibbeler
 */

import java.util.function.BiFunction;

public class Main {
  public static void main(String[] argv) {
    testOne();
    testTwo();
  }

  public static void testOne() {
    System.out.println("\nBegin Test One:");
    String s1 = "mean";
    String s2 = "name";
    float delta = 2.0f;

    // define a BiFunction here that will return the following:
    // if c1 == c2, then zero
    // if c1 and c2 are both vowels, then 1.0 (vowels are a, e, i, o, u)
    // if c1 and c2 are both consonants, then 1.0 (if both are not vowels)
    // otherwise, 3.0

    BiFunction <Character, Character, Float> alpha = (c1, c2) -> {
      float alphaVal = 0.0f;
      if (c1 == c2) {
        return alphaVal;
      }
      char[] vowels = {'a', 'e', 'i', 'o', 'u'};
      boolean c1Vowel = false;
      boolean c2Vowel = false;
      for (char vowel : vowels) {
        if (c1 == vowel) {
          c1Vowel = true;
        }
        if (c2 == vowel) {
          c2Vowel = true;
        }
      }
      if ((c1Vowel && c2Vowel) || (!(c1Vowel) && !(c2Vowel))) {
        alphaVal = 1.0f;
      } else {
        alphaVal = 3.0f;
      }
      return alphaVal;
    };


    Aligner aligner = new Aligner(alpha, delta);
    float alignmentScore = aligner.align(s1, s2);
    System.out.printf("Final alignment score: %s\n", alignmentScore);
    // this should produce the following alignment:
    // n-ame
    // mean-
    // with cost = 6.0
  }

  public static void testTwo() {
    System.out.println("\nBegin Test Two:");
    String s1 = "nowisthedimeforallgoodmen";
    String s2 = "mowisthetimeallforgoodmen";
    float delta = 2.0f;

    // BiFunction <Character, Character, Float> alpha = (c1, c2)  -> c1 == c2 ? 0.0f : 3.0f;
    BiFunction <Character, Character, Float> alpha = (c1, c2) -> {
      float alphaVal = 0.0f;
      if (c1 == c2) {
        return alphaVal;
      }
      char[] vowels = {'a', 'e', 'i', 'o', 'u'};
      boolean c1Vowel = false;
      boolean c2Vowel = false;
      for (char vowel : vowels) {
        if (c1 == vowel) {
          c1Vowel = true;
        }
        if (c2 == vowel) {
          c2Vowel = true;
        }
      }
      if ((c1Vowel && c2Vowel) || (!(c1Vowel) && !(c2Vowel))) {
        alphaVal = 1.0f;
      } else {
        alphaVal = 3.0f;
      }
      return alphaVal;
    };

    Aligner aligner = new Aligner(alpha, delta);
    float alignmentScore = aligner.align(s1, s2);
    System.out.printf("Final alignment score: %s\n", alignmentScore);
    // this should give you the following alignment:
    // mowisthetime---allforgoodmen
    // nowisthedimeforall---goodmen
    // with cost = 18.0
  }

}
