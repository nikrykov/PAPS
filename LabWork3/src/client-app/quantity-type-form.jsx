import React, { useState, useEffect } from "react";
import axios from "axios";

const QuantityTypeForm = () => {
  return (
      <div>
          <label>Минимальное значение:</label>
          <input
            type="number"
            value={minValue}
            onChange={(e) => setMinValue(e.target.value)}
          />
          <br />
          <label>Максимальное значение:</label>
          <input
            type="number"
            value={maxValue}
            onChange={(e) => setMaxValue(e.target.value)}
          />
        </div>
  );
};

export default QuantityTypeForm;