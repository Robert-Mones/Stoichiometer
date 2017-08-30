
/**
 * A Term is the block equations are built with. A single Term holds a variable,
 * a Element, and a fraction coefficient.
 * 
 * Term member variables are all public to emulate a C-struct
 *
 * @author Justin Stephens
 * @version 29 August, 2017
 */
public class Term
{
    public int numerator;
    public int denominator;
    public int variable;
    public int element;

    Term()
    {
        this(0, 1, 0, 0);
    }
    Term (int num, int den, int v, int e)
    {
        numerator = num;
        denominator = den;
        variable = v;
        element = e;
    }
    
    public boolean IsFraction()
    {
        return (denominator != 1);
    }
    
    public String toString()
    {
        return numerator + ((this.IsFraction()) ? "/" + denominator : "")
        + (char)(122-variable) + (char)(element+65);
    }
}
