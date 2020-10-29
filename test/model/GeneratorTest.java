package model;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class GeneratorTest {
	private Generator g;
	
	void setUp1(){
		g= new Generator();
	}
	@Test
	public void generateTest() throws IOException {
		setUp1();
		g.generate(100000000);
		System.out.println(g.getTotal());
		/**
		 * for (int i = 0; i < totalPerson.size(); i++) {
		 * System.out.println(totalPerson.get(i).toString());
		}
		 */
			
	}
}
