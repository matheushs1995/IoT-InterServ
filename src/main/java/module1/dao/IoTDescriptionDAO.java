package module1.dao;

import java.io.File;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.OWLObjectRenderer;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.reasoner.SimpleConfiguration;
import org.semanticweb.owlapi.model.*;
import com.clarkparsia.pellet.owlapiv3.PelletReasonerFactory;
import java.util.List;
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

    private static void prepareOWLApi() throws OWLOntologyCreationException {

        manager = OWLManager.createOWLOntologyManager();
        ontology = manager.loadOntologyFromOntologyDocument(file);
        reasonerFactory = PelletReasonerFactory.getInstance();
        renderer = new DLSyntaxObjectRenderer();
        reasoner = reasonerFactory.createReasoner(ontology, new SimpleConfiguration());
        factory = manager.getOWLDataFactory();
        lastModification = file.lastModified();
    }

    public static String getTopic(String IoT) throws OWLOntologyCreationException {

        prepareAcess();

        OWLNamedIndividual ind = factory.getOWLNamedIndividual(IRI.create(uri + IoT));
        OWLObjectProperty p = factory.getOWLObjectProperty(IRI.create(uri + "hasTopicMoreContext"));

        String s = renderer.render(reasoner.getObjectPropertyValues(ind, p).getFlattened().iterator().next());

        return s;
    }

    public static String getUnitsAndFunctionsForConvert(String id) throws OWLOntologyCreationException {

        prepareAcess();

        OWLNamedIndividual ind = factory.getOWLNamedIndividual(IRI.create(uri + id));
        OWLObjectProperty p = factory.getOWLObjectProperty(IRI.create(uri + "ThisIdentificationHasUnit"));

        String unit = renderer.render(reasoner.getObjectPropertyValues(ind, p).getFlattened().iterator().next());

        if (unit.compareTo("") == 0) {
            return "inf";
        }

        p = factory.getOWLObjectProperty(IRI.create(uri + "hasTopicMoreContextByID"));

        String topic = renderer.render(reasoner.getObjectPropertyValues(ind, p).getFlattened().iterator().next());

        ind = factory.getOWLNamedIndividual(IRI.create(uri + topic));
        p = factory.getOWLObjectProperty(IRI.create(uri + "hasUnitTopic"));

        String unitsAndFunctions = "";

        for (OWLNamedIndividual unitInd : reasoner.getObjectPropertyValues(ind, p).getFlattened()) {
            if (renderer.render(unitInd).compareTo(unit) != 0) {
                if (unitsAndFunctions.compareTo("") != 0) {
                    unitsAndFunctions += ";";
                }
                unitsAndFunctions += getUnitAndFunction(renderer.render(unitInd), unit);
            }
        }

        return unitsAndFunctions;
    }

    public static String getUnit(String id) throws OWLOntologyCreationException {

        prepareAcess();

        OWLNamedIndividual ind = factory.getOWLNamedIndividual(IRI.create(uri + id));
        OWLObjectProperty p = factory.getOWLObjectProperty(IRI.create(uri + "thisIdentificationHasUnit"));

        return renderer.render(reasoner.getObjectPropertyValues(ind, p).getFlattened().iterator().next());

    }

    public static String getTopicForConsumeSimpleMethod(String topic) throws OWLOntologyCreationException {
        prepareAcess();

        if (topic.compareTo("") == 0) {
            return "inf";
        }

        OWLNamedIndividual ind = factory.getOWLNamedIndividual(IRI.create(uri + topic));
        OWLObjectProperty p = factory.getOWLObjectProperty(IRI.create(uri + "hasTopicForConsume"));

        String temp = "";
        for (OWLNamedIndividual topicInd : reasoner.getObjectPropertyValues(ind, p).getFlattened()) {
            if (temp.compareTo("") != 0) {
                temp += ",";
            }
            temp += renderer.render(topicInd);
        }
        return temp;
    }

    public static List<String> getTopicForConsume(String IoT) throws OWLOntologyCreationException {
        return IoTTopicsMoreContextDAO.getTopicForConsume(getTopic(IoT));
    }

    public static String getName(String id) throws OWLOntologyCreationException {

        prepareAcess();

        OWLNamedIndividual ind = factory.getOWLNamedIndividual(IRI.create(uri + id));
        OWLObjectProperty p = factory.getOWLObjectProperty(IRI.create(uri + "isIdentificationOf"));

        return renderer.render(reasoner.getObjectPropertyValues(ind, p).getFlattened().iterator().next());
    }

    private static String getUnitAndFunction(String unitSource, String unitTarget) {

        OWLNamedIndividual ind = factory.getOWLNamedIndividual(IRI.create(uri + unitSource + "To" + unitTarget));
        OWLDataProperty d = factory.getOWLDataProperty(IRI.create(uri + "hasFunction"));

        String function = renderer.render(reasoner.getDataPropertyValues(ind, d).iterator().next());

        return unitTarget + "," + function;
    }

    

}
