package module1.webservice.internal;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import module1.model.*;

@WebServlet(urlPatterns = "/PrepareProducer", loadOnStartup = 1)

public class PrepareProducer extends HttpServlet {

    private String request;
    private String response;

    @Override

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        request = req.getParameter("id");

        if (!(request == null || request.isBlank())) {
            try {
                response = Producer.createANewProduceIfThisNotExist(request);
            } catch (Exception ex) {
                response = "error"; // error to prepare
            }
        } else {
            response = "rb"; //request-blank
        }

        resp.getWriter().print(response);

    }

}
