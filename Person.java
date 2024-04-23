public abstract class Person {
    protected String firstName;
    protected String lastName;
    protected String username;
    protected String password;
    public Person() {
        this.firstName = "";
        this.lastName = "";
        this.username = "";
        this.password = "";
    }
    public Person(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setFirstName(String newFirstName) {
        this.firstName = newFirstName;
    }
    public void setLastName(String newLastName){
        this.lastName = newLastName;
    }
    public void setUsername(String newUsername) {
        this.username = newUsername; 
    }
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
    public void displayInfo(){ 
        System.out.printf("First Name: %s ", firstName);
        System.out.printf("Last Name: %s ", lastName);
        System.out.printf("Username: %s ", username);
    }
}