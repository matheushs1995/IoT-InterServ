package module2.model;

import java.util.ArrayList;
import java.util.List;

public class GenericModel5 {
    public String superContext;
    public List<GenericModel6> children;

    public GenericModel5(String n){
        superContext = n;
    }
    
    public void setChild(GenericModel6 c){
        if(children == null){
            children = new ArrayList<>();
        }
        
        children.add(c);
    }
    

            
}
