package examples;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

public class Consumer {

    Properties props;
    KafkaConsumer<String, String> consumer;
    
    String IoTSensor;
    List<String[]> functionConvert = new ArrayList<>();
    
    double totalValue;
    double currentValue;
    

    public Consumer(String i) {
        IoTSensor = i;
    }

    public void start() throws OWLOntologyCreationException {
        setConsumer();

        String s1 = getResponse("PrepareConsumer");
        
        System.out.println("Consumer: "+IoTSensor+ ", Status Topics: "+s1);
        
        if (!testResponse(s1)) {
            return;
        }
        
        String s2 = getResponse("GetFunctionsForConvert");
        
        System.out.println("Consumer: "+IoTSensor+ ", Status Units and Functions: "+s2);
        
        if (!testResponse(s2)) {
            return;
        }
        
        formatFunctions(s2);

        System.out.println("");
        
        startConsumer(s1);
        
    }
    
    public void close(){
        consumer.close();
    }
    
    private String getResponse(String topic){
        Client client = ClientBuilder.newClient();
        String response = client.target(ServerInformation.getServerAddress()+topic+"?id="+IoTSensor)
                    .request(MediaType.APPLICATION_JSON).get(String.class);
        
        return response;
    }

    private static boolean testResponse(String s) {
        
        if (s.compareTo("") == 0) {
            System.out.println("blank message !!!");
            return false;
        }
        
        if (s.compareTo("error") == 0) {
            System.out.println("error to prepare producer !!!");
            return false;
        }
        
        if (s.compareTo("rb") == 0) {
            System.out.println("Request is blank");
            return false;
        }
        
        if (s.compareTo("inf") == 0) {
            System.out.println("identify not found !!!");
            return false;
        }

        if (s.compareTo("infc") == 0) {
            System.out.println("identify is not from a consumer !!!");
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
                    System.out.print("");
                    ConsumerRecords<String, String> records = consumer.poll(100);
                    for (ConsumerRecord<String, String> record : records) {

                        try {
                            System.out.println("ID: "+IoTSensor+", Topic: " + record.topic() + ", Value: " + convert(record.value()));
                        } catch (Exception ex) {

                        }

                    }
                }
            }

        }.start();
    }

    private String convert(String s) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        
        String[] recordValue = s.split(",");

        if (recordValue.length == 1) {
            return "Error1: value-" + s;
        }
        if (recordValue.length == 2) {
            return resultStringExecute(getFunction(recordValue[0]), recordValue[1]);
        }
        
        return recordValue[0] + "," + resultStringExecute(getFunction(recordValue[1]), recordValue[2]);
    }

    private int getFunction(String unit) {
        for (int i = 0; i < functionConvert.size(); i++) {
            if (functionConvert.get(i)[0].compareTo(unit) == 0) {
                return i;
            }
        }

        return -1;
    }

    public String resultStringExecute(int i, String value) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        if (i == -1) {
            return value;
        }

        totalValue= Double.parseDouble(value);
        for (int j = 1; j < functionConvert.get(i).length ; j++) {
            String[] tempS = functionConvert.get(i)[j].split("_");
            if(tempS.length !=2){
                return "Error3: value-" + value;
            }
            getResult(tempS[0],tempS[1]);
        }

        return ""+totalValue;
    }

    public void getResult(String op, String value) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        try {
            Class c = Class.forName("examples.Consumer");
            Method m = c.getMethod("get" + op);

            currentValue = Double.parseDouble(value);
            m.invoke(this);

        } catch (ClassNotFoundException ex) {
            System.out.println("Operator \"" + op + "\" does not exist");
            Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void getO1() {
        totalValue += currentValue;
    }

    public void getO2() {
        totalValue -= currentValue;
    }

    public void getO3() {
        totalValue *= currentValue;
    }

    public void getO4() {
        if (totalValue != 0) {
            totalValue /= currentValue;
        }
    }

    public void getO5() {
        totalValue = Math.pow(totalValue, currentValue);
    }

    public void getO6() {
        if(currentValue != 0){
            totalValue = Math.pow(totalValue, 1/currentValue);
        }
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
