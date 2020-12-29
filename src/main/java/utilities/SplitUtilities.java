package utilities;
import java.util.Random;

public class SplitUtilities {
    private static final int splitCodeLength = 6;

    public static String generateCode() {

        int min = (int) Math.pow(10, splitCodeLength - 1);
        int max = (int) Math.pow(10, splitCodeLength); // bound is exclusive

        Random random = new Random();

        return Integer.toString(random.nextInt(max - min) + min);
    }
}
