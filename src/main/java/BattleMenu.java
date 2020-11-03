import processing.core.PApplet;
import processing.data.Table;

import java.util.ArrayList;

public class BattleMenu {
    boolean visbel = false;
    float eLife=100, pLife=100;
    PApplet p;
    ArrayList<QaustionsData> spagersmal = new ArrayList<QaustionsData>();
    AlmindeligKnap QaustionOne, QaustionTwo, QaustionThree;
    Table qaustions;
    HealthBar EHealthBar, PHealthBar;
    BattleMenu(PApplet p,Table qaustions){
    this.p = p;
    this.qaustions = qaustions;
        QaustionOne= new AlmindeligKnap(p,350,500,200,220, "1");
        QaustionTwo = new AlmindeligKnap(p,550,500,200,220, "2");
        QaustionThree = new AlmindeligKnap(p,750,500,200,220, "3");
        EHealthBar = new HealthBar(p,50,100,eLife);
        PHealthBar = new HealthBar(p,1000,600,pLife);
        makeQaustions();
    }

        void drawBattleMenu(){
          QaustionOne.tegnKnap();
          QaustionTwo.tegnKnap();
          QaustionThree.tegnKnap();
          EHealthBar.tegnHealthBar();
          PHealthBar.tegnHealthBar();
          //he


        }
    void clicked(float mouseX, float mouseY) {
        if (visbel) {
            QaustionOne.registrerKlik(mouseX, mouseY);
            QaustionTwo.registrerKlik(mouseX, mouseY);
            QaustionThree.registrerKlik(mouseX, mouseY);
            p.println(QaustionOne.erKlikket());
        }
    }

        void makeQaustions () {
            for (int i = 1; i < qaustions.getRowCount(); ++i) {
                Table data = new Table();
                data.addRow(qaustions.getRow(i));
                spagersmal.add(new QaustionsData(data));
                System.out.println(i);
            }
        }
    }
