/*
JAKUB WAWAK
ALL RIGHTS RESERVED.
kubawawak@gmail.com
 */
package shoplistmaker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 *DictReader.
 * Klasa otwierajaca i obslugujaca operacje na slowniku.
 * Jedyna klasa ktora moze operowac na slowniku.
 * @author jakubwawak
 * Wersja unowoczśniona do spełniania wszystkich wymogów aktualnych aplikacji
 * opierających się na 
 */
public class DictReader {
    
    //zawiera wartosci slownika
    public ArrayList<ArrayList<String>> wartosci = new ArrayList<>();
    public int amount_of_wartosci = 0;
    //zawiera klucze slownika
    public ArrayList<String> klucze = new ArrayList<>();
    //dane slownika sa zarzadzane poprzez indeksy poszczegolnych kluczy
    
    //zawiera wartosci slownika zapisane w hashsetach
    private ArrayList<HashSet> wartosci2 = new ArrayList<>();
    
    //poszczegolna linia slownika, kompletnie nie wazne
    public ArrayList<String> L_zrozumianeslowa = new ArrayList<String>();
    
    //lista zawierajace wszystkie najblizsze slowa do slowa szukanego
    //metoda szukanie_fragmentaryczne
    public ArrayList<String> trafienia = new ArrayList<>();
    
    private String dict_src = "dict.txt";
    
    //funkcja ktora operuje gdy wartosc klucza jest pojedyncza
    int single_mode = 0;
    
    /**
     * Konstruktor DictReader.
     * Tworzy obiekt zawierajacy dane slownika.
     */
    DictReader(String src,InfoHandler act){
        
        if(!src.equals("")){
            dict_src = src;
        }
        if(act.lg==1){
            dict_src = act.get_config().get_dict_path();
        }
        if(act.mode == 1){ //tryb obslugi pliku konfiguracyjnego
            dict_src = src;
        }

       File slownikf = new File(dict_src);
       
       BufferedReader reader = null;
       System.out.println("DICT_READER: ŁADOWANIE PLIKU SŁOWNIKOWEGO");
       
       if (slownikf.exists()){
           
           try{
               String linia;
               
               reader = new BufferedReader(new FileReader(slownikf));
               
               while((linia = reader.readLine()) != null){
                   L_zrozumianeslowa.add(linia.substring(0, linia.length()));
               }
               
           }catch(FileNotFoundException e){
               System.out.println("DICT_READER: "+e.getMessage());
           }catch(IOException e){
               System.out.println("DICT_READER: "+e.getMessage());
           }
       } 
       for(String linia: L_zrozumianeslowa){
           if(linia.startsWith("%")==true){
               klucze.add(linia);
               ArrayList<String> chwilowa = new ArrayList<String>();
               HashSet<ArrayList> chwilowa2 = new HashSet<ArrayList>();
               chwilowa2.add(chwilowa);
               wartosci.add(chwilowa);
               wartosci2.add(chwilowa2);
               for(int i=L_zrozumianeslowa.indexOf(linia)+1;i<L_zrozumianeslowa.size()-1;i++){
                   if(L_zrozumianeslowa.get(i).startsWith("%")==false){
                     chwilowa.add(L_zrozumianeslowa.get(i));
                     amount_of_wartosci++;
                   }
                   else{
                       break;
                   }
               } 
            }
        }
       System.out.println("DICT_READER: SINGLE MODE = "+single_mode);
       System.out.println("DICT_READER: POPRAWNIE ZALADOWANO SLOWNIK");
    }
    /**
     * dodaj.
     * Dodaje wartosci do slownika.
     * @param klucz
     * @param wartosc
     * @throws FileNotFoundException 
     * DZIALA.
     */
    public void dodaj(String klucz, String wartosc) throws FileNotFoundException{
        if(klucze.contains(klucz)){
            int index_klucza = klucze.indexOf(klucz);
            wartosci.get(index_klucza).add(wartosc);
            System.out.println("DICT_READER: KLUCZ ZNALEZIONY");
            System.out.println("DICT_READER: DODANO NOWA WARTOSC KLUCZA");
        }
        else{
            ArrayList<String> a = new ArrayList <String>();
            klucze.add(klucz);
            a.add(wartosc);
            wartosci.add(a);
            System.out.println("DICT_READER: NIE ZNALEZIONO KLUCZA. UTWORZONO");
            System.out.println("DICT_READER: DODANO NOWA WARTOSC KLUCZA");
        }
        save_dict();
    }    
    
