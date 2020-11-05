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
	private String picture;
	
	public Person(String id, String name, boolean gender, LocalDate bornDate, double height, String nationality,
			String picture) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.bornDate = bornDate;
		this.height = height;
		this.nationality = nationality;
		this.picture = picture;
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

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	@Override
	public String toString() {
		String genderText=(gender==false ? "Female":"Male");
		return "ID: "+ id+ "\nName: "+name+"\nGender: "+genderText+"\nBorn Date: "+ bornDate.toString()+"\nHeight: "+height+"\nNationality: "+nationality+"\n";
	}
	
}
