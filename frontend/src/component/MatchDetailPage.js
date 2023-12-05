/** @format */

// MatchDetailPage.js

import React, { useEffect, useState } from "react";
import axios from "axios";
import "./MatchDetailPage.css";
import { useParams, useNavigate, Link } from "react-router-dom";

const MatchDetailPage = () => {
  const [matchDetails, setMatchDetails] = useState({});
  const { matchId } = useParams();
  const navigate = useNavigate();

  const [matchResults, setMatchResults] = useState([]);
  const [matchAnalysis, setMatchAnalysis] = useState([]);
  const mockItem = {
    events: [
      {
        eventTime: "게임내 시간",
        eventType: "CHAMPION_KILL",
        participantId: 1, // 위에 summoner아이디랑 같은값.
        itemName: "아이템이름",
      },
      {
        eventTime: "게임내 시간",
        eventType: "CHAMPION_KILL",
        participantId: 1, // 위에 summoner아이디랑 같은값.
        itemName: "아이템이름",
      },
    ],
  };

  const handleSameChamp = (e, champName) => {
    e.preventDefault();
    navigate(`/champ/${champName}`);
  };

  const getRandomImage = () => {
    const randomImageWidth = Math.floor(Math.random() * 400) + 200;
    const randomImageHeight = Math.floor(Math.random() * 400) + 200;
    return `https://picsum.photos/${randomImageWidth}/${randomImageHeight}`;
  };

  useEffect(() => {
    const fetchMatchDetails = async () => {
      try {
        const response = await axios.get(
          `/summoner/match/log?matchId=${matchId}`,
        );
        setMatchDetails(response.data);
      } catch (error) {
        console.error("Error fetching match details:", error);
      }
    };

    const fetchMatchAnalysis = async () => {
      try {
        await axios
          .get(`/phase2/query10?matchId=${matchId}`, {
            matchId: matchDetails.matchId,
          })
          .then((res) => {
            setMatchAnalysis(res.data.response);
          });
      } catch (error) {
        console.error("Error fetching match analysis:", error);
      }
    };

    const fetchMatchResults = async () => {
      try {
        await axios.get(`/phase2/query13?duration=1100`).then((res) => {
          setMatchResults(res.data.response);
        });
      } catch (error) {
        console.error("Error fetching match results:", error);
      }
    };

    fetchMatchDetails();
    fetchMatchResults();
    fetchMatchAnalysis();
  }, [matchId]);

  return (
    <div className='match-detail-page'>
      <h1>매치 상세 정보</h1>

      <div className='match-results' style={{ left: "4px" }}>
        <h4 style={{ marginBottom: "0px" }}>비슷한 시간대의 진행된</h4>
        <h4 style={{ marginTop: "0px" }}>경기들은 어떨까?</h4>
        {matchAnalysis.map((result, index) => (
          <div key={index} className='match-result'>
            <div className='match-result'>
              <Link
                style={{ textDecoration: "none", color: "inherit" }}
                to={{
                  pathname: `/match_detail/${result.matchId}`,
                }}
              >
                <p className='linkP'>매치 ID: {result.matchId}</p>
              </Link>

              <p>진행 시간: {result.duration} 초</p>
            </div>
          </div>
        ))}
      </div>

      <div style={{ display: "flex", flexDirection: "row" }}>
        <div className='participants'>
          {matchDetails.summoners &&
            matchDetails.summoners.map((summoner) => (
              <div key={summoner.participantId} className='participant'>
                <img
                  src={getRandomImage()}
                  alt={summoner.selectedChampion}
                  className='champion-image'
                />
                <div
                  style={{
                    display: "flex",
                    flexDirection: "column",
                    minWidth: "200px",
                  }}
                >
                  <p>참가자 번호 : {summoner.participantId}</p>
                  <p>{summoner.summonerName}</p>
                  <p>Champion: {summoner.selectedChampion} </p>
                  <button
                    style={{ marginTop: "15px", marginLeft: "10px" }}
                    onClick={(e) => {
                      handleSameChamp(e, summoner.selectedChampion);
                    }}
                  >
                    같은 챔피언을 사용한
                    <br /> 소환사 보기
                  </button>
                </div>
              </div>
            ))}
        </div>
        <div className='match-results'>
          <h4 style={{ marginBottom: "0px" }}>진행 시간이 비슷한</h4>
          <h4 style={{ marginTop: "0px" }}>경기들의 결과는?</h4>
          {matchResults.map((result, index) => (
            <div key={index} className='match-result'>
              <div className={`match-result ${result.isWin ? "winner" : ""}`}>
                <p>
                  {result.isWin ? "승리" : "패배"}- 팀 {result.teamId}
                </p>
                <p>진행 시간: {result.duration} 초</p>
              </div>
            </div>
          ))}
        </div>
      </div>
      <div className='events'>
        <h2>Events</h2>
        {mockItem &&
          mockItem.events.map((event, index) => (
            <div key={index} className='event'>
              <p>{event.eventTime}</p>
              <p>{event.eventType}</p>
              <p>{event.participantId}</p>
              {event.eventType === "ITEM_PURCHASED" ? (
                <p>{event.itemName}</p>
              ) : null}
            </div>
          ))}
      </div>
    </div>
  );
};

export default MatchDetailPage;
