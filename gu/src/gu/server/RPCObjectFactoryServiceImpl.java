package gu.server;

import gu.client.RPCObjectFactoryService;
import gu.client.model.BaseObject;
import gu.client.model.Consignee;
import gu.client.model.Shipper;
import java.util.ArrayList;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;


public class RPCObjectFactoryServiceImpl extends RemoteServiceServlet implements
		RPCObjectFactoryService {

	public void addTo(String type, String Id, String member,
			BaseObject objectToAdd) {
		
		if( type.equals("Story") && member.equals("digs") ){
			PersistenceManager pm = PMF.get().getPersistenceManager();
		    pm.setDetachAllOnCommit(true);
	        try {
	   /*     	Story story = (Story)pm.getObjectById(Story.class, Long.parseLong(Id) );
	        	long userId = Long.parseLong(objectToAdd.getId());
	        	User user = (User)pm.getObjectById(User.class, userId );
	        	if( story.getDigKeys() == null )
	        		story.setDigKeys(new ArrayList<Long>());
	        	story.getDigKeys().add(userId);
	        	pm.makePersistent(story);*/
	        } finally {
	            pm.close();
	        }
		}	
	}

	public List<BaseObject> getAll(String type) {
		List result = null;
		if (type.equals("Shipper")) {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			pm.setDetachAllOnCommit(true);

			try {
				Query query = pm.newQuery(Shipper.class);
				result = new ArrayList<BaseObject>(
						(List<BaseObject>) query.execute());
			} finally {
				pm.close();
			}
		}
		if (type.equals("Consignee")) {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			pm.setDetachAllOnCommit(true);

			try {
				Query query = pm.newQuery(Consignee.class);
				result = new ArrayList<BaseObject>(
						(List<BaseObject>) query.execute());
			} finally {
				pm.close();
			}
		}
		return result;
	}

	public List<BaseObject> getAllFrom(String type, String Id, String member) {
		List result = new ArrayList();
		if (type.equals("Shipper")) {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			pm.setDetachAllOnCommit(true);
			try {
				Shipper story = (Shipper) pm.getObjectById(Shipper.class,
						Long.parseLong(Id));
				result = new ArrayList<BaseObject>();
			} finally {
				pm.close();
			}

		}
		
		if (type.equals("Consignee")) {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			pm.setDetachAllOnCommit(true);
			try {
				Consignee story = (Consignee) pm.getObjectById(Consignee.class,
						Long.parseLong(Id));
				result = new ArrayList<BaseObject>();
			} finally {
				pm.close();
			}

		}
		return result;
	}

	public BaseObject getById(String type, String Id) {
		BaseObject result = null;
		if (type.equals("Shipper")) {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			pm.setDetachAllOnCommit(true);
			try {
				result = pm.getObjectById(Shipper.class, Long.parseLong(Id));
			} finally {
				pm.close();
			}
			}

		if (type.equals("Consignee")) {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			pm.setDetachAllOnCommit(true);
			try {
				result = pm.getObjectById(Consignee.class, Long.parseLong(Id));
			} finally {
				pm.close();
			}
			}

		return result;
	}
	
	public void save(BaseObject object) {

		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {

			pm.makePersistent(object);
		} finally {
			pm.close();
		}
	}

	public void delete(String type, String id) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.deletePersistent(pm.getObjectById(Shipper.class,
					Long.parseLong(id)));
		} finally {
			pm.close();
		}

	}

}
