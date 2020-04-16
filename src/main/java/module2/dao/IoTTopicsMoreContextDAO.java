package module2.dao;

import java.io.File;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.OWLObjectRenderer;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.reasoner.SimpleConfiguration;
import org.semanticweb.owlapi.model.*;
import com.clarkparsia.pellet.owlapiv3.PelletReasonerFactory;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import module2.model.*;
import module2.similarity.NaturalLanguageProcessor;
import uk.ac.manchester.cs.owlapi.dlsyntax.DLSyntaxObjectRenderer;

public class IoTTopicsMoreContextDAO {

    static String projectDir = "C:\\Users\\mathe\\Documents\\Dropbox\\Projetos\\PRIME-IoT";
    static File file = new File(projectDir + "/Files/Module2.Ontologies/IoTTopicsMoreContext.owl");
    static String uri = "http://www.semanticweb.org/mathe/ontologies/2019/4/IoTTopicsMoreContext#";
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

    private static void prepareOWLApi() throws OWLOntologyCreationException {
        manager = OWLManager.createOWLOntologyManager();
        ontology = manager.loadOntologyFromOntologyDocument(file);
        reasonerFactory = PelletReasonerFactory.getInstance();
        renderer = new DLSyntaxObjectRenderer();
        reasoner = reasonerFactory.createReasoner(ontology, new SimpleConfiguration());
        factory = manager.getOWLDataFactory();
        lastModification = file.lastModified();
    }

    public static String getFirstNameOfIndividualClass(String indName) throws OWLOntologyCreationException {

        prepareAcess();

        OWLNamedIndividual ind = factory.getOWLNamedIndividual(IRI.create(uri + indName));
        OWLObjectProperty p = factory.getOWLObjectProperty(IRI.create(uri + "hasGenericTopic"));

        for (OWLClass c : reasoner.getTypes(ind, true).getFlattened()) {
            return renderer.render(c);
        }

        return "";

    }

    public static String getFirstNameOfIndividualClassForRules(String indName) throws OWLOntologyCreationException {

        prepareAcess();

        OWLNamedIndividual ind = factory.getOWLNamedIndividual(IRI.create(uri + indName));

        for (OWLClass c : reasoner.getTypes(ind, true).getFlattened()) {
            return renderer.render(c).split("#")[1];
        }

        return "";

    }

    public static boolean tmcExist(String tmc) throws OWLOntologyCreationException {

        prepareAcess();

        OWLNamedIndividual ind = factory.getOWLNamedIndividual(IRI.create(uri + tmc));
        OWLObjectProperty p = factory.getOWLObjectProperty(IRI.create(uri + "hasGenericTopic"));

        return reasoner.getObjectPropertyValues(ind, p).getFlattened().iterator().hasNext();

    }

    public static List<String> getAllTopic() throws OWLOntologyCreationException {

        prepareAcess();

        List<String> listTopics = new ArrayList<>();

        OWLClass topicClass = factory.getOWLClass(IRI.create(uri + "AlternativeTopic"));

        for (OWLNamedIndividual topicInd : reasoner.getInstances(topicClass, false).getFlattened()) {
            listTopics.add(renderer.render(topicInd));
        }

        return listTopics;

    }

    public static List<String> getAllSpecificTopic() throws OWLOntologyCreationException {

        prepareAcess();

        List<String> listTopics = new ArrayList<>();

        OWLClass topicClass = factory.getOWLClass(IRI.create(uri + "SpecificTopic"));

        for (OWLNamedIndividual topicInd : reasoner.getInstances(topicClass, false).getFlattened()) {
            listTopics.add(renderer.render(topicInd));
        }

        return listTopics;

    }

    public static List<String> getAllSpecificTopicProducer() throws OWLOntologyCreationException {

        prepareAcess();

        List<String> listTopics = new ArrayList<>();

        OWLClass topicClass = factory.getOWLClass(IRI.create(uri + "Producer"));

        for (OWLNamedIndividual topicInd : reasoner.getInstances(topicClass, false).getFlattened()) {
            listTopics.add(renderer.render(topicInd));
        }

        return listTopics;
    }

    static List<String> getSpecificTopic(String altTopic) throws OWLOntologyCreationException {

        prepareAcess();

        List<String> listTopics = new ArrayList<>();

        OWLNamedIndividual ind = factory.getOWLNamedIndividual(IRI.create(uri + altTopic));
        OWLObjectProperty p = factory.getOWLObjectProperty(IRI.create(uri + "isAlternativeTopicOfTMC"));

        for (OWLNamedIndividual topicInd : reasoner.getObjectPropertyValues(ind, p).getFlattened()) {
            listTopics.add(renderer.render(topicInd));
        }
        return listTopics;
    }

    static String getFirstSpecificTopic(String altTopic) throws OWLOntologyCreationException {

        OWLNamedIndividual ind = factory.getOWLNamedIndividual(IRI.create(uri + altTopic));
        OWLObjectProperty p = factory.getOWLObjectProperty(IRI.create(uri + "isAlternativeTopicOfTMC"));

        for (OWLNamedIndividual topicInd : reasoner.getObjectPropertyValues(ind, p).getFlattened()) {
            return renderer.render(topicInd);
        }

        return "";
    }

    public static List<String> getAllSuperContext() throws OWLOntologyCreationException {
        prepareAcess();

        List<String> listSuperContexts = new ArrayList<>();

        OWLClass topicClass = factory.getOWLClass(IRI.create(uri + "SuperContext"));

        for (OWLNamedIndividual topicInd : reasoner.getInstances(topicClass, false).getFlattened()) {
            listSuperContexts.add(renderer.render(topicInd));
        }

        return listSuperContexts;
    }

    public static List<String> getAllSuperContext(String tempS2) throws OWLOntologyCreationException {

        prepareAcess();

        String et = getFirstSpecificTopic(tempS2);
        List<String> listSP = new ArrayList<>();

        OWLNamedIndividual ind = factory.getOWLNamedIndividual(IRI.create(uri + et));
        OWLObjectProperty p = factory.getOWLObjectProperty(IRI.create(uri + "hasSuperContext"));

        for (OWLNamedIndividual topicInd : reasoner.getObjectPropertyValues(ind, p).getFlattened()) {
            listSP.add(renderer.render(topicInd));
        }

        p = factory.getOWLObjectProperty(IRI.create(uri + "hasSuperContext2"));

        for (OWLNamedIndividual topicInd : reasoner.getObjectPropertyValues(ind, p).getFlattened()) {
            listSP.add(renderer.render(topicInd));
        }

        return listSP;

    }

    public static List<String> getAllChildrenOfSuperContext(String sc) throws OWLOntologyCreationException {
        prepareAcess();

        List<String> listSC = new ArrayList<>();

        OWLNamedIndividual ind = factory.getOWLNamedIndividual(IRI.create(uri + sc));
        OWLObjectProperty p = factory.getOWLObjectProperty(IRI.create(uri + "hasTopContext"));

        for (OWLNamedIndividual topicInd : reasoner.getObjectPropertyValues(ind, p).getFlattened()) {
            listSC.add(renderer.render(topicInd));
        }
        return listSC;
    }

    public static String getCategoryOfContext(String contextChild) throws OWLOntologyCreationException {
        prepareAcess();

        OWLNamedIndividual ind = factory.getOWLNamedIndividual(IRI.create(uri + contextChild));
        OWLObjectProperty p = factory.getOWLObjectProperty(IRI.create(uri + "hasCategory"));

        return renderer.render(reasoner.getObjectPropertyValues(ind, p).getFlattened().iterator().next());

    }

