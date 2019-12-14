package module1.interoperability.model;

import module1.interoperability.ruleFunctions.FunctionType;
import module1.dao.IoTTopicsMoreContextDAO;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

class Pragmatic {

    private static List<Pragmatic> pragmatics = new ArrayList<>();

    private String name;
    private int count;

    private List<FunctionType> fts = new ArrayList<>();
    private List<String> values = new ArrayList<>();
    private List<String> topics = new ArrayList<>();

    public List<FunctionType> getFts() {
        return fts;
    }

    public List<String> getValues() {
        return values;
    }

    public List<String> getTopics() {
        return topics;
    }

    public Pragmatic(String n) throws OWLOntologyCreationException, InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
        name = n;
        setRule();
        count++;
    }

    public static Pragmatic getPragmaticObject(String p) throws OWLOntologyCreationException, InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {

        if (p.compareTo("") == 0) {
            return null;
        }

        for (int i = 0; i < pragmatics.size(); i++) {
            if (pragmatics.get(i).name.compareTo(p) == 0) {
                pragmatics.get(i).count++;
                return pragmatics.get(i);
            }
        }

        Pragmatic prag = new Pragmatic(p);
        pragmatics.add(prag);

        return prag;

    }

    private void setRule() throws OWLOntologyCreationException, InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
        List<String[]> rules = IoTTopicsMoreContextDAO.getRule(name);

        for (String[] rule : rules) {
            String[] temp = rule[0].split("-");

            try {
                Class c = Class.forName("module1.interoperability.manager.PrincipalManager");
                Method m = c.getMethod("get"+temp[0]+"()");
                
                fts.add((FunctionType) m.invoke(this));
                values.add(temp[1]);
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
