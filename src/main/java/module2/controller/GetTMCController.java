package module2.controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import module2.dao.IoTDescriptionDAO;
import module2.dao.IoTTopicsMoreContextDAO;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

public class GetTMCController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String iot = request.getParameter("iot");
        String type = request.getParameter("type");

        List<String> tmcs;
        String responseData = "";
        if (type != null) {
            if (iot != null) {

                try {
                    tmcs = IoTDescriptionDAO.getTMCOfIoT(iot, type);

                    if(tmcs.size()>0){
                        responseData += "<option value=''>---</option>";
                    }    
                    
                    for (int i = 0; i < tmcs.size(); i++) {
                        responseData += "<option value='" + tmcs.get(i) + "'>" + tmcs.get(i) + "</option>";
                    }

                } catch (OWLOntologyCreationException ex) {
                    Logger.getLogger(GetTMCController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    tmcs = IoTTopicsMoreContextDAO.getAllTMC(type);
                    
                    if(tmcs.size()>0){
                        responseData += "<option value=''>---</option>";
                    }    
                    
                    for (int i = 0; i < tmcs.size(); i++) {
                        responseData += "<option value='" + tmcs.get(i) + "'>" + tmcs.get(i) + "</option>";
                    }
                } catch (OWLOntologyCreationException ex) {
                    Logger.getLogger(GetTMCController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        response.getOutputStream().print(responseData);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
