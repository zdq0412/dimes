package com.digitzones.dao.impl;
import org.springframework.stereotype.Repository;
import com.digitzones.dao.IPositionDao;
import com.digitzones.model.Position;
@Repository
public class PositionDaoImpl extends CommonDaoImpl<Position> implements IPositionDao {
	public PositionDaoImpl() {
		super(Position.class);
	}
}
