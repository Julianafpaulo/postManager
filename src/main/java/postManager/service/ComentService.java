package postManager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import postManager.domain.Coment;
import postManager.domain.exception.ComentNotFoundException;
import postManager.repository.ComentRepository;
import postManager.repository.ComentRepository;

import java.util.*;

@Service
public class ComentService {

    @Autowired
    private ComentRepository repository;

    public Coment addComent(Coment coment) {
        return repository.save(coment);

    }

    public List<Coment> getAllComents() {
        return repository.findAll();
    }

    public Coment getComent(Long id) {
        return repository.findById(id).
                orElseThrow(() -> new ComentNotFoundException(" Coment not found with id " + id));

    }

    public Coment updateComent(Long id, Coment Coment) {
        return repository.findById(id)
                .map(p -> {
                    p.setText(Coment.getText());
                    p.setMoment(Coment.getMoment());
                    return repository.save(p);
                }).orElseThrow(() -> new ComentNotFoundException("Coment not found with id " + id));
    }

    public ResponseEntity<?> deleteComent(Long id) {
        return repository.findById(id)
                .map(p -> {
                    repository.delete(p);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ComentNotFoundException("Coment not found with id " + id));

    }

}