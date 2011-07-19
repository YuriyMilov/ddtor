package gu.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("db")
public interface dbService extends RemoteService {
	String[][] getData(String name);

}