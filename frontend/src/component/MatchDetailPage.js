/** @format */

// MatchDetailPage.js

import React, { useEffect, useState } from "react";
import axios from "axios";
import "./MatchDetailPage.css";
import { useParams, useNavigate, Link } from "react-router-dom";
import { Audio } from "react-loader-spinner";

const MatchDetailPage = () => {
  const [matchDetails, setMatchDetails] = useState({});
  const { matchId } = useParams();
  const navigate = useNavigate();
  const [isLoading, setIsLoading] = useState(false);

  const [matchResults, setMatchResults] = useState([]);
  const [matchAnalysis, setMatchAnalysis] = useState([]);

  const [currentPage, setCurrentPage] = useState(1); // 추가: 현재 페이지 상태
  const eventsPerPage = 20; // 추가: 페이지당 이벤트 수

  const handlePageChange = (pageNumber) => {
    setCurrentPage(pageNumber);
  };

  const handleSameChamp = (e, champName) => {
    e.preventDefault();
    navigate(`/champ/${champName}`);
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
        await axios.get(`/phase2/query10?matchId=${matchId}`).then((res) => {
          setMatchAnalysis(res.data.response);
        });
      } catch (error) {
        console.error("Error fetching match analysis:", error);
      }
    };

    const fetchMatchResults = async () => {
      try {
        await axios.get(`/phase2/query13?matchId=${matchId}`).then((res) => {
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
      <h2>매치 상세 정보</h2>

      <div
        className='match-results'
        style={{
          left: "4px",
          maxHeight: "600px",
          overflowY: "scroll",
          scrollBehavior: "smooth",
        }}
      >
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

      <div style={{ flexDirection: "row" }}>
        <div className='participants'>
          {matchDetails.summoners &&
            matchDetails.summoners.map((summoner) => (
              <div key={summoner.participantId} className='participant'>
                <img
                  src={`/img/${summoner.selectedChampionImage}`}
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
        <div
          className='match-results'
          style={{
            right: "4px",
            maxHeight: "600px",
            overflowY: "scroll",
            scrollBehavior: "smooth",
          }}
        >
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
        <h2>이벤트</h2>
        <h4>
          이벤트 항목을 누르면 그 이벤트와 일어난 시간이 비슷한 이벤트를 볼 수
          있습니다.
        </h4>
        {matchDetails.events &&
          matchDetails.events
            .slice(
              (currentPage - 1) * eventsPerPage,
              currentPage * eventsPerPage,
            )
            .map((event, index) => (
              <div className='event'>
                <Link
                  style={{ textDecoration: "none", color: "inherit" }}
                  key={index}
                  to={{
                    pathname: `/event/${event.eventTime}`,
                  }}
                >
                  <p style={{ fontWeight: "800" }}>
                    참가자 {event.participantId} 님의 {event.eventType}
                  </p>
                  <p>이벤트가 일어난 시간 : {event.eventTime} (초)</p>
                  {event.eventType === "ITEM_PURCHASED" ? (
                    <p>구매한 아이템 : {event.itemName}</p>
                  ) : null}
                </Link>
              </div>
            ))}
      </div>

      <ul className='pagination'>
        {matchDetails.events &&
          Array.from(
            {
              length: Math.ceil(matchDetails.events.length / eventsPerPage),
            },
            (_, index) => (
              <li
                key={index}
                onClick={() => handlePageChange(index + 1)}
                style={{
                  fontSize: "16px",
                  fontWeight: currentPage === index + 1 ? "bold" : "normal",
                }}
              >
                {index + 1}
              </li>
            ),
          )}
      </ul>
    </div>
  );
};

export default MatchDetailPage;
