/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mandi
 */
public class TableroControl {
    private static Casilla[][] casillas; //Matriz de casillas
    //Vector con las columnas del tablero
    public static final char[] COLUMNAS = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
    //Instanciamos nuestros objetos Ficha
    private FichaNegra fichaNegra;
    private FichaRoja fichaRoja;

    /*Constructor de nuestro talbero*/
    public TableroControl() {
        casillas = new Casilla[8][8]; //Creamos la matriz de casilla
    }
    //getters & setters
    public static Casilla[][] getCasillas() {
        return casillas;
    }

    public static void setCasillas(Casilla[][] casillas) {
        TableroControl.casillas = casillas;
    }

    /*Metodo que crea las casillas y fichas de manera visual y logica
    * al mismo tiempo que se crean, se guardan en la matriz de casillas */
    public void crearTablero(){
        boolean seleccionDeColor = false; //esta variable nos ayudará a elegir el color de la casilla
        String colorDeLaCasilla; //guarda el color de la casilla en turno de dibujo
        int yPosition = 160; //posicion inicial de "y"

        //Ahora, creamos las casillas de nuestro tablero
        for(int i = 0; i < 8; i++){ //filas
            int xPosition = 160; //posicion inicial de "x"
            for (int j = 0; j < 8; j++) {
                if(seleccionDeColor) { //columnas
                    colorDeLaCasilla = "ch2"; //color de la casilla oscura
                    if (i < 3) { // Esta condicion nos habilita las ficha para poder dibujarlas en el canvas
                        // y las guarda en la casilla oscura.
                       fichaNegra = new FichaNegra(TableroView.CANVAS, xPosition, yPosition); //crea ficha negra
                        //crea y guarda una casilla en la matriz, esta casilla guarda la ficha creada
                       casillas[i][j] = new Casilla(TableroView.CANVAS, colorDeLaCasilla, xPosition, yPosition, fichaNegra);
                    } else if (i > 4) { // Esta condicion nos habilita las ficha para poder dibujarlas en el canvas
                        // y las guarda en la casilla oscura.
                        fichaRoja = new FichaRoja(TableroView.CANVAS,  xPosition, yPosition); //crea ficha roja
                        //crea y guarda una casilla en la matriz, esta casilla guarda la ficha creada
                        casillas[i][j] = new Casilla(TableroView.CANVAS, colorDeLaCasilla, xPosition, yPosition, fichaRoja);
                    } else //crea casillas oscuras vacías
                        casillas[i][j] = new Casilla(TableroView.CANVAS, colorDeLaCasilla, xPosition, yPosition);
                }else {
                    colorDeLaCasilla = "ch1"; //creamos las casillas claras
                    casillas[i][j] = new Casilla(TableroView.CANVAS, colorDeLaCasilla, xPosition, yPosition);
                }
                seleccionDeColor = !seleccionDeColor; //cambiamos el color de la siguiente casilla
                xPosition += 60; // aumentamos la posicion del eje "x"
            }
            seleccionDeColor = !seleccionDeColor; //cambiamos el color de la siguiente casilla
            yPosition += 60; // // aumentamos la posicion del eje "y"
        }
    }

    /*Metodo que nos permite mover las fichas negras, recibe como parametros las filas de origen - destino
    y las columnas de origen - destino */
    public boolean moverFichasNegras(int filaOrigen, char columnaOrigen, int filaDestino, char columnaDestino){
        boolean movimientoExitoso = false;//indica si se pudo mover o no la ficha

        //Entero que guarda la posicion de la columna de origen de la ficha en el array COLUMNAS.
        int posColumnaOrigen = new String(TableroControl.COLUMNAS).indexOf(columnaOrigen);

        //Entero que guarda la posicion de la columna destino de la ficha en el array COLUMNAS.
        int posColumnaDestino = new String(TableroControl.COLUMNAS).indexOf(columnaDestino);

        //verificamos que las posiciones estén dentro de los limites del tablero (array)
        if(verificarLimites(filaOrigen, posColumnaOrigen, filaDestino, posColumnaDestino)) {
            //verificamos que la casilla no esté vacía
            if (casillas[filaOrigen][posColumnaOrigen].getFicha() != null) {
                //verificamos que la casilla no esté ocupada
                if (!casillas[filaDestino][posColumnaDestino].isCasillaOcupada()) {
                    //idenificamos un movimiento básico (una casilla)
                    if (filaOrigen + 1 == filaDestino) {
                        //verificamos que se pueda realizar el movimiento
                        if (fichaNegra.movimientoFichaNegra(casillas[filaOrigen][posColumnaOrigen].getFicha(), posColumnaOrigen, posColumnaDestino))
                            movimientoExitoso = true; //si se pudo realizar, retorna true
                        else //si no, muestra un mensaje de error
                            System.out.println("No es posible trasladarse a esa casilla.");
                        //Si no era un moviminto básico, entonces, es para comer una pieza (dos casillas)
                    } else if (filaOrigen + 2 == filaDestino) {
                        //verificamos que se pueda realizar el movimiento
                        if (fichaNegra.comer(casillas[filaOrigen][posColumnaOrigen].getFicha(), filaOrigen, posColumnaOrigen, posColumnaDestino)) {
                            movimientoExitoso = true; //si se pudo realizar, retorna true
                        } else //si no, muestra un mensaje de error
                            System.out.println("No es posible trasladarse a esa casilla.");
                    }
                } else // si la casilla está ocupada, lo indicamos
                    System.out.println("CASILLA OCUPADA");
            } else // si la ficha no existe, se lo indicamos al usuario
                System.out.println("Esa ficha no existe");
        } else //si está fuera de los limites, lo indicamos
            System.out.println("Out of limits");
        //Si nuestro movimiento fue exitoso
        if(movimientoExitoso) {
            casillas[filaDestino][posColumnaDestino].ocuparCasilla(); //Ocupamos la casilla destino
            casillas[filaDestino][posColumnaDestino].setFicha(casillas[filaOrigen][posColumnaOrigen].getFicha()); //movemos la ficha de casilla
            casillas[filaOrigen][posColumnaOrigen].desocuparCasilla(); //descupamos la casilla de la ficha a mover
        }
        return movimientoExitoso; //retornamos el resultado del método
    }

