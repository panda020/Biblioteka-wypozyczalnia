public class Main {

    public static void main(String[] args) {

        Biblioteka biblioteka = new Biblioteka();
        biblioteka.dodajKsiazkeDoListy(  "Adam", "Mickiewicz", "Pan Tadeusz", "poemat epicki, lektura", "1998" );
        biblioteka.dodajKsiazkeDoListy(  "Henryk", "Sienkiewicz", "W pustyni i w puszczy, lektura", "mlodziezowa", "2001" );
        biblioteka.dodajKsiazkeDoListy(  "Bolesław", "Prus", "Lalka", "powieść, lektura", "1995" );
        biblioteka.dodajKsiazkeDoListy(  "Suzanne", "Collins", "Igrzyska śmierci", "przygodowa", "2010" );


        //biblioteka.wyszukaj( "zbrodnia" );
        //biblioteka.wyszukaj( "ka" );

        System.out.println( "Ksiazki wyszukane:" );
        for (int i = 0; i < biblioteka.getListaWyszukanych().size(); i++) {
            System.out.println( biblioteka.getListaWyszukanych().get( i ).getId() + " " + biblioteka.getListaWyszukanych().get( i ).getImieAutora() + " " + biblioteka.getListaWyszukanych().get( i ).getNazwiskoAutora() + " {" + biblioteka.getListaWyszukanych().get( i ).getTytul() + "} " + biblioteka.getListaWyszukanych().get( i ).czyDostepna() );
        }

        Logowanie jakis = new Logowanie();
        Uzytkownik uzytkownik1 = new Uzytkownik( "ALeksandra", "Matuszek", "123" );
        //jakis.zarejestrujUzytkownika("Aleksandra", "Matuszek", "a123");
        //jakis.zarejestrujUzytkownika( "Anna", "Wroblewska", "123" );

        for(int i = 0; i<jakis.getListaUzytkownikow().size(); i++){
            System.out.println( jakis.getListaUzytkownikow().get( i ).getImieUzytkownika() );
        }
    }

}
