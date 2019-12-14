package module1.dao;

import java.io.File;
import java.util.List;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.OWLObjectRenderer;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.reasoner.SimpleConfiguration;
import org.semanticweb.owlapi.model.*;
import com.clarkparsia.pellet.owlapiv3.PelletReasonerFactory;
import java.util.ArrayList;
import uk.ac.manchester.cs.owlapi.dlsyntax.DLSyntaxObjectRenderer;

public class IoTTopicsMoreContextDAO {

    static File file = new File("C:/Users/mathe/Documents/Dropbox/Projetos/PragmaticIoT-V2/files/IoTTopicsMoreContext.owl");
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
    
    public static List<String> getTopicForConsume(String topic) throws OWLOntologyCreationException {

        prepareAcess();

        OWLNamedIndividual ind = factory.getOWLNamedIndividual(IRI.create(uri + topic));
        OWLDataProperty d = factory.getOWLDataProperty(IRI.create(uri + "hasTypeConsume"));

        String type = renderer.render(reasoner.getDataPropertyValues(ind, d).iterator().next());

        if (type.compareTo("") == 0) {
            return null;
        }

        if (type.compareTo("1") == 0) {
            return getTopicForConsumeTypeCNRO(topic); // Context Not Restricted and Open
        }
        if (type.compareTo("2") == 0) {
            return getTopicForConsumeTypeCNRC(topic); // Context Not Restricted and Closed
        }
        if (type.compareTo("3") == 0) {
            return getTopicForConsumeTypeCRO(topic); // Context Restricted and Open
        }
        return getTopicForConsumeTypeCRC(topic); // Context Restricted and Closed
    }

    private static List<String> getContextsOfTopic(String topic) throws OWLOntologyCreationException {

        List<String> listContexts = new ArrayList<>();

        OWLNamedIndividual ind = factory.getOWLNamedIndividual(IRI.create(uri + topic));
        OWLObjectProperty p = factory.getOWLObjectProperty(IRI.create(uri + "hasContext"));

        for (OWLNamedIndividual topicInd : reasoner.getObjectPropertyValues(ind, p).getFlattened()) {
            listContexts.add(renderer.render(topicInd));
        }
        return listContexts;
    }

    private static List<String> getTopicForConsumeTypeCNRO(String topic) throws OWLOntologyCreationException {
        List<String> listContexts = getContextsOfTopic(topic);
        List<String> listContextsCopy;
        List<String> choosedProducers = new ArrayList<>();

        OWLClass producers = factory.getOWLClass(IRI.create(uri + "Producer"));
        OWLObjectProperty p1 = factory.getOWLObjectProperty(IRI.create(uri + "hasContext"));
        OWLObjectProperty p2 = factory.getOWLObjectProperty(IRI.create(uri + "hasSubContext"));
        OWLObjectProperty p3 = factory.getOWLObjectProperty(IRI.create(uri + "hasGenericContext"));
        String tempS;

        for (OWLNamedIndividual producer : reasoner.getInstances(producers, false).getFlattened()) {
            if (renderer.render(reasoner.getObjectPropertyValues(producer, p3).getFlattened().iterator().next())
                    .compareTo(topic) == 0) {
                listContextsCopy = new ArrayList<>(listContexts);
                for (OWLNamedIndividual context : reasoner.getObjectPropertyValues(producer, p1).getFlattened()) {
                    tempS = renderer.render(context);
                    for (int i = 0; i < listContextsCopy.size(); i++) {
                        if (tempS.compareTo(listContextsCopy.get(i)) == 0) {
                            listContextsCopy.remove(i);
                            break;
                        }
                    }
                    for (int i = 0; i < listContextsCopy.size(); i++) {
                        if (isSubContextOf(listContextsCopy.get(i), tempS)) {
                            listContextsCopy.remove(i);
                            break;
                        }
                    }
                }
                if (listContextsCopy.isEmpty()) {
                    choosedProducers.add(renderer.render(producer));
                }
            }
        }

        return choosedProducers;
    }

