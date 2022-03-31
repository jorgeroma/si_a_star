import algoritmos.AEstrella;
import heuristicos.Heuristico;
import laberinto.Laberinto;

public class Main {
    // ArrayLabsSinSol (6*8): [7, 11, 21, 26, 28, 34, 41, 49, 50, 52, 56]
    // ArrayLabsSinSol (60*80): [8, 9, 50, 90, 96, 132, 136, 162, 163, 172, 178] -> 9, 50, 96, 132 'G' inacc, 90 'I' inacc
    // Algunos buenos -> Sol: 7, 16, 32, 99, 127     Sin Sol: 9, 90
    public static void main(String[] args) {
        if (args.length < 3)
            throw new RuntimeException("ERROR: faltan argumentos en main");

        Laberinto lab = new Laberinto(Integer.parseInt(args[0]),Integer.parseInt(args[1]), Integer.parseInt(args[2]));

        // Opt 0: pos aleatoria Opt 1: extremos | (I/G)
        if (!lab.generarLaberinto(0)) { // generarLaberinto devuelve true si opt es valido
            return;
        }

        // .ampl() .manhat() .eucl()
        AEstrella alg = new AEstrella(lab, (new Heuristico(lab)).ampl());
        System.out.println("Laberinto generado:\n" + lab);

        System.out.print("Nodo inicial: " + lab.getInicial().toString());
        System.out.println(" -> Nodo objetivo: " + lab.getObjetivo().toString());

        alg.ejecutar(lab);

        if (lab.getSolucionable()) {
            System.out.println("El laberinto tiene solucion:");
            lab.printSolucion(alg.getSolucion());
            System.out.println("Solucion: " + alg.getSolucion());
            System.out.println("Coste Solucion: " + alg.getSolucion().size());
        } else {
            System.out.println(lab);
            System.out.println("El laberinto no tiene solucion: " + Laberinto.mensaje(lab.getError()));
        }
    }
}
