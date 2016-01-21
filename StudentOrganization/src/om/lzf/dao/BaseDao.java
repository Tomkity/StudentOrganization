package om.lzf.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * ������DAO��(������������һ�ű��)
 * 
 * @author lujun
 * 
 */
public class BaseDao extends HibernateDaoSupport {
	// ʵ�������������(����.����)
	private String poName;

	public void setPoName(String poName) {
		this.poName = poName;
	}

	/**
	 * ��ѯ����ʵ�����(�������Զ���)
	 * 
	 * @return ʵ�����ļ���
	 */
	public List findAll() {
		String hql = "from " + poName;
		HibernateTemplate template = getHibernateTemplate();
		List list = template.find(hql);
		return list;
	}

	/**
	 * ����HQL���Ͳ���ֵ�����ѯ���ж�������鼯��
	 * 
	 * @param hql���
	 * @param hql����в���ֵ������
	 * @return ʵ����������ļ���
	 */
	public List findObjects(String hql, Object... params) {
		HibernateTemplate template = getHibernateTemplate();
	//	org.springframework.orm.hibernate3.support.OpenSessionInViewFilter
		List list = template.find(hql, params);
		return list;
	}

	/**
	 * ����ʵ��������������ID��ѯʵ�����
	 * 
	 * @param ����id
	 * @return ʵ�����
	 */
	public Object findById(Serializable id) {
		HibernateTemplate template = getHibernateTemplate();
		Object object = template.get(poName, id);
		return object;
	}

	/**
	 * ���ʵ�����
	 * 
	 * @param ʵ�����
	 * @return true��ʾ�ɹ���false��ʾʧ��
	 */
	public boolean save(Object entity) {
		HibernateTemplate template = getHibernateTemplate();
		try {
			template.save(entity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * ��������idɾ��ʵ�����
	 * 
	 * @param ����id
	 * @return true��ʾ�ɹ���false��ʾʧ��
	 */
	public boolean deleteById(Serializable id) {
		HibernateTemplate template = getHibernateTemplate();
		// ���Ҫɾ���Ķ���
		Object obj = this.findById(id);
		try {
			template.delete(obj);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * �޸�ʵ�����
	 * 
	 * @param ʵ�����
	 * @return true��ʾ�ɹ���false��ʾʧ��
	 */
	public boolean update(Object entity) {
		HibernateTemplate template = getHibernateTemplate();
		try {
			template.update(entity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
   /**
    * ������޸Ĳ�������(�����������)�����ǲ�����������ɾ�����͵����������
    * @param hql
    * @param params
    * @return
    */
	public boolean update(final String hql, final Object... params) {
		boolean flag = false;
		HibernateTemplate template = getHibernateTemplate();
		flag = template.execute(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				// �õ�Query����
				Query query = session.createQuery(hql);
				for (int i = 0; i < params.length; i++) {
					query.setParameter(i, params[i]);
				}

				int m = query.executeUpdate();
				if (m > 0) {
					return true;
				} else {
					return false;
				}

			}

		});
		return flag;

	}

	/**
	 * ����hql����ѯ����Ψһ����ķ���(ͳ�ƺ���Max,SUM,Count�õõ�)
	 * 
	 * @param hql
	 * @return
	 */
	public Object getUnqueResult(String hql, Object... params) {
		Object obj = this.findObjects(hql, params).get(0);
		return obj;

	}

	/**
	 * ���ݲ�ѯ���͵�ǰҳҳ�ţ�ÿҳ�������ص�ǰҳ�����ݼ���
	 * 
	 * @param hql
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	public List getPageData(final String hql, final int pageSize,
			final int pageNo) {
		HibernateTemplate template = getHibernateTemplate();
		List list = template.executeFind(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				// �õ�Query����
				Query query = session.createQuery(hql);
				// ���õ�ǰҳ��1����¼������
				query.setFirstResult((pageNo - 1) * pageSize);
				// ����ÿҳ���������¼
				query.setMaxResults(pageSize);
				return query.list();
			}
		});
		return list;
	}
}
