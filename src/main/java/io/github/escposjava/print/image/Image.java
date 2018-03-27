package io.github.escposjava.print.image;

import java.awt.image.BufferedImage;

public class Image {

   public int[][] getPixelsSlow(BufferedImage image) {
      int width = image.getWidth();
      int height = image.getHeight();
      int[][] result = new int[height][width];
      for (int row = 0; row < height; row++) {
         for (int col = 0; col < width; col++) {
            result[row][col] = image.getRGB(col, row);
         }
      }

      return result;
   }

   public byte[] recollectSlice(int y, int x, int[][] img) {
      byte[] slices = new byte[] {0, 0, 0};
      for (int yy = y, i = 0; yy < y + 24 && i < 3; yy += 8, i++) {
         byte slice = 0;
         for (int b = 0; b < 8; b++) {
            int yyy = yy + b;
            if (yyy >= img.length) {
               continue;
            }
            int col = img[yyy][x];
            boolean v = shouldPrintColor(col);
            slice |= (byte) ((v ? 1 : 0) << (7 - b));
         }
         slices[i] = slice;
      }

      return slices;
   }

   private boolean shouldPrintColor(int col) {
      final int threshold = 127;
      int a, r, g, b, luminance;
      a = (col >> 24) & 0xff;
      if (a != 0xff) { // Ignore transparencies
         return false;
      }
      r = (col >> 16) & 0xff;
      g = (col >> 8) & 0xff;
      b = col & 0xff;

      luminance = (int) (0.299 * r + 0.587 * g + 0.114 * b);

      return luminance < threshold;
   }

}
