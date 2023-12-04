/** @format */

// EditUser.js

import React, { useState, useEffect } from "react";
import "./EditUser.css";

const mockData = {
  gameAccounts: [
    {
      gameName: "Noob piss off",
      tier: "CHALLENGER",
      accountLevel: 300,
    },
    {
      gameName: "NS Calix",
      tier: "CHALLENGER",
      accountLevel: 859,
    },
    {
      gameName: "NEED HER",
      tier: "MASTER",
      accountLevel: 843,
    },
    {
      gameName: "No way out",
      tier: "MASTER",
      accountLevel: 118,
    },
    {
      gameName: "Naind",
      tier: "MASTER",
      accountLevel: 102,
    },
  ],
};

const EditItem = () => {
  const [isAddFormVisible, setIsAddFormVisible] = useState(false);
  const [isEditFormVisible, setIsEditFormVisible] = useState(false);
  const [isDeleteFormVisible, setIsDeleteFormVisible] = useState(false);
  const [existingAccount, setExistingAccount] = useState(false);
  const [gameName, setGameName] = useState("");
  const [newGameName, setNewGameName] = useState("");
  const [tier, setTier] = useState("");
  const [accountLevel, setAccountLevel] = useState("");
  const [editing, setEditing] = useState(false);
  const [canTouch, setCanTouch] = useState(false);
  const [gameAccounts, setGameAccounts] = useState([]);

  const handleInputChange = (e) => {
    setGameName(e.target.value);
  };

  const handleNewGameNameChange = (e) => {
    setNewGameName(e.target.value);
  };

  const handleTierChange = (e) => {
    setTier(e.target.value);
  };

  const handleAccountLevelChange = (e) => {
    setAccountLevel(e.target.value);
  };

  const handleAdd = () => {
    const newGameAccount = {
      gameName: newGameName,
      tier,
      accountLevel,
    };

    setGameAccounts([...gameAccounts, newGameAccount]);
    clearInputs();
  };

  const handleEdit = () => {
    if (canTouch) {
      const editedGameAccounts = gameAccounts.map((account) =>
        account.gameName === gameName
          ? { ...account, gameName: newGameName, tier, accountLevel }
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

  const handleDelete = () => {
    const updatedGameAccounts = gameAccounts.filter(
      (account) => account.gameName !== gameName,
    );

    setGameAccounts(updatedGameAccounts);
    clearInputs();
  };

  const handleEditClick = (account) => {
    setGameName(account.gameName);
    setNewGameName(account.gameName);
    setTier(account.tier);
    setAccountLevel(account.accountLevel);
    setEditing(true);
  };

  // 수정/삭제 시 회원 존재 확인
  const checkExistedName = () => {
    setExistingAccount(
      mockData.gameAccounts.find((account) => account.gameName === gameName),
    );
    if (!existingAccount) {
      alert("존재하지 않는 게임 이름입니다.");
      setGameName("");
    }
  };

  // 수정/등록 시 새 이름 중복 여부 확인
  const checkSameName = () => {
    setExistingAccount(
      mockData.gameAccounts.find((account) => account.gameName === gameName),
    );
    if (existingAccount) {
      alert("이미 존재하는 게임 이름입니다.");
      setNewGameName("");
    } else {
      alert("사용 가능한 게임 이름입니다.");
    }
  };

  const clearInputs = () => {
    setGameName("");
    setNewGameName("");
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
              value={newGameName}
              onChange={handleNewGameNameChange}
            />
            <label>Tier:</label>
            <input type='text' value={tier} onChange={handleTierChange} />
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
            <input type='text' value={gameName} onChange={handleInputChange} />
            <button onClick={checkExistedName}>수정할 게임 이름 확인</button>

            <label>New Game Name:</label>
            <input
              type='text'
              value={newGameName}
              onChange={handleNewGameNameChange}
            />
            <button onClick={checkSameName}>중복 여부 확인</button>

            <label>Tier:</label>
            <input type='text' value={tier} onChange={handleTierChange} />
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
            <input type='text' value={gameName} onChange={handleInputChange} />
            <button onClick={checkExistedName}>게임 이름 확인</button>

            <button onClick={handleDelete}>Delete</button>
          </>
        )}
      </div>
      <div>
        <h2 style={{ marginTop: "40px" }}>게임 계정 정보</h2>
        <ul>
          {gameAccounts.map((account) => (
            <li key={account.gameName}>
              <span>{account.gameName}</span>
              <span>{account.tier}</span>
              <span>{account.accountLevel}</span>
              <button onClick={() => handleEditClick(account)}>Edit</button>
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
};

export default EditItem;
