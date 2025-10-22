using System;
using System.CodeDom;
using System.Collections.Generic;
using System.Configuration;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TransitSoftDBManager
{
    public sealed class DBManager
    {
        private string hostname;
        private string port;
        private string username;
        private string password;
        private string database;

        private static readonly DBManager instance = new DBManager();
        public static DBManager Instance => instance;

        private DBManager()
        {
            hostname = ConfigurationManager.AppSettings["db.hostname"];
            username = ConfigurationManager.AppSettings["db.username"];
            password = DBManager.Descifrar(ConfigurationManager.AppSettings["db.password"]);
            database = ConfigurationManager.AppSettings["db.database"];
            port = ConfigurationManager.AppSettings["db.port"];
        }

        public string Hostname { get => hostname; set => hostname = value; }
        public string Port { get => port; set => port = value; }
        public string Username { get => username; set => username = value; }
        public string Password { get => password; set => password = value; }
        public string Database { get => database; set => database = value; }

        public static string Cifrar(string texto)
        {
            return Convert.ToBase64String(Encoding.Unicode.GetBytes(texto));
        }

        public static string Descifrar(string textoEncriptado)
        {
            return Encoding.Unicode.GetString(Convert.FromBase64String(textoEncriptado));
        }

    }
}
