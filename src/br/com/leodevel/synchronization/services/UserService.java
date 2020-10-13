package br.com.leodevel.synchronization.services;

import br.com.leodevel.synchronization.models.Column;
import br.com.leodevel.synchronization.models.User;
import br.com.leodevel.synchronization.shared.CrudService;
import java.util.Arrays;
import java.util.List;

public class UserService extends CrudService<User> {

    private static UserService instance;
    
    public static UserService getInstance(){
        if (instance == null){
            instance = new UserService();
        }
        return instance;
    }
    
    @Override
    public int getPosition(List<User> list, User obj) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getLogin().equals(obj.getLogin())) {
                return i;
            }
        }
        return 0;
    }

    public User getUser(String login, String password) throws Exception {
        List<User> list = getAll();
        for (User u : list) {
            if (u.getLogin().equalsIgnoreCase(login) && u.getPassword()
                    .equals(password)) {
                return u;
            }
        }
        return null;
    }
    
    @Override
    public List<Column> getColumns(){
        List<Column> columns = Arrays.asList(
                new Column("Nome", "name", String.class),
                new Column("Login", "login", String.class));
        return columns;
    }

}
