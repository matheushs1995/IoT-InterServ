package module2.dao;

import java.io.File;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.OWLObjectRenderer;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.reasoner.SimpleConfiguration;
import org.semanticweb.owlapi.model.*;

import com.clarkparsia.pellet.owlapiv3.PelletReasonerFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.semanticweb.owlapi.util.OWLEntityRemover;
import uk.ac.manchester.cs.owlapi.dlsyntax.DLSyntaxObjectRenderer;

public class IoTDescriptionDAO {

    static File file = new File("C:/Users/mathe/Documents/Dropbox/Projetos/PragmaticIoT-V2/files/IoTDescription.owl");
    static String uri = "http://www.semanticweb.org/mathe/ontologies/2019/4/IoTDescription#";
    static long lastModification = 0;
    static OWLOntologyManager manager;
    static OWLOntology ontology;
    static OWLReasonerFactory reasonerFactory;
    static OWLObjectRenderer renderer;
    static OWLReasoner reasoner;
    static OWLDataFactory factory;

    private static void prepareAcess() throws OWLOntologyCreationException {
        if (lastModification != file.lastModified()) {
            prepareOWLApi();
        }
    }

    public static boolean tmcExist(String tmc) throws OWLOntologyCreationException {

        prepareAcess();

        OWLNamedIndividual ind = factory.getOWLNamedIndividual(IRI.create(uri + tmc));
        OWLObjectProperty p = factory.getOWLObjectProperty(IRI.create(uri + "isTopicOf"));

        if (reasoner.getObjectPropertyValues(ind, p).getFlattened().iterator().hasNext()) {
            return true;
        } else {
            return false;
        }

    }

    private static void prepareOWLApi() throws OWLOntologyCreationException {
        manager = OWLManager.createOWLOntologyManager();
        ontology = manager.loadOntologyFromOntologyDocument(file);
        reasonerFactory = PelletReasonerFactory.getInstance();
        renderer = new DLSyntaxObjectRenderer();
        reasoner = reasonerFactory.createReasoner(ontology, new SimpleConfiguration());
        factory = manager.getOWLDataFactory();
        lastModification = file.lastModified();
    }

    public static List<String> getTMCOfIoT(String iot, String type) throws OWLOntologyCreationException {

        prepareAcess();

        OWLNamedIndividual ind = factory.getOWLNamedIndividual(IRI.create(uri + iot));
        OWLObjectProperty p = factory.getOWLObjectProperty(IRI.create(uri + "hasTopicMoreContext"));

        String temp = renderer.render(reasoner.getObjectPropertyValues(ind, p).getFlattened().iterator().next());

        if (type.compareTo("c") == 0) {
            return IoTTopicsMoreContextDAO.getAllConsumer(temp);
        }
        if (type.compareTo("p") == 0) {
            return IoTTopicsMoreContextDAO.getAllProducer(temp);
        }

        return null;

    }

    public static List<String> getAllUnitsByIoT(String iot) throws OWLOntologyCreationException {

        prepareAcess();

        List<String> units = new ArrayList<>();

        OWLNamedIndividual ind = factory.getOWLNamedIndividual(IRI.create(uri + iot));
        OWLObjectProperty p = factory.getOWLObjectProperty(IRI.create(uri + "hasTopicMoreContext"));

        String temp = renderer.render(reasoner.getObjectPropertyValues(ind, p).getFlattened().iterator().next());

        List<String> tmcs = IoTTopicsMoreContextDAO.getAllSpecificTopicThatHaveSameGT(temp);

        p = factory.getOWLObjectProperty(IRI.create(uri + "hasUnitTopic"));
        for (String s : tmcs) {
            ind = factory.getOWLNamedIndividual(IRI.create(uri + s));
            temp = renderer.render(reasoner.getObjectPropertyValues(ind, p).getFlattened().iterator().next());

            int test = 0;
            for (String u : units) {
                if (u.compareTo(temp) == 0) {
                    test = 1;
                    break;
                }
            }

            if (test == 0) {
                units.add(temp);
            }

        }

        return units;
    }

