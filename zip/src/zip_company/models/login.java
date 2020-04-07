package zip_company.models;

import zip_company.Controllers.superController;

import javax.swing.*;
import java.security.NoSuchAlgorithmException;

public class login {
    private static String username;
    private String password, question="", answer="";
    private static security secure;
    private superController controller;


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String update_question_answer_query(){
        return "update users set question = '"+this.getQuestion()+"', answer = '"+
                this.getAnswer()+"' where name = '"+this.getUsername()+"';";
    }

    public String get_check_user_db_query(){
        return "select * from users where name = '" +this.getUsername()+"';";
    }
    public String get_update_password_query(){
        return "update users set password = '" + this.getPassword() + "' where name  = '" +this.getUsername() + "';";

    }
    public String get_forgot_pass_questions_query(){
        return "select question, answer from users where name = '"+this.getUsername()+"';";
    }
    public void set_username(String username){
        this.username = username;
    }
    public void set_password(String password){
        if (!password.isEmpty()){
            this.password = password;
            this.secure_password();
        }
        else{
            System.out.println("[Models->login]- Password is Empty!");
            JOptionPane.showMessageDialog(null,"password is empty\n");
        }
    }
    public String getUsername()
    {
        return this.username;
    }
    public String getPassword(){
        return this.password;
    }

    public void secure_password(){
        try {
            this.password = secure.getSecured(this.password);
        } catch (NoSuchAlgorithmException e) {
            JOptionPane.showMessageDialog(null,"passwrod did not hash"); }
    }

    public String get_type_query(){
        return "select type from users where name = '" + this.getUsername() +
                "' and password  = '" +this.getPassword() + "';";
    }


    public  login(){
        this.username = null;
        this.password = null;
        this.secure = new security();
    }
}
