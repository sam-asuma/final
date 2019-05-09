package nurse.pals;

import java.util.Arrays;
import java.util.Date;
import java.util.regex.Pattern;
 

public class MedAdmin {

	private String user;
	private Date lgInDate;
	private String patient;
	private String prescript;
	private String sig;
	private String dosage;
	private String duration;
	private String notes;
	
	private String Administrator;
	private Prescription drug;
	private double dropRate;
	private String [] dose = {"First Dose", "Second Dose", "Third Dose", "Fourth Dose",
			"Fifth Dose", "Sixth Dose", "Seventh Dose", "Eight Dose", "Ninth Dose", "Tenth Dose"};
	private String [] script = {"Prednisone", "Azithromiacin"};
	private String [] eventData = {"Current Session", "Daily", "Monthly", "Yearly", "All"};
	
	
	
	
	
	
	 public MedAdmin() {
		super();
		// TODO Auto-generated constructor stub
	}




	public MedAdmin(String user, Date lgInDate, String patient, String prescript, String sig, String dosage,
			String duration, String notes) {
		super();
		this.user = user;
		this.lgInDate = lgInDate;
		this.patient = patient;
		this.prescript = prescript;
		this.sig = sig;
		this.dosage = dosage;
		this.duration = duration;
		this.notes = notes;
	}




	public String getUser() {
		return user;
	}




	public void setUser(String user) {
		this.user = user;
	}




	public Date getLgInDate() {
		return lgInDate;
	}




	public void setLgInDate(Date lgInDate) {
		this.lgInDate = lgInDate;
	}




	public String getPatient() {
		return patient;
	}




	public void setPatient(String patient) {
		this.patient = patient;
	}




	public String getPrescript() {
		return prescript;
	}




	public void setPrescript(String prescript) {
		this.prescript = prescript;
	}




	public String getSig() {
		return sig;
	}




	public void setSig(String sig) {
		this.sig = sig;
	}




	public String getDosage() {
		return dosage;
	}




	public void setDosage(String dosage) {
		this.dosage = dosage;
	}




	public String getDuration() {
		return duration;
	}




	public void setDuration(String duration) {
		this.duration = duration;
	}




	public String getNotes() {
		return notes;
	}




	public void setNotes(String notes) {
		this.notes = notes;
	}




	public String[] getEventData() {
		return eventData;
	}




	public void setEventData(String[] eventData) {
		this.eventData = eventData;
	}




	public String[] getDose() {
		return dose;
	}



	public void setDose(String[] dose) {
		this.dose = dose;
	}



	public String[] getScript() {
		return script;
	}



	public void setScript(String[] script) {
		this.script = script;
	}
	
	
	



	@Override
	public String toString() {
		return "MedAdmin:" + "\n" + "User: " + user + "," + "\n" + "Login Time Stamp: " + lgInDate + ","+ "\n" + "Patient: " + patient
				+ ", "+ "\n" + "Script: " + prescript + ","+  "\n" + "Sig: " + sig + "," + "\n" + "Dosage: " + dosage 
				+ "," + "\n" + "Duration: " + duration + "," + "\n" + "Notes: " + notes ;
	}






	public static  String txt = "1 T PO QD";
	 

	
	

	

	

	 public static String translate(String sig) {
			
		 	 sig = sig.toLowerCase();
		 
		 	
			String find [] = { " t ", "po","qd", "bid", "tid"};
			String replace [] = {" Tablet ", "by mouth", "every day", "two times a day", "three times a day" };

			

			for (int i = 0; i < find.length; i++) {
				
				
				sig = sig.replace(find[i], replace[i]);
				
				//System.out.println(sig);
			}
			
			return sig;
				
				
				 
			}
	/*
	 * public void display() { for (int i = 0; i < words.length; i++) {
	 * System.out.print(words[i] + " "); } }
	 */
	


	public static void main(String[] args) {
		
		String s  = translate(txt);
		System.out.println(s);

		

	}
	
	
}

