/** @format */

// Search.js

import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./Search.css";

const Search = () => {
  const [summonerName, setSummonerName] = useState("");
  const navigate = useNavigate();

  const handleInputChange = (e) => {
    setSummonerName(e.target.value);
  };

  const handleSearch = () => {
    // 실제로는 소환사 검색 로직이 들어갑니다.
    // ...

    // 임시로 SummonerListPage로 이동
    navigate("/summoner-list");
  };

  return (
    <div className='app'>
      <h3 style={{ marginBottom: "0px" }}>롤 매치의 모든 정보</h3>
      <h1 style={{ marginTop: "0px" }}>소환사 검색</h1>
      <div className='search-container'>
        <input
          type='text'
          placeholder='소환사명을 입력하세요'
          value={summonerName}
          onChange={handleInputChange}
        />
        <button onClick={handleSearch}>검색</button>
      </div>
    </div>
  );
};

export default Search;
