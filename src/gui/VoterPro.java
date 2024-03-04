package gui;

import database.Voter;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class VoterPro {
    JFrame jf;
    JTextField jt1, jt2, jt4, yu;
    JLabel jl6, jl, jl2, jl3, jl4, jl5;
    ImageIcon ii;
    JButton jb4;
    public VoterPro(Voter user) {
        jf = new JFrame("Profile Page");

        Font font2 = new Font("TIMES NEW ROMAN", Font.BOLD, 16);

        Color var = new Color(255, 128, 0);
        Color var2 = new Color(0,128,255);

        // Creating text field
        jl6 = new JLabel("Profile");
        jl6.setBounds(0, 0, 600, 50);
        jl6.setBackground(Color.BLUE);
        jl6.setForeground(Color.WHITE);

        // Setting the font size on the text field
        Font font = new Font("TIMES NEW ROMAN", Font.BOLD, 30);
        jl6.setFont(font);
        jl6.setHorizontalAlignment(JLabel.CENTER);

        // Creating labels
        jl = new JLabel("Full Name");
        jl.setBounds(80, 350, 100, 25);
        jl.setBackground(var2);
        jl.setFont(font2);
        jl.setForeground(Color.WHITE);

        jt1 = new JTextField(user.getFullName());
        jt1.setBounds(197, 350, 100, 25);
        jt1.setBackground(Color.WHITE);
        jt1.setForeground(Color.BLACK);
        jt1.setEditable(false);
        jt1.setFont(font2);

        jl2 = new JLabel("Phone Number");
        jl2.setBounds(80, 385, 130, 25);
        jl2.setBackground(var2);
        jl2.setFont(font2);
        jl2.setForeground(Color.WHITE);

        jt2 = new JTextField(user.getPhoneNumber());
        jt2.setBounds(197, 385, 100, 25);
        jt2.setBackground(Color.WHITE);
        jt2.setForeground(Color.BLACK);
        jt2.setEditable(false);
        jt2.setFont(font2);

        jb4 = new JButton("Back");
        jb4.setBounds(415, 465, 100, 25);
        jb4.setForeground(Color.WHITE);
        jb4.setFont(font2);
        jb4.setBackground(var2);

        jb4.addActionListener(e-> {
            new VoterDash();
            jf.dispose();
        });

        ii = Resize("C:\\Users\\Nas\\IdeaProjects\\SpicyS\\src\\Images\\voterpro.jpeg", 455, 270);
        jl3 = new JLabel(ii);
        jl3.setBounds(70, 50, 455, 270);

        jl3.setBorder(new LineBorder(var2, 4));

        jl4 = new JLabel("Age");
        jl4.setFont(font2);
        jl4.setBackground(var2);
        jl4.setForeground(Color.WHITE);
        jl4.setBounds(353, 350, 70, 25);

        // Creating the vote viewing label
        yu = new JTextField(user.getAge());
        yu.setEditable(false);
        yu.setBounds(415, 350, 100, 25);
        yu.setBackground(Color.WHITE);
        yu.setForeground(Color.BLACK);
        yu.setFont(font2);

        jl5 = new JLabel("Email");
        jl5.setFont(font2);
        jl5.setBackground(var2);
        jl5.setForeground(Color.WHITE);
        jl5.setBounds(80, 420, 100, 25);

        jt4 = new JTextField(user.getEmail());
        jt4.setBounds(197, 420, 100, 25);
        jt4.setBackground(Color.WHITE);
        jt4.setForeground(Color.BLACK);
        jt4.setEditable(false);
        jt4.setFont(font2);

        // Adding components to the frame
        jf.add(jl);
        jf.add(jl3);
        jf.add(jl4);
        jf.add(jl2);
        jf.add(jl6);
        jf.add(jl5);
        jf.add(jt1);
        jf.add(jt2);
        jf.add(jt4);
        jf.add(yu);
        jf.add(jb4);

        jf.setSize(600, 550);
        jf.setLocationRelativeTo(null);
        jf.getContentPane().setBackground(var);
        jf.setLayout(null);
        jf.setVisible(true);
    }
    public ImageIcon Resize(String imagePath, int width, int height) {
        try {
            // Load the original image
            BufferedImage originalImage = ImageIO.read(new File(imagePath));

            // Resize the image while maintaining its aspect ratio
            Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);

            // Create a button with the resized image
            return new ImageIcon(resizedImage);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    public static void main(String args[]){
    }
}
