/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package pe.edu.pucp.hilos;

/**
 *
 * @author luism
 */
public class Main {

    public static void main(String[] args) {
        MiHilo t1 = new MiHilo();
        t1.setName("Hilo 1");

        t1.getState(); // para sacar el estado de un hilo
        
        t1.start(); //hilo 1, asincrono 
//        t1.run();  // main, sincrono
        Runnable tarea = new Tarea();
        Thread t2 = new Thread(tarea, "Hilo 2");
        t2.start();

        //puntero a funcion
        Thread t3 = new Thread(() -> { // A partir de Java8 tú puedes pasar la fraccion que va a ser concurrente
            System.out.println("Hilo: "
                    + Thread.currentThread().getName());
        }, "Hilo3");
        t3.start();

        //Yo quiero que esto se ejecute solamente cuando el resto de hilos han terminado
        //te aseguras que hilo principal termine al final
        
        try{
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException ex){
            
        }
        
        
        // Paralelo o concurrente? Se delega al SO
        System.out.println("Hilo principal");
        
        // COMO GESTIONAR  AREA CRITICA
        // EL monitor es como el candado 
        // Creo el objeto que se llama lock, le digo ya mira este sera el candado
        // Cuando venga un hilo, el candado solo sera usado por un solo hilo a la vez | MUTUAMENTE EXCLU = MUTEX
         // Area mut exclu se define en el RECURSO compartido, no tarea ni hilo principal
    }
}
