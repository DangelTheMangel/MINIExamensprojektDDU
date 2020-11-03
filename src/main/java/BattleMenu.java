import processing.core.PApplet;
import processing.data.Table;

import java.util.ArrayList;

public class BattleMenu {
    PApplet p;
    ArrayList<QaustionsData> spagersmal = new ArrayList<QaustionsData>();
    AlmindeligKnap QaustionOne, QaustionTwo, QaustionThree;
    Table qaustions;

    BattleMenu(PApplet p,Table qaustions){
    this.p = p;
    this.qaustions = qaustions;
        QaustionOne= new AlmindeligKnap(p,350,500,200,220, "1");
        QaustionTwo = new AlmindeligKnap(p,550,500,200,220, "2");
        QaustionThree = new AlmindeligKnap(p,750,500,200,220, "3");
        makeQaustions();
    }

        void drawBattleMenu(){
          QaustionOne.tegnKnap();
          QaustionTwo.tegnKnap();
          QaustionThree.tegnKnap();

        }



    void makeQaustions(){
        for(int i = 1 ; i<qaustions.getRowCount(); ++i){
            Table data = new Table();
            data.addRow(qaustions.getRow(i));
            spagersmal.add( new QaustionsData(data));
            System.out.println(i);
        }
    }
}
