package com.example.batch.project.writer;

import com.example.batch.project.item.CreateUsersProjectsItem;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.transform.FieldExtractor;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

/**
 * ユーザ別従事プロジェクト抽出バッチで出力するCSVの項目を抽出しフォーマットを行うExtractor。
 */
@Component
@StepScope
public class CreateUsersProjectsFieldExtractor implements FieldExtractor<CreateUsersProjectsItem> {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("uuuu/MM/dd");

    @Override
    public Object[] extract(CreateUsersProjectsItem project) {
        return new Object[] {
                project.getProjectId(),
                project.getProjectName(),
                project.getProjectType(),
                project.getProjectClass(),
                project.getProjectStartDate().format(DATE_FORMATTER),
                project.getProjectEndDate().format(DATE_FORMATTER),
                project.getOrganizationId(),
                project.getClientId(),
                project.getProjectManager(),
                project.getProjectLeader(),
                project.getNote(),
                project.getSales(),
                project.getVersionNo()
        };
    }
}
