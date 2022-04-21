package interfaces;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public interface HashCrackerBirthday {
    public String getHashAlgorithm();

    public String getHash();

    public List<String> crackHash(LocalDate controlDate, DateTimeFormatter formatter);
}
