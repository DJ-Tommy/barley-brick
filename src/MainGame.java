import puzzler.Coord;
import puzzler.MatrixGame;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//import static java.awt.Event.*;

public class MainGame extends JFrame

{
    private JPanel gamePanel;
    private JPanel menuPanel;
    private Coord coord;
    private MatrixGame mm;


    private final String TITLE = "15 puzler's";
    private final static int ROWS = 4;
    private final static int COLS = 4;
    private final int SIZE_BLOCK = 110;

    public static void main(String[] args)
    {
        new MainGame(); //Инициализируем конструктор

    }

    public MainGame () //Конструктор класса, возвращает окно
    {
        MatrixGame.setSize(new Coord(COLS, ROWS));
        new MatrixGame();
        MatrixGame.mixStart();
        initGamePanel();
        initMenuPanel();
        initFrame();

    }

    private void initGamePanel ()
    {
        gamePanel = new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Font font = new Font("Tahoma", Font.BOLD, SIZE_BLOCK / 3);
                g.setFont(font);
                for (int y = 0; y < ROWS; y++) {
                    for (int x = 0; x < COLS; x++) {
                        if (x == 0) {
                            g.drawLine(0, (y + 1) * SIZE_BLOCK, COLS * SIZE_BLOCK, (y + 1) * SIZE_BLOCK);
                        }

                        if (y == 0) {
                            g.drawLine((x + 1) * SIZE_BLOCK, 0, (x + 1) * SIZE_BLOCK, ROWS * SIZE_BLOCK);
                        }
                        int ii = MatrixGame.getMatrix(new Coord(x, y));
                        String iii = String.valueOf(ii);


                        if (MatrixGame.getMatrix(new Coord(x, y)) != 0) {
                            g.drawString(iii, x * SIZE_BLOCK + SIZE_BLOCK/ 5 * 2, y * SIZE_BLOCK + SIZE_BLOCK / 5 * 3);
                            g.setColor(new Color(255, 0, 0));
                            g.drawRoundRect(x * SIZE_BLOCK + 4, y * SIZE_BLOCK + 4, SIZE_BLOCK - 8, SIZE_BLOCK - 8, SIZE_BLOCK * 3 / 5, SIZE_BLOCK * 3 / 5);
                            g.setColor(new Color(0, 0, 0));
                        }
                    }

                }

            }



        };
        gamePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x_mouse = e.getX() / SIZE_BLOCK;
                int y_mouse = e.getY() / SIZE_BLOCK;
                Coord coord = new Coord(x_mouse, y_mouse);
                if (e.getButton() == MouseEvent.BUTTON1) { // левая кнопка мыши
                    MatrixGame.moveMouse(coord, true);
                }
                if (e.getButton() == MouseEvent.BUTTON3) { // правая кнока мыши
                    MatrixGame.mixStart();
                }
                //if (e.getButton() == MouseEvent.BUTTON2) // средняя кнопка мыши
                    //game.start(); // перезапускаем игру
                //label.setText(getessage());
                gamePanel.repaint();
                menuPanel.repaint();
            }
        });

        gamePanel.setPreferredSize(new Dimension(COLS * SIZE_BLOCK, ROWS * SIZE_BLOCK));
        gamePanel.setBackground(Color.gray);
        gamePanel.setBorder(new BevelBorder(0));
        add(gamePanel, BorderLayout.CENTER, BevelBorder.RAISED);


    }

    private void initMenuPanel() {
        menuPanel = new JPanel()
        {
            @Override
            protected void paintComponent(Graphics gm) {
                super.paintComponent(gm);
                Font font = new Font("Tahoma", Font.BOLD, SIZE_BLOCK / 5);
                gm.setFont(font);
                gm.drawString("Количество ходов: " + String.valueOf(MatrixGame.getCount_moves()), SIZE_BLOCK / 6, SIZE_BLOCK / 3);
                if (MatrixGame.gameOver())
                gm.drawString("УРА !!! ПОБЕДА !!! ", SIZE_BLOCK / 6, SIZE_BLOCK / 3 * 2);
            }

        };
        menuPanel.setPreferredSize(new Dimension(COLS * SIZE_BLOCK, 1 * SIZE_BLOCK));
        //menuPanel.setBackground(Color.green);
        menuPanel.setBorder(new BevelBorder(0));
        add(menuPanel, BorderLayout.NORTH);
    }

    private void initFrame ()
    {
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e1) {

            }

            @Override
            public void keyPressed(KeyEvent e2) {

                if (e2.getKeyCode() == KeyEvent.VK_DOWN) {
                    MatrixGame.moveKeyboard(6, true);
                }

                if (e2.getKeyCode() == KeyEvent.VK_UP) {
                    MatrixGame.moveKeyboard(0, true);
                }

                if (e2.getKeyCode() == KeyEvent.VK_LEFT) {
                    MatrixGame.moveKeyboard(9, true);
                }

                if (e2.getKeyCode() == KeyEvent.VK_RIGHT) {
                    MatrixGame.moveKeyboard(3, true);
                }
                repaint();

            }

            @Override
            public void keyReleased(KeyEvent e2) {

            }
        });
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle(TITLE);
        setResizable(false);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
    }



}
