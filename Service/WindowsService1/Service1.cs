using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Diagnostics;
using System.ServiceProcess;
using System.Text;
using System.Timers;
using System.Data.OleDb;
using System.Threading;


namespace WindowsService1
{
    public partial class Service1 : ServiceBase
    {
        public Service1()
        {
 
            InitializeComponent();


            int i = get_interval(0);



           // richTextBox1.Clear();

           // string s = "";

            if (i > 0)
            {
                bool bb = check_time(0);
                if (bb)
                    m1();
                timer.Elapsed += new ElapsedEventHandler(timer_Tick);// Everytime timer ticks, timer_Tick will be called
                timer.Interval = i;// (1000) * (60);             // Timer will tick evert 60 seconds
                timer.Enabled = true;                       // Enable the timer
                timer.Start();

               // s = "Service 1 " + (i / 60000).ToString() + " min. period\r\n";
            }



            i = get_interval(1);

            if (i > 0)
            {
                if (check_time(1))
                    m2();
                timer2.Elapsed += new ElapsedEventHandler(timer_Tick2);// Everytime timer ticks, timer_Tick will be called
                timer2.Interval = i;// (1000) * (150);             // Timer will tick evert 60 seconds
                timer2.Enabled = true;                       // Enable the timer
                timer2.Start();

                //s = s + "Service 2 " + (i / 60000).ToString() + " min. period";

            }
            //richTextBox1.Text = s;                           // Start the timer
        }

        System.Timers.Timer timer = new System.Timers.Timer();
        System.Timers.Timer timer2 = new System.Timers.Timer();
        protected override void OnStart(string[] args)
        {
            // TODO: Add code here to start your service.



        }

        protected override void OnStop()
        {
            // TODO: Add code here to perform any tear-down necessary to stop your service.
        }

