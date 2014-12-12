package ch.fhnw.gui;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Font;
import java.io.File;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingWorker;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import ch.fhnw.support.FileChooser;
import ch.fhnw.support.TextAreaOutputStream;
import ch.fhnw.wiki.XWikiGenerator;


public class MainWindow extends JFrame {

	/**
	 *  
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFileChooser fileChooserBPMN;
	private JLabel lblResult = new JLabel("Ready!");
	private JCheckBox chckbxUserAdmin = new JCheckBox("User Admin");
	private JCheckBox chckbxSpaceName = new JCheckBox("Space Name");
	private JTextField txtFldDirectory;
	private JTextField txtUser;
	private JTextField txtSpace;
	private JTextArea textArea;
	private String directoryBPMN="";
	private String xwikiUser = "Admin";
	private String spaceName = "IMPORTED";
	private JButton btnTranslator = new JButton("Translate in XWiki");
	private JButton btnSearch;
	private SwingWorker worker;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {


					File file = new File("C:\\Users\\Daniele\\Desktop\\LearnPAd.xml"); 
					JFileChooser directory = new JFileChooser();
					directory.setSelectedFile(file);

					//CHANGE TO 1 TO START THE GUI, THIS IS TEST MODE
					int test = 1;

					if (test == 0){

						XWikiGenerator.xWikiGenerator(directory, "Admin", "IMPORTED");

					}else{


						MainWindow frame = new MainWindow();
						frame.setVisible(true);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}



	/**
	 * Create the frame.
	 */
	public MainWindow() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Daniele\\Google Drive\\TESI\\ECLIPSE\\BPMN to XML\\BPMN to WIki.png"));
		setResizable(false);
		setTitle("BPMN to XWIKI translator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 818, 687);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				txtFldDirectory.setText("");
				btnTranslator.setEnabled(false);
				lblResult.setText("Ready!");
				textArea.setText("");
			}
		});
		mnFile.add(mntmNew);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				System.exit(0);
			}
		});
		mnFile.add(mntmExit);


		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		// search directory button
		btnSearch = new JButton("Insert the ADOxx XML File");
		//directory = "empty";
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				fileChooserBPMN = FileChooser.fileChooser();
				directoryBPMN = fileChooserBPMN.getSelectedFile().toString();
				txtFldDirectory.setText(directoryBPMN);
				btnTranslator.setEnabled(true);
			}
		});


		btnTranslator.setEnabled(false);
		btnTranslator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				textArea.setText("");
				btnTranslator.setEnabled(false);
				// multithread worker to show the results on console
				if (worker!=null)
				{
					worker.cancel(true);
				}
				worker = new SwingWorker()
				{
					@Override
					protected Integer doInBackground()//Perform the required GUI update here.
					{
						try
						{


							if(!chckbxUserAdmin.isSelected() && !chckbxSpaceName.isSelected()){
								xwikiUser = txtUser.getText().toString();
								spaceName = txtSpace.getText().toString();
							}
							else if (!chckbxUserAdmin.isSelected() && chckbxSpaceName.isSelected())
							{
								xwikiUser = txtUser.getText().toString();
							}
							else if (chckbxUserAdmin.isSelected() && !chckbxSpaceName.isSelected())
							{
								spaceName = txtSpace.getText().toString();
							}




							// Forward the directory of the adoxx xml file to the xwikigenerator
							if(chckbxUserAdmin.isSelected() && chckbxSpaceName.isSelected())
							{
								lblResult.setText( XWikiGenerator.xWikiGenerator(fileChooserBPMN, xwikiUser="Admin", spaceName="IMPORTED") );									
							}
							else if( (!chckbxUserAdmin.isSelected() && txtUser.getText().equals("")) || (!chckbxSpaceName.isSelected() && txtSpace.getText().equals("") ) )
							{
								lblResult.setText("Please insert a User/Space Name  for XWiki");
							}
							else if(!chckbxUserAdmin.isSelected() && !chckbxSpaceName.isSelected() && !txtUser.getText().equals("") && !txtSpace.getText().equals("") )
							{
								lblResult.setText( XWikiGenerator.xWikiGenerator(fileChooserBPMN, xwikiUser=txtUser.getText().toString(), spaceName=txtSpace.getText().toString()) );
							}
							else if( (!chckbxUserAdmin.isSelected() && !txtUser.getText().equals("")) || (!chckbxSpaceName.isSelected() && !txtSpace.getText().equals("") ))
							{
								if(!txtUser.getText().equals(""))
									lblResult.setText( XWikiGenerator.xWikiGenerator(fileChooserBPMN, xwikiUser=txtUser.getText().toString(), spaceName) );
								else if (!txtSpace.getText().equals(""))
								{
									lblResult.setText( XWikiGenerator.xWikiGenerator(fileChooserBPMN, xwikiUser, spaceName=txtSpace.getText().toString()) );
								}
							}





						}catch(Exception ex){}
						//activate the button when the process is over
						btnTranslator.setEnabled(true);
						return 0;
					}       
				};
				//Schedules this SwingWorker for execution on a worker thread.
				worker.execute();



			}
		});

		txtFldDirectory = new JTextField();
		txtFldDirectory.setEditable(false);
		txtFldDirectory.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtFldDirectory.setColumns(10);


		lblResult.setHorizontalAlignment(SwingConstants.CENTER);


		chckbxUserAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {


				if (!chckbxUserAdmin.isSelected()){
					//user is not admin and it must show an input box with the name of the User
					txtUser.setEnabled(true);
					txtUser.setEditable(true);
					//System.out.println("user = not Admin");
				}
				else if(chckbxUserAdmin.isSelected())
				{
					//System.out.println("user = admin");
					txtUser.setEnabled(false);
					txtUser.setEditable(false);
					//xwikiUser = "Admin";
				}

			}
		});
		chckbxUserAdmin.setSelected(true);

		txtUser = new JTextField();
		txtUser.setText("admin");
		txtUser.setEditable(false);
		txtUser.setEnabled(false);
		txtUser.setColumns(10);

		JTextPane txtpnChangeThisParameter = new JTextPane();
		txtpnChangeThisParameter.setBackground(UIManager.getColor("Button.background"));
		txtpnChangeThisParameter.setText("Modify this parameter if you want to change User in Xwiki");


		chckbxSpaceName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (!chckbxSpaceName.isSelected()){
					//user is not admin and it must show an input box with the name of the User
					txtSpace.setEnabled(true);
					txtSpace.setEditable(true);
					//System.out.println("user = not Admin");
				}
				else if(chckbxSpaceName.isSelected())
				{
					//System.out.println("user = admin");
					txtSpace.setEnabled(false);
					txtSpace.setEditable(false);
					//xwikiUser = "Admin";
				}

			}
		});
		chckbxSpaceName.setSelected(true);

		txtSpace = new JTextField();
		txtSpace.setText("IMPORTED");
		txtSpace.setEditable(false);
		txtSpace.setEnabled(false);
		txtSpace.setColumns(10);

		JSeparator separator = new JSeparator();

		JTextPane txtpnModifyThisParameter = new JTextPane();
		txtpnModifyThisParameter.setText("Modify this parameter if you want to change Space in Xwiki");
		txtpnModifyThisParameter.setBackground(UIManager.getColor("Button.background"));

		JScrollPane scrollPane = new JScrollPane();

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(32)
										.addComponent(chckbxSpaceName)
										.addGap(18)
										.addComponent(txtSpace, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(txtpnModifyThisParameter, GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE))
										.addGroup(gl_contentPane.createSequentialGroup()
												.addGap(33)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
														.addGroup(gl_contentPane.createSequentialGroup()
																.addComponent(separator, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
																.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
																		.addGroup(gl_contentPane.createSequentialGroup()
																				.addComponent(txtFldDirectory, GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE)
																				.addGap(18)
																				.addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE))
																				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 744, Short.MAX_VALUE)
																				.addComponent(lblResult, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 744, Short.MAX_VALUE)
																				.addComponent(btnTranslator, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 744, Short.MAX_VALUE)))
																				.addGroup(gl_contentPane.createSequentialGroup()
																						.addComponent(chckbxUserAdmin, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
																						.addGap(18)
																						.addComponent(txtUser, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
																						.addGap(21)
																						.addComponent(txtpnChangeThisParameter, GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)))))
																						.addGap(24))
				);
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtFldDirectory, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnSearch))
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_contentPane.createSequentialGroup()
												.addGap(26)
												.addComponent(txtpnChangeThisParameter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addGroup(gl_contentPane.createSequentialGroup()
														.addPreferredGap(ComponentPlacement.RELATED)
														.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
																.addComponent(txtUser, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
																.addComponent(chckbxUserAdmin, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
																.addGap(35)
																.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
																		.addGroup(gl_contentPane.createSequentialGroup()
																				.addPreferredGap(ComponentPlacement.RELATED, 375, Short.MAX_VALUE)
																				.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																				.addGap(88))
																				.addGroup(gl_contentPane.createSequentialGroup()
																						.addGap(40)
																						.addComponent(btnTranslator)
																						.addGap(18)
																						.addComponent(lblResult, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
																						.addGap(18)
																						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 364, GroupLayout.PREFERRED_SIZE)))
																						.addContainerGap())
																						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
																								.addGap(104)
																								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
																										.addComponent(txtpnModifyThisParameter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																										.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
																												.addComponent(txtSpace, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
																												.addComponent(chckbxSpaceName)))
																												.addContainerGap(497, Short.MAX_VALUE))
				);

		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		//Redirect the default system outputs to the JTextArea in order to show the progresses
		TextAreaOutputStream taos = new TextAreaOutputStream( textArea, 160 );
		PrintStream ps = new PrintStream( taos );
		System.setOut( ps );
		//System.setErr( ps );
		contentPane.setLayout(gl_contentPane);
	}
}
