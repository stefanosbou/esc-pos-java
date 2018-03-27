package io.github.escposjava;

import io.github.escposjava.print.Commands;
import io.github.escposjava.print.NetworkPrinter;
import io.github.escposjava.print.Printer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;


public class PrinterServiceTest {
   private PrinterService printerService;
   private Printer mockPrinter;

   @Before
   public void CreatePrintService(){
      mockPrinter = mock(NetworkPrinter.class);
      printerService = new PrinterService(mockPrinter);
   }

   @Test
   public void testPrinterServiceInstantiation() {
      assertNotNull(printerService);
      verify(mockPrinter).open();
   }

   @Test
   public void testPrinterServicePrintText() {
      String text = "Text to print";
      printerService.print(text);
      verify(mockPrinter).write(text.getBytes());
   }

   @Test
   public void testPrinterServicePrintTextWithNewline() {
      String text = "Text to print";
      String textWithNewLine = text + System.getProperty("line.separator");
      printerService.printLn(text);
      verify(mockPrinter).write(textWithNewLine.getBytes());
   }

   @Test
   public void testPrinterServicePrintLineBreak() {
      printerService.lineBreak();
      verify(mockPrinter).write(Commands.CTL_LF);
   }

   @Test
   public void testPrinterServicePrintMultipleLineBreaks() {
      int counter = 5;
      printerService.lineBreak(counter);
      verify(mockPrinter, times(counter)).write(Commands.CTL_LF);
   }

   @Test
   public void testPrinterServiceSetTextSizeNormal() {
      printerService.setTextSizeNormal();
      verify(mockPrinter).write(Commands.TXT_NORMAL);
   }

   @Test
   public void testPrinterServiceSetTextSize2H() {
      printerService.setTextSize2H();
      InOrder inOrder = inOrder(mockPrinter);
      inOrder.verify(mockPrinter).write(Commands.TXT_NORMAL);
      inOrder.verify(mockPrinter).write(Commands.TXT_2HEIGHT);
   }

   @Test
   public void testPrinterServiceSetTextSize2W() {
      printerService.setTextSize2W();
      InOrder inOrder = inOrder(mockPrinter);
      inOrder.verify(mockPrinter).write(Commands.TXT_NORMAL);
      inOrder.verify(mockPrinter).write(Commands.TXT_2WIDTH);
   }

   @Test
   public void testPrinterServiceSetText4Square() {
      printerService.setText4Square();
      InOrder inOrder = inOrder(mockPrinter);
      inOrder.verify(mockPrinter).write(Commands.TXT_NORMAL);
      inOrder.verify(mockPrinter).write(Commands.TXT_4SQUARE);
   }


}
