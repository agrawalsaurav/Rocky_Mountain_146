import java.util.LinkedList;
import java.util.Scanner;
import java.io.*;


public class Transaction_Manager {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		 Read_Load_Save rls = new Read_Load_Save();
		 LinkedList <Transaction> ins = rls.getInTransactions();
		 LinkedList <Transaction> outs = rls.getOutTransactions();
		 Transaction_Processor tp = new Transaction_Processor(ins, outs);
		 tp.processTransactions();
		 ins = tp.getInTransactions();
		 outs = tp.getOutTransactions();
		 rls.save(ins,outs);
		 
		 

	}
}
