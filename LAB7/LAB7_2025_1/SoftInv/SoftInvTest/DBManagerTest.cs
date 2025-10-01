using System;
using System.Data.Common;
using System.IO;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using SoftInv.Db;

namespace SoftInvTest
{
    [TestClass]
    public class DBManagerTest
    {
        //[TestMethod]
        //public void TestCifrarContra()
        //{
        //    Console.WriteLine("Cifrado de contra");
        //    string contra = DBManager.Cifrar("pacoflaco");
        //    Console.WriteLine(contra);
        //}

        //[TestMethod]
        //public void TestProbarLectura()
        //{
        //    Console.WriteLine("Lectura de contra");

        //    try
        //    {
        //        string contra = File.ReadAllText("password.txt");
        //        Console.WriteLine(contra);
        //    }
        //    catch (Exception ex)
        //    {
        //        throw new Exception($"No se pudo leer el archivo de la contraseña en la ruta . {ex.Message}");
        //    }
        //}


        [TestMethod]
        public void TestGetInstance()
        {
            Console.WriteLine("GetInstance");
            DBManager dBManager1 = DBManager.Instance;
            DBManager dBManager2 = DBManager.Instance;
            Assert.IsNotNull(dBManager1);
            Assert.IsNotNull(dBManager2);
            Assert.AreEqual(dBManager1, dBManager2);
        }

        [TestMethod]
        public void TestGetConnection()
        {
            Console.WriteLine("GetConnection");
            DbConnection conexion = DBManager.Instance.Connection;
            Assert.IsNotNull(conexion);
            conexion.Open();
        }
    }
}
