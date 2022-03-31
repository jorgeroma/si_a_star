package algoritmos;

import laberinto.Laberinto;
import nodo.Nodo;

import java.util.*;

public class AEstrella {
    private Queue<Nodo> abiertos;
    private List<Nodo> cerrados;
    private Laberinto lab;
    private List<Nodo> solucion;

    public AEstrella(Laberinto lab, Comparator<Nodo> heuristico) {
        this.lab = lab;
        abiertos = new PriorityQueue<>(heuristico);
        abiertos.add(lab.getInicial());
        cerrados = new ArrayList<>();
        solucion = null;
    }

    public Queue<Nodo> getAbiertos() {
        return abiertos;
    }

    public void setAbiertos(Queue<Nodo> abiertos) {
        this.abiertos = abiertos;
    }

    public List<Nodo> getCerrados() {
        return cerrados;
    }

    public void setCerrados(List<Nodo> cerrados) {
        this.cerrados = cerrados;
    }

    public Laberinto getLab() {
        return lab;
    }

    public void setLab(Laberinto lab) {
        this.lab = lab;
    }

    public List<Nodo> getSolucion() {
        return solucion;
    }

    public void setSolucion(List<Nodo> solucion) {
        this.solucion = solucion;
    }

    boolean objetivo(Nodo n) {
        return n.mismaPos(lab.getObjetivo());
    }

    public void ejecutar(Laberinto lab) {
        while (!abiertos.isEmpty()) {
            Nodo act = abiertos.poll();
            cerrados.add(act);
            if (objetivo(act)) {
                solucion = camino(act);
            } else {
                Set<Nodo> M = sucesores(act, lab);
                M.removeAll(antecesores(act));
                for (Nodo n : M) {
                    if (!abiertos.contains(n) && !cerrados.contains(n)) {
                        n.setPadre(act);
                        abiertos.add(n);
                    }
                }
            }
        }
        // Estado G inaccesible
        if (solucion == null) {
            lab.setSolucionable(false);
            lab.setError(1);
        }
    }

    private List<Nodo> camino(Nodo act) {
        List<Nodo> sol = new ArrayList<>();
        while (act.getPadre() != null) {
            sol.add(act);
            act = act.getPadre();
        }
        return sol;
    }

    public Set<Nodo> antecesores(Nodo node) {
        return antecesoresRec(node, new HashSet<>());
    }

    public Set<Nodo> antecesoresRec(Nodo node, Set<Nodo> set) {
        if (node == null) {
            return set;
        } else {
            set.add(node);
            return antecesoresRec(node.getPadre(), set);
        }
    }

    public Set<Nodo> sucesores(Nodo node, Laberinto lab) {
        Set<Nodo> suc = new HashSet<>();
        String[][] matriz = lab.getMatriz();
        // Arriba
        if (node.getCordY() - 1 >= 0 && !matriz[node.getCordY() - 1][node.getCordX()].equals("*")) {
            suc.add(new Nodo(node.getCordX() , node.getCordY() - 1, node));
        }
        // Derecha
        if (node.getCordX() + 1 < matriz[0].length && !matriz[node.getCordY()][node.getCordX() + 1].equals("*")) {
            suc.add(new Nodo(node.getCordX() + 1, node.getCordY(), node));
        }
        // Abajo
        if (node.getCordY() + 1 < matriz.length && !matriz[node.getCordY() + 1][node.getCordX()].equals("*")) {
            suc.add(new Nodo(node.getCordX(), node.getCordY() + 1, node));
        }
        // Izquierda
        if (node.getCordX() - 1 >= 0 && !matriz[node.getCordY()][node.getCordX() - 1].equals("*")) {
            suc.add(new Nodo(node.getCordX() - 1, node.getCordY(), node));
        }
        return suc;
    }

}
