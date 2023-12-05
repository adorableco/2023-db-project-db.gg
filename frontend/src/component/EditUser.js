/** @format */

// EditUser.js

import React, { useState, useEffect } from "react";
import "./EditUser.css";
import axios from "axios";

const mockData = {
  gameAccounts: [
    {
      summonerName: "Noob piss off",
      Tier: "CHALLENGER",
      accountLevel: 300,
    },
    {
      summonerName: "NS Calix",
      Tier: "CHALLENGER",
      accountLevel: 859,
    },
    {
      summonerName: "NEED HER",
      Tier: "MASTER",
      accountLevel: 843,
    },
    {
      summonerName: "No way out",
      Tier: "MASTER",
      accountLevel: 118,
    },
    {
      summonerName: "Naind",
      Tier: "MASTER",
      accountLevel: 102,
    },
  ],
};

const EditUser = () => {
  const [isAddFormVisible, setIsAddFormVisible] = useState(false);
  const [isEditFormVisible, setIsEditFormVisible] = useState(false);
  const [isDeleteFormVisible, setIsDeleteFormVisible] = useState(false);
  const [existingAccount, setExistingAccount] = useState(false);
  const [summonerName, setsummonerName] = useState("");
  const [newsummonerName, setNewsummonerName] = useState("");
  const [newGameAccountId, setNewGameAccountId] = useState("");
  const [updatedGameAccounts, setUpdatedGameAccounts] = useState([]);
  const [Tier, setTier] = useState("");
  const [accountLevel, setAccountLevel] = useState("");
  const [editing, setEditing] = useState(false);
  const [canTouch, setCanTouch] = useState(false);
  const [gameAccounts, setGameAccounts] = useState([]);

  const handleInputChange = (e) => {
    setsummonerName(e.target.value);
  };

  const handleNewsummonerNameChange = (e) => {
    setNewsummonerName(e.target.value);
  };

  const handleTierChange = (e) => {
    setTier(e.target.value);
  };

  const handleAccountLevelChange = (e) => {
    setAccountLevel(e.target.value);
  };

  //api 연결해야 할 곳
  const handleAdd = async () => {
    try {
      await axios
        .post(`/api`, {
          uniqueGameAccountId: 672,
          summonerName: "새로운계정",
          accountLevel: "80",
          Tier: "CHALLENGER",
        })
        .then((res) => {
          // 중복 여부 체크
          // if(res.data == "이미 존재하는 사용자입니다."){
          //   alert("이미 존재하는 사용자입니다.")
          // }

          console.log(res.data);
        });
      const newGameAccount = {
        uniqueGameAccountId: newGameAccountId,
        summonerName: newsummonerName,
        Tier,
        accountLevel,
      };

      setGameAccounts([...gameAccounts, newGameAccount]);
      clearInputs();
    } catch {
      console.error("Adding User Failed!");
    }
  };

  //api 연결해야 할 곳
  const handleEdit = () => {
    if (canTouch) {
      const editedGameAccounts = gameAccounts.map((account) =>
        account.summonerName === summonerName
          ? { ...account, summonerName: newsummonerName, Tier, accountLevel }
          : account,
      );

      setGameAccounts(editedGameAccounts);
      setEditing(false);
      clearInputs();
    } else {
      alert("'게임 이름 확인 버튼'을 눌러 존재 여부를 확인하세요.");
      clearInputs();
    }
  };

  //api 연결해야 할 곳
  const handleDelete = async () => {
    try {
      await axios.delete(`/?`).then(() => {
        alert("삭제가 완료되었습니다.");
        // const updatedGameAccounts = gameAccounts.filter(
        //   (account) => account.summonerName !== summonerName,
        // );
      });
    } catch {
      console.error("Deleting User Failed!");
    }

    setGameAccounts(updatedGameAccounts);
    clearInputs();
  };

  const handleEditClick = (account) => {
    setsummonerName(account.summonerName);
    setNewsummonerName(account.summonerName);
    setTier(account.Tier);
    setAccountLevel(account.accountLevel);
    setEditing(true);
  };

  // 수정/삭제 시 회원 존재 확인
  const checkExistedName = () => {
    setExistingAccount(
      mockData.gameAccounts.find(
        (account) => account.summonerName === summonerName,
      ),
    );
    if (!existingAccount) {
      alert("존재하지 않는 게임 이름입니다.");
      setsummonerName("");
    }
  };

  // 수정/등록 시 새 이름 중복 여부 확인
  const checkSameName = () => {
    setExistingAccount(
      mockData.gameAccounts.find(
        (account) => account.summonerName === summonerName,
      ),
    );
    if (existingAccount) {
      alert("이미 존재하는 게임 이름입니다.");
      setNewsummonerName("");
    } else {
      alert("사용 가능한 게임 이름입니다.");
    }
  };

  const clearInputs = () => {
    setsummonerName("");
    setNewsummonerName("");
    setTier("");
    setAccountLevel("");
    setCanTouch(false);
  };

  useEffect(() => {
    setGameAccounts(mockData.gameAccounts);
  }, []);

  const toggleForms = (formType) => {
    clearInputs();
    setIsAddFormVisible(formType === "add" ? !isAddFormVisible : false);
    setIsEditFormVisible(formType === "edit" ? !isEditFormVisible : false);
    setIsDeleteFormVisible(
      formType === "delete" ? !isDeleteFormVisible : false,
    );
  };

  return (
    <div>
      <h1>사용자 추가/수정/삭제</h1>
      <div>
        <button onClick={() => toggleForms("add")}>Add Account</button>

        <button onClick={() => toggleForms("edit")}>Edit Account</button>

        <button onClick={() => toggleForms("delete")}>Delete Account</button>
      </div>
      <div>
        {isAddFormVisible && (
          <>
            <label>Game Name:</label>
            <input
              type='text'
              value={newsummonerName}
              onChange={handleNewsummonerNameChange}
            />
            <label>Tier:</label>
            <input type='text' value={Tier} onChange={handleTierChange} />
            <label>Account Level:</label>
            <input
              type='number'
              value={accountLevel}
              onChange={handleAccountLevelChange}
            />
            <button onClick={handleAdd}>Add</button>
          </>
        )}

        {isEditFormVisible && (
          <>
            <label>Game Name:</label>
            <input
              type='text'
              value={summonerName}
              onChange={handleInputChange}
            />
            <button onClick={checkExistedName}>수정할 게임 이름 확인</button>

            <label>New Game Name:</label>
            <input
              type='text'
              value={newsummonerName}
              onChange={handleNewsummonerNameChange}
            />
            <button onClick={checkSameName}>중복 여부 확인</button>

            <label>Tier:</label>
            <input type='text' value={Tier} onChange={handleTierChange} />
            <label>Account Level:</label>
            <input
              type='number'
              value={accountLevel}
              onChange={handleAccountLevelChange}
            />
            <button onClick={handleEdit}>Edit</button>
          </>
        )}

        {isDeleteFormVisible && (
          <>
            <label>Game Name:</label>
            <input
              type='text'
              value={summonerName}
              onChange={handleInputChange}
            />
            <button onClick={checkExistedName}>게임 이름 확인</button>

            <button onClick={handleDelete}>Delete</button>
          </>
        )}
      </div>
      <div>
        <h2 style={{ marginTop: "40px" }}>게임 계정 정보</h2>
        <ul>
          {gameAccounts.map((account) => (
            <li key={account.summonerName}>
              <span>{account.summonerName}</span>
              <span>{account.Tier}</span>
              <span>{account.accountLevel}</span>
              <button onClick={() => handleEditClick(account)}>Edit</button>
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
};

export default EditUser;
