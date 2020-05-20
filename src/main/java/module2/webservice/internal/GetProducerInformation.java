package module2.webservice.internal;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import module2.dao.*;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

@WebServlet(urlPatterns = "/GetProducerInformation", loadOnStartup = 1)

public class GetProducerInformation extends HttpServlet {

    private String request;
    private String response;

    @Override

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        request = req.getParameter("id");

        if (!(request == null || request.isBlank())) {
            try {
                response = prepareMessage(request);
            } catch (Exception ex) {
                response = "error"; // error to get producer information
            }
        } else {
            response = "rb"; //request-blank
        }

        resp.getWriter().print(response);

    }

    public static String prepareMessage(String id) throws OWLOntologyCreationException {

        if (!IoTDescriptionDAO.idExist(id)) {
            return "idnf";
        }

        String name = IoTDescriptionDAO.getName(id);
        String topic = IoTDescriptionDAO.getTopic(name);
        String unit = IoTDescriptionDAO.getUnit(id);

        if (name.isBlank() || topic.isBlank() || unit.isBlank()) {
            return "ode";
        }

        String pragmatic = IoTTopicsMoreContextDAO.getPragmatic(topic);

        String m = name
                + ";" + topic
                + ";" + unit;

        if (!pragmatic.isBlank()) {
            List<String[]> rules = IoTTopicsMoreContextDAO.getRule(pragmatic);

            String rulesString = "";
            if (!rules.isEmpty()) {
                rulesString = rules.get(0)[0] + "!" + rules.get(0)[1];
                for (int i = 1; i < rules.size(); i++) {
                    rulesString += "-" + rules.get(i)[0] + "!" + rules.get(i)[1];
                }
            }
            m += ";" + pragmatic
                    + ";" + rulesString;
        }

        return m;
    }

}
