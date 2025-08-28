using System.Collections.Generic;
namespace Pregunta2.Properties
{
    public class CancionBuilder
    {
        ///<summary>
        ///Atributos
        ///</summary>
        private string titulo;
        private string otro_titulo;
        private List<string> interpretes;
        private List<string> compositores;
        private string album;
        private int opus;
        private int subOpus;
        private string dedicatoria;
        private Genero genero;

        ///<summary>
        ///Propiedades
        ///</summary>
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
            get
            {
                return new List<string>(interpretes);
            }
            set
            {
                if (value != null)
                {
                    interpretes = new List<string>(value);
                }
            }
        }
        public void AgregarInterpretes(string interprete)
        {
            interpretes.Add(interprete);
        }

        public List<string> Compositores
        {
            get
            {
                return new List<string>(compositores);
            }
            set
            {
                if (value != null)
                {
                    compositores = new List<string>(value);
                }
            }
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

        public int Opus
        {
            get => opus;
            set => opus = value;
        }

        public int SubOpus
        {
            get => subOpus;
            set => subOpus = value;
        }

        public string Dedicatoria
        {
            get => dedicatoria;
            set => dedicatoria = value;
        }

        public Genero Genero
        {
            get => genero;
            set => genero = value;
        }


        public CancionBuilder ConTitulo(string titulo)
        {
            this.titulo = titulo;
            return this;
        }


        public CancionBuilder TambienConocidaComo(string otro_titulo)
        {
            this.otro_titulo = otro_titulo;
            return this;
        }

        public CancionBuilder InterpretadoPor(string interprete)
        {
            this.interpretes.Add(interprete);
            return this;
        }

        public CancionBuilder CompuestoPor(string compositor)
        {
            this.compositores.Add(compositor);
            return this;
        }

        public CancionBuilder DelGenero(Genero genero)
        {
            this.genero = genero;
            return this;
        }
        public CancionBuilder EnElAlbum(string album)
        {
            this.album = album;
            return this;

        }
        public CancionBuilder IdentificadoConOpus(int opus)
        {
            this.opus = opus;
            return this;
        }
        public CancionBuilder IdentificadoConSubOpus(int subOpus)
        {
            this.subOpus = subOpus;
            return this;
        }
        public CancionBuilder DedicadoA(string dedicatoria)
        {
            this.dedicatoria = dedicatoria;
            return this;
        }
        public Cancion BuildCancion()
        {
            return new Cancion(titulo,otro_titulo,interpretes,compositores,album,opus,subOpus,dedicatoria,genero);

        }
        ///<summary>
        ///Metodos 
        ///</summary>

        public CancionBuilder()
        {
            interpretes = new List<string>();
            compositores = new List<string>();
        }

    }
}