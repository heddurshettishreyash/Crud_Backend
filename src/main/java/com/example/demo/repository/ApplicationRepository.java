package com.example.demo.repository;

import com.example.demo.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    @Query(value = "SELECT * FROM application a " +
            "WHERE " +
            "(:filterType = 'appId' AND CAST(a.app_id AS TEXT) LIKE %:filterValue%) OR " +
            "(:filterType = 'appName' AND a.app_name LIKE %:filterValue%) OR " +
            "(:filterType = 'appDesc' AND a.app_desc LIKE %:filterValue%) OR " +
            "(:filterType = 'appType' AND a.app_type LIKE %:filterValue%) " +
            "ORDER BY " +
            "CASE WHEN :sortBy = 'appId' AND :direction = 'asc' THEN a.app_id END ASC, " +
            "CASE WHEN :sortBy = 'appId' AND :direction = 'desc' THEN a.app_id END DESC, " +
            "CASE WHEN :sortBy = 'appName' AND :direction = 'asc' THEN a.app_name END ASC, " +
            "CASE WHEN :sortBy = 'appName' AND :direction = 'desc' THEN a.app_name END DESC, " +
            "CASE WHEN :sortBy = 'appDesc' AND :direction = 'asc' THEN a.app_desc END ASC, " +
            "CASE WHEN :sortBy = 'appDesc' AND :direction = 'desc' THEN a.app_desc END DESC, " +
            "CASE WHEN :sortBy = 'appType' AND :direction = 'asc' THEN a.app_type END ASC, " +
            "CASE WHEN :sortBy = 'appType' AND :direction = 'desc' THEN a.app_type END DESC " +
            "LIMIT :limit OFFSET :offset",
            nativeQuery = true)
    List<Application> findByFilterSortAndPage(@Param("sortBy") String sortBy,
                                              @Param("direction") String direction,
                                              @Param("limit") int limit,
                                              @Param("offset") int offset,
                                              @Param("filterType") String filterType,
                                              @Param("filterValue") String filterValue);
}
