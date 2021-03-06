package initGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;

import dbResource.DatabaseConnection;

public class UpdateFrame {

	private JFrame frame;
	Map<String,String> colNames;
	String selTable;
	JButton btnNext;
	JButton btnMenu;
	JTextField[] textFields;
	JTextField[] pktextFields;
	Map<String,String> modCol;
	Map<String,String> pk;
	Map<String,String> pkCol;

	/**
	 * Create the application.
	 */
	public UpdateFrame(String table,Map<String,String> sel) {
		selTable = table;
		colNames = sel;
		pk = DatabaseConnection.getInstance().getPrimaryKey(selTable);
		initialize();
		
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//frame.setVisible(false);
				frame.dispose();
				//Get selected tables 
				int i = 0;
				pkCol = new HashMap<String,String>();
				for(Map.Entry<String,String> entry:pk.entrySet()){
					pkCol.put(entry.getKey(), pktextFields[i].getText());
					i++;
				}
				i = 0;
				modCol = new HashMap<String,String>();
				for(Map.Entry<String,String> entry:colNames.entrySet()){
					modCol.put(entry.getKey(), textFields[i].getText());
					i++;
				}
				DatabaseConnection.getInstance().updateRow(pkCol,modCol,selTable);
				@SuppressWarnings("unused")
				MenuFrame m = new MenuFrame();
			}	
				
		});	
		
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//frame.setVisible(false);
				frame.dispose();
				@SuppressWarnings("unused")
				MenuFrame m = new MenuFrame();
			}	
				
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 1000, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblEnterValuesTo = new JLabel("Enter the details(PRIMARY KEY) of the row you want to 'update' :");
		int i=0, x=50, y=50, width=500, height=60; //choose whatever you want
		pktextFields = new JTextField[pk.size()];
		for(Map.Entry<String,String> entry:pk.entrySet()) {
			
			JLabel lblCol = new JLabel(entry.getKey()+"("+entry.getValue()+") :");
			lblCol.setBounds(x, y, width, height);
			frame.getContentPane().add(lblCol);
			y+=40;
			pktextFields[i] = new JTextField();
			pktextFields[i].setColumns(10);
            pktextFields[i].setBounds(x, y, width, height);
            
            frame.getContentPane().add(pktextFields[i]);
            y+=40;
            i++;
		}
		
		btnNext = new JButton("Next");
		
		btnMenu = new JButton("Menu");
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addComponent(lblEnterValuesTo)
					.addContainerGap(531, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(427)
					.addComponent(btnMenu, GroupLayout.PREFERRED_SIZE, 73, Short.MAX_VALUE)
					.addGap(398)
					.addComponent(btnNext)
					.addGap(36))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(36)
					.addComponent(lblEnterValuesTo)
					.addGap(811)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnMenu, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnNext))
					.addGap(85))
		);
		frame.getContentPane().setLayout(groupLayout);
		y+=50;
		JLabel lblEnterValues = new JLabel("Enter the values to 'update' :");
		lblEnterValues.setBounds(x-20, y, width, height);
		frame.getContentPane().add(lblEnterValues);
		y+=40;
		i=0;
		textFields = new JTextField[colNames.size()];
		for(Map.Entry<String,String> entry:colNames.entrySet()) {
			
			JLabel lblCol = new JLabel(entry.getKey()+"("+entry.getValue()+") :");
			lblCol.setBounds(x, y, width, height);
			frame.getContentPane().add(lblCol);
			y+=40;
			textFields[i] = new JTextField();
			textFields[i].setColumns(10);
            textFields[i].setBounds(x, y, width, height);
            
            frame.getContentPane().add(textFields[i]);
            y+=40;
            i++;
		}
	}
}
