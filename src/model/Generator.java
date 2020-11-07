package model;
import java.io.IOException;
import java.time.LocalDate;

public class Generator {
	
	///// Data in Arrays. (In order to save memory).
	public static final String[] COUNTRIES= {"Argentina","Bahamas", "Barbados","Belice","Bolivia","Brasil","Canadá","Chile","Colombia","Costa Rica","Cuba","Dominica","Ecuador","El Salvador","Estados Unidos","Granada","Guatemala","Guyana","Haití","Honduras","Jamaica","México","Nicaragua","Panamá","Paraguay","Perú","República Dominicana","Santa Lucía","Surinam","Trinidad y Tobago","Uruguay","Venezuela"};
	public static final double MAXHEIGHT=200.0;
	public static final String[] IDS= {"","UJJML","NEUGO","WMXZG","GSZCA", "ADSHSKA"};

	//// Distribution
	public int women;
	public int men;
	public int amountOfPeople;

	//// Data Loaded
	public String[] names;
	public String[] surnames;
	public double percentByCountries[];
	public int peopleByCountrie[];
	public int percentPeopleForAges[];
	private int nameIndex;
	private int surnameIndex;
	
	public Generator(Loader loader) throws IOException {
		percentByCountries=loader.getPercentByCountries();
		peopleByCountrie=loader.getPeopleByCountrie();
		names=loader.getNames();
		surnames=loader.getSurnames();
		men=0;
		women=0;
		nameIndex=0;
		surnameIndex=0;
		amountOfPeople=loader.getPopulation();
	}	
	
	
	public Person generate(int index, int array) throws IOException {			 
		int plus=index*array;

		if(surnameIndex<surnames.length) {
			surnameIndex++;
		}else {
			surnameIndex=0;
			nameIndex++;
		}

		LocalDate date=getBornDate(index);
		return new Person(IDS[array]+(index%IDS.length)*array,names[nameIndex]+" "+surnames[surnameIndex],gender(),date,height(date),COUNTRIES[getCountry(plus)]);
		}

	public int getCountry(int num) {
		int cnty=(num%(COUNTRIES.length)/2);
		boolean available=false;
		
		while(!available && cnty<COUNTRIES.length-1) {
		
			if((int)percentByCountries[cnty]>=peopleByCountrie[cnty]) { /// Esto lo que hace es que si un pais ya tiene su limite lleno, no asigne mas personas a este pais.
				available=true;
				peopleByCountrie[cnty]++;
			}else  
				cnty++;	
		}
		return cnty;
	}
	
	public LocalDate getBornDate(int index) {
		int years=2020-index%90;
		int month=years%12;
		int day=years%30;
		month=(month==0? 1:month);
		day=(day==0? 1:day);
		return LocalDate.of(years, month, day);
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
	
	public double height(LocalDate date) {
		double random=Math.random()*180;
		int sustraction=2020-date.getYear();
		
		if(sustraction<10) {
			if(random>=100) 
				random=100;
			else if(random<50) 
				random=30;
		}else {
			if(random<60) 
				random=MAXHEIGHT-random;
			else if(random<90) {
				random=MAXHEIGHT-(100-random);
			}
		}
		return (double)Math.round(random * 100)/100;
	}
	
	public long getPopulation() {
		return amountOfPeople;
	}
	
}
