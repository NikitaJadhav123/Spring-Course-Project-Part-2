import com.upgrad.hirewheels.dao.BookingDAO;
import com.upgrad.hirewheels.entities.Booking;
import com.upgrad.hirewheels.exceptions.BookingAmountException;
import com.upgrad.hirewheels.service.BookingService;
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

public class BookingServiceTest {



    static Booking booking;

    @BeforeEach
    public void setUpTestMockData(){
        Booking booking=new Booking("29-5-2020","29-5-20","25-5-20",3000.00);
        bookingDAO.save(booking);

    }



    @Test
    public void BookingAmountTest(){
        Assertions.assertThrows(BookingAmountException.class,()->bookingService.addBooking(booking));
    }


    @Autowired
    private BookingService bookingService;

    @Qualifier("bookingDAO")
    @Autowired
   private BookingDAO bookingDAO;

}
