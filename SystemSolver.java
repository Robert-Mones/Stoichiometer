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
        
        Stoichiometer.pl();
        Stoichiometer.pl("Separated equations:");
        for(int i = 0; i < Eqs.size(); i++) {
            Stoichiometer.p("\t");
            Stoichiometer.pl(Eqs.get(i).toString(true));
        }
        
        Stoichiometer.pl();
        Stoichiometer.pl("Mathematical equations:");
        for(int i = 0; i < Eqs.size(); i++) {
            Stoichiometer.p("\t");
            Stoichiometer.pl(Eqs.get(i).toString(false));
        }
        
        Stoichiometer.pl();
        
        Vector<Relation> relations = Equation.solveRelations(Eqs);
        Stoichiometer.pl();
        Stoichiometer.pl(relations.toString());
    }
    
    public static void TestSolve()
    {
        Equation Eq = new Equation();
        
        // Numerator, Denominator, Variable, Element
        /* Variable:
         * w = 3
         * x = 2
         * y = 1
         * z = 0
         */
        /* Element:
         * A = 0
         * B = 1
         * C = 2
         * D = 3
         */
        
        Eq.left.add(new Term(2, 1, 3, 0)); // 2wA
        Eq.left.add(new Term(1, 1, 3, 1)); // wB
        Eq.left.add(new Term(2, 1, 2, 2)); // 2xC
        Eq.left.add(new Term(1, 1, 2, 3)); // xD
        
        Eq.right.add(new Term(1, 1, 1, 0)); // yA
        Eq.right.add(new Term(2, 1, 1, 3)); // 2yD
        Eq.right.add(new Term(2, 1, 1, 2)); // 2yC
        Eq.right.add(new Term(1, 1, 0, 1)); // zB
        Eq.right.add(new Term(4, 1, 0, 2)); // 4zC
        
        Stoichiometer.pl(Eq.toString(true));
        
        Solve(Eq);
    }
    
    public static void SolvePhoto()
    {
        Equation Eq = new Equation();
        
        // Numerator, Denominator, Variable, Element
        /* Variable:
         * w = 3
         * x = 2
         * y = 1
         * z = 0
         */
        /* Element:
         * A = 0
         * B = 1
         * C = 2
         * D = 3
         */
        
        Eq.left.add(new Term(2, 1, 3, 0)); // 2wA
        Eq.left.add(new Term(1, 1, 3, 1)); // wB
        Eq.left.add(new Term(1, 1, 2, 2)); // xC
        Eq.left.add(new Term(2, 1, 2, 1)); // 2xB
        
        Eq.right.add(new Term(2, 1, 1, 1)); // 2yB
        Eq.right.add(new Term(6, 1, 0, 2)); // 6zC
        Eq.right.add(new Term(12, 1, 0, 0)); // 12zA
        Eq.right.add(new Term(6, 1, 0, 1)); // 6zB
        
        Stoichiometer.pl(Eq.toString(true));
        
        Solve(Eq);
    }
    
    public static void Test()
    {
        Term t = new Term(4,0,0,0);
        t.coefficient.isWhole();
    }
}
