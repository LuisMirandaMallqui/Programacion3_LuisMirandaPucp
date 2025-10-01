using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SoftInvModel
{
    public class GenerosDTO
    {
        private int? idGenero;
        private string descripcionGenero;

        public GenerosDTO() { 
            this.IdGenero = null;
            this.DescripcionGenero = null;
        }

        public GenerosDTO(int? idGenero, string descripcionGenero)
        {
            this.IdGenero = idGenero;
            this.DescripcionGenero = descripcionGenero;
        }

        public int? IdGenero { get => idGenero; set => idGenero = value; }
        public string DescripcionGenero { get => descripcionGenero; set => descripcionGenero = value; }
    }
}
