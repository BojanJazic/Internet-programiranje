package etf.unibl.org.ip.service;

import etf.unibl.org.ip.dto.RentalResponseDTO;
import etf.unibl.org.ip.entities.*;
import etf.unibl.org.ip.repositories.CarEntityRepository;
import etf.unibl.org.ip.repositories.EBikeEntityRepository;
import etf.unibl.org.ip.repositories.EScooterRepository;
import etf.unibl.org.ip.repositories.RentalEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Map.entry;
import static org.hibernate.internal.util.collections.ArrayHelper.forEach;

@Service
public class RentalService {

    private static final Map<String, Integer> monthMap = Map.ofEntries(
            entry("Januar", 1),
            entry("Februar", 2),
            entry("Mart", 3),
            entry("April", 4),
            entry("Maj", 5),
            entry("Jun", 6),
            entry("Jul", 7),
            entry("Avgust", 8),
            entry("Septembar", 9),
            entry("Oktobar", 10),
            entry("Novembar", 11),
            entry("Decembar", 12)
    );


    private final RentalEntityRepository rentalEntityRepository;

    private final CarEntityRepository carEntityRepository;

    private final EBikeEntityRepository ebikeEntityRepository;

    private final EScooterRepository eScooterRepository;


    public RentalService(RentalEntityRepository rentalEntityRepository, CarEntityRepository carEntityRepository, EBikeEntityRepository ebikeEntityRepository, EScooterRepository eScooterRepository) {
        this.rentalEntityRepository = rentalEntityRepository;
        this.carEntityRepository = carEntityRepository;
        this.ebikeEntityRepository = ebikeEntityRepository;
        this.eScooterRepository = eScooterRepository;
    }


    public Map<Integer, Double> calculateDailyRevenueForMonth(int year, String monthName) {
        int monthNumber = monthMap.get(monthName);

        List<RentalEntity> rentals = rentalEntityRepository.getAllRentalEntities();

        return rentals.stream()
                .filter(rental -> {
                    LocalDateTime rentalDate = rental.getDateTime();
                    return rentalDate.getYear() == year && rentalDate.getMonthValue() == monthNumber;
                })
                .collect(Collectors.groupingBy(
                        rental -> rental.getDateTime().getDayOfMonth(),
                        Collectors.summingDouble(RentalEntity::getRentalPrice)
                ));

    }

    public List<RentalResponseDTO> getAllRentals(String idVehicle) {
        return rentalEntityRepository.getAllRentals(idVehicle);
    }

    public List<RentalResponseDTO> getAllRentals(){
        return rentalEntityRepository.getAllRentals();
    }


    //ukupno prihoda po vrsti vozila
    public Map<String, Double> getTotalPriceByVehicleType(){
        List<RentalEntity> rentals = rentalEntityRepository.getAllRentalEntities();

        return rentals.stream()
                .collect(Collectors.groupingBy(
                        rental -> {
                           String idVehicle = rental.getIdVehicle().getIdVehicle();
                           if(carEntityRepository.existsCarByIdVehicle(idVehicle)){
                               return "Car";
                           }else if(ebikeEntityRepository.existsEBikeEntityByIdVehicle(idVehicle)){
                               return "EBike";
                           }else if(eScooterRepository.existsEScooterEntityByIdVehicle(idVehicle)){
                               return "EScooter";
                           } else {
                               return "Unknown";
                           }
                        },
                        Collectors.summingDouble(RentalEntity::getRentalPrice) // Saberi cene za svaku vrstu vozila
                ));

    }



}
