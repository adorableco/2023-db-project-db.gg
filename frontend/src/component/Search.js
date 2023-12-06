/** @format */

// Search.js

import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "./Search.css";

const Search = () => {
  const [summonerName, setSummonerName] = useState("");
  const navigate = useNavigate();

  const handleInputChange = (e) => {
    setSummonerName(e.target.value);
  };

  const handleSearch = async () => {
    try {
      await axios.get(`/summoner?summonerName=${summonerName}`).then((res) => {
        navigate(`/summoner_list/${summonerName}`, {
          state: res.data,
        });
      });
    } catch {
      console.error("search summoner failed");
    }
  };

  const handleNewUser = () => {
    navigate("/user_edit");
  };

  const handleItem = () => {
    navigate("/item_edit");
  };

  const handleChamp = () => {
    navigate("/champ_edit");
  };

  return (
    <div className='app'>
      <img src='/logo.png' style={{ width: "60px" }} />
      <h3 style={{ marginBottom: "0px", marginTop: "0px" }}>
        롤 매치의 모든 정보
      </h3>
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
      <button onClick={handleNewUser} style={{ marginTop: "10px" }}>
        사용자 데이터 수정하기
      </button>
      <button onClick={handleItem} style={{ marginTop: "10px" }}>
        아이템 데이터 수정하기
      </button>
      <button onClick={handleChamp} style={{ marginTop: "10px" }}>
        챔피언 데이터 수정하기
      </button>
      <footer style={{ color: "gray", fontSize: "12px", marginTop: "80px" }}>
        2023 경북대학교 데이터베이스 수업 프로젝트
      </footer>
    </div>
  );
};

export default Search;
