public class Patient {
    private String patientName;
    private int patientAge;
    private String patientSex;

    public Patient(String name, int age, String sex) {
        patientName = name;
        patientAge = age;
        patientSex = sex;
    }

    public Patient(String name) {
        patientName = name;
        patientAge = 0;
        patientSex = "Unknown";
    }
    
    private void printName() {
        System.out.println(patientName);
    }

    public static void main(String args[]) {
        Patient patient1 = new Patient("John Smith");
        patient1.printName();
    }
}
