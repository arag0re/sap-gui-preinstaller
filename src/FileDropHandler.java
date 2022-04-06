import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.TransferHandler;


final public class FileDropHandler extends TransferHandler {
    private static final long serialVersionUID = -1684586185539643210L;
	public List<File> files = new ArrayList<>();
    
	@Override
    public boolean canImport(TransferHandler.TransferSupport support) {
        for (DataFlavor flavor : support.getDataFlavors()) {
            if (flavor.isFlavorJavaFileListType()) {
                return true;
            }
        }
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean importData(TransferHandler.TransferSupport support) {
        if (!this.canImport(support))
            return false;

        
        try {
            files = (List<File>) support.getTransferable()
                    .getTransferData(DataFlavor.javaFileListFlavor);
        } catch (UnsupportedFlavorException | IOException ex) {
            // should never happen (or JDK is buggy)
            return false;
        }

        for (File file: files) {
            //setPath(file.toString());
            //System.out.println(file);
            JLabel label = (JLabel) support.getComponent();
            label.setText(file.toString());
        }
        
        return true;
    }
}