import java.util.ArrayList;

public class Biblioteka{
    private ArrayList<Ksiazka> listaKsiazek;
    private ArrayList<Ksiazka> listaWyszukanych;
    private ArrayList<Czasopismo> listaCzasopism;
    private ArrayList<Czasopismo> listaWyszukanychCzasopism;
    private Ksiazka najczesciejWypozyczanaKsiazka;
    private Logowanie logowanie = new Logowanie();
    private int przyznaneIdKsiazki = 1;
    private int przyznaneIdCzasopisma = 1;

    public ArrayList<Czasopismo> getListaCzasopism() {
        return listaCzasopism;
    }

    public ArrayList<Czasopismo> getListaWyszukanychCzasopism() {
        return listaWyszukanychCzasopism;
    }

    public ArrayList<Ksiazka> getListaWyszukanych() {
        return listaWyszukanych;
    }

    public ArrayList<Ksiazka> getListaKsiazek() {
        return listaKsiazek;
    }

    public Logowanie getLogowanie() {
        return logowanie;
    }

    public Biblioteka() {
        listaKsiazek = new ArrayList<>();
        listaWyszukanych = new ArrayList<>();
        listaCzasopism = new ArrayList<>(  );
        listaWyszukanychCzasopism = new ArrayList<>(  );
    }

    public void wypozycz(int nrKsiazki) throws WypozyczException{
        if(logowanie.getAktualnieZalogowany() != null) {
            Ksiazka wypozyczana = listaKsiazek.get( nrKsiazki - 1 );
            if (wypozyczana.getDostepnosc()) {
                logowanie.getAktualnieZalogowany().getListaWypozyczonych().add( wypozyczana );
                listaKsiazek.get( nrKsiazki - 1 ).dostepnosc = false;
                wypozyczana.setLicznikWypozyczen( wypozyczana.getLicznikWypozyczen()+1 );
            }
            else {
                System.out.println( "Ksiazka niedostepna" );
            }
        }
        else
            throw new WypozyczException("Nie jestes zalogowany");
    }
    public void wypozyczCzasopismo(int idCzasopisma) throws WypozyczException{
        if(logowanie.getAktualnieZalogowany() != null) {
            Czasopismo wypozyczane = listaCzasopism.get( idCzasopisma - 1 );
            if (wypozyczane.getDostepnosc()) {
                logowanie.getAktualnieZalogowany().getListaWypozyczonychCzasopism().add(wypozyczane);
                listaCzasopism.get( idCzasopisma - 1 ).dostepnosc = false;
            }
            else {
                System.out.println( "Ksiazka niedostepna" );
            }
        }
        else
            throw new WypozyczException("Nie jestes zalogowany");
    }

    public void oddaj(int nrKsiazki) {
        Ksiazka wypozyczana = listaKsiazek.get(nrKsiazki-1);
        if(wypozyczana.getDostepnosc() == false){
            logowanie.getAktualnieZalogowany().getListaWypozyczonych().remove( wypozyczana );
            listaKsiazek.get( nrKsiazki-1).dostepnosc = true;
        }
        else
            System.out.println("Ksiazka nie jest wyporzyczona przez ciebie");
    }

    public void oddajCzasopismo(int idCzasopisma){
        Czasopismo oddawane = listaCzasopism.get( idCzasopisma-1);
        if(oddawane.getDostepnosc() == false){
            logowanie.getAktualnieZalogowany().getListaWypozyczonychCzasopism().remove( oddawane );
            listaCzasopism.get( idCzasopisma-1).dostepnosc = true;
        }
        else
            System.out.println("Nie masz takiego czasopisma na liscie wypozyczonych");
    }



    //metoda dodajaca ksiazke do listy ksiazek

