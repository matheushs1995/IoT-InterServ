
package module2.model;

import java.util.ArrayList;
import java.util.List;

public class GenericModel10 {
    public String name;
    public List <String> contexts;
    public List <String> rules;
    public String evaluation;

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }
    
    public GenericModel10(String t){
        name = t;
    }

    public void setContexts(String c){
        if (contexts == null) {
            contexts = new ArrayList<>();
        }

        contexts.add(c);
    }
    
    public void setRules(List<String> r){
        rules = r;
    }
    
}
