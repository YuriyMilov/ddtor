using System;
using System.IO;
using System.Windows.Forms;
using Simply.Domain.Utility;
using SimplySDK;
using SimplySDK.Support;
using SimplySDK.GeneralModule;
using SimplySDK.PayableModule;
using SimplySDK.ReceivableModule;
using SimplySDK.PayrollModule;
using SimplySDK.InventoryModule;
using SimplySDK.ProjectModule;
using System.Data;





namespace SDKExample
{
   #region class ConsoleAlert
   /// <summary>
   /// This class overrides the default behaviour of alerts in the SDK.
   /// The default behaviour of the SDK throws exceptions.
   /// Here, Messages are displayed via the Console.
   /// </summary>
   public class ConsoleAlert : SDKAlert
   {
      /// <summary>
      /// AskAlert expects a return value of AlertResult.YES or AlertResult.NO
      /// </summary>
      public override AlertResult AskAlert(SimplyMessage message)
      {
         Console.WriteLine(message.Message);         

         string answer = Console.ReadLine().ToLower();

         if (answer[0] == 'y')
            return AlertResult.YES;
         else 
            return AlertResult.NO;
      }

      /// <summary>
      /// AskSaveAlert pops up when a ledger is unsaved and an action is performed  
      /// which attempts to close the current ledger.
      /// AskAlert expects a return value of AlertResult.YES or AlertResult.NO
      /// </summary>
      public override AlertResult AskSaveAlert()
      {
         Console.WriteLine("Save ledger record before continue?");

         string answer = Console.ReadLine().ToLower();

         if (answer[0] == 'y')
            return AlertResult.YES;
         else
            return AlertResult.NO;
      }

      /// <summary>
      /// YNCAlert expects a return value of AlertResult.YES or AlertResult.NO or AlertResult.CANCEL
      /// </summary>
      public override AlertResult YNCAlert(SimplyMessage message)
      {
         Console.WriteLine(message.Message);

         string answer = Console.ReadLine().ToLower();

         if (answer[0] == 'y')
            return AlertResult.YES;
         else if (answer[0] == 'n')
            return AlertResult.NO;
         else
            return AlertResult.CANCEL;
      }

      /// <summary>
      /// StopAlert displays generic error messages
      /// </summary>
      public override void StopAlert(SimplyMessage message)
      {
         Console.WriteLine(message.Message);
      }

      /// <summary>
      /// StopAlertNotShow displays confirmations
      /// </summary>
      public override bool StopAlertNotShow(SimplyMessage message)
      {
         Console.WriteLine(message.Message);
         return false;
      }
   }
   #endregion

   #region class MessageBoxAlert
   /// <summary>
   /// This class overrides the default behaviour of alerts in the SDK.
   /// The default behaviour of the SDK throws exceptions.
   /// Here, Messages are displayed via the MessageBoxes.
   /// </summary>
   public class MessageBoxAlert : SDKAlert
   {
      /// <summary>
      /// AskAlert expects a return value of AlertResult.YES or AlertResult.NO
      /// </summary>
      public override AlertResult AskAlert(SimplyMessage message)
      {
         DialogResult res = MessageBox.Show(message.Message, "", MessageBoxButtons.YesNo);

         if (res == DialogResult.Yes)
            return AlertResult.YES;
         else
            return AlertResult.NO;
      }

      /// <summary>
      /// AskSaveAlert pops up when a ledger is unsaved and an action is performed  
      /// which attempts to close the current ledger.
      /// AskAlert expects a return value of AlertResult.YES or AlertResult.NO
      /// </summary>
      public override AlertResult AskSaveAlert()
      {
         DialogResult res = MessageBox.Show("Save ledger record before continue?", "", MessageBoxButtons.YesNo);

         if (res == DialogResult.Yes)
            return AlertResult.YES;
         else
            return AlertResult.NO;
      }

      /// <summary>
      /// YNCAlert expects a return value of AlertResult.YES or AlertResult.NO or AlertResult.CANCEL
      /// </summary>
      public override AlertResult YNCAlert(SimplyMessage message)
      {
         DialogResult res = MessageBox.Show(message.Message, "", MessageBoxButtons.YesNoCancel);

         if (res == DialogResult.Yes)
            return AlertResult.YES;
         else if (res == DialogResult.No)
            return AlertResult.NO;
         else
            return AlertResult.CANCEL;
      }

      /// <summary>
      /// StopAlert displays generic error messages
      /// </summary>
      public override void StopAlert(SimplyMessage message)
      {
         MessageBox.Show(message.Message);
      }

      /// <summary>
      /// StopAlertNotShow displays confirmations
      /// </summary>
      public override bool StopAlertNotShow(SimplyMessage message)
      {
         MessageBox.Show(message.Message);
         return false;
      }
   }
   #endregion

   #region class NoAlert
   /// <summary>
   /// This class simulates the default behaviour of alerts in the SDK.
   /// The default behaviour of the SDK throws exceptions.
   /// It is intentionally empty, since no methods are overridden.
   /// </summary>
   public class NoAlert : SDKAlert
   {
   }
   #endregion

   #region class SDKExample
   /// <summary>
   /// Sample SDK program
   /// </summary>
   public class SDKExample
   {
      #region Misc Account Number Setup for SDKExample
      private const int SDK_ACCTBANK = 1054;
      private const int SDK_ACCTINVASSSET = 1521;
      private const int SDK_ACCTINVREV = 4021;
      private const int SDK_ACCTINVCOGS = 5011;
      private static short m_ActNumLength = 4;
      /// <summary>
      /// Sets m_ActNumLength according to database setting
      /// </summary>
      private static void GetAccountNumberLen()
      {
         m_ActNumLength = (short)(new SDKDatabaseUtility()).RunScalerQuery("SELECT nActNumLen FROM tCompOth");
      }

      /// <summary>
      /// Generates Account Number according to m_ActNumLength
      /// </summary>
      private static int MakeAccountNumber(int number)
      {
         return MakeAccountNumber(number, m_ActNumLength);
      }

      /// <summary>
      /// Generates Account Number according to m_ActNumLength
      /// </summary>
      private static int MakeAccountNumber(int number, short maxLen)
      {
         string zeros = string.Empty;
         string num_str = (number <= 0) ? "1" : num_str = Convert.ToString(number);

         for (int i = num_str.Length; i < Math.Min((short)8, maxLen); i++)
            zeros = zeros + "0";

         return Convert.ToInt32(num_str + zeros);
      }

      /// <summary>
      /// Checks if an account exists
      /// </summary>
      private static bool AccountExists(int number)
      {
         number = MakeAccountNumber(number);

         if ((int)(new SDKDatabaseUtility()).RunScalerQuery("SELECT COUNT(lId) FROM tAccount WHERE lId = " + number.ToString()) > 0)
            return true;

         return false;
      }
      #endregion

