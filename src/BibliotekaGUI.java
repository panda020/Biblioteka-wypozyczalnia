import java.util.ArrayList;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class BibliotekaGUI {
    private Biblioteka biblioteka;
    private Uzytkownik uzytkownik;
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JTabbedPane tabbedPane2;
    private JTextField wyszukiwarkaKsiazek;
    private JButton szukajButton;
    private JList list1;
    private JButton najczesciejWypozyczaneButton;
    private JButton wszystkieKsiazkiButton;
    private JTextField ksiazkaDoWypozyczenia;
    private JButton wypozycz;
    private JTextField imieZalogowanego;
    private JTextField idZalogowanego;
    private JTextField nazwiskoZalogowanego;
    private JList profilLista;
    private JButton wylogujButton;
    private JTextField ksiazkaDoOddania;
    private JButton oddajButton;
    private JButton zalogujButton;
    private JTextField idZaloguj;
    private JTextField imieZarejestruj;
    private JTextField nazwiskoZarejestruj;
    private JButton zarejestrujUzytkownikaButton;
    private JPasswordField hasloZarejestruj;
    private JPasswordField hasloZaloguj;
    private JScrollPane scrollPane;
    private JTextField wyszukajCzasopismo;
    private JButton wyszukajButton;
    private JList listaCzasopisma;
    private JTextField czasopismoDoWypozyczenia;
    private JButton wypozyczCzasopismo;
    private JButton listaWszystkichCzasopismButton;
    private JTextField czasopismoDoOddania;
    private JButton oddajCzasopismoButton;
    private JList profilListaCzasopism;


    DefaultListModel listaWyswietlajacaWypozyczone = new DefaultListModel();
    DefaultListModel listaCzasopismaWypozyczone = new DefaultListModel();

    private void uaktualnijWypozyczone() {
        listaWyswietlajacaWypozyczone.clear();
        listaCzasopismaWypozyczone.clear();
        Uzytkownik user = biblioteka.getLogowanie().getAktualnieZalogowany();
        if (user != null) {
            imieZalogowanego.setText( user.getImieUzytkownika() );
            nazwiskoZalogowanego.setText( user.getNazwiskoUzytkownika() );
            idZalogowanego.setText( String.valueOf( user.getIdUzytkownika() ) );
            for (int i = 0; i < biblioteka.getLogowanie().getAktualnieZalogowany().getListaWypozyczonych().size(); i++) {
                Ksiazka wyswietlaj = user.getListaWypozyczonych().get( i );
                listaWyswietlajacaWypozyczone.addElement( wyswietlaj.getId() + " " + wyswietlaj.getImieAutora() + " " + wyswietlaj.getNazwiskoAutora() + " {" + wyswietlaj.getTytul() + "} " );
            }
            for (int i = 0; i < biblioteka.getLogowanie().getAktualnieZalogowany().getListaWypozyczonychCzasopism().size(); i++) {
                Czasopismo wyswietlaj = user.getListaWypozyczonychCzasopism().get( i );
                listaCzasopismaWypozyczone.addElement( wyswietlaj.getId() + " {" + wyswietlaj.getNazwa()+"} " + wyswietlaj.getNumer() );
            }
            if (listaWyswietlajacaWypozyczone.isEmpty()) {
                listaWyswietlajacaWypozyczone.addElement( "Brak wypozyczonych" );
            }
            if (listaCzasopismaWypozyczone.isEmpty())
                listaCzasopismaWypozyczone.addElement( "Brak wypozyczonych czasopism" );
            profilLista.setModel( listaWyswietlajacaWypozyczone );
            profilListaCzasopism.setModel( listaCzasopismaWypozyczone );
        }
    }
    private void wyczyscPola() {
        listaWyswietlajacaWypozyczone.clear();
        imieZalogowanego.setText( "" );
        nazwiskoZalogowanego.setText( "" );
        idZalogowanego.setText( "" );
        listaCzasopismaWypozyczone.clear();

    }
    DefaultListModel listaWyswietlajacaWyszukane = new DefaultListModel();
    private void wyswietlaj() {
        for (int i = 0; i < biblioteka.getListaWyszukanych().size(); i++) {
            Ksiazka wyswietl = biblioteka.getListaWyszukanych().get( i );
            listaWyswietlajacaWyszukane.addElement( wyswietl.getId() + " " + wyswietl.getImieAutora() + " " + wyswietl.getNazwiskoAutora() + " {" + wyswietl.getTytul() + "} " + wyswietl.czyDostepna() );
        }
        if (listaWyswietlajacaWyszukane.isEmpty()) {
            listaWyswietlajacaWyszukane.addElement( "Nie ma takiej ksiazki w bibliotece" );
        }
        list1.setModel( listaWyswietlajacaWyszukane );
    }
    DefaultListModel listaWyswietlajacaWyszukaneCzasopisma = new DefaultListModel();
    private void wyswietlajCzasopisma() {
        for (int i = 0; i < biblioteka.getListaWyszukanychCzasopism().size(); i++) {
            Czasopismo wyswietl = biblioteka.getListaCzasopism().get( i );
            listaWyswietlajacaWyszukaneCzasopisma.addElement( wyswietl.getId() + " {" + wyswietl.getNazwa() + "} " + wyswietl.getNumer() + " " + wyswietl.czyDostepna() );
        }
        if (listaWyswietlajacaWyszukaneCzasopisma.isEmpty()) {
            listaWyswietlajacaWyszukaneCzasopisma.addElement( "Nie ma takiego czasopisma w bibliotece" );
        }
        listaCzasopisma.setModel( listaWyswietlajacaWyszukaneCzasopisma );
    }


    public BibliotekaGUI(Biblioteka b) {
        biblioteka = b;
//Zakladka wyszukaj ksiazki
        szukajButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listaWyswietlajacaWyszukane.clear();
                try {
                    b.wyszukaj( wyszukiwarkaKsiazek.getText() );
                    wyswietlaj();
                } catch (WyszukajException e1) {
                    listaWyswietlajacaWyszukane.addElement( "Wpisz tekst do wyszukania" );
                }
                wyszukiwarkaKsiazek.setText("");


            }
        } );

        wszystkieKsiazkiButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                listaWyswietlajacaWyszukane.clear();
                wyszukiwarkaKsiazek.setText( "" );
                for (int i = 0; i < biblioteka.getListaKsiazek().size(); i++) {
                    Ksiazka wyswietlaj = biblioteka.getListaKsiazek().get( i );
                    listaWyswietlajacaWyszukane.addElement( wyswietlaj.getId() + " " + wyswietlaj.getImieAutora() + " " + wyswietlaj.getNazwiskoAutora() + " {" + wyswietlaj.getTytul() + "} " + wyswietlaj.czyDostepna() );
                }
                list1.setModel( listaWyswietlajacaWyszukane );

            }
        } );

        list1.addMouseListener( new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked( e );
                String klikniety = list1.getSelectedValue().toString();
                ksiazkaDoWypozyczenia.setText( klikniety );
            }
        } );

        wypozycz.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ksiazkaDoWypozyczenia.getText().equals( "" ))
                    JOptionPane.showMessageDialog( null, "Zaznacz ksiazke, ktora chcesz wypozyczyc" );
                else {
                    String tekst = ksiazkaDoWypozyczenia.getText();
                    String[] parts = tekst.split( " " );
                    String part1 = parts[0];
                    int idWypozyczanej = Integer.parseInt( part1 );
                    try {
                        Ksiazka wypozyczana = biblioteka.getListaKsiazek().get( idWypozyczanej - 1 );
                        if (wypozyczana.getDostepnosc() == true) {
                            biblioteka.wypozycz( idWypozyczanej );
                            JOptionPane.showMessageDialog( null, "Wypozyczyłes ksiazke " + wypozyczana.getTytul() );
                            wszystkieKsiazkiButton.doClick();
                        } else
                            JOptionPane.showMessageDialog( null, "Ksiazka jest niedostepna" );
                    } catch (WypozyczException e1) {
                        JOptionPane.showMessageDialog( null, "Aby wypożyczać musisz być zalogowany!" );
                    }
                    uaktualnijWypozyczone();
                    ksiazkaDoWypozyczenia.setText( "" );
                }
            }
        } );

        najczesciejWypozyczaneButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listaWyswietlajacaWyszukane.clear();
                Ksiazka ksiazka = biblioteka.najczesciejWypozyczana();
                listaWyswietlajacaWyszukane.addElement( ksiazka.getId() + " " + ksiazka.getImieAutora() + " " + ksiazka.getNazwiskoAutora() + " {" + ksiazka.getTytul() +"} " + ksiazka.czyDostepna());
            }
        } );
