package edu.ktu.ds.lab2.zilinskas;

import edu.ktu.ds.lab2.gui.ValidationException;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MotorcyclesGenerator {

    private int startIndex = 0, lastIndex = 0;
    private boolean isStart = true;

    private Motorcycle[] motos;

    public Motorcycle[] generateShuffle(int setSize,
                                 double shuffleCoef) throws ValidationException {

        return generateShuffle(setSize, setSize, shuffleCoef);
    }

    /**
     * @param setSize
     * @param setTake
     * @param shuffleCoef
     * @return Gražinamas aibesImtis ilgio masyvas
     * @throws ValidationException
     */
    public Motorcycle[] generateShuffle(int setSize,
                                 int setTake,
                                 double shuffleCoef) throws ValidationException {

        Motorcycle[] motos = IntStream.range(0, setSize)
                .mapToObj(i -> new Motorcycle.Builder().buildRandom())
                .toArray(Motorcycle[]::new);
        return shuffle(motos, setTake, shuffleCoef);
    }

    public Motorcycle takeMotorcycle() throws ValidationException {
        if (lastIndex < startIndex) {
            throw new ValidationException(String.valueOf(lastIndex - startIndex), 4);
        }
        // Vieną kartą Automobilis imamas iš masyvo pradžios, kitą kartą - iš galo.
        isStart = !isStart;
        return motos[isStart ? startIndex++ : lastIndex--];
    }

    private Motorcycle[] shuffle(Motorcycle[] motos, int amountToReturn, double shuffleCoef) throws ValidationException {
        if (motos == null) {
            throw new IllegalArgumentException("Motociklų nėra (null)");
        }
        if (amountToReturn <= 0) {
            throw new ValidationException(String.valueOf(amountToReturn), 1);
        }
        if (motos.length < amountToReturn) {
            throw new ValidationException(motos.length + " >= " + amountToReturn, 2);
        }
        if ((shuffleCoef < 0) || (shuffleCoef > 1)) {
            throw new ValidationException(String.valueOf(shuffleCoef), 3);
        }

        int amountToLeave = motos.length - amountToReturn;
        int startIndex = (int) (amountToLeave * shuffleCoef / 2);

        Motorcycle[] takeToReturn = Arrays.copyOfRange(motos, startIndex, startIndex + amountToReturn);
        Motorcycle[] takeToLeave = Stream
                .concat(Arrays.stream(Arrays.copyOfRange(motos, 0, startIndex)),
                        Arrays.stream(Arrays.copyOfRange(motos, startIndex + amountToReturn, motos.length)))
                .toArray(Motorcycle[]::new);

        Collections.shuffle(Arrays.asList(takeToReturn)
                .subList(0, (int) (takeToReturn.length * shuffleCoef)));
        Collections.shuffle(Arrays.asList(takeToLeave)
                .subList(0, (int) (takeToLeave.length * shuffleCoef)));

        this.startIndex = 0;
        this.lastIndex = takeToLeave.length - 1;
        this.motos = takeToLeave;
        return takeToReturn;
    }
}
