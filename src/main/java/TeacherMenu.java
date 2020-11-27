import org.w3c.dom.Text;
import processing.core.PApplet;
import processing.data.StringList;

public class TeacherMenu {
    PApplet p;
    long userID;
    StringList users;
    boolean visibal = false;
    AlmindeligKnap editorKnap;
    AlmindeligKnap resultKnap;

    TeacherMenu(PApplet p){
        this.p =p;
        editorKnap = new AlmindeligKnap(p, 200, 100, 100, 100, "Editor");
        resultKnap = new AlmindeligKnap(p, 200, 200, 100, 100, "Resultater");
    }

    void drawMenu(){
        System.out.println(userID);
        //p.text("Velkommen" + users.get((int) userID ), 100,100);
        editorKnap.tegnKnap();
        editorKnap.registrerKlik(200,100);
        resultKnap.tegnKnap();
        resultKnap.registrerKlik(200,100);

    }

}
