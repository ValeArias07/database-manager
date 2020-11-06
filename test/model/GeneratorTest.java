package model;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import ui.DataBase;

public class GeneratorTest {
	private DataBase data;

	void setUp1() throws IOException{
		data= new DataBase();
	}
	
	@Test
	public void generateTest() throws IOException, InterruptedException {
		setUp1();
		String amount="10000000";
		long time=System.currentTimeMillis();
		data.create(amount);
		System.out.println(System.currentTimeMillis()-time);
		data.remove("UJJML4");
		System.out.println(data.search("UJJML4"));
		
	}
}
