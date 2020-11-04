package model;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class GeneratorTest {
	private Generator g;
	
	void setUp1() throws IOException{
		g= new Generator();
	}
	@Test
	public void generateTest() throws IOException {
		setUp1();
	ArrayList<Person> p=g.generate(100);
	//g.getPercentage();
	System.out.println(g.getTotal());
	for (int i = 0; i < p.size(); i++) {
		System.out.println(p.get(i));
	}
	
	}
}
