package interfaces;

import java.io.IOException;
import java.util.List;

public interface HashCrackerDictionary {
    public List<String> crackHash() throws IOException;

    public String getHash();

    public String getHashAlgorithm();
}
