package jogoambiental;

import java.awt.Color; import java.awt.Dimension;import java.awt.Font;
import java.awt.Graphics;import java.awt.Graphics2D;import java.awt.Image;
import java.awt.Toolkit;import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;import java.awt.event.MouseMotionListener;
import java.util.ArrayList;import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Fase extends JPanel implements ActionListener{
    
   Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
   
   
   private final double width = screenSize.getWidth() - 600;
   private final double height = screenSize.getHeight() - 150;
   private final int espacamentoLatas = (int) (width - 250)/3;
   private final int alturaLatas = (int) height - 250;
 
   // Distribuindo as latas igualmente na tela
   private final int x_Lata_Azul = 0 + 120;
   private final int x_Lata_Verde = espacamentoLatas + 120;
   private final int x_Lata_Vermelha =  (2 * espacamentoLatas) + 120;
   private final int x_Lata_Amarela = (3 * espacamentoLatas) + 120;
   private final Image fundo; 
   private final Image paper;
   private final Image glass;
   private final Image metal;
   private final Image plastic;
   private static int contador = 0;
   private final Timer timer; 
   private List<Objeto> inimigos;
   private boolean emJogo;
   private boolean inicio = false;
   
   public int coordinate(){ 
       int coo;
       coo = ThreadLocalRandom.current().nextInt(100,(int) width - 100);
       return coo;
   }
   
    public Fase(){
        
        setFocusable(true);
        setDoubleBuffered(true);
        addKeyListener(new TecladoAdapter());
        addMouseMotionListener(new MouseAdapters());
        addMouseListener(new MouseAdapters());
        
        ImageIcon referencia = new ImageIcon(Aplicativo.class.getResource("Imagens/Fundo.jpg"));
        fundo = referencia.getImage();
    
        ImageIcon lataB = new ImageIcon(Aplicativo.class.getResource("Imagens/lata_azul.png"));
        paper = lataB.getImage();
    
        ImageIcon lataG = new ImageIcon(Aplicativo.class.getResource("Imagens/lata_verde.png"));
        glass = lataG.getImage();
    
        ImageIcon lataR = new ImageIcon(Aplicativo.class.getResource("Imagens/lata_vermelha.png"));
        metal = lataR.getImage();
    
        ImageIcon lataY = new ImageIcon(Aplicativo.class.getResource("Imagens/lata_amarela.png"));
        plastic = lataY.getImage(); emJogo = true; inicializarObjetos();
    
        timer = new Timer(5, this);
        timer.start();
        
    }
    
    public void inicializarObjetos(){
        
        inimigos = new ArrayList<>();
        int coo = coordinate();inimigos.add(new Objeto(coo, 000));
        Timer timer = new Timer(4000, new ActionListener() {
     
            @Override
            public void actionPerformed(ActionEvent e){

                int coo = coordinate(); inimigos.add(new Objeto(coo, 000));
            }
        });
       timer.start(); 
   }
    
       public void paint(Graphics g){
      
           Graphics2D graficos = (Graphics2D) g;
           if(inicio){  graficos.drawImage(fundo, 0, 0, null);
               graficos.drawImage(paper,x_Lata_Azul,alturaLatas, null);
               graficos.drawImage(glass, x_Lata_Verde,alturaLatas, null);
               graficos.drawImage(metal, x_Lata_Vermelha,alturaLatas, null);
               graficos.drawImage(plastic,x_Lata_Amarela,alturaLatas, null);
               if(emJogo){
                  for (int i = 0; i < inimigos.size(); i++){
                      Objeto in = inimigos.get(i);
                      graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
                  }
                  g.setFont(new Font("Impact", Font.PLAIN, 17)); 
                  graficos.drawString("Objetos: " + contador, 5, 15);
               }else{
                  g.setFont(new Font("Impact", Font.BOLD, 40)); 
                  g.setColor(Color.RED);
                  graficos.drawString("GAME OVER", (int) ((width/2) - 100), (int) ((height/2) - 100));
                  graficos.drawString("Precione enter para jogar novamente ", (int) ((width/2) - 320), (int) ((height/2 - 50)));
                    contador = 0;timer.start();
               }
                 g.dispose();
               }else{
                  graficos.drawImage(fundo, 0, 0, null);
                  g.setFont(new Font("Impact", Font.PLAIN, 60)); 
                  g.setColor(Color.RED);
                  graficos.drawString("JOGO SOBRE RECICLAGEM", (int) ((width/2) - 300), (int) ((height/2) - 200));
                  graficos.drawString("Clique na tela para iniciar o jogo.", (int) ((width/2) - 400), (int) ((height/2 - 50)));
               }
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
         for (int i = 0; i < inimigos.size(); i++){
              Objeto in = inimigos.get(i); 
              
              if (in.isVisible()){
                  in.mexer();
              }else{ 
                  inimigos.remove(i);
              }
         }
         if(inicio){
             encontros();
             repaint();
         }
 
    }

    public void encontros(){ 
        for(int i = 0; i < inimigos.size(); i++){
             Objeto tempObjeto = inimigos.get(i);
             int ide = tempObjeto.getId();
             if(tempObjeto.getY() > 900){
                 emJogo = false;
             } 
             
             switch(ide){
                case 0:
                   if(tempObjeto.getX() >= x_Lata_Azul - 26 && tempObjeto.getX() <= x_Lata_Azul + 175 && tempObjeto.getY() <= height - 200 && tempObjeto.getY() >= height - 270){ 
                        contador += 1;
                        tempObjeto.setVisible(false); 
                   }else{
                       if((tempObjeto.getX() >= x_Lata_Verde - 30 && tempObjeto.getX() <= x_Lata_Verde+140 || tempObjeto.getX() >= x_Lata_Vermelha - 30 && tempObjeto.getX() <= x_Lata_Vermelha + 140 || tempObjeto.getX() >= x_Lata_Amarela - 50 && tempObjeto.getX() <= x_Lata_Amarela + 150) && tempObjeto.getY() <= height - 200 && tempObjeto.getY() >= height - 270){ 
                           emJogo = false;  
                       }
                   }
                      break;
               case 1:
                   if(tempObjeto.getX() >= x_Lata_Verde - 30 && tempObjeto.getX() <= x_Lata_Verde+140
                       && tempObjeto.getY() <= height - 200 && tempObjeto.getY() >= height - 310){ 

                        contador += 1;
                        tempObjeto.setVisible(false);
                   }else{
                       if((tempObjeto.getX() >= x_Lata_Azul - 30 && tempObjeto.getX() <= x_Lata_Azul + 140||
                             tempObjeto.getX() >= x_Lata_Vermelha -30 && tempObjeto.getX() <= x_Lata_Vermelha + 140 || tempObjeto.getX() >= x_Lata_Amarela -30 && tempObjeto.getX() <= x_Lata_Amarela + 150) && tempObjeto.getY() <= height - 200 && tempObjeto.getY() >= height - 270){ 
                                 emJogo = false; 
                       } 
                   }
                      break;
               case 2:
                   if(tempObjeto.getX() >= x_Lata_Vermelha - 30 && tempObjeto.getX() <= x_Lata_Vermelha + 140 && tempObjeto.getY() <= height - 200 && tempObjeto.getY() >= height - 310){  
                       contador += 1; tempObjeto.setVisible(false); 
                   }else{
                       if((tempObjeto.getX() >= x_Lata_Azul - 30 && tempObjeto.getX() <= x_Lata_Azul + 140 || tempObjeto.getX() >= x_Lata_Verde - 30 && tempObjeto.getX() <= x_Lata_Verde+140 || tempObjeto.getX() >= x_Lata_Amarela - 30 && tempObjeto.getX() <= x_Lata_Amarela + 150)  && tempObjeto.getY() <= height - 200 && tempObjeto.getY() >= height - 270){
                          emJogo = false;
                       }
                   }
                      break;
 
                   case 3:
                       if(tempObjeto.getX() >= x_Lata_Amarela - 30 - 50 && tempObjeto.getX() <= x_Lata_Amarela + 150
                            && tempObjeto.getY() <= height - 200 && tempObjeto.getY() >= height - 300){ 
                        
                             contador += 1; tempObjeto.setVisible(false); 
                       }else{
                          if((tempObjeto.getX() >= x_Lata_Azul - 30 && tempObjeto.getX() <= x_Lata_Azul + 140 || tempObjeto.getX() >= x_Lata_Verde - 30 && tempObjeto.getX() <= x_Lata_Verde+140 || tempObjeto.getX() >= x_Lata_Vermelha - 30 && tempObjeto.getX() <= x_Lata_Vermelha + 140) && tempObjeto.getY() <= height - 200 && tempObjeto.getY() >= height - 270){  
                              emJogo = false;
                          }
                       }
                          break;
                   default:
             } 
        }
    }

    private class TecladoAdapter extends KeyAdapter{

        @Override
        public void keyPressed(KeyEvent e){
            if (e.getKeyCode() == KeyEvent.VK_ENTER && !emJogo){
                emJogo = true;
                inicializarObjetos();
            } 
        } 
    }
    
    private class MouseAdapters extends MouseAdapter implements MouseMotionListener{

        @Override
        public void mouseClicked(MouseEvent e){
            inicio = true;
        }

        @Override
        public void mouseDragged(MouseEvent e){
             for(int i = 0; i < inimigos.size(); i++){    

                   if(e.getX() < inimigos.get(i).getX() + 80 && e.getX() > inimigos.get(i).getX() -80
                         && e.getY() < inimigos.get(i).getY() + 80 && e.getY() > inimigos.get(i).getY() - 80){
                             inimigos.get(i).setX(e.getX() - 40);
                             inimigos.get(i).setY(e.getY() - 40);
                   }
             }
        }
    }
}

