import java.awt.Adjustable;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Console {
    final JFrame frame = new JFrame("CONSOLE");
    JTextArea textPane;
    JScrollPane scroll;

    public Console() throws Exception {
        textPane = new JTextArea();
        textPane.setPreferredSize(new Dimension(525, 600));
        scroll = new JScrollPane(textPane);
        textPane.setBackground(Color.BLACK);

        scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );

        textPane.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                scrollToBottom(scroll);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        //Add textPane in to middle panel
        frame.add(scroll);
        frame.setSize(100, 600);
        frame.pack();
        frame.setVisible(false);
        redirectOut();

    }

    public PrintStream redirectOut(String) {
        OutputStream out = new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                textPane.append(String.valueOf((char) b));
            }
        };
        OutputStream errOut = new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                textPane.append(String.valueOf((char) b));
            }
        };
        PrintStream ps = new PrintStream(out);
        PrintStream err = new PrintStream(errOut);

        System.setOut(ps);
        System.setErr(err);
        scrollToBottom(scroll);

        return ps;
    }

    public JFrame getFrame() {
        return frame;
    }

    private void scrollToBottom(JScrollPane scrollPane) {
        JScrollBar verticalBar = scrollPane.getVerticalScrollBar();
        AdjustmentListener downScroller = new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                Adjustable adjustable = e.getAdjustable();
                adjustable.setValue(adjustable.getMaximum());
                verticalBar.removeAdjustmentListener(this);
            }
        };
        verticalBar.addAdjustmentListener(downScroller);
    }
}