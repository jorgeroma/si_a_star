package heuristicos;

import laberinto.Laberinto;
import nodo.Nodo;

import java.util.Comparator;

public class Heuristico {
    private Laberinto lab;
    public Heuristico(Laberinto lab) {
        this.lab = lab;
    }

    public Comparator<Nodo> ampl() {
        return new Comparator<Nodo>() {
            @Override
            public int compare(Nodo o1, Nodo o2) {
                return 0;
//                return Integer.compare(o1.getCosteG(), o2.getCosteG());
            }
        };
    }

    public Comparator<Nodo> manhat() {
        return new Comparator<Nodo>() {
            public int h(Nodo n1) {
                return Math.abs(n1.getCordX() - lab.getObjetivo().getCordX()) + Math.abs(n1.getCordY() - lab.getObjetivo().getCordY());
            }
            @Override
            public int compare(Nodo o1, Nodo o2) {
                return Integer.compare(o1.getCosteG() + h(o1), o2.getCosteG() + h(o2));
            }
        };
    }

    public Comparator<Nodo> eucl() {
        return new Comparator<Nodo>() {
            public double h(Nodo n) {
                return Math.sqrt(Math.pow(n.getCordX() - lab.getObjetivo().getCordX(), 2) + Math.pow(n.getCordY() - lab.getObjetivo().getCordY(), 2));
            }
            @Override
            public int compare(Nodo o1, Nodo o2) {
                return Double.compare(o1.getCosteG() + h(o1), o2.getCosteG() + h(o2));
            }
        };
    }
}
