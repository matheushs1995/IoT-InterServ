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
import module2.dao.IoTDescriptionDAO;
import module2.model.*;

@WebServlet(urlPatterns = "/GetSameUnits", loadOnStartup = 1)

public class GetSameUnits extends HttpServlet {

    ObjectMapper objectMapper = new ObjectMapper();

    private String request1;
    private String request2;
    private String response;

    @Override

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        request1 = req.getParameter("unit");
        request2 = req.getParameter("type");

        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        if (!(request1 == null || request1.isBlank())) {

            if (request2 == null || request2.compareTo("0") == 0) {
                try {
                    List<GenericModel1> units = IoTDescriptionDAO.sameUnitGt1(request1);
                    String arrayToJson = objectMapper.writeValueAsString(units);
                    response = arrayToJson;
                } catch (Exception ex) {
                    response = "[]";
                }
            } else {
                if (request2.compareTo("1") == 0) {
                    try {
                        List<GenericModel2> units = IoTDescriptionDAO.sameUnitGt2(request1);
                        String arrayToJson = objectMapper.writeValueAsString(units);
                        response = arrayToJson;
                    } catch (Exception ex) {
                        response = "[]";
                    }
                }
            }

        } else {
            response = "[]";
        }

        resp.getWriter().print(response);

    }

}
