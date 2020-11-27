import org.w3c.dom.Text;
import processing.core.PApplet;
import processing.data.StringList;

public class TeacherMenu {
    PApplet p;
    long userID;
    StringList users;
    TextFlet removeSpergsmaal;
    TextFlet addSpergsmall;
    TextFlet addRigtigtSvar;
    TextFlet addSvar2;
    TextFlet addSvar3;
    boolean visibal = false; //christian visibel
    AlmindeligKnap editorKnap;
    AlmindeligKnap resultKnap;
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
        editorKnap = new AlmindeligKnap(p, 400, 100, 100, 100, "Editor");
        resultKnap = new AlmindeligKnap(p, 400, 200, 100, 100, "Resultater");
    }
    void drawMenu() {
        removeSpergsmaal.tegnTextFlet();
        addSpergsmall.tegnTextFlet();
        addRigtigtSvar.tegnTextFlet();
        addSvar2.tegnTextFlet();
        addSvar3.tegnTextFlet();
        p.text("Velkommen" + users.get((int) userID ), 100,100);
        if(menuVisible) {
            editorKnap.tegnKnap();
            editorKnap.registrerKlik(200, 100);
            resultKnap.tegnKnap();
            resultKnap.registrerKlik(200, 100);
        }

        if (resultKnap.klikket){
            visibal = false;
            resultVisible = true;
        }
        if (editorKnap.klikket){
            visibal = false;
            editorVisible = true;
        }


        if(resultVisible){
            System.out.println();
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

        removeSpergsmaal.KlikTjek(mx, my);
        addSpergsmall.KlikTjek(mx, my);
        addRigtigtSvar.KlikTjek(mx, my);
        addSvar2.KlikTjek(mx, my);
        addSvar3.KlikTjek(mx, my);

    }

}
