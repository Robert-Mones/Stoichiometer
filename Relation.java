
/**
 * A relation class to store relationships between variables
 *
 * @author Robbie Mones
 * @version 12/13/17
 */
public class Relation
{
    public int baseVar;
    public int relativeVar;
    public Fraction factor;
    
    /*
     * Relationship between variables
     * Ex. Relation(0, new Fraction(1,2), 1).toString()
     *  => z = 1/2y
     */
    Relation(int base, Fraction factor, int relative) {
        this.baseVar = base;
        this.relativeVar = relative;
        this.factor = factor;
    }
    
    public boolean involves(int var) {
        return (this.baseVar == var || this.relativeVar == var);
    }
    
    public boolean isRedundant() {
        return (this.baseVar == this.relativeVar);
    }
    
    public static boolean areRedundant(Relation r1, Relation r2) {
        return 
            (r1.baseVar == r2.baseVar && r1.relativeVar == r2.relativeVar && r1.factor.equals(r2.factor)) || 
            (r1.baseVar == r2.relativeVar && r1.relativeVar == r2.baseVar && Fraction.recip(r1.factor).equals(r2.factor));
    }
    
    public String toString() {
        return (char)(122-baseVar) +
            "=" + ((factor.numerator == factor.denominator) ? "" : factor.toString()) + 
            (char)(122-relativeVar);
    }
}
