package gui;

import database.Linker;
import database.Voter;
import java.io.*;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.border.LineBorder;

public class AdminDash {
        JFrame jf;
        ImageIcon ii;
        JLabel jl, jl2, jl3, jl4;
        JButton jb, jb2, jb3, jb4;

        public AdminDash () {

            Color var = new Color(255, 128, 0);
            Color var2 = new Color(0,128,255);

            Font font2 = new Font("TIMES NEW ROMAN", Font.BOLD, 16);

            jf = new JFrame("Admin Dashboard");

            // Creating labels
            jl = new JLabel("Voters");
            jl.setBounds(50, 400, 50, 25);
            jl.setForeground(Color.WHITE);
            jl2 = new JLabel("Candidates");
            jl2.setBounds(240, 400, 100, 25);
            jl2.setForeground(Color.WHITE);
            jl3 = new JLabel("Results");
            jl3.setBounds(440, 400, 50, 25);
            jl3.setForeground(Color.WHITE);

            ii = Resize("C:\\Users\\Nas\\IdeaProjects\\SpicyS\\src\\Images\\admindash.jpeg", 435, 245);
            jl4 = new JLabel(ii);
            jl4.setBounds(75, 20, 435, 245);

            jl4.setBorder(new LineBorder(var2, 2));

            // Adding the button icons
            jb = new JButton(Resize("C:\\Users\\Nas\\IdeaProjects\\SpicyS\\src\\Images\\profile.jpg", 100, 100));
            //jb.setBounds(50, 50, 100,100);
            jb2 = new JButton(Resize("C:\\Users\\Nas\\IdeaProjects\\SpicyS\\src\\Images\\candidates.jpeg",100, 100));
            //jb2.setBounds(200, 50, 100,100);
            jb3 = new JButton(Resize("C:\\Users\\Nas\\IdeaProjects\\SpicyS\\src\\Images\\results.jpeg",100, 100));
            //jb3.setBounds(310, 50, 100,100);

            // Setting the bounds for the icons
            jb.setBounds(50, 300, 100, 100);
            jb2.setBounds(240, 300, 100, 100);
            jb3.setBounds(440, 300, 100, 100);

            jb4 = new JButton("Back");
            jb4.setBounds(225, 450, 150, 25);
            jb4.setForeground(Color.WHITE);
            jb4.setBackground(var2);

            jb4.addActionListener(e-> {
                new Main();
                jf.dispose();
            });

            // Setting events
            jb.addActionListener(e -> {
                new AdminVot();
                jf.dispose();
            });
            jb2.addActionListener(e -> {
                new AdminCan();
                jf.dispose();
            });
            jb3.addActionListener(e -> {
                new ResultDash();
            });

            jl.setFont(font2);
            jl2.setFont(font2);
            jb4.setFont(font2);
            jl3.setFont(font2);

            jf.add(jl);
            jf.add(jl2);
            jf.add(jl3);
            jf.add(jb);
            jf.add(jb2);
            jf.add(jb3);
            jf.add(jb4);
            jf.add(jl4);

            // Finalizing input
            jf.setSize(600, 550);
            jf.getContentPane().setBackground(var);
            jf.setLocationRelativeTo(null);
            jf.setLayout(null);
            jf.setVisible(true);
        }
        // Method for resizing images
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
        public static void main (String [] args) {
        }
}
