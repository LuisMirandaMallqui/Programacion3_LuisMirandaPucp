using PUCP.TransitSoft.Modelo;
using PUCP.TransitSoft.Negocio.BO;
using PUCP.TransitSoft.Negocio.BOImpl;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.IO;
using System.Text.Json;

namespace TransitSoftGestionVial {
    public class Program {
        public static void Main(string[] args) {
            string inboxPath = ConfigurationManager.AppSettings["inbox.path"];
            List<Infraccion> infracciones = LeerInfracciones(inboxPath);

            IInfraccionBO bo = new InfraccionBOImpl();
            infracciones.ForEach(infraccion => {
                int id = bo.Crear(infraccion);
                infraccion.Id = id;
            });

            Console.WriteLine("***REPORTE DE INFRACCIONES***");
            Console.WriteLine();
            infracciones.ForEach(infraccion => {
                Console.WriteLine(infraccion);
            });
        }

        public static List<Infraccion> LeerInfracciones(string inboxPath) {
            List<Infraccion> infracciones = new List<Infraccion>();

            try {
                string fullPath = Path.GetFullPath(inboxPath);
                string[] archivos = Directory.GetFiles(fullPath, "*.json");

                foreach (string archivo in archivos) {
                    string jsonString = File.ReadAllText(archivo);

                    Infraccion infraccion = JsonSerializer.Deserialize<Infraccion>(jsonString);
                    if (infraccion != null) {
                        infracciones.Add(infraccion);
                        Console.WriteLine($"Infracción leída desde: {archivo}");
                    }
                }
            }
            catch (DirectoryNotFoundException) {
                Console.WriteLine($"Error: El directorio '{inboxPath}' no fue encontrado.");
            }
            catch (Exception ex) {
                Console.WriteLine($"Ocurrió un error al leer los archivos: {ex.Message}");
            }

            return infracciones;
        }
    }
}
