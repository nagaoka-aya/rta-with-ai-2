package com.example.web.project.controller;

import com.example.web.project.dto.OrganizationDto;
import com.example.web.project.form.ProjectCreateForm;
import com.example.web.project.service.ProjectService;
import com.example.web.common.errorhandling.OnRejectError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import jp.fintan.keel.spring.web.token.transaction.TransactionTokenCheck;
import jp.fintan.keel.spring.web.token.transaction.TransactionTokenType;

/**
 * プロジェクト登録コントローラー
 */
@Controller
@RequestMapping("/project")
@TransactionTokenCheck("project/create")
public class ProjectController {
    
    @Autowired
    private ProjectService projectService;
    
    /**
     * 事業部一覧を取得する
     * @return 事業部一覧
     */
    @ModelAttribute("divisions")
    public List<OrganizationDto> getDivisions() {
        return projectService.searchOrganizations(null);
    }
    
    /**
     * 登録画面を表示する
     * @param form フォーム
     * @return テンプレートパス
     */
    @GetMapping("/create")
    public String create(ProjectCreateForm form) {
        return "project/create/index";
    }
    
    /**
     * 部門一覧を取得する
     * @param divisionId 事業部ID
     * @return 部門一覧
     */
    @GetMapping("/departments")
    @ResponseBody
    public ResponseEntity<List<OrganizationDto>> getDepartments(@RequestParam("divisionId") Integer divisionId) {
        List<OrganizationDto> departments = projectService.searchOrganizations(divisionId);
        return ResponseEntity.ok(departments);
    }
    
    /**
     * 確認画面を表示する
     * @param form フォーム
     * @param result バリデーション結果
     * @return テンプレートパス
     */
    @PostMapping("/create/confirm")
    @TransactionTokenCheck(type = TransactionTokenType.BEGIN)
    @OnRejectError(path = "project/create/index")
    public String confirm(@Validated ProjectCreateForm form, BindingResult result) {
        return "project/create/confirm";
    }
    
    /**
     * プロジェクトを登録する
     * @param form フォーム
     * @param result バリデーション結果
     * @return リダイレクトパス
     */
    @PostMapping(path = "/create/register", params = "execute")
    @TransactionTokenCheck(type = TransactionTokenType.CHECK)
    @OnRejectError(path = "project/create/confirm")
    public String register(@Validated ProjectCreateForm form, BindingResult result) {
        projectService.createProject(form.toProjectDto());
        return "redirect:/project/create/complete";
    }
    
    /**
     * 登録画面に戻る
     * @param form フォーム
     * @return テンプレートパス
     */
    @PostMapping(path = "/create/register", params = "back")
    public String back(ProjectCreateForm form) {
        return "project/create/index";
    }
    
    /**
     * 完了画面を表示する
     * @return テンプレートパス
     */
    @GetMapping("/create/complete")
    public String complete() {
        return "project/create/complete";
    }
}
