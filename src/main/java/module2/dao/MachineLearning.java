package module2.dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import module2.model.ServerInformation;

public class MachineLearning {

    static FileWriter arq;
    static PrintWriter gravarArq;
    static String modelsPath = "C:\\Users\\mathe\\Documents\\Dropbox\\Projetos\\PRIME-IoT\\Files\\Module2.MachineLearningModels\\";

    private static List<Integer> countTermGroup = new ArrayList<>();
    private final static int limitCounTermGroupUpdateModel = 15;

    public static void setTG(String tg) {

        try {
            int ind = Integer.parseInt(tg.substring(2, tg.length()));
            if (ind <= countTermGroup.size()) {
                if (countTermGroup.get(ind - 1) == 15) {
                    if (getResponse("SetModel", ind).compareTo("Set") == 0) {
                        countTermGroup.set(ind - 1, 0);
                    }
                } else {
                    countTermGroup.set(ind - 1, countTermGroup.get(ind - 1) + 1);
                }
            } else {
                while (ind - 1 != countTermGroup.size()) {
                    countTermGroup.add(0);
                }
                countTermGroup.add(1);
                if (getResponse("SetModel", countTermGroup.size()).compareTo("Set") != 0) {
                    countTermGroup.set(countTermGroup.size() - 1, 15);
                }

            }

        } catch (Exception ex) {
            System.out.println("format error");
        }
    }

    private static String getResponse(String topic, int id) {
        Client client = ClientBuilder.newClient();
        String response = client.target(ServerInformation.getServerAddress()+topic + "?id=" + id)
                .request(MediaType.APPLICATION_JSON).get(String.class);

        return response;
    }

    public static void main(String[] args) throws OWLOntologyCreationException, IOException {
        //generateModel("Temp");
        setTG("GT1");
        
    }

    public static String getModelTG(String tg) throws OWLOntologyCreationException, IOException {

        if (tg.isBlank()) {
            return "term not exist";
        }

        File file = new File(modelsPath + tg + ".arff");
        if (!file.exists()) {
            generateModel(IoTTopicsMoreContextDAO.getFirstAltenatveTopicOfGT(tg));
            file = new File(modelsPath + tg + ".arff");
        }

        String tempS = "";
        Scanner reader = new Scanner(file);

        while (reader.hasNextLine()) {
            tempS += reader.nextLine() + "\n";
        }

        return tempS;

    }

    public static String getModel(String term) throws OWLOntologyCreationException, IOException {

        String termGroup = IoTTopicsMoreContextDAO.getGenericTopicOfAT(term);
        if (termGroup.isBlank()) {
            return "term not exist";
        }

        File file = new File(modelsPath + termGroup + ".arff");
        if (!file.exists()) {
            generateModel(term);
            file = new File(modelsPath + termGroup + ".arff");
        }

        String tempS = "";
        Scanner reader = new Scanner(file);

        while (reader.hasNextLine()) {
            tempS += reader.nextLine() + "\n";
        }

        return tempS;

    }

