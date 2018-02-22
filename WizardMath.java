import java.util.Vector;

public class WizardMath
{
    public static int gcd(int a, int b)
    {
        while (b > 0)
        {
            int temp = b;
            b = a % b; // % is remainder
            a = temp;
        }
        return a;
    }
    
    public static int gcd(int[] input)
    {
        int result = input[0];
        for(int i = 1; i < input.length; i++) result = gcd(result, input[i]);
        return result;
    }
    
    public static int lcm(int a, int b)
    {
        return a * (b / gcd(a, b));
    }
    
    public static int lcm(Vector<Integer> input)
    {
        int result = input.get(0);
        for(int i = 1; i < input.size(); i++) result = lcm(result, input.get(i));
        return result;
    }
}
