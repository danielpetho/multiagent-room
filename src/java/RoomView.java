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
	Room env;
	
	JFrame frame;
	
	//labels
	JLabel winLabel1, winLabel2, winLabel3, stockLabel, tempLabel, moistLabel, humLabel, lightLabel;
	
	//buttons
	JButton btnWin1,btnWin2,btnWin3, btnLight, btnFill;
	
	//feedback
	JLabel lWin1, lWin2, lWin3, lLight, lFill, lTemp, lMoisture, lHum;
	
	//spinners
	JSpinner spTemp, spMoist, spHum;
	
	//progressbar for stock
	JProgressBar stock;
	

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
	
	public void setEnv(Room env) {
		this.env = env;
	}
	
	public void refresh() {
		
		if(rmodel.windows[0]) {
			btnWin1.setText("Close");
			lWin1.setText("Open");
		} else {
			btnWin1.setText("Open");
			lWin1.setText("Closed");
		}
		
		if(rmodel.windows[1]) {
			btnWin2.setText("Close");
			lWin2.setText("Open");
		} else {
			btnWin2.setText("Open");
			lWin2.setText("Closed");
		}
		
		if(rmodel.windows[2]) {
			btnWin3.setText("Close");
			lWin3.setText("Open");
		} else {
			btnWin3.setText("Open");
			lWin3.setText("Closed");
		}
		
		if(rmodel.light) {
			btnLight.setText("Off");
			lLight.setText("On");
		} else {
			btnLight.setText("On");
			lLight.setText("Off");
		}
		
		humLabel.setText("Humidity: " + rmodel.hum + "%");
		moistLabel.setText("Moisture: " + rmodel.moist + "%");
		tempLabel.setText("Temperature: " + rmodel.temp + "°C");
		stock.setValue(rmodel.stock);
		
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(RoomModel model) {
		
		rmodel = model;
				
		frame = new JFrame("Multiagent Smart Room");
		frame.setSize(500, 500);
		frame.setLayout(null);
		
		winLabel1 = new JLabel("Window 1:");
		winLabel1.setFont(new Font("Ubuntu", Font.BOLD, 15));
		winLabel1.setBounds(50,50, 80,20); 
		btnWin1 = new JButton("Close");
		btnWin1.setBounds(230,50, 80,20);
		lWin1 = new JLabel("Open");
		lWin1.setBounds(140,50, 80,20); 		
		
		winLabel2 = new JLabel("Window 2:");
		winLabel2.setBounds(50,80, 200,20); 
		winLabel2.setFont(new Font("Ubuntu", Font.BOLD, 15));
		btnWin2 = new JButton("Close");
		btnWin2.setBounds(230,80, 80,20);
		lWin2 = new JLabel("Open");
		lWin2.setBounds(140,80, 80,20);
		
		winLabel3 = new JLabel("Window 3:");
		winLabel3.setBounds(50,110, 200,20);
		winLabel3.setFont(new Font("Ubuntu", Font.BOLD, 15));
		btnWin3 = new JButton("Close");
		btnWin3.setBounds(230,110, 80,20);
		lWin3 = new JLabel("Open");
		lWin3.setBounds(140,110, 80,20); 
		
		lightLabel = new JLabel("Internal Lights:");
		lightLabel.setBounds(30,145, 200,20);
		lightLabel.setFont(new Font("Ubuntu", Font.BOLD, 15));
		btnLight = new JButton("Off");
		btnLight.setBounds(230,145, 60,20);
		lLight = new JLabel("On");
		lLight.setBounds(160,145, 80,20);	
	
		SpinnerModel tempValue = new SpinnerNumberModel(rmodel.temp, -10, 35, 1);
		spTemp = new JSpinner(tempValue);
		spTemp.setBounds(230, 180, 50, 30);
		tempLabel = new JLabel("Temperature: " + rmodel.temp + "°C");
		tempLabel.setBounds(50, 180, 200, 30);
		
		SpinnerModel moistValue = new SpinnerNumberModel(rmodel.moist, 0, 100, 1);
		spMoist = new JSpinner(moistValue);
		spMoist.setBounds(230, 215, 50, 30);
		moistLabel = new JLabel("Moisture: " + rmodel.moist + "%");
		moistLabel.setBounds(50, 215, 200, 30);
		
		SpinnerModel humValue = new SpinnerNumberModel(rmodel.hum, 0, 100, 1);
		spHum = new JSpinner(humValue);
		spHum.setBounds(230, 250, 50, 30);
		humLabel = new JLabel("Humidity: " + rmodel.hum + "%");
		humLabel.setBounds(50, 250, 200, 30);
		
		
		stockLabel = new JLabel("Stock:");
		stockLabel.setBounds(50, 290, 200, 20);
		stock = new JProgressBar();
		stock.setValue(rmodel.stock);
		stock.setStringPainted(true);
		stock.setBounds(140, 290, 100, 20);
		btnFill = new JButton("Fill");
		btnFill.setBounds(230, 290, 80, 20);
		
		
		
		btnWin1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if(lWin1.getText() == "Open") {
					rmodel.closeWindow(0);
				} else {
					rmodel.openWindow(0);
				}
				
			}  
		});
		
		btnWin2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if(lWin2.getText() == "Open") {

					rmodel.closeWindow(1);
				} else {

					rmodel.openWindow(1);
				}
				
			}  
		});
		
		btnWin3.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if(lWin3.getText() == "Open") {
					rmodel.closeWindow(2);
				} else {
					rmodel.openWindow(2);
				}
				
			}  
		});
		
		btnLight.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if(lLight.getText() == "On") {
					rmodel.offLight();
				} else {
					rmodel.onLight();
				}
				
			}  
		});
		
		btnFill.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				rmodel.stock();		
			}  
		});
		
		spTemp.addChangeListener(new ChangeListener() {  
	        public void stateChanged(ChangeEvent e) {
	        	JSpinner spin = (JSpinner) e.getSource();
	        	int value = (Integer) spin.getValue();
	        	tempLabel.setText("Temperature: " + value + "°C");
	        	rmodel.setTemp(value);
	        }  
	     });
		
		spMoist.addChangeListener(new ChangeListener() {  
	        public void stateChanged(ChangeEvent e) {
	        	JSpinner spin = (JSpinner) e.getSource();
	        	int value = (Integer) spin.getValue();
	        	moistLabel.setText("Moisture: " + value + "%");
	        	rmodel.setMoist(value);
	        }  
	     });
		
		spHum.addChangeListener(new ChangeListener() {  
	        public void stateChanged(ChangeEvent e) {
	        	JSpinner spin = (JSpinner) e.getSource();
	        	int value = (Integer) spin.getValue();
	        	humLabel.setText("Humidity: " + value + "%");
	        	rmodel.setHum(value);
	        	env.updatePercepts();
	    		env.informAgsEnvironmentChanged();
	        }  
	     });
		
	  	
		frame.setBounds(100, 100, 400, 400);
		
		frame.add(spTemp);
		frame.add(tempLabel);
		frame.add(spMoist);
		frame.add(moistLabel);
		frame.add(spHum);
		frame.add(humLabel);
		
		frame.add(lightLabel);
		frame.add(btnLight);
		frame.add(lLight);
		
		
		frame.add(winLabel1);
		frame.add(winLabel2);
		frame.add(winLabel3);
		frame.add(btnWin1);
		frame.add(btnWin2);
		frame.add(btnWin3);
		frame.add(lWin1);
		frame.add(lWin2);
		frame.add(lWin3);
		
		frame.add(stockLabel);
		frame.add(stock);
		frame.add(btnFill);
		
		refresh();
		frame.setVisible(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
