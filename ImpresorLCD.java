package psl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class ImpresorLCD {

    // Puntos fijos
    private final int[] puntoFinal1;
    private final int[] puntoFinal2;
    private final int[] puntoFinal3;
    private final int[] puntoFinal4;
    private final int[] puntoFinal5;
    private String[][] matrizImpr;

    static final String CARACTER_VERTICAL = "|";
    static final String CARACTER_HORIZONTAL = "-";
    static final String POSICION_X = "X";
    static final String POSICION_Y = "Y";

    // TODO code application logic here
    //String entrada = JOptionPane.showInputDialog("Digite el numero");
    private int size;

    // Calcula el numero de filasDig
    private int filasDig;
    private int columDig;
    private int totalFilas;
    private int totalColum;

    public ImpresorLCD() {
        // Inicializa variables
        this.puntoFinal1 = new int[2];
        this.puntoFinal2 = new int[2];
        this.puntoFinal3 = new int[2];
        this.puntoFinal4 = new int[2];
        this.puntoFinal5 = new int[2];
    }

    /**
     * Metodo encargado de añadir una linea a la matriz de Impresion
     * @param matriz Matriz Impresion
     * @param punto Punto Pivote
     * @param posFija Posicion Fija
     * @param size Tamaño Segmento
     * @param caracter Caracter Segmento
     */    
    private void adicionarLinea(String[][] matriz, int[] punto, String posFija,
            int size, String caracter) {

    	int y, i, valor;
        if (posFija.equalsIgnoreCase(POSICION_X)) 
        {
            for (y = 1; y <= size; y++) 
            {
                valor = punto[1] + y;
                matriz[punto[0]][valor] = caracter;
            }
        } 
        else 
        {
            for (i = 1; i <= size; i++) 
            {
                valor = punto[0] + i;
                matriz[valor][punto[1]] = caracter;
            }
        }
    }

    /**
     *
     * Metodo encargado de un segmento a la matriz de Impresion
     *
     * @param segmento Segmento a adicionar
     */  
    private void adicionarSegmento(int segmento) {

        switch (segmento) {
            case 1:
                adicionarLinea(this.matrizImpr, this.puntoFinal1, POSICION_Y,
                        this.size, CARACTER_VERTICAL);
                break;
            case 2:
                adicionarLinea(this.matrizImpr, this.puntoFinal2, POSICION_Y,
                        this.size, CARACTER_VERTICAL);
                break;
            case 3:
                adicionarLinea(this.matrizImpr, this.puntoFinal5, POSICION_Y,
                        this.size, CARACTER_VERTICAL);
                break;
            case 4:
                adicionarLinea(this.matrizImpr, this.puntoFinal4, POSICION_Y,
                        this.size, CARACTER_VERTICAL);
                break;
            case 5:
                adicionarLinea(this.matrizImpr, this.puntoFinal1, POSICION_X,
                        this.size, CARACTER_HORIZONTAL);
                break;
            case 6:
                adicionarLinea(this.matrizImpr, this.puntoFinal2, POSICION_X,
                        this.size, CARACTER_HORIZONTAL);
                break;
            case 7:
                adicionarLinea(this.matrizImpr, this.puntoFinal3, POSICION_X,
                        this.size, CARACTER_HORIZONTAL);
                break;
            default:
                break;
        }
    }

    /**
    *Metodo para añadir los segmentos correspondientes a cada numero a la lista del metodo adicionarDigito
    *@see adicionarDigito(int numero)
    *@param segmentoNumeros los segmentos que se deben agregar
    *@param segList la lista de segmentos 
    *@param cantSegs la cantidad de segmentos a adicionar.
    */

    private List<Integer> generarSegList(int[] segmentosNumeros, List<Integer> segList, int cantSegs){
    	int i;
    	for (i = 0; i<cantSegs; i++){
    		segList.add(segmentosNumeros[i]);
    	}
    	return segList;
    }

    /**
     *
     * Metodo encargado de definir los segmentos que componen un digito y
     * a partir de los segmentos adicionar la representacion del digito a la matriz
     *
     * @param numero Digito
     */
    private void adicionarDigito(int numero) {

        // Establece los segmentos de cada numero
        List<Integer> segList = new ArrayList<>();

        switch (numero) {
            case 1:
                segList= Arrays.asList(3,4);
                break;
            case 2:
            	segList= Arrays.asList(5,3,6,2,7);
                break;
            case 3:
            	segList= Arrays.asList(5,3,6,4,7);
                break;
            case 4:
            	segList= Arrays.asList(1,6,3,4);
                break;
            case 5:
            	segList= Arrays.asList(5,1,6,4,7);
                break;
            case 6:
            	segList= Arrays.asList(5,1,6,2,7,4);
                break;
            case 7:
            	segList= Arrays.asList(5,3,4);
                break;
            case 8:
            	segList= Arrays.asList(1,2,3,4,5,6,7);
                break;
            case 9:
            	segList= Arrays.asList(1,3,4,5,6,7);
                break;
            case 0:
            	segList= Arrays.asList(1,2,3,4,5,7);
                break;
            default:
                break;
        }

        Iterator<Integer> iterator = segList.iterator();

        while (iterator.hasNext()) {
            adicionarSegmento(iterator.next());
        }
    }

    /**
     *
     * Metodo encargado de imprimir un numero
     *
     * @param size Tamaño Segmento Digitos
     * @param numeroImp Numero a Imprimir
     * @param espacio Espacio Entre digitos
     */    
    private void imprimirNumero(int size, String numeroImp, int espacio) 
    {
        int pivotX = 0;
        int i,j, numero;
        char[] digitos;

        this.size = size;

        // Calcula el numero de filas cada digito
        this.filasDig = (2 * this.size) + 3;

        // Calcula el numero de columna de cada digito
        this.columDig = this.size + 2;

        // Calcula el total de filas de la matriz en la que se almacenaran los digitos
        this.totalFilas = this.filasDig;

        // Calcula el total de columnas de la matriz en la que se almacenaran los digitos
        this.totalColum = (this.columDig * numeroImp.length())
                + (espacio * numeroImp.length());

        // crea matriz para almacenar los numero a imprimir
        this.matrizImpr = new String[this.totalFilas][this.totalColum];

        // crea el arreglo de digitos
        digitos = numeroImp.toCharArray();

        // Inicializa matriz
        for (i = 0; i < this.totalFilas; i++) {
            for (j = 0; j < this.totalColum; j++) {
                this.matrizImpr[i][j] = " ";
            }
        }

        for (char digito : digitos) {
            
            //Valida que el caracter sea un digito
            if( ! Character.isDigit(digito))
            {
                throw new IllegalArgumentException("Caracter " + digito
                    + " no es un digito");
            }

            numero = Integer.parseInt(String.valueOf(digito));

            //Calcula puntos fijos
            this.puntoFinal1[0] = 0;
            this.puntoFinal1[1] = 0 + pivotX;

            this.puntoFinal2[0] = (this.filasDig / 2);
            this.puntoFinal2[1] = 0 + pivotX;

            this.puntoFinal3[0] = (this.filasDig - 1);
            this.puntoFinal3[1] = 0 + pivotX;

            this.puntoFinal4[0] = (this.columDig - 1);
            this.puntoFinal4[1] = (this.filasDig / 2) + pivotX;

            this.puntoFinal5[0] = 0;
            this.puntoFinal5[1] = (this.columDig - 1) + pivotX;

            pivotX = pivotX + this.columDig + espacio;

            adicionarDigito(numero);
        }

        // Imprime matriz
        for (i = 0; i < this.totalFilas; i++) {
            for (j = 0; j < this.totalColum; j++) {
                System.out.print(this.matrizImpr[i][j]);
            }
            System.out.println();
        }
    }

     /**
     *
     * Metodo encargado de procesar la entrada que contiene el size del segmento
     * de los digitos y los digitos a imprimir
     *
     * @param comando Entrada que contiene el size del segmento de los digito
     * y el numero a imprimir
     * @param espacioDig Espacio Entre digitos
     */  
    public void procesar(String comando, int espacioDig) {
        
        String[] parametros;
        
        int tam;

        if (!comando.contains(",")) {
            throw new IllegalArgumentException("Cadena " + comando
                    + " no contiene caracter ,");
        }
        
        //Se hace el split de la cadena
        parametros = comando.split(",");
        
        //Valida la cantidad de parametros
        if(parametros.length>2)
        {
           throw new IllegalArgumentException("Cadena " + comando
                    + " contiene mas caracter ,"); 
        }
        
        //Valida la cantidad de parametros
        if(parametros.length<2)
        {
           throw new IllegalArgumentException("Cadena " + comando
                    + " no contiene los parametros requeridos"); 
        }
        
        //Valida que el parametro size sea un numerico
        if(isNumeric(parametros[0]))
        {
            tam = Integer.parseInt(parametros[0]);
            
            // se valida que el size este entre 1 y 10
            if(tam <1 || tam >10)
            {
                throw new IllegalArgumentException("El parametro size ["+tam
                        + "] debe estar entre 1 y 10");
            }
        }
        else
        {
            throw new IllegalArgumentException("Parametro Size [" + parametros[0]
                    + "] no es un numero");
        }

        // Realiza la impresion del numero
        imprimirNumero(tam, parametros[1],espacioDig);

    }

    /**
     *
     * Metodo encargado de validar si una cadena es numerica
     *
     * @param cadena Cadena
     */  
    static boolean isNumeric(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

}
