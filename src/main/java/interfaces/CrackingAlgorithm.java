package interfaces;

import java.security.MessageDigest;

public interface CrackingAlgorithm {
    String printStackTrace();

    MessageDigest getHashAlgorithm(String name);
}
