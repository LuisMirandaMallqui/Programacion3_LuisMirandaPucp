/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.hilos;

/**
 *
 * @author luism
 */
public class Retiro implements Runnable {

    private final double monto;
    private final CuentaBancaria cuenta;

    public Retiro(CuentaBancaria cuenta, double monto) {
        this.monto = monto;
        this.cuenta = cuenta;
    }

    @Override
    public void run() {
        String nombreCliente = Thread.currentThread().getName();
        this.cuenta.retirar(nombreCliente, monto);
    }
}
