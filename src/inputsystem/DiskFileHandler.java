package inputsystem;

import java.io.InputStream;

public class DiskFileHandler implements FileHandler {

  public DiskFileHandler(String name) {

  }

  @Override
  public void Open() {

  }

  @Override
  public int Close() {
    return 0;
  }

  @Override
  public int Read(byte[] buf, int begin, int len) {
    return 0;
  }
}
