
package module2.model;

import module2.similarity.NaturalLanguageProcessor;

public class GenericModel9 {
    
    public String word1;
    public String word2;
    public double similarity;
    
    public double maxSimilarityPossible=0;
    public double minSimilarityPossible=2;
    
    public GenericModel9(String w1, String w2){
        word1 = w1;
        word2 = w2;
        similarity = NaturalLanguageProcessor.similarityCalculator(w1, w2);
    }
    
}
