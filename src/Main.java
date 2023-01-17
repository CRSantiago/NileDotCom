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
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class Main extends JFrame implements ActionListener {
	
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
	
	Main() {
		
		this.setTitle("Nile Dot Com"); // sets title
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit out of application
		//frame.setResizable(false); //prevent frame from being resized
		this.setLayout(null);
		this.setSize(750, 500); // sets x and y dimension of frame
		
		ImageIcon image = new ImageIcon("src/river.png"); //create image icon
		this.setIconImage(image.getImage());
		
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
		JPanel textfieldPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 0, 15));
		textfieldPanel.setBackground(Color.DARK_GRAY);
		textfieldPanel.setPreferredSize(new Dimension(300,150));
		
		//text fields
		idTextField = new JTextField();
		idTextField.setPreferredSize(new Dimension(300,20));
		textfieldPanel.add(idTextField);
		
		quantityTextField = new JTextField();
		quantityTextField.setPreferredSize(new Dimension(300,20));
		textfieldPanel.add(quantityTextField);
		
		detailsTextField = new JTextField();
		detailsTextField.setPreferredSize(new Dimension(300,20));
		textfieldPanel.add(detailsTextField);
		
		subtotalTextField = new JTextField();
		subtotalTextField.setPreferredSize(new Dimension(300,20));
		textfieldPanel.add(subtotalTextField);
		northPanel.add(textfieldPanel);
		
		JPanel southPanel = new JPanel(new FlowLayout(0,15,15));
		southPanel.setBackground(Color.cyan);
		southPanel.setBounds(0,300,750,200);
		
		// south panel buttons
		findItemButton = new JButton();
		findItemButton.setText("Find Item #1");
		findItemButton.setFocusable(false);
		findItemButton.setBackground(Color.white);
		findItemButton.setFont(new Font("Verdana",Font.PLAIN, 14));
		findItemButton.addActionListener(this);
		findItemButton.setPreferredSize(new Dimension(350,25));
		southPanel.add(findItemButton);
		
		purchaseItemButton = new JButton();
		purchaseItemButton.setText("Purchase Item #1");
		purchaseItemButton.setFocusable(false);
		purchaseItemButton.setBackground(Color.white);
		purchaseItemButton.setFont(new Font("Verdana",Font.PLAIN, 14));
		purchaseItemButton.addActionListener(this);
		purchaseItemButton.setPreferredSize(new Dimension(350,25));
		southPanel.add(purchaseItemButton);
		
		viewOrderButton = new JButton();
		viewOrderButton.setText("View Current Order");
		viewOrderButton.setFocusable(false);
		viewOrderButton.setBackground(Color.white);
		viewOrderButton.setFont(new Font("Verdana",Font.PLAIN, 14));
		viewOrderButton.addActionListener(this);
		viewOrderButton.setPreferredSize(new Dimension(350,25));
		southPanel.add(viewOrderButton);
		
		completeOrderButton = new JButton();
		completeOrderButton.setText("Complete Order - Check Out");
		completeOrderButton.setFocusable(false);
		completeOrderButton.setBackground(Color.white);
		completeOrderButton.setFont(new Font("Verdana",Font.PLAIN, 14));
		completeOrderButton.addActionListener(this);
		completeOrderButton.setPreferredSize(new Dimension(350,25));
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
			System.out.println("Find Item Button clicked!");
		} else if (e.getSource() == purchaseItemButton) {
			System.out.println("Purchase Item Button clicked!");
		} else if (e.getSource() == viewOrderButton) {
			System.out.println("View Order Button clicked!");
		} else if (e.getSource() == completeOrderButton) {
			System.out.println("Complete Order Button clicked!");
		} else if (e.getSource() == newOrderButton) {
			System.out.println("New Order Button clicked!");
		} else if (e.getSource() == exitButton) {
			System.out.println("Exit Button clicked!");
		}
	}
}

;