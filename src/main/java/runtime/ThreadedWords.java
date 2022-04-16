package runtime;

import crackers.CrackDict;

import java.util.ArrayList;
import java.util.List;

public class ThreadedWords implements Runnable{
    private List<String> words;
    private String hash;
    private List<String> out = new ArrayList<>();

    public ThreadedWords(List<String> words, String hash) {
        if (words == null || hash == null) {
            throw new IllegalArgumentException();
        }
        this.words = words;
        this.hash = hash;
    }

    @Override
    public void run(){
    }

    public List<String> getResult() {
        return this.out;
    }
}
