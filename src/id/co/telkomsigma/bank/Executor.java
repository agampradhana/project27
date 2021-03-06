package id.co.telkomsigma.bank;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Executor {
	private static ObjectOutputStream obts;
	private static Object msg;
	
	public static void excute(String cmd, ObjectOutputStream ots){
		
		Executor exe = new Executor();
		obts=ots;
		String[] prs = cmd.split("\\|");
		String code=null;
		Integer idUser=null;
		Long nominal=null;
		String reff=null;
		
		
		if(prs[0].toUpperCase().equals("L")){
			if(User.cekLogin(Integer.parseInt(prs[1]),prs[2])){
				msg="success";
			}else{
				msg="error";
			}
		}else if(prs[0].toUpperCase().equals("S")){//SETOR TUNAI
			code="S";
			idUser=Integer.parseInt(prs[1]);
			nominal = Long.parseLong(prs[2]);
			Saldo saldo= (Saldo)SingletonATM.getInstance().getSaldoDB().get(idUser);
			saldo.setSaldo(saldo.getSaldo()+ nominal);
			SingletonATM.getInstance().getSaldoDB().put(idUser,saldo);
			exe.insertMutasi(code, idUser, nominal, reff);

			msg=saldo;
		}else if(prs[0].toUpperCase().equals("T")){//TARIK TUNAI
			code="T";
			nominal=Long.parseLong(prs[2]);
			idUser=Integer.parseInt(prs[1]);
			Saldo saldot= (Saldo)SingletonATM.getInstance().getSaldoDB().get(idUser);
			Long tmp=saldot.getSaldo();
			if(tmp<nominal){
				msg="Saldo tidak Mencukupi\n Saldo : "+tmp.toString();
			}else{
				saldot.setSaldo(saldot.getSaldo()- nominal);
				SingletonATM.getInstance().getSaldoDB().put(idUser,saldot);
				exe.insertMutasi(code, idUser, nominal, reff);
				msg=saldot;
				System.out.println(msg);
			}
			System.out.println(SingletonATM.getInstance().getSaldoDB());
			
		}else if(prs[0].toUpperCase().equals("C")){//CEK SALDO
			idUser=Integer.parseInt(prs[1]);
			Saldo saldot= (Saldo)SingletonATM.getInstance().getSaldoDB().get(idUser);
			
			msg=saldot;
			
		}else if(prs[0].toUpperCase().equals("K")){//TRANSFER
			code="K";
			idUser=Integer.parseInt(prs[1]);
			nominal=Long.parseLong(prs[3]);
			reff=prs[2];
			Saldo sSaldo= (Saldo)SingletonATM.getInstance().getSaldoDB().get(idUser);
			Integer nIdUser = User.searchUser(prs[2]);
			Saldo dSaldo= (Saldo)SingletonATM.getInstance().getSaldoDB().get(nIdUser);

			sSaldo.setSaldo(sSaldo.getSaldo()-nominal);
			dSaldo.setSaldo(dSaldo.getSaldo()+nominal);
			exe.insertMutasi(code, idUser, nominal, reff);
			msg=sSaldo;
			
			System.out.println(SingletonATM.getInstance().getMutasiDB());
			
			
		}else if(prs[0].toUpperCase().equals("M")){//CETAK MUTASI
			idUser=Integer.parseInt(prs[1]);
			msg=Mutasi.getMutasiById(idUser);
		}
		
		
		FileFactory.updateFile();
		exe.sendToClient(msg);
		
	}
	
	private void sendToClient(Object obj){
		try {
			obts.reset();
			obts.writeObject(msg);
			obts.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void insertMutasi(String cmd, Integer idUser, Long nominal, String reff){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		String cDate = dateFormat.format(date);
		
		Saldo sSaldo= (Saldo)SingletonATM.getSaldoDB().get(idUser);
		String sNoRek = sSaldo.getNorek();
		String type = null,desc=null;
		if(cmd.equals("S")){
			type="KREDIT";
			desc="SETOR TUNAI";
			Mutasi mts = new Mutasi(Mutasi.getLastId(), idUser, sNoRek, nominal, type, reff, desc, cDate);
			SingletonATM.getInstance().addMutasi(mts, Mutasi.getLastId());
		}else if(cmd.equals("T")){
			type="DEBIT";
			desc="TARIK TUNAI";
			Mutasi mts = new Mutasi(Mutasi.getLastId(), idUser, sNoRek, nominal, type, reff, desc, cDate);
			SingletonATM.getInstance().addMutasi(mts, Mutasi.getLastId());
		}else if(cmd.equals("K")){
			Mutasi mts = new Mutasi(Mutasi.getLastId(), idUser, sNoRek, nominal, "DEBIT", reff, "TRANSFER", cDate);
			SingletonATM.getInstance().addMutasi(mts, Mutasi.getLastId());
			Integer idUser2 = User.searchUser(reff);
			Mutasi mts2 = new Mutasi(Mutasi.getLastId(), idUser2, reff, nominal, "KREDIT", sNoRek, "TRANSFER", cDate);
			SingletonATM.getInstance().addMutasi(mts2, Mutasi.getLastId());
		}
		
		
	}
}
