package initGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.LayoutStyle.ComponentPlacement;

import dbResource.Temporal_Join;
import temporalGUI.ResultFrame;

public class JoinColumnsFrame {

	private JFrame frame;
	private JButton btnNext;
	private JCheckBox[] jCheckBox;
	String[][] joinTables;
	ArrayList<String> join_col, sel_join_col;
	private JButton btnMenu;
	/**
	 * Create the application.
	 */
	public JoinColumnsFrame(String[][] sel) {
		joinTables = sel;
		Temporal_Join temp_join = new Temporal_Join();
		temp_join.initiate_join(joinTables);
		join_col = temp_join.getJoinColumns();
		initialize();
		
		//Next button action
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//frame.setVisible(false);
				frame.dispose();
				//Get selected tables 
				sel_join_col = new ArrayList<String>();
				for(int i=0;i<join_col.size();i++) {
					boolean isSelected = jCheckBox[i].isSelected();
					if(isSelected) {
						sel_join_col.add(join_col.get(i));
					}
				}
				@SuppressWarnings("unused")
				ResultFrame r = new ResultFrame(temp_join.perform_join(sel_join_col));
			}
		});
		
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//frame.setVisible(false);
				frame.dispose();
				@SuppressWarnings("unused")
				MenuFrame menu = new MenuFrame();
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
		
		JLabel lblNewLabel = new JLabel("Select the columns you want to see after JOIN :");
		btnNext = new JButton("Next");
		
		btnMenu = new JButton("Menu");
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(55)
					.addComponent(lblNewLabel)
					.addContainerGap(625, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(295)
					.addComponent(btnMenu)
					.addPreferredGap(ComponentPlacement.RELATED, 464, Short.MAX_VALUE)
					.addComponent(btnNext)
					.addGap(61))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(35)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED, 857, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNext)
						.addComponent(btnMenu))
					.addGap(38))
		);
		frame.getContentPane().setLayout(groupLayout);

		int x=50, y=50, width=800, height=60; //choose whatever you want
        jCheckBox = new JCheckBox[join_col.size()];
		for(int i=0;i<join_col.size();i++, y+=40) {
            jCheckBox[i] = new JCheckBox(join_col.get(i));
            jCheckBox[i].setBounds(x, y, width, height);
            frame.getContentPane().add(jCheckBox[i]);
        }
	}
}
