import java.util.Random;

public class RandomUtil {
    public static Random random = new Random();

    public static int getRandomInt(int min, int max) {
        return random.nextInt(max) + min;
    }

    public static float getRandom() {
        return random.nextFloat();
    }

    public static double gaussRandom() {
        return random.nextGaussian() * 2 - 1;
    }
}
