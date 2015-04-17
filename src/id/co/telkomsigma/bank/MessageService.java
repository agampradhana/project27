package id.co.telkomsigma.bank;

import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

public abstract class MessageService implements  Perbankan  {


	@Override
	public void login(int idUser, String password, Socket socket) {
		String data = "L|"+idUser+"|"+password+"\n";
		sendToServer(data, socket);

	}
	

	@Override
	public void setorTarikTunai(Integer idUser, String tipe,Long nominal, Socket socket) {
		String data = tipe+"|"+idUser+"|"+nominal+"\n";
		sendToServer(data, socket);

	}

	@Override
	public void cekSaldo(Integer idUser,Socket socket) {
		String data = "C|"+idUser+"\n";
		sendToServer(data, socket);	
	}


	@Override
	public void transfer(Integer idUser,String norek, Long nominal, Socket socket) {
		String data = "K|"+idUser+"|"+norek+"|"+nominal+"\n";
		sendToServer(data, socket);
	}


	@Override
	public void cetakMutasi(Integer idUser, Socket socket) {
		String data = "M|"+idUser+"\n";
		sendToServer(data, socket);

	}
	
	protected void getFromServer(ObjectInputStream ois){
		Saldo sld=null;
		try {
			sld = (Saldo)ois.readObject();
			System.out.println(sld);
			JOptionPane.showMessageDialog(null, sld.toString());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void sendToServer(String data, Socket socket){
		OutputStream outs = null;
		if(outs==null){
			try {
				outs =  socket.getOutputStream();
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				outs.write(data.getBytes());
				outs.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
	}
}
