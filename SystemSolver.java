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
    public static Vector<Integer> Solve(Equation Eq)
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
        Stoichiometer.pl();
        Vector<Relation> iRelations = findRelativeToZ(relations);
        Stoichiometer.pl(iRelations.toString());
        
        Vector<Integer> denoms = new Vector<Integer>();
        for(int i = 0; i < iRelations.size(); i++) {
            denoms.add(iRelations.get(i).factor.denominator);
        }
        Stoichiometer.pl(denoms.toString());
        int lcd = WizardMath.lcm(denoms);
        Stoichiometer.pl(Integer.toString(lcd));
        Stoichiometer.pl();
        
        Vector<Integer> quantities = new Vector<Integer>();
        quantities.setSize(32);
        
        quantities.set(0,lcd);
        for(int i = 0; i < iRelations.size(); i++) {
            Relation r = iRelations.get(i);
            quantities.set(r.baseVar, r.factor.intValue());
        }
        
        for(int i = 0; i < 32; i++)
            if(quantities.get(i) == null) {
                quantities.setSize(i);
                break;
            }
            
        Stoichiometer.pl(quantities.toString());
        return quantities;
    }
    
    public static Vector<Relation> findRelativeToZ(Vector<Relation> allRel) {
        Vector<Relation> zRel = new Vector<Relation>();
        
        for(int i = 0; i < allRel.size(); i++) {
            Relation r = allRel.get(i);
            
            if(r.relativeVar != 0) continue;
            
            boolean failed = false;
            for(int j = 0; j < zRel.size(); j++)
                if(r.baseVar == zRel.get(j).baseVar) { failed = true; continue; }
            if(failed) { Stoichiometer.p("Failed:"); Stoichiometer.pl(r.toString()); continue; }
            
            zRel.add(r);
        }
        
        return zRel;
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
    
    public static void TestSolveDR()
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
        
        Eq.left.add(new Term(3, 1, 3, 0)); // 3wA
        Eq.left.add(new Term(1, 1, 3, 1)); // wB
        Eq.left.add(new Term(1, 1, 2, 2)); // xC
        Eq.left.add(new Term(1, 1, 2, 3)); // xD
        
        Eq.right.add(new Term(1, 1, 1, 0)); // yA
        Eq.right.add(new Term(1, 1, 1, 3)); // yD
        Eq.right.add(new Term(3, 1, 0, 2)); // 3zC
        Eq.right.add(new Term(1, 1, 0, 1)); // zB
        
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
