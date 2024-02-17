package demo.adziri.docker_practice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getById(Long id){
        return userRepository.findById(id).orElseThrow();
    }
    public User getByName(String name){
        return userRepository.findByName(name).orElseThrow();
    }

    public ResponseEntity<String> getInfo(String name){
        User user = getByName(name);
        return ResponseEntity.ok("Имя = "+user.getName() +" id = "+user.getId());
    }

    public String save(String name){
        User user = new User();
        user.setName(name);
        userRepository.save(user);
        return "Пользователь " + name + " сохранен";
    }
}
