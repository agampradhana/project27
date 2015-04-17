package id.co.telkomsigma.bank;

import java.net.ServerSocket;

public class ServerATM {

	public static void main(String[] args) {
		ServerSocket listenerServer=null;

		
		try {

			listenerServer = new ServerSocket(9797);
			FactoryDB.generateTable("User.txt");
			FactoryDB.generateTable("Saldo.txt");
			FactoryDB.generateTable("Mutasi.txt");
						
			System.out.println("Server is ready.\nWaiting for connection...");
		
			Thread server = new Thread(new RunnableServer(listenerServer));
			server.start();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