      #region AddLedgersExample
      /// <summary>
      ///  Add Ledgers Example
      /// </summary>
      public static void AddLedgersExample()
      {
         string input;

         Console.WriteLine();
         Console.WriteLine("-----------------------------------");
         Console.WriteLine("Adding Ledger Records Example");
         Console.WriteLine("-----------------------------------");
         Console.WriteLine();

         // accounts
         Console.Write("Enter a new Account Number:  ");
         input = Console.ReadLine();

         AccountLedger acctled = SDKInstanceManager.Instance.OpenAccountLedger();
         try
         {
            acctled.InitializeNew();
            acctled.Name = "Test Account";
            acctled.NameAlt = "Test AccountF";
            acctled.Number = Convert.ToInt32(input);
            acctled.Type = "G";
            if (acctled.Save())
               Console.WriteLine("Account added successfully");
         }
         catch (InvalidEntryException e)
         {
            Console.WriteLine(e.Message);
         }
         SDKInstanceManager.Instance.CloseAccountLedger();



         // vendors
         Console.WriteLine();
         Console.Write("Enter a new Vendor name:  ");
         input = Console.ReadLine();

         VendorLedger venled = SDKInstanceManager.Instance.OpenVendorLedger();
         venled.InitializeNew();
         venled.Name = input;
         venled.Contact = "Richard Zhanen";
         venled.Street1 = "7077-76th Avenue";
         venled.City = "New Westminister";
         venled.Province = "British Columbia";
         venled.PostalCode = "V8Y 3K7";
         venled.Country = "Canada";
         venled.Phone1 = "604-552-5149";
         venled.Phone2 = "604-555-7204";
         venled.Fax = "604-555-7211";
         venled.Email = "richard@hotmail.com";
         venled.WebSite = "www.richard.com";
         if (venled.Save())
            Console.WriteLine("Vendor added successfully");
         SDKInstanceManager.Instance.CloseVendorLedger();



         // customers
         Console.WriteLine();
         Console.Write("Enter a new Customer name:  ");
         input = Console.ReadLine();

         CustomerLedger custled = SDKInstanceManager.Instance.OpenCustomerLedger();
         custled.InitializeNew();
         custled.Name = input;
         custled.Contact = "Darryl Wolfe";
         custled.Street1 = "552 Columbia Street";
         custled.City = "Seattle";
         custled.Province = "Washington";
         custled.PostalCode = "98505";
         custled.Country = "USA";
         custled.Phone1 = "206-445-7007";
         custled.Phone2 = "206-283-6009";
         custled.Fax = "206-555-7012";
         custled.Email = "darryl@hotmail.com";
         custled.WebSite = "www.darryl.com";
         if (custled.Save())
            Console.WriteLine("Customer added successfully");
         SDKInstanceManager.Instance.CloseCustomerLedger();



         // employees
         Console.WriteLine();
         Console.Write("Enter a new Employee name:  ");
         input = Console.ReadLine();

         EmployeeLedgerBase empled = SDKInstanceManager.Instance.OpenEmployeeLedger();
         empled.InitializeNew();
         empled.Name = input;
         if (empled is EmployeeLedgerUS)
         {
            empled.Street1 = "656 Pitt Street";
            empled.City = "Los Angeles";
            empled.Province = "CALIFORNIA";
            empled.PostalCode = "91101";
            empled.Phone1 = "604-555-7888";
            empled.Phone2 = "604-555-7999";
            empled.SINSSN = "706921129";
            empled.BirthDate = new DateTime(1976, 1, 1);
            empled.HireDate = new DateTime(2001, 1, 1);
            empled.TaxTable = "CALIFORNIA";
            (empled as EmployeeLedgerUS).StateStatus = "Single";
            empled.PayPeriods = 24;
         }
         else if (empled is EmployeeLedger)
         {
            empled.Street1 = "656 Pitt Street";
            empled.City = "Burnaby";
            empled.Province = "British Columbia";
            empled.PostalCode = "V7T 3H9";
            empled.Phone1 = "604-555-7888";
            empled.Phone2 = "604-555-7999";
            empled.SINSSN = "706921129";
            empled.BirthDate = new DateTime(1976, 1, 1);
            empled.HireDate = new DateTime(2001, 1, 1);
            empled.TaxTable = "British Columbia";
            empled.PayPeriods = 24;
         }
         if (empled.Save())
            Console.WriteLine("Employee added successfully");
         SDKInstanceManager.Instance.CloseEmployeeLedger();



         // inventory
         Console.WriteLine();
         Console.Write("Enter a new Inventory number:  ");
         input = Console.ReadLine();

         InventoryLedger invled = SDKInstanceManager.Instance.OpenInventoryLedger();
         invled.InitializeNew();
         invled.Number = input;
         invled.Name = "Service1";
         invled.NameAlt = "Service1";
         invled.IsServiceType = true;
         invled.IsActivityType = false;
         invled.StockingUnit = "Hour";
         invled.StockingUnitAlt = "Hour";
         invled.RegularPrice = 10.49;
         invled.PreferredPrice = 10.49;
         if (invled.Save())
            Console.WriteLine("Inventory added successfully");
         SDKInstanceManager.Instance.CloseInventoryLedger();



         // projects
         Console.WriteLine();
         Console.Write("Enter a new Project name:  ");
         input = Console.ReadLine();

         ProjectLedger projled = SDKInstanceManager.Instance.OpenProjectLedger();
         projled.InitializeNew();
         projled.Name = input;
         projled.NameAlt = input;
         projled.StartDate = new DateTime(2001, 2, 2);
         if (projled.Save())
            Console.WriteLine("Project added successfully");
         SDKInstanceManager.Instance.CloseProjectLedger();
      }
      #endregion

