package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

import java.text.SimpleDateFormat;

@Component
public class keepAwake {

    private static final Logger log = LoggerFactory.getLogger(keepAwake.class);
    public static GpioPinDigitalOutput pin;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    @Scheduled(fixedRate = 1000)
    public void reportCurrentTime() {

        RestTemplate restTemplate = new RestTemplate();
        if(pin==null){
            GpioController gpio = GpioFactory.getInstance();
            pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_18,"My LED",PinState.LOW);
        }
        int response=0;
        try {

            response = Integer.parseInt(restTemplate.getForObject("https://controller1.herokuapp.com/Check",Object.class).toString());

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        if(response==0){
            pin.low();
        }
        else{
            pin.high();
        }
    }

}
