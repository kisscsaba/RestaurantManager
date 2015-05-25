package RestaurantManager.UI;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import RestaurantManager.Database.SystemDAO;
import RestaurantManager.Service.Service;
import RestaurantManager.Unit.Item;
import RestaurantManager.Unit.Table;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;

public class UI extends JFrame {

	private static final long serialVersionUID = -3970037334457903443L;
	SystemDAO dao = new SystemDAO();
	Service service = new Service();
	private String tableManagerSelectedItemType = "";
	private int selectedItemID = 0;
	private int selectedTableNumber = 0;
	private Item item = null;
	private ArrayList<Table> tables = new ArrayList<Table>();
	 
	
	private JPanel WindowPanel;
	private JPanel TableManagerPanel;
	private JPanel TableReservationPanel;
	private JPanel ItemManagerPanel;
	private JPanel ItemAddPanel;
	private JPanel TableSettingsPanel;
	private JPanel tableInformationPanel;


	private JMenuBar menuBar;
	private JMenu manuTables;
	private JMenu menuSettings;
	private JMenuItem tablesManagerMenu;
	private JMenuItem tablesReservationMenu;
	private JMenuItem settingsItemsMenu;
	private JMenuItem settingsTablesMenu;
	private JMenuItem settingsAddItemsMenu;
	
	private JComboBox<String> itemTypeSelector;
	private JComboBox<Item> itemSelector;
	private JPanel itemInformationPanel;
	private JSeparator separator;
	private JSeparator separator2;
	private JLabel itemEditLabel;
	private JLabel itemEditPriceLabel;
	private JLabel itemEditNumberLabel;
	private JTextField itemEditPriceValue;
	private JTextField itemEditNumberValue;
	private JLabel itemEditFtLabel;
	private JButton itemEditButton;
	private JButton itemDeleteButton;
	private JLabel itemEditResultLabel;
	
	private JLabel itemAddLabel;
	private JSeparator itemAddSeparatorStart;
	private JSeparator itemAddSeparatorEnd;
	private JLabel itemAddResultLabel;
	private JLabel itemAddTypeLabel;
	private JTextField itemAddTypeValue;
	private JLabel itemAddNameLabel;
	private JTextField itemAddNameValue;
	private JLabel itemAddPriceLabel;
	private JLabel itemAddPriceFtLabel;
	private JTextField itemAddPriceValue;
	private JLabel itemAddNumberLabel;
	private JTextField itemAddNumberValue;
	private JButton itemAddButton;
	
	private JLabel tableSettingsLabel;
	private JSeparator tableSettingsSeparatorStart;
	private JSeparator tableSettingsSeparatorEnd;
	private JLabel tableAddNumberLabel;
	private JTextField tableAddNumberValue;
	private JButton tableAddButton;
	private JLabel tableSettingsResultLabel;
	
	private JLabel tableManaherLabel;
	private JSeparator tableManagerSeparatorHorizontal;
	private JSeparator tableManagerSeparatorStart;
	private JTree tree;
	private JLabel selectedTableLabel;
	private JComboBox<String> tableManagerItemTypeSelector;
	private JComboBox<Item> tableManagerItemSelector;
	private JTextField tableItemAddNumberValue;
	private JLabel tableAddItemNumberDBLabel;
	private JButton tableDeleteItemButton;
	private JButton tableAddItemButton;
	private JLabel tableItemsLabel;
	private JTextPane tableItemList;
	private JLabel tableOverallInvoiceLabel;
	private JLabel tableDiscountLabel;
	private JLabel tablePayableLabel;
	private JLabel tableActionResultLabel;
	private JButton tableClearButton;
	
