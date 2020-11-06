import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import processing.data.StringList;
import processing.data.Table;

import java.util.Random;

public class Qaustions {
    PApplet p;
    Table questions;
    String questionsText;
    AlmindeligKnap btnAnswerOne, btnAnswerTwo, btnAnswerThree, btnAnswerFour, btnNext ;
    boolean answerd = false , rightAnser;
    int points;
    boolean visibal = false;
    String[] answers = {"","","",""};
    PVector rbg = new PVector(0,0,0);
    PImage card;

    public Qaustions(PApplet p, Table questions) {
        this.p = p;
        this.questions = questions;
        points = 0;
       // ran = (int)p.random(1,questions.getRowCount());
        btnAnswerOne = new AlmindeligKnap(p,100,100,300,50, "1");
        btnAnswerTwo = new AlmindeligKnap(p,100,200,300,50, "2");
        btnAnswerThree = new AlmindeligKnap(p,100,300,300,50, "3");
        btnAnswerFour = new AlmindeligKnap(p,100,400,300,50, "4");
        btnNext = new AlmindeligKnap(p,100,450,300,50, "next");
        rightAnser = false;
        newQustion();

    }

    public void battleVisual(){
        p.background(200,200,200,100);
        card = p.loadImage("baggrund.png");
        erClikked(btnAnswerFour);
        erClikked(btnAnswerThree);
        erClikked(btnAnswerTwo);
        erClikked(btnAnswerOne);
        p.fill(rbg.x,rbg.y,rbg.z);
        p.text(questionsText,100,50);
        btnAnswerFour.tegnKort(card);
        btnAnswerThree.tegnKort(card);
        btnAnswerTwo.tegnKort(card);
        btnAnswerOne.tegnKort(card);

        if(answerd){
            btnNext.tegnKnap();
            if(btnNext.klikket){
                if(rightAnser){
                    points = -10;
                }else {
                    points = 10;
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
            btnAnswerFour.registrerKlik(mouseX, mouseY);
        }

        if(answerd){
            btnNext.registrerKlik(mouseX,mouseY);
        }
    }

    void  erClikked(AlmindeligKnap btn){
        if(btn.klikket && btn.text.equals(questionsText = questions.getString(0,1)) && !answerd){
            rbg = new PVector(0,200,0);
            btn.registrerRelease();
            answerd = true;
            rightAnser = false;
        } else if(btn.klikket && !btn.text.equals(questionsText = questions.getString(0,1))&& !answerd){
            rbg = new PVector(200,0,0);
            btn.registrerRelease();
            answerd = true;
            rightAnser = true;

        }
    }

    void newQustion(){
        rbg = new PVector(0,0,0);
        questionsText = questions.getString(0,0);

        StringList sp = new StringList();
        for(int i = 1; i < answers.length + 1 ; ++i){
            sp.append(questions.getString(0,i));

        }

        sp.shuffle();
        for(int i = 0; i < answers.length  ; ++i){
            answers[i] = sp.get(i);

        }
        btnAnswerOne.text = answers[0];
        btnAnswerTwo.text = answers[1];
        btnAnswerThree.text = answers[2];
        btnAnswerFour.text = answers[3];
    }

    int getPoints(){
        return points;
    }
}
