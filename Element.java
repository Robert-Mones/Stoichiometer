public class Element
{
    private String symbol;
    private int quantity;
    
    public Element(String symbol, int quantity)
    {
        this.symbol = symbol;
        this.quantity = quantity;
    }
    
    public String getSymbol() {
        return symbol;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public String toString() {
        return symbol + "-" + Integer.toString(quantity);
    }
}
