import algoritmos.AEstrella;
import heuristicos.Heuristico;
import laberinto.Laberinto;

public class MainPruebas {
    private static final int LAB = 5;
    private static final int SINSOL = 7;
    public static void main(String[] args) {
        Laberinto lab = new Laberinto(6,8,30, 5);
        // Opt 0: aleat Opt 1: extremos | (I/G)
        if (!lab.generarLaberinto(0)) { // generalLaberinto devuelve 'solucionable'
            return;
        }

        // .ampl() .manhat() .eucl()
        AEstrella alg = new AEstrella(lab, (new Heuristico(lab)).ampl());
        System.out.println("Laberinto generado:\n" + lab.toString());

        System.out.print("Nodo inicial: " + lab.getInicial().toString());
        System.out.println(" -> Nodo objetivo: " + lab.getObjetivo().toString());
        /*
        System.out.println("Nodos abiertos (nodo inicial): " + alg.abiertos.toString());
        assert alg.abiertos.peek() != null;
        System.out.println("Sucesores ini: " + alg.sucesores(alg.abiertos.peek(), lab).toString());
        assert alg.abiertos.peek() != null;
        System.out.print("Costes g(n): " + alg.abiertos.peek().getCosteG() + " ");
        assert alg.abiertos.peek() != null;
        for (Nodo n : alg.sucesores(alg.abiertos.peek(), lab)) {
            System.out.print(n.getCosteG() + " ");
        }
        System.out.println("\nOrden: Arriba Derecha Abajo Izquierda");
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
        } */

        alg.ejecutar(lab);
        if (lab.getSolucionable()) {
            System.out.println("El laberinto tiene solucion:");
            lab.printSolucion(alg.solucion);
            System.out.println("Solucion: " + alg.solucion);
        } else {
            System.out.println(lab.toString());
            System.out.println("El laberinto no tiene solucion: posicion G inaccesible");
        }
    }
}
