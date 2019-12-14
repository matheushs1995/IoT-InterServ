
package module1.interoperability.manager;

import module1.dao.IoTDescriptionDAO;
import java.util.Collections;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import module1.interoperability.conector.Conector;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

public class ConsumerManager {
    static Properties props;
    static KafkaProducer<String, String> producer;
    static KafkaConsumer<String, String> consumerP;
    static KafkaConsumer<String, String> consumerC;
    

    public static void setProperties(){
        setProducer();
        setConsumer();
    }
    
    public static void eventPrepareConsumer() {
        consumerP.subscribe(Collections.singletonList(PrincipalManager.getServer()+"/PrepareConsumer"));

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    ConsumerRecords<String, String> records = consumerP.poll(100);
                    for (ConsumerRecord<String, String> record : records) {
                        
                        try {
                            returnResponsePrepareConsumer(record.value());
                        } catch (OWLOntologyCreationException ex) {
                            Logger.getLogger(ConsumerManager.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                    }
                }
            }

        }.start();
    }
    
    public static void eventGetFunctionsforConvert() {
        consumerC.subscribe(Collections.singletonList(PrincipalManager.getServer()+"/GetFunctionsforConvert"));

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    ConsumerRecords<String, String> records = consumerC.poll(100);
                    for (ConsumerRecord<String, String> record : records) {
                        try {
                            returnResponseGetFunctionsforConvert(record.value());
                        } catch (OWLOntologyCreationException ex) {
                            Logger.getLogger(ConsumerManager.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }

        }.start();
    }
    
    private static void setProducer() {
        props = new Properties();
        props.put("bootstrap.servers", PrincipalManager.getServer());
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<>(props);
    }

    private static void setConsumer(){
        props = new Properties();
        props.put("bootstrap.servers", PrincipalManager.getServer());
        props.put("group.id", "consumer");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("auto.offset.reset", "latest");
        props.put("max.poll.records", "1");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumerP = new KafkaConsumer<>(props);
        consumerC = new KafkaConsumer<>(props);
    }
    
    private static void returnResponsePrepareConsumer(String IS) throws OWLOntologyCreationException { 
        Conector.send(IS+"-PrepareConsumer",IoTDescriptionDAO.getTopicForConsumeSimpleMethod(IoTDescriptionDAO.getTopic(IS)));
    }
    
    private static void returnResponseGetFunctionsforConvert(String IS) throws OWLOntologyCreationException { 
        Conector.send(IS+"-GetFunctionsforConvert",IoTDescriptionDAO.getUnitsAndFunctionsForConvert(IS));
    }
}
