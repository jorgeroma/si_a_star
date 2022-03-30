import algoritmo.Algoritmo;
import heuristicos.Heuristico;
import laberinto.Laberinto;
import nodo.Nodo;

import java.util.Comparator;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        Laberinto lab = new Laberinto(8,6,30, 4);
        Algoritmo al = new Algoritmo(lab, 0, new Heuristico(lab).manhat());
        List<Nodo> s = al.ejecutar();
        lab.pintarSolucion(s);
        System.out.println(lab.toString());


    }
}

