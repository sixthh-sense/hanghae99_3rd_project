// 미리 작성된 영역 - 수정하지 않으셔도 됩니다.
// 사용자가 내용을 올바르게 입력하였는지 확인합니다.
function isValidContents(comment) {
    if (comment == '') {
        alert('내용을 입력해주세요');
        return false;
    }
    if (comment.trim().length > 140) {
        alert('공백 포함 140자 이하로 입력해주세요');
        return false;
    }
    return true;
}

// 익명의 name을 만듭니다.
function genRandomName(length) {
    let result = '';
    let characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    let charactersLength = characters.length;
    for (let i = 0; i < length; i++) {
        let number = Math.random() * charactersLength;
        let index = Math.floor(number);
        result += characters.charAt(index);
    }
    return result;
}

// 수정 버튼을 눌렀을 때, 기존 작성 내용을 textarea 에 전달합니다.
// 숨길 버튼을 숨기고, 나타낼 버튼을 나타냅니다.
function editPost(id) {
    showEdits(id);
    let comment = $(`#${id}-comment`).text().trim();
    $(`#${id}-textarea`).val(comment);
}

function showEdits(id) {
    $(`#${id}-editarea`).show();
    $(`#${id}-submit`).show();
    $(`#${id}-delete`).show();

    $(`#${id}-comment`).hide();
    $(`#${id}-edit`).hide();
}

function hideEdits(id) {
    $(`#${id}-editarea`).hide();
    $(`#${id}-submit`).hide();
    $(`#${id}-delete`).hide();

    $(`#${id}-comment`).show();
    $(`#${id}-edit`).show();
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// 여기서부터 코드를 작성해주시면 됩니다.

$(document).ready(function () {
    // HTML 문서를 로드할 때마다 실행합니다.
    getMessages();
})

// 메모를 불러와서 보여줍니다.
function getMessages() {
    // 1. 기존 메모 내용을 지웁니다.
    $('#cards-box').empty();
    // 2. 메모 목록을 불러와서 HTML로 붙입니다.
    $.ajax({
        type: 'GET',
        url: '/api/memories',
        success: function (response) {
            for (let i = 0; i < response.length; i++) {
                let memory = response[i];
                let id = memory.id;
                let title = memory.title;
                let name = memory.name;
                let comment = memory.comment;
                let modifiedAt = memory.modifiedAt;
                addHTML(id, title, name, comment, modifiedAt);
            }
        }
    })
}

// 메모 하나를 HTML로 만들어서 body 태그 내 원하는 곳에 붙입니다.
function addHTML(id, title, name, comment, modifiedAt) {
    // 1. HTML 태그를 만듭니다.
    let tempHtml = `<div class="card">
        <!-- date/name 영역 -->
        <div class="metadata">
            <div class="date">
                ${modifiedAt}
            </div>
            <div id="${id}-name" class="name">
                ${name}
            </div>
        </div>
        <!-- comment 조회/수정 영역-->
        <div class="comment">
            <div id="${id}-comment" class="text">
                ${comment}
            </div>
            <div id="${id}-editarea" class="edit">
                <textarea id="${id}-textarea" class="te-edit" name="" id="" cols="30" rows="5"></textarea>
            </div>
        </div>
        <!-- 버튼 영역-->
        <div class="footer">
            <img id="${id}-edit" class="icon-start-edit" src="images/edit.png" alt="" onclick="editPost('${id}')">
            <img id="${id}-delete" class="icon-delete" src="images/delete.png" alt="" onclick="deleteOne('${id}')">
            <img id="${id}-submit" class="icon-end-edit" src="images/done.png" alt="" onclick="submitEdit('${id}')">
        </div>
    </div>`;
    // 2. #cards-box 에 HTML을 붙인다.
    $('#cards-box').append(tempHtml);
}

// 메모를 생성합니다.
function writePost() {
    // 1. 작성한 메모를 불러옵니다.
    let comment = $('#comment').val();
    // 2. 작성한 메모가 올바른지 isValidContents 함수를 통해 확인합니다.
    if (isValidContents(comment) == false) {
        return;
    }
    // 3. genRandomName 함수를 통해 익명의 name을 만듭니다.
    let name = genRandomName(10);
    // 4. 전달할 data JSON으로 만듭니다.
    let data = {'name': name, 'comment': comment};
    // 5. POST /api/memos 에 data를 전달합니다.
    $.ajax({
        type: "POST",
        url: "/api/memories",
        contentType: "application/json", // JSON 형식으로 전달함을 알리기
        data: JSON.stringify(data),
        success: function (response) {
            alert('메시지가 성공적으로 작성되었습니다.');
            window.location.reload(); // 새로고침
        }
    });
}

// 메모를 수정합니다.
function submitEdit(id) {
    // 1. 작성 대상 메모의 name과 comment 를 확인합니다.
    let name = $(`#${id}-name`).text().trim();
    let comment = $(`#${id}-textarea`).val().trim();
    // 2. 작성한 메모가 올바른지 isValidContents 함수를 통해 확인합니다.
    if (isValidContents(comment) == false) {
        return;
    }
    // 3. 전달할 data JSON으로 만듭니다.
    let data = {'name': name, 'comment': comment};
    // 4. PUT /api/memos/{id} 에 data를 전달합니다.
    $.ajax({
        type: "PUT",
        url: `/api/memories/${id}`,
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function (response) {
            alert('메시지 변경에 성공하였습니다.');
            window.location.reload();
        }
    });
}

// 메모를 삭제합니다.
function deleteOne(id) {
    $.ajax({
        type: "DELETE",
        url: `/api/memories/${id}`,
        success: function (response) {
            alert('메시지 삭제에 성공하였습니다.');
            window.location.reload();
        }
    })
}