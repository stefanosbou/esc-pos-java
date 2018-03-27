package io.github.escposjava;

import io.github.escposjava.print.NetworkPrinter;
import io.github.escposjava.print.Printer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class NetworkPrinterTest {
   private Printer mockPrinter;

   @Before
   public void CreatePrinter(){
      mockPrinter = mock(NetworkPrinter.class);
   }

   @Test
   public void testPrinterInstantiation(){
      assertNotNull(mockPrinter);
   }

   @Test
   public void testPrinterOpen(){
      mockPrinter.open();
      verify(mockPrinter).open();
   }

   @Test
   public void testPrinterClose() {
      mockPrinter.close();
      verify(mockPrinter).close();
   }

   @Test
   public void testPrinterWrite() {
      byte[] command = {0x1d,0x7c,0x00};
      mockPrinter.write(command);
      verify(mockPrinter).write(command);
   }
}