    public static List<String> getAllUnitsByTMC(String tmc) throws OWLOntologyCreationException {

        prepareAcess();

        List<String> units = new ArrayList<>();

        OWLNamedIndividual ind;
        OWLObjectProperty p = factory.getOWLObjectProperty(IRI.create(uri + "hasUnitTopic"));
        List<String> tmcs = IoTTopicsMoreContextDAO.getAllSpecificTopicThatHaveSameGT(tmc);

        for (String s : tmcs) {
            ind = factory.getOWLNamedIndividual(IRI.create(uri + s));

            if (reasoner.getObjectPropertyValues(ind, p).getFlattened().iterator().hasNext()) {
                String temp = renderer.render(reasoner.getObjectPropertyValues(ind, p).getFlattened().iterator().next());
                int test = 0;
                for (String u : units) {
                    if (u.compareTo(temp) == 0) {
                        test = 1;
                        break;
                    }
                }

                if (test == 0) {
                    units.add(temp);
                }
            }

        }

        return units;
    }

    public static List<String> getAllUnits() throws OWLOntologyCreationException {

        prepareAcess();

        List<String> units = new ArrayList<>();

        OWLClass unitClass = factory.getOWLClass(IRI.create(uri + "Unit"));

        for (OWLNamedIndividual topicInd : reasoner.getInstances(unitClass, false).getFlattened()) {
            units.add(renderer.render(topicInd));
        }

        return units;
    }

    public static List<String> getAllIoTs() throws OWLOntologyCreationException {
        prepareAcess();

        List<String> iots = new ArrayList<>();

        OWLClass iotClass = factory.getOWLClass(IRI.create(uri + "IoT"));

        for (OWLNamedIndividual topicInd : reasoner.getInstances(iotClass, false).getFlattened()) {
            iots.add(renderer.render(topicInd));
        }

        return iots;
    }

