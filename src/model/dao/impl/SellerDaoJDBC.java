package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

	// conexão banco de dados

	private Connection conn;

	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Seller findById(Integer id) {
		// TODO Auto-generated method stub
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement(
					"SELECT seller .*,department.Name as DepName " + "FROM seller INNER JOIN department "
							+ "ON seller.DepartmentId = departmentId " + "WHERE seller.Id = ? ");
			st.setInt(1, id); // primeiro ? vai receber o id
			rs = st.executeQuery(); // executando o comando

			if (rs.next()) {
				// instância o departamento
				Department dep = instantiateDepartment(rs); 
				Seller obj = instantiateSeller(rs, dep);
				return obj; // retornando o objeto completo
			}
			
			return null; // se não teve nenhum registro de vendedor ele vai retornar null

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
			
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}
	// método auxiliar para instanciar seller
	private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
		// TODO Auto-generated method stub
		Seller obj = new Seller(); // criando o objeto seller
		// colocando os valores da tabela
		obj.setId(rs.getInt("id"));
		obj.setName(rs.getString("Name"));
		obj.setEmail(rs.getString("Email"));
		obj.setBaseSalary(rs.getDouble("BaseSalary"));
		obj.setBirthDate(rs.getDate("BirthDate"));
		obj.setDepartment(dep); // fazendo uma junção de objeto, ele já está declarado no código então não precisa usar get
		return obj;
	}

	// método auxiliar para instanciar departament
	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		Department dep = new Department();
		dep.setId(rs.getInt("DepartmentId")); // colocando o valor no objeto através da table
		dep.setName(rs.getString("DepName")); // colocando o valor no objeto através da table
		return dep;
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
