package com.im.model.pickuppoint.entity;

public class PickupPoint {

	/*id bigint(20) PK 
	latitude decimal(10,2) 
	longitude decimal(10,2) 
	vehicle_id bigint(20) 
	student_id bigint(20) 
	pickup_point_name varchar(45)*/
	
	private long pickupPointId ;
	private double latitude ;
	private double longitude;
	private long vehicleId;
	private long studentId;
	private String pickupPointName;
	private long routeId;
	
	
	
	
	public long getRouteId() {
		return routeId;
	}
	public void setRouteId(long routeId) {
		this.routeId = routeId;
	}
	public long getPickupPointId() {
		return pickupPointId;
	}
	public void setPickupPointId(long pickupPointId) {
		this.pickupPointId = pickupPointId;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public long getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(long vehicleId) {
		this.vehicleId = vehicleId;
	}
	public long getStudentId() {
		return studentId;
	}
	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}
	public String getPickupPointName() {
		return pickupPointName;
	}
	public void setPickupPointName(String pickupPointName) {
		this.pickupPointName = pickupPointName;
	}
	
	
	
}
