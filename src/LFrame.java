import java.awt.Color;
import matlabcontrol.*;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import matlabcontrol.MatlabInvocationException;

import com.mysql.jdbc.ResultSet;

public class LFrame extends JFrame implements ActionListener{
 public Component previous;
 public Object proxy_new;
 
 int i;
 int w=50;
 JLabel slabel;
 JLabel lfilename;
 JLabel duration;
 JLabel background;
 JLabel lbackground;
 JButton back;
 JButton heart;
 JButton play;
 JButton pause;
 Font font1;
 Font font2;
 
	public LFrame(Object ref,Object stm,Object link,Object proxy,int count)
	{
		proxy_new = proxy;
		java.sql.ResultSet rs = null;
try {
	rs =((Statement) stm).executeQuery("SELECT * FROM fav");
} catch (SQLException e) {
	e.printStackTrace();
}
	previous =(Component)ref;
	font1 = new Font("Arial", Font.BOLD,13);
	font2 = new Font("Arial", Font.ITALIC,10);
	setLayout(null);
	setUndecorated(true);
	setBackground(new Color(0,0,0,0));
   	setTitle("Apple Pie!");
	setLocation(500,250);
	setSize(240,340);
	setResizable(false);
		
	 background = new JLabel(new ImageIcon("C:/Users/atul2527/Desktop/images/background.png"));
	
	 ImageIcon backi = new ImageIcon("C:/Users/atul2527/Desktop/images/library.png");
	 back = new JButton("",backi);
	 back.addActionListener(this);
	 back.setBorderPainted(false);
	 add(background);
	 background.add(back);
	
	 for(i=1;i<=count;i++){
		 w = w+5;
		 try {
			rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 slabel = new JLabel();
	 slabel.setOpaque(true);
	 slabel.setBackground(Color.lightGray);
	 
	 
	 lbackground = new JLabel(new ImageIcon("C:/Users/atul2527/Desktop/images/library_bg.png"));
	 
	 lfilename = new JLabel();
	 try {
		lfilename.setText(rs.getString("song_name"));
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 lfilename.setOpaque(false);
	 lfilename.setForeground(Color.red);
	 
	 duration = new JLabel();
	 duration.setText("Song Duration");
	 duration.setVisible(true);
	 duration.setForeground(Color.black);
	 
	 ImageIcon hearti = new ImageIcon("C:/Users/atul2527/Desktop/images/heart_red_lib.png");
	 heart = new JButton("",hearti);
	 heart.setBorderPainted(false);
	 
	 ImageIcon playi = new ImageIcon("C:/Users/atul2527/Desktop/images/play_lib.png");
	 
	 play = new JButton("",playi);
	 play.addActionListener(this);
 	 play.setBorderPainted(false);
 	
 	ImageIcon pausei = new ImageIcon("C:/Users/atul2527/Desktop/images/pause.png");
 	 pause = new JButton("",pausei);
	 pause.addActionListener(this);
	 pause.setBorderPainted(false);
	 pause.setVisible(false);
	 background.add(slabel);
	 slabel.add(lbackground);
	 lbackground.add(lfilename);
	 lbackground.add(duration);
	 lbackground.add(heart);
	 lbackground.add(play);
	 lbackground.add(pause);
	 slabel.setBounds(30,w,180,40);
	 w=w+40;
	 lbackground.setBounds(0,0,180,40);
	 lfilename.setBounds(5,5,180,15);
	 lfilename.setFont(font1);
	 duration.setBounds(5,20,180,15);
	 duration.setFont(font2);
	 heart.setBounds(132,10,15,20);
	 play.setBounds(154,10,20,20);
	 pause.setBounds(154,10,20,20);
	 }
	 
	 
	 background.setBounds(0,0,240,340);
	 back.setBounds(25,25,15,15);	 	 
	}
	
	
	public void actionPerformed(ActionEvent ae1) {
		
		if(ae1.getSource()== back)
		{	
			this.setVisible(false);
			previous.setVisible(true) ;
		}
		else if(ae1.getSource() == pause)
    	{		
        		{
        			play.setVisible(true);
        			pause.setVisible(false);
        			try {
						((MatlabOperations)proxy_new).eval("pause(song);");
					} catch (MatlabInvocationException e) 
					{
						e.printStackTrace();
					}
        		}}
    	else if(ae1.getSource()==play)
        			//if(result.equals("off"))
        		{	
        			play.setVisible(false);
    				pause.setVisible(true);
        		
    				try {
						((MatlabOperations)proxy_new).eval("resume(song);");
					} catch (MatlabInvocationException e) 
					{
						e.printStackTrace();
					}
				}
    		 
	}

}


/*
create table fav
(
	song_id INT AUTO_INCREMENT,
	song_name VARCHAR(255) NOT NULL,
	song_path VARCHAR(255) NOT NULL,
	PRIMARY KEY(song_id)
	
);
*/
