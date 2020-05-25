package module2.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import module2.dao.IoTDescriptionDAO;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

public class SetIoTController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String request1 = convert(request.getParameter("values"));
        String[] values = request1.split("#");

        String result;
        try {
            result = IoTDescriptionDAO.save(values);
            if (!result.isEmpty()) {
                response.getOutputStream().print(result);
            } else {
                response.getOutputStream().print("INA");
            }
        } catch (OWLOntologyCreationException ex) {
            Logger.getLogger(SetIoTController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (OWLOntologyStorageException ex) {
            Logger.getLogger(SetIoTController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private static String convert(String value) {
        String[] funcBits = value.split(" ");

        String func = "";

        for (String charFunc : funcBits) {
            int num = Integer.parseInt(charFunc, 2);
            char letter = (char) num;
            func += letter;
        }

        return func;

    }

}
