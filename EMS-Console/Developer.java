public class Developer extends Employee {

    // Constructor using varargs, you can pass multiple strings
    public Developer(String... args) throws InvalidSalaryException {
        // Call the Employee constructor with the given arguments
        super(args[0], args[1], args[2], args[3], args[4], 
              Double.parseDouble(args[5]), args[6], args[7], args[8], args[9], args[10]);
    }

    // Constructor with specific parameters for developer
    public Developer(String id, String name, String surname, String dob, String addr, double salary, 
                     String phone, String email, String edu, String desg, String aadhar) throws InvalidSalaryException {
        super(id, name, surname, dob, addr, salary, phone, email, edu, desg, aadhar);
    }

    @Override
    public String getRole() {
        return "Developer";
    }
}
