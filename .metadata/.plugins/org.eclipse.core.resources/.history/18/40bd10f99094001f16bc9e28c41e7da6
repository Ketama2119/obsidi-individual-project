package emergenceshelter.management.user;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import emergenceshelter.management.file.ReadWrite;

public class AccountHolder {

    // Fields
    //In Java, a protected instance variable is a member variable that can be 
    // accessed within its own package and by subclasses (even if they are in different packages).
    // This means that the variable is more accessible than private variables, 
    //which can only be accessed within the same class, but less accessible than public variables, 
    //which can be accessed from anywhere. The protected keyword is often used to allow controlled 
    //access to variables while still maintaining some encapsulation.
    protected static ReadWrite rw = new ReadWrite();

    protected String firstName;
    protected String lastName;
    protected String email;



    // Constructor
    public AccountHolder(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        setEmail(email);
    }

    // Getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    // Setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        if (isValidEmail(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Invalid email format: " + email);
        }
    }

    // Email validation method
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}