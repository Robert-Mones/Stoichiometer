
/**
 * A Term is the block equations are built with. A single Term holds a variable,
 * a Element, and a fraction coefficient.
 * 
 * Term member variables are all public to emulate a C-struct
 *
 * @author Justin Stephens
 * @version 30 August, 2017
 */
public class Term
{
    public int numerator;
    public int denominator;
    public int variable;
    public int element;

    /**
     * Returns whether the term is a fraction or not (i.e. has a denominator
     * other than 1).
     * 
     * @param  num  The numerator of the coefficient
     * @param  den  The denominator of the coefficient
     * @param  v    The variable (z, y, x, w...)
     * @param  e    The element (A, B, C, D...)
     * @return True when the term is a fraction, False otherwise
     */
    Term (int num, int den, int v, int e)
    {
        numerator = num;
        denominator = den;
        variable = v;
        element = e;
    }
    Term()
    {
        this(0, 1, 0, 0);
    }
    
    /**
     * Returns whether the term is a fraction or not (i.e. has a denominator
     * other than 1).
     *
     * @return True when the term is a fraction
     */
    public boolean IsFraction()
    {
        return (denominator != 1);
    }
    
    /**
     * Returns whether the term is in a whole number form or not (i.e. the
     * numerator is divisible by the denominator without remainder)
     *
     * @return True when the term is a whole
     */
    public boolean IsWhole()
    {
        return ((float)numerator/denominator) % 1 == 0;
    }
    
    /**
     * Returns a human-readable representation of the Term's Contents
     * Ex. 1/2zA
     *
     * @return String containing Term
     */
    public String toString()
    {
        return numerator + ((this.IsFraction()) ? "/" + denominator : "")
        + (char)(122-variable) + (char)(element+65);
    }
}