    void save_dict() throws FileNotFoundException{
        PrintWriter plik = new PrintWriter(dict_src);
        int indeks = 0;
        for(String i : klucze){
            plik.write(i +"\n");
            ArrayList<String> lista = wartosci.get(indeks);
            Iterator i_lista =lista.iterator();
            while(i_lista.hasNext()){
                plik.write(i_lista.next()+"\n");
            }
            indeks++;
        }
        plik.write("\n");
        plik.close();
        System.out.println("DICT_READER: POPRAWNIE NADPISANO SLOWNIK");
    }
    /**
     * daj_klucz.
     * @param szukana_wartosc
     * Funkcja szukajaca w slowniku wartosci i zwracajaca liste klucz
     * pod ktorymi wartosc moze sie znajdowac.
     * @return ArrayList
     */
    public ArrayList daj_klucz(String szukana_wartosc){
        
        ArrayList<String> klucze = new ArrayList<>();
        
        for(ArrayList<String> element_wartosci : wartosci){
            for(String nazwa_wartosci: element_wartosci){
                if(nazwa_wartosci.equals(szukana_wartosc)){
                    klucze.add(this.klucze.get(wartosci.indexOf(element_wartosci)));//tutaj dostajemy klucz pod ktorym znajduje sie dana wartosc
                }
            }
        }
        return klucze;
    }
    /**
     * zamien_wartosc
     * @param klucz
     * @param wartosc
     * @return boolean
     * Zwraca true jesli klucz istnieje, wartosc podana przez uzytkownika
     * zostaje wymieniona.
     * UWAGA! Dziala tylko gdy wartosci to arraylisty z pojedynczymi wartosciami
     */
    public boolean zamien_wartosc(String klucz,String wartosc) throws FileNotFoundException{
        for(ArrayList<String> a : wartosci){
            if(a.size()!=1){
                return false;
            }
        }
        wartosci.get(klucze.indexOf(klucz)).clear();
        wartosci.get(klucze.indexOf(klucz)).add(wartosc);
        System.out.println("DICT_READER: WYPISUJE WARTOSCI:");
        for(ArrayList<String> s : wartosci){
            System.out.println(s.get(0));
        }
        System.out.println("DICT_READER: KONCZE WYPISYWANIE WARTOSCI");
        save_dict();
        dodaj(klucz,wartosc);
        return true;
    }
    /**
     * szukanie_fragmentaryczne
     * @param szukana_wartosc
     * @param wielkosc_skoku
     * Funkcja wyszukuje zadany zmienna wielkosc_skoku fragment
     * danej wartosci w tablicy wartosci i zwraca jej klucz badz klucze
     * @return ArrayList 
     */
    
    public ArrayList szukanie_fragmentaryczne(String szukana_wartosc,int wielkosc_skoku){
        trafienia.clear();
        ArrayList<String> klucze = new ArrayList<>();
        
        for(ArrayList<String> element_wartosci : wartosci){
            for(String nazwa_wartosci: element_wartosci){
                String to_check = szukana_wartosc.substring(0, wielkosc_skoku);
                if(nazwa_wartosci.contains(to_check)){
                    trafienia.add(nazwa_wartosci);
                    klucze.add(this.klucze.get(wartosci.indexOf(element_wartosci)));
                }
            }
        }
        return klucze;
    }
    /**
     * szukaj.
     * Funkcja szukajaca klucza w slowniku i zwaracajaca ArrayList jego wartosci.
     * Jesli nie znajdzie zwraca null.
     * @param poszukiwany_klucz
     * @return ArrayList
     * DZIALA.
     */
    public ArrayList szukaj(String poszukiwany_klucz){
        if(klucze.contains(poszukiwany_klucz)){
            return wartosci.get(klucze.indexOf(poszukiwany_klucz));
        }
        return null;
    }
    /**
     * szukaj2.
     * Funkcja szukajaca klucza w slowniku i zwaracajaca HashSet jego wartosci.
     * Jesli nie znajdzie zwraca null.
     * @param poszukiwany_klucz
     * @return HashSet
     * DZIALA.
     */
    public HashSet szukaj2(String poszukiwany_klucz){
        if(klucze.contains(poszukiwany_klucz)){
            return wartosci2.get(klucze.indexOf(poszukiwany_klucz));
        }
        return null;
    }
    /**
     * r_klucze.
     * Zwraca klucze slownika
     * @return ArrayList
     */
    public ArrayList r_klucze(){
        return klucze;
    }
    /**
     * r_wartosci
     * Zwraca wartosci slownika
     * @return ArrayList
     */
    public ArrayList r_wartosci(){
        return wartosci;
    }
    
    void show_klucze(){
        System.out.println("Wypisuje klucze slownika");
        for(String klucz: klucze){
            System.out.println(klucz);
        }
        System.out.println("Zakonczylem wypisywanie slownika");
    }
    /**
     * DictReader.show_dictionary()
     * Function for showing dictionary
     */
    void show_dictionary(){
        System.out.println("Wypisuje slownik:");
        for(String klucz:  klucze){
            System.out.println("Klucz: "+klucz);
            int index = klucze.indexOf(klucz);
            for (String wartosc : wartosci.get(index)){
                System.out.println("    "+wartosc);
            }
        }
    }
    
    void show_wartosci(){
        System.out.println("Wypisuje wartosci");
        for(ArrayList<String> wartosc: wartosci){
            System.out.println("---------");
            System.out.println(this.klucze.get(wartosci.indexOf(wartosc)));
            for(String s : wartosc){
                System.out.println(s);
            }
        }
        System.out.println("Zakonczylem wypisywanie wartosci"); 
    }
}
