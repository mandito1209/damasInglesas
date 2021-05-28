/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mandi
 */
public class Ficha extends Circle{
    //Constructor que pasa los parametros de posicion, color y dibjo a
    //la clase padre
    public Ficha(Canvas canvas, String color, int xPosition, int yPosition) {
        super(canvas, color, xPosition, yPosition);
    }
    //Este metodo nos permite mover la ficha de posicion
    public void mover(int yPosition, int xPosition) {
        moveVertical(yPosition);
        moveHorizontal(xPosition);
    }
}
