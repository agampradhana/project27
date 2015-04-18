package id.co.telkomsigma.bank;

import java.net.Socket;

public interface Perbankan {
	public void login(int idUser, String password, Socket socket);
	public void setorTarikTunai(Integer idUser, String tipe,Long nominal, Socket socket);
	public void cekSaldo(Integer idUser,Socket socket);
	public void transfer(Integer idUser,String norek, Long nominal, Socket socket);
	public void cetakMutasi(Integer idUser, Socket socket);
}
/*INi adalah interface*/