package inputsystem;

import java.io.UnsupportedEncodingException;
import java.util.Scanner;

/**
 * @author mac
 */
public class StdInHandler implements FileHandler {

  private String input_buffer = "";
  private int curPos = 0;

  @Override
  public void Open() {

    Scanner s = new Scanner(System.in);
    while (true) {
      String line = s.nextLine();
      if (line.equals("end")) {
        break;
      }
      input_buffer += line;
    }
    s.close();
  }

  @Override
  public int Close() {
    return 0;
  }

  @Override
  public int Read(byte[] buf, int begin, int len) {

    if (curPos >= input_buffer.length()) {
      return 0;
    }

    int readCnt = 0;
    try {
      byte[] inputBuf = input_buffer.getBytes("UTF8");
      while (curPos + readCnt < input_buffer.length() && readCnt < len) {
        buf[begin + readCnt] = inputBuf[curPos + readCnt];
        readCnt++;
      }
    } catch (UnsupportedEncodingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return readCnt;

  }

}
