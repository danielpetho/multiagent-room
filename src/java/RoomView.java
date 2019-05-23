import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;  
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.event.ChangeListener;

public class RoomView {

	RoomModel rmodel;
	
	private JFrame frame;
	
	//labels
	JLabel winLabel1, winLabel2, winLabel3, fillLabel, tempLabel, moistLabel, humLabel;
	
	//buttons
	JButton btnWin1,btnWin2,btnWin3, btnLight, btnFill;
	
	//feedback
	JLabel lWin1, lWin2, lWin3, lLight, lFill, lTemp, lMoisture, lHum;
	
	//spinners
	JSpinner spTemp, spMoisture, spHum;
	

	/**
	 * Launch the application.
	 */

	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RoomView window = new RoomView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public RoomView(RoomModel model) {
		initialize(model);
	}
	
	public void setModel(RoomModel model) {
		rmodel = model;
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(RoomModel model) {
		
		rmodel = model;
				
		frame = new JFrame("Multiagent Smart Room");
		frame.setSize(800, 800);
		frame.setLayout(null);
		
		winLabel1 = new JLabel("Window 1");
		winLabel1.setFont(new Font("Serif", Font.BOLD, 17));
		winLabel1.setBounds(100,0, 200,30); 
		btnWin1 = new JButton("Close");
		btnWin1.setBounds(50,50, 80,20);
		lWin1 = new JLabel("Open");
		lWin1.setBounds(200,50, 80,20); 
		
		
		winLabel2 = new JLabel("Window 2");
		winLabel2.setBounds(100,100, 200,30); 
		winLabel2.setFont(new Font("Serif", Font.BOLD, 17));
		btnWin2 = new JButton("Close");
		btnWin2.setBounds(50,150, 80,20);
		lWin2 = new JLabel("Open");
		lWin2.setBounds(200,150, 80,20);
		
		winLabel3 = new JLabel("Window 3");
		winLabel3.setBounds(100,200, 200,30);
		winLabel3.setFont(new Font("Serif", Font.BOLD, 17));
		btnWin3 = new JButton("Close");
		btnWin3.setBounds(50,250, 80,20);
		lWin3 = new JLabel("Open");
		lWin3.setBounds(200,250, 80,20); 
		
		
		
	
		SpinnerModel tempValue = new SpinnerNumberModel(23, -10, 35, 1);
		spTemp = new JSpinner(tempValue);
		spTemp.setBounds(50, 300, 50, 50);
		tempLabel = new JLabel("Temperature: 23");
		tempLabel.setBounds(200, 300, 200, 30);
		
		SpinnerModel moistValue = new SpinnerNumberModel(23, -10, 35, 1);
		SpinnerModel humValue = new SpinnerNumberModel(23, -10, 35, 1);
		
		
		
		btnWin1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if(lWin1.getText() == "Open") {
					btnWin1.setText("Open");
					lWin1.setText("Closed");
					rmodel.closeWindow(0);
				} else {
					btnWin1.setText("Close");
					lWin1.setText("Open");
					rmodel.openWindow(0);
				}
				
			}  
		});
		
		btnWin2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if(lWin2.getText() == "Open") {
					btnWin2.setText("Open");
					lWin2.setText("Closed");
					rmodel.closeWindow(1);
				} else {
					btnWin2.setText("Close");
					lWin2.setText("Open");
					rmodel.openWindow(1);
				}
				
			}  
		});
		
		btnWin3.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if(lWin3.getText() == "Open") {
					btnWin3.setText("Open");
					lWin3.setText("Closed");
					rmodel.closeWindow(2);
				} else {
					btnWin3.setText("Close");
					lWin3.setText("Open");
					rmodel.openWindow(2);
				}
				
			}  
		});
		
		spTemp.addChangeListener(new ChangeListener() {  
	        public void stateChanged(ChangeEvent e) {
	        	JSpinner spin = (JSpinner) e.getSource();
	        	int value = (Integer) spin.getValue();
	        	tempLabel.setText("Temperature : " + value);
	        	rmodel.setTemp(value);
	        }  
	     });  
		
	   
		
		btnLight = new JButton("On");
		btnFill = new JButton("Fill");	
		frame.setBounds(100, 100, 800, 600);
		
		frame.add(spTemp);
		frame.add(tempLabel);
		frame.add(winLabel1);
		frame.add(winLabel2);
		frame.add(winLabel3);
		frame.add(btnWin1);
		frame.add(btnWin2);
		frame.add(btnWin3);
		frame.add(lWin1);
		frame.add(lWin2);
		frame.add(lWin3);
		frame.setVisible(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
