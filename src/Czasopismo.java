public class Czasopismo extends Komponent {
    private String nazwa;
    private String numer;

    public Czasopismo(String nazwa, String numer) {
        this.nazwa = nazwa;
        this.numer = numer;
    }

    public String getNazwa() {
        return nazwa;
    }

    public String getNumer() {
        return numer;
    }
}
