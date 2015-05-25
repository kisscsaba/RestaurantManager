package RestaurantManager.Unit;

public class Item {
	private int ID;
    private String name;
    private String type;
    private int numberOfItem;
    private int price;



    public Item(int iD, String name, String type, int numberOfItem, int price) {
		this.ID = iD;
		this.name = name;
		this.type = type;
		this.numberOfItem = numberOfItem;
		this.price = price;
	}

    public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	public int getNumberOfItem() {
		return numberOfItem;
	}

	public void setNumberOfItem(int numberOfItem) {
		this.numberOfItem = numberOfItem;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getOverallPrice(){
        return this.numberOfItem * this.price;
    }

	@Override
	public String toString() {
		return name;
	}
    
    

}
