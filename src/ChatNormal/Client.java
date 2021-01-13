package ChatNormal;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

import java.util.Calendar;
import java.text.SimpleDateFormat;
import javax.swing.*;

public class Client implements ActionListener {

    JPanel p1;
    JTextField t1;
    JButton b1;
    static JPanel a1;
    static JFrame f = new JFrame("Chat App");
    static JPanel f1 = new JPanel();

    static Box vertical = Box.createVerticalBox();

    static Socket socket;
    static DataInputStream din;
    static DataOutputStream dout;

    Boolean typing;

    Client() {

        f.add(f1);
        p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(7, 94, 84));
        p1.setBounds(0, 0, 450, 70);
        f1.add(p1);

        JLabel l3 = new JLabel("Client");
        l3.setFont(new Font("Dialog", Font.BOLD, 18));
        l3.setForeground(Color.WHITE);
        l3.setBounds(110, 15, 300, 18);
        p1.add(l3);

        JLabel l4 = new JLabel("Đang Hoạt Động");
        l4.setFont(new Font("Dialog", Font.PLAIN, 14));
        l4.setForeground(Color.WHITE);
        l4.setBounds(110, 35, 200, 20);
        p1.add(l4);

        Timer t = new Timer(1, new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (!typing) {
                    l4.setText("Đang Hoạt Động");
                }
            }
        });

        t.setInitialDelay(2000);

        a1 = new JPanel();
        a1.setBounds(5, 75, 400, 400);
        a1.setFont(new Font("Dialog", Font.PLAIN, 16));
        f1.add(a1);

        t1 = new JTextField();
        t1.setBounds(5, 500, 300, 40);
        t1.setFont(new Font("Dialog", Font.PLAIN, 16));
        f1.add(t1);
        t1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        String out = t1.getText();
                        JPanel p2 = formatLabel(out);
                        a1.setLayout(new BorderLayout());
                        JPanel right = new JPanel(new BorderLayout());
                        right.add(p2, BorderLayout.LINE_END);
                        vertical.add(right);
                        vertical.add(Box.createVerticalStrut(15));
                        a1.add(vertical, BorderLayout.PAGE_START);
                        dout.writeUTF(out);
                        t1.setText("");
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
        });

        t1.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                l4.setText("Đang nhập...");

                t.stop();

                typing = true;
            }

            public void keyReleased(KeyEvent ke) {
                typing = false;

                if (!t.isRunning()) {
                    t.start();
                }
            }
        });

        b1 = new JButton("Send");
        b1.setBounds(300, 500, 123, 40);
        b1.setBackground(new Color(7, 94, 84));
        b1.setForeground(Color.WHITE);
        b1.setFont(new Font("Dialog", Font.PLAIN, 16));
        b1.addActionListener(this);
        f1.add(b1);

        f.getContentPane().setBackground(Color.WHITE);
        f1.setLayout(null);
        f.setSize(450, 600);
        f.setLocation(100, 40);
        f.setVisible(true);
        f.setDefaultCloseOperation(1);

    }

    public void actionPerformed(ActionEvent ae) {

        try {
            String out = t1.getText();

            JPanel p2 = formatLabel(out);

            a1.setLayout(new BorderLayout());

            JPanel right = new JPanel(new BorderLayout());
            right.add(p2, BorderLayout.LINE_END);
            vertical.add(right);
            vertical.add(Box.createVerticalStrut(15));

            a1.add(vertical, BorderLayout.PAGE_START);

            //a1.add(p2);
            dout.writeUTF(out);
            t1.setText("");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static JPanel formatLabel(String out) {
        JPanel p3 = new JPanel();
        p3.setLayout(new BoxLayout(p3, BoxLayout.Y_AXIS));

        JLabel l1 = new JLabel("<html><p style = \"width : 150px\">" + out + "</p></html>");
        l1.setFont(new Font("Dialog", Font.PLAIN, 16));
        l1.setBackground(new Color(37, 211, 102));
        l1.setOpaque(true);
        l1.setBorder(new EmptyBorder(15, 15, 15, 50));

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        JLabel l2 = new JLabel();
        l2.setText(sdf.format(cal.getTime()));

        p3.add(l1);
        p3.add(l2);
        return p3;
    }

    public static void Show() {
        new Client().f1.setVisible(true);

        try {

            socket = new Socket("127.0.0.1", 4433);
            din = new DataInputStream(socket.getInputStream());
            dout = new DataOutputStream(socket.getOutputStream());

            String msginput = "";

            while (true) {
                a1.setLayout(new BorderLayout());
                msginput = din.readUTF();
                JPanel p2 = formatLabel(msginput);
                JPanel left = new JPanel(new BorderLayout());
                left.add(p2, BorderLayout.LINE_START);

                vertical.add(left);
                vertical.add(Box.createVerticalStrut(15));
                a1.add(vertical, BorderLayout.PAGE_START);
                f1.validate();
            }

        } catch (Exception e) {
        }
    }
}
