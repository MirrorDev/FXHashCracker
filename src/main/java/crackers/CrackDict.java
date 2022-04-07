package crackers;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class CrackDict implements interfaces.HashCrackerDictionary{
    @Override
    public List<String> crackHash() throws IOException {
        return null;
    }

    @Override
    public String getHash() {
        return null;
    }

    @Override
    public String printStackTrace() {
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
