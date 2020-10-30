package model;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import imports.InputReader;


/**
 * LEER PORFAVOR.
 * ESTE ES MI PRIMER INTENTO DEL PROGRAMA. FALTAN VARIAS CONDICIONES, REVISE COMO ESTA Y ME COMENTA
 */

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
	public static final double PERCENTCOUNTRIES[]= {4.43,0.04,0.03,0.04,1.14,20.84,3.7,1.87,4.99,0.5,1.11,0.01,1.73,32.45,0.64,0.01,1.76,0.08,1.12,0.97,0.29,12.64,0.65,0.42,0.7,3.24,0.28,1.06,0.02,0.06,0.14,0.34,2.79};
	public static final double PERCENTPEOPLE[]= {18.62 ,13.12 ,39.29 ,12.94 ,16.03};
	public static final double MAXHEIGHT=200.0;
	
	public static String[] names;
	public static String[] surnames;
	
	//// Data's Adress
	public static final String MNAMES ="data/datasets/MaleNames.txt";
	public static final String FNAMES ="data/datasets/FemaleNames.txt";
	public static final String SURNAMES ="data/datasets/Surnames.txt";
	public static final String ID="data/datasets/Id.txt";
	
	//// Distribution
	public int women;
	public int men;
	public long amountOfPeople;
	
	public double percentByCountries[];//// Atributo para medir la cantidad de gente generada en un determinado pais.
	public int peopleByCountrie[];
	public int percentPeopleForAges[];
	public int ages[];
	
	public Generator() throws IOException {
		this.percentByCountries= new double[PERCENTCOUNTRIES.length];
		this.percentPeopleForAges= new int[PERCENTPEOPLE.length];
		this.peopleByCountrie=new int[PERCENTCOUNTRIES.length];
		storeNames();
		storeSurnames();
		men=0;
		women=0;
		amountOfPeople=0;
	}	
	
	public void generate(int amountToGenerate) throws IOException {
		int nameIndex=0, surnameIndex=0, bornIndex=0;
		LocalDate date;
		generatePercentAge(amountToGenerate);
		generatePercentPeopleByCountrie(amountToGenerate);
		bornDate(amountToGenerate);
		long time=System.currentTimeMillis();		
		//ArrayList<Person> peopleGenerated=new ArrayList<Person>();/// Se almacenan aca por si quiere ver como quedan los objetos con el metodo toString.
	
		while(amountOfPeople<amountToGenerate) { ///Ciclo para que se generen el numero de personas
				
				///Creacion de fecha.
				date=getBornDate(bornIndex);

				///Creacion de persona.
				Person p=new Person("",names[nameIndex]+" "+surnames[nameIndex],gender(),date,height(date),COUNTRIES[getCountry()],"defined");
				
				//atribbuto para ver si se crean la cantidad de personas correcta.
				nameIndex=(nameIndex>names.length? 0:+1);
				surnameIndex=(surnameIndex>surnames.length?0:1);
				bornIndex++;
				amountOfPeople++;
		}

		System.out.println(System.currentTimeMillis()-time);
	}

	public  void storeSurnames() throws IOException {
		surnames=new String[100000];
		InputReader readerS = new InputReader(new FileInputStream(SURNAMES));
		int max=0;
		while(max<=names.length-1) {
			surnames[max]=readerS.nextLine();
			max++;
		}
	}
	public void storeNames() throws IOException {
		names=new String[100000];
		InputReader readerF = new InputReader(new FileInputStream(FNAMES));
		InputReader readerM = new InputReader(new FileInputStream(MNAMES));
		int max=0;
		while(max<=names.length-1) {
			if(max%2==0) {
				names[max]=readerM.nextLine();
			}else{
				names[max]=readerF.nextLine();
			}
			max++;
		}
	}
	
	public void generatePercentAge(long population) {
		for (int i = 0; i < PERCENTPEOPLE.length; i++) {
			percentPeopleForAges[i]=(int)(PERCENTPEOPLE[i]*population)/100;
		}
	}
	
	public void generatePercentPeopleByCountrie(long population) {
		for (int i = 0; i < PERCENTCOUNTRIES.length; i++) {
			percentByCountries[i]=(int)(PERCENTCOUNTRIES[i]*population)/100;
		}
	}
	

	public int getCountry() {
		int cnty=(int)(Math.random()*30);
		boolean available=false;
		while(!available) {
			if(PERCENTPEOPLE[cnty]>=percentByCountrie[cnty]) { /// Esto lo que hace es que si un pais ya tiene su limite lleno, no asigne mas personas a este pais.
				available=true;
				percentFulled[cnty]=percentFulled[cnty]+1;
			}else { /// De ya estar lleno el pais, se cambia a otro aleatorio.
				cnty=(int)(Math.random()*30);
			}
		}
		return cnty;
	}
	
	public LocalDate getBornDate(int index) {
		int years=ages[index];
		int month=years%12;
		int day=years%30;
		month=(month==0? 1:month);
		day=(day==0? 1:day);
		return LocalDate.of(years, month, day);
	}


	public LocalDate bornDate() {
		int random=(int)(Math.random()*80); /// Genera un numero aleatorio de 0 a 80.
		LocalDate newDate=INITIALYEAR.minus(random, ChronoUnit.YEARS); /// Le resta ese numero aleatorio a los anos de la fecha base.
		newDate=newDate.minus(random%30, ChronoUnit.DAYS); /// Intento de generar un numero aleatorio basandose en el numero de anos sumados.
		newDate=newDate.minus(random%12, ChronoUnit.MONTHS); //Igual que lo anterior
		return newDate;
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
			if(random<90) 
				random=MAXHEIGHT-random;
		}
		return (double)Math.round(random * 100) / 100;
	}
	
	
	
	public long getTotal() { ///Metodo para comprobar que efectivamente se crearon el numero n de personas.
		return amountOfPeople;
	}
	
	public void getPercentage() {
		for (int i = 0; i < ages.length; i++) {
			System.out.println(ages[i]);
		}
	}
}
