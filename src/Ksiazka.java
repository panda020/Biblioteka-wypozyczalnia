public class Ksiazka extends Komponent{
    private String imieAutora;
    private String nazwiskoAutora;
    private String tytul;
    private String gatunek;
    private String rok;
    private int licznikWypozyczen;

    //dobrz by było jakby id bylo przydzielane i dostepnosc moze sie ustawiac na true przy tworzeniu ksiazki
    public Ksiazka( String imieAutora, String nazwiskoAutora, String tytul, String gatunek, String rok) {
        this.imieAutora = imieAutora;
        this.nazwiskoAutora = nazwiskoAutora;
        this.tytul = tytul;
        this.gatunek = gatunek;
        this.rok = rok;
        licznikWypozyczen = 0;
    }

    public int getLicznikWypozyczen() {
        return licznikWypozyczen;
    }
    public String getImieAutora() {
        return imieAutora;
    }

    public String getNazwiskoAutora() {
        return nazwiskoAutora;
    }

    public String getTytul() {
        return tytul;
    }

    public String getGatunek() {
        return gatunek;
    }

    public String getRok() {
        return rok;
    }

    public void setLicznikWypozyczen(int licznikWypozyczen) {
        this.licznikWypozyczen = licznikWypozyczen;
    }
}
