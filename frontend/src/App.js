/** @format */

import logo from "./logo.svg";
import { Routes, Route } from "react-router-dom";
import "./App.css";
import Search from "./component/Search";
import SearchResultPage from "./component/SearchResultPage";
import MatchDetailPage from "./component/MatchDetailPage";
import AdminPage from "./component/AdminPage";

function App() {
  return (
    <div>
      <Routes>
        <Route path='/' element={<Search />} />
        <Route path='/summoner/matches' element={<MatchDetailPage />} />
        <Route path='/admin' element={<AdminPage />} />
        <Route
          path='/summoner/matches/match_id'
          element={<MatchDetailPage />}
        />
      </Routes>
    </div>
  );
}

export default App;
