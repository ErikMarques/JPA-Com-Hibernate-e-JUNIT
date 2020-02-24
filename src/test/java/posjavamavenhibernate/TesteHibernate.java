package posjavamavenhibernate;

import java.util.List;

import org.junit.Test;

import dao.DaoGeneric;
import model.UsuarioPessoa;

public class TesteHibernate {

	@Test
	public void testeHibernateUtil() {

		// Instânciando o objeto DaoGeneric passando a entidade UsuarioPessoa
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

		UsuarioPessoa pessoa = new UsuarioPessoa();

		pessoa.setIdade(45);
		pessoa.setLogin("teste");
		pessoa.setNome("Erik 5");
		pessoa.setSenha("123");
		pessoa.setSobrenome("teste");
		pessoa.setEmail("teste@teste.com.br");

		daoGeneric.salvar(pessoa);
	}

	@Test
	public void testeBuscar() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa pessoa = new UsuarioPessoa();
		pessoa.setId(1L);

		pessoa = daoGeneric.pesquisar(pessoa);

		System.out.println(pessoa);
	}

	@Test
	public void testeBuscar2() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa pessoa = daoGeneric.pesquisar2(2L, UsuarioPessoa.class);

		System.out.println(pessoa);

		// System.out.println("Projeto Maven - Hibernate Rodando!");
		// HibernateUtil.geEntityManager();
	}

	@Test
	public void testeUpdate() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa pessoa = daoGeneric.pesquisar2(2L, UsuarioPessoa.class);
		pessoa.setIdade(99);
		pessoa.setNome("Nome atualizado hibernate");
		pessoa.setSenha("123mudar");

		daoGeneric.updateMerge(pessoa);

		System.out.println(pessoa);

	}

	@Test
	public void testeDelete() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa pessoa = daoGeneric.pesquisar2(1L, UsuarioPessoa.class);
		daoGeneric.deletarPorId(pessoa);
		System.out.println(pessoa);
	}

	@Test
	public void testeConsultar() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

		List<UsuarioPessoa> list = daoGeneric.listar(UsuarioPessoa.class);

		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
			System.out.println("------------------------------------------------------------");
		}
	}

	// Teste QHL
	@Test
	public void testeQueryList() {
		// Sempre iniciar o DaoGeneric
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		// Carregando uma lista de UsuarioPessoa
		List<UsuarioPessoa> list = daoGeneric.getEntityManager()
				.createQuery(" from UsuarioPessoa where nome = 'Erik 5'").getResultList();
		// Iterando o objeto pessoa com um foreach
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}
	}

	// Retorna todos os ids cadastrados na tabela, os métodos podem ser alterados
	// para que a função seja outra.
	@Test
	public void testeQueryListMaxResult() {
		// Sempre iniciar o DaoGeneric
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		// Carregando uma lista de UsuarioPessoa
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createQuery(" from UsuarioPessoa order by id")
				.setMaxResults(3).getResultList();
		// Iterando o objeto pessoa com um foreach
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}
	}

	// Passando parâmetros para a lista com queryes condicionas customizada, mais de uma opção de customização do sql
	@Test
	public void testeQueryListParameter() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		@SuppressWarnings("unchecked")
		List<UsuarioPessoa> list = daoGeneric.getEntityManager()
				.createQuery("from UsuarioPessoa where nome = :nome and sobrenome = :sobrenome")//Pode ser usado or, and e etc
				.setParameter("nome", "Erik").setParameter("sobrenome", "Marques").getResultList();

		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}
	}
}
