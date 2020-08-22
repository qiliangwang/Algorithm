package TCPIPImplementation.protocol;

public interface IICMPErrorMsgHandler {
    public  boolean handleICMPErrorMsg(int type, int code, byte[] data);
}
