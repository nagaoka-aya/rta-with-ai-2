以下のアーキテクチャガイドを順守した実装としてください。

# Webアプリケーションアーキテクチャガイド

## 1. アーキテクチャの概要

本プロジェクトは、Spring Boot + Spring MVCを使用したWebアプリケーションで、以下のレイヤードアーキテクチャを採用しています。

### 1.1 レイヤー構造

```
[Presentation Layer]
    ├── Controller
    ├── Form
    └── ViewHelper

[Application Layer]
    ├── Service
    └── DTO

[Infrastructure Layer]
    ├── Mapper
    ├── Model
    └── Configuration
```

## 2. パッケージ構造

基本パッケージ構造は以下の通りです：

```
com.example.web
    ├── common/          # 共通コンポーネント
    ├── security/        # セキュリティ関連
    └── [機能名]/        # 各機能のパッケージ
        ├── controller/  # コントローラー
        ├── service/     # サービス
        ├── mapper/      # マッパー
        ├── model/       # モデル
        ├── dto/         # DTOクラス
        ├── form/        # フォームクラス
        ├── helper/      # ビューヘルパー
        └── configuration/ # 設定クラス
```

## 3. コンポーネントの役割と責務

### 3.1 DTOとModelの使い分け
- DTO (Data Transfer Object)
  - 画面とサービス層の間のデータ転送に使用
  - 画面表示に必要な形式でデータを保持
  - 画面入力値の一時保持やバリデーションにはFormクラスを使用し、DTOは利用しない

- Model
  - データベースのテーブル構造を反映
  - ライブラリとして`com.example.web.project.model.detail`namespaceに実装済み
  - 永続化層との連携に使用

### 3.2 APIとの連携
- 外部APIとの通信は専用のClientクラスを作成
- Client層はInfrastructure Layerに配置
- レスポンスはDTO形式で返却
- 通信エラーは適切な例外でラップ

### 3.3 画面遷移
- PRG (Post-Redirect-Get) パターンの採用
- 二重送信防止のためのトークンチェック実装
- エラー時の適切なフォーム値の維持
- バリデーションエラーの適切な表示

### 3.4 ViewHelper
- 画面表示用の補助ロジックを提供。
- 主にID→名称変換や、コード値リストの取得、テキスト整形など、View層のロジックをControllerやServiceから分離する

### 3.4 ファイルアップロード
- MultipartFileの使用
- アップロードサイズの制限設定
- 一時ファイルと永続ファイルの分離
- 適切なファイル形式チェックの実装
- ウィルススキャンの実施（推奨）

### 3.5 Formクラスの実装規約

#### 3.5.1 基本規約
- JavaBeansパターンに準拠（getter/setterの実装）
- プロパティには適切なJavaDocを付与

#### 3.5.2 バリデーション
- 必須チェックには@Requiredアノテーションを使用
- ドメインバリデーションには@Domainアノテーションを使用
- 日付項目には@DateTimeFormatで形式を指定
- 項目間の相関チェックは@AssertTrueで実装

#### 3.5.3 DTOとの連携
- FormからDTOへの変換メソッドを実装（toXxxDto）
- 変換にはBeanUtils.copyPropertiesを使用
- DTOとの項目の整合性を維持

#### 3.5.4 命名規則
- クラス名は機能名 + "Form"
- プロパティ名はキャメルケース
- 変換メソッド名はto + 対象DTOクラス名

#### 3.5.5 実装例
```java
@Required
@Domain("projectName")
private String projectName;

@DateTimeFormat(pattern = "uuuu-MM-dd")
private LocalDate projectStartDate;

@AssertTrue(message = "{validator.periodConsistencyCheck.message}")
public boolean isStartDateBeforeEndDate() {
    if (startDate == null || endDate == null) {
        return true;
    }
    return !startDate.isAfter(endDate);
}

public ProjectDto toProjectDto() {
    ProjectDto dto = new ProjectDto();
    BeanUtils.copyProperties(this, dto);
    return dto;
}
```

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

#### 3.6.6 戻り値の規約
- 画面遷移: テンプレートパスを返却
- リダイレクト: "redirect:/"プレフィックスを使用
- PRGパターン: 処理後は完了画面へリダイレクト

#### 3.6.7 エラーハンドリング
- BindingResultによるバリデーションエラーの捕捉
- @OnRejectErrorによるエラー時の遷移先指定
- 業務エラー発生時の適切な画面遷移

#### 3.6.8 セキュリティ
- 適切な認可設定（@PreAuthorize等）
- CSRFトークンの検証
- トランザクショントークンによる二重送信防止

## 4. レイヤー間の依存関係ルール

### 4.1 基本ルール
- 上位レイヤーから下位レイヤーへの依存のみ許可
- 同一レイヤー内での依存は許可
- 下位レイヤーから上位レイヤーへの依存は禁止

### 4.2 具体的な依存ルール
1. Controller
   - Serviceへの依存可
   - Formの使用可
   - DTOの使用可
   - 他のControllerへの依存禁止

2. Service
   - Mapperへの依存可
   - DTOの使用可
   - Modelの使用可
   - Controllerへの依存禁止

3. Mapper
   - Modelの使用可
   - DTOの使用可
   - Service、Controllerへの依存禁止

## 5. 命名規約

