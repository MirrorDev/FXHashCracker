package crackers;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class CrackDict implements interfaces.HashCrackerDictionary{
    private List<String> words;
    private String hash;

    public CrackDict(List<String> words, String hash) {
        if (words == null || hash == null) {
            throw new IllegalArgumentException("No Arguments!");
        }
        this.words = words;
        this.hash = hash;
    }

    @Override
    public List<String> crackHash() throws IOException {
        // Method-Variables
        List<String> consoleOut = new ArrayList<>();
        MessageDigest hasher = getHashAlgorithm("SHA-256");
        String hash = getHash();
        StringBuilder hexBuilder = new StringBuilder();
        String hex = "";
        byte[] hashedBytes = new byte[0];
        boolean hashFound = false;

        for (String value : words) { // For as long there are words
            hasher.update(value.getBytes(StandardCharsets.UTF_8)); // Give hasher the word
            hashedBytes = hasher.digest(); // "Hashing it"

            for (byte b : hashedBytes) { // Running through bytes
                hexBuilder.append(String.format("%02x", b)); // Converting byte into Hexadecimal-String
            }
            hex = hexBuilder.toString(); // Giving hex the Hex-String

            // switch-case is useless

            if (hex.equals(hash)) {
                consoleOut.add(hex);
                consoleOut.add(value);
                return consoleOut;
            } else {
                continue;
            }
        }
        return null;
    }

    @Override
    public String getHash() {
        return this.hash;
    }

    @Override
    public MessageDigest getHashAlgorithm(String name) {
        try {
            return MessageDigest.getInstance(name);
        } catch (NoSuchAlgorithmException e) {
            System.exit(-2);
            return null;
        }
    }

    public static void getWordsFromFile(String filename) {
        throw new UnsupportedOperationException("TODO");
    }

    public static List<List<String>> getWordsFromFileSplit(String filename) {
        throw new UnsupportedOperationException("TODO");
    }
}
