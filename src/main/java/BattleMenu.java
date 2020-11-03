import processing.core.PApplet;
import processing.data.Table;

import java.util.ArrayList;

public class BattleMenu {
    boolean visbel = false;
    float eLife=100, pLife=100;
    int firstQ = 0,middleQ= 1,lastQ = 2 , chosen;
    PApplet p;
    ArrayList<QaustionsData> spagersmal = new ArrayList<QaustionsData>();
    AlmindeligKnap QaustionOne, QaustionTwo, QaustionThree;
    Table qaustions;
    HealthBar EHealthBar, PHealthBar;
    Qaustions qs;
    BattleMenu(PApplet p,Table qaustions){
    this.p = p;
    this.qaustions = qaustions;
        QaustionOne= new AlmindeligKnap(p,350,500,200,220, "1");
        QaustionTwo = new AlmindeligKnap(p,550,500,200,220, "2");
        QaustionThree = new AlmindeligKnap(p,750,500,200,220, "3");
        EHealthBar = new HealthBar(p,50,100,eLife);
        PHealthBar = new HealthBar(p,1000,600,pLife);
        qs = new Qaustions(p,qaustions);
        makeQaustions();
        changeBtnName(QaustionOne, spagersmal.get(firstQ).qaustion.getString(0,0));
        changeBtnName(QaustionTwo, spagersmal.get(middleQ).qaustion.getString(0,0));
        changeBtnName(QaustionThree, spagersmal.get(lastQ).qaustion.getString(0,0));

    }

        void drawBattleMenu(){
          QaustionOne.tegnKnap();
          QaustionTwo.tegnKnap();
          QaustionThree.tegnKnap();
          EHealthBar.tegnHealthBar();
          PHealthBar.tegnHealthBar();

          registerClick(QaustionOne, firstQ);
          registerClick(QaustionTwo,middleQ);
          registerClick(QaustionThree,lastQ);

          if(qs.visibal){
              spagersmal.get(chosen).points = qs.getPoints();

              qs.battleVisual();

          }
            int sum = spagersmal.get(0).points + spagersmal.get(1).points + spagersmal.get(2).points;
            //System.out.println(sum);


        }
    void clicked(float mouseX, float mouseY) {
        if (visbel && !qs.visibal) {
            QaustionOne.registrerKlik(mouseX, mouseY);
            QaustionTwo.registrerKlik(mouseX, mouseY);
            QaustionThree.registrerKlik(mouseX, mouseY);
            p.println(QaustionOne.erKlikket());

        } else if(qs.visibal ){
            qs.clicked(mouseX,mouseY);
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

        void changeBtnName(AlmindeligKnap k, String title){
            k.text = title;
        }

        void registerClick(AlmindeligKnap k,int i){
            if(k.klikket){
                spagersmal.get(i).Answerd = true;
                chosen = i;
                qs = new Qaustions(p,spagersmal.get(i).qaustion);
                qs.visibal = true;
                k.registrerRelease();
            }
        }
    }
