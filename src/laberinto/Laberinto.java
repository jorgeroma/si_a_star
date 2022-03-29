package laberinto;

import algoritmoA_Estrella.AEstrella;
import nodo.Nodo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Laberinto {
    private int dimensionX, dimensionY;
    private char[][] matriz;
    private int prb;
    private int iniX, iniY, objX, objY;
    private int seed;
    private Random rnd;
    private boolean solucionable;

    public Laberinto(int dimensionX, int dimensionY, int prb, int seed) {
        this.dimensionX = dimensionX;
        this.dimensionY = dimensionY;
        matriz = new char[dimensionX][dimensionY];
        rnd = new Random(seed);
        this.prb = prb;
    }

    public Laberinto(int dimensionX, int dimensionY, int prb) {
//        this(dimensionX, dimensionY, prb, );
        this.dimensionX = dimensionX;
        this.dimensionY = dimensionY;
        matriz = new char[dimensionX][dimensionY];
        rnd = new Random();
        this.prb = prb;
    }

    public char[][] getMatriz() {
        return matriz;
    }

    public void setSolucionable(boolean solucionable) {
        this.solucionable = solucionable;
    }

    public void generarLaberinto() {
        Random rnd = new Random();
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                matriz[i][j] = (rnd.nextInt(100) < prb) ? '*' : ' ';
            }
        }
        colocarIniObj(matriz, rnd);
    }

    private void colocarIniObj(char[][] matriz, Random rnd) {
        do {
            iniX = rnd.nextInt(matriz[0].length);
            iniY = rnd.nextInt(matriz.length);
        } while (matriz[iniY][iniX] != ' ');
        matriz[iniY][iniX] = 'I';
        do {
            objX = rnd.nextInt(matriz[0].length);
            objY = rnd.nextInt(matriz.length);
        } while (matriz[objY][objX] != ' ');
        matriz[objY][objX] = 'G';
    }

    // FIXME
    public void mostrarSolucion(ArrayList<Nodo> solucion) {
        StringBuilder sb = new StringBuilder("Solucion(");
        for (Nodo n : solucion) {
            sb.append(" ").append(n.toString()).append(" ");
        }
        System.out.println(sb.toString());
        System.out.println(")");
    }

//    public void pintarSolucion(List<Nodo> solucion){
//        for (Nodo nodo : solucion) {
//            matrix[nodo.getY()][nodo.getX()] = '+';
//        }
//    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                sb.append(matriz[i][j]);
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Laberinto lab = new Laberinto(6,8,30);
        lab.generarLaberinto();
        AEstrella alg = new AEstrella(new Nodo(lab.iniX, lab.iniY, null));
//        aEstrella(lab);
        System.out.println(lab.toString());

        System.out.println("Nodo inicial: " + (new Nodo(lab.iniX, lab.iniY, null)).toString());
        System.out.println("Nodo objetivo: " + (new Nodo(lab.objX, lab.objY, null)).toString());
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
