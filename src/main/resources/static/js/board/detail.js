// 댓글 목록 불러오기
function loadComments(boardId) {
  fetch(`/api/comments/${boardId}`)
    .then(res => res.json())
    .then(data => {
      const list = document.getElementById('comment-list');
      list.innerHTML = '';
      data.data.forEach(c => {
        const li = document.createElement('li');
        li.innerHTML = `<strong>${c.writer}</strong>: ${c.content}`;
        list.appendChild(li);
      });
    });
}

// 댓글 등록
function initCommentForm(boardId) {
  const submitBtn = document.getElementById('comment-submit');
  submitBtn.addEventListener('click', () => {
    const writer = document.getElementById('comment-writer').value.trim();
    const content = document.getElementById('comment-content').value.trim();

    if (!writer || !content) {
      alert('작성자와 내용을 모두 입력해주세요!');
      return;
    }

    fetch('/api/comments', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ boardId, writer, content })
    })
      .then(res => res.json())
      .then(() => {
        loadComments(boardId);
        document.getElementById('comment-writer').value = '';
        document.getElementById('comment-content').value = '';
      });
  });
}
