package projectOrganization.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import projectOrganization.entity.Military_units;

@Data
@NoArgsConstructor
public class Military_unitsModel {
    private Integer id_unit;
    private String name_unit;
    private Integer id_association;
    private Integer id_dislocation;

    public static Military_unitsModel toModel(Military_units unit) {
        Military_unitsModel model = new Military_unitsModel();

        model.setId_unit(unit.getId_unit());
        model.setName_unit(unit.getName_unit());
        model.setId_association(unit.getId_association());
        model.setId_dislocation(unit.getId_dislocation());

        return model;
    }
}
