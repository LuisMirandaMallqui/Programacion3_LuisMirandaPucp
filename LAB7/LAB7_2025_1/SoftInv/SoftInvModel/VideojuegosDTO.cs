using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SoftInvModel
{
    public class VideojuegosDTO
    {
        private int? idVideojuego;
        private GenerosDTO genero;
        private CategoriasDTO categoria;
        private string nombreVideojuego;
        private DateTime? fechaLanzamiento;
        private double? precioAproxMercado;
        private int? numJugadoresMc;

        public VideojuegosDTO()
        {
            this.IdVideojuego = null;
            this.Genero = null;
            this.Categoria = null;
            this.NombreVideojuego = null;
            this.FechaLanzamiento = null;
            this.PrecioAproxMercado = null;
            this.NumJugadoresMc = null;
        }

        public VideojuegosDTO(int? idVideojuego, GenerosDTO genero, CategoriasDTO categoria, string nombreVideojuego, DateTime? fechaLanzamiento, double? precioAproxMercado, int? numJugadoresMc)
        {
            this.IdVideojuego = idVideojuego;
            this.Genero = genero;
            this.Categoria = categoria;
            this.NombreVideojuego = nombreVideojuego;
            this.FechaLanzamiento = fechaLanzamiento;
            this.PrecioAproxMercado = precioAproxMercado;
            this.NumJugadoresMc = numJugadoresMc;
        }

        public int? IdVideojuego { get => idVideojuego; set => idVideojuego = value; }
        public GenerosDTO Genero { get => genero; set => genero = value; }
        public CategoriasDTO Categoria { get => categoria; set => categoria = value; }
        public string NombreVideojuego { get => nombreVideojuego; set => nombreVideojuego = value; }
        public DateTime? FechaLanzamiento { get => fechaLanzamiento; set => fechaLanzamiento = value; }
        public double? PrecioAproxMercado { get => precioAproxMercado; set => precioAproxMercado = value; }
        public int? NumJugadoresMc { get => numJugadoresMc; set => numJugadoresMc = value; }
    }
}
