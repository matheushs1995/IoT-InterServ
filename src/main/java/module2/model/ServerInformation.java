package module2.model;

import java.util.ArrayList;
import java.util.List;

public class ServerInformation {

    private static final String serverAddress = "http://localhost:8081/PRIME-IoT/";
    private static List<String> moduleThreeServersAddress = new ArrayList<>();
    private static int count = 0;
    
    public static void setModuleThreeServersAddress(){
        moduleThreeServersAddress.add("http://localhost:8081/PRIME-IoT/");
    }
           
    public static String getServerAddress() {
        return serverAddress;
    }

    public static String getNextModuleThreeServerAddress() {
        if (moduleThreeServersAddress.size() > 0) {
            if (count != 0) {
                count = moduleThreeServersAddress.size() % count;
            }
            
            String tempS = moduleThreeServersAddress.get(count);
            count++;
            return tempS;
        }
        return "";
    }
    
    public static void addModuleThreeServerAddress(String s){
        moduleThreeServersAddress.add(s);
    }

}
