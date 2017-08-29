
/**
 * Write a description of class Term here.
 *
 * @author Justin Stephens
 * @version 29 August, 2017
 */
public class Term
{
    public double coefficient;
    public int variable;
    public int element;

    Term()
    {
        this(1.0, 0, 0);
    }
    Term (double c, int v, int e)
    {
        coefficient = c;
        variable = v;
        element = e;
    }
}
