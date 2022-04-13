import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class test
{
    private MyButton customButton;
    private MyButton customButton2;

    private void displayGUI()
    {
        JFrame frame = new JFrame("Custom Button Example");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        customButton = new MyButton();
        customButton2 = new MyButton();
        customButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                //MyButton.isClicked = true;
                customButton.repaint();
            }
        });

        frame.getContentPane().add(customButton, BorderLayout.CENTER);
        frame.getContentPane().add(customButton2, BorderLayout.SOUTH);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main(String... args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new test().displayGUI();
            }
        });
    }
}
/*
class MyButton extends JButton
{
    public static boolean isClicked = false;

    public Dimension getPreferredSize()
    {
        return (new Dimension(100, 40));
    }

    public void paintComponent(Graphics g)
    {
        if (!isClicked)
            super.paintComponent(g);
        else
        {
            g.setColor(Color.BLUE);
            g.fillOval(getHorizontalAlignment(), getVerticalAlignment(), getWidth(), getHeight());
        }
    }
}
*/
