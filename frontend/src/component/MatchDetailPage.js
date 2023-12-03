/** @format */

import React from "react";
import { useParams } from "react-router-dom";

const MatchDetailPage = () => {
  const { matchId } = useParams();

  // 매치에 대한 상세 정보 로직을 추가하세요. API 호출 등

  return (
    <div>
      <h2>매치 상세 정보 - {matchId}</h2>
      {/* 상세 정보를 표시하는 내용 추가 */}
    </div>
  );
};

export default MatchDetailPage;
