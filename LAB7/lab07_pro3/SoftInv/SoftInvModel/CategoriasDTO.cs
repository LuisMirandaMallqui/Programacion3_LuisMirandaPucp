using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SoftInvModel
{
    public class CategoriasDTO
    {
        private char? idCategoria;
        private string descripcionCategoria;

        public CategoriasDTO() { 
            this.IdCategoria = null;
            this.DescripcionCategoria = null;
        }

        public CategoriasDTO(char? idCategoria, string descripcionCategoria)
        {
            this.IdCategoria = idCategoria;
            this.DescripcionCategoria = descripcionCategoria;
        }

        public char? IdCategoria { get => idCategoria; set => idCategoria = value; }
        public string DescripcionCategoria { get => descripcionCategoria; set => descripcionCategoria = value; }
    }
}
