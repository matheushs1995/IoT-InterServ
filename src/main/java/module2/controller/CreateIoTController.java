
package module2.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import module2.dao.IoTDescriptionDAO;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

public class CreateIoTController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, OWLOntologyCreationException {
        request.setAttribute("iots", IoTDescriptionDAO.getAllIoTs());
        RequestDispatcher view;
        view = request.getRequestDispatcher("/CreateIoT.jsp");
        view.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (OWLOntologyCreationException ex) {
            Logger.getLogger(CreateIoTController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (OWLOntologyCreationException ex) {
            Logger.getLogger(CreateIoTController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
