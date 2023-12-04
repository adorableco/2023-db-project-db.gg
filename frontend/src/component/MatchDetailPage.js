/** @format */

// MatchDetailPage.js

import React from "react";
import { useParams } from "react-router-dom";
import "./MatchDetailPage.css";

const mockMatchData = {
  summoner_name: "name",
  summoner_tier: "Challenger",
  summoner_level: 1,
  match_info: [
    {
      match_id: "KR_6766621885",
      kills: 1,
      deaths: 2,
      assists: 3,
      selected_champion: 5,
    },
    {
      match_id: "KR_6766621886",
      kills: 2,
      deaths: 1,
      assists: 4,
      selected_champion: 6,
    },
  ],
};

const MatchDetailPage = () => {
  const { matchId } = useParams();
  const { summoner_name, summoner_tier, summoner_level, match_info } =
    mockMatchData;

  return (
    <div className='match-detail'>
      <h2>{summoner_name}</h2>
      <p className='match-info'>티어: {summoner_tier}</p>
      <p className='match-info'>레벨: {summoner_level}</p>

      <h3>참가한 매치</h3>
      <ul>
        {match_info.map((match) => (
          <li key={match.match_id} className='participant'>
            <p style={{ fontWeight: "700" }}>매치 ID: {match.match_id}</p>
            <p>Kills: {match.kills}</p>
            <p>Deaths: {match.deaths}</p>
            <p>Assists: {match.assists}</p>
            <p>선택한 챔피언: {match.selected_champion}</p>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default MatchDetailPage;
