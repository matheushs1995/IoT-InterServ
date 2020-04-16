package examples;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

class SimpleProducerTest {

    static Properties props;
    static KafkaProducer<String, String> producer;
    static final String server = "localhost:9092";
    static final String serverId = "001";

    private static void setProducer() {
        props = new Properties();
        props.put("bootstrap.servers", server);
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<>(props);
    }

    public static void start() {
        setProducer();

        for (int i = 0; i < 10; i++) {
            producer.send(new ProducerRecord<>("getProducerInformation", ""+i));
        }

    }

}
