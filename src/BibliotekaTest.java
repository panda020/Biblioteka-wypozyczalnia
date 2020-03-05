import org.junit.Test;

import static org.junit.Assert.*;

public class BibliotekaTest {

    Biblioteka biblioteka = new Biblioteka();

    @Test
    public void wypozycz() {

        biblioteka.dodajKsiazkeDoListy( "Adam", "Mickiewicz", "Pan Tadeusz", "lektura", "1998" );
        try {
            biblioteka.getLogowanie().zarejestrujUzytkownika( "Anna", "Wroblewska", "123" );
        } catch (ZarejestrujException e) {
            e.printStackTrace();
        }

        Ksiazka ksiazka = biblioteka.getListaKsiazek().get(0);


        assertEquals( true, ksiazka.getDostepnosc() );

        try {
            biblioteka.wypozycz(1);
        } catch (WypozyczException e) {
            //e.printStackTrace();
            System.out.println( "Nie jestes zalogowany!!" );
        }
        assertEquals( true, ksiazka.getDostepnosc() );

        try {
            biblioteka.getLogowanie().zaloguj( 1, "123" );
        } catch (LogowanieException e) {
           // e.printStackTrace();
        }

        try {
            biblioteka.wypozycz(1);
        } catch (WypozyczException e) {
            //e.printStackTrace();
            System.out.println( "Nie jestes zalogowany!!" );
        }


        assertEquals( false, ksiazka.getDostepnosc());

    }

    @Test
    public void oddaj() {

        biblioteka.dodajKsiazkeDoListy( "Adam", "Mickiewicz", "Pan Tadeusz", "lektura", "1998" );

        try {
            biblioteka.getLogowanie().zarejestrujUzytkownika( "Anna", "Wroblewska", "123" );
        } catch (ZarejestrujException e) {
            e.printStackTrace();
        }

        Ksiazka ksiazka = biblioteka.getListaKsiazek().get(0);

        assertEquals( true, ksiazka.getDostepnosc() );

        biblioteka.oddaj(1);

        assertEquals(true, ksiazka.getDostepnosc());

        try {
            biblioteka.getLogowanie().zaloguj( 1, "123" );
        } catch (LogowanieException e) {
            // e.printStackTrace();
        }

        try {
            biblioteka.wypozycz(1);
        } catch (WypozyczException e) {
            //e.printStackTrace();
            System.out.println( "Nie jestes zalogowany!!" );
        }

        assertEquals(false, ksiazka.getDostepnosc());

        biblioteka.oddaj(1);

        assertEquals(true, ksiazka.getDostepnosc());




    }

    @Test
    public void dodajKsiazkeDoListy() {

        assertEquals(0, biblioteka.getListaKsiazek().size());

        biblioteka.dodajKsiazkeDoListy( "Adam", "Mickiewicz", "Pan Tadeusz", "lektura", "1998" );

        assertEquals(1, biblioteka.getListaKsiazek().size());

        assertEquals("Adam", biblioteka.getListaKsiazek().get(0).getImieAutora());
        assertEquals("Mickiewicz", biblioteka.getListaKsiazek().get(0).getNazwiskoAutora());
        assertEquals("Pan Tadeusz", biblioteka.getListaKsiazek().get(0).getTytul());
        assertEquals(1, biblioteka.getListaKsiazek().get(0).getId());

        biblioteka.dodajKsiazkeDoListy(  "Henryk", "Sienkiewicz", "W pustyni i w puszczy, lektura", "mlodziezowa", "2001" );

        assertEquals(2, biblioteka.getListaKsiazek().size());

        assertEquals(2, biblioteka.getListaKsiazek().get(1).getId());




    }

