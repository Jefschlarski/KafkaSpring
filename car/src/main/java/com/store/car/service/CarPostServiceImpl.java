package com.store.car.service;

import com.store.car.dto.CarPostDTO;
import com.store.car.entity.CarPostEntity;
import com.store.car.repository.CarPostRepository;
import com.store.car.repository.OwnerPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CarPostServiceImpl implements CarPostService {

    @Autowired
    private CarPostRepository carPostRepository;

    @Autowired
    private OwnerPostRepository ownerPostRepository;

    @Override
    public void newPostDetails(CarPostDTO carPostDTO) {
        CarPostEntity carPostEntity = mapCarDtoToEntity(carPostDTO);
        carPostRepository.save(carPostEntity);
    }

    @Override
    public List<CarPostDTO> getCarSales() {
        List<CarPostDTO> listCarsSales = new ArrayList<>();
        carPostRepository.findAll().forEach(item -> {
            listCarsSales.add(mapCarEntityToDTO(item));
        });
        return listCarsSales;
    }

    // Metodo change
    @Override
    public void changeCarSale(CarPostDTO carPostDTO, Long postId) {
        // buscando pelo id usando o carPostRepository e o metodo findByID
        // IfPresentOrElse é um metodo de if else, se tiver um item presente ele vai
        // atualizar o objeto senão ele pula
        carPostRepository.findById(postId).ifPresentOrElse(item -> {
            item.setDescription(carPostDTO.getDescription());
            item.setContact(carPostDTO.getContact());
            item.setPrice(carPostDTO.getPrice());
            item.setBrand(carPostDTO.getBrand());
            item.setEngineVersion(carPostDTO.getEngineVersion());
            item.setModel(carPostDTO.getModel());

            carPostRepository.save(item); // salva o elemento
        }, () -> {
            throw new NoSuchElementException();
        });
    }

    // Metodo delete
    @Override
    public void removeCarSale(Long postId) {
        carPostRepository.deleteById(postId); // deleta o objeto utilizando o id e o metedo deleteById
    }

    // Mapeamento que recebe um DTO e devolve um Entity
    private CarPostEntity mapCarDtoToEntity(CarPostDTO carPostDTO) {
        CarPostEntity carPostEntity = new CarPostEntity();
        // Procura no ownerPostRepositor pelo id o usuario,
        // caso encontre atribui no ownerPost o nome e o contato
        ownerPostRepository.findById(carPostDTO.getOwnerId()).ifPresentOrElse(item -> {
            carPostEntity.setOwnerPost(item);
            carPostEntity.setContact(item.getContactNumber());
        }, () -> {
            // caso nao encontre o usuario ele lança uma exception
            throw new RuntimeException();
        });

        carPostEntity.setModel(carPostDTO.getModel());
        carPostEntity.setBrand(carPostDTO.getBrand());
        carPostEntity.setPrice(carPostDTO.getPrice());
        carPostEntity.setCity(carPostDTO.getCity());
        carPostEntity.setDescription(carPostDTO.getDescription());
        carPostEntity.setEngineVersion(carPostDTO.getEngineVersion());
        carPostEntity.setCreatedDate(carPostDTO.getCreatedDate());

        return carPostEntity;
    }

    // Mapeamento que recebe um Entity e devolve um DTO
    private CarPostDTO mapCarEntityToDTO(CarPostEntity carPostEntity) {
        return CarPostDTO.builder()
                .brand(carPostEntity.getBrand())
                .city(carPostEntity.getCity())
                .model(carPostEntity.getModel())
                .description(carPostEntity.getDescription())
                .engineVersion(carPostEntity.getEngineVersion())
                .createdDate(carPostEntity.getCreatedDate())
                .ownerName(carPostEntity.getOwnerPost().getName())
                .price(carPostEntity.getPrice()).build();
    }
}
