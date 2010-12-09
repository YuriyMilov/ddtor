using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using System.IO;
using Simply.Domain.Utility;
using SimplySDK;
using SimplySDK.Support;
using SimplySDK.GeneralModule;
using SimplySDK.PayableModule;
using SimplySDK.ReceivableModule;
using SimplySDK.PayrollModule;
using SimplySDK.InventoryModule;
using SimplySDK.ProjectModule;
using System.Xml;
using Excel = Microsoft.Office.Interop.Excel;


namespace sa
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            richTextBox1.Clear();
           string username = textBox1.Text;
            string password = textBox2.Text;
            string saiFile = textBox3.Text;
            string sajFile = string.Empty;
            richTextBox1.AppendText("Checking the file ...\n\n");
            saiFile = saiFile.ToUpper();
            if (!saiFile.EndsWith(".SAI"))
            {
                richTextBox1.AppendText("Not a valid Simply Accounting file.");
                return;
            }

            if (!File.Exists(saiFile))
            {
                richTextBox1.AppendText("Simply Accounting file (.SAI) does not exist.");
                return;
            }

            sajFile = saiFile.Replace(".SAI", ".SAJ");
            if (!Directory.Exists(sajFile))
            {
                richTextBox1.AppendText("Simply Accounting folder (.SAJ) does not exist.");
                return;
            }

           richTextBox1.AppendText("Opening database...\n\n");

            if (SDKInstanceManager.Instance.OpenDatabase(saiFile, username, password, false, "Simply SDK Sample Program", "SASDK", 1))
            {
                try
                {
                    richTextBox1.AppendText("\n 1 \n");
                    GetCustomer();
                    richTextBox1.AppendText("\n 2 \n");
                }
                finally
                {
                    richTextBox1.AppendText("\n 3 \n");
                    SDKInstanceManager.Instance.CloseDatabase();
                }
            }
            richTextBox1.AppendText("\n 4 \n");
        }


        private void GetCustomer()
        {
            SDKDatabaseUtility uu = new SDKDatabaseUtility();
            string s = (string)uu.RunScalerQuery("SELECT sName FROM tCustomr");
            //         string s = (string)uu.RunScalerQuery("SELECT sName FROM tAccount");
            richTextBox1.AppendText(s);
            int i = uu.RunSelectQuery("SELECT * FROM tCustomr");
  
            //CreateWorkbook(uu.GetDataSetFromLastSelectQuery());
        }




    } 
}