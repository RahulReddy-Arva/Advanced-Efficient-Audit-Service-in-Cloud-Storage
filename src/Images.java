import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Images {

   BufferedImage  image;
   int width,w;
   int height,h;
   int store_nonregion[]=new int[400];
   
   
   
   public  BufferedImage Images_process(String ImageLink) 
   		{   
      try {
         File input = new File(ImageLink);
         image = ImageIO.read(input);
         width = image.getWidth();
         height = image.getHeight();
         h=height/20;
         w=width/20;
         int i,j,n;
         System.out.println("height="+height+"width="+width+"h="+h+"w="+w);
         i=w;
         int a=0;
         int sumred=0,sumgreen=0,sumblue=0,count=0;
         int array[]=new int[3];
         while(i<width)
         {
        	 j=h;
        	 
        	 while(j<height)
        	 {
        		 //System.out.println("Mahendar Reddy");
        		 n=check(i,j,w,h);
        		 store_nonregion[a]=n;
        		 a++;
           		 j=j+h;
        	 }
        	 i=i+w;
         }
         Color c=new Color((sumred/count),(sumgreen/count),(sumblue/count));
         image.setRGB(0, 0, c.getRGB());
        File ouptut = new File("mahi3.jpg");
         ImageIO.write(image, "jpg", ouptut);
      } catch (Exception e) {
    	  System.out.println(e);
      }
      calculate_averages();
      return image;
   }
   
   
   
   
   private int check(int x,int y,int w,int h)
   {
	   int m=x-w;
	   int n=y-h;
	   int i,j,c1 = 0,c2 = 0;
	   for(i=n;i<y;i++)
	   {
		for(j=m;j<x;j++)
		{
			//
			if(i<width-1&&j<height-1)
			{
			c1=image.getRGB(j,i);
			c2=image.getRGB(j+1,i);
			}
			if(c1!=c2)
			{
				return 2;
			}
		}
	   }
	   for(i=n;i<y;i++)
	   {
		  // System.out.println("i="+i);
		   if(i<height-1)
		   {
		   c1=image.getRGB(m,i);
		   c2=image.getRGB(m,i+1);
		   }
		   if(c1!=c2)
			{
				return 2;
			}
	   }
	   if(c1!=-16777216)
	   {
		   return 2;
	   }
		
	   return 0;
   }
   
   
   public void calculate_averages()
   {
	   int i,j,x,y;
	   i=w;j=h;x=0;y=0;
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
						   Color c=new Color(image.getRGB(m, n));
						   sumred=sumred+c.getRed();
						   sumgreen=sumgreen+c.getGreen();
						   sumblue=sumblue+c.getBlue();
						   count++;
					   }
				   }
			   }
			   Color d=new Color(sumred/count,sumgreen/count,sumblue/count);
			   image.setRGB(x, y,d.getRGB());
			   }
			   j=j+h;
	       }
		   i=i+w;
        }
   }
   
   
   
   public void insert_data(String name,String famname,String Clinic,String age,String gender)
   {
	   String concat_string="@"+name+"$"+famname+"$"+Clinic+"$"+age+"$"+gender+"@";
	   int c[]=new int[200];
	   int j=0;
	   for(char i:concat_string.toCharArray())
	   {
		   c[j]=(int)(i);
		   j++;
	   }
	   int a[]=embed_data(c, j);
	   //retrieve_data(a);
	   for(int i=0;i<5;i++)
	   {
		   System.out.println(a[i]);
		   Color col=new Color(a[i],0,0);
		   image.setRGB(0, i+1, col.getRGB());
	   }
	   retrieve_data(a);
   }
   
   
   
   public BufferedImage print_divide_blocks(BufferedImage b )
   {
	   int height,width,block_width,block_height,i,j;
	   height=b.getHeight();
	   width=b.getWidth();
	   block_width=width/20;
	   block_height=height/20;
	   i=w;
       while(i<width)
       {
      	 for(j=0;j<height;j++)
      	 {
      		 Color c=new Color(255,255,255);
      		 b.setRGB(i, j, c.getRGB());
      	 }
      	 i=i+w;
       }
       i=h;
       while(i<height)
       {
      	 for(j=0;j<width;j++)
      	 {
      		 Color c=new Color(255,255,255);
      		 b.setRGB(j, i, c.getRGB());
      	 }
      	 i=i+h;
       }
	   
	   return b;
   }
   
   
   public BufferedImage paint_nonregion()
   {
	   int d,r,x,y,m,n;
	   Color c=new Color(150,150,150);
	   for(int i=0;i<400;i++)
	   {
		   if(store_nonregion[i]==0)
		   {
			   d=i/20;
			   r=i%20;
			   x=(d+1)*w;
			   y=(r+1)*h;
			   m=x-w;
			   n=y-h;
			   for(int a=m;a<x;a++)
			   {
				   for(int b=n;b<y;b++)
				   {
					   image.setRGB(a, b, c.getRGB());
					   System.out.print(a+"-"+b+"\t");
				   }
				   System.out.println();
			   }
		   }
		   System.out.println("\n\n");
	   }
	   return image;
   }
   
   
   public int [] embed_data(int ascii_values[],int length)
   {
	   int d,r,x,y,m,n,ascii_i=0,embedregion_i=0;
	   int embedregion[]=new int[10];
	   for(int i=0;i<400;i++)
	   {
		   if(store_nonregion[i]==0)
		   {
			   d=i/20;
			   r=i%20;
			   x=(d+1)*w;
			   y=(r+1)*h;
			   m=x-w;
			   n=y-h;
			   for(int a=m;a<x;a++)
			   {
				   for(int b=n;b<y;b++)
				   {
					   if(a!=m&&b!=n)
					   {
					   if(ascii_i<length)
					   {
						   Color c=new Color(ascii_values[ascii_i],0,0);
						   image.setRGB(a, b, c.getRGB());
						   ascii_i++;
					   }
					   }
				   }
			   }
			   embedregion[embedregion_i]=i;
			   embedregion_i++;
			   if(ascii_i==length)
			   {
				   return embedregion;
			   }
		   }
	   }
	   return embedregion;
   }
   
   
   
   public void retrieve_data(int embed_values[])
   {
	   int d,r,x,y,m,n,ascii_i=0,str_len=0;
	   int ascii[]=new int[w*h];
	   char str[]=new char[w*h];
	  
	   for(int i=0;i<5;i++)
	   {
		   
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
			   for(int a=m;a<x;a++)
			   {
				   for(int b=n;b<y;b++)
				   {
					   if(a!=m&&b!=n)
					   {
						   Color c=new Color(image.getRGB(a, b));
						 ascii[ascii_i]=c.getRed();
						 ascii_i++;
						// System.out.print(a+"_"+b+"\t");
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
	   System.out.println(s);
   }
   
   
   
   
   static public void main(String args[]) throws Exception 
   {
      Images obj = new Images();
     // obj.Images_process();
   }
}