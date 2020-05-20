package examples;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

public class Test {

    public static void main(String[] args) throws OWLOntologyCreationException, InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException, IOException {
        test3();
    }

    public static void test1() throws OWLOntologyCreationException, InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException, IOException {
        Producer p = new Producer("ID1");
        p.start();
    }

    public static void test2() throws OWLOntologyCreationException {
        Consumer c = new Consumer("ID3");
        c.start();
    }

    public static void test3() throws OWLOntologyCreationException, InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException, IOException {

        System.out.println("");

        Consumer c1 = new Consumer("ID3");
        Consumer c2 = new Consumer("ID4");
        Consumer c3 = new Consumer("ID5");
        Consumer c4 = new Consumer("ID6");
        c1.start();
        c2.start();
        c3.start();
        c4.start();

        Producer p = new Producer("ID1");
        p.start();
    }
    
    public static void test4() throws OWLOntologyCreationException, InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException, IOException {

        System.out.println("");

        Consumer c1 = new Consumer("ID3");
        Consumer c2 = new Consumer("ID4");
        Consumer c3 = new Consumer("ID5");
        Consumer c4 = new Consumer("ID6");
        c1.start();
        c2.start();
        c3.start();
        c4.start();

        Producer p = new Producer("ID1");
        p.start();
    }
    
}
