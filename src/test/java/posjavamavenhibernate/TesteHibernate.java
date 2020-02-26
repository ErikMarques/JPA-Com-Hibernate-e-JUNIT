package posjavamavenhibernate;

import java.util.List;

import org.junit.Test;

import dao.DaoGeneric;
import model.TelefoneUser;
import model.UsuarioPessoa;

public class TesteHibernate {

	@Test
	public void testeHibernateUtil() {

		// Instânciando o objeto DaoGeneric passando a entidade UsuarioPessoa
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

		UsuarioPessoa pessoa = new UsuarioPessoa();

		pessoa.setIdade(45);
		pessoa.setLogin("teste");
		pessoa.setNome("Dan");
		pessoa.setSenha("123");
		pessoa.setSobrenome("Marques");
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
		UsuarioPessoa pessoa = daoGeneric.pesquisar2(9L, UsuarioPessoa.class);

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
	@SuppressWarnings("unchecked")
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
	@SuppressWarnings("unchecked")
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

	// Passando parâmetros para a lista com queryes condicionas customizada, mais de
	// uma opção de customização do sql
	@Test
	public void testeQueryListParameter() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		@SuppressWarnings("unchecked")
		List<UsuarioPessoa> list = daoGeneric.getEntityManager()
				.createQuery("from UsuarioPessoa where nome = :nome and sobrenome = :sobrenome")// Pode ser usado or,
																								// and e etc
				.setParameter("nome", "Erik").setParameter("sobrenome", "Marques").getResultList();

		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}
	}

	@Test
	public void testeQuerySomaIdade() {// Exemplo de soma de todas as idades contidas na tabela.
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		// Este retorno Long vai variar de acordo com o banco de dados que é utilizado.
		// A função NUM retorna a soma de valores de uma coluna.
		// A função AVG retorna a média de valores de uma coluna.
		Long somaIdade = (Long) daoGeneric.getEntityManager().createQuery("select sum(u.idade) from UsuarioPessoa u")
				.getSingleResult();

		System.out.println("Soma de todas as idades: " + somaIdade);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testeNameQuery1() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createNamedQuery("UsuarioPessoa.todos")
				.getResultList();

		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);

		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testeNameQuery2() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createNamedQuery("UsuarioPessoa.buscaPorNome")
				.setParameter("nome", "Luis Paulo").getResultList();

		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);

		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testeGravaTelefone() {
		DaoGeneric daoGeneric = new DaoGeneric();

		UsuarioPessoa pessoa = (UsuarioPessoa) daoGeneric.pesquisar2(10L, UsuarioPessoa.class);

		TelefoneUser telefoneUser = new TelefoneUser();

		telefoneUser.setTipo("Casa");
		telefoneUser.setNumero("10101010");
		telefoneUser.setUsuarioPessoa(pessoa);

		daoGeneric.salvar(telefoneUser);

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testeConsultaTelefones() {
		DaoGeneric daoGeneric = new DaoGeneric();

		UsuarioPessoa pessoa = (UsuarioPessoa) daoGeneric.pesquisar2(10L, UsuarioPessoa.class);

		for (TelefoneUser fone : pessoa.getTelefoneUsers()) {
			System.out.println(fone);
			System.out.println(fone.getNumero());
			System.out.println(fone.getTipo());
			System.out.println(fone.getUsuarioPessoa().getNome());
			System.out.println("--------------------------------------------------------------");

		}

	}
}
