package com.gmail.deamon999.demo.repository;

import com.gmail.deamon999.demo.entity.DataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DataModelRepo extends JpaRepository<DataModel, Integer> {

    @Query("SELECT DISTINCT a.ssoid FROM DataModel a")
    List<String> findDistinctSsoid();

    List<DataModel> getAllBySsoid(String ssoid);

    @Query("SELECT DISTINCT a.formid FROM DataModel a")
    List<String> findDistinctformid();

    int countAllByFormid(String formId);
}
