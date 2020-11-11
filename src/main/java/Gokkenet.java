import processing.core.PApplet;
import processing.core.PImage;
import processing.data.Table;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Gokkenet extends PApplet {

    LoginSide ls;
    String testUser = "Albert" , testPassword = "Abe123";
    long userId;
    BattleMenu bm;
    PImage bg;
    Table questions;
    public DataHolder dh;

    private String databaseURL = "jdbc:ucanaccess://src//main//java//resources//database.accdb";
    private Connection connection = null;

    public static void main(String[] args) {
        PApplet.main("Gokkenet");
    }

    public Gokkenet() {
        try {
            connection = DriverManager.getConnection(databaseURL);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        println("Connected to MS Access database. ");
    }

    @Override
    public void settings() {
        size(1280,720);
    }

    @Override
    public void setup() {
        questions = loadTable("sp.csv");
        ls = new LoginSide(this);
        bm = new BattleMenu(this, questions );
        bg = loadImage("bg.png");
    }

    @Override
    public void draw() {

        clear();

        background(bg);
        if(ls.visible){
            ls.drawSide();

        } else if(bm.visbel){
            bm.drawBattleMenu();
        }

        if (ls.visible && ls.btnLogin.klikket == true) {
            login();
        }

    }

    @Override
    public void keyTyped() {
        if(ls.visible){
            ls.typede(key);
        }



    }

    @Override
    public void mouseClicked() {
        if (ls.visible) {
            ls.clik(mouseX, mouseY);
        }
        if(bm.visbel){
            bm.clicked(mouseX,mouseY);

        }
    }
    public String getHash(String passwordToHash){

        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(passwordToHash.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }


    private void login() {
        ls.btnLogin.registrerRelease();
        Statement s = null;
        try {
            s = connection.createStatement();
            ResultSet rsUser = s.executeQuery("SELECT [username],[password], [Teacher], [userID] FROM [user]");

            while (rsUser.next()) {
                String rsUsername = rsUser.getString(1);
                String rsPassword = rsUser.getString(2);
                boolean rsRoleIsTeacher = rsUser.getBoolean(3);


                System.out.println(rsUsername);
                System.out.println(rsPassword);
                System.out.println(rsRoleIsTeacher +"\n");

                if (ls.userName.indput.equals(rsUsername) && getHash(ls.password.indput).equals(rsPassword)
                        || testUser.equals(rsUsername) && getHash(testPassword).equals(rsPassword) ) {
                    ls.visible = false;
                    if(!rsRoleIsTeacher){
                        bm.visbel = true;
                    }else {

                    }


                    ls.password.klikket = false;
                    userId = rsUser.getLong(3);
                    bm.dh = new DataHolder(0,0,userId, rsUsername);

                }
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }




}
