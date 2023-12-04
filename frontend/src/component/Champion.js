/** @format */

// Champion.js

import React, { useEffect, useState } from "react";
import "./Champion.css";
import { useParams } from "react-router-dom";

const mockData = {
  response: [
    {
      kill: 4,
      death: 7,
      assist: 6,
      champName: "아펠리오스",
    },
    {
      kill: 2,
      death: 6,
      assist: 0,
      champName: "아펠리오스",
    },
  ],
};

const Champion = () => {
  const [championStats, setChampionStats] = useState([
    {
      kill: 4,
      death: 7,
      assist: 6,
      champName: "아펠리오스",
    },
    {
      kill: 2,
      death: 6,
      assist: 0,
      champName: "아펠리오스",
    },
  ]);

  useEffect(() => {
    const fetchChampionStats = () => {
      // 여기에 나중에 API 호출 코드 추가
      // axios.get(`/champion/${match.params.championName}`).then((res) => {
      //   setChampionStats(res.data);
      // });
      setChampionStats(mockData.response);
    };

    fetchChampionStats();
  }, []);

  // 각 통계의 평균 계산
  const calculateAverage = (statsArray, property) => {
    const total = statsArray.reduce((acc, stats) => acc + stats[property], 0);
    return (total / statsArray.length).toFixed(2);
  };

  return (
    <div className='champion-container'>
      <h2>{championStats[0].champName}를 사용한 소환사 전적 분석</h2>
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
