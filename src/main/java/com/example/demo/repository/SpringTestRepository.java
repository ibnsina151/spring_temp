package com.example.demo.repository;

import com.example.demo.repository.model.SpringTest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SpringTestRepository extends CrudRepository<SpringTest,Long> {

    List<SpringTest> findByFirstName(String FirstName);
    List<SpringTest> findAll();
    List<SpringTest> findByMobile(String mobile);

//    @Query("select c from SpringTest c where c.gender like %?1")
    List<SpringTest> findTop2ByGender(String gender);

}
