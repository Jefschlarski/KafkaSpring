package com.portal.api.service;

import com.portal.api.dto.CarPostDTO;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface CarPostStoreService {
    List<CarPostDTO> getCarForSales();

    void changeCarForSale(CarPostDTO carPost, String id);

    void removeCarForSale(String id);
}
