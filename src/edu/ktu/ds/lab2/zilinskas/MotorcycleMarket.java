package edu.ktu.ds.lab2.zilinskas;

import edu.ktu.ds.lab2.utils.BstSet;
import edu.ktu.ds.lab2.utils.Set;

public class MotorcycleMarket {

    public static Set<String> duplicateMotorcycleMakes(Motorcycle[] motos) {
        Set<Motorcycle> uni = new BstSet<>(Motorcycle.byMake);
        Set<String> duplicates = new BstSet<>();
        for (Motorcycle moto : motos) {
            int sizeBefore = uni.size();
            uni.add(moto);

            if (sizeBefore == uni.size()) {
                duplicates.add(moto.getMake());
            }
        }
        return duplicates;
    }

    public static Set<String> uniqueMotorcycleModels(Motorcycle[] motos) {
        Set<String> uniqueModels = new BstSet<>();
        for (Motorcycle moto : motos) {
            uniqueModels.add(moto.getModel());
        }
        return uniqueModels;
    }
}
