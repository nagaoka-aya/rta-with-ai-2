package com.example.web.project.controller;

import com.example.web.common.helper.CodeViewHelper;
import com.example.web.project.dto.OrganizationDto;
import com.example.web.project.dto.ProjectDto;
import com.example.web.project.form.ProjectCreateForm;
import com.example.web.project.service.OrganizationService;
import com.example.web.project.service.ProjectCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * プロジェクト登録コントローラ
 */
@Controller
@RequestMapping("project/create")
@TransactionTokenCheck("project/create")
public class ProjectCreateController {

    @Autowired
    private ProjectCreateService service;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private CodeViewHelper codeViewHelper;

    /**
     * 初期表示
     *
     * @param form  フォーム
     * @param model モデル
     * @return ビュー名
     */
    @GetMapping
    public String index(ProjectCreateForm form, Model model) {
        // 事業部リストを取得してモデルに設定
        List<OrganizationDto> divisions = organizationService.getDivisions();
        model.addAttribute("divisions", divisions);

        return "project/create/index";
    }

    /**
     * 確認画面表示
     *
     * @param form          フォーム
     * @param bindingResult バインディング結果
     * @param model         モデル
     * @return ビュー名
     */
    @PostMapping("confirm")
    @TransactionTokenCheck(type = TransactionTokenType.BEGIN)
    @OnRejectError(path = "project/create/index")
    public String confirm(@Validated ProjectCreateForm form, BindingResult bindingResult, Model model) {
        // バリデーションエラーがある場合は入力画面に戻る（@OnRejectErrorにより自動的に処理される）
        if (bindingResult.hasErrors()) {
            // 事業部リストを再取得
            List<OrganizationDto> divisions = organizationService.getDivisions();
            model.addAttribute("divisions", divisions);

            // 部門リストも再取得（事業部が選択されている場合のみ）
            if (form.getDivisionId() != null && !form.getDivisionId().isEmpty()) {
                List<OrganizationDto> departments = organizationService.getDepartments(form.getDivisionId());
                model.addAttribute("departments", departments);
            }

            return "project/create/index";
        }

        // DTOに変換
        ProjectDto projectDto = form.toProjectDto();

        // 組織名を設定
        if (projectDto.getOrganizationId() != null && !projectDto.getOrganizationId().isEmpty()) {
            String organizationName = service.getOrganizationName(projectDto.getOrganizationId());
            projectDto.setOrganizationName(organizationName);
        }

        // 事業部名を設定
        if (projectDto.getDivisionId() != null && !projectDto.getDivisionId().isEmpty()) {
            String divisionName = service.getOrganizationName(projectDto.getDivisionId());
            projectDto.setDivisionName(divisionName);
        }

        // 顧客名を設定
        if (projectDto.getClientId() != null && !projectDto.getClientId().isEmpty()) {
            String clientName = service.getClientName(projectDto.getClientId());
            projectDto.setClientName(clientName);
        }

        // プロジェクト種別名を設定
        if (projectDto.getProjectType() != null && !projectDto.getProjectType().isEmpty()) {
            String projectTypeName = codeViewHelper.getName("C0200001", projectDto.getProjectType());
            projectDto.setProjectTypeName(projectTypeName);
        }

        // プロジェクト分類名を設定
        if (projectDto.getProjectClass() != null && !projectDto.getProjectClass().isEmpty()) {
            String projectClassName = codeViewHelper.getName("C0200002", projectDto.getProjectClass());
            projectDto.setProjectClassName(projectClassName);
        }

        model.addAttribute("projectDto", projectDto);

        return "project/create/confirm";
    }

    /**
     * 部門リスト取得
     * ステップ7で実装するメソッドの定義だけ記述（実装は省略）
     *
     * @param divisionId 事業部ID
     * @return 部門リスト
     */
    @GetMapping("departments")
    @ResponseBody
    public List<OrganizationDto> getDepartments(@RequestParam String divisionId) {
        // このメソッドは別のステップで実装する
        return null;
    }

    /**
     * 顧客検索画面表示
     * ステップ11で実装するメソッドの定義だけ記述（実装は省略）
     *
     * @return ビュー名
     */
    @GetMapping("clientSearch")
    public String clientSearch() {
        // このメソッドは別のステップで実装する
        return null;
    }

    /**
     * 顧客検索画面を表示する
     * 
     * @return 顧客検索画面のテンプレートパス
     */
    @GetMapping("client-search")
    public String clientSearch(Model model) {
        // 顧客検索画面用のレイアウトを指定
        model.addAttribute("layout", "layouts/popup");
        // 親画面に選択結果を返すためのJavaScriptを読み込むフラグ
        model.addAttribute("useClientSelectScript", true);

        return "client/search/index";
    }
}
