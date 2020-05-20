package module2.webservice.external;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import module2.dao.IoTTopicsMoreContextDAO;
import module2.model.GenericModel8;

@WebServlet(urlPatterns = "/GetPragmaticRules", loadOnStartup = 1)

public class GetPragmaticRules extends HttpServlet {

    ObjectMapper objectMapper = new ObjectMapper();

    private List<String> requests = new ArrayList<>();
    private String response;

    @Override

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        requests.add(req.getParameter("term"));
        requests.add(req.getParameter("c1"));

        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        if (!(requests.get(0) == null || requests.get(0).isBlank()
                || requests.get(1) == null || requests.get(1).isBlank())) {

            int i = 2;
            do {
                String tempS = req.getParameter("c" + i);
                if (tempS == null || tempS.isBlank()) {
                    break;
                }
                requests.add(tempS);
                i++;

            } while (true);

            try {
                GenericModel8 termRules = IoTTopicsMoreContextDAO.getPragmaticRulesForAltenativeTopicAndContext(requests);
                String arrayToJson = objectMapper.writeValueAsString(termRules);
                response = arrayToJson;
            } catch (Exception ex) {
                response = "[]";
            }

        } else {
            response = "[]";
        }

        resp.getWriter().print(response);

    }

}
