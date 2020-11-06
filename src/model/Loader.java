package model;
 // A very special import to do, without this, our program wasn't fast as it is now. To check more please enter to: https://github.com/williamfiset/FastJavaIO
import imports.InputReader;
import java.io.FileInputStream;
import java.io.IOException;

public class Loader {
	
	public static String[] names;
	public double percentByCountries[];
	public int peopleByCountrie[];
	public int population;
	
	////Data's Adress
	public static final String NAMES ="data/datasets/Names.txt";
	public static final String SURNAMES ="data/datasets/Surnames.txt";
	
	public static final double PERCENTCOUNTRIES[]= {4.43,0.04,0.03,0.04,1.14,20.84,3.7,1.87,4.99,0.5,1.11,0.01,1.73,32.45,0.64,0.01,1.76,0.08,1.12,0.97,0.29,12.64,0.65,0.42,0.7,3.24,0.28,1.06,0.02,0.06,0.14,0.34,2.79};
	public static final double PERCENTPEOPLE[]= {18.62 ,13.12 ,39.29 ,12.94 ,16.03};
	
	public Loader(int amountToGenerate) throws IOException {
		this.percentByCountries= new double[PERCENTCOUNTRIES.length];
		this.peopleByCountrie=new int[PERCENTCOUNTRIES.length];
		generatePercentPeopleByCountrie(amountToGenerate);
		storeNames();
		population=amountToGenerate;
	}

	public void storeNames() throws IOException{
		names=new String[100000];
		InputReader readerF = new InputReader(new FileInputStream(NAMES));
		InputReader readerS = new InputReader(new FileInputStream(SURNAMES));
		for (int i = 0; i < names.length; i++) {
			names[i]=readerF.nextLine()+" "+readerS.nextLine();
		}
	}
	
	public void generatePercentPeopleByCountrie(long population) {
		for (int i = 0; i < PERCENTCOUNTRIES.length; i++) {
			percentByCountries[i]=(int)(PERCENTCOUNTRIES[i]*population)/100;
		}
	}
	
	public String[] getNames() {
		return names;
	}
	
	public double[] getPercentByCountries() {
		return percentByCountries;
	}

	public int[] getPeopleByCountrie() {
		return peopleByCountrie;
	}

	public int getPopulation() {
		return population;
	}
}
