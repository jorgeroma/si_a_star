package nodo;

import java.util.Objects;

public class Nodo {
    private int cordX;
    private int cordY;
    private int costeG;
    private Nodo padre;

    public Nodo(int cordX, int cordY, Nodo padre) {
        this.cordX = cordX;
        this.cordY = cordY;
        this.costeG = (padre == null) ? 0 : 1 + padre.getCosteG();
        this.padre = padre;
    }

    public Nodo(int cordX, int cordY) {
        this(cordX, cordY, null);
    }

    public int getCordX() {
        return cordX;
    }

    public int getCordY() {
        return cordY;
    }

    public int getCosteG() {
        return this.costeG;
    }

    public Nodo getPadre() {
        return padre;
    }

    public void setPadre(Nodo padre) {
        this.padre = padre;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Nodo node) {
            return this.cordX == node.cordX && this.cordY == node.cordY;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cordX, cordY);
    }

    @Override
    public String toString() {
        return "(" + cordX + ", " + cordY + ")";
    }

//----Si no almacenace costeG:
//    private int coste(Nodo n) {
//        if (n == null) {
//            return -1;
//        } else {
//            return coste(n.padre) + 1;
//        }
//    }
}
