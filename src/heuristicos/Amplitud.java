package heuristicos;

import laberinto.Laberinto;
import nodo.Nodo;

import java.util.Comparator;

public class Amplitud implements Heuristico {
    @Override
    public Comparator<Nodo> fcota(Laberinto lab) {
        return new Comparator<Nodo>() {
            @Override
            public int compare(Nodo o1, Nodo o2) {
                // h == 0 -> solo comparo g(n)
                return Integer.compare(o1.getCosteG(), o2.getCosteG());
            }
        };
    }
}
