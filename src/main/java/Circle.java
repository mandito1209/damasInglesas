import java.awt.geom.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mandi
 */
public class Circle {
    protected int diameter; //diametro del circulo
    protected int xPosition; //posicion en el eje x
    protected int yPosition; //posicion en el eje y
    protected String color; //color de la figura
    protected boolean isVisible; //no indica si la figura es visible
    protected Canvas canvas;// nuestro canvas de dibujo

    /**Este constructor es utilizado para dibujar las fichas en tablero */
    public Circle(Canvas canvas, String color, int xPosition, int yPosition) {
        diameter = 60;
        this.canvas = canvas;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.color = color;
        isVisible = false;
    }

    public String getColor() {
        return color;
    }

    public boolean isVisible()
    {
        return isVisible;
    }

    /*Make this circle visible. If it was already visible, do nothing.*/
    public void makeVisible() {
        isVisible = true;
        draw();
    }

    /*Make this circle invisible. If it was already invisible, do nothing.*/
    public void makeInvisible(){
        erase();
        isVisible = false;
    }

    /*Move the circle horizontally by 'distance' pixels.*/
    public void moveHorizontal(int distance) {
        erase();
        xPosition += distance;
        draw();
    }

    /*Move the circle vertically by 'distance' pixels.*/
    public void moveVertical(int distance) {
        erase();
        yPosition += distance;
        draw();
    }

    /*Draw the circle with current specifications on screen.*/
    public void draw() {
        if(isVisible) {
            canvas.draw(this, color, new Ellipse2D.Double(xPosition, yPosition,
                    diameter, diameter));
            canvas.wait(10);
        }
    }

    /*Erase the circle on screen.*/
    public void erase() {
        if(isVisible) {
            canvas.erase(this);
        }
    }
}
