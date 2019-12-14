package module2.similarity;

import hultig.sumo.Word;

public class NaturalLanguageProcessor {

    public static double getSilarityOneToMany(String word, String[] tempV) {

        double similarity = 2.0;
        double tempSimilarity;

        for (String v : tempV) {
            tempSimilarity = similarityCalculator(word, v);
            if (tempSimilarity < similarity) {
                similarity = tempSimilarity;
            }
        }
        return similarity;
    }
    
    public static double getSilarityManyToMany(String[] tempV1, String[] tempV2) {
        
        double similarity = 2.0;
        double tempSimilarity;

        for (String v : tempV1) {
            tempSimilarity = getSilarityOneToMany(v,tempV2);
            if (tempSimilarity < similarity) {
                similarity = tempSimilarity;
            }
        }
        return similarity;
        
    }

    public static double similarityCalculator(String w1, String w2) {

        double result;

        if (w1.split(" ").length > 1) {
            if (w2.split(" ").length > 1) {
                result = getCompoundSimilarity(w1, w2);
            } else {
                result = getSimilarity(w2, w1);
            }

        } else {
            if (w2.split(" ").length > 1) {
                result = getSimilarity(w1, w2);
            } else {
                result = stringSimilarityLexicalDistance(w1, w2);
            }
        }

        return result;

    }

    public static double getSimilarity(String w1, String w2) {

        w1 = w1.toLowerCase();
        w2 = w2.toLowerCase();

        String[] vW2 = w2.split(" ");
        double result = 2;
        double tempResult;

        for (String tw : vW2) {

            if (!tw.isBlank()) {
                tempResult = stringSimilarityLexicalDistance(w1, tw);
                if (tempResult < result) {
                    result = tempResult;
                }
            }
        }

        return result;
    }

    private static double getCompoundSimilarity(String w1, String w2) {

        String[] vW1 = w1.split(" ");

        double result = 0;
        int count = 0;

        for (String tw : vW1) {
            if (!tw.isBlank()) {
                result += getSimilarity(tw, w2);
                count++;
            }
        }

        return result / count;

    }

    public static float stringSimilarityLexicalDistance(String a, String b) {

        Word w1 = new Word(a);
        Word w2 = new Word(b);

        return w1.distlex(w2);

    }

}
