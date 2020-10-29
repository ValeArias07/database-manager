package model;
/**
 * Curious Things:
 * The most older person have 110 years.
 * The most younger have 6 months.
 * 
 * @author USER
 *
 */
public class Generator {
	///// Data in Arrays. (In order to save memory).
	public static final String[] countries= {"Argentina","Bahamas", "Barbados","Belice","Bolivia","Brasil","Canadá","Chile","Colombia","Costa Rica","Cuba","Dominica","Ecuador","El Salvador","Estados Unidos","Granada","Guatemala","Guyana","Haití","Honduras","Jamaica","México","Nicaragua","Panamá","Paraguay","Perú","República Dominicana","Santa Lucía","Surinam","Trinidad y Tobago","Uruguay","Venezuela"};
	public static final double percent[]= {45267449, 393893, 287437,398845,11700207,212821986, 37799407,19144605,50976248,5102158,11325391,17688599, 6491923, 331341050,112614,17971382,787215,11426356,9931333,2963429,129166028,6638075,4326296,7147553,33050211,10866667,183774,587541,1400283,3475842,28421581};
	public static final int initialYear= 1910;
	
	//// Data's Adress
	public static final String names ="/data/datasets/Names.txt";
	public static final String surnames ="/data/datasets/Surnames.txt";
	public static final String id="/data/datasets/Id.txt";
	
	public double percentFulled[];

	public Generator() {
		this.percentFulled = new double[percent.length];
	}	
	
}
