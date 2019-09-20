package com.flytre.Images;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PixelEditor {

    static Color[][] getArray(BufferedImage img) {
        Color[][] arr = new Color[img.getHeight()][img.getWidth()];
        for (int j = 0; j < arr.length; j++)
            for (int k = 0; k < arr[0].length; k++)
                arr[j][k] = new Color(img.getRGB(k, j), true);
        return arr;
    }

    static void setImage(BufferedImage img, Color[][] arr) {
        for (int j = 0; j < arr.length; j++)
            for (int k = 0; k < arr[0].length; k++)
                img.setRGB(k, j, arr[j][k].getRGB());
    }

    static Color transform(Color initial, Color result, Color modif) {
        int red = (int) (255 - ((255 - modif.getRed()) * percentMultiply(initial.getRed(), result.getRed(), 255)));
        int grn = (int) (255 - ((255 - modif.getGreen()) * percentMultiply(initial.getGreen(), result.getGreen(), 255)));
        int blu = (int) (255 - ((255 - modif.getBlue()) * percentMultiply(initial.getBlue(), result.getBlue(), 255)));
        return new Color(red, grn, blu);

    }

    static double percentMultiply(int initial, int result, int val) {

        if (result > initial) {
            double diffInit = val - initial;
            double diffRes = val - result;

            return diffRes / diffInit;

        } else if (result < initial) {

            return initial / result;

        } else {
            return 1;
        }
    }
}
