
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JOptionPane;

public class PreInstaller {
	final static String aarch64 = "aarch64";
	final static String x86_64 = "x86_64";
	final static String windoof = "Windows";
	final static String macOS = "Mac OS X";
	static String downloadUrl = "";
	static String installerPath = "";
	static String[] archs = {aarch64, x86_64};
	public static void main(String[] args) {
		downloadUrl = JOptionPane.showInputDialog("Enter the Download URL copied from Github page for SapMachine");
		installerPath = JOptionPane.showInputDialog("Enter the path to the installer DMG for SAPGUI");
		int response = JOptionPane.showConfirmDialog(null, "Do you want to install SAPGUI for Mac?",
                "", JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE);
        if(response==1) {
        	System.out.println("exit program");
        	System.exit(0);
        } else { 
        	String osArchitecture = System.getProperty("os.arch");
        	String osName = System.getProperty("os.name");
        	System.out.println(osArchitecture);
        	
            		System.out.println("plaform and architecture have been checked "+ osArchitecture);
            		executeBashScript(0);
            		// AARCH64
            	
            		//executeBashScript(1);
            		// X86_64
            	
        	
        	
        	/*
        	String[] props = getSysProps();
        	int seperatorIndex = 24;
        	props = removeTheElement(props, 0); 
        	props = removeTheElement(props, seperatorIndex); //delete seperatorLine
        	props = removeTheElement(props, seperatorIndex); //delete blank seperatorLine
        	//System.out.println(Arrays.toString(props));
        	Map<String, String> sysProperties  = new HashMap<String, String>();
        	for(String str: props) {
        			String[] mapEntries = str.split("=");
        			String key = mapEntries[0];
        			String value = mapEntries[1];
        			sysProperties.put(key, value);
        			//System.out.println(str);
            }
        	var platform = sysProperties.get("os.name");
        	var architecture = sysProperties.get("os.arch");
        	if(architecture.equals(aarch64) && platform.equals(macOS)) {
        		System.out.println("plaform and architecture have been checked");
        		executeBashScript(0);
        		// AARCH64
        	} else {
        		executeBashScript(1);
        		// X86_64
        	}*/
        }
	}
	
	public static void getTestDict() {
		System.out.println();
	}
	
   
	
	public static String[] getSysProps() {
		String sysProps = "";
		String[] props = null;
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    final String utf8 = StandardCharsets.UTF_8.name();
        try (PrintStream ps = new PrintStream(baos, true, utf8)) {
        	System.getProperties().list(ps);
        } catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        try {
			sysProps = baos.toString(utf8);
			props = sysProps.split(System.lineSeparator());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        return props;
	}
	
	public static void executeBashScript(int key) {
		var arch = archs[key];
		//System.out.println();
		String[] cmd = new String[]{"/bin/sh", "/Users/antonstadie/Developer/EclipseWorkspace/SAP-GUI-PreInstaller/src/" + arch + ".sh", downloadUrl, installerPath};
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


