package com.im.model.pickuppoint.persistance;

import java.util.List;

import com.im.model.pickuppoint.entity.PickupPoint;

public interface PickupPointPersistance {

public List<PickupPoint> findAll();

public List<PickupPoint> findPickupPointsByRouteId(long routeId);

public PickupPoint findById(long id);


}
