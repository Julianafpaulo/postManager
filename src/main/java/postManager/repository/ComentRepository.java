package postManager.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import postManager.domain.Coment;

@Repository
public interface ComentRepository extends JpaRepository<Coment,Long>  {


}