package mskory.base.authentication.service;

import java.util.List;
import mskory.base.authentication.model.User;

public interface UserService {
    User findByEmail(String email);

    List<User> getAll();
}
