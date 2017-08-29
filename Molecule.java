import java.util.Vector;

public class Molecule
{
    private Vector<Element> elementList;
    
    public Molecule()
    {
        elementList = new Vector<Element>();
        elementList.clear();
    }
    
    public void addElement(Element newElement) {
        elementList.add(newElement);
    }
    
    public void clear() {
        elementList.clear();
    }
    
    public Element getElement(int index) {
        return elementList.elementAt(index);
    }
    
    public int size() {
        return elementList.size();
    }
}
