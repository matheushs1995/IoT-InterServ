package module1.ruleFunctions;

import Allmodules.conector.SimpleProducer;

public class FT1_FT6 implements FunctionType {

    @Override
    public String function(String values, String id, String topic, String unit, String produceValue) {

        String[] valuesSplit = values.split(",");
        
        try {
            float pV = Float.parseFloat(produceValue);
            float v1 = Float.parseFloat(valuesSplit[0]);
            float v2 = Float.parseFloat(valuesSplit[1]);
            if (pV > v1 && pV != v2) {
                return SimpleProducer.send(id, topic, unit, produceValue);
            }

            return "";

        } catch (Exception e) {
            return "Error: Produce is not a float value";
        }

    }
    
}
