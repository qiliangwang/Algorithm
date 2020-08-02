package inputsystem;

/*
 *	从输入流中获取信息的接口，磁盘文件或者system.in 
 */
public interface FileHandler {

	public void open();
	public int close();
	
	/*
	 * 返回读取的长度
	 */
	public int read(byte[] buf, int begin, int len);
}
