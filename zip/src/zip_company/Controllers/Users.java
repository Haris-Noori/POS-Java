package zip_company.Controllers;

public class Users {
    public Users(String name) {
        this.name = name;
    }
    public Users() {
        this.name = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String name ;
}
