import processing.core.PApplet;
import processing.data.Table;

import java.util.ArrayList;

public class BattleMenu {
    boolean visbel = false;
    float eLife=100, pLife=100;
    int firstQ = 0,middleQ= 1,lastQ = 2 , chosen;
    int oldsum;
    PApplet p;
    ArrayList<QaustionsData> spagersmal = new ArrayList<QaustionsData>();
    AlmindeligKnap QaustionOne, QaustionTwo, QaustionThree;
    Table qaustions;
    HealthBar EHealthBar, PHealthBar;
    Qaustions qs;
    DataHolder dh;
    BattleMenu(PApplet p,Table qaustions){
    this.p = p;
    this.qaustions = qaustions;
        QaustionOne= new AlmindeligKnap(p,350,500,200,220, "1");
        QaustionTwo = new AlmindeligKnap(p,550,500,200,220, "2");
        QaustionThree = new AlmindeligKnap(p,750,500,200,220, "3");
        qs = new Qaustions(p,qaustions);
        makeQaustions();
        changeBtnName(QaustionOne, spagersmal.get(firstQ).qaustion.getString(0,0));
        changeBtnName(QaustionTwo, spagersmal.get(middleQ).qaustion.getString(0,0));
        changeBtnName(QaustionThree, spagersmal.get(lastQ).qaustion.getString(0,0));


    }

        void drawBattleMenu(){
            System.out.println(dh.elevNavn);
            EHealthBar = new HealthBar(p,50,100,eLife);
            PHealthBar = new HealthBar(p,1000,600,pLife);
          QaustionOne.tegnKnap();
          QaustionTwo.tegnKnap();
          QaustionThree.tegnKnap();
          EHealthBar.tegnHealthBar();
          PHealthBar.tegnHealthBar();

          registerClick(QaustionOne, firstQ);
          registerClick(QaustionTwo,middleQ);
          registerClick(QaustionThree,lastQ);

          if(qs.visibal  ){
              if(qs.answerd) {

                  for(int i = 0; i < spagersmal.size(); ++i)
                      System.out.println(spagersmal.get(i).qaustion.getString(0,0)+ " " + spagersmal.get(i).points + " \n");
              }

              qs.battleVisual();

          }
            if(qs.rightAnser ==true&& qs.answerd ==true && qs.visibal == false){
                eLife-=50;
                firstQ =ifSomthingDoTHis(firstQ);
                middleQ =ifSomthingDoTHis(middleQ);
                lastQ = ifSomthingDoTHis(lastQ);
                changeBtnName(QaustionOne, spagersmal.get(firstQ).qaustion.getString(0,0));
                changeBtnName(QaustionTwo, spagersmal.get(middleQ).qaustion.getString(0,0));
                changeBtnName(QaustionThree, spagersmal.get(lastQ).qaustion.getString(0,0));

                System.out.println("firstQ: " + firstQ + " middleQ: " + middleQ+ " lastQ: " + lastQ);
                qs.answerd = false;


            }

            if(qs.rightAnser ==false&& qs.answerd ==true && qs.visibal == false){
                pLife-=50;
                firstQ =ifSomthingDoTHis(firstQ);
                middleQ =ifSomthingDoTHis(middleQ);
                lastQ = ifSomthingDoTHis(lastQ);
                changeBtnName(QaustionOne, spagersmal.get(firstQ).qaustion.getString(0,0));
                changeBtnName(QaustionTwo, spagersmal.get(middleQ).qaustion.getString(0,0));
                changeBtnName(QaustionThree, spagersmal.get(lastQ).qaustion.getString(0,0));

                System.out.println("firstQ: " + firstQ + " middleQ: " + middleQ+ " lastQ: " + lastQ);
                qs.answerd = false;


            }

            p.println(qs.rightAnser);
            ifLifeIsZero(EHealthBar);
            ifLifeIsZero(PHealthBar);



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

       int ifSomthingDoTHis(int i){
           /* if(i+1 <qaustions.getRowCount()){
                i =0;
            }else{
                i++;
            }*/
           i++;


            return i;
        }

        void ifLifeIsZero(HealthBar h){
            if(h.health <= 0){
                p.text(dh.elevNavn + " fik " + dh.svaretRigtigt + " point.",p.width/2,p.height/2);
            }
        }
    }
