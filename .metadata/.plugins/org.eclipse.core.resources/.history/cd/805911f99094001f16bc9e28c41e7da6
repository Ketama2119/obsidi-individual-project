package emergenceshelter.management.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Supervisor extends Staff {

    public Supervisor(String firstName, String lastName, String email, boolean isVerified, String password) {
        super(firstName, lastName, email, isVerified, password);
    }

    public static List<Staff> getAllMembers() {
        try {
            List<List<String>> membersList = rw.readCSV(Staff.filePath);
            List<Staff> members = new ArrayList<>();

            for (List<String> member : membersList) {
                members.add(
                    new Supervisor(
                        member.get(1),
                        member.get(2),
                        member.get(3),
                        Boolean.parseBoolean(member.get(4)),
                        member.get(5)
                    )
                );
            }

            return members;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void actionMenu(Scanner scanner) {
        while (true) {
            StaffMenu choice = printActionMenu(scanner);
            switch (choice) {
                case VIEW_ASSIGNED_RESIDENTS:
                    viewAssignedResidents();
                    break;
                case ASSIGN_RESIDENT_TO_CASEWORKER:
                    assignResidentToCaseWorker(scanner);
                    break;
                case ADD_NEW_RESIDENT:
                    addNewResident(scanner);
                    break;
                case BACK:
                    return;
                case EXIT:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        }
    }

    private StaffMenu printActionMenu(Scanner scanner) {
        System.out.println("\n==========================");
        System.out.println("     Supervisor Actions      ");
        System.out.println("==========================");
        System.out.println("1. Assign Resident to Case Worker");
        System.out.println("2. View Assigned Residents");
        System.out.println("3. Add New Resident");
        System.out.println("4. Back");
        System.out.println("5. Exit");
        System.out.println("==========================");
        System.out.print("Please choose an option: ");

        int input = scanner.nextInt();
        scanner.nextLine();

        if (input == 1) {
            return StaffMenu.ASSIGN_RESIDENT_TO_CASEWORKER;
        } else if (input == 2) {
            return StaffMenu.VIEW_ASSIGNED_RESIDENTS;
        } else if (input == 3) {
            return StaffMenu.ADD_NEW_RESIDENT;
        } else if (input == 4) {
            return StaffMenu.BACK;
        } else if (input == 5) {
            return StaffMenu.EXIT;
        } else {
            return StaffMenu.NONE;
        }
    }

    public void addNewResident(Scanner scanner) {
        System.out.print("Enter Resident's First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter Resident's Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter Resident's Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Resident's Phone Number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Enter Resident's Health Status: ");
        String healthStatus = scanner.nextLine();

        String assignedTo = "None";

        Resident newResident = new Resident(firstName, lastName, email, phoneNumber, healthStatus, assignedTo);

        List<String> residentData = newResident.toList();

        try {
            Resident.rw.appendToCSV("./file/residents.csv", residentData);
            System.out.println("New resident added successfully.");
        } catch (IOException e) {
            System.out.println("Error adding new resident to file.");
            e.printStackTrace();
        }
    }

    public void assignResidentToCaseWorker(Scanner scanner) {
        // Step 1: List and Select Resident
     Resident selectedResident = selectResident(scanner);

        if (selectedResident == null) {
            System.out.println("Invalid resident selection.");
            return;
        }

        // Step 2: List and Select Caseworker
        CaseWorker selectedCaseworker = selectCaseWorker(scanner);

        if (selectedCaseworker == null) {
            System.out.println("Invalid caseworker selection.");
            return;
        }

        // Step 3: Update the resident's assigned caseworker and save to CSV
        Resident.updateAssignedCaseworker(selectedResident.getEmail(), selectedCaseworker.getEmail());
        System.out.println("Resident " + selectedResident.getFirstName() + " assigned to Case Worker " + selectedCaseworker.getFirstName());
    }

    // Helper method to list residents and prompt user for selection
    private Resident selectResident(Scanner scanner) {
        List<Resident> residents = Resident.loadResidents();
        System.out.println("\nAvailable Residents:");
        System.out.println(Resident.getHeader());  // Print resident header

        for (int i = 0; i < residents.size(); i++) {
            System.out.printf("%d. %s", (i + 1), residents.get(i));
        }

        System.out.print("\nSelect a resident by number: ");
        int residentChoice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        if (residentChoice < 1 || residentChoice > residents.size()) {
            return null;  // Invalid selection
        }

        return residents.get(residentChoice - 1);
    }

    // Helper method to list caseworkers and prompt user for selection
    private CaseWorker selectCaseWorker(Scanner scanner) {
        List<Staff> staffMembers = Staff.getAllStaff();
        System.out.println("\nAvailable Caseworkers:");
        System.out.println(Supervisor.getHeader());  // Print caseworker header

        int displayIndex = 1;
        List<CaseWorker> caseworkers = new ArrayList<>();
        for (Staff staff : staffMembers) {
            if (staff instanceof CaseWorker) {
                System.out.printf("%d. %s", displayIndex, staff.toString());
                caseworkers.add((CaseWorker) staff);
                displayIndex++;
            }
        }

        System.out.print("Select a caseworker by number: ");
        int caseworkerChoice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        if (caseworkerChoice < 1 || caseworkerChoice > caseworkers.size()) {
            return null;  // Invalid selection
        }

        return caseworkers.get(caseworkerChoice - 1);
    }

    @Override
    public void viewAssignedResidents() {
        List<Resident> residents = Resident.loadResidents();
        System.out.println("\nAssigned Residents:");
        System.out.println(Resident.getHeader());

        boolean hasAssignedResidents = false;

        for (Resident resident : residents) {
            if (resident.getAssignedTo() != null && !resident.getAssignedTo().equals("None")) {
                System.out.println(resident.toString());
                hasAssignedResidents = true;
            }
        }

        if (!hasAssignedResidents) {
            System.out.println("No residents are currently assigned to any caseworkers.");
        }
    }
}