package com.im.model.route.service;

import com.im.framework.exceptions.AppException;
import com.im.framework.exceptions.ValidationException;
import com.im.model.route.entity.Route;


public interface RouteService {

	public Route getAllRoutes() throws ValidationException,AppException;

}
