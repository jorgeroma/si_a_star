package nodo;

public class Nodo {
    private final int cordX, cordY;
    private int costeG;
    private Nodo padre;

    public Nodo(int cordX, int cordY, Nodo padre){
        this.cordX = cordX;
        this.cordY = cordY;
        this.padre = padre;
        this.costeG = 1 + (padre == null ? 0 : padre.getCosteG());
    }

    public Nodo(int cordX, int cordY){
        this.cordX = cordX;
        this.cordY = cordY;
        this.padre = null;
        this.costeG = 0;
    }

    public boolean mismaPos(Nodo nodo){
        return this.cordX == nodo.getX() && this.cordY == nodo.getY();
    }

    public int getX(){
        return this.cordX;
    }

    public int getY(){
        return this.cordY;
    }

    public Nodo getPadre(){
        return this.padre;
    }

//    public void setCosteG(int coste){
//        this.costeG = coste;
//    }

    public int getCosteG(){
        return this.costeG;
    }

    public void setPadre(Nodo padre){
        this.padre = padre;
        this.costeG = padre.getCosteG()+1;
    }


}
