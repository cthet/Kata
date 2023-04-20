import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BloomFilter {
    private final int size = 16;
    private final boolean[] bitArray = new boolean[size];
    private final Map<String, List<Integer>> log = new HashMap<>();
    private final String[] hashAlgorithms = {"SHA-1", "SHA-256", "SHA-512"};

    public void add(String entry) throws NoSuchAlgorithmException {
        log.put(entry, new ArrayList<>());

        for (String hashAlgorithm : hashAlgorithms) {
            int index = hash(entry, hashAlgorithm) % size;
            bitArray[index] = true;
            log.get(entry).add(index);
        }
    }

    public boolean test(String entry) throws NoSuchAlgorithmException {
        for (String hashAlgorithm : hashAlgorithms) {
            int index = hash(entry, hashAlgorithm) % size;
            if (!bitArray[index]) {
                return false;
            }
        }
        return true;
    }

    public void logPresent(String entry) throws NoSuchAlgorithmException {
        System.out.println(log.get(entry));
    }

    private int hash(String entry, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(algorithm);
        byte[] bytes = md.digest(entry.getBytes(StandardCharsets.UTF_8));
        int result = 0;
        for (byte b : bytes) {
            result = (result * 256 + (b & 0xFF)) % size;
        }
        return result;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        BloomFilter bf = new BloomFilter();

        System.out.println("test(\"word1\"): " + bf.test("word1"));
        bf.add("word1");
        System.out.println("test(\"word1\"): " + bf.test("word1"));
        System.out.println("test(\"word2\"): " + bf.test("word2"));

        bf.logPresent("word1");
    }
}