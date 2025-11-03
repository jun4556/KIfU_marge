package com.objetdirect.gwt.umldrawer.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.objetdirect.gwt.umldrawer.client.beans.EditEvent;
import com.objetdirect.gwt.umldrawer.client.beans.SaveEventInfo;
import com.objetdirect.gwt.umldrawer.client.canvas.CanvasService;
// ▼▼▼ import先が drawer 側の Dao になっていることを確認 ▼▼▼
import com.objetdirect.gwt.umldrawer.server.dao.Dao;

public class CanvasServiceImpl extends RemoteServiceServlet implements CanvasService {

	/**
	 * 参照コードに基づき、saveCanvas を呼び出すように修正
	 */
	@Override
	public void saveCanvas(String studentId, int exerciseId, String canvasUrl) {
		Dao dao = new Dao();
		// drawer/Dao.java の saveCanvas メソッドを呼び出す
		dao.saveCanvas( studentId, exerciseId, canvasUrl);
	}

	/**
	 * 参照コードに基づき、loadCanvas を呼び出すように修正
	 * マージ機能では、DrawerBase からイベントID(文字列)が渡されることを想定し、
	 * drawer/Dao.java 側でIDによる処理の分岐が行われることを期待します。
	 */
	@Override
	public EditEvent loadCanvas(String studentId, int exercisesId) {
		Dao dao = new Dao();
		// drawer/Dao.java の loadCanvas メソッドを呼び出す
		return dao.loadCanvas( studentId, exercisesId);
	}

	/**
	 * 参照コードに基づき、undo を呼び出すように修正
	 */
	@Override
	public EditEvent undo(String studentId, int exercisesId) {
		Dao dao = new Dao();
		// drawer/Dao.java の undo メソッドを呼び出す
		return dao.undo(studentId, exercisesId);
	}

	/**
	 * 参照コードに基づき、saveCanvasAsAnswer を呼び出すように修正
	 */
	@Override
	public boolean saveCanvasAsAnswer(String studentId, int exerciseId, String canvasUrl) {
		Dao dao = new Dao();
		// drawer/Dao.java の saveCanvasAsAnswer メソッドを呼び出す
		return dao.saveCanvasAsAnswer( studentId, exerciseId, canvasUrl);
	}

	/**
	 * 参照コードに基づき、getAnswer を呼び出すように修正
	 */
	@Override
	public String getAnswer(int exerciseId) {
		Dao dao = new Dao();
		// drawer/Dao.java の getAnswer メソッドを呼び出す
		return dao.getAnswer(exerciseId);
	}

	// --- 以下、マージ機能のために追加されたメソッド ---

	/**
	 * マージ後の保存処理。既存の saveCanvas メソッドを流用します。
	 */
	@Override
	public void saveMergedCanvas(String commonId, int exerciseId, String canvasUrl) {
	    Dao dao = new Dao();
	    // 共通IDを学生IDとして、既存のsaveCanvasメソッドで保存
	    dao.saveCanvas(commonId, exerciseId, canvasUrl);
	}
	
	/**
	 * マージ候補のリストを取得する処理
	 */
	@Override
	public List<SaveEventInfo> getRecentSaves(int exerciseId, String currentStudentId) {
		Dao dao = new Dao();
		int limit = 10;
		// drawer/Dao.java に追加した getRecentSaveEvents メソッドを呼び出す
		return dao.getRecentSaveEvents(exerciseId, currentStudentId, limit);
	}

