package com.im.model.route.entity;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Route {

	
	
	private long routeId ;
	private String routeType ;
	private  String name;
	private double startlatitude;
	private double startLongitude;
	private double endLongitude;
	private double endLatitude;
	
	
	public long getRouteId() {
		return routeId;
	}
	public void setRouteId(long routeId) {
		this.routeId = routeId;
	}
	public String getRouteType() {
		return routeType;
	}
	public void setRouteType(String routeType) {
		this.routeType = routeType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getStartlatitude() {
		return startlatitude;
	}
	public void setStartlatitude(double startlatitude) {
		this.startlatitude = startlatitude;
	}
	public double getStartLongitude() {
		return startLongitude;
	}
	public void setStartLongitude(double startLongitude) {
		this.startLongitude = startLongitude;
	}
	public double getEndLongitude() {
		return endLongitude;
	}
	public void setEndLongitude(double endLongitude) {
		this.endLongitude = endLongitude;
	}
	public double getEndLatitude() {
		return endLatitude;
	}
	public void setEndLatitude(double endLatitude) {
		this.endLatitude = endLatitude;
	}
	
	
	
}
