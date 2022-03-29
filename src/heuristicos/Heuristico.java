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
                // h == 0 -> solo comparo g(n)
                return Integer.compare(o1.getCosteG(), o2.getCosteG());
            }
        };
    }

    public Comparator<Nodo> manhat() {
        return new Comparator<Nodo>() {
            public int h(Nodo n) {
                Nodo objetivo = lab.getNodoFin();
                return Math.abs(n.getX() - objetivo.getX()) + Math.abs(n.getY() - objetivo.getY());
            }
            @Override
            public int compare(Nodo o1, Nodo o2) {
                // h(n) dist. manhat.
                return Integer.compare(o1.getCosteG() + h(o1), o2.getCosteG() + h(o2));
            }
        };
    }

    public Comparator<Nodo> eucl() {
        return new Comparator<Nodo>() {
            public double h(Nodo n) {
                Nodo objetivo = lab.getNodoFin();
                return Math.sqrt(Math.pow(n.getX() - objetivo.getX(), 2) + Math.pow(n.getY() - objetivo.getY(), 2));
            }
            @Override
            public int compare(Nodo o1, Nodo o2) {
                // h(n) dist. eucl.
                return Double.compare(o1.getCosteG() + h(o1), o2.getCosteG() + h(o2));
            }
        };
    }
}
