package heuristicos;

import laberinto.Laberinto;
import nodo.Nodo;

import java.util.Comparator;

public class Euclidea implements Heuristico {
    @Override
    public Comparator<Nodo> fcota(Laberinto lab) {
        return new Comparator<Nodo>() {
            public double h(Nodo n) {
                return Math.sqrt(Math.pow(n.getCordX() - lab.getObjetivo().getCordX(), 2)
                        + Math.pow(n.getCordY() - lab.getObjetivo().getCordY(), 2));
            }
            @Override
            public int compare(Nodo o1, Nodo o2) {
                // h(n) dist. eucl.
                return Double.compare(o1.getCosteG() + h(o1), o2.getCosteG() + h(o2));
            }
        };
    }
}
