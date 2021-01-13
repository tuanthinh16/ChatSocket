package ChatGroup;

import ChatApp.Connectdtb;
import com.mysql.jdbc.Connection;
import javax.swing.*;
import java.sql.Statement;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

import java.io.*;
import java.net.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Máy_1 extends JFrame implements ActionListener, Runnable {

    String usernamecsdl;
    String passcsdl;
    Connection conn = Connectdtb.getConnectdtb();
    Statement statement;
    ResultSet rs;
    JPanel p1;
    JTextField t1;
    JButton b1;
    static JTextArea a1;

    BufferedWriter writer;
    BufferedReader reader;

    Máy_1() {

        p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(22, 189, 30));
        p1.setBounds(0, 0, 450, 70);
        add(p1);

        JLabel l3 = new JLabel("Nhóm VIP PRO");
        l3.setFont(new Font("Dialog", Font.BOLD, 22));
        l3.setForeground(Color.WHITE);
        l3.setBounds(110, 15, 200, 18);
        p1.add(l3);

        JLabel l4 = new JLabel("TuanThinh,Akai và 2 người khác");
        l4.setFont(new Font("Dialog", Font.ITALIC, 16));
        l4.setForeground(new Color(192, 74, 17));
        l4.setBounds(110, 35, 260, 20);
        p1.add(l4);

        a1 = new JTextArea();
        a1.setBackground(new Color(8, 114, 163));
        a1.setBounds(0, 75, 440, 400);
        a1.setFont(new Font("Dialog", Font.PLAIN, 16));
        a1.setEditable(false);
        a1.setLineWrap(true);
        a1.setWrapStyleWord(true);
        add(a1);

        t1 = new JTextField();
        t1.setBounds(0, 500, 310, 40);
        t1.setFont(new Font("Dialog", Font.PLAIN, 16));
        //gán phím enter thay nút gửi
        t1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    String str = "Máy 1- Tuấn Thịnh \n" + t1.getText();
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
        add(t1);

        b1 = new JButton("Send");
        b1.setBounds(320, 500, 123, 40);
        b1.setBackground(new Color(13, 35, 244));
        b1.setForeground(Color.WHITE);
        b1.setFont(new Font("Dialog", Font.BOLD, 16));
        b1.addActionListener(this);

        add(b1);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setSize(455, 600);
        setLocation(20, 40);
        setVisible(true);

        try {

            Socket socketClient = new Socket("localhost", 443);
            writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
        } catch (Exception e) {
        }

    }

    public void actionPerformed(ActionEvent ae) {
        String str = "Máy 1- Tuấn Thịnh :\n" + t1.getText();
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
        Máy_1 one = new Máy_1();
        Thread t1 = new Thread(one);
        t1.start();
    }
}
