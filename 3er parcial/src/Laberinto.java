import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.Scanner;

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
private int nivelActual;
private final int nivel1 = 1;
private final int nivel2 = 2;

public Laberinto() {
    this.addKeyListener(this);
    this.setFocusable(true);
    // Seleccionar el nivel actual
    seleccionarNivel();
    generarLaberintoNivel1();
    generarLaberintoNivel2();
}

// seleccionar el nivel
private void seleccionarNivel() {
    String input = JOptionPane.showInputDialog(null, "¿Qué nivel deseas jugar? Ingresa 1 para nivel 1 o 2 para nivel 2.");
    int nivel = Integer.parseInt(input);
    if (nivel == nivel1) {
        nivelActual = nivel1;
    } else if (nivel == nivel2) {
        nivelActual = nivel2;
    } else {
        JOptionPane.showMessageDialog(null, "Opción inválida. Por defecto se jugará el nivel 1.");
        nivelActual = nivel1;
    }
}


// generar el laberinto del nivel 1
private void generarLaberintoNivel1() {
    // Configuración del tamaño del laberinto y de las paredes
    int filas = 15;
    int columnas = 15;
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
            case 1: // Derecha
                laberinto[i][j + 1] = 1;
                break;
            case 2: // Abajo
                laberinto[i + 1][j] = 1;
                break;
            case 3: // Izquierda
                laberinto[i][j - 1] = 1;
                break;
            }
        }
    }
}

// Establece la posición inicial del jugador y la meta
jugadorX = 1;
jugadorY = 1;
metaX = filas - 2;
metaY = columnas - 2;
}

//  laberinto del nivel 2
private void generarLaberintoNivel2() {
// Configuración del tamaño del laberinto y de las paredes
	int filas = 85;
	int columnas = 85;
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

// Establece la posición inicial del jugador y la meta
	jugadorX = 1;
	jugadorY = 1;
	metaX = filas - 2;
	metaY = columnas - 2;
	}}}}


	@Override
	public void paintComponent(Graphics g) {
	super.paintComponent(g);
	int anchoCelda = getWidth() / laberinto[0].length;
	int altoCelda = getHeight() / laberinto.length;

//Dibujado del laberinto

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

// Dibujar al jugador y la meta
g.setColor(Color.red);
g.fillRect(jugadorY * anchoCelda, jugadorX * altoCelda, anchoCelda, altoCelda);
g.setColor(Color.green);
g.fillRect(metaY * anchoCelda, metaX * altoCelda, anchoCelda, altoCelda);

// Mostrar mensaje de ganador si es el caso
if (ganador) {
    JOptionPane.showMessageDialog(null, "¡Felicidades! Has ganado el nivel " + nivelActual + ".");
}
}

// Método para actualizar la posición del jugador
	private void moverJugador(int dx, int dy) {
		int nuevaX = jugadorX + dx;
		int nuevaY = jugadorY + dy;
		
		// Verificar si la nueva posición es válida
		if (nuevaX >= 0 && nuevaX < laberinto.length && nuevaY >= 0 && nuevaY < laberinto[0].length && laberinto[nuevaX][nuevaY] == 0) {
		jugadorX = nuevaX;
		jugadorY = nuevaY;
		// Verificar si el jugador llegó a la meta
		if (jugadorX == metaX && jugadorY == metaY) {
		    ganador = true;
		    repaint();
	}
	}
	}


	@Override
	public void keyPressed(KeyEvent e) {
	int codigo = e.getKeyCode();
	switch (codigo) {
	case KeyEvent.VK_UP:
	moverJugador(-1, 0);
	break;
	case KeyEvent.VK_DOWN:
	moverJugador(1, 0);
	break;
	case KeyEvent.VK_LEFT:
	moverJugador(0, -1);
	break;
	case KeyEvent.VK_RIGHT:
	moverJugador(0, 1);
	break;
	}
	repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
	
	}

	@Override
	public void keyTyped(KeyEvent e) {
	
	}

	public static void main(String[] args) {
		JFrame ventana = new JFrame("Laberinto");
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setSize(500, 500);
		Laberinto laberinto = new Laberinto();
		ventana.add(laberinto);
		ventana.setVisible(true);
	}
}