
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
    
    public static Fraction divide(Fraction f1, Fraction f2) {
        return reduce(new Fraction((f1.numerator*f2.denominator), (f1.denominator*f2.numerator)));
    }
    
    public static Fraction recip(Fraction f) {
        return new Fraction(f.denominator, f.numerator);
    }
    
    public static Fraction reduce(Fraction f) {
        if(f.denominator == f.numerator) {
            return new Fraction(1,1);
        } else {
            for(int i = ((f.denominator > f.numerator) ? f.denominator : f.numerator); i >= 0; i--) {
                if((f.denominator % i == 0) && (f.numerator % i == 0)) {
                    return new Fraction(f.numerator/i,f.denominator/i);
                }
            }
            return f;
        }
    }
    
    boolean isFraction() {
        return ((this.numerator >= 0) && (this.denominator > 1));
    }
    
    /**
     * Returns whether the term is in a whole number form or not (i.e. the
     * numerator is divisible by the denominator without remainder)
     *
     * @return True when the term is a whole, false if not whole or undefined
     */
    public boolean isWhole()
    {
        if (denominator == 0) return false;
        return ((float)numerator/denominator) % 1 == 0;
    }
    
    public boolean equals(Fraction f) {
        return (this.numerator == f.numerator && this.denominator == f.denominator);
    }
    
    public String toString() {
        if(denominator == 1)
            return Integer.toString(numerator);
        
        return Integer.toString(numerator) +
            "/" + Integer.toString(denominator);
    }
}
