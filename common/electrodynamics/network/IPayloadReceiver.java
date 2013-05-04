package electrodynamics.network;

public interface IPayloadReceiver {

	public void handlePayload(byte[] data);
	
}
