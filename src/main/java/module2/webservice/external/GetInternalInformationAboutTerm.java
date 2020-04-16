package module2.webservice.external;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import module2.dao.IoTTopicsMoreContextDAO;
import module2.model.*;

@WebServlet(urlPatterns = "/GetInternalInformationAboutTerm", loadOnStartup = 1)

public class GetInternalInformationAboutTerm extends HttpServlet {

    ObjectMapper objectMapper = new ObjectMapper();

    private String request;
    private String response;

    @Override

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        request = req.getParameter("term");

        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        if (!(request == null || request.isBlank())) {

            try {
                GenericModel3 topic = IoTTopicsMoreContextDAO.getInformationAboutAlternativeTopic(request);
                String arrayToJson = objectMapper.writeValueAsString(topic);
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
