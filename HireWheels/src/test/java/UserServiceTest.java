import com.upgrad.hirewheels.dao.RoleDAO;
import com.upgrad.hirewheels.dao.UsersDAO;
import com.upgrad.hirewheels.entities.Role;
import com.upgrad.hirewheels.entities.UsersData;
import com.upgrad.hirewheels.exceptions.UserAlreadyExistsException;
import com.upgrad.hirewheels.exceptions.UserDetailsNotfoundException;
import com.upgrad.hirewheels.service.UserService;
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

public class UserServiceTest {



    static UsersData users;
    static int userId;


    @BeforeEach
    public void setUpTestMockData(){

        Role adminUserType = new Role("Admin");
        Role customerUserType = new Role("User");
        roleDAO.save(adminUserType);
        roleDAO.save(customerUserType);

        users = new UsersData("Admin", "Admin", "admin@123", "upgrad@gmail.com", "9999999999", 10000.00, adminUserType);
        userId = usersDAO.save(users).getId();
    }



    @Test
    public void UserDetailsAlreadyExist(){
        Assertions.assertThrows(UserAlreadyExistsException.class,()->userService.createUser(users));
    }




    @Test
    public void UserDetailsNotFound(){
        Assertions.assertThrows(UserDetailsNotfoundException.class,()->userService.getUser("upgad@gmail.com","admi@123"));
    }


    @Test
    public void a(){
        Assertions.assertTrue(true);
    }


    @Autowired
    private UserService userService;

    @Qualifier("usersDAO")
    @Autowired
   private UsersDAO usersDAO;

    @Qualifier("roleDAO")
    @Autowired
   private RoleDAO roleDAO;

}
