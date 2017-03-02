import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
/**
 * Write a description of class AnalizadorAccesosAServidor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AnalizadorAccesosAServidor
{
    // instance variables - replace the example below with your own
    ArrayList<Acceso> lineasAcceso = new ArrayList<Acceso>();
    ArrayList<Integer> hora = new ArrayList<Integer>();
    ArrayList<Integer> numeroVecesHora = new ArrayList<Integer>();
    
    public void analizarArchivoDeLog(String nombreArchivo)
    {
        try{
            File archivo = new File(nombreArchivo);
            Scanner sc = new Scanner(archivo);
            String accesoActual = "";
            int anio = -1;
            int mes = -1;
            int dia = -1;
            int hora = -1;
            int minuto = -1;
            while (sc.hasNextLine()){
                accesoActual = sc.nextLine();
                String linea[] = accesoActual.toString().split(" ");
                anio = Integer.parseInt(linea[0]);
                mes = Integer.parseInt(linea[1]);
                dia = Integer.parseInt(linea[2]);
                hora = Integer.parseInt(linea[3]);
                minuto = Integer.parseInt(linea[4]);
                Acceso acceso = new Acceso(anio, mes, dia, hora, minuto);
                lineasAcceso.add(acceso);
            }
            sc.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
    
    public int obtenerHoraMasAccesos()
    {
        int horaADevolver = -1;
        int posicionHoraMasRepetida = -1;
        int mayor = 0;
        if (lineasAcceso.size() > 0){
            for (int i = 0; i < lineasAcceso.size(); i++){
                if (i > 0){
                    boolean encontrado = false;
                    int j = 0;
                    while (j < hora.size() && encontrado == false){
                        if (lineasAcceso.get(i).getHora() == hora.get(j)){
                            numeroVecesHora.set(j, (numeroVecesHora.get(j) + 1));
                            encontrado = true;
                        }
                        j++;
                    }
                    if (encontrado == false){
                        hora.add(lineasAcceso.get(i).getHora());
                        numeroVecesHora.add(1);
                    }
                }
                else{
                    hora.add(lineasAcceso.get(i).getHora());
                    numeroVecesHora.add(1);
                }
            }
            
            for (int i = 0; i < numeroVecesHora.size() - 1; i++){
                if (mayor < numeroVecesHora.get(i)){
                    posicionHoraMasRepetida = i;
                    mayor = numeroVecesHora.get(i);
                }
            }
            horaADevolver = hora.get(posicionHoraMasRepetida);
            System.out.println("La hora a la que se producen mas accesos es: " + hora.get(posicionHoraMasRepetida));
        }
        else{
            System.out.println("Aun no se han registrado accesos.");
        }
        while (lineasAcceso.size() > 0){
            lineasAcceso.remove(lineasAcceso.size() - 1);
        }
        while (hora.size() > 0){
            hora.remove(hora.size() - 1);
        }
        while (numeroVecesHora.size() > 0){
            numeroVecesHora.remove(numeroVecesHora.size() - 1);
        }
        return horaADevolver;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