    @Test
    public void wyszukaj() {
        assertEquals(0, biblioteka.getListaWyszukanych().size() );


        biblioteka.dodajKsiazkeDoListy(  "Adam", "Mickiewicz", "Pan Tadeusz", "poemat epicki, lektura", "1998" );
        biblioteka.dodajKsiazkeDoListy(  "Henryk", "Sienkiewicz", "W pustyni i w puszczy, lektura", "mlodziezowa", "2001" );
        biblioteka.dodajKsiazkeDoListy(  "Bolesław", "Prus", "Lalka", "powieść, lektura", "1995" );
        biblioteka.dodajKsiazkeDoListy(  "Suzanne", "Collins", "Igrzyska śmierci", "przygodowa", "2010" );
        biblioteka.dodajKsiazkeDoListy(  "Adam", "Mickiewicz", "Pan Tadeusz", "poemat epicki, lektura", "1998" );
        biblioteka.dodajKsiazkeDoListy(  "Henryk", "Sienkiewicz", "W pustyni i w puszczy, lektura", "mlodziezowa", "2001" );
        biblioteka.dodajKsiazkeDoListy(  "Bolesław", "Prus", "Lalka", "powieść, lektura", "1995" );
        biblioteka.dodajKsiazkeDoListy(  "Suzanne", "Collins", "Igrzyska śmierci", "przygodowa", "2010" );
        biblioteka.dodajKsiazkeDoListy(  "Adam", "Mickiewicz", "Pan Tadeusz", "poemat epicki, lektura", "1998" );
        biblioteka.dodajKsiazkeDoListy(  "Henryk", "Sienkiewicz", "W pustyni i w puszczy, lektura", "mlodziezowa", "2001" );
        biblioteka.dodajKsiazkeDoListy(  "Bolesław", "Prus", "Lalka", "powieść, lektura", "1995" );
        biblioteka.dodajKsiazkeDoListy(  "Suzanne", "Collins", "Igrzyska śmierci", "przygodowa", "2010" );

        try {
            biblioteka.wyszukaj( "" );
        } catch (WyszukajException e) {
            //e.printStackTrace();
            System.out.println( "Nic nie wpisano!!" );
        }

        assertEquals(0, biblioteka.getListaWyszukanych().size() );

        try {
            biblioteka.wyszukaj( "Adam" );
        } catch (WyszukajException e) {
            //e.printStackTrace();
            System.out.println( "Nic nie wpisano!!" );
        }

        assertEquals(3, biblioteka.getListaWyszukanych().size() );

        try {
            biblioteka.wyszukaj( "kkkkkk" );
        } catch (WyszukajException e) {
            //e.printStackTrace();
            System.out.println( "Nic nie wpisano!!" );
        }
        assertEquals(0, biblioteka.getListaWyszukanych().size() );
    }

    @Test
    public void wyswietlListeKsiazek() {

        biblioteka.dodajKsiazkeDoListy(  "Adam", "Mickiewicz", "Pan Tadeusz", "poemat epicki, lektura", "1998" );
        biblioteka.dodajKsiazkeDoListy(  "Henryk", "Sienkiewicz", "W pustyni i w puszczy, lektura", "mlodziezowa", "2001" );
        biblioteka.dodajKsiazkeDoListy(  "Bolesław", "Prus", "Lalka", "powieść, lektura", "1995" );
        biblioteka.dodajKsiazkeDoListy(  "Suzanne", "Collins", "Igrzyska śmierci", "przygodowa", "2010" );
        biblioteka.dodajKsiazkeDoListy(  "Adam", "Mickiewicz", "Pan Tadeusz", "poemat epicki, lektura", "1998" );
        biblioteka.dodajKsiazkeDoListy(  "Henryk", "Sienkiewicz", "W pustyni i w puszczy, lektura", "mlodziezowa", "2001" );
        biblioteka.dodajKsiazkeDoListy(  "Bolesław", "Prus", "Lalka", "powieść, lektura", "1995" );
        biblioteka.dodajKsiazkeDoListy(  "Suzanne", "Collins", "Igrzyska śmierci", "przygodowa", "2010" );
        biblioteka.dodajKsiazkeDoListy(  "Adam", "Mickiewicz", "Pan Tadeusz", "poemat epicki, lektura", "1998" );
        biblioteka.dodajKsiazkeDoListy(  "Henryk", "Sienkiewicz", "W pustyni i w puszczy, lektura", "mlodziezowa", "2001" );
        biblioteka.dodajKsiazkeDoListy(  "Bolesław", "Prus", "Lalka", "powieść, lektura", "1995" );
        biblioteka.dodajKsiazkeDoListy(  "Suzanne", "Collins", "Igrzyska śmierci", "przygodowa", "2010" );

        biblioteka.wyswietlListeKsiazek();

        assertEquals(12, biblioteka.getListaKsiazek().size() );
    }

}