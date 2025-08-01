---
applyTo: "web/src/main/resources/templates/**/*.html"
---

## 外部ファイルの参照

- 画面固有のcssおよびjsファイルを参照させる場合は以下のように実装すること
```html
<!--/* 画面固有のJavaScriptファイル */-->
  <th:block th:fragment="additionalScripts">
    <!-- サーバーサイドデータをJavaScriptに渡す -->
    <script th:src="@{/path/to/filename.js}" src="../../static/path/to/filename.js" defer></script>
  </th:block>
```

## エラーの表示

- 特定の項目に紐づかない業務エラーは入力フォーム上部に表示すること
  実装例
  ```
  <form method="POST" th:action="@{/xxx/yyyy}" action="./xxxx.html" th:object="${xxxxForm}">
      <span th:if="${#fields.hasGlobalErrors()}" th:errors="*{global}" class="text-danger fw-bold"></span>
  </form>
  ```
- 単項目精査エラー、相関精査エラーの場合は、該当の項目に`is-invalid`クラスを付与すること。また、該当項目の直下に`invalid-feedback`クラスのエラーメッセージを表示すること
  実装例
  ```
  <input type="text" th:field="*{projectManager}" placeholder="PM" class="form-control" th:errorclass="is-invalid" maxlength="128"/>
  <span th:if="${#fields.hasErrors('projectManager')}" th:errors="*{projectManager}" class="invalid-feedback"></span>
  ```


## 必須項目の表現

- 必須項目には `<span class="fw-6 text-danger">*</span>` を付与すること


## モーダル画面の表示

- サブウィンドウは全てモーダルとして実装する
- モーダルの表示にはbootstrapのdata-bs属性を使用する

## 12. Thymeleafヘルパー機能

### 12.1 コードヘルパー（CodeViewHelper）
コード値を画面に表示するときはCodeViewHelperを利用する。

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

// テンプレートでの使用例（コード値をvalue、コード名称をlabelとしてラジオボタンを出力する例）
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
ページングを行う際はPagingViewHelperを利用する。

```html
<div th:replace="common/paging :: navigation('path', Pageオブジェクト)"></div>
```