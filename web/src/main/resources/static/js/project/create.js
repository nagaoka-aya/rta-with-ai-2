/**
 * プロジェクト登録画面用JavaScript
 */
$(function () {
    'use strict';

    /**
     * 初期化処理
     */
    const init = function () {
        // 事業部プルダウン変更イベントの設定
        $('#divisionId').on('change', handleDivisionChange);

        // 顧客検索ボタンクリックイベントの設定
        $('#clientSearchButton').on('click', openClientSearchPopup);

        // フォーム送信前バリデーションの設定
        $('#projectCreateForm').on('submit', validateBeforeSubmit);

        // 初期表示時に事業部が選択されていれば部門を読み込む
        if ($('#divisionId').val()) {
            loadDepartments($('#divisionId').val());
        }
    };

    /**
     * 事業部変更時の処理
     * 選択された事業部IDに基づいて部門リストを非同期取得する
     */
    const handleDivisionChange = function () {
        const divisionId = $(this).val();

        if (divisionId) {
            loadDepartments(divisionId);
        } else {
            // 事業部が未選択の場合は部門リストをクリア
            clearDepartments();
        }
    };

    /**
     * 部門リストを非同期で取得する
     * @param {string} divisionId 事業部ID
     */
    const loadDepartments = function (divisionId) {
        // 部門リストの取得中表示
        $('#organizationId').prop('disabled', true);
        $('#loadingDepartments').show();

        $.ajax({
            url: `/project/create/departments/${divisionId}`,
            type: 'GET',
            dataType: 'json',
            cache: false
        })
            .done(function (departments) {
                updateDepartmentOptions(departments);
            })
            .fail(function (xhr, status, error) {
                showErrorMessage('部門情報の取得に失敗しました。');
                console.error('Error loading departments:', error);
            })
            .always(function () {
                $('#loadingDepartments').hide();
                $('#organizationId').prop('disabled', false);
            });
    };

    /**
     * 部門プルダウンの選択肢を更新する
     * @param {Array} departments 部門リスト
     */
    const updateDepartmentOptions = function (departments) {
        const $departmentSelect = $('#organizationId');
        const currentValue = $departmentSelect.val();

        // 既存のオプションをクリア
        $departmentSelect.empty();

        // 未選択オプションを追加
        $departmentSelect.append($('<option>').val('').text('選択してください'));

        // 部門オプションを追加
        $.each(departments, function (index, department) {
            const $option = $('<option>')
                .val(department.organizationId)
                .text(department.organizationName);

            $departmentSelect.append($option);
        });

        // 以前選択されていた値と同じものがあれば選択状態にする
        if (currentValue && $departmentSelect.find(`option[value="${currentValue}"]`).length > 0) {
            $departmentSelect.val(currentValue);
        }
    };

    /**
     * 部門プルダウンをクリアする
     */
    const clearDepartments = function () {
        const $departmentSelect = $('#organizationId');
        $departmentSelect.empty();
        $departmentSelect.append($('<option>').val('').text('事業部を選択してください'));
    };

    /**
     * 顧客検索画面をポップアップ表示する
     * @param {Event} e イベントオブジェクト
     */
    const openClientSearchPopup = function (e) {
        e.preventDefault();

        // ポップアップウィンドウの設定
        const width = 800;
        const height = 600;
        const left = (window.screen.width - width) / 2;
        const top = (window.screen.height - height) / 2;

        // ポップアップウィンドウを開く
        const popup = window.open(
            '/project/create/client-search',
            'clientSearchWindow',
            `width=${width},height=${height},left=${left},top=${top},resizable=yes,scrollbars=yes`
        );

        // ポップアップがブロックされた場合の処理
        if (!popup || popup.closed || typeof popup.closed === 'undefined') {
            showErrorMessage('ポップアップがブロックされました。ブラウザの設定を確認してください。');
        }

        // ポップアップからのコールバック関数を設定
        window.setSelectedClient = function (clientId, clientName) {
            $('#clientId').val(clientId);
            $('#clientName').val(clientName);
            popup.close();
        };
    };

    /**
     * フォーム送信前のクライアントサイドバリデーション
     * @param {Event} e イベントオブジェクト
     * @returns {boolean} バリデーション結果
     */
    const validateBeforeSubmit = function (e) {
        // エラーメッセージをクリア
        clearErrorMessages();

        let isValid = true;

        // 必須項目チェック
        const requiredFields = [
            { id: 'divisionId', message: '事業部を選択してください' },
            { id: 'organizationId', message: '部門を選択してください' },
            { id: 'projectName', message: 'プロジェクト名を入力してください' },
            { id: 'projectType', message: 'プロジェクト種別を選択してください' },
            { id: 'projectClass', message: 'プロジェクト分類を選択してください' },
            { id: 'clientId', message: '顧客を選択してください' },
            { id: 'projectStartDate', message: 'プロジェクト開始日を入力してください' },
            { id: 'projectEndDate', message: 'プロジェクト終了日を入力してください' }
        ];

        requiredFields.forEach(function (field) {
            if (!$(`#${field.id}`).val()) {
                showFieldError(field.id, field.message);
                isValid = false;
            }
        });

        // 日付の大小関係チェック
        if (isValid && $('#projectStartDate').val() && $('#projectEndDate').val()) {
            const startDate = new Date($('#projectStartDate').val());
            const endDate = new Date($('#projectEndDate').val());

            if (startDate > endDate) {
                showFieldError('projectEndDate', 'プロジェクト終了日はプロジェクト開始日以降の日付を入力してください');
                isValid = false;
            }
        }

        // 売上高の数値チェック
        if ($('#sales').val() && !/^[0-9]+(\.[0-9]+)?$/.test($('#sales').val())) {
            showFieldError('sales', '売上高は数値で入力してください');
            isValid = false;
        }

        if (!isValid) {
            e.preventDefault();
            showErrorSummary();
        }

        return isValid;
    };

    /**
     * エラーメッセージを表示する
     * @param {string} fieldId フィールドID
     * @param {string} message エラーメッセージ
     */
    const showFieldError = function (fieldId, message) {
        const $field = $(`#${fieldId}`);
        const $formGroup = $field.closest('.form-group');

        $formGroup.addClass('has-error');
        $formGroup.find('.help-block').remove();
        $formGroup.append($('<div>').addClass('help-block').text(message));
    };

    /**
     * エラーメッセージ一覧を表示する
     */
    const showErrorSummary = function () {
        const $errorSummary = $('#error-summary');

        if ($errorSummary.length === 0) {
            const $summary = $('<div>')
                .attr('id', 'error-summary')
                .addClass('alert alert-danger')
                .text('入力内容に誤りがあります。修正してください。');

            $('#projectCreateForm').prepend($summary);

            // 画面上部にスクロール
            $('html, body').animate({ scrollTop: 0 }, 200);
        }
    };

    /**
     * 指定されたメッセージでエラーを表示する
     * @param {string} message エラーメッセージ
     */
    const showErrorMessage = function (message) {
        const $errorMessage = $('<div>')
            .addClass('alert alert-danger alert-dismissible fade in')
            .attr('role', 'alert')
            .append(
                $('<button>')
                    .attr('type', 'button')
                    .addClass('close')
                    .attr('data-dismiss', 'alert')
                    .attr('aria-label', '閉じる')
                    .append($('<span>').attr('aria-hidden', 'true').html('&times;'))
            )
            .append(message);

        $('#messageArea').html($errorMessage);
    };

    /**
     * すべてのエラーメッセージをクリアする
     */
    const clearErrorMessages = function () {
        $('.form-group').removeClass('has-error');
        $('.help-block').remove();
        $('#error-summary').remove();
    };

    // 初期化処理を実行
    init();
});
