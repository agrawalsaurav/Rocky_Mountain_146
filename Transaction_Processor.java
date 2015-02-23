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
        	while(initer.hasNext() && out.getQuantity() > 0) {
        		in = initer.next();
        		if(out.getItem() == in.getItem() && out.getQuantity() <= in.getQuantity()) {
        			in.setQuantity(in.getQuantity() - out.getQuantity());
        			out.setTotal((out.getQuantity()*out.getPrice()) - (out.getQuantity() * in.getPrice()));
        			out.setPrice(out.getQuantity() * in.getPrice());   
//        			out.setQuantity(0);
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
