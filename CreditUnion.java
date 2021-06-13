//Programmer: Pauline Finlay
//Student No: B00107945
//Date: 15.03.2018
//Assignment 2
//Program to create a credit union and manipulate accounts
//Note: uses swing

import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.text.DecimalFormat;

public class CreditUnion extends JFrame implements ActionListener //declare class CreditUnion and implement ActionListener
{

public JFrame window = new JFrame("Finlay Financial"); //create main frame

public JPanel panelsouth = new JPanel();	//create 5 smaller panels to place on main panel
public JPanel panelcenter = new JPanel();
public JPanel paneleast = new JPanel();
public JPanel panelwest = new JPanel();
public JPanel panelnorth = new JPanel();

public ImageIcon image1 = new ImageIcon(getClass().getResource("vault.jpg")); //declare image
public JLabel label1 = new JLabel(image1); //create a label to display the image

JLabel label2 = new JLabel("Welcome to Finlay Financial"); //create JLabel as heading


JLabel id =	new JLabel("Account ID No:(1 - 100)");	//account no label
JTextField accountIDField = new JTextField(15);		//account no. textfield for input & display

JLabel cname = new JLabel("First Name");			//first name label
JTextField firstNameField = new JTextField(15);		//first name textfield for input & display

JLabel surname = new JLabel("Last Name");			//surname label
JTextField lastNameField = new JTextField(15);		//surname textfield for input & display

JLabel bal = new JLabel("Balance");					//account balance label
JTextField balanceField = new JTextField(15);		//account balance textfield for input & display

JLabel over = new JLabel("Overdraft Limit");		//overdraft limit label
JTextField overdraftField = new JTextField(15);		//overdraft textfield for input & display

JButton begin = new JButton ("Open New Credit Union");	//JButton to open new credit union
JButton opexist = new JButton("Open Existing Credit Union"); //JButton to open existing credit union
JButton closeCU = new JButton("Close Credit Union");	//JButton to close credit union

Icon openicon = new ImageIcon("open.jpg"); //images for buttons
Icon viewicon = new ImageIcon("view.jpg");
JButton open = new JButton("Open New Account", openicon);		//JButton to open new account entry system
JButton view = new JButton("View Existing Account", viewicon);	//JButton to access reading of existing accounts

JButton addac = new JButton ("Save Account Details"); //JButton to save account details
JButton closeac = new JButton ("Close Account"); //JButton to close account
JButton checkbal = new JButton("Check Balance"); //JButton to check balance
JButton checklimit = new JButton("Check Overdraft Limit"); //JButton to check overdraft limit
JButton lodgeBut = new JButton("Lodge");	//JButton to make lodgement
JButton withAmt = new JButton("Withdraw");	//JButton to make withdrawal
JButton enter = new JButton("Enter");	//JButton to read file for account no entered
JButton limit = new JButton("Set New Overdraft Limit");	//JButton to set new overdraft limit
JButton done = new JButton("Log Out");	//JButton to logout of account currently in view

Record data = new Record(); //record to store date
public RandomAccessFile output, input; 	//files for input & output


public CreditUnion() //GUI constructor
{
	window.setLayout(new BorderLayout(5,5)); //create main panel with Border Layout
	window.add(panelcenter, BorderLayout.CENTER); //add sub panels to main panel and set layout
	window.add(panelsouth, BorderLayout.SOUTH);
	window.add(paneleast, BorderLayout.EAST);
	window.add(panelwest, BorderLayout.WEST);
	window.add(panelnorth, BorderLayout.NORTH);
	panelcenter.setVisible(false); //set invisible at start of program
	panelsouth.setVisible(true); //set visible from start
	paneleast.setVisible(false); //set invisible at start of program
	panelwest.setVisible(true); //set visible from start
	panelnorth.setVisible(true); //set visible from start

	panelwest.setBackground(Color.white); //set background color of panel to white
	panelwest.add(label1); //add image

	panelnorth.add(label2);	//add header to panel in North layout
	label2.setFont(new Font ("TimesRoman", Font.BOLD + Font.ITALIC, 24)); //set font type, size & effects

	panelsouth.setBackground(Color.white); //set background color of panel to white
	panelsouth.add(begin); //add open CU button
	begin.addActionListener(this); //add action listener
	panelsouth.add(opexist); //add open existing CU Button
	opexist.addActionListener(this); //add action listener
	panelsouth.add(closeCU); //add close CU button
	closeCU.addActionListener(this); //add action listener

	paneleast.setBackground(Color.white); //set background color of panel to white
	paneleast.setLayout(new GridLayout(2,2)); //set layout to 2 rows and 2 columns
	paneleast.add(open);	//add open new account entry button
	open.addActionListener(this); //add action listener
	paneleast.add(view); //add view existing ac button
	view.addActionListener(this); //add action listener

	panelcenter.setBackground(Color.white); //set background color of panel to white
	panelcenter.setLayout(new GridLayout(10,2)); //layout 9 rows and 2 columns
	panelcenter.add(id);	//add account no label
	panelcenter.add(accountIDField); //add account no textfield
	accountIDField.setMaximumSize( accountIDField.getPreferredSize() ); //set max size of textfield
	accountIDField.addActionListener(this); //add action listener
	panelcenter.add(cname); //add first name label
	panelcenter.add(firstNameField); //add first name textfield
	firstNameField.setMaximumSize( firstNameField.getPreferredSize() ); //set max size of textfield
	firstNameField.addActionListener(this); //add action listener
	panelcenter.add(surname); //add surname label
	panelcenter.add(lastNameField); //add surname textfield
	lastNameField.setMaximumSize( lastNameField.getPreferredSize() ); //set max size of textfield
	lastNameField.addActionListener(this); //add action listener
	panelcenter.add(bal); //add balance label
	panelcenter.add(balanceField); //add balance textfield
	balanceField.setMaximumSize( balanceField.getPreferredSize() ); //set max size of textfield
	balanceField.addActionListener(this); //add action listener
	panelcenter.add(over); //add overdraft limit label
	panelcenter.add(overdraftField); //add overdraft limit textfield
	overdraftField.setMaximumSize( overdraftField.getPreferredSize() ); //set max size of textfield
	overdraftField.addActionListener(this); //add action listener
	panelcenter.add(enter); //add button to read account no details to screen
	enter.addActionListener(this); //add action listener
	panelcenter.add(done); //add button to log out of account in view
	done.addActionListener(this); //add action listener
	panelcenter.add(lodgeBut); //add button to lodge money
	lodgeBut.addActionListener(this); //add action listener
	panelcenter.add(withAmt); //add button to withdraw money
	withAmt.addActionListener(this); //add action listener
	panelcenter.add(checkbal); //add button to check balance
	checkbal.addActionListener(this); //add action listener
	panelcenter.add(checklimit); //add button to check overdraft limit
	checklimit.addActionListener(this); //add action listener
	panelcenter.add(limit); //add button to set new overdraft limit
	limit.addActionListener(this); //add action listener
	panelcenter.add(closeac); //add button to close account
	closeac.addActionListener(this); //add action listener
	panelcenter.add(addac); //add button to save new account details
	addac.addActionListener(this); //add action listener

	window.setSize(1300, 450); //set main panel frame size
	window.setVisible(true); //set visible

	}

public void actionPerformed(ActionEvent e) //implement event listener
{
	if(e.getSource() == begin) //action on pressing begin button
		{
		paneleast.setVisible(true); //show button panel in south
		new Record(); //Record method call
		new CreateRandomFile(); //create random file method call
		opexist.setEnabled(false);
		begin.setEnabled(false);
		}

	if(e.getSource() == closeCU) //action on pressing close button
		{
		 System.exit(0); //close screen
		}

	if(e.getSource()== opexist) //action on pressing open existing CU button
		{
		try
			{
			//open up files for read & write
			input = new RandomAccessFile( "credit.dat", "rw" );
			output = new RandomAccessFile( "credit.dat", "rw" );
			}
		catch ( IOException x )
			{
			System.err.println(e.toString() );
			System.exit(1);
			}
		paneleast.setVisible(true); //show button panel in south
		opexist.setEnabled(false);
		begin.setEnabled(false);
		}

	if(e.getSource() == open) //action on pressing open new account button
		{
		openAccount(); //openAccount method call
		panelcenter.setVisible(true); //set center panel visible
		accountIDField.setText( "" ); //clear textfields
		firstNameField.setText( "" );
		lastNameField.setText( "" );
		balanceField.setText( "" );
		overdraftField.setText( "" );
		addac.setEnabled(true);
		addac.setVisible(true);
		lodgeBut.setVisible(false);	//hide all buttons except add account
		enter.setVisible(false);
		withAmt.setVisible(false);
		limit.setVisible(false);
		closeac.setVisible(false);

		checkbal.setVisible(false);
		checklimit.setVisible(false);
		done.setVisible(false);
		accountIDField.setEditable( true ); //set textfield to allow use to enter data
		firstNameField.setEditable( true );
		lastNameField.setEditable( true );
		balanceField.setEditable(true);
		overdraftField.setEditable( true );
		}

	if(e.getSource()== view) //action on pressing view existing account button
		{
		JOptionPane.showMessageDialog(this, "Enter the account number and press enter"); //Joption pane with instructions for user
		panelcenter.setVisible(true); //set center panel visible
		accountIDField.setText(""); //clear all textfields
		firstNameField.setText("");
		lastNameField.setText("");
		balanceField.setText("");
		overdraftField.setText("");
		enter.setVisible(true); //set enter button visible
		enter.setEnabled(true); //disable all buttons except enter button to allow readRecord method
		lodgeBut.setEnabled(false);
		addac.setVisible(false);
		closeac.setEnabled(false);
		withAmt.setEnabled(false);
		limit.setEnabled(false);
		checkbal.setEnabled(false);
		checklimit.setEnabled(false);
		done.setEnabled(false);
		accountIDField.setEditable( true ); //allow user to only enter data in account no in textfield
		accountIDField.addActionListener(this); //add action listener to pick up account no
		firstNameField.setEditable( false );
		lastNameField.setEditable( false );
		balanceField.setEditable(false);
		overdraftField.setEditable( false );

		//read account details when account number entered
		if (! accountIDField.getText().equals(""))
		  	{
			readRecord(); //calling readRecord method
  			}
		}

	if (e.getSource() == enter) //action on pressing enter button
		{
			readRecord(); //call readRecord method

			enter.setEnabled(false);
			lodgeBut.setVisible(true); //show buttons to allow user makeLodgement, makeWithdrawal, closeAccount, requestOverdraft, check balance & logout
			closeac.setVisible(true);
			withAmt.setVisible(true);
			limit.setVisible(true);
			checkbal.setVisible(true);
			checklimit.setVisible(true);
			done.setVisible(true);
			lodgeBut.setEnabled(true); //enable buttons to allow user makeLodgement, makeWithdrawal, closeAccount, requestOverdraft, check balance & logout
			closeac.setEnabled(true);
			withAmt.setEnabled(true);
			limit.setEnabled(true);
			checkbal.setEnabled(true);
			checklimit.setEnabled(true);
			done.setEnabled(true);
  		}

	if(e.getSource() == addac) //action on pressing begin button
		{
			addRecord(); //addRecord method call
		}

	if (e.getSource() == closeac) //action on pressing close account button
		{
			closeAccount(); //closeAccount method call

		}

	if(e.getSource() == lodgeBut) //action on pressing lodge button
		{
			makeLodgement(); //makeLodgement method call
		}

	if(e.getSource() == withAmt) //action on pressing withdraw button
		{
			makeWithdrawal(); //makeWithdrawal method call
		}

	if(e.getSource()== checkbal) //action on pressing check balance button
		{
			balance(); //balance method call
		}

	if(e.getSource()== checklimit) //action on pressing check limit button
		{
			overdraftLimit(); //overdraftLimit method call
		}

	if (e.getSource() == limit) //action on pressing set new overdraft limit button
  		{
		 	requestOverdraft(); //requestOverdraft method call
		}

	if (e.getSource()== done) //action on pressing log out button
		{
		accountIDField.setText(""); //all textfield cleared
		firstNameField.setText("");
		lastNameField.setText("");
		balanceField.setText("");
		overdraftField.setText("");
		JOptionPane.showMessageDialog(this, "Successful Log Out"); //message pane to say logged out
		panelcenter.setVisible(false); //center panel invisible
		}

 } //end actionPerformed


public static void main(String[] args) //call main method
	{
		CreditUnion app = new CreditUnion(); //open frame
		app.addWindowListener(new WindowAdapter()
			   {
				  public void windowClosing(WindowEvent e)
				   {
					   System.exit(0); //normal closing
				   }
			   }
			   );
	} //end main method

//=========================================================================
//=========================================================================
//USER DEFINED METHODS

