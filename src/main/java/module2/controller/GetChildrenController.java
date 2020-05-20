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

public class GetChildrenController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String parent = request.getParameter("parent");
        try {
            List <String> children = IoTTopicsMoreContextDAO.getAllChildrenOfContext(parent);
            
            if(children.isEmpty()){
                response.getOutputStream().print("null");
                return;
            }
            
            String category = IoTTopicsMoreContextDAO.getCategoryOfContext(children.get(0));
            
            String x = "";
            
            for(String child: children){
                x += "<option value='"+child+"'>"+child+"</option>"; 
            }
            
            response.getOutputStream().print(x+"_"+category);
            
        } catch (OWLOntologyCreationException ex) {
            Logger.getLogger(GetChildrenController.class.getName()).log(Level.SEVERE, null, ex);
        }
             
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
