package forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Main {
    private JPanel panelMain;
    private JButton button1;
    private JPanel panelTitle;
    private JPanel panelCenter;
    private JLabel labelTime;
    private JLabel labelVidas;
    private JLabel labelPuntuacion;
    private JLabel labelTrash;
    private JLabel labelPaper;
    private JButton buttonPause;
    private int puntuacion = 0;
    private int segundos = 0;
    private int vidas = 3;
    public Main() {
        panelMain.setPreferredSize(new Dimension(800,600));
        panelMain.setSize(new Dimension(800,600));
        panelMain.setLayout(null);

        showPanelTitle();
        showPanelCenter();

        Timer timer = new Timer(1000, new TimerActionListener());
        timer.start();

        buttonPause.addMouseListener(new ButtonPauseListener(timer));
        panelMain.addKeyListener(new PanelMainListener());
        panelMain.setFocusable(true);
        panelMain.requestFocusInWindow();

    }

    private class PanelMainListener extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);

            int x = labelTrash.getX();

            switch (e.getKeyCode()){
                case KeyEvent.VK_RIGHT: x += 15; break;
                case KeyEvent.VK_LEFT: x -= 15; break;
            }
            if (x>=0 && x<= panelMain.getWidth() - labelTrash.getWidth()){
                labelTrash.setLocation(x,labelTrash.getY());
            }
        }
    }

    private class ButtonPauseListener extends MouseAdapter{
        Timer timer;
        public ButtonPauseListener(Timer timer) {
            this.timer = timer;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            if (buttonPause.getText().equals("Pausa")){
                timer.stop();
                buttonPause.setText("Reanudar");
            }else {
                timer.start();
                buttonPause.setText("Pausa");
                panelMain.requestFocusInWindow();
            }
        }
    }

    public class TimerActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            segundos++;
            labelTime.setText(segundos + " segundos");
        }
    }

    private void showPanelTitle() {
        panelTitle = new JPanel();
        panelTitle.setLocation(0,0);
        panelTitle.setSize(panelMain.getWidth(),50);
        panelTitle.setBackground(Color.GRAY);
        panelMain.add(panelTitle);

        labelTime = new JLabel();
        labelTime.setText("0 segundos");
        panelTitle.add(labelTime);

        labelPuntuacion = new JLabel();
        labelPuntuacion.setText(", 0 puntos");
        panelTitle.add(labelPuntuacion);

        buttonPause = new JButton();
        buttonPause.setText("Pausa");
        buttonPause.setFocusPainted(false);
        buttonPause.setBackground(new Color(25,18,50));
        buttonPause.setForeground(Color.WHITE);
        panelTitle.add(buttonPause);

        labelVidas = new JLabel();
        labelVidas.setText("3 vidas");
        panelTitle.add(labelVidas);
    }

    private void showPanelCenter() {
        panelCenter = new JPanel( );
        panelCenter.setLayout(null);
        panelCenter.setLocation(0, panelTitle.getHeight());
        panelCenter.setSize(panelMain.getWidth(), panelMain.getHeight() - panelTitle.getHeight());
        panelCenter.setBackground(Color.LIGHT_GRAY);
        panelMain.add(panelCenter);

        showTrash();
        showPaper();
    }

    private void showPaper() {
        Random random = new Random();
        labelPaper = new JLabel();
        labelPaper.setSize(40, 40);
        ImageIcon imageIcon = new ImageIcon("src/images/paper.png");
        Icon icon = new ImageIcon(
                imageIcon.getImage().getScaledInstance(labelPaper.getWidth(), labelPaper.getHeight(), Image.SCALE_DEFAULT)
        );
        labelPaper.setIcon(icon);

        int X = panelCenter.getWidth() - labelPaper.getWidth();
        int randomX = random.nextInt(X + 1);
        labelPaper.setLocation(randomX, 0);

        System.out.println(panelCenter.getHeight());
        panelCenter.add(labelPaper);

        Timer timer = new Timer(70, new TimerActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                int Y = labelPaper.getY() + 15;
                labelPaper.setLocation(labelPaper.getX(), Y);

                if (labelPaper.getBounds().intersects(labelTrash.getBounds())){
                    puntuacion +=10;
                    labelPuntuacion.setText(puntuacion + " puntos");
                    labelPaper.setLocation(random.nextInt(X + 1),0);
                }

                if (labelPaper.getY() >= panelCenter.getHeight()){
                    vidas --;
                    labelVidas.setText(vidas + " vidas");
                    labelPaper.setLocation(random.nextInt(X + 1),0);
                }
            }
        });
        timer.start();
    }

    private void showTrash() {
        labelTrash = new JLabel( );
        labelTrash.setSize(90,100);
        ImageIcon imageIcon = new ImageIcon("src/images/trash.png");
        Icon icon = new ImageIcon(
                imageIcon.getImage( ).getScaledInstance(labelTrash.getWidth(), labelTrash.getHeight(), Image.SCALE_DEFAULT)
        );
        labelTrash.setIcon(icon);
        labelTrash.setLocation(panelCenter.getWidth()/2 - labelTrash.getWidth()/2, panelCenter.getHeight()-labelTrash.getHeight());
        System.out.println(panelCenter.getHeight());
        panelCenter.add(labelTrash);
    }

    private static class FrameWindowListener extends WindowAdapter{
        JFrame frame;
        public FrameWindowListener(JFrame frame) {
            this.frame = frame;
        }

        @Override
        public void windowClosing(WindowEvent e) {
            super.windowClosing(e);

            int confirmado = JOptionPane.showConfirmDialog(null,
                    "¿Estas segur de que vols sortir?",
                    "Missatge",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (confirmado == JOptionPane.YES_OPTION){
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            }else {
                frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Main");
        frame.setContentPane(new Main().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        Toolkit pantalla = Toolkit.getDefaultToolkit();
        Image icono = pantalla.getImage("src/images/politecnics.png");
        frame.setIconImage(icono);

        frame.addWindowListener(new FrameWindowListener(frame));
    }
}
