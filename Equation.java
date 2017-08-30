import java.util.Vector;

/**
 * An Equation holds a collection of terms, and is used to find the relational
 * ratios between chemicals in the reactants and products.
 * 
 * Equation member variables are all public to emulate a C-struct
 *
 * @author Justin Stephens
 * @version 30 August, 2017
 */
public class Equation
{
    public Vector<Term> left;
    public Vector<Term> right;
    
    Equation()
    {
        this(new Vector<Term>(), new Vector<Term>());
    }
    Equation(Vector<Term> l, Vector<Term> r)
    {
        left = l;
        right = r;
    }
    
    public void addTermLeft(Term t)
    {
        left.add(t);
    }
    public void addTermRight(Term t)
    {
        right.add(t);
    }
    
    public String toString()
    {
        String str = "";
        
        if (left.size() != 0)
            str += left.get(0);
        for (int i = 1; i < left.size(); i++)
            str += " + " + left.get(i);
        
        str += " = ";
        
        if (right.size() != 0)
            str += right.get(0);
        for (int i = 1; i < right.size(); i++)
            str += " + " + right.get(i);
        
        return str;
    }
}
