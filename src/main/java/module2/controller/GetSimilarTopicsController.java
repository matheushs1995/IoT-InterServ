package module2.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import module2.similarity.Similarity;
import org.json.JSONException;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

public class GetSimilarTopicsController extends HttpServlet {

    static String projectDir = "C:\\Users\\mathe\\Documents\\Dropbox\\Projetos\\PRIME-IoT";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, FileNotFoundException, JSONException, OWLOntologyCreationException {
        String topic = request.getParameter("topic");
        String id = request.getParameter("id");
        String keywords = request.getParameter("keywords");

        if (topic == null || keywords == null || topic.isBlank() || keywords.isBlank()) {
            response.getOutputStream().print("");
        } else {

            if (id != null) {
                if(id.isBlank()){
                   response.getOutputStream().print(""); 
                }else{
                    response.getOutputStream().print(Similarity.getSimilarTopicsOne(topic, id, keywords));
                }
            } else {
                String description = request.getParameter("description");
                
                if(description.isBlank()){
                   response.getOutputStream().print(""); 
                }else{
                    response.getOutputStream().print(Similarity.getSimilarTopicsTwo(topic, description, keywords));
                }
                
            }
        }

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
            throws ServletException, IOException, FileNotFoundException {
        try {
            processRequest(request, response);
        } catch (JSONException ex) {
            Logger.getLogger(GetSimilarTopicsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (OWLOntologyCreationException ex) {
            Logger.getLogger(GetSimilarTopicsController.class.getName()).log(Level.SEVERE, null, ex);
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
            throws ServletException, IOException, FileNotFoundException {
        try {
            processRequest(request, response);
        } catch (JSONException ex) {
            Logger.getLogger(GetSimilarTopicsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (OWLOntologyCreationException ex) {
            Logger.getLogger(GetSimilarTopicsController.class.getName()).log(Level.SEVERE, null, ex);
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
