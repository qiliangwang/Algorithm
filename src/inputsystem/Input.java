package inputsystem;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Input {
	
	public static final int EOF = 0;
	private final int MAXLOOK = 16;//lookahead最大长度
	private final int MAXLEX = 1024;//分词后最大长度
	private final int BUFSIZE = (MAXLEX * 3) + (2 * MAXLOOK);
	private       int endBuf = BUFSIZE;//缓冲区结束地址
	private final int DANGER = (endBuf - MAXLOOK);
	private final int END = BUFSIZE;
	private final byte[] startBuf = new byte[BUFSIZE];
	
	private int next = END;//指向当前要读入的字符位置
	private int sMark = END;//当前被词法分析器分析的字符串位置
	private int eMark = END;//当前被分析的字符串结束位置
	private int pMark = END;//上一个被分析的字符串位置
	private int pLineNum = 0;
	private int pLength = 0;
	
	private FileHandler fileHandler = null;
	
	private int lineNum = 1;//当前行号
	private int mLine = 1;
	
	public boolean EofRead = false;//是否有可读信息
	
	public Input() {
		;
	}
	
	public boolean noMoreChars() {
		return EofRead && (next >= endBuf);
	}
	
	private FileHandler getFileHandler(String fileName) {
		if (fileName != null) {
			return new DiskFileHandler(fileName);
		} else {
			return new StdinHandler();
		}
	}
	
	public void iNewFile(String fileName) {
		if(fileHandler != null) {
			fileHandler.close();
		}
		
		fileHandler = getFileHandler(fileName);
		fileHandler.open();
		
		EofRead = false;
		next     = END;
		pMark    = END;
		sMark    = END;
		eMark    = END;
		endBuf  = END;
		lineNum   = 1;
		mLine    = 1;
	}
	
	public String iText() {
		byte[] str = Arrays.copyOfRange(startBuf, sMark, sMark + iLength());
		return new String(str, StandardCharsets.UTF_8);
	}
	
	public int iLength() {
		return eMark - sMark;
	}
	
	public int iLineNum() {
		return lineNum;
	}
	
	public String iPText() {
		byte[] str = Arrays.copyOfRange(startBuf, pMark, pMark + pLength);
		return new String(str, StandardCharsets.UTF_8);
	}
	
	public int iPLength() {
		return pLength;
	}
	
	public int iPLineNum() {
		return pLineNum;
	}
	
	public int iMarkStart() {
		mLine = lineNum;
		eMark = sMark = next;
		return sMark;
	}
	
	public int iMarkend() {
		mLine = lineNum;
		eMark = next;
		return eMark;
	}
	
	public int iMoveStart() {
		if (sMark >= eMark) {
			return -1;
		}
		else {
			sMark++;
			return sMark;
		}
	}
	
	public int iToMark() {
		lineNum = mLine;
		next = eMark;
		return next;
	}
	
	public int iMarkPrev() {
		/*
		 * 执行这个函数后，上一个被词法解析器解析的字符串将无法在缓冲区中找到
		 */
		pMark = sMark;
		pLineNum = lineNum;
		pLength = eMark - sMark;
		return pMark;
	}
	
	public byte iAdvance() {
		/*
		 * 从缓冲区获取字符，next加1，如果Next的位置距离缓冲区的逻辑末尾(End_buf)不到
		 * MAXLOOK 时， 将会对缓冲区进行一次flush 操作
		 */
		if(noMoreChars()) {
			return 0;
		}
		
		//System.out.println(EofRead);
		//int i;
		if(EofRead == false && iFlush(false) < 0) {
			//System.out.println(i);
			return -1;
		}
		
		if(startBuf[next] == '\n') {
			lineNum++;
		}
		
		return startBuf[next++];
	}
	
	public static int NO_MORE_CHARS_TO_READ = 0;
	public static int FLUSH_OK = 1;
	public static int FLUSH_FAIL = -1;
	
	private int iFlush(boolean force) {
		
		int copy_amt, shift_amt, left_edge;
		if (noMoreChars()) {
			return NO_MORE_CHARS_TO_READ;
		}
		
		if (EofRead) {
			//输入流已经没有多余信息了
			return FLUSH_OK;
		}
		
		if (next > DANGER || force) {
			left_edge = pMark < sMark ? pMark : sMark;
			shift_amt = left_edge;
			if (shift_amt < MAXLEX) {
				if (!force) {
					return FLUSH_FAIL;
				}
				
				left_edge = iMarkStart();
				iMarkPrev();
				shift_amt = left_edge;
			}
			
			copy_amt = endBuf - left_edge;
			System.arraycopy(startBuf, 0, startBuf, left_edge, copy_amt);
			
			if (iFillBuf(copy_amt) == 0) {
				System.err.println("Internal Error, iFlush: Buffer full, can't read");
			}
			
			if (pMark != 0) {
				pMark -= shift_amt;
			}
			
			sMark -= shift_amt;
			eMark -= shift_amt;
			next  -= shift_amt;
		}
		
		return FLUSH_OK;
	}
	
	private int iFillBuf(int starting_at) {
		/*
		 * 从输入流中读取信息，填充缓冲区平移后的可用空间，可用空间的长度是从starting_at一直到End_buf
		 * 每次从输入流中读取的数据长度是MAXLEX写整数倍
		 * 
		 */
		
		int need; //需要从输入流中读入的数据长度
		int got = 0; //实际上从输入流中读到的数据长度
		need = ((END - starting_at) / MAXLEX) * MAXLEX;
		if (need < 0) {
			System.err.println("Internal Error (iFillbuf): Bad read-request starting addr.");
		}
		
		if (need == 0) {
			return 0;
		}
		
		if ((got = fileHandler.read(startBuf, starting_at, need)) == -1) {
			System.err.println("Can't read input file");
		}
		
		endBuf = starting_at + got;
		//System.out.println(got + "  " + need);
		if (got < need) {
			//输入流已经到末尾
			EofRead = true;
		}
		
		return got;
	}

	public boolean iPushBack(int n) {
		/*
		 * 把预读取的若干个字符退回缓冲区
		 */
		while (--n >= 0 && next > sMark) {
			if (startBuf[--next] == '\n' || startBuf[next] == '\0') {
				--lineNum;
			}
		}
		
		if (next < eMark) {
			eMark = next;
			mLine = lineNum;
		}
		
		return (next > sMark);
	}
	
	public byte iLookAhead(int n) {
		/*
		 * 预读取若干个字符
		 */
		byte p = startBuf[next + n - 1];
		if (EofRead && next + n - 1 >= endBuf) {
			return EOF;
		}
		
		return (next + n - 1 < 0 || next + n - 1 >= endBuf) ? 0 : p;
	}
}
