import org.w3c.dom.Text;
import processing.core.PApplet;
import processing.data.StringList;
import processing.data.Table;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class TeacherMenu {
    PApplet p;
    int resint;

    ArrayList<Elever> eleverList = new ArrayList<Elever>();
     long userID = Gokkenet.userId;
    StringList users;
    TextFlet removeSpergsmaal;
    TextFlet addSpergsmall;
    TextFlet addRigtigtSvar;
    TextFlet addSvar2;
    TextFlet addSvar3 , hold, holdNavn;
    Table sp;
    KlasseLoadeder klasseLoadeder = new KlasseLoadeder(p);
    boolean visibal = false; //christian visibel
    AlmindeligKnap editorKnap , backToMenu, nyElev;
    AlmindeligKnap resultKnap, nyHoldMenu, indsaetNytHold;
    AlmindeligKnap addSpergsmalK, elevEn,elevTo,elevTre, exit;
    Connection connection;

    //forskellige menuer
    boolean menuVisible = true;
    boolean resultVisible = false;
    boolean editorVisible = false;
    boolean nyHoldVisible = false;
    TeacherMenu(PApplet p, Connection connection) {
        this.p = p;
        this.connection = connection;
        exit = new AlmindeligKnap(p, 50, 460, p.width-100, 100, "Luk Program");

        hold = new TextFlet(p,50,120,p.width/2-100,50,"Hold");
        removeSpergsmaal = new TextFlet(p,50,50,200,50,"Fjern Spørgsmål");
        addSpergsmall = new TextFlet(p,50,150,200,50,"Tilføj spørgsmål");
        addRigtigtSvar = new TextFlet(p,50,250,200,50,"Tilføj korrekt svar");
        addSvar2 = new TextFlet(p,50,350,200,50,"Tilføj svar mulighed");
        addSvar3 = new TextFlet(p,50,450,200,50,"Tilføj svar mulighed 2");
        backToMenu = new AlmindeligKnap(p,p.width/2 - 100,600,200,50,"Tilbage til menu");
        editorKnap = new AlmindeligKnap(p, 50, 100, p.width-100, 100, "Spørgsmål laver");
        resultKnap = new AlmindeligKnap(p, 50, 220, p.width-100, 100, "Resultater");
        nyHoldMenu = new AlmindeligKnap(p,50, 340, p.width-100, 100, "Tilføj elever fra fil ...");
        addSpergsmalK = new AlmindeligKnap(p,50,650,100,50,"Tilføj spørgsmål");
        //indsaetNytHold = new AlmindeligKnap(p,50,220,p.width/2-100,50,"Tilføj nyt hold");
        //holdNavn = new TextFlet(p,50,100,p.width/2-100,50,"Nyt holdnavn");

        elevEn = new AlmindeligKnap(p,50,100,p.width-100,50,"Tilbage");
        /*elevTo = new AlmindeligKnap(p,50,320,p.width/2-100,50,"2");
        elevTre = new AlmindeligKnap(p,50,420,p.width/2-100,50,"3");*/
        nyElev = new AlmindeligKnap(p,50,520,p.width-100,50,"Frem");
        for(int i = 0; i<200; ++i){
            eleverList.add(new Elever("Navn" + Gokkenet.getHash("w" + i)));
            for(int j = 0; j < 8; j++){
                Date date = new Date();
                eleverList.get(i).addScore((int)p.random(1,100),date);
            }


        }
        sp = p.loadTable("sp.csv");
    }


    void drawMenu() {
        userID = Gokkenet.userId;
        if(exit.klikket){
            p.exit();
        }

        if(nyHoldVisible){
           // holdNavn.tegnTextFlet();
            indsaetNytHold.tegnKnap();
            backToMenu.tegnKnap();
        }
        if(resultVisible){
            backToMenu.tegnKnap();
            drawReMenu();
        }
        if(backToMenu.klikket){
            menuVisible = true;
            resultVisible = false;
            editorVisible = false;
            nyHoldVisible = false;
            backToMenu.registrerRelease();
        }
        if(editorKnap.klikket){
            menuVisible = false;
            resultVisible = false;
            editorVisible = true;
            nyHoldVisible = false;
            editorKnap.registrerRelease();
        }

        if(resultKnap.klikket){
            menuVisible = false;
            resultVisible = true;
            editorVisible = false;
            nyHoldVisible = false;
            resultKnap.registrerRelease();
        }

        if(nyHoldMenu.klikket){

            p.selectInput("Select a file to process:", "fileSelected");

            nyHoldMenu.registrerRelease();
        }



        for(int i=1; i<sp.getRowCount(); i++)
        if(removeSpergsmaal.indput.equals(sp.getString(i,0))){
            sp.removeRow(i);
            p.saveTable(sp,"sp.csv");

        }




        if (addSpergsmalK.klikket){

            sp.addRow();
            sp.setString(sp.getRowCount()-1,0,addSpergsmall.indput);
            sp.setString(sp.getRowCount()-1,1,addRigtigtSvar.indput);
            sp.setString(sp.getRowCount()-1,2,addRigtigtSvar.indput);
            sp.setString(sp.getRowCount()-1,3,addSvar2.indput);
            sp.setString(sp.getRowCount()-1,4,addSvar3.indput);
            p.saveTable(sp,"sp.csv");
            addSpergsmalK.registrerRelease();
        }



        if(menuVisible) {
            editorKnap.tegnKnap();
            resultKnap.tegnKnap();
            nyHoldMenu.tegnKnap();
            exit.tegnKnap();

        }

        if(editorVisible){
            backToMenu.tegnKnap();
            drawSPMenu();
        }




        if (resultKnap.klikket){
            visibal = false;
            resultVisible = true;
        }
        if (editorKnap.klikket){
            visibal = false;
            editorVisible = true;
        }





    }

    void drawReMenu(){
        elevEn.tegnKnap();
        nyElev.tegnKnap();
        if(resint < 0){
            resint = eleverList.size();
        }
        if(resint > eleverList.size()){
            resint = 0;
        }
        String text = getScore(resint) ;
        p.text(text, p.width/2-p.textWidth(text)/2,p.height/2-180);
        if(elevEn.klikket){
            resint --;
            elevEn.registrerRelease();
        }

        if(nyElev.klikket){
            resint ++;
            nyElev.registrerRelease();
        }

    }

    String getScore(int i) {
        Elever elev = eleverList.get(i);
        String k = elev.navn + ": \n";

        for(int j = 0; j < elev.scoreArrayList.size(); ++j) {
            k = k + elev.scoreArrayList.get(j).point + "Points " + elev.scoreArrayList.get(j).date + "\n";

        }
        return k;
    }
    void drawSPMenu(){
        removeSpergsmaal.tegnTextFlet();
        addSpergsmall.tegnTextFlet();
        addRigtigtSvar.tegnTextFlet();
        addSvar2.tegnTextFlet();
        addSvar3.tegnTextFlet();
        addSpergsmalK.tegnKnap();
    }

    void tryk(char key){
        if(editorVisible){
            removeSpergsmaal.keyindput(key);
            addSpergsmall.keyindput(key);
            addRigtigtSvar.keyindput(key);
            addSvar2.keyindput(key);
            addSvar3.keyindput(key);
        }

        if(resultVisible){
            hold.keyindput(key);
        }




    }
    void clik(float mx, float my) {

        if(nyHoldVisible){
            backToMenu.registrerKlik(mx,my);
            indsaetNytHold.registrerKlik(mx,my);
           // holdNavn.KlikTjek(mx,my);
        }
        if(menuVisible){
            exit.registrerKlik(mx,my);
            editorKnap.registrerKlik(p.mouseX,p.mouseY);
            System.out.println("mx: " + p.mouseX + " my: " + p.mouseY);
            resultKnap.registrerKlik(mx,my);
            nyHoldMenu.registrerKlik(mx,my);
        }

        if(editorVisible){
            backToMenu.registrerKlik(mx,my);
            addSpergsmalK.registrerKlik(p.mouseX,p.mouseY);

            if(editorKnap.klikket){
                System.out.println("fack");
            }

            removeSpergsmaal.KlikTjek(mx, my);
            addSpergsmall.KlikTjek(mx, my);
            addRigtigtSvar.KlikTjek(mx, my);
            addSvar2.KlikTjek(mx, my);
            addSvar3.KlikTjek(mx, my);
        }

        if(resultVisible){
            backToMenu.registrerKlik(mx,my);
            hold.KlikTjek(mx,my);
            elevEn.registrerKlik(mx,my);
          //  elevTo.registrerKlik(mx,my);
            //elevTre.registrerKlik(mx,my);
            nyElev.registrerKlik(mx,my);
        }


    }

    public void hold() {
        try {
            Statement statement = connection.createStatement();
            ResultSet rsMessages = statement.executeQuery("SELECT Hold.Navn, " );



        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

   /* void updateHold() {
        try {
            String sql = "INSERT INTO message (userId, threadId, message) VALUES (" + userId + "," + threadId + ",'" + msg + "')";
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }*/

}
