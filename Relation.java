
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
    
    Relation(int base, int relative, Fraction factor) {
        this.baseVar = base;
        this.relativeVar = relative;
        this.factor = factor;
    }
}
