package com.example.web.project.controller;

import com.example.web.project.form.ProjectCreateForm;
import com.example.web.project.helper.ProjectCreateViewHelper;
import com.example.web.project.model.Organization;
import com.example.web.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * プロジェクト登録機能のコントローラクラス
 */
@Controller
@RequestMapping("/project/create")
public class ProjectCreateController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectCreateViewHelper projectCreateViewHelper;

    /**
     * プロジェクト登録画面の初期表示
     *
     * @param model モデル
     * @return テンプレートパス
     */
    @GetMapping
    public String index(Model model) {
        // 事業部一覧を取得
        List<Organization> divisions = projectService.getDivisions();

        // 初期表示データをモデルに設定
        model.addAttribute("divisions", divisions);
        model.addAttribute("projectCreateForm", new ProjectCreateForm());

        return "project/create";
    }

    /**
     * 事業部IDに基づいて部門リストを取得する非同期API
     *
     * @param divisionId 事業部ID
     * @return 部門リスト（JSON形式）
     */
    @GetMapping("/departments/{divisionId}")
    @ResponseBody
    public ResponseEntity<List<Organization>> getDepartments(@PathVariable String divisionId) {
        // 選択された事業部に紐づく部門リストを取得
        List<Organization> departments = projectService.getDepartments(divisionId);

        // ResponseEntityでJSON形式のレスポンスを返す
        return ResponseEntity.ok(departments);
    }

    /**
     * 顧客検索画面を開く
     *
     * @return 顧客検索画面へのリダイレクトURL
     */
    @GetMapping("/client-search")
    public String openClientSearch() {
        // 顧客検索画面へリダイレクト
        return "redirect:/client/search?mode=dialog";
    }

    /**
     * 確認画面表示処理
     *
     * @param form   入力フォーム
     * @param result バインディング結果
     * @param model  モデル
     * @return 遷移先
     */
    @PostMapping("/confirm")
    public String confirm(@ModelAttribute @Validated ProjectCreateForm form,
            BindingResult result,
            Model model) {

        // バリデーションエラーがある場合は入力画面に戻る
        if (result.hasErrors()) {
            // 事業部一覧を再取得してモデルに設定
            model.addAttribute("divisions", projectService.getDivisions());

            // 部門一覧を再取得（事業部が選択されている場合）
            if (form.getDivisionId() != null && !form.getDivisionId().isEmpty()) {
                model.addAttribute("departments",
                        projectService.getDepartments(form.getDivisionId()));
            }

            return "project/create";
        }

        // 組織情報の取得
        if (form.getOrganizationId() != null && !form.getOrganizationId().isEmpty()) {
            Organization organization = projectService.getOrganizationById(form.getOrganizationId());
            model.addAttribute("organization", organization);
        }

        // 顧客情報の取得
        if (form.getClientId() != null && !form.getClientId().isEmpty()) {
            String clientName = projectService.getClientName(form.getClientId());
            model.addAttribute("clientName", clientName);
        }

        // フォームオブジェクトをモデルに設定
        model.addAttribute("projectCreateForm", form);

        // 確認画面を表示
        return "project/confirm";
    }
}
