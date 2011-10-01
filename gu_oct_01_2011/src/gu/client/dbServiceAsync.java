package gu.client;

import com.google.gwt.user.client.rpc.AsyncCallback;


public interface dbServiceAsync {
	void getData(String string, AsyncCallback<String[][]> asyncCallback);
}
