package gu.server;

import gu.PMF;
import gu.client.RPCObjectFactoryService;
import gu.client.model.BaseObject;
import gu.client.model.Story;
import gu.client.model.User;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class RPCObjectFactoryServiceImpl  
	extends RemoteServiceServlet 
	implements RPCObjectFactoryService {

	public void addTo(String type, String Id, String member, BaseObject objectToAdd) {
		if( type.equals("Story") && member.equals("digs") ){
			PersistenceManager pm = PMF.get().getPersistenceManager();
		    pm.setDetachAllOnCommit(true);
	        try {
	        	Story story = (Story)pm.getObjectById(Story.class, Long.parseLong(Id) );
	        	long userId = Long.parseLong(objectToAdd.getId());
	        	User user = (User)pm.getObjectById(User.class, userId );
	        	if( story.getDigKeys() == null )
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
        	if( type.equals("User"))
        		query = pm.newQuery(User.class);
        	else
        		query = pm.newQuery(Story.class);
    	    result = new ArrayList<BaseObject>((List<BaseObject>) query.execute());
        } finally {
            pm.close();
        }
	    
        //clear persistent lists
		if( type.equals("Story") ){
			for( int i = 0; i< result.size(); ++i ){
				((Story)result.get(i)).setDigs( null );
				((Story)result.get(i)).setDigKeys( null );
			}
		}
		return result;
	}

	public List<BaseObject> getAllFrom(String type, String Id, String member) {
		List result = new ArrayList();
		if( type.equals("Story") && member.equals("digs") ){
			PersistenceManager pm = PMF.get().getPersistenceManager();
		    pm.setDetachAllOnCommit(true);
	        try {
	        	Story story = (Story)pm.getObjectById(Story.class, Long.parseLong(Id) );
	        	result = new ArrayList<BaseObject>();
	        	List<Long> digKeys = story.getDigKeys();
	        	if( digKeys != null ){
	        		for( Long k : digKeys ){
	        			result.add( pm.getObjectById(User.class, k) );
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
        	if( type.equals("User"))
        		result = pm.getObjectById(User.class, Long.parseLong(Id) );
        	else
        		result = pm.getObjectById(Story.class, Long.parseLong(Id) );
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

        	if( type.equals("User"))
        		pm.deletePersistent( pm.getObjectById(User.class, Long.parseLong(id) ));
        	else
        		pm.deletePersistent(pm.getObjectById(Story.class, Long.parseLong(id) ));
        } finally {
            pm.close();
        }
        
	}

}
