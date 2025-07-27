package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "SELECT * FROM \"user\" usr " +
            "WHERE " +
            "(:filterType = 'userId' AND CAST(usr.user_id AS TEXT) LIKE %:filterValue%) OR " +
            "(:filterType = 'userName' AND usr.user_name LIKE %:filterValue%) OR " +
            "(:filterType = 'userRole' AND usr.user_role LIKE %:filterValue%) " +
            "ORDER BY " +
            "CASE WHEN :sortBy = 'userId' AND :direction = 'asc' THEN usr.user_id END ASC, " +
            "CASE WHEN :sortBy = 'userId' AND :direction = 'desc' THEN usr.user_id END DESC, " +
            "CASE WHEN :sortBy = 'userName' AND :direction = 'asc' THEN usr.user_name END ASC, " +
            "CASE WHEN :sortBy = 'userName' AND :direction = 'desc' THEN usr.user_name END DESC, " +
            "CASE WHEN :sortBy = 'userRole' AND :direction = 'asc' THEN usr.user_role END ASC, " +
            "CASE WHEN :sortBy = 'userRole' AND :direction = 'desc' THEN usr.user_role END DESC " +
            "LIMIT :limit OFFSET :offset",
            nativeQuery = true)
    List<User> findByFilterSortAndPage(@Param("sortBy") String sortBy,
                                       @Param("direction") String direction,
                                       @Param("limit") int limit,
                                       @Param("offset") int offset,
                                       @Param("filterType") String filterType,
                                       @Param("filterValue") String filterValue);

}
