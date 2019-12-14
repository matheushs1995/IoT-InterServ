package module2.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import module2.dao.IoTTopicsMoreContextDAO;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

public class SetPragmaticRulesController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String responseString = "<span class='titleSection'> Pragmatic Rules </span> <div class='divInput format-input'> <span class='inputName'>Create a Pragmatic Rule</span> <div class='chooseOrCreate'> <div id='divI'> <span class='if space1'>If</span> <input id='I1' type='number' class='input7 inputFormat space1' name='I1' placeholder='Number...'/> <select id='S1' class='S input8 inputFormat space1' onchange='if (this.selectedIndex) chargeSelect1()'> <option value=''></option> <option value='FT1'>&#60;</option> <option value='FT2'>&#62;</option> <option value='FT3'>==</option> <option value='FT4'>&#60;=</option> <option value='FT5'>&#62;=</option> <option value='FT6'>!=</option> </select> <span class='value space1 '>value</span> <select id='S2' class='S input8 inputFormat space1'> <option value=''></option> </select> <input id='I2' class='input7 inputFormat ' name='I2' placeholder='Number...'/> <select id='topics' class='input2 inputFormat'> <option value=''></option>";
    
        List <String> producers = new ArrayList<>();
        
        try {
            producers = IoTTopicsMoreContextDAO.getAllSpecificTopicProducer();
        } catch (OWLOntologyCreationException ex) {
            Logger.getLogger(SetPragmaticRulesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for(String tProducer: producers){
            responseString += "<option value='"+tProducer+"'>"+tProducer+"</option>";
        }
        
        responseString += "</select> </div> <img class='iconMore' src='images/icons/certain.png' onclick='addRule()' width='43'/> </div> </div> <div id='addedI' class='divInput'> <span class='inputName'>Added:</span> </div> <div class='divBtnForm'> <div class='btnForm'> <div class='nextDiv'> </div> <img class='nextB' src='images/next.jpg' onclick='saveWithPragmaticRules()'/> </div> </div>";
        
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
