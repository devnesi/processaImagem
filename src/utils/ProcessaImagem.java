package utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

//Classe criada para reunir todos os efeitos
public class ProcessaImagem {
    public final static String URL = "src\\resources\\cachorro.jpg";

    public BufferedImage getImagem() {
        try {
            return ImageIO.read(Files.newInputStream(Paths.get(URL)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ImageIcon getImagemIcon() {
        try {
            return new ImageIcon(ImageIO.read(Files.newInputStream(Paths.get(URL))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public BufferedImage aplicaGride(){
        final BufferedImage imagem = getImagem();
        for (int x = 0; x < imagem.getWidth(); x += 4) {
            for (int y = 0; y < imagem.getHeight(); y += 4){
                imagem.setRGB(x, y, new Color(0, 0 ,0).getRGB());
            }
        }
        return imagem;
    }

    public BufferedImage aplicaVermelho(){
        final BufferedImage imagem = getImagem();
        Color colorPx;
        for (int i = 0; i < imagem.getWidth(); i++) {
            for (int j = 0; j < imagem.getHeight(); j++) {
                colorPx = new Color (imagem.getRGB(i, j));
                imagem.setRGB(i, j, new Color(colorPx.getRed(), 0, 0).getRGB());
            }
        }
        return imagem;
    }

    public BufferedImage aplicaAzul(){
        final BufferedImage imagem = getImagem();
        Color colorPx;
        for (int i = 0; i < imagem.getWidth(); i++) {
            for (int j = 0; j < imagem.getHeight(); j++) {
                colorPx = new Color (imagem.getRGB(i, j));
                imagem.setRGB(i, j, new Color(0, 0, colorPx.getBlue()).getRGB());
            }
        }
        return imagem;
    }

    public BufferedImage aplicaVerde(){
        final BufferedImage imagem = getImagem();
        Color colorPx;
        for (int i = 0; i < imagem.getWidth(); i++) {
            for (int j = 0; j < imagem.getHeight(); j++) {
                colorPx = new Color (imagem.getRGB(i, j));
                imagem.setRGB(i, j, new Color(0, colorPx.getGreen(), 0).getRGB());
            }
        }
        return imagem;
    }

    //https://stackoverflow.com/questions/9131678/convert-a-rgb-image-to-grayscale-image-reducing-the-memory-in-java
    public BufferedImage aplicaCinza(){
        final BufferedImage img = getImagem();

        for (int x = 0; x < img.getWidth(); ++x)
            for (int y = 0; y < img.getHeight(); ++y)
            {
                int rgb = img.getRGB(x, y);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = (rgb & 0xFF);

                double rr = Math.pow(r / 255.0, 2.2);
                double gg = Math.pow(g / 255.0, 2.2);
                double bb = Math.pow(b / 255.0, 2.2);

                double lum = 0.2126 * rr + 0.7152 * gg + 0.0722 * bb;

                int grayLevel = (int) (255.0 * Math.pow(lum, 1.0 / 2.2));
                int gray = (grayLevel << 16) + (grayLevel << 8) + grayLevel;
                img.setRGB(x, y, gray);
            }

        return img;
    }

    //https://stackoverflow.com/questions/14513542/how-to-convert-image-to-black-and-white-using-java
    public BufferedImage aplicaPretoBranco(){
        final BufferedImage imagem = getImagem();

        Color pixelColor;

        for (int i = 0; i < imagem.getWidth(); i++) {
            for (int j = 0; j < imagem.getHeight(); j++) {
                pixelColor = new Color(imagem.getRGB(i, j));
                int R = 255;
                int G = 255;
                int B = 255;

                int avg = (pixelColor.getRed() + pixelColor.getBlue() + pixelColor.getGreen()) / 3;
                if (avg < 128) {
                    R = 0;
                    G = 0;
                    B = 0;
                }

                imagem.setRGB(i, j, new Color(R, G, B).getRGB());
            }
        }
        return imagem;
    }

    public BufferedImage aplicaNariz(int buffer, int y){
        final BufferedImage imagem = getImagem();

        Color pixelColor;

        for (int i = 0; i < imagem.getWidth(); i++) {
            for (int j = 0; j < imagem.getHeight(); j++) {
                pixelColor = new Color(imagem.getRGB(i, j));

                int sum = (pixelColor.getRed() + pixelColor.getBlue() + pixelColor.getGreen());

                if(j > y){
                    if(sum < (buffer)){
                        imagem.setRGB(i, j, new Color(255, 0, 0).getRGB());
                    }
                }



            }
        }
        return imagem;
    }

}
