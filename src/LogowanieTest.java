import org.junit.Test;

import static org.junit.Assert.*;

public class LogowanieTest {

    @Test
    public void zaloguj() {
        Biblioteka biblioteka = new Biblioteka();
        try {
            biblioteka.getLogowanie().zarejestrujUzytkownika("Anna", "Nowak", "123" );
        } catch (ZarejestrujException e) {
            //e.printStackTrace();
            System.out.println( "Podaj wszystkie wymagane dane" );
        }

        assertEquals( false, biblioteka.getLogowanie().isCzyZalogowano() );

        try {
            biblioteka.getLogowanie().zaloguj( 2, "123" );
        } catch (LogowanieException e) {
            System.out.println( "Nie ma takiego ID" );
        }
        assertEquals( false, biblioteka.getLogowanie().isCzyZalogowano() );

        try {
            biblioteka.getLogowanie().zaloguj( 1, "127" );
        } catch (LogowanieException e) {
            System.out.println( "Nie ma takiego ID" );
        }
        assertEquals( false, biblioteka.getLogowanie().isCzyZalogowano() );

        try {
            biblioteka.getLogowanie().zaloguj( 1,"123" );
        } catch (LogowanieException e) {
            System.out.println( "Nie ma takiego ID" );
        }

        assertEquals( true, biblioteka.getLogowanie().isCzyZalogowano() );
    }

    @Test
    public void wyloguj() {
        Biblioteka biblioteka = new Biblioteka();
        try {
            biblioteka.getLogowanie().zarejestrujUzytkownika("Anna", "Nowak", "123" );
        } catch (ZarejestrujException e) {
            //e.printStackTrace();
            System.out.println( "Podaj wszystkie wymagane dane" );
        }
        try {
            biblioteka.getLogowanie().zaloguj( 1,"123" );
        } catch (LogowanieException e) {
            System.out.println( "Nie ma takiego ID" );
        }

        assertEquals( true, biblioteka.getLogowanie().isCzyZalogowano() );

        biblioteka.getLogowanie().wyloguj();

        assertEquals( false, biblioteka.getLogowanie().isCzyZalogowano() );

    }

    @Test
    public void zarejestrujUzytkownika() {
        Biblioteka biblioteka = new Biblioteka();
        try {
            biblioteka.getLogowanie().zarejestrujUzytkownika("Anna", "", "123" );
        } catch (ZarejestrujException e) {
            //e.printStackTrace();
            System.out.println( "Podaj wszystkie wymagane dane" );
        }

        assertEquals( 0, biblioteka.getLogowanie().getPrzyznaneId() );

        try {
            biblioteka.getLogowanie().zarejestrujUzytkownika("Anna", "Nowak", "123" );
        } catch (ZarejestrujException e) {
            System.out.println( "Podaj wszystkie wymagane dane" );
        }

        assertEquals( 1, biblioteka.getLogowanie().getPrzyznaneId() );

    }
}