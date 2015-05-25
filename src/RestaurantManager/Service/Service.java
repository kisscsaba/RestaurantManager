package RestaurantManager.Service;

import java.util.ArrayList;

import javax.swing.JComboBox;

import RestaurantManager.Unit.Item;
import RestaurantManager.Unit.Table;

public class Service {
	public Service(){}
	
	public void setComboBoxItems(ArrayList<Item> items, JComboBox<Item> comboBox ){
		comboBox.removeAllItems();
		if(!items.isEmpty()){
			comboBox.setVisible(true);
			for(Item item : items)
				comboBox.addItem(item);
		} else 
			comboBox.setVisible(false);
		
	}
	
	public boolean isInt(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    }
	    return true;
	}
	
	public int getLastTableNumber(ArrayList<Table> tables){
		if(!tables.isEmpty())
			return tables.get(tables.size()-1).getTableNumber();
		else
			return 0;
	}
	
	public ArrayList<Table> addTablesToTableList(ArrayList<Table> tables, int numberOfTables){
		int startTableNumber = getLastTableNumber(tables)+1;
		for(int i=0; i<numberOfTables; i++){
			tables.add(new Table(startTableNumber, new ArrayList<Item>(), true));
			startTableNumber++;
		}
		return tables;
	}
	
	public String getAvailableTablesInformation(ArrayList<Table> tables){
		int availableTables = 0;
		
		for(Table table : tables)
			if(table.isAvailable())
				availableTables++;
		
		return "Jelenleg elérhető asztalok száma: " + tables.size() + " (Ebből szabad: " + availableTables + ")";
	}
	
	public String[] getAvailableTablesList(ArrayList<Table> tables){
		ArrayList<String> al = new ArrayList<String>();
		
		for(Table table : tables)
			if(table.isAvailable())
				al.add(table.getTableNumber() + ". Asztal");
		
		String[] returnStringArray = new String[al.size()];
		returnStringArray = al.toArray(returnStringArray);
		return  returnStringArray;
	}
	
	public String getNotAvailableTablesString(ArrayList<Table> tables){
		int notAvailableTables = 0;
		
		for(Table table : tables)
			if(!table.isAvailable())
				notAvailableTables++;
		if(notAvailableTables != 0)
			return "Foglalt asztalok száma: " + notAvailableTables;
		else
			return null;
	}
	
	public int getAvailableTables(ArrayList<Table> tables){
		int availableTables = 0;
		
		for(Table table : tables)
			if(table.isAvailable())
				availableTables++;
		
		return  availableTables;
	}
	
	
	public int getTableNumberForString(String str){
		return Integer.parseInt(str.split("\\.")[0]);
	}

}
