package examples;

import java.util.Collections;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class SimpleConsumerTest {

    static Properties props;
    static KafkaConsumer<String, String> consumer;
    static final String server = "localhost:9092";

    private static void setConsumer() {
        props = new Properties();
        props.put("bootstrap.servers", server);
        props.put("group.id", "consumer");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("auto.offset.reset", "latest");
        props.put("max.poll.records", "1");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumer = new KafkaConsumer<>(props);
    }

    public static void start() {
        setConsumer();

        consumer.subscribe(Collections.singletonList("getProducerInformation-teste"));

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    ConsumerRecords<String, String> records = consumer.poll(100);
                    for (ConsumerRecord<String, String> record : records) {
                        System.out.println(record.value());
                    }
                }

            }
        }.start();
        
        System.out.println("Waiting...");
        
        
    }

}
