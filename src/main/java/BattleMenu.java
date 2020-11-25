import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.data.Table;

import java.util.ArrayList;

public class BattleMenu {
    PImage kort;
    PImage player, Enemy, showHEadhog;
    boolean visbel = false;
    float eLife=100, pLife=100;
    int firstQ = 0,middleQ= 1,lastQ = 2 , chosen;
    int oldsum;
    boolean logOutKnap = false;
    PApplet p;
    ArrayList<QaustionsData> spagersmal = new ArrayList<QaustionsData>();
    AlmindeligKnap QaustionOne, QaustionTwo, QaustionThree, logOut;
    Table qaustions;
    HealthBar EHealthBar, PHealthBar;
    LoginSide ls;
    Qaustions qs;
    DataHolder dh;
    float fly = 0;
    BattleMenu(PApplet p,Table qaustions){
        kort = p.loadImage("Kort.png");
        this.p = p;
        this.qaustions = qaustions;
        String[] en = {"div.png","e.png","x.png"};
        Enemy = p.loadImage(en[(int)p.random(0,en.length)]);
        QaustionOne= new AlmindeligKnap(p,330,400,224,448, "1");
        QaustionTwo = new AlmindeligKnap(p,550,400,224,448,"2");
        QaustionThree = new AlmindeligKnap(p,770,400,224,480, "3");
        logOut = new AlmindeligKnap(p,200,500,700,200, "Log out");
        qs = new Qaustions(p,qaustions, Enemy);
        makeQaustions();
        changeBtnName(QaustionOne, spagersmal.get(firstQ).qaustion.getString(0,0));
        changeBtnName(QaustionTwo, spagersmal.get(middleQ).qaustion.getString(0,0));
        changeBtnName(QaustionThree, spagersmal.get(lastQ).qaustion.getString(0,0));
        player = p.loadImage("fiske.png");

        showHEadhog = p.loadImage("fiskee.png");

    }

    void drawBattleMenu(){
        //System.out.println(dh.elevNavn);


        fly++;
        int sin = (int) (10*p.sin((float) (fly* 0.09)));
        p.image(player,0,p.height-320-110 + sin);
        p.image(showHEadhog,p.width-320 + sin,140, 160 + sin,150 );
        p.image(Enemy,p.width-320 ,80+sin, 150,150);
        EHealthBar = new HealthBar(p,p.width-160,60,eLife);
        EHealthBar.name = "Enemy Health";
        PHealthBar = new HealthBar(p,60,p.height-60,pLife);
        PHealthBar.name = "Player Health";
        QaustionOne.tegnKort(kort,false);
        QaustionTwo.tegnKort(kort,false);
        QaustionThree.tegnKort(kort,false);
        p.fill(200);
        p.rect(0,p.height-120,p.width,120);
        p.fill(0);
        p.textSize(32);
        p.text("Choose a card",p.width/2 -p.textWidth("Choose a card")/2,p.height-60);
        p.textSize(16);
        EHealthBar.tegnHealthBar();
        PHealthBar.tegnHealthBar();
        QaustionOne.positionY += sin/8;
        QaustionTwo.positionY += -sin/7;
        QaustionThree.positionY += sin/8;
        //p.println(lastQ);
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
            eLife-=25;
            firstQ =ifSomthingDoTHis(firstQ);
            middleQ =ifSomthingDoTHis(middleQ);
            lastQ = ifSomthingDoTHis(lastQ);
            changeBtnName(QaustionOne, spagersmal.get(firstQ).qaustion.getString(0,0));
            changeBtnName(QaustionTwo, spagersmal.get(middleQ).qaustion.getString(0,0));
            changeBtnName(QaustionThree, spagersmal.get(lastQ).qaustion.getString(0,0));

            //System.out.println("firstQ: " + firstQ + " middleQ: " + middleQ+ " lastQ: " + lastQ);
            qs.answerd = false;

            dh.svaretRigtigt++;
            dh.spurgt++;
        }

        if(qs.rightAnser ==false&& qs.answerd ==true && qs.visibal == false){
            pLife-=25;
            firstQ =ifSomthingDoTHis(firstQ);
            middleQ =ifSomthingDoTHis(middleQ);
            lastQ = ifSomthingDoTHis(lastQ);
            changeBtnName(QaustionOne, spagersmal.get(firstQ).qaustion.getString(0,0));
            changeBtnName(QaustionTwo, spagersmal.get(middleQ).qaustion.getString(0,0));
            changeBtnName(QaustionThree, spagersmal.get(lastQ).qaustion.getString(0,0));

            qs.answerd = false;
            dh.spurgt++;
        }

        p.println(qs.rightAnser);
        ifLifeIsZero(EHealthBar,true);
        ifLifeIsZero(PHealthBar, false);



    }
    void clicked(float mouseX, float mouseY) {
        if (visbel && !qs.visibal) {
            QaustionOne.registrerKlik(mouseX, mouseY);
            QaustionTwo.registrerKlik(mouseX, mouseY);
            QaustionThree.registrerKlik(mouseX, mouseY);
            p.println(QaustionOne.erKlikket());
            /*if(logOutKnap) {
                logOut.registrerKlik(mouseX, mouseY);
            }*/
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
            qs = new Qaustions(p,spagersmal.get(i).qaustion, Enemy);
            qs.visibal = true;
            k.registrerRelease();

        }
    }

    int ifSomthingDoTHis(int i){
         /*   if(i<qaustions.getRowCount()){
                i =0;

            }else{
                i++;
            }*/
        i++;


        return i;
    }

    void ifLifeIsZero(HealthBar h,boolean enemy){
        if(h.health <= 0){
            logOutKnap = true;
            p.clear();                          //vi laver et clear for at lave en sort skærm. Det kunne være fedt med en baggrund.
            p.fill(255,255,255);                //den her og de 2 under laver teksten som den skal være.
            p.textAlign(PConstants.CENTER);

            if(logOut.erKlikket()){
                visbel = false;

            }
            p.textSize(64);
            if(enemy == true)
                p.text(dh.elevNavn + " fik " + dh.svaretRigtigt + " point."+ "\n og " + dh.elevNavn +" vandt",p.width/2,p.height/2); //og det her er teksten
            if (enemy == false)
                p.text(dh.elevNavn + " fik " + dh.svaretRigtigt + " point."+ "\n og " +
                        "udforderen" +" vandt",p.width/2,p.height/2); //og det her er teksten

        }

        /* logOut.tegnKnap();*/
    }
}

