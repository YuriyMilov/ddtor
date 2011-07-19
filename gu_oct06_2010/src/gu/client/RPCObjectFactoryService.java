package gu.client;

import gu.client.model.BaseObject;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;

public interface RPCObjectFactoryService extends RemoteService{

	List<BaseObject> getAll( String type );
	List<BaseObject> getAllFrom( String type, String Id, String member );
	BaseObject getById( String type, String Id );
	void save( BaseObject object );	
	void delete( String type, String id );
	void addTo(String type, String Id, String member, BaseObject objectToAdd);
}