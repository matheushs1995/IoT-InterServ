
package module2.controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import module2.dao.IoTTopicsMoreContextDAO;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

public class GetJsonOfTopicController extends HttpServlet {
    
    char aspas = '"';
    String name = aspas+"name"+aspas+":";
    String childrenOpen = aspas+"children"+aspas+": [{";
    String childrenClose = "}]";
    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, OWLOntologyCreationException {
        String topic = request.getParameter("topic");
        
        String gt = IoTTopicsMoreContextDAO.getGenericTopicOfAT(topic);
        
    }
    
    private String getGraphOfGT(String gt) throws OWLOntologyCreationException{
        
        String jsonData = "{"+name+aspas+gt+aspas+","+childrenOpen;
        List <String> ats = IoTTopicsMoreContextDAO.getAllAlternativeTopicsOfGT(gt);
        
        return "";
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (OWLOntologyCreationException ex) {
            Logger.getLogger(GetJsonOfTopicController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (OWLOntologyCreationException ex) {
            Logger.getLogger(GetJsonOfTopicController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
