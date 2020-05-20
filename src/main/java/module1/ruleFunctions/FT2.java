
package module1.ruleFunctions;

import module1.conector.SimpleProducer;

public class FT2 implements FunctionType {

    @Override
    public String function(String value,String id, String topic, String unit, String produceValue) {

        try {
            float pV = Float.parseFloat(produceValue);
            float v1 = Float.parseFloat(value);
            if (pV < v1) {
                return SimpleProducer.send(id,topic, unit, produceValue);
            }

            return "";

        } catch (Exception e) {
            return "Error: Produce is not a float value";
        }

    }
    
}
