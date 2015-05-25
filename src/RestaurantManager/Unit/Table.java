package RestaurantManager.Unit;

import java.util.ArrayList;

public class Table {
    private int tableNumber;
    private ArrayList<Item> items;
    private boolean available;

    public Table(int tableNumber, ArrayList<Item> items, boolean available) {
        this.tableNumber = tableNumber;
        this.items = items;
        this.available = available;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
    

    public int getInvoice(){
        int invoice = 0;
        for(Item t : this.items)
            invoice += t.getOverallPrice();

        return invoice;
    }
    
    public String getInvoiceToString(){
    	return getInvoice() + " Ft";
    }
    public boolean isInvoiceDiscount(){
        return this.getInvoice() >= 10000;
    }
    
    public int getInvoiceDiscount(){
        if(isInvoiceDiscount())
            return (int)(this.getInvoice() * 0.1);
        else
            return 0;
    }
    
    public int getItemDiscount(){
        int akcio = 0;
        for(Item t : this.items)
            if( t.getNumberOfItem() >= 5 )
                akcio += (int)(t.getNumberOfItem()/5) * t.getPrice();
                
        return akcio;
    }
    
    public String getDiscount(){
        int itemDiscount = getItemDiscount();
        int invoiceDiscount = getInvoiceDiscount();
        
        if(itemDiscount == 0 && invoiceDiscount == 0)
            return "no";
        else if(itemDiscount >= invoiceDiscount)
            return "item";
        else
            return "invoice";

    }
    
    public int getFinalInvoice(){
        switch(getDiscount()){
            case "no":
                return getInvoice();
            case "item":
                return getInvoice() - getItemDiscount();
            case "invoice":
                return getInvoice() - getInvoiceDiscount();
            default:
                return getInvoice();  
        }
    }
    
    public String getFinalInvoiceToString(){
    	return getFinalInvoice() + " Ft";
    }
    
    public void addItemForTable(Item item){
    	boolean isContains = false;
    	for(int i = 0; i < items.size() ; i++){
    		if(items.get(i).getID()== item.getID()){
    			items.get(i).setNumberOfItem(items.get(i).getNumberOfItem() + item.getNumberOfItem());
    			isContains = true;	
    		}
    	}
    	
    	if(!isContains)
    		items.add(item);
    }
    
    public String getItemListString(){
    	StringBuilder sb = new StringBuilder();
    	if(!items.isEmpty()){
	    	for(Item i : items)
	    		sb.append(" " + i.getName() + " - " + i.getNumberOfItem() + "x " +i.getPrice() + "Ft\n");
	    	return sb.toString();
    	}
    	else return "";


    }
    
   
    
}

