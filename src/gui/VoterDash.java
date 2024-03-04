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

public class VoterDash {
    JFrame jf;
    JLabel jl, jl2, jl3, jl4;
    ImageIcon ii;
    JButton jb, jb2, jb3, jb4;

    public VoterDash () {
        jf = new JFrame("Voter Dashboard");

        Font font2 = new Font("TIMES NEW ROMAN", Font.BOLD, 16);

        Color var = new Color(255, 128, 0);
        Color var2 = new Color(0,128,255);

        ii = Resize("C:\\Users\\Nas\\IdeaProjects\\SpicyS\\src\\Images\\voterdash.jpeg", 435, 245);
        jl4 = new JLabel(ii);
        jl4.setBounds(75, 20, 435, 245);

        jl4.setBorder(new LineBorder(var2, 2));

        // Creating labels
        jl = new JLabel("Profile");
        jl.setBounds(50, 400, 150, 25);
        jl.setFont(font2);
        jl.setBackground(var2);
        jl.setForeground(Color.WHITE);
        jl2 = new JLabel("Candidates");
        jl2.setBounds(240, 400, 150, 25);
        jl2.setFont(font2);
        jl2.setBackground(var2);
        jl2.setForeground(Color.WHITE);
        jl3 = new JLabel("Results");
        jl3.setBounds(440, 400, 150, 25);
        jl3.setFont(font2);
        jl3.setBackground(var2);
        jl3.setForeground(Color.WHITE);

        // Adding the button icons
        jb = new JButton(Resize("C:\\Users\\Nas\\IdeaProjects\\SpicyS\\src\\Images\\profile.jpg", 100, 100));
        jb.setBounds(50, 300, 100, 100);
        jb2 = new JButton(Resize("C:\\Users\\Nas\\IdeaProjects\\SpicyS\\src\\Images\\candidates.jpeg",100, 100));
        jb2.setBounds(240, 300, 100, 100);
        jb3 = new JButton(Resize("C:\\Users\\Nas\\IdeaProjects\\SpicyS\\src\\Images\\results.jpeg",100, 100));
        jb3.setBounds(440, 300, 100, 100);

        // Setting events
        jb.addActionListener(e -> {
            Voter loggedInVoter = (Voter) Linker.getLoggedInUser();
            if(loggedInVoter != null) {
                new VoterPro(loggedInVoter);
                jf.dispose();
            }
        });
        jb2.addActionListener(e -> {
            new VoterCandPos();
            jf.dispose();
        });
        jb3.addActionListener(e -> {
            new ResultDash();
            jf.dispose();
        });

        jb4 = new JButton("Back");
        jb4.setBounds(225, 450, 150, 25);
        jb4.setForeground(Color.WHITE);
        jb4.setFont(font2);
        jb4.setBackground(var2);

        jb4.addActionListener(e-> {
            new Main();
            jf.dispose();
        });

        jf.add(jl);
        jf.add(jl2);
        jf.add(jl3);
        jf.add(jb);
        jf.add(jb2);
        jf.add(jl4);
        jf.add(jb3);
        jf.add(jb4);

        // Finalizing input
        jf.setSize(600, 550);
        jf.getContentPane().setBackground(var);
        jf.setLocationRelativeTo(null);
        jf.setLayout(null);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
