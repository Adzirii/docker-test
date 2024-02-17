package demo.adziri.docker_practice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class MyController {
    private final UserService userService;

    @GetMapping("/save/{name}")
    public String save(@PathVariable String name)
    {
        return userService.save(name);
    }

    @GetMapping("/info/{name}")
    public ResponseEntity<String> getInfo(@PathVariable String name){
        return userService.getInfo(name);
    }

    @GetMapping("hi")
    public String hi(){
        return "hiii";
    }
}
