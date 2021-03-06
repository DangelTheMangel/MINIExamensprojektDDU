
import processing.core.PApplet;
import processing.core.PImage;
import processing.data.StringList;
import processing.data.Table;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main extends PApplet {

    LoginSide ls;
   // String testUser = "Albert" , testPassword = "Abe123";
   String testUser = " Marius" , testPassword = " Sex6";
    StringList users = new StringList();
    public static long userId;
    BattleMenu bm;
    PImage bg;
    Table questions;
    TeacherMenu tm;

    private String databaseURL = "jdbc:ucanaccess://src//main//java//resources//database.accdb";
    private Connection connection = null;

    public static void main(String[] args) {
        PApplet.main("Main");
    }

    public Main() {
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

    public void fileSelected(File selection) {
        if (selection == null) {
            println("Window was closed or the user hit cancel.");
        } else {
            try{
                Table ny= loadTable(selection.getPath());
                tm.klasseLoadeder.filetoklass(ny);
            } catch (NullPointerException g){
                println("Fejl ved at loade filen der ligger ved" + selection.getPath());
            }

        }
    }

    @Override
    public void setup() {
        questions = loadTable("sp.csv");
        ls = new LoginSide(this);
        bm = new BattleMenu(this, questions , connection);
        bg = loadImage("bg.png");
        tm = new TeacherMenu(this, connection);
    }

    @Override
    public void draw() {

        clear();

        background(bg);
        if(ls.visible){
            ls.drawSide();

        }
        if(bm.visbel){
            bm.drawBattleMenu();
        }
        if(tm.visibal){
            tm.drawMenu();

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
        if(tm.visibal){
            tm.tryk(key);
        }



    }

    @Override
    public void mouseClicked() {
        if (ls.visible) {
            ls.clik(mouseX, mouseY);
        }
        if(tm.visibal){
            tm.clik(mouseX,mouseY);
        }
        if(bm.visbel){
            bm.clicked(mouseX,mouseY);

        }
    }
    public static String getHash(String passwordToHash){

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

                users.append(rsUsername);

                System.out.println(rsUsername);
                System.out.println(rsPassword);
                System.out.println(rsRoleIsTeacher +"\n");

                if (ls.userName.indput.equals(rsUsername) && getHash(ls.password.indput).equals(rsPassword)
                        /*|| testUser.equals(rsUsername) && getHash(testPassword).equals(rsPassword)*/) {
                    userId = rsUser.getLong(4);
                    bm.dh = new DataHolder(0,0,userId, rsUsername);
                    ls.visible = false;
                    if(!rsRoleIsTeacher){
                        bm.visbel = true;
                    }
                    if(rsRoleIsTeacher){
                        tm.userID = userId;
                        tm.users=users;
                        tm.visibal= true;
                    }


                    ls.password.klikket = false;


                }
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }




}
