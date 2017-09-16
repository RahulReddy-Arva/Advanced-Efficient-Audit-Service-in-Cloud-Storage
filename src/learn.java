import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class learn
{
	private static Scanner in;

	public static void main(String args[])
	{
		in = new Scanner(System.in);
		int n=in.nextInt();
		int h=5,w=5,x,y;
		int d=n/20;
		int r=n%20;
		x=(d+1)*w;
		y=(r+1)*h;
		System.out.println("x="+x+"y="+y);
	}
}