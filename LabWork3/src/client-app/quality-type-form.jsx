import React, { useState, useEffect } from "react";

const QualityTypeForm = () => {
   return (
      <div>
          <button
            onClick={() =>
              setQualitativeValues((prevValues) => [
                ...prevValues,
                { value: "", order: prevValues.length + 1 },
              ])
            }
          >
            Добавить значение
          </button>
          <br />
          {qualitativeValues.map((value, index) => (
            <div key={index}>
              <input
                type="text"
                value={value.value}
                placeholder="Значение"
                onChange={(e) =>
                  setQualitativeValues((prevValues) => {
                    const newValues = [...prevValues];
                    newValues[index].value = e.target.value;
                    return newValues;
                  })
                }
              />
              <input
                type="number"
                value={value.order}
                placeholder="Порядковый номер"
                onChange={(e) =>
                  setQualitativeValues((prevValues) => {
                    const newValues = [...prevValues];
                    newValues[index].order = parseInt(e.target.value);
                    return newValues;
                  })
                }
              />
            </div>
          ))}
        </div>
   )
}

export default QualityTypeForm;