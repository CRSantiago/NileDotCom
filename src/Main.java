/*
 * Christopher Santiago
 * CNT 4714 - Spring 2023
 * Project 1 - Event-driven Enterprise Simulation
 * Sunday January 29, 2023
 * */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;
import java.util.TimeZone;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.ImageIcon;

public class Main extends JFrame implements ActionListener {
	
	// The following variables are made global to allow overridden actionPerformed() method to access them.
	
	// JLabels
	JLabel idLabel;
	JLabel quantityLabel;
	JLabel detailsLabel;
	JLabel subtotalLabel;
	
	//JButtons
	JButton findItemButton;
	JButton purchaseItemButton;
	JButton viewOrderButton;
	JButton completeOrderButton;
	JButton newOrderButton;
	JButton exitButton;
	
	//JtextFields
	JTextField idTextField;
	JTextField quantityTextField;
	JTextField detailsTextField;
	JTextField subtotalTextField;
	
	// Item Object array. Stores the purchased items to allow for access throughout program flow
	Item[] orderArr = new Item[30];
	
	// Logic globals
	int orderCount = 0; // items in orderArr
	int itemCount = 1; // display in GUI, keeps track of current item user is on
	double subTotal = 0.0; // global sub total used for final invoice
	double tempSubTotal = 0.0; // global temp sub total for displaying when user is between purchases
	// logic values for unavailable items
	boolean found = false;
	boolean inStock = false;
	
	NumberFormat formatter = new DecimalFormat("#0.00");
	
