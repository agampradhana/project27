package id.co.telkomsigma.bank;

import java.util.Date;
import java.util.Set;

public class Mutasi {
	private Integer idTrx;
	private Integer idUser;
	private String noRek;
	private Long nominal;
	private String type;
	private String noReff;
	private String desc;
	private String tglTrx;
	/**
	 * @return the idTrx
	 */
	public Integer getIdTrx() {
		return idTrx;
	}
	/**
	 * @param idTrx the idTrx to set
	 */
	public void setIdTrx(Integer idTrx) {
		this.idTrx = idTrx;
	}
	/**
	 * @return the idUser
	 */
	public Integer getIdUser() {
		return idUser;
	}
	/**
	 * @param idUser the idUser to set
	 */
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}
	/**
	 * @return the noRek
	 */
	public String getNoRek() {
		return noRek;
	}
	/**
	 * @param noRek the noRek to set
	 */
	public void setNoRek(String noRek) {
		this.noRek = noRek;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the noReff
	 */
	public String getNoReff() {
		return noReff;
	}
	/**
	 * @param noReff the noReff to set
	 */
	public void setNoReff(String noReff) {
		this.noReff = noReff;
	}
	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}
	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	/**
	 * @return the tglTrx
	 */
	public String getTglTrx() {
		return tglTrx;
	}
	/**
	 * @param tglTrx the tglTrx to set
	 */
	public void setTglTrx(String tglTrx) {
		this.tglTrx = tglTrx;
	}
	/**
	 * @return the nominal
	 */
	public Long getNominal() {
		return nominal;
	}
	/**
	 * @param nominal the nominal to set
	 */
	public void setNominal(Long nominal) {
		this.nominal = nominal;
	}
	public Mutasi(Integer idTrx, Integer idUser, String noRek, Long nominal,
			String type, String noReff, String desc, String tglTrx) {
		super();
		this.idTrx = idTrx;
		this.idUser = idUser;
		this.noRek = noRek;
		this.nominal = nominal;
		this.type = type;
		this.noReff = noReff;
		this.desc = desc;
		this.tglTrx = tglTrx;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Mutasi [idTrx=" + idTrx + ", idUser=" + idUser + ", noRek="
				+ noRek + ", nominal=" + nominal + ", type=" + type
				+ ", noReff=" + noReff + ", desc=" + desc + ", tglTrx="
				+ tglTrx + "]";
	}
	
	
	public static Integer getLastId(){
		Integer lastId=1;
		Set<Integer> keys = SingletonATM.getInstance().getMutasiDB().keySet();
        for(Integer k:keys){
        	Mutasi mts= (Mutasi)SingletonATM.getInstance().getMutasiDB().get(k);
        	lastId=mts.getIdTrx();
        }
		return ++lastId;
	}
	
	public static String getMutasiById(Integer idUser){
		String mutasi="DATA MUTASI\n"
				+"==================================\n";
		Set<Integer> keys = SingletonATM.getInstance().getMutasiDB().keySet();
        for(Integer k:keys){
        	Mutasi mts= (Mutasi)SingletonATM.getInstance().getMutasiDB().get(k);
        	if(mts.getIdUser().equals(idUser)){
        		mutasi+="No.Rek : "+mts.getNoRek()
        				+"\nTgl.Trx : "+mts.getTglTrx()
	        			+"\nNominal : "+mts.getNominal()
	        			+"\n"+mts.getType()
	        			+"\nNo.Reff : "+mts.getNoReff()
	        			+"\nKet :"+mts.getDesc()
	        			+"\n--------------------------------\n";
        	}
        }
		return mutasi;
	}
	
	
}
