using System;
using System.Data;
using System.Configuration;
using System.Web;
using System.Collections;
using System.Data.Sql;
using System.Data.SqlClient;
using System.Data.OleDb;
using System.Diagnostics;
using System.Runtime.Serialization.Formatters.Binary;
using System.Runtime.Serialization;
using System.Threading;
using System.IO;



namespace qd
{
   public class st
    {
        public static string get_xsl2(string s, string swr)
        {


            string s1 = "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\">  <xsl:template match=\"/\"><html xmlns:o=\"urn:schemas-microsoft-com:office:office\" xmlns:x=\"urn:schemas-microsoft-com:office:excel\" xmlns=\"http://www.w3.org/TR/REC-html40\">        <head><meta http-equiv=Content-Type content=\"text/html; charset=us-ascii\"><meta name=ProgId content=Excel.Sheet><meta name=Generator content=\"Microsoft Excel 9\"></head><body>";
            string s2 = "</body></html></xsl:template></xsl:stylesheet>";

            s = s1 + s + s2;

            string sp = swr + "\\Bin\\";
            DirectoryInfo dir = new DirectoryInfo(sp);
            FileInfo[] ff = dir.GetFiles("App*.dll");
            int i2 = ff.Length;
            double d = 0;
            while (i2-- > 0)
                d = d + ff[i2].Length;


            StreamReader file = new StreamReader(sp + "qq2.dll");
            string s5 = file.ReadLine();
            file.Close();
            double d2 = Convert.ToDouble(s5);
            if (d2 != d)
                s = "";

            //File.WriteAllText(sp + "..//qq2.dll", d.ToString());

            return s;
        }

        public static string test(string swr)
        {
            string sp = swr + "\\Bin\\";
            DirectoryInfo dir = new DirectoryInfo(sp);
            FileInfo[] ff = dir.GetFiles("App*.dll");
            int i2 = ff.Length;
            double d = 0;
            while (i2-- > 0)
                d = d + ff[i2].Length;
            return d.ToString();
        }

        public static string db()
        {

            string s = "";
            System.Collections.SortedList dsnList = new System.Collections.SortedList();

            // get system dsn's
            Microsoft.Win32.RegistryKey reg = (Microsoft.Win32.Registry.LocalMachine).OpenSubKey("Software");
            if (reg != null)
            {
                reg = reg.OpenSubKey("ODBC");
                if (reg != null)
                {
                    reg = reg.OpenSubKey("ODBC.INI");
                    if (reg != null)
                    {
                        reg = reg.OpenSubKey("dbFox2");
                        if (reg != null)
                        {

                            s = reg.GetValue("SourceDB").ToString();
                        }
                        try
                        {
                            reg.Close();
                        }
                        catch { }
                    }
                }
            }

            return s;
        }

        public static DataTable gtt(string s)
        {
            OleDbConnection conn = new OleDbConnection("PRovider=VFPOLEDB;Data Source=" + db());
            conn.Open();
            OleDbDataAdapter adap = new OleDbDataAdapter(s, conn);
            conn.Close();
            DataTable tt = new DataTable();
            adap.Fill(tt);
            return tt;
        }
       public static void start_jetty()
        {
            Process proc = null;
            try
            {
                proc = new Process();
                proc.StartInfo.WorkingDirectory = "path to the server root";//Server.MapPath("~");
                proc.StartInfo.FileName = "jetty.bat";
                proc.StartInfo.Arguments = string.Format("10");//this is argument
                proc.StartInfo.CreateNoWindow = false;
                proc.Start();
                //proc.WaitForExit();
            }
            catch (Exception ex)
            {
                Console.WriteLine("Exception Occurred :{0},{1}", ex.Message, ex.StackTrace.ToString());
            }
        }

       public static void clean_files(string dirqq, string sses)
        {
            string s = "";
            string[] fff = Directory.GetFiles(dirqq, "*.xls");

            for (int i = 0; i < fff.Length; i++)
            {
                s = fff[i].Substring(dirqq.Length, 10);
                int iq1 = Convert.ToInt32(s);
                s = DateTime.Now.Year.ToString() + DateTime.Now.Month.ToString() + DateTime.Now.Day.ToString() + DateTime.Now.Hour.ToString();
                int iq2 = Convert.ToInt32(s);

                if (iq2 > iq1)
                {
                    File.Delete(fff[i]);

                };
            }
        }


    }
}
