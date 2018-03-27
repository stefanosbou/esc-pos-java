# ESC/POS Java Library for thermal printers
[![Build Status](https://travis-ci.org/stefanosbou/esc-pos-java.svg?branch=master)](https://travis-ci.org/stefanosbou/esc-pos-java)
[![codecov](https://codecov.io/gh/stefanosbou/esc-pos-java/branch/master/graph/badge.svg)](https://codecov.io/gh/stefanosbou/esc-pos-java)
## Synopsis

Java library for ESC/POS compatible thermal printers, serial or network connected. 
You can print :
- Text
- Barcodes
- QRCodes
- Images

It is largely based on work from harf18 (https://github.com/harf18/escpospi)

## Code Example for Network connected printer

```java
// 192.168.0.100 is the IP of the network connected thermal printer
// 9100 is the port of the thermal printer
Printer printer = new NetworkPrinter("192.168.0.100", 9100);
PrinterService printerService = new PrinterService(printer);

printerService.print("Test text");
printerService.cutFull()

printerService.close();
```

## Building the project

To build the project, just use:

`mvn clean package`

It will generate a fat-jar in the target directory.

## License

This project is licensed under the terms of the MIT license.
