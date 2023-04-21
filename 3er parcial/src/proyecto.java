
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JLabel;

public class proyecto extends JFrame {
private int xWins = 0; // Contador de partidas ganadas por X
private int oWins = 0; // Contador de partidas ganadas por O
private JLabel xCounterLabel;
private JLabel oCounterLabel;
private JPanel contentPane;
private JButton[] buttons;  // Arreglo para los botones
private boolean xTurn = true;  // True para el jugador X (gato), false para el jugador O (ratón)

public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				proyecto frame = new proyecto();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
}

public proyecto() {
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 450, 300);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

	setContentPane(contentPane);
	contentPane.setLayout(new BorderLayout(0, 0));
	
	JPanel southPanel = new JPanel(new FlowLayout());
	contentPane.add(southPanel, BorderLayout.SOUTH);
	
	JButton restartButton = new JButton("Reiniciar");
	restartButton.setForeground(Color.green);
	restartButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			
			// Habilita los botones del tablero y limpiar el texto
			for (int i = 0; i < 9; i++) {
				buttons[i].setEnabled(true);
				buttons[i].setText("");
				buttons[i].setBackground(Color.YELLOW);
				buttons[i].setForeground(Color.YELLOW);
			}
			
			// Cambiar el turno a X y actualiza el label
			xTurn = true;
			xCounterLabel.setForeground(Color.BLUE);
			oCounterLabel.setForeground(Color.BLACK);
		}
	});
	southPanel.add(restartButton);
	
	JPanel panel = new JPanel();
	contentPane.add(panel, BorderLayout.NORTH);
	panel.setLayout(new GridLayout(1, 2, 0, 0));
	
	JLabel lblNewLabel = new JLabel("X:"+ xWins);
	lblNewLabel.setBackground(Color.BLUE);
	lblNewLabel.setForeground(Color.BLUE);
	panel.add(lblNewLabel);
	

	
	JLabel lblNewLabel_1 = new JLabel("O:"+ oWins);
	lblNewLabel_1.setForeground(Color.BLUE);
	lblNewLabel_1.setBackground(Color.BLUE);
	panel.add(lblNewLabel_1);
	
	// Crear los JLabel para los contadores
	xCounterLabel = new JLabel("X: " + xWins);
	xCounterLabel.setBackground(Color.BLUE);
	xCounterLabel.setForeground(Color.BLUE);

	oCounterLabel = new JLabel("O: " + oWins);
	oCounterLabel.setBackground(Color.BLUE);
	oCounterLabel.setForeground(Color.BLUE);

	// Crear el panel para los contadores y agregarlos
	JPanel counterPanel = new JPanel(new GridLayout(1, 2));
	counterPanel.add(xCounterLabel);
	counterPanel.add(oCounterLabel);

	// Agregar el panel de contadores al panel norte
	contentPane.add(counterPanel, BorderLayout.NORTH);
	
	
	
	JPanel panel_1 = new JPanel();
	contentPane.add(panel_1, BorderLayout.CENTER);
	panel_1.setLayout(new GridLayout(3, 3, 0, 0));
	
	buttons = new JButton[9];  // Inicializar el arreglo de botones
	for (int i = 0; i < 9; i++) {
		buttons[i] = new JButton();
		buttons[i].setBackground(Color.YELLOW);
		buttons[i].setForeground(Color.YELLOW);
		panel_1.add(buttons[i]);
		buttons[i].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton clickedButton = (JButton) e.getSource();
				int buttonIndex = -1;
				for (int i = 0; i <9; i++) {
if (clickedButton == buttons[i]) {
buttonIndex = i;

break;
}
}
if (buttonIndex != -1) {
if (xTurn) {
clickedButton.setText("X");
clickedButton.setBackground(Color.BLUE);
clickedButton.setForeground(Color.BLUE);
} else {
clickedButton.setText("O");
clickedButton.setBackground(Color.RED);
clickedButton.setForeground(Color.RED);
}
clickedButton.setEnabled(false); // Deshabilitar el botón para evitar que se vuelva a presionar
xTurn = !xTurn; // Cambiar el turno
checkForWin(); // Revisar si hay un ganador
}
}
});
}
}

public void checkForWin() {
// Comprobar si hay una línea horizontal
for (int i = 0; i <= 6; i += 3) {
if (!buttons[i].getText().isEmpty() &&
buttons[i].getText().equals(buttons[i+1].getText()) &&
buttons[i+1].getText().equals(buttons[i+2].getText())) {
displayWinner(buttons[i].getText());
return;
}
}
// Comprobar si hay una línea vertical
for (int i = 0; i < 3; i++) {
if (!buttons[i].getText().isEmpty() &&
buttons[i].getText().equals(buttons[i+3].getText()) &&
buttons[i+3].getText().equals(buttons[i+6].getText())) {
displayWinner(buttons[i].getText());
return;
}
}
// Comprobar si hay una línea diagonal
if (!buttons[0].getText().isEmpty() &&
buttons[0].getText().equals(buttons[4].getText()) &&
buttons[4].getText().equals(buttons[8].getText())) {
displayWinner(buttons[0].getText());
return;
}
if (!buttons[2].getText().isEmpty() &&
buttons[2].getText().equals(buttons[4].getText()) &&
buttons[4].getText().equals(buttons[6].getText())) {
displayWinner(buttons[2].getText());
return;
}
// Comprobar si hay un empate
boolean allButtonsEnabled = true;
for (int i = 0; i < 9; i++) {
if (buttons[i].isEnabled()) {
allButtonsEnabled = false;
break;
}
}
if (allButtonsEnabled) {
JOptionPane.showMessageDialog(this, "¡Empate!");
resetGame();
return;
}
}

public void displayWinner(String winner) {
JOptionPane.showMessageDialog(this, "¡" + winner + " ha ganado!");

if (winner.equals("X")) {
    xWins++;
    xCounterLabel.setText("X: " + xWins);
} else {
    oWins++;
    oCounterLabel.setText("O: " + oWins);
}

resetGame();
}





public void resetGame() {
for (int i = 0; i < 9; i++) {
buttons[i].setText("");
buttons[i].setEnabled(true);
buttons[i].setBackground(Color.YELLOW);
buttons[i].setForeground(Color.YELLOW);
}
xTurn = true;
}


}
