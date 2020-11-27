
import processing.core.PApplet;
import processing.data.Table;

import java.io.File;

public class KlasseLoadeder {
    PApplet p;
    Table klasse = new Table();

    KlasseLoadeder(PApplet p){
        this.p = p;
    }

    void loadklasse(String filePath){
        klasse = p.loadTable(filePath);
    }



    void checkclasse(){
        System.out.println("shit der står ... ");
        for(int i = 0; i < klasse.getRowCount(); ++i){
            System.out.println(klasse.getString(i,3));
        }
    }

}
