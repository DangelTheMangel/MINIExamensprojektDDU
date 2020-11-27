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
    TextFlet addSvar3;
    Table sp;
    boolean visibal = false; //christian visibel
    AlmindeligKnap editorKnap;
    AlmindeligKnap resultKnap;
    AlmindeligKnap addSpergsmalK;
    AlmindeligKnap addRigtigtSvarK;
    AlmindeligKnap addSvar2K;
    AlmindeligKnap addSvar3K;
    //forskellige menuer
    boolean menuVisible = true;
    boolean resultVisible = false;
    boolean editorVisible = false;
    TeacherMenu(PApplet p) {
        this.p = p;

        removeSpergsmaal = new TextFlet(p,50,50,200,50,"Fjern Spørgsmål");
        addSpergsmall = new TextFlet(p,50,150,200,50,"Tilføj spørgsmål");
        addRigtigtSvar = new TextFlet(p,50,250,200,50,"Tilføj rigtigt svar");
        addSvar2 = new TextFlet(p,50,350,200,50,"tilføj svarmulighed");
        addSvar3 = new TextFlet(p,50,450,200,50,"tilføj svarmulgihed");
        editorKnap = new AlmindeligKnap(p, 600, 100, 100, 100, "Editor");
        resultKnap = new AlmindeligKnap(p, 600, 200, 100, 100, "Resultater");
        addSpergsmalK = new AlmindeligKnap(p,400,150,50,50,"Add");
        addRigtigtSvarK = new AlmindeligKnap(p,400,250,50,50,"Add");
        addSvar2K = new AlmindeligKnap(p,400,350,50,50,"Add");
        addSvar3K = new AlmindeligKnap(p,400,450,50,50,"Add");
        sp = p.loadTable("sp.csv");
    }
    void drawMenu() {
        removeSpergsmaal.tegnTextFlet();
        addSpergsmall.tegnTextFlet();
        addRigtigtSvar.tegnTextFlet();
        addSvar2.tegnTextFlet();
        addSvar3.tegnTextFlet();
        addSpergsmalK.tegnKnap();
        addRigtigtSvarK.tegnKnap();
        addSvar2K.tegnKnap();
        addSvar3K.tegnKnap();

        p.text("Velkommen" + users.get((int) userID ), 100,100);
        for(int i=1; i<sp.getRowCount(); i++)
        if(removeSpergsmaal.indput.equals(sp.getString(i,0))){
            sp.removeRow(i);
            p.saveTable(sp,"sp.csv");

        }
        boolean c = sp.getString(sp.getRowCount()-1, 0).equals("");

        if (addSpergsmalK.klikket &&
                !sp.getString(sp.getRowCount()-1, 0).equals("")&&
                !sp.getString(sp.getRowCount()-1, 1).equals("")&&
                !sp.getString(sp.getRowCount()-1, 2).equals("")&&
                !sp.getString(sp.getRowCount()-1, 3).equals("")&&
                !sp.getString(sp.getRowCount()-1, 4).equals("")) {

            sp.addRow();
            sp.setString(sp.getRowCount(),0,addSpergsmall.indput);
            p.saveTable(sp,"sp.csv");
            addSpergsmalK.registrerRelease();
        }
        if(addRigtigtSvarK.klikket){
            sp.setString(sp.getRowCount(),1,addRigtigtSvar.indput);
            sp.setString(sp.getRowCount(),2,addRigtigtSvar.indput);
            p.saveTable(sp,"sp.csv");
            addRigtigtSvarK.registrerRelease();
        }
        if(addSvar2K.klikket){
            sp.setString(sp.getRowCount(),3,addSvar2.indput);
            p.saveTable(sp,"sp.csv");
            addSvar2K.registrerRelease();
        }
        if(addSvar3K.klikket){
            sp.setString(sp.getRowCount(),4,addSvar3.indput);
            p.saveTable(sp,"sp.csv");
            addSvar3K.registrerRelease();
        }


        if(menuVisible) {
            editorKnap.tegnKnap();
            editorKnap.registrerKlik(200, 100);
            resultKnap.tegnKnap();
            resultKnap.registrerKlik(200, 100);
        }

        if (resultKnap.klikket){
            System.out.print("skrt");
            visibal = false;
            resultVisible = true;
        }
        if (editorKnap.klikket){
            visibal = false;
            editorVisible = true;
        }


        if(resultVisible){
            TextFlet t = new TextFlet(p,p.width/2,p.height/2,100,100,"Skiv en elevs navn");
            System.out.print("bruh");
        }

    }
    void tryk(char key){
        removeSpergsmaal.keyindput(key);
        addSpergsmall.keyindput(key);
        addRigtigtSvar.keyindput(key);
        addSvar2.keyindput(key);
        addSvar3.keyindput(key);

    }
    void clik(float mx, float my) {
        addSpergsmalK.registrerKlik(p.mouseX,p.mouseY);
        addRigtigtSvarK.registrerKlik(p.mouseX,p.mouseY);
        addSvar2K.registrerKlik(p.mouseX, p.mouseY);
        addSvar3K.registrerKlik(p.mouseX,p.mouseY);

        removeSpergsmaal.KlikTjek(mx, my);
        addSpergsmall.KlikTjek(mx, my);
        addRigtigtSvar.KlikTjek(mx, my);
        addSvar2.KlikTjek(mx, my);
        addSvar3.KlikTjek(mx, my);

    }

}
