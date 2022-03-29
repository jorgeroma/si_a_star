package algoritmoA_Estrella;

import laberinto.Laberinto;
import nodo.Nodo;

import java.util.*;

public class AEstrella {
    public Queue<Nodo> abiertos;
    public List<Nodo> cerrados;

    public AEstrella(Nodo inicial) {
        abiertos = new PriorityQueue<>(11, new Comparator<Nodo>() {
            @Override
            public int compare(Nodo o1, Nodo o2) {
                return Integer.compare(o1.getCosteFunc(), o2.getCosteFunc());
            }
        });
        abiertos.add(inicial);
        cerrados = new ArrayList<>();
//        abiertos.sort(Comparator.comparingInt(Nodo::getCosteFunc));
//        abiertos.sort((o1, o2) -> Integer.compare(o1.getCosteFunc(), o2.getCosteFunc()));
    }

//    public List<Nodo> ejecutar(Laberinto lab) {
//        if (abiertos.isEmpty()) {
//            return null;
//        } else {
//            Nodo node = abiertos.poll();
//            cerrados.add(node);
//            if (objetivo(node)) {
//
//            } else {
//                Set<Nodo> M = sucesores(node, lab);
//                M.removeAll(antecesores(node));
//                for (Nodo n : M) {
//                    if (!abiertos.contains(n) && !cerrados.contains(n)) {
//                        n.setPadre(node);
//                        abiertos.add(n);
//                    } else {
//
//                    }
//                }
//            }
//        }
//    }

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
        char[][] matriz = lab.getMatriz();
        // Arriba
        if (node.getCordY() - 1 >= 0 && matriz[node.getCordY() - 1][node.getCordX()] != '*') {
            suc.add(new Nodo(node.getCordX() , node.getCordY() - 1, node));
        }
        // Derecha
        if (node.getCordX() + 1 < matriz[0].length && matriz[node.getCordY()][node.getCordX() + 1] != '*') {
            suc.add(new Nodo(node.getCordX() + 1, node.getCordY(), node));
        }
        // Abajo
        if (node.getCordY() + 1 < matriz.length && matriz[node.getCordY() + 1][node.getCordX()] != '*') {
            suc.add(new Nodo(node.getCordX(), node.getCordY() + 1, node));
        }
        // Izquierda
        if (node.getCordX() - 1 >= 0 && matriz[node.getCordY()][node.getCordX() - 1] != '*') {
            suc.add(new Nodo(node.getCordX() - 1, node.getCordY(), node));
        }
        if (suc.isEmpty()) lab.setSolucionable(false);
        return suc;
    }
}
