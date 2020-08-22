package TCPIPImplementation.utils;

public interface IPacketProvider {
	public void registerPacketReceiver(jpcap.PacketReceiver receiver);
}
