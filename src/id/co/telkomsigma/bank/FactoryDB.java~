package id.co.telkomsigma.bank;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FactoryDB {
	private static String fileAdd="D:/Documents";
	
	public static void generateTable(String filename){
		File file = new File(fileAdd,filename); 
		try {
			FileReader fr = new FileReader(file);
			BufferedReader bf = new BufferedReader(fr);
			try {
				String row;
				while((row=bf.readLine())!=null){
					if(filename.equals("User.txt")){
						String[] prs = row.split("\\|");
						
						User usr= new User(Integer.parseInt(prs[0]), prs[1]);
						SingletonATM.getInstance().addUser(usr);
					}
					
					if(filename.equals("Saldo.txt")){
						String[] prs = row.split("-");
						Saldo saldo= new Saldo(prs[1], prs[0], Long.parseLong(prs[2]));
						SingletonATM.getInstance().addSaldo(saldo,Integer.parseInt(prs[1]));
					}
					
					if(filename.equals("Mutasi.txt")){
						String[] prs = row.split("-");
						Mutasi mutasi = new Mutasi(Integer.parseInt(prs[0]), Integer.parseInt(prs[1]), prs[2],Long.parseLong(prs[3]), prs[4], prs[5], prs[6],prs[7]);
						SingletonATM.getInstance().addMutasi(mutasi, Integer.parseInt(prs[0]));
					}
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
