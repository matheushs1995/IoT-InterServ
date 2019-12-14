
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

public class GetContextListController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String tempS1 = request.getParameter("SC");
        
        List<String> list;
        List<String> listChildren;
        
        String responseContext = "<span class='titleSection'> Context </span>";
        
        
        if(tempS1==null){
            String tempS2 = request.getParameter("topic");
            try {
                list = IoTTopicsMoreContextDAO.getAllSuperContext(tempS2);
                String context;
                for(String sc : list){
                    context = IoTTopicsMoreContextDAO.getCategoryOfTopContext(sc);
                    listChildren = IoTTopicsMoreContextDAO.getAllChildrenOfSuperContext(sc);
                    responseContext += "<div id='"+sc+"' class='divInput format-input'>";
                    responseContext += "<span id='"+sc+"-in' class='inputName'>Select the context: "+context+"</span><span class='inputName'> - Type of context:"+sc+"</span><div id='"+sc+"-divS' class='divS' name=''><select id='"+sc+"-S' class='contextS input4 inputFormat mod1' name='"+context+"'>";
                    for(String contextChild : listChildren){
                        responseContext +="<option value='"+contextChild+"'>"+contextChild+"</option>";
                    }
                    responseContext += "</select> </div><img class='iconMore2' name='"+sc+"' src='images/icons/more.png' onclick='changeToCreate(this.name)' width='40'/><img class='iconGetChildren' name='"+sc+"' src='images/icons/arrow.png' onclick='getChildren(this.name)' width='40'/></div>";
                }
                
            } catch (OWLOntologyCreationException ex) {
                Logger.getLogger(GetContextListController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }else{
            String[] tempV = tempS1.split(",");
            List<String> contexts;
            
            for(String sc: tempV){
                responseContext += "<div id='"+sc+"' class='divInput format-input' >";
                
                try {
                    contexts = IoTTopicsMoreContextDAO.getAllChildrenOfSuperContext(sc);
                    if(contexts.isEmpty()){
                        responseContext += "<span class='inputName'>Create the context - Type of context:"+sc+"</span><div id='"+sc+"-divI' class='contextI' name='"+sc+"'>";
                        responseContext += "<input id='"+sc+"-I1' class='input5 inputFormat' placeholder='Category..'/> <input id='"+sc+"-I2' class='input5 inputFormat' placeholder='Name..'/>";
                        responseContext += "</div> <img class='iconGetChildren' name='"+sc+"' src='images/icons/arrow.png' onclick='createChildren(this.name)' width='40'/></div>";
                    
                    }else{
                        String context = IoTTopicsMoreContextDAO.getCategoryOfTopContext(sc);
                        listChildren = IoTTopicsMoreContextDAO.getAllChildrenOfSuperContext(sc);
                        responseContext += "<span id='"+sc+"-in' class='inputName'>Select the context: "+context+"</span><span class='inputName'> - Type of context:"+sc+"</span><div id='"+sc+"-divS' class='divS' name=''><select id='"+sc+"-S' class='contextS input4 inputFormat mod1' name='"+context+"'>";
                        for(String contextChild : listChildren){
                            responseContext +="<option value='"+contextChild+"'>"+contextChild+"</option>";
                        }
                        responseContext += "</select> </div><img class='iconMore2' name='"+sc+"' src='images/icons/more.png' onclick='changeToCreate(this.name)' width='40'/><img class='iconGetChildren' name='"+sc+"' src='images/icons/arrow.png' onclick='getChildren(this.name)' width='40'/></div>";
                    }
                    
                } catch (OWLOntologyCreationException ex) {
                    Logger.getLogger(GetContextListController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        
        responseContext += "<div class='divBtnForm'> <div class='btnForm'> <div class='nextDiv'></div> <img class='nextB' src='images/next.jpg' onclick='pragmatic()' /> </div> </div>";
        
        response.getOutputStream().print(responseContext);
    
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
