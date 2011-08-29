package tmr.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface SrvAsync {
	void set_id(String name, String psw, AsyncCallback<String> callback)
	throws IllegalArgumentException;
	void get(String name, String psw, AsyncCallback<String> callback)
	throws IllegalArgumentException;
}
