package echo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws IOException {
		
		//서버 소켓 생성
		ServerSocket serverSocket = new ServerSocket();
		
		//바인드
		serverSocket.bind(new InetSocketAddress("172.30.1.30", 10001));
		
		System.out.println("<서버 시작");
		System.out.println("============================");
		System.out.println("[연결을 기다리고 있습니다.]");
		
		while (true) {
			// accept
			Socket socket = serverSocket.accept();
			System.out.println("[클라이언트가 연결 되었습니다.]");

			// 대화 Thread 실행(출장보내기)
			Thread thread = new ServerThread(socket);
			thread.start();
		}
		
		/*
		//무한루프에서 빠져나오는 문구가 없어서 이 라인에 접근할 수 없는 오류가 발생
		System.out.println("===================================");
		System.out.println("<서버 종료>");
		serverSocket.close();
		*/
	}

}
