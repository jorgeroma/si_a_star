package algoritmo;

import laberinto.Laberinto;
import nodo.Nodo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Algoritmo {
//    private List<Nodo> abiertos;
    PriorityQueue<Nodo> abiertos;
    private final List<Nodo> cerrados;
    private final Laberinto laberinto;
    private final Nodo objetivo;
    private int h = 0;
    public Algoritmo(Laberinto laberinto, int h, Comparator<Nodo> heuristico){
        this.laberinto = laberinto;
//        abiertos = new ArrayList<>();
        abiertos = new PriorityQueue<>(heuristico);
        cerrados = new ArrayList<>();
        this.h = h;
        objetivo = laberinto.getNodoFin();
    }

    public List<Nodo> ejecutar(){
        abiertos.add(laberinto.getNodoInicio());
        Nodo abrir;
        while (!abiertos.isEmpty()){
//            abrir = abiertos.get(0);
            abrir = abiertos.poll();
//            abiertos.remove(0);
            cerrados.add(abrir);

            if(abrir.equals(objetivo)){
                return getSolucion(abrir);
            }else{
                expandir(abrir);
            }

        }

        //TODO paso 3 - Fracaso
        return new ArrayList<Nodo>();
    }

    private void expandir(Nodo nodo){
        Nodo test;
        //Arriba
        test = new Nodo(nodo.getX(), nodo.getY() + 1, nodo);
        aniadir(test);
        //Derecha
        test = new Nodo(nodo.getX() + 1, nodo.getY(), nodo);
        aniadir(test);
        //Abajo
        test = new Nodo(nodo.getX(), nodo.getY() - 1, nodo);
        aniadir(test);
        //Izquierda
        test = new Nodo(nodo.getX() - 1, nodo.getY(), nodo);
        aniadir(test);


    }

    private void aniadir(Nodo test){
        if (laberinto.casillaValida(test.getX(), test.getY()) && !cerrados.contains(test) && !abiertos.contains(test)){
            abiertos.add(test);
        }
    }

    private List<Nodo> getSolucion(Nodo sol){
        List<Nodo> solucion = new ArrayList<>();
        solucion.add(sol);
        if(sol.getPadre() == null){
            return solucion;
        }else{
            Nodo padre = sol.getPadre();
            solucion.addAll(getSolucion(padre));
            return solucion;
        }
    }
}
