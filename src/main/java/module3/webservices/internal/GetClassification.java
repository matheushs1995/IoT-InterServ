package module3.webservices.internal;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import module3.controller.Manager;

@WebServlet(urlPatterns = "/GetClassificationModuleThree", loadOnStartup = 1)

public class GetClassification extends HttpServlet {

    private String request;
    private String response;

    private boolean startModels = false;

    private static String modelsDir = "C:\\Users\\mathe\\Documents\\Dropbox\\Projetos\\PRIME-IoT\\Files\\Module3.MachineLearningModels\\";

    @Override

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        request = req.getParameter("input");
        response = "";

        if (!(request == null || request.isBlank())) {

            String[] requests = request.split(",");

            File f = new File(modelsDir + requests[0] + ".arff");
            if (f.exists()) {
                Scanner s = new Scanner(f);
                try {
                    int tgID = Integer.parseInt(requests[0].substring(2, requests[0].length()));
                    if (s.hasNextLine()) {
                        boolean test = false;
                        boolean test2 = false;
                        String orderContext = "";
                        String[] tempSV;

                        do {
                            tempSV = s.nextLine().split(" ");
                            if (tempSV[0].compareTo("@attribute") == 0) {

                                if (tempSV[tempSV.length - 1].charAt(0) + "".compareTo("{") == 0) {
                                    if (tempSV[0].compareTo("pragmatic") != 0) {
                                        tempSV = tempSV[tempSV.length - 1].substring(1, tempSV.length - 1).split(",");

                                        if (tempSV.length != Manager.getTermGroup(tgID - 1).getIndex()) {
                                            break;
                                        }

                                        for (String c : tempSV) {
                                            test2 = false;
                                            for (int i = 1; i < requests.length; i++) {
                                                if (c.compareTo(requests[i]) == 0) {
                                                    test2 = true;
                                                    if (!test) {
                                                        orderContext = c;
                                                    } else {
                                                        orderContext += "," + c;
                                                    }
                                                    break;
                                                }
                                            }
                                            if (!test2) {
                                                break;
                                            }
                                        }

                                        if (!test2) {
                                            break;
                                        }
                                    } else {
                                        tempSV = tempSV[tempSV.length - 1].substring(1, tempSV.length - 1).split(",");
                                        break;
                                    }
                                } else {
                                    break;
                                }
                            } else {
                                if (test) {
                                    if (s.next().compareTo("@data") == 0) {
                                        break;
                                    }
                                }
                            }
                        } while (s.hasNext());

                        if (test2) {
                            int id = Manager.getTermGroup(tgID - 1).getClassification(orderContext);
                            response = tempSV[id]+","+Manager.getTermGroup(tgID - 1).getEvaluation();
                        }

                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }

            }

        } else {
            response = "rb"; //request-blank
        }

        resp.getWriter().print(response);

    }

}
