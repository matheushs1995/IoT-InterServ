package module2.webservice.external;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import module2.dao.IoTDescriptionDAO;

@WebServlet(urlPatterns = "/GetFunction", loadOnStartup = 1)

public class GetFunction extends HttpServlet {

    ObjectMapper objectMapper = new ObjectMapper();

    private String request1;
    private String request2;
    private String response;

    @Override

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        request1 = req.getParameter("unit1");
        request2 = req.getParameter("unit2");

        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        if (!(request1 == null || request1.isBlank() || request2 == null || request2.isBlank())) {

            try {
                response = "[ { function : " + IoTDescriptionDAO.getFunction(request1, request2) + " }]";
            } catch (Exception ex) {
                response = "[]";
            }

        } else {
            response = "[]";
        }

        resp.getWriter().print(response);

    }

}
