package scraper.MsgBoardScraper;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
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
import javax.swing.JTable;


@SuppressWarnings("serial")
public class MsgBoardFrame extends JFrame{
	
	public static JPanel contentPane;
	private static JPasswordField passwordField;
	private static JTextArea textResult;
	private static JTextField textUser;
	private static JPanel panel;
	
	public static JPanel searchPanel;
	
	
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
		gbl_panel.rowHeights = new int[]{0, 16, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 1.0, 1.0};
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
		
		JScrollPane scrollPaneSearch = new JScrollPane();
		contentPane.add(scrollPaneSearch, BorderLayout.NORTH);
		
//		stM = new SearchTableModel(faecher);
//		table = new JTable();
//		scrollPaneSearch.add(table, BorderLayout.WEST);
		
		
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

}
