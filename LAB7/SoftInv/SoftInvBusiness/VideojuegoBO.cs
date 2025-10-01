using SoftInv.DAO;
using SoftInvModel;
using SoftInvPersistance.DAO;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SoftInvBusiness
{
    public class VideojuegoBO
    {
        private VideojuegoDAO videojuegoDAO;

        public VideojuegoBO()
        {
            this.videojuegoDAO = new VideojuegoDAOImpl();
        }
        public int Insertar(int idGenero, char idCategoria, string nombreVideojuego, DateTime fechaLanzamiento, double precioAproxMercado, int numJugadoresMc)
        {
            VideojuegosDTO videojuegosDTO = new VideojuegosDTO();
            videojuegosDTO.Genero = new GenerosDTO();
            videojuegosDTO.Genero.IdGenero = idGenero;
            videojuegosDTO.Categoria = new CategoriasDTO();
            videojuegosDTO.Categoria.IdCategoria = idCategoria;
            videojuegosDTO.NombreVideojuego = nombreVideojuego;
            videojuegosDTO.FechaLanzamiento = fechaLanzamiento;
            videojuegosDTO.PrecioAproxMercado = precioAproxMercado;
            videojuegosDTO.NumJugadoresMc = numJugadoresMc;
            return this.videojuegoDAO.Insertar(videojuegosDTO);
        }

        public int Eliminar(int idVideojuego)
        {
            VideojuegosDTO videojuegosDTO = new VideojuegosDTO();
            videojuegosDTO.IdVideojuego = idVideojuego;
            return this.videojuegoDAO.Eliminar(videojuegosDTO);
        }

        public int Modificar(int idVideojuego, int idGenero, char idCategoria, string nombreVideojuego, DateTime fechaLanzamiento, double precioAproxMercado, int numJugadoresMc)
        {
            VideojuegosDTO videojuegosDTO = new VideojuegosDTO();
            videojuegosDTO.IdVideojuego = idVideojuego;
            videojuegosDTO.Genero = new GenerosDTO();
            videojuegosDTO.Genero.IdGenero = idGenero;
            videojuegosDTO.Categoria = new CategoriasDTO();
            videojuegosDTO.Categoria.IdCategoria = idCategoria;
            videojuegosDTO.NombreVideojuego = nombreVideojuego;
            videojuegosDTO.FechaLanzamiento = fechaLanzamiento;
            videojuegosDTO.PrecioAproxMercado = precioAproxMercado;
            videojuegosDTO.NumJugadoresMc = numJugadoresMc;
            return this.videojuegoDAO.Modificar(videojuegosDTO);
        }

        public VideojuegosDTO ObtenerPorId(int idVideojuego)
        {
            return this.videojuegoDAO.ObtenerPorId(idVideojuego);
        }

        public IList<VideojuegosDTO> ListarTodos()
        {
            return this.videojuegoDAO.ListarTodos();
        }
    }
}
