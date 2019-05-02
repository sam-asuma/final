package nurse.pals;

import java.util.Arrays;
import java.util.Date;

public class Prescription {
	private String name;
	private String strenght;
	private String [] dosageForm = {"Tablets", "Capsules",  "Suppositories", "Liquid", "Drops", "Topical", "Injection", "IV"};
	private String  type;
	private int dispenseQty;
	private int refill;
	private String prescriberName;
	private String dea;
	private String address;
	private long	phone;
	private String prescriptionDate;
	private String color;
	private String[] shape = {"Round","Oblong", "Oval","Square","Rectangle","Diamnond"};
	//National Drug Code
	private long ndc;
	private boolean brand;
	private String description;
	private String[] category =  {"OTC", "IV", "RX" } ;
	
	public Prescription() {
		super();
	}
	
	
	
	public Prescription(String name, String strenght, String type, int dispenseQty, int refill, String prescriberName,
			String dea, String address, long phone, String prescriptionDate) {
		super();
		this.name = name;
		this.strenght = strenght;
		this.type = type;
		this.dispenseQty = dispenseQty;
		this.refill = refill;
		this.prescriberName = prescriberName;
		this.dea = dea;
		this.address = address;
		this.phone = phone;
		this.prescriptionDate = prescriptionDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStrenght() {
		return strenght;
	}
	public void setStrenght(String strenght) {
		this.strenght = strenght;
	}
	public String[] getDosageForm() {
		return dosageForm;
	}
	public void setDosageForm(String[] type) {
		this.dosageForm = type;
	}
	public int getDispenseQty() {
		return dispenseQty;
	}
	public void setDispenseQty(int dispenseQty) {
		this.dispenseQty = dispenseQty;
	}
	public int getRefill() {
		return refill;
	}
	public void setRefill(int refill) {
		this.refill = refill;
	}
	public String getPrescriberName() {
		return prescriberName;
	}
	public void setPrescriberName(String prescriberName) {
		this.prescriberName = prescriberName;
	}
	public String getDea() {
		return dea;
	}
	public void setDea(String dea) {
		this.dea = dea;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public String getPrescriptionDate() {
		return prescriptionDate;
	}
	public void setPrescriptionDate(String prescriptionDate) {
		this.prescriptionDate = prescriptionDate;
	}
	
	
	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	@Override
	public String toString() {
		return " Prescriber Name: " + prescriberName
				+ "\n" + " Prescriber Dea #: " + dea + "\n" + " Presciber Address: " + address + "\n" + " Prescriber Phone: " + phone + "\n" + " Prescription Date: "
				+ prescriptionDate + "\n" + "Prescription " + "\n" + " Name: " + name + "\n" + " Strenght: " + strenght + "\n" +" Dosage Form: " + type
				+ "\n" + " Dispense Qty:" + dispenseQty + "\n" + " Refill: " + refill;
	}
	
	
	
	
	

}
