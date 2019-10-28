package scraper.MsgBoardScraper;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


@SuppressWarnings("serial")
public class MsgBoardFrame extends JFrame implements ActionListener{
	
	public static JPanel contentPane;
	private static JPasswordField passwordField;
	private static JTextArea textResult;
	private static JTextField textUser;
	
	private static JButton btnShow3Semester;
	private static JPanel showElements;
	private static JPanel searchBox;
	private static JPanel panel;
	private static GridBagConstraints gbc_serchBox;
	
	private static JCheckBox cbMathe;
	

	
	public MsgBoardFrame(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(400,230));
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnOption = new JMenu("Option");
		menuBar.add(mnOption);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mnOption.add(mntmSave);
		
		JMenuItem mntmLoad = new JMenuItem("Load");
		mnOption.add(mntmLoad);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{98, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 16, 0};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 1.0};
		panel.setLayout(gbl_panel);
		
		JLabel lblUser = new JLabel("User : ");
		GridBagConstraints gbc_lblUser = new GridBagConstraints();
		gbc_lblUser.anchor = GridBagConstraints.EAST;
		gbc_lblUser.insets = new Insets(0, 0, 5, 5);
		gbc_lblUser.gridx = 0;
		gbc_lblUser.gridy = 0;
		panel.add(lblUser, gbc_lblUser);
		
		textUser = new JTextField();
		GridBagConstraints gbc_textUser = new GridBagConstraints();
		gbc_textUser.insets = new Insets(0, 0, 5, 5);
		gbc_textUser.fill = GridBagConstraints.HORIZONTAL;
		gbc_textUser.gridx = 1;
		gbc_textUser.gridy = 0;
		panel.add(textUser, gbc_textUser);
		textUser.setColumns(10);
		
		JLabel lblPasswort = new JLabel("Passwort : ");
		GridBagConstraints gbc_lblPasswort = new GridBagConstraints();
		gbc_lblPasswort.anchor = GridBagConstraints.EAST;
		gbc_lblPasswort.insets = new Insets(0, 0, 5, 5);
		gbc_lblPasswort.gridx = 0;
		gbc_lblPasswort.gridy = 1;
		panel.add(lblPasswort, gbc_lblPasswort);
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 1;
		panel.add(passwordField, gbc_passwordField);
		
		//search Btn
		JButton btnSearch = new JButton("Search");
		getRootPane().setDefaultButton(btnSearch);
		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.insets = new Insets(0, 0, 5, 0);
		gbc_btnSearch.gridx = 2;
		gbc_btnSearch.gridy = 1;
		panel.add(btnSearch, gbc_btnSearch);
		
		
		//Sidebar 
		showElements = new JPanel();
		GridBagConstraints gbc_showElements = new GridBagConstraints();
		gbc_showElements.insets = new Insets(0, 0, 0, 5);
		gbc_showElements.fill = GridBagConstraints.BOTH;
		gbc_showElements.gridx = 0;
		gbc_showElements.gridy = 2;
		panel.add(showElements, gbc_showElements);
		
		
		JLabel lbl3Semester = new JLabel("3. Semester");
		showElements.add(lbl3Semester);
		
		btnShow3Semester = new JButton("+");
		btnShow3Semester.setPreferredSize(new Dimension(20,20));;
		showElements.add(btnShow3Semester);
		
		
		
		searchBox = new JPanel();
		gbc_serchBox = new GridBagConstraints();
		gbc_serchBox.anchor = GridBagConstraints.SOUTH;
		gbc_serchBox.insets = new Insets(0, 0, 0, 5);
		gbc_serchBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_serchBox.gridx = 1;
		gbc_serchBox.gridy = 2;
		panel.add(searchBox, gbc_serchBox);
		
		
		GridBagLayout gbl_searchBox = new GridBagLayout();
		gbl_searchBox.columnWidths = new int[]{132, 98, 54, 0};
		gbl_searchBox.rowHeights = new int[]{23, 0, 0};
		gbl_searchBox.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_searchBox.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		searchBox.setLayout(gbl_searchBox);
		
		JCheckBox chckbxKommunikation = new JCheckBox("Kommunikation");
		GridBagConstraints gbc_chckbxKommunikation = new GridBagConstraints();
		gbc_chckbxKommunikation.anchor = GridBagConstraints.NORTHWEST;
		gbc_chckbxKommunikation.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxKommunikation.gridx = 0;
		gbc_chckbxKommunikation.gridy = 0;
		searchBox.add(chckbxKommunikation, gbc_chckbxKommunikation);
		
		JCheckBox chckbxNetzwerke = new JCheckBox("Netzwerke");
		GridBagConstraints gbc_chckbxNetzwerke = new GridBagConstraints();
		gbc_chckbxNetzwerke.anchor = GridBagConstraints.NORTHWEST;
		gbc_chckbxNetzwerke.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxNetzwerke.gridx = 1;
		gbc_chckbxNetzwerke.gridy = 0;
		searchBox.add(chckbxNetzwerke, gbc_chckbxNetzwerke);
		
		JCheckBox chckbxErp = new JCheckBox("ERP");
		GridBagConstraints gbc_chckbxErp = new GridBagConstraints();
		gbc_chckbxErp.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxErp.anchor = GridBagConstraints.NORTHWEST;
		gbc_chckbxErp.gridx = 2;
		gbc_chckbxErp.gridy = 0;
		searchBox.add(chckbxErp, gbc_chckbxErp);
		
		cbMathe = new JCheckBox("Mathematik für Ökonomen");
		GridBagConstraints gbc_chckbxMatheFrkonomen = new GridBagConstraints();
		gbc_chckbxMatheFrkonomen.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxMatheFrkonomen.gridx = 0;
		gbc_chckbxMatheFrkonomen.gridy = 1;
		searchBox.add(cbMathe, gbc_chckbxMatheFrkonomen);
		
		JCheckBox chckbxSE = new JCheckBox("Software Engineering");
		GridBagConstraints gbc_chckbxSE = new GridBagConstraints();
		gbc_chckbxSE.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxSE.gridx = 1;
		gbc_chckbxSE.gridy = 1;
		searchBox.add(chckbxSE, gbc_chckbxSE);
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = textUser.getText();
				String password = new String(passwordField.getPassword());
				try {
					MsgBoardReader.search(user, password);
				} catch (InterruptedException ie) {
					JOptionPane.showMessageDialog(contentPane, "Interrupted Exception");
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(contentPane, "IO Exception");
				}	
			}
		});
		
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		setTextResult(new JTextArea());
		scrollPane.setViewportView(getTextResult());
		getTextResult().setColumns(50);
		textResult.setRows(4);
		
		pack();
	}
	public static JTextArea getTextResult() {
		return textResult;
	}

	public static void setTextResult(JTextArea textResult) {
		MsgBoardFrame.textResult = textResult;
	}
	
	public static String getCbMatheCont() {
		
		return cbMathe.getText();
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==this.btnShow3Semester) {
			
			
		}
	}
}
