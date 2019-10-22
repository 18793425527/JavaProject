package com.briup.dao;

import java.util.List;

import com.briup.bean.Customer;
import com.briup.bean.ShopCar;

public interface ShopCarDao {
	
	List<ShopCar> findByCustomer(Customer customer);
	
	void saveShopCar(ShopCar shopCar);
	
	void updateShopCar(ShopCar shopCar);
	
	void clearShopCarByCustomer(Customer customer);

}