        public static string db(string s)
        {
            System.Collections.SortedList dsnList = new System.Collections.SortedList();
            Microsoft.Win32.RegistryKey reg = (Microsoft.Win32.Registry.LocalMachine).OpenSubKey("Software");
            if (reg != null)
            {
                reg = reg.OpenSubKey("ODBC");
                if (reg != null)
                {
                    reg = reg.OpenSubKey("ODBC.INI");
                    if (reg != null)
                    {
                        reg = reg.OpenSubKey(s);
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
        
        
        public static int set_period()
        {

          conn.Open();
            OleDbDataAdapter adap = new OleDbDataAdapter(s, conn);
            conn.Close();
            System.Data.DataTable tt = new System.Data.DataTable();
            adap.Fill(tt);

            int i_time_set = Int32.Parse(tt.Rows[0][2].ToString().Trim());
            return 0;
        }

        public static int get_interval(int i)
        {
            int k = -1;
            conn.Open();
            OleDbDataAdapter adap = new OleDbDataAdapter(s, conn);
            conn.Close();
            System.Data.DataTable tt = new System.Data.DataTable();
            adap.Fill(tt);



            if (tt.Rows.Count > i)
            {



                int j = Int32.Parse(tt.Rows[i][2].ToString());




                //TIME_SET=4 
                //"Run Every Hour"
                //Interval=1000*60*60

                if (j == 4)
                    k = 4 * 60 * 60 * 1000;



                //TIME_SET=3
                //"Run Every "+HOUR_SET+" Hour"
                //Interval=HOUR_SET*1000*60*60


                if (j == 3)
                    k = Int32.Parse(tt.Rows[i][4].ToString()) * 60 * 60 * 1000;


                //TIME_SET=2
                //"Run Every Minute" 
                //Interval=1000*60

                if (j == 2)
                    k = 60 * 1000;


                //TIME_SET=1 
                //"Run Every "+MINS_SET+" Minute" 
                //Interval=MINS_SET*1000*60

                if (j == 1)
                    k = Int32.Parse(tt.Rows[i][3].ToString()) * 60 * 1000;

                if (k == 0)
                    k = -1;

            }
            else k = -1;

            return k;

        }

        public static bool check_time(int i)
        {

            bool bbb = true;
            conn.Open();
            OleDbDataAdapter adap = new OleDbDataAdapter(s, conn);
            conn.Close();
            System.Data.DataTable tt = new System.Data.DataTable();
            adap.Fill(tt);

            bool zzz = (bool)(tt.Rows[i][5]);
            int n = 0, j = 0;

            if (zzz)
            {
                string wattime = (string)(tt.Rows[i][6]);
                string uptime = (string)(tt.Rows[i][7]);


                if (wattime.ToUpper().IndexOf("AM") > -1)
                    n = Int32.Parse(wattime.ToUpper().Replace("AM", ""));

                j = wattime.ToUpper().IndexOf("PM");

                if (j > -1)
                {
                    n = Int32.Parse(wattime.ToUpper().Replace("PM", ""));
                    n = n + 12;
                }

                int m = DateTime.Now.ToLocalTime().Hour;
                if (m > n || m == n)
                {
                    if (uptime.Length == 4)
                        if (uptime.ToUpper().IndexOf("AM") > -1)
                            n = Int32.Parse(uptime.ToUpper().Replace("AM", ""));
                        else
                        {
                            n = Int32.Parse(uptime.ToUpper().Replace("PM", ""));
                            n = n + 12;
                        }
                    n = DateTime.Now.ToLocalTime().Hour.CompareTo(n);
                    if (n < 0)
                        bbb = true;
                    else
                        bbb = false;
                }
                else
                    bbb = false;


                bool sun = (bool)(tt.Rows[i][8]);
                bool mon = (bool)(tt.Rows[i][9]);
                bool tue = (bool)(tt.Rows[i][10]);
                bool wen = (bool)(tt.Rows[i][11]);
                bool thu = (bool)(tt.Rows[i][12]);
                bool fri = (bool)(tt.Rows[i][13]);
                bool sat = (bool)(tt.Rows[i][14]);

                s = DateTime.Now.ToLocalTime().DayOfWeek.ToString();
                bbb = bbb && (
                    (sun && s.Equals("Sunday")) ||
                    (mon && s.Equals("Monday")) ||
                    (tue && s.Equals("Tuesday")) ||
                    (wen && s.Equals("Wednesday")) ||
                    (thu && s.Equals("Thursday")) ||
                    (fri && s.Equals("Friday")) ||
                    (sat && s.Equals("Saturday"))
                    );

            }
            return bbb;
        }

        public static string fd(string s)
        {

            if (s.Length == 10)
                return s;
            else
            {
                try
                {
                    string s1 = s.Substring(0, s.IndexOf("/"));
                    if (s1.Length == 1)
                    {
                        s1 = "0" + s1;
                    }

                    string s2 = s.Substring(s.IndexOf("/") + 1);
                    s2 = s2.Substring(0, s2.IndexOf("/"));

                    if (s2.Length == 1)
                    {
                        s2 = "0" + s2;
                    }

                    string s3 = s.Substring(s.LastIndexOf("/") + 1);

                    s = s1 + "/" + s2 + "/" + s3;
                }
                catch (Exception ee) { }
                return s;
            }
        }


        public static string save(string s_cust_id)
        {
            conn.Open();
            OleDbDataAdapter adap = new OleDbDataAdapter(s, conn);
            conn.Close();
            System.Data.DataTable tt = new System.Data.DataTable();
            adap.Fill(tt);
            s = "";
            string s_event_id = "", s_postal = "", s_date_enter = "", s_time_enter = "", s_po = "", s_date_time = "";
            for (int i = 0; i < tt.Rows.Count; i++)
            {
                s_date_enter = tt.Rows[i][2].ToString().Trim();
                s_date_enter = s_date_enter.Substring(0, s_date_enter.IndexOf(" "));

                if (s_date_enter.IndexOf("1899") > 0)
                    s_date_enter = "";



                s_time_enter = tt.Rows[i][3].ToString().Trim();

                if (s_time_enter.Length == 5)
                    s_time_enter = s_time_enter + ":00";
                s_date_time = s_date_enter + " " + s_time_enter;
                s_po = tt.Rows[i][4].ToString().Trim();

                if (tt.Rows[i][1].ToString().Equals("A") || tt.Rows[i][1].ToString().Equals("B"))
                {
                    s_event_id = "521";
                    conn.Open();
                     conn.Close();
                    System.Data.DataTable tt2 = new System.Data.DataTable();
                    adap2.Fill(tt2);

                    if (tt2.Rows.Count > 0)
                        s_postal = tt2.Rows[0][0].ToString().Trim();

                }
                else
                    if (tt.Rows[i][1].ToString().Equals("C"))
                    {
                        s_event_id = "531";
                        conn.Open();
                        conn.Close();
                        System.Data.DataTable tt3 = new System.Data.DataTable();
                        adap3.Fill(tt3);



                        if (tt3.Rows.Count == 0)
                        {
                            conn.Open();
                            conn.Close();
                            adap3.Fill(tt3);
                        }

                        if (tt3.Rows.Count > 0)
                        {
                            s_postal = tt3.Rows[0][0].ToString().Trim(); ;

                            s_date_enter = tt3.Rows[0][1].ToString().Trim();
                            if (s_date_enter.IndexOf(" ") > 0)
                                s_date_enter = s_date_enter.Substring(0, s_date_enter.IndexOf(" "));

                            if (s_date_enter.IndexOf("1899") > 0)
                                s_date_enter = "";

                            s_time_enter = tt3.Rows[0][2].ToString().Trim();

                            if (s_time_enter.Length == 5)
                                s_time_enter = s_time_enter + ":00";
                            s_date_time = s_date_enter + " " + s_time_enter;

                        }

                    }


                    else
                        if (tt.Rows[i][1].ToString().Equals("D"))
                        {
                            s_event_id = "535";
                            conn.Open();
                            conn.Close();
                            System.Data.DataTable tt4 = new System.Data.DataTable();
                            adap4.Fill(tt4);

                            if (tt4.Rows.Count == 0)
                            {
                                conn.Open();
                               conn.Close();
                                adap4.Fill(tt4);
                            }

                            if (tt4.Rows.Count > 0)
                            {
                                s_postal = tt4.Rows[0][0].ToString().Trim(); ;

                                s_date_enter = tt4.Rows[0][1].ToString().Trim();
                                if (s_date_enter.IndexOf(" ") > 0)
                                    s_date_enter = s_date_enter.Substring(0, s_date_enter.IndexOf(" "));

                                if (s_date_enter.IndexOf("1899") > 0)
                                    s_date_enter = "";

                                if (s_time_enter.Length == 5)
                                    s_time_enter = s_time_enter + ":00";
                                s_date_time = s_date_enter + " " + s_time_enter;

                            }
                        }
                string sdd = "", smm = "", syyyy = "";
                if (s_date_enter.Length > 9)
                {
                    sdd = s_date_enter.Substring(0, 2);
                    smm = s_date_enter.Substring(3, 2);
                    syyyy = s_date_enter.Substring(6, 4);
                    s_date_enter = smm + "/" + sdd + "/" + syyyy;
                }
                s = s + s_po + ", " + s_event_id + ", " + s_postal + ", " + fd(s_date_enter) + ", " + s_time_enter + ",\r\n";
            }
            System.IO.StreamWriter file = new System.IO.StreamWriter("c:/_ftp.txt");



            file.WriteLine(s);
            file.Close();

            return s;
        }
  
        public static void send(string s)
        {

            FTP ftplib = new FTP();
            ftplib.OpenUpload("c:/_ftp.txt", s);
            int perc = 0;
            while (ftplib.DoUpload() > 0)
            {
                perc = (int)(((ftplib.BytesTotal) * 100) / ftplib.FileSize);
            }
        }


        public static void m1()
        {
            conn.Open();
            OleDbDataAdapter adap = new OleDbDataAdapter(s, conn);
            conn.Close();
            System.Data.DataTable tt = new System.Data.DataTable();
            adap.Fill(tt);
            // int i = tt.Rows.Count;

            // while (i-- > 0)
            if (tt.Rows.Count > 0)
            {
                if (check_time(0))
                {
                    save(tt.Rows[0][0].ToString());
                    s = DateTime.Now.ToLocalTime().ToString("yyyyMMdd") + "_" + DateTime.Now.ToLocalTime().ToString("HHmmss") + ".txt";
                    send(s);
                    Thread.Sleep(2000);
                }
            }
        }


        public static void m2()
        {
            conn.Open();
            OleDbDataAdapter adap = new OleDbDataAdapter(s, conn);
            conn.Close();
            System.Data.DataTable tt = new System.Data.DataTable();
            adap.Fill(tt);
            // int i =  tt.Rows.Count;

            //while (i-- > 0)
            if (tt.Rows.Count > 1)
            {
                if (check_time(1))
                {
                    save(tt.Rows[1][0].ToString());
                    s = DateTime.Now.ToLocalTime().ToString("yyyyMMdd") + "_" + DateTime.Now.ToLocalTime().ToString("HHmmss") + ".txt";
                    send(s);
                    Thread.Sleep(2000);
                }
            }
        }



        void timer_Tick(object sender, EventArgs e)
        {
            bool bb = check_time(0);
            if (bb)
                m1();

        }

        void timer_Tick2(object sender, EventArgs e)
        {
            bool bb = check_time(1);
            if (bb)
                m2();
        }

    }
}
