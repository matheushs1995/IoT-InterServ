package examples;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TestCreateModel {

    static FileWriter arq;
    static PrintWriter gravarArq;

    public static void main(String[] args) throws IOException {
        arq = new FileWriter("C:\\Users\\mathe\\Documents\\Output.txt");
        gravarArq = new PrintWriter(arq);
        
        gravarArq.println("teste1");
        gravarArq.println("teste2");
        gravarArq.println("teste3");
        gravarArq.println("teste4");
        
        gravarArq.close();
    }

}
