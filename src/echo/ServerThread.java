package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ServerThread extends Thread  {
	
	private Socket soket;
	
	
	public ServerThread() {}
	public ServerThread(Socket soket) {
		this.soket = soket;
	}

	@Override
	public void run(){
		
		try {
			
		//socket <-> socket 종이컵 전화기
		// 메세지 받기용 스트림
		InputStream is = soket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
		BufferedReader br = new BufferedReader(isr);

		// 메세지 보내기용 스트림
		OutputStream os = soket.getOutputStream();
		OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
		BufferedWriter bw = new BufferedWriter(osw);

		// 반복구간 시작
		while (true) {

			// 메세지 받기
			String msg = br.readLine();

			if (msg == null) {
				break;
			}

			System.out.println("받은메세지:" + msg);

			// 메세지 보내기
			bw.write(msg);
			bw.newLine();
			bw.flush();
		}
		// 반복구간 끝

		// 자원종료
		br.close();
		bw.close();
		
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
