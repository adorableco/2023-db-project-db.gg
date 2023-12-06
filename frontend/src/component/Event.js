/** @format */

import React, { useState, useEffect } from "react";
import axios from "axios";
import { useParams } from "react-router-dom";

const Event = () => {
  const [events, setEvents] = useState([]);
  const [currentPage, setCurrentPage] = useState(1); // 추가: 현재 페이지 상태
  const eventsPerPage = 20; // 추가: 페이지당 이벤트 수
  const { eventTime } = useParams();
  const handlePageChange = (pageNumber) => {
    setCurrentPage(pageNumber);
  };

  console.log(events);

  useEffect(() => {
    const fetchEvents = async () => {
      try {
        const response = await axios.get(
          `/phase2/query6?eventTime=${eventTime}`,
        );
        setEvents(response.data.response);
      } catch (error) {
        console.error("Error fetching match details:", error);
      }
    };

    fetchEvents();
  }, []);

  return (
    <div>
      <div>
        {events &&
          events
            .slice(
              (currentPage - 1) * eventsPerPage,
              currentPage * eventsPerPage,
            )
            .map((event, index) => (
              <div key={index} className='event'>
                <p style={{ fontWeight: "800" }}>{event.eventType}</p>
                <p>이벤트가 일어난 시간 : {event.eventTime} (초)</p>
                {/*{event.eventType === "ITEM_PURCHASED" ? (
                  <p>구매한 아이템 : {event.itemName}</p>
                ) : null}*/}
              </div>
            ))}
      </div>

      <ul className='pagination'>
        {events &&
          Array.from(
            {
              length: Math.ceil(events.length / eventsPerPage),
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

export default Event;
