package crackers.dictonaryCracker;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DictonaryCrackerV2 {
    private String hash;
    private String filename;
    private String hashAlgorithm;
    private final String[] SPECIALCHARS = new String[]{"!", "$", "%", "&", "/", "(", ")", "=", "?", ""};

    public DictonaryCrackerV2(String hash, String filename, String hashAlgorithm) {
        if (hash == null || hashAlgorithm == null || filename == null) {
            throw new IllegalArgumentException("Values are not allowed to be null");
        }

        this.hash = hash;
        this.filename = filename;
        this.hashAlgorithm = hashAlgorithm;
    }


    public List<List<String>> crackHash() throws IOException, InterruptedException {
        List<List<String>> listsOfHashesForThreads = new ArrayList<>();
        MessageDigest hasher;

        try {
            hasher = MessageDigest.getInstance(hashAlgorithm);
        } catch (NoSuchAlgorithmException exception) {
            throw new IllegalArgumentException();
        }

        List<String> firstHalf = new ArrayList<>();
        List<String> secondHalf = new ArrayList<>();
        long counter = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename, StandardCharsets.UTF_8))) {
            try (BufferedReader countReader = new BufferedReader(new FileReader(filename, StandardCharsets.UTF_8))) {
                counter = countReader.lines().count();
            }
                while (reader.ready()) {
                if (counter % 2 == 0) {
                    firstHalf.add(reader.readLine());
                    counter--;
                } else {
                    secondHalf.add(reader.readLine());
                    counter--;
                }
            }
        }

        Thread threadOne = new Thread(() -> {
            String control = "";
            byte[] hashedBytes;
            StringBuilder hashedAsHexBuilder;
            String hashedAsHex = "";
            String updateString = "";

            for (Iterator<String> iterator = firstHalf.iterator(); iterator.hasNext(); ) {
                control = iterator.next();
                for (String special : SPECIALCHARS) {
                    for (String special2 : SPECIALCHARS) {
                        updateString = special + control + special2;
                        hasher.update(updateString.getBytes());
                        hashedBytes = hasher.digest();
                        hashedAsHexBuilder = new StringBuilder(hashedBytes.length * 2);
                        for (byte b : hashedBytes) {
                            hashedAsHexBuilder.append(String.format("%02x", b));
                        }
                        hashedAsHex = hashedAsHexBuilder.toString();

                    }
                }
            }
            if (hashedAsHex.equals(getHash())) {
                firstHalf.add(getHash());
                firstHalf.add(hashedAsHex);
                firstHalf.add(control);
                firstHalf.add(updateString);
            } else {
                firstHalf.add("Thread 1");
            }
        });

        Thread threadTwo = new Thread(() -> {
            String control = "";
            byte[] hashedBytes;
            StringBuilder hashedAsHexBuilder;
            String hashedAsHex = "";
            String updateString = "";

            for (Iterator<String> iterator = firstHalf.iterator(); iterator.hasNext(); ) {
                control = iterator.next();
                for (String special : SPECIALCHARS) {
                    for (String special2 : SPECIALCHARS) {
                        updateString = special + control + special2;
                        hasher.update(updateString.getBytes());
                        hashedBytes = hasher.digest();
                        hashedAsHexBuilder = new StringBuilder(hashedBytes.length * 2);
                        for (byte b : hashedBytes) {
                            hashedAsHexBuilder.append(String.format("%02x", b));
                        }
                        hashedAsHex = hashedAsHexBuilder.toString();
                    }
                }
            }
            if (hashedAsHex.equals(getHash())) {
                secondHalf.add(getHash());
                firstHalf.add(hashedAsHex);
                firstHalf.add(control);
                firstHalf.add(updateString);
            } else {
                firstHalf.add("Thread 1");
            }
        });

        threadOne.run();
        threadTwo.run();
        threadOne.join();
        threadTwo.join();

        listsOfHashesForThreads.add(firstHalf);
        listsOfHashesForThreads.add(secondHalf);
        return listsOfHashesForThreads;
    }

    public String getResults(List<List<String>> list) {
        if (list == null) {
            throw new IllegalArgumentException();
        }

        List<String> control;
        StringBuilder builder = new StringBuilder();

        for (Iterator<List<String>> iterator = list.iterator(); iterator.hasNext();) {
            builder.append("--------------------------------------------------\n");
            control = iterator.next();
            if (control.get(0).contains("Thread")) {
                builder.append(control.get(0) + " did not find a vaild hash!");
            } else {
                for (String value : control) {
                    builder.append(value + ", ");
                }
            }
        }
        return builder.toString();
    }

    public String getHash() {
        return hash;
    }

}