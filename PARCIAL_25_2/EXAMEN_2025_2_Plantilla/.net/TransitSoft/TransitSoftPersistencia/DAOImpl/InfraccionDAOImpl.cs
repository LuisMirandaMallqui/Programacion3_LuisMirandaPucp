using PUCP.TransitSoft.Db;
using PUCP.TransitSoft.Modelo;
using PUCP.TransitSoft.Persistencia.DAO;
using SoftInv.DAOImpl.Util;
using System;
using System.Collections;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.Common;
using System.Data.SqlClient;
using System.Linq;

namespace PUCP.TransitSoft.Persistencia.DAOImpl {
    public class InfraccionDAOImpl : DAOImplBase, IInfraccionDAO
    {
        private Infraccion infracion;

        public InfraccionDAOImpl() : base()
        {
            infracion = null;
        }

        public int Crear(Infraccion modelo)
        {
            
            DBManager dbManager = DBFactoryProvider.GetManager();
            using (DbConnection conn = dbManager.GetConnection())
            {
                conn.Open();
                infracion = modelo;
                base.Ejecuta_DML(TipoOperacionDML.INSERTAR);
                
            }

            return this.infracion.Id;
        }

        public List<Infraccion> LeerTodos()
        {
            List<Infraccion> lista = new List<Infraccion>();
            DBManager dbManager = DBFactoryProvider.GetManager();
            using (DbConnection conn = dbManager.GetConnection())
            {
                conn.Open();

                lista = base.ListarTodos().Cast<Infraccion>().ToList();
            }

            return lista;
        }

        protected override string ObtenerProcedimientoParaListarTodos()
        {
            string sql = "EXEC listarInfracciones";
            return sql;
        }

        protected override string ObtenerProcedimientoParaModificacion()
        {
            string sql = "EXEC insertarInfraccion " +
                "@p_placa, @p_velocidad, @p_limite, @p_exceso, " +
                "@p_vehiculo_marca, @p_vehiculo_modelo, @p_vehiculo_anho, " +
                "@p_propietario_dni, @p_propietario_nombres, @p_propietario_apellidos, " +
                "@p_propietario_direccion, @p_camara_modelo, @p_camara_codigo_serie, " +
                "@p_camara_latitud, @p_camara_longitud, @p_monto, " +
                "@p_fecha_captura, @p_fecha_registro, @p_id OUTPUT";
            return sql;
        }

        protected override void IncluirValorDeParametrosParaModificacion()
        {
            AgregarParametro("@p_placa", this.infracion.Placa);
            AgregarParametro("@p_velocidad", this.infracion.Velocidad);
            AgregarParametro("@p_limite",this.infracion.Limite);
            AgregarParametro("@p_exceso",this.infracion.Exceso);
            AgregarParametro("@p_vehiculo_marca",this.infracion.MarcaVehiculo);
            AgregarParametro("@p_vehiculo_modelo",this.infracion.ModeloVehiculo);
            AgregarParametro("@p_vehiculo_anho",this.infracion.AnhoVehiculo);
            AgregarParametro("@p_propietario_dni",this.infracion.DniPropietario);
            AgregarParametro("@p_propietario_nombres",this.infracion.NombresPropietario);
            AgregarParametro("@p_propietario_apellidos",this.infracion.ApellidosPropietario);
            AgregarParametro("@p_propietario_direccion",this.infracion.DireccionPropietario);
            AgregarParametro("@p_camara_modelo",this.infracion.ModeloCamara);
            AgregarParametro("@p_camara_codigo_serie",this.infracion.CodigoSerieCamara);
            AgregarParametro("@p_camara_latitud",this.infracion.Latitud);
            AgregarParametro("@p_camara_longitud",this.infracion.Longitud);
            AgregarParametro("@p_monto",this.infracion.Monto);
            AgregarParametro("@p_fecha_captura",this.infracion.FechaCaptura);
            AgregarParametro("@p_fecha_registro",this.infracion.FechaRegistro);

            SqlParameter outputParam = new SqlParameter("@p_id", this.infracion.Id);
            outputParam.Direction = ParameterDirection.Output;
            comando.Parameters.Add(outputParam);
        }

        protected override void AgregarObjetoALaLista(IList<Object> lista, DbDataReader lector)
        {
            try {
                this.InstanciarObjetoDelResultSet(lector);
                lista.Add(this.infracion);
            }
            catch (Exception ex)
            {
                throw new NotImplementedException("Instanciación incorrecta." + ex.Message, ex);
            }
        }

