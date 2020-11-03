import jdk.javadoc.internal.doclets.formats.html.markup.Table;

public class QaustionsData {
    public boolean Answerd = false;
    public int points = 0;
    public Table qaustion;

    QaustionsData(Table qaustion){
        this.qaustion = qaustion;
    }
}
