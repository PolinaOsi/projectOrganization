package projectOrganization.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import projectOrganization.entity.Departments;

@Data
@NoArgsConstructor
public class DepartmentsModel {
    private Integer id_department;
    private Integer id_platoon;

    public static DepartmentsModel toModel(Departments department) {
        DepartmentsModel model = new DepartmentsModel();
        model.setId_department(department.getId_department());
        model.setId_platoon(department.getPlatoons().getId_platoon());

        return model;
    }
}