    public static String getCategoryOfTopContext(String sc) throws OWLOntologyCreationException {
        prepareAcess();

        OWLNamedIndividual ind = factory.getOWLNamedIndividual(IRI.create(uri + sc));
        OWLObjectProperty p = factory.getOWLObjectProperty(IRI.create(uri + "hasTopContext"));

        String anyTopContext = renderer.render(reasoner.getObjectPropertyValues(ind, p).getFlattened().iterator().next());

        ind = factory.getOWLNamedIndividual(IRI.create(uri + anyTopContext));
        p = factory.getOWLObjectProperty(IRI.create(uri + "hasCategory"));

        return renderer.render(reasoner.getObjectPropertyValues(ind, p).getFlattened().iterator().next());

    }

    public static List<String> getAllChildrenOfContext(String parent) throws OWLOntologyCreationException {

        prepareAcess();

        List<String> listC = new ArrayList<>();

        OWLNamedIndividual ind = factory.getOWLNamedIndividual(IRI.create(uri + parent));
        OWLObjectProperty p = factory.getOWLObjectProperty(IRI.create(uri + "hasSubContext"));

        for (OWLNamedIndividual topicInd : reasoner.getObjectPropertyValues(ind, p).getFlattened()) {
            listC.add(renderer.render(topicInd));
        }
        return listC;
    }

    public static boolean testTMC(String tmc) throws OWLOntologyCreationException {

        OWLNamedIndividual ind = factory.getOWLNamedIndividual(IRI.create(uri + tmc));
        OWLObjectProperty p = factory.getOWLObjectProperty(IRI.create(uri + "hasGenericTopic"));

        String result = renderer.render(reasoner.getObjectPropertyValues(ind, p).getFlattened().iterator().next());

        return !result.isEmpty();

    }

