import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Laberinto extends JPanel implements KeyListener {
    private static final long serialVersionUID = 1L;
    private int[][] laberinto;
    private int jugadorX;
    private int jugadorY;
    private int metaX;
    private int metaY;
    private boolean ganador = false;
    private JButton reiniciarBtn;
    private long tiempoInicial;

    public Laberinto() {
        this.addKeyListener(this);
        this.setFocusable(true);
        generarLaberinto();
        
        reiniciarBtn = new JButton("Reiniciar");
        reiniciarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reiniciarJuego();
            }
        });
        this.add(reiniciarBtn);
        
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == reiniciarBtn) {
            reiniciarJuego();
        }
    }
    private void reiniciarJuego() {
        // Resetear la posición del jugador a su posición inicial
        jugadorX = 1;
        jugadorY = 1;
        ganador = false;
        tiempoInicial = System.currentTimeMillis(); // guardar el tiempo actual
        repaint();
        
        // Volver a dibujar el laberinto
        
    }
    private void generarLaberinto() {
        // Configuración del tamaño del laberinto y de las paredes
        int filas = 65;
        int columnas = 65;
        int grosorPared = 5;
        laberinto = new int[filas][columnas];

        // Generación de las paredes
        Random random = new Random();
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (i == 0 || j == 0 || i == filas - 1 || j == columnas - 1) {
                    // Paredes del borde exterior del laberinto
                    laberinto[i][j] = 1;
                } else if (i % 2 == 0 && j % 2 == 0) {
                    // Paredes internas
                    laberinto[i][j] = 1;
                    int direccion = random.nextInt(4);
                    switch (direccion) {
                    case 0: // Arriba
                        laberinto[i - 1][j] = 1;
                        break;
                    case 1: // Abajo
                        laberinto[i + 1][j] = 1;
                        break;
                    case 2: // Izquierda
                        laberinto[i][j - 1] = 1;
                        break;
                    case 3: // Derecha
                        laberinto[i][j + 1] = 1;
                        break;
                    }
                }
            }
        }
        // Agregar obstáculos adicionales en el laberinto
        laberinto[2][2] = 1;
        laberinto[2][3] = 1;
        laberinto[2][4] = 1;
        laberinto[2][5] = 1;
        laberinto[2][6] = 1;
        laberinto[3][6] = 1;
        laberinto[4][6] = 1;
        laberinto[5][6] = 1;
        laberinto[6][6] = 1;
        laberinto[6][5] = 1;
        laberinto[6][4] = 1;
        laberinto[6][3] = 1;
        laberinto[6][2] = 1;
        laberinto[5][2] = 1;
        laberinto[4][2] = 1;
        laberinto[3][2] = 1;
        laberinto[3][3] = 1;
        laberinto[3][5] = 1;
        laberinto[3][4] = 1;
        laberinto[4][4] = 1;
        laberinto[4][3] = 1;
        laberinto[5][3] = 1;
        laberinto[5][4] = 1;
        laberinto[6][9] = 1;
        laberinto[7][9] = 1;
        laberinto[8][9] = 1;
        laberinto[9][9] = 1;
        laberinto[9][8] = 1;
        laberinto[9][7] = 1;
        laberinto[9][6] = 1;
        laberinto[8][6] = 1;
        laberinto[7][6] = 1;
        laberinto[6][6] = 1;
        laberinto[14][18] = 1;
        laberinto[14][19] = 1;
        laberinto[14][20] = 1;
        laberinto[14][21] = 1;
        laberinto[14][22] = 1;
        laberinto[14][23] = 1;
        laberinto[15][23] = 1;
        laberinto[16][23] = 1;
        laberinto[17][23] = 1;
        laberinto[17][22] = 1;
        laberinto[17][21] = 1;
        laberinto[17][20] = 1;
        laberinto[17][19] = 1;
        laberinto[17][18] = 1;
        laberinto[16][18] = 1;
        laberinto[15][18] = 1;
        // Configuración de la posición inicial del jugador y la posición de la meta
        jugadorX = 1;
        jugadorY = 1;
        metaX = columnas - 2;
        metaY = filas - 2;
    }

 
    

    private void moverJugador(int dx, int dy) {
        if (laberinto[jugadorY + dy][jugadorX + dx] == 0) {
            jugadorX += dx;
            jugadorY += dy;
            if (jugadorX == metaX && jugadorY == metaY) {
                ganador = true;
            }
            repaint();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        reiniciarBtn.setBounds(10, getHeight() - 50, 100, 30);
        this.add(reiniciarBtn);
        // Dibujado del laberinto
        int anchoCelda = getWidth() / laberinto[0].length;
        int altoCelda = getHeight() / laberinto.length;
        for (int i = 0; i < laberinto.length; i++) {
            for (int j = 0; j < laberinto[i].length; j++) {
                int x = j * anchoCelda;
                int y = i * altoCelda;
                if (laberinto[i][j] == 1) {
                    g.setColor(Color.WHITE);
                    g.fillRect(x, y, anchoCelda, altoCelda);
                } else {
                    g.setColor(Color.BLACK);
                    g.fillRect(x, y, anchoCelda, altoCelda);
                }
            }
        }

        // Dibujado del jugador y la meta
        g.setColor(Color.red);
        g.fillRect(jugadorX * anchoCelda, jugadorY * altoCelda, anchoCelda, altoCelda);
        g.setColor(Color.GREEN);
        g.fillRect(metaX * anchoCelda, metaY * altoCelda, anchoCelda, altoCelda);

        // Mostrar un mensaje si el jugador ha ganado o perdido
        if (ganador) {
        	long tiempo = System.currentTimeMillis() - tiempoInicial;
            JOptionPane.showMessageDialog(this, "¡GANASTE! Tardaste " + tiempo/1000.0 + " segundos en llegar a la meta.");
            reiniciarJuego();
            System.exit(0);
        }}
    

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_UP:
            moverJugador(0, -1);
            break;
        case KeyEvent.VK_DOWN:
            moverJugador(0, 1);
            break;
        case KeyEvent.VK_LEFT:
            moverJugador(-1, 0);
            break;
        case KeyEvent.VK_RIGHT:
            moverJugador(1, 0);
            break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public static void main(String[] args) {
    	
        JFrame ventana = new JFrame("Laberinto");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(800, 800);
        Laberinto laberinto = new Laberinto();
        ventana.add(laberinto);
        ventana.setVisible(true);
    }
}