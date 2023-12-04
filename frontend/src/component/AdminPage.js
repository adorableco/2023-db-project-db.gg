/** @format */

// AdminPage.js

import React, { useState } from "react";
import "./AdminPage.css";

const AdminPage = () => {
  const [gameName, setGameName] = useState("");
  const [gameAccountLevel, setGameAccountLevel] = useState("");
  const [gameAccountTier, setGameAccountTier] = useState("");

  const handleSubmit = () => {
    // API 호출 및 데이터 전송 로직 추가
    const newSummoner = {
      game_name: gameName,
      game_account_level: gameAccountLevel,
      game_account_tier: gameAccountTier,
    };

    // 실제로는 여기에서 API로 데이터를 전송해야 합니다.
    console.log("New Summoner:", newSummoner);

    // 전송 후 상태 초기화
    setGameName("");
    setGameAccountLevel("");
    setGameAccountTier("");
  };

  return (
    <div className='admin-container'>
      <h2>새로운 소환사 추가 (관리자 창)</h2>
      <form onSubmit={(e) => e.preventDefault()}>
        <label>
          게임 이름:
          <input
            type='text'
            value={gameName}
            onChange={(e) => setGameName(e.target.value)}
          />
        </label>
        <label>
          게임 계정 레벨:
          <input
            type='number'
            value={gameAccountLevel}
            onChange={(e) => setGameAccountLevel(e.target.value)}
          />
        </label>
        <label>
          게임 계정 티어:
          <select
            value={gameAccountTier}
            onChange={(e) => setGameAccountTier(e.target.value)}
          >
            <option value=''>티어 선택</option>
            <option value='Iron'>아이언</option>
            <option value='Bronze'>브론즈</option>
            <option value='Silver'>실버</option>
            <option value='Gold'>골드</option>
            <option value='Platinum'>플레티넘</option>
            <option value='Diamond'>다이아몬드</option>
            <option value='Master'>마스터</option>
            <option value='Grandmaster'>그랜드마스터</option>
            <option value='Challenger'>챌린저</option>
          </select>
        </label>
        <button onClick={handleSubmit}>추가</button>
      </form>
    </div>
  );
};

export default AdminPage;
