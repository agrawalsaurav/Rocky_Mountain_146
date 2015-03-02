import java.util.Iterator;
import java.util.LinkedList;


public class Transaction_Processor {
	
	LinkedList <Transaction> ins;
	LinkedList <Transaction> outs;
	
	public Transaction_Processor(LinkedList <Transaction> in_transactions, LinkedList <Transaction> out_transactions) {
		ins = in_transactions;
		outs = out_transactions;
	}
	
	public void processTransactions() {
		Iterator<Transaction> outiter = outs.listIterator();
		Iterator<Transaction> initer;
        Transaction out;
        Transaction in;
        while(outiter.hasNext()) {
        	out = outiter.next();
        	initer = ins.listIterator();
        	float cost = 0;
        	float total = 0;
        	int insCount = 0;
        	int outCount = out.getQuantity();
        	while(initer.hasNext() ) {
        		in = initer.next();
        		float soldPrice = out.getPrice();
        		int inQuantity = in.getQuantity();
//        		out.setPrice(0);
        		while((out.getItem() == in.getItem()) && inQuantity > 0 && outCount > 0/*&& out.getQuantity() <= in.getQuantity()*/) {
        			inQuantity--;
        			insCount++;
//        			out.setQuantity(out.getQuantity() - 1);
        			outCount--;
        			total += soldPrice - in.getPrice();
//        			out.setTotal(out.getTotal() + (soldPrice - in.getPrice()));
//        			out.setPrice(out.getPrice() - in.getPrice());
        			cost += in.getPrice();  
//        			out.setQuantity(0);
        		}
        	}
        	
        	if(outCount > 0) {
        		out.setFlag(true);
        		out.setError("Tried to pull " + out.getQuantity() + " items of x" + out.getItem() + ", but found only " + insCount + " item(s)!\n");
        	}
        	else {
        		out.setPrice(cost);
            	out.setTotal(total);
            	initer = ins.listIterator();
            	while(initer.hasNext() ) {
            		in = initer.next();
            		while((out.getItem() == in.getItem()) && in.getQuantity() > 0 && insCount > 0) {
            			in.setQuantity(in.getQuantity() -1);
            			insCount--;
            		}
            	}
        	}
//        	i.next().print_transation();
//        	writer.write(String.format("%1s %20s %20s %20s \r\n", t.getSerial(), "x"+t.getId(), t.getQuantity(), "$"+t.getPrice()));
        }
	}
	
	public LinkedList <Transaction> getInTransactions() {
		return ins;
	}
	
	public LinkedList <Transaction> getOutTransactions() {
		return outs;
	}
	

}
