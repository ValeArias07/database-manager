package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;


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
	public static final double PERCENT[]= {45267449, 393893, 287437,398845,11700207,212821986, 37799407,19144605,50976248,5102158,11325391,17688599, 6491923, 331341050,112614,17971382,787215,11426356,9931333,2963429,129166028,6638075,4326296,7147553,33050211,10866667,183774,587541,1400283,3475842,28421581};
	public static final LocalDate INITIALYEAR= LocalDate.of(2020, 1, 1);
	public static final double MAXHEIGHT=200.0;
	
	//// Data's Adress
	public static final String MNAMES ="data/datasets/MaleNames.txt";
	public static final String FNAMES ="data/datasets/FemaleNames.txt";
	public static final String SURNAMES ="data/datasets/Surnames.txt";
	public static final String ID="data/datasets/Id.txt";
	
	//// Distribution
	public int women;
	public int men;
	public long amountOfPeople;
	
	public double percentFulled[]; //// Atributo para medir la cantidad de gente generada en un determinado pais.
	
	public Generator() {
		this.percentFulled = new double[PERCENT.length];
		men=0;
		women=0;
		amountOfPeople=0;
	}	
	
	public void generate(long amountToGenerate) throws IOException {
		String name="";
		LocalDate date;
		boolean gender;
		long time=System.currentTimeMillis();
		ArrayList<Person> peopleGenerated=new ArrayList<Person>();/// Se almacenan aca por si quiere ver como quedan los objetos con el metodo toString.
		BufferedReader readerId= new BufferedReader(new FileReader(ID)), readerFName= new BufferedReader(new FileReader(FNAMES)), readerMName= new BufferedReader(new FileReader(MNAMES)), readerSurname= new BufferedReader(new FileReader(SURNAMES));
		while(amountOfPeople<amountToGenerate) { ///Ciclo para que se generen el numero de personas
				gender=gender(); /// Llama al metodo gender. Segun el gender, se toma un nombre masculino o femenino.
				
				///Condicional para los nombres.
				name=(gender==false? readerFName.readLine()+" "+readerSurname.readLine():readerMName.readLine()+" "+readerSurname.readLine());
				///Creacion de fecha.
				date=bornDate();
				
				///Creacion de persona.
				peopleGenerated.add(new Person(readerId.readLine(),name,gender,date,height(),COUNTRIES[getCountry()],"defined"));
				
				//atribbuto para ver si se crean la cantidad de personas correcta.
				amountOfPeople++;
		}
		readerId.close();
		readerFName.close();
		readerMName.close();
		System.out.println(System.currentTimeMillis()-time);
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
	
	public double height() {//// Aca tenia unos condicionales pero los quite.
		return MAXHEIGHT-Math.random()*100;
	}
	
	public int getCountry() {
		int cnty=(int)(Math.random()*30);
		boolean available=false;
		while(!available) {
			if(PERCENT[cnty]>=percentFulled[cnty]) { /// Esto lo que hace es que si un pais ya tiene su limite lleno, no asigne mas personas a este pais.
				available=true;
				percentFulled[cnty]=percentFulled[cnty]+1;
			}else { /// De ya estar lleno el pais, se cambia a otro aleatorio.
				cnty=(int)(Math.random()*30);
			}
		}
		return cnty;
	}
	
	public long getTotal() { ///Metodo para comprobar que efectivamente se crearon el numero n de personas.
		return amountOfPeople;
	}
}
