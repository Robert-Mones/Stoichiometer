
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
    public Fraction coefficient;
    public int variable;
    public int element;

    /**
     * @param  num  The numerator of the coefficient
     * @param  den  The denominator of the coefficient
     * @param  v    The variable (z, y, x, w...)
     * @param  e    The chemical element (A, B, C, D...)
     */
    Term (Fraction c, int v, int e)
    {
        coefficient = c;
        variable = v;
        element = e;
    }
    Term()
    {
        this(new Fraction(0,1), 0, 0);
    }
    Term(int n, int d, int v, int e)
    {
        this(new Fraction(n,d), v, e);
    }
    
    /**
     * Returns a human-readable representation of the Term's Contents
     * Ex. Term(1, 2, 0, 0).toString() == "1/2zA"
     *
     * @return String containing Term
     */
    public String toString(boolean printElem)
    {
        if (this.coefficient.isFraction())
            return coefficient.numerator + "/" + coefficient.denominator
            + (char)(122-variable) + ((printElem) ? (char)(element+65) : "");
        else
            return ((coefficient.numerator == 1) ? "" : coefficient.numerator) + ""
            + (char)(122-variable) + ((printElem) ? (char)(element+65) : "");
    }
}
