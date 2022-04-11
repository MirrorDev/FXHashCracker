package crackers;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DateCrack implements interfaces.HashCrackerBirthday{
    private String hash;
    private LocalDate checkUntilDate;

    public DateCrack(String hash, LocalDate checkUntilDate) {
        if (hash == null || checkUntilDate == null) {
            throw new IllegalArgumentException("No Arguments!");
        }
        this.hash = hash;
        this.checkUntilDate = checkUntilDate;
    }

    @Override
    private MessageDigest getHashAlgorithm(String name) {
        try {
            return MessageDigest.getInstance(name);
        } catch (NoSuchAlgorithmException e) {
            System.exit(-2);
            return null;
        }
    }

    @Override
    public String getHash() {
        return this.hash;
    }

    @Override
    public List<String> crackHash(LocalDate controlDate, DateTimeFormatter formatter) {
        // Method-Variables
        List<String> consoleOut = new ArrayList<>();
        MessageDigest hasher = getHashAlgorithm("SHA-256");
        String hash = getHash();
        StringBuilder hexBuilder = new StringBuilder();
        String hex = "";
        byte[] hashedBytes = new byte[0];
        boolean hashFound = false;

        while (!controlDate.equals(LocalDate.now())) {
            hasher.update(controlDate.format(formatter).toString().getBytes(StandardCharsets.UTF_8)); // Give Hasher the date
            hashedBytes = hasher.digest(); // "Hashing it"

            for (byte b : hashedBytes) { // Running through the bytes
                hexBuilder.append(String.format("%02x", b)); // Converting the byte to a Hexadecimal-String
            }
            hex = hexBuilder.toString(); // Giving variable hex the Hex-String

            // switch-case here is useless

            if (hex.equals(hash)) { // If we found the value
                // Add info
                consoleOut.add(hex);
                consoleOut.add(controlDate.format(formatter).toString());
                return consoleOut; // returning info
            } else { // Should that not be the case
                controlDate = controlDate.plusDays(1); // Add one day to the date
            }
        }
        return null;
    }
}
