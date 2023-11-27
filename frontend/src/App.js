/** @format */

import logo from "./logo.svg";
import { Routes, Route } from "react-router-dom";
import "./App.css";
import Search from "./component/Search";

function App() {
  return (
    <div>
      <Routes>
        <Route path='/' element={<Search />} />
      </Routes>
    </div>
  );
}

export default App;
