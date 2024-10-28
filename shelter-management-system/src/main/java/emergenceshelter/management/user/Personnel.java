package emergenceshelter.management.user;

public abstract class Personnel extends AccountHolder {

    protected String password;

    public Personnel(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email);
        this.password = password;
    }

    
}