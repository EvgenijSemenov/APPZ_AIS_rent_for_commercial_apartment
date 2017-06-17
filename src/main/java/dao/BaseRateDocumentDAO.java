package dao;

import entity.BaseRateDocument;
import entity.Organization;
import hibernate.HibernateConnector;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class BaseRateDocumentDAO {
    public List<BaseRateDocument> all() {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            Query query = session.createQuery("from BaseRateDocument");

            List queryList = query.list();
            if (queryList != null && queryList.isEmpty()) {
                return null;
            } else {
                return queryList;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
}
