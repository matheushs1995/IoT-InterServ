package module1.interoperability.manager;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import module1.interoperability.model.Producer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

public class ProducerManager {

    static Properties props;
    static KafkaProducer<String, String> producer;
    static KafkaConsumer<String, String> consumerP;
    static KafkaConsumer<String, String> consumerC;

    public static void setProperties() {
        setProducer();
        setConsumer();
    }

    public static void eventPrepareProducer() {
        consumerP.subscribe(Collections.singletonList(PrincipalManager.getServer()+"/PrepareProducer"));

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    ConsumerRecords<String, String> records = consumerP.poll(100);
                    for (ConsumerRecord<String, String> record : records) {
                        String s = record.value();
                        try {
                            returnResponse(s);
                        } catch (OWLOntologyCreationException ex) {
                            Logger.getLogger(ProducerManager.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (InstantiationException ex) {
                            Logger.getLogger(ProducerManager.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IllegalAccessException ex) {
                            Logger.getLogger(ProducerManager.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (NoSuchMethodException ex) {
                            Logger.getLogger(ProducerManager.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IllegalArgumentException ex) {
                            Logger.getLogger(ProducerManager.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (InvocationTargetException ex) {
                            Logger.getLogger(ProducerManager.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }

        }.start();
    }

    public static void eventCloseProducer() {
        consumerC.subscribe(Collections.singletonList(PrincipalManager.getServer()+"/CloseProducer"));

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    ConsumerRecords<String, String> records = consumerC.poll(100);
                    for (ConsumerRecord<String, String> record : records) {
                        Producer.removeProducer(record.value());
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

    private static void setConsumer() {
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

    private static void returnResponse(String IS) throws OWLOntologyCreationException, InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
        String r = Producer.createANewProduceIfThisNotExist(IS);
        producer.send(new ProducerRecord<>(IS, r));
    }
    
}
