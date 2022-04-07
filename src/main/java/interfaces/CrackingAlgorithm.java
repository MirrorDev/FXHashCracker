package interfaces;

import java.security.MessageDigest;

public interface CrackingAlgorithm {
    MessageDigest getHashAlgorithm(String name);
}
