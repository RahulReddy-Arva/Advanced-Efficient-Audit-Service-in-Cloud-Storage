import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JProgressBar;
import javax.swing.JMenu;
import java.awt.ScrollPane;
import java.awt.Label;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;

import java.awt.Button;
import java.awt.Panel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;


public class ClientPage extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private Button button;
	private String FileLink;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientPage frame = new ClientPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ClientPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 153, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Select File");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel.setForeground(new Color(51, 51, 51));
		lblNewLabel.setBounds(50, 61, 135, 27);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("Times New Roman", Font.ITALIC, 22));
		textField.setBounds(171, 61, 299, 27);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Browse");
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 22));
		btnNewButton.setBounds(480, 61, 110, 27);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
		        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		        fileChooser.setAcceptAllFileFilterUsed(true);
		        int rVal = fileChooser.showOpenDialog(null);
		        if (rVal == JFileChooser.APPROVE_OPTION) {
		        	FileLink=fileChooser.getSelectedFile().toString();
		          textField.setText(FileLink);
		        }
				
			}
		});
		
		JButton btnNewButton_1 = new JButton("Upload");
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 21));
		btnNewButton_1.setBounds(396, 117, 123, 41);
		contentPane.add(btnNewButton_1);
		
		JLabel lblMetaDataLength = new JLabel("Meta Data Length");
		lblMetaDataLength.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblMetaDataLength.setBounds(50, 124, 178, 34);
		contentPane.add(lblMetaDataLength);
		
		textField_1 = new JTextField();
		textField_1.setBounds(238, 128, 64, 30);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblMaxOnly = new JLabel("max  4 only");
		lblMaxOnly.setForeground(new Color(204, 0, 0));
		lblMaxOnly.setFont(new Font("Times New Roman", Font.ITALIC, 14));
		lblMaxOnly.setBounds(224, 169, 70, 14);
		contentPane.add(lblMaxOnly);
		
		JLabel lblListOfDocuments = new JLabel("List of documents in your cloud");
		lblListOfDocuments.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblListOfDocuments.setBounds(61, 183, 277, 27);
		contentPane.add(lblListOfDocuments);
		
		button = new Button("Refresh");
		button.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.setBounds(254, 212, 70, 27);
		contentPane.add(button);
		
		Label label = new Label("Details about  file");
		label.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
		label.setBounds(403, 212, 218, 27);
		contentPane.add(label);
		
		JEditorPane editorPane_1 = new JEditorPane();
		editorPane_1.setBounds(406, 246, 299, 212);
		contentPane.add(editorPane_1);
		
		JButton btnPreview = new JButton("Preview");
		btnPreview.setBounds(404, 483, 89, 23);
		contentPane.add(btnPreview);
		
		JButton btnDownlaod = new JButton("Download");
		btnDownlaod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDownlaod.setBounds(512, 483, 90, 23);
		contentPane.add(btnDownlaod);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(611, 483, 89, 23);
		contentPane.add(btnDelete);
		
		JList list = new JList();
		list.setBounds(241, 282, -167, 176);
		contentPane.add(list);
		
				
	}
}
