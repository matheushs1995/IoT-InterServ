package examples;

import StringMathConvertAndExecute.Expr;
import StringMathConvertAndExecute.Parser;
import StringMathConvertAndExecute.SyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class Consumer {

    Properties props;
    KafkaProducer<String, String> producer;
    KafkaConsumer<String, String> consumer;
    String IoTSensor;
    List<String[]> functionConvert;
    static final String server = "localhost:9092";

    public void setProperties() {
        setProducer();
        setConsumer();
    }

    public Consumer(String i) {
        IoTSensor = i;
    }

    public void start() {
        setProperties();

        String s1 = prepareTopic("PrepareConsumer");
        if (!testResponse(s1)) {
            return;
        }

        String s2 = prepareTopic("GetFunctionsforConvert");
        if (!testResponse(s2)) {
            return;
        }

        formatFunctions(s2);

        startConsumer(s1);

        consumer.close();
        producer.close();

    }

    private static boolean testResponse(String s) {
        if (s.compareTo("") == 0) {
            System.out.println("Time out !!!");
            return false;
        }
        if (s.compareTo("inf") == 0) {
            System.out.println("Identification not found !!!");
            return false;
        }

        if (s.compareTo("FPC") == 0) {
            System.out.println("Erro to prepare consumer");
            return false;
        }

        return true;
    }

    private void startConsumer(String s) {
        setConsumer();
        consumer.subscribe(formatTopics(s));

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    ConsumerRecords<String, String> records = consumer.poll(100);
                    for (ConsumerRecord<String, String> record : records) {
                        try {
                            System.out.println("Topic: " + record.topic() + ", Value: " + convert(record.value()));
                        } catch (SyntaxException ex) {
                            Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }

        }.start();
    }

    private String convert(String s) throws SyntaxException {
        String[] recordValue = s.split(",");

        if (recordValue.length == 1) {
            return "Error1: value-" + s;
        }
        if (recordValue.length == 2) {
            return resultStringExecute(getFunction(recordValue[0]), recordValue[1]);
        }

        return recordValue[0] + "," + resultStringExecute(getFunction(recordValue[1]), recordValue[2]);
    }

    private String getFunction(String unit) {
        for (int i = 0; i < functionConvert.size(); i++) {
            if (functionConvert.get(i)[0].compareTo("unit") == 0) {
                return functionConvert.get(i)[1];
            }
        }

        return "";
    }

    public String resultStringExecute(String s, String value) throws SyntaxException {

        if (s.compareTo("") == 0) {
            return "Error2: value-" + value;
        }

        for (int i = 0; i < s.length(); i++) {
            if ("y".compareTo("" + s.charAt(i)) == 0 || "Y".compareTo("" + s.charAt(i)) == 0) {
                s = s.substring(0, i) + value + s.substring(i + 1, s.length());
            }
        }

        Expr expr;
        expr = Parser.parse(s);

        return "" + expr.value();
    }

    private static List<String> formatTopics(String s) {
        List<String> tempL = new ArrayList<>();
        String[] tempS = s.split(",");

        tempL.addAll(Arrays.asList(tempS));
        return tempL;
    }

    private void formatFunctions(String s3) {
        String[] tempS = s3.split(";");

        for (int i = 0; i < tempS.length; i++) {
            functionConvert.add(tempS[i].split(","));
        }
    }

    private String prepareTopic(String topic) {
        try {
            producer.send(new ProducerRecord<>(server + "/" + topic, IoTSensor)).get();
        } catch (Exception e) {
            producer.close();
            return "FPC";
        }
        return getResponse(IoTSensor + "-" + topic);
    }

    private String getResponse(String topic) {
        int timeWaiting = 500; //miliseconds
        timeWaiting *= 60;

        long lastTime;

        consumer.subscribe(Collections.singletonList(topic));

        lastTime = System.currentTimeMillis() + timeWaiting;
        while (lastTime > System.currentTimeMillis()) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                return record.value();
            }
        }

        return "";
    }

    private void setProducer() {
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

    private void setConsumer() {
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

}
