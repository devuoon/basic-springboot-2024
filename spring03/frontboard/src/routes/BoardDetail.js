import { Link ,useParams } from "react-router-dom";
import React, {useEffect, useState} from "react";

import axios from 'axios';

function BoardDetail() {
  const [board, setBoard] = useState({});
  const [replyList, setReplyList] = useState([]); // 마지막!!!
  // var bno, title, content, createDate, modifyDate, writer;
  // var replyList = [];
  const params = useParams(null);

  const getBoardDetail = async (bno) => {
    const resp = (await axios.get("http://localhost:8080/api/board/detail/" + bno));

    const resultCode = resp.data.resultCode;
    if (resultCode == "OK") {
      setBoard(resp.data.data);
    }

    if (resp.data.data.replyList.length > 0) {
      setReplyList(resp.data.data.replyList);
    }
  }

  // 상세 RESTAPI 요청
  useEffect(() => {
    getBoardDetail(params.bno);
  }, []); // 사용하지 않으면 무한 로딩 발생

  return(
    <div className="container main">
            <h4 className="border-bottom py-2">{board.title}</h4>
            <div className="card my-3">
                <div className="card-body">
                    <div className="card-text">{board.content}</div>
                    <div className="d-flex justify-content-end">
                        {/* <div className="badge bg-light text-dark p-2 text-start mx-3">
                            <div className="mb-2">modified at</div>
                            <div></div>
                        </div> */}
                        <div className="badge bg-light text-dark p-2 text-start">
                            <div className="mb-2">
                                <span>{board.writer}</span>
                            </div>
                            <div>{board.createDate}</div>
                        </div>
                    </div>
                    <div className="my-3">
                    </div>
                </div>
            </div>
            <h5 className="border-bottom my-3 py-2">{`${replyList.length}개의 댓글이 있습니다.`}</h5>
            {replyList.map((reply) => (
                <div className="card my-3" key={reply.rno}>                    
                    <div className="card-body">
                        <div className="card-text">{reply.content}</div>
                        <div className="d-flex justify-content-end">                            
                            <div className="badge bg-light text-dark p-2 text-start">
                                <div className="mb-2">
                                    <span>{reply.writer}</span>
                                </div>
                                <div>{reply.createDate}</div>
                            </div>
                        </div>
                    </div>
                </div>
            ))}
        </div>
  )
}

export default BoardDetail;