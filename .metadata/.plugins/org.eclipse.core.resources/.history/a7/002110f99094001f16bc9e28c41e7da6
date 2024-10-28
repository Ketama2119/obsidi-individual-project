package emergenceshelter.management.user;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CaseWorker extends Staff {

    public static final String path = "./file/caseworkers.csv";

    public CaseWorker(String firstName, String lastName, String email, boolean isVerified, String password) {
        super(firstName, lastName, email, isVerified, password);
    }

    public static List<Staff> getAllMembers() {
        try {
            List<List<String>> membersList = rw.readCSV(path);
            List<Staff> members = new ArrayList<>();

            for (List<String> member : membersList) {
                members.add(
                    new CaseWorker(
                        member.get(1),
                        member.get(2),
                        member.get(3),
                        Boolean.parseBoolean(member.get(4)),
                        member.get(5))
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
                case SCHEDULE_MEETING_WITH_RESIDENT:
                    scheduleMeetingWithResident(scanner);
                    break;
                case VIEW_UPCOMING_MEETINGS:
                    viewUpcomingMeetings();
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

    public StaffMenu printActionMenu(Scanner scanner) {
        System.out.println("\n==========================");
        System.out.println("     CaseWorker Actions      ");
        System.out.println("==========================");
        System.out.println("1. View Assigned Residents");
        System.out.println("2. Schedule Meeting with Resident");
        System.out.println("3. View Upcoming Meetings");
        System.out.println("4. Back");
        System.out.println("5. Exit");
        System.out.println("==========================");
        System.out.print("Please choose an option: ");

        int input = scanner.nextInt();
        scanner.nextLine();

        if (input == 1) {
            return StaffMenu.VIEW_ASSIGNED_RESIDENTS;
        } else if (input == 2) {
            return StaffMenu.SCHEDULE_MEETING_WITH_RESIDENT;
        } else if (input == 3) {
            return StaffMenu.VIEW_UPCOMING_MEETINGS;
        } else if (input == 4) {
            return StaffMenu.BACK;
        } else if (input == 5) {
            return StaffMenu.EXIT;
        } else {
            return StaffMenu.NONE;
        }
    }

    @Override
    public void viewAssignedResidents() {
        List<Resident> residents = Resident.loadResidents();
        System.out.println("\nAssigned Residents:");
        System.out.println(Resident.getHeader());

        boolean hasAssignedResidents = false;

        for (Resident resident : residents) {
            if (resident.getAssignedTo() != null && resident.getAssignedTo().equals(this.getEmail())) {
                System.out.println(resident.toString());
                hasAssignedResidents = true;
            }
        }

        if (!hasAssignedResidents) {
            System.out.println("No residents are currently assigned to you.");
        }
    }

    public void viewUpcomingMeetings() {
        // For simplicity, let's assume the meetings are stored in a CSV file as well.
        // In a full application, this could retrieve data from a more structured data source.
        try {
            List<List<String>> meetings = rw.readCSV("./file/upcoming_meetings.csv");

            System.out.println("\nUpcoming Meetings:");
            System.out.printf("| %-15s | %-30s | %-20s |\n", "Resident Name", "Meeting Date", "Meeting Time");

            boolean hasMeetings = false;
            for (List<String> meeting : meetings) {
                if (meeting.size() > 2 && meeting.get(0).equals(this.getEmail())) {
                    System.out.printf("| %-15s | %-30s | %-20s |\n", meeting.get(1), meeting.get(2), meeting.get(3));
                    hasMeetings = true;
                }
            }

            if (!hasMeetings) {
                System.out.println("You have no upcoming meetings.");
            }
        } catch (IOException e) {
            System.out.println("Error loading meetings.");
            e.printStackTrace();
        }
    }

    public void scheduleMeetingWithResident(Scanner scanner) {
        System.out.print("Enter Resident's Email for the Meeting: ");
        String residentEmail = scanner.nextLine();

        System.out.print("Enter Meeting Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        System.out.print("Enter Meeting Time (HH:MM): ");
        String time = scanner.nextLine();

        List<String> meetingDetails = new ArrayList<>();
        meetingDetails.add(this.getEmail());
        meetingDetails.add(residentEmail);
        meetingDetails.add(date);
        meetingDetails.add(time);

        try {
            rw.appendToCSV("./file/upcoming_meetings.csv", meetingDetails);
            System.out.println("Meeting scheduled successfully with resident: " + residentEmail);
        } catch (IOException e) {
            System.out.println("Error scheduling meeting.");
            e.printStackTrace();
        }
    }

}