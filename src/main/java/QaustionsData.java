import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import processing.core.PApplet;
import processing.core.PVector;
import processing.data.Table;

public class QaustionsData {
    public boolean Answerd = false;
    public int points;
    public Table qaustion;

    QaustionsData(Table qaustion){
        this.qaustion = qaustion;
    }
}
//