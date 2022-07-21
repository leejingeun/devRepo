import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;

public class NetworkGetMacAddress {

	public static void main(String[] args) throws IOException {

		// 로컬 IP취득
		InetAddress ip = InetAddress.getLocalHost();
		System.out.println("IP : " + ip.getHostAddress());

		// 네트워크 인터페이스 취득
		NetworkInterface netif = NetworkInterface.getByInetAddress(ip);

		// 네트워크 인터페이스가 NULL이 아니면
		if (netif != null) {
			// 네트워크 인터페이스 표시명 출력
			System.out.print(netif.getDisplayName() + " : ");

			// 맥어드레스 취득
			byte[] mac = netif.getHardwareAddress();

			// 맥어드레스 출력
			for (byte b : mac) {
				System.out.printf("[%02X]", b);
			}
			System.out.println();
		}
	}

}