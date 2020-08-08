import com.upgrad.hirewheels.dao.VehicleDAO;
import com.upgrad.hirewheels.entities.Vehicle;
import com.upgrad.hirewheels.service.AdminService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:hwBeans.xml"})

public class AdminServiceTest {


    static Vehicle vehicle;
    static int changeStatus;
    static int earlierStatus;

    @BeforeEach
    public void setUpTestMockData(){

        Vehicle vehicle=new Vehicle("Hyundai Creta","M150001","White",1,"https://images.carandbike.com/car-images/large/hyundai/creta/hyundai-creta.webp?v=56");
        earlierStatus=vehicle.getAvailabilityStatus();
        adminService.changeAvailability(vehicle);
        changeStatus=vehicle.getAvailabilityStatus();
        vehicleDAO.save(vehicle);

    }



    @Test
    public void VehicleChangeAvailability(){

        Assertions.assertNotEquals(changeStatus,earlierStatus);
    }



    @Autowired
    private AdminService adminService;

    @Qualifier("vehicleDAO")
    @Autowired
    private VehicleDAO vehicleDAO;


}
