package pe.edu.pucp.inf30.sesiones.publicaciones02.publicaciones;

import pe.edu.pucp.inf30.sesiones.publicaciones02.publicaciones.estandarAPA.ConcreteFactoryAPA;

//ERRORES DE ESTE CODIGO: REUTILIZA, FALTA REFACTORIZAR
//Sol: crear un paquete en main ARTICULO 
public class Publicaciones_v02 {

    public static void main(String[] args) {
        AbstractFactoryPublicacion factory  = new ConcreteFactoryAPA();
        Publicacion publicaciones[] = new Publicacion[3];
        publicaciones[0] = factory.crearArticulo("A relational model of data for large shared data banks", "Codd, Edgar Frank", 1970, "Communications of the ACM", 13, 6, "junio");
        String autores[]= {"Gamma, Erich", "Helm, Richard", "Johnson, Ralph", "Vlissides, John"};        
        publicaciones[1] = factory.crearLibro("Design Patterns: Elements of Reusable Object-Oriented Software", autores, 1970, "1er edición", "Massachusetts", "Addison-Wesley Professional");
        publicaciones[2] = factory.crearTesis("A system of logic based on ordinals", "Turing, Alan Mathison", 1938, Clase_de_Tesis.DOCTORADO, "Departamento de Matemáticas", "Universidad de Princeton", "Princeton", "NJ");                
        
        for (Publicacion publicacion : publicaciones) {
            publicacion.referenciar();
            Publicacion.orden_presentacion++;
        }
    }

}
