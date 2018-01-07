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
    
    public boolean IsBrokenDown()
    {
        if (left.size() == 0 || right.size() == 0)
            return false;
        
        int Element = left.get(0).element;
        for (int i = 1; i < left.size(); i++)
            if (left.get(i).element != Element)
                return false;
        
        for (int i = 0; i < right.size(); i++)
            if (right.get(i).element != Element)
                return false;
        
        return true;
    }
    public Vector<Equation> BreakDown()
    {
        Vector<Integer> elements = new Vector<Integer>();
        
        for (int i = 0; i < left.size(); i++)
            if (!elements.contains(left.get(i).element))
                elements.add(left.get(i).element);
        
        for (int i = 0; i < right.size(); i++)
            if (!elements.contains(right.get(i).element))
                elements.add(right.get(i).element);
        
        
        
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
    public static Vector<Relation> solveRelations(Vector<Equation> eqs) {
        Vector<Relation> simpleRelations = solveSimpleRelations(eqs);
        Vector<Relation> compoundRelations = solveCompoundRelations(simpleRelations);
        Vector<Relation> complexRelations = solveComplexRelations(eqs, compoundRelations);
        return complexRelations;
    }
    
    public static Vector<Relation> solveSimpleRelations(Vector<Equation> eqs) {
        Vector<Relation> relations = new Vector<Relation>();
        
        for(int i = 0; i < eqs.size(); i++) {
            Equation tEq = eqs.get(i);
            Vector<Term> tLeft = tEq.left;
            Vector<Term> tRight = tEq.right;
            if(tLeft.size() == 1 && tRight.size() == 1) {
                Term tLeftTerm = tLeft.get(0);
                Term tRightTerm = tRight.get(0);
                //relations.add(new Relation(tRightTerm.variable, Fraction.divide(tLeftTerm.coefficient,tRightTerm.coefficient), tLeftTerm.variable));
                relations.add(new Relation(tLeftTerm.variable, Fraction.divide(tRightTerm.coefficient,tLeftTerm.coefficient), tRightTerm.variable));
            }
        }
        
        return relations;
    }
    public static Vector<Relation> solveCompoundRelations(Vector<Relation> relations) {
        Vector<Relation> newRelations = new Vector<Relation>();
        
        boolean stillSearching = true;
        while(stillSearching) {
            newRelations = new Vector<Relation>();
            int rSize = relations.size();
            for(int i = 0; i < relations.size()-1; i++) {
                for(int j = i+1; j < relations.size(); j++) {
                    Relation rel1 = relations.get(i);
                    Relation rel2 = relations.get(j);
                    Relation tRel = null;
                    
                    if(rel1.baseVar == rel2.baseVar) {
                        tRel = new Relation(rel1.relativeVar, Fraction.divide(rel2.factor, rel1.factor), rel2.relativeVar);
                    } else if(rel1.baseVar == rel2.relativeVar) {
                        tRel = new Relation(rel1.relativeVar, Fraction.divide(Fraction.recip(rel2.factor), rel1.factor), rel2.baseVar);
                    } else if(rel1.relativeVar == rel2.baseVar) {
                        tRel = new Relation(rel2.relativeVar, Fraction.divide(Fraction.recip(rel1.factor), rel2.factor), rel1.baseVar);
                    } else if(rel1.relativeVar == rel2.relativeVar) {
                        tRel = new Relation(rel1.baseVar, Fraction.divide(rel1.factor, rel2.factor), rel2.baseVar);
                    }
                    
                    if(tRel == null) continue;
                    
                    if(tRel.isRedundant()) {
                        continue;
                    }
                    
                    boolean newRel = true;
                    for(int k = 0; k < relations.size() && newRel; k++) {
                        if(Relation.areRedundant(relations.get(k), tRel)) {
                            newRel = false;
                        }
                    }
                    for(int k = 0; k < newRelations.size() && newRel; k++) {
                        if(Relation.areRedundant(newRelations.get(k), tRel)) {
                            newRel = false;
                        }
                    }
                    if(!newRel) continue;
                    
                    newRelations.add(tRel);
                }
            }
            relations.addAll(newRelations);
            stillSearching = (relations.size() != rSize);
            
            /*Stoichiometer.p(Integer.toString(relations.size()));
            Stoichiometer.p(":");
            Stoichiometer.p(Integer.toString(rSize));
            Stoichiometer.p(" ");*/
            Stoichiometer.pl(relations.toString());
        }
        
        return relations;
    }
    public static Vector<Relation> solveComplexRelations(Vector<Equation> eqs, Vector<Relation> relations) {
        Stoichiometer.pl();
        
        Vector<Integer> allVars = new Vector<Integer>();
        
        // First, find out which variables need to be solved for
        for(int i = 0; i < eqs.size(); i++) {
            for(int j = 0; j < eqs.get(i).left.size(); j++) {
                if(!allVars.contains(eqs.get(i).left.get(j).variable)) allVars.add(eqs.get(i).left.get(j).variable);
            }
            for(int j = 0; j < eqs.get(i).right.size(); j++) {
                if(!allVars.contains(eqs.get(i).right.get(j).variable)) allVars.add(eqs.get(i).right.get(j).variable);
            }
        }
        Stoichiometer.p("All variables: ");
        Stoichiometer.pl(allVars.toString());
        Stoichiometer.pl();
        
        
        Vector<Integer> solvedVars = new Vector<Integer>();
        
        // Next, find out which variables already are solved for
        for(int i = 0; i < relations.size(); i++) {
            if(!solvedVars.contains(relations.get(i).baseVar)) solvedVars.add(relations.get(i).baseVar);
            if(!solvedVars.contains(relations.get(i).relativeVar)) solvedVars.add(relations.get(i).relativeVar);
        }
        Stoichiometer.p("Solved variables: ");
        Stoichiometer.pl(solvedVars.toString());
        Stoichiometer.pl();
        
        
        Vector<Integer> missingVars = new Vector<Integer>();
        
        // Finally, compare the two vectors to see which variables need to be solved for
        for(int i = 0; i < allVars.size(); i++) {
            if(!solvedVars.contains(allVars.get(i))) missingVars.add(allVars.get(i));
        }
        Stoichiometer.p("Missing variables: ");
        Stoichiometer.pl(missingVars.toString());
        Stoichiometer.pl();
        
        
        for(int i = 0; i < eqs.size(); i++) {
            if(eqs.get(i).left.size() == 1 && eqs.get(i).right.size() == 1) continue;
            
            Equation e = eqs.get(i);
            Vector<Integer> lVars = new Vector<Integer>();
            Vector<Integer> rVars = new Vector<Integer>();
            
            for(int j = 0; j < e.left.size(); j++) {
                if(!lVars.contains(e.left.get(j).variable)) lVars.add(e.left.get(j).variable);
            }
            for(int j = 0; j < e.right.size(); j++) {
                if(!rVars.contains(e.right.get(j).variable)) rVars.add(e.right.get(j).variable);
            }
            
            Stoichiometer.p("Left:  ");
            Stoichiometer.pl(lVars.toString());
            
            Stoichiometer.p("Right: ");
            Stoichiometer.pl(rVars.toString());
        }
        
        for(int i = 0; i < eqs.size(); i++) {
            if(eqs.get(i).left.size() == 1 && eqs.get(i).right.size() == 1) continue;
            Equation e = eqs.get(i);
            
            Vector<Integer> possibleVars = solvedVars;
            
            
            for(int j = 0; j < e.left.size(); j++) {
                if(missingVars.contains(e.left.get(j))) continue;
                
                for(int k = 0; k < solvedVars.size(); k++) {
                    
                }
            }
        }
        
        return relations;
    }
    
    public void addTermLeft(Term t)
    {
        left.add(t);
    }
    public void addTermRight(Term t)
    {
        right.add(t);
    }
    
    public String toString(boolean printElem)
    {
        String str = "";
        
        if (left.size() != 0)
            str += left.get(0).toString(printElem);
        for (int i = 1; i < left.size(); i++)
            str += " + " + left.get(i).toString(printElem);
        
        str += " = ";
        
        if (right.size() != 0)
            str += right.get(0).toString(printElem);
        for (int i = 1; i < right.size(); i++)
            str += " + " + right.get(i).toString(printElem);
        
        return str;
    }
}
