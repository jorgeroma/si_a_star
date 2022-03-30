package laberinto;

import nodo.Nodo;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Laberinto {
    private int dimensionX, dimensionY, prb, iniX, iniY, objX, objY;
    private String[][] matrix;
    private Random ran;
    private boolean solucionado = true;
    private List<Nodo> sol;
    public static final String RED_BOLD = "\033[1;31m";    // RED
    public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
    public static final String GREEN_BOLD = "\033[1;32m";  // GREEN
    public static final String ANSI_RESET = "\u001B[0m";    // RESET



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
        matrix = new String[this.dimensionY][this.dimensionX];
        generarObstaculos();
        generarInicioFin();
    }

    private void generarObstaculos(){
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                matrix[i][j] = (ran.nextFloat() < prb/100.0 ? RED_BOLD + "*"  + ANSI_RESET: " " );
//                matrix[i][j] = (ran.nextFloat() < prb/100.0 ? "*" : " " );
            }
        }
    }

    public Nodo getNodoInicio(){
        return new Nodo(iniX, iniY);
    }

    public Nodo getNodoFin(){
        return new Nodo(objX, objY);
    }

    private void generarInicioFin(){
        // Generar Inicio
        int[] tempArray = getCasillaValida();
        iniX = tempArray[0];
        iniY = tempArray[1];
        matrix[iniY][iniX] = "I";


        // Generar Goal
        tempArray = getCasillaValida();
        objX = tempArray[0];
        objY = tempArray[1];
        matrix[objY][objX] = "G";
    }

    private int[] getCasillaValida(){
        int a = ran.nextInt(dimensionX);
        int b = ran.nextInt(dimensionY);
        while(matrix[b][a] == "*"){
            a = ran.nextInt(dimensionX);
            b = ran.nextInt(dimensionY);
        }

        return new int[] {a,b};

    }

    public boolean casillaValida(int x, int y){
        return x >= 0 && x < dimensionX && y >= 0 && y < dimensionY && (matrix[y][x] == " " || matrix[y][x] == "G");
    }

    @Override
    public String toString(){
        String salida = new String();
        for (int i = 0;i<dimensionX; i++){
            salida += "_ ";
        }
        salida += "\n";
        for(int i = 0; i < matrix.length; i++){
            salida += "| ";
            for (String j : matrix[i]){
                salida += j;
                salida += " ";
            }
            salida += "| ";

            salida += "\n";
        }
        for (int i = 0;i<dimensionX; i++){
            salida += "_ ";
        }
        salida += "\n";
        return solucionado ? (salida + "\n" + sol) : (salida + "NO SOLUCIONABLE");
    }

    public void pintarSolucion(List<Nodo> solucion){
        if(solucion.isEmpty()){
            solucionado = false;
        }else sol = solucion;

        Iterator<Nodo> it = solucion.iterator();
        Nodo temp;

        while (it.hasNext()){
            temp = it.next();
            matrix[temp.getY()][temp.getX()] = GREEN_BOLD + "+" + ANSI_RESET;
        }
        matrix[iniY][iniX] = YELLOW_BOLD + "I" + ANSI_RESET;
        matrix[objY][objX] = YELLOW_BOLD + "G" + ANSI_RESET;
    }

}