    public static String save(String[] values) throws OWLOntologyCreationException, OWLOntologyStorageException {

        String unitSource = "", unit = "", function = "";
        if (values.length != 4) {
            if (values.length != 6) {
                return "";
            } else {
                if (values[3].isEmpty()) {
                    return "";
                }
                if (values[4].isEmpty()) {
                    return "";
                }
                if (values[5].isEmpty()) {
                    return "";
                }
                unitSource = values[3];

                function = values[4];
                
                if(!verifyFunction(function)) return "";

                unit = values[5];
            }
        } else {
            if (values[3].isEmpty()) {
                return "";
            }
            unit = values[3];
        }

        if (values[0].compareTo("") == 0) {
            return "";
        }
        if (values[1].compareTo("") == 0) {
            return "";
        }
        if (values[2].compareTo("") == 0) {
            return "";
        }

        String IoTName = values[0];
        String desc = values[1];
        String topic = values[2];

        if ((unitSource.compareTo("") == 0 && function.compareTo("") != 0) || (unitSource.compareTo("") != 0 && function.compareTo("") == 0)) {
            return "";
        }

        if (!IoTTopicsMoreContextDAO.tmcExist(topic)) {
            return "";
        }

        OWLClass class1 = factory.getOWLClass(IRI.create(uri + "IoT"));;
        OWLObjectProperty p1;
        String iot;
        OWLNamedIndividual ind1;
        AddAxiom addAxiom;
        OWLAxiom axiom1;
        String id = "";
        int count;
        boolean iotIsNew = false;

        if (IoTName.compareTo("New IoT") == 0) {
            iotIsNew = true;

            count = 1;
            for (OWLNamedIndividual topicInd : reasoner.getInstances(class1, false).getFlattened()) {
                count++;
            }

            iot = "IoT" + count;

            ind1 = factory.getOWLNamedIndividual(IRI.create(uri + iot));

            axiom1 = factory.getOWLClassAssertionAxiom(class1, ind1);
            addAxiom = new AddAxiom(ontology, axiom1);
            manager.applyChange(addAxiom);

        } else {
            if (!iotExists(IoTName)) {
                return "";
            }
            iot = IoTName;
            ind1 = factory.getOWLNamedIndividual(IRI.create(uri + iot));
            p1 = factory.getOWLObjectProperty(IRI.create(uri + "hasIdentification"));

            id = renderer.render(reasoner.getObjectPropertyValues(ind1, p1).getFlattened().iterator().next());

            if (unitSource.isEmpty()) {
                p1 = factory.getOWLObjectProperty(IRI.create(uri + "hasUnit"));
                String temp = renderer.render(reasoner.getObjectPropertyValues(ind1, p1).getFlattened().iterator().next());

                if (temp.compareTo(unit) == 0) {
                    p1 = factory.getOWLObjectProperty(IRI.create(uri + "hasTopicMoreContext"));
                    temp = renderer.render(reasoner.getObjectPropertyValues(ind1, p1).getFlattened().iterator().next());

                    if (temp.compareTo(topic) == 0) {
                        return "IoT Name: " + iot + " \n ID: " + id;
                    }

                }
            }

            OWLEntityRemover r = new OWLEntityRemover(manager, Collections.singleton(ontology));
            ind1.accept(r);
            manager.applyChanges(r.getChanges());
            reasoner.flush();
            r.reset();

        }

        OWLNamedIndividual ind2, ind3;
        OWLObjectPropertyAssertionAxiom axiom;

        p1 = factory.getOWLObjectProperty(IRI.create(uri + "hasTopicMoreContext"));

        ind2 = factory.getOWLNamedIndividual(IRI.create(uri + topic));
        axiom = factory.getOWLObjectPropertyAssertionAxiom(p1, ind1, ind2);
        addAxiom = new AddAxiom(ontology, axiom);
        manager.applyChange(addAxiom);

        String genericTopic = IoTTopicsMoreContextDAO.getGenericTopic(topic);
        ind3 = factory.getOWLNamedIndividual(IRI.create(uri + genericTopic));
        p1 = factory.getOWLObjectProperty(IRI.create(uri + "hasGenericTopic"));

        axiom = factory.getOWLObjectPropertyAssertionAxiom(p1, ind2, ind3);
        addAxiom = new AddAxiom(ontology, axiom);
        manager.applyChange(addAxiom);

        if (!unitSource.isEmpty()) {
            ind2 = factory.getOWLNamedIndividual(IRI.create(uri + unitSource));
        } else {
            ind2 = factory.getOWLNamedIndividual(IRI.create(uri + unit));
        }

        p1 = factory.getOWLObjectProperty(IRI.create(uri + "thisGTHasOrHadAnUnit"));
        axiom = factory.getOWLObjectPropertyAssertionAxiom(p1, ind3, ind2);
        addAxiom = new AddAxiom(ontology, axiom);
        manager.applyChange(addAxiom);

        if (!IoTDescriptionDAO.tmcExist(topic)) {
            if (IoTTopicsMoreContextDAO.isProducer(topic)) {
                setProducer(topic);
            } else {
                setConsumer(topic);
            }
        }

        if (!unitSource.isEmpty()) {

            class1 = factory.getOWLClass(IRI.create(uri + "Unit"));

            ind2 = factory.getOWLNamedIndividual(IRI.create(uri + unitSource));

            axiom1 = factory.getOWLClassAssertionAxiom(class1, ind2);
            addAxiom = new AddAxiom(ontology, axiom1);
            manager.applyChange(addAxiom);

            ind2 = factory.getOWLNamedIndividual(IRI.create(uri + genericTopic));

            p1 = factory.getOWLObjectProperty(IRI.create(uri + "thisGTHasOrHadAnUnit"));
            OWLObjectProperty p2 = factory.getOWLObjectProperty(IRI.create(uri + "hasTargetUnit"));
            OWLDataProperty d = factory.getOWLDataProperty(IRI.create(uri + "hasFunction"));

            for (OWLNamedIndividual unitInd : reasoner.getObjectPropertyValues(ind2, p1).getFlattened()) {
                String unitT = renderer.render(unitInd);
                if (unitT.compareTo(unit) != 0) {
                    String tFunction = getFuntion(unit, unitT);
                    if(tFunction.isEmpty()) return "";
                    
                    tFunction = function +","+tFunction;
                    createUnitConvert(unitSource, unitT, tFunction);
                
                    tFunction = getInverseFunction(tFunction);
                    createUnitConvert(unitT, unitSource, tFunction);
                }

            }

            createUnitConvert(unitSource, unit, function);
            
            function = getInverseFunction(function);
            createUnitConvert(unit, unitSource, function);
            
            ind2 = factory.getOWLNamedIndividual(IRI.create(uri + unitSource));

        } else {
            if (!iotIsNew && !isUnit(unit)) {
                return "";
            }

            ind2 = factory.getOWLNamedIndividual(IRI.create(uri + unit));
        }

        p1 = factory.getOWLObjectProperty(IRI.create(uri + "hasUnit"));
        OWLObjectPropertyAssertionAxiom axiom3 = factory.getOWLObjectPropertyAssertionAxiom(p1, ind1, ind2);
        addAxiom = new AddAxiom(ontology, axiom3);

        manager.applyChange(addAxiom);

        class1 = factory.getOWLClass(IRI.create(uri + "Identification"));

        if (id.isEmpty()) {
            count = 1;
            for (OWLNamedIndividual topicInd : reasoner.getInstances(class1, false).getFlattened()) {
                count++;
            }

            id = "ID" + count;

        }

        ind2 = factory.getOWLNamedIndividual(IRI.create(uri + id));

        axiom1 = factory.getOWLClassAssertionAxiom(class1, ind2);
        addAxiom = new AddAxiom(ontology, axiom1);

        manager.applyChange(addAxiom);

        p1 = factory.getOWLObjectProperty(IRI.create(uri + "hasIdentification"));

        axiom3 = factory.getOWLObjectPropertyAssertionAxiom(p1, ind1, ind2);
        addAxiom = new AddAxiom(ontology, axiom3);

        manager.applyChange(addAxiom);

        manager.saveOntology(ontology);

        return "IoT Name: " + iot + " \n ID: " + id;

    }

