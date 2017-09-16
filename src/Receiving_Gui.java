import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.apache.commons.net.ftp.FTPClient;


public class Receiving_Gui implements Runnable,ActionListener {

	JFrame frame;
	public ReceivingEnd r;
	String rname,rfamname,rage,rgender,rclinicname;
	FTPClient client;
	BufferedImage b;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Receiving_Gui window = new Receiving_Gui();
					//window.frame.setVisible(true);
					window.initialize(null,"");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 
	public Receiving_Gui() {
		initialize(client);
	}
*/
	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize(FTPClient ftpclient,String sss) {
		r=new ReceivingEnd();
		r.retrievedata(ftpclient,sss);
		b=r.image;
		if(b==null)
		{
			System.out.println("b is null");
		}
		int n=check_tampers();
		if(n==1)
		{
			System.out.println("Image is tampered");
			rname="detailstampered";
			rfamname="detailstampered";
			rage="detailstampered";
			rgender="detailstampered";
			rclinicname="detailstampered";
		}else
		{
			rname=r.name;
			rfamname=r.famname;
			rage=r.age;
			rgender=r.gender;
			rclinicname=r.clinicname;
			System.out.println("Image is not tampered");
		}
		
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Times New Roman", Font.BOLD, 18));
		frame.getContentPane().setBackground(new Color(100, 149, 237));
		frame.getContentPane().setLayout(null);
		frame.setBounds(100,80, 800, 600);
		frame.setVisible(true);
		
		JLabel patientDetails=new JLabel("Patient Details");
		patientDetails.setFont(new Font("Times New Roman", Font.BOLD, 40));
		patientDetails.setBounds(50, 50, 250, 30);
		frame.getContentPane().add(patientDetails);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblName.setBounds(40, 130, 150, 20);
		frame.getContentPane().add(lblName);
		
		JLabel lblPatientname = new JLabel(rname);
		lblPatientname.setBounds(170, 130, 150, 20);
		lblPatientname.setFont(new Font("Times New Roman", Font.BOLD, 18));
		frame.getContentPane().add(lblPatientname);
		
		JLabel lblFamilyName = new JLabel("Family Name:");
		lblFamilyName.setBounds(40, 170, 150, 20);
		lblFamilyName.setFont(new Font("Times New Roman", Font.BOLD, 18));
		frame.getContentPane().add(lblFamilyName);
		
		JLabel lblFamname = new JLabel(rfamname);
		lblFamname.setBounds(170, 170, 150, 20);
		lblFamname.setFont(new Font("Times New Roman", Font.BOLD, 18));
		frame.getContentPane().add(lblFamname);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setBounds(40, 210, 150, 20);
		lblAge.setFont(new Font("Times New Roman", Font.BOLD, 18));
		frame.getContentPane().add(lblAge);
		
		JLabel lblAge_1 = new JLabel(rage);
		lblAge_1.setBounds(170, 210, 150, 20);
		lblAge_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		frame.getContentPane().add(lblAge_1);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setBounds(40, 250, 150, 20);
		lblGender.setFont(new Font("Times New Roman", Font.BOLD, 18));
		frame.getContentPane().add(lblGender);
		
		JLabel lblGender_1 = new JLabel(rgender);
		lblGender_1.setBounds(170, 250, 150, 20);
		lblGender_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		frame.getContentPane().add(lblGender_1);
		
		JLabel lblClinicName = new JLabel("Clinic Name");
		lblClinicName.setBounds(40, 290, 150, 20);
		lblClinicName.setFont(new Font("Times New Roman", Font.BOLD, 18));
		frame.getContentPane().add(lblClinicName);
		
		JLabel lblCinicname = new JLabel(rclinicname);
		lblCinicname.setBounds(170, 290, 150, 20);
		lblCinicname.setFont(new Font("Times New Roman", Font.BOLD, 18));
		frame.getContentPane().add(lblCinicname);
		
		BufferedImage b=scale(r.image,r.image.getType(),250,250,0.25,0.25);
		ImagePanel i=new ImagePanel(b);
		i.setBounds(350, 150, 250, 250);
		frame.getContentPane().add(i);
		
		JButton viewimage=new JButton("View Image");
		viewimage.setBounds(620, 370, 150, 30);
		frame.getContentPane().add(viewimage);
		viewimage.addActionListener(this);
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
	public void run() {
		// TODO Auto-generated method stub
		
		JFrame f=new JFrame();
		if(b==null)
		{
			System.out.println("Mahendar reddy");
		}
		int height=r.image.getHeight();
		int width=r.image.getWidth();
		
		System.out.println(height+"   "+width);
		f.setBounds(500, 100, width, height);
		f.setVisible(true);
		ImagePanel i=new ImagePanel(r.image);
		i.setBounds(0, 0, width, height);
		f.getContentPane().add(i);
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		JFrame f=new JFrame();
		if(b==null)
		{
			System.out.println("Mahendar reddy");
		}
		int height=r.image.getHeight();
		int width=r.image.getWidth();
		
		System.out.println(height+"   "+width);
		f.setBounds(500, 100, width, height);
		f.setVisible(true);
		ImagePanel i=new ImagePanel(r.image);
		i.setBounds(0, 0, width, height);
		f.getContentPane().add(i);
	}
	
	
	public int check_tampers()
	{
		BufferedImage in=r.image;
		int width,height,w,h;
		width=in.getWidth();
		height=in.getHeight();
		w=width/20;
		h=height/20;
		int i,j,x=0,y=0;
		int check;
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
							   Color c=new Color(in.getRGB(m, n));
							   sumred=sumred+c.getRed();
							   sumgreen=sumgreen+c.getGreen();
							   sumblue=sumblue+c.getBlue();
							   count++;
						   }
					   }
				   }
				   //Color d=new Color(sumred/count,sumgreen/count,sumblue/count);
				   Color e=new Color(in.getRGB(x, y));
				  // System.out.println(" "+sumred/count+" "+e.getRed()+" "+sumgreen/count+" "+e.getGreen()+" "+sumblue/count+" "+e.getBlue());
				   if(sumred/count!=e.getRed())
				   {
					   return 1;
				   }
				   if(sumgreen/count!=e.getGreen())
				   {
					   return 1;
				   }
				   if(sumblue/count!=e.getBlue())
				   {
					   return 1;
				   }
				   }
				   j=j+h;
		       }
			   i=i+w;
	        }
		   return 0;
	}
}
