import algoritmoA_Estrella.AEstrella;
import heuristicos.Heuristico;
import laberinto.Laberinto;
import nodo.Nodo;

import java.util.Set;

public class MainPruebas {
    public static void main(String[] args) {
        Laberinto lab = new Laberinto(6,8,30);
        lab.generarLaberinto();
        Heuristico heu = new Heuristico(lab);
        AEstrella alg = new AEstrella(lab, heu.manhat());
        System.out.println(lab.toString());
        alg.ejecutar(lab);
        System.out.println("Solucion: " + alg.solucion);

        System.out.println("Nodo inicial: " + lab.getInicial().toString());
        System.out.println("Nodo objetivo: " + lab.getObjetivo().toString());
        System.out.println("Nodos abiertos (nodo inicial): " + alg.abiertos.toString());
        assert alg.abiertos.peek() != null;
        System.out.println("Sucesores ini: " + alg.sucesores(alg.abiertos.peek(), lab).toString());
        assert alg.abiertos.peek() != null;
        System.out.print("Costes g(n): " + alg.abiertos.peek().getCosteG() + " ");
        assert alg.abiertos.peek() != null;
        for (Nodo n : alg.sucesores(alg.abiertos.peek(), lab)) {
            System.out.print(n.getCosteG() + " ");
        }
        System.out.println();
        assert alg.abiertos.peek() != null;
        int cnt = 0;
        for (Nodo n : alg.sucesores(alg.abiertos.peek(), lab)) {
//            System.out.println(n.toString() + ": " + alg.antecesores(n).toString());
            System.out.println(n.toString() + ": ");
            Set<Nodo> M = alg.sucesores(n, lab);
            System.out.println("\tSucesores: " + M.toString());
            System.out.println("\tAntecesores: " + alg.antecesores(n));
            M.removeAll(alg.antecesores(n));
            System.out.println("\tSucesores sin antecesores: " + M.toString());
            cnt++;
        }
    }
}
