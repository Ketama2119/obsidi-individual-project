package emergenceshelter.management.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Admin extends Personnel {

    public Admin(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password);
    }

    public void loginAdmin(Scanner scanner, List < Staff > staffs, HashMap<String, String> colors) {
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (email.equals(this.email) && password.equals(this.password)) {
            System.out.println(colors.get("GREEN") + "Logged in Successfully!!" + colors.get("RESET"));
            adminMenuHandler(scanner, staffs, colors);
        } else {
            System.out.println(colors.get("RED") + "Incorrect email or password!" + colors.get("RESET"));
        }
    }

    public AdminMenu printAdminMenu(Scanner scanner) {
        int input;

        while (true) {
            System.out.println("\n===========================\n" +
                "ADMIN MENU\n" +
                "---------------------------\n" +
                "1. Approve Staff\n" +
                "2. Revoke Staff\n" +
                "3. View Staff\n" +
                "4. Back\n" +
                "5. Exit\n" +
                "===========================\n");

            System.out.print("Enter your choice: ");
            input = scanner.nextInt();
            scanner.nextLine();

            if (input >= 1 && input <= 5) {
                break;
            } else {
                System.out.println("Invalid input! Please try again.");
            }
        }

        AdminMenu choice;

        if (input == 1) {
            choice = AdminMenu.APPROVE_STAFF;
        } else if (input == 2) {
            choice = AdminMenu.REVOKE_STAFF;
        } else if (input == 3) {
            choice = AdminMenu.VIEW_APPROVED_STAFF;
        } else if (input == 4) {
            choice = AdminMenu.BACK;
        } else if (input == 5) {
            choice = AdminMenu.EXIT;
        } else {
            choice = AdminMenu.NONE;
        }

        return choice;
    }

    public void adminMenuHandler(Scanner scanner, List < Staff > staffs, HashMap<String, String> colors) {
        while (true) {
            AdminMenu choice = printAdminMenu(scanner);
            switch (choice) {
                case APPROVE_STAFF:
                    approveStaff(scanner, staffs, colors);
                    break;
                case REVOKE_STAFF:
                    revokeStaff(scanner, staffs, colors);
                    break;
                case VIEW_APPROVED_STAFF:
                    viewStaff(staffs);
                    break;
                case BACK:
                    return;
                case EXIT:
                    System.out.println(colors.get("RED") + "Exiting the system. Goodbye!" + colors.get("RESET"));
                    System.exit(0);
                default:
                    System.out.println("");
                    break;
            }
        }
    }

    public static Admin getAdmin(HashMap<String, String> colors) {
        try {
            List < String > adminData = rw.readCSV("./file/admin.csv").get(0);
            Admin admin = new Admin(
                adminData.get(1),
                adminData.get(2),
                adminData.get(3),
                adminData.get(4));
            return admin;

        } catch (IOException e) {
            System.out.println(colors.get("RED") + "Error loading admin data." + colors.get("RESET"));
            e.printStackTrace();
            return null;
        }
    }

    public void approveStaff(Scanner scanner, List < Staff > staffs, HashMap<String, String> colors) {
        staffHelper(scanner, staffs, true);
        System.out.println(colors.get("GREEN") + "Selected Account Approved!!!" + colors.get("RESET"));
    }

    public void revokeStaff(Scanner scanner, List < Staff > staffs, HashMap<String, String> colors) {
        staffHelper(scanner, staffs, false);
        System.out.println(colors.get("RED") + "Selected Account Revoked!!!" + colors.get("RESET"));
    }

    public void viewStaff(List < Staff > staffs) {
        System.out.print("\n" + Staff.getHeader());
        for (int i = 0; i < staffs.size(); i++) {
            Staff staff = staffs.get(i);
            System.out.print(Integer.toString(i + 1) + ". " + staff);
        }
    }

    private void staffHelper(Scanner scanner, List< Staff > staffs, boolean approve) {
        viewStaff(staffs);
        System.out.print("\n\nEnter Id of Staff: ");
        String id = scanner.nextLine();
        Staff staff = staffs.get(Integer.parseInt(id) - 1);
        staff.isVerified = approve;

        List < List < String >> rowData = new ArrayList < > ();
        for (Staff s: staffs) {
            rowData.add(s.staffToList());
        }

        try {
            rw.writeAll(Staff.filePath, rowData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}