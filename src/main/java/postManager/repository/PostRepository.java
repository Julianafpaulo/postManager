package postManager.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import postManager.domain.Post;

@Repository
public interface PostRepository extends JpaRepository<Post,Long>  {


}