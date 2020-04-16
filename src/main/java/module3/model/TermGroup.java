package module3.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import module3.controller.Manager;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

public class TermGroup {

    public static List<Integer> modelUpdated = new ArrayList<>();
    public static List<Integer> prepareClassifier = new ArrayList<>();

    private String name;
    private int status;
    private J48 classifier = new J48();
    private J48 classifierCopy = new J48();

    static ConverterUtils.DataSource ds;
    static Instances ins;

    public TermGroup(String n) throws FileNotFoundException {
        name = n;
        status = 0;

        File f = new File(Manager.pathBuilderClassifiers + "\\" + name + ".txt");

        if (f.exists()) {
            Scanner s = new Scanner(f);
            if (s.hasNext()) {
                classifier = ClassifierJ48.deserialization(Manager.pathBuilderClassifiers + "\\" + name + ".txt");
            } else {
                f = new File(Manager.pathBuilderClassifiers + "\\Backup\\" + name + ".txt");
                s = new Scanner(f);
                if (s.hasNext()) {
                    classifier = ClassifierJ48.deserialization(Manager.pathBuilderClassifiers + "\\Backup\\" + name + ".txt");
                    Manager.CopyFile(Manager.pathBuilderClassifiers + "\\Backup\\" + name + ".txt", Manager.pathBuilderClassifiers + "\\" + name + ".txt");
                }
            }
        }

    }

    public TermGroup(String n, int s) {
        name = n;
        status = s;
    }

    public String getName() {
        return name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int s) {
        status = s;
    }

    public J48 getClassifier() {
        return classifier;
    }

    public void setClassifier(J48 classifier) {
        this.classifier = classifier;
    }

    public J48 getClassifierCopy() {
        return classifierCopy;
    }

    public void setClassifierCopy() throws Exception {
        ds = new ConverterUtils.DataSource(Manager.pathModels + "\\" + name + ".arff");
        ins = ds.getDataSet();
        int index = getIndex();
        ins.setClassIndex(index);

        classifierCopy.buildClassifier(ins);
        ClassifierJ48.serialization(classifierCopy, Manager.pathBuilderClassifiers + "\\" + name + ".txt");
        Manager.CopyFile(Manager.pathBuilderClassifiers + "\\" + name + ".txt", Manager.pathBuilderClassifiers + "\\Backup\\" + name + ".txt");
        classifier = classifierCopy;
        status = 0;

    }

    private int getIndex() throws FileNotFoundException {
        File f = new File(Manager.pathModels + "\\" + name + ".arff");
        Scanner s = new Scanner(f);

        while (s.hasNext()) {
            if (s.nextLine().compareTo("@data") == 0) {
                break;
            }
        }

        return s.nextLine().split(",").length - 1;

    }
}
