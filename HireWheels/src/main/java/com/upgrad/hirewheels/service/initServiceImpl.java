package com.upgrad.hirewheels.service;


import com.upgrad.hirewheels.dao.*;
import com.upgrad.hirewheels.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@Service("InitService")
public class initServiceImpl implements InitService {




    Role adminUserType = new Role("Admin");
    Role customerUserType = new Role("User");
    List<Role> roleList=Arrays.asList(adminUserType,customerUserType);

    UsersData users = new UsersData("Admin", "Admin", "admin@123", "upgrad@gmail.com", "9999999999", 10000.00, adminUserType);
    UsersData users1=new UsersData("satheesh","kumar","Sa#1235","saths@g","9090909090",10000.00,customerUserType);
    List<UsersData> usersDataList=Arrays.asList( users,users1);


    VehicleCategory vehicleCategory = new VehicleCategory("Car");
    VehicleCategory vehicleCategory1 = new VehicleCategory("Bike");
    List<VehicleCategory> vehicleCategories=Arrays.asList(vehicleCategory, vehicleCategory1);


    VehicleSubcategory vehicleSubcategory = new VehicleSubcategory("SUV", 300, vehicleCategory);
    VehicleSubcategory vehicleSubcategory1 = new VehicleSubcategory("SEDAN", 350, vehicleCategory);
    VehicleSubcategory vehicleSubcategory2 = new VehicleSubcategory("HATCHBACK", 250, vehicleCategory);
    VehicleSubcategory vehicleSubcategory3 = new VehicleSubcategory("CRUISER", 200, vehicleCategory1);
    VehicleSubcategory vehicleSubcategory4 = new VehicleSubcategory("DIRT BIKE", 200, vehicleCategory1);
    VehicleSubcategory vehicleSubcategory5 = new VehicleSubcategory("SPORTS BIKE", 150, vehicleCategory1);
    List<VehicleSubcategory> vehicleSubcategories=Arrays.asList(vehicleSubcategory,vehicleSubcategory1,vehicleSubcategory2,vehicleSubcategory3,vehicleSubcategory4,vehicleSubcategory5);




    FuelType fuelType = new FuelType("Petrol");
    FuelType fuelType1 = new FuelType("Diesel");
    List<FuelType> fuelTypes=Arrays.asList(fuelType,fuelType1);

    City city = new City("Mumbai");
    List<City> cities=Arrays.asList(city);

    Location location = new Location("Worli", "Dr E Moses Rd, Worli Naka, Upper Worli", city, "400018");
    Location location1 = new Location("Chembur", "Optic Complex", city, "400019");
    Location location2 = new Location("Powai", "Hiranandani Towers", city, "400020");
    List<Location> locationList=Arrays.asList(location,location1,location2);


    Vehicle vehicle=new Vehicle("Hyundai Creta","M150001","White",1,"https://images.carandbike.com/car-images/large/hyundai/creta/hyundai-creta.webp?v=56",users,fuelType,location,vehicleSubcategory);
    List<Vehicle> vehicles=Arrays.asList(vehicle);

    Booking booking=new Booking("29-5-2020","29-5-20","25-5-20",3000.00,users,location,vehicle);
    List<Booking> bookings=Arrays.asList(booking);



    @Qualifier("roleDAO")
    @Autowired
    private RoleDAO roleDAO;

    @Qualifier("usersDAO")
    @Autowired
    private UsersDAO usersDAO;


    @Qualifier("fuelTypeDAO")
    @Autowired
    private FuelTypeDAO fuelTypeDAO;


    @Qualifier("cityDAO")
    @Autowired
    private CityDAO cityDAO;


    @Qualifier("locationDAO")
    @Autowired
    private LocationDAO locationDAO;

    @Qualifier("vehicleDAO")
    @Autowired
    private VehicleDAO vehicleDAO;


    @Qualifier("vehicleCategoryDAO")
    @Autowired
    private VehicleCategoryDAO vehicleCategoryDAO;


    @Qualifier("vehicleSubcategoryDAO")
    @Autowired
    private VehicleSubcategoryDAO vehicleSubcategoryDAO;


    @Qualifier("bookingDAO")
    @Autowired
    private BookingDAO bookingDAO;


    @Override
    public void init() {
        cities.forEach(city -> cityDAO.save(city));
        roleList.forEach(role -> roleDAO.save(role));
        usersDataList.forEach(usersData -> usersDAO.save(usersData));
        vehicleCategories.forEach(vehicleCategory->vehicleCategoryDAO.save(vehicleCategory));
        vehicleSubcategories.forEach(vehicleSubcategory->vehicleSubcategoryDAO.save(vehicleSubcategory));
        locationList.forEach(location->locationDAO.save(location));
        fuelTypes.forEach(fuelType->fuelTypeDAO.save(fuelType));
        vehicles.forEach(vehicle-> vehicleDAO.save(vehicle));
       bookings.forEach(booking->bookingDAO.save(booking));
    }


}
