package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Curious Things:
 * The most older person have 110 years.
 * The most younger have 6 months.
 * The height is in cm
 * @author USER
 *
 */
public class Generator {
	///// Data in Arrays. (In order to save memory).
	public static final String[] COUNTRIES= {"Argentina","Bahamas", "Barbados","Belice","Bolivia","Brasil","Canadá","Chile","Colombia","Costa Rica","Cuba","Dominica","Ecuador","El Salvador","Estados Unidos","Granada","Guatemala","Guyana","Haití","Honduras","Jamaica","México","Nicaragua","Panamá","Paraguay","Perú","República Dominicana","Santa Lucía","Surinam","Trinidad y Tobago","Uruguay","Venezuela"};
	public static final double PERCENT[]= {45267449, 393893, 287437,398845,11700207,212821986, 37799407,19144605,50976248,5102158,11325391,17688599, 6491923, 331341050,112614,17971382,787215,11426356,9931333,2963429,129166028,6638075,4326296,7147553,33050211,10866667,183774,587541,1400283,3475842,28421581};
	public static final LocalDate INITIALYEAR= LocalDate.of(1910, 1, 1);
	public static final double MAXHEIGH=200.0;
	
	//// Data's Adress
	public static final String NAMES ="/data/datasets/Names.txt";
	public static final String SURNAMES ="/data/datasets/Surnames.txt";
	public static final String ID="/data/datasets/Id.txt";
	
	
	public int women;
	public int men;
	
	public double percentFulled[];

	public Generator() {
		this.percentFulled = new double[PERCENT.length];
		men=0;
		women=0;
	}	
	
	public void generate(int amountToGenerate) throws IOException {
		int amountPeople=0, idCounter=0, amount=0;
		String name="", id="";
		LocalDate date;
		BufferedReader readerId= new BufferedReader(new FileReader(ID)), readerName= new BufferedReader(new FileReader(NAMES)), readerSurname= new BufferedReader(new FileReader(SURNAMES));
		while(amountPeople<amountToGenerate) {
			//if(amount>PERCENT[actualCountrie]) {
				name= readerName.readLine()+" "+readerSurname.readLine();
				id=readerId.readLine();
				date=bornDate();
				Person person= new Person(id,name,gender(),date,height(date),COUNTRIES[getCountry()],"defined");
				amount++;
				idCounter++;
			//}else {}
		}
	}

	public LocalDate bornDate() {
		int years= (int)(Math.random()*110);
		int days= (int)(Math.random()*30);
		LocalDate newDate=INITIALYEAR.plus(years, ChronoUnit.YEARS);
		newDate.plus(days, ChronoUnit.DAYS);
		newDate.plus((int)years%12, ChronoUnit.MONTHS);
		return newDate;
	}
	
	public boolean gender() {
		if(women>men) {
			men++;
			return true;
		}else if (men>women) {
			women++;
			return false;
		}else {
			women++;
			return false;
		}
	}
	
	public double height(LocalDate age) {
		double hI=Math.random()*100;
		if(2020-age.getYear()>10) { /// Adults Height. Min 100cm, Max 20cm.
			hI=(hI<100 ? hI+(100-hI): hI);
			return MAXHEIGH-hI;
		}else if(2020-age.getYear()<10) { //// Kids Height. Min 50cm, Max 120cm
			hI=(hI<50 ? hI+(100-hI): hI);
			hI=(hI>120 ? 120: hI);
			return MAXHEIGH-hI; 
		}
		return hI;
	}
	
	public int getCountry() {
		int cnty=(int)(Math.random()*35);
		boolean available=false;
		while(!available) {
			if(PERCENT[cnty]>percentFulled[cnty]) {
				available=true;
				percentFulled[cnty]=percentFulled[cnty]+1;
			}
		}
		return cnty;
	}
}
