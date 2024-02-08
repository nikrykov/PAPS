package com.app.skill.assessment;

import com.app.skill.assessment.models.QualityValuesSkill;
import com.app.skill.assessment.models.QuantityTypeMeasurementSkillSettings;
import com.app.skill.assessment.models.QuantityValuesSkill;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SkillController {
    private final SkillService skillService;
    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping("/settingsQuantitySkills/positionId={idPosition}")
    public List<QuantityTypeMeasurementSkillSettings> getSettingsQuantitySkillsByPosition(@PathVariable Long idPosition) {
        List<QuantityTypeMeasurementSkillSettings> listQuantityMeasurementSkills = skillService.getQuantityMeasurementSkillsByPosition(idPosition);
        return listQuantityMeasurementSkills;
    }

    @GetMapping("/settingsQuantitySkills/positionId={idPosition}/skillId={idSkill}")
    public List<QuantityTypeMeasurementSkillSettings> getSettingsQuantitySkillsByPositionSkill(@PathVariable Long idPosition, @PathVariable Long idSkill) {
        List<QuantityTypeMeasurementSkillSettings> listQuantityMeasurementSkills = skillService.getQuantityMeasurementSkillsByPositionSkill(idPosition, idSkill);
        return listQuantityMeasurementSkills;
    }

    @PostMapping("/save_quantity_skill")
    public String saveQuantityMeasurementSkill(@RequestBody QuantityValuesSkill quantityValuesSkill) {
        skillService.saveQuantityMeasurementSkills(quantityValuesSkill);
        return "Настройка навыка с количественным измерением успешно добавлена.";
    }

    @PostMapping("/save_quality_skill")
    public String saveQualityMeasurementSkill(@RequestBody QualityValuesSkill qualityValuesSkill) {
        skillService.saveQualityMeasurementSkill(qualityValuesSkill);
        return "Настройка навыка с качественным измерением успешно добавлена.";
    }

    @PutMapping("/update_quantity_skill")
    public String updateQuantityMeasurementSkill(@RequestBody QuantityValuesSkill quantityValuesSkill) {
        skillService.updateQuantityMeasurementSkill(quantityValuesSkill);
        return "Настройка навыка с количественным измерением успешно обновлена.";
    }

    @PutMapping("/update_quality_skill")
    public String updateQualityMeasurementSkill(@RequestBody QualityValuesSkill qualityValuesSkill) {
        skillService.updateQualityMeasurementSkill(qualityValuesSkill);
        return "Настройка навыка с качественным измерением успешно обновлена.";
    }

    @DeleteMapping("/deleteQuantitySkill/id_connection={idConnection}")
    public String deleteQuantityMeasurementSkill(@PathVariable Long idConnection) {
        skillService.deleteQuantityMeasurementSkill(idConnection);
        return "Настройка навыка с количественным измерением успешно удалена.";
    }

    @DeleteMapping("/deleteQualitySkill/id_connection={idConnection}")
    public String deleteQualityMeasurementSkill(@PathVariable Long idConnection) {
        skillService.deleteQualityMeasurementSkill(idConnection);
        return "Настройка навыка с качественным измерением успешно удалена.";
    }
}
