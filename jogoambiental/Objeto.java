package jogoambiental;

import java.awt.Dimension;import java.awt.Image;
import java.awt.Toolkit;import java.util.Random;import javax.swing.ImageIcon;

public class Objeto {

    private static final int VELOCIDADE = 1;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private double height = screenSize.getHeight();

    private static int contador = 0 ; private int id ; private int x,y,largura,altura;
    private Image imagem ;  private boolean visible;

    public Objeto(int x, int y) {
        this.x = x;
        this.y = y;

        ImageIcon referencia = new ImageIcon(Aplicativo.class.getResource("Imagens/fundo.jpg"));
        Random randomGenerator = new Random();
        contador = randomGenerator.nextInt(4);
        switch(contador){
            case 0:
                referencia = new ImageIcon(Aplicativo.class.getResource("Imagens/Papel_Icone.jpg"));
                this.id = 0;
                break;
            case 1:
                referencia = new ImageIcon(Aplicativo.class.getResource("Imagens/Vidro_Icone.png"));
                this.id = 1;
                break;
            case 2:
                referencia = new ImageIcon(Aplicativo.class.getResource("Imagens/Plastico_Icone.png"));
                this.id = 2;
                break;
            case 3:
                referencia = new ImageIcon(Aplicativo.class.getResource("Imagens/Metal_Icone.png"));
                this.id = 3;
                break;
            default:
        }
        
        imagem = referencia.getImage(); 
        this.largura = imagem.getWidth(null);
        this.altura = imagem.getHeight(null); visible = true;
    }
   
    public void mexer(){
        if (this.y > height){ 
            this.visible = false; 
        }else{
           this.y += VELOCIDADE; 
        }
    }
   
    public boolean isVisible(){
        return visible;
    }
   
    public void setVisible(boolean visible){
        this.visible = visible; 
    }
   
    public Image getImagem(){
        return imagem; 
    }
   
    public int getX(){ 
        return x;
    }
   
    public void setX(int x){
        this.x = x;
    }
   
    public int getY(){
        return y;
    }
   
    public void setY(int y){
        this.y = y;
    }
   
    public int getAltura(){
        return altura;
    }
   
    public int getLargura(){  
        return largura;
    }
    
    public int getId(){
        return this.id;
    }
}
