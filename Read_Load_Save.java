import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;


public class Read_Load_Save {
	
	private LinkedList <Transaction> ins;
	private LinkedList <Transaction> outs;
	
	public Read_Load_Save() throws IOException{
		
		Scanner s = null;
		String transaction, items, identifier, price_cost, inOut = "";
		int serial = 0;
		int id,quantity = 0;
		float price = 0;
		Transaction new_transaction = null;
		ins = new LinkedList<Transaction>();
		outs = new LinkedList<Transaction>();
	    try {
	        s = new Scanner(new BufferedReader(new FileReader("pa1input.txt")));

	        while (s.hasNext()) {
	        	inOut = s.next();
	        	transaction = s.nextLine();
	        	
        		//Take substring containing the identifier and then parse the string into an int
        		identifier = transaction.substring(0,transaction.indexOf(":"));
        		id = Integer.parseInt(identifier.replaceAll("[\\D]", ""));
        		
        		//Take substring containing the number of items and then parse the string into an int
        		items = transaction.substring(transaction.indexOf(":"), transaction.indexOf("$"));
        		quantity = Integer.parseInt(items.replaceAll("[\\D]", ""));
//        		System.out.println(quantity);
        		
        		//Take substring containing the unit price and then parse the string into a float
        		price_cost = transaction.substring(transaction.indexOf("$"), transaction.length());
        		price = Float.parseFloat(price_cost.replaceAll("[^\\d\\.]", ""));
//        		System.out.println(price);
        		
	        	if(inOut.contains("in")) {
	        		new_transaction = new Transaction(true, serial, id, quantity, price);
	        		ins.add(new_transaction);
	        	}
	        	else {
	        		new_transaction = new Transaction(false, serial, id, quantity, price);
	        		outs.add(new_transaction);
	        	}
	        	serial++;
	        }
//	        Iterator<Transaction> i = ins.listIterator();
//	        while(i.hasNext()) {
//	        	i.next().print_transation();
//	        }
	        
	    } finally {
	        if (s != null) {
	            s.close();
	        }
	    }	
	}
	
	public LinkedList <Transaction> getInTransactions() {
		return ins;
	}
	
	public LinkedList <Transaction> getOutTransactions() {
		return outs;
	}
	
	public void save(LinkedList <Transaction> in_transactions, LinkedList <Transaction> out_transactions) {
		BufferedWriter writer = null;
	    try {
	        //create a temporary file
	        File output = new File("pa1output.txt");

	        // This will output the full path where the file will be written to
//	        System.out.println(output.getCanonicalPath());

	        writer = new BufferedWriter(new FileWriter(output));
	        
	        writer.write("Table 1: Current Inventory\n");
	        writer.write(String.format("%1$s %2$14s %3$23s %4$16s \n", "Serial #", "Item", "Quantity", "Cost"));
	        
	        Iterator<Transaction> i = in_transactions.listIterator();
	        Transaction t;
	        while(i.hasNext()) {
	        	t = i.next();
//	        	i.next().print_transation();
	        	if(t.getQuantity() > 0)
	        	writer.write(String.format("%1s %20s %20s %20s \r\n", t.getSerial(), "x"+t.getItem(), t.getQuantity(), "$"+t.getPrice()));
	        }
	        writer.write("\n\nTable 2: Cost Basis and Gains\n");
	        writer.write(String.format("%1s %14s %23s %21s %16s \r\n", "Serial #", "Item", "Quantity", "Cost Basis($)", "Gains($)"));
	        i = out_transactions.listIterator();
	        while(i.hasNext()) {
	        	t = i.next();
//	        	i.next().print_transation();
	        	writer.write(String.format("%1s %20s %20s %20s %20s \r\n", t.getSerial(), "x"+t.getItem(), t.getQuantity(), "$"+t.getPrice(), "$"+t.getTotal()));
	        }
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            // Close the writer regardless of what happens...
	            writer.close();
	        } catch (Exception e) {
	        }
	    }
	}
}