      #region LoadLedgersExample
      /// <summary>
      ///  Load Ledgers Example
      /// </summary>
      public static void LoadLedgersExample()
      {
         string input;

         Console.WriteLine();
         Console.WriteLine("-----------------------------------");
         Console.WriteLine("Loading Ledger Records Example");
         Console.WriteLine("-----------------------------------");
         Console.WriteLine();

         // accounts
         Console.Write("Enter an Account Number:  ");
         input = Console.ReadLine();
         Console.WriteLine();
         AccountLedger acctled = SDKInstanceManager.Instance.OpenAccountLedger();
         try
         {
            if (acctled.LoadByAccountNumber(Convert.ToInt32(input)))
            {               
               Console.WriteLine("Number:        {0}", acctled.Number);
               Console.WriteLine("Name:          {0}", acctled.Name);
               Console.WriteLine("Name (Alt):    {0}", acctled.NameAlt);
               Console.WriteLine("Type:          {0}", acctled.Type);
               Console.WriteLine("Class:         {0}", acctled.Class);
               Console.WriteLine("CurrencyCode:  {0}", acctled.CurrencyCode);
            }
            else
            {
               Console.WriteLine("Account not found: " + input);
            }
         }
         catch (InvalidEntryException e)
         {
            Console.WriteLine(e.Message + ": " + input);
         }
         Console.WriteLine();
         SDKInstanceManager.Instance.CloseAccountLedger();



         // vendors
         Console.Write("Enter a Vendor Name:  ");
         input = Console.ReadLine();
         Console.WriteLine();
         VendorLedger venled = SDKInstanceManager.Instance.OpenVendorLedger();
         try
         {
            if (venled.LoadByName(input))
            {
               Console.WriteLine("Name:          {0}", venled.Name);
               Console.WriteLine("Contact:       {0}", venled.Contact);
               Console.WriteLine("Street1:       {0}", venled.Street1);
               Console.WriteLine("Street2:       {0}", venled.Street2);
               Console.WriteLine("City:          {0}", venled.City);
               Console.WriteLine("Province:      {0}", venled.Province);
               Console.WriteLine("PostalCode:    {0}", venled.PostalCode);
               Console.WriteLine("Country:       {0}", venled.Country);
               Console.WriteLine("Phone1:        {0}", venled.Phone1);
               Console.WriteLine("Phone2:        {0}", venled.Phone2);
               Console.WriteLine("Fax:           {0}", venled.Fax);
               Console.WriteLine("Email:         {0}", venled.Email);
               Console.WriteLine("WebSite:       {0}", venled.WebSite);
               Console.WriteLine("CurrencyCode:  {0}", venled.CurrencyCode);
               Console.WriteLine("TaxId:         {0}", venled.TaxId);
            }
            else
            {
               Console.WriteLine("Vendor not found: " + input);
            }
         }
         catch (InvalidEntryException e)
         {
            Console.WriteLine(e.Message + ": " + input);
         }
         Console.WriteLine();
         SDKInstanceManager.Instance.CloseVendorLedger();



         // customers
         Console.Write("Enter a Customer Name:  ");
         input = Console.ReadLine();
         Console.WriteLine();
         CustomerLedger cusled = SDKInstanceManager.Instance.OpenCustomerLedger();
         try
         {
            if (cusled.LoadByName(input))
            {
               Console.WriteLine("Name:          {0}", cusled.Name);
               Console.WriteLine("Contact:       {0}", cusled.Contact);
               Console.WriteLine("Street1:       {0}", cusled.Street1);
               Console.WriteLine("Street2:       {0}", cusled.Street2);
               Console.WriteLine("City:          {0}", cusled.City);
               Console.WriteLine("Province:      {0}", cusled.Province);
               Console.WriteLine("PostalCode:    {0}", cusled.PostalCode);
               Console.WriteLine("Country:       {0}", cusled.Country);
               Console.WriteLine("Phone1:        {0}", cusled.Phone1);
               Console.WriteLine("Phone2:        {0}", cusled.Phone2);
               Console.WriteLine("Fax:           {0}", cusled.Fax);
               Console.WriteLine("Email:         {0}", cusled.Email);
               Console.WriteLine("WebSite:       {0}", cusled.WebSite);
               Console.WriteLine("CurrencyCode:  {0}", cusled.CurrencyCode);
               Console.WriteLine("PriceList:     {0}", cusled.PriceList);
            }
            else
            {
               Console.WriteLine("Customer not found: " + input);
            }
         }
         catch (InvalidEntryException e)
         {
            Console.WriteLine(e.Message + ": " + input);
         }
         Console.WriteLine();
         SDKInstanceManager.Instance.CloseCustomerLedger();



         // employees
         Console.Write("Enter an Employee Name:  ");
         input = Console.ReadLine();
         Console.WriteLine();
         EmployeeLedgerBase empled = SDKInstanceManager.Instance.OpenEmployeeLedger();
         try
         {
            if (empled.LoadByName(input))
            {
               Console.WriteLine("Name:          {0}", empled.Name);
               Console.WriteLine("Street1:       {0}", empled.Street1);
               Console.WriteLine("Street2:       {0}", empled.Street2);
               Console.WriteLine("City:          {0}", empled.City);
               Console.WriteLine("State:         {0}", empled.Province);
               Console.WriteLine("Zip Code:      {0}", empled.PostalCode);
               Console.WriteLine("Phone1:        {0}", empled.Phone1);
               Console.WriteLine("Phone2:        {0}", empled.Phone2);
               Console.WriteLine("SSN:           {0}", empled.SINSSN);
               Console.WriteLine("BirthDate:     {0:d}", empled.BirthDate);
               Console.WriteLine("HireDate:      {0:d}", empled.HireDate);
               Console.WriteLine("PayPeriods:    {0}", empled.PayPeriods);
               Console.WriteLine("TaxTable:      {0}", empled.TaxTable);
               if (empled is EmployeeLedgerUS)
               {
                  Console.WriteLine("Federal Status:{0}", (empled as EmployeeLedgerUS).FederalStatus);
                  Console.WriteLine("State Status:  {0}", (empled as EmployeeLedgerUS).StateStatus);
                  Console.WriteLine("Federal Allowances:{0}", (empled as EmployeeLedgerUS).FederalAllowances);
                  Console.WriteLine("State Allowances:{0}", (empled as EmployeeLedgerUS).StateAllowances);
               }
            }
            else
            {
               Console.WriteLine("Employee not found: " + input);
            }
         }
         catch (InvalidEntryException e)
         {
            Console.WriteLine(e.Message + ": " + input);
         }
         Console.WriteLine();
         SDKInstanceManager.Instance.CloseEmployeeLedger();



         // inventory
         Console.Write("Enter an Inventory Part Code:  ");
         input = Console.ReadLine();
         Console.WriteLine();
         InventoryLedger invled = SDKInstanceManager.Instance.OpenInventoryLedger();
         try
         {
            if (invled.LoadByPartCode(input))
            {
               Console.WriteLine("Part Code:       {0}", invled.Number);
               Console.WriteLine("Name:            {0}", invled.Name);
               Console.WriteLine("Name (Alt):      {0}", invled.NameAlt);
               Console.WriteLine("IsServiceType:   {0}", invled.IsServiceType);
               Console.WriteLine("IsActivityType:  {0}", invled.IsActivityType);
               Console.WriteLine("StockingUnit:    {0}", invled.StockingUnit);
               Console.WriteLine("StockingUnit (Alt): {0}", invled.StockingUnitAlt);
               Console.WriteLine("RegularPrice:    {0}", invled.RegularPrice);
               Console.WriteLine("PreferredPrice:  {0}", invled.PreferredPrice);
               Console.WriteLine("AssetAccount:    {0:d}", invled.AssetAccount);
               Console.WriteLine("RevenueAccount:  {0:d}", invled.RevenueAccount);
               Console.WriteLine("ExpenseAccount:  {0}", invled.ExpenseAccount);
               Console.WriteLine("VarianceAccount: {0}", invled.VarianceAccount);
            }
            else
            {
               Console.WriteLine("Inventory not found: " + input);
            }
         }
         catch (InvalidEntryException e)
         {
            Console.WriteLine(e.Message + ": " + input);
         }
         Console.WriteLine();
         SDKInstanceManager.Instance.CloseInventoryLedger();



         // inventory
         Console.Write("Enter a Project Name:  ");
         input = Console.ReadLine();
         Console.WriteLine();
         ProjectLedger projled = SDKInstanceManager.Instance.OpenProjectLedger();
         
         try
         {
            if (projled.LoadByName(input))
            {
               Console.WriteLine("Name:          {0}", projled.Name);
               Console.WriteLine("Name (Alt):    {0}", projled.NameAlt);
               Console.WriteLine("StartDate:     {0:d}", projled.StartDate);


            }
            else
            {
               Console.WriteLine("Project not found: " + input);
            }
         }
         catch (InvalidEntryException e)
         {
            Console.WriteLine(e.Message + ": " + input);
         }
         Console.WriteLine();
         SDKInstanceManager.Instance.CloseProjectLedger();
      }
      #endregion

