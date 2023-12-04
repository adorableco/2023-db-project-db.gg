/** @format */

// SummonerListPage.js

import React, { useState } from "react";
import { useNavigate, useLocation, useParams } from "react-router-dom";
import "./SummonerListPage.css";
const SummonerListPage = () => {
  const { state } = useLocation();
  const { searchKeyword } = useParams();
  const [summoners, setSummoners] = useState(state.gameAccounts);
  const navigate = useNavigate();

  console.log(state);
  const handleSummonerClick = (summoner) => {
    // 클릭한 소환사에 해당하는 SearchResultPage 경로로 이동
    navigate(`/match_list/${summoner.gameName}`, { state: summoner });
  };

  return (
    <div
      style={{
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
      }}
    >
      <h2>"{searchKeyword}" (으)로 시작하는 소환사 이름 검색 결과</h2>
      <ul className='link-container'>
        {summoners.map((summoner) => (
          <li key={summoner.id} onClick={() => handleSummonerClick(summoner)}>
            <p>
              게임 이름 : {summoner.gameName} 게임 레벨 :{" "}
              {summoner.accountLevel}
            </p>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default SummonerListPage;
