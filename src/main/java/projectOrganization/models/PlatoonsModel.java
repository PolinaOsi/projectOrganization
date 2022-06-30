package projectOrganization.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import projectOrganization.entity.Platoons;

@Data
@NoArgsConstructor
public class PlatoonsModel {
    private Integer id_platoon;
    private Integer id_company;

    public static PlatoonsModel toModel(Platoons platoon) {
        PlatoonsModel model = new PlatoonsModel();

        model.setId_platoon(platoon.getCompanies().getId_company());
        model.setId_company(platoon.getId_platoon());
        return model;
    }
}
