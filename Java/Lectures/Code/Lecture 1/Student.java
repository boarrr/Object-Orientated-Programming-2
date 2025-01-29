public class Student {
    // Attributes
    private String name;
    private String address;
    private int age;
    private boolean feesPaid;
	
	// Constructor for all attributes
	public Student(String name, int age, String address, boolean feesPaid) {
		this.name = name;
		this.age = age;
		this.address = address;
		this.feesPaid = feesPaid;
	}

	// Constructor that takes just name
	public Student(String name) {
		this.name = name;
	}

    public Student() {
        this.name = "";
        this.address = "";
        this.age = 0;
        this.feesPaid = false;
    }


	public String getName() {
		return "The name of this student is " + name;
	}
}