      #region ErrorCheckingExample
      /// <summary>
      /// Error Checking Example
      /// Must step through for this example
      /// </summary>
      public static void ErrorCheckingExample()
      {
         Console.WriteLine();
         Console.WriteLine("-----------------------------------");
         Console.WriteLine("Error Checking Example");
         Console.WriteLine("-----------------------------------");
         Console.WriteLine();

         // SimplyNoAccessException examples

         // Account Ledger
         AccountLedger acctled = SDKInstanceManager.Instance.OpenAccountLedger();
         acctled.InitializeNew();
         acctled.Number = MakeAccountNumber(1051);
         acctled.Name = "Petty Cash";
         acctled.Type = "H";        // Note: Setting account type to Group Heading
         try
         {
            // Trying to access an object that does not currently exist
            Console.WriteLine("Attempting set the class of a Header account...");
            acctled.Class = "Bank";    // This is the Account Class under the Class Options tab
         }
         catch (SimplyNoAccessException e)
         {
            // Cannot do this here because the current account type is a Group Heading
            // Group Headings cannot specify an account class (not available at all)
            Console.WriteLine(e.Message);
            Console.WriteLine();
         }
         SDKInstanceManager.Instance.CloseAccountLedger();



         // Inventory & Services Ledger
         InventoryLedger invled = SDKInstanceManager.Instance.OpenInventoryLedger();
         invled.InitializeNew();
         invled.IsServiceType = false;  // set the Type to Inventory
         try
         {
            // Trying to select the Activity (Time & Billing) checkbox
            Console.WriteLine("Attempting set to an activity when the item is not a service...");
            invled.IsActivityType = true;
         }
         catch (SimplyNoAccessException e)
         {
            // Cannot do this because the Activity (Time & Billing) checkbox doesn't
            // exist when the Type is Inventory
            Console.WriteLine(e.Message);
            Console.WriteLine();
         }
         SDKInstanceManager.Instance.CloseInventoryLedger();



         // InvalidEntryException examples

         // Account Ledger
         acctled = SDKInstanceManager.Instance.OpenAccountLedger();
         acctled.InitializeNew();
         acctled.Number = MakeAccountNumber(1501);
         try
         {
            // Set the Account Class to a class that doesn't exist
            Console.WriteLine("Attempting set an account class that does not exist [Fake Class]...");
            acctled.Class = "Fake Class";
         }
         catch (InvalidEntryException e)
         {
            // Cannot do this because this account class doesn't exist
            Console.WriteLine(e.Message);
            Console.WriteLine();
         }
         SDKInstanceManager.Instance.CloseAccountLedger();


         // Customer Ledger
         CustomerLedger custled = SDKInstanceManager.Instance.OpenCustomerLedger();
         custled.InitializeNew();
         custled.Name = "test customer";
         try
         {
            // Selecting a price list that does not exist
            Console.WriteLine("Attempting set an price list to an item that does not exist [Fake Price List]...");
            custled.PriceList = "Fake Price List";
         }
         catch (InvalidEntryException e)
         {
            // Cannot do this because the item being selected is not in the price list
            Console.WriteLine(e.Message);
            Console.WriteLine();
         }
         SDKInstanceManager.Instance.CloseCustomerLedger();
      }
      #endregion

      #region AlertMessagesExample
      /// <summary>
      ///  Alert Message example
      /// </summary>
      public static void AlertMessagesExample()
      {
         Console.WriteLine();
         Console.WriteLine("-----------------------------------");
         Console.WriteLine("Alert Messages Example");
         Console.WriteLine("-----------------------------------");
         Console.WriteLine();

         CustomerLedger custled = SDKInstanceManager.Instance.OpenCustomerLedger();

         // Create a new customer
         custled.Name = "Danny12345";
         custled.Save();

         // Create the same customer to show the handling of alerts (ConsoleAlert)
         custled.Save();

         // Create the same customer to show the handling of alerts (MessagBoxAlert)
         SDKInstanceManager.Instance.SetAlertImplementation(new MessageBoxAlert());
         custled.Save();

         // Set to have no alerts handled to show the catching of SimlyErrorMessageException
         SDKInstanceManager.Instance.SetAlertImplementation(new NoAlert());
         custled.Name = "Danny12345";
         try
         {
            custled.Save();
         }
         catch (SimplyErrorMessageException e)
         {
            // Able to catch the default exception if no alerts
            if (e.MessageId == SimplyMessageId.E71)
               Console.WriteLine("DUPLICATE CUSTOMER");
            else
               Console.WriteLine(e.Message);
         }
         SDKInstanceManager.Instance.CloseCustomerLedger();


         // demostrate catching of AlertNotImplementedException
         InventoryLedger invled = SDKInstanceManager.Instance.OpenInventoryLedger();
         invled.InitializeNew();
         invled.IsServiceType = false;
         try
         {
            invled.AssetAccount = MakeAccountNumber(1100).ToString();     // Setting an account that does not have the Asset class type will prompt the user
         }
         catch(AlertNotImplementedException e)
         {
            // Alerts are still off, and this message prompts the user
            // However, the program is unable to continue since this type of exception requires user input
            Console.WriteLine(e.Message);
         }
         SDKInstanceManager.Instance.CloseInventoryLedger();

         SDKInstanceManager.Instance.SetAlertImplementation(new ConsoleAlert());
      }
      #endregion

