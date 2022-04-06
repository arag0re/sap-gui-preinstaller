
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
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
	static int confirmationStatus;
	static String[] archs = {aarch64, x86_64};
	
	public static void setDownloadUrl() {
		downloadUrl = JOptionPane.showInputDialog("Enter the Download URL copied from Github page for SapMachine");
	}
	
	public static String getDownloadUrl() {
		return downloadUrl;
	}
	
	public static void setInstallerPath() {
		installerPath =  DragNDrop.showDragNDropPanel();
	}
	
	public static String getInstallerPath() {
		return installerPath;
	}
	
	
	
	public static void main(String[] args) {
		String osArch = System.getProperty("os.arch");
    	String osName = System.getProperty("os.name");
		setDownloadUrl();
		setInstallerPath();	
		if(installerPath != "" ) {
			executeBashScript(0);
		}
		
		
	}
	
	
	
	
	public static void executeBashScript(int key) {
		String doUrl = getDownloadUrl();
		String path = getInstallerPath();
		//System.out.println();
		String arch = archs[key];
		String[] cmd = new String[]{"/bin/sh", "/Users/antonstadie/Developer/EclipseWorkspace/SAP-GUI-PreInstaller/src/"+arch+".sh", doUrl, path};
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
}


