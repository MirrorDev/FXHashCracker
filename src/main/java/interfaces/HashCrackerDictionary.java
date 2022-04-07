package interfaces;

import java.io.IOException;
import java.util.List;

public interface HashCrackerDictionary extends CrackingAlgorithm{
    public List<String> crackHash() throws IOException;

    public String getHash();

}
