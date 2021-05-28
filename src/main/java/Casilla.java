/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mandi
 */
public class Casilla extends Square{
    private boolean casillaOcupada; //Nos indica si está ocupada
    private Ficha ficha; // Ficha que recibe la casilla

    /** Este constructor se utiliza para dibujar las casillas  y
     *  guardar las fichas al mismo tiempo.
     *  @param canvas Canvas en el que se dibujarán
     *  @param color Color de la casilla
     *  @param xPosition Posición en eje x
     *  @param yPosition Posición en eje y
     *  @param ficha Recibe la ficha a guardar (roja / negra)**/
    public Casilla(Canvas canvas, String color, int xPosition, int yPosition, Ficha ficha) {
        super(canvas, color, xPosition, yPosition);
        this.ficha = ficha;
        casillaOcupada = true;
    }

    /** Este constructor se utiliza para crear casillas que no guardan fichas (casillas blancas)
     * con los atributos de posicion y color heredados de Square*/
    public Casilla(Canvas canvas, String color, int xPosition, int yPosition){
        super(canvas, color, xPosition, yPosition);
    }

    //Cambia el estado de la casilla a ocupada
    public void ocuparCasilla(){
        casillaOcupada = true;
    }
    //Cambia el estado de la casilla a desocupada
    public void desocuparCasilla(){
        casillaOcupada = false;
        ficha = null;
    }
    //Obtiene a la ficha dentro de la casilla
    public Ficha getFicha() {
        return ficha;
    }
    //Establecemos una nueva ficha en casilla
    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }
    //Nos indica el estado de la casilla
    public boolean isCasillaOcupada() {
        return casillaOcupada;
    }
    
}
