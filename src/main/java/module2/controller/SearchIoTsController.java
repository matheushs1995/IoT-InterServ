
package module2.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import module2.dao.IoTDescriptionDAO;
import module2.order.Order;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

public class SearchIoTsController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, OWLOntologyCreationException {
        
        List<String> Iots = IoTDescriptionDAO.getAllIoTs();
        Iots = Order.orderList(Iots);
        
        List<String> tempL = new ArrayList<>();
        for(int i=0; i<Iots.size();i++){
            tempL.add(IoTDescriptionDAO.getAllInfomationAboutIoTInTHML(Iots.get(i)));
        }
        
        request.setAttribute("iots", tempL);
        RequestDispatcher view;
        view = request.getRequestDispatcher("/SearchIoTs.jsp");
        view.forward(request, response);

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
            Logger.getLogger(SearchIoTsController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SearchIoTsController.class.getName()).log(Level.SEVERE, null, ex);
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
