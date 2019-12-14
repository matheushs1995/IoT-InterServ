package examples;

import java.util.Collections;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class Producer {

    Properties props;
    KafkaProducer<String, String> producer;
    KafkaConsumer<String, String> consumer;
    String IoTSensor;
    static final String server = "localhost:9092";

    public void setProperties() {
        setProducer();
        setConsumer();
    }
    
    public Producer(String i){
        IoTSensor = i;
    }

    public void start() {
        setProperties();

        String s = prepareProducer();

        if (s.compareTo("") == 0) {
            System.out.println("Time out !!!");
            return;
        }
        if (s.compareTo("tnf") == 0) {
            System.out.println("Topic not found !!!");
            return;
        }

        if (s.compareTo("FPC") == 0) {
            System.out.println("Erro to prepare consumer");
            return;
        }

        startProducer(s);
        closeProducer();

        consumer.close();
        producer.close();

    }

    private void startProducer(String s) {
        producer.send(new ProducerRecord<>(IoTSensor, s));
    }

    private String prepareProducer() {

        try {
            producer.send(new ProducerRecord<>(server+"/PrepareProducer", IoTSensor)).get();
        } catch (Exception e) {
            producer.close();
            return "FPC";
        }
        return getResponse();

    }

    private String getResponse() {
        int timeWaiting = 500; //miliseconds
        timeWaiting *= 60;

        long lastTime;

        consumer.subscribe(Collections.singletonList(IoTSensor));

        lastTime = System.currentTimeMillis() + timeWaiting;
        while (lastTime > System.currentTimeMillis()) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                return record.value();
            }
        }
        return "";
    }

    private String closeProducer() {
        try {
            producer.send(new ProducerRecord<>(server+"/CloseProducer", IoTSensor)).get();
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
