package heuristicos;

import laberinto.Laberinto;
import nodo.Nodo;

import java.util.Comparator;

public class Manhattan implements Heuristico {
    @Override
    public Comparator<Nodo> fcota(Laberinto lab) {
        return new Comparator<Nodo>() {
            public int h(Nodo n1) {
                return Math.abs(n1.getCordX() - lab.getObjetivo().getCordX())
                        + Math.abs(n1.getCordY() - lab.getObjetivo().getCordY());
            }
            @Override
            public int compare(Nodo o1, Nodo o2) {
                // h(n) dist. manhat.
                return Integer.compare(o1.getCosteG() + h(o1), o2.getCosteG() + h(o2));
            }
        };
    }
}
