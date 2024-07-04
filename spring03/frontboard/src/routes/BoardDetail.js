import { Link ,useParams } from "react-router-dom";
import React, {useEffect} from "react";

import axios from 'axios';

function BoardDetail() {

  //const [bno, setBno] = useState(0);

  const params = useParams();
  console.log(params.bno);

  const getBoardDetail = async (bno) => {
    const resp = (await axios.get("http://localhost:8080/api/board/detail/" + bno));

    const resultCode = resp.data.resultCode;
    console.log(resultCode);
    console.log(resp.data.data);
  }

  useEffect(() => {
    getBoardDetail(params.bno);
  }, null);

  return(
    <div className="container main">
      <h1>boardDetail {params.bno}</h1>
    </div>
  )
}

export default BoardDetail;