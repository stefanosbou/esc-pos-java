package io.github.escposjava.print.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import io.github.escposjava.print.exceptions.QRCodeException;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.EnumMap;
import java.util.Map;

public class QRCodeGenerator {

   public BufferedImage generate(String textValue) throws QRCodeException {
      return generate(textValue, 150);
   }

   public BufferedImage generate(String textValue, int size) throws QRCodeException {

      try {

         Map<EncodeHintType, Object> hintMap = setEncodingbehavior();

         BitMatrix bm = getByteMatrix(textValue, size, hintMap);

         return getImage(bm);

      } catch (WriterException e) {
         throw new QRCodeException("QRCode generation error", e);
      }
   }

   private Map<EncodeHintType, Object> setEncodingbehavior(){
      Map<EncodeHintType, Object> hintMap = new EnumMap<>(EncodeHintType.class);
      hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
      hintMap.put(EncodeHintType.MARGIN, 1);
      hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
      return hintMap;
   }


   private BitMatrix getByteMatrix(String textValue, int size, Map<EncodeHintType, Object> hintMap) throws WriterException {
      QRCodeWriter qrCodeWriter = new QRCodeWriter();
      return qrCodeWriter.encode(textValue, BarcodeFormat.QR_CODE, size, size, hintMap);
   }

   private BufferedImage getImage(BitMatrix bm){
      BufferedImage image = new BufferedImage(bm.getWidth(), bm.getWidth(), BufferedImage.TYPE_INT_RGB);
      image.createGraphics();
      Graphics2D graphics = (Graphics2D) image.getGraphics();
      graphics.setColor(Color.WHITE);
      graphics.fillRect(0, 0, bm.getWidth(), bm.getWidth());
      graphics.setColor(Color.BLACK);

      for (int i = 0; i < bm.getWidth(); i++) {
         for (int j = 0; j < bm.getWidth(); j++) {
            if (bm.get(i, j)) {
               graphics.fillRect(i, j, 1, 1);
            }
         }
      }
      return image;
   }
}

