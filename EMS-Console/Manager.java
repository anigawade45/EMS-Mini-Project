public class Manager extends Employee {

    // Constructor using varargs, can pass multiple strings
    public Manager(String... args) throws InvalidSalaryException {
        // Call the Employee constructor with the given arguments
        super(args[0], args[1], args[2], args[3], args[4],
                Double.parseDouble(args[5]), args[6], args[7], args[8], args[9], args[10]);
    }

    // Constructor with specific parameters for Manager
    public Manager(String id, String name, String surname, String dob, String addr, double salary,
            String phone, String email, String edu, String desg, String aadhar) throws InvalidSalaryException {
        // Call the parent class constructor
        super(id, name, surname, dob, addr, salary, phone, email, edu, desg, aadhar);
    }

    // @Override
    // public double calculatePay() {
    //     // Manager's pay includes an additional bonus
    //     return salary + 10000;
    // }

    // @Override
    // public String generateReport() {
    //     // Example report for Manager
    //     return "Manager report generated.";
    // }

    @Override
    public String getRole() {
        return "Manager";
    }
}
