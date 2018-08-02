package com.digitzones.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import com.digitzones.dao.IEquipmentDao;
import com.digitzones.model.Equipment;
@Repository
public class EquipmentDaoImpl extends CommonDaoImpl<Equipment> implements IEquipmentDao {

	public EquipmentDaoImpl() {
		super(Equipment.class);
	}

	@Override
	public Serializable addEquipment(Equipment equipment, File pic) {
		if(pic!=null) {
			FileInputStream input = null;
			try {
				input = new FileInputStream(pic);
				equipment.setPic(Hibernate.getLobCreator(getSession()).createBlob(input, input.available()));
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}finally {
				if(input!=null) {
					try {
						input.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return this.save(equipment);
	}
}
