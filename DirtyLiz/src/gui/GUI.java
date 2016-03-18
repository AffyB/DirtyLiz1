package gui;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import gamelogic.GameManager;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI {

	private JFrame frame;
	private JTextField noGames;
	//private mainController controller;
	private GameManager newGame;
	private String[] comboBoxArray = {"Play High AI", "Play Low AI", "Play Random AI", "Play My Smart AI", "Play Smart Queen AI", "Play Smart Heart AI", "Play Smart Void AI"};
	private JTextField csvFileName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialise the contents of the frame.
	 */
	private void initialize() {
		//controller = mainController.getController();
		frame = new JFrame();
		frame.setBounds(100, 100, 848, 476);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblDirtyLiz = new JLabel("Dirty Liz - Game Strategies");
		lblDirtyLiz.setHorizontalAlignment(SwingConstants.CENTER);
		lblDirtyLiz.setFont(new Font("Agency FB", Font.PLAIN, 61));
		
		noGames = new JTextField();
		noGames.setColumns(10);
		
		csvFileName = new JTextField();
		csvFileName.setColumns(10);
		
		JLabel lblHowManyGames = new JLabel("How many games?");
		lblHowManyGames.setHorizontalAlignment(SwingConstants.LEFT);
		lblHowManyGames.setFont(new Font("Agency FB", Font.PLAIN, 21));
		
		JLabel lblAiForPlayer = new JLabel("AI for player 1");
		lblAiForPlayer.setHorizontalAlignment(SwingConstants.LEFT);
		lblAiForPlayer.setFont(new Font("Agency FB", Font.PLAIN, 21));
		
		JLabel lblAiForPlayer_1 = new JLabel("AI for player 2");
		lblAiForPlayer_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblAiForPlayer_1.setFont(new Font("Agency FB", Font.PLAIN, 21));
		
		JLabel lblAiForPlayer_2 = new JLabel("AI for player 3");
		lblAiForPlayer_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblAiForPlayer_2.setFont(new Font("Agency FB", Font.PLAIN, 21));
		
		JLabel lblAiForPlayer_3 = new JLabel("AI for player 4");
		lblAiForPlayer_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblAiForPlayer_3.setFont(new Font("Agency FB", Font.PLAIN, 21));
		
		JComboBox comboBox = new JComboBox(comboBoxArray);
		
		JComboBox comboBox2 = new JComboBox(comboBoxArray);
		
		JComboBox comboBox3 = new JComboBox(comboBoxArray);
		
		JComboBox comboBox4 = new JComboBox(comboBoxArray);
		
		JButton btnStart = new JButton("Begin");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String noOfGames = noGames.getText();
				int numberGames = Integer.parseInt(noOfGames);
				String nameOfFile = csvFileName.getText();
				String AI1 = (String)comboBox.getSelectedItem();
				String AI2 = (String)comboBox2.getSelectedItem();
				String AI3 = (String)comboBox3.getSelectedItem();
				String AI4 = (String)comboBox4.getSelectedItem();
				newGame = new GameManager(numberGames, AI1, AI2, AI3, AI4, nameOfFile);
				newGame.start();
				JOptionPane.showMessageDialog(null,"Finished! Close to run another simulation");
			}
		});
		
		
		JButton btnNewButton = new JButton("Press to Start Fresh Stimulation ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				noGames.setText(null);	
				csvFileName.setText(null);
				
				
			}
		});
		
		
		
		JLabel lblNameForYour = new JLabel("Name for your output file");
		lblNameForYour.setHorizontalAlignment(SwingConstants.LEFT);
		lblNameForYour.setFont(new Font("Agency FB", Font.PLAIN, 21));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblDirtyLiz, GroupLayout.PREFERRED_SIZE, 824, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(23)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblAiForPlayer_1, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblAiForPlayer_2, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblAiForPlayer_3, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(168)
							.addComponent(btnStart, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblHowManyGames, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAiForPlayer, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(noGames, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
									.addGap(85)
									.addComponent(lblNameForYour, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(csvFileName, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
									.addGap(150))))))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(265)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(334, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblDirtyLiz)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addGap(39)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblHowManyGames, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addComponent(noGames, GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNameForYour, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addComponent(csvFileName, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)))
					.addGap(25)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblAiForPlayer, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblAiForPlayer_1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBox2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(42)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblAiForPlayer_3, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBox4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(67))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(comboBox3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAiForPlayer_2, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addGap(23)
							.addComponent(btnStart, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
							.addGap(27))))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
