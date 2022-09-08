
    public enum AccountTypes {

        //declare constants of available types of account: Personal, ISA and Business
        Personal ("Personal Account"),
        ISA ("ISA Account"),
        Business ("Business Account"); //Business constant holding the Business Account value

        private String type; //private variable to holds the enum value

        //get to access the  type values
        public String getType() {
            return type;
        }

        //create enum constructor
        AccountTypes(String type) {
            this.type = type;
        }


}
