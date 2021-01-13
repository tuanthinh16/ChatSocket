package ChatGroup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.io.*;
import java.net.*;

public class Máy_4 extends JFrame implements ActionListener, Runnable {

    JPanel p1;
    JTextField t1;
    JButton b1;
    static JTextArea a1;

    BufferedWriter writer;
    BufferedReader reader;

    Máy_4() {

        p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(7, 94, 84));
        p1.setBounds(0, 0, 450, 70);
        add(p1);

        JLabel l3 = new JLabel("Nhóm VIP PRO");
        l3.setFont(new Font("Dialog", Font.BOLD, 18));
        l3.setForeground(Color.WHITE);
        l3.setBounds(110, 15, 200, 18);
        p1.add(l3);

        JLabel l4 = new JLabel("TuanThinh,Akai và 2 người khác");
        l4.setFont(new Font("Dialog", Font.PLAIN, 14));
        l4.setForeground(Color.WHITE);
        l4.setBounds(110, 35, 260, 20);
        p1.add(l4);

        a1 = new JTextArea();
        a1.setBounds(5, 75, 300, 400);
        a1.setFont(new Font("Dialog", Font.PLAIN, 16));
        a1.setEditable(false);
        a1.setLineWrap(true);
        a1.setWrapStyleWord(true);
        add(a1);

        t1 = new JTextField();
        t1.setBounds(5, 500, 310, 40);
        t1.setFont(new Font("Dialog", Font.PLAIN, 16));
        add(t1);

        b1 = new JButton("Send");
        b1.setBounds(320, 500, 123, 40);
        b1.setBackground(new Color(7, 94, 84));
        b1.setForeground(Color.WHITE);
        b1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        b1.addActionListener(this);

        t1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    String str = "Máy 4 - Marya: \n" + t1.getText();
                    try {
                        writer.write(str);
                        writer.write("\r\n");
                        writer.flush();
                    } catch (Exception e) {
                    }
                    t1.setText("");
                }
            }
        });
        add(b1);

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setSize(450, 600);
        setLocation(400, 40);
        setVisible(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        try {

            Socket socketClient = new Socket("localhost", 443);
            writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
        } catch (Exception e) {
        }

    }

    public void actionPerformed(ActionEvent ae) {
        String str = "Máy 4 - Marya: \n" + t1.getText();
        try {
            writer.write(str);
            writer.write("\r\n");
            writer.flush();
        } catch (Exception e) {
        }
        t1.setText("");
    }

    public void run() {
        try {
            String msg = "";
            while ((msg = reader.readLine()) != null) {
                a1.append(msg + "\n");
            }
        } catch (Exception e) {
        }
    }

    public static void shower() {
        Máy_4 one = new Máy_4();
        Thread t1 = new Thread(one);
        t1.start();
    }

}