	private JComboBox<String> tableReservationTableSelector;
	private JLabel tableReservationLabel;
	private JLabel tableReservationResultLabel;
	private JButton tableReservationButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI frame = new UI();
					frame.setVisible(true);
					frame.setTitle("Étterem Kezelő");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public UI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 500, 400);
		WindowPanel = new JPanel();
		WindowPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		WindowPanel.setLayout(null);
		setResizable(false);
		//Menu start
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 500, 25);

			manuTables = new JMenu("Asztalok");
			menuBar.add(manuTables);
			
				tablesManagerMenu = new JMenuItem("Asztalok kezelése");
				manuTables.add(tablesManagerMenu);
				
				tablesReservationMenu = new JMenuItem("Asztal foglalás");
				manuTables.add(tablesReservationMenu);

			menuSettings = new JMenu("Beállítások");
			menuBar.add(menuSettings);
			
				settingsItemsMenu = new JMenuItem("Termékek");
				menuSettings.add(settingsItemsMenu);
				
				settingsAddItemsMenu = new JMenuItem("Termék hozzáadás");
				menuSettings.add(settingsAddItemsMenu);
			
				settingsTablesMenu = new JMenuItem("Asztalok");
				menuSettings.add(settingsTablesMenu);

			WindowPanel.add(menuBar);
			
			//INIT PANELS START	
			TableManagerPanel = new JPanel();
			TableManagerPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
			TableManagerPanel.setLayout(null);
			TableManagerPanel.setVisible(false);
			TableManagerPanel.setBounds(0, 25, 485, 335);
			WindowPanel.add(TableManagerPanel);
			
			TableReservationPanel = new JPanel();
			TableReservationPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
			TableReservationPanel.setLayout(null);
			TableReservationPanel.setVisible(false);
			TableReservationPanel.setBounds(0, 25, 485, 335);
			WindowPanel.add(TableReservationPanel);
			
			ItemManagerPanel = new JPanel();
			ItemManagerPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
			ItemManagerPanel.setLayout(null);
			ItemManagerPanel.setVisible(false);
			ItemManagerPanel.setBounds(0, 25, 485, 335);
			WindowPanel.add(ItemManagerPanel);
			
			ItemAddPanel = new JPanel();
			ItemAddPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
			ItemAddPanel.setLayout(null);
			ItemAddPanel.setVisible(false);
			ItemAddPanel.setBounds(0, 25, 485, 335);
			WindowPanel.add(ItemAddPanel);
			
			TableSettingsPanel = new JPanel();
			TableSettingsPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
			TableSettingsPanel.setLayout(null);
			TableSettingsPanel.setVisible(true);
			TableSettingsPanel.setBounds(0, 25, 485, 335);
			WindowPanel.add(TableSettingsPanel);
			
			//INIT PANELS END	
			
			//MENU ACTIONS
			tablesManagerMenu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setTitle("Étterem Kezelő - Asztalok kezelése");
					String notAvailableTables = service.getNotAvailableTablesString(tables);
					if(notAvailableTables == null){
						tableManaherLabel.setText("Nincs foglalt asztal!");	
					} else {
						tableManaherLabel.setText(notAvailableTables);
					}
					DefaultTreeModel dtm = new DefaultTreeModel(
							new DefaultMutableTreeNode("Foglalt asztalok:") {
								private static final long serialVersionUID = 1L;

								{
									for(Table table : tables)
										if(!table.isAvailable())
											add(new DefaultMutableTreeNode(table.getTableNumber() + ". Asztal"));

								}
							}
						);

					tree.setModel(dtm);		
					TableManagerPanel.setVisible(true);
					ItemManagerPanel.setVisible(false);
					ItemAddPanel.setVisible(false);
					TableSettingsPanel.setVisible(false);
					tableInformationPanel.setVisible(false);
					TableReservationPanel.setVisible(false);
				}
			});
			
			tablesReservationMenu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(service.getAvailableTables(tables) == 0){
						tableReservationLabel.setText("Jelenleg nincs szabad asztal!");
	                	tableReservationButton.setEnabled(false);
	                	tableReservationTableSelector.setEnabled(false);
					}
					else{
						tableReservationLabel.setText("Szabad asztalok száma: " + service.getAvailableTables(tables));
	                	tableReservationButton.setEnabled(true);
	                	tableReservationTableSelector.setEnabled(true);
					}
					tableReservationTableSelector.setModel(new DefaultComboBoxModel<String>(service.getAvailableTablesList(tables)));
                	tableReservationResultLabel.setText("");
					setTitle("Étterem Kezelő - Asztalfoglalás");	
					
					TableManagerPanel.setVisible(false);
					ItemManagerPanel.setVisible(false);
					ItemAddPanel.setVisible(false);
					TableSettingsPanel.setVisible(false);
					tableInformationPanel.setVisible(false);
					TableReservationPanel.setVisible(true);
				}
			});
			
			settingsItemsMenu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setTitle("Étterem Kezelő - Tételek kezelése");
					TableManagerPanel.setVisible(false);
					ItemManagerPanel.setVisible(true);
					ItemAddPanel.setVisible(false);
					TableSettingsPanel.setVisible(false);
					TableReservationPanel.setVisible(false);
					itemInformationPanel.setVisible(false);
					itemSelector.setVisible(false);
					itemTypeSelector.setModel(new DefaultComboBoxModel<String>(dao.getItemTypes()));
				}
			});
			settingsAddItemsMenu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setTitle("Étterem Kezelő - Tétel hozzáadás");
					TableManagerPanel.setVisible(false);
					ItemManagerPanel.setVisible(false);
					ItemAddPanel.setVisible(true);
					TableSettingsPanel.setVisible(false);
					TableReservationPanel.setVisible(false);
					
					itemAddTypeValue.setText("");
					itemAddNameValue.setText("");
					itemAddPriceValue.setText("");
					itemAddNumberValue.setText("");
					itemAddResultLabel.setText("");
				}
			});

			settingsTablesMenu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setTitle("Étterem Kezelő - Aszatlok beállítása");
					if(tables.isEmpty())
						tableSettingsLabel.setText("Jelenleg nincs elérhető asztal.");
					else{
						tableSettingsLabel.setText(service.getAvailableTablesInformation(tables));
					}
					TableManagerPanel.setVisible(false);
					ItemManagerPanel.setVisible(false);
					ItemAddPanel.setVisible(false);
					TableSettingsPanel.setVisible(true);
					TableReservationPanel.setVisible(false);
					tableSettingsResultLabel.setText("");
				}
			});
			//MENU ACTIONS AND
		
		//MENU END

		//ITEM MANAGER START			
			itemTypeSelector = new JComboBox<>();
			itemTypeSelector.setModel(new DefaultComboBoxModel<String>(dao.getItemTypes()));
			itemTypeSelector.setBounds(5, 5, 120, 20);
			ItemManagerPanel.add(itemTypeSelector);
			
			itemSelector = new JComboBox<Item>();
			itemSelector.setModel(new DefaultComboBoxModel<Item>());
			itemSelector.setVisible(false);
			itemSelector.setBounds(5, 30, 120, 20);
			ItemManagerPanel.add(itemSelector);
			
			itemInformationPanel = new JPanel();
			itemInformationPanel.setBounds(0, 60, 496, 286);
			ItemManagerPanel.add(itemInformationPanel);
			itemInformationPanel.setVisible(false);
			itemInformationPanel.setLayout(null);
			
			separator = new JSeparator();
			separator.setBounds(5, 0, 490, 10);
			itemInformationPanel.add(separator);
			
			separator2 = new JSeparator();
			separator2.setBounds(5, 90, 490, 10);
			itemInformationPanel.add(separator2);

			itemEditLabel = new JLabel("");
			itemEditLabel.setBounds(5, 5, 250, 15);
			itemInformationPanel.add(itemEditLabel);
			
			itemEditPriceLabel = new JLabel("Ár:");
			itemEditPriceLabel.setBounds(20, 38, 50, 15);;
			itemInformationPanel.add(itemEditPriceLabel);
			
			itemEditNumberLabel = new JLabel("Darab:");
			itemEditNumberLabel.setBounds(20, 63, 50, 15);
			itemInformationPanel.add(itemEditNumberLabel);
			
			itemEditPriceValue = new JTextField();
			itemEditPriceValue.setBounds(65, 35, 100, 20);
			itemInformationPanel.add(itemEditPriceValue);

			itemEditNumberValue = new JTextField();
			itemEditNumberValue.setBounds(65, 60, 100, 20);
			itemInformationPanel.add(itemEditNumberValue);
			
			itemEditFtLabel = new JLabel("Ft");
			itemEditFtLabel.setBounds(170, 38, 50, 15);
			itemInformationPanel.add(itemEditFtLabel);
			
			itemEditButton = new JButton("Módosítás");
			itemEditButton.setBounds(275, 60, 100, 20);
			itemInformationPanel.add(itemEditButton);
			
			itemDeleteButton = new JButton("Törlés");
			itemDeleteButton.setBounds(385, 60, 100, 20);
			itemInformationPanel.add(itemDeleteButton);
			
			itemEditResultLabel = new JLabel();
			itemEditResultLabel.setBounds(5, 95, 490, 25);
			itemInformationPanel.add(itemEditResultLabel);
			
			itemTypeSelector.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(!((String)itemTypeSelector.getSelectedItem()).equals("Termék típus")){
						service.setComboBoxItems(dao.getItemsByItemType((String)itemTypeSelector.getSelectedItem()), itemSelector);
						itemSelector.setVisible(true);
					} else {
						itemSelector.setVisible(false);
					}
					itemEditResultLabel.setText("");
				}
				
			});
			
			itemSelector.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(itemSelector.getSelectedItem()!= null){
						item = (Item)itemSelector.getSelectedItem();
						if(selectedItemID != item.getID()){
							selectedItemID = item.getID();
						}
						itemEditLabel.setText("Termék: " + item.getName());
						itemEditNumberValue.setText(Integer.toString(item.getNumberOfItem()));
						itemEditPriceValue.setText(Integer.toString(item.getPrice()));
						itemInformationPanel.setVisible(true);
						itemEditResultLabel.setText("");
						
					}
				}
			});
			
			itemEditButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(itemEditNumberValue.getText().isEmpty() || itemEditPriceValue.getText().isEmpty())
					{
						itemEditResultLabel.setText("Hiányzik adat a módosításhoz!");
						itemEditResultLabel.setForeground(Color.red);
					} 
					else if(!service.isInt(itemEditNumberValue.getText()) || !service.isInt(itemEditPriceValue.getText()))
					{
						itemEditResultLabel.setText("Nem megfelelő adattípus!");
						itemEditResultLabel.setForeground(Color.red);
					} 
					else
					{
						item.setNumberOfItem(Integer.parseInt(itemEditNumberValue.getText()));
						item.setPrice(Integer.parseInt(itemEditPriceValue.getText()));
						try {
							dao.updateItem(item);
							itemEditResultLabel.setText("Sikeres módosítás!");
							itemEditResultLabel.setForeground(Color.green);	
						} catch (SQLException e) {
							itemEditResultLabel.setText("Sikertelen módosítás!");
							itemEditResultLabel.setForeground(Color.red);	
						}

					}
				}
			});
			
			itemDeleteButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						dao.deleteItem(item.getID());
						service.setComboBoxItems(dao.getItemsByItemType((String)itemTypeSelector.getSelectedItem()), itemSelector);
						if(itemSelector.getItemCount() == 0){
							itemTypeSelector.setModel(new DefaultComboBoxModel<String>(dao.getItemTypes()));
							itemSelector.setVisible(false);
							itemInformationPanel.setVisible(false);
						}
						itemEditResultLabel.setText("Sikeres törlés!");
						itemEditResultLabel.setForeground(Color.green);
					} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
						itemEditResultLabel.setText("Sikertelen törlés!");
						itemEditResultLabel.setForeground(Color.red);	
					}
				}
			});
		//ITEM MANAGER END
			
		//ITEM ADD START			
			itemAddLabel = new JLabel("Új termék hozzáadása");
			itemAddLabel.setBounds(10, 10, 200, 15);
			ItemAddPanel.add(itemAddLabel);
			
			itemAddSeparatorStart = new JSeparator();
			itemAddSeparatorStart.setBounds(5, 30, 490, 10);
			ItemAddPanel.add(itemAddSeparatorStart);
			
			itemAddSeparatorEnd = new JSeparator();
			itemAddSeparatorEnd.setBounds(5, 100, 490, 10);
			ItemAddPanel.add(itemAddSeparatorEnd);
			
			itemAddResultLabel = new JLabel();
			itemAddResultLabel.setBounds(10, 110, 350, 15);
			ItemAddPanel.add(itemAddResultLabel);
			
			itemAddTypeLabel = new JLabel("Típus:");
			itemAddTypeLabel.setBounds(10, 40, 50, 15);
			ItemAddPanel.add(itemAddTypeLabel);
			
			itemAddTypeValue = new JTextField();
			itemAddTypeValue.setBounds(60, 40, 100, 20);
			ItemAddPanel.add(itemAddTypeValue);
			
			itemAddNameLabel = new JLabel("Név:");
			itemAddNameLabel.setBounds(10, 70, 50, 15);
			ItemAddPanel.add(itemAddNameLabel);
			
			itemAddNameValue = new JTextField();
			itemAddNameValue.setBounds(60, 70, 100, 20);
			ItemAddPanel.add(itemAddNameValue);
			
			itemAddPriceLabel = new JLabel("Ár:");
			itemAddPriceLabel.setBounds(180, 40, 50, 15);
			ItemAddPanel.add(itemAddPriceLabel);
			
			itemAddPriceFtLabel = new JLabel("Ft");
			itemAddPriceFtLabel.setBounds(335, 40, 50, 15);
			ItemAddPanel.add(itemAddPriceFtLabel);
			
			itemAddPriceValue = new JTextField();
			itemAddPriceValue.setBounds(230, 40, 100, 20);
			ItemAddPanel.add(itemAddPriceValue);
			
			itemAddNumberLabel = new JLabel("Darab:");
			itemAddNumberLabel.setBounds(180, 70, 50, 15);
			ItemAddPanel.add(itemAddNumberLabel);
			
			itemAddNumberValue = new JTextField();
			itemAddNumberValue.setBounds(230, 70, 100, 20);
			ItemAddPanel.add(itemAddNumberValue);
			
			itemAddButton = new JButton("Hozzáadás");
			itemAddButton.setBounds(360, 70, 100, 20);
			ItemAddPanel.add(itemAddButton);
			
			itemAddButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(itemAddTypeValue.getText().isEmpty() || itemAddNameValue.getText().isEmpty() || itemAddPriceValue.getText().isEmpty() || itemAddNumberValue.getText().isEmpty() )
					{
						itemAddResultLabel.setText("Hiányzik adat a hozzáadáshoz!");
						itemAddResultLabel.setForeground(Color.red);
					} 
					else if(!service.isInt(itemAddNumberValue.getText()) || !service.isInt(itemAddPriceValue.getText()))
					{
						itemAddResultLabel.setText("Nem megfelelő adattípus!");
						itemAddResultLabel.setForeground(Color.red);
					} 
					else 
					{
						try {
							dao.insertItem(new Item(0, itemAddNameValue.getText(), itemAddTypeValue.getText(), Integer.parseInt(itemAddNumberValue.getText()), Integer.parseInt(itemAddPriceValue.getText())));
							itemAddResultLabel.setText("Sikeres hozzáadás!");
							itemAddTypeValue.setText("");
							itemAddNameValue.setText("");
							itemAddPriceValue.setText("");
							itemAddNumberValue.setText("");
							itemAddResultLabel.setForeground(Color.green);
						} catch (NumberFormatException | InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
							itemAddResultLabel.setText("Sikertelen hozzáadás!");
							itemAddResultLabel.setForeground(Color.red);	
						}
					}
					
				}
			});
		
		//ITEM ADD END
		//TABLE SETTINGS START
			tableSettingsLabel = new JLabel("Jelenleg nincs asztal a rendszerben.");
			tableSettingsLabel.setBounds(10, 10, 350, 15);
			TableSettingsPanel.add(tableSettingsLabel);
			
			tableSettingsSeparatorStart = new JSeparator();
			tableSettingsSeparatorStart.setBounds(5, 30, 490, 10);
			TableSettingsPanel.add(tableSettingsSeparatorStart);
			
			tableSettingsSeparatorEnd = new JSeparator();
			tableSettingsSeparatorEnd.setBounds(5, 70, 490, 10);
			TableSettingsPanel.add(tableSettingsSeparatorEnd);
			
			tableAddNumberLabel = new JLabel("Létrehozandó asztalok száma:");
			tableAddNumberLabel.setBounds(10, 42, 180, 15);
			TableSettingsPanel.add(tableAddNumberLabel);
			
			tableAddNumberValue = new JTextField();
			tableAddNumberValue.setBounds(190, 40, 50, 21);
			TableSettingsPanel.add(tableAddNumberValue);
			
			tableAddButton = new JButton("Hozzáadás");
			tableAddButton.setBounds(245, 40, 100, 20);
			TableSettingsPanel.add(tableAddButton);
			
			tableSettingsResultLabel = new JLabel();
			tableSettingsResultLabel.setBounds(10, 75, 350, 15);
			TableSettingsPanel.add(tableSettingsResultLabel);
			
			tableAddButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(tableAddNumberValue.getText().isEmpty())
					{
						tableSettingsResultLabel.setText("Hiányzik adat a létrehozáshoz!");
						tableSettingsResultLabel.setForeground(Color.red);
					} 
					else if(!service.isInt(tableAddNumberValue.getText()))
					{
						tableSettingsResultLabel.setText("Az asztalok száma pozitív egész szám!");
						tableSettingsResultLabel.setForeground(Color.red);
					}
					else if(Integer.parseInt(tableAddNumberValue.getText()) <= 0)
					{
						tableSettingsResultLabel.setText("Az asztalok száma pozitív egész szám!");
						tableSettingsResultLabel.setForeground(Color.red);
					}
					else 
					{
						int addedTables = Integer.parseInt(tableAddNumberValue.getText());
						tables = service.addTablesToTableList(tables, addedTables );
						tableSettingsResultLabel.setText(addedTables + " új asztal létrehozva!");
						tableSettingsResultLabel.setForeground(Color.green);
						tableAddNumberValue.setText("");
						tableSettingsLabel.setText(service.getAvailableTablesInformation(tables));
					}
					
				}
			});
			
		//TABLE SETTINGS END	
		//TABLE MANAGER START
			tableManaherLabel = new JLabel("");
			tableManaherLabel.setBounds(10, 10, 350, 15);
			TableManagerPanel.add(tableManaherLabel);

			tableManagerSeparatorStart = new JSeparator();
			tableManagerSeparatorStart.setBounds(5, 30, 490, 10);
			TableManagerPanel.add(tableManagerSeparatorStart);
			
			tableManagerSeparatorHorizontal = new JSeparator();
			tableManagerSeparatorHorizontal.setOrientation(SwingConstants.VERTICAL);
			tableManagerSeparatorHorizontal.setBounds(126, 30, 10, 400);
			TableManagerPanel.add(tableManagerSeparatorHorizontal);

			tree = new JTree();
			tree.setBounds(5, 30, 120, 310);
			TableManagerPanel.add(tree);
			
			tableInformationPanel = new JPanel();
			tableInformationPanel.setBounds(126, 30, 400, 310);
			TableManagerPanel.add(tableInformationPanel);
			tableInformationPanel.setVisible(true);
			tableInformationPanel.setLayout(null);

			
			selectedTableLabel = new JLabel();
			selectedTableLabel.setBounds(5, 5, 180, 15);
			tableInformationPanel.add(selectedTableLabel);
			
			tableManagerItemTypeSelector = new JComboBox<>();
			tableManagerItemTypeSelector.setModel(new DefaultComboBoxModel<String>(dao.getItemTypes()));
			tableManagerItemTypeSelector.setBounds(5, 30, 120, 20);
			tableInformationPanel.add(tableManagerItemTypeSelector);
			
			tableManagerItemSelector = new JComboBox<Item>();
			tableManagerItemSelector.setModel(new DefaultComboBoxModel<Item>());
			tableManagerItemSelector.addItem(new Item(0,"Termék", null, 0, 0));
			tableManagerItemSelector.setBounds(5, 55, 120, 20);
			tableInformationPanel.add(tableManagerItemSelector);
			
			tableItemAddNumberValue = new JTextField();
			tableItemAddNumberValue.setBounds(130, 55, 50, 21);
			tableInformationPanel.add(tableItemAddNumberValue);
			
			tableAddItemNumberDBLabel = new JLabel("db");
			tableAddItemNumberDBLabel.setBounds(185, 57, 30, 15);
			tableInformationPanel.add(tableAddItemNumberDBLabel);
			
			tableDeleteItemButton = new JButton("Törlés");
			tableDeleteItemButton.setBounds(255, 30, 100, 20);
			tableDeleteItemButton.setEnabled(false);
			tableInformationPanel.add(tableDeleteItemButton);

			tableAddItemButton = new JButton("Hozzáadás");
			tableAddItemButton.setBounds(255, 55, 100, 20);
			tableAddItemButton.setEnabled(false);
			tableInformationPanel.add(tableAddItemButton);
			
			tableItemsLabel = new JLabel("Rendelés:");
			tableItemsLabel.setBounds(5, 80, 100, 20);
			tableInformationPanel.add(tableItemsLabel);
			
			tableItemList = new JTextPane();
			tableItemList.setBounds(5, 100, 150, 205);
			tableItemList.setEditable(false);
			tableItemList.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
			tableInformationPanel.add(tableItemList);
			
			tableOverallInvoiceLabel = new JLabel("Összesen:");
			tableOverallInvoiceLabel.setBounds(160, 100, 200, 20);
			tableInformationPanel.add(tableOverallInvoiceLabel);

			tableDiscountLabel = new JLabel("Kedvezmény: nincs");
			tableDiscountLabel.setBounds(160, 120, 200, 20);
			tableInformationPanel.add(tableDiscountLabel);

			tablePayableLabel = new JLabel("Fizetendő:");
			tablePayableLabel.setBounds(160, 140, 200, 20);
			tableInformationPanel.add(tablePayableLabel);

			tableActionResultLabel = new JLabel();
			tableActionResultLabel.setBounds(160, 260, 200, 20);
			tableInformationPanel.add(tableActionResultLabel);
			
			tableClearButton = new JButton("Fizetés");
			tableClearButton.setBounds(255, 285, 100, 20);
			tableInformationPanel.add(tableClearButton);
			
			tree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
		        public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
		        	if(tree.getSelectionPath() != null)
			        	if(!evt.getNewLeadSelectionPath().getLastPathComponent().toString().equals("Foglalt asztalok:")){
							selectedTableLabel.setText("Kiválasztott asztal: "+evt.getNewLeadSelectionPath().getLastPathComponent().toString());
							selectedTableNumber = service.getTableNumberForString(evt.getNewLeadSelectionPath().getLastPathComponent().toString());
							tableInformationPanel.setVisible(true);
							for(Table t : tables){
								if(t.getTableNumber() == selectedTableNumber){
									tableItemList.setText(t.getItemListString());
									tableOverallInvoiceLabel.setText("Összesen: " + t.getInvoiceToString());
									tablePayableLabel.setText("Fizetendő: " + t.getFinalInvoiceToString());
									switch(t.getDiscount()){
										case "no":
											tableDiscountLabel.setText("Kedvezmény: Nincs");
											break;
										case "item":
											tableDiscountLabel.setText("Kedvezmény: Termék");
											break;
										case "invoice":
											tableDiscountLabel.setText("Kedvezmény: Számla");
											break;
									}
									break;
								}
							}
			        	}
		        } 
		    });
			
			
			tableManagerItemTypeSelector.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(!tableManagerItemTypeSelector.getSelectedItem().toString().equals("Termék típus")){
						if(!tableManagerSelectedItemType.equals((String)tableManagerItemTypeSelector.getSelectedItem())){
							tableManagerSelectedItemType = (String)tableManagerItemTypeSelector.getSelectedItem();
							service.setComboBoxItems(dao.getItemsByItemType((String)tableManagerItemTypeSelector.getSelectedItem()), tableManagerItemSelector);
							tableDeleteItemButton.setEnabled(true);
							tableAddItemButton.setEnabled(true);
	
						}
					} else {
						if(!tableManagerSelectedItemType.equals((String)tableManagerItemTypeSelector.getSelectedItem())){
							tableManagerSelectedItemType = (String)tableManagerItemTypeSelector.getSelectedItem();
							tableDeleteItemButton.setEnabled(false);
							tableAddItemButton.setEnabled(false);
							tableManagerItemSelector.removeAllItems();
							tableManagerItemSelector.addItem(new Item(0,"Termék", null, 0, 0));
						}
					}
					
				}
			});
			
			tableAddItemButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(tableItemAddNumberValue.getText().isEmpty()){
						tableActionResultLabel.setText("Hiányzik adat a felvételhez!");
						tableActionResultLabel.setForeground(Color.red);
					} else if(!service.isInt(tableItemAddNumberValue.getText())){
						tableActionResultLabel.setText("Nem megfelelő adattípus!");
						tableActionResultLabel.setForeground(Color.red);
					} else {
						item = dao.getItem(((Item)tableManagerItemSelector.getSelectedItem()).getID());
						int itemNumber = Integer.parseInt(tableItemAddNumberValue.getText());
						if(itemNumber <= item.getNumberOfItem()){
							for(Table t : tables){
								if(t.getTableNumber() == selectedTableNumber){
									boolean itemIsFound = false;
									for(Item i : t.getItems())
										if(i.getID() == item.getID()){
											itemIsFound = true;
											i.setNumberOfItem(i.getNumberOfItem() + itemNumber);
										}
									
									if(!itemIsFound)
										t.addItemForTable(new Item(item.getID(), item.getName(), item.getType(), itemNumber, item.getPrice()));
									
									try {
										tableActionResultLabel.setText("Rendelés felvéve.");
										tableActionResultLabel.setForeground(Color.green);
										item.setNumberOfItem(item.getNumberOfItem() - itemNumber);
										dao.updateItem(item);
									} catch (SQLException e) {
										tableActionResultLabel.setText("Adatbázis hiba! Rendelés felvéve.");
										tableActionResultLabel.setForeground(Color.red);
									}
									
									tableItemList.setText(t.getItemListString());
									tableOverallInvoiceLabel.setText("Összesen: " + t.getInvoiceToString());
									tablePayableLabel.setText("Fizetendő: " + t.getFinalInvoiceToString());
									switch(t.getDiscount()){
										case "no":
											tableDiscountLabel.setText("Kedvezmény: Nincs");
											break;
										case "item":
											tableDiscountLabel.setText("Kedvezmény: Termék");
											break;
										case "invoice":
											tableDiscountLabel.setText("Kedvezmény: Számla");
											break;
									}
									tableItemAddNumberValue.setText("");
									break;
								}
							}
						} else {
							tableActionResultLabel.setForeground(Color.red);
							if(item.getNumberOfItem() == 0)
								tableActionResultLabel.setText("Termék elfogyott.");
							else
								tableActionResultLabel.setText("Csak "+ item.getNumberOfItem() +" db termék érhető el.");
							
						}
					}
				}
			});
			
			tableDeleteItemButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(tableItemAddNumberValue.getText().isEmpty()){
						tableActionResultLabel.setText("Hiányzik adat a törléshez!");
						tableActionResultLabel.setForeground(Color.red);
					} else if(!service.isInt(tableItemAddNumberValue.getText())){
						tableActionResultLabel.setText("Nem megfelelő adattípus!");
						tableActionResultLabel.setForeground(Color.red);
					} else {
						item = dao.getItem(((Item)tableManagerItemSelector.getSelectedItem()).getID());
						int itemNumber = Integer.parseInt(tableItemAddNumberValue.getText());
						
							for(Table t : tables){
								if(t.getTableNumber() == selectedTableNumber){
									boolean itemIsFound = false;
									for(Item i : t.getItems()){
										if(i.getID() == item.getID()){
											itemIsFound = true;
											if(i.getNumberOfItem() < itemNumber){
												tableActionResultLabel.setForeground(Color.red);
												tableActionResultLabel.setText("Nincs ennyi termék a számlán!");
											} else {
												i.setNumberOfItem(i.getNumberOfItem() - itemNumber);
												try {
													item.setNumberOfItem(item.getNumberOfItem() + itemNumber);
													dao.updateItem(item);
													tableActionResultLabel.setText("Rendelés felvéve.");
													tableActionResultLabel.setForeground(Color.green);
												} catch (SQLException e) {
													tableActionResultLabel.setText("Adatbázis hiba! Rendelés felvéve.");
													tableActionResultLabel.setForeground(Color.red);
												}
												
											}
										}
									}
									
									if(!itemIsFound){
										tableActionResultLabel.setText("Nincs ilyen termék a számlán!");
										tableActionResultLabel.setForeground(Color.red);		
									}

									
									tableItemList.setText(t.getItemListString());
									tableOverallInvoiceLabel.setText("Összesen: " + t.getInvoiceToString());
									tablePayableLabel.setText("Fizetendő: " + t.getFinalInvoiceToString());
									switch(t.getDiscount()){
										case "no":
											tableDiscountLabel.setText("Kedvezmény: Nincs");
											break;
										case "item":
											tableDiscountLabel.setText("Kedvezmény: Termék");
											break;
										case "invoice":
											tableDiscountLabel.setText("Kedvezmény: Számla");
											break;
									}
									tableItemAddNumberValue.setText("");
									break;
								}
							}

					}
				}
			});
			
			tableClearButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					for(Table t : tables){
						if(t.getTableNumber() == selectedTableNumber){
							t.setAvailable(true);
							t.setItems(new ArrayList<Item>());
							DefaultTreeModel dtm = new DefaultTreeModel(
									new DefaultMutableTreeNode("Foglalt asztalok:") {
										private static final long serialVersionUID = 1L;

										{
											for(Table table : tables)
												if(!table.isAvailable())
													add(new DefaultMutableTreeNode(table.getTableNumber() + ". Asztal"));

										}
									}
								);

							tree.setModel(dtm);
							tableInformationPanel.setVisible(false);
					        break;
						}
						
					}

					
				}
			});
			
		//TABLE MANAGER END
		//TABLE RESERVATION START
			
			tableReservationLabel = new JLabel("Szabad asztalok");
			tableReservationLabel.setBounds(5, 5, 300, 15);
			TableReservationPanel.add(tableReservationLabel);
			
			tableReservationTableSelector = new JComboBox<>();
			tableReservationTableSelector.setModel(new DefaultComboBoxModel<String>(service.getAvailableTablesList(tables)));
			tableReservationTableSelector.setBounds(5, 30, 120, 20);
			TableReservationPanel.add(tableReservationTableSelector);
			
			tableReservationButton = new JButton("Foglalás");
			tableReservationButton.setBounds(130, 30, 100, 20);
			TableReservationPanel.add(tableReservationButton);

			tableReservationResultLabel = new JLabel();
			tableReservationResultLabel.setBounds(5, 60, 300, 15);
			TableReservationPanel.add(tableReservationResultLabel);
			
			tableReservationButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(tableReservationTableSelector.getSelectedItem() != null){
						int tableNumber = service.getTableNumberForString((String)tableReservationTableSelector.getSelectedItem());
						for(int i = 0; i<tables.size();i++){
							if(tableNumber == tables.get(i).getTableNumber())
								tables.get(i).setAvailable(false);
						}
						tableReservationResultLabel.setText((String)tableReservationTableSelector.getSelectedItem() + " lefoglalva!");
						tableReservationTableSelector.setModel(new DefaultComboBoxModel<String>(service.getAvailableTablesList(tables)));
					}
					
	                ComboBoxModel<String> model = tableReservationTableSelector.getModel();
	                int size = model.getSize();
	                if(size == 0){
	                	tableReservationLabel.setText("Jelenleg nincs szabad asztal!");
	                	tableReservationButton.setEnabled(false);
	                	tableReservationTableSelector.setEnabled(false);
	                } else {
	                	tableReservationLabel.setText("Szabad asztalok száma: " + service.getAvailableTables(tables));
	                }
				}
			});
			
		//TABLE RESERVATION END
		
		setContentPane(WindowPanel);
		
	}
}
