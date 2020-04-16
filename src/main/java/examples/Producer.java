package examples;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

public class Producer {

    Properties props;
    KafkaProducer<String, String> producer;
    KafkaConsumer<String, String> consumer;
    String IoTSensor;
    static final String serverID = "001";

    public void setProperties() {
        setProducer();
        setConsumer();
    }

    public Producer(String i) {
        IoTSensor = i;
    }

    public void start() throws OWLOntologyCreationException, InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException, IOException {
        setProperties();

        String s = getResponse("PrepareProducer");

        if (!testResponse(s)) {
            return;
        }

        System.out.println("Producer: start");
        startProducer();
    }

    private static boolean testResponse(String s) {
        if (s.compareTo("") == 0) {
            System.out.println("Blank message !!!");
            return false;
        }
        if (s.compareTo("inf") == 0) {
            System.out.println("identify not found !!!");
            return false;
        }

        if (s.compareTo("error") == 0) {
            System.out.println("error to prepare producer !!!");
            return false;
        }

        if (s.compareTo("odi") == 0) {
            System.out.println("Ontology information is not complete");
            return false;
        }

        if (s.compareTo("rb") == 0) {
            System.out.println("Request is blank");
            return false;
        }

        return true;
    }

    public void close() {
        closeProducer();
        consumer.close();
        producer.close();
    }

    private String getResponse(String topic) {
        Client client = ClientBuilder.newClient();
        String response = client.target(ServerInformation.getServerAddress() + topic + "?id=" + IoTSensor)
                .request(MediaType.APPLICATION_JSON).get(String.class);

        return response;
    }

    private void startProducer() {

        for (int j = 0; j < 100; j++) {
            for (int i = 0; i < 40; i += 5) {
                producer.send(new ProducerRecord<>(IoTSensor, "" + i));
            }
        }
    }

    private String closeProducer() {
        try {
            producer.send(new ProducerRecord<>(serverID + "-CloseProducer", IoTSensor)).get();
        } catch (Exception e) {
            producer.close();
            return "MNS";
        }

        return "MS";

    }

    private void setProducer() {
        props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<>(props);
    }

    private void setConsumer() {
        props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "consumer");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("auto.offset.reset", "latest");
        props.put("max.poll.records", "1");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumer = new KafkaConsumer<>(props);
    }

}