    public static String save(String[] values) throws OWLOntologyCreationException, OWLOntologyStorageException {
        if (values.length != 6) {
            return "";
        }

        if (values[0].compareTo("") == 0) {
            return "";
        }
        String topicName = values[0];

        if (values[1].compareTo("") == 0) {
            return "";
        }
        String description = values[1];

        if (values[2].compareTo("") == 0) {
            return "";
        }
        String[] superContexts = values[2].split(",");

        if (values[3].compareTo("") == 0) {
            return "";
        }
        String[] contexts = values[3].split(";");

        String[] pragmatic = null;
        if (!values[4].isEmpty()) {
            pragmatic = values[4].split(";");
        }

        if (values[5].compareTo("") == 0) {
            return "";
        }
        String typeTopic = values[5];

        if (superContexts.length != contexts.length) {
            return "";
        }
        int[] test = new int[contexts.length];

        //String[] topicInfo = values[6].split(";");
        prepareAcess();
        OWLClass class1 = factory.getOWLClass(IRI.create(uri + "SuperContext"));
        OWLNamedIndividual ind1, ind2;
        OWLObjectProperty p1 = factory.getOWLObjectProperty(IRI.create(uri + "hasTopContext"));
        OWLObjectProperty p2 = factory.getOWLObjectProperty(IRI.create(uri + "hasSubContextWH"));

        if (pragmatic != null) {
            for (String s : pragmatic) {
                String[] sv1 = s.split("[,/]+");
                String tmc;
                try {
                    String sT = sv1[0].substring(0, 2);
                    if (sT.compareTo("FT") != 0) {
                        return "";
                    }

                    if (sv1[0].length() == 3) {
                        int i1 = Integer.parseInt("" + sv1[0].charAt(2));
                        if (!(i1 > 0 && i1 < 7)) {
                            return "";
                        }

                        if (sv1.length == 3) {
                            try {
                                float f = Float.parseFloat(sv1[1]);
                            } catch (Exception e) {
                                return "";
                            }
                        } else {
                            return "";
                        }

                        tmc = sv1[2];

                    } else {
                        sT = sv1[0].substring(3, 6);
                        if (sT.compareTo("_FT") != 0) {
                            return "";
                        }
                        sT = "" + sv1[0].charAt(2) + sv1[0].charAt(6);
                        int i1 = Integer.parseInt(sT);
                        if (i1 != 12 && i1 != 15 && i1 != 16 && i1 != 24 && i1 != 26 && i1 != 45 && i1 != 46 && i1 != 56 && i1 != 66) {
                            return "";
                        }

                        if (sv1.length == 4) {
                            try {
                                float f = Float.parseFloat(sv1[1]);
                                f = Float.parseFloat(sv1[2]);
                            } catch (Exception e) {
                                return "";
                            }
                        } else {
                            return "";
                        }

                        tmc = sv1[3];
                    }
                } catch (Exception e) {
                    return "";
                }

                if (!testTMC(tmc)) {
                    return "";
                }

            }
        }

        String tempS, tempS2;
        String[] tempSV;

        for (OWLNamedIndividual topicInd : reasoner.getInstances(class1, false).getFlattened()) {
            tempS = renderer.render(topicInd);
            for (int i = 0; i < superContexts.length; i++) {
                if (tempS.compareTo(superContexts[i]) == 0) {
                    tempSV = contexts[i].split(",");

                    if (tempSV.length == 0) {
                        return "";
                    }
                    if (tempSV[0].compareTo("") == 0) {
                        return "";
                    }
                    if (tempSV[0].length() == 0) {
                        return "";
                    }
                    if (tempSV[0].compareTo(superContexts[i]) == 0) {
                        return "";
                    }

                    ind1 = factory.getOWLNamedIndividual(IRI.create(uri + superContexts[i]));

                    if (tempSV[0].split("-").length == 1) {
                        for (OWLNamedIndividual superContextInd : reasoner.getObjectPropertyValues(ind1, p1).getFlattened()) {
                            tempS2 = renderer.render(superContextInd);
                            boolean breakTest = false;
                            if (tempS2.compareTo(tempSV[0]) == 0) {
                                if (tempSV.length > 1) {
                                    for (int j = 1; j < tempSV.length; j++) {
                                        if (tempSV[j].split("-").length != 2) {
                                            return "";
                                        }
                                    }
                                }
                                test[i] = 1;
                                breakTest = true;
                            } else {
                                ind2 = factory.getOWLNamedIndividual(IRI.create(uri + tempS2));
                                for (OWLNamedIndividual contextInd : reasoner.getObjectPropertyValues(ind2, p2).getFlattened()) {
                                    tempS2 = renderer.render(contextInd);
                                    if (tempS2.compareTo(tempSV[0]) == 0) {
                                        if (tempSV.length > 1) {
                                            for (int j = 1; j < tempSV.length; j++) {
                                                if (tempSV[j].split("-").length != 2) {
                                                    return "";
                                                }
                                            }
                                        }
                                        test[i] = 1;
                                        breakTest = true;
                                    }
                                    if (breakTest) {
                                        break;
                                    }
                                }
                            }
                            if (breakTest) {
                                break;
                            }
                        }
                    }

                    break;

                }
            }
        }

        for (int i = 0; i < test.length; i++) {
            if (test[i] == 0) {
                tempSV = contexts[i].split(",");
                if (tempSV.length == 0) {
                    return "";
                }

                for (int j = 0; j < tempSV.length; j++) {
                    if (tempSV[j].split("-").length != 2) {
                        return "";
                    }
                }
            }
        }

        class1 = factory.getOWLClass(IRI.create(uri + "AlternativeTopic"));
        OWLDataProperty d = factory.getOWLDataProperty(IRI.create(uri + "hasTypeConsume"));
        p1 = factory.getOWLObjectProperty(IRI.create(uri + "isAlternativeTopicOfTMC"));
        p2 = factory.getOWLObjectProperty(IRI.create(uri + "hasContext"));

        int testTopic = 0;
        int testTMC = 1;

        for (int i = 0; i < test.length; i++) {
            if (test[i] == 0) {
                testTMC = 0;
                break;
            } else {
                if (contexts[i].contains(",")) {
                    testTMC = 0;
                    break;
                }
            }
        }

        for (OWLNamedIndividual topicInd : reasoner.getInstances(class1, false).getFlattened()) {
            if (renderer.render(topicInd).compareTo(topicName) == 0) {
                testTopic = 1;
                break;
            }
        }

        if (testTMC == 1) {
            int testT = 1;

            if (testTopic == 1) {
                String tmc = "";
                ind1 = factory.getOWLNamedIndividual(IRI.create(uri + topicName));

                for (OWLNamedIndividual tmcInd : reasoner.getObjectPropertyValues(ind1, p1).getFlattened()) {
                    tmc = renderer.render(tmcInd);
                    ind2 = factory.getOWLNamedIndividual(IRI.create(uri + tmc));
                    String result1 = "";

                    if (reasoner.getDataPropertyValues(ind2, d).iterator().hasNext()) {
                        result1 = renderer.render(reasoner.getDataPropertyValues(ind2, d).iterator().next());
                    }

                    if (result1 != "") {
                        if (typeTopic.compareTo(result1) != 0) {
                            testT = 0;
                            break;
                        }
                    } else {
                        if (typeTopic.compareTo("0") != 0) {
                            testT = 0;
                            break;
                        }
                    }

                    List<String> contextsCopy = Arrays.asList(contexts);

                    if (testT == 1) {

                        for (OWLNamedIndividual contextsInd : reasoner.getObjectPropertyValues(ind2, p2).getFlattened()) {
                            int testC = 0;
                            for (int j = 0; j < contextsCopy.size(); j++) {
                                if (contextsCopy.get(j).compareTo(renderer.render(contextsInd)) == 0) {
                                    testC = 1;
                                    contextsCopy.remove(j);
                                    j--;
                                    break;
                                }
                            }
                            if (testC == 0) {
                                testT = 0;
                                break;
                            }
                        }

                    }

                }
                if (testT == 1) {
                    return tmc;
                }
            }

        }

        p1 = factory.getOWLObjectProperty(IRI.create(uri + "hasIntentionTopic"));
        String pragInd = "";
        if (pragmatic != null) {
            List<String> prag = new ArrayList<>();
            boolean allRulesExist = true;
            for (int k = 0; k < pragmatic.length; k++) {
                tempSV = pragmatic[k].split("[,/]+");
                tempS = tempSV[0];
                tempS += "," + Float.parseFloat(tempSV[1]);
                if (tempSV.length == 4) {
                    tempS += "," + Float.parseFloat(tempSV[2]);
                }

                String tmcP = tempSV[tempSV.length - 1];

                pragmatic[k] = tempS + "/" + tmcP;

                class1 = factory.getOWLClass(IRI.create(uri + tempS));
                int testTMCP = 0;
                for (OWLNamedIndividual rule : reasoner.getInstances(class1, false).getFlattened()) {
                    ind1 = factory.getOWLNamedIndividual(IRI.create(uri + renderer.render(rule)));
                    String result = renderer.render(reasoner.getObjectPropertyValues(ind1, p1).getFlattened().iterator().next());

                    if (tmcP.compareTo(result) == 0) {
                        prag.add(renderer.render(rule));
                        testTMCP = 1;
                        break;
                    }
                }

                if (testTMCP == 0) {
                    allRulesExist = false;
                    prag.add("");
                }

            }

            if (allRulesExist) {
                List<String> aPIOFR = new ArrayList<>();  //all pragmatic ind of first rule;
                ind1 = factory.getOWLNamedIndividual(IRI.create(uri + prag.get(0)));
                p1 = factory.getOWLObjectProperty(IRI.create(uri + "isRuleOf"));

                for (OWLNamedIndividual pI : reasoner.getObjectPropertyValues(ind1, p1).getFlattened()) {
                    aPIOFR.add(renderer.render(pI));
                }

                for (int i = 1; i < prag.size() || aPIOFR.isEmpty(); i++) {
                    ind1 = factory.getOWLNamedIndividual(IRI.create(uri + prag.get(i)));
                    for (int j = 0; j < aPIOFR.size(); j++) {
                        int t2 = 0;
                        for (OWLNamedIndividual pI : reasoner.getObjectPropertyValues(ind1, p1).getFlattened()) {
                            if (aPIOFR.get(j).compareTo(renderer.render(pI)) == 0) {
                                t2 = 1;
                                break;
                            }
                        }

                        if (t2 == 0) {
                            aPIOFR.remove(j);
                            j--;
                        }

                    }
                }

                if (!aPIOFR.isEmpty()) {
                    pragInd = aPIOFR.get(0);
                }
            }

            if (pragInd.isEmpty()) {

                class1 = factory.getOWLClass(IRI.create(uri + "Pragmatic"));

                int count = 1;
                for (OWLNamedIndividual p : reasoner.getInstances(class1, false).getFlattened()) {
                    count++;
                }

                pragInd = "P" + count;
                ind1 = factory.getOWLNamedIndividual(IRI.create(uri + pragInd));

                class1 = factory.getOWLClass(IRI.create(uri + "IntentionRule"));

                int countRule = 0;
                for (OWLNamedIndividual p : reasoner.getInstances(class1, false).getFlattened()) {
                    countRule++;
                }

                p1 = factory.getOWLObjectProperty(IRI.create(uri + "hasRule"));

                for (int i = 0; i < prag.size(); i++) {
                    tempS = prag.get(i);
                    if (tempS.compareTo("") != 0) {
                        ind2 = factory.getOWLNamedIndividual(IRI.create(uri + tempS));
                        OWLObjectPropertyAssertionAxiom axiom3 = factory.getOWLObjectPropertyAssertionAxiom(p1, ind1, ind2);
                        AddAxiom addAxiom = new AddAxiom(ontology, axiom3);
                        manager.applyChange(addAxiom);
                    } else {
                        String[] sv = pragmatic[i].split("/");
                        class1 = factory.getOWLClass(IRI.create(uri + sv[0]));
                        System.out.println(class1.toString());
                        countRule++;
                        ind2 = factory.getOWLNamedIndividual(IRI.create(uri + "IR" + countRule));
                        System.out.println(ind2.toString());

                        OWLAxiom axiom1 = factory.getOWLClassAssertionAxiom(class1, ind2);
                        AddAxiom addAxiom = new AddAxiom(ontology, axiom1);
                        manager.applyChange(addAxiom);

                        OWLObjectPropertyAssertionAxiom axiom3 = factory.getOWLObjectPropertyAssertionAxiom(p1, ind1, ind2);
                        addAxiom = new AddAxiom(ontology, axiom3);
                        manager.applyChange(addAxiom);

                        ind1 = factory.getOWLNamedIndividual(IRI.create(uri + sv[1]));
                        p1 = factory.getOWLObjectProperty(IRI.create(uri + "hasIntentionTopic"));
                        axiom3 = factory.getOWLObjectPropertyAssertionAxiom(p1, ind2, ind1);
                        addAxiom = new AddAxiom(ontology, axiom3);
                        manager.applyChange(addAxiom);

                        ind1 = factory.getOWLNamedIndividual(IRI.create(uri + pragInd));
                        p1 = factory.getOWLObjectProperty(IRI.create(uri + "hasRule"));
                    }

                }

            }

        }

        class1 = factory.getOWLClass(IRI.create(uri + "SpecificTopic"));

        int count = 1;
        for (OWLNamedIndividual topicInd : reasoner.getInstances(class1, false).getFlattened()) {
            count++;
        }

        String TMC = "TMC" + count;

        ind1 = factory.getOWLNamedIndividual(IRI.create(uri + TMC));
        int t = 0;

        if (pragmatic != null) {
            ind2 = factory.getOWLNamedIndividual(IRI.create(uri + pragInd));
            p1 = factory.getOWLObjectProperty(IRI.create(uri + "hasPragmatic"));
            OWLObjectPropertyAssertionAxiom axiom3 = factory.getOWLObjectPropertyAssertionAxiom(p1, ind1, ind2);
            AddAxiom addAxiom = new AddAxiom(ontology, axiom3);
            manager.applyChange(addAxiom);
        }

        if (typeTopic.compareTo("0") == 0) {
            class1 = factory.getOWLClass(IRI.create(uri + "Producer"));
        } else {
            try {
                t = Integer.parseInt(typeTopic);
                if (t > 0 && t < 3) {
                    class1 = factory.getOWLClass(IRI.create(uri + "Consumer"));
                } else {
                    return "";
                }
            } catch (Exception ex) {
                return "";
            }
        }

        OWLAxiom axiom1 = factory.getOWLClassAssertionAxiom(class1, ind1);
        AddAxiom addAxiom = new AddAxiom(ontology, axiom1);
        manager.applyChange(addAxiom);

        if (t != 0) {
            OWLDataPropertyAssertionAxiom axiom2 = factory.getOWLDataPropertyAssertionAxiom(d, ind1, "" + t);
            addAxiom = new AddAxiom(ontology, axiom2);
            System.out.println(addAxiom.toString());
            manager.applyChange(addAxiom);
        }

        p1 = factory.getOWLObjectProperty(IRI.create(uri + "isAlternativeTopicOf"));

        OWLNamedIndividual ind3 = factory.getOWLNamedIndividual(IRI.create(uri + topicName));

        String gt;
        if (testTopic == 1) {
            gt = renderer.render(reasoner.getObjectPropertyValues(ind3, p1).getFlattened().iterator().next());
            ind2 = factory.getOWLNamedIndividual(IRI.create(uri + gt));
        } else {
            class1 = factory.getOWLClass(IRI.create(uri + "GenericTopic"));

            count = 1;
            for (OWLNamedIndividual topicInd : reasoner.getInstances(class1, false).getFlattened()) {
                count++;
            }

            gt = "GT" + count;

            ind2 = factory.getOWLNamedIndividual(IRI.create(uri + gt));

            OWLObjectPropertyAssertionAxiom axiom3 = factory.getOWLObjectPropertyAssertionAxiom(p1, ind3, ind2);
            addAxiom = new AddAxiom(ontology, axiom3);
            System.out.println(addAxiom.toString());

            manager.applyChange(addAxiom);

        }

        p1 = factory.getOWLObjectProperty(IRI.create(uri + "hasGenericTopic"));

        OWLObjectPropertyAssertionAxiom axiom3 = factory.getOWLObjectPropertyAssertionAxiom(p1, ind1, ind2);
        addAxiom = new AddAxiom(ontology, axiom3);
        System.out.println(addAxiom.toString());

        manager.applyChange(addAxiom);

        OWLNamedIndividual ind4;
        p1 = factory.getOWLObjectProperty(IRI.create(uri + "hasCategory"));
        p2 = factory.getOWLObjectProperty(IRI.create(uri + "isTopContextOf"));
        OWLObjectProperty p3 = factory.getOWLObjectProperty(IRI.create(uri + "isSubContextOf"));
        String parent;
        for (int i = 0; i < test.length; i++) {
            p1 = factory.getOWLObjectProperty(IRI.create(uri + "hasCategory"));
            if (test[i] == 0) {
                parent = superContexts[i];
                tempSV = contexts[i].split(",");

                String[] tempSV2 = tempSV[0].split("-");
                ind3 = factory.getOWLNamedIndividual(IRI.create(uri + tempSV2[1]));
                ind4 = factory.getOWLNamedIndividual(IRI.create(uri + tempSV2[0]));

                axiom3 = factory.getOWLObjectPropertyAssertionAxiom(p1, ind3, ind4);
                addAxiom = new AddAxiom(ontology, axiom3);
                System.out.println(addAxiom.toString());
                manager.applyChange(addAxiom);

                ind2 = factory.getOWLNamedIndividual(IRI.create(uri + parent));
                axiom3 = factory.getOWLObjectPropertyAssertionAxiom(p2, ind3, ind2);
                addAxiom = new AddAxiom(ontology, axiom3);
                System.out.println(addAxiom.toString());
                manager.applyChange(addAxiom);

                parent = tempSV2[1];

                for (int j = 1; j < tempSV.length; j++) {
                    tempSV2 = tempSV[j].split("-");
                    ind3 = factory.getOWLNamedIndividual(IRI.create(uri + tempSV2[1]));
                    ind4 = factory.getOWLNamedIndividual(IRI.create(uri + tempSV2[0]));

                    axiom3 = factory.getOWLObjectPropertyAssertionAxiom(p1, ind3, ind4);
                    addAxiom = new AddAxiom(ontology, axiom3);
                    System.out.println(addAxiom.toString());
                    manager.applyChange(addAxiom);

                    ind2 = factory.getOWLNamedIndividual(IRI.create(uri + parent));
                    axiom3 = factory.getOWLObjectPropertyAssertionAxiom(p3, ind3, ind2);
                    addAxiom = new AddAxiom(ontology, axiom3);
                    System.out.println(addAxiom.toString());
                    manager.applyChange(addAxiom);

                    parent = tempSV2[1];
                }
            } else {
                tempSV = contexts[i].split(",");
                parent = tempSV[0];

                int w = 1;
                String[] tempSV2;

                ind3 = factory.getOWLNamedIndividual(IRI.create(uri + parent));

                if (parent.compareTo(superContexts[i]) == 0) {
                    tempSV2 = tempSV[1].split("-");
                    ind3 = factory.getOWLNamedIndividual(IRI.create(uri + tempSV2[1]));
                    ind4 = factory.getOWLNamedIndividual(IRI.create(uri + tempSV2[0]));

                    axiom3 = factory.getOWLObjectPropertyAssertionAxiom(p1, ind3, ind4);
                    addAxiom = new AddAxiom(ontology, axiom3);
                    System.out.println(addAxiom.toString());
                    manager.applyChange(addAxiom);

                    ind2 = factory.getOWLNamedIndividual(IRI.create(uri + parent));
                    axiom3 = factory.getOWLObjectPropertyAssertionAxiom(p2, ind3, ind2);
                    addAxiom = new AddAxiom(ontology, axiom3);
                    System.out.println(addAxiom.toString());
                    manager.applyChange(addAxiom);

                    parent = tempSV2[1];

                    w++;
                }

                for (int j = 1; j < tempSV.length; j++) {
                    tempSV2 = tempSV[j].split("-");
                    ind3 = factory.getOWLNamedIndividual(IRI.create(uri + tempSV2[1]));
                    ind4 = factory.getOWLNamedIndividual(IRI.create(uri + tempSV2[0]));

                    axiom3 = factory.getOWLObjectPropertyAssertionAxiom(p1, ind3, ind4);
                    addAxiom = new AddAxiom(ontology, axiom3);
                    System.out.println(addAxiom.toString());
                    manager.applyChange(addAxiom);

                    ind2 = factory.getOWLNamedIndividual(IRI.create(uri + parent));
                    axiom3 = factory.getOWLObjectPropertyAssertionAxiom(p3, ind3, ind2);
                    addAxiom = new AddAxiom(ontology, axiom3);
                    System.out.println(addAxiom.toString());
                    manager.applyChange(addAxiom);

                    parent = tempSV2[1];
                }

            }

            p1 = factory.getOWLObjectProperty(IRI.create(uri + "hasContext"));
            axiom3 = factory.getOWLObjectPropertyAssertionAxiom(p1, ind1, ind3);
            addAxiom = new AddAxiom(ontology, axiom3);
            System.out.println(addAxiom.toString());
            manager.applyChange(addAxiom);

            manager.saveOntology(ontology);

        }

        return TMC;

    }

