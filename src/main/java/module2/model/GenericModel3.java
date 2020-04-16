package module2.model;

import java.util.ArrayList;
import java.util.List;

public class GenericModel3 {

    public String name;
    public String genericTopic;

    public String primaryDefinition;
    public String primaryExternalTerms;
    public String primarySynonyms;

    public List<String[]> secondaryDES; //Definition ExternalTerms and Synonyms

    public GenericModel3(String n, String gt) {
        name = n;
        genericTopic = gt;

    }

    public void setPrimaryDescription(String pTerms, String pDefinition, String pSynonyms) {
        primaryDefinition = pTerms;
        primaryExternalTerms = pDefinition;
        primarySynonyms = pSynonyms;
    }

    public void setSecondaryDescription(String pTerms, String pDefinition, String pSynonyms) {
        if (secondaryDES == null) {
            secondaryDES = new ArrayList<>();
        }

        secondaryDES.add(new String[]{pTerms, pDefinition, pSynonyms});
    }
}