//Zakladka wyszukaj czasopisma
        wyszukajButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listaWyswietlajacaWyszukaneCzasopisma.clear();
                try {
                    b.wyszukajCzasopismo( wyszukajCzasopismo.getText() );
                    wyswietlajCzasopisma();
                } catch (WyszukajException e1) {
                    listaWyswietlajacaWyszukaneCzasopisma.addElement( "Wpisz tekst do wyszukania" );
                }
                wyszukajCzasopismo.setText("");


            }
        } );
        wypozyczCzasopismo.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (czasopismoDoWypozyczenia.getText().equals( "" ))
                    JOptionPane.showMessageDialog( null, "Zaznacz czasopismo, ktora chcesz wypozyczyc" );
                else {
                    String tekst = czasopismoDoWypozyczenia.getText();
                    String[] parts = tekst.split( " " );
                    String part3 = parts[0];
                    int idWypozyczanego = Integer.parseInt( part3 );
                    try {
                        Czasopismo wypozyczane = biblioteka.getListaCzasopism().get( idWypozyczanego - 1 );
                        if (wypozyczane.getDostepnosc() == true) {
                            biblioteka.wypozyczCzasopismo( idWypozyczanego );
                            listaWszystkichCzasopismButton.doClick();
                            JOptionPane.showMessageDialog( null, "wypozyczyles czasopismo " + wypozyczane.getNazwa() );
                        } else
                            JOptionPane.showMessageDialog( null, "Czasopismo niedostepne" );
                    } catch (WypozyczException e1) {
                        JOptionPane.showMessageDialog( null, "Aby wypożyczać musisz być zalogowany!" );
                    }
                    uaktualnijWypozyczone();
                    czasopismoDoWypozyczenia.setText( "" );
                }
            }
        } );

        listaCzasopisma.addMouseListener( new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked( e );
                String klikniete = listaCzasopisma.getSelectedValue().toString();
                czasopismoDoWypozyczenia.setText( klikniete );
            }
        } );

        listaWszystkichCzasopismButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                listaWyswietlajacaWyszukaneCzasopisma.clear();
                wyszukajCzasopismo.setText( "" );
                for (int i = 0; i < biblioteka.getListaCzasopism().size(); i++) {
                    Czasopismo wyswietlaj = biblioteka.getListaCzasopism().get( i );
                    listaWyswietlajacaWyszukaneCzasopisma.addElement( wyswietlaj.getId() + " {" + wyswietlaj.getNazwa() + "} " + wyswietlaj.getNumer() + " " + wyswietlaj.czyDostepna() );
                }
                listaCzasopisma.setModel( listaWyswietlajacaWyszukaneCzasopisma );

            }
        } );

