using System;
using Pregunta2.Properties;

namespace Pregunta2
{
    public class Principal
    {
        static void Main(string[] args)
        {
           //CancionBuilder cancionBuilder = new CancionBuilder();
            //Cancion cancion = cancionBuilder;
            Cancion cancion = new Cancion();
            cancion.Titulo = "Cuando estoy triste";
            cancion.AgregarInterpretes("Mercedes Sosa");
            cancion.AgregarCompositores("Jose Pedroni");
            cancion.AgregarCompositores("Damián Sánchez");
            cancion.Genero = GeneroMusical.FOLKLORE;
            cancion.Album  = "A que florezca mi pueblo";
            cancion.OtroTitulo = "Cajita de música";
            Console.WriteLine(cancion.ToString());
            /*

                .ConTitulo("Cuando estoy triste")
                .TambienConocidaComo("Cajita de música")
                .InterpretadoPor("Mercedes Sosa")
                .CompuestoPor("Jose Pedroni")                
                .CompuestoPor("Damián Sánchez")                
                .DelGenero(Genero.FOLKLORE)
                .EnElAlbum("A que florezca mi pueblo")
                .BuildCancion();
            Console.WriteLine(cancion);
            Console.WriteLine();

            cancion = new CancionBuilder()
                .ConTitulo("Sonata para violonchelo n.1 en fa mayor")
                .CompuestoPor("Ludwig van Beethoven")
                .DelGenero(Genero.CLASICA)
                .IdentificadoConOpus(5)
                .IdentificadoConSubOpus(1)
                .DedicadoA("Federico Guillermo II de Prusia")
                .BuildCancion();
            Console.WriteLine(cancion);
            Console.WriteLine();

            cancion = new CancionBuilder()
                .ConTitulo("Concierto para piano n.1 en do mayor")
                .CompuestoPor("Ludwig van Beethoven")
                .DelGenero(Genero.CLASICA)
                .IdentificadoConOpus(15)
                .DedicadoA("Princesa Barbara Odescalchi")
                .BuildCancion();
            Console.WriteLine(cancion);
            Console.WriteLine();
            */
            Console.Write("HOla");
        }
    }
}
