import javax.swing.*;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.border.*;
import javax.swing.plaf.basic.BasicButtonListener;

public class GraphUserInter extends JFrame {
	
	Write write = new Write();
	private static  int WIDTH = 250;
	private static  int HEIGHT = 300;
	
	private JRadioButton newSearch, prevSearch; //newSearch moves to next menu which asks user which search they want averages
												//for. prevSearch reads from txt of previous data.
	
	private JButton submit;
	
//********************************************************************************************************************************
	
	public GraphUserInter() 
	{
		setTitle("GUI");
		setSize(WIDTH,HEIGHT);
		setLayout(new FlowLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		createContents();
		positionWindow();
		setVisible(true);
	} // end GUI constructor
 
//********************************************************************************************************************************
	private void positionWindow() //This will just have the UI window open above and left of center of the screen
	{
	        // Get the size of the screen
	        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

	        // Determine the new location of the window
	        int w = this.getSize().width;
	        int h = this.getSize().height;
	        int x = (dim.width-w)/3;
	        int y = (dim.height-h)/3;

	        // Move the window
	        this.setLocation(x, y);
	} //end position Window
	
	public void createContents()
	{
		JPanel windowPanel = new JPanel(new BorderLayout(0,10));
		windowPanel.setBorder(new EmptyBorder(10,10,10,10));
		JPanel centerPanel = new JPanel(new GridLayout(11,11));
		
		JPanel southPanel = new JPanel(new FlowLayout());
		
		
		
		newSearch = new JRadioButton("New Data Scrape");
		prevSearch = new JRadioButton("Previous Data Scrape");
		ButtonGroup radioGroup = new ButtonGroup();
		radioGroup.add(newSearch);
		radioGroup.add(prevSearch);
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ButtonListener());
		
		
		
		
		centerPanel.add(new JLabel("Options:"));
		centerPanel.add(newSearch);
		centerPanel.add(prevSearch);
		centerPanel.add(new JLabel());
		centerPanel.add(submit);
		
		windowPanel.add(centerPanel, BorderLayout.CENTER);
		//southPanel.add(submit);
		windowPanel.add(southPanel, BorderLayout.SOUTH);
		add(windowPanel);
	} //end createContents
	

	private void prevScrape()
	{
		setSize(500,HEIGHT);
		JPanel windowPanel = new JPanel(new BorderLayout(0,10));
		windowPanel.setBorder(new EmptyBorder(10,10,10,10));
		
		JPanel centerPanel = new JPanel(new GridLayout(11,11));

		JPanel southPanel = new JPanel(new FlowLayout());
		
		
		prevSearch = new JRadioButton("Toilet Paper");
		
		ButtonGroup radioGroup = new ButtonGroup();
		
		radioGroup.add(prevSearch);
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ButtonListener());
		
		centerPanel.add(new JLabel());
		centerPanel.add(prevSearch);
		
		windowPanel.add(centerPanel, BorderLayout.CENTER);
		southPanel.add(submit);
		windowPanel.add(southPanel, BorderLayout.SOUTH);
		add(windowPanel);
		
		//return null;
	}
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if (newSearch.isSelected())
			{
			JOptionPane.showMessageDialog(null, "Not Currently Availible, Jackass");
			
			}
			else
			{
			Write write = new Write();
			try {
				write.writeTenToText();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			}
		}
	}
	
  //-----------------------------------------------------------------DRIVER----------------------------------------------------------------------
  public static void main(String[] args)
  {
    new GraphUserInter();
    
    
    }
}
