package postManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import postManager.domain.Coment;
import postManager.service.ComentService;
import java.util.List;

@RestController
@RequestMapping("/coments")
public class ComentController {

    @Autowired
    private ComentService comentService;

    /*@GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<coment> getAllcoments() {
        return comentService.getAllcoments();
    }
*/
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addcoment(@RequestBody Coment coment) {
        comentService.addComent(coment);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updatecoment(@RequestBody Coment topic,@PathVariable Long id){
        comentService.updateComent(id,topic);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletecoment(@PathVariable Long id){
        comentService.deleteComent(id);
    }

    @GetMapping ("/search")
    @ResponseStatus(HttpStatus.OK)
    public Coment getComent(@PathVariable ("id") Long id){
        Coment coment = comentService.getComent(id);
        return coment;
    }
}
