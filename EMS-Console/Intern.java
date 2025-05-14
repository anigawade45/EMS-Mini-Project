public class Intern extends Employee {
    public Intern(String... args) throws InvalidSalaryException {

        super(args[0], args[1], args[2], args[3], args[4], 
              Double.parseDouble(args[5]), args[6], args[7], args[8], args[9], args[10]);
    }

    public Intern(String id, String name, String surname, String dob, String addr, double salary, 
                  String phone, String email, String edu, String desg, String aadhar) throws InvalidSalaryException {
        super(id, name, surname, dob, addr, salary, phone, email, edu, desg, aadhar);
    }

    // @Override
    // public double calculatePay() {
    //     return salary;
    // }

    @Override
    public String getRole() {
        return "Intern";
    }
}
