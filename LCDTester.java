package psl;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**	
*Clase para testear el funcionamiento se la clase ImpresorLCD.
*@see ImpresorLCD.java
*/

public class LCDTester {

    static final String CADENA_FINAL = "0,0";
    
    public static void main(String[] args) {

        // Establece los segmentos de cada numero
        List<String> listaComando = new ArrayList<>();
        String comando;
        int espacioEntreDigs;
        
        try {

            	Scanner lector = new Scanner(System.in);
                System.out.println("Espacio entre Digitos (0 a 5): ");
                comando = lector.next();
                // Valida si es un numero
                if (ImpresorLCD.isNumeric(comando))
                {
                    espacioEntreDigs = Integer.parseInt(comando);
                    // se valida que el espaciado este entre 0 y 5
                    if(espacioEntreDigs <0 || espacioEntreDigs >5)
                    {
                        throw new IllegalArgumentException("El espacio entre "
                                + "digitos debe estar entre 0 y 5");
                    }
                } 
                else 
                {
                    throw new IllegalArgumentException("Cadena " + comando
                            + " no es un numero entero");
                }
     
                do
                {
                    System.out.println("Entrada: ");
                    comando = lector.next();
                    if(!comando.equalsIgnoreCase(CADENA_FINAL))
                    {
                        listaComando.add(comando);
                    }
                }while (!comando.equalsIgnoreCase(CADENA_FINAL)); 

            ImpresorLCD impresorLCD = new ImpresorLCD();

            Iterator<String> iterator = listaComando.iterator();
            while (iterator.hasNext()) 
            {
                try 
                {
                    impresorLCD.procesar(iterator.next(), espacioEntreDigs);
                } catch (Exception ex) 
                {
                    System.out.println("Error: "+ex.getMessage());
                }
            }
        } catch (Exception ex) 
        {
            System.out.println("Error: "+ex.getMessage());
        }

    }

}