    private static void createUnitConvert(String uSource, String uTarget, String function) {

        OWLClass class1 = factory.getOWLClass(IRI.create(uri + "UnitConvert"));
        String unitC = uSource + "To" + uTarget;

        OWLNamedIndividual ind1 = factory.getOWLNamedIndividual(IRI.create(uri + unitC));
        OWLDataProperty d = factory.getOWLDataProperty(IRI.create(uri + "hasFunction"));

        OWLClassAssertionAxiom axiom1 = factory.getOWLClassAssertionAxiom(class1, ind1);
        AddAxiom addAxiom = new AddAxiom(ontology, axiom1);
        manager.applyChange(addAxiom);

        OWLDataPropertyAssertionAxiom axiom2 = factory.getOWLDataPropertyAssertionAxiom(d, ind1, function);
        addAxiom = new AddAxiom(ontology, axiom2);
        manager.applyChange(addAxiom);

        OWLObjectProperty p1 = factory.getOWLObjectProperty(IRI.create(uri + "hasSourceUnit"));
        OWLNamedIndividual ind2 = factory.getOWLNamedIndividual(IRI.create(uri + uSource));

        OWLObjectPropertyAssertionAxiom axiom3 = factory.getOWLObjectPropertyAssertionAxiom(p1, ind1, ind2);
        addAxiom = new AddAxiom(ontology, axiom3);
        manager.applyChange(addAxiom);

        p1 = factory.getOWLObjectProperty(IRI.create(uri + "hasTargetUnit"));
        ind2 = factory.getOWLNamedIndividual(IRI.create(uri + uTarget));

        axiom3 = factory.getOWLObjectPropertyAssertionAxiom(p1, ind1, ind2);
        addAxiom = new AddAxiom(ontology, axiom3);
        manager.applyChange(addAxiom);

    }

    private static void setProducer(String topic) throws OWLOntologyCreationException {
        List<String> tmcs = IoTTopicsMoreContextDAO.getAllConsumerThatHaveSameGT(topic);

        OWLNamedIndividual ind1 = factory.getOWLNamedIndividual(IRI.create(uri + topic));
        OWLNamedIndividual ind2;
        OWLObjectProperty p = factory.getOWLObjectProperty(IRI.create(uri + "hasTopicForConsume"));

        for (int i = 0; i < tmcs.size(); i++) {
            if (IoTTopicsMoreContextDAO.isProducerForConsume(topic, tmcs.get(i))) {
                ind2 = factory.getOWLNamedIndividual(IRI.create(uri + tmcs.get(i)));
                OWLObjectPropertyAssertionAxiom axiom = factory.getOWLObjectPropertyAssertionAxiom(p, ind2, ind1);
                AddAxiom addAxiom = new AddAxiom(ontology, axiom);
                manager.applyChange(addAxiom);

            }

        }
    }

    private static void setConsumer(String topic) throws OWLOntologyCreationException {
        List<String> tmcs = IoTTopicsMoreContextDAO.getAllProducerThatHaveSameGT(topic);

        OWLNamedIndividual ind1 = factory.getOWLNamedIndividual(IRI.create(uri + topic));
        OWLNamedIndividual ind2;

        OWLObjectProperty p = factory.getOWLObjectProperty(IRI.create(uri + "hasTopicForConsume"));

        for (int i = 0; i < tmcs.size(); i++) {
            if (IoTTopicsMoreContextDAO.isProducerForConsume(tmcs.get(i), topic)) {
                ind2 = factory.getOWLNamedIndividual(IRI.create(uri + tmcs.get(i)));
                OWLObjectPropertyAssertionAxiom axiom = factory.getOWLObjectPropertyAssertionAxiom(p, ind1, ind2);
                AddAxiom addAxiom = new AddAxiom(ontology, axiom);
                manager.applyChange(addAxiom);

            }

        }
    }

