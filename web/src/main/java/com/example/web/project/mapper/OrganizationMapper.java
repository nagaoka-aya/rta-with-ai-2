package com.example.web.project.mapper;

import com.example.web.project.model.Organization;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 組織情報を取得するマッパー
 */
@Mapper
public interface OrganizationMapper {

    /**
     * 事業部一覧を取得する（上位組織IDがNULLの組織）
     *
     * @return 事業部一覧
     */
    @Select("SELECT organization_id, organization_name, upper_organization " +
            "FROM organization " +
            "WHERE upper_organization IS NULL " +
            "ORDER BY organization_id")
    List<Organization> selectDivisions();

    /**
     * 指定された事業部に属する部門一覧を取得する
     *
     * @param divisionId 事業部ID
     * @return 部門一覧
     */
    @Select("SELECT organization_id, organization_name, upper_organization " +
            "FROM organization " +
            "WHERE upper_organization = #{divisionId} " +
            "ORDER BY organization_id")
    List<Organization> selectDepartmentsByDivision(@Param("divisionId") String divisionId);

    /**
     * 組織IDから組織情報を取得する
     *
     * @param organizationId 組織ID
     * @return 組織情報
     */
    @Select("SELECT organization_id, organization_name, upper_organization " +
            "FROM organization " +
            "WHERE organization_id = #{organizationId}")
    Organization selectOrganizationById(@Param("organizationId") String organizationId);
}
