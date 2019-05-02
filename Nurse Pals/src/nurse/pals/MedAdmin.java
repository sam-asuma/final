package nurse.pals;

import java.util.Arrays;
import java.util.Date;
import java.util.regex.Pattern;
 

public class MedAdmin {
	
	private Date date;
	private String Administrator;
	private Prescription drug;
	private double dropRate;
	
	public double dropRate() {
		return dropRate;
		
	}
	
	 public static  String txt = "1 T PO QD";
	 

	
	

	

	

	 public static String translate(String sig) {
			
		 	 sig = sig.toLowerCase();
		 
		 	
			String find [] = { " t ", "po","qd"};
			String replace [] = {" Tablet ", "by mouth", "every day" };

			

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

