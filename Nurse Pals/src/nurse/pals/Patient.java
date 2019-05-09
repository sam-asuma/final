package nurse.pals;

import java.awt.List;
import java.util.ArrayList;
import java.util.Comparator;



public class Patient {
	
	private long id;
	private String firstName;
	private String lastName;
	private char middleInitial;
	private long SSN;
	private String DOB;
	private double height;
	private double weight;
	private char sex;
	private String address;
	private String primaryDoctor;
	private String pharmacy;
	private String emergencyContact;
	
	
	
	
	public Patient(long id, String firstName, String lastName, char middleInitial, long SSN, String dOB, double height,
			double weight, char sex, String address, String primaryDoctor) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleInitial = middleInitial;
		this.SSN = SSN;
		DOB = dOB;
		this.height = height;
		this.weight = weight;
		this.sex = sex;
		this.address = address;
		this.primaryDoctor = primaryDoctor;
	}
	
	
	public Patient() {
		super();
	}
	
	
	public Patient(String firstName, String lastName, long SSN, String address) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.SSN= SSN;
		this.address = address;
	}
	 
	
	


	

	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public char getMiddleInitial() {
		return middleInitial;
	}
	public void setMiddleInitial(char middleInitial) {
		this.middleInitial = middleInitial;
	}
	public long getSSN() {
		return SSN;
	}
	public void setSSN(long SSN) {
		this.SSN = SSN;
	}
	public String getDOB() {
		return DOB;
	}
	public void setDOB(String dOB) {
		DOB = dOB;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}

	public char getSex() {
		return sex;
	}
	public void setSex(char sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPrimaryDoctor() {
		return primaryDoctor;
	}
	public void setPrimaryDoctor(String primaryDoctor) {
		this.primaryDoctor = primaryDoctor;
	}
	public String getPharmacy() {
		return pharmacy;
	}
	public void setPharmacy(String pharmacy) {
		this.pharmacy = pharmacy;
	}
	public String getEmergencyContact() {
		return emergencyContact;
	}
	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
	}
	
	
	
	
	
	
	@Override
	public String toString() {
		return  firstName + lastName + SSN +  address;
	}


	public String toAllString() {
		return "Patient id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", middleInitial="
				+ middleInitial + ", SSN=" + SSN + ", DOB=" + DOB + ", height=" + height + ", weight=" + weight
				+ ", sex=" + sex + ", address=" + address + ", primaryDoctor=" + primaryDoctor + ", pharmacy="
				+ pharmacy + ", emergencyContact=" + emergencyContact ;
	}

	
	
	public static Comparator<Patient> CompareByFirstName = new Comparator<Patient>() {
		@Override
		public int compare(Patient p1, Patient p2) {
			return p1.firstName.compareTo(p2.firstName);
		}
	};

	@Override
    public boolean equals(Object obj) {
        return (this.firstName.equals(((Patient) obj).firstName)
                && this.lastName.equals(((Patient) obj).lastName)&&
                this.address.equals(((Patient) obj).address));
    }
	
    }

	
	
	


