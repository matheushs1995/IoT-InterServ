package module2.model;

import java.util.ArrayList;
import java.util.List;

public class GenericModel4 {

    public String name;
    
    public List<String> superContexts;

    public GenericModel4(String n) {
        name = n;
    }
    
    public void setSuperContext(String sp){
        if(superContexts == null ){
            superContexts = new ArrayList<>();
        }
        
        superContexts.add(sp);
    }
    
    

}
