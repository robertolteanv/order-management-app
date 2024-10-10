package presentation;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
/**
 * This is the ButtonRenderer class, responsible for rendering buttons in a JTable.
 * It extends JButton and implements the TableCellRenderer interface.
 *
 *
 */
public class ButtonRenderer extends JButton implements TableCellRenderer {
    /**
     * This is the constructor of the ButtonRenderer class.
     * It sets the button to be opaque.
     */
    public ButtonRenderer() {
        setOpaque(true);
    }
    /**
     * This method is used to get the table cell renderer component.
     * It sets the text of the button and returns it as the renderer.
     *
     * @param table This is the table the button is in.
     * @param value This is the value of the cell.
     * @param isSelected This indicates whether the cell is selected.
     * @param hasFocus This indicates whether the cell has focus.
     * @param row This is the row of the cell.
     * @param column This is the column of the cell.
     * @return Component This returns the button as the renderer.
     */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setText((value == null) ? "" : value.toString());
        return this;
    }
}
