public class MainExample {
    public static void main(String[] args) {
        // Examples with all attributes added
        Student student1 = new Student("John Doe",20, "157 Baker Street", true);

        // Example with just a name given
        Student student2 = new Student("Billie Jean");

        // Example with nothing given, a blank instance
        Student student3 = new Student();
        
        // Call the getName() method for the student 1 object 
        System.out.println(student1.getName());
    }
}
