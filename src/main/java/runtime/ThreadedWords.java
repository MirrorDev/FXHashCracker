package runtime;

import crackers.CrackDict;

import java.util.List;

public class ThreadedWords implements Runnable{
    private List<String> words;
    private String hash;

    public ThreadedWords(List<String> words, String hash) {
        if (words == null || hash == null) {
            throw new IllegalArgumentException();
        }
        this.words = words;
        this.hash = hash;
    }

    @Override
    public void run(){
        CrackDict cracker = new CrackDict(words, hash);
        List<String> out = cracker.crackHash();
    }
}
