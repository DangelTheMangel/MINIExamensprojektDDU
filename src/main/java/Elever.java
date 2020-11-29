import java.util.ArrayList;
import java.util.Date;

public class Elever {

    String navn;
    ArrayList<Score> scoreArrayList = new ArrayList<Score>();

    Elever(String navn){
        this.navn = navn;

    }

    void addScore(int point, Date date){
        scoreArrayList.add(new Score(point,date));

    }
}
