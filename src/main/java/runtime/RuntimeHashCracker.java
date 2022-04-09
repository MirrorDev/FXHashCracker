package runtime;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.ListResourceBundle;
import java.util.Map;

public class RuntimeHashCracker {
    private boolean whichCrack;
    private Object data;

    public RuntimeHashCracker(boolean whichCrack, Object data) {
        this.whichCrack = whichCrack;
        this.data = data;
    }

    public List<String> getDataDateThreaded(String hash, List<String> dates) {
        Map<String, DateTimeFormatter> dateMaps = new HashMap<>();
        for (String value : dates) {
            try {
            dateMaps.put(value, DateTimeFormatter.ofPattern(value));
            } catch (Exception e) {
                return null;
            }
        }


    }

    public List<String> getDataDictThreaded() {
        throw new UnsupportedOperationException("TODO");
    }
}