    public static List<String> getAllSpecificTopicThatHaveSameGT(String tmc) throws OWLOntologyCreationException {

        prepareAcess();

        OWLNamedIndividual ind = factory.getOWLNamedIndividual(IRI.create(uri + tmc));
        OWLObjectProperty p = factory.getOWLObjectProperty(IRI.create(uri + "hasSameGT"));

        List<String> tmcs = new ArrayList<>();

        for (OWLNamedIndividual topicInd : reasoner.getObjectPropertyValues(ind, p).getFlattened()) {
            tmcs.add(renderer.render(topicInd));
        }

        return tmcs;
    }

    static List<String> getAllProducerThatHaveSameGT(String tmc) throws OWLOntologyCreationException {

        prepareAcess();

        OWLNamedIndividual ind1 = factory.getOWLNamedIndividual(IRI.create(uri + tmc));
        OWLObjectProperty p = factory.getOWLObjectProperty(IRI.create(uri + "hasSameGT"));

        List<String> tmcs = new ArrayList<>();

        for (OWLNamedIndividual topicInd : reasoner.getObjectPropertyValues(ind1, p).getFlattened()) {
            String tempS = renderer.render(topicInd);
            if (isProducer(tempS) && tempS.compareTo(tmc) != 0) {
                tmcs.add(tempS);
            }
        }

        return tmcs;
    }

