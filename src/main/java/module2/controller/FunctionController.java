package module2.controller;

import StringMathConvertAndExecute.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FunctionController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] funcBits = request.getParameter("func").split(" ");

        String func = "";
        
        for (String charFunc : funcBits) {
            int num =Integer.parseInt(charFunc,2);
            char letter = (char) num;
            func += letter;
        }

        if(testFunction(func)) response.getOutputStream().print("true");
        
        response.getOutputStream().print("false");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public static boolean testFunction(String s) {

        Expr expr;

        try {
            expr = Parser.parse(s);
            return true;
        } catch (SyntaxException ex) {
            return false;
        }

    }

}