    private static boolean isUnit(String unit) throws OWLOntologyCreationException {

        prepareAcess();

        OWLClass unitClass = factory.getOWLClass(IRI.create(uri + "Unit"));

        for (OWLNamedIndividual unitInd : reasoner.getInstances(unitClass, false).getFlattened()) {
            if (renderer.render(unitInd).compareTo(unit) == 0) {
                return true;
            }
        }

        return false;

    }

    private static boolean iotExists(String iot) throws OWLOntologyCreationException {
        prepareAcess();

        OWLClass iotClass = factory.getOWLClass(IRI.create(uri + "IoT"));

        for (OWLNamedIndividual iotInd : reasoner.getInstances(iotClass, false).getFlattened()) {
            if (renderer.render(iotInd).compareTo(iot) == 0) {
                return true;
            }
        }

        return false;
    }

    private static String getFuntion(String unit, String unitT) throws OWLOntologyCreationException {
        prepareAcess();

        OWLClass unitConvertClass = factory.getOWLClass(IRI.create(uri + "UnitConvert"));
        OWLDataProperty d = factory.getOWLDataProperty(IRI.create(uri + "hasFunction"));

        for (OWLNamedIndividual unitConvertInd : reasoner.getInstances(unitConvertClass, false).getFlattened()) {
            if (renderer.render(unitConvertInd).compareTo(unit + "To" + unitT) == 0) {
                return renderer.render(reasoner.getDataPropertyValues(unitConvertInd, d).iterator().next());
            }
        }

        return "";

    }

    public static List<String> getAllUnitIfTopicHasOrHadUnit(String topic, String unit) throws OWLOntologyCreationException {
        prepareAcess();

        OWLNamedIndividual ind = factory.getOWLNamedIndividual(IRI.create(uri + topic));
        OWLObjectProperty p = factory.getOWLObjectProperty(IRI.create(uri + "thisSTHasOrHadAnUnit"));

        List<String> units = new ArrayList<>();

        int test = 0;
        for (OWLNamedIndividual unitInd : reasoner.getObjectPropertyValues(ind, p).getFlattened()) {
            if (renderer.render(unitInd).compareTo(unit) == 0) {
                test = 1;
                break;
            } else {
                units.add(renderer.render(unitInd));
            }
        }

        if (test == 1) {
            return null;
        }

        return units;
    }

    private static String getInverseFunction(String function) {
        
        String[] operations =  function.split(",");
        String[] operation;
        
        function="";
        
        operation = operations[operations.length-1].split("_");
        function += getInverseOperator(operation[0])+"_"+operation[1];
        
        
        for(int i = operations.length-2; i>=0; i--){
            operation = operations[i].split("_");
            function += ","+getInverseOperator(operation[0])+"_"+operation[1];
        }
        
        return function;
    }

    private static String getInverseOperator(String op) {
        if(op.compareTo("O1")==0) return "O2";
        if(op.compareTo("O2")==0) return "O1";
        if(op.compareTo("O3")==0) return "O4";
        if(op.compareTo("O4")==0) return "O3";
        if(op.compareTo("O5")==0) return "O6";
        return "O5";
    }

    private static boolean verifyFunction(String function) {
        
        String[] operations =  function.split(",");
        String[] operation;
        
        for(int i = 0; i< operations.length; i++){
            operation = operations[i].split("_");
            if(!(isOperator(operation[0]) && isValue(operation[1]))) return false;
        }
        
        return true;
    }

    private static boolean isOperator(String op) {
        if(op.compareTo("O1")==0 || op.compareTo("O2")==0 || op.compareTo("O3")==0 || op.compareTo("O4")==0 || op.compareTo("O5")==0 || op.compareTo("O6")==0) return true;
        
        return false;
    }

    private static boolean isValue(String value) {
        try{
            Float.parseFloat(value);
            return true;
        }catch(Exception ex){
            return false;
        }
    }

}