    private static List<String> getTopicForConsumeTypeCNRC(String topic) throws OWLOntologyCreationException {
        List<String> listContexts = getContextsOfTopic(topic);
        List<String> listContextsCopy;
        List<String> choosedProducers = new ArrayList<>();

        OWLClass producers = factory.getOWLClass(IRI.create(uri + "Producer"));
        OWLObjectProperty p1 = factory.getOWLObjectProperty(IRI.create(uri + "hasContext"));
        OWLObjectProperty p2 = factory.getOWLObjectProperty(IRI.create(uri + "hasSubContext"));
        OWLObjectProperty p3 = factory.getOWLObjectProperty(IRI.create(uri + "hasGenericContext"));
        String tempS;

        for (OWLNamedIndividual producer : reasoner.getInstances(producers, false).getFlattened()) {
            if (renderer.render(reasoner.getObjectPropertyValues(producer, p3).getFlattened().iterator().next())
                    .compareTo(topic) == 0) {
                listContextsCopy = new ArrayList<>(listContexts);
                for (OWLNamedIndividual context : reasoner.getObjectPropertyValues(producer, p1).getFlattened()) {
                    tempS = renderer.render(context);
                    for (int i = 0; i < listContextsCopy.size(); i++) {
                        if (tempS.compareTo(listContextsCopy.get(i)) == 0) {
                            listContextsCopy.remove(i);
                            break;
                        }
                    }
                }

                if (listContextsCopy.isEmpty()) {
                    choosedProducers.add(renderer.render(producer));
                }

            }

        }

        return choosedProducers;
    }

    private static List<String> getTopicForConsumeTypeCRO(String topic) throws OWLOntologyCreationException {
        List<String> listContexts = getContextsOfTopic(topic);
        List<String> listContextsCopy;
        List<String> choosedProducers = new ArrayList<>();

        OWLClass producers = factory.getOWLClass(IRI.create(uri + "Producer"));
        OWLObjectProperty p1 = factory.getOWLObjectProperty(IRI.create(uri + "hasContext"));
        OWLObjectProperty p2 = factory.getOWLObjectProperty(IRI.create(uri + "hasSubContext"));
        OWLObjectProperty p3 = factory.getOWLObjectProperty(IRI.create(uri + "hasGenericContext"));
        String tempS;
        int tempI;

        for (OWLNamedIndividual producer : reasoner.getInstances(producers, false).getFlattened()) {
            if (renderer.render(reasoner.getObjectPropertyValues(producer, p3).getFlattened().iterator().next())
                    .compareTo(topic) == 0) {
                listContextsCopy = new ArrayList<>(listContexts);
                for (OWLNamedIndividual context : reasoner.getObjectPropertyValues(producer, p1).getFlattened()) {
                    tempS = renderer.render(context);
                    tempI = 0;
                    for (int i = 0; i < listContextsCopy.size(); i++) {
                        if (tempS.compareTo(listContextsCopy.get(i)) == 0) {
                            listContextsCopy.remove(i);
                            tempI = 1;
                            break;
                        }
                    }

                    if (tempI == 0) {
                        for (int i = 0; i < listContextsCopy.size(); i++) {
                            if (isSubContextOf(listContextsCopy.get(i), tempS)) {
                                listContextsCopy.remove(i);
                                tempI = 1;
                                break;
                            }
                        }
                    }

                    if (tempI == 0) {
                        break;
                    }
                }

                if (listContextsCopy.isEmpty()) {
                    choosedProducers.add(renderer.render(producer));
                }

            }
        }

        return choosedProducers;
    }

