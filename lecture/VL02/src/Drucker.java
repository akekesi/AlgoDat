import java.util.LinkedList;
import java.util.Queue;

public class Drucker extends Elektrogeraet{
    private String netzwerkname;
    private Queue<Druckauftrag> druckauftraege;
    private int tinte;

    public Drucker(String netzwerkname) {
        this.netzwerkname = netzwerkname;
        this.druckauftraege = new LinkedList<>();
        this.tinte = 100;
        this.tageBisPruefdatum = 365;
    }

    public int getTinte() {
        return this.tinte;
    }

    public void DruckauftragEinreihen(Druckauftrag druckauftrag) {
        this.druckauftraege.add(druckauftrag);
    }

    public void fill() {
        System.out.println("Tinte auffuellen");
        this.tinte = 100;
    }

    private void warten(int Sekunden) {
        try {
            for (int i = 0; i < 20; i++){
                Thread.sleep(Sekunden * 50);
                System.out.print(".");
            }
            System.out.println();

        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    public void getInfo() {
        System.out.println("Netzwerkname:         " + this.netzwerkname);
        System.out.println("Tinte:                " + this.tinte + "%");
        System.out.println("Anstehende Auftraege: " + this.druckauftraege);
        System.out.println("Tage bis Pruefdatum:  " + this.tageBisPruefdatum);
    }

    public void drucken() {
        if (tageBisPruefdatum <= 0) {
            this.pruefen();
            this.warten(3);
        }
        Druckauftrag druckauftrag;
        while (this.druckauftraege.size() > 0) {
            druckauftrag = this.druckauftraege.poll();
            if (this.tinte < druckauftrag.getSeitenzahl()) {
                this.fill();
                this.warten(Math.max(3, (int) (6 * (100 - this.tinte) / 100)));
            }
            druckauftrag.report();
            this.warten((int) (5 * druckauftrag.getSeitenzahl() / 100));
            this.tinte -= druckauftrag.getSeitenzahl();
        }
    }
}
