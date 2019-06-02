package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

import java.util.List;

@RestController
public class ServerController {

    @Autowired
    public ServerDAO dao;


    //API ENDPOINT USATI COME TEST
    @GetMapping(path = "/all")
    public @ResponseBody
    List<Light> getAllUsers() {

        List<Light> light = dao.getAll();
        return light;
    }
    //-----------------------------------------------------------------


    //API ENDPOINT USATI DALL'APPLICAZIONE MOBILE
    @RequestMapping(path = "/Check", method = RequestMethod.GET)
    @ResponseBody
    public Integer checkLight() {

        int foundId = dao.check();

        return foundId;
    }

    @RequestMapping(path = "/Set", method = RequestMethod.GET)
    @ResponseBody
    public void setLight() {

        dao.set();

    }

}