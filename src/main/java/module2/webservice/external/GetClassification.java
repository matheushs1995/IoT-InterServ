package module2.webservice.external;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import module2.dao.IoTTopicsMoreContextDAO;
import static module2.dao.MachineLearning.getServerTermGroupMachineLearning;
import module2.model.GenericModel10;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

@WebServlet(urlPatterns = "/GetClassification", loadOnStartup = 1)

public class GetClassification extends HttpServlet {

    ObjectMapper objectMapper = new ObjectMapper();

    private String requests;
    private String response;

    @Override

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String tempS1 = req.getParameter("term");
        String tempS2 = req.getParameter("c1");

        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        if (!(tempS1 == null || tempS1.isBlank()
                || tempS2 == null || tempS2.isBlank())) {

            try {
                String tg = IoTTopicsMoreContextDAO.getGenericTopicOfAT(tempS1);
                if (!tg.isBlank()) {
                    requests = tg;
                    requests += "," + tempS2;

                    int i = 2;
                    do {
                        tempS2 = req.getParameter("c" + i);
                        if (tempS2 == null || tempS2.isBlank()) {
                            break;
                        }
                        requests += "," + tempS2;
                        i++;

                    } while (true);

                    String server = getServerTermGroupMachineLearning(tg);
                    if (!(server == null || server.isBlank())) {
                        String[] responseV = getResponse("/GetClassificationModuleThree", requests, server).split(",");

                        if (!(responseV[0].isBlank() || responseV[0].compareTo("rb") == 0)) {
                            //"Temp,Person,PowerPlant,Chile"
                            GenericModel10 result = new GenericModel10(tempS1);
                            
                            String[] tempSV = requests.split(",");
                            for(int j=1;j<tempSV.length;j++){
                                result.setContexts(tempSV[j]);
                            }
                            
                            result.setRules(IoTTopicsMoreContextDAO.getOnlyRule(responseV[0]));
                            result.setEvaluation(responseV[1]);
                            
                            String arrayToJson = objectMapper.writeValueAsString(result);
                            response = arrayToJson;

                        }
                    } else {
                        response = "[]";
                    }

                } else {
                    response = "[]";
                }
            } catch (OWLOntologyCreationException ex) {
                Logger.getLogger(GetClassification.class.getName()).log(Level.SEVERE, null, ex);
                response = "[]";
            }

        } else {
            response = "[]";
        }

        resp.getWriter().print(response);

    }

    private static String getResponse(String topic, String input, String server) {

        Client client = ClientBuilder.newClient();
        String response = client.target(server + topic + "?input=" + input)
                .request(MediaType.APPLICATION_JSON).get(String.class);

        return response;
    }

}
