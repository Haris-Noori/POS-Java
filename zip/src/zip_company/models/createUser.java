package zip_company.models;

import java.security.NoSuchAlgorithmException;

public class createUser {
    private String name, type, password;
    private  security securePass;

    public String get_insert_query(){
        return "insert into users values ('" +this.getName()+"','"+this.getType()+"','"+this.getPassword()+"','"+""+"','"+""+"');";
    }

    public  String get_users_query(){ return  "select * from users;";}

    public String get_check_user_db_query(){
        return "select type from users where name = '" +this.name+"';";
    }

    public String get_delete_user_query(){ return "delete from users where name = '"+this.getName()+"';"; }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password)  {
        try {
            this.securePass = new security();
            this.password = this.securePass.getSecured(password);

        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
