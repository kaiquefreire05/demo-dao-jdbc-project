package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Main2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao(); // conectando ao banco de dados, na tabela department
		
		/*System.out.println("TEST 1 - Insert");
		Department newDep = new Department(null, "Music");
		departmentDao.insert(newDep);
		System.out.println("Inserted! New id: " + newDep.getId()); */
		
		System.out.println("TEST 2 - Update");
		Department dep2 = departmentDao.findById(5); // o objeto vai receber os atributos do campo de dados com id 5
		dep2.setName("Food");
		departmentDao.update(dep2);
		System.out.println("Update completed");
		
		System.out.println("TEST 3 - findById");
		Department dep = departmentDao.findById(1);
		System.out.println(dep);
	}

}
