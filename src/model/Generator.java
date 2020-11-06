package model;
import java.io.IOException;
import java.time.LocalDate;
/**
 * A very special import to do, without this, our program wasn't fast as
 * it is now. To check more please enter to: https://github.com/williamfiset/FastJavaIO
 */
import imports.InputReader;

/**
 * Curious Things: (A lo que se supone que quiero llegar.)
 * The most older person have 80 years. Si
 * The most younger have 6 months. No
 * The height is in cm Si
 * 
 */
public class Generator {
	///// Data in Arrays. (In order to save memory).
	public static final String[] COUNTRIES= {"Argentina","Bahamas", "Barbados","Belice","Bolivia","Brasil","Canadá","Chile","Colombia","Costa Rica","Cuba","Dominica","Ecuador","El Salvador","Estados Unidos","Granada","Guatemala","Guyana","Haití","Honduras","Jamaica","México","Nicaragua","Panamá","Paraguay","Perú","República Dominicana","Santa Lucía","Surinam","Trinidad y Tobago","Uruguay","Venezuela"};
	
	public static final double MAXHEIGHT=200.0;
	public static final String[] IDS= {"PBVXZ","UJJML","NEUGO","WMXZG","GOZCA"};
	public String[] names;
	
	//// Distribution
	public int women;
	public int men;
	public int amountOfPeople;
	
	public double percentByCountries[];//// Atributo para medir la cantidad de gente generada en un determinado pais.
	public int peopleByCountrie[];
	public int percentPeopleForAges[];
	
	public Generator(Loader loader) throws IOException {
		percentByCountries=loader.getPercentByCountries();
		peopleByCountrie=loader.getPeopleByCountrie();
		names=loader.getNames();
		men=0;
		women=0;
		amountOfPeople=loader.getPopulation();
	}	
	
	public Person generate(int index, int array) throws IOException {			 
		int namesI=0;
		
		int plus=index*array;
		if(plus<names.length) {
		namesI=plus;
		}

		///Creacion de fecha.
		 	LocalDate date=getBornDate(index);
		///Creacion de persona.
		 	return new Person(IDS[array]+(index%IDS.length)*array,names[namesI],gender(),date,height(date),COUNTRIES[getCountry(plus)]);
			//return new Person(IDS[plus%IDS.length]+plus,names[namesI],gender(),date,height(date),COUNTRIES[getCountry(plus)]);
		}
	
	
	
	public int getCountry(int num) {
		int cnty=(num%(COUNTRIES.length)/2);
		boolean available=false;
		
		while(!available && cnty<COUNTRIES.length-1) {
		
			if((int)percentByCountries[cnty]>=peopleByCountrie[cnty]) { /// Esto lo que hace es que si un pais ya tiene su limite lleno, no asigne mas personas a este pais.
				available=true;
				peopleByCountrie[cnty]++;
			}else { 
					cnty++;	
			}
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
		//// Como se supone que debe haber una distribucion igual de mujeres y hombres, se creo este condicional.
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
	
	public double height(LocalDate date) {//// Aca tenia unos condicionales pero los quite.
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
