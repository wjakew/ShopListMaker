/*
JAKUB WAWAK
ALL RIGHTS RESERVED.
kubawawak@gmail.com
 */
package shoplistmaker;

import java.util.ArrayList;

/**
 *
 * @author jakubwawak
 */
public class Shoplifter {
    
    DictReader dict;
    /*
        warzywaiowoce = index 0
        napoje = index 1
        przyprawy = index 2
        pieczywo = index 3
        przekaski = index 4
        chemia = index 5
        zdrowezyienie = index 6
        nabial = index 7
        inne = index 8
    */
    ArrayList<String> warzywaiowoce;
    ArrayList<String> napoje;
    ArrayList<String> przyprawy;
    ArrayList<String> pieczywo;
    ArrayList<String> przekaski;
    ArrayList<String> chemia;
    ArrayList<String> zdrowezywienie;
    ArrayList<String> nabial;
    ArrayList<String> inne;
    
    ArrayList<String> brak_kategorii;
    
    ArrayList<String> to_do;
    
    InfoHandler info;
    
    
    Shoplifter(ArrayList<String> shop,InfoHandler inf){
        
        warzywaiowoce = new ArrayList<>();
        napoje = new ArrayList<>();
        przyprawy = new ArrayList<>();
        pieczywo = new ArrayList<>();
        przekaski = new ArrayList<>();
        chemia = new ArrayList<>();
        zdrowezywienie = new ArrayList<>();
        nabial = new ArrayList<>();
        inne = new ArrayList<>();
        
        brak_kategorii = new ArrayList<>();
  
        info = inf;
        dict = info.dictionary;
        to_do = shop;
        
        for(String element_listy : shop){
            decide(element_listy);
        }
    }
    Shoplifter(InfoHandler inf){
        warzywaiowoce = new ArrayList<>();
        napoje = new ArrayList<>();
        przyprawy = new ArrayList<>();
        pieczywo = new ArrayList<>();
        przekaski = new ArrayList<>();
        chemia = new ArrayList<>();
        zdrowezywienie = new ArrayList<>();
        nabial = new ArrayList<>();
        inne = new ArrayList<>();
        
        brak_kategorii = new ArrayList<>();
  
        info = inf;
        dict = new DictReader("",info);
    }
    
    
    void decide(String element){
        ArrayList<String> old_znalezione = new ArrayList<String>();
        ArrayList<String> znalezione_klucze = new ArrayList<String>();
        ArrayList<String> trafione; //lista na elementy pasujace do wartosci szukanej
        
        int ilosc_znakow = element.length(); //pobieramy ilosc znakow elementu
        
        int rozmiar = 10;
        
        for(int i = 2; i <ilosc_znakow;i++){//wyszykujemy z coraz wieksza dokladnoscia 
            znalezione_klucze  = dict.szukanie_fragmentaryczne(element, i);
            if(znalezione_klucze.size()<=rozmiar){
                old_znalezione = znalezione_klucze;
                rozmiar = znalezione_klucze.size();
            }
            else{
                break;
            }
        }
        trafione = dict.trafienia;
        
        //jesli w tym momencie mamy wiecej niz jeden musimy zastosowac wyszukiwanie drugiej frazy
        //zakladamy ze wpis przez uzytkownika zostal dokonany po spacji
        
        
        for(String s: trafione){
            String[] elementy = s.split(" ");
            String[] szukane = element.split(" ");
            int lastindex1,lastindex2;
            
            if(elementy.length==1 || szukane.length==1){
                break;
            }
            else{
                lastindex1 = elementy.length-1;
                lastindex2 = szukane.length-1;
            }
            if(elementy[lastindex1].equals(szukane[lastindex2])){
                znalezione_klucze = dict.daj_klucz(s);
                break;
            }
        }
        
        //tutaj mamy pewnosc ze trafilismy na pewno na dobra wartosc
        
        if(znalezione_klucze.isEmpty()){
            brak_kategorii.add(element);
        }
        else{
            if(znalezione_klucze.get(0).equals("%warzywaiowoce")){
            warzywaiowoce.add(element);
            }
            if(znalezione_klucze.get(0).equals("%napoje")){
                napoje.add(element);
            }
            if(znalezione_klucze.get(0).equals("%przyprawy")){
                przyprawy.add(element);
            }
            if(znalezione_klucze.get(0).equals("%pieczywo")){
                pieczywo.add(element);
            }
            if(znalezione_klucze.get(0).equals("%przekaski")){
                przekaski.add(element);
            }
            if(znalezione_klucze.get(0).equals("%chemia")){
                chemia.add(element);
            }
            if(znalezione_klucze.get(0).equals("%zdrowezywienie")){
                zdrowezywienie.add(element);
            } 
            if(znalezione_klucze.get(0).equals("%nabial")){
                nabial.add(element);
            } 
            if(znalezione_klucze.get(0).equals("%inne")){
                nabial.add(element);
            }
            
        }
          
    }
    
