import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class GuiApp1 extends JFrame {

    private JTextField tfCount;
    private static WebClient client = new WebClient();

    JLabel label = new JLabel();

    public GuiApp1() {

        try {

           
            label = new JLabel(new ImageIcon(ImageIO.read(GuiApp1.class.getResourceAsStream("/logo.png"))));

        }
        catch (IOException e)
        {

        }

        Container cp = getContentPane();

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.insets = new Insets(0, 15, 250, 15);

        //constraints.anchor = GridBagConstraints.NORTH;

        cp.setLayout(new GridBagLayout());

        Font bigFont = new Font("Calibri", Font.PLAIN, 20);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 0;

        label.setSize(100,100);

        constraints.anchor = GridBagConstraints.CENTER;

        cp.add(label,constraints);

        constraints.insets = new Insets(0, 15, 0, 15);

        cp.setBackground(new Color(0,0,0));

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 1;

        constraints.anchor = GridBagConstraints.CENTER;

        JLabel username = new JLabel("Sláið inn KT");

        username.setForeground(Color.WHITE);

        username.setFont(bigFont);

        cp.add(username, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 1;

        constraints.anchor = GridBagConstraints.CENTER;

        tfCount = new JTextField("", 10);
        tfCount.setEditable(true);
        tfCount.setFont(bigFont);
        cp.add(tfCount,constraints);





        tfCount.addKeyListener
                (new KeyAdapter() {
                     public void keyPressed(KeyEvent e) {
                         int key = e.getKeyCode();
                         if (key == KeyEvent.VK_ENTER) {

                             handleGateControl(tfCount.getText());

                             tfCount.setText("");
                         }
                     }

                 }
                );



        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 2;
        constraints.gridy = 1;

        constraints.anchor = GridBagConstraints.CENTER;

        JButton btnCount = new JButton("Senda");
        cp.add(btnCount, constraints);

        btnCount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                handleGateControl(tfCount.getText());
                tfCount.setText("");

            }

        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Wodify Gate System");
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

    }

    public void handleGateControl(String data) {

        int messageType = JOptionPane.PLAIN_MESSAGE;

        if (client.wodifyRequest(data).equals("True")) {

            if (client.openGate().equals("\"true\"")) {

                createCloseTimer(5).start();


                JOptionPane.showMessageDialog(null, "Velkomin", "CFR Gate Control", messageType);
            }
            else {

                createCloseTimer(10).start();
                JOptionPane.showMessageDialog(null, "Velkomin", "CFR Gate Control", messageType);

            }
        }
        else{

            createCloseTimer(10).start();
            JOptionPane.showMessageDialog(null, "Röng kennitala", "CFR Gate Control", JOptionPane.ERROR_MESSAGE);

        }
    }

    

    private Timer createCloseTimer(int seconds) {
        ActionListener close = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Window[] windows = Window.getWindows();
                for (Window window : windows) {
                    if (window instanceof JDialog) {
                        JDialog dialog = (JDialog) window;
                        if (dialog.getContentPane().getComponentCount() == 1
                                && dialog.getContentPane().getComponent(0) instanceof JOptionPane){
                            dialog.dispose();
                        }
                    }
                }

            }

        };
        Timer t = new Timer(seconds * 1000, close);
        t.setRepeats(false);
        return t;
    }

    /** The entry main() method */
    public static void main(String[] args) {
        // Run the GUI construction in the Event-Dispatching thread for thread-safety
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GuiApp1(); // Let the constructor do the job
            }
        });
    }
}









