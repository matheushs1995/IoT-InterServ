package module3.webservices.internal;


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import module3.model.ServerInformation;

@WebServlet(urlPatterns = "/SetModel", loadOnStartup = 1)

public class SetModel extends HttpServlet {

    private String request;
    private String response;

    private static String projectDir = "C:\\Users\\mathe\\Documents\\Dropbox\\Projetos\\PRIME-IoT\\Files\\Module3.MachineLearningModels\\";

    @Override

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        request = req.getParameter("id");

        if (!(request == null || request.isBlank())) {

            response = getResponse("GetModel", "GT" + (request));

            if (!(response.compareTo("[]") == 0 || response.isBlank())) {
                PrintWriter w = new PrintWriter(projectDir+"GT" + (request)+".arff");
                w.println(response);
                w.close();
                
                w = new PrintWriter(projectDir+"\\Backup\\GT" + (request)+".arff");
                w.println(response);
                w.close();
                response = "Set";
            } else {
                response = "error";
            }

        } else {
            response = "rb"; //request-blank
        }

        resp.getWriter().print(response);

    }

    private String getResponse(String topic, String id) {
        Client client = ClientBuilder.newClient();
        String response = client.target(ServerInformation.getServerAddress()+topic + "?tg=" + id)
                .request(MediaType.APPLICATION_JSON).get(String.class);

        return response;
    }

}
