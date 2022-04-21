package crackers.bruteforcebirthday;

import interfaces.HashCrackerBirthday;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class BirthdayCrackerDictionary implements HashCrackerBirthday {
    private String hash;
    private String hashAlgorithm;
    private static final DateTimeFormatter FORMATONE = DateTimeFormatter.ofPattern("d.M.yyyy");
    private static final DateTimeFormatter FORMATWO = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static void main(String[] args) throws InterruptedException{
        String hash = "9dc6167f0dc793883ccd1836edaac23c86330c30f06862901544549b732a9594";
        BirthdayCrackerDictionary birthdayCracker= new BirthdayCrackerDictionary(hash, "SHA-256");
        List<List<String>> results = new ArrayList<>();

        Thread threadOne = new Thread(() -> {
            List<String> dateFormatOne = birthdayCracker.crackHash(LocalDate.parse("1900-01-01"), FORMATONE);

            if (dateFormatOne == null) {
                results.add(new ArrayList<>(Arrays.asList("Thread 1")));

            } else {
                dateFormatOne.add("Thread 1");
                results.add(dateFormatOne);
            }
        });

        Thread threadTwo = new Thread(() -> {
            List<String> dateFormatTwo = birthdayCracker.crackHash(LocalDate.parse("1900-01-01"), FORMATWO);

            if (dateFormatTwo == null) {
                results.add(new ArrayList<>(Arrays.asList("Thread 2")));

            } else {
                dateFormatTwo.add("Thread 2");
                results.add(dateFormatTwo);
            }
        });

        threadOne.start();
        threadTwo.start();


        System.out.println("Threads are processing...\n----------------------------------------------------------------");

        threadOne.join();
        threadTwo.join();

        for (List<String> listControl : results) {
            if (!listControl.get(0).contains("Thread")) {
                System.out.println("From " + listControl.get(listControl.size() - 1) + ":\nOriginal Hash:\n" +
                        hash +
                        "\nDate:\n" +
                        listControl.get(1) +
                        "\nHash from date (to compare if it is correct):\n" +
                        listControl.get(0));
            } else {
                System.out.println("\n" + listControl.get(0) + " did not find a vaild Hash");
            }
        }
    }

    public BirthdayCrackerDictionary(String hash, String hashAlgorithm) {
        if (hash == null || hashAlgorithm == null) {
            throw new IllegalArgumentException("Values are not allowed to be null");
        }

        this.hash = hash;
        this.hashAlgorithm = hashAlgorithm;
    }

    @Override
    public String getHash() {
        return hash;
    }

    @Override
    public String getHashAlgorithm() {
        return hashAlgorithm;
    }

    public List<String> crackHash(LocalDate controlDate, DateTimeFormatter formatter) {
        List<String> out = new ArrayList<>();
        MessageDigest hasher = null;
        String hashedDate = new String();
        StringBuilder hashedAsHexBuilder = null;
        String hasedAsHex = new String();
        byte[] hashedBytes = new byte[]{};
        boolean foundHash = false;

        try {
            hasher = MessageDigest.getInstance(getHashAlgorithm());
        } catch (NoSuchAlgorithmException exception) {
            System.out.println("Please enter a vaild algorithm!");
            return null;
        }

        while (!controlDate.equals(LocalDate.now())) {
            hasher.update(controlDate.format(formatter).toString().getBytes());
            hashedBytes = hasher.digest();

            hashedAsHexBuilder = new StringBuilder(hashedBytes.length * 2);
            for (byte b : hashedBytes) {
                hashedAsHexBuilder.append(String.format("%02x", b));
            }
            hasedAsHex = hashedAsHexBuilder.toString();

             if (hasedAsHex.equals(getHash())) {
                 out.add(hasedAsHex);
                 out.add(controlDate.format(formatter).toString());
                 return out;
             } else {
                 controlDate = controlDate.plusDays(1);
             }
        }

        return null;

    }
}