        protected override void InstanciarObjetoDelResultSet(DbDataReader lector)
        {
            /* this.infracion = new Infraccion();
             this.infracion.Id = lector.GetInt32(0);
             this.infracion.Placa = lector.GetString(1);
             this.infracion.Velocidad = lector.GetDouble(2);
             this.infracion.Limite = lector.GetDouble(3);
             this.infracion.Exceso = lector.GetDouble(4);
             this.infracion.MarcaVehiculo = lector.GetString(5);
             this.infracion.ModeloVehiculo = lector.GetString(6);
             this.infracion.AnhoVehiculo = lector.GetInt32(7);
             this.infracion.DniPropietario = lector.GetString(8);
             this.infracion.NombresPropietario = lector.GetString(9);
             this.infracion.ApellidosPropietario = lector.GetString(10);
             this.infracion.DireccionPropietario = lector.GetString(11);
             this.infracion.ModeloCamara = lector.GetString(12);
             this.infracion.CodigoSerieCamara = lector.GetString(13);
             this.infracion.Latitud = lector.GetInt64(14);
             this.infracion.Longitud = lector.GetInt64(15);
             this.infracion.Monto = lector.GetDouble(16);
             this.infracion.FechaCapturaTimestamp = lector.GetInt64(17);
             this.infracion.FechaRegistroTimestamp = lector.GetInt64(18);*/
            this.infracion = new Infraccion();
            try { this.infracion.Id = lector.GetInt32(0); }
            catch (Exception ex) { throw new Exception($"Error en columna 0 (Id): {ex.Message}"); }

            try { this.infracion.Placa = lector.GetString(1); }
            catch (Exception ex) { throw new Exception($"Error en columna 1 (Placa): {ex.Message}"); }

            try { this.infracion.Velocidad = lector.GetDouble(2); }
            catch (Exception ex) { throw new Exception($"Error en columna 2 (Velocidad): {ex.Message}"); }

            try { this.infracion.Limite = lector.GetDouble(3); }
            catch (Exception ex) { throw new Exception($"Error en columna 3 (Limite): {ex.Message}"); }

            try { this.infracion.Exceso = lector.GetDouble(4); }
            catch (Exception ex) { throw new Exception($"Error en columna 4 (Exceso): {ex.Message}"); }

            try { this.infracion.MarcaVehiculo = lector.GetString(5); }
            catch (Exception ex) { throw new Exception($"Error en columna 5 (MarcaVehiculo): {ex.Message}"); }

            try { this.infracion.ModeloVehiculo = lector.GetString(6); }
            catch (Exception ex) { throw new Exception($"Error en columna 6 (ModeloVehiculo): {ex.Message}"); }

            try { this.infracion.AnhoVehiculo = lector.GetInt32(7); }
            catch (Exception ex) { throw new Exception($"Error en columna 7 (AnhoVehiculo): {ex.Message}"); }

            try { this.infracion.DniPropietario = lector.GetString(8); }
            catch (Exception ex) { throw new Exception($"Error en columna 8 (DniPropietario): {ex.Message}"); }

            try { this.infracion.NombresPropietario = lector.GetString(9); }
            catch (Exception ex) { throw new Exception($"Error en columna 9 (NombresPropietario): {ex.Message}"); }

            try { this.infracion.ApellidosPropietario = lector.GetString(10); }
            catch (Exception ex) { throw new Exception($"Error en columna 10 (ApellidosPropietario): {ex.Message}"); }

            try { this.infracion.DireccionPropietario = lector.GetString(11); }
            catch (Exception ex) { throw new Exception($"Error en columna 11 (DireccionPropietario): {ex.Message}"); }

            try { this.infracion.ModeloCamara = lector.GetString(12); }
            catch (Exception ex) { throw new Exception($"Error en columna 12 (ModeloCamara): {ex.Message}"); }

            try { this.infracion.CodigoSerieCamara = lector.GetString(13); }
            catch (Exception ex) { throw new Exception($"Error en columna 13 (CodigoSerieCamara): {ex.Message}"); }

            try { this.infracion.Latitud = (long)lector.GetInt32(14); }
            catch (Exception ex) { throw new Exception($"Error en columna 14 (Latitud): {ex.Message}"); }

            try { this.infracion.Longitud = (long)lector.GetInt32(15); }
            catch (Exception ex) { throw new Exception($"Error en columna 15 (Longitud): {ex.Message}"); }

            try { this.infracion.Monto = (double)lector.GetDecimal(16); }
            catch (Exception ex) { throw new Exception($"Error en columna 16 (Monto): {ex.Message}"); }
           
            try {
                DateTime fechaCaptura = lector.GetDateTime(17);
                this.infracion.FechaRegistroTimestamp = new DateTimeOffset(fechaCaptura).ToUnixTimeMilliseconds();
            }
            catch (Exception ex) { throw new Exception($"Error en columna 17 (FechaCapturaTimestamp): {ex.Message}"); }

            try {
                DateTime fechaRegistro = lector.GetDateTime(18);
                this.infracion.FechaRegistroTimestamp = new DateTimeOffset(fechaRegistro).ToUnixTimeMilliseconds();
            }
            catch (Exception ex) { throw new Exception($"Error en columna 18 (FechaRegistroTimestamp): {ex.Message}"); }
        }

        protected override void LimpiarObjetoDelResultSet()
        {
            this.infracion = null;
        }

    }

}
