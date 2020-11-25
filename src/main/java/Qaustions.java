import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import processing.data.StringList;
import processing.data.Table;

import java.util.ArrayList;
import java.util.Random;

public class Qaustions {
    PApplet p;
    Table questions;
    String questionsText;
    AlmindeligKnap btnAnswerOne, btnAnswerTwo, btnAnswerThree, btnNext ;
    boolean answerd = false , rightAnser;
    int points;
    boolean visibal = false;
    PImage enem;
    String[] answers = {"","",""};
    PVector rbg = new PVector(0,0,0);
    PImage card, player, Enemy, showHEadhog;
    float fly = 0;

    public Qaustions(PApplet p, Table questions, PImage enem) {
        this.p = p;
        this.enem = enem;
        this.questions = questions;
        points = 0;

       // ran = (int)p.random(1,questions.getRowCount());
        btnAnswerOne = new AlmindeligKnap(p,100,100,400,70, "1");
        btnAnswerTwo = new AlmindeligKnap(p,100,200,400,70, "2");
        btnAnswerThree = new AlmindeligKnap(p,100,300,400,70, "3");

        btnNext = new AlmindeligKnap(p,100,450,300,50, "next");
        rightAnser = false;
        newQustion();

    }

    public void battleVisual(){
        fly ++;
        int sin = (int) (10*p.sin((float) (fly* 0.09)));

        p.background(200,200,200,100);
        card = p.loadImage("Hurtig.png");

        erClikked(btnAnswerThree);
        erClikked(btnAnswerTwo);
        erClikked(btnAnswerOne);
        p.fill(rbg.x,rbg.y,rbg.z);
        p.text(questionsText,100,50);

        btnAnswerThree.tegnKort(card,true);
        btnAnswerTwo.tegnKort(card,true);
        btnAnswerOne.tegnKort(card,true);
        p.image(enem,p.width-500,90+sin, 400,400);

        if(answerd){
            btnNext.tegnKnap();
            if(btnNext.klikket){
                if(rightAnser){
                   points =points-1;
                }else {
                    points = points+1;
                }
                System.out.println(points);
                visibal= false;
                btnNext.registrerRelease();
            }
        }

    }

    void clicked(float mouseX, float mouseY){
        if(visibal) {
            btnAnswerOne.registrerKlik(mouseX, mouseY);
            btnAnswerTwo.registrerKlik(mouseX, mouseY);
            btnAnswerThree.registrerKlik(mouseX, mouseY);

        }

        if(answerd){
            btnNext.registrerKlik(mouseX,mouseY);
        }
    }

    void  erClikked(AlmindeligKnap btn){

        boolean klikket = btn.klikket;
        String questionTe = questions.getString(0,1);
        String btntext = btn.text;
        boolean equalsAnwsered = btntext.equalsIgnoreCase(questionTe);
        if(klikket && equalsAnwsered && !answerd){
            rbg = new PVector(0,200,0);
            btn.registrerRelease();
            answerd = true;
            rightAnser = true;

        }
        if(klikket && !equalsAnwsered&& !answerd){
            rbg = new PVector(200,0,0);
            btn.registrerRelease();
            answerd = true;
            rightAnser = false;

        }
    }

    void newQustion(){
        rbg = new PVector(0,0,0);
        questionsText = questions.getString(0,0);

        StringList sp = new StringList();
        for(int i = 2; i < answers.length +2 ; ++i){
            sp.append(questions.getString(0,i));

        }

        sp.shuffle();
        for(int i = 0; i < sp.size()
                ; ++i){
            answers[i] = sp.get(i);

        }
        btnAnswerOne.text = answers[0];
        btnAnswerTwo.text = answers[1];
        btnAnswerThree.text = answers[2];
  ;
    }

    int getPoints(){
        return points;
    }
}
