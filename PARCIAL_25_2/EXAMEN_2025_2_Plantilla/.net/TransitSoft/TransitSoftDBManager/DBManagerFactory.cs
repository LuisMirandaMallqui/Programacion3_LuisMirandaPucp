namespace PUCP.TransitSoft.Db {
    public abstract class DBManagerFactory {
        public abstract DBManager CrearDBManager(string host, int puerto,
                                                 string esquema, string usuario,
                                                 string password);
    }
}