    private static List<String> getTopicForConsumeTypeCRC(String topic) throws OWLOntologyCreationException {
        List<String> listContexts = getContextsOfTopic(topic);
        List<String> listContextsCopy;
        List<String> choosedProducers = new ArrayList<>();

        OWLClass producers = factory.getOWLClass(IRI.create(uri + "Producer"));
        OWLObjectProperty p1 = factory.getOWLObjectProperty(IRI.create(uri + "hasContext"));
        OWLObjectProperty p2 = factory.getOWLObjectProperty(IRI.create(uri + "hasSubContext"));
        OWLObjectProperty p3 = factory.getOWLObjectProperty(IRI.create(uri + "hasGenericContext"));
        String tempS;
        int tempI;

        for (OWLNamedIndividual producer : reasoner.getInstances(producers, false).getFlattened()) {
            if (renderer.render(reasoner.getObjectPropertyValues(producer, p3).getFlattened().iterator().next())
                    .compareTo(topic) == 0) {
                listContextsCopy = new ArrayList<>(listContexts);
                for (OWLNamedIndividual context : reasoner.getObjectPropertyValues(producer, p1).getFlattened()) {
                    tempS = renderer.render(context);
                    tempI = 0;
                    for (int i = 0; i < listContextsCopy.size(); i++) {
                        if (tempS.compareTo(listContextsCopy.get(i)) == 0) {
                            listContextsCopy.remove(i);
                            tempI = 1;
                            break;
                        }
                    }
                    if (tempI == 0) {
                        break;
                    }
                }

                if (listContextsCopy.isEmpty()) {
                    choosedProducers.add(renderer.render(producer));
                }
            }

        }

        return choosedProducers;
    }

    private static boolean isSubContextOf(String isSubC, String isSuperC) {

        OWLNamedIndividual ind = factory.getOWLNamedIndividual(IRI.create(uri + isSubC));
        OWLObjectProperty p = factory.getOWLObjectProperty(IRI.create(uri + "isSubContextOf"));

        for (OWLNamedIndividual superC : reasoner.getObjectPropertyValues(ind, p).getFlattened()) {
            if (renderer.render(superC).compareTo(isSuperC) == 0) {
                return true;
            }
        }

        return false;

    }

    public static String getId(String pragmatic) throws OWLOntologyCreationException {

        prepareAcess();

        OWLNamedIndividual ind = factory.getOWLNamedIndividual(IRI.create(uri + pragmatic));
        OWLDataProperty d = factory.getOWLDataProperty(IRI.create(uri + "hasId"));

        return renderer.render(reasoner.getDataPropertyValues(ind, d).iterator().next());

    }

    public static List<String[]> getRule(String pragmatic) throws OWLOntologyCreationException {

        prepareAcess();

        List<String[]> rules = new ArrayList<>();
        OWLNamedIndividual ind = factory.getOWLNamedIndividual(IRI.create(uri + pragmatic));
        OWLObjectProperty p1 = factory.getOWLObjectProperty(IRI.create(uri + "hasRule"));
        OWLObjectProperty p2 = factory.getOWLObjectProperty(IRI.create(uri + "hasIntentionTopic"));
        
        OWLNamedIndividual ind2;
        String r,t;
        for (OWLNamedIndividual rule : reasoner.getObjectPropertyValues(ind, p1).getFlattened()) {
            renderer.render(rule);
            ind2 = factory.getOWLNamedIndividual(IRI.create(uri + rule));

            r = renderer.render(reasoner.getTypes(ind, false).getFlattened().iterator().next());
            t = renderer.render(reasoner.getObjectPropertyValues(ind, p2).getFlattened().iterator().next());
            rules.add(new String[]{r,t});
                    
        }

        return rules;
    }

    public static String getPragmatic(String topic) throws OWLOntologyCreationException {

        prepareAcess();

        OWLNamedIndividual ind = factory.getOWLNamedIndividual(IRI.create(uri + topic));
        OWLObjectProperty p = factory.getOWLObjectProperty(IRI.create(uri + "hasPragmatic"));

        return renderer.render(reasoner.getObjectPropertyValues(ind, p).getFlattened().iterator().next());
    }

    public static String getTopicOfID(String id) throws OWLOntologyCreationException {
       
        prepareAcess();

        OWLNamedIndividual ind = factory.getOWLNamedIndividual(IRI.create(uri + id));
        OWLObjectProperty p = factory.getOWLObjectProperty(IRI.create(uri + "hasTopicMoreContextByID"));

        return renderer.render(reasoner.getObjectPropertyValues(ind, p).getFlattened().iterator().next());
    }

    

}
