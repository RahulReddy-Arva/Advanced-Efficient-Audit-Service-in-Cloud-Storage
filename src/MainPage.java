import java.awt.AlphaComposite;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JTextArea;
import javax.swing.JEditorPane;
import javax.swing.JScrollBar;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;


public class MainPage implements ActionListener{

	public JFrame frame;
	public String fname,famname,age,gender,clinic,ImageFileLink;
	public JTextField tfFirstname,tfFamilyname,tfAge,tfclinicname,Imagelink,recipient;
	public JRadioButton rdbtnMale,rdbtnFemale;
	public JButton subbtn,browsebtn,sendbtn,ok;
	public ImagePanel imgpanel;
	public ButtonGroup gendergrp;
	public BufferedImage returnedImage;
	public Images img;
	public static File out;
	public FTPClient ftpclient;
	public JLabel btmtext,recev,shwtxt;
	public int sucsubmit;
	public String USERNAME;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage window = new MainPage();
					window.initialize(null,"");
					//window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 
	public MainPage() {
		initialize();
	}*/

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize(FTPClient client,String a) {
		
		ftpclient=client;
		USERNAME=a;
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Times New Roman", Font.BOLD, 18));
		frame.getContentPane().setBackground(new Color(100, 149, 237));
		frame.setBounds(100, 100, 850,550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JLabel lblNewLabel = new JLabel("Patient Details");
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.setFont(new Font("serif",Font.BOLD,40));
		lblNewLabel.setBounds(24, 35, 312, 36);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblPatientName = new JLabel("First Name");
		lblPatientName.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblPatientName.setBounds(35, 134, 115, 24);
		frame.getContentPane().add(lblPatientName);
		
		JLabel lblFamilyName = new JLabel("Family Name");
		lblFamilyName.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblFamilyName.setBounds(35, 172, 103, 24);
		frame.getContentPane().add(lblFamilyName);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblAge.setBounds(35, 210, 46, 24);
		frame.getContentPane().add(lblAge);
		
		JLabel lblSex = new JLabel("Sex");
		lblSex.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblSex.setBounds(35, 248, 46, 24);
		frame.getContentPane().add(lblSex);
		
		JLabel lblClinicName = new JLabel("Clinic Name");
		lblClinicName.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblClinicName.setBounds(35, 286, 109, 24);
		frame.getContentPane().add(lblClinicName);
		
		 tfFirstname=new JTextField();
		tfFirstname.setBounds(155, 135, 130, 24);
		tfFirstname.setText("");
		frame.getContentPane().add(tfFirstname);
		
		tfFamilyname=new JTextField();
		tfFamilyname.setBounds(155, 173, 130, 24);
		tfFamilyname.setText("");
		frame.getContentPane().add(tfFamilyname);
		
		tfAge=new JTextField();
		tfAge.setBounds(155, 210, 50, 24);
		tfAge.setText("");
		frame.getContentPane().add(tfAge);
		
		tfclinicname=new JTextField();
		tfclinicname.setBounds(155, 286, 130, 24);
		tfclinicname.setText("");
		frame.getContentPane().add(tfclinicname);
		
		rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setBounds(155, 248, 57, 23);
		frame.getContentPane().add(rdbtnMale);
		
		rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setBounds(217, 248, 66, 23);
		frame.getContentPane().add(rdbtnFemale);
		
		gendergrp=new ButtonGroup();
		gendergrp.add(rdbtnFemale);
		gendergrp.add(rdbtnMale);
		
		Imagelink=new JTextField();
		Imagelink.setBounds(350, 130, 200, 30);
		frame.getContentPane().add(Imagelink);
		
		browsebtn=new JButton("Browse");
		browsebtn.setBounds(550, 130, 100, 30);
		frame.getContentPane().add(browsebtn);
		browsebtn.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		        JFileChooser fileChooser = new JFileChooser();
		        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		        fileChooser.setAcceptAllFileFilterUsed(true);
		        int rVal = fileChooser.showOpenDialog(null);
		        if (rVal == JFileChooser.APPROVE_OPTION) {
		        	ImageFileLink=fileChooser.getSelectedFile().toString();
		          Imagelink.setText(ImageFileLink);
		        }
		        File img=new File(ImageFileLink);
				BufferedImage image = null;
				if(img!=null)
				{
				try {
					image=ImageIO.read(img);
				} catch (IOException d) {
					// TODO Auto-generated catch block
					d.printStackTrace();
				}
				BufferedImage image1=scale(image,image.getType(),125,125,0.25,0.25);
				/*File ouptut = new File("grey.jpg");
		        try {
					ImageIO.write(image1, "jpg", ouptut);
				} catch (IOException d) {
					// TODO Auto-generated catch block
					d.printStackTrace();
				}*/
				imgpanel = new ImagePanel(image1);
				imgpanel.setBounds(350, 220, 125, 125);
				frame.getContentPane().add(imgpanel);
				
				JLabel normalimage=new JLabel("Normal Image");
				normalimage.setBounds(350,180,150,30);
				normalimage.setFont(new Font("Times New Roman", Font.BOLD, 17));
				frame.getContentPane().add(normalimage);
				frame.repaint();
		      }
		      }
		    });
		 
		JLabel imageselection=new JLabel("Select Image");
		imageselection.setBounds(350, 90, 200, 30);
		frame.getContentPane().add(imageselection);
		imageselection.setFont(new Font("Times New Roman", Font.BOLD, 25));
		
		btmtext=new JLabel("");
		btmtext.setBounds(100, 312, 200, 30);
		btmtext.setForeground(new Color(255,0,0));
		frame.getContentPane().add(btmtext);
		btmtext.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		
		
		subbtn=new JButton("SUBMIT"); 
		subbtn.setBounds(180, 350,100,30);
		frame.getContentPane().add(subbtn);
		subbtn.addActionListener(this);
		
		sendbtn=new JButton("SEND");
		sendbtn.setBounds(300, 350, 100, 30);
		frame.getContentPane().add(sendbtn);
		sendbtn.setEnabled(false);
		sendbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				sendbtn.setEnabled(false);
				
				shwtxt=new JLabel("");
				shwtxt.setFont(new Font("Times New Roman", Font.BOLD, 12));
				shwtxt.setForeground(new Color(255,0,0));
				shwtxt.setBounds(300, 470, 300,30);
				frame.getContentPane().add(shwtxt);
				
				
				recev=new JLabel("Recipient Username");
				recev.setFont(new Font("Times New Roman", Font.BOLD, 18));
				recev.setBounds(300, 400, 200, 30);
				frame.getContentPane().add(recev);
				
				recipient=new JTextField("");
				recipient.setFont(new Font("Times New Roman", Font.BOLD, 18));
				recipient.setBounds(300, 440,200,30);
				frame.getContentPane().add(recipient);
				
				
				
				
				ok=new JButton("Ok");
				ok.setFont(new Font("Times New Roman", Font.BOLD, 25));
				ok.setBounds(520, 440, 100, 30);
				frame.getContentPane().add(ok);
				frame.repaint();
				ok.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						String recip=recipient.getText();
						recipient.setText("");
						Boolean res=false;
						if(recip.equals("")||(USERNAME.equals(recip)))
						{
							shwtxt.setText("Please Enter Recipient Username");
							frame.repaint();
						}else{
							try {
							res=checkDirectoryExists(recip);
							} catch (Exception e2) {
								// TODO Auto-generated catch block
								
							}
							if(res==true){
								try{ 
									
										ftpclient.setFileType(FTP.BINARY_FILE_TYPE);
				                        File in=new File("output.png");
				                        String remotefile="recieve.png";
				                        Boolean done=null;
				                        try{
				                        InputStream input=new FileInputStream(in);
				                        System.out.println("Started uploading");
				                        done=ftpclient.storeFile(remotefile, input);
				                        input.close();
				                        }catch(Exception e)
				                        {
				                        	System.out.println("file not loaded");
				                        }
				                       
				                        if(done)
				                        {
				                        	System.out.println("file uploaded successfully");
				                        }else
				                        {
				                        	System.out.println("File not uploaded");
				                        }
					                   try {
					                	   ftpclient.logout();
										ftpclient.disconnect();
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								ok.setEnabled(false);
								
								JLabel successtxt=new JLabel("File sent successfully");
								successtxt.setFont(new Font("Times New Roman", Font.BOLD, 18));
								successtxt.setForeground(new Color(0,255,0));
								successtxt.setBounds(550, 400, 200,30);
								frame.getContentPane().add(successtxt);
								
								
								JButton close=new JButton("Close");
								close.setFont(new Font("Times New Roman", Font.BOLD, 25));
								close.setBounds(630, 440, 100, 30);
								frame.getContentPane().add(close);
								frame.repaint();
								close.addActionListener(new ActionListener() {
									
									@Override
									public void actionPerformed(ActionEvent arg0) {
										// TODO Auto-generated method stub
										frame.dispose();
									}
								});
								
								
							}else{
								shwtxt.setText("Enter valid Username");
							}
						}
					}
				});
				
				
			}
		});
		
	}
	
	
	boolean checkDirectoryExists(String dirPath) throws IOException {
		ftpclient.changeWorkingDirectory(dirPath);
		int replycode=ftpclient.getReplyCode();
		if(replycode==550)
		{
			return false;
		}
		return true;
		}
	
	
	
	public static BufferedImage scale(BufferedImage sbi, int imageType, int dWidth, int dHeight, double fWidth, double fHeight) {
	    BufferedImage dbi = null;
	    if(sbi != null) {
	        dbi = new BufferedImage(dWidth, dHeight, imageType);
	        Graphics2D g = dbi.createGraphics();
	        //AffineTransform at = AffineTransform.getScaleInstance(fWidth, fHeight);
	        g.drawImage(sbi, 0,0,dWidth,dHeight,null);
	        g.dispose();
	        g.setComposite(AlphaComposite.Src);
	        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	        g.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
	        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
	    }
	    return dbi;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		fname="";famname="";age="";gender="";clinic="";
		fname=tfFirstname.getText();
		famname=tfFamilyname.getText();
		age=tfAge.getText();
		clinic=tfclinicname.getText();
		if(rdbtnMale.isSelected())
		{
			gender="Male";
		}else{
			gender="Female";
		}
		System.out.println(fname+"\t"+famname+"\t"+age+"\t"+clinic+"\t"+gender);
		if(fname.equals("")||famname.equals("")||age.equals("")||clinic.equals("")||gender.equals("")||ImageFileLink.equals(""))
		{
			btmtext.setText("All the fields are mandatory");
		}else{
			
			try{
				int a=Integer.parseInt(age);
				if(a<0)
				{
					btmtext.setText("Please enter Valid age");
					return;
				}else{
					btmtext.setText("");
				}
				
			}catch(Exception e)
			{
				btmtext.setText("Please enter Valid age");
				return;
			}
			
		sendbtn.setEnabled(true);
		subbtn.setEnabled(false);
		browsebtn.setEnabled(false);
		img=new Images();
		returnedImage=img.Images_process(ImageFileLink);
		img.insert_data(fname, famname, age, clinic,gender);
		BufferedImage b=scale(returnedImage,returnedImage.getType(),125,125,0.25,0.25);
		ImagePanel shortimage=new ImagePanel(b);
		shortimage.setBounds(520, 220, 125, 125);
		frame.getContentPane().add(shortimage);
		
		JLabel watermarkedimage=new JLabel("WaterMarked Image");
		watermarkedimage.setBounds(520,180,180,30);
		watermarkedimage.setFont(new Font("Times New Roman", Font.BOLD, 17));
		frame.getContentPane().add(watermarkedimage);
		frame.repaint();
		
		out=new File("output.png");
		try {
			Image i=(Image)(returnedImage);
			ImageIO.write(returnedImage, "png",out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		
		/*BufferedImage k = null;
		try {
			File o=new File("output.png");
			k=ImageIO.read(o);
			System.out.println(k.getHeight()+"   "+k.getWidth());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int a[]=new int[5];
		for(int i=1;i<=5;i++)
		{
			if(k!=null)
			{
			Color c=new Color(k.getRGB(0,i));
			Color d=new Color(returnedImage.getRGB(0,i));
			System.out.println(c.getRed()+"   "+d.getRed());
			a[i-1]=c.getRed();
			}
		}
		img.retrieve_data(a);
		check_tampers();*/
	}
	
	
	public void check_tampers()
	{
		BufferedImage in,out=null;
		in=img.image;
		File outn=new File("output.png");
		try {
			out=ImageIO.read(outn);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int width,height,w,h;
		width=in.getWidth();
		height=in.getHeight();
		w=width/20;
		h=height/20;
		int i,j,x=0,y=0;
		i=w;
		int sumred=0,sumgreen=0,sumblue=0,count=0;
		   while(i<width)
		   {
			   j=h;
			   while(j<height)
			   {
				   if(i!=w&&j!=h)
				   {
				   x=i-w;
				   y=j-h;
				   sumred=0;sumgreen=0;sumblue=0;count=0;
				   for(int m=x;m<i;m++)
				   {
					   for(int n=y;n<j;n++)
					   {
						   if(m!=x&&n!=y)
						   {
							   Color c=new Color(out.getRGB(m, n));
							   sumred=sumred+c.getRed();
							   sumgreen=sumgreen+c.getGreen();
							   sumblue=sumblue+c.getBlue();
							   count++;
						   }
					   }
				   }
				   Color d=new Color(sumred/count,sumgreen/count,sumblue/count);
				   Color e=new Color(out.getRGB(x, y));
				   //System.out.println(" "+sumred/count+" "+e.getRed()+" "+sumgreen/count+" "+e.getGreen()+" "+sumblue/count+" "+e.getBlue());
				   }
				   j=j+h;
		       }
			   i=i+w;
	        }
	}
}
