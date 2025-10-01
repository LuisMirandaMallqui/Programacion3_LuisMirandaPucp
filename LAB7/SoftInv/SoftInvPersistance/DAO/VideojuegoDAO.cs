using SoftInvModel;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SoftInvPersistance.DAO
{
    public interface VideojuegoDAO
    {
        int Insertar(VideojuegosDTO videojuego);

        VideojuegosDTO ObtenerPorId(int idVideojuego);

        BindingList<VideojuegosDTO> ListarTodos();

        int Modificar(VideojuegosDTO videojuego);

        int Eliminar(VideojuegosDTO videojuego);
    }
}
