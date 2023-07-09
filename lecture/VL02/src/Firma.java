import java.util.Timer;
import java.util.Scanner;
import java.util.LinkedList;

public class Firma {
    private String name;
    private LinkedList<Mitarbeiter> mitarbeiterLinkedList;
    private Drucker drucker;
    int mitarbeiterNummer;

    public Firma(String Name) {
        this.name = Name;
        this.mitarbeiterLinkedList = new LinkedList<Mitarbeiter>();
        this.drucker = new Drucker("Drucker");
        this.mitarbeiterNummer = 0;
    }

    public void einstellen (String Name) {
        this.mitarbeiterLinkedList.add( new Mitarbeiter(Name, ++this.mitarbeiterNummer));
    }

    private int getNumberFromString (String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void checkSeitenzahl (Scanner scanner, Mitarbeiter mitarbeiter){
        System.out.println("Wie viele Seiten sollen fuer " + mitarbeiter.getName() + " gedruckt werden (1-100)?");

        while (true) {
            String eingabe = scanner.next();
            int seitenZahl = getNumberFromString(eingabe);
            if ( (seitenZahl > 0) && (seitenZahl < 101) ) {
                System.out.println("Es werden " + seitenZahl + " Seiten in Auftrag gegeben.");
                System.out.println();
                this.drucker.DruckauftragEinreihen(new Druckauftrag(mitarbeiter.getName(), seitenZahl));
                return;
            } else {
                System.out.println("Ihre Eingabe: " + eingabe + " konnte nicht verarbeitet werden.");
                System.out.println("Bitte geben Sie eine Seitenzahl zwischen 1 und 100 ein");
            }
        }
    }

    private Mitarbeiter getMitarbeiterFromEingabe (String eingabe) {

        int nummer = getNumberFromString(eingabe);
        if ((nummer < 1) || (nummer > this.mitarbeiterNummer) ) {
            System.out.println("Eingegebene MitarbeiterNummer: " + eingabe + " ist ungueltig!");
            System.out.println("Geben Sie eine MitarbeiterNummer zwischen 1 und " + this.mitarbeiterNummer + " ein!");
            System.out.println();
            return null;
        } else {
            return this.mitarbeiterLinkedList.get(--nummer);
        }
    }

    public void Druckauftragssteuerung () {
        System.out.println();
        System.out.println("Wilkommen in der Druckauftragssteuerung der " + this.name + " Korporation");
        System.out.println("Sie koennen Druckauftraege im Namen der Mitarbeiter in Auftrag geben.");
        System.out.println("Haben Sie alle Auftraege eingegeben, koennen sie mit dem Drucken beginnen.");
        if (this.mitarbeiterNummer < 1) {
            System.out.println("Die Firma " + this.name + "hat noch keine angestellten!");
            System.out.println("Stellen Sie Mitarbeiter in ihrer Firma in der main() ein!");
            return;
        }
        System.out.println();
        System.out.println("######## Druckauftrag anlegen ########");
        System.out.println("Geben Sie die MitarbeiterNr ein (1.." + this.mitarbeiterNummer + ") und bestaetigen Sie mit Enter");
        System.out.println("Legen Sie anschliessend eine Seitenzahl zwischen 1 und 100 fest.");
        System.out.println("Bestaetigen Sie erneut mit Enter.");
        System.out.println("######################################");
        System.out.println();
        System.out.println("######### weitere Befehle #############");
        System.out.println("Sie verlassen die Druckauftragssteuerung mit (q)");
        System.out.println("Sie Drucken alle angelegten Auftraege mit (d)");
        System.out.println("Sie erhalten Infromationen ueber den Drucker mit (i)");
        System.out.println("######################################");
        System.out.println();
        Scanner scanner = new Scanner(System.in);

        long time0 = System.nanoTime();
        long time1;
        while (true){
            System.out.println("Mitarbeiter auswaehlen           (1.." + this.mitarbeiterNummer + ")");
            System.out.println("Alle Auftraege Drucken           (d)");
            System.out.println("Druckauftragssteuerung verlassen (q)");
            System.out.println("Drucker Informationen            (i)");
            System.out.println();

            String Eingabe = scanner.next();
            time1 = System.nanoTime();
            this.drucker.tageBisPruefdatum -= (int) ((time1 - time0) / 1000000000);
            time0 = time1;
            switch (Eingabe){
                case "q":
                    System.out.println("Sie verlassen die Druckauftragssteuerung");
                    return;
                case "i":
                    this.drucker.getInfo();
                    break;
                case "d":
                    System.out.println("\nStarte Druckvorgang!");
                    this.drucker.drucken();
                    break;
                default:
                    Mitarbeiter mitarbeiter = getMitarbeiterFromEingabe(Eingabe);
                    if (mitarbeiter != null) {
                        checkSeitenzahl(scanner, mitarbeiter);
                    }
            }
        }
    }

    public static void main(String [] args) {
        Firma firma = new Firma("Druckerei");

        firma.einstellen("Anton");
        firma.einstellen("Berta");
        firma.einstellen("Chris");

        firma.Druckauftragssteuerung();
    }
}