	/**
	 * AIマージサーバーと通信する処理
	 */
//	@Override
//	public String mergeCanvas(String myUrl, String opponentUrl) {
//        HttpURLConnection connection = null;
//        try {
//            byte[] decodedMyBytes = Base64.getDecoder().decode(myUrl);
//            byte[] decodedOpponentBytes = Base64.getDecoder().decode(opponentUrl);
//            String decodedMyUrl = new String(decodedMyBytes, StandardCharsets.UTF_8);
//            String decodedOpponentUrl = new String(decodedOpponentBytes, StandardCharsets.UTF_8);
//
//            String pythonApiUrl = "http://host.docker.internal:8000/merge";
//            String jsonInput = "{\"dataA\": \"" + escapeJson(decodedMyUrl) + "\", \"dataB\": \"" + escapeJson(decodedOpponentUrl) + "\"}";
//
//            URL url = new URL(pythonApiUrl);
//            connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("POST");
//            connection.setRequestProperty("Content-Type", "application/json; utf-8");
//            connection.setConnectTimeout(10000);
//            connection.setReadTimeout(30000);
//            connection.setDoOutput(true);
//
//            try (OutputStream os = connection.getOutputStream()) {
//                byte[] input = jsonInput.getBytes(StandardCharsets.UTF_8);
//                os.write(input, 0, input.length);
//            }
//
//            int responseCode = connection.getResponseCode();
//			if (responseCode != HttpURLConnection.HTTP_OK) {
//				String errorResponse = "";
//				try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getErrorStream(), StandardCharsets.UTF_8))) {
//					String responseLine;
//					while ((responseLine = br.readLine()) != null) { errorResponse += responseLine.trim(); }
//				} catch (Exception readEx) { /* ignore */ }
//				throw new RuntimeException("Python API Error: HTTP " + responseCode + " - " + errorResponse);
//			}
//
//            StringBuilder response = new StringBuilder();
//            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
//                String responseLine = null;
//                while ((responseLine = br.readLine()) != null) { response.append(responseLine.trim()); }
//            }
//
//            String mergedPlainText = response.toString();
//            return Base64.getEncoder().encodeToString(mergedPlainText.getBytes(StandardCharsets.UTF_8));
//
//        } catch (Exception e) {
//			System.err.println("!!! Exception in mergeCanvas !!!");
//            e.printStackTrace();
//            throw new RuntimeException("Failed to call or process Python merge service.", e);
//        } finally {
//            if (connection != null) {
//                connection.disconnect();
//            }
//        }
//	}
	@Override
	public String mergeCanvas(String myUrl, String opponentUrl) {
		HttpURLConnection connection = null;
		try {
			byte[] decodedMyBytes = Base64.getDecoder().decode(myUrl);
			byte[] decodedOpponentBytes = Base64.getDecoder().decode(opponentUrl);
			String decodedMyUrl = new String(decodedMyBytes, StandardCharsets.UTF_8);
			String decodedOpponentUrl = new String(decodedOpponentBytes, StandardCharsets.UTF_8);

			String pythonApiUrl = "http://host.docker.internal:8000/merge"; // Docker対応済み
			String jsonInput = "{\"dataA\": \"" + escapeJson(decodedMyUrl) + "\", \"dataB\": \"" + escapeJson(decodedOpponentUrl) + "\"}";

            // ▼▼▼ デバッグログ追加 (1/2) ▼▼▼
            System.out.println("--- [CanvasServiceImpl] Python API Request ---");
            System.out.println("MyData Length: " + decodedMyUrl.length());
            System.out.println("OpponentData Length: " + decodedOpponentUrl.length());
         // 「全データプレビューor先頭200文字」
            System.out.println("MyData : " + decodedMyUrl);
            System.out.println("OpponentData : " + decodedOpponentUrl);
//            System.out.println("MyData (Preview): " + (decodedMyUrl.length() > 200 ? decodedMyUrl.substring(0, 200) : decodedMyUrl));
//            System.out.println("OpponentData (Preview): " + (decodedOpponentUrl.length() > 200 ? decodedOpponentUrl.substring(0, 200) : decodedOpponentUrl));
            System.out.println("----------------------------------------------");
            // ▲▲▲ デバッグログ追加 ▲▲▲

            URL url = new URL(pythonApiUrl);
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(30000);

            connection.setDoOutput(true);

            try (OutputStream os = connection.getOutputStream()) {
            	byte[] input = jsonInput.getBytes(StandardCharsets.UTF_8);
            	os.write(input, 0, input.length);
            	}

            int responseCode = connection.getResponseCode();
			if (responseCode != HttpURLConnection.HTTP_OK) {
				String errorResponse = "";
				try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getErrorStream(), StandardCharsets.UTF_8))) {
					String responseLine;
					while ((responseLine = br.readLine()) != null) { errorResponse += responseLine.trim(); }
				} catch (Exception readEx) { /* ignore */ }
				throw new RuntimeException("Python API Error: HTTP " + responseCode + " - " + errorResponse);
			}


            StringBuilder response = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
            	String responseLine = null;
            	while ((responseLine = br.readLine()) != null) { response.append(responseLine.trim()); }
            	}

            String mergedPlainText = response.toString();

            // ▼▼▼ デバッグログ追加 (2/2) ▼▼▼
            System.out.println("--- [CanvasServiceImpl] Python API Response ---");
            System.out.println("MergedData Length: " + mergedPlainText.length());
            System.out.println("MergedData : " + mergedPlainText);
//            System.out.println("MergedData Length: " + mergedPlainText.length());
//            System.out.println("MergedData (Preview): " + (mergedPlainText.length() > 200 ? mergedPlainText.substring(0, 200) : mergedPlainText));
            System.out.println("-----------------------------------------------");
            // ▲▲▲ デバッグログ追加 ▲▲▲

            return Base64.getEncoder().encodeToString(mergedPlainText.getBytes(StandardCharsets.UTF_8));

		} catch (Exception e) {
			System.err.println("!!! Exception in mergeCanvas !!!");
			e.printStackTrace();
			throw new RuntimeException("Failed to call or process Python merge service.", e);
	        } finally {
          if (connection != null) {
              connection.disconnect();
          }
      }
	}
	private String escapeJson(String str) {
	    if (str == null) { return ""; }
	    return str.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n").replace("\r", "\\r").replace("\t", "\\t").replace("\f", "\\f").replace("\b", "\\b");
	}
}