      #region DatabaseUtilityExamples
      /// <summary>
      /// Database Utility examples
      /// </summary>
      public static void DatabaseUtilityExamples()
      {
         Console.WriteLine();
         Console.WriteLine("-----------------------------------");
         Console.WriteLine("Usage of SDKDatabaseUtility Example");
         Console.WriteLine("-----------------------------------");
         Console.WriteLine();

         SDKDatabaseUtility util = new SDKDatabaseUtility();



         // Example of getting a string
         // Get all names from the vendor table
         int count = util.RunSelectQuery("SELECT sName FROM tVendor");
         string name;
         for (int index = 0; index < count; index++)
         {
            name = util.GetStringFromLastSelectQuery(index, 0);
            Console.WriteLine("RunSelectQuery(\"SELECT sName FROM tVendor\"): " + name);
         }
         Console.WriteLine();



         // Example of getting an integer
         // Get the asset account numbers that are not blank from the inventory table
         count = util.RunSelectQuery("SELECT lAcNAsset FROM tInvent WHERE lAcNAsset != 0");
         int assetAccount;
         for (int index = 0; index < count; index++)
         {
            assetAccount = util.GetIntFromLastSelectQuery(index, 0);
            Console.WriteLine("RunSelectQuery(\"SELECT lAcNAsset FROM tInvent WHERE lAcNAsset != 0\"): " + assetAccount.ToString());
         }
         Console.WriteLine();



         // Example of getting a short
         // Get all pay periods from the Employee table
         count = util.RunSelectQuery("SELECT nAcctClass FROM tAccount");
         short acctclass;
         for (int index = 0; index < count; index++)
         {
            acctclass = util.GetShortFromLastSelectQuery(index, 0);
            Console.WriteLine("RunSelectQuery(\"SELECT nAcctClass FROM tAccount\"): " + acctclass.ToString());
         }
         Console.WriteLine();



         // Example of getting a date time
         // Get all start dates from the project table
         count = util.RunSelectQuery("SELECT dtStart FROM tProject");
         DateTime? startDate;
         for (int index = 0; index < count; index++)
         {
            startDate = util.GetDateTimeFromLastSelectQuery(index, 0);
            Console.WriteLine("RunSelectQuery(\"SELECT dtStart FROM tProject\"): " + startDate.ToString());
         }
         Console.WriteLine();



         // Example of getting a double
         // Get all prices that are greater than 50 from the the inventory price table
         count = util.RunSelectQuery("SELECT dPrice FROM tInvPrc WHERE dPrice > 50");
         double price;
         for (int index = 0; index < count; index++)
         {
            price = util.GetDoubleFromLastSelectQuery(index, 0);
            Console.WriteLine("RunSelectQuery(\"SELECT dPrice FROM tInvPrc WHERE dPrice > 50\"): " + price.ToString());
         }
         Console.WriteLine();



         // Example of getting a data set
         // Get Vendor dataset
         int x = util.RunSelectQuery("SELECT sName FROM tVendor");
         DataSet s = util.GetDataSetFromLastSelectQuery();
         name = (string)s.Tables[0].Rows[0][0];
         Console.WriteLine("RunSelectQuery(\"SELECT sName FROM tVendor\"): " + name);
         Console.WriteLine();



         // Example of scaler query
         // Get count of records in vendor table where name is greater than 'M'
         long numRecords = (long)util.RunScalerQuery("SELECT count(*) FROM tVendor WHERE sName > 'M'");
         Console.WriteLine("RunScalerQuery(\"SELECT count(*) FROM tVendor WHERE sName > 'M'\"): " + numRecords.ToString() + " records");
         Console.WriteLine();



         // Example of non query
         // Update all record to "Canada" in vendor table
         int ret = util.RunNonQuery("UPDATE tVendor SET sCountry = 'Canada'");
         Console.WriteLine("RunNonQuery(\"UPDATE tVendor SET sCountry = 'Canada'\"): " + ret + "records affected");
         Console.WriteLine();
      }
      #endregion

      #region Process Journal

      /// <summary>
      ///  Add ledger records required for journal examples
      /// </summary>
      public static void SetupJournalsExample()
      {
         AccountLedger acctled = SDKInstanceManager.Instance.OpenAccountLedger();

         // Create 4 expense accounts
         int accountNum = 5611;
         for (int i = 1; i <= 4; i++)
         {
            if (!acctled.LoadByAccountNumber(MakeAccountNumber(accountNum)))
            {
               acctled.InitializeNew();
               acctled.Name = "Expense " + i;
               acctled.Number = MakeAccountNumber(accountNum);
               acctled.Type = "G";

               acctled.Save();
            }
            accountNum++;
         }

         // Create bank account
         if (!acctled.LoadByAccountNumber(MakeAccountNumber(SDK_ACCTBANK)))
         {
            acctled.InitializeNew();
            acctled.Name = "Checking Account";
            acctled.Number = MakeAccountNumber(SDK_ACCTBANK);
            acctled.Type = "A";
            acctled.Class = "Bank";

            acctled.Save();
         }

         // Create inventory asset
         if (!acctled.LoadByAccountNumber(MakeAccountNumber(SDK_ACCTINVASSSET)))
         {
            acctled.InitializeNew();
            acctled.Name = "Nails Asset";
            acctled.Number = MakeAccountNumber(SDK_ACCTINVASSSET);
            acctled.Type = "G";
            acctled.Class = "Inventory";

            acctled.Save();
         }

         // Create inventory revenue
         if (!acctled.LoadByAccountNumber(MakeAccountNumber(SDK_ACCTINVREV)))
         {
            acctled.InitializeNew();
            acctled.Name = "Nails revenue";
            acctled.Number = MakeAccountNumber(SDK_ACCTINVREV);
            acctled.Type = "G";
            acctled.Class = "Operating Revenue";

            acctled.Save();
         }

         // Create inventory COGS
         if (!acctled.LoadByAccountNumber(MakeAccountNumber(SDK_ACCTINVCOGS)))
         {
            acctled.InitializeNew();
            acctled.Name = "Nails Cogs";
            acctled.Number = MakeAccountNumber(SDK_ACCTINVCOGS);
            acctled.Type = "G";
            acctled.Class = "Cost of Goods Sold";

            acctled.Save();
         }
         SDKInstanceManager.Instance.CloseAccountLedger();


         // Create vendor
         VendorLedger vendled = SDKInstanceManager.Instance.OpenVendorLedger();
         vendled.InitializeNew();
         vendled.Name = "Sage Vendor";
         vendled.Save();
         SDKInstanceManager.Instance.CloseVendorLedger();


         // Create customer
         CustomerLedger custled = SDKInstanceManager.Instance.OpenCustomerLedger();
         custled.InitializeNew();
         custled.Name = "Sage Customer";
         custled.Save();
         SDKInstanceManager.Instance.CloseCustomerLedger();


         // Create inventory
         InventoryLedger invled = SDKInstanceManager.Instance.OpenInventoryLedger();
         if (!invled.LoadByPartCode("N-3347"))
         {
            invled.InitializeNew();
            invled.Number = "N-3347";
            invled.Name = "Nails";
            invled.IsServiceType = false;
            invled.IsActivityType = false;
            invled.StockingUnit = "Box";
            invled.RegularPrice = 1.49;

            invled.AssetAccount = MakeAccountNumber(SDK_ACCTINVASSSET).ToString();
            invled.RevenueAccount = MakeAccountNumber(SDK_ACCTINVREV).ToString();
            invled.ExpenseAccount = MakeAccountNumber(SDK_ACCTINVCOGS).ToString();
            invled.Save();
         }
         
         // Create service
         if (!invled.LoadByPartCode("CR-10"))
         {
            invled.InitializeNew();
            invled.Number = "CR-10";
            invled.Name = "Computer Repair";
            invled.IsServiceType = true;
            invled.IsActivityType = false;
            invled.StockingUnit = "Hour";
            invled.Save();
         }
         SDKInstanceManager.Instance.CloseInventoryLedger();


         // create project
         ProjectLedger projled = SDKInstanceManager.Instance.OpenProjectLedger();
         projled.InitializeNew();
         projled.Name = "Sage Project";
         projled.Save();
         SDKInstanceManager.Instance.CloseProjectLedger();
      }

