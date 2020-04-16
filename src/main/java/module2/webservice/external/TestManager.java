package module2.webservice.external;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.FileNotFoundException;
import java.util.List;
import module2.dao.IoTDescriptionDAO;
import module2.dao.IoTTopicsMoreContextDAO;
import module2.model.*;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

public class TestManager {

    public static void main(String[] args) throws OWLOntologyCreationException, JsonProcessingException, FileNotFoundException {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        // Sintático
        System.out.println("\n\n\n\n\n\n");
        System.out.println("==============================================================================================================================================");

        List<GenericModel1> units = IoTDescriptionDAO.sameUnitGt1("Celsius");
        String arrayToJson = objectMapper.writeValueAsString(units);
        System.out.println(arrayToJson);

        System.out.println("==============================================================================================================================================");

        List<GenericModel2> units2 = IoTDescriptionDAO.sameUnitGt2("Celsius");
        arrayToJson = objectMapper.writeValueAsString(units2);
        System.out.println(arrayToJson);

        System.out.println("==============================================================================================================================================");

        System.out.println("[ { function : " + IoTDescriptionDAO.getFunction("Celsius", "Kelvin") + " }]");

        System.out.println("==============================================================================================================================================");

        //Semântico
        List<GenericModel1> topics = IoTTopicsMoreContextDAO.similarTopic("Temp");
        arrayToJson = objectMapper.writeValueAsString(topics);
        System.out.println(arrayToJson);

        System.out.println("==============================================================================================================================================");

        GenericModel3 topic = IoTTopicsMoreContextDAO.getInformationAboutAlternativeTopic("Pressure");
        arrayToJson = objectMapper.writeValueAsString(topic);
        System.out.println(arrayToJson);

        System.out.println("==============================================================================================================================================");

        GenericModel4 topic2 = IoTTopicsMoreContextDAO.getSuperContextOfALternativeTopic("Temp");
        arrayToJson = objectMapper.writeValueAsString(topic2);
        System.out.println(arrayToJson);

        System.out.println("==============================================================================================================================================");

        GenericModel5 superContext = IoTTopicsMoreContextDAO.getSuperContextChildren("Localization");
        arrayToJson = objectMapper.writeValueAsString(superContext);
        System.out.println(arrayToJson);

        System.out.println("==============================================================================================================================================");

        GenericModel7 allContextGroup = IoTTopicsMoreContextDAO.getAllContextGroup("Temp");
        arrayToJson = objectMapper.writeValueAsString(allContextGroup);
        System.out.println(arrayToJson);

        System.out.println("==============================================================================================================================================");

        System.out.println(IoTTopicsMoreContextDAO.getFileOfTerm("press"));

        System.out.println("==============================================================================================================================================");
        
        GenericModel9  similarityB2Words = new GenericModel9("Pressure", "Press");
        arrayToJson = objectMapper.writeValueAsString(similarityB2Words);
        System.out.println(arrayToJson); //GetSimilarityBetweenTwoWords
        
        System.out.println("==============================================================================================================================================");
        
        //Pragmático
        //GenericModel8 termRules = IoTTopicsMoreContextDAO.getPragmaticRulesForAltenativeTopicAndContext("Temp,Person,PowerPlant,Chile");
        //arrayToJson = objectMapper.writeValueAsString(termRules);
        //System.out.println(arrayToJson);

    }

}
