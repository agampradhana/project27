package id.co.telkomsigma.bank;

import java.net.Socket;
import java.util.LinkedHashMap;

public class SingletonATM {
	private static SingletonATM singletonATM;
	private static LinkedHashMap userDB;
	private static LinkedHashMap saldoDB;
	private static LinkedHashMap mutasiDB;
	/**
	 * @return the mutasiDB
	 */
	public static LinkedHashMap getMutasiDB() {
		return mutasiDB;
	}


	/**
	 * @param mutasiDB the mutasiDB to set
	 */
	public static void setMutasiDB(LinkedHashMap mutasiDB) {
		SingletonATM.mutasiDB = mutasiDB;
	}


	private int index;
	

	private SingletonATM(){
		userDB= new LinkedHashMap<Integer,User>();
		index=1;
		saldoDB= new LinkedHashMap<Integer,Saldo>();
		mutasiDB= new LinkedHashMap<Integer,Mutasi>();
	
	}
	
	
	public static LinkedHashMap getSaldoDB() {
		return saldoDB;
	}


	public static void setSaldoDB(LinkedHashMap saldoDB) {
		SingletonATM.saldoDB = saldoDB;
	}


	public synchronized static SingletonATM getInstance(){
		if(singletonATM==null){
			singletonATM=new SingletonATM();
		}
		return singletonATM;
	}
	
	public void addUser(User user){
		this.userDB.put(getIndexUser(),user);
	}
	
	public void addSaldo(Saldo saldo, Integer idUser){
		this.saldoDB.put(idUser,saldo);
	}
	
	public void addMutasi(Mutasi mutasi, Integer idUser){
		this.mutasiDB.put(idUser,mutasi);
	}


	public static LinkedHashMap getUserDB() {
		return userDB;
	}


	public static void setUserDB(LinkedHashMap userDB) {
		SingletonATM.userDB = userDB;
	}


	public int getIndexUser() {
		return index++;
	}


	public void setIndexUser(int index) {
		this.index = index;
	}
}
