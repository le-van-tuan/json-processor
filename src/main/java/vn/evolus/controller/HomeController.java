package vn.evolus.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.evolus.model.Car;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/home")
    public String index() {
        return "index";
    }

    @GetMapping("/another/home")
    public String another() {
        return "index";
    }

    @ResponseBody
    @GetMapping("/json-list")
    public String test() throws JsonProcessingException {
        List<Car> cars = new ArrayList<>();
        cars.add(new Car(1, "Telsa"));
        cars.add(new Car(2, "VinFast"));
        cars.add(new Car(3, "Ford"));
        cars.add(new Car(4, "Lexus"));

        return objectMapper.writeValueAsString(cars);
    }

    @ResponseBody
    @GetMapping("/json-object")
    public String jsonObject() throws JsonProcessingException {
        return objectMapper.writeValueAsString(new Car(1, "Telsa"));
    }
}
