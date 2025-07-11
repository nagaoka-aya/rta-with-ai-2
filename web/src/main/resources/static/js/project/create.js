/**
 * プロジェクト登録画面のJavaScript
 */
document.addEventListener('DOMContentLoaded', function () {
    const divisionSelect = document.getElementById('divisionId');
    const organizationSelect = document.getElementById('organizationId');

    // 初期状態では部門プルダウンを無効化
    organizationSelect.disabled = true;

    // サーバーサイドから渡されたデータを使用
    const divisionValue = window.formData ? window.formData.divisionId : '';
    const organizationValue = window.formData ? window.formData.organizationId : '';

    if (divisionValue) {
        loadDepartments(divisionValue, organizationValue);
    }

    // 事業部プルダウンの変更イベントリスナー
    divisionSelect.addEventListener('change', function () {
        const divisionId = this.value;

        if (divisionId) {
            loadDepartments(divisionId, null);
        } else {
            // 事業部未選択時は部門プルダウンを初期化
            organizationSelect.innerHTML = '<option value="">選択してください</option>';
            organizationSelect.disabled = true;
        }
    });

    function loadDepartments(divisionId, selectedOrganizationId) {
        const url = `/project/departments?divisionId=${encodeURIComponent(divisionId)}`;

        // 部門一覧を取得
        fetch(url)
            .then(response => {
                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }
                return response.json();
            })
            .then(departments => {
                // 部門プルダウンを再構築
                organizationSelect.innerHTML = '<option value="">選択してください</option>';

                departments.forEach(department => {
                    const option = document.createElement('option');
                    option.value = department.organizationId;
                    option.textContent = department.organizationName;

                    // 復元時の選択状態を設定
                    if (selectedOrganizationId && department.organizationId == selectedOrganizationId) {
                        option.selected = true;
                    }

                    organizationSelect.appendChild(option);
                });

                // 部門プルダウンを有効化
                organizationSelect.disabled = false;
            })
            .catch(error => {
                console.error('部門一覧の取得に失敗しました:', error);
                alert('部門一覧の取得に失敗しました。しばらく時間をおいて再度お試しください。');

                // エラー時は部門プルダウンを初期状態に戻す
                organizationSelect.innerHTML = '<option value="">選択してください</option>';
                organizationSelect.disabled = true;
            });
    }
});