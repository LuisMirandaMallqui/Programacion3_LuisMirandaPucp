using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;
using TransitSoftDBManager;
namespace TransitSoftTest
{
    [TestClass]
    public class UnitTest1
    {
        [TestMethod]
        public void TestMethod1()
        {
            Console.WriteLine(DBManager.Cifrar("pacoflaco123"));
        }
    }
}
