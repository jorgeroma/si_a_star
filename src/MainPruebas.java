import algoritmos.AEstrella;
import heuristicos.Heuristico;
import laberinto.Laberinto;

import java.util.LinkedList;
import java.util.List;

public class MainPruebas {
    public static void main(String[] args) {
        Laberinto lab;
        AEstrella alg;
        List<Integer> listaLabs = new LinkedList<>();
        int i = 0, cnt = 0;
        if (args.length < 2)
            throw new RuntimeException("ERROR: faltan argumentos en main");
        do {
            lab = new Laberinto(Integer.parseInt(args[0]),Integer.parseInt(args[1]),30, i);
            lab.generarLaberinto(0);
            alg = new AEstrella(lab, (new Heuristico(lab)).ampl());
            alg.ejecutar(lab);
            if (lab.getError() != 0) {
                listaLabs.add(i);
                cnt++;
            }
            i++;
        } while (cnt <= 10);
        System.out.println("ArrayLabs: " + listaLabs);  // ArrayLabs: [7, 11, 21, 26, 28, 34, 41, 49, 50, 52, 56]
        for (Integer k : listaLabs) {
            lab = new Laberinto(Integer.parseInt(args[0]),Integer.parseInt(args[1]),30, k);
            lab.generarLaberinto(0);
            alg.ejecutar(lab);
            System.out.println("-------------------- LABERINTO "+k+" ------------------------");
            System.out.println("------------- ERROR: "+Laberinto.mensaje(lab.getError())+" --------------");
            System.out.println(lab);
        }
        System.out.println("ArrayLabs: " + listaLabs);  // ArrayLabs: [8, 9, 50, 90, 96, 132, 136, 162, 163, 172, 178]
//        System.out.println("Laberinto " + (i - 1) + " con errror " + Laberinto.mensaje(lab.getError()));
        // Opt 0: aleat Opt 1: extremos | (I/G)
//        if (!lab.generarLaberinto(0)) { // generalLaberinto devuelve 'solucionable'
//            return;
//        }
//
//        // .ampl() .manhat() .eucl()
//        AEstrella alg = new AEstrella(lab, (new Heuristico(lab)).ampl());
//        System.out.println("Laberinto generado:\n" + lab.toString());
//
//        System.out.print("Nodo inicial: " + lab.getInicial().toString());
//        System.out.println(" -> Nodo objetivo: " + lab.getObjetivo().toString());
//        /*
//        System.out.println("Nodos abiertos (nodo inicial): " + alg.abiertos.toString());
//        assert alg.abiertos.peek() != null;
//        System.out.println("Sucesores ini: " + alg.sucesores(alg.abiertos.peek(), lab).toString());
//        assert alg.abiertos.peek() != null;
//        System.out.print("Costes g(n): " + alg.abiertos.peek().getCosteG() + " ");
//        assert alg.abiertos.peek() != null;
//        for (Nodo n : alg.sucesores(alg.abiertos.peek(), lab)) {
//            System.out.print(n.getCosteG() + " ");
//        }
//        System.out.println("\nOrden: Arriba Derecha Abajo Izquierda");
//        assert alg.abiertos.peek() != null;
//        int cnt = 0;
//        for (Nodo n : alg.sucesores(alg.abiertos.peek(), lab)) {
////            System.out.println(n.toString() + ": " + alg.antecesores(n).toString());
//            System.out.println(n.toString() + ": ");
//            Set<Nodo> M = alg.sucesores(n, lab);
//            System.out.println("\tSucesores: " + M.toString());
//            System.out.println("\tAntecesores: " + alg.antecesores(n));
//            M.removeAll(alg.antecesores(n));
//            System.out.println("\tSucesores sin antecesores: " + M.toString());
//            cnt++;
//        } */
//
//        if (lab.getSolucionable()) {
//            alg.ejecutar(lab);
//        }
//        if (lab.getSolucionable()) {
//            System.out.println("El laberinto tiene solucion:");
//            lab.printSolucion(alg.solucion);
//            System.out.println("Solucion: " + alg.solucion);
//        } else {
//            System.out.println(lab.toString());
//            System.out.println("El laberinto no tiene solucion: posicion G inaccesible");
//        }
    }
}
