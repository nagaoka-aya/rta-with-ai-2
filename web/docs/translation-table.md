実装の英単語を決定する場合は以下の英単語表を守って決定してください。

# テーブル・カラム 英日対応表

## テーブル

| 英語 | 日本語 |
|------|--------|
| BatchJobExecution | バッチジョブ実行 |
| BatchJobExecutionContext | バッチジョブ実行コンテキスト |
| BatchJobExecutionParams | バッチジョブ実行パラメータ |
| BatchJobInstance | バッチジョブインスタンス |
| BatchStepExecution | バッチステップ実行 |
| BatchStepExecutionContext | バッチステップ実行コンテキスト |
| BusinessDate | 業務日付 |
| CodeName | コード名称 |
| CodePattern | コードパターン |
| MailAttachedFile | メール添付ファイル |
| MailRecipient | メール送信先 |
| MailRequest | メール送信要求 |
| MailTemplate | メールテンプレート |
| Organization | 組織 |
| Project | プロジェクト |
| ProjectsByUser | ユーザ別従事プロジェクト |
| ProjectsByUserRequest | ユーザ別従事プロジェクト作成要求 |
| ProjectUser | プロジェクト担当者 |
| ProjectWork | プロジェクトワーク |
| ResidentBatchState | 常駐バッチ状態管理 |
| SpringSession | Springセッション |
| SpringSessionAttributes | Springセッション属性 |
| SystemAccount | システムアカウント |
| Users | ユーザ |

## カラム

### バッチ関連

| 英語 | 日本語 |
|------|--------|
| jobExecutionId | ジョブ実行ID |
| version | バージョン番号 |
| jobInstanceId | ジョブインスタンスID |
| createTime | 作成日時 |
| startTime | 開始日時 |
| endTime | 終了日時 |
| status | ステータス |
| exitCode | 終了コード |
| exitMessage | 終了メッセージ |
| lastUpdated | 最終更新日時 |
| shortContext | ショートコンテキスト |
| serializedContext | シリアライズドコンテキスト |
| parameterName | パラメータ名 |
| parameterType | データ型 |
| parameterValue | パラメータ値 |
| identifying | 一意識別フラグ |
| jobName | ジョブ名 |
| jobKey | ジョブキー |
| stepExecutionId | ステップ実行ID |
| stepName | ステップ名 |
| commitCount | コミット回数 |
| readCount | 読み込みデータ件数 |
| filterCount | フィルタリングデータ件数 |
| writeCount | 書込データ件数 |
| readSkipCount | 読込スキップデータ件数 |
| writeSkipCount | 書込スキップデータ件数 |
| processSkipCount | プロセススキップデータ件数 |
| rollbackCount | ロールバック回数 |

### プロジェクト関連

| 英語 | 日本語 |
|------|--------|
| projectId | プロジェクトID |
| projectName | プロジェクト名 |
| projectType | プロジェクト種別 |
| projectClass | プロジェクト分類 |
| projectStartDate | プロジェクト開始日付 |
| projectEndDate | プロジェクト終了日付 |
| projectManager | プロジェクトマネージャー |
| projectLeader | プロジェクトリーダー |
| projectWorkId | プロジェクトワークID |

### ユーザ関連

| 英語 | 日本語 |
|------|--------|
| userId | ユーザID |
| loginId | ログインID |
| userPassword | パスワード |
| passwordExpirationDate | パスワード有効期限 |
| failedCount | 認証失敗回数 |
| effectiveDateFrom | 有効期限(FROM) |
| effectiveDateTo | 有効期限(TO) |
| lastLoginDateTime | 最終ログイン日時 |
| kanjiName | 漢字氏名 |
| kanaName | ふりがな |
| pmFlag | PM職フラグ |

### メール関連

| 英語 | 日本語 |
|------|--------|
| mailRequestId | メール送信要求ID |
| serialNumber | 連番 |
| fileName | 添付ファイル名/ファイル名 |
| contentType | Content-Type |
| attachedFile | 添付ファイル |
| recipientType | 送信先区分 |
| mailAddress | メールアドレス |
| subject | 件名 |
| mailFrom | 送信者メールアドレス |
| replyTo | 返信先メールアドレス |
| returnPath | 差戻し先メールアドレス |
| charset | 文字セット |
| requestDateTime | 要求日時 |
| sendDateTime | 送信日時 |
| mailBody | 本文 |
| mailTemplateId | メールテンプレートID |

### その他

| 英語 | 日本語 |
|------|--------|
| organizationId | 組織ID |
| organizationName | 組織名 |
| upperOrganization | 上位組織ID |
| clientId | 顧客ID |
| note | 備考 |
| sales | 売上高 |
| requestId | 要求ID |
| expireDateTime | 有効期限 |
| segmentId | 区分 |
| bizDate | 業務日付 |
| codeId | コードID |
| codeValue | コード値 |
| lang | 言語 |
| sortOrder | ソート順 |
| codeName | 名称 |
| shortName | 略称 |
| option1-10 | オプション名称1-10 |
| pattern1-20 | コードパターン1-20 |
| primaryId | プライマリーID |
| sessionId | セッションID |
| maxInactiveInterval | 最大非活性時間 |
| expiryTime | 有効期限 |
| principalName | プリンシパル名 |
| attributeName | 属性名 |
| attributeBytes | 属性値 |
| jobId | ジョブID |
| running | 実行フラグ |