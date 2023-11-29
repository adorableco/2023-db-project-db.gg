/** @format */

import React from "react";
import { useState } from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import SearchResultPage from "./SearchResultPage";
import MatchDetailPage from "./MatchDetailPage";
import "./Search.css";
import axios from "axios";
import logo from "../img/logo.PNG";

const Search = () => {
  const [summonerName, setSummonerName] = useState("");
  const [searchResult, setSearchResult] = useState([]);

  const handleInputChange = (e) => {
    setSummonerName(e.target.value);
  };

  const handleSearch = async () => {
    await axios.get(`/summoners/match?name=${summonerName}`);
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
      <img src={logo} style={{ width: "60px" }} />
      <h3 style={{ marginBottom: "0px" }}>롤 매치의 모든 정보</h3>
      <h1 style={{ marginTop: "0px" }}> 롤 전적 검색 서비스</h1>
      <div className='search-container'>
        <input
          type='text'
          placeholder='소환사명을 입력하세요'
          value={summonerName}
          onChange={handleInputChange}
          required
          style={{ margin: "0px", marginRight: "10px" }}
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
