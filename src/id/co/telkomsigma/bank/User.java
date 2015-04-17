package id.co.telkomsigma.bank;


import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.Set;


public class User{
	private Integer idUser;
	private String password;

	public User(Integer idUser, String password) {
		this.idUser = idUser;
		this.password = password;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public static boolean cekLogin(Integer idUser, String pwd){
		Set<Integer> keys = SingletonATM.getInstance().getUserDB().keySet();
		boolean log= false;
        for(Integer k:keys){
        	User usr= (User)SingletonATM.getInstance().getUserDB().get(k);
        	System.out.println(usr);
        	if(idUser==usr.getIdUser() && pwd.equals(usr.getPassword())){
        		log=true;
        	}
        }
		return log;
	}
	
	public static Integer searchUser(String norek){
		Set<Integer> keys = SingletonATM.getInstance().getSaldoDB().keySet();
		Integer idUser = null;
        for(Integer k:keys){
        	Saldo usr= (Saldo)SingletonATM.getInstance().getSaldoDB().get(k);
        	if(usr.getNorek().equals(norek)){
        		idUser=Integer.parseInt(usr.getIdUser());
        	}
        }
		return idUser;
	}
	
	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", password=" + password + "]";
	}
		
}
