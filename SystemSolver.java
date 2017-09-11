import java.util.Vector;

/**
 * SystemSolver is a class meant to solve a System of Equations in a Stoichiometry
 * setting.
 *
 * @author Justin Stephens
 * @version 31 August, 2017
 */
public class SystemSolver
{    
    public static void Solve(Equation Eq)
    {                
        Vector<Equation> Eqs = Eq.BreakDown();
        
        for (int j = 0; j < Eqs.size(); j++)
        {
            System.out.println(Eqs.get(j));
        }
    }
    
    public static void TestSolve()
    {
        Equation Eq = new Equation();
        
        Eq.left.add(new Term(2, 1, 4, 0));
        Eq.left.add(new Term(6, 1, 4, 1));
        Eq.left.add(new Term(1, 1, 4, 2));
        Eq.left.add(new Term(2, 1, 3, 3));
        
        Eq.right.add(new Term(1, 1, 2, 0));
        Eq.right.add(new Term(2, 1, 2, 3));
        Eq.right.add(new Term(2, 1, 1, 1));
        Eq.right.add(new Term(1, 1, 1, 3));
        Eq.right.add(new Term(1, 1, 0, 2));
        Eq.right.add(new Term(1, 1, 0, 3));
        
        Solve(Eq);
    }
    
    public static void Test()
    {
        Term t = new Term(4,0,0,0);
        t.IsWhole();
    }
}