    static List<String> getAllProducer(String tmc) throws OWLOntologyCreationException {

        prepareAcess();

        OWLNamedIndividual ind1 = factory.getOWLNamedIndividual(IRI.create(uri + tmc));
        OWLObjectProperty p = factory.getOWLObjectProperty(IRI.create(uri + "hasSameGT"));

        List<String> tmcs = new ArrayList<>();

        for (OWLNamedIndividual topicInd : reasoner.getObjectPropertyValues(ind1, p).getFlattened()) {
            String tempS = renderer.render(topicInd);
            if (isProducer(tempS)) {
                tmcs.add(tempS);
            }
        }

        return tmcs;
    }

    static List<String> getAllConsumerThatHaveSameGT(String tmc) throws OWLOntologyCreationException {

        prepareAcess();

        OWLNamedIndividual ind1 = factory.getOWLNamedIndividual(IRI.create(uri + tmc));
        OWLObjectProperty p = factory.getOWLObjectProperty(IRI.create(uri + "hasSameGT"));

        List<String> tmcs = new ArrayList<>();

        for (OWLNamedIndividual topicInd : reasoner.getObjectPropertyValues(ind1, p).getFlattened()) {
            String tempS = renderer.render(topicInd);
            if (!isProducer(tempS) && tempS.compareTo(tmc) != 0) {
                tmcs.add(tempS);
            }
        }

        return tmcs;
    }

    static List<String> getAllConsumer(String tmc) throws OWLOntologyCreationException {

        prepareAcess();

        OWLNamedIndividual ind1 = factory.getOWLNamedIndividual(IRI.create(uri + tmc));
        OWLObjectProperty p = factory.getOWLObjectProperty(IRI.create(uri + "hasSameGT"));

        List<String> tmcs = new ArrayList<>();

        for (OWLNamedIndividual topicInd : reasoner.getObjectPropertyValues(ind1, p).getFlattened()) {
            String tempS = renderer.render(topicInd);
            if (!isProducer(tempS)) {
                tmcs.add(tempS);
            }
        }

        return tmcs;
    }

    static boolean isProducer(String topic) throws OWLOntologyCreationException {
        prepareAcess();

        OWLNamedIndividual ind = factory.getOWLNamedIndividual(IRI.create(uri + topic));
        OWLDataProperty d = factory.getOWLDataProperty(IRI.create(uri + "hasTypeConsume"));

        return !reasoner.getDataPropertyValues(ind, d).iterator().hasNext();

    }

    static boolean isProducerForConsume(String producer, String consumer) throws OWLOntologyCreationException {
        prepareAcess();

        List<String> contextsP = getContexts(producer);
        List<String> contextsC = getContexts(consumer);
        OWLNamedIndividual ind = factory.getOWLNamedIndividual(IRI.create(uri + consumer));
        OWLDataProperty d = factory.getOWLDataProperty(IRI.create(uri + "hasTypeConsume"));

        int dp = Integer.parseInt(renderer.render(reasoner.getDataPropertyValues(ind, d).iterator().next()));

        for (String s1 : contextsP) {
            int test = 0;
            for (String s2 : contextsC) {
                if (s1.compareTo(s2) == 0) {
                    test = 1;
                    break;
                }
            }
            if (dp == 1 && test == 0) {
                for (String s2 : contextsC) {
                    if (isSubContext(s1, s2)) {
                        test = 1;
                        break;
                    }
                }
            }

            if (test == 0) {
                return false;
            }

        }

        return true;

    }

    private static List<String> getContexts(String topic) throws OWLOntologyCreationException {
        prepareAcess();

        OWLNamedIndividual ind1 = factory.getOWLNamedIndividual(IRI.create(uri + topic));
        OWLObjectProperty p = factory.getOWLObjectProperty(IRI.create(uri + "hasContext"));

        List<String> c = new ArrayList<>();

        for (OWLNamedIndividual topicInd : reasoner.getObjectPropertyValues(ind1, p).getFlattened()) {
            c.add(renderer.render(topicInd));
        }

        return c;

    }

    private static boolean isSubContext(String s1, String s2) throws OWLOntologyCreationException {
        prepareAcess();

        OWLNamedIndividual ind1 = factory.getOWLNamedIndividual(IRI.create(uri + s2));
        OWLObjectProperty p = factory.getOWLObjectProperty(IRI.create(uri + "hasSubContextWH"));

        for (OWLNamedIndividual topicInd : reasoner.getObjectPropertyValues(ind1, p).getFlattened()) {
            if (s1.compareTo(renderer.render(topicInd)) == 0) {
                return true;
            }
        }

        return false;
    }

    public static List<String> getAllTMC(String type) throws OWLOntologyCreationException {

        prepareAcess();

        List<String> tmcs = new ArrayList<>();

        if (type.compareTo("c") == 0) {
            type = "Consumer";
        } else {
            if (type.compareTo("p") == 0) {
                type = "Producer";
            } else {
                return tmcs;
            }
        }

        OWLClass unitClass = factory.getOWLClass(IRI.create(uri + type));

        for (OWLNamedIndividual topicInd : reasoner.getInstances(unitClass, false).getFlattened()) {
            tmcs.add(renderer.render(topicInd));
        }

        return tmcs;
    }

    static String getGenericTopic(String topic) throws OWLOntologyCreationException {

        prepareAcess();

        OWLNamedIndividual ind = factory.getOWLNamedIndividual(IRI.create(uri + topic));
        OWLObjectProperty p = factory.getOWLObjectProperty(IRI.create(uri + "hasGenericTopic"));

        return renderer.render(reasoner.getObjectPropertyValues(ind, p).getFlattened().iterator().next());
    }

    public static String getGenericTopicOfAT(String topic) throws OWLOntologyCreationException {

        prepareAcess();

        OWLNamedIndividual ind = factory.getOWLNamedIndividual(IRI.create(uri + topic));
        OWLObjectProperty p = factory.getOWLObjectProperty(IRI.create(uri + "isAlternativeTopicOf"));

        if (reasoner.getObjectPropertyValues(ind, p).getFlattened().iterator().hasNext()) {
            return renderer.render(reasoner.getObjectPropertyValues(ind, p).getFlattened().iterator().next());
        }
        
        return "";
    }

    public static List<String> getAllAlternativeTopicsOfGT(String topic) throws OWLOntologyCreationException {

        prepareAcess();

        List<String> ats = new ArrayList<>();

        OWLNamedIndividual ind = factory.getOWLNamedIndividual(IRI.create(uri + topic));
        OWLObjectProperty p = factory.getOWLObjectProperty(IRI.create(uri + "hasAlternativeTopic"));

        for (OWLNamedIndividual topicInd : reasoner.getObjectPropertyValues(ind, p).getFlattened()) {
            ats.add(renderer.render(topicInd));
        }

        return ats;

    }

