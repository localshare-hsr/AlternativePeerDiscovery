package ch.hsr.epj.ouroboros.printer;

public class HexDump {

  private static void printablePrinter(byte[] lineData, final int lengthOfLine) {
    int currentChar = 0;
    for (byte b : lineData) {
      if (b >= 32 && b <= 126) {
        System.out.print((char) b);
      } else {
        System.out.print(".");
      }

      if (currentChar == 7) {
        System.out.print(" ");
      }

      if (currentChar == lengthOfLine) {
        return;
      }
      currentChar++;
    }
  }

  private static void hexLine(int lineNumber, byte[] lineData, int lengthOfLine) {
    System.out.printf("%04X:  ", lineNumber);

    for (int i = 0; i < lengthOfLine; i++) {
      System.out.printf("%02X ", lineData[i]);

      if (i == 7) {
        System.out.print(" ");
      }
    }

    int missingSpaces = 16 - lengthOfLine;

    for (int i = 0; i < missingSpaces; i++) {
      System.out.print("   ");
    }

    System.out.print(" ");
    printablePrinter(lineData, lengthOfLine);

    System.out.println();
  }

  public static void simpleHexPrinter(byte[] data, int length) {

    int elementsPerLine = 16;
    byte[] lineOfData = new byte[elementsPerLine];

    int marker = 0;
    do {

      int end = marker + elementsPerLine;

      int j = 0;
      for (int i = marker; i < end; i++) {
        if (i == length) {
          break;
        }
        lineOfData[j] = data[i];
        j++;
      }
      hexLine(marker, lineOfData, j);
      lineOfData = new byte[elementsPerLine];

      marker += elementsPerLine;

    } while (marker < length);
  }
}