    ArrayList<String> szukaj(String nazwa_elementu){
        
        for(String szukaj: warzywaiowoce){
            if(szukaj.equals(nazwa_elementu)){
                return warzywaiowoce;
            }
        }
        for(String szukaj: napoje){
            if(szukaj.equals(nazwa_elementu)){
                return napoje;
            }
        }
        for(String szukaj: przyprawy){
            if(szukaj.equals(nazwa_elementu)){
                return przyprawy;
            }
        }
        for(String szukaj:pieczywo){
            if(szukaj.equals(nazwa_elementu)){
                return pieczywo;
            }
        }
        for(String szukaj: przekaski){
            if(szukaj.equals(nazwa_elementu)){
                return przekaski;
            }
        }
        for(String szukaj: chemia){
            if(szukaj.equals(nazwa_elementu)){
                return chemia;
            }
        }
        for(String szukaj: zdrowezywienie){
            if(szukaj.equals(nazwa_elementu)){
                return zdrowezywienie;
            }
        }
        for(String szukaj: nabial){
            if(szukaj.equals(nazwa_elementu)){
                return nabial;
            }
        }
        for(String szukaj: inne){
            if(szukaj.equals(nazwa_elementu)){
                return inne;
            }
        }
        return null;
    }
    
    boolean edit(String nazwa_elementu,int gdzie){
        if(gdzie==0){
            if(szukaj(nazwa_elementu)!=null){
                szukaj(nazwa_elementu).remove(nazwa_elementu);
                warzywaiowoce.add(nazwa_elementu);
                return true;
            }
            else{
                return false;
            }
        }
        if(gdzie==1){
            if(szukaj(nazwa_elementu)!=null){
                szukaj(nazwa_elementu).remove(nazwa_elementu);
                napoje.add(nazwa_elementu);
                return true;
            }
            else{
                return false;
            }
        }
        if(gdzie==2){
            if(szukaj(nazwa_elementu)!=null){
                szukaj(nazwa_elementu).remove(nazwa_elementu);
                przyprawy.add(nazwa_elementu);
                return true;
            }
            else{
                return false;
            }
        }
        if(gdzie==3){
            if(szukaj(nazwa_elementu)!=null){
                szukaj(nazwa_elementu).remove(nazwa_elementu);
                pieczywo.add(nazwa_elementu);
                return true;
            }
            else{
                return false;
            }
        }
        if(gdzie==4){
            if(szukaj(nazwa_elementu)!=null){
                szukaj(nazwa_elementu).remove(nazwa_elementu);
                przekaski.add(nazwa_elementu);
                return true;
            }
            else{
                return false;
            }
        }
        if(gdzie==5){
            if(szukaj(nazwa_elementu)!=null){
                szukaj(nazwa_elementu).remove(nazwa_elementu);
                chemia.add(nazwa_elementu);
                return true;
            }
            else{
                return false;
            }
        }
        if(gdzie==6){
            if(szukaj(nazwa_elementu)!=null){
                szukaj(nazwa_elementu).remove(nazwa_elementu);
                nabial.add(nazwa_elementu);
                return true;
            }
            else{
                return false;
            }
        }
        if(gdzie==7){
            if(szukaj(nazwa_elementu)!=null){
                szukaj(nazwa_elementu).remove(nazwa_elementu);
                inne.add(nazwa_elementu);
                return true;
            }
            else{
                return false;
            }
        }
        return false;
    }
    void show_act(){//dokoncz
        for(String w:warzywaiowoce){
            
        }
        
    }
    
    String make_list(){
        String to_ret = "";
        
        if(!warzywaiowoce.isEmpty()){
            to_ret = to_ret +"Your groceries:\n";
            for(String ac: warzywaiowoce){
                to_ret = to_ret + ac + "\n";
                }
            to_ret +="\n";
        }
        
        if(!napoje.isEmpty()){
            to_ret = to_ret +"Drinks:\n";
            for(String ac: napoje){
                to_ret = to_ret + ac + "\n";
                }
            to_ret +="\n";
        }
        
        if(!przyprawy.isEmpty()){
            to_ret = to_ret +"Spices:\n";
            for(String ac: przyprawy){
                to_ret = to_ret + ac + "\n";
                }
            to_ret +="\n";
        }
        
        if(!pieczywo.isEmpty()){
            to_ret = to_ret +"Pastry:\n";
            for(String ac: pieczywo){
                to_ret = to_ret + ac + "\n";
                }
            to_ret +="\n";
        }
        
        if(!przekaski.isEmpty()){
            to_ret = to_ret +"Snacks:\n";
            for(String ac: przekaski){
                to_ret = to_ret + ac + "\n";
                }
        }
        to_ret +="\n";
        if(!chemia.isEmpty()){
            to_ret = to_ret +"Cleaning products and cosmetics:\n";
            for(String ac: chemia){
                to_ret = to_ret + ac + "\n";
                }
            to_ret +="\n";
        }
        
        if(!zdrowezywienie.isEmpty()){
            to_ret = to_ret +"Healthy food:\n";
            for(String ac: zdrowezywienie){
                to_ret = to_ret + ac + "\n";
                }
            to_ret +="\n";
        }
        
        if(!nabial.isEmpty()){
            to_ret = to_ret +"Diary: \n";
            for(String ac: nabial){
                to_ret = to_ret + ac + "\n";
                }
            to_ret +="\n";
        }
        if(!inne.isEmpty()){
            to_ret = to_ret +"Other: \n";
            for(String ac: inne){
                to_ret = to_ret + ac + "\n";
                }
            to_ret +="\n";
        }
        
        return to_ret;
    }
    
    
}
