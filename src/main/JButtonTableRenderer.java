package main;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;


public class JButtonTableRenderer extends JPanel implements TableCellRenderer {
    @Override
    public Component getTableCellRendererComponent( final JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    	JButton val = (JButton)value;
    	val.setSize(100,100);
        return val;
    }


}
