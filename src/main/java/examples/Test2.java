package examples;

import java.util.Collections;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class Test2 {
    
    static Properties props;
    static KafkaProducer<String, String> producer;
    static KafkaConsumer<String, String> consumerP1;

    public static void main(String[] args) {
        setProperties();
        getProducerInformation();
        SimpleConsumerTest.start();
        SimpleProducerTest.start();
        
    }
    
    public static void setProperties() {
        setProducer();
        setConsumer();
    }
    
    public static void getProducerInformation() {
        String name = "getProducerInformation";
        consumerP1.subscribe(Collections.singletonList(name));

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    ConsumerRecords<String, String> records = consumerP1.poll(100);
                    for (ConsumerRecord<String, String> record : records) {
                        String m = record.value();
                        m = send("getProducerInformation-teste", m);
                        
                    }
                }
            }

        }.start();
    }
    
    public static String send(String topic, String data) {
        
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

    private static void setConsumer() {
        props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "consumer");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("auto.offset.reset", "latest");
        props.put("max.poll.records", "1");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumerP1 = new KafkaConsumer<>(props);
    }

}
