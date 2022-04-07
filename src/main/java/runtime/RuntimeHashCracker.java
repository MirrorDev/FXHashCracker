package runtime;

import java.util.List;

public class RuntimeHashCracker {
    private boolean whichCrack;
    private Object data;

    public RuntimeHashCracker(boolean whichCrack, Object data) {
        this.whichCrack = whichCrack;
        this.data = data;
    }

    public List<String> getDataDateThreaded() {
        throw new UnsupportedOperationException("TODO");
    }

    public List<String> getDataDictThreaded() {
        throw new UnsupportedOperationException("TODO");
    }
}
