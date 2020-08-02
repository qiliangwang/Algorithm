package ThompsonConstruction;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import inputsystem.Input;

/*
 * ????????????
 */
public class MacroHandler {

	/*
	 * <macro name> <space> <text>
	 */
	
	private HashMap<String, String> macroMap = new HashMap<String, String>();
	private Input inputSystem;
	
	public MacroHandler(Input input) {
		this.inputSystem = input;
		while(input.iLookAhead(1) != Input.EOF) {
			newMacro();
		}
	}
	
	/*
	 * ???????? - ???? ????HashMap
	 */
	public void newMacro() {	
		//?????????
		while(Character.isSpaceChar(inputSystem.iLookAhead(1)) || inputSystem.iLookAhead(1) == '\n') {
			inputSystem.iAdvance();
		}
		
		String macroName = "";
		char c = (char)inputSystem.iLookAhead(1);
		while(Character.isSpaceChar(c) == false && c != '\n') {
			macroName += c;
			inputSystem.iAdvance();
			c = (char)inputSystem.iLookAhead(1);
		}
		
		//?????????????????
		while (Character.isSpaceChar(inputSystem.iLookAhead(1))) {
			inputSystem.iAdvance();
		}
		
		//???????????
		c = (char)inputSystem.iLookAhead(1);
		String macroContent = "";
		while (Character.isSpaceChar(c) == false && c != '\n') {
			macroContent += c;
			inputSystem.iAdvance();
			c = (char)inputSystem.iLookAhead(1);
		}
		
		//????\n
		inputSystem.iAdvance();
		
		macroMap.put(macroName, macroContent);
	}
	
	public String expandMacro(String macroName) throws Exception {
		if (macroMap.containsKey(macroName) == false) {
			ErrorHandler.parseErr(ErrorHandler.Error.E_NOMAC);
		}
		else {
			return "(" + macroMap.get(macroName) + ")";
		}
		
		return "ERROR"; //?????????????bug
	}
	
	public void printMacs() {
		if (macroMap.isEmpty()) {
			System.out.println("There are no macros");
		}
		else {
			Iterator<Entry<String, String>> iter = macroMap.entrySet().iterator();
			while (iter.hasNext()) {
				Entry<String, String> entry = (Entry<String, String>)iter.next();
				System.out.println("Macro name: " + entry.getKey() + " Macro content: " + entry.getValue());
			}
		}
	}
}
