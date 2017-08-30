import java.util.Vector;

/**
 * Write a description of class SystemsSolver here.
 *
 * @author Justin Stephens
 * @version (a version number or a date)
 */
public class SystemsSolver
{
    public SystemsSolver()
    {
    }
    
    public static void TestCase()
    {
        Equation Eq = new Equation();
        
        Eq.left.add(new Term(4, 3, 0, 0));
        Eq.left.add(new Term(5, 1, 0, 0));
        Eq.right.add(new Term(4, 3, 0, 0));
        Eq.right.add(new Term(4, 3, 0, 0));
        
        //System.out.println(t);
        System.out.println(Eq);
    }
}
