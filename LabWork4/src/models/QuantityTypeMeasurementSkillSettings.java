package com.app.skill.assessment.models;

import lombok.Data;

@Data
public class QuantityTypeMeasurementSkillSettings {
    private Long idConnection;
    private Long idPosition;
    private String namePosition;
    private Long idSkill;
    private String nameSkill;
    private Long idTypeMeasurement;
    private String nameTypeMeasurement;
    private Double minValue;
    private Long idInterval;
    private int minValueInterval;
    private int maxValueInterval;
}
