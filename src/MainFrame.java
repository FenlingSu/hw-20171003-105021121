import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private Container cp;
    private JPanel jpn = new JPanel(new GridLayout(7,1,3,3));
    private JButton jbtex = new JButton("Example");
    private JButton jbten = new JButton("Encryption");
    private JButton jbtcl = new JButton("Clear");
    private JButton jbtde = new JButton("Decrypte");
    private JButton jbtout = new JButton("Exit");
    private JLabel jlbkey = new JLabel("key");
    private JTextField jtf = new JTextField("csie");
    private JTextArea jtaL = new JTextArea();
    private JTextArea jtaR = new JTextArea();
    private JScrollPane jspL = new JScrollPane(jtaL);
    private JScrollPane jspR = new JScrollPane(jtaR);


    public MainFrame(){
        initComp();
    }
    private void initComp(){
        this.setBounds(100,100,500,400);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        cp = this.getContentPane();
        cp.setLayout(new BorderLayout(3,3));
        cp.add(jpn , BorderLayout.CENTER);
        jpn.add(jbtex);
        jpn.add(jbten);
        jpn.add(jlbkey);
        jpn.add(jtf);
        jpn.add(jbtcl);
        jpn.add(jbtde);
        jpn.add(jbtout);

        cp.add(jspL , BorderLayout.WEST);
        cp.add(jspR , BorderLayout.EAST);
        jtaL.setPreferredSize(new Dimension(200,400));
        jtaR.setPreferredSize(new Dimension(200,400));
        jtaL.setLineWrap(true);
        jtaR.setLineWrap(true);

        jbtex.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jtaL.setText(
                        "The UK Times Higher Education (THE) Report " +
                        "announces the global young university ranking " +
                        "result, and Asia University (AU), being 16 years old " +
                        "only, is ranked to be in the list of top 150-200.");
            }
        });

        jbten.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                byte data[] = jtaL.getText().getBytes();     //網路查詢相關作法
                byte key[] = jtf.getText().getBytes();
                int len = data.length;
                int bin = 0;

                for (int i = 0; i < len; i++) {
                    data[i] = (byte)(data[i] ^ key[bin]);    //網路查詢相關作法
                    if(++bin == key.length){
                        bin = 0;
                    }
                }

                jtaR.setText(new String(data));
            }
        });

        jbtde.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                byte data[] = jtaR.getText().getBytes();
                byte key[] = jtf.getText().getBytes();
                int len = data.length;
                int bin = 0;

                for (int i = 0 ; i<len ; i++) {
                    data[i] = (byte)(data[i] ^ key[bin]);
                    if(++bin == key.length){
                        bin = 0;
                    }
                }

                jtaR.setText(new String(data));
            }
        });



        jbtcl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jtaL.setText("");
                jtaR.setText("");
            }
        });

        jbtout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

    }
}
