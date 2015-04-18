/*CLass Runnableagar tidak bentrok*/
package id.co.telkomsigma.bank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class RunnableServer implements Runnable {
	
	private ServerSocket listenerATM;
	
	public RunnableServer(ServerSocket listenerATM) {
		this.listenerATM = listenerATM;
	}

	@Override
	public void run() {
		while(true){
			
			Socket socketATM = null;
			try {
				socketATM = listenerATM.accept();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				System.out.println("Receiving connection from ATM : "+socketATM.getRemoteSocketAddress());
				ObjectOutputStream ots = new ObjectOutputStream(socketATM.getOutputStream());
				InputStream is= socketATM.getInputStream();
				InputStreamReader sr= new InputStreamReader(is);
				BufferedReader br = new BufferedReader(sr);
				
				String str=null;
				
				while((str=br.readLine())!=null){
					//System.out.println(str);
					Executor.excute(str, ots);
					
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}



}
