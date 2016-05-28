package com.im.model.pickuppoint.service;

import java.util.List;

import com.im.framework.exceptions.ValidationException;
import com.im.model.pickuppoint.entity.PickupPoint;

public interface PickupPointService {

	

public List<PickupPoint> getAllPickupPoints();

public List<PickupPoint> getPickupPointsByRouteId(long routeId) throws ValidationException;

public PickupPoint getByPickupPointId(long id) throws ValidationException;
}
