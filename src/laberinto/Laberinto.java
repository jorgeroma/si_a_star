package laberinto;

import nodo.Nodo;

import java.util.List;
import java.util.Random;

public class Laberinto {
    private int dimensionX, dimensionY;
    private char[][] matriz;
    private int prb;
    private int iniX, iniY, objX, objY;
    private boolean solucionable;
    private int error = 0;
    private Random rnd;

    public Laberinto(int dimensionX, int dimensionY, int prb, int seed) {
        this.dimensionX = dimensionX;
        this.dimensionY = dimensionY;
        matriz = new char[dimensionX][dimensionY];
        this.prb = prb;
        solucionable = true;
        rnd = new Random(seed);
    }

    public Laberinto(int dimensionX, int dimensionY, int prb) {
        this.dimensionX = dimensionX;
        this.dimensionY = dimensionY;
        matriz = new char[dimensionX][dimensionY];
        this.prb = prb;
        solucionable = true;
        rnd = new Random();
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

    public boolean getSolucionable() {
        return solucionable;
    }

    public void setSolucionable(boolean solucionable) {
        this.solucionable = solucionable;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public boolean generarLaberinto(int opt) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                matriz[i][j] = (rnd.nextInt(100) < prb) ? '*' : ' ';
            }
        }
        boolean flag = true;
        switch (opt) {
            case 0 -> colocarIniObj(matriz);
            case 1 -> colocarIniObjExtremos(matriz);
            default -> {
                System.err.println("Opt " + opt + " no disponible - (0/1)");
                flag = false;
            }
        }
        return flag;
    }

    private void colocarIniObjExtremos(char[][] matriz) {
        iniX = 0;
        iniY = 0;
        matriz[0][0] = 'I';

        objX = matriz[0].length - 1;
        objY = matriz.length - 1;
        matriz[matriz.length - 1][matriz[0].length - 1] = 'G';
    }

    private void colocarIniObj(char[][] matriz) {
        if (prb == 100) {
            setSolucionable(false);
            error = 2;
            return;
        }
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

    public void printSolucion(List<Nodo> solucion) {
        // Empiezo en 1 para no sobrescribir el nodo correspondiente a G
        for (int i = 1; i < solucion.size(); i++) {
            Nodo n = solucion.get(i);
            matriz[n.getCordY()][n.getCordX()] = '+';
        }
        // (Opcional) Muestra por pantalla unicamente el camino de I a G
//        for (int i = 0; i < matriz.length; i++) {
//            for (int j = 0; j < matriz[0].length; j++) {
//                if (matriz[i][j] == '*') {
//                    matriz[i][j] = ' ';
//                }
//            }
//        }
        System.out.println(this);
    }

    public static String mensaje(int error) {
        return switch (error) {
            case 1 -> "posicion G inaccesible";
            case 2 -> "no hay espacio para estados inicial y final";
            default -> "error desconocido";
        };
    }

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