    public static List<String[]> getSimilarityWithFixDefinition(String topic, String terms, String def, String syn, String keywords) throws OWLOntologyCreationException {

        prepareAcess();

        List<String[]> descsAndSimilarity = new ArrayList<>();

        OWLClass topicClass = factory.getOWLClass(IRI.create(uri + "GenericTopic"));

        OWLNamedIndividual ind;
        OWLObjectProperty p = factory.getOWLObjectProperty(IRI.create(uri + "hasDescription"));
        OWLDataProperty ddef = factory.getOWLDataProperty(IRI.create(uri + "hasDefinition"));
        OWLDataProperty dterm = factory.getOWLDataProperty(IRI.create(uri + "hasTerms"));
        OWLDataProperty dsyn = factory.getOWLDataProperty(IRI.create(uri + "hasSynonyms"));

        String[] tempV;

        for (OWLNamedIndividual topicInd : reasoner.getInstances(topicClass, false).getFlattened()) {

            String tempDesc = renderer.render(reasoner.getObjectPropertyValues(topicInd, p).getFlattened().iterator().next());

            ind = factory.getOWLNamedIndividual(IRI.create(uri + tempDesc));

            if (renderer.render(reasoner.getDataPropertyValues(ind, ddef).iterator().next()).compareTo(def) == 0) {
                descsAndSimilarity.add(new String[]{"ed", renderer.render(topicInd)});
                break;
            }

            Double similarity = 0.0;
            int count;

            tempV = renderer.render(reasoner.getDataPropertyValues(ind, dterm).iterator().next()).split(",");
            similarity += NaturalLanguageProcessor.getSilarityOneToMany(topic, tempV);
            similarity += NaturalLanguageProcessor.getSilarityManyToMany(terms.split(","), tempV);
            similarity += NaturalLanguageProcessor.getSilarityManyToMany(syn.split(","), tempV);
            similarity += NaturalLanguageProcessor.getSilarityManyToMany(keywords.split(","), tempV);
            count = 4;

            if (reasoner.getDataPropertyValues(ind, dsyn).iterator().hasNext()) {
                tempV = renderer.render(reasoner.getDataPropertyValues(ind, dsyn).iterator().next()).split(",");
                similarity += NaturalLanguageProcessor.getSilarityOneToMany(topic, tempV);
                similarity += NaturalLanguageProcessor.getSilarityManyToMany(terms.split(","), tempV);
                similarity += NaturalLanguageProcessor.getSilarityManyToMany(syn.split(","), tempV);
                similarity += NaturalLanguageProcessor.getSilarityManyToMany(keywords.split(","), tempV);
                count += 4;
            }

            tempV = new String[]{tempDesc, "" + (similarity / count)};

            descsAndSimilarity.add(tempV);

        }

        return descsAndSimilarity;

    }

    public static List<String[]> getSimilarityWithFixDefinition(String topic, String def, String keywords) throws OWLOntologyCreationException {

        prepareAcess();

        List<String[]> descsAndSimilarity = new ArrayList<>();

        OWLClass topicClass = factory.getOWLClass(IRI.create(uri + "GenericTopic"));

        OWLNamedIndividual ind;
        OWLObjectProperty p = factory.getOWLObjectProperty(IRI.create(uri + "hasDescription"));
        OWLDataProperty ddef = factory.getOWLDataProperty(IRI.create(uri + "hasDefinition"));
        OWLDataProperty dterm = factory.getOWLDataProperty(IRI.create(uri + "hasTerms"));
        OWLDataProperty dsyn = factory.getOWLDataProperty(IRI.create(uri + "hasSynonyms"));

        String[] tempV;

        for (OWLNamedIndividual topicInd : reasoner.getInstances(topicClass, false).getFlattened()) {

            String tempDesc = renderer.render(reasoner.getObjectPropertyValues(topicInd, p).getFlattened().iterator().next());

            ind = factory.getOWLNamedIndividual(IRI.create(uri + tempDesc));

            Double similarity = 0.0;
            int count;

            tempV = renderer.render(reasoner.getDataPropertyValues(ind, ddef).iterator().next()).split(",");
            similarity += NaturalLanguageProcessor.getSilarityOneToMany(topic, tempV);
            similarity += NaturalLanguageProcessor.getSilarityOneToMany(def, tempV);
            similarity += NaturalLanguageProcessor.getSilarityManyToMany(keywords.split(","), tempV);

            tempV = renderer.render(reasoner.getDataPropertyValues(ind, dterm).iterator().next()).split(",");
            similarity += NaturalLanguageProcessor.getSilarityOneToMany(topic, tempV);
            similarity += NaturalLanguageProcessor.getSilarityOneToMany(def, tempV);
            similarity += NaturalLanguageProcessor.getSilarityManyToMany(keywords.split(","), tempV);

            count = 6;

            if (reasoner.getDataPropertyValues(ind, dsyn).iterator().hasNext()) {
                tempV = renderer.render(reasoner.getDataPropertyValues(ind, dsyn).iterator().next()).split(",");
                similarity += NaturalLanguageProcessor.getSilarityOneToMany(topic, tempV);
                similarity += NaturalLanguageProcessor.getSilarityOneToMany(def, tempV);
                similarity += NaturalLanguageProcessor.getSilarityManyToMany(keywords.split(","), tempV);
                count += 3;
            }

            tempV = new String[]{tempDesc, "" + (similarity / count)};

            descsAndSimilarity.add(tempV);

        }

        return descsAndSimilarity;

    }

    public static String getGTofDescription(String desc) throws OWLOntologyCreationException {
        prepareAcess();

        OWLNamedIndividual ind = factory.getOWLNamedIndividual(IRI.create(uri + desc));
        OWLObjectProperty p1 = factory.getOWLObjectProperty(IRI.create(uri + "isDescriptionOf"));
        OWLObjectProperty p2 = factory.getOWLObjectProperty(IRI.create(uri + "isDescriptionOfSP"));

        if (reasoner.getObjectPropertyValues(ind, p1).getFlattened().iterator().hasNext()) {
            return renderer.render(reasoner.getObjectPropertyValues(ind, p1).getFlattened().iterator().next());
        } else {
            if (reasoner.getObjectPropertyValues(ind, p2).getFlattened().iterator().hasNext()) {
                return renderer.render(reasoner.getObjectPropertyValues(ind, p2).getFlattened().iterator().next());
            }

            return "";

        }
    }

    public static String getTerms(String desc) throws OWLOntologyCreationException {
        prepareAcess();

        OWLNamedIndividual ind = factory.getOWLNamedIndividual(IRI.create(uri + desc));
        OWLDataProperty ddef = factory.getOWLDataProperty(IRI.create(uri + "hasTerms"));

        if (reasoner.getDataPropertyValues(ind, ddef).iterator().hasNext()) {
            return renderer.render(reasoner.getDataPropertyValues(ind, ddef).iterator().next());
        }

        return "";

    }

    public static String getDefinition(String desc) throws OWLOntologyCreationException {
        prepareAcess();

        OWLNamedIndividual ind = factory.getOWLNamedIndividual(IRI.create(uri + desc));
        OWLDataProperty ddef = factory.getOWLDataProperty(IRI.create(uri + "hasDefinition"));

        if (reasoner.getDataPropertyValues(ind, ddef).iterator().hasNext()) {
            return renderer.render(reasoner.getDataPropertyValues(ind, ddef).iterator().next());
        }

        return "";

    }

    public static String getSynonyms(String desc) throws OWLOntologyCreationException {
        prepareAcess();

        OWLNamedIndividual ind = factory.getOWLNamedIndividual(IRI.create(uri + desc));
        OWLDataProperty ddef = factory.getOWLDataProperty(IRI.create(uri + "hasSynonyms"));

        if (reasoner.getDataPropertyValues(ind, ddef).iterator().hasNext()) {
            return renderer.render(reasoner.getDataPropertyValues(ind, ddef).iterator().next());
        }

        return "";

    }

    public static String getFirstAltenatveTopicOfGT(String gt) throws OWLOntologyCreationException {
        prepareAcess();

        OWLNamedIndividual ind = factory.getOWLNamedIndividual(IRI.create(uri + gt));
        OWLObjectProperty p = factory.getOWLObjectProperty(IRI.create(uri + "hasAlternativeTopic"));

        for (OWLNamedIndividual topicInd : reasoner.getObjectPropertyValues(ind, p).getFlattened()) {
            return renderer.render(topicInd);
        }

        return "";

    }

