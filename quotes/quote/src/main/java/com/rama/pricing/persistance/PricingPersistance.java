package com.rama.pricing.persistance;

import com.rama.pricing.entity.Pricing;

public interface PricingPersistance {

	Pricing findPrincingDetailsByProductId(Integer productId);


	Pricing create(Pricing pricing);

	void delete(Integer pricingId);

}
