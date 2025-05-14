public abstract class Employee {
    protected String id, name, surname, dob, address, phone, email, education, designation, aadhar;
    protected double salary;

    public Employee(String id, String name, String surname, String dob, String address,
            double salary, String phone, String email, String education,
            String designation, String aadhar) throws InvalidSalaryException {
        if (salary < 0)
            throw new InvalidSalaryException("Salary cannot be negative!");
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dob = dob;
        this.address = address;
        this.salary = salary;
        this.phone = phone;
        this.email = email;
        this.education = education;
        this.designation = designation;
        this.aadhar = aadhar;
    }

    public String getId() {
        return id;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) throws InvalidSalaryException {
        if (salary < 0)
            throw new InvalidSalaryException("Salary cannot be negative!");
        this.salary = salary;
    }

    @Override
    public String toString() {
        return String.format("""
                ID: %s
                Name: %s %s
                DOB: %s
                Address: %s
                Phone: %s
                Email: %s
                Education: %s
                Designation: %s
                Aadhar: %s
                Salary: %.2f
                """, id, name, surname, dob, address, phone, email, education, designation, aadhar, salary);
    }

    public abstract String getRole();
}
