package projectOrganization.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import projectOrganization.entity.Employees;

@Data
@NoArgsConstructor
public class EmployeesModel {
    private Integer id_employee;
    private String name_employee;
    private String surname_employee;
    private String patronymic_employee;
    private Integer id_boss;
    private String date_birth;
    private Integer id_association;
    private Integer id_rank;
    private Integer id_army;
    private Integer id_unit;
    private Integer id_department;
    private String characteristic;

    public static EmployeesModel toModel(Employees employee) {
        EmployeesModel model = new EmployeesModel();
        model.setId_employee(employee.getId_employee());
        model.setName_employee(employee.getName_employee());
        model.setSurname_employee(employee.getSurname_employee());
        model.setPatronymic_employee(employee.getPatronymic_employee());
        model.setId_boss(employee.getEmployee().getId_employee());
        model.setDate_birth(employee.getDate_birth());
        model.setId_association(employee.getAssociation().getId_association());
        model.setId_rank(employee.getRanks().getId_rank());
        model.setId_army(employee.getArmy().getId_army());
        model.setId_unit(employee.getMilitary_unit().getId_unit());
        model.setId_department(employee.getDepartment().getId_department());
        model.setCharacteristic(employee.getCharacteristic());
        return model;
    }
}
