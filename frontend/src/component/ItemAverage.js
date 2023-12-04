/** @format */

// ItemAverage.js

import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import axios from "axios";
import "./ItemAverage.css";

const ItemAverage = () => {
  const mockData = {
    response: [
      {
        matchId: "KR_6763622125",
        itemPurchasedCnt: 2,
      },
      {
        matchId: "KR_6764815928",
        itemPurchasedCnt: 3,
      },
      {
        matchId: "KR_6765784656",
        itemPurchasedCnt: 11,
      },
    ],
  };
  const [itemData, setItemData] = useState(mockData.response);

  //   useEffect(() => {
  //     const fetchData = async () => {
  //       try {
  //         const response = await axios.get("/api/item-average"); // API 엔드포인트에 따라 수정
  //         setItemData(response.data.response);
  //       } catch (error) {
  //         console.error("Error fetching item data:", error);
  //       }
  //     };

  //     fetchData();
  //   }, []);

  const renderAverageItems = () => {
    return itemData.map((item, index) => (
      <div key={index} className='item-container'>
        <Link
          style={{ textDecoration: "none", color: "inherit" }}
          to={{
            pathname: `/match_detail/${item.matchId}`,
          }}
        >
          <p className='linkP'>매치 ID: {item.matchId}</p>
          <p>아이템 구매 횟수: {item.itemPurchasedCnt}</p>
        </Link>
      </div>
    ));
  };

  return (
    <div className='item-average'>
      <h2>Item Purchase Averages</h2>
      {renderAverageItems()}
    </div>
  );
};

export default ItemAverage;
