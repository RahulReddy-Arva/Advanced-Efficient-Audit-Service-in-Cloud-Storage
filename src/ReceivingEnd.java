import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;


public class ReceivingEnd {
	BufferedImage image;
	int width,height,w,h;
	String name,famname,gender,age,clinicname;
	File down;
	String USERNAME;
	
	
	public void retrievedata(FTPClient ftpclient,String s)
	{
		int a[]=new int[5];
		down=null;
		USERNAME=s;
		try
		{
		ftpclient.setFileType(FTP.BINARY_FILE_TYPE);
        ftpclient.enterLocalPassiveMode();
        String remote=USERNAME+"/recieve.png";
        down=new File("output.png");
        OutputStream out=new BufferedOutputStream(new FileOutputStream(down));
        Boolean done=ftpclient.retrieveFile(remote, out);
        out.close();
        if(done)
        {
        	System.out.println("File downloaded successfully");
        }else
        {
        	System.out.println("File not downloaded successfully");
        }
		}catch(Exception e)
		{
			System.out.println("errors while downloading file");
		}
		try {
			image=ImageIO.read(new File("output.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		w=image.getWidth()/20;
		h=image.getHeight()/20;
		for(int i=1;i<=5;i++)
		{
			Color c=new Color(image.getRGB(0, i));
			a[i-1]=c.getRed();
			//System.out.println(c.getRed());
		}
		for(int i=0;i<5;i++)
		{
			System.out.println(a[i]);
		}
		retrieve_data(a);
	}
	
	
	
	
	public void retrieve_data(int embed_values[])
	   {
		   int d,r,x,y,m,n,ascii_i=0,str_len=0;
		   int ascii[]=new int[w*h];
		   char str[]=new char[w*h];
		   //System.out.println(w+"  "+h);
		   for(int i=0;i<5;i++)
		   {	
			   //System.out.println(embed_values[i]);
			   if(embed_values[i]==0)
			   {
				   break;
			   }else{
				   d=embed_values[i]/20;
				   r=embed_values[i]%20;
				   x=(d+1)*w;
				   y=(r+1)*h;
				   m=x-w;
				   n=y-h;
				 //  System.out.println(x+"  "+y+"  "+m+"  "+n+" "+d+" "+r);
				   for(int a=m;a<x;a++)
				   {
					   for(int b=n;b<y;b++)
					   {
						   if(a!=m&&b!=n)
						   {
							   Color c=new Color(image.getRGB(a, b));
							 ascii[ascii_i]=c.getRed();
							 ascii_i++;
							 //System.out.print(a+"_"+b+"\t");
						   }
					   }
				   }
			   }
		   }
		   for(int i=0;i<ascii_i;i++)
		   {
			   char c=(char)ascii[i];
			   if(c=='@'&&i!=0)
			   {
				   break;
			   }
			   else
			   {
				   str[i]=c;
				   str_len++;
				  //System.out.println(c);
			   }
		   }
		   String s="";
		   for(int i=0;i<str_len;i++)
		   {
			   s=s+str[i];
		   }
		   //System.out.println(s);
		   separate_list(s);
		}
	
	
	
	public void separate_list(String s)
	{
		String a[]=new String[5];
		int j=0;
		for(char i:s.toCharArray())
		{
			if(i=='$')
			{
				j=j+1;
				a[j]="";
				continue;
			}
			else if(i=='@')
			{
				a[j]="";
			}
			else
			{
				a[j]=a[j]+i;
			}
		}
		name=a[0];
		famname=a[1];
		age=a[2];
		gender=a[4];
		clinicname=a[3];
		System.out.println(name+famname+age+gender+clinicname);
	}
}
