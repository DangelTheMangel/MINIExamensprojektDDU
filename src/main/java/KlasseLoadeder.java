
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


        for(int i = 0; i < 2; ++i ){
            ny.removeRow(i);
        }
        ny.removeRow(ny.getRowCount()-1);
        ny.removeRow(ny.getRowCount()-1);

        for(int j = 0; j< ny.getRowCount();++j){
            System.out.println(ny.getString(j,3));
        }

    }

    void addKlasseToDB(){
        Statement s = null;
        try{
            s = connection.createStatement();
            ResultSet rsUser = s.executeQuery("SELECT [username],[password], [Teacher], [userID] FROM [user]");
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
