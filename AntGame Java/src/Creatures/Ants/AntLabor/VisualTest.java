package Creatures.Ants.AntLabor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import Structures.SpriteManager;
import Structures.StorageBuilding;

public class VisualTest extends JPanel implements ActionListener {
    private final List<Ant> ants;
    private final List<Task> tasks;

    private final Timer timer;

    private static final int SCALE = 20;        // pixels per unit
    private static final int PANEL_SIZE = 400;  // window is 400×400 px

    public VisualTest() {
        setPreferredSize(new Dimension(PANEL_SIZE, PANEL_SIZE));
        setBackground(Color.WHITE);

// before:
// ants.add(new Ant(0,0,0)); …
// tasks.add(new Task(...));

// after:
        this.ants  = GameData.createAnts();
        this.tasks = GameData.createTasks();

// and remove the field initializers for ants/tasks

        // assign tasks
        for (Task t : tasks) {
            Ant a = PrioritizationMethod.DEFAULT.assignTask(t, ants);
            if (a != null) a.setTask(t);
        }

        // Swing timer: calls actionPerformed() every 200ms
        timer = new Timer(200, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // draw tasks as red squares
        g.setColor(Color.RED);
        for (Task t : tasks) {
            int px = (int)(t.getX() * SCALE);
            int py = (int)(t.getY() * SCALE);
            g.fillRect(px - 5, py - 5, 10, 10);
        }

        // draw ants as black circles
        g.setColor(Color.BLACK);
        for (Ant ant : ants) {
            int px = (int)(ant.getX() * SCALE);
            int py = (int)(ant.getY() * SCALE);
            g.fillOval(px - 5, py - 5, 10, 10);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // one “tick” per timer event
        float dt = 0.2f; // seconds per tick
        for (Ant ant : ants) ant.update(dt);
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ant Colony Visual Test");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(new VisualTest());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
