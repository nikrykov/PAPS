import React, { useState, useEffect } from "react";
import axios from "axios";

const App = () => {
  const [positions, setPositions] = useState([]);
  const [skillTypes, setSkillTypes] = useState([]);
  const [selectedPosition, setSelectedPosition] = useState("");
  const [selectedSkillType, setSelectedSkillType] = useState("");

  // Запрос списка должностей и типов навыков с сервера
  useEffect(() => {
    axios.get("/api/positions").then((response) => {
      setPositions(response.data);
    });

    axios.get("/api/skilltypes").then((response) => {
      setSkillTypes(response.data);
    });
  }, []);

  // Отправка данных формы на сервер
  const submitForm = () => {
    const formValues = {
      position: selectedPosition,
      skillType: selectedSkillType,
      minValue: minValue,
      maxValue: maxValue,
      qualitativeValues: qualitativeValues,
    };

    // Отправка данных на сервер
    axios.post("/api/formdata", formValues).then((response) => {
      console.log(response.data);
    });
  };

  return (
    <div>
      <h1>Форма навыков</h1>
      <div>
        <label>Должность:</label>
        <select
          value={selectedPosition}
          onChange={(e) => setSelectedPosition(e.target.value)}
        >
          <option value="">Выберите должность</option>
          {positions.map((position) => (
            <option key={position.id} value={position.value}>
              {position.label}
            </option>
          ))}
        </select>
      </div>

      <div>
        <label>Тип навыка:</label>
        <select value={selectedSkillType} onChange={handleSkillTypeChange}>
          <option value="">Выберите тип навыка</option>
          {skillTypes.map((skillType) => (
            <option key={skillType.id} value={skillType.value}>
              {skillType.label}
            </option>
          ))}
        </select>
      </div>

      {selectedSkillType === "quantity" && <QuantityTypeForm/>}

      {selectedSkillType === "qualitative" && <QualityTypeForm/>}

      <button onClick={submitForm}>Добавить навык для должности</button>
    </div>
  );
};

export default App;