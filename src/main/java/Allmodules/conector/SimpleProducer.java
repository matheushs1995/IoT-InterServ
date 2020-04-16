package Allmodules.conector;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class SimpleProducer {

    static Properties props;
    static KafkaProducer<String, String> producer;

    public static String send(String id, String topic, String unit, String data) {

        setProducer();

        for (int i = 0; i < 10; i++) {
            try {
                producer.send(new ProducerRecord<>(topic, id + "," + unit + "," + data)).get();
                return "TS"; // Topic Send
            } catch (Exception e) {
                producer.close();
            }
        }

        return "TNS"; // Topic Not Send
    }

    public static String send(String topic, String data) {

        setProducer();

        for (int i = 0; i < 10; i++) {
            try {
                producer.send(new ProducerRecord<>(topic, data)).get();
                return "TS"; // Topic Send
            } catch (Exception e) {
                producer.close();
            }
        }

        return "TNS"; // Topic Not Send

    }

    private static void setProducer() {
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

}
