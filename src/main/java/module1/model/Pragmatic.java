package module1.model;

import module1.ruleFunctions.FunctionType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

class Pragmatic {

    private static final List<Pragmatic> pragmatics = new ArrayList<>();

    private final String name;
    private int count;

    private final List<FunctionType> fts = new ArrayList<>();
    private final List<String> values = new ArrayList<>();
    private final List<String> topics = new ArrayList<>();

    public List<FunctionType> getFts() {
        return fts;
    }

    public List<String> getValues() {
        return values;
    }

    public List<String> getTopics() {
        return topics;
    }

    public Pragmatic(String n, String rs) throws OWLOntologyCreationException, InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
        name = n;
        setRule(rs);
        count++;
    }

    public static Pragmatic getPragmaticObject(String p, String rs) throws OWLOntologyCreationException, InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {

        if (p.compareTo("") == 0) {
            return null;
        }

        for (int i = 0; i < pragmatics.size(); i++) {
            if (pragmatics.get(i).name.compareTo(p) == 0) {
                pragmatics.get(i).count++;
                return pragmatics.get(i);
            }
        }

        Pragmatic prag = new Pragmatic(p, rs);
        pragmatics.add(prag);

        return prag;

    }

    private void setRule(String rs) throws OWLOntologyCreationException, InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
        List<String[]> rules = new ArrayList<>();

        String[] tempRs = rs.split("-");
        for (String r : tempRs) {
            rules.add(r.split("!"));
        }

        for (String[] rule : rules) {
            String[] temp = rule[0].split(",");

            try {
                Class c = Class.forName("module1.ruleFunctions.RuleManager");
                Method m = c.getMethod("get" + temp[0]);

                fts.add((FunctionType) m.invoke(this));
                String tempS = temp[1];
                if(temp.length==3){
                    tempS+=","+temp[2];
                }
                values.add(tempS);
                topics.add(rule[1]);

            } catch (ClassNotFoundException ex) {
                System.out.println("Class \"" + temp[0] + "\" does not exist");
                Logger.getLogger(Pragmatic.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void decreaseUseOfPragmatic() {
        if (count > 1) {
            count--;
        } else {
            pragmatics.remove(this);
        }
    }

}
