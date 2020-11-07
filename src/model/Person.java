package model;
import java.time.LocalDate;

public class Person {

	public static final String GENDER_M = "Male";
	public static final String GENDER_F = "Female";
	
	private String id;	///Generator&text.
	private String name; ///Text.
	private boolean gender; ///Generator.
	private LocalDate bornDate; ///Generator.
	private double height; ///Generator. Conditional with the age
	private String nationality; ///Array.
	
	public Person(String id, String name, boolean gender, LocalDate bornDate, double height, String nationality) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.bornDate = bornDate;
		this.height = height;
		this.nationality = nationality;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public LocalDate getBornDate() {
		return bornDate;
	}

	public void setBornDate(LocalDate bornDate) {
		this.bornDate = bornDate;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	@Override
	public String toString() {
		String genderText=(gender==false ? "Female":"Male");
		return "ID: "+ id+ "\nName: "+name+"\nGender: "+genderText+"\nBorn Date: "+ bornDate.toString()+"\nHeight: "+height+"\nNationality: "+nationality+"\n_________________";
	}
}
