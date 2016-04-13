package edu.wctc.tcl.bookwebapp.repository;

import edu.wctc.tcl.bookwebapp.model.Author;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author jlombardo
 */
public interface AuthorRepository extends JpaRepository<Author, Integer>, Serializable {
    
//    @Query("SELECT a FROM Author a JOIN FETCH a.bookSet WHERE a.authorId = (:id)")
//    public Author findByIdAndFetchBooksEagerly(@Param("id") Integer id);
////    
//    @Query("SELECT a.authorName FROM Author a")
//    public Object[] findAllWithNameOnly();
    
    @Query("SELECT a FROM Author a WHERE a.name = (:name)")
    public List<Author> findAuthorByName(@Param("name") String name);
}
