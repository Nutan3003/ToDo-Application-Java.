import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class TaskComponents extends JPanel implements ActionListener {
    private JCheckBox checkbox;
    private JTextPane taskField;
    private JButton deleteButton;
    private JPanel parentPanel;

    public JTextPane getTaskFiled() {
        return taskField;
    }

    public TaskComponents(JPanel parentPanel) {
        this.parentPanel = parentPanel;

        taskField = new JTextPane();
        taskField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        taskField.setPreferredSize(CommonContants.TASKFIELD_SIZE);
        taskField.setContentType("text/html");
        taskField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e){
                taskField.setBackground(Color.WHITE);
            }
            public void focusLost(FocusEvent e){
                taskField.setBackground(null);
            }
        });

        checkbox = new JCheckBox();
        checkbox.setPreferredSize(CommonContants.CHECKBOX_SIZE);
        checkbox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        checkbox.addActionListener(this);

        deleteButton = new JButton("X");
        deleteButton.setPreferredSize(CommonContants.DELETE_BUTTON_SIZE);
        deleteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
        
        // Setting the action command for delete button
        deleteButton.setActionCommand("DELETE");

        deleteButton.addActionListener(this);
        
        add(checkbox);
        add(taskField);
        add(deleteButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle checkbox action
        if (checkbox.isSelected()) {
            String taskText = taskField.getText().replaceAll("<[^>]*>", "");
            taskField.setText("<html><s>" + taskText + "</s></html>");
        } else {
            String taskText = taskField.getText().replaceAll("<[^>]*>", "");
            taskField.setText(taskText);
        }

        // Handle delete button action
        if (e.getActionCommand().equalsIgnoreCase("DELETE")) {
            // Remove this TaskComponents panel from parentPanel
            parentPanel.remove(this);

            // Revalidate and repaint the parent panel to reflect changes
            parentPanel.revalidate();
            parentPanel.repaint();
        }
    }
}
