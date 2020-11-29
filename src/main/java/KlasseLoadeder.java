
import processing.core.PApplet;
import processing.data.Table;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class KlasseLoadeder {
    PApplet p;
    Table klasse = new Table();
    Connection connection;
    KlasseLoadeder(PApplet p, Connection connection){

        this.p = p;
        this.connection = connection;
    }

    void filetoklass(Table ny){
        for (int i = 0; i < 3; ++i ) {
            ny.removeRow(0);
        }
        ny.removeRow(ny.getRowCount()-1);
        ny.removeRow(ny.getRowCount()-1);
       /* for(int j = 0; j< ny.getRowCount();++j){
            if(ny.getString(j,1).equals("Lærer")){
                ny.removeRow(j);
                j--;
            }
        }*/
        for(int j = 0; j< ny.getRowCount();++j){

            System.out.println(ny.getString(j,3) + " " + ny.getString(j,1));
        }
        klasse = ny;

        addKlasseToDB();

    }

    void addKlasseToDB() {
        Statement s = null;
        try{
            s = connection.createStatement();
            boolean virkede = false;
            for (int i = 0; i< klasse.getRowCount(); ++i){
                String allFirstnames = klasse.getString(i,3);
                String[] words = allFirstnames.split(" ");
                String fornavn = words[0];
                String efternavn = klasse.getString(i,4);
                String userName = klasse.getString(i,4);
                String password = efternavn+42;
                boolean teacher =  klasse.getString(i,1).equals("Lærer");

                String sql = "INSERT INTO user (username, password, teacher, fornavn, efternavn) VALUES ('"+userName+"', '"+Gokkenet.getHash(password)+
                        "', "+ teacher +", '"+fornavn+"', '"+efternavn+"');";
                System.out.println(sql);
                boolean success = s.execute(sql);
                sql = "INSERT INTO personhold (personid, holdid) select userid, 1 from user where fornavn='"+fornavn+"' AND efternavn='+"+efternavn+"+';";
                success = s.execute(sql);
            }
            System.out.println("Elver indsat = " +  virkede);

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    void checkclasse(){
        System.out.println("shit der står ... ");
        for(int i = 0; i < klasse.getRowCount(); ++i){
            System.out.println(klasse.getString(i,3));
        }
    }

}
