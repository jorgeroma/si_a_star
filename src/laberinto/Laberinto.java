package laberinto;

import nodo.Nodo;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Laberinto {
    private int dimensionX, dimensionY, prb, iniX, iniY, objX, objY;
    private char[][] matrix;
    private boolean solucionable;
    private Random ran;

    public Laberinto(int dimensionX, int dimensionY, int prb, int seed){
        this.dimensionX = dimensionX;
        this.dimensionY = dimensionY;
        this.prb = prb;
        ran = new Random(seed);
        this.generarLaberinto();
    }

    public Laberinto(int dimensionX, int dimensionY, int prb){
        this.dimensionX = dimensionX;
        this.dimensionY = dimensionY;
        this.prb = prb;
        ran = new Random();
        this.generarLaberinto();
    }

    private void generarLaberinto(){
        matrix = new char[this.dimensionY][this.dimensionX];
        generarObstaculos();
        generarInicioFin();
    }

    private void generarObstaculos(){
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                matrix[i][j] = (ran.nextInt() < prb/100 ? '*' : ' ' );
            }
        }
    }

    private void generarInicioFin(){
        // Generar Inicio
        int[] tempArray = casillaValida();
        iniX = tempArray[0];
        iniY = tempArray[1];
        matrix[iniY][iniX] = 'I';


        // Generar Goal
        tempArray = casillaValida();
        objX = tempArray[0];
        objY = tempArray[1];
        matrix[objY][objX] = 'G';
    }

    private int[] casillaValida(){
        int a = ran.nextInt(dimensionX);
        int b = ran.nextInt(dimensionY);
        while(matrix[b][a] == '*'){
            a = ran.nextInt(dimensionX);
            b = ran.nextInt(dimensionY);
        }

        return new int[] {a,b};

    }

    @Override
    public String toString(){
        String salida = new String();
        for(int i = 0; i < matrix.length; i++){
            salida += Arrays.toString(matrix[i]);
            salida += "\n";
        }
        return salida;
    }

    public void pintarSolucion(List<Nodo> solucion){
        for (Nodo nodo : solucion) {
            matrix[nodo.getY()][nodo.getX()] = '+';
        }
    }

}
