import algoritmos.AEstrella;
import heuristicos.Heuristico;
import laberinto.Laberinto;

public class Main {
    private static final int LAB = 5;
    private static final int SINSOL = 7;    // Para lab(6,8,30,7)
    public static void main(String[] args) {
        Laberinto lab = new Laberinto(60,80,30);
        // Opt 0: aleat Opt 1: extremos | (I/G)
        if (!lab.generarLaberinto(0)) { // generalLaberinto devuelve 'solucionable'
            return;
        }

        // .ampl() .manhat() .eucl()
        AEstrella alg = new AEstrella(lab, (new Heuristico(lab)).ampl());
        System.out.println("Laberinto generado:\n" + lab.toString());

        System.out.print("Nodo inicial: " + lab.getInicial().toString());
        System.out.println(" -> Nodo objetivo: " + lab.getObjetivo().toString());

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
