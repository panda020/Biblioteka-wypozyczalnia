import java.util.*;

public class Uzytkownik {

    private String imieUzytkownika;
    private String nazwiskoUzytkownika;
    private int idUzytkownika;
    private String haslo;
    private ArrayList<Ksiazka> listaWypozyczonych;
    private ArrayList<Czasopismo> listaWypozyczonychCzasopism;

    public Uzytkownik(String imieUzytkownika, String nazwiskoUzytkownika, String haslo ) {
        this.imieUzytkownika = imieUzytkownika;
        this.nazwiskoUzytkownika = nazwiskoUzytkownika;
        this.haslo = haslo;
        listaWypozyczonych = new ArrayList<>();
        listaWypozyczonychCzasopism = new ArrayList<>(  );
    }

    public ArrayList<Czasopismo> getListaWypozyczonychCzasopism() {
        return listaWypozyczonychCzasopism;
    }

    public ArrayList<Ksiazka> getListaWypozyczonych() {
        return listaWypozyczonych;
    }

    public String getImieUzytkownika() {
        return imieUzytkownika;
    }

    public String getNazwiskoUzytkownika() {
        return nazwiskoUzytkownika;
    }

    public int getIdUzytkownika() {
        return idUzytkownika;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setIdUzytkownika(int idUzytkownika) {
        this.idUzytkownika = idUzytkownika;
    }
}
