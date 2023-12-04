/** @format */

import logo from "./logo.svg";
import { Routes, Route } from "react-router-dom";
import "./App.css";
import Search from "./component/Search";
import SearchResultPage from "./component/SearchResultPage";
import MatchDetailPage from "./component/MatchDetailPage";
import AdminPage from "./component/AdminPage";
import SummonerListPage from "./component/SummonerListPage";

function App() {
  return (
    <div>
      <Routes>
        <Route path='/admin' element={<AdminPage />} />
        <Route path='/' element={<Search />} />
        <Route path='/summoner-list' element={<SummonerListPage />} />
        <Route
          path='/search-result/:summonerId'
          element={<MatchDetailPage />}
        />
      </Routes>
    </div>
  );
}

export default App;
