using PUCP.TransitSoft.Db;
using SoftInv.DAOImpl.Util;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data.Common;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PUCP.TransitSoft.Persistencia.DAOImpl
{
    public class DAOImplBase
    {
        protected DbConnection conexion;
        protected DbTransaction transaccion;
        protected DbCommand comando;
        protected DbDataReader lector;
        protected bool retornarLlavePrimaria;
        protected bool usarTransaccion;

        public DAOImplBase()
        {
            this.retornarLlavePrimaria = true;
            this.usarTransaccion = true;
        }

        protected void AbrirConexion()
        {
            this.conexion = DBFactoryProvider.GetManager().GetConnection();
            this.conexion.Open();
        }

        protected void CerrarConexion()
        {
            if (this.conexion != null)
            {
                this.conexion.Close();
            }
        }

        protected DbCommand CrearComando()
        {
            return new SqlCommand();
        }

        protected void IniciarTransaccion()
        {
            this.AbrirConexion();
            this.transaccion = this.conexion.BeginTransaction();
        }

        protected void ComitarTransaccion()
        {
            this.transaccion.Commit();
            this.transaccion = null;
        }

        protected void RollbackTransaccion()
        {
            if (this.transaccion != null)
            {
                this.transaccion.Rollback();
            }
            this.transaccion = null;
        }

        protected void ColocarSQLenComando(string sql)
        {
            this.comando.Connection = this.conexion;
            this.comando.CommandText = sql;
            this.comando.CommandType = System.Data.CommandType.Text;
            this.comando.Transaction = this.transaccion;
        }

        protected void EjecutarConsultaEnBD()
        {
            this.lector = this.comando.ExecuteReader();
        }

        protected void Ejecuta_DML(TipoOperacionDML tipoOperacion)
        {
            try
            {
                this.IniciarTransaccion();
                using (this.comando = this.CrearComando()) ;
                string sql = "";
                switch (tipoOperacion)
                {
                    case TipoOperacionDML.MODIFICAR:
                        // sql = this.GenerarSQLParaInsercion();
                        break;
                    case TipoOperacionDML.INSERTAR:
                        sql = this.ObtenerProcedimientoParaModificacion();
                        break;
                    case TipoOperacionDML.ELIMINAR:
                        //sql = this.GenerarSQLParaEliminacion();
                        break;
                }
                this.ColocarSQLenComando(sql);
                switch (tipoOperacion)
                {
                    case TipoOperacionDML.MODIFICAR:
                        //this.IncluirValorDeParametrosParaInsercion();
                        break;
                    case TipoOperacionDML.INSERTAR:
                        this.IncluirValorDeParametrosParaModificacion();
                        break;
                    case TipoOperacionDML.ELIMINAR:
                        //this.IncluirValorDeParametrosParaEliminacion();
                        break;
                }
                this.comando.ExecuteNonQuery();
                if (this.usarTransaccion)
                {
                    this.ComitarTransaccion();
                }
            }
            catch (Exception ex)
            {
                if (this.usarTransaccion)
                {
                    this.RollbackTransaccion();
                }
                throw new Exception(ex.Message);
            }
            finally
            {
                this.CerrarConexion();
            }
        }

        protected IList<Object> ListarTodos()
        {
            IList<Object> lista = new BindingList<Object>();
            this.comando = CrearComando();
            try
            {
                this.AbrirConexion();
                string sql = this.ObtenerProcedimientoParaListarTodos();
                this.ColocarSQLenComando(sql);
                this.EjecutarConsultaEnBD();
                while (this.lector.Read())
                {
                    this.AgregarObjetoALaLista(lista, this.lector);
                }
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message);
            }
            finally
            {
                this.CerrarConexion();
            }
            return lista;
        }

        protected virtual string ObtenerProcedimientoParaListarTodos()
        {
            throw new NotImplementedException("Método no sobreescrito.");
        }

        protected virtual string ObtenerProcedimientoParaModificacion()
        {
            throw new NotImplementedException("Método no sobreescrito.");
        }

        protected virtual void IncluirValorDeParametrosParaModificacion()
        {
            throw new NotImplementedException("Método no sobreescrito.");
        }


        protected void AgregarParametro(string nombre, object value)
        {
            DbParameter parametero = this.comando.CreateParameter();
            parametero.ParameterName = nombre;
            parametero.Value = value;
            this.comando.Parameters.Add(parametero);
        }

        protected virtual void AgregarObjetoALaLista(IList<Object> lista, DbDataReader lector)
        {
            throw new NotImplementedException("Método no sobreescrito.");
        }

        protected virtual void InstanciarObjetoDelResultSet(DbDataReader lector)
        {
            throw new NotImplementedException("Método no sobreescrito.");
        }

        protected virtual void LimpiarObjetoDelResultSet()
        {
            throw new NotImplementedException("Método no sobreescrito.");
        }

    }
}
