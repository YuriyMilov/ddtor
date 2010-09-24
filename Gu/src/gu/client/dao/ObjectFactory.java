package gu.client.dao;

import gu.client.model.BaseObject;

public interface ObjectFactory {
	public interface ObjectDAO {
		void getById( String id, ObjectListener handler );
		void getAll( CollectionListener handler );
		void getAllFrom( BaseObject object, String member, CollectionListener handler );
		void save( BaseObject object );
		void delete( BaseObject object );
		void addTo( BaseObject object, String string, BaseObject objectToAdd);
	}
	ObjectDAO getShipperDAO();
	ObjectDAO getConsigneeDAO();
	ObjectDAO getUserDAO();
	ObjectDAO getStoryDAO();
	ObjectDAO getWorderDAO();
	void setListener( ObjectFactoryListener listener );
}
