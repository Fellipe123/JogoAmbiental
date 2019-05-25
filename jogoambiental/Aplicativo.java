package jogoambiental;

import java.awt.Dimension;  import java.awt.Toolkit;
import java.awt.event.ActionEvent; import java.awt.event.ActionListener;
import javax.swing.JFrame; import javax.swing.JMenu;
import javax.swing.JMenuBar; import javax.swing.JMenuItem;
import javax.swing.JOptionPane; import javax.swing.JPanel;
import javax.swing.JSeparator;

public class Aplicativo extends JFrame{  
    
    public Aplicativo(){
        construirMenuBar();
        construirFase();
        configurarTela();
    }
    
    private JMenuBar construirMenuBar(){
        
            JMenuBar menuBar = new JMenuBar();
            JMenu menu = new JMenu("Opções"); 
            JMenuItem sobre = new JMenuItem("Sobre");

             sobre.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(null,"Jogo sobre reciclgaem para APS", "Informações", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JMenuItem sair = new JMenuItem("Sair");

        sair.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e){
                  System.exit(0);
            }
        });
    
        menu.add(sobre);  
        menu.add(new JSeparator()); 
        menu.add(sair);
        menuBar.add(menu); 
       
        setJMenuBar(menuBar); 
       
        return menuBar;
       
    }
    
    private JPanel construirFase(){ 
        
        Fase fase = new Fase();
        add(fase);
        
        return fase; 
    }
        
    private void configurarTela(){
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        setSize((int) width - 600, (int) height - 150);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("Jogo Ambiental");
    
    }
    
    public static void main(String[] args){ 
          new Aplicativo(); 
    }
}

