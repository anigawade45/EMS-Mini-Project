import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class EmployeeManager {
    private final List<Employee> employees = new ArrayList<>();
    private int nextId = 1;

    public void addEmployee(Employee e) {
        employees.add(e);
        nextId++;
    }

    public void viewEmployees() {
        if (employees.isEmpty())
            System.out.println("No employees found.");
        else
            for (Employee e : employees) {
                System.out.println("----------------------------------");
                System.out.println(e);
            }
    }

    public void updateEmployee(String id, Employee updated) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId().equals(id)) {
                employees.set(i, updated);
                System.out.println("Updated successfully.");
                return;
            }
        }
        System.out.println("Employee not found.");
    }

    public void removeEmployee(String id) {
        employees.removeIf(e -> e.getId().equals(id));
        System.out.println("Removed successfully.");
    }

    public void saveToFile(String file) {
        try (PrintWriter pw = new PrintWriter(file)) {
            for (Employee e : employees) {
                pw.printf("%s,%s,%s,%s,%s,%.2f,%s,%s,%s,%s,%s\n",
                        e.id, e.name, e.surname, e.dob, e.address, e.salary,
                        e.phone, e.email, e.education, e.designation, e.aadhar);
            }
            System.out.println("Saved to " + file);
        } catch (IOException e) {
            System.out.println("Error saving: " + e.getMessage());
        }
    }

    public void loadFromFile(String file) {
        File f = new File(file);
        if (!f.exists())
            return;
        try (Scanner sc = new Scanner(f)) {
            while (sc.hasNextLine()) {
                String[] parts = sc.nextLine().split(",");
                Employee emp = switch (parts[9].toLowerCase()) {
                    case "manager" -> new Manager(parts);
                    case "developer" -> new Developer(parts);
                    case "intern" -> new Intern(parts);
                    default -> null;
                };
                if (emp != null) {
                    employees.add(emp);
                    nextId = Math.max(nextId, Integer.parseInt(parts[0]) + 1);
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading file.");
        }
    }

    public String generateNewEmployeeId() {
        return String.valueOf(nextId);
    }

    public Employee createEmployee(Scanner sc, String id) throws InvalidSalaryException {
        try {
            System.out.print("First Name: ");
            String name = sc.nextLine();
            System.out.print("Surname: ");
            String surname = sc.nextLine();
            String dob;
            while (true) {
                System.out.print("DOB (DD-MM-YYYY): ");
                dob = sc.nextLine();
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    LocalDate birthDate = LocalDate.parse(dob, formatter);
                    LocalDate today = LocalDate.now();
                    Period age = Period.between(birthDate, today);

                    if (birthDate.isAfter(today)) {
                        System.out.println("DOB cannot be in the future.");
                    } else if (age.getYears() < 18) {
                        System.out.println("Employee must be at least 18 years old.");
                    } else {
                        break;
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date format. Please use DD-MM-YYYY.");
                }
            }
            
            System.out.print("Address: ");
            String addr = sc.nextLine();

            double salary;
            while (true) {
                try {
                    System.out.print("Salary: ");
                    salary = Double.parseDouble(sc.nextLine());
                    if (salary < 0) {
                        System.out.println("Salary cannot be negative.");
                    } else {
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Enter a numeric salary.");
                }
            }

            String phone;
            while (true) {
                System.out.print("Phone (10-digit): ");
                phone = sc.nextLine();
                if (phone.matches("\\d{10}")) {
                    break;
                } else {
                    System.out.println("Invalid phone number. Must be exactly 10 digits.");
                }
            }

            String email;
            while (true) {
                System.out.print("Email: ");
                email = sc.nextLine();
                if (email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                    break;
                } else {
                    System.out.println("Invalid email format. Please enter a valid email address.");
                }
            }

            System.out.print("Education: ");
            String edu = sc.nextLine();

            String desg;
            while (true) {
                System.out.print("Designation (Manager/Developer/Intern): ");
                desg = sc.nextLine().toLowerCase();
                if (desg.equals("manager") || desg.equals("developer") || desg.equals("intern")) {
                    break;
                } else {
                    System.out.println("Invalid designation. Please enter Manager, Developer, or Intern.");
                }
            }

            String aadhar;
            while (true) {
                System.out.print("Aadhar (12-digit): ");
                aadhar = sc.nextLine();
                if (aadhar.matches("\\d{12}")) {
                    break;
                } else {
                    System.out.println("Invalid Aadhar number. Must be exactly 12 digits.");
                }
            }

            return switch (desg) {
                case "manager" -> new Manager(id, name, surname, dob, addr, salary, phone, email, edu, desg, aadhar);
                case "developer" ->
                    new Developer(id, name, surname, dob, addr, salary, phone, email, edu, desg, aadhar);
                case "intern" -> new Intern(id, name, surname, dob, addr, salary, phone, email, edu, desg, aadhar);
                default -> null;
            };

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

}
