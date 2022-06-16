package projectOrganization.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import projectOrganization.entity.Technics;

@Data
@NoArgsConstructor
public class TechnicsModel {
    private Integer id_technic;
    private String name_technic;
    private Integer count_technic;
    private Integer id_unit;

    public static TechnicsModel toModel(Technics technic) {
        TechnicsModel model = new TechnicsModel();

        model.setId_technic(technic.getId_technic());
        model.setName_technic(technic.getName_technic());
        model.setCount_technic(technic.getCount_technic());
        model.setId_unit(technic.getId_unit());
        return model;
    }
}
