---
applyTo: "web/src/main/resources/templates/**/*.html"
---

## 外部ファイルの参照

- cssおよびjsファイルの参照はhead要素内に含めること


## エラーの表示

- 特定の項目に紐づかない業務エラーは画面上部に表示すること
  実装例
  ```
  <body class="container">
      <span th:if="${errorMessage}" th:text="${errorMessage}"></span>
      画面レイアウト
  </body>
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