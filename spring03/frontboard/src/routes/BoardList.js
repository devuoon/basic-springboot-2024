import axios from 'axios';  // REST API 

// Hook 함수 사용
import React, { useState, useEffect } from 'react';

// Navigation
import { Link } from 'react-router-dom';

function BoardList() {  // 객체를 만드는 함수

  const [boardList, setBoardList] = useState([]);

  // 함수 선언
  // 제일 중요!!
  const getBoardList = async () => {
    // 변수 선언
    var pageString = 'page=0';

    try {
      const resp = (await axios.get("//localhost:8080/api/board/list/free?" + pageString));
      
      if (resp.status === 200) {
        setBoardList(resp.data);
        console.log(resp.data);
      } else if (resp.status === 404){
        alert('서버 페이지가 없습니다.');
      } else if (resp.status === 500) {
        alert('서버  오류 입니다.');
      }
    
    } catch {
      // console.error('>>' + error);
      alert('서버가 연결되지 않았습니다.');
    }
  } 

  useEffect(() => {
    getBoardList();
  }, []);

  return(
    <div className="container">
      <table className='table table-striped'>
        <thead className='table-primary'>
          <tr className='text-center'>
            <th>번호</th>
            <th style={{ width: '50%' }}>제목</th>
            <th>글쓴이</th>
            <th>조회수</th>
            <th>작성일시</th>
          </tr>
        </thead>
        <tbody>
         {/* 반복으로 들어갈 부분 */}
         {boardList.map((board) => (
          <tr className='text-center' key={board.bno}>
            <td>{board.bno}</td>
            <td className='text-start'>{board.title}</td>
            <td>{board.writer}</td>
            <td>{board.hit}</td>
            <td>{board.createDate}</td>
          </tr>
        ))}
        </tbody>
      </table>
    </div>
  )
}

export default BoardList;