    public static String getAllInfomationAboutTopicInTHML(String topic) throws OWLOntologyCreationException {
        prepareAcess();

        OWLNamedIndividual ind = factory.getOWLNamedIndividual(IRI.create(uri + topic));
        OWLObjectProperty p = factory.getOWLObjectProperty(IRI.create(uri + "hasAlternativeTopicForTMC"));

        String tempS = "<strong class='thick space2'>Name:</strong>"
                + "<strong class='space1'>" + topic + "</strong>";

        boolean testFirst = true;
        for (OWLNamedIndividual indO : reasoner.getObjectPropertyValues(ind, p).getFlattened()) {
            if (testFirst) {
                tempS += "<strong class='thick space2'>Alternative Topics:</strong>"
                        + "<strong class='space1'>" + renderer.render(indO);
                testFirst = false;
            } else {
                tempS += "," + renderer.render(indO);
            }
        }

        if (!testFirst) {
            tempS += ";</strong>";
            testFirst = true;
        }

        p = factory.getOWLObjectProperty(IRI.create(uri + "hasGenericTopic"));
        for (OWLNamedIndividual indO : reasoner.getObjectPropertyValues(ind, p).getFlattened()) {
            tempS += "<strong class='thick space2'>Generic Topic:</strong>"
                    + "<strong class='space1'>" + renderer.render(indO) + ";</strong>";

        }

        p = factory.getOWLObjectProperty(IRI.create(uri + "hasContext"));
        for (OWLNamedIndividual indO : reasoner.getObjectPropertyValues(ind, p).getFlattened()) {
            if (testFirst) {
                tempS += "<strong class='thick space2'>Contexts:</strong>"
                        + "<strong class='space1'>" + renderer.render(indO);
                testFirst = false;
            } else {
                tempS += "," + renderer.render(indO);
            }
        }

        if (!testFirst) {
            tempS += ";</strong>";
            testFirst = true;
        }

        OWLDataProperty d = factory.getOWLDataProperty(IRI.create(uri + "hasTypeConsume"));
        tempS += "<strong class='thick space2'>Type:</strong>"
                + "<strong class='space1'>";
        if (reasoner.getDataPropertyValues(ind, d).iterator().hasNext()) {
            tempS += "Consumer";
        } else {
            tempS += "Producer";
        }
        tempS += ";</strong>";

        return tempS;
    }

    public static String getPragmatic(String topic) throws OWLOntologyCreationException {

        prepareAcess();

        OWLNamedIndividual ind = factory.getOWLNamedIndividual(IRI.create(uri + topic));
        OWLObjectProperty p = factory.getOWLObjectProperty(IRI.create(uri + "hasPragmatic"));

        if (reasoner.getObjectPropertyValues(ind, p).getFlattened().iterator().hasNext()) {
            return renderer.render(reasoner.getObjectPropertyValues(ind, p).getFlattened().iterator().next());
        }

        return "";
    }

    public static List<String> getContextsAndParents(String topic) throws OWLOntologyCreationException {

        prepareAcess();

        List<String> currentContext = getContexts(topic);
        List<String> contexts = new ArrayList<>();

        OWLNamedIndividual ind;
        OWLObjectProperty p1 = factory.getOWLObjectProperty(IRI.create(uri + "isTopContextOf"));
        OWLObjectProperty p2 = factory.getOWLObjectProperty(IRI.create(uri + "hasCategory"));

        for (String c : currentContext) {

            ind = factory.getOWLNamedIndividual(IRI.create(uri + c));
            if (reasoner.getObjectPropertyValues(ind, p2).getFlattened().iterator().hasNext()) {
                contexts.add(renderer.render(reasoner.getObjectPropertyValues(ind, p2).getFlattened().iterator().next()));
                contexts.add(c);
            }

            while (reasoner.getObjectPropertyValues(ind, p1).getFlattened().iterator().hasNext()) {
                String tempContext = renderer.render(reasoner.getObjectPropertyValues(ind, p1).getFlattened().iterator().next());
                ind = factory.getOWLNamedIndividual(IRI.create(uri + tempContext));
                if (reasoner.getObjectPropertyValues(ind, p2).getFlattened().iterator().hasNext()) {
                    contexts.add(renderer.render(reasoner.getObjectPropertyValues(ind, p2).getFlattened().iterator().next()));
                    contexts.add(tempContext);
                }
            }
        }

        return contexts;
    }

    public static List<String[]> getRule(String pragmatic) throws OWLOntologyCreationException {

        prepareAcess();

        List<String[]> rules = new ArrayList<>();
        OWLNamedIndividual ind = factory.getOWLNamedIndividual(IRI.create(uri + pragmatic));
        OWLObjectProperty p1 = factory.getOWLObjectProperty(IRI.create(uri + "hasRule"));
        OWLObjectProperty p2 = factory.getOWLObjectProperty(IRI.create(uri + "hasIntentionTopic"));

        OWLNamedIndividual ind2;
        String r, t;
        for (OWLNamedIndividual rule : reasoner.getObjectPropertyValues(ind, p1).getFlattened()) {
            ind2 = factory.getOWLNamedIndividual(IRI.create(uri + renderer.render(rule)));

            r = getFirstNameOfIndividualClassForRules(renderer.render(rule));

            t = renderer.render(reasoner.getObjectPropertyValues(ind2, p2).getFlattened().iterator().next());
            rules.add(new String[]{r, t});

        }

        return rules;
    }

    public static List<String> getRuleWithoutIntention(String pragmatic) throws OWLOntologyCreationException {

        prepareAcess();

        List<String> rules = new ArrayList<>();
        OWLNamedIndividual ind = factory.getOWLNamedIndividual(IRI.create(uri + pragmatic));
        OWLObjectProperty p = factory.getOWLObjectProperty(IRI.create(uri + "hasRule"));

        OWLNamedIndividual ind2;
        for (OWLNamedIndividual rule : reasoner.getObjectPropertyValues(ind, p).getFlattened()) {
            rules.add(getFirstNameOfIndividualClassForRules(renderer.render(rule)));
        }

        return rules;
    }

    public static List<GenericModel1> similarTopic(String t) throws OWLOntologyCreationException {
        prepareAcess();

        List<GenericModel1> topics = new ArrayList<>();

        OWLNamedIndividual ind = factory.getOWLNamedIndividual(IRI.create(uri + t));
        OWLObjectProperty p = factory.getOWLObjectProperty(IRI.create(uri + "isSimilar"));

        for (OWLNamedIndividual topicInd : reasoner.getObjectPropertyValues(ind, p).getFlattened()) {
            if (renderer.render(topicInd).compareTo(t) != 0) {
                topics.add(new GenericModel1(renderer.render(topicInd)));
            }
        }

        return topics;

    }

    public static GenericModel3 getInformationAboutAlternativeTopic(String t) throws OWLOntologyCreationException {
        prepareAcess();

        String genericTopic = getGenericTopicOfAT(t);
        String description = getDescription(genericTopic);

        GenericModel3 topic = new GenericModel3(t, genericTopic);

        if (description.isBlank()) {
            return topic;
        }

        String primaryTerms = getTerms(description);
        String primaryDefinition = getDefinition(description);
        String primarySynonyms = getDefinition(description);

        topic.setPrimaryDescription(primaryTerms, primaryDefinition, primarySynonyms);

        List<String> similarDescriptions = getSimilarDescriptions(description);

        for (String sDesc : similarDescriptions) {
            topic.setSecondaryDescription(getTerms(sDesc), getDefinition(sDesc), getDefinition(sDesc));
        }

        return topic;

    }

