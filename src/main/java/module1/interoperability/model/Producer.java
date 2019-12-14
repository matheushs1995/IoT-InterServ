package module1.interoperability.model;

import module1.dao.IoTDescriptionDAO;
import module1.dao.IoTTopicsMoreContextDAO;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

public class Producer {

    private static final List<Producer> producers = new ArrayList<>();
    
    private String id;
    private String name;
    private String topic;
    private String unit;
    private Pragmatic pragmatic;

    public Producer(String i){
        id = i;
    }

    public static String createANewProduceIfThisNotExist(String i) throws OWLOntologyCreationException, InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException{
  
        if (!verifyIfListContainProducer(i)){
            Producer p = new Producer(i);
            p.setName();
            p.setTopic();
            p.setUnit();
            p.setPragmatic();
            p.setMapRule();
            producers.add(p);
        }
        
        return "OK";
    }

    private static boolean verifyIfListContainProducer(String s) {
        for (Producer p : producers){
            if (p.id.compareTo(s) == 0){
                return true;
            }
        }
        return false;
    }

    public static void removeProducer(String i) {
        for (int j = 0; j < producers.size(); j++){
            if (producers.get(j).id.compareTo(i) == 0){
                producers.get(j).pragmatic.decreaseUseOfPragmatic();
                producers.remove(j);

            }
        }
    }

    private void setMapRule(){
        Map<String, Object> props = new HashMap<>();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, topic);
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        StreamsConfig config = new StreamsConfig(props);

        StreamsBuilder builder = new StreamsBuilder();

        builder.<String, String>stream(id).mapValues(value -> intention(value)).to(topic);

        KafkaStreams streams = new KafkaStreams(builder.build(), config);
        streams.start();
    }

    private String intention(String value) {
        
        if(pragmatic==null) return unit+","+value;
        
        for(int i=0; i< pragmatic.getFts().size();i++){
            pragmatic.getFts().get(i).function(pragmatic.getValues().get(i),name, pragmatic.getTopics().get(i), unit, value);
        }
        
        return unit+","+value;
    }

    private void setTopic() throws OWLOntologyCreationException {
        topic = IoTDescriptionDAO.getTopic(id);
    }
    
    private void setPragmatic() throws OWLOntologyCreationException, InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException{
        pragmatic = Pragmatic.getPragmaticObject(IoTTopicsMoreContextDAO.getPragmatic(topic));
    }

    private void setUnit() throws OWLOntologyCreationException {
        unit = IoTDescriptionDAO.getUnit(id);
    }

    private void setName() throws OWLOntologyCreationException {
       name = IoTDescriptionDAO.getName(id);
    }
    
    
    
    

}