 public void openAccount() //openAccount method
 {
	//open file
	data = new Record(); //create record called data

	//access record
	try
		{
		output = new RandomAccessFile( "credit.dat", "rw" ); //write data to credit.dat random access file
		}
	catch ( IOException e) //catch for input error
		{
		System.err.println( e.toString() );
		System.exit(1);
		}
 }

//=========================================================================
//=========================================================================

public void addRecord() //create method for adding records to file
{
	DecimalFormat twoDigits = new DecimalFormat( "0.00" ); //set decimal format
	int accountIDNumber = 0; //initialise account no to 0 to begin
	Double balance, overdraft; //declare variables for balance & overdraft amounts

	if ( ! accountIDField.getText().equals( "" ) ); //once account no entered
	{

	try
	{
	accountIDNumber = Integer.parseInt( accountIDField.getText() ); //get account no from textfield
	balance = Double.parseDouble( balanceField.getText() ); //read balance figure from textfield
	overdraft = Double.parseDouble( overdraftField.getText() ); //read overdraft from textfield

		if (accountIDNumber < 1 || accountIDNumber > 100)  //validate account number is in range
		{
			JOptionPane.showMessageDialog(this, "Account ID number must be between 1 & 100"); //error message if incorrect account no
		}

		else
		if (accountIDNumber > 0 && accountIDNumber <= 100 ) //if account no value on range
		{
		//read file to check if account number already exists.
			output.seek((long) (accountIDNumber - 1) * Record.size());
			data.read(output);

		if (data.getAccount() == accountIDNumber)  //if account exists,display dialog message to user
	    	{
			JOptionPane.showMessageDialog(this,"Account already exists! Please try a different account number");
			accountIDField.setText("");   // clear account textfield
			}

		else   //once conditions are met, data is written to file.
           {
            data.setAccount( accountIDNumber );
            data.setFirstName( firstNameField.getText() );
            data.setLastName( lastNameField.getText() );
            balance = new Double( balanceField.getText() );
            data.setBalance( balance.doubleValue() );
            overdraft = new Double( overdraftField.getText() );
            data.setOverdraft( overdraft.doubleValue() );
            output.seek( (long) ( accountIDNumber-1 ) * Record.size() );
            data.write( output );
	        JOptionPane.showMessageDialog(null, "Account Details Saved");
           }
    	}
            //reset textfields to take next entry
            accountIDField.setText("");
            firstNameField.setText("");
            lastNameField.setText("");
            balanceField.setText("");
            overdraftField.setText("");

  	}//end try statement
	catch (NumberFormatException nfe )
		{
		System.err.println("You must enter an integer ID number"); //error message if account no is not an int
		}
	catch (IOException io)
		{
		System.err.println("error during write to file\n" + io.toString() ); //error message is account details not written to file
      	}

	}//end initial if statement
} //end addRecord method

//=========================================================================
//=========================================================================

public void readRecord() //read record method
{
//Open the file

try
	{
	//set up files for read & write
	input = new RandomAccessFile( "credit.dat", "rw" );
	output = new RandomAccessFile( "credit.dat", "rw" );
	}
catch ( IOException e )
	{
	System.err.println(e.toString() ); //catch input output error
	System.exit(1);
	}

data = new Record(); //create new record

DecimalFormat twoDigits = new DecimalFormat( "0.00" ); //decimal format
	try
	{
	int accountIDNumber = Integer.parseInt(accountIDField.getText()); //get account no from textfield

	if (accountIDNumber < 1 || accountIDNumber > 100) //account must be within 1 and 100
		{
			JOptionPane.showMessageDialog(this, "Account Number does not exist"); //display error message to user
			panelcenter.setVisible(false); //hide center panel
		}
	else
		{
			input.seek( (accountIDNumber - 1)*Record.size()); //if account no valid search for file
			data.read(input); //read file

			accountIDField.setText(String.valueOf( data.getAccount() ) ); //display account no in textfield
			firstNameField.setText( data.getFirstName() ); //display first name in textfield
			lastNameField.setText( data.getLastName() ); //display surname in textfield
			balanceField.setText( String.valueOf(twoDigits.format( data.getBalance() ) ) ); //display balance in textfield
			overdraftField.setText( String.valueOf(twoDigits.format( data.getOverdraft() ) ) ); //display overdraft limit in textfield
		}
	if (data.getAccount() == 0) //if no files match
		{
			JOptionPane.showMessageDialog(this, "Account does not exist");
			panelcenter.setVisible(false);
		}
	}//end try statement

	catch (IOException e )
	{
		System.err.println("Error during read from file\n " + e.toString() ); //error message if file can't be read
		System.exit( 1 );
	}

}

//=========================================================================
//=========================================================================

public void closeAccount() //closeAccount Method
{
try
	{
   	 int accountIDNumber = Integer.parseInt( accountIDField.getText() ); //get account no from textfield
   	 double balance =Double.parseDouble(balanceField.getText()); //get balance from textfield

	 if(balance==0) //if balance is 0
	 	{
		data.setAccount( 0 ); //delete file
		data.setFirstName( null );
		data.setLastName( null);
		data.setBalance( 0);
		data.setOverdraft(0);

		output.seek( (long) ( accountIDNumber-1 ) * Record.size() );
		data.write( output ); //rewrite file

		JOptionPane.showMessageDialog(this, "This account has been closed"); //confirm closure to user
		panelcenter.setVisible(false); //close center panel
		}

	else //if balance is not 0
		{
		JOptionPane.showMessageDialog(this, "Account cannot be closed at this time - Check Balance"); //error message to user
		}

	}//end try

catch (NumberFormatException nfe )
   	  	 {
   	  	  System.err.println("You must enter an integer id number"); //error message if account no incorrect
   	  	 }

catch (IOException io)
   	  	 {
   	  	  System.err.println("error during write to file\n" + io.toString() ); //error message if not saved to file
   	      }

 }

//=========================================================================
//=========================================================================

public void makeLodgement() //makeLodgement method
{
try
    {
		DecimalFormat twoDigits = new DecimalFormat( "0.00" ); //decimal format
    	int accountIDNumber = Integer.parseInt( accountIDField.getText() ); //get account no from textfield
    	double balance =Double.parseDouble(balanceField.getText()); //get balance from textfield
    	String number1 = JOptionPane.showInputDialog(this, "Enter Lodgement Amount"); //prompt user to enter lodgement amount
    	double lodge = Double.parseDouble(number1); //parse to double
    	balance = balance + lodge; //compute new balance
    	balanceField.setText( String.valueOf(twoDigits.format(balance))); //set new balance to textfield

 		data.setBalance(balance); //save to file

 		output.seek( (long) ( accountIDNumber-1 ) * Record.size() );
 		data.write( output ); //write to file output
 		JOptionPane.showMessageDialog(this, "Lodgement Successful - New Balance "+(twoDigits.format(balance))); //confirm lodgement to user

 	}//end try

 catch (NumberFormatException nfe )
	{
		System.err.println("You must enter an integer id number"); //error message if account no entered incorrectly
	}

 catch (IOException io)
	{
		System.err.println("error during write to file\n" + io.toString() ); //error message if not saved to file
	}
}

//=========================================================================
//=========================================================================

public void makeWithdrawal() //makeWithdrawal method
{
try
    {
		DecimalFormat twoDigits = new DecimalFormat( "0.00" ); //decimal format
    	int accountIDNumber = Integer.parseInt( accountIDField.getText() ); //get account no from textfield
    	double balance =Double.parseDouble(balanceField.getText()); //get balance from textfield
    	double overdraft =Double.parseDouble(overdraftField.getText()); //get overdraft limit from textfield
    	double availfunds=balance+overdraft; //double available funds is balance plus limit
    	String number2 = JOptionPane.showInputDialog(this, "Enter Withdrawal Amount"); //prompt user to enter withdrawal amount
    	double withdraw = Double.parseDouble(number2); //parse value to double

		if(availfunds>=withdraw) //if funds grater that withdrawal
		{
    	balance = balance - withdraw; //reset balance less withdrawal amount
    	balanceField.setText( String.valueOf(twoDigits.format(balance))); //reset balance textfield with new amount

 		data.setBalance(balance); //save to file

 		output.seek( (long) ( accountIDNumber-1 ) * Record.size() );
 		data.write( output ); //write to file output
 		JOptionPane.showMessageDialog(this, "Withdrawal Successful - New Balance "+(twoDigits.format(balance))); //confirm withdrawal to user
		}

		if(availfunds<withdraw) //if funds not greater that withdrawal amount
		{
		JOptionPane.showMessageDialog(this, "Insufficient Funds"); //confirm insufficient funds to user
		}

 	}//end try

 catch (NumberFormatException nfe )
    	  	 {
    	  	  System.err.println("You must enter an integer id number"); //error message if integer account not entered
    	  	 }

 catch (IOException io)
    	  	 {
    	  	  System.err.println("error during write to file\n" + io.toString() ); //error message if write to file not completed
    	      }
}

//=========================================================================
//=========================================================================

public void requestOverdraft() //requestOverdraft method
{
try
    {
		DecimalFormat twoDigits = new DecimalFormat( "0.00" ); //decimal format
    	int accountIDNumber = Integer.parseInt( accountIDField.getText() ); //get account no from textfield
    	double overdraft =Double.parseDouble(overdraftField.getText()); //get overdraft limit from textfield
    	String number3 = JOptionPane.showInputDialog(this, "Enter New Overdraft Limit"); //prompt user to enter new limit
    	double overlimit = Double.parseDouble(number3); //parse to double
    	overdraftField.setText( String.valueOf(twoDigits.format(overlimit))); //reset textfield with new limit

 		data.setOverdraft(overlimit); //save new limit to file

 		output.seek( (long) ( accountIDNumber-1 ) * Record.size() );
 		data.write( output ); //write to output file

 		JOptionPane.showMessageDialog(this, "New Overdraft Limit: " +(twoDigits.format(overlimit))); //confirm new limit to user

 	}//end try

 catch (NumberFormatException nfe )
	{
		System.err.println("You must enter an integer id number"); //error message if integer account not entered
	}

 catch (IOException io)
	{
		System.err.println("error during write to file\n" + io.toString() ); //error message if file not written
	}
}

//=========================================================================
//=========================================================================

public void balance() //display Balance method

{
	DecimalFormat twoDigits = new DecimalFormat( "0.00" );
	try
	  	{
		int accountIDNumber = Integer.parseInt( accountIDField.getText() ); //read account no
		double Balance= Double.parseDouble(balanceField.getText()); //read balance
		JOptionPane.showMessageDialog(this, "Account Balance: " +(twoDigits.format(Balance))); //display balance to user
		}
	catch (NumberFormatException nfe )
	   	{
	   	System.err.println("You must enter an integer id number"); //error message display
   	  	}
}

//=========================================================================
//=========================================================================

public void overdraftLimit() //display overdraftLimit method

{
	DecimalFormat twoDigits = new DecimalFormat( "0.00" );
	try
	  	{
		int accountIDNumber = Integer.parseInt( accountIDField.getText() ); //read account no
		double Overdraft= Double.parseDouble(overdraftField.getText()); //read limit
		JOptionPane.showMessageDialog(this, "Overdraft Limit: " +(twoDigits.format(Overdraft))); //display overdraft limit to user
		}
	catch (NumberFormatException nfe )
	   	{
	   	System.err.println("You must enter an integer id number"); //error message display
   	  	}
}

//=========================================================================
//=========================================================================
}