package module3.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import module3.model.TermGroup;

public class Manager {

    public static File pathModels = new File("C:\\Users\\mathe\\Documents\\Dropbox\\Projetos\\PRIME-IoT\\Files\\Module3.MachineLearningModels\\");
    public static File pathBuilderClassifiers = new File("C:\\Users\\mathe\\Documents\\Dropbox\\Projetos\\PRIME-IoT\\Files\\Module3.BuilderClassifiers\\");

    static List<TermGroup> termGroups = new ArrayList<>();

    public static TermGroup getTermGroup(int id){
        return termGroups.get(id);
    }
    
    public static void start() throws FileNotFoundException {

        File[] files = pathModels.listFiles();

        for (File f : files) {
            if (f.isFile() && f.getName().length() > 7) {

                String nameTG = verifyFileName(f.getName());

                if (nameTG.isBlank()) {
                    break;
                }
                Scanner s = new Scanner(f);
                File fb = new File(pathModels + "\\Backup\\" + f.getName());
                if (s.hasNext()) {
                    if (fb.exists()) {
                        s = new Scanner(fb);
                        if (!s.hasNext()) {
                            CopyFile(pathModels + "\\" + f.getName(), pathModels + "\\Backup\\" + f.getName());
                        }
                    } else {
                        CopyFile(pathModels + "\\" + f.getName(), pathModels + "\\Backup\\" + f.getName());
                    }
                    termGroups.add(new TermGroup(nameTG));
                } else {

                    if (fb.exists()) {
                        s = new Scanner(fb);
                        if (s.hasNext()) {
                            CopyFile(pathModels + "\\Backup\\" + f.getName(), pathModels + "\\" + f.getName());
                            termGroups.add(new TermGroup(nameTG));
                        }
                    }
                }
            }
        }

        
        UpdateClassifier();
    }

    private static String verifyFileName(String name) {
        String start = name.substring(0, 2);
        if (start.compareTo("GT") != 0) {
            return "";
        }
        String middle = name.substring(2, name.length() - 5);
        try {
            Integer.parseInt(middle);
        } catch (Exception ex) {
            return "";
        }
        String end = name.substring(name.length() - 5);
        if (end.compareTo(".arff") != 0) {
            return "";
        }

        return start + middle;
    }

    public static void CopyFile(String source, String target) throws FileNotFoundException {
        File fSource = new File(source);
        Scanner s = new Scanner(fSource);

        PrintWriter fTarget = new PrintWriter(target);

        while (s.hasNext()) {
            fTarget.println(s.nextLine());
        }

        fTarget.close();

    }

    private static void UpdateClassifier() {
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(60000);

                        for (int i = 0; i < termGroups.size(); i++) {
                            if (termGroups.get(i).getStatus() == 0) {
                                File builderClassifier = new File(pathBuilderClassifiers + "\\" + termGroups.get(i).getName() + ".txt");
                                File model = new File(pathModels + "\\" + termGroups.get(i).getName() + ".arff");

                                if (builderClassifier.exists()) {
                                    if (model.lastModified() > builderClassifier.lastModified()) {
                                        termGroups.get(i).setStatus(1);
                                        termGroups.get(i).setClassifierCopy();
                                    }
                                } else {
                                    termGroups.get(i).setStatus(1);
                                    termGroups.get(i).setClassifierCopy();
                                }
                            }
                        }

                    } catch (InterruptedException ex) {
                        Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        }.start();
    }

}
