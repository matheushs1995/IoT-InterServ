
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

public class SetContextListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List <String> listContexts=null;
        try {
            listContexts=IoTTopicsMoreContextDAO.getAllSuperContext();
        } catch (OWLOntologyCreationException ex) {
            Logger.getLogger(SetContextListController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String function ="getContexts()";
        String responseString = "<span class='titleSection'> Type of Contexts </span> <div class='divInput format-input' > <span class='inputName'>Select an elements</span> <div id ='CC' class='chooseOrCreate'> <div id='divS'> <select id='S' class='inputSelect inputFormat' name='S'>";
        
        for(int i=0;i<listContexts.size();i++){
            responseString += "<option value='"+listContexts.get(i)+"'>"+listContexts.get(i)+"</option>";
        }
        
        responseString +="</select> </div> <img class='iconMore' src='images/icons/certain.png' onclick='add1()' width='43'/> </div> </div> <div class='divInput format-input'> <span class='inputName'>Create an element</span> <div class='chooseOrCreate'> <div id='divI'> <input id='I' class='input2 inputFormat' name='I' placeholder='Create..'/> </div> <img class='iconMore' src='images/icons/certain.png' onclick='add2()' width='43'/> </div> </div> <div id='addedI' class='divInput'> <span class='inputName'>Added:</span> </div> <div class='divBtnForm'> <div class='btnForm'> <div class='nextDiv'></div> <img class='nextB' src='images/next.jpg' onclick='"+function+"' /> </div> </div>";
    
        response.getOutputStream().print(responseString);
    
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
