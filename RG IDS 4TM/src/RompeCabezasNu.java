import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class RompeCabezasNu extends JFrame {
    private JPanel contentPane;
    private ArrayList<JButton> buttonlist = new ArrayList<JButton>();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RompeCabezasNu frame = new RompeCabezasNu();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public RompeCabezasNu() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 429);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.NORTH);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setBackground(Color.yellow);
        panel.add(lblNewLabel);

        JPanel panel_1 = new JPanel();
        contentPane.add(panel_1, BorderLayout.SOUTH);
        
        JButton btnReset = new JButton("Reiniciar");
        panel_1.add(btnReset);
        
        

        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setBackground(Color.yellow);
        panel_1.add(lblNewLabel_1);

        JPanel panel_2 = new JPanel();
        contentPane.add(panel_2, BorderLayout.EAST);

        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setBackground(Color.yellow);
        panel_2.add(lblNewLabel_2);

        JPanel panel_3 = new JPanel();

        contentPane.add(panel_3, BorderLayout.WEST);

        JLabel lblNewLabel_3 = new JLabel("");
        lblNewLabel_3.setBackground(Color.yellow);
        panel_3.add(lblNewLabel_3);

        JPanel panel_4 = new JPanel();
        contentPane.add(panel_4, BorderLayout.CENTER);
        panel_4.setLayout(new GridLayout(4, 4, 0, 0));
        
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Collections.shuffle(buttonlist);
                panel_4.removeAll();
                for (JButton button : buttonlist) {
                    if (button != null) {
                        panel_4.add(button);
                    } else {
                        panel_4.add(new JLabel(""));
                    }
                }
                panel_4.revalidate();
                panel_4.repaint();
            }
        });

        
        for (int i = 1; i <= 15; i++) {
            JButton button = new JButton("" + i);
            button.setBackground(Color.yellow);
            panel_4.add(button);
            buttonlist.add(button);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton clickedButton = (JButton) e.getSource();
                    
                    int clickedIndex = buttonlist.indexOf(clickedButton);
                    // Buscar el índice del botón vacío en la lista de botones
                    int emptyIndex = buttonlist.indexOf(null);
                    
                    // Si el botón clickeado está arriba, abajo, a la izquierda o a la derecha del botón vacío,
                    // intercambiar los botones
                    if ((clickedIndex == emptyIndex - 1 && emptyIndex % 4 != 0) || 
                        (clickedIndex == emptyIndex + 1 && clickedIndex % 4 != 0) || 
                        clickedIndex == emptyIndex - 4 || 
                        clickedIndex == emptyIndex + 4) {
                        
                        // Intercambiar los botones en la lista
                        Collections.swap(buttonlist, clickedIndex, emptyIndex);
                        
                        // Eliminar todos los botones del panel y volver a agregarlos
                        panel_4.removeAll();
                        for (JButton button : buttonlist) {
                            if (button != null) {
                                panel_4.add(button);
                            } else {
                                panel_4.add(new JLabel(""));
                            }
                        }
                        panel_4.revalidate();
                        panel_4.repaint();
                        
                        // Si todos los botones están en orden, mostrar mensaje de victoria
                        if (checkWin()) {
                            JOptionPane.showMessageDialog(contentPane, "¡Ganaste!");
                        }
                    }
                }
            });
					}
					// Agregar el botón vacío al final de la lista de botones
					buttonlist.add(null);
					panel_4.add(new JLabel(""));
					// Revolver los botones
					Collections.shuffle(buttonlist);
					for (JButton button : buttonlist) {
					if (button != null) {
					panel_4.add(button);
					} else {
					panel_4.add(new JLabel(""));
					}
					}
					}
/**
 * Comprueba si todos los botones están en orden.
 *
 * @return true si los botones están en orden, false en caso contrario.
 */
private boolean checkWin() {
    for (int i = 0; i < buttonlist.size() - 1; i++) {
        JButton button = buttonlist.get(i);
        if (button == null || !button.getText().equals("" + (i + 1))) {
            return false;
        }
    }
    return true;
}
}