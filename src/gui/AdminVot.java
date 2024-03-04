package gui;

import database.Voter;
import database.Linker;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AdminVot extends JFrame {
    private DefaultTableModel tableModel;
    private JTable voterTable;
    JButton jb3;

    public AdminVot() {
        super("Voter Information Table");
        Color var = new Color(255, 128, 0);
        Color var2 = new Color(0,128,255);

        Font font3 = new Font("TIMES NEW ROMAN", Font.BOLD, 16);

        // Set up the GUI
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        jb3 = new JButton("Back");
        jb3.setFont(font3);
        jb3.setBackground(var2);
        jb3.setForeground(Color.WHITE);
        jb3.setBounds(300, 500, 100, 25);
        jb3.addActionListener(e -> {
            new AdminDash();
            dispose();
        });
        add(jb3);

        // Create table model with column names
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Full Name");
        tableModel.addColumn("Age");
        tableModel.addColumn("Phone Number");
        tableModel.addColumn("Email");
        tableModel.addColumn("Vote Status");
        tableModel.addColumn("Account Status");
        tableModel.addColumn("ID");
        tableModel.addColumn("Remove"); // New column for Remove button

        // Create JTable with the table model
        voterTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(voterTable);

        int height = 25;
        voterTable.setRowHeight(height);

        // Set button renderers and editors for the new columns
        voterTable.getColumnModel().getColumn(7).setCellRenderer(new ButtonRenderer());
        voterTable.getColumnModel().getColumn(7).setCellEditor(new ButtonEditor(new ButtonClickListener()));

        scrollPane.getViewport().setBackground(var);
        voterTable.setBackground(var2);

        // Add components to the frame
        add(scrollPane);

        // Load voter information from the database
        loadVoterInfo();

        // Display the frame
        setVisible(true);
    }

    private void loadVoterInfo() {
        Font font2 = new Font("TIMES NEW ROMAN", Font.BOLD, 14);

        // Set the custom renderer for the entire row
        voterTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component renderer = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Set the font for the entire row
                renderer.setFont(font2);
                renderer.setForeground(Color.WHITE);

                return renderer;
            }
        });

        // Set the font for the column names (headers)
        JTableHeader header = voterTable.getTableHeader();
        header.setFont(font2);

        // Retrieve voter information from the Linker class
        for (Voter voter : Linker.getVoters()) {
            Object[] rowData = new Object[]{
                    voter.getFullName(),
                    voter.getAge(),
                    voter.getPhoneNumber(),
                    voter.getEmail(),
                    voter.getVoteStatus(),
                    voter.getAccountStatus(),
                    voter.getRandomId(),
                    "Remove"
            };

            tableModel.addRow(rowData);
        }
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Handle button clicks here
            if ("Remove".equals(e.getActionCommand())) {
                int selectedRow = voterTable.getSelectedRow();
                if (selectedRow != -1) {
                    // Perform removal action
                    String voterID = (String) tableModel.getValueAt(selectedRow, 6);
                    try {
                        Linker.removeVoterByID(voterID);
                        tableModel.removeRow(selectedRow);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
    }
}}