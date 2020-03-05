import java.util.ArrayList;

public class Logowanie {


    private Uzytkownik aktualnieZalogowany;
    private int przyznaneId = 0;
    private ArrayList<Uzytkownik> listaUzytkownikow;
    private boolean czyZalogowano = false;
    public Logowanie() {
        listaUzytkownikow = new ArrayList<>(  );
    }

    public boolean isCzyZalogowano() {
        return czyZalogowano;
    }

    public void setCzyZalogowano(boolean czyZalogowano) {
        this.czyZalogowano = czyZalogowano;
    }

    public int getId_uzytkownika() {
        return przyznaneId;
    }

    public ArrayList<Uzytkownik> getListaUzytkownikow() {
        return listaUzytkownikow;
    }

    public Uzytkownik getAktualnieZalogowany() {
        return aktualnieZalogowany;
    }

    public int getPrzyznaneId() { return przyznaneId;}

    public String zaloguj(int id, String wprowadzoneHaslo) throws LogowanieException{
        if(id <= getListaUzytkownikow().size()){
            if(wprowadzoneHaslo.equals( listaUzytkownikow.get(id-1).getHaslo())){
                czyZalogowano = true;
                aktualnieZalogowany = listaUzytkownikow.get( id-1 );
                return "Zalogowano poprawnie";
            }
            else
                System.out.println( "Podaj poprawne haslo" );
                return "Podaj poprawne haslo!";
        }
        else
            throw new LogowanieException( "Nie ma takiego ID uzytkownika!" );

    }

    public void wyloguj (){
        aktualnieZalogowany = null;
        czyZalogowano = false;
    }

        // Rejestracja uzytkownika
    public void zarejestrujUzytkownika(String imie, String nazwisko, String haslo) throws ZarejestrujException{  // dodajemy uzytkownika którego id bedzie pozycja w arrayListach zawierajacych uzytkownikow i ich hasla
        if(imie.equals( "" ) || nazwisko.equals( "" ) || haslo.equals( "" )) {
            throw new ZarejestrujException( "Podaj wszystkie wymagane dane" );
        }
        else{
            przyznaneId++;
            Uzytkownik nowy = new Uzytkownik( imie, nazwisko, haslo );
            listaUzytkownikow.add( nowy );
            nowy.setIdUzytkownika( przyznaneId );
            System.out.println( "Przyznane id: " + przyznaneId + " jest twoim loginem" );
        }
    }
}
