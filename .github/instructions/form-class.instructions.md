---
applyTo: "web/src/**/*Form.java"
---

### 3.5 Formクラスの実装規約

#### 3.5.1 基本規約
- JavaBeansパターンに準拠（getter/setterの実装）
- プロパティには適切なJavaDocを付与

#### 3.5.2 バリデーション
- 必須チェックには@Requiredアノテーションを使用
- ドメインバリデーションには@Domainアノテーションを使用( ドメインの定義は #file:common\src\main\java\com\example\common\nablarch\validation\DomainBean.java 参照)
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
import org.springframework.format.annotation.DateTimeFormat;
import nablarch.core.validation.ee.Domain;
import nablarch.core.validation.ee.Required;

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