package com.example.web.project.controller;

import com.example.web.common.errorhandling.OnRejectError;
import com.example.web.project.form.ProjectSearchForm;
import com.example.web.project.service.ProjectService;
import com.example.web.project.service.ProjectSearchService;
import com.example.web.project.dto.OrganizationDto;
import com.example.web.project.dto.ProjectSearchDto;
import com.example.web.project.dto.ProjectDetailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * プロジェクト検索画面を担当するコントローラー。
 */
@Controller
@RequestMapping("/project/search")
public class ProjectSearchController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectSearchService projectSearchService;

    /**
     * 事業部一覧を取得してModelに設定する
     *
     * @return 事業部一覧
     */
    @ModelAttribute("divisions")
    public List<OrganizationDto> getDivisions() {
        return projectService.searchOrganizations(null);
    }

    /**
     * プロジェクト検索画面の初期表示。
     *
     * @param model モデル
     * @return テンプレートパス
     */
    @GetMapping
    public String index(Model model) {
        model.addAttribute("projectSearchForm", new ProjectSearchForm());
        return "project/search/index";
    }

    /**
     * プロジェクト検索処理（POST）。
     *
     * @param form フォーム
     * @param bindingResult バリデーション結果
     * @param model モデル
     * @return テンプレートパス
     */
    @PostMapping("/search")
    @OnRejectError(path = "project/search/index")
    public String searchProjects(@Validated ProjectSearchForm form, BindingResult bindingResult, Model model) {
        executeSearch(form, model);
        return "project/search/index";
    }

    /**
     * プロジェクト検索処理（GET）。
     *
     * @param form フォーム
     * @param model モデル
     * @return テンプレートパス
     */
    @GetMapping("/search")
    public String searchProjectsGet(ProjectSearchForm form, Model model) {
        executeSearch(form, model);
        return "project/search/index";
    }

    /**
     * プロジェクト詳細画面の表示。
     *
     * @param projectId プロジェクトID
     * @param model モデル
     * @return テンプレートパス
     */
    @GetMapping("/detail")
    public String detail(@RequestParam Integer projectId, Model model) {
        ProjectDetailDto project = projectSearchService.getProjectDetail(projectId);
        
        if (project == null) {
            return "error/general";
        }
        
        model.addAttribute("project", project);
        return "project/detail/index";
    }

    /**
     * 検索処理を実行してModelに結果を設定する
     *
     * @param form フォーム
     * @param model モデル
     */
    private void executeSearch(ProjectSearchForm form, Model model) {
        Page<ProjectSearchDto> page = projectSearchService.searchProjects(form);
        
        model.addAttribute("projects", page.getContent());
        model.addAttribute("page", page);
        model.addAttribute("hasResults", page.hasContent());
        model.addAttribute("searchExecuted", true);
        model.addAttribute("projectSearchForm", form);
    }
}
