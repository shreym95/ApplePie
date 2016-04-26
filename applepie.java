
import matlabcontrol.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.sql.*;

import com.mysql.*;
import com.mysql.jdbc.*;
import com.mysql.jdbc.configs.*;
import com.mysql.jdbc.jdbc2.optional.*;
import com.mysql.jdbc.log.*;
import com.mysql.jdbc.profiler.*;
import com.mysql.jdbc.profiler.*;
import com.mysql.jdbc.util.*;
import org.gjt.mm.mysql.*;


class applepie extends JFrame implements ActionListener
{	
	public static String filepath;
	public String filename;
	public static MatlabOperations proxy = null;
	public String result;
	public static Statement stm;
	public static Connection link;
	public ResultSet rs;
	public double dur;
	public long frames;
	public String hrt_path;
	public ImageIcon hearti;
	public int rows=0;
	
	JLabel background;
	JLabel name;
	JLabel art;
	JFileChooser fc;
	JButton closeButton;
	JButton openButton;
	JButton playButton;
	JButton pauseButton;
	JButton forwardButton;
	JButton backwardButton;
	JButton shuffleButton;
	JButton heartButton;
	JButton repeatButton;
	JButton heart_redButton;
	JButton libraryButton;
	
	
	public applepie()
    {
		
		
		setLayout(null);
		setUndecorated(true);
    	setBackground(new Color(0,0,0,0));
       	setTitle("Apple Pie!");
    	setLocation(500,250);
    	setSize(240,340);
    	setVisible(true);
    	setResizable(false);
    	
    	
    	background = new JLabel(new ImageIcon("C:/Users/SKILLET/Desktop/images/background.png"));
    	art = new JLabel(new ImageIcon("C:/Users/SKILLET/Desktop/images/02.jpg"));
    	ImageIcon closei = new ImageIcon("C:/Users/SKILLET/Desktop/images/close.png");
    	closeButton = new JButton("",closei);
    	closeButton.addActionListener(this);
    	closeButton.setBorderPainted(false);
    	
    	fc = new JFileChooser();
    	fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
    	
    	name = new JLabel("");
    	name.setText("Choose an audio file.");
 
    	
    	ImageIcon openi=new ImageIcon("C:/Users/SKILLET/Desktop/images/search.png");
    	openButton = new JButton("",openi);
    	openButton.addActionListener(this);
    	openButton.setBorderPainted(false);
    	
    	ImageIcon playi=new ImageIcon("C:/Users/SKILLET/Desktop/images/play.png");
    	playButton = new JButton("",playi);
    	playButton.addActionListener(this);
    	playButton.setBorderPainted(false);
    	
    	ImageIcon pausei=new ImageIcon("C:/Users/SKILLET/Desktop/images/pause.png");
    	pauseButton = new JButton("",pausei);
    	pauseButton.addActionListener(this);
    	pauseButton.setBorderPainted(false);
    	pauseButton.setVisible(false);
    	
    	ImageIcon forwardi=new ImageIcon("C:/Users/SKILLET/Desktop/images/forward.png");
    	forwardButton = new JButton("",forwardi);
    	forwardButton.addActionListener(this);
    	forwardButton.setBorderPainted(false);
    	
    	ImageIcon backwardi=new ImageIcon("C:/Users/SKILLET/Desktop/images/backward.png");
    	backwardButton = new JButton("",backwardi);
    	backwardButton.addActionListener(this);
    	backwardButton.setBorderPainted(false);
    	
    	ImageIcon shufflei=new ImageIcon("C:/Users/SKILLET/Desktop/images/shuffle.png");
    	shuffleButton = new JButton("",shufflei);
    	shuffleButton.addActionListener(this);
    	shuffleButton.setBorderPainted(false);
    	
    	hrt_path = "C:/Users/SKILLET/Desktop/images/heart.png";
    	hearti=new ImageIcon(hrt_path);
    	heartButton = new JButton("",hearti);
    	
    	heartButton.addActionListener(this);
    	heartButton.setBorderPainted(false);
    	
    	ImageIcon heart_redi = new ImageIcon("C:/Users/SKILLET/Desktop/images/heart_red.png");
    	heart_redButton = new JButton("",heart_redi);
    	heart_redButton.addActionListener(this);
    	heart_redButton.setBorderPainted(false);
    	heart_redButton.setVisible(false);
    	
    	     
    	ImageIcon repeati=new ImageIcon("C:/Users/SKILLET/Desktop/images/repeat.png");
    	repeatButton = new JButton("",repeati);
    	repeatButton.addActionListener(this);
    	repeatButton.setBorderPainted(false);
    	
    	ImageIcon libraryi=new ImageIcon("C:/Users/SKILLET/Desktop/images/library.png");
    	libraryButton = new JButton("",libraryi);
    	libraryButton.addActionListener(this);
    	libraryButton.setBorderPainted(false);
    	
    	add(background);
    	background.add(art);
    	background.add(closeButton);
    	background.add(name);
    	background.add(openButton);
    	background.add(playButton);background.add(pauseButton);background.add(forwardButton);background.add(backwardButton);
    	background.add(shuffleButton);background.add(heartButton);background.add(heart_redButton);background.add(repeatButton);
    	background.add(libraryButton); background.add(fc);
    	
    	background.setBounds(0,0,240,340);
    	art.setBounds(65,73,110,110);
    	closeButton.setBounds(200,16,16,21);
    	libraryButton.setBounds(23,48,20,25);
    	openButton.setBounds(198,48,20,25);
    	name.setBounds(40,190,150,20);
    	name.setBackground(null);
    	name.setForeground(Color.white);
    	name.setFont(null);
    	playButton.setBounds(115,230,12,25);
    	pauseButton.setBounds(115,230,12,25);
    	forwardButton.setBounds(155,233,22,17);
    	backwardButton.setBounds(60,233,22,17);
    	heartButton.setBounds(25,285,20,25);
    	heart_redButton.setBounds(25,285,20,22);
    	shuffleButton.setBounds(195,285,20,25);
    	repeatButton.setBounds(113,285,20,25);
    }
	
	
    public void actionPerformed(ActionEvent ae) 
    {
    	int count=0;
    	if(ae.getSource() == forwardButton)
    	{
    		try {
				proxy.eval("pause(song);song = audioplayer(Y,Fs*3);resume(song);");
			} catch (MatlabInvocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    	else if(ae.getSource() == backwardButton)
    	{
    		try {
				proxy.eval("pause(song);song = audioplayer(Y,Fs/3);resume(song);");
			} catch (MatlabInvocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	else if(ae.getSource() == libraryButton)
    	{
		try {
			ResultSet rs = stm.executeQuery("SELECT COUNT(*) from fav");
				
			while(rs.next())
			{
				count = rs.getInt("count(*)");
			}
	
			} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		JFrame lframe = new LFrame(this,this.stm,this.link,this.proxy,count);
		lframe.setVisible(true);
		this.setVisible(false);		
    	}
    else if (ae.getSource() == openButton) 
        {
            int returnVal = fc.showOpenDialog(applepie.this);
            	if (returnVal == JFileChooser.APPROVE_OPTION) 
            	{
            		File myfile = fc.getSelectedFile();
            		try {
						AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(myfile);
						AudioFormat format = audioInputStream.getFormat();
							frames = audioInputStream.getFrameLength();
							dur = (frames+0.0)/(format.getFrameRate()*60);
					} catch (UnsupportedAudioFileException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            		filepath = myfile.getPath();
            		filename = myfile.getName();
            		name.setText(null);
            		name.setText(filename);      		
            	} 
            	else 
            	{
            	name.setText(null);
                name.setText("No media found/selected.");
            	}
            	
            	try 
        		{
        			proxy.setVariable("file", filepath);
        		} 
        		catch (MatlabInvocationException e) 
        		{
        			e.printStackTrace();
        		}
        		try 
        		{
        			proxy.eval("[Y,Fs]=wavread(file);song = audioplayer(Y,Fs);");
        		} 
        		catch (MatlabInvocationException e) 
        		{
        			e.printStackTrace();
        		}
        		
        		
        		try {
    				PreparedStatement prep = link.prepareStatement("SELECT COUNT(*) FROM fav WHERE song_path = ?");
    				prep.setString(1, filepath);
    				ResultSet rs = prep.executeQuery();
    				
    				while(rs.next())
    				{
    					rows = rs.getInt("count(*)");
    					
    				}
    			} catch (SQLException e1) {
    				e1.printStackTrace();
    			}
    			
    			if(rows != 0)
    			{
    				heartButton.setVisible(false);
    	    		heart_redButton.setVisible(true);
    				
    			}
       }
    		
    	else if(ae.getSource() == pauseButton)
    	{		
        		{
        			playButton.setVisible(true);
        			pauseButton.setVisible(false);
        			try {
						proxy.eval("pause(song);");
					} catch (MatlabInvocationException e) 
					{
						e.printStackTrace();
					}
        		}}
    	else if(ae.getSource()==playButton)
        			//if(result.equals("off"))
        		{	
        			playButton.setVisible(false);
    				pauseButton.setVisible(true);
        			try {
						proxy.eval("resume(song);");
					} catch (MatlabInvocationException e) 
					{
						e.printStackTrace();
					}
				}
    		 
    
    	else if(ae.getSource() == heartButton)
    	{
    		heartButton.setVisible(false);
    		heart_redButton.setVisible(true);
			try 
			{
    			PreparedStatement prep = link.prepareStatement("INSERT INTO fav(song_name,song_path) VALUES(?, ?)");
    			prep.setString(1, filename);
    			prep.setString(2, filepath);
    			prep.executeUpdate();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
    	
    	else if(ae.getSource() == heart_redButton)
    	{
    		heartButton.setVisible(true);
    		heart_redButton.setVisible(false);
	
			PreparedStatement prep;
			try {
				prep = link.prepareStatement("DELETE FROM fav WHERE song_path=?");
    			prep.setString(1, filepath);
    			prep.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	
    	}
        		else if (ae.getSource()== closeButton)
        		{		
        			try 
        			{
						proxy.eval("stop(song)");
					} 
        			catch (MatlabInvocationException e) {
        				e.printStackTrace();
					}
        			System.exit(0);
        		}
    	}
    
    
  
    public static void main(String s[]) throws MatlabInvocationException, MatlabConnectionException, ClassNotFoundException, SQLException
    {
    	JFrame MyFrame = new applepie();
    	
    	MatlabProxyFactoryOptions options = new MatlabProxyFactoryOptions.Builder()
    	.setHidden(true)
    	.setUsePreviouslyControlledSession(true)
    	.setMatlabLocation(null)
    	.build();

	MatlabProxyFactory factory = new MatlabProxyFactory(options);
	
		try 
		{
			proxy = factory.getProxy();
		} 
		catch (MatlabConnectionException e) 
		{
			e.printStackTrace();
		}
		
		Class.forName("com.mysql.jdbc.Driver");
		link = DriverManager.getConnection("jdbc:mysql://localhost:3306/music","root","root");
		 stm = link.createStatement();
    }
} 
