package posjavamavenhibernate;

import org.junit.Test;

import dao.DaoGeneric;
import model.UsuarioPessoa;

public class TesteHibernate {

	@Test
	public void testeHibernateUtil() {

		//Instânciando o objeto DaoGeneric passando a entidade UsuarioPessoa
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

		UsuarioPessoa pessoa = new UsuarioPessoa();
		
		pessoa.setIdade(45);
		pessoa.setLogin("teste");
		pessoa.setNome("Erik");
		pessoa.setSenha("123");
		pessoa.setSobrenome("Marques");
		pessoa.setEmail("teste@teste.com.br");
		
		daoGeneric.salvar(pessoa);
		

		// System.out.println("Projeto Maven - Hibernate Rodando!");
		// HibernateUtil.geEntityManager();
	}
}
