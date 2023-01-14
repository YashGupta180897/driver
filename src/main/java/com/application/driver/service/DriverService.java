package com.application.driver.service;

import com.application.driver.exception.DriverNotFoundException;
import com.application.driver.model.Driver;
import com.application.driver.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

    public Driver markDriverAvailable(int id) throws DriverNotFoundException {
        Optional<Driver> driver=driverRepository.findById(id);
        if(!driver.isPresent())
            throw new DriverNotFoundException("Driver not found");

        driver.get().setAvailable(true);
        driverRepository.save(driver.get());
        return driver.get();
    }

    public Driver createDriver(Driver driver) {
        return driverRepository.save(driver);
    }

    public Driver getDriver(int id) throws DriverNotFoundException {
        Optional<Driver> driver=driverRepository.findById(id);
        if(!driver.isPresent())
            throw new DriverNotFoundException("Driver does not exist in database");
        return driver.get();
    }

    public List<Driver> getDrivers() throws DriverNotFoundException {
        List<Driver> drivers=driverRepository.findAll();
        if(!drivers.isEmpty())
            throw new DriverNotFoundException("Driver does not exist in database");
        return drivers;
    }
}
