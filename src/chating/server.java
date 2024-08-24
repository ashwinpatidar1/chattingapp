package chating;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.text.*;
import java.io.*;
public class server implements ActionListener  {

    JTextField text;
    JPanel a1;
    static Box vertical = Box.createVerticalBox();
    static JFrame f = new JFrame();
    static DataOutputStream dout;
    server(){
        f.setLayout(null);

        JPanel p1 = new JPanel();
        p1.setBackground(new Color(00, 205 , 255));
        p1.setBounds(0 ,0,350 , 50);
        p1.setLayout(null);
        f.add(p1);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
        Image i2 = i1.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT );
        ImageIcon i3 = new ImageIcon(i2);
        JLabel back = new JLabel(i3);
        back.setBounds(10, 11 ,25, 25);
        p1.add(back);
        back.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent ae){
                System.exit(0);
            }
        });

        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/munna.png"));
        Image i5 = i4.getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT );
        ImageIcon i6 = new ImageIcon(i5);
        JLabel profile = new JLabel(i6);
        profile.setBounds(40, 5 ,40, 40);
        p1.add(profile);

        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
        Image i8 = i7.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT );
        ImageIcon i9 = new ImageIcon(i8);
        JLabel video = new JLabel(i9);
        video.setBounds(270, 15,20, 20);
        p1.add(video);

        ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
        Image i11 = i10.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT );
        ImageIcon i12 = new ImageIcon(i11);
        JLabel phone = new JLabel(i12);
        phone.setBounds(240, 15,20, 20);
        p1.add(phone);

        ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
        Image i14 = i13.getImage().getScaledInstance(10,20,Image.SCALE_DEFAULT );
        ImageIcon i15 = new ImageIcon(i14);
        JLabel icon = new JLabel(i15);
        icon.setBounds(310, 15,10, 20);
        p1.add(icon);

        JLabel name = new JLabel("munna Tripathi");
        name.setBounds(90 , 3 , 100, 30);
        name.setForeground(Color.WHITE);
        p1.add(name);

        JLabel an = new JLabel("Active now");
        an.setBounds(90 , 15, 100, 30);
        an.setForeground(Color.WHITE);
        p1.add(an);

        a1 = new JPanel();
        a1.setBounds(5,55,340,545);
        a1.setBackground(new Color(255 , 247 , 238));
        f.add(a1);

        text = new JTextField();
        text.setBounds(5,615 , 270,25);
        text.setFont(new Font("SAN_SERIF" , Font.PLAIN , 16));
        f.add(text);

        JButton send = new JButton("send");
        send.setBounds(280, 615 ,65, 25);
        send.setBackground(new Color(00, 205 , 255));
        send.setForeground(new Color(255 , 247 , 238));
        send.addActionListener(this);
        text.setFont(new Font("SAN_SERIF" , Font.PLAIN , 16));
        f.add(send);

        f.setSize(350 , 650);
        f.setLocation(50 , 50);
        f.setUndecorated(true);
        f.getContentPane().setBackground(new Color(255 , 247 , 238) );

        f.setVisible(true);

    }
    public void actionPerformed(ActionEvent ae){
        try {
            String out = text.getText();


            JPanel p2 = formatepanel(out);


            a1.setLayout(new BorderLayout());

            JPanel right = new JPanel(new BorderLayout());
            right.add(p2, BorderLayout.LINE_END);
            vertical.add(right);
            vertical.add(Box.createVerticalStrut(13));
            a1.add(vertical, BorderLayout.PAGE_START);

            dout.writeUTF(out);

            text.setText("");

            f.repaint();
            f.invalidate();
            f.validate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static JPanel formatepanel(String out){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel , BoxLayout.Y_AXIS));
        JLabel output = new JLabel(out);
        output.setFont(new Font("Tahoma",Font.PLAIN,20));
        output.setBackground(new Color(00, 205 , 255));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15,15,15,45));
        output.setForeground(new Color(255 , 247 , 238));
        panel.add(output);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH : mm");

        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime()));

        panel.add(time);

        return panel;
    }

    public static JPanel formaterepanel(String out) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel output = new JLabel(out);
        output.setFont(new Font("Tahoma", Font.PLAIN, 20));
        output.setBackground(new Color(169,169,169));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15, 15, 15, 45));
        output.setForeground(new Color(255, 247, 238));
        panel.add(output);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH : mm");

        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime()));

        panel.add(time);

        return panel;
    }

     public static void main(String[] args){
         new server();

         try{
             ServerSocket skt = new ServerSocket(5001);
             while(true){
                 Socket s  = skt.accept();
                 DataInputStream din = new DataInputStream(s.getInputStream());
                 dout = new DataOutputStream(s.getOutputStream());

                 while(true){
                     String msg = din.readUTF();
                     JPanel panel = formaterepanel(msg);

                     JPanel left = new JPanel(new BorderLayout());
                     left.add(panel , BorderLayout.LINE_START);
                     vertical.add(left);
                     f.validate();
                 }
             }
         } catch(Exception e){
             e.printStackTrace();
         }
     }
}
