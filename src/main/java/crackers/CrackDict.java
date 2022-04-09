package crackers;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
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
                return consoleOut; // return info
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

    public static List<String> getWordsFromFile(String filename) throws IOException{
        List<String> out = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            while (reader.ready()) {
                out.add(reader.readLine());
            }
        }
        return out;
    }
    public static List<List<String>> getWordsFromFileSplit(String filename) throws IOException{
        List<String> out = new ArrayList<>();
        List<String> out2 = new ArrayList<>();
        int counter = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            while (reader.ready()) {
                if (counter % 2 == 0) {
                    out.add(reader.readLine());
                } else {
                    out2.add(reader.readLine());
                }
            }
        }
        return new ArrayList<>(Arrays.asList(out, out2));
    }
}
