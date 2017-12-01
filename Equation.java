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
    // Left side of the equation
    public Vector<Term> left;
    // Right side of the equation
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

    /**
     * A Equation is "broken down" if there is only one chemical element in it.
     * 
     * @return      true if the equation is broken down.
     */
    public boolean IsBrokenDown()
    {
        // Make sure the Equation is populated
        if (left.size() == 0 || right.size() == 0)
            return false;
        
        // Get the first element, and make sure all the other terms' elements
        // on both match by scanning the left and right side of the equation.
        int Element = left.get(0).element;
        for (int i = 1; i < left.size(); i++)
            if (left.get(i).element != Element)
                return false;

        for (int i = 0; i < right.size(); i++)
            if (right.get(i).element != Element)
                return false;
        
        // If this return statement is reached, all terms have the same
        // element, and the equation is "broken down"
        return true;
    }
    /**
     * Breaks the equation down into multiple equations so that each one has
     * all the terms of a particular element.
     * 
     * @return      A vector of equations, each equation with only one element
     */
    public Vector<Equation> BreakDown()
    {
        // Pulls out all the chemical elements found within the equation (i.e.
        // if the equation is Ax + By = Ax + Bz, it will pull out [A, B])
        Vector<Integer> elements = new Vector<Integer>();

        for (int i = 0; i < left.size(); i++)
            if (!elements.contains(left.get(i).element))
                elements.add(left.get(i).element);

        for (int i = 0; i < right.size(); i++)
            if (!elements.contains(right.get(i).element))
                elements.add(right.get(i).element);



        // Builds a new equation for each of the chemical elements, containing
        // all terms of the element (i.e. if the equation is Ax + By = Ax + Bz,
        // [Ax = Ax, By = Bz] is built). Newly built equations are put in a
        // vector, and returned.
        Vector<Equation> equations = new Vector<Equation>();
        for (int i = 0; i < elements.size(); i++)
        {
            int element = elements.get(i);
            Equation eq = new Equation();


            for (int j = 0; j < left.size(); j++)
                if (left.get(j).element == element)
                    eq.left.add(left.get(j));

            for (int j = 0; j < right.size(); j++)
                if (right.get(j).element == element)
                    eq.right.add(right.get(j));

            equations.add(eq);
        }
        return equations;
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
