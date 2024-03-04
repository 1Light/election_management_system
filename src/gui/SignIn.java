package gui;

import database.Linker;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SignIn {

    JFrame jf;
    JButton jb, jb2;
    JPanel jp;
    ImageIcon ii;
    JLabel jt, lb, lb2;
    JLabel ja, jl,  jl2;
    JTextField jt2, jt3;

    public SignIn(String userType) {

        Color var = new Color(255, 128, 0);
        Color var2 = new Color(0,128,255);
        Color var3 = new Color(200,200,200);

        jf = new JFrame("Login Page");

        jp = new JPanel();

        /*// Creating text field
        jt = new JLabel("Welcome Back!!!");
        jt.setBounds(0,0,600, 50);
        jt.setBackground(var);
        jt.setForeground(Color.BLACK);

        // Setting the font size on the text field
        Font font = new Font("TIMES NEW ROMAN", Font.BOLD, 26);
        jt.setFont(font);
        jt.setHorizontalAlignment(JTextField.CENTER);*/

        // Creating a label and inserting an image;
        ii = Resize("C:\\Users\\Nas\\IdeaProjects\\SpicyS\\src\\Images\\signin.jpeg", 370, 205);
        lb = new JLabel(ii);
        lb.setBounds(110, 10, 370, 205);
        lb.setBorder(new LineBorder(var2, 2));

        // Creating the opening text area
        ja = new JLabel("Let's secure your spot! Please drop your login details here.");
        ja.setBounds(72,230,600, 25);

        // Setting the font of the text area
        Font xy = new Font("TIMES NEW ROMAN", Font.BOLD, 18);
        ja.setFont(xy);

        // Creating the name and password text fields
        jt2 = new JTextField();
        //setEditableAndAddFocusListener(jt2,"Enter Email");
        //jt2.setBounds(20,60,350, 25);
        jt2.setForeground(Color.BLACK);
        jt3 = new JTextField();
        //setEditableAndAddFocusListener(jt3,"Enter Password");
        //jt3.setBounds(20,125,350, 25);
        jt3.setForeground(Color.BLACK);

        // Creating the name and password labels
        jl = new JLabel("Email");
        jl.setBounds(20, 10, 300, 25);
        jl.setForeground(Color.BLACK);

        jl2 = new JLabel("Password");
        jl2.setBounds(20, 70, 300, 25);
        jl2.setForeground(Color.BLACK);

        jp.setBounds(129, 270, 337, 150);
        jp.setBackground(var3);

// Adjust the position of text fields within the panel
        jt2.setBounds(20, 40, 300, 25);
        jt3.setBounds(20, 100, 300, 25);

        jp.add(jl);
        jp.add(jl2);
        jp.add(jt2);
        jp.add(jt3);
        jf.add(jp);

        jp.setLayout(null);

        // Creating a button
        jb = new JButton("Login");
        jb.setBounds(129, 450, 150, 25);
        jb.addActionListener(e -> {
                String email = jt2.getText();
                String password = jt3.getText();

                if (Linker.isValidLogin(userType, email, password) && "Admin".equals(userType)) {
                    // Admin login successful
                new AdminDash();
                jf.dispose();
                }
                else if (Linker.isValidLogin(userType, email, password) && "Voter".equals(userType)) {
                    //JOptionPane.showMessageDialog(jf, "Login successful!");
                    new VoterDash();
                    jf.dispose();
                } else {
                    JOptionPane.showMessageDialog(jf, "Invalid credentials. Please try again.");
                }
        });
        jb.setBackground(var2);
        jb.setForeground(Color.WHITE);
        Font font2 = new Font("TIMES NEW ROMAN", Font.BOLD, 16);
        jl.setFont(font2);
        jl2.setFont(font2);
        jb.setFont(font2);

        //Back button
        jb2 = new JButton("Back");
        jb2.setBounds(316, 450, 150, 25);
        jb2.setForeground(Color.WHITE);
        jb2.setFont(font2);
        jb2.setBackground(var2);
        jb2.addActionListener(e -> {
            if(userType.equals("Voter")){
                new SignUpV();
                jf.dispose();
            }else if(userType.equals("Candidate")){
                new SignUpC();
                jf.dispose();
            }
            else {
                new Main();
                jf.dispose();
        }});
        // Adding components to the frame
        //jf.add(jt);
        jf.add(ja);
        jf.add(lb);
        jf.add(jb);
        jf.add(jb2);

        // Finalizing input
        jf.setSize(600, 550);
        jf.getContentPane().setBackground(var);
        jf.setLocationRelativeTo(null);
        jf.setResizable(false);
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
    private void setEditableAndAddFocusListener(JTextField textField,String Defualt) {
        // Set the text field to be editable
        textField.setEditable(true);

        // Add a FocusListener to clear the default text when focused
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(Defualt)) {
                    textField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Optional: You can add logic for when focus is lost
            }
        });
    }
    public static void main (String args []){
        }
    }