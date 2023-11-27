/** @format */

import React from "react";
import { useState } from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import SearchResultPage from "./SearchResultPage";
import MatchDetailPage from "./MatchDetailPage";
import "./Search.css";

const Search = () => {
  const [summonerName, setSummonerName] = useState("");
  const [searchResult, setSearchResult] = useState([]);

  const handleInputChange = (e) => {
    setSummonerName(e.target.value);
  };

  const handleSearch = async () => {
    // 검색 로직을 추가하세요. 예를 들면, API 호출 등
    console.log("Searching for summoner:", summonerName);

    // 가상의 매치 데이터 예시
    const fakeMatchData = [
      { matchId: "1", result: "Win" },
      { matchId: "2", result: "Loss" },
      // ... 더 많은 매치 데이터
    ];

    setSearchResult(fakeMatchData);
  };

  return (
    <div className='app'>
      <h1>롤 전적 검색</h1>
      <div className='search-container'>
        <input
          type='text'
          placeholder='소환사명을 입력하세요'
          value={summonerName}
          onChange={handleInputChange}
        />
        <button onClick={handleSearch}>검색</button>
      </div>

      <Routes>
        <Route
          path='/search-result'
          element={<SearchResultPage matches={searchResult} />}
        ></Route>
        <Route path='/match/:matchId' element={<MatchDetailPage />}></Route>
      </Routes>
    </div>
  );
};

export default Search;
