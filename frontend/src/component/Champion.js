/** @format */

// Champion.js

import React, { useEffect, useState } from "react";
import "./Champion.css";
import { useParams } from "react-router-dom";
import axios from "axios";

const Champion = () => {
  const { champName } = useParams();
  const [championStats, setChampionStats] = useState([]);

  useEffect(() => {
    const fetchChampionStats = () => {
      console.log("here!");
      axios.get(`/phase2/query7?champName=${champName}`).then((res) => {
        setChampionStats(res.data.response);
      });
    };
    fetchChampionStats();
  });

  const calculateAverage = (statsArray, property) => {
    const total = statsArray.reduce((acc, stats) => acc + stats[property], 0);
    return (total / statsArray.length).toFixed(2);
  };

  return (
    <div className='champion-container'>
      <h2>{champName}을(를) 사용한 소환사 전적 분석</h2>
      <div className='average-stats'>
        <p>전체 킬 평균: {calculateAverage(championStats, "kill")} 회</p>
        <p>전체 데스 평균: {calculateAverage(championStats, "death")} 회</p>
        <p>
          전체 어시스트 평균: {calculateAverage(championStats, "assist")} 회
        </p>
      </div>
      <ul className='champion-stats'>
        {championStats.map((stats, index) => (
          <li key={index} className='stats-item'>
            <p>킬: {stats.kill} 회</p>
            <p>데스: {stats.death} 회</p>
            <p>어시스트: {stats.assist} 회</p>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default Champion;
