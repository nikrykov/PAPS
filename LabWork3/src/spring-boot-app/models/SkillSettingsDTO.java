package app.assessing.skills;

import jakarta.persistence.Id;
import lombok.Data;
import java.util.Date;

@Data
public class SkillSettingsDTO {
  @Id
  private int Id
  private String skill;
  private String position;
  private String skillType;
  private String minValue;
  private String maxValue;
  private List<String> qualitativeValues;
}