### 5.1 クラス名
- Controller: `〇〇Controller`
- Service: `〇〇Service`
- Mapper: `〇〇Mapper`
- Model: `テーブル名をキャメルケースにしたクラス名`
- DTO: `〇〇Dto`
- Form: `〇〇Form`
- Configuration: `〇〇Config`
- ViewHelper: `〇〇ViewHelper`

### 5.2 メソッド名
- Controller
  - 一覧表示: `index`
  - 詳細表示: `detail`
  - 作成: `create`
  - 更新: `update`
  - 削除: `delete`

- Service
  - 取得: `get〇〇`
  - 検索: `search〇〇`
  - 作成: `create〇〇`
  - 更新: `update〇〇`
  - 削除: `delete〇〇`

## 6. セキュリティ関連ルール

### 6.1 認証・認可
- Spring Securityを使用
- すべてのエンドポイントに適切な認可設定を実装
- CSRF対策の実装必須

### 6.2 入力値検証
- すべてのフォーム入力に対するバリデーション実装必須
- クロスサイトスクリプティング対策の実装必須

## 7. 共通コンポーネントの使用規約

### 7.1 例外ハンドリング
- 業務例外は`BusinessException`を使用
- システム例外は`SystemException`を使用
- 例外ハンドリングは`ExceptionHandler`で一元管理

### 7.2 トランザクション管理
- `@Transactional`アノテーションを使用
- トランザクション境界はServiceレイヤーに設定

### 7.3 ログ出力
- 適切なログレベルの使用
  - ERROR: システムエラー
  - WARN: 業務エラー
  - INFO: 操作ログ
  - DEBUG: デバッグ情報

## 9. 設定ファイル管理

### 9.1 設定ファイルの配置
- アプリケーション設定: `application.properties`
- メッセージ: `messages.properties`
- 機能固有の設定: `properties/[機能名]/〇〇.properties`

### 9.2 環境依存値の管理
- 環境依存値は環境変数または外部設定ファイルで管理
- 機密情報はプロパティファイルに直接記載しない

## 10. リソース管理

### 10.1 静的リソース
- CSS: `static/css/`
- JavaScript: `static/js/`
- 画像: `static/images/`

### 10.2 テンプレート
- Thymeleafテンプレート: `templates/`
- 共通レイアウト: `templates/common/`
- 機能別テンプレート: `templates/[機能名]/`

### 10.3 Mybatis Mapperファイル
- Mapperファイル: `web/src/main/resources/com/example/web/project/mapper`


## 11. 使用ライブラリとフレームワーク

### 11.1 コアフレームワーク
- Spring Boot 3.x
  - Spring Web MVC: Webアプリケーションフレームワーク
  - Spring Security: 認証・認可フレームワーク
  - Spring AOP: アスペクト指向プログラミング
  - Spring Data Commons: データアクセス共通機能

### 11.2 テンプレートエンジン
- Thymeleaf
  - thymeleaf-extras-springsecurity6: Spring Security連携
  - 独自ダイアレクト: プロジェクト固有の拡張機能

### 11.3 データベース
- PostgreSQL: リレーショナルデータベース
- Spring Session JDBC: セッション管理

### 11.4 開発支援
- Spring Boot DevTools: 開発生産性向上
- Spring Boot Actuator: アプリケーション監視
- Spring Boot Configuration Processor: 設定メタデータ生成

### 11.5 その他
- Apache Commons Lang3: ユーティリティライブラリ
- Keel Transaction Token: 二重送信防止

## 12. Thymeleafヘルパー機能

### 12.1 コードヘルパー（CodeViewHelper）
```java
/**
 * @param codeId コードID
 * @param value コード値
 * @return 対応するコード名称
 */
public String getName(String codeId, String value)

/**
  * @param codeId コードID
  * @param pattern 使用するパターンのカラム名（大文字・小文字を区別せずに使用する）
  * @return コードIDとパターンに紐付くコード値
  */
public List<String> getValues(String codeId, String pattern)

// テンプレートでの使用例
<div
  class="form-check form-check-inline"
  th:each="codeValue : ${@codeViewHelper.getValues('C0200001','pattern01')}"
  th:classappend="${#fields.hasErrors('projectClass')} ? 'is-invalid' : ''"
>
  <input
    type="radio"
    class="form-check-input"
    th:field="*{projectClass}"
    th:value="${codeValue}"
    th:errorclass="is-invalid"
  />
  <label th:for="${#ids.prev('projectClass')}" class="form-check-label" th:text="${@codeViewHelper.getName('C0200001',codeValue)}">SS</label>
</div>
```

### 12.2 ページングヘルパー（PagingViewHelper）
```java
/**
 * 現在のページ情報をもとにナビゲーションを行うページ番号の配列を生成して返す。
 *
 * @param result 現在のページ情報
 * @return ナビゲーションを行うページ番号の配列
 */
public int[] getPageNumbers(Page<?> result)

// テンプレートでの使用例
<li class="page-item" th:classappend="${pageNumber == page.number} ? 'active' : ''"  th:each="pageNumber : ${@pagingViewHelper.getPageNumbers(page)}">
  <a class="page-link" th:if="${pageNumber != page.number}" th:href="@{__${path}__/(pageNumber=${pageNumber})}" th:text="${pageNumber + 1}">1</a>
  <span class="page-link" th:if="${pageNumber == page.number}" th:text="${pageNumber + 1}"></span>
</li>
```
