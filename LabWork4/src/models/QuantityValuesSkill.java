package com.app.skill.assessment.models;

import lombok.Data;

@Data
public class QuantityValuesSkill {
    private Long idInterval;
    private Long idConnection;
    private int minValue;
    private int maxValue;
}
