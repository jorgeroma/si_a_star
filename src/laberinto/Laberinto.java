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

    public Nodo getInicial() {
        return new Nodo(iniX, iniY);
    }

    public Nodo getObjetivo() {
        return new Nodo(objX, objY);
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
    public void printSolucion(ArrayList<Nodo> solucion) {
//        StringBuilder sb = new StringBuilder("Solucion(");
//        for (Nodo n : solucion) {
//            sb.append(" ").append(n.toString()).append(" ");
//        }
//        System.out.println(sb.toString());
//        System.out.println(")");
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

}
