import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class Main {
//	JButton findItemButton;
	
//	Main() {
//		this.setTitle("Nile Dot Com"); // sets title
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit out of application
//		//frame.setResizable(false); //prevent frame from being resized
//		this.setLayout(null);
//		this.setSize(750, 500); // sets x and y dimension of frame
//		this.setVisible(true); // makes frame visible
//		
//		ImageIcon image = new ImageIcon("src/river.png"); //create image icon
//		this.setIconImage(image.getImage());
//		
//		JPanel northPanel = new JPanel();
//		northPanel.setBackground(Color.DARK_GRAY);
//		northPanel.setBounds(0,0,750,250);
//		
//		// north panel labels
//		JLabel idLabel = new JLabel();
//		idLabel.setText("Enter item ID for Item #1");
//		idLabel.setForeground(Color.yellow);
//		idLabel.setFont(new Font("Verdana",Font.PLAIN, 10));
//		northPanel.add(idLabel);
//		JLabel quantityLabel = new JLabel();
//		quantityLabel.setText("Enter quantity item for Item #1");
//		quantityLabel.setForeground(Color.yellow);
//		quantityLabel.setFont(new Font("Verdana",Font.PLAIN, 10));
//		northPanel.add(quantityLabel);
//		JLabel detailsLabel = new JLabel();
//		detailsLabel.setText("Details for Item #1");
//		detailsLabel.setForeground(Color.yellow);
//		detailsLabel.setFont(new Font("Verdana",Font.PLAIN, 10));
//		northPanel.add(detailsLabel);
//		JLabel subtotalLabel = new JLabel();
//		subtotalLabel.setText("Order subtotal for 0 item(s)");
//		subtotalLabel.setForeground(Color.yellow);
//		subtotalLabel.setFont(new Font("Verdana",Font.PLAIN, 10));
//		northPanel.add(subtotalLabel);
//		//end of north panel labels
//		
//		JPanel southPanel = new JPanel();
//		southPanel.setBackground(Color.cyan);
//		southPanel.setBounds(0,250,750,250);
//		findItemButton = new JButton();
//		findItemButton.addActionListener(this);
//		southPanel.add(findItemButton);
//		
//		this.add(northPanel);
//		this.add(southPanel);
//	}
	
	public static void main(String args[]) {
		JFrame frame = new JFrame();
		frame.setTitle("Nile Dot Com"); // sets title
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit out of application
		//frame.setResizable(false); //prevent frame from being resized
		frame.setLayout(null);
		frame.setSize(750, 500); // sets x and y dimension of frame
		
		ImageIcon image = new ImageIcon("src/river.png"); //create image icon
		frame.setIconImage(image.getImage());
		
		JPanel northPanel = new JPanel();
		northPanel.setBackground(Color.DARK_GRAY);
		northPanel.setBounds(0,0,750,300);
	
		
		// label panel
		JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING, 0,15));
		labelPanel.setBackground(Color.DARK_GRAY);
		labelPanel.setPreferredSize(new Dimension(250,150));
		
		// label panel labels
		JLabel idLabel = new JLabel();
		idLabel.setText("Enter item ID for Item #1:");
		idLabel.setForeground(Color.yellow);
		idLabel.setFont(new Font("Verdana",Font.PLAIN, 14));
		labelPanel.add(idLabel);
		JLabel quantityLabel = new JLabel();
		quantityLabel.setText("Enter quantity item for Item #1:");
		quantityLabel.setForeground(Color.yellow);
		quantityLabel.setFont(new Font("Verdana",Font.PLAIN, 14));
		labelPanel.add(quantityLabel);
		JLabel detailsLabel = new JLabel();
		detailsLabel.setText("Details for Item #1:");
		detailsLabel.setForeground(Color.yellow);
		detailsLabel.setFont(new Font("Verdana",Font.PLAIN, 14));
		labelPanel.add(detailsLabel);
		JLabel subtotalLabel = new JLabel();
		subtotalLabel.setText("Order subtotal for 0 item(s):");
		subtotalLabel.setForeground(Color.yellow);
		subtotalLabel.setFont(new Font("Verdana",Font.PLAIN, 14));
		labelPanel.add(subtotalLabel);
		//end of label panel labels
		
		northPanel.add(labelPanel);
		
		// text field panel labels
		JPanel textfieldPanel = new JPanel();
		textfieldPanel.setBackground(Color.DARK_GRAY);
		textfieldPanel.setPreferredSize(new Dimension(250,300));
		northPanel.add(textfieldPanel);
		
		JPanel southPanel = new JPanel(new FlowLayout(0,15,15));
		southPanel.setBackground(Color.cyan);
		southPanel.setBounds(0,300,750,200);
		
		// south panel buttons
		JButton findItemButton = new JButton();
		findItemButton.setText("Find Item #1");
		findItemButton.setFocusable(false);
		findItemButton.setBackground(Color.white);
		findItemButton.setFont(new Font("Verdana",Font.PLAIN, 14));
		findItemButton.addActionListener(e -> findItem());
		findItemButton.setPreferredSize(new Dimension(350,25));
		southPanel.add(findItemButton);
		
		JButton purchaseItemButton = new JButton();
		purchaseItemButton.setText("Purchase Item #1");
		purchaseItemButton.setFocusable(false);
		purchaseItemButton.setBackground(Color.white);
		purchaseItemButton.setFont(new Font("Verdana",Font.PLAIN, 14));
		purchaseItemButton.addActionListener(e -> findItem());
		purchaseItemButton.setPreferredSize(new Dimension(350,25));
		southPanel.add(purchaseItemButton);
		
		JButton viewOrderButton = new JButton();
		viewOrderButton.setText("View Current Order");
		viewOrderButton.setFocusable(false);
		viewOrderButton.setBackground(Color.white);
		viewOrderButton.setFont(new Font("Verdana",Font.PLAIN, 14));
		viewOrderButton.addActionListener(e -> findItem());
		viewOrderButton.setPreferredSize(new Dimension(350,25));
		southPanel.add(viewOrderButton);
		
		JButton completeOrderButton = new JButton();
		completeOrderButton.setText("Complete Order - Check Out");
		completeOrderButton.setFocusable(false);
		completeOrderButton.setBackground(Color.white);
		completeOrderButton.setFont(new Font("Verdana",Font.PLAIN, 14));
		completeOrderButton.addActionListener(e -> findItem());
		completeOrderButton.setPreferredSize(new Dimension(350,25));
		southPanel.add(completeOrderButton);
		
		JButton newOrderButton = new JButton();
		newOrderButton.setText("Start New Order");
		newOrderButton.setFocusable(false);
		newOrderButton.setBackground(Color.white);
		newOrderButton.setFont(new Font("Verdana",Font.PLAIN, 14));
		newOrderButton.addActionListener(e -> findItem());
		newOrderButton.setPreferredSize(new Dimension(350,25));
		southPanel.add(newOrderButton);
		
		JButton exitButton = new JButton();
		exitButton.setText("Exit (Close App)");
		exitButton.setFocusable(false);
		exitButton.setBackground(Color.white);
		exitButton.setFont(new Font("Verdana",Font.PLAIN, 14));
		exitButton.addActionListener(e -> findItem());
		exitButton.setPreferredSize(new Dimension(350,25));
		southPanel.add(exitButton);
		
		frame.add(northPanel);
		frame.add(southPanel);
		frame.setVisible(true); // makes frame visible
	}

	static void findItem() {
		System.out.println("Find item button clicked");
	}
}

;