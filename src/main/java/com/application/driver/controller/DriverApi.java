package com.application.driver.controller;

import com.application.driver.exception.DriverNotFoundException;
import com.application.driver.model.Driver;
import com.application.driver.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("driver")
public class DriverApi {

    @Autowired
    private DriverService driverService;

    @PutMapping(value="{id}/markAvailable", produces = "application/json")
    public ResponseEntity<Driver> markDriverAvailable(@PathVariable int id){
        Driver result;
        try {
            result=driverService.markDriverAvailable(id);
        } catch (DriverNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping(value = "addDriver", produces = "application/json")
    public ResponseEntity<Driver> createDriver(@RequestBody Driver driver){
        Driver d= driverService.createDriver(driver);
        return ResponseEntity.status(HttpStatus.OK).body(d);
    }

    @GetMapping(value = "{id}/details", produces = "application/json")
    public ResponseEntity<Driver> getDriver(@PathVariable int id){
        try {
            Driver driver= driverService.getDriver(id);
            return ResponseEntity.status(HttpStatus.OK).body(driver);
        } catch (DriverNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "allDrivers", produces = "application/json")
    public ResponseEntity<List<Driver>> getAllDrivers(){
        try {
            List<Driver> drivers= driverService.getDrivers();
            return ResponseEntity.status(HttpStatus.OK).body(drivers);
        } catch (DriverNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
