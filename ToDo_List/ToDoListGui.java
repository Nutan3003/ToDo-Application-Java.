import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import javax.swing.*;

public class ToDoListGui extends JFrame implements ActionListener {
    private JPanel taskPanel, taskComponenPanel;

    public ToDoListGui() {
        super("To Do List Application");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(CommonContants.GUI_SIZE);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        addGuiComponent();
    }

    private void addGuiComponent() {
        // Create the banner label
        JLabel bannerLabel = new JLabel("To Do List");
        bannerLabel.setFont(createFont("resources/LEMONMILK-Light.oft", 36f));
        bannerLabel.setBounds(
            (CommonContants.GUI_SIZE.width - bannerLabel.getPreferredSize().width) / 2, 15,
            CommonContants.BANNER_SIZE.width,
            CommonContants.BANNER_SIZE.height
        );

        // Initialize taskPanel and taskComponenPanel
        taskPanel = new JPanel();
        taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.Y_AXIS)); // Ensure taskPanel uses BoxLayout

        taskComponenPanel = new JPanel();
        taskComponenPanel.setLayout(new BoxLayout(taskComponenPanel, BoxLayout.Y_AXIS));

        // Add taskComponenPanel to taskPanel
        taskPanel.add(taskComponenPanel);

        // Create a JScrollPane to hold taskPanel
        JScrollPane scrollPane = new JScrollPane(taskPanel);
        scrollPane.setBounds(8, 70, CommonContants.TASKPANAL_SIZE.width, CommonContants.TASKPANAL_SIZE.height);
        scrollPane.setMaximumSize(CommonContants.TASKPANAL_SIZE);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Add task button
        JButton addTaskButton = new JButton("Add Task");
        addTaskButton.setBounds(-5, CommonContants.GUI_SIZE.height - 88,
                CommonContants.ADDTASK_BUTTON_SIZE.width, CommonContants.ADDTASK_BUTTON_SIZE.height);
        addTaskButton.setActionCommand("Add Task");  // Set action command
        addTaskButton.addActionListener(this);

        // Add components to the JFrame
        this.getContentPane().add(bannerLabel);
        this.getContentPane().add(scrollPane);
        this.getContentPane().add(addTaskButton);
    }

    private Font createFont(String resourcePath, float size) {
        try {
            // Use getResourceAsStream to load font
            InputStream fontStream = getClass().getClassLoader().getResourceAsStream(resourcePath);
            if (fontStream == null) {
                System.out.println("Font file not found: " + resourcePath);
                return null;
            }

            Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(size);
            return customFont;
        } catch (Exception e) {
            System.out.println("Error loading font: " + e);
            return null;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equalsIgnoreCase("Add Task")) {
            TaskComponents taskComponents = new TaskComponents(taskComponenPanel);
            taskComponenPanel.add(taskComponents);

            if(taskComponenPanel.getComponentCount()>1){
                TaskComponents previousTask =(TaskComponents)taskComponenPanel.getComponent(
           taskComponents.getComponentCount()-2);
           previousTask.getTaskFiled().setBackground(null);
                
            }
            taskComponents.getTaskFiled().requestFocus();
            repaint();
            revalidate();
        }
    }
}
