package edu.ktu.ds.lab2.zilinskas;

import edu.ktu.ds.lab2.utils.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;

/*
 * Aibės testavimas be Gui
 *
 */
public class ManualTest {

    static Motorcycle[] motos;
    static ParsableSortedSet<Motorcycle> cSeries = new ParsableBstSet<>(Motorcycle::new, Motorcycle.byPrice);

    public static void main(String[] args) throws CloneNotSupportedException {
        BstSet set1 = new BstSet();
        Motorcycle c4 = new Motorcycle("Suzuki   ABS 2001 115900 700");
        Motorcycle c5 = new Motorcycle("Yamaha   YZF  2001  36400 80.3");
        Motorcycle c66 = new Motorcycle("BMW   YZF  2001  36400 80.3");
        //set1.add(c4);
        set1.add(c5);
        set1.add(c66);
//        BstSet set2 = new BstSet();
//        Motorcycle c6 = new Motorcycle("Suzuki   ABS  1946 365100 9500");
//        Motorcycle c7 = new Motorcycle("Kawasaki ZephyrX 2001 115900 7500");
//        set2.add(c6);
//        set2.add(c7);
        //set1.addAll(set2);
       
         Iterator iter = set1.descendingIterator();
        while (iter.hasNext()) {
            Ks.oun(iter.next());
        }
        
        Ks.oun("Floor: "+set1.floor(c4));
        
//        set1.pollLast();
//        
//         Iterator iter2 = set1.descendingIterator();
//        while (iter2.hasNext()) {
//            Ks.oun(iter2.next());
//        }
        
         //Ks.oun( set1.lower(c66));
       // Locale.setDefault(Locale.US); // Suvienodiname skaičių formatus
        //executeTest();
    }

