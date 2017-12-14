
/**
 * A fraction class to store fractions
 *
 * @author Robbie Mones
 * @version 12/14/17
 */
public class Fraction
{
    public int numerator;
    public int denominator;
    
    Fraction() {
        this.numerator = 0;
        this.denominator = 0;
    }
    Fraction(int num, int denom) {
        this.numerator = num;
        this.denominator = denom;
    }
    
    boolean isFraction() {
        return ((this.numerator >= 0) && (this.denominator > 0));
    }
}
