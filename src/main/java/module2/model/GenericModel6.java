
package module2.model;

import java.util.ArrayList;
import java.util.List;

public class GenericModel6 {
    public String category;
    public String  name;
    public List<GenericModel6> children;

    public GenericModel6(String c,String n){
        category = c;
        name = n;
    }
    
    public void setChild(GenericModel6 c){
        if(children == null){
            children = new ArrayList<>();
        }
        
        children.add(c);
    }
        
}
