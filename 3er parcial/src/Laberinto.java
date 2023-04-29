import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Laberinto extends JPanel implements KeyListener {
    private static final long serialVersionUID = 1L;
    private int[][] laberinto;
    private int jugadorX;
    private int jugadorY;
    private int metaX;
    private int metaY;
    private boolean ganador = false;

    public Laberinto() {
        this.addKeyListener(this);
        this.setFocusable(true);
        generarLaberinto();
    }

    private void generarLaberinto() {
        //tamaño del laberinto y de las paredes
        int filas = 25;
        int columnas = 25;
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
            g.setColor(Color.RED);
            g.drawString("¡GANASTE!", getWidth() / 2 - 50, getHeight() / 210);
        }
        
    }

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
        ventana.setSize(600, 600);
        Laberinto laberinto = new Laberinto();
        ventana.add(laberinto);
        ventana.setVisible(true);
    }
}