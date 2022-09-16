
// Enum class that stores the different types of bank accounts an Account Holder can have.
public enum AccountTypes {
    Personal("Personal Account"),
    ISA("ISA Account"),
    Business("Business Account");

    private final String type;

    public String getType() {
        return type;
    }

    // create enum constructor
    AccountTypes(String type) {
        this.type = type;
    }

}
