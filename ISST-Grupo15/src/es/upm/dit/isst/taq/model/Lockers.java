package es.upm.dit.isst.taq.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Lockers {
	
	@Id
	private Integer id;
	
	private Integer lockerNumber;
	
	private Integer lockerStateId;
	
	private Integer locationId;
	
	private Date createdAt;
	
	private Date updatedAt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLockerNumber() {
		return lockerNumber;
	}

	public void setLockerNumber(Integer lockerNumber) {
		this.lockerNumber = lockerNumber;
	}

	public Integer getLockerStateId() {
		return lockerStateId;
	}

	public void setLockerStateId(Integer lockerStateId) {
		this.lockerStateId = lockerStateId;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	
}
