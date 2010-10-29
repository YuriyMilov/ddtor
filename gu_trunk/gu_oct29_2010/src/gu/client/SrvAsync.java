package gu.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface SrvAsync {
	void set_id(String input, AsyncCallback<String> callback)
	throws IllegalArgumentException;
	void get(String input, AsyncCallback<String> callback)
	throws IllegalArgumentException;
}