    /*Metodo que nos permite mover las fichas rojas, recibe como parametros las filas de origen - destino
        y las columnas de origen - destino */
    public boolean moverFichasRojas(int filaOrigen, char columnaOrigen, int filaDestino, char columnaDestino){
        boolean movimientoExitoso = false;
        //Entero que guarda la posicion de la columna de origen de la ficha en el array COLUMNAS.
        int posColumnaOrigen = new String(TableroControl.COLUMNAS).indexOf(columnaOrigen);
        //Entero que guarda la posicion de la columna destino de la ficha en el array COLUMNAS.
        int posColumnaDestino = new String(TableroControl.COLUMNAS).indexOf(columnaDestino);

        //verificamos que las posiciones estén dentro de los limites del tablero (array)
        if(verificarLimites(filaOrigen, posColumnaOrigen, filaDestino, posColumnaDestino)){
            //verificamos que la casilla no esté vacía
            if (casillas[filaOrigen][posColumnaOrigen].getFicha() != null) {
                //verificamos que la casilla no esté ocupada
                if (!casillas[filaDestino][posColumnaDestino].isCasillaOcupada()) {
                    //idenificamos un movimiento básico (una casilla)
                    if (filaOrigen - 1 == filaDestino) {
                        //verificamos que se pueda realizar el movimiento
                        if (fichaRoja.movimientoFichaRoja(casillas[filaOrigen][posColumnaOrigen].getFicha(), posColumnaOrigen, posColumnaDestino))
                            movimientoExitoso = true; //si se pudo realizar, retorna true
                        else //si no, muestra un mensaje de error
                            System.out.println("No es posible trasladarse a esa casilla.");
                        //Si no era un moviminto básico, entonces, es para comer una pieza (dos casillas)
                    } else if (filaOrigen - 2 == filaDestino) {
                        //verificamos que se pueda realizar el movimiento
                        if (fichaRoja.comer(casillas[filaOrigen][posColumnaOrigen].getFicha(), filaOrigen, posColumnaOrigen, posColumnaDestino)) {
                            movimientoExitoso = true; //si se pudo realizar, retorna true
                        } else //si no, muestra un mensaje de error
                            System.out.println("No es posible trasladarse a esa casilla.");
                    }
                } else //si la casilla está ocupada, lo indicamos
                    System.out.println("CASILLA OCUPADA");
            } else // si la ficha no existe, se lo indicamos al usuario
                System.out.println("Esa ficha no existe");
        } else //si está fuera de los limites, lo indicamos
            System.out.println("Out of limits");
        //Si nuestro movimiento fue exitoso
        if(movimientoExitoso) {
            casillas[filaDestino][posColumnaDestino].ocuparCasilla(); //Ocupamos la casilla destino
            casillas[filaDestino][posColumnaDestino].setFicha(casillas[filaOrigen][posColumnaOrigen].getFicha()); //movemos la ficha de casilla
            casillas[filaOrigen][posColumnaOrigen].desocuparCasilla(); //descupamos la casilla de la ficha a mover
        }
        return movimientoExitoso; //retornamos el resultado del método
    }

    /*Nos ayuda a contar las fichas restantes en el tablero
    * @param color indica el color de la ficha a contar*/
    public int contarFichaRestantes(String color){
        int fichaVisiblesEnTablero = 0; //contador de fichas
        //Recorremos la matriz de casillas
        for(int i = 0; i < 8; i ++){
            for(int j = 0; j < 8; j++) {
                //no cuenta fichas nulas
                if(casillas[i][j].getFicha() != null) {
                    //si la ficha en casilla es igual a la que buscamos y está visible
                    if (casillas[i][j].getFicha().color.equals(color) &&
                            casillas[i][j].getFicha().isVisible())
                        fichaVisiblesEnTablero++; //aumentamos contador
                    }
            }
        }
        return fichaVisiblesEnTablero; //retornamos valor del contador
    }

    /*Con este metodo verificamos que las posiciones introducidos por el usuario esten dentro
    * del limite del tablero*/
    public boolean verificarLimites(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino){
        //verificamos que los valores introducidos esten dentro de las dimensiones del tablero
        if(filaOrigen >= 0 && filaOrigen < 8 && filaDestino >= 0 && filaDestino < 8
        && columnaOrigen >= 0 && columnaOrigen < 8 && columnaDestino >= 0 && columnaDestino < 8) {
            return  true; //true = está dentro del tablero
        } else
            return false; //false = está fuera del tablero
    }
}
