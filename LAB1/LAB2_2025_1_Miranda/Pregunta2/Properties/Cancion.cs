using System.Collections.Generic;

namespace Pregunta2.Properties
{
    public class Cancion
    {
        /*(1 punto) Implementar la clase Cancion considerando los atributos antes mencionados y utilizando
            una propiedad para cada atributo, además implemente el constructor sin parámetro. Implemente
            además el enumerado Genero
        */
        private string titulo;
        private string otro_titulo;
        private List<string> interpretes;
        private List<string> compositores;
        private string album;
        private string opus;
        private string subOpus;
        private string dedicatoria;
        private GeneroMusical genero;
        
        public string Titulo
        {
            get => titulo;
            set => titulo = value;
        }

        public string OtroTitulo
        {
            get => otro_titulo;
            set => otro_titulo = value;
        }
// las listas tienen setters y getters?
        public List<string> Interpretes
        {
            get => interpretes;
        }
        public void AgregarInterpretes(string interprete)
        {
            interpretes.Add(interprete);
        }

        public List<string> Compositores
        {
            get => compositores;
        }
        public void AgregarCompositores(string compositor)
        {
            compositores.Add(compositor);
        }


        public string Album
        {
            get => album;
            set => album = value;
        }

        public string Opus
        {
            get => opus;
            set => opus = value;
        }

        public string SubOpus
        {
            get => subOpus;
            set => subOpus = value;
        }

        public string Dedicatoria
        {
            get => dedicatoria;
            set => dedicatoria = value;
        }

        public GeneroMusical Genero
        {
            get => genero;
            set => genero = value;
        }

        public Cancion()
        {
            this.titulo = null;
            this.otro_titulo = null;
            this.interpretes = new List<string>(); 
            this.compositores = new List<string>(); 
            this.album = null;
            this.opus = null;
            this.subOpus = null;
            this.dedicatoria = null;
        }
        public Cancion(string titulo, string otroTitulo, List<string> interpretes, List<string> compositores, string album, string opus, string subOpus, string dedicatoria, GeneroMusical genero)
        {
            this.titulo = titulo;
            this.otro_titulo = otroTitulo;
            this.interpretes = new List<string>(interpretes);
            this.compositores = new List<string>(compositores);
            this.album = album;
            this.opus = opus;
            this.subOpus = subOpus;
            this.dedicatoria = dedicatoria;
            this.genero = genero;
        }
        /*
        (1 punto) Sobreescriba el método ToString en la clase Cancion de forma tal que retorne una cadena
            de caracteres que incluya los datos de la canción que no sean nulos. Vea el resultado de la Consola
            para el formato de la sobreescritura del método.
        */
        public override string ToString()
        {
            //   string resultado = $"PROCESO DE ADMISION: {cantidad_postulantes} postulantes, {cantidad_admitidos} admitidos\n\nLISTA DE ADMITIDOS:\n";

            string resultado = "";
            if (!string.IsNullOrEmpty(titulo))
                resultado += $"TITULO: {titulo}\n";
            if(!string.IsNullOrEmpty(otro_titulo))
                resultado += $"OTRO TITULO: {otro_titulo}\n";
            if(interpretes.Count > 0 && interpretes != null && !string.IsNullOrEmpty(interpretes[0]))
                resultado += $"INTERPRETADO POR: {string.Join(", ", interpretes)}\n";
            if(compositoresCount>0 && compositores != null && !string.IsNullOrEmpty(compositores[0]))
                resultado += $"COMPUESTO POR: {string.Join(", ", compositores)}\n";
            if(!string.IsNullOrEmpty(genero.ToString()))
                resultado += $"GENERO: {Funciones.CapitalizarPalabra(genero.ToString())}\n";
            if (!string.IsNullOrEmpty(opus))
                resultado += $"OPUS: {opus\n}";
            if (!string.IsNullOrEmpty(subOpus))
                resultado += $"OPUS: {subOpus\n}";
            if (!string.IsNullOrEmpty(dedicatoria))
                resultado += $"OPUS: {dedicatoria\n}";
            if (!string.IsNullOrEmpty(album))
                resultado += $"ALBUM: {album}\n";

                INTERPRETADO POR: {string.Join(", ", interpretes)}\nCOMPUESTO POR: {string.Join(", ", compositores)}\nGENERO: {Funciones.CapitalizarPalabra(genero.ToString())}";
            /*
             TITULO: Cuando estoy triste
             TAMBIEN CONOCIDA COMO: Cajita de música
             INTERPRETADO POR: Mercedes Sosa
             COMPUESTO POR: Jose Pedroni, Damián Sánchez
             TIPO: Folklore
             ALBUM: A que florezca mi puebl
            */
            return resultado;
        }
    }
}