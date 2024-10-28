package emergenceshelter.management.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Staff extends Personnel {

    protected final static String filePath = "./file/staff.csv";

    public boolean isVerified;
    public abstract void viewAssignedResidents();
    public abstract void actionMenu(Scanner scanner);


    public Staff(String firstName, String lastName, String email, boolean isVerified, String password) {
        super(firstName, lastName, email, password);
        this.isVerified = isVerified;
    }


    public List < String > staffToList() {
        List < String > list = new ArrayList < > ();
        list.add(this.firstName);
        list.add(this.lastName);
        list.add(this.email);
        list.add(Boolean.toString(isVerified));
        list.add(this.password);
        list.add(this.getClass().getSimpleName());
        return list;
    }

    public String getPassword() {
        return this.password;
    }


    public static List < Staff > getAllStaff() {
        List < Staff > staffs = new ArrayList < > ();

        try {
            List < List < String >> rowData = rw.readCSV(Staff.filePath);

            for (List < String > row: rowData) {
                String role = row.get(5);
                if (role.equals("Supervisor")) {
                    staffs.add(
                        new Supervisor(
                            row.get(0),
                            row.get(1),
                            row.get(2),
                            Boolean.parseBoolean(row.get(3)),
                            row.get(4))
                    );
                } else if (role.equals("CaseWorker")) {
                    staffs.add(
                        new CaseWorker(
                            row.get(0),
                            row.get(1),
                            row.get(2),
                            Boolean.parseBoolean(row.get(3)),
                            row.get(4))
                    );
                }
            }

            return staffs;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static void signUpStaff(Scanner scanner) {
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter role (1. Supervisor, 2. Caseworker): ");
        String role = scanner.nextLine();

        System.out.print("Enter new password: ");
        String password = scanner.nextLine();

        Staff newStaff = null;

        if (role.equals("1")) {
            newStaff = new Supervisor(firstName, lastName, email, false, password);
        } else if (role.equals("2")) {
            newStaff = new CaseWorker(firstName, lastName, email, false, password);
        } else {
            System.out.println("Invalid role selected!");
            return;
        }

        newStaff.addToDatabase();
        System.out.println("\nUser Added Sucessfuly! Please wait until you are approved by Admin.");
    }


    public boolean addToDatabase() {
        List < String > line = this.staffToList();
        try {
            rw.appendToCSV(Staff.filePath, line);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }



    @Override
    public String toString() {
        return String.format("| %-15s  %-30s  %-12s  %-10s |\n",
            getFirstName() + " " + getLastName(),
            getEmail(),
            Boolean.toString(isVerified),
            getClass().getSimpleName()
        );
    }


    public static String getHeader() {
        return String.format("| %-15s | %-30s | %-12s | %-10s |\n",
            "Name", "Email", "Verified", "Role");
    }


}