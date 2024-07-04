import { Link ,useParams } from "react-router-dom";
import React, {useEffect, useState} from "react";

import axios from 'axios';

function BoardDetail() {
  const [board, setBoard] = useState(0);
  var bno, title, content, createDate, modifyDate, writer;
  var replyList = [];
  const params = useParams();

  const getBoardDetail = async (bno) => {
    const resp = (await axios.get("http://localhost:8080/api/board/detail/" + bno));

    const resultCode = resp.data.resultCode;
    if (resultCode == "OK") {
      setBoard(resp.data.data);
    }
  }

  // 상세 RESTAPI 요청
  getBoardDetail(params.bno);

  return(
    <div className="container main">
      <h1>boardDetail {params.bno}</h1>
      <h5>{board.title}</h5>
      <h5>{board.content}</h5>
    </div>
  )
}

export default BoardDetail;