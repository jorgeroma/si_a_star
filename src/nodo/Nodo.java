package nodo;

import java.util.Objects;

public class Nodo {
    private int cordX;
    private int cordY;
    private int costeG;
    private int costeF;
    private Nodo padre;

    public Nodo(int cordX, int cordY, Nodo padre) {
        this.cordX = cordX;
        this.cordY = cordY;
//        this.costeG = coste(padre);
        this.costeG = 1 + (padre == null ? 0 : padre.getCosteG());
        this.padre = padre;
    }

    public int getCordX() {
        return cordX;
    }

    public int getCordY() {
        return cordY;
    }

    public int getCosteG() {
//        return coste(this);
        return this.costeG;
    }

    public int getCosteFunc() {
        return costeF;
    }

    public Nodo getPadre() {
        return padre;
    }

    public void setCordX(int cordX) {
        this.cordX = cordX;
    }

    public void setCordY(int cordY) {
        this.cordY = cordY;
    }

    public void setPadre(Nodo padre) {
        this.padre = padre;
    }

//    private int coste(Nodo n) {
//        if (n == null) {
//            return -1;
//        } else {
//            return coste(n.padre) + 1;
//        }
//    }

    public boolean mismaPos(Nodo n) {
        return this.cordX == n.cordX && this.cordY == n.cordY;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Nodo node) {
            return this.mismaPos(node);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cordX, cordY);
//        return Integer.hashCode(cordX) + Integer.hashCode(cordY);
    }

    @Override
    public String toString() {
        return "(" + cordX + ", " + cordY + ")";
    }
}


