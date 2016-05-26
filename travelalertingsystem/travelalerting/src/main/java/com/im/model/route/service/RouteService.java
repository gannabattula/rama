package com.im.model.route.service;

import java.util.List;

import com.im.framework.exceptions.AppException;
import com.im.framework.exceptions.ValidationException;
import com.im.model.route.entity.Route;


public interface RouteService {

	public List<Route> getAllRoutes() throws ValidationException,AppException;

	 Route getRouteById(Long Id)throws AppException, ValidationException;
}
