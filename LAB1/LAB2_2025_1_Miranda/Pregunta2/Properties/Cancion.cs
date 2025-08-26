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
            set => interpretes = value;
        }

        public List<string> Compositores
        {
            get => compositores;
            set => compositores = value;
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
            this.interpretes = new List<string>(compositores);
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


            return;
        }
    }
}