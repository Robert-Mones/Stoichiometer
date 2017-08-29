
/**
 * Write a description of class Stoichiometer here.
 *
 * @author (Robbie Mones)
 * @version (a version number or a date)
 */

import java.util.Scanner;
import java.util.Vector;

public class Stoichiometer
{
    public static Scanner sc = new Scanner(System.in);
    
    public static String strReactants;
    public static String strProducts;
    
    public static Vector<Molecule> reactants;
    public static Vector<Molecule> products;
    
    public static Vector<String> reactantElements;
    public static Vector<String> productElements;
    
    public static Vector<String> elements;
    
    public static void pl(String out) {
        System.out.println(out);
    }
    public static void pl() {
        System.out.println("");
    }
    public static void p(String out) {
        System.out.print(out);
    }
    public static String in() {
        return sc.nextLine();
    }
    public static String pointsToString(int x, int y) {
        return "(" + Integer.toString(x) + "," + Integer.toString(y) + ")";
    }
    public static String toStr(int a) {
        return Integer.toString(a);
    }
    
    
    public static Vector<Molecule> parseMolecules(String unparsedMolecules) {
        String workingCopy = unparsedMolecules;
        String[] parsedMolecules;
        Vector<Molecule> finished = new Vector<Molecule>();
        finished.clear();
        
        // Remove spaces
        workingCopy = workingCopy.replace(" ","");
        
        // Parse by '+' to get molecules
        parsedMolecules = workingCopy.split("\\+");
        
        // Split molecules into their elements and insert that 
        for(int i = 0; i < parsedMolecules.length; i++) {
            String workingMolecule = parsedMolecules[i];
            Vector<Integer> upperIndexes = new Vector<Integer>();
            Molecule moleculeToAdd = new Molecule();
            upperIndexes.clear();
            
            for(int j = 0; j < workingMolecule.length(); j++) {
                if(Character.isUpperCase(workingMolecule.charAt(j))) {
                    upperIndexes.add(j);
                }
            }
            
            for(int j = 0; j < upperIndexes.size(); j++) {
                String moleculeCode = "";
                if(j == upperIndexes.size()-1) {
                    moleculeCode = workingMolecule.substring(upperIndexes.elementAt(j));
                } else {
                    moleculeCode = workingMolecule.substring(upperIndexes.elementAt(j),upperIndexes.elementAt(j+1));
                }
                
                String elementSymbol = moleculeCode.replaceAll("\\d","");
                int elementQuantity = 1;
                
                try {
                    elementQuantity = Integer.parseInt(moleculeCode.replaceAll("[A-Za-z]",""));
                } catch(NumberFormatException e) {
                    elementQuantity = 1;
                }
                
                moleculeToAdd.addElement(new Element(elementSymbol,elementQuantity));
            }
            
            finished.addElement(moleculeToAdd);
        }
        
        
        return finished;
    }
    
    public static void printRandP(Vector<Molecule> reactants, Vector<Molecule> products) {
        pl();
        pl();
        
        pl("Reactants:");
        for(int i = 0; i < reactants.size(); i++) {
            Molecule currentMolecule = reactants.elementAt(i);
            for(int j = 0; j < currentMolecule.size(); j++) {
                Element currentElement = currentMolecule.getElement(j);
                pl(currentElement.toString());
            }
            pl();
        }
        
        pl();
        pl();
        
        pl("Products:");
        for(int i = 0; i < products.size(); i++) {
            Molecule currentMolecule = products.elementAt(i);
            for(int j = 0; j < currentMolecule.size(); j++) {
                Element currentElement = currentMolecule.getElement(j);
                pl(currentElement.toString());
            }
            pl();
        }
    }
    
    public static Vector<String> getElements(Vector<Molecule> molecules) {
        Vector<String> elements = new Vector<String>();
        elements.clear();
        
        for(int i = 0; i < molecules.size(); i++)
            for(int j = 0; j < molecules.elementAt(i).size(); j++)
                if(!elements.contains(molecules.elementAt(i).getElement(j).getSymbol()))
                    elements.add(molecules.elementAt(i).getElement(j).getSymbol());
        
        return elements;
    }
    
    public static void printElements(Vector<String> rE, Vector<String> pE) {
        pl();
        pl();
        pl("Reactant Elements:");
        for(int i = 0; i < rE.size(); i++) {
            pl(rE.elementAt(i));
        }
        
        pl();
        pl();
        pl("Product Elements:");
        for(int i = 0; i < pE.size(); i++) {
            pl(pE.elementAt(i));
        }
    }
    
    public static boolean verifyElements(Vector<String> rE, Vector<String> pE) {
        return true;
    }
    
    public static Vector<Integer> balanceEquation(Vector<Molecule> reactants, Vector<Molecule> products) {
        
        
        return new Vector<Integer>();
    }
    
    public static void main() {
        p("Reactants:");
        strReactants = in();
        
        p("Products:");
        strProducts = in();
        
        reactants = parseMolecules(strReactants);
        products = parseMolecules(strProducts);
        
        printRandP(reactants, products);
        
        reactantElements = getElements(reactants);
        productElements = getElements(products);
        
        printElements(reactantElements, productElements);
        
        if(!verifyElements(reactantElements, productElements)) {
            pl("Unbalancable elements");
            return;
        }
    }
}
