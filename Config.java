/*
JAKUB WAWAK
ALL RIGHTS RESERVED.
kubawawak@gmail.com
 */
package shoplistmaker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 *
 * @author jakubwawak
 */
public class Config {
    
    
    /*
    Struktura pliku obslugiwana przez Config:
    
    %imie
    ...         <- tutaj imie uzytkownika wpisywane na poczatku
    %dict_path
    ...         <- tutaj sciezka do pliku slownikowego
    
    */
    
    
    boolean is;
    
    String config_path = "config_file.txt";
    
    ArrayList<ArrayList<String>> config_list; //lista zawierajaca wszystkie elementy kluczy
    ArrayList<ArrayList<String>> config;   //prawidlowo zinterpretowany config programu
    InfoHandler info;           
    DictReader config_file;     //slownik konfiguracyjny
    boolean is_alive = false;   //true -> plik konfiguracyjny zaladowany, istnieje
    
    int first_start = 0;        //mozliwosc dodania recznego danych do congfig podczas pierwszego rozruchu programu
    
    Config(InfoHandler info){
        
        this.info = info;
        
        config_list = new ArrayList<>();
        
        File test = new File(config_path);
        
        if(test.exists()){ //plik istnieje
            
            info.mode=1;    //wlaczenie trybu dla obslugi pliku konfiguracyjnego
            
            
            //tutaj pobieranie informacji z pliku konfiguracyjnego
            //do ciała funkcji
            
            config_file = new DictReader(config_path,info);
            
            //tutaj dodajemy funkcjonalnosci
            
            config_list.add(config_file.szukaj("%imie"));
            config_list.add(config_file.szukaj("%dict_path"));
            
            config = get_config();
            
            info.mode=0;
            
            is_alive = true;
        }
        else{
            config = new ArrayList<>();
            config_file = new DictReader(config_path,info);
            first_start = 1;
        }
    }
    void reload() throws FileNotFoundException{
        
    config_list = new ArrayList<>();

    File test = new File(config_path);

    if(test.exists()){ //plik istnieje

        info.mode=1;    //wlaczenie trybu dla obslugi pliku konfiguracyjnego


        //tutaj pobieranie informacji z pliku konfiguracyjnego
        //do ciała funkcji

        config_file = new DictReader(config_path,info);

        //tutaj dodajemy funkcjonalnosci

        config_list.add(config_file.szukaj("%imie"));
        config_list.add(config_file.szukaj("%dict_path"));

        config = get_config();

        info.mode=0;

        is_alive = true;
    }
    else{
        
        config_file = new DictReader(config_path,info);
        config_file.dodaj("%imie", "");
        config_file.dodaj("%dict_path","");
        }   
    }
    //test istnienia konfiguracji
    boolean exists(){
        return is_alive;
    }
    
    void make(String imie,String path) throws FileNotFoundException{
        config_file.dodaj("%imie", imie);
        config_file.dodaj("%dict_path", path);
        config_file.dodaj("%uwaga", "prosimy nie modyfikowac pliku konfiguracyjnego");
        //mozliwosc rozwoju funkcjonalnosci
        
        //w celu prawidlowego rozruchu programu przy pierwszym podejsciu
        if(first_start==1){
            ArrayList<String> a = new ArrayList<>();
            a.add(path);
            config.add(a);
            a.clear();
            a.add(imie);
            config.add(a);
        }
    }
    
    //pobieranie zawartosci pliku konfiguracyjnego
    ArrayList<ArrayList<String>> get_config(){
        return config_file.r_wartosci();
    }
    
    String get_dict_path(){
        return config.get(1).get(0);
        
    }
    String get_imie(){
        return config.get(0).get(0);
        
    }
    
    void change_imie(String nowe_imie) throws FileNotFoundException{
        info.mode=1;
        config_file.zamien_wartosc("%imie", nowe_imie);
        reload();
        info.mode=0;
    }
    void change_path(String new_path) throws FileNotFoundException{
        info.mode=1;
        config_file.zamien_wartosc("%dict_path", new_path);
        reload();
        info.mode=0;
    }
    
    
    void show_config(){
        config_file.show_klucze();
        System.out.println("----");
        config_file.show_wartosci();
    }
}
