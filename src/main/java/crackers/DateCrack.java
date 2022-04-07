package crackers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DateCrack implements interfaces.HashCrackerBirthday{
    private String hash;
    private LocalDate checkUntilDate;

    public DateCrack(String hash, LocalDate checkUntilDate) {
        this.hash = hash;
        this.checkUntilDate = checkUntilDate;
    }

    @Override
    public MessageDigest getHashAlgorithm(String name) {
        try {
            return MessageDigest.getInstance(name);
        } catch (NoSuchAlgorithmException e) {
            System.exit(-2);
        }
    }

    @Override
    public String getHash() {
        return this.hash;
    }

    @Override
    public List<String> crackHash(LocalDate controlDate, DateTimeFormatter formatter) {
        return null;
    }

    @Override
    public String printStackTrace() {
        return null;
    }
}
