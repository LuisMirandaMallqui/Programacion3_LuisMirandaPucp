/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.hilos;

/**
 *
 * @author luism
 */
public class MiHilo extends Thread {

    @Override
    public void run() { // en este metodo se ejecuta el codigo (tarea), porcion de codigo repetitiva
        // de forma concurrente o paralela
        System.out.println("Hilo: " + Thread.currentThread().getName());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

    }
}
