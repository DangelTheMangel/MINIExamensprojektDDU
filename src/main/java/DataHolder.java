public class DataHolder {
    public float svaretRigtigt, spurgt; //hvad skal den ellers have? Har tænkt over at man skal kunne se HVAD de svarer rigtigt på.
    Long elevID;
    String elevNavn;

    DataHolder(float sr, float sp, Long eid, String en){
        sr = svaretRigtigt;
        sp = spurgt;
        eid = elevID;
        elevNavn=en;
    }
}
