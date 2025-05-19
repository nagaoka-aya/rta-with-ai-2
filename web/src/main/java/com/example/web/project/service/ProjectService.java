package com.example.web.project.service;

import com.example.web.project.mapper.OrganizationMapper;
import com.example.web.project.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;

/**
 * プロジェクト関連のサービスクラス
 */
@Service
public class ProjectService {

    private final OrganizationMapper organizationMapper;

    @Autowired
    public ProjectService(OrganizationMapper organizationMapper) {
        this.organizationMapper = organizationMapper;
    }

    /**
     * 事業部一覧を取得する
     *
     * @return 事業部一覧
     */
    @Transactional(readOnly = true)
    public List<Organization> getDivisions() {
        return organizationMapper.selectDivisions();
    }

    /**
     * 指定された事業部に所属する部門一覧を取得する
     *
     * @param divisionId 事業部ID
     * @return 部門一覧
     */
    @Transactional(readOnly = true)
    public List<Organization> getDepartments(String divisionId) {
        if (!StringUtils.hasText(divisionId)) {
            return Collections.emptyList();
        }
        return organizationMapper.selectDepartmentsByDivision(divisionId);
    }

    /**
     * 組織IDから組織情報を取得する
     *
     * @param organizationId 組織ID
     * @return 組織情報
     */
    @Transactional(readOnly = true)
    public Organization getOrganizationById(String organizationId) {
        if (!StringUtils.hasText(organizationId)) {
            return null;
        }
        return organizationMapper.selectOrganizationById(organizationId);
    }

    /**
     * 顧客IDから顧客名を取得する
     * 注: 実際の実装では外部APIとの連携が必要になる可能性あり
     *
     * @param clientId 顧客ID
     * @return 顧客名
     */
    @Transactional(readOnly = true)
    public String getClientName(String clientId) {
        if (!StringUtils.hasText(clientId)) {
            return "";
        }

        // TODO: 実際の実装では、外部APIまたはDBから顧客情報を取得する処理を追加
        // 仮実装として、顧客IDをそのまま返す
        return "顧客_" + clientId;
    }
}
