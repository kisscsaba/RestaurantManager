package RestaurantManager.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import RestaurantManager.Service.Service;
import RestaurantManager.Unit.Item;
import RestaurantManager.Unit.Table;

public class JUnitTest {

	private Service service;
	
	private Item item1;
	private Item item2;
	private Item item3;
	private Item item4;
	private Table table1;
	private Table table2;
	private Table table3;
	private ArrayList<Table> tables;


	@Before
	public void setUp(){
		service = new Service();
		item1 = new Item(1,"Sör", "Ital", 10, 300);
		item2 = new Item(2,"Bor", "Ital", 3, 250);
		item3 = new Item(3,"Fagyi", "Étel", 40, 300);
		item4 = new Item(4,"Hamburger", "Étel", 4, 5000);
		
		table1 = new Table(1, new ArrayList<Item>( Arrays.asList(new Item[]{item1, item2}) ), false);
		table2 = new Table(2, new ArrayList<Item>( Arrays.asList(new Item[]{item1, item2, item3}) ), false);
		table3 = new Table(3, new ArrayList<Item>( Arrays.asList(new Item[]{item4}) ), false);
		tables = new ArrayList<Table>();
		tables.add(table1);
		tables.add(table2);
		tables.add(table3);
		
	}
	
	@Test
	public void itemGetOverallPrice(){
		assertEquals(item1.getOverallPrice(), 3000);
	}
	
	@Test
	public void table1GetInvoice(){
		assertEquals(table1.getInvoice(), 3750);
	}
	
	@Test
	public void table1IsInvoiceDiscount(){
		assertEquals(table1.isInvoiceDiscount(), false);
	}
	
	@Test
	public void table1GetInvoiceDiscount(){
		assertEquals(table1.getInvoiceDiscount(), 0);
	}
	
	@Test
	public void table1GetItemDiscount(){
		assertEquals(table1.getItemDiscount(), 600);
	}
	
	@Test
	public void table1GetDiscount(){
		assertEquals(table1.getDiscount(), "item");
	}
	
	@Test
	public void table1GetFinalInvoice(){
		assertEquals(table1.getFinalInvoice(), 3150);
	}
	
	@Test
	public void table2GetInvoice(){
		assertEquals(table2.getInvoice(), 15750);
	}
	
	@Test
	public void table2IsInvoiceDiscount(){
		assertEquals(table2.isInvoiceDiscount(), true);
	}
	
	@Test
	public void table2GetInvoiceDiscount(){
		assertEquals(table2.getInvoiceDiscount(), 1575);
	}
	
	@Test
	public void table2GetItemDiscount(){
		assertEquals(table2.getItemDiscount(), 3000);
	}
	
	@Test
	public void table2GetDiscount(){
		assertEquals(table2.getDiscount(), "item");
	}
	
	@Test
	public void table2GetFinalInvoice(){
		assertEquals(table2.getFinalInvoice(), 12750);
	}
	
	@Test
	public void table3GetInvoice(){
		assertEquals(table3.getInvoice(), 20000);
	}
	
	@Test
	public void table3IsInvoiceDiscount(){
		assertEquals(table3.isInvoiceDiscount(), true);
	}
	
	@Test
	public void table3GetInvoiceDiscount(){
		assertEquals(table3.getInvoiceDiscount(), 2000);
	}
	
	@Test
	public void table3GetItemDiscount(){
		assertEquals(table3.getItemDiscount(), 0);
	}
	
	@Test
	public void table3GetDiscount(){
		assertEquals(table3.getDiscount(), "invoice");
	}
	
	@Test
	public void table3GetFinalInvoice(){
		assertEquals(table3.getFinalInvoice(), 18000);
	}
	
	@Test
	public void table3AddItemForTable1(){
		tables.get(0).addItemForTable(item1);
		assertEquals(tables.get(0).getItems().get(0).getNumberOfItem(), 20);
	}
	
	@Test
	public void table3AddItemForTable2(){
		tables.get(0).addItemForTable(item1);
		assertEquals(tables.get(0).getItems().size(), 2);
	}
	
	@Test
	public void table3AddItemForTable3(){
		tables.get(0).addItemForTable(item3);
		assertEquals(tables.get(0).getItems().size(), 3);
	}
	
	@Test
	public void serviceIsInt1(){
		assertEquals(service.isInt("Test"), false);
	}
	
	@Test
	public void serviceIsInt2(){
		assertEquals(service.isInt("10"), true);
	}
	
	@Test
	public void serviceGetLastTableNumber1(){
		assertEquals(service.getLastTableNumber(tables), 3);
	}
	
	@Test
	public void serviceGetLastTableNumber2(){
		ArrayList<Table> tables1 = new ArrayList<Table>();
		assertEquals(service.getLastTableNumber(tables1), 0);
	}
	
	@Test
	public void serviceAddTablesToTableList(){
		assertEquals(service.addTablesToTableList(tables, 2).size(), 5);
	}
	
	@Test
	public void serviceGetAvailableTablesInformation1(){
		assertEquals(service.getAvailableTablesInformation(tables), "Jelenleg elérhető asztalok száma: 3 (Ebből szabad: 0)");
	}
	
	@Test
	public void serviceGetAvailableTablesInformation2(){
		tables = service.addTablesToTableList(tables, 2);
		assertEquals(service.getAvailableTablesInformation(tables), "Jelenleg elérhető asztalok száma: 5 (Ebből szabad: 2)");
	}
	
	@Test
	public void serviceGetAvailableTablesList1(){
		assertEquals(service.getAvailableTablesList(tables).length, 0);
	}
	
	@Test
	public void serviceGetAvailableTablesList2(){
		tables = service.addTablesToTableList(tables, 2);
		assertEquals(service.getAvailableTablesList(tables).length, 2);
	}
	
	@Test
	public void serviceGetNotAvailableTablesString1(){
		assertEquals(service.getNotAvailableTablesString(tables), "Foglalt asztalok száma: 3");
	}
	
	@Test
	public void serviceGetNotAvailableTablesString2(){
		tables = service.addTablesToTableList(tables, 2);
		assertEquals(service.getNotAvailableTablesString(tables), "Foglalt asztalok száma: 3");
	}
	
	@Test
	public void serviceGetAvailableTables1(){
		assertEquals(service.getAvailableTables(tables), 0);
	}
	
	@Test
	public void serviceGetAvailableTables2(){
		tables = service.addTablesToTableList(tables, 2);
		assertEquals(service.getAvailableTables(tables), 2);
	}
	
	@Test
	public void serviceGetTableNumberForString(){
		assertEquals(service.getTableNumberForString("2. Asztal"), 2);
	}
	
	

}
