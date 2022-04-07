package crackers;

import java.io.BufferedReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class CrackDict implements interfaces.HashCrackerDictionary{
    private List<String> words;
    private String hash;

    @Override
    public List<String> crackHash() throws IOException {
        return null;
    }

    @Override
    public String getHash() {
        return null;
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
}
