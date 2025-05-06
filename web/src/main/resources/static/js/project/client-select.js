/**
 * 顧客選択時の処理
 * 顧客検索画面から選択された顧客情報を親画面に反映する
 */

/**
 * 顧客情報を親画面に設定する
 * @param {string} clientId - 顧客ID
 * @param {string} clientName - 顧客名
 */
function selectClient(clientId, clientName) {
    // 親画面のフォーム要素に値を設定
    if (window.opener && !window.opener.closed) {
        var opener = window.opener;
        opener.document.getElementById('clientId').value = clientId;
        opener.document.getElementById('clientName').value = clientName;

        // 画面を閉じる
        window.close();
    }
}

/**
 * 顧客検索画面をポップアップで開く
 */
function openClientSearchPopup() {
    // 画面サイズ
    var width = 800;
    var height = 600;

    // 画面の中央に配置
    var left = (screen.width - width) / 2;
    var top = (screen.height - height) / 2;

    // ポップアップウィンドウを開く
    window.open(
        'client-search',
        'ClientSearchWindow',
        'width=' + width + ',height=' + height + ',top=' + top + ',left=' + left + ',resizable=yes,scrollbars=yes'
    );

    return false;
}

// 顧客検索ボタンにイベントリスナーを設定
document.addEventListener('DOMContentLoaded', function () {
    var clientSearchButton = document.getElementById('clientSearchButton');
    if (clientSearchButton) {
        clientSearchButton.addEventListener('click', openClientSearchPopup);
    }
});
