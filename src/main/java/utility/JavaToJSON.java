package utility;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JavaToJSON {


    public static String convertToJSON(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
