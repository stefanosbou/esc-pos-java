package io.github.escposjava.print;

public interface Printer {
   void open();

   void write(byte[] command);

   void close();
}
