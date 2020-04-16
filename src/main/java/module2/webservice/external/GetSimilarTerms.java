package module2.webservice.external;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import module2.dao.IoTTopicsMoreContextDAO;
import module2.model.*;

@WebServlet(urlPatterns = "/GetSimilarTerms", loadOnStartup = 1)

public class GetSimilarTerms extends HttpServlet {

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
                List<GenericModel1> topics = IoTTopicsMoreContextDAO.similarTopic(request);
                String arrayToJson = objectMapper.writeValueAsString(topics);
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
