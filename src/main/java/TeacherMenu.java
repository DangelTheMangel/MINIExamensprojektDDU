import org.w3c.dom.Text;
import processing.core.PApplet;
import processing.data.StringList;

public class TeacherMenu {
    PApplet p;
    long userID;
    StringList users;
    boolean visibal = false;

    TeacherMenu(PApplet p){
        this.p =p;
    }

    void drawMenu(){
        System.out.println(userID);
        p.text("Velkommen" + users.get((int) userID ), 100,100);
    }

}
