package module1.interoperability.manager;

import module1.dao.IoTDescriptionDAO;
import java.util.List;
import java.util.Properties;
import module1.interoperability.ruleFunctions.*;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

public class PrincipalManager {

    private static Properties props;
    private static KafkaProducer<String, String> producer;
    private static KafkaConsumer<String, String> consumer;
    private static final String server = "localhost:9092";

    public static String getServer() {
        return server;
    }

    private static final FT1 ft1 = new FT1();
    private static final FT2 ft2 = new FT2();
    private static final FT3 ft3 = new FT3();
    private static final FT4 ft4 = new FT4();
    private static final FT5 ft5 = new FT5();
    private static final FT6 ft6 = new FT6();

    private static final FT1_FT2 ft1_ft2 = new FT1_FT2();
    private static final FT1_FT5 ft1_ft5 = new FT1_FT5();
    private static final FT1_FT6 ft1_ft6 = new FT1_FT6();

    private static final FT2_FT4 ft2_ft4 = new FT2_FT4();
    private static final FT2_FT6 ft2_ft6 = new FT2_FT6();

    private static final FT4_FT5 ft4_ft5 = new FT4_FT5();
    private static final FT4_FT6 ft4_ft6 = new FT4_FT6();

    private static final FT5_FT6 ft5_ft6 = new FT5_FT6();

    private static final FT6_FT6 ft6_ft6 = new FT6_FT6();

    public static void setProperties() {
        setProducer();
        setConsumer();
    }

    public static void start() {
        ProducerManager.setProperties();
        ProducerManager.eventPrepareProducer();
        ProducerManager.eventCloseProducer();

        ConsumerManager.setProperties();
        ConsumerManager.eventPrepareConsumer();
        ConsumerManager.eventGetFunctionsforConvert();
    }


    private static void returnTopic(String IoT) throws OWLOntologyCreationException {
        String temp = IoTDescriptionDAO.getTopic(IoT);

        if (temp.compareTo("") == 0) {
            producer.send(new ProducerRecord<>(IoT, "tnf"));
        } else {
            producer.send(new ProducerRecord<>(IoT, temp));
        }

    }

    private static void returnTopicForConsume(String IoT) throws OWLOntologyCreationException {
        List<String> temp = IoTDescriptionDAO.getTopicForConsume(IoT);

        if (temp == null || temp.isEmpty()) {
            producer.send(new ProducerRecord<>(IoT, "tnf"));
        } else {
            producer.send(new ProducerRecord<>(IoT, formatListInString(temp)));
        }
    }

    private static String formatListInString(List<String> tempL) {
        String tempS = tempL.get(0);
        for (int i = 1; i < tempL.size(); i++) {
            tempS += "," + tempL.get(i);
        }

        return tempS;
    }

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

    public static FT1 getFT1() {
        return ft1;
    }

    public static FT2 getFT2() {
        return ft2;
    }

    public static FT3 getFT3() {
        return ft3;
    }

    public static FT4 getFT4() {
        return ft4;
    }

    public static FT5 getFT5() {
        return ft5;
    }

    public static FT6 getFT6() {
        return ft6;
    }

    public static FT1_FT2 getFT1_FT2() {
        return ft1_ft2;
    }

    public static FT1_FT5 getFT1_FT5() {
        return ft1_ft5;
    }

    public static FT1_FT6 getFT1_FT6() {
        return ft1_ft6;
    }

    public static FT2_FT4 getFT2_FT4() {
        return ft2_ft4;
    }

    public static FT2_FT6 getFT2_FT6() {
        return ft2_ft6;
    }

    public static FT4_FT5 getFT4_FT5() {
        return ft4_ft5;
    }

    public static FT4_FT6 getFT4_FT6() {
        return ft4_ft6;
    }

    public static FT5_FT6 getFT5_FT6() {
        return ft5_ft6;
    }

    public static FT6_FT6 getFT6_FT6() {
        return ft6_ft6;
    }

}
