import org.w3c.dom.Text;
import processing.core.PApplet;
import processing.data.StringList;
import processing.data.Table;

public class TeacherMenu {
    PApplet p;
    long userID;
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


    //forskellige menuer
    boolean menuVisible = true;
    boolean resultVisible = false;
    boolean editorVisible = false;
    boolean nyHoldVisible = false;
    TeacherMenu(PApplet p) {
        this.p = p;
        exit = new AlmindeligKnap(p, 50, 460, p.width-100, 100, "Luk Program");
        elevEn = new AlmindeligKnap(p,50,220,p.width/2-100,50,"1");
        elevTo = new AlmindeligKnap(p,50,320,p.width/2-100,50,"2");
        elevTre = new AlmindeligKnap(p,50,420,p.width/2-100,50,"3");
        nyElev = new AlmindeligKnap(p,50,520,p.width/2-100,50,"Ny elev");
        hold = new TextFlet(p,50,120,p.width/2-100,50,"Hold");
        removeSpergsmaal = new TextFlet(p,50,50,200,50,"Fjern Spørgsmål");
        addSpergsmall = new TextFlet(p,50,150,200,50,"Tilføj spørgsmål");
        addRigtigtSvar = new TextFlet(p,50,250,200,50,"Tilføj korrekt svar");
        addSvar2 = new TextFlet(p,50,350,200,50,"Tilføj svar mulighed");
        addSvar3 = new TextFlet(p,50,450,200,50,"Tilføj svar mulighed 2");
        backToMenu = new AlmindeligKnap(p,p.width/2 - 100,600,200,50,"back to menu");
        editorKnap = new AlmindeligKnap(p, 50, 100, p.width-100, 100, "Editor");
        resultKnap = new AlmindeligKnap(p, 50, 220, p.width-100, 100, "Resultater");
        nyHoldMenu = new AlmindeligKnap(p,50, 340, p.width-100, 100, "Hold instillinger");
        addSpergsmalK = new AlmindeligKnap(p,50,650,100,50,"Tilføj spørgsmål");
        indsaetNytHold = new AlmindeligKnap(p,50,220,p.width/2-100,50,"Tilføj nyt hold");
        holdNavn = new TextFlet(p,50,100,p.width/2-100,50,"Nyt holdnavn");

        sp = p.loadTable("sp.csv");
    }


    void drawMenu() {
        if(exit.klikket){
            p.exit();
        }

        if(nyHoldVisible){
            holdNavn.tegnTextFlet();
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
            menuVisible = false;
            resultVisible = false;
            editorVisible = false;
            nyHoldVisible = true;
            //p.selectInput("Select a file to process:", "fileSelected");

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

        if(indsaetNytHold.klikket){
            System.out.println("hold tilføjet");
            holdNavn.indput = "";
            indsaetNytHold.registrerRelease();
        }




    }

    void drawReMenu(){
        elevTre.tegnKnap();
        elevTo.tegnKnap();
        elevEn.tegnKnap();
        nyElev.tegnKnap();
        hold.tegnTextFlet();
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

        if(nyHoldVisible){
            holdNavn.keyindput(key);
        }


    }
    void clik(float mx, float my) {

        if(nyHoldVisible){
            backToMenu.registrerKlik(mx,my);
            indsaetNytHold.registrerKlik(mx,my);
            holdNavn.KlikTjek(mx,my);
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
            elevTo.registrerKlik(mx,my);
            elevTre.registrerKlik(mx,my);
            nyElev.registrerKlik(mx,my);
        }


    }

}
