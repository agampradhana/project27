package id.co.telkomsigma.bank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Set;

public class FileFactory {
	private static String fileAdd="D:/Documents";
	public static void updateFile(){
		//File fileUser = new File(fileAdd,"User.txt");
		File fileSaldo = new File(fileAdd,"Saldo.txt");
		File fileMutasi = new File(fileAdd,"Mutasi.txt");
		FileWriter fw;
		FileWriter fw2;
		
		try {
			fw = new FileWriter(fileSaldo,false);
			BufferedWriter bw = new BufferedWriter(fw);
			Set<Integer> keys = SingletonATM.getInstance().getSaldoDB().keySet();
	        for(Integer k:keys){
	        	Saldo sld= (Saldo)SingletonATM.getInstance().getSaldoDB().get(k);
	        	bw.write(sld.getNorek()+"-"+sld.getIdUser()+"-"+sld.getSaldo());
	        	bw.newLine();
	        }
	        bw.flush();
	        bw.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		try {
			fw2 = new FileWriter(fileMutasi,false);
			BufferedWriter bw2 = new BufferedWriter(fw2);
			Set<Integer> keys = SingletonATM.getInstance().getMutasiDB().keySet();
	        for(Integer k:keys){
	        	Mutasi mts= (Mutasi)SingletonATM.getInstance().getMutasiDB().get(k);
	        	bw2.write(mts.getIdTrx()+"-"+mts.getIdUser()+"-"+mts.getNoRek()
	        			+"-"+mts.getNominal()+"-"+mts.getType()+"-"+mts.getNoReff()
	        			+"-"+mts.getDesc()+"-"+mts.getTglTrx());
	        	bw2.newLine();
	        }
	        bw2.flush();
	        bw2.close();
	        
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
