package heuristicos;

import laberinto.Laberinto;
import nodo.Nodo;

import java.util.Comparator;

public interface Heuristico {
    Comparator<Nodo> fcota(Laberinto lab);
}
