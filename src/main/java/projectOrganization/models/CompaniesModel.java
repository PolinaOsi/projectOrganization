package projectOrganization.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import projectOrganization.entity.Companies;

@Data
@NoArgsConstructor
public class CompaniesModel {
    private Integer id_company;
    private Integer id_unit;

    public static CompaniesModel toModel(Companies company) {
        CompaniesModel model = new CompaniesModel();
        model.setId_company(company.getId_company());
        model.setId_unit(company.getMilitary_units().getId_unit());

        return model;
    }
}
