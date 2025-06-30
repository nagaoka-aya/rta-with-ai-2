/**
 * プロジェクト登録画面のJavaScript
 */
document.addEventListener('DOMContentLoaded', function () {
    const divisionSelect = document.getElementById('divisionId');
    const organizationSelect = document.getElementById('organizationId');

    // 初期状態では部門プルダウンを無効化
    organizationSelect.disabled = true;

    // 事業部プルダウンの変更イベントリスナー
    divisionSelect.addEventListener('change', function () {
        const divisionId = this.value;

        // 部門プルダウンをクリア
        organizationSelect.innerHTML = '<option value="">選択してください</option>';

        if (divisionId === '') {
            // 事業部未選択時は部門プルダウンを無効化
            organizationSelect.disabled = true;
            return;
        }

        // ローディング表示
        organizationSelect.innerHTML = '<option value="">読み込み中...</option>';
        organizationSelect.disabled = true;

        // Ajax通信で部門一覧を取得
        fetch(`/project/departments?divisionId=${encodeURIComponent(divisionId)}`)
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
    });
});
