package module1.model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

public class Producer {

    private static final List<Producer> producers = new ArrayList<>();

    private String[] information;
    private final String id;
    private String name;
    private String topic;
    private String unit;
    private Pragmatic pragmatic;

    static FileWriter arq;
    static PrintWriter gravarArq;

    public Producer(String i) {
        id = i;
    }

    public static String createANewProduceIfThisNotExist(String i) throws OWLOntologyCreationException, InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException, IOException {

        Producer p = verifyIfListContainProducer(i);
        if (p == null) {

            String inf = getResponse("GetProducerInformation", i);
            
            //ode - Ontology data error 
            //idnf - identifier not found
            if (inf.isBlank() || inf.compareTo("error") == 0 || inf.compareTo("ode") == 0 || inf.compareTo("idnf") == 0) {
                return inf;
            }

            p = new Producer(i);
            p.setInformation(inf);
            p.setName();
            p.setTopic();
            p.setUnit();

            if (p.information.length == 5) {
                p.setPragmatic();
                p.setMapRule();

            }

            producers.add(p);

        }

        return "OK";
    }

    private static String getResponse(String topic, String id) {
        Client client = ClientBuilder.newClient();
        String response = client.target(ServerInformation.getServerAddress() + topic + "?id=" + id)
                .request(MediaType.APPLICATION_JSON).get(String.class);

        return response;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTopic() {
        return topic;
    }

    public String getUnit() {
        return unit;
    }

    public Pragmatic getPragmatic() {
        return pragmatic;
    }

    private static Producer verifyIfListContainProducer(String s) {
        for (Producer p : producers) {
            if (p.id.compareTo(s) == 0) {
                return p;
            }
        }
        return null;
    }

    public static String removeProducer(String i) {
        for (int j = 0; j < producers.size(); j++) {
            if (producers.get(j).id.compareTo(i) == 0) {
                producers.get(j).pragmatic.decreaseUseOfPragmatic();
                producers.remove(j);
                return "Removed";

            }
        }

        return "Not removed";
    }

    private void setMapRule() {
        Map<String, Object> props = new HashMap<>();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, topic);
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        StreamsConfig config = new StreamsConfig(props);

        StreamsBuilder builder = new StreamsBuilder();

        builder.<String, String>stream(id).mapValues(value -> intention(value)).to(topic);

        KafkaStreams streams = new KafkaStreams(builder.build(), config);
        streams.start();
    }

    private String intention(String value) {

        if (pragmatic == null) {
            return unit + "," + value;
        }

        for (int i = 0; i < pragmatic.getFts().size(); i++) {
            pragmatic.getFts().get(i).function(pragmatic.getValues().get(i), name, pragmatic.getTopics().get(i), unit, value);
        }

        return unit + "," + value;
    }

    private void setTopic() throws OWLOntologyCreationException {
        topic = information[1];
    }

    private void setPragmatic() throws OWLOntologyCreationException, InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
        pragmatic = Pragmatic.getPragmaticObject(information[3], information[4]);
    }

    private void setUnit() throws OWLOntologyCreationException {
        unit = information[2];
    }

    private void setName() throws OWLOntologyCreationException {
        name = information[0];
    }

    private void setInformation(String inf) {
        information = inf.split(";");
    }

}