// Zakladka profil

        wylogujButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b.getLogowanie().wyloguj();
                wyczyscPola();
            }
        } );
        profilLista.addMouseListener( new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked( e );
                String klikniety = profilLista.getSelectedValue().toString();
                ksiazkaDoOddania.setText( klikniety );
            }
        } );

        oddajButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ksiazkaDoOddania.getText().equals( "" ))
                    JOptionPane.showMessageDialog( null, "Zaznacz ksiazke, ktora chcesz oddac" );
                else {
                    String tekst = ksiazkaDoOddania.getText();
                    String[] parts = tekst.split( " " );
                    String part2 = parts[0];
                    int idOddawanej = Integer.parseInt( part2 );
                    biblioteka.oddaj( idOddawanej );
                    uaktualnijWypozyczone();
                    ksiazkaDoOddania.setText( "" );
                    wszystkieKsiazkiButton.doClick();
                }
            }
        } );
        oddajCzasopismoButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (czasopismoDoOddania.getText().equals( "" ))
                    JOptionPane.showMessageDialog( null, "Zaznacz czasopismo, ktora chcesz oddac" );
                else {
                    String tekst = czasopismoDoOddania.getText();
                    String[] parts = tekst.split( " " );
                    String part4 = parts[0];
                    int idOddawanego = Integer.parseInt( part4 );
                    biblioteka.oddajCzasopismo( idOddawanego );
                    uaktualnijWypozyczone();
                    czasopismoDoOddania.setText( "" );
                    listaWszystkichCzasopismButton.doClick();
                }
            }
        } );
        profilListaCzasopism.addMouseListener( new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked( e );
                String klikniete = profilListaCzasopism.getSelectedValue().toString();
                czasopismoDoOddania.setText( klikniete );
            }
        } );

