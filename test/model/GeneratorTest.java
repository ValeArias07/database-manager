package model;
import java.io.IOException;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import ui.DataBase;

public class GeneratorTest {
	private DataBase data;

	void setUp1() throws IOException{
		data= new DataBase();
	}
	
	void setUp2() throws IOException{
		String amount="10000000";
		data= new DataBase();
		data.create(amount);
	}
	
	@Test
	public void generateTest() throws IOException, InterruptedException {
		setUp1();
		String amount="10000000";
		long time=System.currentTimeMillis();
		data.create(amount);
		System.out.println(System.currentTimeMillis()-time);
	}
	
	@Test
	public void add() throws IOException {
		DataBase data= new DataBase();
		data.add("Sofia", false, LocalDate.of(2001, 3, 26), 1.45 , "Argentina");
		data.add("Alejandro", true, LocalDate.of(2002, 12, 28), 1.32 , "Costa Rica");
		data.add("Valentina", false, LocalDate.of(2001, 4, 18), 1.53 , "Colombia");
		data.add("Jose", true, LocalDate.of(2001, 9, 12), 1.68 , "USA");
		data.add("Adam", true, LocalDate.of(2001, 12, 10), 1.90 , "Peru");
		
		System.out.println(data.search("ADSHSKA0"));
		System.out.println(data.search("ADSHSKA1"));
		System.out.println(data.search("ADSHSKA2"));
		System.out.println(data.search("ADSHSKA3"));
		System.out.println(data.search("ADSHSKA4"));
		System.out.println(data.search("ADSHSKA5"));
		System.out.println(data.getT1().getSize());
		System.out.println(data.getT2().getSize());
		System.out.println(data.getT3().getSize());
		System.out.println(data.getT4().getSize());
	}
	
	
}

