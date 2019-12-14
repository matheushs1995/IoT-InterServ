package module1.interoperability.conector;

import java.util.Collections;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class Conector {

    static Properties props;
    static KafkaProducer<String, String> producer;
    static KafkaConsumer<String, String> consumer;

    private static void setProperties() {
        setProducer();
        setConsumer();
    }

    public static String requestResponse(String topic, String data, int timeWaiting) {
        setProperties();

        try {
            producer.send(new ProducerRecord<>(topic, data)).get();
        } catch (Exception e) {
            producer.close();
            return "TNS"; // Topic Not Send      
        }

        timeWaiting *= 60;
        long lastTime;

        consumer.subscribe(Collections.singletonList(data));

        lastTime = System.currentTimeMillis() + timeWaiting;
        while (lastTime > System.currentTimeMillis()) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                return record.value();
            }
        }

        return "TO"; // Time Out

    }

    public static String send(String id, String topic, String unit, String data) {

        for (int i = 0; i < 10; i++) {
            try {
                producer.send(new ProducerRecord<>(topic,id+","+unit+","+data)).get();
                return "TS"; // Topic Send
            } catch (Exception e) {
                producer.close();
            }
        }
        
        return "TNS"; // Topic Not Send
    }
    
    public static String send(String topic, String data) {

        for (int i = 0; i < 10; i++) {
            try {
                producer.send(new ProducerRecord<>(topic,data)).get();
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
        consumer = new KafkaConsumer<>(props);
    }
}
