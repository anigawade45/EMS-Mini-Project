import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InvalidSalaryException {
        EmployeeManager manager = new EmployeeManager();
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("\u001B[36m--------------------------------------------------\u001B[0m");
            System.out.println("\u001B[32m     WELCOME TO THE EMPLOYEE MANAGEMENT SYSTEM    \u001B[0m");
            System.out.println("\u001B[36m--------------------------------------------------\u001B[0m");

            manager.loadFromFile("employees.txt");

            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            if (!username.equals("admin") || !password.equals("admin123")) {
                System.out.println("Authentication failed.");
                return;
            }

            int choice;
            do {
                System.out.println("\n1. Add Employee");
                System.out.println("2. View Employees");
                System.out.println("3. Update Employee");
                System.out.println("4. Remove Employee");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");
                while (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Enter a number.");
                    scanner.next();
                }
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> {
                        Employee newEmp = manager.createEmployee(scanner, manager.generateNewEmployeeId());
                        manager.addEmployee(newEmp);
                    }
                    case 2 -> manager.viewEmployees();
                    case 3 -> {
                        System.out.print("Enter Employee ID to update: ");
                        String id = scanner.nextLine();
                        Employee updatedEmp = manager.createEmployee(scanner, id);
                        manager.updateEmployee(id, updatedEmp);
                    }
                    case 4 -> {
                        System.out.print("Enter Employee ID to remove: ");
                        String id = scanner.nextLine();
                        manager.removeEmployee(id);
                    }
                    case 5 -> System.out.println("Exiting...");
                    default -> System.out.println("Invalid choice.");
                }
            } while (choice != 5);

            manager.saveToFile("employees.txt");
        }
    }
}
