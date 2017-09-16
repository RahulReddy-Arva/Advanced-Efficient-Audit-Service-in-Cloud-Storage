import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import org.apache.commons.net.ftp.FTPClient;


public class Login implements Runnable{

	JFrame frame;
	JLabel lblName,UserName,PassWord,btmtext;
	JTextField Username;
	JPasswordField Password;
	String username,password;
	public FTPClient ftpclient;
	MainPage mainpage;
	
	//JButton login;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.initialize();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 
	public Login() {
		//initialize();
	}*/

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Times New Roman", Font.BOLD, 18));
		frame.getContentPane().setBackground(new Color(100, 149, 237));
		frame.setBounds(300, 300, 500, 400);
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		lblName = new JLabel("Login Page");
		lblName.setBounds(50, 20, 200, 50);
		lblName.setFont(new Font("Times New Roman", Font.BOLD, 40));
		frame.getContentPane().add(lblName);
		
		UserName = new JLabel("Username :");
		UserName.setBounds(50, 100, 200, 50);
		UserName.setFont(new Font("Times New Roman", Font.BOLD, 25));
		frame.getContentPane().add(UserName);
		
		PassWord = new JLabel("Password  :");
		PassWord.setBounds(50, 150, 200, 50);
		PassWord.setFont(new Font("Times New Roman", Font.BOLD, 25));
		frame.getContentPane().add(PassWord);
		
		Username=new JTextField();
		Username.setFont(new Font("Times New Roman", Font.BOLD, 25));
		Username.setBounds(190, 110, 200, 30);
		frame.getContentPane().add(Username);
		
		Password=new JPasswordField();
		Password.setFont(new Font("Times New Roman", Font.BOLD, 25));
		Password.setBounds(190, 160, 200, 30);
		frame.getContentPane().add(Password);
		
		btmtext=new JLabel("");
		btmtext.setBounds(100, 190, 400, 50);
		btmtext.setFont(new Font("Times New Roman", Font.BOLD, 25));
		frame.getContentPane().add(btmtext);
		btmtext.setForeground(new Color(255,0,0));
		
		JButton send=new JButton("Send");
		send.setBounds(70,250, 150, 50);
		send.setFont(new Font("Times New Roman", Font.BOLD, 25));
		frame.getContentPane().add(send);
		send.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				username=Username.getText();
				password=Password.getText();
				Username.setText("");
				Password.setText("");
				System.out.println(username+password);
				Boolean result=true;
				try {
					ftpclient=new FTPClient();
					ftpclient.connect("localhost", 21);
					result=ftpclient.login(username, password);
					if(result==true)
					{
						frame.dispose();
						mainpage=new MainPage();
						mainpage.initialize(ftpclient,username);
					}else{
						btmtext.setText("Invalid Username or Password");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("errors");
				}
			}
			
		});
		
		JButton receive=new JButton("Receive");
		receive.setBounds(250, 250,150, 50);
		receive.setFont(new Font("Times New Roman", Font.BOLD, 25));
		frame.getContentPane().add(receive);
		receive.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				username=Username.getText();
				password=Password.getText();
				Username.setText("");
				Password.setText("");
				System.out.println(username+password);
				Boolean result=true;
				try {
					frame.dispose();
					ftpclient=new FTPClient();
					ftpclient.connect("localhost", 21);
					result=ftpclient.login(username, password);
					if(result==true)
					{
						Receiving_Gui r=new Receiving_Gui();
						r.initialize(ftpclient,username);
						
					}else{
						btmtext.setText("Invalid Username or Password");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("errors");
				}
			}
		});
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("mahendar reddy");
	}
}