// Zakladka Zaloguj

        zalogujButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println( (Integer.parseInt( idZaloguj.getText() )) );

                try {
                    b.getLogowanie().zaloguj( Integer.parseInt( idZaloguj.getText() ), String.valueOf( hasloZaloguj.getPassword() ) );
                    if (b.getLogowanie().isCzyZalogowano() == true) {
                        JOptionPane.showMessageDialog( null, "Zalogowano poprawnie" );
                    } else
                        JOptionPane.showMessageDialog( null, "Podane haslo jest nieprawidlowe" );
                } catch (LogowanieException e1) {
                    JOptionPane.showMessageDialog( null, "Nie ma uzytkownika o podanym ID" );
                }
                uaktualnijWypozyczone();
                idZaloguj.setText( "" );
                hasloZaloguj.setText( "" );
            }
        } );

// Zakladka Zarejestruj

        zarejestrujUzytkownikaButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    b.getLogowanie().zarejestrujUzytkownika( imieZarejestruj.getText(), nazwiskoZarejestruj.getText(), String.valueOf( hasloZarejestruj.getPassword() ) );
                    JOptionPane.showMessageDialog( null, "Przyznane ID:" + b.getLogowanie().getPrzyznaneId() + ". \n Przyznane ID jest Twoim loginem!" );
                } catch (ZarejestrujException e1) {
                    JOptionPane.showMessageDialog( null, "Prosze wypelnic wszystkie wymagane pola" );
                }
            }
        } );

    }


    public static void main(String[] args) {
        Biblioteka biblioteka1 = new Biblioteka();
        try {
            biblioteka1.getLogowanie().zarejestrujUzytkownika( "Aleksandra","Matuszek", "1" );
        } catch (ZarejestrujException e) { }

        try {
            BufferedReader imiona = new BufferedReader( new FileReader( "src\\pliki\\imie_autora.txt" ) );
            BufferedReader nazwiska = new BufferedReader( new FileReader( "src\\pliki\\nazwisko_autora.txt" ) );
            BufferedReader tytuly = new BufferedReader( new FileReader( "src\\pliki\\tytul.txt" ) );
            BufferedReader gatunki = new BufferedReader( new FileReader( "src\\pliki\\gatunek.txt" ) );
            BufferedReader rok = new BufferedReader( new FileReader( "src\\pliki\\rok.txt" ) );
            BufferedReader indeks = new BufferedReader( new FileReader( "src\\pliki\\indeks_ksiazki.txt" ) );

            ArrayList<String> imiona_lista = new ArrayList<>(  );
            ArrayList<String> nazwisko_lista = new ArrayList<>(  );
            ArrayList<String> tytul_lista = new ArrayList<>(  );
            ArrayList<String> gatunek_lista = new ArrayList<>(  );
            ArrayList<String> rok_lista = new ArrayList<>(  );

            while(indeks.readLine() != null){
                imiona_lista.add( imiona.readLine() );
                nazwisko_lista.add( nazwiska.readLine() );
                tytul_lista.add( tytuly.readLine() );
                gatunek_lista.add( gatunki.readLine() );
                rok_lista.add( rok.readLine() );
            }

            for (int i =0; i<imiona_lista.size(); i++) {
                biblioteka1.dodajKsiazkeDoListy( imiona_lista.get(i), nazwisko_lista.get(i), tytul_lista.get(i), gatunek_lista.get(i), rok_lista.get(i) );
            }
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println( "Nie udalo sie wczytac" );
        }

        try {
            BufferedReader nazwy = new BufferedReader( new FileReader( "src\\pliki\\nazwa.txt" ) );
            BufferedReader numery = new BufferedReader( new FileReader( "src\\pliki\\numer.txt" ) );
            BufferedReader indeks_czasopism= new BufferedReader( new FileReader( "src\\pliki\\indeks_czasopism.txt" ) );

            ArrayList<String> nazwa_lista = new ArrayList<>(  );
            ArrayList<String> numer_lista = new ArrayList<>(  );

            while(indeks_czasopism.readLine() != null){
                nazwa_lista.add( nazwy.readLine() );
                numer_lista.add( numery.readLine() );
            }

            for (int i =0; i<nazwa_lista.size(); i++) {
                biblioteka1.dodajCzasopismoDoListy( nazwa_lista.get(i), numer_lista.get(i) );
            }
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println( "Nie udalo sie wczytac" );
        }

        biblioteka1.dodajCzasopismoDoListy( "Cos", "2/2018" );

        JFrame frame = new JFrame( "BibliotekaGUI" );
        frame.revalidate();
        frame.setContentPane( new BibliotekaGUI( biblioteka1 ).panel1 );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.pack();
        frame.setVisible( true );
    }
}