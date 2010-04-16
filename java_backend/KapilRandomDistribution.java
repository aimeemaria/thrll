import java.util.Random;

public class KapilRandomDistribution {

    private static int kapilRandRanged(int a, int b) {
        if(a < b) {
            return (int) (Math.random() * (b - a) + a);						//Random Generation
        }
        else
            return (int) (Math.random() * (a - b) + b);
    }
    
    private static int gaussianRandomRanged(int a, int b) {
        Random generator = new Random();
        double r = generator.nextGaussian();
        int ret;
        if(b > a) {
            ret = (int) ((a + b) + (b - a) * r) / 2;						// Normal Distribution
        }
        else {
            ret = (int) ((a + b) + (a - b) * r) / 2;
        }
        return (ret > 20 || ret < 1) ? gaussianRandomRanged(a, b) : ret;
    }
    
    public static void main(String[] args) throws Exceptuion{
        System.out.println("Even Distribution: ");
        for(int i = 0; i < 5; i++) {
            System.out.println(kapilRandRanged(1, 20));
        }
        System.out.println("Gaussian Distribution: ");
        for(int i = 0; i < 5; i++) {
            int randVal = gaussianRandomRanged(1, 20);
            if(randVal > 20 || randVal < 1) {
                throw new Exception("get lost");
            }
            System.out.println(randVal);
        }
    }
} 
