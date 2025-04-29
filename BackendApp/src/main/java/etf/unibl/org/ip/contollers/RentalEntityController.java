package etf.unibl.org.ip.contollers;


import etf.unibl.org.ip.dto.RentalResponseDTO;
import etf.unibl.org.ip.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/rentals")
public class RentalEntityController {

    private final RentalService rentalService;


    public RentalEntityController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping("/{idVehicle}")
    public List<RentalResponseDTO> getAllRentals(@PathVariable String idVehicle) {
        return rentalService.getAllRentals(idVehicle);
    }

    @GetMapping
    public List<RentalResponseDTO> getAllRentals(){
        return rentalService.getAllRentals();
    }

    @GetMapping("/income/{year}/{month}")
    public Map<Integer, Double> getRentalPrice(@PathVariable int year, @PathVariable String month) {
        return rentalService.calculateDailyRevenueForMonth(year, month);
    }

    @GetMapping("/incomeByVehicleType")
    public Map<String, Double> getRentalPriceByVehicleType() {

        Map<String, Double> result = rentalService.getTotalPriceByVehicleType();

        for (Map.Entry<String, Double> entry : result.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        return rentalService.getTotalPriceByVehicleType();
    }

}
