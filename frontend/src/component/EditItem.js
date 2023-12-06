/** @format */

// EditItem.js

import React, { useState, useEffect } from "react";
import "./EditUser.css";
import axios from "axios";

const EditItem = () => {
  const [isAddFormVisible, setIsAddFormVisible] = useState(false);
  const [isEditFormVisible, setIsEditFormVisible] = useState(false);
  const [isDeleteFormVisible, setIsDeleteFormVisible] = useState(false);
  const [existingitem, setExistingitem] = useState(false);
  const [name, setName] = useState("");
  const [newname, setNewName] = useState("");
  const [newItemId, setNewItemId] = useState("");
  const [price, setPrice] = useState("");
  const [newDescription, setNewDescription] = useState("");
  const [editing, setEditing] = useState(false);
  const [items, setItems] = useState([]);
  const [updateditems, setUpdateditems] = useState([]);

  const handleInputChange = (e) => {
    setName(e.target.value);
  };

  const handleNewItemChange = (e) => {
    setNewItemId(e.target.value);
  };

  const handleNewnameChange = (e) => {
    setNewName(e.target.value);
  };

  const handlepriceChange = (e) => {
    setPrice(e.target.value);
  };

  const handleNewDescriptionChange = (e) => {
    setNewDescription(e.target.value);
  };

  //api 연결해야 할 곳
  const handleAdd = async () => {
    try {
      await axios
        .post(
          `/phase3/createItem?itemId=${newItemId}&name=${newname}&description=${newDescription}&price=${price}`,
        )
        .then((res) => {
          // 중복 여부 체크
          // if(res.data == "이미 존재하는 아이템입니다."){
          //   alert("이미 존재하는아이템입니다.")
          // }

          console.log(res.data);
        });

      clearInputs();
    } catch {
      console.error("Adding Item Failed!");
    }
  };

  //api 연결해야 할 곳
  const handleEdit = async () => {
    await axios
      .post(
        `/phase3/editItem?itemId=${newItemId}&name=${newname}&description=${newDescription}&price=${price}`,
      )
      .then(() => {
        alert("아이템 정보 수정이 완료되었습니다!");
        setEditing(false);
        clearInputs();
      });
  };

  //api 연결해야 할 곳
  const handleDelete = async () => {
    try {
      await axios.delete(`/phase3/removeItem?itemId=${newItemId}`).then(() => {
        alert("삭제가 완료되었습니다.");
        // const updateditems = items.filter(
        //   (item) => item.name !== name,
        // );
      });
    } catch {
      console.error("Deleting Item Failed!");
    }

    setItems(updateditems);
    clearInputs();
  };

  // // 수정/삭제 시 회원 존재 확인
  // const checkExistedName = () => {
  //   setExistingitem(mockData.items.find((item) => item.name === name));
  //   if (!existingitem) {
  //     alert("존재하지 않는 아이템 이름입니다.");
  //     setName("");
  //   }
  // };

  // 수정/등록 시 새 이름 중복 여부 확인
  // const checkSameName = () => {
  //   setExistingitem(mockData.items.find((item) => item.name === name));
  //   if (existingitem) {
  //     alert("이미 존재하는 아이템 이름입니다.");
  //     setNewName("");
  //   } else {
  //     alert("사용 가능한 아이템 이름입니다.");
  //   }
  // };

  const clearInputs = () => {
    setName("");
    setNewName("");
    setPrice("");
    setNewDescription("");
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
      <h1>아이템 추가/수정/삭제</h1>
      <div>
        <button onClick={() => toggleForms("add")}>Add item</button>

        <button onClick={() => toggleForms("edit")}>Edit item</button>

        <button onClick={() => toggleForms("delete")}>Delete item</button>
      </div>
      <div>
        {isAddFormVisible && (
          <>
            <label>Item Id:</label>
            <input
              type='number'
              value={newItemId}
              onChange={handleNewItemChange}
            />
            <label>Item Name:</label>
            <input type='text' value={newname} onChange={handleNewnameChange} />
            <label>description:</label>
            <input
              type='text'
              value={newDescription}
              onChange={handleNewDescriptionChange}
            />
            <label>price:</label>
            <input type='text' value={price} onChange={handlepriceChange} />

            <button onClick={handleAdd}>Add</button>
          </>
        )}

        {isEditFormVisible && (
          <>
            <label>Item Id:</label>
            <input
              type='number'
              value={newItemId}
              onChange={handleNewItemChange}
            />
            {/* <button onClick={checkExistedName}>수정할 아이템 이름 확인</button>*/}

            <label>New Item Name:</label>
            <input type='text' value={newname} onChange={handleNewnameChange} />
            {/*<button onClick={checkSameName}>중복 여부 확인</button>*/}

            <label>New description:</label>
            <input
              type='text'
              value={newDescription}
              onChange={handleNewDescriptionChange}
            />

            <label>New Price:</label>
            <input type='text' value={price} onChange={handlepriceChange} />

            <button onClick={handleEdit}>Edit</button>
          </>
        )}

        {isDeleteFormVisible && (
          <>
            <label>Item Id:</label>
            <input
              type='number'
              value={newItemId}
              onChange={handleNewItemChange}
            />
            {/*<button onClick={checkExistedName}>아이템 이름 확인</button>*/}

            <button onClick={handleDelete}>Delete</button>
          </>
        )}
      </div>
    </div>
  );
};

export default EditItem;
