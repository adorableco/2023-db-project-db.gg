/** @format */

import React from "react";
import { Link } from "react-router-dom";

const SearchResultPage = ({ matches }) => {
  return (
    <div>
      <h2>매치 리스트</h2>
      <ul>
        {matches.map((match) => (
          <li key={match.matchId}>
            <Link to={`/match/${match.matchId}`}>{match.matchId}</Link>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default SearchResultPage;
