import algoritmo.Algoritmo;
import laberinto.Laberinto;
import nodo.Nodo;

import java.util.List;


public class Main {

    public static void main(String[] args) {
        Laberinto lab = new Laberinto(20,15,15);
        Algoritmo al = new Algoritmo(lab, 0);
        List<Nodo> s = al.ejecutar();
        lab.pintarSolucion(s);
        System.out.println(lab.toString());


    }
}

