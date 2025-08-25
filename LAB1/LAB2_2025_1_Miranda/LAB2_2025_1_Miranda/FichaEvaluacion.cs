using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LAB2_2025_1_Miranda
{

    class FichaEvaluacion
    {
        //Se debe dejar en public si se quiere acceder en la propiedad
        public enum estado_candidato
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
        private estado_candidato estado; // Aquí almacenas el valor del enum

        public Postulante Candidato { get => candidato; set => candidato = value; }
        public DateTime Fecha_hora { get => fecha_hora; set => fecha_hora = value; }
        public int Evaluacion_expendiente { get => evaluacion_expendiente; set => evaluacion_expendiente = value; }
        public int Evaluacion_entrevista { get => evaluacion_entrevista; set => evaluacion_entrevista = value; }
        public int Evaluacion_examen { get => evaluacion_examen; set => evaluacion_examen = value; }
        public estado_candidato Estado
        {
            get => estado; // Devuelve el valor actual del estado
            set => estado = value; // Asigna un nuevo valor al estado
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
            this.estado = estado_candidato.SIN_EVALUACION;
        }
        public FichaEvaluacion(Postulante,DateTime,int,int,estado_candidato)
        {

        }
        public FichaEvaluacion(FichaEvaluacion otraFichaEvaluacion)
        {

        }
    }
}
