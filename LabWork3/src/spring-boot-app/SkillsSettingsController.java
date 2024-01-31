package app.assessing.skills;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SkillsSettingsController {
  private final SkillsSettingsService skillsSettingsService;

  public SkillsSettingsController(SkillsSettingsService skillsSettingsService) {
    this.skillsSettingsService = skillsSettingsService;
  }

  @GetMapping("/positions")
  public List<Position> getAllPositions() {
    return skillsSettingsService.getAllPositions();
  }

  @GetMapping("/skillTypes")
  public List<SkillType> getAllSkillTypes() {
    return skillsSettingsService.getAllSkillTypes();
  }

  @PostMapping("/saveSkillSettings")
  public void saveSkillSettings(@RequestBody SkillSettingsDTO skillSettingsDTO) {
    String skill = skillSettingsDTO.getSkill();
    String position = skillSettingsDTO.getPosition();
    String skillType = skillSettingsDTO.getSkillType();
    String minValue = skillSettingsDTO.getMinValue();
    String maxValue = skillSettingsDTO.getMaxValue();
    List<String> qualitativeValues = skillSettingsDTO.getQualitativeValues();

    skillsSettingsService.saveSkillSettings(skill, position, skillType, minValue, maxValue, qualitativeValues);
  }
}