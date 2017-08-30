import java.util.Vector;

/**
 * SystemSolver is a class meant to solve a System of Equations in a Stoichiometry
 * setting.
 *
 * @author Justin Stephens
 * @version 30 August, 2017
 */
public class SystemSolver
{
    public SystemSolver()
    {
    }
    
    public static void Test()
    {
        Equation Eq = new Equation();
        
        Eq.left.add(new Term(4, 3, 0, 3));
        Eq.left.add(new Term(5, 6, 1, 2));
        Eq.right.add(new Term(23, 1, 2, 2));
        Eq.right.add(new Term(4, 3, 3, 3));
        
        //System.out.println(t);
        System.out.println(Eq);
        System.out.println("Is Broken Down: " + Eq.IsBrokenDown());
        
        Vector<Equation> Eqs = Eq.BreakDown();
        System.out.println("Equations when broken down: " + Eqs.size());
        System.out.println("----------------");
        
        
        for (int i = 0; i < Eqs.size(); i++)
            System.out.println(Eqs.get(i));
    }
}
