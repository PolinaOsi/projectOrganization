package projectOrganization.dto;

import lombok.Data;

@Data
public class TechnicsOutDTO {
    private Integer id_technic;
    private String category;
    private String name_technic;
    private Integer count_technic;
    private Integer id_unit;
}
