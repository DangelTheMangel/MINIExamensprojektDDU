

# MINIExamensprojektDDU


## Introduction
Detter var et DDDU projekt
### Formål
Der skal laves et It-system som kan hjælpe en underviser med at se om eleverne har lært et pensum hen over et sat tidspunkt. Systemet skal give læreren et overblik over hver elevs før- og efter resultater i en klasse. Det faglige indhold må man selv bestemme. Der må gerne være brug af gamification, i form af point der skal belønne elever som klarer det godt. 

### Problemanalyse

Vores produkt sigter efter at løse den mundane hverdag som elever står over for, og gøre den en smule mere spændende, ved hjælp at et nyt og innovativt læringsværktøj.
Spænding er en vigtig faktor i at elever kan engagere sig med undervisning. Man lærer trods alt mere når man har det sjovt med det. Ifølge en undersøgelse af EVA, er et af elevernes hovedfokus er når de vil have et godt undervisningsmiljø, en god ro og koncentration.  [1]. Ifølge en anden undersøgelse fra Eva hvor en gruppe elever der siger at mindre støj giver et større læringsudbytte [2].Støj i undervisningen kan være et tegn på  disengagement, kedsomhed og afkobling fra undervisning, der ikke motiverer eleverne. [3].  Vi har med produktet tænkt os at tage spørgeskemaet som er lavet til at se hvordan elever ligger i forhold til det ønsket kernestof og gøre det mere spænde med gamification. Gamification er brugen af ​​elementer i spil i en anden aktivitet, normalt for at gøre den aktivitet mere interessant[4].  Vi valgte at tage inspiration fra tunebase battles hvilken plejer at have billede at man tænker sig om når man vælger sit angreb. Ved kombination af disse to kan vi camouflere en test i et spil. 

### Teknisk problemstilling
Vores målgruppe er elever i folkeskolens udskolingstrin, der har svært ved at lære af diverse grunde man finder i meget undervisning. Yderligere er lærere også på en måde vores målgruppe, da det er dem der skal benytte interfacen der skal bruges til at give feedback. Vi vil benytte os af et computerprogram med spilelementer, der skal spørge eleverne diverse spørgsmål, som de så kan få feedback på. Læreren kan også se disse spørgsmål, og derfor passe undervisningen til elevernes faglige niveau. Der er mange lignende produkter på markedet, men få er lavet med fokus på enkelthed og med denne målgruppe i fokus.
Problemformulering
I det danske skolesystem er det alt for svært for elever at blive vurderet i forhold til deres faglige niveau[5]. 


## Kodeord og Brugernavne
Brugernavn:Casper
Rolle: Elev
Kodeord:Cas123

Brugernavn:Albert
Rolle: Elev
Kodeord:Abe123

Brugernavn:Marius
Rolle: Lære
Kodeord:Sex6

##  tilføje bruger
### hvordan man tilføj bruger
Det er kun lærer der kan tilføje bruger. Man starter med at logge ind 
### coden der laver brugerne
``` Java
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

                String sql = "INSERT INTO user (username, password, teacher, fornavn, efternavn) VALUES ('"+userName+"', '"+ Main.getHash(password)+
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

```
