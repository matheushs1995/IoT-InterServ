package module2.model;

import java.util.ArrayList;
import java.util.List;

public class GenericModel7 {

    public String name;
    public List<List<String>> contextGroup;

    public GenericModel7(String n) {
        name = n;
    }

    public void setContextGroup(List<String> c) {
        if (contextGroup == null) {
            contextGroup = new ArrayList<>();
        }

        contextGroup.add(c);
    }

}
