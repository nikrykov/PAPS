package com.app.skill.assessment;

import com.app.skill.assessment.models.QualityValuesSkill;
import com.app.skill.assessment.models.QuantityTypeMeasurementSkillSettings;
import com.app.skill.assessment.models.QuantityValuesSkill;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {
    private final SkillRepo skillRepo;
    public SkillService(SkillRepo skillRepository) {
        this.skillRepo = skillRepository;
    }

    public List<QuantityTypeMeasurementSkillSettings> getQuantityMeasurementSkillsByPosition(Long idPosition) {
        List<QuantityTypeMeasurementSkillSettings> skillsList = skillRepo.getQuantityMeasurementSkillsByPosition(idPosition);
        return skillsList;
    }

    public List<QuantityTypeMeasurementSkillSettings> getQuantityMeasurementSkillsByPositionSkill(Long idPosition, Long idSkill) {
        List<QuantityTypeMeasurementSkillSettings> skillsList = skillRepo.getQuantityMeasurementSkillsByPositionSkill(idPosition, idSkill);
        return skillsList;
    }

    public void saveQuantityMeasurementSkills(QuantityValuesSkill quantitySkill) {
        skillRepo.saveQuantityMeasurementSkill(quantitySkill);
    }

    public void saveQualityMeasurementSkill(QualityValuesSkill qualityValuesSkill) {
        skillRepo.saveQualityMeasurementSkill(qualityValuesSkill);
    }

    public void updateQuantityMeasurementSkill(QuantityValuesSkill quantitySkill) {
        skillRepo.updateQuantityMeasurementSkill(quantitySkill);
    }

    public void updateQualityMeasurementSkill(QualityValuesSkill qualityValuesSkill) {
        skillRepo.updateQualityMeasurementSkill(qualityValuesSkill);
    }

    public void deleteQuantityMeasurementSkill(Long idConnection) {
        skillRepo.deleteQuantityMeasurementSkill(idConnection);
    }

    public void deleteQualityMeasurementSkill(Long idConnection) {
        skillRepo.deleteQualityMeasurementSkill(idConnection);
    }
}
