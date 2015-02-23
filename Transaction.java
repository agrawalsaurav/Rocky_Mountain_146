
public class Transaction {
	
	private boolean in;
	private int serial;
	private int item;
	private int quantity;
	private float price;
	private float total;
	
	public Transaction(boolean is_in, int serial_num, int identifier, int item_quantity, float unit_price) {
		in = is_in;
		serial = serial_num;
		item = identifier;
		quantity = item_quantity;
		price = unit_price;
		total = quantity * price;
	}
	
	public void print_transation() {
		if(in)
			System.out.println("In x" + item + ": " + quantity + " at $" + price + " each");
		else
			System.out.println("Out x" + item + ": " + quantity + " at $" + price + " each");
			
	}
	
	public int getSerial() {
		return this.serial;
	}
	
	public boolean is_in() {
		return this.in;
	}
	
	public void setSerial(int serial_num) {
		this.serial = serial_num;
	}
	
	public int getItem() {
		return this.item;
	}
	
	public void setItem(int identifier) {
		this.item = identifier;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public void setQuantity(int item_quantity) {
		this.quantity = item_quantity;
	}
	
	public float getPrice() {
		return this.price;
	}
	
	public void setPrice(float unit_price) {
		this.price = unit_price;
	}
	
	public float getTotal() {
		return this.total;
	}
	
	public void setTotal(float transaction_total) {
		this.total  = transaction_total;
	}
	
//	public class Item {
//		int id;
//		public Item(int identifier) {
//			id = identifier;
//		}
//		
//		public int getId() {
//			return id;
//		}
//		
//		public boolean equals(Item other) {
//			return this.id == other.id;
//		}
//	}
}
