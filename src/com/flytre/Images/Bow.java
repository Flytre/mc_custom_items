package com.flytre.Images;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bow extends PixelEditor {

    public static void main(String[] args) {
        Color wood = new Color(200,100,0);
        Color rest = new Color(200,200,0);
        Color string = new Color(100,200,0);
        recolorBow("resources/bow/bow.png",string,wood,rest);
        recolorBow("resources/bow/bow_pulling_0.png",string,wood,rest);
        recolorBow("resources/bow/bow_pulling_1.png",string,wood,rest);
        recolorBow("resources/bow/bow_pulling_2.png",string,wood,rest);

    }

    public static void recolorBow(String file,Color drawString, Color wood, Color rest) {
        File f = new File(file);
        BufferedImage bi = new BufferedImage(16,16,BufferedImage.TYPE_INT_ARGB);
        try {
            bi = ImageIO.read(f);
        } catch (IOException e) {
        }

        Color[][] pixels = getArray(bi);

        recolorPixels(pixels,drawString,wood,rest);


        setImage(bi, pixels);
        File out = new File(f.getName());
        try {
            ImageIO.write(bi, "png", out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void recolorPixels(Color[][] pixels,Color drawString, Color wood, Color rest) {
        for (int c = 0; c < pixels.length; c++)
            for (int r = 0; r < pixels[c].length; r++) {
                switch (pixels[r][c].getRGB()) {
                    case -11979243: //brown top of bow
                        pixels[r][c] = wood;
                        break;
                    case -7772377: //brown filling lighter
                        pixels[r][c] = transform(new Color(-11979243),new Color(-7772377),wood);
                        break;
                    case -9941474: //brown filling darker
                        pixels[r][c] = transform(new Color(-11979243),new Color(-9941474),wood);
                        break;
                    case -14148085: //brown dark bottom
                        pixels[r][c] = transform(new Color(-11979243),new Color(-14148085),wood);
                        break;
                    case -9737364: //dark gray
                    case -9737365:
                        pixels[r][c] = rest;
                        break;
                    case -6908266://light gray
                        pixels[r][c] = transform(new Color(-9737364),new Color(-6908266),rest);
                        break;
                    case -12303292: //drawstring
                        pixels[r][c] = drawString;
                        break;

                }

            }
    }

}