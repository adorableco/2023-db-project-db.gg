/** @format */

// EditUser.js

import React, { useState, useEffect } from "react";
import "./EditUser.css";
import axios from "axios";

const mockData = {
  items: [
    {
      name: "Noob piss off",
      price: "CHALLENGER",
      description: 300,
    },
    {
      name: "NS Calix",
      price: "CHALLENGER",
      description: 859,
    },
    {
      name: "NEED HER",
      price: "MASTER",
      description: 843,
    },
    {
      name: "No way out",
      price: "MASTER",
      description: 118,
    },
    {
      name: "Naind",
      price: "MASTER",
      description: 102,
    },
  ],
};

const EditItem = () => {
  const [isAddFormVisible, setIsAddFormVisible] = useState(false);
  const [isEditFormVisible, setIsEditFormVisible] = useState(false);
  const [isDeleteFormVisible, setIsDeleteFormVisible] = useState(false);
  const [existingitem, setExistingitem] = useState(false);
  const [name, setName] = useState("");
  const [newname, setNewName] = useState("");
  const [newItemId, setNewItemId] = useState("");
  const [price, setPrice] = useState("");
  const [description, setDescription] = useState("");
  const [editing, setEditing] = useState(false);
  const [canTouch, setCanTouch] = useState(false);
  const [items, setItems] = useState([]);
  const [updateditems, setUpdateditems] = useState([]);

  const handleInputChange = (e) => {
    setName(e.target.value);
  };

  const handleNewnameChange = (e) => {
    setNewName(e.target.value);
  };

  const handlepriceChange = (e) => {
    setPrice(e.target.value);
  };

  const handledescriptionChange = (e) => {
    setDescription(e.target.value);
  };

  //api 연결해야 할 곳
  const handleAdd = async () => {
    try {
      await axios
        .post(`/api`, {
          itemId: 3187,
          name: "새로운아이템",
          description: "설명",
          price: 100,
        })
        .then((res) => {
          // 중복 여부 체크
          // if(res.data == "이미 존재하는 아이템입니다."){
          //   alert("이미 존재하는아이템입니다.")
          // }

          console.log(res.data);
        });
      const newItem = {
        itemId: newItemId,
        name: newname,
        price,
        description,
      };

      setItems([...items, newItem]);
      clearInputs();
    } catch {
      console.error("Adding Item Failed!");
    }
  };

  //api 연결해야 할 곳
  const handleEdit = () => {
    if (canTouch) {
      const editeditems = items.map((item) =>
        item.name === name
          ? { ...item, name: newname, price, description }
          : item,
      );

      setItems(editeditems);
      setEditing(false);
      clearInputs();
    } else {
      alert("'아이템 이름 확인 버튼'을 눌러 존재 여부를 확인하세요.");
      clearInputs();
    }
  };

  //api 연결해야 할 곳
  const handleDelete = async () => {
    try {
      await axios.delete(`/?`).then(() => {
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

  const handleEditClick = (item) => {
    setName(item.name);
    setNewName(item.name);
    setPrice(item.price);
    setDescription(item.description);
    setEditing(true);
  };

  // 수정/삭제 시 회원 존재 확인
  const checkExistedName = () => {
    setExistingitem(mockData.items.find((item) => item.name === name));
    if (!existingitem) {
      alert("존재하지 않는 아이템 이름입니다.");
      setName("");
    }
  };

  // 수정/등록 시 새 이름 중복 여부 확인
  const checkSameName = () => {
    setExistingitem(mockData.items.find((item) => item.name === name));
    if (existingitem) {
      alert("이미 존재하는 아이템 이름입니다.");
      setNewName("");
    } else {
      alert("사용 가능한 아이템 이름입니다.");
    }
  };

  const clearInputs = () => {
    setName("");
    setNewName("");
    setPrice("");
    setDescription("");
    setCanTouch(false);
  };

  useEffect(() => {
    setItems(mockData.items);
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
      <h1>아이템 추가/수정/삭제</h1>
      <div>
        <button onClick={() => toggleForms("add")}>Add item</button>

        <button onClick={() => toggleForms("edit")}>Edit item</button>

        <button onClick={() => toggleForms("delete")}>Delete item</button>
      </div>
      <div>
        {isAddFormVisible && (
          <>
            <label>Item Name:</label>
            <input type='text' value={newname} onChange={handleNewnameChange} />
            <label>price:</label>
            <input type='text' value={price} onChange={handlepriceChange} />
            <label>item Level:</label>
            <input
              type='number'
              value={description}
              onChange={handledescriptionChange}
            />
            <button onClick={handleAdd}>Add</button>
          </>
        )}

        {isEditFormVisible && (
          <>
            <label>Item Name:</label>
            <input type='text' value={name} onChange={handleInputChange} />
            <button onClick={checkExistedName}>수정할 아이템 이름 확인</button>

            <label>New Item Name:</label>
            <input type='text' value={newname} onChange={handleNewnameChange} />
            <button onClick={checkSameName}>중복 여부 확인</button>

            <label>price:</label>
            <input type='text' value={price} onChange={handlepriceChange} />
            <label>item Level:</label>
            <input
              type='number'
              value={description}
              onChange={handledescriptionChange}
            />
            <button onClick={handleEdit}>Edit</button>
          </>
        )}

        {isDeleteFormVisible && (
          <>
            <label>Item Name:</label>
            <input type='text' value={name} onChange={handleInputChange} />
            <button onClick={checkExistedName}>아이템 이름 확인</button>

            <button onClick={handleDelete}>Delete</button>
          </>
        )}
      </div>
      <div>
        <h2 style={{ marginTop: "40px" }}>아이템 정보</h2>
        <ul>
          {items.map((item) => (
            <li key={item.name}>
              <span>이름 : {item.name}</span>
              <span>가격 : {item.price}</span>
              <span>설명 : {item.description}</span>
              <button onClick={() => handleEditClick(item)}>Edit</button>
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
};

export default EditItem;
