package module2.webservice.external;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import module2.dao.MachineLearning;

@WebServlet(urlPatterns = "/GetModel", loadOnStartup = 1)

public class GetModel extends HttpServlet {

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
                response = MachineLearning.getModel(request);
            } catch (Exception ex) {
                response = "[]";
            }

        } else {

            request = req.getParameter("tg");
            
            if (!(request == null || request.isBlank())) {

                try {
                    response = MachineLearning.getModelTG(request);
                } catch (Exception ex) {
                    response = "[]";
                }

            } else {
                response = "[]";
            }
        }

        resp.getWriter().print(response);

    }

}
