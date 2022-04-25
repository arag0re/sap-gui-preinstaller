
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class DragNDrop {
	FileDropHandler myFileDropHandler = null;
	JFrame myFrame = null;
	JLabel myLabel = null;
	JButton myButton = null;
	String pathVar = "";
	final static String aarch64 = "aarch64";
	final static String x86_64 = "x86_64";
	final static String windoof = "Windows";
	final static String macOS = "Mac OS X";
	final static String linux = "Linux";
	static String downloadUrl;
	static String installerPath;
	static int confirmationStatus;
	static String[] archs = {aarch64, x86_64};
	static JFrame jFrame = new JFrame();
	static JLabel jLabel = new JLabel();
	

	  // Create a class constructor for the Main class
	public DragNDrop() {
		downloadUrl = JOptionPane.showInputDialog("Enter the Download URL copied from Github page for SapMachine");
		myFileDropHandler = new FileDropHandler(); 
		myFrame = new JFrame("DragNDrop");
		myLabel = new JLabel();
		myButton = new JButton("ok");
		myButton.setSize(20,20);
		myButton.setAlignmentY(0);
		myButton.setAlignmentX(0);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setSize(500,400);
		myFrame.setLocationRelativeTo(null);
		myFrame.setVisible(true);
		myFrame.add(myButton);
		myFrame.add(myLabel);
		myLabel.setText("drag in here");
		myLabel.setTransferHandler(myFileDropHandler);
		myButton.addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {
		        System.out.println(myLabel.getText());
		        myFrame.setVisible(false);
		        executeBashScript(getArch(), downloadUrl, myLabel.getText(), getOs());
		    }
		});
		myFrame.addComponentListener(new ComponentAdapter()
		{  
	        public void componentResized(ComponentEvent evt) {
	            Component c = (Component)evt.getSource();
	            myButton.setSize(20,20);
	        }
		});
	}
	
	public String getArch() {
		return System.getProperty("os.arch");
	}
	
	public String getOs() {
    	String osName = System.getProperty("os.name");
    	String os = null;
    	switch(osName) {
    	case macOS:
    		os = "macOS";
    		break;
    	case windoof:
    		os = "Windoof";
    		break;
    	case linux:
    		os = "Linux";
    		break;
    	default:
    		break;
    	}
    	return os;
	}
	
	public static void executeBashScript(String arch, String url, String path, String OS) {
		String[] cmd = new String[]{"/bin/sh", "/Users/antonstadie/Developer/EclipseWorkspace/SAP-GUI-PreInstaller/src/"+ OS + "/"+arch+".sh", url ,path};
    	try {
			Process pr = Runtime.getRuntime().exec(cmd);
			BufferedReader stdInput = new BufferedReader(new 
				     InputStreamReader(pr.getInputStream()));
				
				BufferedReader stdError = new BufferedReader(new 
				     InputStreamReader(pr.getErrorStream()));

				// Read the output from the command
				System.out.println("Here is the standard output of the command:\n");
				String s = null;
				
				while ((s = stdInput.readLine()) != null) {
					
					
				    System.out.println(s);
				}
				// Read any errors from the attempted command
				System.out.println("Here is the standard error of the command (if any):\n");
				
				while ((s = stdError.readLine()) != null) {
				    System.out.println(s);
				   
				}
				
				System.out.println(pr);
				System.exit(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String showDragNDropPanel() {
		DragNDrop myObj = new DragNDrop();
		myObj.myButton.addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {
		        System.out.println(myObj.myLabel.getText());
		        myObj.myFrame.setVisible(false);
		        myObj.pathVar = myObj.myLabel.getText();
		    }
		});
		myObj.myFrame.addComponentListener(new ComponentAdapter()
		{  
	        public void componentResized(ComponentEvent evt) {
	            Component c = (Component)evt.getSource();
	            myObj.myButton.setSize(20,20);
	        }
	});
		return myObj.pathVar;
		
	}
	
	public static void main(String[] args) {
		DragNDrop myObj = new DragNDrop();
		
	}
}
