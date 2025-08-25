using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LAB2_2025_1_Miranda
{

    class FichaEvaluacion
    {
        ///<summary>
        ///constante para evitar número magico (que alguien externo se cuestione por qué ese valor en especifico
        ///</summary>
        private const int puntaje_minimo_admitido = 75;

        //Se debe dejar en public si se quiere acceder en la propiedad
        public enum EstadoCandidato
        {
            ADMITIDO,
            NO_ADMITIDO,
            SIN_EVALUACION
        }
        private Postulante candidato;
        private DateTime fecha_hora;
        private int evaluacion_expendiente;
        private int evaluacion_entrevista;
        private int evaluacion_examen;
        private EstadoCandidato estado; // Aquí almacenas el valor del enum

        public Postulante Candidato { get => candidato; set => candidato = value; }
        public DateTime Fecha_hora { get => fecha_hora; set => fecha_hora = value; }
        public int Evaluacion_expediente { get => evaluacion_expendiente; set => evaluacion_expendiente = value; }
        public int Evaluacion_entrevista { get => evaluacion_entrevista; set => evaluacion_entrevista = value; }
        public int Evaluacion_examen { get => evaluacion_examen; set => evaluacion_examen = value; }
        public EstadoCandidato Estado
        {
            ///<summary>
            /// Devuelve el valor actual del estado
            ///</summary> 
            get
            {
                if (evaluacion_expendiente + evaluacion_entrevista + evaluacion_examen > puntaje_minimo_admitido) estado = EstadoCandidato.ADMITIDO;
                else estado = EstadoCandidato.NO_ADMITIDO;
                return estado;
            }
            ///<summary>
            /// Asigna un nuevo valor al estado
            ///</summary> 
            set
            {
                estado = value;
            }
        }

        ///<summary>
        ///Constructor por defecto
        /// </summary>
        public FichaEvaluacion()
        {
            this.candidato = null;
            this.fecha_hora = DateTime.MinValue;
            this.evaluacion_expendiente = 0;
            this.evaluacion_entrevista = 0;
            this.evaluacion_examen = 0;
            this.estado = EstadoCandidato.SIN_EVALUACION;
        }
        ///<summary>
        ///Constructor por parametros
        /// </summary>
        public FichaEvaluacion(Postulante candidato, DateTime fecha_hora, int evaluacion_expendiente, int evaluacion_entrevista, int evaluacion_examen, EstadoCandidato estado)
        {
            this.candidato = new Postulante(candidato); 
            this.fecha_hora = fecha_hora;
            this.evaluacion_expendiente = evaluacion_expendiente;
            this.evaluacion_entrevista = evaluacion_entrevista;
            this.evaluacion_examen = evaluacion_examen;
            this.estado = estado;
        }
        ///<summary>
        ///Constructor copia
        /// </summary>
        public FichaEvaluacion(FichaEvaluacion otraFichaEvaluacion)
        {
            this.candidato = new Postulante(otraFichaEvaluacion.Candidato);
            this.fecha_hora = otraFichaEvaluacion.Fecha_hora;
            this.evaluacion_expendiente = otraFichaEvaluacion.Evaluacion_expediente;
            this.evaluacion_entrevista = otraFichaEvaluacion.Evaluacion_entrevista;
            this.evaluacion_examen = otraFichaEvaluacion.Evaluacion_examen;
            this.estado = otraFichaEvaluacion.Estado;
        }
    }
}
