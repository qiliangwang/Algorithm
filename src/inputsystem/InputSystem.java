package inputsystem;

import java.io.UnsupportedEncodingException;

public class InputSystem {

	private Input input = new Input();
	
	public InputSystem() {
		
	}
	
	public void runStdinExampe() {
		input.iNewFile(null);
		input.iMarkStart();
		printWord();
		input.iMarkend();
		input.iMarkPrev();
		
		input.iMarkStart();
    	printWord();
    	input.iMarkend();
    	
    	System.out.println("prev word: " + input.iPText());// 打印出 typedef
    	System.out.println("current word: " + input.iText()); //打印出int
	}
	
	private void printWord() {
    	
    	byte c;
    	while ((c = input.iAdvance()) != ' ') {
    		byte[] buf = new byte[1];
    		buf[0] = c;
    		try {
				String s = new String(buf, "UTF8");
				System.out.print(s);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	}
    	
    	System.out.println("");
    }

	public static void main(String[] args) {
		InputSystem input = new InputSystem();
		input.runStdinExampe();
	}
}
