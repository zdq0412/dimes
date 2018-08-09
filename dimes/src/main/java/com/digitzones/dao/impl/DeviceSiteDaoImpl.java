package com.digitzones.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.digitzones.dao.IDeviceSiteDao;
import com.digitzones.model.DeviceSite;
@Repository
public class DeviceSiteDaoImpl extends CommonDaoImpl<DeviceSite> implements IDeviceSiteDao {
	public DeviceSiteDaoImpl() {
		super(DeviceSite.class);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Long queryCountOfDeviceSite() {
		return (Long) this.getHibernateTemplate().find("select count(*) from DeviceSite ds where ds.device.productionUnit!=null",new Object[] {}).get(0);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Long queryCountOfDeviceSiteByStatus(String status) {
		return (Long) this.getHibernateTemplate().find("select count(*) from DeviceSite ds where ds.status=?0",new Object[] {status}).get(0);
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<DeviceSite> queryDeviceSitesByClassesId(Long classesId) {
		String sql = "select distinct ds.id,ds.code,ds.name,ds.disabled,ds.note,ds.barCodeAddress, "
				+ " ds.goalOee,ds.status,ds.DEVICE_ID,ds.show from DEVICESITE ds inner join DEVICE d on ds.DEVICE_ID=d.id inner join CLASSES_DEVICE cd on d.id=cd.DEVICE_ID where cd.CLASSES_ID=?0";
		return getSession().createSQLQuery(sql).setParameter(0, classesId).addEntity(DeviceSite.class).list();
	}

	@SuppressWarnings("deprecation")
	@Override
	public Integer queryLostTimeCountByDeviceSiteId(Long deviceSiteId) {
		String sql = "select count(id) from LOSTTIMERECORD  where deviceSite_id=?0";
		Integer count = (Integer) getSession().createSQLQuery(sql).setParameter(0, deviceSiteId).uniqueResult();
		return count==null?0:count;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Integer queryPressLightCountByDeviceSiteId(Long deviceSiteId) {
		String sql = "select count(id) from PRESSLIGHTRECORD  where deviceSite_id=?0";
		Integer count = (Integer) getSession().createSQLQuery(sql).setParameter(0, deviceSiteId).uniqueResult();
		return count==null?0:count;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Integer queryProcessDeviceSiteMappingCountByDeviceSiteId(Long deviceSiteId) {
		String sql = "select count(id) from PROCESS_DEVICESITE  where deviceSite_id=?0";
		Integer count = (Integer) getSession().createSQLQuery(sql).setParameter(0, deviceSiteId).uniqueResult();
		return count==null?0:count;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<BigDecimal> queryDeviceSiteIdsByProductionUnitId(Long productionUnitId) {
		String sql = "select ds.id from devicesite ds inner join device d on ds.device_id = d.id"
				+ " inner join productionunit p on d.productionunit_id=p.id where p.id=?0";
		List<BigDecimal> ids = getSession().createSQLQuery(sql).setParameter(0, productionUnitId).list();
		return ids;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<BigDecimal> queryAllDeviceSiteIds() {
		String sql = "select ds.id from devicesite ds";
		List<BigDecimal> ids = getSession().createSQLQuery(sql).list();
		return ids;
	}
}
