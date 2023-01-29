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
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class Main extends JFrame implements ActionListener {
	
	JLabel idLabel;
	JLabel quantityLabel;
	JLabel detailsLabel;
	JLabel subtotalLabel;
	
	JButton findItemButton;
	JButton purchaseItemButton;
	JButton viewOrderButton;
	JButton completeOrderButton;
	JButton newOrderButton;
	JButton exitButton;
	
	JTextField idTextField;
	JTextField quantityTextField;
	JTextField detailsTextField;
	JTextField subtotalTextField;
	
	Item[] orderArr = new Item[30];
	int orderCount = 0;
	int itemCount = 1;
	double subTotal = 0.0;
	double tempSubTotal = 0.0;
	boolean found = false;
	boolean inStock = false;
	
	Main() {
		
		this.setTitle("Nile Dot Com"); // sets title
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // exit out of application
		//frame.setResizable(false); //prevent frame from being resized
		this.setLayout(null);
		this.setSize(750, 500); // sets x and y dimension of frame
		
		ImageIcon image = new ImageIcon("src/river.png"); //create image icon
		this.setIconImage(image.getImage());
		
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
						   System.out.println(lineFromFile);
						   String[] itemFields = lineFromFile.split(",");
						   // here we ensure the id inputed by the user
						   if(itemFields[0].equals(idTextField.getText())) {
							   found = true;
							   if(itemFields[2].strip().equals( "true")) {
								   inStock = true;
								   int quantity = Integer.parseInt(quantityTextField.getText());
								   double discount = 0.0;
								   if(quantity >= 5 && quantity <= 9) {
									   discount = 0.1;
								   } else if(quantity >= 10 && quantity <= 14) {
									   discount = 0.15;
								   } else if(quantity >= 15) {
									   discount = 0.2;
								   }
								   String cost = itemFields[3];
								   double total = Double.parseDouble(cost) * quantity;
								   if (discount > 0.0) {
									   total -= (total * discount);
								   } 
								   orderArr[orderCount] = new Item(itemFields[0],itemFields[1],itemFields[2],itemFields[3], discount, quantity, total);
								   detailsTextField.setText(orderArr[orderCount].id  + orderArr[orderCount].title + " $" + orderArr[orderCount].cost + " " + orderArr[orderCount].quantity + " %" + orderArr[orderCount].discount + " " + orderArr[orderCount].total);
								   tempSubTotal += total;
								   if(orderCount > 0) {
									   subtotalTextField.setText("$" + subTotal);
								   } else {
									   subtotalTextField.setText("$" + tempSubTotal);
								   }
								   
								   findItemButton.setEnabled(false);
								   purchaseItemButton.setEnabled(true);
								   detailsLabel.setText("Details for Item #" + itemCount + ":");
						       break;
							   }
						   }
					   }
					}
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
			System.out.println("Find Item Button clicked!");
			found = false;
			inStock = false;
		} else if (e.getSource() == purchaseItemButton) {
			System.out.println("Purchase Item Button clicked!");
			orderCount += 1;
			itemCount += 1;
			subTotal += tempSubTotal;
			tempSubTotal = 0.0;
			subtotalTextField.setText("$" + subTotal);
			findItemButton.setEnabled(true);
			purchaseItemButton.setEnabled(false);
			completeOrderButton.setEnabled(true);
			viewOrderButton.setEnabled(true);
			idTextField.setText("");
			quantityTextField.setText("");
			idLabel.setText("Enter item ID for Item #" + itemCount + ":");
			quantityLabel.setText("Enter quantity item for Item #" + itemCount + ":");
			subtotalLabel.setText("Order subtotal for " + orderCount + " item(s):");
			purchaseItemButton.setText("Purchase Item #" + itemCount);
			findItemButton.setText("Find Item #" + itemCount);
		} else if (e.getSource() == viewOrderButton) {
			System.out.println("View Order frist id: " + orderArr[0].id);
			String orderStr = "";
			int count = 1;
			for(int i = 0; i < orderCount; i++){
				orderStr = orderStr.concat(count + ". "+ orderArr[i].id  + orderArr[i].title + " $" + orderArr[i].cost + " " + orderArr[i].quantity + " %" + orderArr[i].discount + " " + orderArr[i].total + "\n");
				count += 1;
			}
			JOptionPane.showMessageDialog(this, orderStr, "Nile Dot Com - Current Shopping Cart Status",JOptionPane.INFORMATION_MESSAGE);
			System.out.println("View Order Button clicked!");
		} else if (e.getSource() == completeOrderButton) {
			System.out.println("Complete Order Button clicked!");
		} else if (e.getSource() == newOrderButton) {
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
			completeOrderButton.setEnabled(false);
			viewOrderButton.setEnabled(false);
			System.out.println("New Order Button clicked!");
		} else if (e.getSource() == exitButton) {
			System.out.println("Exit Button clicked!");
			this.dispose();
		}
	}
}

;