      /// <summary>
      ///  Process General Journal Example
      /// </summary>
      public static void ProcessGeneralJournalExample()
      {
         Console.WriteLine();
         Console.WriteLine("-----------------------------------");
         Console.WriteLine("Processing General Journal Example");
         Console.WriteLine("-----------------------------------");
         Console.WriteLine();

         SetupJournalsExample();
         Console.WriteLine();

         // General Journal Example
         GeneralJournal genJourn = SDKInstanceManager.Instance.OpenGeneralJournal();

         genJourn.Source = "GJ 1";
         genJourn.Comment = "Test General Journal";

         genJourn.SetAccount(MakeAccountNumber(5611).ToString(), 1);
         genJourn.SetDebit(100.00, 1);
         genJourn.SetAccount(MakeAccountNumber(5612).ToString(), 2);
         genJourn.SetDebit(200.00, 2);
         genJourn.SetAccount(MakeAccountNumber(5613).ToString(), 3);
         genJourn.SetDebit(300.00, 3);
         genJourn.SetAccount(MakeAccountNumber(5614).ToString(), 4);
         genJourn.SetDebit(400.00, 4);

         genJourn.SetAccount(MakeAccountNumber(SDK_ACCTBANK).ToString(), 5);
         genJourn.SetCredit(1000.00, 5);

         genJourn.SetComment("Comment 1", 1);

         if (genJourn.Post())
            Console.WriteLine("Purchase invoice 'GJ 1' posting succeeded");
         else
            Console.WriteLine("Purchase invoice 'GJ 1' posting failed");
         Console.WriteLine();

         SDKInstanceManager.Instance.CloseGeneralJournal();
      }

