package com.example.demo.repository;

import com.example.demo.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Integer> {

    @Query(value = "SELECT * FROM organization o " +
            "where" +
            "(:filterType = 'orgId' AND CAST(o.org_id AS TEXT) LIKE %:filterValue%) OR " +
            "(:filterType = 'orgName' AND o.org_name LIKE %:filterValue%) OR " +
            "(:filterType = 'orgEmail' AND o.org_email LIKE %:filterValue%) OR " +
            "(:filterType = 'orgNumber' AND CAST(o.org_number AS TEXT) LIKE %:filterValue%) " +
            "ORDER BY " +
            "CASE WHEN :sortBy = 'orgId' AND :direction = 'asc' THEN o.org_id END ASC, " +
            "CASE WHEN :sortBy = 'orgId' AND :direction = 'desc' THEN o.org_id END DESC, " +
            "CASE WHEN :sortBy = 'orgName' AND :direction = 'asc' THEN o.org_name END ASC, " +
            "CASE WHEN :sortBy = 'orgName' AND :direction = 'desc' THEN o.org_name END DESC, " +
            "CASE WHEN :sortBy = 'orgEmail' AND :direction = 'asc' THEN o.org_email END ASC, " +
            "CASE WHEN :sortBy = 'orgEmail' AND :direction = 'desc' THEN o.org_email END DESC, " +
            "CASE WHEN :sortBy = 'orgNumber' AND :direction = 'asc' THEN o.org_number END ASC, " +
            "CASE WHEN :sortBy = 'orgNumber' AND :direction = 'desc' THEN o.org_number END DESC " +
            "LIMIT :limit OFFSET :offset",
            nativeQuery = true)
    List<Organization> findByFilterSortAndPage(@Param("sortBy") String sortBy,
                                               @Param("direction") String direction,
                                               @Param("limit") int limit,
                                               @Param("offset") int offset,
                                               @Param("filterType") String filterType,
                                               @Param("filterValue") String filterValue);

}