	Main() {
		
		this.setTitle("Nile Dot Com"); // sets title
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // exit out of application
		this.setLayout(null);
		this.setSize(750, 500); // sets x and y dimension of frame
		
		ImageIcon image = new ImageIcon("src/river.png"); //create image icon
		this.setIconImage(image.getImage());
		
		// North panel to house labels and text fields
		JPanel northPanel = new JPanel();
		northPanel.setBackground(Color.DARK_GRAY);
		northPanel.setBounds(0,0,750,300);
		northPanel.setBorder(new EmptyBorder(50, 0, 0, 0));
	
		
		// label panel
		JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING, 0,15));
		labelPanel.setBackground(Color.DARK_GRAY);
		labelPanel.setPreferredSize(new Dimension(250,150));
		
		// label panel labels
		idLabel = new JLabel();
		idLabel.setText("Enter item ID for Item #" + itemCount + ":");
		idLabel.setForeground(Color.yellow);
		idLabel.setFont(new Font("Verdana",Font.PLAIN, 14));
		labelPanel.add(idLabel);
		quantityLabel = new JLabel();
		quantityLabel.setText("Enter quantity item for Item #" + itemCount + ":");
		quantityLabel.setForeground(Color.yellow);
		quantityLabel.setFont(new Font("Verdana",Font.PLAIN, 14));
		labelPanel.add(quantityLabel);
		detailsLabel = new JLabel();
		detailsLabel.setText("Details for Item #" + itemCount + ":");
		detailsLabel.setForeground(Color.yellow);
		detailsLabel.setFont(new Font("Verdana",Font.PLAIN, 14));
		labelPanel.add(detailsLabel);
		subtotalLabel = new JLabel();
		subtotalLabel.setText("Order subtotal for " + orderCount + " item(s):");
		subtotalLabel.setForeground(Color.yellow);
		subtotalLabel.setFont(new Font("Verdana",Font.PLAIN, 14));
		labelPanel.add(subtotalLabel);
		//end of label panel labels
		
		northPanel.add(labelPanel);
		
		// text field panel labels
		JPanel textfieldPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 0, 15));
		textfieldPanel.setBackground(Color.DARK_GRAY);
		textfieldPanel.setPreferredSize(new Dimension(350,150));
		
		//text fields
		idTextField = new JTextField();
		idTextField.setPreferredSize(new Dimension(350,20));
		textfieldPanel.add(idTextField);
		
		quantityTextField = new JTextField();
		quantityTextField.setPreferredSize(new Dimension(350,20));
		textfieldPanel.add(quantityTextField);
		
		detailsTextField = new JTextField();
		detailsTextField.setPreferredSize(new Dimension(350,20));
		detailsTextField.setEditable(false);
		textfieldPanel.add(detailsTextField);
		
		subtotalTextField = new JTextField();
		subtotalTextField.setPreferredSize(new Dimension(350,20));
		subtotalTextField.setEditable(false);
		textfieldPanel.add(subtotalTextField);
		northPanel.add(textfieldPanel);
		//end of text field labels
		
		// south panel to house buttons
		JPanel southPanel = new JPanel(new FlowLayout(0,15,15));
		southPanel.setBackground(Color.cyan);
		southPanel.setBounds(0,300,750,200);
		
		// south panel buttons
		findItemButton = new JButton();
		findItemButton.setText("Find Item #" + itemCount);
		findItemButton.setFocusable(false);
		findItemButton.setBackground(Color.white);
		findItemButton.setFont(new Font("Verdana",Font.PLAIN, 14));
		findItemButton.addActionListener(this);
		findItemButton.setPreferredSize(new Dimension(350,25));
		southPanel.add(findItemButton);
		
		purchaseItemButton = new JButton();
		purchaseItemButton.setText("Purchase Item #" + itemCount);
		purchaseItemButton.setFocusable(false);
		purchaseItemButton.setBackground(Color.white);
		purchaseItemButton.setFont(new Font("Verdana",Font.PLAIN, 14));
		purchaseItemButton.addActionListener(this);
		purchaseItemButton.setPreferredSize(new Dimension(350,25));
		purchaseItemButton.setEnabled(false);
		southPanel.add(purchaseItemButton);
		
		viewOrderButton = new JButton();
		viewOrderButton.setText("View Current Order");
		viewOrderButton.setFocusable(false);
		viewOrderButton.setBackground(Color.white);
		viewOrderButton.setFont(new Font("Verdana",Font.PLAIN, 14));
		viewOrderButton.addActionListener(this);
		viewOrderButton.setPreferredSize(new Dimension(350,25));
		viewOrderButton.setEnabled(false);
		southPanel.add(viewOrderButton);
		
		completeOrderButton = new JButton();
		completeOrderButton.setText("Complete Order - Check Out");
		completeOrderButton.setFocusable(false);
		completeOrderButton.setBackground(Color.white);
		completeOrderButton.setFont(new Font("Verdana",Font.PLAIN, 14));
		completeOrderButton.addActionListener(this);
		completeOrderButton.setPreferredSize(new Dimension(350,25));
		completeOrderButton.setEnabled(false);
		southPanel.add(completeOrderButton);
		
		newOrderButton = new JButton();
		newOrderButton.setText("Start New Order");
		newOrderButton.setFocusable(false);
		newOrderButton.setBackground(Color.white);
		newOrderButton.setFont(new Font("Verdana",Font.PLAIN, 14));
		newOrderButton.addActionListener(this);
		newOrderButton.setPreferredSize(new Dimension(350,25));
		southPanel.add(newOrderButton);
		
		exitButton = new JButton();
		exitButton.setText("Exit (Close App)");
		exitButton.setFocusable(false);
		exitButton.setBackground(Color.white);
		exitButton.setFont(new Font("Verdana",Font.PLAIN, 14));
		exitButton.addActionListener(this);
		exitButton.setPreferredSize(new Dimension(350,25));
		southPanel.add(exitButton);
		
		this.add(northPanel);
		this.add(southPanel);
		this.setVisible(true); // makes frame visible
	}
	
	public static void main(String args[]) {
		new Main();
	}

	@Override 
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == findItemButton) {
			String item = idTextField.getText();
			File file = new File("inventory.txt");
			Scanner scanner;
			try {
				scanner = new Scanner(file);
				while (scanner.hasNextLine()) {
					   final String lineFromFile = scanner.nextLine();
					   //contain only checks for the existence of the substring
					   if(lineFromFile.contains(item)) { 
					       // a match!
						   //itemFields format -  itemId, title, in stock, cost
						   String[] itemFields = lineFromFile.split(",");
						   // here we ensure the id inputed by the user
						   if(itemFields[0].equals(idTextField.getText())) {
							   found = true;
							   // ensure item is in stock
							   if(itemFields[2].strip().equals( "true")) {
								   inStock = true;
								   int quantity = Integer.parseInt(quantityTextField.getText());
								   double discount = 0.0;
								   // calculate discount according to criterea
								   if(quantity >= 5 && quantity <= 9) {
									   discount = 0.1;
								   } else if(quantity >= 10 && quantity <= 14) {
									   discount = 0.15;
								   } else if(quantity >= 15) {
									   discount = 0.2;
								   }
								   //extract cost
								   String cost = itemFields[3];
								   // calculate total
								   double total = Double.parseDouble(cost) * quantity;
								   //update total based on discount
								   if (discount > 0.0) {
									   total -= (total * discount);
								   } 
								   // add item to orderArr
								   orderArr[orderCount] = new Item(itemFields[0],itemFields[1],itemFields[2],itemFields[3], discount, quantity, total);
								   // display order details
								   detailsTextField.setText(orderArr[orderCount].id  + orderArr[orderCount].title + " $" + orderArr[orderCount].cost + " " + orderArr[orderCount].quantity + " %" + orderArr[orderCount].discount + " " + "$" + orderArr[orderCount].total);
								   // temp subtotal before purchase of an item
								   tempSubTotal += total;
								   // if the user is on second or more item, only display total value of those items that were purchased
								   if(orderCount > 0) {
									   subtotalTextField.setText("$" + formatter.format(subTotal));
								   } else {
									   subtotalTextField.setText("$" + formatter.format(tempSubTotal));
								   }
								   
								   findItemButton.setEnabled(false); // forces user to perform an action of current item instead of searching for another
								   purchaseItemButton.setEnabled(true); // enables purchase item button
								   detailsLabel.setText("Details for Item #" + itemCount + ":"); // changes details label content to reflect current item before purchase. 
								   break;
							   }
						   }
					   }
					}
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// display error messages
			if(found == false) {
				JOptionPane.showMessageDialog(this,
					    "Item ID " + idTextField.getText() + " not in file",
					    "Nile Dot Com - ERROR",
					    JOptionPane.ERROR_MESSAGE);
			} else if(inStock == false) {
				JOptionPane.showMessageDialog(this,
					    "Sorry.. that item is not in stock. Please try another item.",
					    "Nile Dot Com - ERROR",
					    JOptionPane.ERROR_MESSAGE);
			}
			
			// reset logic values for unavailable items
			found = false;
			inStock = false;
		} else if (e.getSource() == purchaseItemButton) {
			ImageIcon checkImg = new ImageIcon("src/checked.png");
			// display message confirming users purchase
			JOptionPane.showMessageDialog(this, "Item #" + itemCount + " accepted. Added to your cart.", "Nile Dot Com - Item Confirmed", JOptionPane.INFORMATION_MESSAGE, checkImg);
			orderCount += 1; // increment orderCount
			itemCount += 1; // incremeant itemCount
			subTotal += tempSubTotal; // update subtotal
			tempSubTotal = 0.0; // reset tempSubtotal
			subtotalTextField.setText("$" + subTotal); // display new subtotal
			findItemButton.setEnabled(true); // allow for additional items
			purchaseItemButton.setEnabled(false); // disable purchase until new item is searched for
			completeOrderButton.setEnabled(true); // allow user to complete current order
			viewOrderButton.setEnabled(true); // allow user to view current order
			idTextField.setText(""); // reset id field for new search
			quantityTextField.setText(""); // reset quantity for new search
			// update the following fields to reflect current item user is on
			idLabel.setText("Enter item ID for Item #" + itemCount + ":");
			quantityLabel.setText("Enter quantity item for Item #" + itemCount + ":");
			subtotalLabel.setText("Order subtotal for " + orderCount + " item(s):");
			purchaseItemButton.setText("Purchase Item #" + itemCount);
			findItemButton.setText("Find Item #" + itemCount);
		} else if (e.getSource() == viewOrderButton) {
			String orderStr = "";
			int count = 1; // used to display in message dialog order of items purchased
			// loop through orderArr and concat item details and new line
			for(int i = 0; i < orderCount; i++){
				orderStr = orderStr.concat(count + ". "+ orderArr[i].id  + orderArr[i].title + " $" + orderArr[i].cost + " " + orderArr[i].quantity + " %" + orderArr[i].discount + " " + formatter.format(orderArr[i].total) + "\n");
				count += 1;
			}
			// display current order
			JOptionPane.showMessageDialog(this, orderStr, "Nile Dot Com - Current Shopping Cart Status",JOptionPane.INFORMATION_MESSAGE);
		} else if (e.getSource() == completeOrderButton) {
			// used to create uniqued id for items in  transaction.txt - formate: ddMMyyyyHHmm
			LocalDateTime localDateObj = LocalDateTime.now();   
			DateTimeFormatter idFormatObj = DateTimeFormatter.ofPattern("ddMMyyyyHHmm");   
			String formattedID = localDateObj.format(idFormatObj); 
			
			// get current date
			DateTimeFormatter formatObj = DateTimeFormatter.ofPattern("MM/dd/yyyy");
			String date = localDateObj.format(formatObj);
			
			// get current time
			Date dateObj = new Date();
			SimpleDateFormat formatTime = new SimpleDateFormat("hh:mm:ss aa");
			String time = formatTime.format(dateObj);
			
			// open transactions.txt
			File file = new File("transactions.txt");
			try {
				FileWriter fr = new FileWriter(file, true);
				// loop through orderArr, writing order details to file 
				for(int i = 0; i < orderCount; i++){
					fr.write(formattedID + ", " + orderArr[i].id + ", "  + orderArr[i].title + ", " + orderArr[i].cost + ", " + orderArr[i].quantity + ", " + orderArr[i].discount + ", $" + formatter.format(orderArr[i].total)  + ", " + date + ", " + time + " EST" + "\n");
				}
				fr.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			findItemButton.setEnabled(false); // disallow user to find new item
			purchaseItemButton.setEnabled(false); // disallow user to purchase another item
			completeOrderButton.setEnabled(false); // disallow user to complete a new order 
			quantityTextField.setEditable(false); // disallow user to search new item
			idTextField.setEditable(false); // ^*
			
			// create invoice panel
			JPanel invoicePanel = new JPanel();
			invoicePanel.setLayout(new GridLayout(8+orderCount,1,0,10));
			invoicePanel.setBorder(new EmptyBorder(10, 30, 10, 10));
			// create dialog object to place invoicePanel
			JDialog invoice = new JDialog(this, "Nile Dot Com - FINAL INVOICE");
			
			invoicePanel.add(new JLabel("Date: " + date + ", " +  time + " EST"));
			invoicePanel.add(new JLabel("Number of line items " + orderCount));
			invoicePanel.add(new JLabel("Item # / ID / Title / Qty / Disc % / Subtotal:"));
			
			int count = 1;
			// loop through orderArr and append new Jlabel for each item
			for(int i = 0; i < orderCount; i++){
				String orderStr = count + ". "+ orderArr[i].id  + orderArr[i].title + " $" + orderArr[i].cost + " " + orderArr[i].quantity + " %" + orderArr[i].discount + " " + orderArr[i].total;
				invoicePanel.add(new JLabel(orderStr));
				count += 1;
			}
			invoicePanel.add(new JLabel("Order subtotal: " + subTotal)); // display subtotal
			invoicePanel.add(new JLabel("Tax Rate: 6%")); //display taxt rate
			double taxAmount = subTotal * 0.06; // calculate tax amount
			invoicePanel.add(new JLabel("Tax amount: $" + formatter.format(taxAmount)));
			double orderTotal = subTotal + taxAmount; // calculate subtotal plus tax amount
			invoicePanel.add(new JLabel("ORDER TOTAL: $" + formatter.format(orderTotal))); //display order total
			invoicePanel.add(new JLabel("Thanks for shopping at Nile Dot Com!"));
			invoice.add(invoicePanel);// add invoice panel
			invoice.setSize(new Dimension(450,600)); // create invoice size
			invoice.setVisible(true); // set visisble
			
		} else if (e.getSource() == newOrderButton) {
			// reset all global variables
			idTextField.setText("");
			quantityTextField.setText("");
			detailsTextField.setText("");
			subtotalTextField.setText("");
			orderCount = 0;
			itemCount = 1;
			subTotal = 0.0;
			tempSubTotal = 0.0;
			idLabel.setText("Enter item ID for Item #" + itemCount + ":");
			quantityLabel.setText("Enter quantity item for Item #" + itemCount + ":");
			subtotalLabel.setText("Order subtotal for " + orderCount + " item(s):");
			purchaseItemButton.setText("Purchase Item #" + itemCount);
			findItemButton.setText("Find Item #" + itemCount);
			detailsLabel.setText("Details for Item #" + itemCount + ":");
			findItemButton.setEnabled(true);
			completeOrderButton.setEnabled(false);
			viewOrderButton.setEnabled(false);
			quantityTextField.setEditable(true);
			idTextField.setEditable(true);
		} else if (e.getSource() == exitButton) {
			this.dispose();
		}
	}
}

;