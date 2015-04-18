/*COmment diatas*/
package id.co.telkomsigma.bank;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

public class ClientATM extends MessageService{

	public static void main(String[] args) {
		String serverAddress = "localhost";
		Socket socket = null;
		String pilih;
		ClientATM atm = new ClientATM();
		boolean login=false;
		ObjectInputStream obs= null;

		try {
			socket = new Socket(serverAddress, 9797);
			obs = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e2) {

			e2.printStackTrace();
		}
		try {
			//JOptionPane.showMessageDialog(null, arg1)
			String idUser = JOptionPane.showInputDialog("Selamat Datang di ATM\nSilahkan Login\n\nMasukan ID");
			String password = JOptionPane.showInputDialog("Masukan Password");

			atm.login(Integer.parseInt(idUser),password,socket);

			Object str=null;
			while(true){

				str=obs.readObject();

				if(str.equals("success")){		
					login=true;
				}else{
					socket.close();
				}

				while(login){
					pilih = JOptionPane.showInputDialog("\t\tATM \n"
							+"\n========================\n" +
							"1.Setor Tunai"
							+"\n2.Tarik Tunai"
							+"\n3.Cek Saldo"
							+"\n4.Transfer"
							+"\n5.Cetak Mutasi"
							+"\n6.Keluar"
							+"\n========================"
							+"\nMasukan nomor pilihan :");

					if (pilih.equals("1")){
						Long nominal=Long.parseLong(JOptionPane.showInputDialog("Masukan nominal yang ingin disetor"));
						atm.setorTarikTunai(Integer.parseInt(idUser), "S", nominal, socket);
						atm.getFromServer(obs);
					}else if(pilih.equals("2")){
						Long nominalt=Long.parseLong(JOptionPane.showInputDialog("Masukan nominal yang ingin ditarik"));
						atm.setorTarikTunai(Integer.parseInt(idUser), "T", nominalt, socket);
						JOptionPane.showMessageDialog(null, obs.readObject());
					}else if(pilih.equals("3")){
						atm.cekSaldo(Integer.parseInt(idUser), socket);
						atm.getFromServer(obs);
					}else if(pilih.equals("4")){
						String norek=JOptionPane.showInputDialog("TRANSFER\n"
								+"\n========================"
								+"\nMasukan No.Rek tujuan");
						Long nominalt=Long.parseLong(JOptionPane.showInputDialog("TRANSFER\n"
								+"========================"
								+"\nNo.Rek tujuan : "+norek
								+"\nMasukan nominal yang ingin ditransfer"));
						
						atm.transfer(Integer.parseInt(idUser), norek, nominalt, socket);
						
					}else if(pilih.equals("5")){
						atm.cetakMutasi(Integer.parseInt(idUser), socket);
						JOptionPane.showMessageDialog(null, obs.readObject());
					}else if(pilih.equals("6")){
						JOptionPane.showMessageDialog(null, "Terima kasih");
						System.exit(0);	/*keluar*/				
					}else{
						JOptionPane.showMessageDialog(null, "Menu yang pilih anda salah!");

					}
				}


			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Connection timeout/terminate.");
		}
	}

}
/*aaaaaaaaaaa*/
/*BBBBBBBBBBB*/