import algoritmos.AEstrella;
import heuristicos.Heuristico;
import laberinto.Laberinto;

import java.util.LinkedList;
import java.util.List;

public class MainPruebas {
    public static void main(String[] args) {
        Laberinto lab;
        AEstrella alg;
//        List<Integer> listaLabs = new LinkedList<>();
        List<Integer> listaSol = new LinkedList<>();
        List<Integer> listaNoSol = new LinkedList<>();
        List<Integer> costes = new LinkedList<>();
        int i = 1;
        if (args.length < 3)
            throw new RuntimeException("ERROR: faltan argumentos en main");
        int prb = Integer.parseInt(args[2]);
        do {
            lab = new Laberinto(Integer.parseInt(args[0]), Integer.parseInt(args[1]), prb, i);
            lab.generarLaberinto(0);
            alg = new AEstrella(lab, (new Heuristico(lab)).ampl());
            alg.ejecutar(lab);
            if (lab.getSolucionable()) {
                listaSol.add(i);
                costes.add(alg.solucion.size());
            } else {
                listaNoSol.add(i);
            }
            i++;
        } while (i <= 100);
//        System.out.println("Mapas con prb = " + pr);
//        System.out.println("Mapas resolubles: (TAM:" + listaSol.size() + ") " + listaSol);
//        System.out.println("Costes: " + costes);
//        System.out.println("Mapas no resolubles: (TAM:" + listaNoSol.size() + ") " + listaNoSol);
        int total = 0;
        for (Integer coste : costes) {
            total += coste;
        }
        System.out.println("--- Para una probabilidad = " + prb);
        System.out.println("Mapas resolubles: (" + listaSol.size() + "/100) ");
        System.out.println("Coste medio: " + ((total == 0)? 0 : total/(double)costes.size()));
        System.out.println("Mapas no resolubles: (" + listaNoSol.size() + "/100) ");

//        MUESTRA LABERINTOS CON ERRORES (G INACCESIBLE)
//
//        do {
//            lab = new Laberinto(Integer.parseInt(args[0]),Integer.parseInt(args[1]),30, i);
//            lab.generarLaberinto(0);
//            alg = new AEstrella(lab, (new Heuristico(lab)).ampl());
//            alg.ejecutar(lab);
////            if (lab.getSolucionable()) {
////                listaLabs.add(i);
////                cnt++;
////            }
//            i++;
//        } while (!lab.getSolucionable());
//        lab.printSolucion(alg.solucion);
//        System.out.println("LABERINTO " + i + " CON SOLUCION");

//        MUESTRA LABERINTOS CON ERRORES (G INACCESIBLE)
//
//        System.out.println("ArrayLabs: " + listaLabs);  // ArrayLabs: [7, 11, 21, 26, 28, 34, 41, 49, 50, 52, 56]
//        for (Integer k : listaLabs) {
//            lab = new Laberinto(Integer.parseInt(args[0]),Integer.parseInt(args[1]),30, k);
//            lab.generarLaberinto(0);
//            alg.ejecutar(lab);
//            System.out.println("-------------------- LABERINTO "+k+" ------------------------");
//            System.out.println("------------- ERROR: "+Laberinto.mensaje(lab.getError())+" --------------");
//            System.out.println(lab);
//        }
//        System.out.println("ArrayLabs: " + listaLabs);  // ArrayLabs: [8, 9, 50, 90, 96, 132, 136, 162, 163, 172, 178]

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
