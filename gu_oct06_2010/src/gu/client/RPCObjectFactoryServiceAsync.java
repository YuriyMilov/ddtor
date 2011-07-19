package gu.client;

import gu.client.model.BaseObject;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface RPCObjectFactoryServiceAsync{
	void getAll( String type, AsyncCallback callback );
	void getAllFrom( String type, String Id, String member, AsyncCallback callback );
	void getById( String type, String Id, AsyncCallback callback );
	void save( BaseObject object, AsyncCallback callback );	
	void delete( String type, String id, AsyncCallback callback );
	void addTo(String type, String Id, String member, BaseObject objectToAdd, AsyncCallback callback );
}