    public void dodajKsiazkeDoListy ( String imie_autora, String nazwisko_autora, String tytul, String gatunek, String rok ){
        Ksiazka nowaKsiazka = new Ksiazka( imie_autora, nazwisko_autora, tytul, gatunek, rok );
        listaKsiazek.add( nowaKsiazka );
        nowaKsiazka.setId( przyznaneIdKsiazki );
        przyznaneIdKsiazki++;
    }
    public void dodajCzasopismoDoListy(String nazwa, String numer){
        Czasopismo noweCzasopismo = new Czasopismo( nazwa, numer );
        listaCzasopism.add( noweCzasopismo );
        noweCzasopismo.setId( przyznaneIdCzasopisma );
        przyznaneIdCzasopisma++;
    }

    //metoda ktora po podaniu jakiegos tekstu wyszukuje ksiazke która zawiera podany tekst w tytule imieniu lub nazwisku autora
    public void wyszukaj(String tekst) throws WyszukajException{
        if(!tekst.equals( "" )) {
            boolean czyJestTakaKsiazka;
            czyJestTakaKsiazka = false;
            listaWyszukanych.clear();

            for (int i = 0; i < listaKsiazek.size(); i++) {
                if (listaKsiazek.get( i ).getTytul().contains( tekst ) || listaKsiazek.get( i ).getImieAutora().contains( tekst ) || listaKsiazek.get( i ).getNazwiskoAutora().contains( tekst )) {
                    System.out.println( listaKsiazek.get( i ).getId() + " " + listaKsiazek.get( i ).getImieAutora() + " " + listaKsiazek.get( i ).getNazwiskoAutora() + " {" + listaKsiazek.get( i ).getTytul() + "} " + listaKsiazek.get( i ).czyDostepna() );
                    listaWyszukanych.add( listaKsiazek.get( i ) );
                    czyJestTakaKsiazka = true;
                }
            }
            if (czyJestTakaKsiazka == false) {
                System.out.println( "szukanej ksiazki nie ma w bibliotece" );  // wiem, ze w tym miejscu to nie moze byc
            }
        }
        else
            throw new WyszukajException( "Wpisz tekst do wyszukania" );

    }

    public void wyszukajCzasopismo(String tekst) throws WyszukajException{
        if(!tekst.equals( "" )) {
            boolean czyJestTakieCzasopismo;
            czyJestTakieCzasopismo = false;
            listaWyszukanych.clear();

            for (int i = 0; i < listaCzasopism.size(); i++) {
                if (listaCzasopism.get( i ).getNazwa().contains(tekst)){
                    System.out.println( listaCzasopism.get( i ).getId() + " " + listaCzasopism.get( i ).getNazwa()+" "+ listaCzasopism.get( i ).czyDostepna());
                    listaWyszukanychCzasopism.add( listaCzasopism.get( i ) );
                    czyJestTakieCzasopismo = true;
                }
            }
            if (czyJestTakieCzasopismo == false) {
                System.out.println( "szukanego czasopisma nie ma w bibliotece" );
            }
        }
        else
            throw new WyszukajException( "Wpisz tekst do wyszukania" );

    }

    // wyswietla liste ksiazek: id imie nazwisko autora tytul, info czy wypozyczona nie wiem czy to sie przyda
    public void wyswietlListeKsiazek() {
        System.out.println( "Ksiazki w naszej bibliotece:" );
        for (int i = 0; i < listaKsiazek.size(); i++) {
            System.out.println( listaKsiazek.get(i).getId() + " " + listaKsiazek.get(i).getImieAutora() + " " + listaKsiazek.get(i).getNazwiskoAutora() + " {" +  listaKsiazek.get(i).getTytul() + "} " + listaKsiazek.get(i).czyDostepna());
        }
    }

    public Ksiazka najczesciejWypozyczana(){
        najczesciejWypozyczanaKsiazka = listaKsiazek.get( 0 );
        for (int i =1; i<listaKsiazek.size(); i++){
            if (listaKsiazek.get(i).getLicznikWypozyczen()>najczesciejWypozyczanaKsiazka.getLicznikWypozyczen()){
                najczesciejWypozyczanaKsiazka = listaKsiazek.get(i);
            }
        }
        return najczesciejWypozyczanaKsiazka;
    }

}
