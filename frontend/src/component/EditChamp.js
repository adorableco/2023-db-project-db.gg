/** @format */

// EditChamp.js

import React, { useState, useEffect } from "react";
import "./EditUser.css";
import axios from "axios";

const EditChamp = () => {
  const [isAddFormVisible, setIsAddFormVisible] = useState(false);
  const [isEditFormVisible, setIsEditFormVisible] = useState(false);
  const [isDeleteFormVisible, setIsDeleteFormVisible] = useState(false);
  const [existingchamp, setExistingchamp] = useState(false);
  const [champId, setchampId] = useState("");
  const [newchampId, setNewchampId] = useState("");
  const [newChampchampId, setNewChampchampId] = useState("");
  const [updatedchampions, setUpdatedchampions] = useState([]);
  const [champName, setChampName] = useState("");
  const [champPhoto, setChampPhoto] = useState("");
  const [editing, setEditing] = useState(false);
  const [champions, setChampions] = useState([]);

  const handleInputChange = (e) => {
    setchampId(e.target.value);
  };

  const handleNewchampIdChange = (e) => {
    setNewchampId(e.target.value);
  };

  const handlechampNameChange = (e) => {
    setChampName(e.target.value);
  };

  const handlechampPhotoChange = (e) => {
    setChampPhoto(e.target.value);
  };

  //api 연결해야 할 곳
  const handleAdd = async () => {
    try {
      await axios
        .post(
          `/phase3/createChampion?uniqueChampId=${newchampId}&champName=${champName}&champPhoto=${champPhoto}`,
        )
        .then((res) => {
          alert("챔피언 추가가 완료되었습니다!");
          console.log(res.data);

          // 중복 여부 체크
          // if(res.data == "이미 존재하는 챔피언입니다."){
          //   alert("이미 존재하는 챔피언입니다.")
          // }
        });

      clearInputs();
    } catch {
      console.error("Adding Champ Failed!");
    }
  };

  //api 연결해야 할 곳
  const handleEdit = async () => {
    try {
      await axios
        .post(
          `/phase3/editChampion?uniqueChampId=${newchampId}&champName=${champName}&champPhoto=${champPhoto}`,
        )
        .then(() => {
          alert("챔피언 정보 수정이 완료되었습니다.");

          setEditing(false);
          clearInputs();
        });
    } catch {
      console.error("Editing Champ Failed!");
    }
  };

  //api 연결해야 할 곳
  const handleDelete = async () => {
    try {
      await axios
        .delete(`/phase3/removeChampion?uniqueChampId=${champId}`)
        .then(() => {
          alert("삭제가 완료되었습니다.");
          // const updatedchampions = champions.filter(
          //   (champ) => champ.champId !== champId,
          // );
        });
    } catch {
      console.error("Deleting Champ Failed!");
    }

    setChampions(updatedchampions);
    clearInputs();
  };

  const handleEditClick = (champ) => {
    setchampId(champ.champId);
    setNewchampId(champ.champId);
    setChampName(champ.champName);
    setChampPhoto(champ.champPhoto);
    setEditing(true);
  };

  // 수정/삭제 시 회원 존재 확인
  // const checkExistedName = () => {
  //   setExistingchamp(
  //     mockData.champions.find((champ) => champ.champId === champId),
  //   );
  //   if (!existingchamp) {
  //     alert("존재하지 않는 챔피언 이름입니다.");
  //     setchampId("");
  //   }
  // };

  // 수정/등록 시 새 이름 중복 여부 확인
  // const checkSameName = () => {
  //   setExistingchamp(
  //     mockData.champions.find((champ) => champ.champId === champId),
  //   );
  //   if (existingchamp) {
  //     alert("이미 존재하는 챔피언 이름입니다.");
  //     setNewchampId("");
  //   } else {
  //     alert("사용 가능한 챔피언 이름입니다.");
  //   }
  // };

  const clearInputs = () => {
    setchampId("");
    setNewchampId("");
    setChampName("");
    setChampPhoto("");
  };

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
      <h1>챔피언 추가/수정/삭제</h1>
      <div>
        <button onClick={() => toggleForms("add")}>Add champ</button>

        <button onClick={() => toggleForms("edit")}>Edit champ</button>

        <button onClick={() => toggleForms("delete")}>Delete champ</button>
      </div>
      <div>
        {isAddFormVisible && (
          <>
            <label>Champ Id:</label>
            <input
              type='number'
              value={newchampId}
              onChange={handleNewchampIdChange}
            />
            <label>champName:</label>
            <input
              type='text'
              value={champName}
              onChange={handlechampNameChange}
            />
            <label>champ Photo:</label>
            <p>문자열 형태의 이미지 주소를 입력하세요.</p>
            <input
              type='text'
              value={champPhoto}
              onChange={handlechampPhotoChange}
            />
            <button onClick={handleAdd}>Add</button>
          </>
        )}

        {isEditFormVisible && (
          <>
            <label>Champ Id:</label>
            <input
              type='number'
              value={newchampId}
              onChange={handleNewchampIdChange}
            />
            {/*<button onClick={checkSameName}>중복 여부 확인</button>*/}

            <label>New Champ Name:</label>
            <input
              type='text'
              value={champName}
              onChange={handlechampNameChange}
            />
            <label>Champ Photo:</label>
            <input
              type='text'
              value={champPhoto}
              onChange={handlechampPhotoChange}
            />
            <button onClick={handleEdit}>Edit</button>
          </>
        )}

        {isDeleteFormVisible && (
          <>
            <label>Champ Id:</label>
            <input type='text' value={champId} onChange={handleInputChange} />
            {/*<button onClick={checkExistedName}>챔피언 이름 확인</button>*/}

            <button onClick={handleDelete}>Delete</button>
          </>
        )}
      </div>
      <div>
        <h2 style={{ marginTop: "40px" }}>챔피언 정보</h2>
        <ul>
          {champions.map((champ) => (
            <li key={champ.champId}>
              <span>{champ.champId}</span>
              <span>{champ.champName}</span>
              <span>{champ.champPhoto}</span>
              <button onClick={() => handleEditClick(champ)}>Edit</button>
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
};

export default EditChamp;
