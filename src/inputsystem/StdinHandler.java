package inputsystem;

import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class StdinHandler implements FileHandler {

	private String inputBuf = "";
	private int curPos = 0;
	
	@Override
	public void open() {
		Scanner s = new Scanner(System.in);
		while(true) {
			String line = s.nextLine();
			if(line.equals("end")) {
				break;
			}
			inputBuf += line;
			inputBuf += '\n';
		}
		//s.close();
	}

	@Override
	public int close() {
		return 0;
	}

	@Override
	public int read(byte[] buf, int begin, int len) {
		if(curPos >= inputBuf.length()) {
			return 0;
		}
		
		int readCnt = 0;
		try {
			byte[] b = inputBuf.getBytes("UTF8");
			while(curPos + readCnt < inputBuf.length() && readCnt < len) {
				buf[begin + readCnt] = b[curPos + readCnt];
				readCnt++;
			}
		} catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return readCnt;
	}

}
