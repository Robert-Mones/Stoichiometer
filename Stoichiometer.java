
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
    
    public static int totalMolecules;
    
    public static void pl(String out) {
        System.out.println(out);
    }
    public static void pl(char out) {
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
        
        // Loop through each element in expression
        for(int i = 0; i < parsedMolecules.length; i++) {
            // Create some variables to store temporary data
            String workingMolecule = parsedMolecules[i];            // Molecule the algorithm is currently working on
            Vector<Integer> upperIndexes = new Vector<Integer>();   // A will-be empty vector to store indecies of uppercase letters
            Molecule moleculeToAdd = new Molecule();                // A temporary Molecule to be added to final list
            upperIndexes.clear();
            
            // Find indecies of uppercase letters and add them to a vector
            for(int j = 0; j < workingMolecule.length(); j++) {
                if(Character.isUpperCase(workingMolecule.charAt(j))) {
                    upperIndexes.add(j);
                }
            }
            
            for(int j = 0; j < upperIndexes.size(); j++) { // Loop through each element in the working molecule
                String moleculeCode = "";
                if(j == upperIndexes.size()-1) {    // If at the last uppercase index, get only letter of uppercase character
                    moleculeCode = workingMolecule.substring(upperIndexes.elementAt(j));
                } else {                            // otherwise, parse from the index to the next index
                    moleculeCode = workingMolecule.substring(upperIndexes.elementAt(j),upperIndexes.elementAt(j+1));
                }
                
                String elementSymbol = moleculeCode.replaceAll("\\d",""); // Get rid of numbers
                int elementQuantity = 1; // Assume 1 if no quantity
                
                try { // Remove letters and try to convert to integer
                    elementQuantity = Integer.parseInt(moleculeCode.replaceAll("[A-Za-z]",""));
                } catch(NumberFormatException e) { // If it fails, just use 1
                    elementQuantity = 1;
                }
                
                moleculeToAdd.addElement(new Element(elementSymbol,elementQuantity)); // Add the parsed element to the working molecule
            }
            
            finished.addElement(moleculeToAdd); // Add the finished molecule to the finalized molecule list
        }
        
        
        return finished; // Return the finalized molecule list
    }
    
    public static void printRandP(Vector<Molecule> reactants, Vector<Molecule> products) {
        // Print reactants and products with symbols and quantities
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
        // Get unified list of all involved elements in a molecule
        Vector<String> elements = new Vector<String>();
        elements.clear();
        
        for(int i = 0; i < molecules.size(); i++)
            for(int j = 0; j < molecules.elementAt(i).size(); j++)
                if(!elements.contains(molecules.elementAt(i).getElement(j).getSymbol()))
                    elements.add(molecules.elementAt(i).getElement(j).getSymbol());
        
        return elements;
    }
    
    public static void printElements(Vector<String> rE, Vector<String> pE) {
        // Print unified list of elements
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
        // I got lazy. This function only mostly works
        return rE.size() == pE.size();
    }
    
    public static String formEquation(Vector<Molecule> reactants, Vector<Molecule> products) {
        String reactantStr = "";
        String productStr = "";
        
        Vector<String> tElementSymbol = new Vector<String>();
        Vector<Character> tElementChar = new Vector<Character>();
        
        for(char currentConst = 'A'; currentConst < reactantElements.size()+65; currentConst++) {
            tElementSymbol.addElement(reactantElements.elementAt((int)(currentConst)-65));
            tElementChar.addElement(currentConst);
        }
        
        pl();
        pl();
        for(int i = 0; i < tElementSymbol.size(); i++) {
            pl(tElementSymbol.elementAt(i) + "->" + tElementChar.elementAt(i));
        }
        pl();
        pl();
        
        char currentVar = 'z';
        for(int i = 1; i < totalMolecules; i++) {
            currentVar--;
        }
        
        for(int i = 0; i < reactants.size(); i++) {
            reactantStr +=
                currentVar + "(";
            
            for(int j = 0; j < reactants.elementAt(i).size(); j++) {
                reactantStr +=
                    reactants.elementAt(i).getElement(j).getQuantityAsString() +
                    tElementChar.elementAt(tElementSymbol.indexOf(reactants.elementAt(i).getElement(j).getSymbol()));
                
                if(j < (reactants.elementAt(i).size()-1))
                    reactantStr += "+";
            }
            
            reactantStr += ")";
            
            if(i < (reactants.size()-1))
                reactantStr += "+";
            
            currentVar++;
        }
        
        for(int i = 0; i < products.size(); i++) {
            productStr +=
                currentVar + "(";
            
            for(int j = 0; j < products.elementAt(i).size(); j++) {
                productStr +=
                    products.elementAt(i).getElement(j).getQuantityAsString() +
                    tElementChar.elementAt(tElementSymbol.indexOf(products.elementAt(i).getElement(j).getSymbol()));
                
                if(j < (products.elementAt(i).size()-1))
                    productStr += "+";
            }
            
            productStr += ")";
            
            if(i < (products.size()-1))
                productStr += "+";
            
            currentVar++;
        }
        
        return reactantStr + "=" + productStr;
    }
    
    public static void main(String[] args) {
        // Input reactants
        p("Reactants:");
        strReactants = in();
        
        // Input products
        p("Products:");
        strProducts = in();
        
        // Parse reactants and products into Molecules
        reactants = parseMolecules(strReactants);
        products = parseMolecules(strProducts);
        
        // Print reactants and products (only for debug)
        printRandP(reactants, products);
        
        // Get unified list of elements involved in reaction and print it
        reactantElements = getElements(reactants);
        productElements = getElements(products);
        
        printElements(reactantElements, productElements);
        
        // Verify the reactants and products to ensure it can be balanced
        if(!verifyElements(reactantElements, productElements)) {
            pl("Unbalancable elements");
            return;
        }
        
        // 
        totalMolecules = reactants.size() + products.size();
        
        pl(formEquation(reactants, products));
    }
}
