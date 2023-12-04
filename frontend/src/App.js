/** @format */

import logo from "./logo.svg";
import { Routes, Route } from "react-router-dom";
import "./App.css";
import Search from "./component/Search";
import SearchResultPage from "./component/SearchResultPage";
import MatchDetailPage from "./component/MatchDetailPage";
import AdminPage from "./component/AdminPage";
import SummonerListPage from "./component/SummonerListPage";
import EditUser from "./component/EditUser";
import EditItem from "./component/EditItem";
import ItemAverage from "./component/ItemAverage";
import Champion from "./component/Champion";

function App() {
  return (
    <div>
      <Routes>
        <Route path='/admin' element={<AdminPage />} />
        <Route path='/' element={<Search />} />
        <Route
          path='/summoner_list/:searchKeyword'
          element={<SummonerListPage />}
        />
        <Route path='/match_list/:summonerId' element={<SearchResultPage />} />
        <Route path='/match_detail/:matchId' element={<MatchDetailPage />} />
        <Route path='/user_edit' element={<EditUser />} />
        <Route path='/item_edit' element={<EditItem />} />
        <Route path='/item_average/:kills' element={<ItemAverage />} />
        <Route path='/champ/:champName' element={<Champion />} />
      </Routes>
    </div>
  );
}

export default App;
