package projectOrganization.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import projectOrganization.entity.Constructions;

@Data
@NoArgsConstructor
public class ConstructionsModel {
    private Integer id_construction;
    private String name_construction;
    private Integer id_unit;

    public static ConstructionsModel toModel(Constructions construction) {
        ConstructionsModel model = new ConstructionsModel();

        model.setId_construction(construction.getId_construction());
        model.setName_construction(construction.getName_construction());
        model.setId_unit(construction.getId_unit());
        return model;
    }
}