    public static void executeTest() throws CloneNotSupportedException {
        Motorcycle c1 = new Motorcycle("Suzuki", "Gixxer", 1997, 50000, 1700);
        Motorcycle c2 = new Motorcycle.Builder()
                .make("Suzuki")
                .model("ABS")
                .year(2001)
                .mileage(20000)
                .price(3500)
                .build();
        Motorcycle c3 = new Motorcycle.Builder().buildRandom();
        Motorcycle c4 = new Motorcycle("Suzuki   ABS 2001 115900 700");
        Motorcycle c5 = new Motorcycle("Suzuki   ABS  1946 365100 9500");
        Motorcycle c6 = new Motorcycle("Yamaha   YZF  2001  36400 80.3");
        Motorcycle c7 = new Motorcycle("Kawasaki ZephyrX 2001 115900 7500");
        Motorcycle c8 = new Motorcycle("Yamaha   YZF 1946 365100 950");
        Motorcycle c9 = new Motorcycle("Honda    Monkey  2007  36400 850.3");

        Motorcycle[] motosArray = {c9, c7, c8, c5, c1, c6};

        Ks.oun("Auto Aibė:");
        ParsableSortedSet<Motorcycle> motosSet = new ParsableBstSet<>(Motorcycle::new);

        for (Motorcycle c : motosArray) {
            motosSet.add(c);
            Ks.oun("Aibė papildoma: " + c + ". Jos dydis: " + motosSet.size());
        }
        Ks.oun("");
        Ks.oun(motosSet.toVisualizedString(""));

        ParsableSortedSet<Motorcycle> motosSetCopy = (ParsableSortedSet<Motorcycle>) motosSet.clone();

        motosSetCopy.add(c2);
        motosSetCopy.add(c3);
        motosSetCopy.add(c4);
        Ks.oun("Papildyta autoaibės kopija:");
        Ks.oun(motosSetCopy.toVisualizedString(""));

        c9.setMileage(10000);

        Ks.oun("Originalas:");
        Ks.ounn(motosSet.toVisualizedString(""));

        Ks.oun("Ar elementai egzistuoja aibėje?");
        for (Motorcycle c : motosArray) {
            Ks.oun(c + ": " + motosSet.contains(c));
        }
        Ks.oun(c2 + ": " + motosSet.contains(c2));
        Ks.oun(c3 + ": " + motosSet.contains(c3));
        Ks.oun(c4 + ": " + motosSet.contains(c4));
        Ks.oun("");

        Ks.oun("Ar elementai egzistuoja aibės kopijoje?");
        for (Motorcycle c : motosArray) {
            Ks.oun(c + ": " + motosSetCopy.contains(c));
        }
        Ks.oun(c2 + ": " + motosSetCopy.contains(c2));
        Ks.oun(c3 + ": " + motosSetCopy.contains(c3));
        Ks.oun(c4 + ": " + motosSetCopy.contains(c4));
        Ks.oun("");

        Ks.oun("Elementų šalinimas iš kopijos. Aibės dydis prieš šalinimą:  " + motosSetCopy.size());
        for (Motorcycle c : new Motorcycle[]{c2, c1, c9, c8, c5, c3, c4, c2, c7, c6, c7, c9}) {
            motosSetCopy.remove(c);
            Ks.oun("Iš autoaibės kopijos pašalinama: " + c + ". Jos dydis: " + motosSetCopy.size());
        }
        Ks.oun("");

        Ks.oun("Motociklų aibė su iteratoriumi:");
        Ks.oun("");
        for (Motorcycle c : motosSet) {
            Ks.oun(c);
        }
        Ks.oun("");
        Ks.oun("Motociklų aibė AVL-medyje:");
        ParsableSortedSet<Motorcycle> motosSetAvl = new ParsableAvlSet<>(Motorcycle::new);
        for (Motorcycle c : motosArray) {
            motosSetAvl.add(c);
        }
        Ks.ounn(motosSetAvl.toVisualizedString(""));

        Ks.oun("Motociklų aibė su iteratoriumi:");
        Ks.oun("");
        for (Motorcycle c : motosSetAvl) {
            Ks.oun(c);
        }

        Ks.oun("");
        Ks.oun("Motociklų aibė su atvirkštiniu iteratoriumi:");
        Ks.oun("");
        Iterator iter = motosSetAvl.descendingIterator();
        while (iter.hasNext()) {
            Ks.oun(iter.next());
        }

        Ks.oun("");
        Ks.oun("Motociklų aibės toString() metodas:");
        Ks.ounn(motosSetAvl);

        // Išvalome ir suformuojame aibes skaitydami iš failo
        motosSet.clear();
        motosSetAvl.clear();

        Ks.oun("");
        Ks.oun("Motociklų aibė DP-medyje:");
        motosSet.load("data\\ban.txt");
        Ks.ounn(motosSet.toVisualizedString(""));
        Ks.oun("Išsiaiškinkite, kodėl medis augo tik į vieną pusę.");

        Ks.oun("");
        Ks.oun("Motociklų aibė AVL-medyje:");
        motosSetAvl.load("data\\ban.txt");
        Ks.ounn(motosSetAvl.toVisualizedString(""));

        Set<String> motosSet4 = MotorcycleMarket.duplicateMotorcycleMakes(motosArray);
        Ks.oun("Pasikartojančios automobilių markės:\n" + motosSet4.toString());

        Set<String> motosSet5 = MotorcycleMarket.uniqueMotorcycleModels(motosArray);
        Ks.oun("Unikalūs automobilių modeliai:\n" + motosSet5.toString());
    }

    static ParsableSortedSet generateSet(int kiekis, int generN) {
        motos = new Motorcycle[generN];
        for (int i = 0; i < generN; i++) {
            motos[i] = new Motorcycle.Builder().buildRandom();
        }
        Collections.shuffle(Arrays.asList(motos));

        cSeries.clear();
        Arrays.stream(motos).limit(kiekis).forEach(cSeries::add);
        return cSeries;
    }
}