    public static void generateModel(String term) throws OWLOntologyCreationException, IOException {

        List<String> topics = IoTTopicsMoreContextDAO.getSpecificTopic(term);

        if (topics.isEmpty()) {
            return;
        }

        String termGroup = IoTTopicsMoreContextDAO.getGenericTopicOfAT(term);

        if (termGroup.isBlank()) {
            return;
        }

        List<String> superContexts = IoTTopicsMoreContextDAO.getSuperContextOfTopic(topics.get(0));

        arq = new FileWriter(modelsPath + termGroup + ".arff");
        gravarArq = new PrintWriter(arq);

        gravarArq.println("@relation " + termGroup);
        gravarArq.println();

        List<String> pragmatics = new ArrayList<>();

        for (int i = 0; i < topics.size(); i++) {
            String tempPrag = IoTTopicsMoreContextDAO.getPragmatic(topics.get(i));
            if (tempPrag.isEmpty()) {
                topics.remove(i);
                i--;
            } else {
                pragmatics.add(tempPrag);
            }
        }

        List<List<String>> contextsAndParents = new ArrayList<>();
        List<List<String>> categories = new ArrayList<>();

        List<String> tempList;
        List<String> newCategory;
        for (String topic : topics) {
            tempList = IoTTopicsMoreContextDAO.getContextsAndParents(topic);
            contextsAndParents.add(tempList);
            for (int i = 0; i < tempList.size(); i += 2) {
                boolean test = true;
                for (List<String> category : categories) {
                    if (tempList.get(i).compareTo(category.get(0)) == 0) {
                        test = false;
                        break;
                    }
                }

                if (test) {
                    newCategory = new ArrayList<>();
                    newCategory.add(tempList.get(i));
                    categories.add(newCategory);
                }

            }
        }

        for (List<String> cp : contextsAndParents) {
            for (List<String> category : categories) {
                boolean test = true;
                for (int i = 0; i < cp.size(); i += 2) {
                    if (category.get(0).compareTo(cp.get(i)) == 0) {
                        category.add(cp.get(i + 1));
                        test = false;
                        break;
                    }
                }

                if (test) {
                    category.add("?");
                }

            }
        }

        for (List<String> category : categories) {
            boolean test;
            String tempS = "@attribute " + category.get(0) + " {";
            boolean testNotFirst = false;
            for (int i = 1; i < category.size(); i++) {
                if (category.get(i).compareTo("?") != 0) {
                    test = true;
                    for (int j = 1; j < i; j++) {
                        if (category.get(i).compareTo(category.get(j)) == 0) {
                            test = false;
                            break;
                        }
                    }

                    if (test) {
                        if (testNotFirst) {
                            tempS += ",";
                        } else {
                            testNotFirst = true;
                        }
                        tempS += category.get(i);
                    }
                }

            }

            gravarArq.println(tempS + "}");
        }

        String tempS = "@attribute pragmatic {";
        boolean testNotFirst = false;
        for (int i = 0; i < pragmatics.size(); i++) {
            boolean test = true;
            for (int j = 0; j < i; j++) {
                if (pragmatics.get(i).compareTo(pragmatics.get(j)) == 0) {
                    test = false;
                    break;
                }
            }

            if (test) {
                if (testNotFirst) {
                    tempS += ",";
                } else {
                    testNotFirst = true;
                }
                tempS += pragmatics.get(i);
            }
        }

        gravarArq.println(tempS + "}");

        gravarArq.println();
        gravarArq.println("@data");

        categories = sortCategoriesAndHandleMissingData(superContexts, categories);

        if (categories == null) {
            return;
        }

        for (int i = 0; i < pragmatics.size(); i++) {
            tempS = categories.get(0).get(i + 1);
            for (int j = 1; j < categories.size(); j++) {
                tempS += "," + categories.get(j).get(i + 1);
            }
            tempS += "," + pragmatics.get(i);
            gravarArq.println(tempS);

        }

        gravarArq.close();

    }

    private static List<List<String>> sortCategoriesAndHandleMissingData(List<String> sc, List<List<String>> categories) throws OWLOntologyCreationException {
        List<List<String>> newCategories = new ArrayList<>();

        for (int i = 0; i < sc.size(); i++) {
            String category = IoTTopicsMoreContextDAO.getCategoryOfTopContext(sc.get(i));
            if (category.isBlank()) { //MasterCategory
                return null;
            }

            int countNC = 0;
            while (!category.isBlank()) {
                boolean test = false;
                for (int j = 0; j < categories.size(); j++) {
                    if (categories.get(j).get(0).compareTo(category) == 0) {
                        newCategories.add(categories.get(j));
                        countNC++;
                        categories.remove(j);
                        test = true;
                        break;
                    }
                }

                if (!test) {
                    return null;
                }

                category = IoTTopicsMoreContextDAO.getChildCategory(category);
            }

            sc.set(i, "" + countNC);
            countNC = 0;

        }

        int salt = 0;
        int scCount = 0;
        List<String[]> lastCalculatedModaValues = new ArrayList<>();
        for (int j = 0; j < newCategories.size() || scCount > sc.size(); j++) {

            if (j == salt) {
                for (String s : newCategories.get(j)) {
                    if (s.compareTo("?") == 0) {
                        return null;
                    }
                }

                salt += Integer.parseInt(sc.get(scCount));
                scCount++;

            } else {
                for (int k = 0; k < newCategories.get(j).size(); k++) {
                    if (newCategories.get(j).get(k).compareTo("?") == 0) {
                        String result = modaValue(newCategories.get(j - 1).get(k), lastCalculatedModaValues);
                        if (result.isBlank()) {
                            result = getNewValue(newCategories.get(j), IoTTopicsMoreContextDAO.getAllChildrenOfContext(newCategories.get(j - 1).get(k)));
                            lastCalculatedModaValues.add(new String[]{newCategories.get(j - 1).get(k), result});
                        }

                        newCategories.get(j).set(k, result);
                    }
                }
            }
        }
        return newCategories;
    }

    private static String getNewValue(List<String> category, List<String> allChildrenOfContext) {
        int moda = 0, maxValue = 0;
        for (int i = 0; i < allChildrenOfContext.size(); i++) {
            int tempCount = 0;
            for (int j = 0; j < category.size(); j++) {
                if (allChildrenOfContext.get(i).compareTo(category.get(j)) == 0) {
                    tempCount++;
                }
            }

            if (maxValue < tempCount) {
                maxValue = tempCount;
                moda = i;
            }
        }

        return allChildrenOfContext.get(moda);
    }

    private static String modaValue(String name, List<String[]> lastCalculatedModaValues) {
        for (String[] s : lastCalculatedModaValues) {
            if (s[0].compareTo(name) == 0) {
                return s[1];
            }
        }
        return "";
    }
}
