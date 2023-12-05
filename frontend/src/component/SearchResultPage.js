/** @format */

import React, { useEffect, useState, useRef } from "react";
import { Link, useLocation, useParams, useNavigate } from "react-router-dom";
import axios from "axios";
import "./SearchResultPage.css";
import { Audio } from "react-loader-spinner";

const SearchResultPage = () => {
  const { summonerId } = useParams();
  const [matches, setMatches] = useState([]);
  const [loading, setLoading] = useState(true);
  const { state } = useLocation();
  const [currentPage, setCurrentPage] = useState(1);
  const [summoner, setSummoner] = useState();
  const matchesPerPage = 5;
  const navigate = useNavigate();

  const scrollRef = useRef(null);
  const isFirstLoad = useRef(true);

  useEffect(() => {
    const getMatches = async () => {
      return await axios
        .get(`/summoner/match?gameName=${summonerId}`)
        .then((res) => {
          setMatches(res.data.matchInfo);
        });
    };

    const fetchData = async () => {
      setLoading(true);
      await getMatches();
      setLoading(false);
      if (!isFirstLoad.current) {
        scrollToTop(); // 첫 로드 시에는 최상단으로 이동하지 않음
      }
    };

    fetchData();
  }, [summonerId]);

  useEffect(() => {
    if (!isFirstLoad.current) {
      scrollToTop();
    } else {
      isFirstLoad.current = false;
    }
  }, [matches]);

  // Get current matches
  const indexOfLastMatch = currentPage * matchesPerPage;
  const indexOfFirstMatch = indexOfLastMatch - matchesPerPage;
  const currentMatches = matches.slice(indexOfFirstMatch, indexOfLastMatch);

  const scrollToTop = () => {
    window.scrollTo({ top: 0, behavior: "smooth" });
  };

  const paginate = (pageNumber) => {
    setCurrentPage(pageNumber);
    // 페이지네이션 숫자를 누를 때 최상단으로 이동
    scrollToTop();
  };

  const handleItemAverage = () => {
    navigate(`/item_average/${calculateAverageKDA()}`);
  };

  const calculateKDA = (match) => {
    const kda = ((match.kills + match.assists) / match.deaths).toFixed(2);
    return isNaN(kda) ? "Perfect" : kda;
  };

  const calculateAverageKDA = () => {
    const totalKills = matches.reduce((sum, match) => {
      const kills = match.kills;
      return sum + (isNaN(kills) ? 0 : kills);
    }, 0);

    const averageKills = (totalKills / matches.length).toFixed(2);
    return isNaN(averageKills) ? "Perfect" : averageKills;
  };

  return (
    <div className='match-detail'>
      {loading ? (
        <Audio />
      ) : (
        <>
          <h2>{state.gameName} 님</h2>
          <p className='match-info'>티어: {state.tier}</p>
          <p className='match-info'>레벨: {state.accountLevel}</p>
          <p style={{ color: "darkblue", fontWeight: "800" }}>
            {state.gameName}님의 전체 매치 평균 KILL 횟수 :{" "}
            {calculateAverageKDA()}
          </p>
          <p>
            승리한 매치에서 {state.gameName} 님보다 많은 KILL을 달성한 회원은
            어떨까요?{" "}
            <button style={{ height: "40px" }} onClick={handleItemAverage}>
              클릭하여 알아보기
            </button>
          </p>

          <h3>참가한 매치</h3>
          <ul>
            {currentMatches.map((match) => (
              <Link
                style={{ textDecoration: "none", color: "inherit" }}
                to={{
                  pathname: `/match_detail/${match.matchId}`,
                }}
              >
                <li key={match.matchId} className='participant_list'>
                  <p style={{ fontWeight: "700" }}>매치 ID: {match.matchId}</p>

                  <div>
                    <p style={{ fontSize: "14px" }}>
                      {summonerId}님의 KDA: {calculateKDA(match)}
                    </p>
                    <div style={{ display: "flex", flexDirection: "row" }}>
                      <p>Kills: </p>
                      <p className='kda' style={{ marginRight: "5px" }}>
                        {match.kills}
                      </p>
                      <p> Deaths:</p>
                      <p className='kda' style={{ marginRight: "5px" }}>
                        {" "}
                        {match.deaths}
                      </p>
                      <p>Assists:</p>{" "}
                      <p className='kda' style={{ marginRight: "5px" }}>
                        {match.assists}
                      </p>
                    </div>
                  </div>
                  <div>
                    <p style={{ lineHeight: "0px" }}>
                      선택한 챔피언: {match.selectedChampion}{" "}
                    </p>
                    <img
                      src={`/img/${match.selectedChampionImage}`}
                      alt={match.selectedChampion}
                      className='champion-image'
                      style={{ width: "65px", height: "65px" }}
                    />
                  </div>
                </li>
              </Link>
            ))}
          </ul>

          <ul className='pagination'>
            {Array.from(
              { length: Math.ceil(matches.length / matchesPerPage) },
              (_, index) => (
                <li
                  style={{ fontSize: "20px" }}
                  key={index}
                  onClick={() => paginate(index + 1)}
                >
                  {index + 1}
                </li>
              ),
            )}
          </ul>
        </>
      )}
    </div>
  );
};

export default SearchResultPage;
