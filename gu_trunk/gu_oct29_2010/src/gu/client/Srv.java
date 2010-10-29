package gu.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("srv")
public interface Srv extends RemoteService {
	String set_id(String name) throws IllegalArgumentException;
	String get(String name) throws IllegalArgumentException;
}
