import java.awt.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mandi
 */
public class TableroView {
     //Creamos el canvas en el que dibujaremos nuestro juego
    public static final Canvas CANVAS = new Canvas("CHECKERS", 800, 800, new Color(255, 255, 255));
    /** Creamos nuestro constructor*/
    public TableroView() {
        //Hacemos visible nuestro canvas
        CANVAS.setVisible(true);
    }
    //Metodo que dibuja una ficha
    public void dibujarFicha(Ficha ficha){
        if(ficha != null) //la hace visible si no es nula
            ficha.makeVisible();
    }
    //Este metodo dibuja nuestro tablero
    public void dibujarTablero(){
        //creamos la base de nuestro tablero
        Square base = new Square(CANVAS);
        base.makeVisible(); // la hacemos visible
        //dibujamos las casillas y sus respectivas fichas
        for (int i = 0; i < 8; i ++) { //filas
            for(int j = 0; j < 8; j++) { //columnas
                //hacemos visibles las casillas
                TableroControl.getCasillas()[i][j].makeVisible();
                //si la ficha no es nula, se dibuja en el tblero
                dibujarFicha(TableroControl.getCasillas()[i][j].getFicha());
            }
        }
    }
}
