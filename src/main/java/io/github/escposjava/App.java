package io.github.escposjava;

import io.github.escposjava.print.NetworkPrinter;
import io.github.escposjava.print.Printer;

public class App {

   public static void main(String[] args){
      Printer printer = new NetworkPrinter("192.168.0.100", 9100);
      PrinterService printerService = new PrinterService(printer);

      printerService.print("Test text");

      printerService.close();
   }
}
