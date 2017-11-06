package com.techtest.abbysmith;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController{
  private CommandInterpreter ci = new CommandInterpreter();
  private List<Vehicle> vehicles;

  AppController () throws Exception {
    //Set up list of vehicles from JSON
    vehicles = ci.parseJsonVehicles("http://www.rentalcars.com/js/vehicles.json");
  }

  @RequestMapping("/vehicles-by-price")
  public ModelAndView getVehiclesByPrice() {
    List<Vehicle> vehiclesPrices = ci.vehiclesByPrice(vehicles);
    ModelAndView mv = new ModelAndView();
    mv.addObject("vehiclePrices", vehiclesPrices);
    mv.setViewName("vehiclePrice");
    return mv;
  }

  @RequestMapping("/vehicles-by-scores")
  public ModelAndView getVehiclesByScore() {
    List<Vehicle> vehicleScores = ci.vehiclesByScore(vehicles);
    ModelAndView mv = new ModelAndView();
    mv.addObject("vehicleScores", vehicleScores);
    mv.setViewName("vehicleScore");
    return mv;
  }

  @RequestMapping("/rating-by-type")
  public ModelAndView getVehiclesByHighestSupp() {
    List<Vehicle> vehicleRatings = ci.vehiclesByHighestSupp(vehicles);
    ModelAndView mv = new ModelAndView();
    mv.addObject("vehicleRatings", vehicleRatings);
    mv.setViewName("vehicleRating");
    return mv;
  }

  @RequestMapping("/sipp-specs")
  public ModelAndView getVehicleSpecs() {
    ModelAndView mv = new ModelAndView();
    mv.addObject("vehicles", vehicles);
    mv.setViewName("vehicleSipp");
    return mv;
  }

}
