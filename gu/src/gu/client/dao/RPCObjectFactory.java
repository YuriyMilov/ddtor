package gu.client.dao;

import gu.client.RPCObjectFactoryService;
import gu.client.RPCObjectFactoryServiceAsync;
import gu.client.model.BaseObject;
import java.util.List;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class RPCObjectFactory implements ObjectFactory{

	private ObjectFactoryListener listener;
	protected class RPCObjectDAO implements ObjectDAO {
		private final String type;
		public RPCObjectDAO( String type ){
			this.type = type;
		}

		public void getAll(CollectionListener handler) {
			listener.onLoadingStart();
			service.getAll(type, new CollectionCallback( handler ) );
		}

		public void getAllFrom(BaseObject object, String member, CollectionListener handler) {
			listener.onLoadingStart();
			service.getAllFrom(type, object.getId(), member, new CollectionCallback( handler ) );
		}

		public void getById(String id, ObjectListener handler) {
			listener.onLoadingStart();
			service.getById(type, id, new ObjectCallback( handler ) );
		}

		public void save(BaseObject object) {
			listener.onLoadingStart();
			service.save( object , new RefreshCallback() );
		}
		
		public void delete(BaseObject object) {
			listener.onLoadingStart();
			service.delete( type, object.getId(), new RefreshCallback() );
		}

		public void addTo(BaseObject object, String member, BaseObject objectToAdd) {
			listener.onLoadingStart();
			service.addTo( type, object.getId(), member, objectToAdd, new RefreshCallback() );
		}
	}
	
	protected class CollectionCallback implements AsyncCallback{
		private CollectionListener handler;
		public CollectionCallback(CollectionListener handler) {
			this.handler = handler;
		}
		public void onFailure(Throwable exception) 
			{ GWT.log( "error", exception );listener.onLoadingFinish(); }
		public void onSuccess(Object result) {
			handler.onCollection((List)result);
			listener.onLoadingFinish();
		}
	}	
	
	protected class ObjectCallback implements AsyncCallback{
		private ObjectListener handler;
		public ObjectCallback(ObjectListener handler) {
			this.handler = handler;
		}
		public void onFailure(Throwable exception) 
			{ GWT.log( "error", exception ); listener.onLoadingFinish(); }
		public void onSuccess(Object result) {
			handler.onObject((BaseObject)result);
			listener.onLoadingFinish();
		}
	}	
	
	protected class RefreshCallback implements AsyncCallback{
		public void onFailure(Throwable exception) 
			{ GWT.log( "error", exception ); listener.onLoadingFinish(); }
		public void onSuccess(Object result) {
			listener.onRefresh();
			listener.onLoadingFinish();
		}
	}
	
	private RPCObjectDAO shipperDAO = new RPCObjectDAO("Shipper");
	private RPCObjectDAO consigneeDAO = new RPCObjectDAO("Consignee");
	
	private RPCObjectFactoryServiceAsync service;
	public RPCObjectFactory(String baseUrl) {
		service = (RPCObjectFactoryServiceAsync) GWT.create( RPCObjectFactoryService.class );
		ServiceDefTarget endpoint = (ServiceDefTarget) service;
		endpoint.setServiceEntryPoint( baseUrl );	
	}


	public void setListener(ObjectFactoryListener listener) {
		this.listener = listener;
	}

	@Override
	public ObjectDAO getShipperDAO() {
		return shipperDAO;
	}


	@Override
	public ObjectDAO getConsigneeDAO() {
		return consigneeDAO;
	}


}
