package com.objetdirect.gwt.umldrawer.client.canvas;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.objetdirect.gwt.umldrawer.client.beans.EditEvent;
import com.objetdirect.gwt.umldrawer.client.beans.SaveEventInfo;

/**
 * CanvasServiceの非同期インターフェース。
 * CanvasService.java にメソッドを追加したことに伴い、
 * こちらにも対応する非同期メソッドを追加します。
 */
public interface CanvasServiceAsync {

	// --- 既存のメソッドの非同期版 ---
	void saveCanvas(String studentId, int exercisesId, String canvasUrl, AsyncCallback<Void> callback);

	void loadCanvas(String studentId, int exercisesId, AsyncCallback<EditEvent> callback);

	void undo(String studentId, int exercisesId, AsyncCallback<EditEvent> callback);

	void saveCanvasAsAnswer(String studentId, int exercisesId, String canvasUrl, AsyncCallback<Boolean> callback);

	void getAnswer(int exerciseId, AsyncCallback<String> callback);
	
	// --- マージ機能のために追加するメソッドの非同期版 ---
	void mergeCanvas(String myUrl, String opponentUrl, AsyncCallback<String> callback);
	
	void getRecentSaves(int exerciseId, String currentStudentId, AsyncCallback<List<SaveEventInfo>> callback);
	
	void saveMergedCanvas(String commonId, int exerciseId, String canvasUrl, AsyncCallback<Void> callback);

}