      /// <summary>
      ///  Process Invoices Example
      /// </summary>
      public static void ProcessInvoicesExample()
      {
         Console.WriteLine();
         Console.WriteLine("-----------------------------------");
         Console.WriteLine("Processing Invoices Example");
         Console.WriteLine("-----------------------------------");
         Console.WriteLine();

         SetupJournalsExample();
         Console.WriteLine();

         
         // Purchase Invoice Example
         PurchasesJournal purJourn = SDKInstanceManager.Instance.OpenPurchasesJournal();
         string purInvoiceNum = "PJ" + (new Random().Next(9999).ToString());

         // Pay Later
         purJourn.SelectTransType(0); // invoice
         purJourn.SelectPaidByType("Pay Later");
         purJourn.InvoiceNumber = purInvoiceNum;
         purJourn.SelectAPARLedger("Sage Vendor");

         purJourn.SetItemNumber("N-3347", 1);
         purJourn.SetQuantity(100, 1);
         purJourn.SetUnit("Box", 1);
         purJourn.SetPrice(0.99, 1);

         purJourn.SetDescription("Item #2", 2);
         purJourn.SetQuantity(100, 2);
         purJourn.SetUnit("Each", 2);
         purJourn.SetPrice(0.49, 2);
         purJourn.SetLineAccount(MakeAccountNumber(5611).ToString(), 2);

         purJourn.SetFreightAmount(15.00);

         purJourn.SetTermDiscPercent(5.00);
         purJourn.SetTermDiscDay(10);
         purJourn.SetTermDiscNetDay(21);

         AdditionalInfo addInfo = purJourn.DoAdditionalInfo();
         addInfo.SetAdditionalComment("Comment");
         addInfo.SetAdditionalDate("Mar 15, 2015");
         addInfo.Save();

         if (purJourn.Post())
            Console.WriteLine("Purchase invoice '" + purInvoiceNum + "' posting succeeded");
         else
            Console.WriteLine("Purchase invoice '" + purInvoiceNum + "' posting failed");
         Console.WriteLine();

         // Lookup posted invoice
         purJourn.Undo();
         if (purJourn.LoadInvoiceForLookup("Sage Vendor", purInvoiceNum))
         {
            Console.WriteLine("Lookup purchase invoice succeeded");
            Console.WriteLine(purJourn.GetAPARLedgerName() + ":\t" + purJourn.InvoiceNumber);
            for (int lineNum = 1; lineNum <= purJourn.NumberOfJournalLines; lineNum++)
            {
               Console.WriteLine("Line {0}:\t{1}\t{2:f}\t{3}\t{4}\t{5:f}\t{6:f}\t{7}", 
                                 lineNum.ToString(),
                                 purJourn.GetItemNumber(lineNum),
                                 purJourn.GetQuantity(lineNum),
                                 purJourn.GetUnit(lineNum),
                                 purJourn.GetDescription(lineNum),
                                 purJourn.GetPrice(lineNum),
                                 purJourn.GetLineAmount(lineNum),
                                 purJourn.GetAccountNumber(lineNum));
            }
            Console.WriteLine("Total: " + purJourn.GetTotalAmount().ToString());
            Console.WriteLine();

            // Adjust currently loaded invoice
            if (purJourn.AdjustInvoiceFromLookup())
            {
               for (int lineNum = 1; lineNum <= purJourn.NumberOfJournalLines; lineNum++)
               {
                  if (purJourn.GetQuantity(lineNum) > 2)
                     purJourn.SetQuantity(purJourn.GetQuantity(lineNum) - 1.0, lineNum);
               }
               if (purJourn.Post())
                  Console.WriteLine("Purchase invoice '" + purInvoiceNum + "' adjustment succeeded");
               else
                  Console.WriteLine("Purchase invoice '" + purInvoiceNum + "' adjustment failed");
               Console.WriteLine();
            }
         }

         SDKInstanceManager.Instance.ClosePurchasesJournal();


         // Sales Invoice Example
         SalesJournal salJourn = SDKInstanceManager.Instance.OpenSalesJournal();

         // Cash
         salJourn.SelectTransType(0);  // invoice
         salJourn.SelectPaidByType("Cash");
         salJourn.InvoiceNumber = "SJ 1";
         salJourn.SetShipDate(salJourn.GetJournalDate());
         salJourn.SelectAPARLedger("Sage Customer");

         salJourn.SelectShiptoAddressName("<Mailing Address>");
         salJourn.SetShipToAddressLine("DOS Ltd", 1);
         salJourn.SetShipToAddressLine("Tom Vu", 2);
         salJourn.SetShipToAddressLine("7789 Rich Street", 3);
         salJourn.SetShipToAddressLine("Richmond, BC", 4);
         salJourn.SetShipToAddressLine("V3K 2Q2", 5);

         salJourn.SetItemNumber("CR-10", 1);
         salJourn.SetQuantity(1, 1);
         salJourn.SetUnit("Hour", 1);
         salJourn.SetDescription("Computer Repair - No Warranty", 1);
         salJourn.SetPrice(90.00, 1);
         salJourn.SetLineAmount(90.00, 1);
         salJourn.SetLineAccount(MakeAccountNumber(SDK_ACCTINVREV).ToString(), 1);

         salJourn.SetDescription("Item #2", 2);
         salJourn.SetQuantity(2, 2);
         salJourn.SetUnit("Each", 2);
         salJourn.SetPrice(0.99, 2);
         salJourn.SetLineAccount(MakeAccountNumber(SDK_ACCTINVREV).ToString(), 2);
         // allocate projects
         ProjectAllocation projAlloc = salJourn.AllocateLine(2);
         projAlloc.SetProject("Sage Project", 1);
         try
         {
            projAlloc.SetPercent(50.0, 1);
         }
         catch (SimplyNoAccessException)
         {
            // can't allocate by percent, allocation by amount
            projAlloc.SetAmount(0.99, 1);
         }
         projAlloc.Save();

         salJourn.SetFreightAmount(0.00);
         salJourn.SetDiscountRate(5.00);

         salJourn.SetComment("Paid by cash");
         // additional information
         addInfo = salJourn.DoAdditionalInfo();
         addInfo.SetAdditionalComment("Comment");
         addInfo.SetAdditionalDate("Mar 15, 2015");
         addInfo.Save();

         if (salJourn.Post())
            Console.WriteLine("Sales invoice 'SJ 1' posting succeeded");
         else
            Console.WriteLine("Sales invoice 'SJ 1' posting failed");
         Console.WriteLine();

         // Lookup posted invoice
         salJourn.Undo();
         if (salJourn.LoadInvoiceForLookup("Sage Customer", "SJ 1"))
         {
            Console.WriteLine("Lookup sales invoice succeeded");
            Console.WriteLine(salJourn.GetAPARLedgerName() + ":\t" + salJourn.InvoiceNumber);
            for (int lineNum = 1; lineNum <= salJourn.NumberOfJournalLines; lineNum++)
            {
               Console.WriteLine("Line {0}:\t{1}\t{2:f}\t{3}\t{4}\t{5:f}\t{6:f}\t{7}",
                                 lineNum.ToString(),
                                 salJourn.GetItemNumber(lineNum),
                                 salJourn.GetQuantity(lineNum),
                                 salJourn.GetUnit(lineNum),
                                 salJourn.GetDescription(lineNum),
                                 salJourn.GetPrice(lineNum),
                                 salJourn.GetLineAmount(lineNum),
                                 salJourn.GetAccountNumber(lineNum));
            }
            // Show taxes
            TaxSummaryInfo taxtotal = salJourn.GetTotalTaxAmountInfo();
            for (int lineNum = 1; lineNum <= taxtotal.GetCount(); lineNum++)
            {
               Console.WriteLine("{0}:\t\t{1:f}",
                                 taxtotal.GetTaxNameByRow(lineNum),
                                 taxtotal.GetTaxAmountByRow(lineNum));               
            }
            Console.WriteLine("Total Taxes:\t{0:f}", salJourn.GetTaxTotalAmount());
            Console.WriteLine("Total: " + salJourn.GetTotalAmount().ToString());
            Console.WriteLine();

            // Adjust currently loaded invoice
            if (salJourn.AdjustInvoiceFromLookup())
            {
               salJourn.SetQuantity(salJourn.GetQuantity(1) + 1.0, 1);
               if (salJourn.Post())
                  Console.WriteLine("Sales invoice 'SJ 1' adjustment succeeded");
               else
                  Console.WriteLine("Sales invoice 'SJ 1' adjustment failed");
               Console.WriteLine();
            }
         }

         // Reverse posted invoice
         salJourn.Undo();
         if (salJourn.LoadInvoiceForLookup("Sage Customer", "SJ 1"))
         {
            if (salJourn.ReverseInvoice())
               Console.WriteLine("Sales invoice 'SJ 1' reversal succeeded");
            else
               Console.WriteLine("Sales invoice 'SJ 1' reversal failed");
         }

         SDKInstanceManager.Instance.CloseSalesJournal();
      }