    private static String getDescription(String genericTopic) throws OWLOntologyCreationException {

        prepareAcess();

        OWLNamedIndividual ind = factory.getOWLNamedIndividual(IRI.create(uri + genericTopic));
        OWLObjectProperty p = factory.getOWLObjectProperty(IRI.create(uri + "hasDescription"));

        if (reasoner.getObjectPropertyValues(ind, p).getFlattened().iterator().hasNext()) {
            return renderer.render(reasoner.getObjectPropertyValues(ind, p).getFlattened().iterator().next());
        }

        return "";
    }

    private static List<String> getSimilarDescriptions(String desc) throws OWLOntologyCreationException {

        List<String> descs = new ArrayList<>();

        OWLNamedIndividual ind = factory.getOWLNamedIndividual(IRI.create(uri + desc));
        OWLObjectProperty p = factory.getOWLObjectProperty(IRI.create(uri + "hasSimilarDescription"));

        for (OWLNamedIndividual nInd : reasoner.getObjectPropertyValues(ind, p).getFlattened()) {
            if (renderer.render(nInd).compareTo(desc) != 0) {
                descs.add(renderer.render(nInd));
            }
        }

        return descs;

    }

    public static List<String> getSuperContextOfTopic(String t) throws OWLOntologyCreationException {

        List<String> sc = new ArrayList<>();

        OWLNamedIndividual ind = factory.getOWLNamedIndividual(IRI.create(uri + t));
        OWLObjectProperty p = factory.getOWLObjectProperty(IRI.create(uri + "hasSuperContext"));

        for (OWLNamedIndividual nInd : reasoner.getObjectPropertyValues(ind, p).getFlattened()) {
            if (renderer.render(nInd).compareTo(t) != 0) {
                sc.add(renderer.render(nInd));
            }
        }

        p = factory.getOWLObjectProperty(IRI.create(uri + "hasSuperContext2"));

        for (OWLNamedIndividual nInd : reasoner.getObjectPropertyValues(ind, p).getFlattened()) {
            if (renderer.render(nInd).compareTo(t) != 0) {
                sc.add(renderer.render(nInd));
            }
        }

        return sc;

    }

    public static GenericModel4 getSuperContextOfALternativeTopic(String t) throws OWLOntologyCreationException {

        String firstSpecificTopic = getFirstSpecificTopic(t);

        GenericModel4 topic = new GenericModel4(t);

        if (firstSpecificTopic.isBlank()) {
            return topic;
        }

        OWLNamedIndividual ind = factory.getOWLNamedIndividual(IRI.create(uri + firstSpecificTopic));
        OWLObjectProperty p = factory.getOWLObjectProperty(IRI.create(uri + "hasSuperContext"));

        for (OWLNamedIndividual nInd : reasoner.getObjectPropertyValues(ind, p).getFlattened()) {
            if (renderer.render(nInd).compareTo(t) != 0) {
                topic.setSuperContext(renderer.render(nInd));
            }
        }

        p = factory.getOWLObjectProperty(IRI.create(uri + "hasSuperContext2"));

        for (OWLNamedIndividual nInd : reasoner.getObjectPropertyValues(ind, p).getFlattened()) {
            if (renderer.render(nInd).compareTo(t) != 0) {
                topic.setSuperContext(renderer.render(nInd));
            }
        }

        return topic;

    }

    public static GenericModel5 getSuperContextChildren(String s) throws OWLOntologyCreationException {

        prepareAcess();

        GenericModel5 sc = new GenericModel5(s);

        OWLNamedIndividual ind = factory.getOWLNamedIndividual(IRI.create(uri + s));
        OWLObjectProperty p = factory.getOWLObjectProperty(IRI.create(uri + "hasTopContext"));

        for (OWLNamedIndividual nInd : reasoner.getObjectPropertyValues(ind, p).getFlattened()) {
            sc.setChild(setChildren(renderer.render(nInd)));
        }

        return sc;

    }

    public static String getTopContextCategory(String sc) throws OWLOntologyCreationException {

        prepareAcess();

        OWLNamedIndividual ind = factory.getOWLNamedIndividual(IRI.create(uri + sc));
        OWLObjectProperty p = factory.getOWLObjectProperty(IRI.create(uri + "hasTopContextCategory"));

        for (OWLNamedIndividual nInd : reasoner.getObjectPropertyValues(ind, p).getFlattened()) {
            return renderer.render(nInd);
        }

        return "";

    }

    public static String getChildCategory(String c) throws OWLOntologyCreationException {

        prepareAcess();

        OWLNamedIndividual ind = factory.getOWLNamedIndividual(IRI.create(uri + c));
        OWLObjectProperty p = factory.getOWLObjectProperty(IRI.create(uri + "IsParentCategoryOf"));

        for (OWLNamedIndividual nInd : reasoner.getObjectPropertyValues(ind, p).getFlattened()) {
            return renderer.render(nInd);
        }

        return "";
    }

    public static GenericModel7 getAllContextGroup(String t) throws OWLOntologyCreationException {

        prepareAcess();

        GenericModel7 term = new GenericModel7(t);

        List<String> TMCs = getSpecificTopic(t);

        OWLNamedIndividual ind;
        OWLObjectProperty p = factory.getOWLObjectProperty(IRI.create(uri + "hasContext"));

        List<String> tempContexts;
        for (String s : TMCs) {
            tempContexts = new ArrayList<>();
            ind = factory.getOWLNamedIndividual(IRI.create(uri + s));
            for (OWLNamedIndividual nInd : reasoner.getObjectPropertyValues(ind, p).getFlattened()) {
                tempContexts.add(renderer.render(nInd));
            }

            term.setContextGroup(tempContexts);

        }

        return term;

    }

    public static String getFileOfTerm(String t) throws FileNotFoundException {

        t = t.toLowerCase();

        File file = new File(projectDir + "\\Files\\Module2.ExternalTerms\\" + t + ".json");

        if (file.exists()) {
            Scanner reader = new Scanner(file);
            return reader.nextLine();
        }

        return "";
    }

    private static GenericModel6 setChildren(String c) throws OWLOntologyCreationException {

        OWLNamedIndividual ind = factory.getOWLNamedIndividual(IRI.create(uri + c));
        OWLObjectProperty p = factory.getOWLObjectProperty(IRI.create(uri + "hasSubContext"));

        List<GenericModel6> children = new ArrayList<>();
        GenericModel6 child = new GenericModel6(getCategoryOfContext(c), c);

        for (OWLNamedIndividual nInd : reasoner.getObjectPropertyValues(ind, p).getFlattened()) {
            child.setChild(setChildren(renderer.render(nInd)));

        }

        return child;
    }

    public static GenericModel8 getPragmaticRulesForAltenativeTopicAndContext(List<String> inf) throws OWLOntologyCreationException {

        prepareAcess();

        GenericModel8 term = new GenericModel8(inf.get(0));

        for (int i = 1; i < inf.size(); i++) {
            term.setContexts(inf.get(i));
        }

        List<String> TMCs = getSpecificTopic(term.name);

        OWLNamedIndividual ind;
        OWLObjectProperty p1 = factory.getOWLObjectProperty(IRI.create(uri + "hasPragmatic"));
        OWLObjectProperty p2 = factory.getOWLObjectProperty(IRI.create(uri + "hasContext"));

        for (String s : TMCs) {
            ind = factory.getOWLNamedIndividual(IRI.create(uri + s));
            if (reasoner.getObjectPropertyValues(ind, p1).getFlattened().iterator().hasNext()) {
                String pragmatic = renderer.render(reasoner.getObjectPropertyValues(ind, p1).getFlattened().iterator().next());
                boolean test = false;
                for (String c : term.contexts) {
                    for (OWLNamedIndividual nInd : reasoner.getObjectPropertyValues(ind, p2).getFlattened()) {
                        if (renderer.render(nInd).compareTo(c) == 0) {
                            test = true;
                            break;
                        }

                    }
                    if (!test) {
                        break;
                    }
                }

                if (test) {
                    term.setRules(getRuleWithoutIntention(pragmatic));
                }
            }
        }

        return term;

    }

}
