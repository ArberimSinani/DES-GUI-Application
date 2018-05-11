package desAlgorithm;


import java.awt.EventQueue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JProgressBar;
import java.awt.Toolkit;

public class DES_ApplicationFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	DES objDes = new DES();
	
	
	int returnVal;
	String currentLine;
	private JTextField txtSelectedFilePath;
	private JTextField txtSaveFilePath;
	private JTextField txtKey;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					DES_ApplicationFrame frame = new DES_ApplicationFrame();
					frame.setVisible(true);
					frame.setResizable(false);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DES_ApplicationFrame() {
		setTitle("DES-Algorithm");
		setIconImage(Toolkit.getDefaultToolkit().getImage(DES_ApplicationFrame.class.getResource("/desAlgorithm/images/PlatformEncryptionlogo.png")));
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File  
				(System.getProperty("user.home") + System.getProperty("file.separator")+ "Desktop"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 388, 491);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		//Select the file you want to encrypt/decrypt:
		JButton btnSelect = new JButton("Select File");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnSelect) {
					returnVal = fileChooser.showOpenDialog(null);
					if(returnVal == JFileChooser.APPROVE_OPTION) {
						txtSelectedFilePath.setText(fileChooser.getSelectedFile().getAbsolutePath());
						//save the choosen file to the file variable;	
					}
				}
			}
		});
		
		btnSelect.setBounds(278, 231, 89, 23);
		contentPane.add(btnSelect);
		
		//Set the encrypted/ decrypted file save path:
				JButton btnSaveFile = new JButton("Save File");
				btnSaveFile.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						returnVal = fileChooser.showSaveDialog(null);
						if(returnVal == JFileChooser.APPROVE_OPTION) {
							txtSaveFilePath.setText(fileChooser.getSelectedFile().getAbsolutePath());	
						}
					}
				});
		
		btnSaveFile.setBounds(278, 301, 89, 23);
		contentPane.add(btnSaveFile);
		
		JProgressBar progBar = new JProgressBar();
		progBar.setStringPainted(true);
		progBar.setBounds(0, 438, 372, 14);
		contentPane.add(progBar);
		
		//Encrypt the given file
		JButton btnEncrypt = new JButton("Encrypt");
		btnEncrypt.setForeground(new Color(0, 51, 255));
		btnEncrypt.setBackground(new Color(0, 51, 255));
		btnEncrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				File plainTextFile = new File(txtSelectedFilePath.getText());
				File encrypted = new File(txtSaveFilePath.getText());
				try {
					DES.encryptDecrypt(txtKey.getText(), Cipher.ENCRYPT_MODE, plainTextFile, encrypted);
					for(int i=0; i<=100; i++) {
						progBar.setValue(i);		
					}
					JOptionPane.showMessageDialog(null, "File encrypted succesfuly!");
				} catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException
						| NoSuchPaddingException | IOException e) {
					
					e.printStackTrace();
				}
				
			}
		});
		btnEncrypt.setBounds(80, 360, 89, 23);
		contentPane.add(btnEncrypt);
		//Decryot the given file
		JButton btnDecrypt = new JButton("Decrypt");
		btnDecrypt.setForeground(new Color(0, 51, 255));
		btnDecrypt.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnDecrypt.setBackground(new Color(0, 51, 255));
		btnDecrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				File cipherTextFile = new File(txtSelectedFilePath.getText());
				File decryptedFile = new File(txtSaveFilePath.getText());
				try {
					DES.encryptDecrypt(txtKey.getText(), Cipher.DECRYPT_MODE, cipherTextFile, decryptedFile);
					for(int i=0; i<=100; i++) {
						progBar.setValue(i);		
					}
					JOptionPane.showMessageDialog(null, "File decrypted succesfuly!");
					
				} catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException
						| NoSuchPaddingException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnDecrypt.setBounds(179, 360, 89, 23);
		contentPane.add(btnDecrypt);
		
		txtSelectedFilePath = new JTextField();
		txtSelectedFilePath.setBackground(new Color(255, 255, 255));
		txtSelectedFilePath.setBounds(10, 231, 258, 23);
		contentPane.add(txtSelectedFilePath);
		txtSelectedFilePath.setColumns(10);
		
		txtSaveFilePath = new JTextField();
		txtSaveFilePath.setBounds(10, 301, 258, 23);
		contentPane.add(txtSaveFilePath);
		txtSaveFilePath.setColumns(10);
		
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(DES_ApplicationFrame.class.getResource("/desAlgorithm/images/PlatformEncryptionlogo.png")));
		lblNewLabel.setBounds(79, 11, 201, 209);
		contentPane.add(lblNewLabel);
		
		txtKey = new JTextField();
		txtKey.setBounds(41, 265, 227, 23);
		contentPane.add(txtKey);
		txtKey.setColumns(10);
		
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				txtKey.setText(String.valueOf(objDes.randomKeyGen()));
			}
		});
		btnGenerate.setBounds(278, 265, 89, 23);
		contentPane.add(btnGenerate);
		
		JLabel lblKey = new JLabel("Key:");
		lblKey.setBounds(10, 265, 22, 23);
		contentPane.add(lblKey);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(179, 438, 193, 14);
		contentPane.add(progressBar);
		
		
		
		
	}
}
