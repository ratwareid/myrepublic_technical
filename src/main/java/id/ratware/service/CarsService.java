package id.ratware.service;

import id.ratware.entity.CarEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

/***********************************************************************
 * Module:  id.ratware.service.CarsService
 * Author:  Ratwareid
 * Created: 25/10/2023
 * Info:  If You dont know me ? Just type ratwareid in google.
 ***********************************************************************/

@ApplicationScoped
public class CarsService {

    public List<CarEntity> getAll() {
        return CarEntity.findAll().list();
    }

    @Transactional
    public Optional<CarEntity> getById(Long id) {
        return CarEntity.findByIdOptional(id);
    }

    @Transactional
    public void update(Long id,String warna,Integer harga) {
        CarEntity carEntity = CarEntity.findById(id);
        if (warna != null) {
            carEntity.setWarna(warna);
        }
        if (harga != null) {
            carEntity.setHarga(harga);
        }
    }

    @Transactional
    public void save(CarEntity carEntity) {
        carEntity.persist();
    }

    @Transactional
    public void delete(Long id) {
        CarEntity.deleteById(id);
    }
}
