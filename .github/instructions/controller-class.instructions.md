---
applyTo: "web/src/**/*Controller.java"
---

### 3.6 Controllerクラスの実装規約

#### 3.6.1 基本規約
- @Controllerアノテーションを付与
- @RequestMappingでベースパスを指定
- クラス名は機能名 + "Controller"
- 適切なJavaDocを付与

#### 3.6.2 トランザクション管理
- @TransactionTokenCheckアノテーションによる二重送信防止
- トランザクショントークンの適切な開始（BEGIN）と検証
- PRG（Post-Redirect-Get）パターンの採用

#### 3.6.3 メソッド定義
- 画面表示: @GetMapping
- データ処理: @PostMapping
- バリデーション: @Validated
- エラーハンドリング: @OnRejectError
- 共通データ提供: @ModelAttribute

#### 3.6.4 標準的なメソッド構成
- index(): 入力画面表示
- confirm(): 確認画面表示
- execute(): 登録/更新処理実行
- complete(): 完了画面表示
- back(): 入力画面への戻り処理

#### 3.6.5 実装例
```java
@Controller
@RequestMapping("project/create")
@TransactionTokenCheck("project/create")
public class ProjectCreateController {

    @Autowired
    private ProjectCreateService service;

    @GetMapping
    public String index(ProjectCreateForm form) {
        return "project/create/index";
    }

    @PostMapping("confirm")
    @TransactionTokenCheck(type = TransactionTokenType.BEGIN)
    @OnRejectError(path = "project/create/index")
    public String confirm(@Validated ProjectCreateForm form, 
                         BindingResult bindingResult) {
        return "project/create/confirm";
    }

    @ModelAttribute("items")
    public List<ItemDto> getItems() {
        return service.getItems();
    }
}
```

## コントローラの複数ボタン対応方針

フォーム内の複数ボタン（例：戻る・登録等）は、各ボタンに`name`属性を付与し、Springコントローラの`@PostMapping`の`params`属性でメソッドを分岐すること。

### 実装例

#### フォームHTML
```html
<form action="/execute" method="post">
  <button type="submit" name="back">戻る</button>
  <button type="submit" name="submit">登録</button>
</form>
```

#### コントローラ
```java
@PostMapping(path = "execute", params = "back")
public String back(ProjectCreateForm form) { ... }

@PostMapping(path = "execute", params = "submit")
public String submit(ProjectCreateForm form) { ... }
```java