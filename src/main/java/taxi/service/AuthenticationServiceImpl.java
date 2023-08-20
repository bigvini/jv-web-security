package taxi.service;

import java.util.Optional;
import taxi.dao.DriverDao;
import taxi.exception.AuthenticationException;
import taxi.lib.Inject;
import taxi.lib.Service;
import taxi.model.Driver;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    DriverDao driverDao;

    @Override
    public Driver login(String login, String password) {
        Optional<Driver> driver = driverDao.findByLogin(login);
        if (driver.isEmpty()) {
            throw new AuthenticationException("Driver login or password incorrect");
        }
        if (driver.get().getPassword().equals(password)) {
            return driver.get();
        }
        throw new AuthenticationException("Driver login or password incorrect");
    }
}
