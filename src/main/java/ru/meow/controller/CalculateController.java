package ru.meow.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.meow.model.ParamsCalculation;

@RestController
@RequestMapping(path = "/calculate")
public class CalculateController {

    @PostMapping("/summ")
    public int summ(@RequestBody ParamsCalculation paramsCalculation) {
        return paramsCalculation.getA() + paramsCalculation.getB();
    }

    @PostMapping("/multi")
    public int multi(@RequestBody ParamsCalculation paramsCalculation) {
        return paramsCalculation.getA() * paramsCalculation.getB();
    }

    @PostMapping("/devide")
    public int devide(@RequestBody ParamsCalculation paramsCalculation) {
        return paramsCalculation.getA() / paramsCalculation.getB();
    }

    @PostMapping("/minus")
    public int minus(@RequestBody ParamsCalculation paramsCalculation) {
        return paramsCalculation.getA() - paramsCalculation.getB();
    }
}
