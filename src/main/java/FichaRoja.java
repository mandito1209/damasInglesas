/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mandi
 */
public class FichaRoja extends Ficha{
     /** Este constructor dibuja la ficha roja en la casilla que se guardará.
     *  @param canvas Canvas en el que se dibujarán
     *  @param xPosition Posición en eje x
     *  @param yPosition Posición en eje y**/
    public FichaRoja(Canvas canvas, int xPosition, int yPosition) {
        super(canvas, "red", xPosition, yPosition);
    }
    /** En este metodo controlamos los movimientos básicos de una ficha roja
     *  @param fichaAMover Le pasamos la ficha que vamos a mover para
     *  poder modificar sus posiciones en el plano x, y
     *  @param columnaOrigen Posición de la columna origen de la ficha
     *  @param columnaDestino Posición de la columna a la que la moveremos
     * @return  **/
    public boolean movimientoFichaRoja(Ficha fichaAMover, int columnaOrigen, int columnaDestino){
        boolean movimientoExitoso; //Determina si se pudo mover
        //Para moverla a la derecha una casilla
        if(columnaOrigen + 1 == columnaDestino) {
            //utilizamos el metodo mover(y, x), heredado de la clase Ficha.
            fichaAMover.mover(-60, 60);
            movimientoExitoso = true;   //Marcamos movimiento exitoso
        }
        //Para moverla a la izquierda una casilla
        else if(columnaOrigen - 1 == columnaDestino){
            //utilizamos el metodo mover(y, x), heredado de la clase Ficha.
            fichaAMover.mover(-60, -60);
            movimientoExitoso = true;   //Marcamos movimiento exitoso
        }
        else movimientoExitoso = false; //Si no fue exito, marcará false
        return movimientoExitoso;  //retornamos el resultado del método (true / false)
    }

    /** En este metodo controlamos los movimientos que permiten comer una ficha.
     *  @param fichaAMover Le pasamos la ficha que vamos a mover para
     *  poder modificar sus posiciones en el plano x, y.
     *  @param filaOrigen Posicion original de la fila de la ficha a mover.
     *  @param columnaOrigen Posición de la columna origen de la ficha.
     *  @param columnaDestino Posición de la columna a la que la moveremos.
     * @return  **/
    public boolean comer(Ficha fichaAMover, int filaOrigen, int columnaOrigen, int columnaDestino){
        boolean movimientoExitoso; //Determina si se pudo mover
        //movimiento a la derecha
        if(columnaOrigen + 2 == columnaDestino){
            //Si la casilla siguiente tiene una ficha del color contrario para poder comerla
            if(TableroControl.getCasillas()[filaOrigen - 1][columnaOrigen + 1].getFicha().color.equals("black")){
                fichaAMover.mover(-120, 120); //nos movemos dos casillas
                //Escondemos la ficha que comimos, del canvas
                TableroControl.getCasillas()[filaOrigen - 1][columnaOrigen + 1].getFicha().makeInvisible();
                //Desocupamos la casilla de la ficha comida
                TableroControl.getCasillas()[filaOrigen - 1][columnaOrigen + 1].desocuparCasilla();
            }
            movimientoExitoso = true; //Marcamos movimiento exitoso, si pudimos comer
        }
        //movimiento a la izquierda
        else if(columnaOrigen - 2 == columnaDestino){
            //Si la casilla siguiente tiene una ficha del color contrario para poder comerla
            if(TableroControl.getCasillas()[filaOrigen - 1][columnaOrigen - 1].getFicha().color.equals("black")){
                fichaAMover.mover(-120, -120); //nos movemos dos casillas
                //Escondemos la ficha que comimos, del canvas
                TableroControl.getCasillas()[filaOrigen - 1][columnaOrigen - 1].getFicha().makeInvisible();
                //Desocupamos la casilla de la ficha comida
                TableroControl.getCasillas()[filaOrigen - 1][columnaOrigen - 1].desocuparCasilla();
            }
            movimientoExitoso = true; //Marcamos movimiento exitoso, si pudimos comer
        }
        else movimientoExitoso = false; //Si no tuvo exito, al comer marcará false
        return movimientoExitoso; //retornamos el resultado del método (true / false)
    }
}
