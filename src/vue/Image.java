package vue;
import javax.swing.*;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;

public class Image extends JPanel{
  protected String entree;
  protected int largeur;
  protected int hauteur;

  public Image(String entree, int largeur, int hauteur){
    try
    {
      redimension(entree, largeur, hauteur);
      decoupe(entree);
    }
    catch (IOException ex)
    {
        ex.printStackTrace();
    }
  }

 /**
  * Méthode qui permet de redimensioner l'image pour qu'elle s'adapte au panel
  * @param image l'image à redimensionner
  * @param largeur la largeur de l'image
  * @param hauteur la hauteur de l'image
  * @throws IOException 
  */
 public void redimension(String image, int largeur, int hauteur)
 throws IOException
 {
      //On lit l'image
      BufferedImage imageEntiere = ImageIO.read(new File(image));
      //On crée notre image final
      BufferedImage img = new BufferedImage(largeur, hauteur, 5);
      //On dessine notre image de base dans l'image final avec les nouvelles dimensions
      Graphics2D g = img.createGraphics();
      g.drawImage(imageEntiere, 0, 0, largeur, hauteur, null);
      g.dispose();
      //On enregistre notre image
      ImageIO.write(img, "jpeg", new File(image));
 }

 /**
  * Méthode qui permet de découper une image en sous-image
  * @param image l'image à découper
  * @throws IOException
  */
 public void decoupe(String image)
 throws IOException{
   //On lit notre image
     BufferedImage imageEntiere = ImageIO.read(new File(image));
     int k = 1;
     //On parcourt notre image quand découpe en 3 dans la longueur et la largeur
     for(int i = 0; i < 498; i+= 500/3){
       for(int j = 0; j < 498; j+= 500/3){
         BufferedImage SubImg = imageEntiere.getSubimage(j, i, 167, 167);
         String name = image.replaceFirst("[.][^.]+$", "");
         File outputfile = new File(name + Integer.valueOf(k).toString() + ".jpeg");
         //On crée nos sous-image
         ImageIO.write(SubImg,"jpeg", outputfile);
         k+=1;
       }
    }
  }
}
