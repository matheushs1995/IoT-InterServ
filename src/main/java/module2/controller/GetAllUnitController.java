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
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

public class GetAllUnitController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<String> units = null;

        try {

            units = IoTDescriptionDAO.getAllUnits();
            
        } catch (OWLOntologyCreationException ex) {
            Logger.getLogger(GetAllUnitController.class.getName()).log(Level.SEVERE, null, ex);
        }

        String unitsString = "";

        if (!units.isEmpty()) {

            unitsString = "<select id ='unit' class='inputSelect inputFormat' name='optUnits' onchange='if (this.selectedIndex) setUnit(this.id)'>";
            unitsString += "<option value=''>---</option>";
            
            for (String unit : units) {
                unitsString += "<option value=" + unit + ">" + unit + "</option>";
            }

            unitsString += "</select>";

        }
        response.getOutputStream().print(unitsString);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
