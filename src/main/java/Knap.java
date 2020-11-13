import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public abstract class Knap {
    //variabler
    float positionX, positionY, sizeX, sizeY;
    float mouseX, mouseY;
    String text;
    boolean klikket = false;

    PApplet p;

    Knap(PApplet papp, int posX, int posY, int sizeX, int sizeY, String text ) {

        p = papp;
        positionX = posX;
        positionY = posY;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.text = text;

    }

    void klik() {
        if (p.mousePressed &&
                mouseX > positionX &&
                mouseX < positionX + sizeX &&
                mouseY > positionY &&
                mouseY < positionY + sizeY) {
        }
    }

    void setTekst(String tekst) {
        p.fill(0);

        p.text(tekst, positionX +(sizeX/16), positionY + (sizeY/2));

    }

    void tegnKnap() {
        p.stroke(1, 46, 74);
        p.noFill();
        if(klikket){
            p.fill(200);
        } else{
            p.fill(100);
        }

        p.rect(positionX, positionY, sizeX, sizeY);
        setTekst(text);
    }

    void tegnKort(PImage img) {
        p.stroke(1, 46, 74);
        p.noFill();
        p.image(img, positionX, positionY);
        setTekst(text);
    }

    boolean erKlikket() {
        return klikket;
    }

    abstract void registrerKlik(float mouseX, float mouseY);
}

