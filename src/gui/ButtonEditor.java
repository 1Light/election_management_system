package gui;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;

public class ButtonEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {
    private JButton button;
    private boolean clicked;
    private transient ActionListener actionListener;

    public ButtonEditor(ActionListener actionListener) {
        this.actionListener = actionListener;
        button = new JButton();
        button.addActionListener(this);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        fireEditingStopped();
        actionListener.actionPerformed(e);
    }

    @Override
    public Object getCellEditorValue() {
        return clicked ? "Button" : null;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        button.setText(value == null ? "Button" : value.toString());
        clicked = true;
        return button;
    }
}
