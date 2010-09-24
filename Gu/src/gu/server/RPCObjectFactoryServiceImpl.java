package gu.server;

import gu.client.RPCObjectFactoryService;
import gu.client.model.BaseObject;
import gu.client.model.Consignee;
import gu.client.model.Shipper;
import gu.client.model.Story;
import gu.client.model.User;
import gu.client.model.Worder;

import java.util.ArrayList;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class RPCObjectFactoryServiceImpl extends RemoteServiceServlet implements
		RPCObjectFactoryService {

	public void addTo(String type, String Id, String member,
			BaseObject objectToAdd) {
		if (type.equals("Story") && member.equals("digs")) {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			pm.setDetachAllOnCommit(true);
			try {
				Story story = (Story) pm.getObjectById(Story.class,
						Long.parseLong(Id));
				long userId = Long.parseLong(objectToAdd.getId());
				User user = (User) pm.getObjectById(User.class, userId);
				if (story.getDigKeys() == null)
					story.setDigKeys(new ArrayList<Long>());
				story.getDigKeys().add(userId);
				pm.makePersistent(story);
			} finally {
				pm.close();
			}
		}
		
		if (type.equals("Worder") && member.equals("wigs")) {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			pm.setDetachAllOnCommit(true);
			try {
				Worder story = (Worder) pm.getObjectById(Worder.class,
						Long.parseLong(Id));
				long userId = Long.parseLong(objectToAdd.getId());
				User user = (User) pm.getObjectById(User.class, userId);
				if (story.getDigKeys() == null)
					story.setDigKeys(new ArrayList<Long>());
				story.getDigKeys().add(userId);
				pm.makePersistent(story);
			} finally {
				pm.close();
			}
		}
	}

	public List<BaseObject> getAll(String type) {
		List result = null;

		PersistenceManager pm = PMF.get().getPersistenceManager();
		pm.setDetachAllOnCommit(true);
		try {
			Query query = null;

			if (type.equals("Worder"))
				query = pm.newQuery(Worder.class);
			if (type.equals("User"))
				query = pm.newQuery(User.class);
			if (type.equals("Story"))
				query = pm.newQuery(Story.class);
			if (type.equals("Shipper"))
				query = pm.newQuery(Shipper.class);
			if (type.equals("Consignee"))
				query = pm.newQuery(Consignee.class);

			result = new ArrayList<BaseObject>(
					(List<BaseObject>) query.execute());
		} finally {
			pm.close();
		}

		// clear persistent lists
		if (type.equals("Story")) {
			for (int i = 0; i < result.size(); ++i) {
				((Story) result.get(i)).setDigs(null);
				((Story) result.get(i)).setDigKeys(null);
			}
		}
		
		// clear persistent lists
		if (type.equals("Worder")) {
			for (int i = 0; i < result.size(); ++i) {
				((Worder) result.get(i)).setDigs(null);
				((Worder) result.get(i)).setDigKeys(null);
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

		if (type.equals("Story") && member.equals("digs")) {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			pm.setDetachAllOnCommit(true);
			try {
				Story story = (Story) pm.getObjectById(Story.class,
						Long.parseLong(Id));
				result = new ArrayList<BaseObject>();
				List<Long> digKeys = story.getDigKeys();
				if (digKeys != null) {
					for (Long k : digKeys) {
						result.add(pm.getObjectById(User.class, k));
					}
				}
			} finally {
				pm.close();
			}

		}
		
		if (type.equals("Worder") && member.equals("digs")) {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			pm.setDetachAllOnCommit(true);
			try {
				Worder story = (Worder) pm.getObjectById(Worder.class,
						Long.parseLong(Id));
				result = new ArrayList<BaseObject>();
				List<Long> digKeys = story.getDigKeys();
				if (digKeys != null) {
					for (Long k : digKeys) {
						result.add(pm.getObjectById(User.class, k));
					}
				}
			} finally {
				pm.close();
			}

		}

		return result;
	}

	public BaseObject getById(String type, String Id) {
		BaseObject result = null;

		PersistenceManager pm = PMF.get().getPersistenceManager();
		pm.setDetachAllOnCommit(true);
		try {
			if (type.equals("User"))
				result = pm.getObjectById(User.class, Long.parseLong(Id));
			if (type.equals("Story"))
				result = pm.getObjectById(Story.class, Long.parseLong(Id));
			if (type.equals("Worder"))
				result = pm.getObjectById(Worder.class, Long.parseLong(Id));
			if (type.equals("Shipper"))
				result = pm.getObjectById(Shipper.class, Long.parseLong(Id));
			if (type.equals("Consignee"))
				result = pm.getObjectById(Consignee.class, Long.parseLong(Id));

		} finally {
			pm.close();
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
			if (type.equals("Worder"))
				pm.deletePersistent(pm.getObjectById(Worder.class,
						Long.parseLong(id)));
			if (type.equals("User"))
				pm.deletePersistent(pm.getObjectById(User.class,
						Long.parseLong(id)));
			if (type.equals("Shipper"))
				pm.deletePersistent(pm.getObjectById(Shipper.class,
						Long.parseLong(id)));
			if (type.equals("Story"))
				pm.deletePersistent(pm.getObjectById(Story.class,
						Long.parseLong(id)));
			if (type.equals("Consignee"))
				pm.deletePersistent(pm.getObjectById(Consignee.class,
						Long.parseLong(id)));
		} finally {
			pm.close();
		}

	}

}