      /// <summary>
      ///  Process Orders and Quotes Example
      /// </summary>
      public static void ProcessOrdersQuotesExample()
      {
         Console.WriteLine();
         Console.WriteLine("-----------------------------------");
         Console.WriteLine("Processing Journals Example");
         Console.WriteLine("-----------------------------------");
         Console.WriteLine();

         SetupJournalsExample();

         // Purchase Order Example
         PurchasesJournal purJourn = SDKInstanceManager.Instance.OpenPurchasesJournal();

         try
         {
            purJourn.SelectTransType(1); // order
         }
         catch (SimplyNoAccessException)
         {
            Console.WriteLine("Purchase Orders are currently not available in this database");
            SDKInstanceManager.Instance.ClosePurchasesJournal();
            return;
         }

         purJourn.SelectAPARLedger("Sage Vendor");
         purJourn.OrderQuoteNum = "PurOrder1";

         purJourn.SetItemNumber("N-3347", 1);
         purJourn.SetOrdered(50, 1);
         purJourn.SetUnit("Box", 1);
         purJourn.SetPrice(0.99, 1);

         if (purJourn.Post())
            Console.WriteLine("Purchase order 'PurOrder1' posting succeeded");
         else
            Console.WriteLine("Purchase order 'PurOrder1' posting failed");
         Console.WriteLine();

         // Lookup posted order
         purJourn.Undo();
         purJourn.SelectTransType(1);
         if (purJourn.LoadOrderQuoteRecord("PurOrder1"))
         {
            Console.WriteLine("Lookup purchase order succeeded");
            Console.WriteLine(purJourn.GetAPARLedgerName() + ":\t" + purJourn.OrderQuoteNum);
            for (int lineNum = 1; lineNum <= purJourn.NumberOfJournalLines; lineNum++)
            {
               Console.WriteLine("Line {0}:\t{1}\t{2:f}\t{3:f}\t{4}\t{5}\t{6:f}\t{7:f}\t{8}",
                                 lineNum.ToString(),
                                 purJourn.GetItemNumber(lineNum),
                                 purJourn.GetOrdered(lineNum),
                                 purJourn.GetBackOrdered(lineNum),
                                 purJourn.GetUnit(lineNum),
                                 purJourn.GetDescription(lineNum),
                                 purJourn.GetPrice(lineNum),
                                 purJourn.GetLineAmount(lineNum),
                                 purJourn.GetAccountNumber(lineNum));
            }
            Console.WriteLine("Total: " + purJourn.GetTotalAmount().ToString());
            Console.WriteLine();

            // Adjust currently loaded order
            purJourn.AdjustOrderQuoteRecord();
            purJourn.SetOrdered(40, 1);
            if (purJourn.Post())
               Console.WriteLine("Purchase order 'PurOrder1' adjustment succeeded");
            else
               Console.WriteLine("Purchase order 'PurOrder1' adjustment failed");
            Console.WriteLine();
         }

         // remove order
         purJourn.Undo();
         purJourn.SelectTransType(1);
         if (purJourn.LoadOrderQuoteRecord("PurOrder1"))
         {            
            if (purJourn.RemoveOrderQuote("PurOrder1"))
               Console.WriteLine("Purchase order 'PurOrder1' removal succeeded");
            else
               Console.WriteLine("Purchase order 'PurOrder1' removal failed");
            Console.WriteLine();
         }

         SDKInstanceManager.Instance.ClosePurchasesJournal();

         // Sales Quote Example
         SalesJournal salJourn = SDKInstanceManager.Instance.OpenSalesJournal();
         try
         {
            salJourn.SelectTransType(2); // quote
         }
         catch (SimplyNoAccessException)
         {
            Console.WriteLine("Sales Quotes are currently not available in this database");
            SDKInstanceManager.Instance.ClosePurchasesJournal();
            return;
         }
         salJourn.SelectAPARLedger("Sage Customer");
         salJourn.OrderQuoteNum = "QuoteToOrd1";
         salJourn.SetShipDate(salJourn.GetJournalDate());

         salJourn.SetItemNumber("CR-10", 1);
         salJourn.SetOrdered(3, 1);
         salJourn.SetUnit("Hour", 1);
         salJourn.SetPrice(90.00, 1);
         salJourn.SetLineAccount(MakeAccountNumber(SDK_ACCTINVREV).ToString(), 1);

         if (salJourn.Post())
            Console.WriteLine("Sales quote 'QuoteToOrd1' posting succeeded");
         else
            Console.WriteLine("Sales quote 'QuoteToOrd1' posting failed");
         Console.WriteLine();

         // Convert Quote to an order
         salJourn.Undo();
         salJourn.SelectTransType(2);
         if (salJourn.LoadOrderQuoteRecord("QuoteToOrd1"))
         {
            try
            {
               salJourn.ConvertQuoteToOrder();
            }
            catch (SimplyNoAccessException)
            {
               Console.WriteLine("Sales Orders are currently not available in this database");
               SDKInstanceManager.Instance.ClosePurchasesJournal();
               return;
            }
            if (salJourn.Post())
               Console.WriteLine("Sales order 'QuoteToOrd1' posting ysucceeded");
            else
               Console.WriteLine("Sales order 'QuoteToOrd1' posting failed");
            Console.WriteLine();
         }

         // Remove order that was just converted
         salJourn.Undo();
         salJourn.SelectTransType(1);
         if (salJourn.LoadOrderQuoteRecord("QuoteToOrd1"))
         {
            if (salJourn.RemoveOrderQuote("QuoteToOrd1"))
               Console.WriteLine("Sales order 'QuoteToOrd1' removal succeeded");
            else
               Console.WriteLine("Sales order 'QuoteToOrd1' removal failed");
            Console.WriteLine();
         }

         SDKInstanceManager.Instance.CloseSalesJournal();
      }

      #endregion 


      #region Main
      public static void Main(string[] args)
      {
         string saiFile = string.Empty;
         string sajFile = string.Empty;
         string username = string.Empty;
         string password = string.Empty;

         SDKInstanceManager.Instance.SetAlertImplementation(new ConsoleAlert());
         Console.WriteLine("Enter file name of a sample Sage Simply Accounting database");
         Console.Write("> ");
//         saiFile = Console.ReadLine();
         saiFile = "C:/Documents and Settings/Administrator/My Documents/Simply Accounting/DATA/test.SAI";
         Console.WriteLine(saiFile);



         saiFile = saiFile.ToUpper();
         if (!saiFile.EndsWith(".SAI"))
         {
            Console.WriteLine("Not a valid Sage Simply Accounting file.");
            return;
         }

         if (!File.Exists(saiFile))
         {
            Console.WriteLine("Sage Simply Accounting file (.SAI) does not exist.");
            return;
         }

         sajFile = saiFile.Replace(".SAI", ".SAJ");
         if (!Directory.Exists(sajFile))
         {
            Console.WriteLine("Sage Simply Accounting folder (.SAJ) does not exist.");
            return;
         }

         Console.WriteLine("\nEnter username");
         Console.Write("> ");
//         username = Console.ReadLine();
         username = "sysadmin";
         Console.WriteLine(username);

         

         Console.WriteLine("\nEnter password");
         Console.Write("> ");
         //password = Console.ReadLine();
         password = "qq";
         Console.WriteLine(password);

         Console.WriteLine("\nOpening database...\n");

         // Please note, that this sample program may update data in your database, so please do not use live data.  
         // =====================================================
         // We recommend using Premium sample data.
         // =====================================================
         // 
         // If you use data that does not have account numbers turned on or if the number of digits used for 
         // account numbers is not 4, this sample program will not work properly.
         // If so, you will need to modify this sample program according to your test database.
         if (SDKInstanceManager.Instance.OpenDatabase(saiFile, username, password, false, "Simply SDK Sample Program", "SASDK", 1))
         {
             try
             {


                           SDKDatabaseUtility uu = new SDKDatabaseUtility();
            string s = (string)uu.RunScalerQuery("SELECT sName FROM tCustomr");
            //         string s = (string)uu.RunScalerQuery("SELECT sName FROM tAccount");
            Console.WriteLine(s);

            AddCustomer();

            string ss = "";
            
              // GetAccountNumberLen();

               //Uncomment the examples you want to run            
               //AddLedgersExample();
               //LoadLedgersExample();
               //ErrorCheckingExample();
               //AlertMessagesExample();
               //DatabaseUtilityExamples();
               //ProcessGeneralJournalExample();
               //ProcessInvoicesExample();
               //ProcessOrdersQuotesExample();
            }
            finally
            {
               SDKInstanceManager.Instance.CloseDatabase();
            }
         }
      }

       private static void AddCustomer()
       {




           CustomerLedger custled = SDKInstanceManager.Instance.OpenCustomerLedger();
           try
           {
               custled.InitializeNew();
               custled.Name = "Test Customer Gala test 22222222 444";

               if (custled.Save())
                   Console.WriteLine("Account added successfully");
           }
           catch (InvalidEntryException e)
           {
               Console.WriteLine(e.Message);
           }
           SDKInstanceManager.Instance.CloseAccountLedger();




       }


      #endregion
   }
   #endregion
}
